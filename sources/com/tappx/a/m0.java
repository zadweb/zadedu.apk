package com.tappx.a;

import android.content.Context;
import android.view.View;
import com.tappx.a.n;
import com.tappx.a.n1;
import com.tappx.a.u1;

public abstract class m0<T extends u1> implements n1.b {

    /* renamed from: a  reason: collision with root package name */
    private final t2 f751a;
    private final n1 b;
    private m0<T>.d c;
    private T d;
    private boolean e;

    public interface b<T extends u1> {
        m0<T> a();

        boolean a(u1 u1Var);
    }

    public interface c {
        void a();

        void a(View view);

        void a(a2 a2Var);

        void b();

        void c();

        void d();
    }

    private class d implements c {

        /* renamed from: a  reason: collision with root package name */
        private final c f752a;

        @Override // com.tappx.a.m0.c
        public void a(View view) {
            m0.this.a(true);
            this.f752a.a(view);
        }

        @Override // com.tappx.a.m0.c
        public void b() {
            this.f752a.b();
        }

        @Override // com.tappx.a.m0.c
        public void c() {
            this.f752a.c();
        }

        @Override // com.tappx.a.m0.c
        public void d() {
            this.f752a.d();
        }

        private d(c cVar) {
            this.f752a = cVar;
        }

        @Override // com.tappx.a.m0.c
        public void a(a2 a2Var) {
            m0.this.a(true);
            this.f752a.a(a2Var);
        }

        @Override // com.tappx.a.m0.c
        public void a() {
            this.f752a.a();
        }
    }

    protected m0(t2 t2Var, n1 n1Var) {
        this.f751a = t2Var;
        this.b = n1Var;
        n1Var.a(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: com.tappx.a.u1 */
    /* JADX WARN: Multi-variable type inference failed */
    public void a(Context context, c cVar, u1 u1Var) {
        try {
            this.d = u1Var;
            a(false);
            this.c = new d(cVar);
            f();
            j0.d("w73w5GD8aw/6JbEwVsPDUQ", getClass().getSimpleName());
            b(context, this.c, this.d);
        } catch (ClassCastException unused) {
            cVar.a(a2.INTERNAL_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.b.a();
        e();
    }

    /* access modifiers changed from: protected */
    public abstract void b(Context context, c cVar, T t);

    /* access modifiers changed from: package-private */
    public T c() {
        return this.d;
    }

    public t2 d() {
        return this.f751a;
    }

    /* access modifiers changed from: protected */
    public abstract void e();

    /* access modifiers changed from: package-private */
    public void f() {
        a(a(this.d));
    }

    public m0(t2 t2Var) {
        this(t2Var, new n1());
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        if (j < n.a.f761a) {
            j0.d("/K518OsQwGWEySte999XTohBdjGQhBkPInQIUsTjG/cUwA8AiN+9hbf5gwErXHcf", Long.valueOf(j));
            j = n.a.f761a;
        } else if (j > n.a.b) {
            j0.d("wUWo9wuOBqc42QHm8/JVjGXXMTT2DoYHEa3wguYezUW0KEhBaolGwT3KPMo6Sz+d", Long.valueOf(j));
            j = n.a.b;
        }
        this.b.a(j);
    }

    /* access modifiers changed from: protected */
    public long a(T t) {
        return n.f760a;
    }

    @Override // com.tappx.a.n1.b
    public void a() {
        if (!this.e) {
            j0.d("tJ/RDdwmde5sNRNl2OtVTfCw4OuAfiMIPu/zkSLGIT2zwNm3C7thqGQX04tsdosL", new Object[0]);
            m0<T>.d dVar = this.c;
            if (dVar != null) {
                dVar.a(a2.TIMEOUT);
            }
        }
    }

    public void a(boolean z) {
        this.e = z;
        if (z) {
            this.b.a();
        }
    }
}
