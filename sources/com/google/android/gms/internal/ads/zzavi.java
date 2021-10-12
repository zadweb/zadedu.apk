package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzavi {
    static final /* synthetic */ int[] zzdhz;
    static final /* synthetic */ int[] zzdia;
    static final /* synthetic */ int[] zzdib;

    /* JADX WARNING: Can't wrap try/catch for region: R(19:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|(2:15|16)|17|19|20|21|22|23|24|26) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0054 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005e */
    static {
        int[] iArr = new int[zzawk.values().length];
        zzdib = iArr;
        try {
            iArr[zzawk.UNCOMPRESSED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzdib[zzawk.COMPRESSED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        int[] iArr2 = new int[zzawy.values().length];
        zzdia = iArr2;
        iArr2[zzawy.NIST_P256.ordinal()] = 1;
        try {
            zzdia[zzawy.NIST_P384.ordinal()] = 2;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zzdia[zzawy.NIST_P521.ordinal()] = 3;
        } catch (NoSuchFieldError unused4) {
        }
        int[] iArr3 = new int[zzaxa.values().length];
        zzdhz = iArr3;
        iArr3[zzaxa.SHA1.ordinal()] = 1;
        zzdhz[zzaxa.SHA256.ordinal()] = 2;
        try {
            zzdhz[zzaxa.SHA512.ordinal()] = 3;
        } catch (NoSuchFieldError unused5) {
        }
    }
}
