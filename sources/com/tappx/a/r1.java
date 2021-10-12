package com.tappx.a;

import com.tappx.a.q1;
import com.tappx.a.v;
import com.tappx.sdk.android.AdRequest;

public class r1 implements q1 {

    /* renamed from: a  reason: collision with root package name */
    private final v f802a;
    private final k0 b;
    private final d2 c;
    private q1.a d;
    private v.b e;

    class a implements m<w1> {
        a() {
        }

        public void a(w1 w1Var) {
            r1.this.b((r1) w1Var);
        }
    }

    class b implements h<v.a> {
        b() {
        }

        public void a(v.a aVar) {
            r1.this.b((r1) aVar);
        }
    }

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f805a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            int[] iArr = new int[v.a.values().length];
            f805a = iArr;
            iArr[v.a.DEVELOPER_ERROR.ordinal()] = 1;
            f805a[v.a.SERVER_ERROR.ordinal()] = 2;
            f805a[v.a.NO_FILL.ordinal()] = 3;
            try {
                f805a[v.a.NETWORK_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public r1(v vVar, k0 k0Var, d2 d2Var) {
        this.f802a = vVar;
        this.b = k0Var;
        this.c = d2Var;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(w1 w1Var) {
        if (w1Var.f()) {
            a(a2.NO_FILL);
        } else {
            a(w1Var);
        }
    }

    @Override // com.tappx.a.q1
    public void destroy() {
        a();
    }

    @Override // com.tappx.a.q1
    public void a(q1.a aVar) {
        this.d = aVar;
    }

    @Override // com.tappx.a.q1
    public void a(String str, String str2, v1 v1Var, AdRequest adRequest) {
        a();
        this.e = this.f802a.a(this.b.a(str, v1Var, str2, adRequest), new a(), new b());
        this.c.a();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(v.a aVar) {
        a(aVar);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        v.b bVar = this.e;
        if (bVar != null) {
            this.f802a.a(bVar);
            this.e = null;
        }
    }

    private void a(v.a aVar) {
        int i = c.f805a[aVar.ordinal()];
        if (i == 1) {
            a(a2.DEVELOPER_ERROR);
        } else if (i == 2) {
            a(a2.SERVER_ERROR);
        } else if (i == 3) {
            a(a2.NO_FILL);
        } else if (i != 4) {
            a(a2.UNSPECIFIED);
        } else {
            a(a2.NETWORK_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public void a(a2 a2Var) {
        this.e = null;
        q1.a aVar = this.d;
        if (aVar != null) {
            aVar.a(a2Var);
        }
    }

    /* access modifiers changed from: protected */
    public void a(w1 w1Var) {
        this.e = null;
        q1.a aVar = this.d;
        if (aVar != null) {
            aVar.a(w1Var);
        }
    }
}
