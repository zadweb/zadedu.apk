package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.co;
import com.yandex.metrica.impl.ob.cq;
import com.yandex.metrica.impl.ob.cu;
import com.yandex.metrica.impl.ob.du;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.o;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.utils.k;
import com.yandex.metrica.impl.utils.l;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* access modifiers changed from: package-private */
public class bh extends ak {

    /* renamed from: a  reason: collision with root package name */
    private ba f988a;
    private Context b;
    private t c;
    private cd m;
    private boolean n = false;
    private du o;
    private List<String> p;

    @Override // com.yandex.metrica.impl.ak
    public boolean n() {
        return true;
    }

    @Override // com.yandex.metrica.impl.ak
    public long p() {
        return 0;
    }

    public bh(t tVar) {
        this.c = tVar;
        this.b = tVar.m();
        this.f988a = tVar.h();
        this.m = tVar.E();
        this.p = this.f988a.D();
    }

    @Override // com.yandex.metrica.impl.ak
    public boolean b() {
        a(false);
        this.f988a.b(this.c);
        if (s()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.yandex.metrica.impl.ak
    public cq d() {
        return new cu(this.m.h(null)).a(h());
    }

    /* access modifiers changed from: package-private */
    public boolean s() {
        boolean z;
        boolean z2 = !this.f988a.a(this.m.a(0));
        String a2 = l.a(this.c.j().u());
        if (!z2 && !TextUtils.isEmpty(a2)) {
            if (a2.equals(this.m.m())) {
                if ((System.currentTimeMillis() - this.m.n()) / 1000 <= 86400) {
                    z2 = false;
                }
            }
            z2 = true;
        }
        if (z2) {
            return z2;
        }
        String d = ci.a().d();
        if (TextUtils.isEmpty(d)) {
            z = TextUtils.isEmpty(a(this.f988a));
        } else {
            z = TextUtils.equals(a(this.f988a), d);
        }
        return !z;
    }

    private static String a(ba baVar) {
        String c2 = baVar.c();
        String e = baVar.e();
        if (TextUtils.isEmpty(c2)) {
            return TextUtils.isEmpty(e) ? "" : e;
        }
        return c2;
    }

    /* access modifiers changed from: package-private */
    public void a(Uri.Builder builder) {
        builder.path("analytics/startup");
        builder.appendQueryParameter("deviceid", a(this.f988a));
        builder.appendQueryParameter("app_platform", this.f988a.m());
        builder.appendQueryParameter("protocol_version", this.f988a.f());
        builder.appendQueryParameter("analytics_sdk_version", this.f988a.h());
        builder.appendQueryParameter("analytics_sdk_version_name", this.f988a.g());
        builder.appendQueryParameter("model", this.f988a.p());
        builder.appendQueryParameter("manufacturer", this.f988a.o());
        builder.appendQueryParameter("os_version", this.f988a.q());
        builder.appendQueryParameter("screen_width", String.valueOf(this.f988a.s()));
        builder.appendQueryParameter("screen_height", String.valueOf(this.f988a.t()));
        builder.appendQueryParameter("screen_dpi", String.valueOf(this.f988a.u()));
        builder.appendQueryParameter("scalefactor", String.valueOf(this.f988a.v()));
        builder.appendQueryParameter("locale", this.f988a.w());
        builder.appendQueryParameter("device_type", this.f988a.G());
        builder.appendQueryParameter("query_hosts", InternalAvidAdSessionContext.AVID_API_LEVEL);
        builder.appendQueryParameter("features", bi.b("easy_collecting", "package_info", "socket", "permissions_collecting", "features_collecting"));
        builder.appendQueryParameter("browsers", "1");
        builder.appendQueryParameter("socket", "1");
        builder.appendQueryParameter("app_id", this.c.l().b());
        builder.appendQueryParameter("app_debuggable", this.f988a.N());
        Map<String, String> u = this.c.j().u();
        String v = this.c.j().v();
        if (TextUtils.isEmpty(v)) {
            v = this.m.c();
        }
        if (!bk.a(u)) {
            builder.appendQueryParameter("distribution_customization", "1");
            a(builder, "clids_set", l.a(u));
            if (!TextUtils.isEmpty(v)) {
                builder.appendQueryParameter("install_referrer", v);
            }
        }
        a(builder, "uuid", this.f988a.b());
    }

    private static void a(Uri.Builder builder, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    @Override // com.yandex.metrica.impl.ak
    public boolean c() {
        this.k = false;
        if (t()) {
            this.k = true;
        } else if (200 == this.h) {
            Map<String, String> u = this.c.j().u();
            bg.a a2 = bg.a(this.i);
            if (bg.a.EnumC0047a.OK == a2.k()) {
                this.m.u(this.f988a.y());
                this.f988a.a(a2);
                Long a3 = bg.a(l());
                if (a3 != null) {
                    k.a().a(a3.longValue());
                }
                this.f988a.b(ci.a().c(this.b, this.f988a.c()));
                String str = "";
                if (a2.o() == null) {
                    g.a().a(o.class);
                } else {
                    try {
                        str = a2.o().d();
                        g.a().b(new o(a2.o()));
                    } catch (JSONException unused) {
                    }
                }
                a(this.f988a, str);
                this.c.a(l.a(this.f988a.y()).equals(u));
                j.a(this.c.k(), this.f988a, a2);
                this.k = true;
            } else {
                this.o = du.PARSE;
            }
        }
        return this.k;
    }

    @Override // com.yandex.metrica.impl.ak
    public void g() {
        this.o = du.NETWORK;
    }

    @Override // com.yandex.metrica.impl.ak
    public void f() {
        if (!this.k) {
            if (this.o == null) {
                this.o = du.UNKNOWN;
            }
            j.a(this.c.k(), this.o);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(ba baVar, String str) {
        if (!t()) {
            this.m.j(baVar.b()).a(baVar.E()).l(baVar.C()).m(baVar.B()).n(baVar.A()).i(baVar.L()).a(baVar.H()).b(baVar.I()).c(baVar.J()).d(baVar.K()).t(str);
            String y = baVar.y();
            if (!TextUtils.isEmpty(y)) {
                this.m.p(y);
            }
            this.m.h();
            a(System.currentTimeMillis() / 1000);
            co.a().a(this.b, this.f988a.b(), baVar.L());
            if (!bi.a(baVar.c())) {
                Intent intent = new Intent("com.yandex.metrica.intent.action.SYNC");
                intent.putExtra("CAUSE", "CAUSE_DEVICE_ID");
                intent.putExtra("SYNC_TO_PKG", this.c.l().b());
                intent.putExtra("SYNC_DATA", baVar.c());
                intent.putExtra("SYNC_DATA_2", baVar.b());
                this.b.sendBroadcast(intent);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(long j) {
        this.m.b(j).h();
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(boolean z) {
        this.n = z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean t() {
        return this.n;
    }

    @Override // com.yandex.metrica.impl.ak
    public boolean o() {
        return q() + 1 < this.p.size();
    }

    @Override // com.yandex.metrica.impl.ak
    public void e() {
        super.e();
        Uri.Builder buildUpon = Uri.parse(this.p.get(q())).buildUpon();
        a(buildUpon);
        a(buildUpon.build().toString());
    }

    @Override // com.yandex.metrica.impl.ak
    public String a() {
        return "Startup task for component: " + this.c.l().toString();
    }
}
