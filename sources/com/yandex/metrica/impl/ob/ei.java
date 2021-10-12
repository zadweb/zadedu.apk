package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;

/* access modifiers changed from: package-private */
public class ei implements er {

    /* renamed from: a  reason: collision with root package name */
    private final ek f1116a;
    private final ek b;
    private final ek c;

    ei(ey eyVar) {
        this.f1116a = new ek(eyVar.a());
        this.b = new eo(eyVar.b());
        this.c = new ek(eyVar.c());
    }

    @Override // com.yandex.metrica.impl.ob.er
    public boolean a(X509Certificate[] x509CertificateArr) {
        return this.f1116a.a(x509CertificateArr);
    }

    @Override // com.yandex.metrica.impl.ob.er
    public boolean b(X509Certificate[] x509CertificateArr) {
        return this.b.a(x509CertificateArr);
    }

    @Override // com.yandex.metrica.impl.ob.er
    public boolean c(X509Certificate[] x509CertificateArr) {
        return this.c.a(x509CertificateArr);
    }
}
