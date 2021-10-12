package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.appnext.base.b.d;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.ads.AdRequest;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

public final class HlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private static final Pattern REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern REGEX_AUDIO = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern REGEX_AUTOSELECT = compileBooleanAttrPattern("AUTOSELECT");
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern REGEX_CHANNELS = Pattern.compile("CHANNELS=\"(.+?)\"");
    private static final Pattern REGEX_CHARACTERISTICS = Pattern.compile("CHARACTERISTICS=\"(.+?)\"");
    private static final Pattern REGEX_CLOSED_CAPTIONS = Pattern.compile("CLOSED-CAPTIONS=\"(.+?)\"");
    private static final Pattern REGEX_CODECS = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern REGEX_DEFAULT = compileBooleanAttrPattern("DEFAULT");
    private static final Pattern REGEX_FORCED = compileBooleanAttrPattern("FORCED");
    private static final Pattern REGEX_FRAME_RATE = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern REGEX_GROUP_ID = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern REGEX_IMPORT = Pattern.compile("IMPORT=\"(.+?)\"");
    private static final Pattern REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern REGEX_IV = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern REGEX_KEYFORMATVERSIONS = Pattern.compile("KEYFORMATVERSIONS=\"(.+?)\"");
    private static final Pattern REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern REGEX_MEDIA_TITLE = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
    private static final Pattern REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
    private static final Pattern REGEX_NAME = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern REGEX_SUBTITLES = Pattern.compile("SUBTITLES=\"(.+?)\"");
    private static final Pattern REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern REGEX_URI = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern REGEX_VALUE = Pattern.compile("VALUE=\"(.+?)\"");
    private static final Pattern REGEX_VARIABLE_REFERENCE = Pattern.compile("\\{\\$([a-zA-Z0-9\\-_]+)\\}");
    private static final Pattern REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern REGEX_VIDEO = Pattern.compile("VIDEO=\"(.+?)\"");
    private final HlsMasterPlaylist masterPlaylist;

    public HlsPlaylistParser() {
        this(HlsMasterPlaylist.EMPTY);
    }

    public HlsPlaylistParser(HlsMasterPlaylist hlsMasterPlaylist) {
        this.masterPlaylist = hlsMasterPlaylist;
    }

    @Override // com.google.android.exoplayer2.upstream.ParsingLoadable.Parser
    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        String trim;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayDeque arrayDeque = new ArrayDeque();
        try {
            if (checkPlaylistHeader(bufferedReader)) {
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        trim = readLine.trim();
                        if (!trim.isEmpty()) {
                            if (trim.startsWith("#EXT-X-STREAM-INF")) {
                                arrayDeque.add(trim);
                                HlsMasterPlaylist parseMasterPlaylist = parseMasterPlaylist(new LineIterator(arrayDeque, bufferedReader), uri.toString());
                                Util.closeQuietly(bufferedReader);
                                return parseMasterPlaylist;
                            } else if (trim.startsWith("#EXT-X-TARGETDURATION") || trim.startsWith("#EXT-X-MEDIA-SEQUENCE") || trim.startsWith("#EXTINF") || trim.startsWith("#EXT-X-KEY") || trim.startsWith("#EXT-X-BYTERANGE") || trim.equals("#EXT-X-DISCONTINUITY") || trim.equals("#EXT-X-DISCONTINUITY-SEQUENCE") || trim.equals("#EXT-X-ENDLIST")) {
                                arrayDeque.add(trim);
                            } else {
                                arrayDeque.add(trim);
                            }
                        }
                    } else {
                        Util.closeQuietly(bufferedReader);
                        throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                    }
                }
                arrayDeque.add(trim);
                return parseMediaPlaylist(this.masterPlaylist, new LineIterator(arrayDeque, bufferedReader), uri.toString());
            }
            throw new UnrecognizedInputFormatException("Input does not start with the #EXTM3U header.", uri);
        } finally {
            Util.closeQuietly(bufferedReader);
        }
    }

    private static boolean checkPlaylistHeader(BufferedReader bufferedReader) throws IOException {
        int read = bufferedReader.read();
        if (read == 239) {
            if (!(bufferedReader.read() == 187 && bufferedReader.read() == 191)) {
                return false;
            }
            read = bufferedReader.read();
        }
        int skipIgnorableWhitespace = skipIgnorableWhitespace(bufferedReader, true, read);
        for (int i = 0; i < 7; i++) {
            if (skipIgnorableWhitespace != "#EXTM3U".charAt(i)) {
                return false;
            }
            skipIgnorableWhitespace = bufferedReader.read();
        }
        return Util.isLinebreak(skipIgnorableWhitespace(bufferedReader, false, skipIgnorableWhitespace));
    }

    private static int skipIgnorableWhitespace(BufferedReader bufferedReader, boolean z, int i) throws IOException {
        while (i != -1 && Character.isWhitespace(i) && (z || !Util.isLinebreak(i))) {
            i = bufferedReader.read();
        }
        return i;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static HlsMasterPlaylist parseMasterPlaylist(LineIterator lineIterator, String str) throws IOException {
        Uri uri;
        char c;
        float f;
        int i;
        int i2;
        String str2;
        int i3;
        String str3;
        String str4;
        int i4;
        HashSet hashSet;
        HashMap hashMap;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        boolean z;
        int i5;
        int i6;
        String str5 = str;
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        ArrayList arrayList11 = new ArrayList();
        boolean z2 = false;
        boolean z3 = false;
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            if (next.startsWith("#EXT")) {
                arrayList11.add(next);
            }
            if (next.startsWith("#EXT-X-DEFINE")) {
                hashMap3.put(parseStringAttr(next, REGEX_NAME, hashMap3), parseStringAttr(next, REGEX_VALUE, hashMap3));
            } else if (next.equals("#EXT-X-INDEPENDENT-SEGMENTS")) {
                z2 = true;
            } else if (next.startsWith("#EXT-X-MEDIA")) {
                arrayList9.add(next);
            } else {
                if (next.startsWith("#EXT-X-SESSION-KEY")) {
                    DrmInitData.SchemeData parseDrmSchemeData = parseDrmSchemeData(next, parseOptionalStringAttr(next, REGEX_KEYFORMAT, "identity", hashMap3), hashMap3);
                    if (parseDrmSchemeData != null) {
                        arrayList3 = arrayList8;
                        z = z2;
                        arrayList10.add(new DrmInitData(parseEncryptionScheme(parseStringAttr(next, REGEX_METHOD, hashMap3)), parseDrmSchemeData));
                    } else {
                        arrayList3 = arrayList8;
                        z = z2;
                    }
                } else {
                    arrayList3 = arrayList8;
                    z = z2;
                    if (next.startsWith("#EXT-X-STREAM-INF")) {
                        boolean contains = z3 | next.contains("CLOSED-CAPTIONS=NONE");
                        int parseIntAttr = parseIntAttr(next, REGEX_BANDWIDTH);
                        parseOptionalIntAttr(next, REGEX_AVERAGE_BANDWIDTH, -1);
                        String parseOptionalStringAttr = parseOptionalStringAttr(next, REGEX_CODECS, hashMap3);
                        String parseOptionalStringAttr2 = parseOptionalStringAttr(next, REGEX_RESOLUTION, hashMap3);
                        if (parseOptionalStringAttr2 != null) {
                            String[] split = parseOptionalStringAttr2.split(AvidJSONUtil.KEY_X);
                            int parseInt = Integer.parseInt(split[0]);
                            int parseInt2 = Integer.parseInt(split[1]);
                            if (parseInt <= 0 || parseInt2 <= 0) {
                                parseInt2 = -1;
                                parseInt = -1;
                            }
                            i5 = parseInt2;
                            i6 = parseInt;
                        } else {
                            i6 = -1;
                            i5 = -1;
                        }
                        String parseOptionalStringAttr3 = parseOptionalStringAttr(next, REGEX_FRAME_RATE, hashMap3);
                        float parseFloat = parseOptionalStringAttr3 != null ? Float.parseFloat(parseOptionalStringAttr3) : -1.0f;
                        String parseOptionalStringAttr4 = parseOptionalStringAttr(next, REGEX_VIDEO, hashMap3);
                        String parseOptionalStringAttr5 = parseOptionalStringAttr(next, REGEX_AUDIO, hashMap3);
                        String parseOptionalStringAttr6 = parseOptionalStringAttr(next, REGEX_SUBTITLES, hashMap3);
                        String parseOptionalStringAttr7 = parseOptionalStringAttr(next, REGEX_CLOSED_CAPTIONS, hashMap3);
                        if (lineIterator.hasNext()) {
                            Uri resolveToUri = UriUtil.resolveToUri(str5, replaceVariableReferences(lineIterator.next(), hashMap3));
                            arrayList2 = arrayList10;
                            arrayList4.add(new HlsMasterPlaylist.Variant(resolveToUri, Format.createVideoContainerFormat(Integer.toString(arrayList4.size()), null, "application/x-mpegURL", null, parseOptionalStringAttr, null, parseIntAttr, i6, i5, parseFloat, null, 0, 0), parseOptionalStringAttr4, parseOptionalStringAttr5, parseOptionalStringAttr6, parseOptionalStringAttr7));
                            ArrayList arrayList12 = (ArrayList) hashMap2.get(resolveToUri);
                            if (arrayList12 == null) {
                                arrayList12 = new ArrayList();
                                hashMap2.put(resolveToUri, arrayList12);
                            }
                            arrayList = arrayList11;
                            arrayList12.add(new HlsTrackMetadataEntry.VariantInfo((long) parseIntAttr, parseOptionalStringAttr4, parseOptionalStringAttr5, parseOptionalStringAttr6, parseOptionalStringAttr7));
                            z3 = contains;
                            z2 = z;
                            arrayList8 = arrayList3;
                            arrayList10 = arrayList2;
                            arrayList11 = arrayList;
                        } else {
                            throw new ParserException("#EXT-X-STREAM-INF tag must be followed by another line");
                        }
                    }
                }
                arrayList = arrayList11;
                arrayList2 = arrayList10;
                z2 = z;
                arrayList8 = arrayList3;
                arrayList10 = arrayList2;
                arrayList11 = arrayList;
            }
            arrayList3 = arrayList8;
            arrayList = arrayList11;
            arrayList2 = arrayList10;
            z = z2;
            z2 = z;
            arrayList8 = arrayList3;
            arrayList10 = arrayList2;
            arrayList11 = arrayList;
        }
        ArrayList arrayList13 = new ArrayList();
        HashSet hashSet2 = new HashSet();
        int i7 = 0;
        while (i7 < arrayList4.size()) {
            HlsMasterPlaylist.Variant variant = (HlsMasterPlaylist.Variant) arrayList4.get(i7);
            if (hashSet2.add(variant.url)) {
                Assertions.checkState(variant.format.metadata == null);
                hashMap = hashMap2;
                hashSet = hashSet2;
                arrayList13.add(variant.copyWithFormat(variant.format.copyWithMetadata(new Metadata(new HlsTrackMetadataEntry(null, null, (List) Assertions.checkNotNull(hashMap2.get(variant.url)))))));
            } else {
                hashMap = hashMap2;
                hashSet = hashSet2;
            }
            i7++;
            hashSet2 = hashSet;
            hashMap2 = hashMap;
        }
        List list = null;
        Format format = null;
        int i8 = 0;
        while (i8 < arrayList9.size()) {
            String str6 = (String) arrayList9.get(i8);
            String parseStringAttr = parseStringAttr(str6, REGEX_GROUP_ID, hashMap3);
            String parseStringAttr2 = parseStringAttr(str6, REGEX_NAME, hashMap3);
            String parseOptionalStringAttr8 = parseOptionalStringAttr(str6, REGEX_URI, hashMap3);
            if (parseOptionalStringAttr8 == null) {
                uri = null;
            } else {
                uri = UriUtil.resolveToUri(str5, parseOptionalStringAttr8);
            }
            String parseOptionalStringAttr9 = parseOptionalStringAttr(str6, REGEX_LANGUAGE, hashMap3);
            int parseSelectionFlags = parseSelectionFlags(str6);
            int parseRoleFlags = parseRoleFlags(str6, hashMap3);
            String str7 = parseStringAttr + ":" + parseStringAttr2;
            Metadata metadata = new Metadata(new HlsTrackMetadataEntry(parseStringAttr, parseStringAttr2, Collections.emptyList()));
            String parseStringAttr3 = parseStringAttr(str6, REGEX_TYPE, hashMap3);
            switch (parseStringAttr3.hashCode()) {
                case -959297733:
                    if (parseStringAttr3.equals("SUBTITLES")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -333210994:
                    if (parseStringAttr3.equals("CLOSED-CAPTIONS")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 62628790:
                    if (parseStringAttr3.equals("AUDIO")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 81665115:
                    if (parseStringAttr3.equals("VIDEO")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                HlsMasterPlaylist.Variant variantWithVideoGroup = getVariantWithVideoGroup(arrayList4, parseStringAttr);
                if (variantWithVideoGroup != null) {
                    Format format2 = variantWithVideoGroup.format;
                    String codecsOfType = Util.getCodecsOfType(format2.codecs, 2);
                    int i9 = format2.width;
                    int i10 = format2.height;
                    f = format2.frameRate;
                    str2 = codecsOfType;
                    i2 = i9;
                    i = i10;
                } else {
                    str2 = null;
                    i2 = -1;
                    i = -1;
                    f = -1.0f;
                }
                Format copyWithMetadata = Format.createVideoContainerFormat(str7, parseStringAttr2, "application/x-mpegURL", str2 != null ? MimeTypes.getMediaMimeType(str2) : null, str2, null, -1, i2, i, f, null, parseSelectionFlags, parseRoleFlags).copyWithMetadata(metadata);
                if (uri != null) {
                    arrayList5.add(new HlsMasterPlaylist.Rendition(uri, copyWithMetadata, parseStringAttr, parseStringAttr2));
                }
            } else if (c == 1) {
                HlsMasterPlaylist.Variant variantWithAudioGroup = getVariantWithAudioGroup(arrayList4, parseStringAttr);
                String codecsOfType2 = variantWithAudioGroup != null ? Util.getCodecsOfType(variantWithAudioGroup.format.codecs, 1) : null;
                String mediaMimeType = codecsOfType2 != null ? MimeTypes.getMediaMimeType(codecsOfType2) : null;
                String parseOptionalStringAttr10 = parseOptionalStringAttr(str6, REGEX_CHANNELS, hashMap3);
                if (parseOptionalStringAttr10 != null) {
                    int parseInt3 = Integer.parseInt(Util.splitAtFirst(parseOptionalStringAttr10, "/")[0]);
                    if ("audio/eac3".equals(mediaMimeType) && parseOptionalStringAttr10.endsWith("/JOC")) {
                        mediaMimeType = "audio/eac3-joc";
                    }
                    str3 = mediaMimeType;
                    i3 = parseInt3;
                } else {
                    str3 = mediaMimeType;
                    i3 = -1;
                }
                Format createAudioContainerFormat = Format.createAudioContainerFormat(str7, parseStringAttr2, "application/x-mpegURL", str3, codecsOfType2, null, -1, i3, -1, null, parseSelectionFlags, parseRoleFlags, parseOptionalStringAttr9);
                if (uri == null) {
                    format = createAudioContainerFormat;
                    i8++;
                    str5 = str;
                    arrayList9 = arrayList9;
                    arrayList13 = arrayList13;
                    z3 = z3;
                } else {
                    arrayList6.add(new HlsMasterPlaylist.Rendition(uri, createAudioContainerFormat.copyWithMetadata(metadata), parseStringAttr, parseStringAttr2));
                }
            } else if (c == 2) {
                arrayList7.add(new HlsMasterPlaylist.Rendition(uri, Format.createTextContainerFormat(str7, parseStringAttr2, "application/x-mpegURL", "text/vtt", null, -1, parseSelectionFlags, parseRoleFlags, parseOptionalStringAttr9).copyWithMetadata(metadata), parseStringAttr, parseStringAttr2));
            } else if (c == 3) {
                String parseStringAttr4 = parseStringAttr(str6, REGEX_INSTREAM_ID, hashMap3);
                if (parseStringAttr4.startsWith("CC")) {
                    i4 = Integer.parseInt(parseStringAttr4.substring(2));
                    str4 = "application/cea-608";
                } else {
                    i4 = Integer.parseInt(parseStringAttr4.substring(7));
                    str4 = "application/cea-708";
                }
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(Format.createTextContainerFormat(str7, parseStringAttr2, null, str4, null, -1, parseSelectionFlags, parseRoleFlags, parseOptionalStringAttr9, i4));
                format = format;
                i8++;
                str5 = str;
                arrayList9 = arrayList9;
                arrayList13 = arrayList13;
                z3 = z3;
            }
            format = format;
            i8++;
            str5 = str;
            arrayList9 = arrayList9;
            arrayList13 = arrayList13;
            z3 = z3;
        }
        if (z3) {
            list = Collections.emptyList();
        }
        return new HlsMasterPlaylist(str, arrayList11, arrayList13, arrayList5, arrayList6, arrayList7, arrayList8, format, list, z2, hashMap3, arrayList10);
    }

    private static HlsMasterPlaylist.Variant getVariantWithAudioGroup(ArrayList<HlsMasterPlaylist.Variant> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMasterPlaylist.Variant variant = arrayList.get(i);
            if (str.equals(variant.audioGroupId)) {
                return variant;
            }
        }
        return null;
    }

    private static HlsMasterPlaylist.Variant getVariantWithVideoGroup(ArrayList<HlsMasterPlaylist.Variant> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMasterPlaylist.Variant variant = arrayList.get(i);
            if (str.equals(variant.videoGroupId)) {
                return variant;
            }
        }
        return null;
    }

    private static HlsMediaPlaylist parseMediaPlaylist(HlsMasterPlaylist hlsMasterPlaylist, LineIterator lineIterator, String str) throws IOException {
        String str2;
        TreeMap treeMap;
        DrmInitData drmInitData;
        long j;
        long j2;
        HlsMasterPlaylist hlsMasterPlaylist2 = hlsMasterPlaylist;
        boolean z = hlsMasterPlaylist2.hasIndependentSegments;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        TreeMap treeMap2 = new TreeMap();
        String str3 = "";
        char c = 0;
        int i = 1;
        boolean z2 = z;
        long j3 = -9223372036854775807L;
        long j4 = -9223372036854775807L;
        String str4 = str3;
        boolean z3 = false;
        int i2 = 0;
        String str5 = null;
        String str6 = null;
        long j5 = 0;
        int i3 = 0;
        long j6 = 0;
        int i4 = 1;
        boolean z4 = false;
        DrmInitData drmInitData2 = null;
        long j7 = 0;
        long j8 = 0;
        DrmInitData drmInitData3 = null;
        boolean z5 = false;
        String str7 = null;
        long j9 = -1;
        int i5 = 0;
        long j10 = 0;
        HlsMediaPlaylist.Segment segment = null;
        while (true) {
            long j11 = 0;
            while (lineIterator.hasNext()) {
                String next = lineIterator.next();
                if (next.startsWith("#EXT")) {
                    arrayList2.add(next);
                }
                if (next.startsWith("#EXT-X-PLAYLIST-TYPE")) {
                    String parseStringAttr = parseStringAttr(next, REGEX_PLAYLIST_TYPE, hashMap);
                    if ("VOD".equals(parseStringAttr)) {
                        i2 = 1;
                    } else if ("EVENT".equals(parseStringAttr)) {
                        i2 = 2;
                    }
                } else if (next.startsWith("#EXT-X-START")) {
                    j3 = (long) (parseDoubleAttr(next, REGEX_TIME_OFFSET) * 1000000.0d);
                } else if (next.startsWith("#EXT-X-MAP")) {
                    String parseStringAttr2 = parseStringAttr(next, REGEX_URI, hashMap);
                    String parseOptionalStringAttr = parseOptionalStringAttr(next, REGEX_ATTR_BYTERANGE, hashMap);
                    if (parseOptionalStringAttr != null) {
                        String[] split = parseOptionalStringAttr.split("@");
                        long parseLong = Long.parseLong(split[c]);
                        if (split.length > i) {
                            j7 = Long.parseLong(split[i]);
                        }
                        j = parseLong;
                        j2 = j7;
                    } else {
                        j2 = j7;
                        j = j9;
                    }
                    if (str5 == null || str7 != null) {
                        segment = new HlsMediaPlaylist.Segment(parseStringAttr2, j2, j, str5, str7);
                        c = 0;
                        j7 = 0;
                        j9 = -1;
                    } else {
                        throw new ParserException("The encryption IV attribute must be present when an initialization segment is encrypted with METHOD=AES-128.");
                    }
                } else {
                    if (next.startsWith("#EXT-X-TARGETDURATION")) {
                        j4 = ((long) parseIntAttr(next, REGEX_TARGET_DURATION)) * 1000000;
                    } else if (next.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                        j8 = parseLongAttr(next, REGEX_MEDIA_SEQUENCE);
                        j6 = j8;
                    } else if (next.startsWith("#EXT-X-VERSION")) {
                        i4 = parseIntAttr(next, REGEX_VERSION);
                    } else {
                        if (next.startsWith("#EXT-X-DEFINE")) {
                            String parseOptionalStringAttr2 = parseOptionalStringAttr(next, REGEX_IMPORT, hashMap);
                            if (parseOptionalStringAttr2 != null) {
                                String str8 = hlsMasterPlaylist2.variableDefinitions.get(parseOptionalStringAttr2);
                                if (str8 != null) {
                                    hashMap.put(parseOptionalStringAttr2, str8);
                                }
                            } else {
                                hashMap.put(parseStringAttr(next, REGEX_NAME, hashMap), parseStringAttr(next, REGEX_VALUE, hashMap));
                            }
                        } else if (next.startsWith("#EXTINF")) {
                            str4 = parseOptionalStringAttr(next, REGEX_MEDIA_TITLE, str3, hashMap);
                            j11 = (long) (parseDoubleAttr(next, REGEX_MEDIA_DURATION) * 1000000.0d);
                        } else if (next.startsWith("#EXT-X-KEY")) {
                            String parseStringAttr3 = parseStringAttr(next, REGEX_METHOD, hashMap);
                            String parseOptionalStringAttr3 = parseOptionalStringAttr(next, REGEX_KEYFORMAT, "identity", hashMap);
                            if ("NONE".equals(parseStringAttr3)) {
                                treeMap2.clear();
                                str5 = null;
                                drmInitData3 = null;
                                str7 = null;
                            } else {
                                String parseOptionalStringAttr4 = parseOptionalStringAttr(next, REGEX_IV, hashMap);
                                if (!"identity".equals(parseOptionalStringAttr3)) {
                                    if (str6 == null) {
                                        str6 = parseEncryptionScheme(parseStringAttr3);
                                    }
                                    DrmInitData.SchemeData parseDrmSchemeData = parseDrmSchemeData(next, parseOptionalStringAttr3, hashMap);
                                    if (parseDrmSchemeData != null) {
                                        treeMap2.put(parseOptionalStringAttr3, parseDrmSchemeData);
                                        str7 = parseOptionalStringAttr4;
                                        str5 = null;
                                        drmInitData3 = null;
                                    }
                                } else if ("AES-128".equals(parseStringAttr3)) {
                                    str5 = parseStringAttr(next, REGEX_URI, hashMap);
                                    str7 = parseOptionalStringAttr4;
                                }
                                str7 = parseOptionalStringAttr4;
                                str5 = null;
                            }
                        } else if (next.startsWith("#EXT-X-BYTERANGE")) {
                            String[] split2 = parseStringAttr(next, REGEX_BYTERANGE, hashMap).split("@");
                            j9 = Long.parseLong(split2[0]);
                            if (split2.length > i) {
                                j7 = Long.parseLong(split2[i]);
                            }
                        } else if (next.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                            i3 = Integer.parseInt(next.substring(next.indexOf(58) + i));
                            z3 = true;
                        } else if (next.equals("#EXT-X-DISCONTINUITY")) {
                            i5++;
                        } else if (next.startsWith("#EXT-X-PROGRAM-DATE-TIME")) {
                            if (j5 == 0) {
                                j5 = C.msToUs(Util.parseXsDateTime(next.substring(next.indexOf(58) + i))) - j10;
                            }
                        } else if (next.equals("#EXT-X-GAP")) {
                            c = 0;
                            z5 = true;
                        } else if (next.equals("#EXT-X-INDEPENDENT-SEGMENTS")) {
                            c = 0;
                            z2 = true;
                        } else if (next.equals("#EXT-X-ENDLIST")) {
                            c = 0;
                            z4 = true;
                        } else if (!next.startsWith("#")) {
                            String hexString = str5 == null ? null : str7 != null ? str7 : Long.toHexString(j8);
                            long j12 = j8 + 1;
                            long j13 = j9 == -1 ? 0 : j7;
                            if (drmInitData3 != null || treeMap2.isEmpty()) {
                                treeMap = treeMap2;
                                str2 = str3;
                                drmInitData = drmInitData3;
                            } else {
                                DrmInitData.SchemeData[] schemeDataArr = (DrmInitData.SchemeData[]) treeMap2.values().toArray(new DrmInitData.SchemeData[0]);
                                drmInitData = new DrmInitData(str6, schemeDataArr);
                                if (drmInitData2 == null) {
                                    DrmInitData.SchemeData[] schemeDataArr2 = new DrmInitData.SchemeData[schemeDataArr.length];
                                    treeMap = treeMap2;
                                    str2 = str3;
                                    int i6 = 0;
                                    while (i6 < schemeDataArr.length) {
                                        schemeDataArr2[i6] = schemeDataArr[i6].copyWithData(null);
                                        i6++;
                                        schemeDataArr = schemeDataArr;
                                    }
                                    drmInitData2 = new DrmInitData(str6, schemeDataArr2);
                                } else {
                                    treeMap = treeMap2;
                                    str2 = str3;
                                }
                            }
                            arrayList.add(new HlsMediaPlaylist.Segment(replaceVariableReferences(next, hashMap), segment, str4, j11, i5, j10, drmInitData, str5, hexString, j13, j9, z5));
                            j10 += j11;
                            if (j9 != -1) {
                                j13 += j9;
                            }
                            j7 = j13;
                            hlsMasterPlaylist2 = hlsMasterPlaylist;
                            j9 = -1;
                            j8 = j12;
                            drmInitData3 = drmInitData;
                            treeMap2 = treeMap;
                            str3 = str2;
                            str4 = str3;
                            c = 0;
                            i = 1;
                            z5 = false;
                        }
                        hlsMasterPlaylist2 = hlsMasterPlaylist;
                        treeMap2 = treeMap2;
                        str3 = str3;
                        c = 0;
                        i = 1;
                    }
                    c = 0;
                }
            }
            return new HlsMediaPlaylist(i2, str, arrayList2, j3, j5, z3, i3, j6, i4, j4, z2, z4, j5 != 0, drmInitData2, arrayList);
        }
    }

    private static int parseSelectionFlags(String str) {
        int i = parseOptionalBooleanAttribute(str, REGEX_DEFAULT, false) ? 1 : 0;
        if (parseOptionalBooleanAttribute(str, REGEX_FORCED, false)) {
            i |= 2;
        }
        return parseOptionalBooleanAttribute(str, REGEX_AUTOSELECT, false) ? i | 4 : i;
    }

    private static int parseRoleFlags(String str, Map<String, String> map) {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, REGEX_CHARACTERISTICS, map);
        int i = 0;
        if (TextUtils.isEmpty(parseOptionalStringAttr)) {
            return 0;
        }
        String[] split = Util.split(parseOptionalStringAttr, ",");
        if (Util.contains(split, "public.accessibility.describes-video")) {
            i = AdRequest.MAX_CONTENT_URL_LENGTH;
        }
        if (Util.contains(split, "public.accessibility.transcribes-spoken-dialog")) {
            i |= 4096;
        }
        if (Util.contains(split, "public.accessibility.describes-music-and-sound")) {
            i |= d.fb;
        }
        return Util.contains(split, "public.easy-to-read") ? i | 8192 : i;
    }

    private static DrmInitData.SchemeData parseDrmSchemeData(String str, String str2, Map<String, String> map) throws ParserException {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, REGEX_KEYFORMATVERSIONS, "1", map);
        if ("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed".equals(str2)) {
            String parseStringAttr = parseStringAttr(str, REGEX_URI, map);
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "video/mp4", Base64.decode(parseStringAttr.substring(parseStringAttr.indexOf(44)), 0));
        } else if ("com.widevine".equals(str2)) {
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "hls", Util.getUtf8Bytes(str));
        } else {
            if (!"com.microsoft.playready".equals(str2) || !"1".equals(parseOptionalStringAttr)) {
                return null;
            }
            String parseStringAttr2 = parseStringAttr(str, REGEX_URI, map);
            return new DrmInitData.SchemeData(C.PLAYREADY_UUID, "video/mp4", PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(parseStringAttr2.substring(parseStringAttr2.indexOf(44)), 0)));
        }
    }

    private static String parseEncryptionScheme(String str) {
        return ("SAMPLE-AES-CENC".equals(str) || "SAMPLE-AES-CTR".equals(str)) ? "cenc" : "cbcs";
    }

    private static int parseIntAttr(String str, Pattern pattern) throws ParserException {
        return Integer.parseInt(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static int parseOptionalIntAttr(String str, Pattern pattern, int i) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : i;
    }

    private static long parseLongAttr(String str, Pattern pattern) throws ParserException {
        return Long.parseLong(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static double parseDoubleAttr(String str, Pattern pattern) throws ParserException {
        return Double.parseDouble(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static String parseStringAttr(String str, Pattern pattern, Map<String, String> map) throws ParserException {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, pattern, map);
        if (parseOptionalStringAttr != null) {
            return parseOptionalStringAttr;
        }
        throw new ParserException("Couldn't match " + pattern.pattern() + " in " + str);
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern, Map<String, String> map) {
        return parseOptionalStringAttr(str, pattern, null, map);
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern, String str2, Map<String, String> map) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str2 = matcher.group(1);
        }
        return (map.isEmpty() || str2 == null) ? str2 : replaceVariableReferences(str2, map);
    }

    private static String replaceVariableReferences(String str, Map<String, String> map) {
        Matcher matcher = REGEX_VARIABLE_REFERENCE.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (map.containsKey(group)) {
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(map.get(group)));
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static boolean parseOptionalBooleanAttribute(String str, Pattern pattern, boolean z) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1).equals("YES") : z;
    }

    private static Pattern compileBooleanAttrPattern(String str) {
        return Pattern.compile(str + "=(" + "NO" + "|" + "YES" + ")");
    }

    /* access modifiers changed from: private */
    public static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> queue, BufferedReader bufferedReader) {
            this.extraLines = queue;
            this.reader = bufferedReader;
        }

        @EnsuresNonNullIf(expression = {"next"}, result = true)
        public boolean hasNext() throws IOException {
            String trim;
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = (String) Assertions.checkNotNull(this.extraLines.poll());
                return true;
            }
            do {
                String readLine = this.reader.readLine();
                this.next = readLine;
                if (readLine == null) {
                    return false;
                }
                trim = readLine.trim();
                this.next = trim;
            } while (trim.isEmpty());
            return true;
        }

        public String next() throws IOException {
            if (hasNext()) {
                String str = this.next;
                this.next = null;
                return str;
            }
            throw new NoSuchElementException();
        }
    }
}
