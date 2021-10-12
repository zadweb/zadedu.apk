package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

/* access modifiers changed from: package-private */
public final class zzbas extends zzbaq {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzdqd;
    private int zzdqe;
    private int zzdqf;
    private int zzdqg;
    private int zzdqh;

    private zzbas(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzdqh = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzdqf = i;
        this.zzdqd = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x0068;
     */
    private final int zzacc() throws IOException {
        int i;
        int i2 = this.pos;
        int i3 = this.limit;
        if (i3 != i2) {
            byte[] bArr = this.buffer;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.pos = i4;
                return b;
            } else if (i3 - i4 >= 9) {
                int i5 = i4 + 1;
                int i6 = b ^ (bArr[i4] << 7);
                if (i6 < 0) {
                    i = i6 ^ -128;
                } else {
                    int i7 = i5 + 1;
                    int i8 = i6 ^ (bArr[i5] << 14);
                    if (i8 >= 0) {
                        i = i8 ^ 16256;
                    } else {
                        i5 = i7 + 1;
                        int i9 = i8 ^ (bArr[i7] << 21);
                        if (i9 < 0) {
                            i = i9 ^ -2080896;
                        } else {
                            i7 = i5 + 1;
                            byte b2 = bArr[i5];
                            i = (i9 ^ (b2 << 28)) ^ 266354560;
                            if (b2 < 0) {
                                i5 = i7 + 1;
                                if (bArr[i7] < 0) {
                                    i7 = i5 + 1;
                                    if (bArr[i5] < 0) {
                                        i5 = i7 + 1;
                                        if (bArr[i7] < 0) {
                                            i7 = i5 + 1;
                                            if (bArr[i5] < 0) {
                                                i5 = i7 + 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i5 = i7;
                }
                this.pos = i5;
                return i;
            }
        }
        return (int) zzabz();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b0, code lost:
        if (((long) r2[r0]) >= 0) goto L_0x006e;
     */
    private final long zzacd() throws IOException {
        long j;
        long j2;
        long j3;
        long j4;
        int i;
        int i2 = this.pos;
        int i3 = this.limit;
        if (i3 != i2) {
            byte[] bArr = this.buffer;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.pos = i4;
                return (long) b;
            } else if (i3 - i4 >= 9) {
                int i5 = i4 + 1;
                int i6 = b ^ (bArr[i4] << 7);
                if (i6 < 0) {
                    i = i6 ^ -128;
                } else {
                    int i7 = i5 + 1;
                    int i8 = i6 ^ (bArr[i5] << 14);
                    if (i8 >= 0) {
                        i5 = i7;
                        j = (long) (i8 ^ 16256);
                    } else {
                        i5 = i7 + 1;
                        int i9 = i8 ^ (bArr[i7] << 21);
                        if (i9 < 0) {
                            i = i9 ^ -2080896;
                        } else {
                            long j5 = (long) i9;
                            int i10 = i5 + 1;
                            long j6 = j5 ^ (((long) bArr[i5]) << 28);
                            if (j6 >= 0) {
                                j4 = 266354560;
                            } else {
                                i5 = i10 + 1;
                                long j7 = j6 ^ (((long) bArr[i10]) << 35);
                                if (j7 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    i10 = i5 + 1;
                                    j6 = j7 ^ (((long) bArr[i5]) << 42);
                                    if (j6 >= 0) {
                                        j4 = 4363953127296L;
                                    } else {
                                        i5 = i10 + 1;
                                        j7 = j6 ^ (((long) bArr[i10]) << 49);
                                        if (j7 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            int i11 = i5 + 1;
                                            j2 = (j7 ^ (((long) bArr[i5]) << 56)) ^ 71499008037633920L;
                                            i5 = j2 < 0 ? i11 + 1 : i11;
                                            j = j2;
                                        }
                                    }
                                }
                                j2 = j7 ^ j3;
                                j = j2;
                            }
                            j = j4 ^ j6;
                            i5 = i10;
                        }
                    }
                    this.pos = i5;
                    return j;
                }
                j = (long) i;
                this.pos = i5;
                return j;
            }
        }
        return zzabz();
    }

    private final int zzace() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 4) {
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }
        throw zzbbu.zzadl();
    }

    private final long zzacf() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 8) {
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzbbu.zzadl();
    }

    private final void zzacg() {
        int i = this.limit + this.zzdqe;
        this.limit = i;
        int i2 = i - this.zzdqf;
        int i3 = this.zzdqh;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzdqe = i4;
            this.limit = i - i4;
            return;
        }
        this.zzdqe = 0;
    }

    private final byte zzach() throws IOException {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzbbu.zzadl();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzacf());
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzace());
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final String readString() throws IOException {
        int zzacc = zzacc();
        if (zzacc > 0 && zzacc <= this.limit - this.pos) {
            String str = new String(this.buffer, this.pos, zzacc, zzbbq.UTF_8);
            this.pos += zzacc;
            return str;
        } else if (zzacc == 0) {
            return "";
        } else {
            if (zzacc < 0) {
                throw zzbbu.zzadm();
            }
            throw zzbbu.zzadl();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final int zzabk() throws IOException {
        if (zzaca()) {
            this.zzdqg = 0;
            return 0;
        }
        int zzacc = zzacc();
        this.zzdqg = zzacc;
        if ((zzacc >>> 3) != 0) {
            return zzacc;
        }
        throw zzbbu.zzado();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final long zzabl() throws IOException {
        return zzacd();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final long zzabm() throws IOException {
        return zzacd();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final int zzabn() throws IOException {
        return zzacc();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final long zzabo() throws IOException {
        return zzacf();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final int zzabp() throws IOException {
        return zzace();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final boolean zzabq() throws IOException {
        return zzacd() != 0;
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final String zzabr() throws IOException {
        int zzacc = zzacc();
        if (zzacc > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzacc <= i - i2) {
                if (zzbem.zzf(this.buffer, i2, i2 + zzacc)) {
                    int i3 = this.pos;
                    this.pos = i3 + zzacc;
                    return new String(this.buffer, i3, zzacc, zzbbq.UTF_8);
                }
                throw zzbbu.zzads();
            }
        }
        if (zzacc == 0) {
            return "";
        }
        if (zzacc <= 0) {
            throw zzbbu.zzadm();
        }
        throw zzbbu.zzadl();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final zzbah zzabs() throws IOException {
        byte[] bArr;
        int zzacc = zzacc();
        if (zzacc > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzacc <= i - i2) {
                zzbah zzc = zzbah.zzc(this.buffer, i2, zzacc);
                this.pos += zzacc;
                return zzc;
            }
        }
        if (zzacc == 0) {
            return zzbah.zzdpq;
        }
        if (zzacc > 0) {
            int i3 = this.limit;
            int i4 = this.pos;
            if (zzacc <= i3 - i4) {
                int i5 = zzacc + i4;
                this.pos = i5;
                bArr = Arrays.copyOfRange(this.buffer, i4, i5);
                return zzbah.zzp(bArr);
            }
        }
        if (zzacc > 0) {
            throw zzbbu.zzadl();
        } else if (zzacc == 0) {
            bArr = zzbbq.zzduq;
            return zzbah.zzp(bArr);
        } else {
            throw zzbbu.zzadm();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final int zzabt() throws IOException {
        return zzacc();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final int zzabu() throws IOException {
        return zzacc();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final int zzabv() throws IOException {
        return zzace();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final long zzabw() throws IOException {
        return zzacf();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final int zzabx() throws IOException {
        return zzbu(zzacc());
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final long zzaby() throws IOException {
        return zzl(zzacd());
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzbaq
    public final long zzabz() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzach = zzach();
            j |= ((long) (zzach & Byte.MAX_VALUE)) << i;
            if ((zzach & 128) == 0) {
                return j;
            }
        }
        throw zzbbu.zzadn();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final boolean zzaca() throws IOException {
        return this.pos == this.limit;
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final int zzacb() {
        return this.pos - this.zzdqf;
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final void zzbp(int i) throws zzbbu {
        if (this.zzdqg != i) {
            throw zzbbu.zzadp();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final boolean zzbq(int i) throws IOException {
        int zzabk;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.limit - this.pos >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.buffer;
                    int i4 = this.pos;
                    this.pos = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzbbu.zzadn();
            }
            while (i3 < 10) {
                if (zzach() < 0) {
                    i3++;
                }
            }
            throw zzbbu.zzadn();
            return true;
        } else if (i2 == 1) {
            zzbt(8);
            return true;
        } else if (i2 == 2) {
            zzbt(zzacc());
            return true;
        } else if (i2 == 3) {
            do {
                zzabk = zzabk();
                if (zzabk == 0) {
                    break;
                }
            } while (zzbq(zzabk));
            zzbp(((i >>> 3) << 3) | 4);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzbt(4);
                return true;
            }
            throw zzbbu.zzadq();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final int zzbr(int i) throws zzbbu {
        if (i >= 0) {
            int zzacb = i + zzacb();
            int i2 = this.zzdqh;
            if (zzacb <= i2) {
                this.zzdqh = zzacb;
                zzacg();
                return i2;
            }
            throw zzbbu.zzadl();
        }
        throw zzbbu.zzadm();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final void zzbs(int i) {
        this.zzdqh = i;
        zzacg();
    }

    @Override // com.google.android.gms.internal.ads.zzbaq
    public final void zzbt(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.limit;
            int i3 = this.pos;
            if (i <= i2 - i3) {
                this.pos = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzbbu.zzadm();
        }
        throw zzbbu.zzadl();
    }
}
