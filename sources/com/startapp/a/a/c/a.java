package com.startapp.a.a.c;

import com.startapp.a.a.c.b;

/* compiled from: StartAppSDK */
public class a extends b {

    /* renamed from: a  reason: collision with root package name */
    static final byte[] f243a = {13, 10};
    private static final byte[] d = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] e = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] f = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    private final byte[] g;
    private final byte[] h;
    private final byte[] i;
    private final int j;
    private final int k;

    public a() {
        this(0);
    }

    public a(boolean z) {
        this(76, f243a, z);
    }

    public a(int i2) {
        this(i2, f243a);
    }

    public a(int i2, byte[] bArr) {
        this(i2, bArr, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(int i2, byte[] bArr, boolean z) {
        super(3, 4, i2, bArr == null ? 0 : bArr.length);
        this.h = f;
        if (bArr == null) {
            this.k = 4;
            this.i = null;
        } else if (c(bArr)) {
            String a2 = f.a(bArr);
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + a2 + "]");
        } else if (i2 > 0) {
            this.k = bArr.length + 4;
            byte[] bArr2 = new byte[bArr.length];
            this.i = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        } else {
            this.k = 4;
            this.i = null;
        }
        this.j = this.k - 1;
        this.g = z ? e : d;
    }

    /* access modifiers changed from: package-private */
    @Override // com.startapp.a.a.c.b
    public void a(byte[] bArr, int i2, int i3, b.a aVar) {
        if (!aVar.f) {
            if (i3 < 0) {
                aVar.f = true;
                if (aVar.h != 0 || this.c != 0) {
                    byte[] a2 = a(this.k, aVar);
                    int i4 = aVar.d;
                    int i5 = aVar.h;
                    if (i5 != 0) {
                        if (i5 == 1) {
                            int i6 = aVar.d;
                            aVar.d = i6 + 1;
                            a2[i6] = this.g[(aVar.f245a >> 2) & 63];
                            int i7 = aVar.d;
                            aVar.d = i7 + 1;
                            a2[i7] = this.g[(aVar.f245a << 4) & 63];
                            if (this.g == d) {
                                int i8 = aVar.d;
                                aVar.d = i8 + 1;
                                a2[i8] = 61;
                                int i9 = aVar.d;
                                aVar.d = i9 + 1;
                                a2[i9] = 61;
                            }
                        } else if (i5 == 2) {
                            int i10 = aVar.d;
                            aVar.d = i10 + 1;
                            a2[i10] = this.g[(aVar.f245a >> 10) & 63];
                            int i11 = aVar.d;
                            aVar.d = i11 + 1;
                            a2[i11] = this.g[(aVar.f245a >> 4) & 63];
                            int i12 = aVar.d;
                            aVar.d = i12 + 1;
                            a2[i12] = this.g[(aVar.f245a << 2) & 63];
                            if (this.g == d) {
                                int i13 = aVar.d;
                                aVar.d = i13 + 1;
                                a2[i13] = 61;
                            }
                        } else {
                            throw new IllegalStateException("Impossible modulus " + aVar.h);
                        }
                    }
                    aVar.g += aVar.d - i4;
                    if (this.c > 0 && aVar.g > 0) {
                        System.arraycopy(this.i, 0, a2, aVar.d, this.i.length);
                        aVar.d += this.i.length;
                        return;
                    }
                    return;
                }
                return;
            }
            int i14 = 0;
            while (i14 < i3) {
                byte[] a3 = a(this.k, aVar);
                aVar.h = (aVar.h + 1) % 3;
                int i15 = i2 + 1;
                byte b = bArr[i2];
                int i16 = b;
                if (b < 0) {
                    i16 = b + 256;
                }
                aVar.f245a = (aVar.f245a << 8) + (i16 == 1 ? 1 : 0);
                if (aVar.h == 0) {
                    int i17 = aVar.d;
                    aVar.d = i17 + 1;
                    a3[i17] = this.g[(aVar.f245a >> 18) & 63];
                    int i18 = aVar.d;
                    aVar.d = i18 + 1;
                    a3[i18] = this.g[(aVar.f245a >> 12) & 63];
                    int i19 = aVar.d;
                    aVar.d = i19 + 1;
                    a3[i19] = this.g[(aVar.f245a >> 6) & 63];
                    int i20 = aVar.d;
                    aVar.d = i20 + 1;
                    a3[i20] = this.g[aVar.f245a & 63];
                    aVar.g += 4;
                    if (this.c > 0 && this.c <= aVar.g) {
                        System.arraycopy(this.i, 0, a3, aVar.d, this.i.length);
                        aVar.d += this.i.length;
                        aVar.g = 0;
                    }
                }
                i14++;
                i2 = i15;
            }
        }
    }

    public static String a(byte[] bArr) {
        return f.a(a(bArr, false));
    }

    public static byte[] a(byte[] bArr, boolean z) {
        return a(bArr, z, false);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2) {
        return a(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2, int i2) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = z ? new a(z2) : new a(0, f243a, z2);
        long d2 = aVar.d(bArr);
        if (d2 <= ((long) i2)) {
            return aVar.b(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + d2 + ") than the specified maximum size of " + i2);
    }

    /* access modifiers changed from: protected */
    @Override // com.startapp.a.a.c.b
    public boolean a(byte b) {
        if (b >= 0) {
            byte[] bArr = this.h;
            return b < bArr.length && bArr[b] != -1;
        }
    }
}
