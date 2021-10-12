package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzzd {
    private static final /* synthetic */ int[] zzbvf;
    static final /* synthetic */ int[] zzbvg;

    /* JADX WARNING: Can't wrap try/catch for region: R(16:0|(2:1|2)|3|(2:5|6)|7|9|10|(2:11|12)|13|15|16|17|18|19|20|22) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0044 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004e */
    static {
        int[] iArr = new int[AdRequest.ErrorCode.values().length];
        zzbvg = iArr;
        try {
            iArr[AdRequest.ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzbvg[AdRequest.ErrorCode.INVALID_REQUEST.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        zzbvg[AdRequest.ErrorCode.NETWORK_ERROR.ordinal()] = 3;
        try {
            zzbvg[AdRequest.ErrorCode.NO_FILL.ordinal()] = 4;
        } catch (NoSuchFieldError unused3) {
        }
        int[] iArr2 = new int[AdRequest.Gender.values().length];
        zzbvf = iArr2;
        iArr2[AdRequest.Gender.FEMALE.ordinal()] = 1;
        zzbvf[AdRequest.Gender.MALE.ordinal()] = 2;
        try {
            zzbvf[AdRequest.Gender.UNKNOWN.ordinal()] = 3;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
