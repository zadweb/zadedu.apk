package com.yandex.metrica.impl.ob;

import android.util.Log;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* access modifiers changed from: package-private */
public class eu {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1126a = eu.class.getSimpleName();
    private ArrayList<Object> b = new ArrayList<>();
    private ek c;
    private final Lock d;
    private final Lock e;
    private final Condition f;
    private ff g;

    eu(fb fbVar) {
        this.c = new eo(fbVar);
        this.d = new ReentrantLock();
        this.e = new ReentrantLock();
        this.f = this.d.newCondition();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:15|16|27|25|13|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0058, code lost:
        r3.d.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005d, code lost:
        throw r4;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x003a */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A[SYNTHETIC, Splitter:B:15:0x0042] */
    public void a(X509Certificate[] x509CertificateArr) {
        this.e.lock();
        try {
            if (!this.c.a(x509CertificateArr)) {
                this.g = new ff(x509CertificateArr);
                boolean z = false;
                Iterator<Object> it = this.b.iterator();
                while (it.hasNext()) {
                    it.next();
                    z = true;
                }
                if (z) {
                    Log.i(f1126a, "waiting for trust issue resolve");
                    this.d.lock();
                    while (!this.g.b()) {
                        this.f.await(30000, TimeUnit.MILLISECONDS);
                        this.g.c();
                    }
                }
            }
        } finally {
            this.e.unlock();
        }
    }
}
