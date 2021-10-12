package androidx.media2.exoplayer.external.extractor.mp4;

import android.util.Pair;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.audio.Ac3Util;
import androidx.media2.exoplayer.external.audio.Ac4Util;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.GaplessInfoHolder;
import androidx.media2.exoplayer.external.extractor.mp4.Atom;
import androidx.media2.exoplayer.external.extractor.mp4.FixedSampleSizeRechunker;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.CodecSpecificDataUtil;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.MimeTypes;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.Util;
import androidx.media2.exoplayer.external.video.AvcConfig;
import androidx.media2.exoplayer.external.video.DolbyVisionConfig;
import androidx.media2.exoplayer.external.video.HevcConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* access modifiers changed from: package-private */
public final class AtomParsers {
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    /* access modifiers changed from: private */
    public interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    private static int getTrackTypeForHdlr(int i) {
        if (i == 1936684398) {
            return 1;
        }
        if (i == 1986618469) {
            return 2;
        }
        if (i == 1952807028 || i == 1935832172 || i == 1937072756 || i == 1668047728) {
            return 3;
        }
        return i == 1835365473 ? 4 : -1;
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long j2;
        Atom.LeafAtom leafAtom2;
        long[] jArr;
        long[] jArr2;
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(1835297121);
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(containerAtomOfType.getLeafAtomOfType(1751411826).data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(containerAtom.getLeafAtomOfType(1953196132).data);
        long j3 = -9223372036854775807L;
        if (j == -9223372036854775807L) {
            leafAtom2 = leafAtom;
            j2 = parseTkhd.duration;
        } else {
            leafAtom2 = leafAtom;
            j2 = j;
        }
        long parseMvhd = parseMvhd(leafAtom2.data);
        if (j2 != -9223372036854775807L) {
            j3 = Util.scaleLargeTimestamp(j2, 1000000, parseMvhd);
        }
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(1835626086).getContainerAtomOfType(1937007212);
        Pair<Long, String> parseMdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(1835296868).data);
        StsdData parseStsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(1937011556).data, parseTkhd.id, parseTkhd.rotationDegrees, (String) parseMdhd.second, drmInitData, z2);
        if (!z) {
            Pair<long[], long[]> parseEdts = parseEdts(containerAtom.getContainerAtomOfType(1701082227));
            jArr = (long[]) parseEdts.second;
            jArr2 = (long[]) parseEdts.first;
        } else {
            jArr2 = null;
            jArr = null;
        }
        if (parseStsd.format == null) {
            return null;
        }
        return new Track(parseTkhd.id, trackTypeForHdlr, ((Long) parseMdhd.first).longValue(), parseMvhd, j3, parseStsd.format, parseStsd.requiredSampleTransformation, parseStsd.trackEncryptionBoxes, parseStsd.nalUnitLengthFieldLength, jArr2, jArr);
    }

    public static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox sampleSizeBox;
        boolean z;
        int i;
        int i2;
        int i3;
        long j;
        int[] iArr;
        long[] jArr;
        Track track2;
        int[] iArr2;
        long[] jArr2;
        int i4;
        int i5;
        int[] iArr3;
        int[] iArr4;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z2;
        int i11;
        int i12;
        int i13;
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(1937011578);
        if (leafAtomOfType != null) {
            sampleSizeBox = new StszSampleSizeBox(leafAtomOfType);
        } else {
            Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(1937013298);
            if (leafAtomOfType2 != null) {
                sampleSizeBox = new Stz2SampleSizeBox(leafAtomOfType2);
            } else {
                throw new ParserException("Track has no sample table size information");
            }
        }
        int sampleCount = sampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(track, new long[0], new int[0], 0, new long[0], new int[0], -9223372036854775807L);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(1937007471);
        if (leafAtomOfType3 == null) {
            leafAtomOfType3 = containerAtom.getLeafAtomOfType(1668232756);
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType3.data;
        ParsableByteArray parsableByteArray2 = containerAtom.getLeafAtomOfType(1937011555).data;
        ParsableByteArray parsableByteArray3 = containerAtom.getLeafAtomOfType(1937011827).data;
        Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(1937011571);
        ParsableByteArray parsableByteArray4 = null;
        ParsableByteArray parsableByteArray5 = leafAtomOfType4 != null ? leafAtomOfType4.data : null;
        Atom.LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(1668576371);
        ParsableByteArray parsableByteArray6 = leafAtomOfType5 != null ? leafAtomOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int readUnsignedIntToInt = parsableByteArray3.readUnsignedIntToInt() - 1;
        int readUnsignedIntToInt2 = parsableByteArray3.readUnsignedIntToInt();
        int readUnsignedIntToInt3 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray6 != null) {
            parsableByteArray6.setPosition(12);
            i = parsableByteArray6.readUnsignedIntToInt();
        } else {
            i = 0;
        }
        int i14 = -1;
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            i2 = parsableByteArray5.readUnsignedIntToInt();
            if (i2 > 0) {
                i14 = parsableByteArray5.readUnsignedIntToInt() - 1;
                parsableByteArray4 = parsableByteArray5;
            }
        } else {
            parsableByteArray4 = parsableByteArray5;
            i2 = 0;
        }
        if (!(sampleSizeBox.isFixedSampleSize() && "audio/raw".equals(track.format.sampleMimeType) && readUnsignedIntToInt == 0 && i == 0 && i2 == 0)) {
            long[] jArr3 = new long[sampleCount];
            int[] iArr5 = new int[sampleCount];
            long[] jArr4 = new long[sampleCount];
            iArr = new int[sampleCount];
            int i15 = i14;
            long j2 = 0;
            long j3 = 0;
            int i16 = 0;
            i3 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = i;
            int i21 = readUnsignedIntToInt3;
            int i22 = readUnsignedIntToInt2;
            int i23 = readUnsignedIntToInt;
            int i24 = i2;
            while (true) {
                if (i16 >= sampleCount) {
                    i8 = i22;
                    i9 = i17;
                    i10 = i18;
                    break;
                }
                long j4 = j3;
                int i25 = i18;
                boolean z3 = true;
                while (i25 == 0) {
                    z3 = chunkIterator.moveNext();
                    if (!z3) {
                        break;
                    }
                    long j5 = chunkIterator.offset;
                    i25 = chunkIterator.numSamples;
                    j4 = j5;
                    i22 = i22;
                    i21 = i21;
                    sampleCount = sampleCount;
                }
                i8 = i22;
                if (!z3) {
                    Log.w("AtomParsers", "Unexpected end of chunk data");
                    jArr3 = Arrays.copyOf(jArr3, i16);
                    iArr5 = Arrays.copyOf(iArr5, i16);
                    jArr4 = Arrays.copyOf(jArr4, i16);
                    iArr = Arrays.copyOf(iArr, i16);
                    sampleCount = i16;
                    i9 = i17;
                    i10 = i25;
                    break;
                }
                if (parsableByteArray6 != null) {
                    while (i19 == 0 && i20 > 0) {
                        i19 = parsableByteArray6.readUnsignedIntToInt();
                        i17 = parsableByteArray6.readInt();
                        i20--;
                    }
                    i19--;
                }
                jArr3[i16] = j4;
                iArr5[i16] = sampleSizeBox.readNextSampleSize();
                if (iArr5[i16] > i3) {
                    i3 = iArr5[i16];
                }
                jArr4[i16] = j2 + ((long) i17);
                iArr[i16] = parsableByteArray4 == null ? 1 : 0;
                if (i16 == i15) {
                    iArr[i16] = 1;
                    i24--;
                    if (i24 > 0) {
                        i15 = parsableByteArray4.readUnsignedIntToInt() - 1;
                    }
                }
                j2 += (long) i21;
                int i26 = i8 - 1;
                if (i26 != 0 || i23 <= 0) {
                    i13 = i21;
                    i12 = i23;
                } else {
                    i26 = parsableByteArray3.readUnsignedIntToInt();
                    i13 = parsableByteArray3.readInt();
                    i12 = i23 - 1;
                }
                i18 = i25 - 1;
                i16++;
                j3 = j4 + ((long) iArr5[i16]);
                i15 = i15;
                i21 = i13;
                sampleCount = sampleCount;
                i17 = i17;
                i23 = i12;
                i22 = i26;
            }
            long j6 = j2 + ((long) i9);
            while (true) {
                if (i20 <= 0) {
                    z2 = true;
                    break;
                } else if (parsableByteArray6.readUnsignedIntToInt() != 0) {
                    z2 = false;
                    break;
                } else {
                    parsableByteArray6.readInt();
                    i20--;
                }
            }
            if (i24 == 0 && i8 == 0 && i10 == 0 && i23 == 0) {
                i11 = i19;
                if (i11 == 0 && z2) {
                    track2 = track;
                    i4 = sampleCount;
                    jArr2 = jArr3;
                    jArr = jArr4;
                    iArr2 = iArr5;
                    j = j6;
                }
            } else {
                i11 = i19;
            }
            track2 = track;
            int i27 = track2.id;
            String str = !z2 ? ", ctts invalid" : "";
            StringBuilder sb = new StringBuilder(str.length() + 262);
            sb.append("Inconsistent stbl box for track ");
            sb.append(i27);
            sb.append(": remainingSynchronizationSamples ");
            sb.append(i24);
            sb.append(", remainingSamplesAtTimestampDelta ");
            sb.append(i8);
            sb.append(", remainingSamplesInChunk ");
            sb.append(i10);
            sb.append(", remainingTimestampDeltaChanges ");
            sb.append(i23);
            sb.append(", remainingSamplesAtTimestampOffset ");
            sb.append(i11);
            sb.append(str);
            Log.w("AtomParsers", sb.toString());
            i4 = sampleCount;
            jArr2 = jArr3;
            jArr = jArr4;
            iArr2 = iArr5;
            j = j6;
        } else {
            track2 = track;
            long[] jArr5 = new long[chunkIterator.length];
            int[] iArr6 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr5[chunkIterator.index] = chunkIterator.offset;
                iArr6[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results rechunk = FixedSampleSizeRechunker.rechunk(Util.getPcmFrameSize(track2.format.pcmEncoding, track2.format.channelCount), jArr5, iArr6, (long) readUnsignedIntToInt3);
            long[] jArr6 = rechunk.offsets;
            int[] iArr7 = rechunk.sizes;
            int i28 = rechunk.maximumSize;
            jArr = rechunk.timestamps;
            iArr = rechunk.flags;
            j = rechunk.duration;
            i3 = i28;
            i4 = sampleCount;
            iArr2 = iArr7;
            jArr2 = jArr6;
        }
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(j, 1000000, track2.timescale);
        if (track2.editListDurations == null || gaplessInfoHolder.hasGaplessInfo()) {
            Util.scaleLargeTimestampsInPlace(jArr, 1000000, track2.timescale);
            return new TrackSampleTable(track, jArr2, iArr2, i3, jArr, iArr, scaleLargeTimestamp);
        }
        if (track2.editListDurations.length == 1 && track2.type == 1 && jArr.length >= 2) {
            long j7 = track2.editListMediaTimes[0];
            i5 = i4;
            long scaleLargeTimestamp2 = Util.scaleLargeTimestamp(track2.editListDurations[0], track2.timescale, track2.movieTimescale) + j7;
            if (canApplyEditWithGaplessInfo(jArr, j, j7, scaleLargeTimestamp2)) {
                long j8 = j - scaleLargeTimestamp2;
                long scaleLargeTimestamp3 = Util.scaleLargeTimestamp(j7 - jArr[0], (long) track2.format.sampleRate, track2.timescale);
                long scaleLargeTimestamp4 = Util.scaleLargeTimestamp(j8, (long) track2.format.sampleRate, track2.timescale);
                if (!(scaleLargeTimestamp3 == 0 && scaleLargeTimestamp4 == 0) && scaleLargeTimestamp3 <= 2147483647L && scaleLargeTimestamp4 <= 2147483647L) {
                    gaplessInfoHolder.encoderDelay = (int) scaleLargeTimestamp3;
                    gaplessInfoHolder.encoderPadding = (int) scaleLargeTimestamp4;
                    Util.scaleLargeTimestampsInPlace(jArr, 1000000, track2.timescale);
                    return new TrackSampleTable(track, jArr2, iArr2, i3, jArr, iArr, Util.scaleLargeTimestamp(track2.editListDurations[0], 1000000, track2.movieTimescale));
                }
            }
        } else {
            i5 = i4;
        }
        if (track2.editListDurations.length == 1 && track2.editListDurations[0] == 0) {
            long j9 = track2.editListMediaTimes[0];
            for (int i29 = 0; i29 < jArr.length; i29++) {
                jArr[i29] = Util.scaleLargeTimestamp(jArr[i29] - j9, 1000000, track2.timescale);
            }
            return new TrackSampleTable(track, jArr2, iArr2, i3, jArr, iArr, Util.scaleLargeTimestamp(j - j9, 1000000, track2.timescale));
        }
        boolean z4 = track2.type == 1;
        int[] iArr8 = new int[track2.editListDurations.length];
        int[] iArr9 = new int[track2.editListDurations.length];
        int i30 = 0;
        boolean z5 = false;
        int i31 = 0;
        int i32 = 0;
        while (i30 < track2.editListDurations.length) {
            long j10 = track2.editListMediaTimes[i30];
            if (j10 != -1) {
                i7 = i5;
                i6 = i3;
                long scaleLargeTimestamp5 = Util.scaleLargeTimestamp(track2.editListDurations[i30], track2.timescale, track2.movieTimescale);
                iArr8[i30] = Util.binarySearchCeil(jArr, j10, true, true);
                iArr9[i30] = Util.binarySearchCeil(jArr, j10 + scaleLargeTimestamp5, z4, false);
                while (iArr8[i30] < iArr9[i30] && (iArr[iArr8[i30]] & 1) == 0) {
                    iArr8[i30] = iArr8[i30] + 1;
                }
                i31 += iArr9[i30] - iArr8[i30];
                z5 = (i32 != iArr8[i30]) | z5;
                i32 = iArr9[i30];
            } else {
                i7 = i5;
                i6 = i3;
            }
            i30++;
            jArr2 = jArr2;
            i5 = i7;
            iArr2 = iArr2;
            i3 = i6;
        }
        int[] iArr10 = iArr2;
        int i33 = 0;
        boolean z6 = true;
        if (i31 == i5) {
            z6 = false;
        }
        boolean z7 = z5 | z6;
        long[] jArr7 = z7 ? new long[i31] : jArr2;
        int[] iArr11 = z7 ? new int[i31] : iArr10;
        int i34 = z7 ? 0 : i3;
        int[] iArr12 = z7 ? new int[i31] : iArr;
        long[] jArr8 = new long[i31];
        long j11 = 0;
        int i35 = 0;
        while (i33 < track2.editListDurations.length) {
            long j12 = track2.editListMediaTimes[i33];
            int i36 = iArr8[i33];
            int i37 = iArr9[i33];
            if (z7) {
                int i38 = i37 - i36;
                iArr3 = iArr9;
                System.arraycopy(jArr2, i36, jArr7, i35, i38);
                iArr4 = iArr10;
                System.arraycopy(iArr4, i36, iArr11, i35, i38);
                System.arraycopy(iArr, i36, iArr12, i35, i38);
            } else {
                iArr3 = iArr9;
                iArr4 = iArr10;
            }
            while (i36 < i37) {
                jArr8[i35] = Util.scaleLargeTimestamp(j11, 1000000, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr[i36] - j12, 1000000, track2.timescale);
                if (z7 && iArr11[i35] > i34) {
                    i34 = iArr4[i36];
                }
                i35++;
                i36++;
                iArr8 = iArr8;
                jArr7 = jArr7;
                i37 = i37;
            }
            j11 += track2.editListDurations[i33];
            i33++;
            iArr8 = iArr8;
            iArr10 = iArr4;
            iArr9 = iArr3;
        }
        return new TrackSampleTable(track, jArr7, iArr11, i34, jArr8, iArr12, Util.scaleLargeTimestamp(j11, 1000000, track2.movieTimescale));
    }

    public static Metadata parseUdta(Atom.LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1835365473) {
                parsableByteArray.setPosition(position);
                return parseUdtaMeta(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    public static Metadata parseMdtaFromMeta(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(1751411826);
        Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(1801812339);
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(1768715124);
        if (leafAtomOfType == null || leafAtomOfType2 == null || leafAtomOfType3 == null || parseHdlr(leafAtomOfType.data) != 1835299937) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType2.data;
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        String[] strArr = new String[readInt];
        for (int i = 0; i < readInt; i++) {
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i] = parsableByteArray.readString(readInt2 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafAtomOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int readInt3 = parsableByteArray2.readInt();
            int readInt4 = parsableByteArray2.readInt() - 1;
            if (readInt4 < 0 || readInt4 >= readInt) {
                StringBuilder sb = new StringBuilder(52);
                sb.append("Skipped metadata with unknown key index: ");
                sb.append(readInt4);
                Log.w("AtomParsers", sb.toString());
            } else {
                MdtaMetadataEntry parseMdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + readInt3, strArr[readInt4]);
                if (parseMdtaMetadataEntryFromIlst != null) {
                    arrayList.add(parseMdtaMetadataEntryFromIlst);
                }
            }
            parsableByteArray2.setPosition(position + readInt3);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1768715124) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i = 16;
        }
        parsableByteArray.skipBytes(i);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                z = true;
                break;
            } else if (parsableByteArray.data[position + i3] != -1) {
                z = false;
                break;
            } else {
                i3++;
            }
        }
        long j = -9223372036854775807L;
        if (z) {
            parsableByteArray.skipBytes(i);
        } else {
            long readUnsignedInt = parseFullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (readUnsignedInt != 0) {
                j = readUnsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && readInt4 == -65536 && readInt5 == 0) {
            i2 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && readInt4 == 65536 && readInt5 == 0) {
            i2 = 270;
        } else if (readInt2 == -65536 && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i2 = 180;
        }
        return new TkhdData(readInt, j, i2);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        parsableByteArray.skipBytes(i);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        StringBuilder sb = new StringBuilder(3);
        sb.append((char) (((readUnsignedShort >> 10) & 31) + 96));
        sb.append((char) (((readUnsignedShort >> 5) & 31) + 96));
        sb.append((char) ((readUnsignedShort & 31) + 96));
        return Pair.create(Long.valueOf(readUnsignedInt), sb.toString());
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i3 = 0; i3 < readInt; i3++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            Assertions.checkArgument(readInt2 > 0, "childAtomSize should be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == 1635148593 || readInt3 == 1635148595 || readInt3 == 1701733238 || readInt3 == 1836070006 || readInt3 == 1752589105 || readInt3 == 1751479857 || readInt3 == 1932670515 || readInt3 == 1987063864 || readInt3 == 1987063865 || readInt3 == 1635135537 || readInt3 == 1685479798 || readInt3 == 1685479729 || readInt3 == 1685481573 || readInt3 == 1685481521) {
                parseVideoSampleEntry(parsableByteArray, readInt3, position, readInt2, i, i2, drmInitData, stsdData, i3);
            } else if (readInt3 == 1836069985 || readInt3 == 1701733217 || readInt3 == 1633889587 || readInt3 == 1700998451 || readInt3 == 1633889588 || readInt3 == 1685353315 || readInt3 == 1685353317 || readInt3 == 1685353320 || readInt3 == 1685353324 || readInt3 == 1935764850 || readInt3 == 1935767394 || readInt3 == 1819304813 || readInt3 == 1936684916 || readInt3 == 778924083 || readInt3 == 1634492771 || readInt3 == 1634492791 || readInt3 == 1970037111 || readInt3 == 1332770163 || readInt3 == 1716281667) {
                parseAudioSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, z, drmInitData, stsdData, i3);
            } else if (readInt3 == 1414810956 || readInt3 == 1954034535 || readInt3 == 2004251764 || readInt3 == 1937010800 || readInt3 == 1664495672) {
                parseTextSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, stsdData);
            } else if (readInt3 == 1667329389) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), "application/x-camera-motion", null, -1, null);
            }
            parsableByteArray.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) throws ParserException {
        parsableByteArray.setPosition(i2 + 8 + 8);
        String str2 = "application/ttml+xml";
        List list = null;
        long j = Long.MAX_VALUE;
        if (i != 1414810956) {
            if (i == 1954034535) {
                int i5 = (i3 - 8) - 8;
                byte[] bArr = new byte[i5];
                parsableByteArray.readBytes(bArr, 0, i5);
                list = Collections.singletonList(bArr);
                str2 = "application/x-quicktime-tx3g";
            } else if (i == 2004251764) {
                str2 = "application/x-mp4-vtt";
            } else if (i == 1937010800) {
                j = 0;
            } else if (i == 1664495672) {
                stsdData.requiredSampleTransformation = 1;
                str2 = "application/x-mp4-cea-608";
            } else {
                throw new IllegalStateException();
            }
        }
        stsdData.format = Format.createTextSampleFormat(Integer.toString(i4), str2, null, -1, 0, str, -1, null, j, list);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, int i5, DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        int i7 = i2;
        DrmInitData drmInitData2 = drmInitData;
        parsableByteArray.setPosition(i7 + 8 + 8);
        parsableByteArray.skipBytes(16);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        int i8 = i;
        if (i8 == 1701733238) {
            Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i7, i3);
            if (parseSampleEntryEncryptionData != null) {
                i8 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData2 == null) {
                    drmInitData2 = null;
                } else {
                    drmInitData2 = drmInitData2.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData.trackEncryptionBoxes[i6] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        String str = null;
        String str2 = null;
        List<byte[]> list = null;
        byte[] bArr = null;
        boolean z = false;
        float f = 1.0f;
        int i9 = -1;
        while (position - i7 < i3) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (readInt == 0 && parsableByteArray.getPosition() - i7 == i3) {
                break;
            }
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1635148611) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                AvcConfig parse = AvcConfig.parse(parsableByteArray);
                list = parse.initializationData;
                stsdData.nalUnitLengthFieldLength = parse.nalUnitLengthFieldLength;
                if (!z) {
                    f = parse.pixelWidthAspectRatio;
                }
                str = "video/avc";
            } else if (readInt2 == 1752589123) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                HevcConfig parse2 = HevcConfig.parse(parsableByteArray);
                list = parse2.initializationData;
                stsdData.nalUnitLengthFieldLength = parse2.nalUnitLengthFieldLength;
                str = "video/hevc";
            } else if (readInt2 == 1685480259 || readInt2 == 1685485123) {
                DolbyVisionConfig parse3 = DolbyVisionConfig.parse(parsableByteArray);
                if (parse3 != null) {
                    str2 = parse3.codecs;
                    str = "video/dolby-vision";
                }
            } else if (readInt2 == 1987076931) {
                Assertions.checkState(str == null);
                str = i8 == 1987063864 ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
            } else if (readInt2 == 1635135811) {
                Assertions.checkState(str == null);
                str = "video/av01";
            } else if (readInt2 == 1681012275) {
                Assertions.checkState(str == null);
                str = "video/3gpp";
            } else if (readInt2 == 1702061171) {
                Assertions.checkState(str == null);
                Pair<String, byte[]> parseEsdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                str = (String) parseEsdsFromParent.first;
                list = Collections.singletonList((byte[]) parseEsdsFromParent.second);
            } else if (readInt2 == 1885434736) {
                f = parsePaspFromParent(parsableByteArray, position2);
                z = true;
            } else if (readInt2 == 1937126244) {
                bArr = parseProjFromParent(parsableByteArray, position2, readInt);
            } else if (readInt2 == 1936995172) {
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray.skipBytes(3);
                if (readUnsignedByte == 0) {
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (readUnsignedByte2 == 0) {
                        i9 = 0;
                    } else if (readUnsignedByte2 == 1) {
                        i9 = 1;
                    } else if (readUnsignedByte2 == 2) {
                        i9 = 2;
                    } else if (readUnsignedByte2 == 3) {
                        i9 = 3;
                    }
                }
            }
            position += readInt;
            i7 = i2;
        }
        if (str != null) {
            stsdData.format = Format.createVideoSampleFormat(Integer.toString(i4), str, str2, -1, -1, readUnsignedShort, readUnsignedShort2, -1.0f, list, i5, f, bArr, i9, null, drmInitData2);
        }
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(1701606260)) == null) {
            return Pair.create(null, null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[readUnsignedIntToInt];
        long[] jArr2 = new long[readUnsignedIntToInt];
        for (int i = 0; i < readUnsignedIntToInt; i++) {
            jArr[i] = parseFullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = parseFullAtomVersion == 1 ? parsableByteArray.readLong() : (long) parsableByteArray.readInt();
            if (parsableByteArray.readShort() == 1) {
                parsableByteArray.skipBytes(2);
            } else {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) throws ParserException {
        int i6;
        int i7;
        int i8;
        String str2;
        DrmInitData drmInitData2;
        int i9;
        int i10;
        String str3;
        int i11 = i2;
        DrmInitData drmInitData3 = drmInitData;
        parsableByteArray.setPosition(i11 + 8 + 8);
        if (z) {
            i6 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(8);
            i6 = 0;
        }
        if (i6 == 0 || i6 == 1) {
            int readUnsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            int readUnsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
            if (i6 == 1) {
                parsableByteArray.skipBytes(16);
            }
            i7 = readUnsignedFixedPoint1616;
            i8 = readUnsignedShort;
        } else if (i6 == 2) {
            parsableByteArray.skipBytes(16);
            i7 = (int) Math.round(parsableByteArray.readDouble());
            i8 = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray.skipBytes(20);
        } else {
            return;
        }
        int position = parsableByteArray.getPosition();
        List list = null;
        int i12 = i;
        if (i12 == 1701733217) {
            Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i11, i3);
            if (parseSampleEntryEncryptionData != null) {
                i12 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData3 == null) {
                    drmInitData3 = null;
                } else {
                    drmInitData3 = drmInitData3.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData.trackEncryptionBoxes[i5] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        DrmInitData drmInitData4 = drmInitData3;
        String str4 = "audio/raw";
        String str5 = i12 == 1633889587 ? "audio/ac3" : i12 == 1700998451 ? "audio/eac3" : i12 == 1633889588 ? "audio/ac4" : i12 == 1685353315 ? "audio/vnd.dts" : (i12 == 1685353320 || i12 == 1685353324) ? "audio/vnd.dts.hd" : i12 == 1685353317 ? "audio/vnd.dts.hd;profile=lbr" : i12 == 1935764850 ? "audio/3gpp" : i12 == 1935767394 ? "audio/amr-wb" : (i12 == 1819304813 || i12 == 1936684916) ? str4 : i12 == 778924083 ? "audio/mpeg" : i12 == 1634492771 ? "audio/alac" : i12 == 1634492791 ? "audio/g711-alaw" : i12 == 1970037111 ? "audio/g711-mlaw" : i12 == 1332770163 ? "audio/opus" : i12 == 1716281667 ? "audio/flac" : null;
        int i13 = i8;
        int i14 = i7;
        byte[] bArr = null;
        while (position - i11 < i3) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1702061171 || (z && readInt2 == 2002876005)) {
                str2 = str4;
                str3 = str5;
                drmInitData2 = drmInitData4;
                i9 = position;
                i10 = readInt;
                int findEsdsPosition = readInt2 == 1702061171 ? i9 : findEsdsPosition(parsableByteArray, i9, i10);
                if (findEsdsPosition != -1) {
                    Pair<String, byte[]> parseEsdsFromParent = parseEsdsFromParent(parsableByteArray, findEsdsPosition);
                    str5 = (String) parseEsdsFromParent.first;
                    bArr = (byte[]) parseEsdsFromParent.second;
                    if ("audio/mp4a-latm".equals(str5)) {
                        Pair<Integer, Integer> parseAacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                        i14 = ((Integer) parseAacAudioSpecificConfig.first).intValue();
                        i13 = ((Integer) parseAacAudioSpecificConfig.second).intValue();
                    }
                    position = i10 + i9;
                    i11 = i2;
                    drmInitData4 = drmInitData2;
                    str4 = str2;
                }
            } else {
                if (readInt2 == 1684103987) {
                    parsableByteArray.setPosition(position + 8);
                    stsdData.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData4);
                } else if (readInt2 == 1684366131) {
                    parsableByteArray.setPosition(position + 8);
                    stsdData.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData4);
                } else if (readInt2 == 1684103988) {
                    parsableByteArray.setPosition(position + 8);
                    stsdData.format = Ac4Util.parseAc4AnnexEFormat(parsableByteArray, Integer.toString(i4), str, drmInitData4);
                } else if (readInt2 == 1684305011) {
                    str3 = str5;
                    drmInitData2 = drmInitData4;
                    str2 = str4;
                    stsdData.format = Format.createAudioSampleFormat(Integer.toString(i4), str5, null, -1, -1, i13, i14, null, drmInitData2, 0, str);
                    i10 = readInt;
                    i9 = position;
                } else {
                    str2 = str4;
                    str3 = str5;
                    drmInitData2 = drmInitData4;
                    if (readInt2 == 1634492771) {
                        i10 = readInt;
                        byte[] bArr2 = new byte[i10];
                        i9 = position;
                        parsableByteArray.setPosition(i9);
                        parsableByteArray.readBytes(bArr2, 0, i10);
                        bArr = bArr2;
                    } else {
                        i10 = readInt;
                        i9 = position;
                        if (readInt2 == 1682927731) {
                            int i15 = i10 - 8;
                            byte[] bArr3 = opusMagic;
                            byte[] bArr4 = new byte[(bArr3.length + i15)];
                            System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
                            parsableByteArray.setPosition(i9 + 8);
                            parsableByteArray.readBytes(bArr4, opusMagic.length, i15);
                            bArr = bArr4;
                        } else if (i10 == 1684425825) {
                            int i16 = i10 - 12;
                            byte[] bArr5 = new byte[i16];
                            parsableByteArray.setPosition(i9 + 12);
                            parsableByteArray.readBytes(bArr5, 0, i16);
                            bArr = bArr5;
                        }
                    }
                }
                str2 = str4;
                str3 = str5;
                drmInitData2 = drmInitData4;
                i9 = position;
                i10 = readInt;
            }
            str5 = str3;
            position = i10 + i9;
            i11 = i2;
            drmInitData4 = drmInitData2;
            str4 = str2;
        }
        if (stsdData.format == null && str5 != null) {
            int i17 = str4.equals(str5) ? 2 : -1;
            String num = Integer.toString(i4);
            if (bArr != null) {
                list = Collections.singletonList(bArr);
            }
            stsdData.format = Format.createAudioSampleFormat(num, str5, null, -1, -1, i13, i14, i17, list, drmInitData4, 0, str);
        }
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == 1702061171) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if ("audio/mpeg".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts.hd".equals(mimeTypeFromMp4ObjectType)) {
            return Pair.create(mimeTypeFromMp4ObjectType, null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        return Pair.create(mimeTypeFromMp4ObjectType, bArr);
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) {
        Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == 1936289382 && (parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt)) != null) {
                return parseCommonEncryptionSinfFromParent;
            }
            position += readInt;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        boolean z = false;
        String str = null;
        Integer num = null;
        int i4 = -1;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1718775137) {
                num = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == 1935894637) {
                parsableByteArray.skipBytes(4);
                str = parsableByteArray.readString(4);
            } else if (readInt2 == 1935894633) {
                i4 = i3;
                i5 = readInt;
            }
            i3 += readInt;
        }
        if (!"cenc".equals(str) && !"cbc1".equals(str) && !"cens".equals(str) && !"cbcs".equals(str)) {
            return null;
        }
        Assertions.checkArgument(num != null, "frma atom is mandatory");
        Assertions.checkArgument(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, str);
        if (parseSchiFromParent != null) {
            z = true;
        }
        Assertions.checkArgument(z, "tenc atom is mandatory");
        return Pair.create(num, parseSchiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1952804451) {
                int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (parseFullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = readUnsignedByte & 15;
                    i4 = (readUnsignedByte & 240) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z && readUnsignedByte2 == 0) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[readUnsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, readUnsignedByte3);
                }
                return new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += readInt;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1886547818) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, readInt + i3);
            }
            i3 += readInt;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        int constrainValue = Util.constrainValue(4, 0, length);
        int constrainValue2 = Util.constrainValue(jArr.length - 4, 0, length);
        if (jArr[0] > j2 || j2 >= jArr[constrainValue] || jArr[constrainValue2] >= j3 || j3 > j) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() != 1 ? false : true, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long j;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                j = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                j = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = j;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static final class TkhdData {
        private final long duration;
        private final int id;
        private final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    /* access modifiers changed from: private */
    public static final class StsdData {
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    /* access modifiers changed from: package-private */
    public static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize = this.data.readUnsignedIntToInt();
        private final int sampleCount = this.data.readUnsignedIntToInt();

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
        }

        @Override // androidx.media2.exoplayer.external.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media2.exoplayer.external.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == 0 ? this.data.readUnsignedIntToInt() : i;
        }

        @Override // androidx.media2.exoplayer.external.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize = (this.data.readUnsignedIntToInt() & 255);
        private final int sampleCount = this.data.readUnsignedIntToInt();
        private int sampleIndex;

        @Override // androidx.media2.exoplayer.external.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
        }

        @Override // androidx.media2.exoplayer.external.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media2.exoplayer.external.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 != 0) {
                return this.currentByte & 15;
            }
            int readUnsignedByte = this.data.readUnsignedByte();
            this.currentByte = readUnsignedByte;
            return (readUnsignedByte & 240) >> 4;
        }
    }
}
