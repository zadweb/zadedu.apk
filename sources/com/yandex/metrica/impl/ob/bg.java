package com.yandex.metrica.impl.ob;

import android.os.SystemClock;
import android.text.TextUtils;
import com.yandex.metrica.impl.ba;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

public abstract class bg {

    /* renamed from: a  reason: collision with root package name */
    protected t f1024a;
    protected bk b;
    protected long c = this.b.a(-1);
    protected long d;
    protected AtomicLong e = new AtomicLong(this.b.e(0));
    private boolean f = this.b.b(true);
    private volatile a g;

    /* access modifiers changed from: protected */
    public abstract bl a();

    /* access modifiers changed from: protected */
    public abstract int b();

    bg(t tVar, bk bkVar) {
        this.f1024a = tVar;
        this.b = bkVar;
        this.d = bkVar.c(SystemClock.elapsedRealtime());
        this.b.d(this.d).a();
    }

    /* access modifiers changed from: package-private */
    public long c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public long e() {
        return this.b.g(0) - TimeUnit.MILLISECONDS.toSeconds(this.d);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A[RETURN] */
    public boolean f() {
        boolean z;
        if (this.c >= 0) {
            long elapsedRealtime = (SystemClock.elapsedRealtime() / 1000) - this.b.g(0);
            if (!(elapsedRealtime < 0 || elapsedRealtime >= ((long) b()) || g() >= bh.c)) {
                a l = l();
                if (!(l != null && !l.a(this.f1024a.h()))) {
                    z = false;
                    return z;
                }
            }
        }
        z = true;
        if (z) {
        }
    }

    /* access modifiers changed from: package-private */
    public long g() {
        return TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - this.d);
    }

    /* access modifiers changed from: package-private */
    public synchronized void h() {
        this.b.h(-2147483648L).a();
        this.g = null;
    }

    /* access modifiers changed from: package-private */
    public void i() {
        this.b.h(SystemClock.elapsedRealtime() / 1000).a();
    }

    /* access modifiers changed from: package-private */
    public long j() {
        long andIncrement = this.e.getAndIncrement();
        this.b.f(this.e.get()).a();
        return andIncrement;
    }

    /* access modifiers changed from: package-private */
    public boolean k() {
        return this.f && c() > 0;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (this.f != z) {
            this.f = z;
            this.b.a(z).a();
        }
    }

    private a l() {
        if (this.g == null) {
            synchronized (this) {
                if (this.g == null) {
                    try {
                        String asString = this.f1024a.i().c(c(), a()).getAsString("report_request_parameters");
                        if (!TextUtils.isEmpty(asString)) {
                            this.g = new a(new JSONObject(asString));
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final String f1025a;
        private final String b;
        private final String c;
        private final String d;
        private final String e;
        private final String f;
        private final int g;

        a(JSONObject jSONObject) {
            this.f1025a = jSONObject.optString("kitVer");
            this.b = jSONObject.optString("clientKitVer");
            this.c = jSONObject.optString("kitBuildNumber");
            this.d = jSONObject.optString("appVer");
            this.e = jSONObject.optString("appBuild");
            this.f = jSONObject.optString("osVer");
            this.g = jSONObject.optInt("osApiLev", -1);
        }

        /* access modifiers changed from: package-private */
        public boolean a(ba baVar) {
            return TextUtils.equals(baVar.h(), this.f1025a) && TextUtils.equals(baVar.i(), this.b) && TextUtils.equals(baVar.k(), this.c) && TextUtils.equals(baVar.x(), this.d) && TextUtils.equals(baVar.z(), this.e) && TextUtils.equals(baVar.q(), this.f) && this.g == baVar.r();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void d() {
        this.c = System.currentTimeMillis() / 1000;
        this.e.set(0);
        this.d = SystemClock.elapsedRealtime();
        this.g = null;
        this.b.i(this.c).h(SystemClock.elapsedRealtime() / 1000).d(this.d).f(this.e.get()).a();
        this.f1024a.i().a(this.c, a());
        a(true);
    }
}
