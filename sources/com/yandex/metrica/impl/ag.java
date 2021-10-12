package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.co;
import com.yandex.metrica.impl.ob.cp;
import com.yandex.metrica.impl.ob.dr;
import com.yandex.metrica.impl.ob.ds;
import com.yandex.metrica.impl.ob.ef;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.o;
import com.yandex.metrica.impl.ob.q;
import com.yandex.metrica.impl.ob.r;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.k;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

public class ag implements ae {
    private static final Executor c = new cp();
    private static final ExecutorService d = Executors.newSingleThreadExecutor();
    private static final Map<String, t> e = new HashMap();
    private static final q f = new q();

    /* renamed from: a  reason: collision with root package name */
    private Context f952a;
    private MetricaService.b b;

    static /* synthetic */ boolean a(CounterConfiguration counterConfiguration) {
        return counterConfiguration == null;
    }

    public ag(Context context, MetricaService.b bVar) {
        this.f952a = context;
        this.b = bVar;
    }

    @Override // com.yandex.metrica.impl.ae
    public void a() {
        new bd(this.f952a).a(this.f952a);
        k.a().a(this.f952a);
        GoogleAdvertisingIdGetter.b.f935a.a(this.f952a);
        cd cdVar = new cd(bp.a(this.f952a).d(), this.f952a.getPackageName());
        co.a().a(this.f952a, cdVar.b((String) null), cdVar.h(null));
        a(cdVar);
        ci.a().a(this.f952a);
    }

    @Override // com.yandex.metrica.impl.ae
    public void a(Intent intent, int i) {
        b(intent, i);
    }

    @Override // com.yandex.metrica.impl.ae
    public void a(Intent intent, int i, int i2) {
        b(intent, i2);
    }

    @Override // com.yandex.metrica.impl.ae
    public void a(Intent intent) {
        dr.a(this.f952a).a();
        y.a(this.f952a).a(this);
        ef.a(this.f952a).a();
    }

