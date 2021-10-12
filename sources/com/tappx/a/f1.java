package com.tappx.a;

import android.content.Context;
import com.tappx.a.d1;
import com.tappx.a.e1;
import java.util.List;

public class f1 extends p1 implements e1 {
    private e1.a d;
    private final List<d1.a> e;
    private d1 f;
    private d1 g;
    private a h;
    private a i;

    public f1(List<d1.a> list) {
        this.e = list;
    }

    /* access modifiers changed from: protected */
    @Override // com.tappx.a.p1
    public void c() {
        d1 d1Var = this.f;
        if (d1Var != null) {
            d1Var.b();
            this.f = null;
            this.h = null;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.tappx.a.p1
    public void b() {
        d1 d1Var = this.g;
        if (d1Var != null) {
            d1Var.b();
            this.g = null;
            this.i = null;
        }
    }

    @Override // com.tappx.a.e1
    public void a(e1.a aVar) {
        this.d = aVar;
    }

    /* access modifiers changed from: protected */
    @Override // com.tappx.a.p1
    public boolean a(Context context, u1 u1Var) {
        for (d1.a aVar : this.e) {
            if (aVar.a(u1Var)) {
                d1 a2 = aVar.a();
                this.f = a2;
                a a3 = a(a2);
                this.h = a3;
                this.f.a(context, a3, u1Var);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public final class a implements d1.b {

        /* renamed from: a  reason: collision with root package name */
        private final d1 f673a;

        public a(d1 d1Var) {
            this.f673a = d1Var;
        }

        private boolean g() {
            return this != f1.this.i;
        }

        private boolean h() {
            return this != f1.this.h;
        }

        @Override // com.tappx.a.d1.b
        public void a() {
        }

        @Override // com.tappx.a.d1.b
        public void a(d1 d1Var) {
            if (!h()) {
                f1.this.e();
                f1.this.f = null;
                f1.this.h = null;
                f1.this.b();
                f1.this.i = this;
                f1.this.g = f();
                f1.this.d.a(e(), f());
            }
        }

        @Override // com.tappx.a.d1.b
        public void b() {
        }

        @Override // com.tappx.a.d1.b
        public void c() {
            f1.this.d.a(e());
        }

        @Override // com.tappx.a.d1.b
        public void d() {
            if (!g()) {
                f1.this.d.b(e());
            }
        }

        public u1 e() {
            return this.f673a.c();
        }

        public d1 f() {
            return this.f673a;
        }

        @Override // com.tappx.a.d1.b
        public void a(a2 a2Var) {
            if (!h()) {
                f1.this.d();
            }
        }
    }

    private a a(d1 d1Var) {
        return new a(d1Var);
    }

    /* access modifiers changed from: protected */
    @Override // com.tappx.a.p1
    public void a(a2 a2Var) {
        e1.a aVar = this.d;
        if (aVar != null) {
            aVar.a(a2Var);
        }
    }
}
