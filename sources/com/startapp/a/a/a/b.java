package com.startapp.a.a.a;

import java.nio.ByteBuffer;

/* compiled from: StartAppSDK */
public class b {
    public static long a(ByteBuffer byteBuffer, int i, int i2, long j) {
        long j2;
        long j3 = (j & 4294967295L) ^ (((long) i2) * -4132994306676758123L);
        int i3 = i2 >> 3;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + (i4 << 3);
            long j4 = ((((long) byteBuffer.get(i5 + 0)) & 255) + ((((long) byteBuffer.get(i5 + 1)) & 255) << 8) + ((((long) byteBuffer.get(i5 + 2)) & 255) << 16) + ((((long) byteBuffer.get(i5 + 3)) & 255) << 24) + ((((long) byteBuffer.get(i5 + 4)) & 255) << 32) + ((((long) byteBuffer.get(i5 + 5)) & 255) << 40) + ((((long) byteBuffer.get(i5 + 6)) & 255) << 48) + ((((long) byteBuffer.get(i5 + 7)) & 255) << 56)) * -4132994306676758123L;
            j3 = (j3 ^ ((j4 ^ (j4 >>> 47)) * -4132994306676758123L)) * -4132994306676758123L;
        }
        int i6 = i2 & 7;
        switch (i6) {
            case 7:
                j3 ^= ((long) byteBuffer.get(((i + i2) - i6) + 6)) << 48;
            case 6:
                j3 ^= ((long) byteBuffer.get(((i + i2) - i6) + 5)) << 40;
            case 5:
                j3 ^= ((long) byteBuffer.get(((i + i2) - i6) + 4)) << 32;
            case 4:
                j3 ^= ((long) byteBuffer.get(((i + i2) - i6) + 3)) << 24;
            case 3:
                j3 ^= ((long) byteBuffer.get(((i + i2) - i6) + 2)) << 16;
            case 2:
                j3 ^= ((long) byteBuffer.get(((i + i2) - i6) + 1)) << 8;
            case 1:
                j2 = -4132994306676758123L;
                j3 = (((long) byteBuffer.get((i + i2) - i6)) ^ j3) * -4132994306676758123L;
                break;
            default:
                j2 = -4132994306676758123L;
                break;
        }
        long j5 = (j3 ^ (j3 >>> 47)) * j2;
        return (j5 >>> 47) ^ j5;
    }
}
