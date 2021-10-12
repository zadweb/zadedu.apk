package com.tappx.a;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.tappx.a.m0;
import com.tappx.a.t3;

public class a1 {

    /* renamed from: a  reason: collision with root package name */
    private final Context f604a;
    private int b;
    private int c;
    private t3 d;
    private m0.c e;
    private t3.b f = new a();

    public a1(Context context) {
        this.f604a = context;
    }

    class a implements t3.b {
        a() {
        }

        @Override // com.tappx.a.t3.b
        public void a(View view) {
            view.setLayoutParams(new ViewGroup.LayoutParams(a1.this.b, a1.this.c));
            a1.this.e.a(view);
        }

        @Override // com.tappx.a.t3.b
        public void a(boolean z) {
        }

        @Override // com.tappx.a.t3.b
        public void b() {
            a1.this.e.d();
        }

        @Override // com.tappx.a.t3.b
        public void c() {
            a1.this.e.c();
        }

        @Override // com.tappx.a.t3.b
        public void d() {
            a1.this.e.a(a2.UNSPECIFIED);
        }

        @Override // com.tappx.a.t3.b
        public void a() {
            a1.this.e.b();
        }
    }

    public void a() {
        t3 t3Var = this.d;
        if (t3Var != null) {
            t3Var.destroy();
        }
    }

    public void a(y1 y1Var, m0.c cVar) {
        this.e = cVar;
        String h = y1Var.h();
        t3 a2 = w3.a(this.f604a, h);
        this.d = a2;
        a2.a(this.f);
        this.d.a(b4.INLINE, h, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.f604a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int l = y1Var.l();
        int j = y1Var.j();
        this.b = (int) TypedValue.applyDimension(1, (float) l, displayMetrics);
        this.c = (int) TypedValue.applyDimension(1, (float) j, displayMetrics);
    }
}
