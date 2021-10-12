package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.yandex.metrica.IMetricaService;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ad {

    /* renamed from: a  reason: collision with root package name */
    public static final long f940a = TimeUnit.SECONDS.toMillis(10);
    private final Context b;
    private final Handler c;
    private final List<a> d = new CopyOnWriteArrayList();
    private volatile IMetricaService e = null;
    private final Runnable f = new Runnable() {
        /* class com.yandex.metrica.impl.ad.AnonymousClass1 */

        public void run() {
            ad.this.f();
        }
    };
    private final ServiceConnection g = new ServiceConnection() {
        /* class com.yandex.metrica.impl.ad.AnonymousClass2 */

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ad.this.e = IMetricaService.Stub.asInterface(iBinder);
            ad.b(ad.this);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            ad.this.e = null;
            ad.this.g();
        }
    };

    /* access modifiers changed from: package-private */
    public interface a {
        void a();
    }

    public ad(Context context, Handler handler) {
        this.b = context.getApplicationContext();
        this.c = handler;
    }

    public synchronized void a() {
        if (this.e == null) {
            try {
                this.b.bindService(be.c(this.b), this.g, 1);
            } catch (Exception unused) {
            }
        }
    }

    public void b() {
        this.c.removeCallbacks(this.f);
        this.c.postDelayed(this.f, f940a);
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.c.removeCallbacks(this.f);
    }

    public boolean d() {
        return this.e != null;
    }

    public IMetricaService e() {
        return this.e;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void f() {
        if (this.b != null && d()) {
            try {
                this.b.unbindService(this.g);
                this.e = null;
            } catch (Exception unused) {
            }
        }
        this.e = null;
        g();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void g() {
        Iterator<a> it = this.d.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    public void a(a aVar) {
        this.d.add(aVar);
    }

    static /* synthetic */ void b(ad adVar) {
        for (a aVar : adVar.d) {
            aVar.a();
        }
    }
}
