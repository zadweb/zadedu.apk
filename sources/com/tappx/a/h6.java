package com.tappx.a;

import com.mopub.common.Constants;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class h6 extends b6 {

    /* renamed from: a  reason: collision with root package name */
    private final b f698a;
    private final SSLSocketFactory b;

    static class a extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private final HttpURLConnection f699a;

        a(HttpURLConnection httpURLConnection) {
            super(h6.b(httpURLConnection));
            this.f699a = httpURLConnection;
        }

        @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() {
            super.close();
            this.f699a.disconnect();
        }
    }

    public interface b {
        String a(String str);
    }

    public h6() {
        this(null);
    }

    private static boolean a(int i, int i2) {
        return (i == 4 || (100 <= i2 && i2 < 200) || i2 == 204 || i2 == 304) ? false : true;
    }

    /* access modifiers changed from: private */
    public static InputStream b(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    public h6(b bVar) {
        this(bVar, null);
    }

    @Override // com.tappx.a.b6
    public g6 a(s5<?> s5Var, Map<String, String> map) {
        String r = s5Var.r();
        HashMap hashMap = new HashMap();
        hashMap.putAll(map);
        hashMap.putAll(s5Var.f());
        b bVar = this.f698a;
        if (bVar != null) {
            String a2 = bVar.a(r);
            if (a2 != null) {
                r = a2;
            } else {
                throw new IOException("URL blocked by rewriter: " + r);
            }
        }
        HttpURLConnection a3 = a(new URL(r), s5Var);
        try {
            for (String str : hashMap.keySet()) {
                a3.setRequestProperty(str, (String) hashMap.get(str));
            }
            b(a3, s5Var);
            int responseCode = a3.getResponseCode();
            if (responseCode == -1) {
                throw new IOException("Could not retrieve response code from HttpUrlConnection.");
            } else if (a(s5Var.g(), responseCode)) {
                return new g6(responseCode, a(a3.getHeaderFields()), a3.getContentLength(), new a(a3));
            } else {
                g6 g6Var = new g6(responseCode, a(a3.getHeaderFields()));
                a3.disconnect();
                return g6Var;
            }
        } catch (Throwable th) {
            if (0 == 0) {
                a3.disconnect();
            }
            throw th;
        }
    }

    public h6(b bVar, SSLSocketFactory sSLSocketFactory) {
        this.f698a = bVar;
        this.b = sSLSocketFactory;
    }

    static void b(HttpURLConnection httpURLConnection, s5<?> s5Var) {
        switch (s5Var.g()) {
            case -1:
                byte[] j = s5Var.j();
                if (j != null) {
                    httpURLConnection.setRequestMethod("POST");
                    a(httpURLConnection, s5Var, j);
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                return;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                a(httpURLConnection, s5Var);
                return;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                a(httpURLConnection, s5Var);
                return;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                return;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                return;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                return;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                return;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                a(httpURLConnection, s5Var);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    static List<m5> a(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                for (String str : entry.getValue()) {
                    arrayList.add(new m5(entry.getKey(), str));
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection a(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }

    private HttpURLConnection a(URL url, s5<?> s5Var) {
        SSLSocketFactory sSLSocketFactory;
        HttpURLConnection a2 = a(url);
        int p = s5Var.p();
        a2.setConnectTimeout(p);
        a2.setReadTimeout(p);
        a2.setUseCaches(false);
        a2.setDoInput(true);
        if (Constants.HTTPS.equals(url.getProtocol()) && (sSLSocketFactory = this.b) != null) {
            ((HttpsURLConnection) a2).setSSLSocketFactory(sSLSocketFactory);
        }
        return a2;
    }

    private static void a(HttpURLConnection httpURLConnection, s5<?> s5Var) {
        byte[] b2 = s5Var.b();
        if (b2 != null) {
            a(httpURLConnection, s5Var, b2);
        }
    }

    private static void a(HttpURLConnection httpURLConnection, s5<?> s5Var, byte[] bArr) {
        httpURLConnection.setDoOutput(true);
        if (!httpURLConnection.getRequestProperties().containsKey("Content-Type")) {
            httpURLConnection.setRequestProperty("Content-Type", s5Var.c());
        }
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.write(bArr);
        dataOutputStream.close();
    }
}
