package androidx.media2.exoplayer.external.extractor.ts;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.audio.Ac3Util;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.TrackOutput;
import androidx.media2.exoplayer.external.extractor.ts.TsPayloadReader;
import androidx.media2.exoplayer.external.util.ParsableBitArray;
import androidx.media2.exoplayer.external.util.ParsableByteArray;

public final class Ac3Reader implements ElementaryStreamReader {
    private int bytesRead;
    private Format format;
    private final ParsableBitArray headerScratchBits;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private boolean lastByteWas0B;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;
    private String trackFormatId;

    @Override // androidx.media2.exoplayer.external.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public Ac3Reader() {
        this(null);
    }

    public Ac3Reader(String str) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[128]);
        this.headerScratchBits = parsableBitArray;
        this.headerScratchBytes = new ParsableByteArray(parsableBitArray.data);
        this.state = 0;
        this.language = str;
    }

    @Override // androidx.media2.exoplayer.external.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWas0B = false;
    }

    @Override // androidx.media2.exoplayer.external.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.trackFormatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    @Override // androidx.media2.exoplayer.external.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        this.timeUs = j;
    }

    @Override // androidx.media2.exoplayer.external.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                        this.output.sampleData(parsableByteArray, min);
                        int i2 = this.bytesRead + min;
                        this.bytesRead = i2;
                        int i3 = this.sampleSize;
                        if (i2 == i3) {
                            this.output.sampleMetadata(this.timeUs, 1, i3, 0, null);
                            this.timeUs += this.sampleDurationUs;
                            this.state = 0;
                        }
                    }
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.data, 128)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 128);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
                this.headerScratchBytes.data[0] = 11;
                this.headerScratchBytes.data[1] = 119;
                this.bytesRead = 2;
            }
        }
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        int min = Math.min(parsableByteArray.bytesLeft(), i - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, min);
        int i2 = this.bytesRead + min;
        this.bytesRead = i2;
        return i2 == i;
    }

    private boolean skipToNextSync(ParsableByteArray parsableByteArray) {
        while (true) {
            boolean z = false;
            if (parsableByteArray.bytesLeft() <= 0) {
                return false;
            }
            if (!this.lastByteWas0B) {
                if (parsableByteArray.readUnsignedByte() == 11) {
                    z = true;
                }
                this.lastByteWas0B = z;
            } else {
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                if (readUnsignedByte == 119) {
                    this.lastByteWas0B = false;
                    return true;
                }
                if (readUnsignedByte == 11) {
                    z = true;
                }
                this.lastByteWas0B = z;
            }
        }
    }

    private void parseHeader() {
        this.headerScratchBits.setPosition(0);
        Ac3Util.SyncFrameInfo parseAc3SyncframeInfo = Ac3Util.parseAc3SyncframeInfo(this.headerScratchBits);
        if (!(this.format != null && parseAc3SyncframeInfo.channelCount == this.format.channelCount && parseAc3SyncframeInfo.sampleRate == this.format.sampleRate && parseAc3SyncframeInfo.mimeType == this.format.sampleMimeType)) {
            Format createAudioSampleFormat = Format.createAudioSampleFormat(this.trackFormatId, parseAc3SyncframeInfo.mimeType, null, -1, -1, parseAc3SyncframeInfo.channelCount, parseAc3SyncframeInfo.sampleRate, null, null, 0, this.language);
            this.format = createAudioSampleFormat;
            this.output.format(createAudioSampleFormat);
        }
        this.sampleSize = parseAc3SyncframeInfo.frameSize;
        this.sampleDurationUs = (((long) parseAc3SyncframeInfo.sampleCount) * 1000000) / ((long) this.format.sampleRate);
    }
}
