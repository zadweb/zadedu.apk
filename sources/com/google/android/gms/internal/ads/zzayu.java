package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzayu {
    static final /* synthetic */ int[] zzdnm;
    static final /* synthetic */ int[] zzdnn;

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0039 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001d */
    static {
        int[] iArr = new int[zzayv.values().length];
        zzdnn = iArr;
        try {
            iArr[zzayv.NIST_P256.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        zzdnn[zzayv.NIST_P384.ordinal()] = 2;
        try {
            zzdnn[zzayv.NIST_P521.ordinal()] = 3;
        } catch (NoSuchFieldError unused2) {
        }
        int[] iArr2 = new int[zzayw.values().length];
        zzdnm = iArr2;
        iArr2[zzayw.UNCOMPRESSED.ordinal()] = 1;
        try {
            zzdnm[zzayw.COMPRESSED.ordinal()] = 2;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