    @Override // com.yandex.metrica.impl.ae
    public void b(Intent intent) {
        dr.a(this.f952a).a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x001a A[SYNTHETIC] */
    @Override // com.yandex.metrica.impl.ae
    public void c(Intent intent) {
        boolean z;
        String encodedAuthority = intent.getData().getEncodedAuthority();
        synchronized (e) {
            for (Map.Entry entry : new HashMap(e).entrySet()) {
                String str = (String) entry.getKey();
                t tVar = (t) entry.getValue();
                if (!(str == null || tVar == null)) {
                    if (!str.startsWith(encodedAuthority)) {
                        z = false;
                        if (!z) {
                            e.remove(str);
                            if (tVar != null) {
                                tVar.c();
                            }
                        }
                    }
                }
                z = true;
                if (!z) {
                }
            }
        }
        if (e.isEmpty()) {
            dr.a(this.f952a).b();
        }
    }

    @Override // com.yandex.metrica.impl.ae
    public void a(int i, String str, int i2, String str2, Bundle bundle) throws RemoteException {
        bundle.setClassLoader(CounterConfiguration.class.getClassLoader());
        a(i, new h(str2, str, i2), bundle);
    }

    @Override // com.yandex.metrica.impl.ae
    public void a(int i, Bundle bundle) throws RemoteException {
        bundle.setClassLoader(CounterConfiguration.class.getClassLoader());
        a(i, h.b(bundle), bundle);
    }

    /* access modifiers changed from: package-private */
    public void a(cd cdVar) {
        String l = cdVar.l();
        if (TextUtils.isEmpty(l)) {
            g.a().a(o.class);
            return;
        }
        try {
            g.a().b(new o(new ds(l)));
        } catch (JSONException unused) {
        }
    }

    private void b(Intent intent, int i) {
        if (intent != null) {
            intent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
            boolean z = false;
            if (!(intent == null || intent.getData() == null)) {
                h b2 = h.b(intent.getExtras());
                if (b2.n()) {
                    b2.a(intent.getIntExtra("EXTRA_KEY_KEY_START_TYPE", p.a.EVENT_TYPE_UNDEFINED.a())).b(intent.getStringExtra("EXTRA_KEY_KEY_START_EVENT")).c("");
                }
                if (!b2.m() && !b2.n()) {
                    Bundle bundleExtra = intent.getBundleExtra("EXTRA_KEY_LIB_CFG");
                    if (bundleExtra == null) {
                        bundleExtra = intent.getExtras();
                    }
                    CounterConfiguration b3 = CounterConfiguration.b(bundleExtra);
                    if (b3 == null) {
                        z = true;
                    }
                    if (!z) {
                        String encodedAuthority = intent.getData().getEncodedAuthority();
                        b(b3, encodedAuthority);
                        b(b3);
                        y.a(this.f952a).a(b2.e());
                        try {
                            t tVar = new t(this.f952a, c, r.a(this.f952a, b3, null, encodedAuthority), b3, f);
                            tVar.a(b2);
                            tVar.d();
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }
        this.b.a(i);
    }

    /* access modifiers changed from: private */
    public final class a implements Runnable {
        private final int b;
        private final h c;
        private final Bundle d;
        private final Context e;

        a(Context context, h hVar, Bundle bundle, int i) {
            this.e = context.getApplicationContext();
            this.b = i;
            this.c = hVar;
            this.d = bundle;
        }

        public void run() {
            r a2;
            CounterConfiguration a3;
            CounterConfiguration b2 = CounterConfiguration.b(this.d);
            if (!ag.a(b2) && (a2 = ag.this.a(this.c, b2, this.b)) != null) {
                ag.b(b2, a2.b());
                synchronized (ag.e) {
                    ag.this.b((ag) b2);
                    ag.a(ag.this, this.e.getPackageName().equals(b2.f()), b2.m());
                    String j = b2.j();
                    if (this.d.containsKey("COUNTER_MIGRATION_CFG_OBJ") && (a3 = a(this.d)) != null && a3.D()) {
                        r a4 = r.a(this.e, a3, Integer.valueOf(this.b), null);
                        if (!ag.e.containsKey(a4.toString())) {
                            CounterConfiguration counterConfiguration = new CounterConfiguration(a3);
                            counterConfiguration.a(j);
                            ag.a(ag.this, a4, counterConfiguration, null).f();
                        }
                    }
                    t a5 = ag.a(ag.this, a2, b2, this.c);
                    if (!ag.a(a5)) {
                        y.a(this.e).a(this.c.e());
                        if (!p.a(this.c.c())) {
                            a5.a(b2);
                        }
                        if (!ag.a(a5, this.c)) {
                            a5.a(this.c);
                        }
                    }
                }
            }
        }

        private static CounterConfiguration a(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            try {
                return (CounterConfiguration) bundle.getParcelable("COUNTER_MIGRATION_CFG_OBJ");
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(CounterConfiguration counterConfiguration) {
        if (TextUtils.isEmpty(counterConfiguration.h())) {
            c(counterConfiguration);
            return;
        }
        String d2 = ci.a().d();
        if (!(TextUtils.isEmpty(d2) || TextUtils.equals(counterConfiguration.h(), d2))) {
            c(counterConfiguration);
        }
    }

    private void c(CounterConfiguration counterConfiguration) {
        String g = ci.g(this.f952a, counterConfiguration.f());
        if (!TextUtils.isEmpty(g)) {
            counterConfiguration.e(g);
        }
    }

    /* access modifiers changed from: private */
    public static void b(CounterConfiguration counterConfiguration, String str) {
        if (TextUtils.isEmpty(counterConfiguration.f())) {
            counterConfiguration.c(str);
        }
    }

    /* access modifiers changed from: package-private */
    public r a(h hVar, CounterConfiguration counterConfiguration, int i) {
        if (!p.a(hVar)) {
            return r.a(this.f952a, counterConfiguration, Integer.valueOf(i), null);
        }
        String l = hVar.l();
        boolean z = false;
        Iterator<ApplicationInfo> it = this.f952a.getPackageManager().getInstalledApplications(0).iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().packageName.equals(l)) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (z) {
            return r.a(l);
        }
        return null;
    }

    private void a(int i, h hVar, Bundle bundle) {
        if (!hVar.n()) {
            d.execute(new a(this.f952a, hVar, bundle, i));
        }
    }

    @Override // com.yandex.metrica.impl.ae
    public void b() {
        y.a(this.f952a).b(this);
        ef.a(this.f952a).b();
    }

    static /* synthetic */ t a(ag agVar, r rVar, CounterConfiguration counterConfiguration, h hVar) {
        if (rVar == null) {
            return null;
        }
        t tVar = e.get(rVar.toString());
        if (tVar == null) {
            tVar = new t(agVar.f952a, c, rVar, counterConfiguration, f);
            if (hVar == null || !p.a(hVar)) {
                e.put(rVar.toString(), tVar);
            }
        } else {
            tVar.b(counterConfiguration);
        }
        return tVar;
    }

    static /* synthetic */ void a(ag agVar, boolean z, boolean z2) {
        y.a(agVar.f952a).a(agVar, z, z2);
    }

    static /* synthetic */ boolean a(t tVar) {
        return tVar == null || tVar.o();
    }

    static /* synthetic */ boolean a(t tVar, h hVar) {
        if (p.a.EVENT_TYPE_STARTUP.a() == hVar.c()) {
            tVar.e();
            return true;
        } else if (p.a.EVENT_TYPE_REFERRER_RECEIVED.a() != hVar.c()) {
            return false;
        } else {
            tVar.b(hVar);
            return true;
        }
    }
}
