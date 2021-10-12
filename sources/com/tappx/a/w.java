package com.tappx.a;

import com.tappx.a.a0;
import com.tappx.a.f0;
import com.tappx.a.q;
import com.tappx.a.s;
import com.tappx.a.u;
import com.tappx.a.v;
import com.tappx.a.y;

public class w implements v {

    /* renamed from: a  reason: collision with root package name */
    private final b0 f842a;
    private final y.a b;
    private final s.a c;
    private final q.a d;
    private final u.b e;
    private String f;

    class a implements f0.b<w1> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ m f843a;

        a(w wVar, m mVar) {
            this.f843a = mVar;
        }

        public void a(w1 w1Var) {
            j0.d("0SvrL3Mu6kpegPQCJvH2Z3Pn/6HNDFPvXWhwtIQHwo9OKbwcnbuQeVUXYNnm0mlw", Integer.valueOf(w1Var.b().size()));
            this.f843a.a(w1Var);
        }
    }

    class b implements f0.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ h f844a;

        b(h hVar) {
            this.f844a = hVar;
        }

        @Override // com.tappx.a.f0.a
        public void a(a0 a0Var) {
            j0.d("UJIn9VFKpDDGLj92vFtsDu89edbIfDnf+1BS1Op+N3ibnXDClU6Qn4m9zcPUJndT", String.valueOf(a0Var.a()), Integer.valueOf(a0Var.b));
            this.f844a.a(w.this.a(a0Var));
        }
    }

    class c implements f0.b<y1> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ m f845a;

        c(w wVar, m mVar) {
            this.f845a = mVar;
        }

        public void a(y1 y1Var) {
            j0.d("+QDdaoqvt3qJgm+ZybmeEWKfNA+0iZtjtThflGW6n9w", new Object[0]);
            this.f845a.a(y1Var);
        }
    }

    class d implements f0.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ h f846a;

        d(h hVar) {
            this.f846a = hVar;
        }

        @Override // com.tappx.a.f0.a
        public void a(a0 a0Var) {
            j0.d("w4andMWX7t5Jfk790BgWUrMNcOuo2+YQaoHGCbc1pcv+JGcCt9DRPjxTYUAbO5pE", String.valueOf(a0Var.a()), Integer.valueOf(a0Var.b));
            this.f846a.a(w.this.a(a0Var));
        }
    }

    class e implements f0.b<j2> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ m f847a;

        e(w wVar, m mVar) {
            this.f847a = mVar;
        }

        public void a(j2 j2Var) {
            this.f847a.a(j2Var);
        }
    }

    class f implements f0.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ h f848a;

        f(w wVar, h hVar) {
            this.f848a = hVar;
        }

        @Override // com.tappx.a.f0.a
        public void a(a0 a0Var) {
            this.f848a.a(null);
        }
    }

    class g implements f0.b<Void> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ m f849a;

        g(w wVar, m mVar) {
            this.f849a = mVar;
        }

        public void a(Void r2) {
            this.f849a.a(r2);
        }
    }

    class h implements f0.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ h f850a;

        h(w wVar, h hVar) {
            this.f850a = hVar;
        }

        @Override // com.tappx.a.f0.a
        public void a(a0 a0Var) {
            this.f850a.a(null);
        }
    }

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class i {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f851a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            int[] iArr = new int[a0.a.values().length];
            f851a = iArr;
            iArr[a0.a.SERVER_ERROR.ordinal()] = 1;
            f851a[a0.a.PARSE_ERROR.ordinal()] = 2;
            f851a[a0.a.NO_FILL.ordinal()] = 3;
            try {
                f851a[a0.a.NETWORK_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    protected static final class j extends v.b {

        /* renamed from: a  reason: collision with root package name */
        private final d0<?> f852a;

        /* synthetic */ j(d0 d0Var, a aVar) {
            this(d0Var);
        }

        public d0<?> a() {
            return this.f852a;
        }

        private j(d0<?> d0Var) {
            this.f852a = d0Var;
        }
    }

    public w(b0 b0Var, y.a aVar, s.a aVar2, q.a aVar3, u.b bVar, String str) {
        this.f842a = b0Var;
        this.b = aVar;
        this.d = aVar3;
        this.c = aVar2;
        this.e = bVar;
        this.f = str;
    }

    @Override // com.tappx.a.v
    public v.b a(b2 b2Var, m<w1> mVar, h<v.a> hVar) {
        y a2 = this.b.a(this.f, b2Var, new a(this, mVar), new b(hVar));
        j0.d("upDm/dcl7UFgv/WqQEFY8gxmh3157yb0PYmjrJydiuLWTs98xZyVkrKHoj9tmnz38qJvrbo3OSEcqRch9gbHFw", new Object[0]);
        this.f842a.a(a2);
        return new j(a2, null);
    }

    /* access modifiers changed from: package-private */
    public v.a a(a0 a0Var) {
        int i2 = i.f851a[a0Var.a().ordinal()];
        if (i2 == 1 || i2 == 2) {
            return v.a.SERVER_ERROR;
        }
        if (i2 != 3) {
            return v.a.NETWORK_ERROR;
        }
        return v.a.NO_FILL;
    }

    @Override // com.tappx.a.v
    public void a(v.b bVar) {
        if (bVar instanceof j) {
            this.f842a.b(((j) bVar).a());
        }
    }

    @Override // com.tappx.a.v
    public v.b a(z1 z1Var, m<y1> mVar, h<v.a> hVar) {
        s a2 = this.c.a(this.f, z1Var.h(), new c(this, mVar), new d(hVar));
        this.f842a.a(a2);
        return new j(a2, null);
    }

    @Override // com.tappx.a.v
    public v.b a(m<j2> mVar, h<Void> hVar) {
        q a2 = this.d.a(new e(this, mVar), new f(this, hVar));
        this.f842a.a(a2);
        return new j(a2, null);
    }

    @Override // com.tappx.a.v
    public v.b a(long j2, p2 p2Var, m<Void> mVar, h<Void> hVar) {
        u a2 = this.e.a(new g(this, mVar), new h(this, hVar), j2, p2Var);
        this.f842a.a(a2);
        return new j(a2, null);
    }
}
