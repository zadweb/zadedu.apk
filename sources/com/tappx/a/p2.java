package com.tappx.a;

public enum p2 {
    GRANTED_DEVELOPER,
    DENIED_DEVELOPER,
    GRANTED_USER,
    DENIED_USER,
    MISSING_ANSWER;

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f787a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            int[] iArr = new int[p2.values().length];
            f787a = iArr;
            iArr[p2.GRANTED_USER.ordinal()] = 1;
            f787a[p2.DENIED_USER.ordinal()] = 2;
            f787a[p2.GRANTED_DEVELOPER.ordinal()] = 3;
            try {
                f787a[p2.DENIED_DEVELOPER.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public boolean a() {
        int i = a.f787a[ordinal()];
        return i == 2 || i == 4;
    }

    public boolean b() {
        int i = a.f787a[ordinal()];
        return i == 1 || i == 2;
    }

    public boolean c() {
        int i = a.f787a[ordinal()];
        return i == 1 || i == 3;
    }
}
