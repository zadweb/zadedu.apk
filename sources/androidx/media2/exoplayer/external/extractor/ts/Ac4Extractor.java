package androidx.media2.exoplayer.external.extractor.ts;

import androidx.media2.exoplayer.external.audio.Ac4Util;
import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.ExtractorsFactory;
import androidx.media2.exoplayer.external.extractor.PositionHolder;
import androidx.media2.exoplayer.external.extractor.SeekMap;
import androidx.media2.exoplayer.external.extractor.ts.TsPayloadReader;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.io.IOException;

public final class Ac4Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = Ac4Extractor$$Lambda$0.$instance;
    private final long firstSampleTimestampUs;
    private final Ac4Reader reader;
    private final ParsableByteArray sampleData;
    private boolean startedPacket;

    @Override // androidx.media2.exoplayer.external.extractor.Extractor
    public void release() {
    }

    static final /* synthetic */ Extractor[] lambda$static$0$Ac4Extractor() {
        return new Extractor[]{new Ac4Extractor()};
    }

    public Ac4Extractor() {
        this(0);
    }

    public Ac4Extractor(long j) {
        this.firstSampleTimestampUs = j;
        this.reader = new Ac4Reader();
        this.sampleData = new ParsableByteArray(16384);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0042, code lost:
        if ((r4 - r3) < 8192) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0039, code lost:
        r9.resetPeekPosition();
        r4 = r4 + 1;
     */
    @Override // androidx.media2.exoplayer.external.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(10);
        int i = 0;
        while (true) {
            extractorInput.peekFully(parsableByteArray.data, 0, 10);
            parsableByteArray.setPosition(0);
            if (parsableByteArray.readUnsignedInt24() != 4801587) {
                break;
            }
            parsableByteArray.skipBytes(3);
            int readSynchSafeInt = parsableByteArray.readSynchSafeInt();
            i += readSynchSafeInt + 10;
            extractorInput.advancePeekPosition(readSynchSafeInt);
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i);
        int i2 = i;
        while (true) {
            int i3 = 0;
            while (true) {
                extractorInput.peekFully(parsableByteArray.data, 0, 7);
                parsableByteArray.setPosition(0);
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                if (readUnsignedShort != 44096 && readUnsignedShort != 44097) {
                    break;
                }
                i3++;
                if (i3 >= 4) {
                    return true;
                }
                int parseAc4SyncframeSize = Ac4Util.parseAc4SyncframeSize(parsableByteArray.data, readUnsignedShort);
                if (parseAc4SyncframeSize == -1) {
                    return false;
                }
                extractorInput.advancePeekPosition(parseAc4SyncframeSize - 7);
            }
            extractorInput.advancePeekPosition(i2);
        }
    }

    @Override // androidx.media2.exoplayer.external.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.reader.createTracks(extractorOutput, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput.endTracks();
        extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
    }

    @Override // androidx.media2.exoplayer.external.extractor.Extractor
    public void seek(long j, long j2) {
        this.startedPacket = false;
        this.reader.seek();
    }

    @Override // androidx.media2.exoplayer.external.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        int read = extractorInput.read(this.sampleData.data, 0, 16384);
        if (read == -1) {
            return -1;
        }
        this.sampleData.setPosition(0);
        this.sampleData.setLimit(read);
        if (!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, 4);
            this.startedPacket = true;
        }
        this.reader.consume(this.sampleData);
        return 0;
    }
}
