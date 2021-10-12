package com.yandex.metrica.impl.ob;

import com.appnext.base.b.d;
import com.yandex.metrica.impl.ob.fr;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectTimeoutException;

public class fq {

    /* renamed from: a  reason: collision with root package name */
    protected final fm f1146a;

    public fq(fm fmVar) {
        this.f1146a = fmVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005b, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005c, code lost:
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0063, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0064, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0067, code lost:
        r1 = r1.getStatusLine().getStatusCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006f, code lost:
        if (r3 != null) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0071, code lost:
        new com.yandex.metrica.impl.ob.ft(r3, r0, (byte) 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0078, code lost:
        if (r1 == 401) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007f, code lost:
        r0 = com.yandex.metrica.impl.ob.fr.a.SERVER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0086, code lost:
        throw new com.yandex.metrica.impl.ob.fr((byte) 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0087, code lost:
        r1 = com.yandex.metrica.impl.ob.fr.a.AUTH;
        a(r12, new com.yandex.metrica.impl.ob.fr((byte) 0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0093, code lost:
        r0 = com.yandex.metrica.impl.ob.fr.a.NETWORK;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009a, code lost:
        throw new com.yandex.metrica.impl.ob.fr((byte) 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009b, code lost:
        r0 = com.yandex.metrica.impl.ob.fr.a.DEFAULT;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a2, code lost:
        throw new com.yandex.metrica.impl.ob.fr(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bb, code lost:
        throw new java.lang.RuntimeException("Bad URL " + r12.a(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bc, code lost:
        r1 = com.yandex.metrica.impl.ob.fr.a.NO_CONNECTION;
        a(r12, new com.yandex.metrica.impl.ob.fr());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c8, code lost:
        r1 = com.yandex.metrica.impl.ob.fr.a.TIMEOUT;
        a(r12, new com.yandex.metrica.impl.ob.fr());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a3 A[ExcHandler: MalformedURLException (r0v3 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException), SYNTHETIC, Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x009b A[SYNTHETIC] */
    public ft a(fu<?> fuVar) throws fr {
        HttpResponse a2;
        while (true) {
            Map emptyMap = Collections.emptyMap();
            HttpResponse httpResponse = null;
            try {
                a2 = this.f1146a.a(fuVar);
                int statusCode = a2.getStatusLine().getStatusCode();
                Header[] allHeaders = a2.getAllHeaders();
                TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                for (int i = 0; i < allHeaders.length; i++) {
                    treeMap.put(allHeaders[i].getName(), allHeaders[i].getValue());
                }
                byte[] a3 = a2.getEntity() != null ? a(a2.getEntity()) : new byte[0];
                if (statusCode >= 200 && statusCode <= 299) {
                    return new ft(a3, treeMap, (byte) 0);
                }
                throw new IOException();
            } catch (SocketTimeoutException unused) {
            } catch (ConnectTimeoutException unused2) {
            } catch (MalformedURLException e) {
            } catch (IOException e2) {
                IOException e3 = e2;
                byte[] bArr = null;
                httpResponse = a2;
                if (httpResponse != null) {
                }
            }
        }
    }

    private static void a(fu<?> fuVar, fr frVar) throws fr {
        try {
            fuVar.o().a(frVar);
        } catch (fr e) {
            throw e;
        }
    }

    private static byte[] a(HttpEntity httpEntity) throws IOException, fr {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max((int) httpEntity.getContentLength(), 256));
        try {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                byte[] bArr = new byte[d.fb];
                while (true) {
                    int read = content.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                return byteArrayOutputStream.toByteArray();
            }
            fr.a aVar = fr.a.SERVER;
            throw new fr();
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException unused) {
            }
            byteArrayOutputStream.close();
        }
    }

    public static String a(Map<String, String> map, String str) {
        String str2 = map.get("Content-Type");
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }
}
