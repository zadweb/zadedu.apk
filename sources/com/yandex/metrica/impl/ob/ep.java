package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;

/* access modifiers changed from: package-private */
public class ep implements er {

    /* renamed from: a  reason: collision with root package name */
    private final ek f1122a;
    private final ek b;

    @Override // com.yandex.metrica.impl.ob.er
    public boolean b(X509Certificate[] x509CertificateArr) {
        return false;
    }

    ep(ey eyVar) {
        this.f1122a = new ek(eyVar.a());
        this.b = new ek(eyVar.c());
    }

    @Override // com.yandex.metrica.impl.ob.er
    public boolean a(X509Certificate[] x509CertificateArr) {
        return this.f1122a.a(x509CertificateArr);
    }

    @Override // com.yandex.metrica.impl.ob.er
    public boolean c(X509Certificate[] x509CertificateArr) {
        return this.b.a(x509CertificateArr);
    }
}
