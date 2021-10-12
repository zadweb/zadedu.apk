package androidx.media2.exoplayer.external.extractor.mp4;

import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.io.IOException;

final class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1635148593, 1752589105, 1751479857, 1635135537, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, 1903435808, 1297305174, 1684175153};

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return sniffInternal(extractorInput, true);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return sniffInternal(extractorInput, false);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, boolean z) throws IOException, InterruptedException {
        boolean z2;
        long length = extractorInput.getLength();
        long j = 4096;
        long j2 = -1;
        if (length != -1 && length <= 4096) {
            j = length;
        }
        int i = (int) j;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        boolean z3 = false;
        int i2 = 0;
        boolean z4 = false;
        while (true) {
            if (i2 >= i) {
                break;
            }
            parsableByteArray.reset(8);
            byte[] bArr = parsableByteArray.data;
            int i3 = z3 ? 1 : 0;
            int i4 = z3 ? 1 : 0;
            int i5 = z3 ? 1 : 0;
            int i6 = z3 ? 1 : 0;
            extractorInput.peekFully(bArr, i3, 8);
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            int readInt = parsableByteArray.readInt();
            int i7 = 16;
            if (readUnsignedInt == 1) {
                extractorInput.peekFully(parsableByteArray.data, 8, 8);
                parsableByteArray.setLimit(16);
                readUnsignedInt = parsableByteArray.readLong();
            } else {
                if (readUnsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j2) {
                        readUnsignedInt = ((long) 8) + (length2 - extractorInput.getPeekPosition());
                    }
                }
                i7 = 8;
            }
            if (length != j2 && ((long) i2) + readUnsignedInt > length) {
                return z3;
            }
            long j3 = (long) i7;
            if (readUnsignedInt < j3) {
                return z3;
            }
            i2 += i7;
            if (readInt == 1836019574) {
                i += (int) readUnsignedInt;
                if (length != -1 && ((long) i) > length) {
                    i = (int) length;
                }
                j2 = -1;
            } else if (readInt == 1836019558 || readInt == 1836475768) {
                z2 = true;
            } else if ((((long) i2) + readUnsignedInt) - j3 >= ((long) i)) {
                break;
            } else {
                int i8 = (int) (readUnsignedInt - j3);
                i2 += i8;
                if (readInt == 1718909296) {
                    if (i8 < 8) {
                        return false;
                    }
                    parsableByteArray.reset(i8);
                    extractorInput.peekFully(parsableByteArray.data, 0, i8);
                    int i9 = i8 / 4;
                    int i10 = 0;
                    while (true) {
                        if (i10 >= i9) {
                            break;
                        }
                        if (i10 == 1) {
                            parsableByteArray.skipBytes(4);
                        } else if (isCompatibleBrand(parsableByteArray.readInt())) {
                            z4 = true;
                            break;
                        }
                        i10++;
                    }
                    if (!z4) {
                        return false;
                    }
                } else if (i8 != 0) {
                    extractorInput.advancePeekPosition(i8);
                }
                j2 = -1;
                length = length;
                z3 = false;
            }
        }
        z2 = false;
        return z4 && z == z2;
    }

    private static boolean isCompatibleBrand(int i) {
        if ((i >>> 8) == 3368816) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }
}
