package com.tappx.a;

public class b3 {

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f616a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            int[] iArr = new int[t1.values().length];
            f616a = iArr;
            iArr[t1.LEFT_TO_RIGHT.ordinal()] = 1;
            f616a[t1.LEFT_TO_RIGHT_BOUNCE.ordinal()] = 2;
            f616a[t1.RANDOM.ordinal()] = 3;
            f616a[t1.RIGHT_TO_LEFT.ordinal()] = 4;
            f616a[t1.RIGHT_TO_LEFT_BOUNCE.ordinal()] = 5;
            try {
                f616a[t1.NONE.ordinal()] = 6;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static k3 a(t1 t1Var) {
        if (t1Var == null) {
            t1Var = t1.NONE;
        }
        int i = a.f616a[t1Var.ordinal()];
        if (i == 1) {
            return k3.FROM_LEFT;
        }
        if (i == 2) {
            return k3.FROM_LEFT_BOUNCE;
        }
        if (i == 3) {
            return k3.RANDOM;
        }
        if (i == 4) {
            return k3.FROM_RIGHT;
        }
        if (i != 5) {
            return k3.NONE;
        }
        return k3.FROM_RIGHT_BOUNCE;
    }
}
