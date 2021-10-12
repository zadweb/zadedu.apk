package com.google.android.gms.internal.ads;

final /* synthetic */ class zzavl {
    static final /* synthetic */ int[] zzdhz;

    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
    static {
        int[] iArr = new int[zzaxa.values().length];
        zzdhz = iArr;
        iArr[zzaxa.SHA1.ordinal()] = 1;
        zzdhz[zzaxa.SHA256.ordinal()] = 2;
        try {
            zzdhz[zzaxa.SHA512.ordinal()] = 3;
        } catch (NoSuchFieldError unused) {
        }
    }
}
