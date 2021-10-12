package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.locks.ReentrantLock;

public class ew implements fk {

    /* renamed from: a  reason: collision with root package name */
    private final a f1127a;

    public ew(Context context, fd fdVar) {
        this(new fe(context), fdVar);
    }

    ew(fe feVar, fd fdVar) {
        if (fdVar.d() != null) {
            this.f1127a = new a(feVar, fdVar);
            return;
        }
        throw new IllegalArgumentException("UUID provider must be set");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new UnsupportedOperationException();
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (x509CertificateArr == null || x509CertificateArr.length == 0 || str == null || str.length() == 0) {
            throw new IllegalArgumentException("null or zero-length parameter");
        } else if (!b(a(x509CertificateArr))) {
            throw new CertificateException("Can't trust certificate chain");
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.f1127a.d().a();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0020 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0026 A[SYNTHETIC, Splitter:B:12:0x0026] */
    private boolean c(X509Certificate[] x509CertificateArr) {
        fh d = this.f1127a.a().d();
        if (d == null) {
            return false;
        }
        ReentrantLock a2 = d.a();
        a2.lock();
        if (d(x509CertificateArr)) {
            a2.unlock();
            return true;
        }
        try {
            if (d.b()) {
                try {
                    return d(x509CertificateArr);
                } catch (CertificateException unused) {
                }
            }
            a2.unlock();
            return false;
        } finally {
            a2.unlock();
        }
    }

    private boolean e(X509Certificate[] x509CertificateArr) throws CertificateException {
        for (er erVar : this.f1127a.e()) {
            if (erVar.c(x509CertificateArr)) {
                return true;
            }
        }
        this.f1127a.c();
        throw new el(new ff(x509CertificateArr));
    }

    /* access modifiers changed from: package-private */
    public X509Certificate[] a(X509Certificate[] x509CertificateArr) {
        boolean z;
        X509Certificate[] x509CertificateArr2 = x509CertificateArr;
        int i = 0;
        while (i < x509CertificateArr2.length) {
            int i2 = i + 1;
            int i3 = i2;
            while (true) {
                z = true;
                if (i3 >= x509CertificateArr2.length) {
                    z = false;
                    break;
                } else if (!x509CertificateArr2[i].getIssuerDN().equals(x509CertificateArr2[i3].getSubjectDN())) {
                    i3++;
                } else if (i3 != i2) {
                    if (x509CertificateArr2 == x509CertificateArr) {
                        x509CertificateArr2 = (X509Certificate[]) x509CertificateArr.clone();
                    }
                    X509Certificate x509Certificate = x509CertificateArr2[i3];
                    x509CertificateArr2[i3] = x509CertificateArr2[i2];
                    x509CertificateArr2[i2] = x509Certificate;
                }
            }
            if (z) {
                i = i2;
            } else if (i2 == x509CertificateArr2.length) {
                return x509CertificateArr2;
            } else {
                X509Certificate[] x509CertificateArr3 = new X509Certificate[i2];
                System.arraycopy(x509CertificateArr2, 0, x509CertificateArr3, 0, i2);
                return x509CertificateArr3;
            }
        }
        return x509CertificateArr2;
    }

    /* access modifiers changed from: private */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final fe f1128a;
        private final fd b;
        private volatile er[] c;
        private volatile fc d;
        private volatile eu e;
        private volatile ej f;
        private volatile eq g;

        public a(fe feVar, fd fdVar) {
            this.f1128a = feVar;
            this.b = fdVar;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private eq a() {
            if (this.g == null) {
                synchronized (this) {
                    if (this.g == null) {
                        this.g = new eq(this.f1128a, this.b);
                    }
                }
            }
            return this.g;
        }

        private ej b() {
            if (this.f == null) {
                synchronized (this) {
                    if (this.f == null) {
                        this.f = new ej();
                    }
                }
            }
            return this.f;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private eu c() {
            if (this.e == null) {
                synchronized (this) {
                    if (this.e == null) {
                        this.e = new eu(b().b());
                    }
                }
            }
            return this.e;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private fc d() {
            if (this.d == null) {
                synchronized (this) {
                    if (this.d == null) {
                        try {
                            this.d = new fc();
                        } catch (GeneralSecurityException e2) {
                            throw new IllegalStateException("Can't get system trust manager", e2);
                        }
                    }
                }
            }
            return this.d;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private er[] e() {
            if (this.c == null) {
                synchronized (this) {
                    if (this.c == null) {
                        ep epVar = new ep(a());
                        this.c = new er[]{new ei(b()), epVar};
                    }
                }
            }
            return this.c;
        }
    }

    private boolean b(X509Certificate[] x509CertificateArr) throws CertificateException {
        try {
            if (this.f1127a.d().a(x509CertificateArr)) {
                boolean d = d(x509CertificateArr);
                this.f1127a.a().e();
                return d;
            }
            throw new CertificateException("System doesn't trust certificate chain");
        } catch (el unused) {
            boolean c = c(x509CertificateArr);
            if (c) {
                return c;
            }
            this.f1127a.c().a(x509CertificateArr);
            return d(x509CertificateArr);
        }
    }

    private boolean d(X509Certificate[] x509CertificateArr) throws CertificateException {
        boolean z;
        er[] e = this.f1127a.e();
        int length = e.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (e[i].b(x509CertificateArr)) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            for (er erVar : this.f1127a.e()) {
                if (erVar.a(x509CertificateArr)) {
                    throw new CertificateException("There is blacklisted certificate in chain");
                }
            }
            return e(x509CertificateArr);
        }
    }
}
