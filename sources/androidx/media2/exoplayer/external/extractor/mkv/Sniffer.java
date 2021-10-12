package androidx.media2.exoplayer.external.extractor.mkv;

import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.io.IOException;

final class Sniffer {
    private int peekLength;
    private final ParsableByteArray scratch = new ParsableByteArray(8);

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        long length = extractorInput.getLength();
        long j = 1024;
        if (length != -1 && length <= 1024) {
            j = length;
        }
        int i = (int) j;
        extractorInput.peekFully(this.scratch.data, 0, 4);
        long readUnsignedInt = this.scratch.readUnsignedInt();
        this.peekLength = 4;
        while (readUnsignedInt != 440786851) {
            int i2 = this.peekLength + 1;
            this.peekLength = i2;
            if (i2 == i) {
                return false;
            }
            extractorInput.peekFully(this.scratch.data, 0, 1);
            readUnsignedInt = ((readUnsignedInt << 8) & -256) | ((long) (this.scratch.data[0] & 255));
        }
        long readUint = readUint(extractorInput);
        long j2 = (long) this.peekLength;
        if (readUint == Long.MIN_VALUE) {
            return false;
        }
        if (length != -1 && j2 + readUint >= length) {
            return false;
        }
        while (true) {
            int i3 = this.peekLength;
            long j3 = j2 + readUint;
            if (((long) i3) < j3) {
                if (readUint(extractorInput) == Long.MIN_VALUE) {
                    return false;
                }
                long readUint2 = readUint(extractorInput);
                if (readUint2 < 0 || readUint2 > 2147483647L) {
                    return false;
                }
                if (readUint2 != 0) {
                    int i4 = (int) readUint2;
                    extractorInput.advancePeekPosition(i4);
                    this.peekLength += i4;
                }
            } else if (((long) i3) == j3) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private long readUint(ExtractorInput extractorInput) throws IOException, InterruptedException {
        int i = 0;
        extractorInput.peekFully(this.scratch.data, 0, 1);
        int i2 = this.scratch.data[0] & 255;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int i3 = 128;
        int i4 = 0;
        while ((i2 & i3) == 0) {
            i3 >>= 1;
            i4++;
        }
        int i5 = i2 & (i3 ^ -1);
        extractorInput.peekFully(this.scratch.data, 1, i4);
        while (i < i4) {
            i++;
            i5 = (this.scratch.data[i] & 255) + (i5 << 8);
        }
        this.peekLength += i4 + 1;
        return (long) i5;
    }
}
