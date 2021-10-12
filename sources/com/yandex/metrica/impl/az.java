package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.impl.ad;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.j;
import com.yandex.metrica.impl.ob.k;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class az implements ad.a {

    /* renamed from: a  reason: collision with root package name */
    private final s f975a;
    private final ad b;
    private final Object c = new Object();
    private final ExecutorService d = Executors.newSingleThreadExecutor();

    public interface c {
        h a(h hVar);
    }

    public az(s sVar) {
        this.f975a = sVar;
        ad a2 = sVar.a();
        this.b = a2;
        a2.a(this);
    }

    public Future<Void> a(d dVar) {
        return this.d.submit((Callable) (dVar.c() ? new a(this, dVar, (byte) 0) : new b(this, dVar, (byte) 0)));
    }

    @Override // com.yandex.metrica.impl.ad.a
    public void a() {
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    /* access modifiers changed from: private */
    public class b implements Callable<Void> {
        final d b;
        boolean c;

        /* synthetic */ b(az azVar, d dVar, byte b2) {
            this(dVar);
        }

        private b(d dVar) {
            this.b = dVar;
            g.a().a(this, ao.class, k.a(new j<ao>() {
                /* class com.yandex.metrica.impl.az.b.AnonymousClass1 */

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.yandex.metrica.impl.ob.i] */
                @Override // com.yandex.metrica.impl.ob.j
                public /* bridge */ /* synthetic */ void a(ao aoVar) {
                    a();
                }

                public void a() {
                    b.this.c = true;
                }
            }).a());
        }

        /* renamed from: a */
        public Void call() {
            int i = 0;
            do {
                try {
                    IMetricaService e = az.this.b.e();
                    if (e != null && a(e, this.b)) {
                        break;
                    }
                    i++;
                    if (!b() || this.c) {
                        break;
                    }
                } catch (Throwable unused) {
                    g.a().a(this);
                    return null;
                }
            } while (i < 3);
            g.a().a(this);
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            az.this.b.a();
            synchronized (az.this.c) {
                if (!az.this.b.d()) {
                    try {
                        az.this.c.wait(500, 0);
                    } catch (InterruptedException unused) {
                        az.this.c.notifyAll();
                        az.this.d.shutdownNow();
                    }
                }
            }
            return true;
        }

        private boolean a(IMetricaService iMetricaService, d dVar) {
            try {
                az.this.f975a.a(iMetricaService, dVar.b(), dVar.b);
                return true;
            } catch (RemoteException unused) {
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public class a extends b {
        /* synthetic */ a(az azVar, d dVar, byte b) {
            this(dVar);
        }

        private a(d dVar) {
            super(az.this, dVar, (byte) 0);
        }

        @Override // com.yandex.metrica.impl.az.b
        /* renamed from: a */
        public Void call() {
            az.this.b.b();
            return super.call();
        }

        /* access modifiers changed from: package-private */
        @Override // com.yandex.metrica.impl.az.b
        public boolean b() {
            d dVar = this.b;
            Context b = az.this.f975a.b();
            Intent c = be.c(b);
            c.putExtras(dVar.f978a.a(dVar.b.c()));
            b.startService(c);
            return false;
        }
    }

    public static class d {

        /* renamed from: a  reason: collision with root package name */
        private h f978a;
        private aw b;
        private boolean c = false;
        private c d;

        d(h hVar, aw awVar) {
            this.f978a = hVar;
            this.b = awVar;
        }

        /* access modifiers changed from: package-private */
        public d a(c cVar) {
            this.d = cVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public d a(boolean z) {
            this.c = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public aw a() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public h b() {
            c cVar = this.d;
            return cVar != null ? cVar.a(this.f978a) : this.f978a;
        }

        /* access modifiers changed from: package-private */
        public boolean c() {
            return this.c;
        }

        public String toString() {
            return "ReportToSend{mReport=" + this.f978a + ", mEnvironment=" + this.b + ", mCrash=" + this.c + ", mAction=" + this.d + '}';
        }
    }
}
