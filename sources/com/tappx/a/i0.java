package com.tappx.a;

import com.tappx.a.a0;
import com.tappx.a.d0;
import com.tappx.a.f0;
import com.tappx.a.s5;
import com.tappx.a.u5;
import java.util.Map;

final class i0<T> extends s5<T> {
    private final d0<T> q;

    class a implements u5.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ d0 f701a;

        a(d0 d0Var) {
            this.f701a = d0Var;
        }

        @Override // com.tappx.a.u5.a
        public void a(z5 z5Var) {
            a0 a0Var;
            f0.a b = this.f701a.b();
            if (b != null) {
                if (z5Var instanceof z) {
                    a0Var = ((z) z5Var).a();
                } else {
                    q5 q5Var = z5Var.f876a;
                    Map<String, String> map = q5Var != null ? q5Var.c : null;
                    q5 q5Var2 = z5Var.f876a;
                    int i = q5Var2 != null ? q5Var2.f800a : -1;
                    if (i0.d(z5Var)) {
                        a0Var = new a0(a0.a.SERVER_ERROR, map, i);
                    } else {
                        a0Var = new a0(a0.a.NETWORK_ERROR, map, i);
                    }
                }
                b.a(a0Var);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f702a;
        static final /* synthetic */ int[] b;

        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        static {
            int[] iArr = new int[d0.b.values().length];
            b = iArr;
            try {
                iArr[d0.b.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[d0.b.HIGH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[d0.b.IMMEDIATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[d0.b.NORMAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[d0.a.values().length];
            f702a = iArr2;
            iArr2[d0.a.POST.ordinal()] = 1;
            f702a[d0.a.DELETE.ordinal()] = 2;
            f702a[d0.a.HEAD.ordinal()] = 3;
            f702a[d0.a.OPTIONS.ordinal()] = 4;
            f702a[d0.a.PATCH.ordinal()] = 5;
            f702a[d0.a.PUT.ordinal()] = 6;
            try {
                f702a[d0.a.GET.ordinal()] = 7;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public i0(d0<T> d0Var) {
        super(a(d0Var.d()), d0Var.g(), new a(d0Var));
        this.q = d0Var;
        a((d0) d0Var);
        a(d0Var.i());
    }

    private static int a(d0.a aVar) {
        switch (b.f702a[aVar.ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 7;
            case 6:
                return 2;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: private */
    public static boolean d(z5 z5Var) {
        int i;
        q5 q5Var = z5Var.f876a;
        if (q5Var != null && (i = q5Var.f800a) >= 500 && i <= 599) {
            return true;
        }
        return false;
    }

    @Override // com.tappx.a.s5
    public byte[] b() {
        return this.q.a();
    }

    @Override // com.tappx.a.s5
    public Map<String, String> f() {
        return this.q.c();
    }

    @Override // com.tappx.a.s5
    public byte[] j() {
        return b();
    }

    @Override // com.tappx.a.s5
    public s5.c m() {
        return a(this.q.e());
    }

    /* access modifiers changed from: protected */
    @Override // com.tappx.a.s5
    public u5<T> a(q5 q5Var) {
        f0<T> a2 = this.q.a(new c0(q5Var.f800a, q5Var.c, q5Var.b, q5Var.f));
        if (a2.a()) {
            return u5.a(a2.f672a, f6.a(q5Var));
        }
        return u5.a(new z(a2.b));
    }

    /* access modifiers changed from: protected */
    @Override // com.tappx.a.s5
    public void a(T t) {
        this.q.a((Object) t);
    }

    /* access modifiers changed from: package-private */
    public void a(d0<T> d0Var) {
        g0 f = d0Var.f();
        if (f != null) {
            b(f.d());
            a((w5) new k5(f.b(), f.c(), f.a()));
        }
    }

    private s5.c a(d0.b bVar) {
        int i = b.b[bVar.ordinal()];
        if (i == 1) {
            return s5.c.LOW;
        }
        if (i == 2) {
            return s5.c.HIGH;
        }
        if (i != 3) {
            return s5.c.NORMAL;
        }
        return s5.c.IMMEDIATE;
    }
}
