package com.google.android.gms.internal.ads;

final class zzbeq extends zzben {
    zzbeq() {
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        if (i2 == 0) {
            return zzbem.zzda(i);
        }
        if (i2 == 1) {
            return zzbem.zzz(i, zzbek.zza(bArr, j));
        }
        if (i2 == 2) {
            return zzbem.zzf(i, zzbek.zza(bArr, j), zzbek.zza(bArr, j + 1));
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0061, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b6, code lost:
        return -1;
     */
    @Override // com.google.android.gms.internal.ads.zzben
    public final int zzb(int i, byte[] bArr, int i2, int i3) {
        int i4;
        long j;
        if ((i2 | i3 | (bArr.length - i3)) >= 0) {
            long j2 = (long) i2;
            int i5 = (int) (((long) i3) - j2);
            if (i5 >= 16) {
                long j3 = j2;
                i4 = 0;
                while (true) {
                    if (i4 >= i5) {
                        i4 = i5;
                        break;
                    }
                    long j4 = j3 + 1;
                    if (zzbek.zza(bArr, j3) < 0) {
                        break;
                    }
                    i4++;
                    j3 = j4;
                }
            } else {
                i4 = 0;
            }
            int i6 = i5 - i4;
            long j5 = j2 + ((long) i4);
            while (true) {
                byte b = 0;
                while (true) {
                    if (i6 <= 0) {
                        break;
                    }
                    long j6 = j5 + 1;
                    b = zzbek.zza(bArr, j5);
                    if (b < 0) {
                        j5 = j6;
                        break;
                    }
                    i6--;
                    j5 = j6;
                }
                if (i6 == 0) {
                    return 0;
                }
                int i7 = i6 - 1;
                if (b >= -32) {
                    if (b >= -16) {
                        if (i7 >= 3) {
                            i6 = i7 - 3;
                            long j7 = j5 + 1;
                            byte zza = zzbek.zza(bArr, j5);
                            if (zza > -65 || (((b << 28) + (zza + 112)) >> 30) != 0) {
                                break;
                            }
                            long j8 = j7 + 1;
                            if (zzbek.zza(bArr, j7) > -65) {
                                break;
                            }
                            j = j8 + 1;
                            if (zzbek.zza(bArr, j8) > -65) {
                                break;
                            }
                        } else {
                            return zza(bArr, b, j5, i7);
                        }
                    } else if (i7 >= 2) {
                        i6 = i7 - 2;
                        long j9 = j5 + 1;
                        byte zza2 = zzbek.zza(bArr, j5);
                        if (zza2 > -65 || ((b == -32 && zza2 < -96) || (b == -19 && zza2 >= -96))) {
                            break;
                        }
                        j5 = j9 + 1;
                        if (zzbek.zza(bArr, j9) > -65) {
                            break;
                        }
                    } else {
                        return zza(bArr, b, j5, i7);
                    }
                } else if (i7 != 0) {
                    i6 = i7 - 1;
                    if (b < -62) {
                        break;
                    }
                    j = j5 + 1;
                    if (zzbek.zza(bArr, j5) > -65) {
                        break;
                    }
                } else {
                    return b;
                }
                j5 = j;
            }
            return -1;
        }
        throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033 A[LOOP:1: B:13:0x0033->B:38:0x00fb, LOOP_START, PHI: r2 r3 r4 r11 
      PHI: (r2v4 int) = (r2v3 int), (r2v6 int) binds: [B:10:0x002f, B:38:0x00fb] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v3 char) = (r3v2 char), (r3v4 char) binds: [B:10:0x002f, B:38:0x00fb] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v3 long) = (r4v2 long), (r4v5 long) binds: [B:10:0x002f, B:38:0x00fb] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v3 long) = (r11v2 long), (r11v4 long) binds: [B:10:0x002f, B:38:0x00fb] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.gms.internal.ads.zzben
    public final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        long j;
        long j2;
        int i3;
        char charAt;
        long j3 = (long) i;
        long j4 = ((long) i2) + j3;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            char charAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (true) {
            char c = 128;
            long j5 = 1;
            if (i4 < length && (charAt = charSequence.charAt(i4)) < 128) {
                zzbek.zza(bArr, j3, (byte) charAt);
                i4++;
                j3 = 1 + j3;
            } else if (i4 != length) {
                return (int) j3;
            } else {
                while (i4 < length) {
                    char charAt3 = charSequence.charAt(i4);
                    if (charAt3 < c && j3 < j4) {
                        long j6 = j3 + j5;
                        zzbek.zza(bArr, j3, (byte) charAt3);
                        j2 = j5;
                        j = j6;
                    } else if (charAt3 < 2048 && j3 <= j4 - 2) {
                        long j7 = j3 + j5;
                        zzbek.zza(bArr, j3, (byte) ((charAt3 >>> 6) | 960));
                        zzbek.zza(bArr, j7, (byte) ((charAt3 & '?') | 128));
                        j = j7 + j5;
                        j2 = j5;
                        i4++;
                        c = 128;
                        j3 = j;
                        j5 = j2;
                    } else if ((charAt3 < 55296 || 57343 < charAt3) && j3 <= j4 - 3) {
                        long j8 = j3 + j5;
                        zzbek.zza(bArr, j3, (byte) ((charAt3 >>> '\f') | 480));
                        long j9 = j8 + j5;
                        zzbek.zza(bArr, j8, (byte) (((charAt3 >>> 6) & 63) | 128));
                        zzbek.zza(bArr, j9, (byte) ((charAt3 & '?') | 128));
                        j = j9 + 1;
                        j2 = 1;
                    } else if (j3 <= j4 - 4) {
                        int i5 = i4 + 1;
                        if (i5 != length) {
                            char charAt4 = charSequence.charAt(i5);
                            if (Character.isSurrogatePair(charAt3, charAt4)) {
                                int codePoint = Character.toCodePoint(charAt3, charAt4);
                                long j10 = j3 + 1;
                                zzbek.zza(bArr, j3, (byte) ((codePoint >>> 18) | 240));
                                long j11 = j10 + 1;
                                zzbek.zza(bArr, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j12 = j11 + 1;
                                zzbek.zza(bArr, j11, (byte) (((codePoint >>> 6) & 63) | 128));
                                j2 = 1;
                                j = j12 + 1;
                                zzbek.zza(bArr, j12, (byte) ((codePoint & 63) | 128));
                                i4 = i5;
                                i4++;
                                c = 128;
                                j3 = j;
                                j5 = j2;
                            } else {
                                i4 = i5;
                            }
                        }
                        throw new zzbep(i4 - 1, length);
                    } else if (55296 > charAt3 || charAt3 > 57343 || ((i3 = i4 + 1) != length && Character.isSurrogatePair(charAt3, charSequence.charAt(i3)))) {
                        StringBuilder sb2 = new StringBuilder(46);
                        sb2.append("Failed writing ");
                        sb2.append(charAt3);
                        sb2.append(" at index ");
                        sb2.append(j3);
                        throw new ArrayIndexOutOfBoundsException(sb2.toString());
                    } else {
                        throw new zzbep(i4, length);
                    }
                    i4++;
                    c = 128;
                    j3 = j;
                    j5 = j2;
                }
                return (int) j3;
            }
        }
        if (i4 != length) {
        }
    }
}
