package com.yandex.metrica.impl.ob;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class ex {
    public static X509Certificate a(InputStream inputStream) {
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(bufferedInputStream);
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
                return x509Certificate;
            } finally {
                bufferedInputStream.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
            throw th;
        }
    }

    public static X509Certificate a(String str) {
        try {
            return a(new ByteArrayInputStream(str.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public static class a extends SSLSocketFactory {

        /* renamed from: a  reason: collision with root package name */
        private final SSLContext f1129a;

        public a(SSLContext sSLContext) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
            super(null);
            this.f1129a = sSLContext;
            setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        }

        @Override // org.apache.http.conn.scheme.LayeredSocketFactory, org.apache.http.conn.ssl.SSLSocketFactory
        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return this.f1129a.getSocketFactory().createSocket(socket, str, i, z);
        }

        @Override // org.apache.http.conn.scheme.SocketFactory, org.apache.http.conn.ssl.SSLSocketFactory
        public Socket createSocket() throws IOException {
            return this.f1129a.getSocketFactory().createSocket();
        }
    }
}
