package com.tappx.a;

/* access modifiers changed from: package-private */
public class i2 {

    /* renamed from: a  reason: collision with root package name */
    private final v f705a;

    /* access modifiers changed from: package-private */
    public class a implements m<Void> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c f706a;

        a(i2 i2Var, c cVar) {
            this.f706a = cVar;
        }

        public void a(Void r2) {
            this.f706a.a(true);
        }
    }

    /* access modifiers changed from: package-private */
    public class b implements h<Void> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c f707a;

        b(i2 i2Var, c cVar) {
            this.f707a = cVar;
        }

        public void a(Void r2) {
            this.f707a.a(false);
        }
    }

    public interface c {
        void a(boolean z);
    }

    i2(v vVar) {
        this.f705a = vVar;
    }

    public void a(p2 p2Var, long j, c cVar) {
        this.f705a.a(j, p2Var, new a(this, cVar), new b(this, cVar));
    }
}
