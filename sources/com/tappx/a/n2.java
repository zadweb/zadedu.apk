package com.tappx.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.tappx.a.i2;
import com.tappx.a.k2;
import com.tappx.sdk.android.PrivacyConsentActivity;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class n2 {
    private static final long f = TimeUnit.DAYS.toSeconds(365);

    /* renamed from: a  reason: collision with root package name */
    private final q2 f764a;
    private final k2 b;
    private final m2 c;
    private final i2 d;
    private boolean e;

    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ WeakReference f765a;
        final /* synthetic */ Runnable b;

        a(WeakReference weakReference, Runnable runnable) {
            this.f765a = weakReference;
            this.b = runnable;
        }

        public void run() {
            n2.this.a((n2) this.f765a);
            if (this.b != null) {
                n2.this.c.a(this.b);
            }
        }
    }

    class d implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Context f768a;
        final /* synthetic */ String b;

        d(Context context, String str) {
            this.f768a = context;
            this.b = str;
        }

        @Override // com.tappx.a.n2.f
        public void a() {
        }

        @Override // com.tappx.a.n2.f
        public void a(String str, String str2) {
            n2.this.a((n2) this.f768a, (Context) this.b, str2);
        }

        @Override // com.tappx.a.n2.f
        public void b() {
        }
    }

    /* access modifiers changed from: package-private */
    public class e implements i2.c {
        e() {
        }

        @Override // com.tappx.a.i2.c
        public void a(boolean z) {
            if (z) {
                n2.this.f764a.b(false);
            }
            n2.this.e = false;
        }
    }

    public interface f {
        void a();

        void a(String str, String str2);

        void b();
    }

    n2(q2 q2Var, k2 k2Var, m2 m2Var, i2 i2Var) {
        this.f764a = q2Var;
        this.b = k2Var;
        this.c = m2Var;
        this.d = i2Var;
    }

    private void c(f fVar) {
        boolean m = this.f764a.m();
        Boolean d2 = this.f764a.d();
        if (Boolean.FALSE.equals(d2) && !m) {
            fVar.b();
        } else if (!Boolean.TRUE.equals(d2) || m) {
            a(fVar);
        } else {
            b(fVar);
        }
    }

    private void k() {
        long e2 = this.f764a.e();
        if (e2 > 0 && Math.abs(l() - e2) > f) {
            this.f764a.b();
        }
    }

    private long l() {
        return System.currentTimeMillis() / 1000;
    }

    public void d() {
        this.f764a.c(true);
    }

    public boolean e() {
        return this.f764a.l();
    }

    public void f() {
        a(p2.DENIED_USER);
    }

    public void g() {
        a(p2.GRANTED_USER);
    }

    public void h() {
        this.f764a.a();
        a(p2.DENIED_DEVELOPER);
    }

    public void i() {
        this.f764a.a();
        a(p2.GRANTED_DEVELOPER);
    }

    public boolean j() {
        if (!Boolean.TRUE.equals(this.f764a.d())) {
            return false;
        }
        return this.f764a.g().b();
    }

    /* access modifiers changed from: package-private */
    public class c implements k2.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ f f767a;

        c(f fVar) {
            this.f767a = fVar;
        }

        @Override // com.tappx.a.k2.c
        public void a(String str, String str2) {
            n2.this.f764a.a(true, str);
            this.f767a.a(str, str2);
        }

        @Override // com.tappx.a.k2.c
        public void b() {
            n2.this.f764a.a(false, null);
            this.f767a.b();
        }

        @Override // com.tappx.a.k2.c
        public void a() {
            this.f767a.a();
        }
    }

    private void b(f fVar) {
        p2 g = this.f764a.g();
        String i = this.f764a.i();
        if (g != p2.MISSING_ANSWER) {
            fVar.b();
        } else if (i == null) {
            a(fVar);
        } else {
            fVar.a(i, null);
        }
    }

    private void a(p2 p2Var) {
        if (this.f764a.g() != p2Var) {
            this.f764a.a(p2Var);
            this.f764a.c(false);
            this.f764a.b(true);
            this.f764a.a(l());
            a();
        }
    }

    /* access modifiers changed from: package-private */
    public class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ WeakReference f766a;

        b(WeakReference weakReference) {
            this.f766a = weakReference;
        }

        @Override // com.tappx.a.n2.f
        public void a(String str, String str2) {
            Context context = (Context) this.f766a.get();
            if (context == null) {
                n2.this.c.a();
            } else {
                n2.this.a((n2) context, (Context) str, str2);
            }
        }

        @Override // com.tappx.a.n2.f
        public void b() {
            n2.this.c.a();
        }

        @Override // com.tappx.a.n2.f
        public void a() {
            n2.this.c.a();
        }
    }

    public void b(String str) {
        this.f764a.c(str);
    }

    public void a(boolean z) {
        this.f764a.a(z);
    }

    public String b() {
        String h = this.f764a.h();
        if (h == null || h.length() <= 5) {
            return null;
        }
        return h;
    }

    public s2 c() {
        return new s2(this.f764a.d(), this.f764a.g(), this.f764a.f(), this.f764a.k(), this.f764a.e());
    }

    public void a(Runnable runnable) {
        this.c.a(runnable);
    }

    public void a(Context context, Runnable runnable) {
        WeakReference weakReference = new WeakReference(context);
        k();
        this.c.a(new a(weakReference, runnable));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(WeakReference<Context> weakReference) {
        this.c.b();
        c(new b(weakReference));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Context context, String str, String str2) {
        Intent a2 = l2.a(context, str, str2);
        if (!(context instanceof Activity)) {
            a2.addFlags(268435456);
        }
        try {
            context.startActivity(a2);
        } catch (Exception unused) {
            String name = PrivacyConsentActivity.class.getName();
            String simpleName = PrivacyConsentActivity.class.getSimpleName();
            j0.b(f.b("dfKcWOaG8KPoMfm5zts08Qlu05+R8BIzO3YcOMbimy7M7b66oYD1J20myZSpOoOWRYcUsjDmTjtwSPWh2TgTXA"), name, simpleName);
        }
    }

    private void a(f fVar) {
        this.b.a(new c(fVar));
    }

    public void a(Context context) {
        Boolean d2 = this.f764a.d();
        boolean equals = Boolean.TRUE.equals(d2);
        String i = this.f764a.i();
        if (equals && i != null) {
            a(context, i, (String) null);
        } else if (!Boolean.FALSE.equals(d2)) {
            a(new d(context, i));
        }
    }

    public void a(String str) {
        this.f764a.b(str);
    }

    public void a(boolean z, int i, String str) {
        this.f764a.a(Boolean.valueOf(z));
        if (str != null) {
            this.f764a.a(str);
        }
        if (this.f764a.j() != i) {
            this.f764a.a(i);
            d();
        }
    }

    public void a() {
        p2 g;
        if (!this.e && this.f764a.c() && (g = this.f764a.g()) != p2.MISSING_ANSWER) {
            this.e = true;
            this.d.a(g, Math.max(l() - this.f764a.e(), 0L), new e());
        }
    }
}
