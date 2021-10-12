package androidx.media2.exoplayer.external.source.hls;

import android.net.Uri;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.DefaultExtractorInput;
import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.metadata.id3.Id3Decoder;
import androidx.media2.exoplayer.external.metadata.id3.PrivFrame;
import androidx.media2.exoplayer.external.source.chunk.MediaChunk;
import androidx.media2.exoplayer.external.source.hls.HlsExtractorFactory;
import androidx.media2.exoplayer.external.source.hls.playlist.HlsMediaPlaylist;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.DataSpec;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.TimestampAdjuster;
import androidx.media2.exoplayer.external.util.UriUtil;
import androidx.media2.exoplayer.external.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class HlsMediaChunk extends MediaChunk {
    private static final AtomicInteger uidSource = new AtomicInteger();
    public final int discontinuitySequenceNumber;
    private final DrmInitData drmInitData;
    private Extractor extractor;
    private final HlsExtractorFactory extractorFactory;
    private final boolean hasGapTag;
    private final Id3Decoder id3Decoder;
    private boolean initDataLoadRequired;
    private final DataSource initDataSource;
    private final DataSpec initDataSpec;
    private final boolean initSegmentEncrypted;
    private boolean isExtractorReusable;
    private final boolean isMasterTimestampSource;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private final boolean mediaSegmentEncrypted;
    private final List<Format> muxedCaptionFormats;
    private int nextLoadPosition;
    private HlsSampleStreamWrapper output;
    public final Uri playlistUrl;
    private final Extractor previousExtractor;
    private final ParsableByteArray scratchId3Data;
    private final boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    public final int uid;

    public static HlsMediaChunk createInstance(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, Format format, long j, HlsMediaPlaylist hlsMediaPlaylist, int i, Uri uri, List<Format> list, int i2, Object obj, boolean z, TimestampAdjusterProvider timestampAdjusterProvider, HlsMediaChunk hlsMediaChunk, byte[] bArr, byte[] bArr2) {
        DataSource dataSource2;
        boolean z2;
        DataSpec dataSpec;
        boolean z3;
        ParsableByteArray parsableByteArray;
        Id3Decoder id3Decoder2;
        Extractor extractor2;
        HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(i);
        DataSpec dataSpec2 = new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.url), segment.byterangeOffset, segment.byterangeLength, null);
        boolean z4 = bArr != null;
        DataSource buildDataSource = buildDataSource(dataSource, bArr, z4 ? getEncryptionIvArray(segment.encryptionIV) : null);
        HlsMediaPlaylist.Segment segment2 = segment.initializationSegment;
        if (segment2 != null) {
            boolean z5 = bArr2 != null;
            byte[] encryptionIvArray = z5 ? getEncryptionIvArray(segment2.encryptionIV) : null;
            DataSpec dataSpec3 = new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment2.url), segment2.byterangeOffset, segment2.byterangeLength, null);
            z2 = z5;
            dataSource2 = buildDataSource(dataSource, bArr2, encryptionIvArray);
            dataSpec = dataSpec3;
        } else {
            dataSpec = null;
            z2 = false;
            dataSource2 = null;
        }
        long j2 = j + segment.relativeStartTimeUs;
        long j3 = j2 + segment.durationUs;
        int i3 = hlsMediaPlaylist.discontinuitySequence + segment.relativeDiscontinuitySequence;
        if (hlsMediaChunk != null) {
            Id3Decoder id3Decoder3 = hlsMediaChunk.id3Decoder;
            ParsableByteArray parsableByteArray2 = hlsMediaChunk.scratchId3Data;
            boolean z6 = !uri.equals(hlsMediaChunk.playlistUrl) || !hlsMediaChunk.loadCompleted;
            id3Decoder2 = id3Decoder3;
            parsableByteArray = parsableByteArray2;
            extractor2 = (!hlsMediaChunk.isExtractorReusable || hlsMediaChunk.discontinuitySequenceNumber != i3 || z6) ? null : hlsMediaChunk.extractor;
            z3 = z6;
        } else {
            id3Decoder2 = new Id3Decoder();
            parsableByteArray = new ParsableByteArray(10);
            extractor2 = null;
            z3 = false;
        }
        return new HlsMediaChunk(hlsExtractorFactory, buildDataSource, dataSpec2, format, z4, dataSource2, dataSpec, z2, uri, list, i2, obj, j2, j3, hlsMediaPlaylist.mediaSequence + ((long) i), i3, segment.hasGapTag, z, timestampAdjusterProvider.getAdjuster(i3), segment.drmInitData, extractor2, id3Decoder2, parsableByteArray, z3);
    }

    private HlsMediaChunk(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, DataSpec dataSpec, Format format, boolean z, DataSource dataSource2, DataSpec dataSpec2, boolean z2, Uri uri, List<Format> list, int i, Object obj, long j, long j2, long j3, int i2, boolean z3, boolean z4, TimestampAdjuster timestampAdjuster2, DrmInitData drmInitData2, Extractor extractor2, Id3Decoder id3Decoder2, ParsableByteArray parsableByteArray, boolean z5) {
        super(dataSource, dataSpec, format, i, obj, j, j2, j3);
        this.mediaSegmentEncrypted = z;
        this.discontinuitySequenceNumber = i2;
        this.initDataSource = dataSource2;
        this.initDataSpec = dataSpec2;
        this.initSegmentEncrypted = z2;
        this.playlistUrl = uri;
        this.isMasterTimestampSource = z4;
        this.timestampAdjuster = timestampAdjuster2;
        this.hasGapTag = z3;
        this.extractorFactory = hlsExtractorFactory;
        this.muxedCaptionFormats = list;
        this.drmInitData = drmInitData2;
        this.previousExtractor = extractor2;
        this.id3Decoder = id3Decoder2;
        this.scratchId3Data = parsableByteArray;
        this.shouldSpliceIn = z5;
        this.initDataLoadRequired = dataSpec2 != null;
        this.uid = uidSource.getAndIncrement();
    }

    public void init(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.output = hlsSampleStreamWrapper;
    }

    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    @Override // androidx.media2.exoplayer.external.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // androidx.media2.exoplayer.external.upstream.Loader.Loadable
    public void load() throws IOException, InterruptedException {
        Extractor extractor2;
        if (this.extractor == null && (extractor2 = this.previousExtractor) != null) {
            this.extractor = extractor2;
            this.isExtractorReusable = true;
            this.initDataLoadRequired = false;
            this.output.init(this.uid, this.shouldSpliceIn, true);
        }
        maybeLoadInitData();
        if (!this.loadCanceled) {
            if (!this.hasGapTag) {
                loadMedia();
            }
            this.loadCompleted = true;
        }
    }

    private void maybeLoadInitData() throws IOException, InterruptedException {
        if (this.initDataLoadRequired) {
            feedDataToExtractor(this.initDataSource, this.initDataSpec, this.initSegmentEncrypted);
            this.nextLoadPosition = 0;
            this.initDataLoadRequired = false;
        }
    }

    private void loadMedia() throws IOException, InterruptedException {
        if (!this.isMasterTimestampSource) {
            this.timestampAdjuster.waitUntilInitialized();
        } else if (this.timestampAdjuster.getFirstSampleTimestampUs() == Long.MAX_VALUE) {
            this.timestampAdjuster.setFirstSampleTimestampUs(this.startTimeUs);
        }
        feedDataToExtractor(this.dataSource, this.dataSpec, this.mediaSegmentEncrypted);
    }

    private void feedDataToExtractor(DataSource dataSource, DataSpec dataSpec, boolean z) throws IOException, InterruptedException {
        DataSpec dataSpec2;
        boolean z2;
        int i = 0;
        if (z) {
            z2 = this.nextLoadPosition != 0;
            dataSpec2 = dataSpec;
        } else {
            dataSpec2 = dataSpec.subrange((long) this.nextLoadPosition);
            z2 = false;
        }
        try {
            DefaultExtractorInput prepareExtraction = prepareExtraction(dataSource, dataSpec2);
            if (z2) {
                prepareExtraction.skipFully(this.nextLoadPosition);
            }
            while (i == 0) {
                try {
                    if (this.loadCanceled) {
                        break;
                    }
                    i = this.extractor.read(prepareExtraction, null);
                } catch (Throwable th) {
                    this.nextLoadPosition = (int) (prepareExtraction.getPosition() - dataSpec.absoluteStreamPosition);
                    throw th;
                }
            }
            this.nextLoadPosition = (int) (prepareExtraction.getPosition() - dataSpec.absoluteStreamPosition);
        } finally {
            Util.closeQuietly(dataSource);
        }
    }

    private DefaultExtractorInput prepareExtraction(DataSource dataSource, DataSpec dataSpec) throws IOException, InterruptedException {
        long j;
        DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(dataSource, dataSpec.absoluteStreamPosition, dataSource.open(dataSpec));
        if (this.extractor != null) {
            return defaultExtractorInput;
        }
        long peekId3PrivTimestamp = peekId3PrivTimestamp(defaultExtractorInput);
        defaultExtractorInput.resetPeekPosition();
        HlsExtractorFactory.Result createExtractor = this.extractorFactory.createExtractor(this.previousExtractor, dataSpec.uri, this.trackFormat, this.muxedCaptionFormats, this.drmInitData, this.timestampAdjuster, dataSource.getResponseHeaders(), defaultExtractorInput);
        this.extractor = createExtractor.extractor;
        this.isExtractorReusable = createExtractor.isReusable;
        if (createExtractor.isPackedAudioExtractor) {
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.output;
            if (peekId3PrivTimestamp != -9223372036854775807L) {
                j = this.timestampAdjuster.adjustTsTimestamp(peekId3PrivTimestamp);
            } else {
                j = this.startTimeUs;
            }
            hlsSampleStreamWrapper.setSampleOffsetUs(j);
        }
        this.output.init(this.uid, this.shouldSpliceIn, false);
        this.extractor.init(this.output);
        return defaultExtractorInput;
    }

    private long peekId3PrivTimestamp(ExtractorInput extractorInput) throws IOException, InterruptedException {
        extractorInput.resetPeekPosition();
        try {
            extractorInput.peekFully(this.scratchId3Data.data, 0, 10);
            this.scratchId3Data.reset(10);
            if (this.scratchId3Data.readUnsignedInt24() != 4801587) {
                return -9223372036854775807L;
            }
            this.scratchId3Data.skipBytes(3);
            int readSynchSafeInt = this.scratchId3Data.readSynchSafeInt();
            int i = readSynchSafeInt + 10;
            if (i > this.scratchId3Data.capacity()) {
                byte[] bArr = this.scratchId3Data.data;
                this.scratchId3Data.reset(i);
                System.arraycopy(bArr, 0, this.scratchId3Data.data, 0, 10);
            }
            extractorInput.peekFully(this.scratchId3Data.data, 10, readSynchSafeInt);
            Metadata decode = this.id3Decoder.decode(this.scratchId3Data.data, readSynchSafeInt);
            if (decode == null) {
                return -9223372036854775807L;
            }
            int length = decode.length();
            for (int i2 = 0; i2 < length; i2++) {
                Metadata.Entry entry = decode.get(i2);
                if (entry instanceof PrivFrame) {
                    PrivFrame privFrame = (PrivFrame) entry;
                    if ("com.apple.streaming.transportStreamTimestamp".equals(privFrame.owner)) {
                        System.arraycopy(privFrame.privateData, 0, this.scratchId3Data.data, 0, 8);
                        this.scratchId3Data.reset(8);
                        return this.scratchId3Data.readLong() & 8589934591L;
                    }
                }
            }
            return -9223372036854775807L;
        } catch (EOFException unused) {
        }
    }

    private static byte[] getEncryptionIvArray(String str) {
        if (Util.toLowerInvariant(str).startsWith("0x")) {
            str = str.substring(2);
        }
        byte[] byteArray = new BigInteger(str, 16).toByteArray();
        byte[] bArr = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr, (16 - byteArray.length) + length, byteArray.length - length);
        return bArr;
    }

    private static DataSource buildDataSource(DataSource dataSource, byte[] bArr, byte[] bArr2) {
        return bArr != null ? new Aes128DataSource(dataSource, bArr, bArr2) : dataSource;
    }
}
