package com.tappx.a;

import com.tappx.sdk.android.AdRequest;

public class k0 {

    /* renamed from: a  reason: collision with root package name */
    private static final String f723a = null;
    private static final String b = null;

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f724a;
        static final /* synthetic */ int[] b;

        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006e */
        static {
            int[] iArr = new int[AdRequest.MaritalStatus.values().length];
            b = iArr;
            try {
                iArr[AdRequest.MaritalStatus.SINGLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[AdRequest.MaritalStatus.LIVING_COMMON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[AdRequest.MaritalStatus.MARRIED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            b[AdRequest.MaritalStatus.DIVORCED.ordinal()] = 4;
            b[AdRequest.MaritalStatus.WIDOWED.ordinal()] = 5;
            try {
                b[AdRequest.MaritalStatus.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[AdRequest.Gender.values().length];
            f724a = iArr2;
            iArr2[AdRequest.Gender.MALE.ordinal()] = 1;
            f724a[AdRequest.Gender.FEMALE.ordinal()] = 2;
            f724a[AdRequest.Gender.OTHER.ordinal()] = 3;
            try {
                f724a[AdRequest.Gender.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public b2 a(String str, v1 v1Var, String str2, AdRequest adRequest) {
        b2 b2Var = new b2();
        b2Var.a(str2);
        b2Var.b(v1Var.a());
        b2Var.c(str);
        b2Var.h(adRequest.getSdkType());
        b2Var.g(adRequest.getMediator());
        b2Var.e(adRequest.getKeywords());
        b2Var.b(adRequest.getYearOfBirth());
        b2Var.a(adRequest.getAge());
        b2Var.d(a(adRequest.getGender()));
        b2Var.f(a(adRequest.getMaritalStatus()));
        b2Var.a(adRequest.isUseTestAds());
        return b2Var;
    }

    private String a(AdRequest.Gender gender) {
        if (gender == null) {
            return null;
        }
        int i = a.f724a[gender.ordinal()];
        if (i == 1) {
            return "M";
        }
        if (i != 2) {
            return i != 3 ? f723a : "O";
        }
        return "F";
    }

    private String a(AdRequest.MaritalStatus maritalStatus) {
        if (maritalStatus == null) {
            return null;
        }
        int i = a.b[maritalStatus.ordinal()];
        if (i == 1) {
            return "S";
        }
        if (i == 2) {
            return "L";
        }
        if (i == 3) {
            return "M";
        }
        if (i != 4) {
            return i != 5 ? b : "W";
        }
        return "D";
    }
}
