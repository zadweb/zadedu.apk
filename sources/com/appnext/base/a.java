package com.appnext.base;

public final class a {
    private int ds;

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    /* renamed from: com.appnext.base.a$a  reason: collision with other inner class name */
    public static final class EnumC0004a extends Enum<EnumC0004a> {
        private static final /* synthetic */ int[] $VALUES$47a19cef = {1, 2, 3};
        public static final int Internal$1d8b5b4a = 1;
        public static final int NoInternet$1d8b5b4a = 2;
        public static final int NoPermission$1d8b5b4a = 3;

        private EnumC0004a(String str, int i) {
        }

        public static int[] W() {
            return (int[]) $VALUES$47a19cef.clone();
        }
    }

    public a(int i) {
        this.ds = i;
    }

    public final int V() {
        return this.ds;
    }
}
