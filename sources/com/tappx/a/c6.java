package com.tappx.a;

import android.os.SystemClock;
import com.appnext.base.b.d;
import com.tappx.a.h5;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class c6 implements n5 {
    protected static final boolean c = a6.b;

    /* renamed from: a  reason: collision with root package name */
    private final b6 f639a;
    protected final d6 b;

    public c6(b6 b6Var) {
        this(b6Var, new d6(4096));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005e, code lost:
        r15 = null;
        r2 = r12;
        r19 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00aa, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b5, code lost:
        r1 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b8, code lost:
        r19 = r1;
        r15 = null;
        r2 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bd, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00be, code lost:
        r19 = r1;
        r15 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c3, code lost:
        r0 = r2.d();
        com.tappx.a.a6.c("Unexpected response code %d for %s", java.lang.Integer.valueOf(r0), r29.r());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00dc, code lost:
        if (r15 != null) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00de, code lost:
        r1 = new com.tappx.a.q5(r0, r15, false, android.os.SystemClock.elapsedRealtime() - r9, r19);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ef, code lost:
        if (r0 == 401) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f8, code lost:
        if (r0 < 400) goto L_0x0105;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0104, code lost:
        throw new com.tappx.a.j5(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0107, code lost:
        if (r0 < 500) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0111, code lost:
        if (r29.x() != false) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0113, code lost:
        a("server", r29, new com.tappx.a.x5(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0124, code lost:
        throw new com.tappx.a.x5(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x012a, code lost:
        throw new com.tappx.a.x5(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x012b, code lost:
        a("auth", r29, new com.tappx.a.g5(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0137, code lost:
        a("network", r29, new com.tappx.a.p5());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0148, code lost:
        throw new com.tappx.a.r5(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0149, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0164, code lost:
        throw new java.lang.RuntimeException("Bad URL " + r29.r(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0165, code lost:
        a("socket", r29, new com.tappx.a.y5());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0149 A[ExcHandler: MalformedURLException (r0v1 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:77:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0143 A[SYNTHETIC] */
    @Override // com.tappx.a.n5
    public q5 a(s5<?> s5Var) {
        g6 a2;
        List<m5> c2;
        byte[] a3;
        List<m5> list;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            List<m5> emptyList = Collections.emptyList();
            g6 g6Var = null;
            try {
                a2 = this.f639a.a(s5Var, a(s5Var.d()));
                int d = a2.d();
                c2 = a2.c();
                if (d == 304) {
                    h5.a d2 = s5Var.d();
                    if (d2 == null) {
                        return new q5(304, (byte[]) null, true, SystemClock.elapsedRealtime() - elapsedRealtime, c2);
                    }
                    return new q5(304, d2.f697a, true, SystemClock.elapsedRealtime() - elapsedRealtime, a(c2, d2));
                }
                InputStream a4 = a2.a();
                a3 = a4 != null ? a(a4, a2.b()) : new byte[0];
                a(SystemClock.elapsedRealtime() - elapsedRealtime, s5Var, a3, d);
                if (d >= 200 && d <= 299) {
                    list = c2;
                    return new q5(d, a3, false, SystemClock.elapsedRealtime() - elapsedRealtime, list);
                }
            } catch (SocketTimeoutException unused) {
            } catch (MalformedURLException e) {
            } catch (IOException e2) {
                IOException e3 = e2;
                list = c2;
                List<m5> list2 = list;
                g6Var = a2;
                byte[] bArr = a3;
                if (g6Var == null) {
                }
            }
        }
        throw new IOException();
    }

    public c6(b6 b6Var, d6 d6Var) {
        this.f639a = b6Var;
        this.b = d6Var;
    }

    private void a(long j, s5<?> s5Var, byte[] bArr, int i) {
        if (c || j > 3000) {
            Object[] objArr = new Object[5];
            objArr[0] = s5Var;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(i);
            objArr[4] = Integer.valueOf(s5Var.n().b());
            a6.b("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void a(String str, s5<?> s5Var, z5 z5Var) {
        w5 n = s5Var.n();
        int p = s5Var.p();
        try {
            n.a(z5Var);
            s5Var.a(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(p)));
        } catch (z5 e) {
            s5Var.a(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(p)));
            throw e;
        }
    }

    private Map<String, String> a(h5.a aVar) {
        if (aVar == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        String str = aVar.b;
        if (str != null) {
            hashMap.put("If-None-Match", str);
        }
        long j = aVar.d;
        if (j > 0) {
            hashMap.put("If-Modified-Since", f6.a(j));
        }
        return hashMap;
    }

    private byte[] a(InputStream inputStream, int i) {
        i6 i6Var = new i6(this.b, i);
        if (inputStream != null) {
            try {
                byte[] a2 = this.b.a(d.fb);
                while (true) {
                    int read = inputStream.read(a2);
                    if (read == -1) {
                        break;
                    }
                    i6Var.write(a2, 0, read);
                }
                byte[] byteArray = i6Var.toByteArray();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                        a6.d("Error occurred when closing InputStream", new Object[0]);
                    }
                }
                this.b.a(a2);
                i6Var.close();
                return byteArray;
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                        a6.d("Error occurred when closing InputStream", new Object[0]);
                    }
                }
                this.b.a((byte[]) null);
                i6Var.close();
                throw th;
            }
        } else {
            throw new x5();
        }
    }

    private static List<m5> a(List<m5> list, h5.a aVar) {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        if (!list.isEmpty()) {
            for (m5 m5Var : list) {
                treeSet.add(m5Var.a());
            }
        }
        ArrayList arrayList = new ArrayList(list);
        List<m5> list2 = aVar.h;
        if (list2 != null) {
            if (!list2.isEmpty()) {
                for (m5 m5Var2 : aVar.h) {
                    if (!treeSet.contains(m5Var2.a())) {
                        arrayList.add(m5Var2);
                    }
                }
            }
        } else if (!aVar.g.isEmpty()) {
            for (Map.Entry<String, String> entry : aVar.g.entrySet()) {
                if (!treeSet.contains(entry.getKey())) {
                    arrayList.add(new m5(entry.getKey(), entry.getValue()));
                }
            }
        }
        return arrayList;
    }
}
