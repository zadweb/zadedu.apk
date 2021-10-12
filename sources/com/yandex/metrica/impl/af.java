package com.yandex.metrica.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class af implements ae {

    /* renamed from: a  reason: collision with root package name */
    private Executor f943a;
    private ae b;

    abstract class a implements Runnable {
        public abstract void a() throws Exception;

        a() {
        }

        public void run() {
            try {
                a();
            } catch (Exception unused) {
            }
        }
    }

    public af(ae aeVar) {
        this(Executors.newSingleThreadExecutor(), aeVar);
    }

    @Override // com.yandex.metrica.impl.ae
    public void a() {
        this.f943a.execute(new a() {
            /* class com.yandex.metrica.impl.af.AnonymousClass1 */

            @Override // com.yandex.metrica.impl.af.a
            public void a() {
                af.this.b.a();
            }
        });
    }

    @Override // com.yandex.metrica.impl.ae
    public void a(final Intent intent, final int i) {
        this.f943a.execute(new a() {
            /* class com.yandex.metrica.impl.af.AnonymousClass2 */

            @Override // com.yandex.metrica.impl.af.a
            public void a() {
                af.this.b.a(intent, i);
            }
        });
    }

    @Override // com.yandex.metrica.impl.ae
    public void a(final Intent intent, final int i, final int i2) {
        this.f943a.execute(new a() {
            /* class com.yandex.metrica.impl.af.AnonymousClass3 */

            @Override // com.yandex.metrica.impl.af.a
            public void a() {
                af.this.b.a(intent, i, i2);
            }
        });
    }

    @Override // com.yandex.metrica.impl.ae
    public void a(final Intent intent) {
        this.f943a.execute(new a() {
            /* class com.yandex.metrica.impl.af.AnonymousClass4 */

            @Override // com.yandex.metrica.impl.af.a
            public void a() {
                af.this.b.a(intent);
            }
        });
    }

    @Override // com.yandex.metrica.impl.ae
    public void b(final Intent intent) {
        this.f943a.execute(new a() {
            /* class com.yandex.metrica.impl.af.AnonymousClass5 */

            @Override // com.yandex.metrica.impl.af.a
            public void a() {
                af.this.b.b(intent);
            }
        });
    }

    @Override // com.yandex.metrica.impl.ae
    public void c(final Intent intent) {
        this.f943a.execute(new a() {
            /* class com.yandex.metrica.impl.af.AnonymousClass6 */

            @Override // com.yandex.metrica.impl.af.a
            public void a() {
                af.this.b.c(intent);
            }
        });
    }

    @Override // com.yandex.metrica.impl.ae
    public void b() {
        this.b.b();
    }

    @Override // com.yandex.metrica.impl.ae
    public void a(final int i, final String str, final int i2, final String str2, final Bundle bundle) throws RemoteException {
        this.f943a.execute(new a() {
            /* class com.yandex.metrica.impl.af.AnonymousClass7 */

            @Override // com.yandex.metrica.impl.af.a
            public void a() throws RemoteException {
                af.this.b.a(i, str, i2, str2, bundle);
            }
        });
    }

    @Override // com.yandex.metrica.impl.ae
    public void a(final int i, final Bundle bundle) throws RemoteException {
        this.f943a.execute(new a() {
            /* class com.yandex.metrica.impl.af.AnonymousClass8 */

            @Override // com.yandex.metrica.impl.af.a
            public void a() throws Exception {
                af.this.b.a(i, bundle);
            }
        });
    }

    af(Executor executor, ae aeVar) {
        this.f943a = executor;
        this.b = aeVar;
    }
}
