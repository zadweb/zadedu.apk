package com.yandex.metrica.impl.ob;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* access modifiers changed from: package-private */
public class fc {

    /* renamed from: a  reason: collision with root package name */
    private Collection<X509TrustManager> f1135a = new ArrayList();

    public fc() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init((KeyStore) null);
        TrustManager[] trustManagers = instance.getTrustManagers();
        for (TrustManager trustManager : trustManagers) {
            if (trustManager instanceof X509TrustManager) {
                this.f1135a.add((X509TrustManager) trustManager);
            }
        }
    }

    public boolean a(X509Certificate[] x509CertificateArr) {
        try {
            for (X509TrustManager x509TrustManager : this.f1135a) {
                x509TrustManager.checkServerTrusted(x509CertificateArr, "RSA");
            }
            return true;
        } catch (CertificateException unused) {
            return false;
        }
    }

    public X509Certificate[] a() {
        ArrayList arrayList = new ArrayList();
        for (X509TrustManager x509TrustManager : this.f1135a) {
            arrayList.addAll(Arrays.asList(x509TrustManager.getAcceptedIssuers()));
        }
        return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
    }
}
