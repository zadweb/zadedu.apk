package com.tappx.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.tappx.a.m0;
import com.tappx.a.n0;
import java.lang.ref.WeakReference;
import java.util.List;

public class o0 extends p1 implements n0 {
    private n0.a d;
    private final List<m0.b> e;
    private m0 f;
    private m0 g;
    private b h;
    private b i;

    /* access modifiers changed from: private */
    public final class b implements m0.c {

        /* renamed from: a  reason: collision with root package name */
        private final m0 f773a;
        private WeakReference<View> b;

        private boolean h() {
            return this != o0.this.i;
        }

        private boolean i() {
            return this != o0.this.h;
        }

        @Override // com.tappx.a.m0.c
        public void a(View view) {
            if (!i()) {
                o0.this.e();
                o0.this.f = null;
                o0.this.h = null;
                o0.this.b();
                this.b = new WeakReference<>(view);
                o0.this.i = this;
                o0.this.g = f();
                o0.this.d.a(e(), view);
            }
        }

        @Override // com.tappx.a.m0.c
        public void b() {
            if (!h()) {
                o0.this.d.c(e());
            }
        }

        @Override // com.tappx.a.m0.c
        public void c() {
            if (!h()) {
                o0.this.d.b(e());
            }
        }

        @Override // com.tappx.a.m0.c
        public void d() {
            if (!h()) {
                o0.this.d.a(e());
            }
        }

        public u1 e() {
            return this.f773a.c();
        }

        public m0 f() {
            return this.f773a;
        }

        /* access modifiers changed from: protected */
        public View g() {
            WeakReference<View> weakReference = this.b;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        private b(m0 m0Var) {
            this.f773a = m0Var;
        }

        @Override // com.tappx.a.m0.c
        public void a(a2 a2Var) {
            if (!i()) {
                o0.this.d();
            }
        }

        @Override // com.tappx.a.m0.c
        public void a() {
            h();
        }
    }

    public o0(List<m0.b> list) {
        this.e = list;
    }

    /* access modifiers changed from: protected */
    @Override // com.tappx.a.p1
    public void c() {
        m0 m0Var = this.f;
        if (m0Var != null) {
            m0Var.b();
            this.f = null;
            this.h = null;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.tappx.a.p1
    public void b() {
        if (this.g != null) {
            View g2 = this.i.g();
            if (g2 != null) {
                a(g2);
            }
            this.g.b();
            this.g = null;
            this.i = null;
        }
    }

    @Override // com.tappx.a.n0
    public void a(n0.a aVar) {
        this.d = aVar;
    }

    /* access modifiers changed from: protected */
    @Override // com.tappx.a.p1
    public boolean a(Context context, u1 u1Var) {
        for (m0.b bVar : this.e) {
            if (bVar.a(u1Var)) {
                m0 a2 = bVar.a();
                this.f = a2;
                b a3 = a(a2);
                this.h = a3;
                this.f.a(context, a3, u1Var);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.tappx.a.p1
    public void a(a2 a2Var) {
        n0.a aVar = this.d;
        if (aVar != null) {
            aVar.a(a2Var);
        }
    }

    private void a(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    private b a(m0 m0Var) {
        return new b(m0Var);
    }
}
