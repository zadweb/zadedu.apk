package com.tappx.a;

import android.content.Context;
import java.util.Iterator;

/* access modifiers changed from: package-private */
public abstract class p1 implements o1 {

    /* renamed from: a  reason: collision with root package name */
    private int f785a = 0;
    private w1 b;
    private Context c;

    p1() {
    }

    private void f() {
        c();
        if (this.b.b().size() <= this.f785a) {
            a(a2.NO_FILL);
            return;
        }
        u1 u1Var = this.b.b().get(this.f785a);
        this.f785a++;
        j0.a(f.b("mo5jy7IL/t1GLb3J/P8gjQ") + u1Var.b(), new Object[0]);
        j0.d("w73w5GD8aw/6JbEwVsPDUQ", u1Var.b());
        if (!a(this.c, u1Var)) {
            f();
        }
    }

    @Override // com.tappx.a.o1
    public void a(Context context, w1 w1Var) {
        a(w1Var);
        this.c = context;
        this.b = w1Var;
        this.f785a = 0;
        f();
    }

    /* access modifiers changed from: protected */
    public abstract void a(a2 a2Var);

    /* access modifiers changed from: protected */
    public abstract boolean a(Context context, u1 u1Var);

    /* access modifiers changed from: protected */
    public abstract void b();

    /* access modifiers changed from: protected */
    public abstract void c();

    /* access modifiers changed from: protected */
    public void d() {
        f();
    }

    @Override // com.tappx.a.o1
    public void destroy() {
        c();
        b();
    }

    /* access modifiers changed from: package-private */
    public void e() {
    }

    private void a(w1 w1Var) {
        Iterator<u1> it = w1Var.b().iterator();
        String str = "{";
        while (it.hasNext()) {
            str = str + it.next().getClass().getSimpleName() + ",";
        }
        j0.d("vowRFCKLTs9aEktGgLPt1r38zreZaMrbDBiCU39LXJU", str + "}");
    }

    @Override // com.tappx.a.o1
    public void a() {
        c();
    }
}
