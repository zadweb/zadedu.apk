package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.cn;
import com.yandex.metrica.impl.ob.cq;
import com.yandex.metrica.impl.ob.r;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/* access modifiers changed from: package-private */
public class aj extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f955a;
    private Executor b;
    private final BlockingQueue<b> c = new LinkedBlockingQueue();
    private final Object d = new Object();
    private volatile b e;

    public aj(Executor executor, r rVar) {
        this.f955a = executor;
        this.b = new cn();
        String.format(Locale.US, "[%s:%s]", "NetworkTaskQueue", rVar.toString());
    }

    public void a(ak akVar) {
        synchronized (this.d) {
            b bVar = new b(akVar, (byte) 0);
            if (!a(bVar)) {
                this.c.offer(bVar);
            }
        }
    }

    public void a() {
        this.e = null;
        this.c.clear();
        interrupt();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:10|11|17) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        r5.e = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0032 */
    public void run() {
        Executor executor;
        while (!Thread.currentThread().isInterrupted()) {
            this.e = this.c.take();
            ak akVar = this.e.f957a;
            if (akVar.n()) {
                executor = this.f955a;
            } else {
                executor = this.b;
            }
            executor.execute(new a(this, akVar, (byte) 0));
            this.e = null;
        }
    }

    public boolean b(ak akVar) {
        return a(new b(akVar, (byte) 0));
    }

    private boolean a(b bVar) {
        return this.c.contains(bVar) || bVar.equals(this.e);
    }

    /* access modifiers changed from: package-private */
    public void c(ak akVar) throws InterruptedException {
        boolean b2 = akVar.b();
        cq d2 = akVar.d();
        if (b2 && !d2.b()) {
            b2 = false;
        }
        while (!Thread.currentThread().isInterrupted() && b2) {
            d(akVar);
            b2 = !akVar.c() && akVar.o();
            if (b2) {
                Thread.sleep(akVar.p());
            }
        }
        akVar.f();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:? */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.BufferedInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    public void d(ak akVar) {
        OutputStream outputStream;
        OutputStream outputStream2;
        InputStream inputStream;
        HttpURLConnection httpURLConnection;
        BufferedOutputStream bufferedOutputStream;
        ?? r4;
        byte[] j;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            akVar.e();
            httpURLConnection = akVar.d().a();
            try {
                boolean z = true;
                if (2 != akVar.i() || (j = akVar.j()) == null || j.length <= 0) {
                    outputStream2 = null;
                    bufferedOutputStream = null;
                } else {
                    String m = akVar.m();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestProperty("Accept-Encoding", m);
                    httpURLConnection.setRequestProperty("Content-Encoding", m);
                    outputStream2 = httpURLConnection.getOutputStream();
                    try {
                        bufferedOutputStream = new BufferedOutputStream(outputStream2, j.length);
                        bufferedOutputStream.write(akVar.j());
                        bufferedOutputStream.flush();
                        bk.a(outputStream2);
                    } catch (Throwable unused) {
                        inputStream = null;
                        r4 = 0;
                        bufferedOutputStream2 = bufferedOutputStream;
                        outputStream = r4;
                        akVar.g();
                        bk.a(bufferedOutputStream2);
                        bk.a(outputStream);
                        bk.a(outputStream2);
                        bk.a(inputStream);
                        bk.a(httpURLConnection);
                    }
                }
                int responseCode = httpURLConnection.getResponseCode();
                akVar.a(responseCode);
                akVar.a(httpURLConnection.getHeaderFields());
                if (responseCode == 400 || responseCode == 500) {
                    z = false;
                }
                if (z) {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        r4 = new BufferedInputStream(inputStream, 8000);
                    } catch (Throwable unused2) {
                        r4 = 0;
                        bufferedOutputStream2 = bufferedOutputStream;
                        outputStream = r4;
                        akVar.g();
                        bk.a(bufferedOutputStream2);
                        bk.a(outputStream);
                        bk.a(outputStream2);
                        bk.a(inputStream);
                        bk.a(httpURLConnection);
                    }
                    try {
                        akVar.b(r.b((InputStream) r4));
                        bk.a(inputStream);
                        bufferedOutputStream2 = r4;
                    } catch (Throwable unused3) {
                        bufferedOutputStream2 = bufferedOutputStream;
                        outputStream = r4;
                        akVar.g();
                        bk.a(bufferedOutputStream2);
                        bk.a(outputStream);
                        bk.a(outputStream2);
                        bk.a(inputStream);
                        bk.a(httpURLConnection);
                    }
                } else {
                    inputStream = null;
                }
                bk.a(bufferedOutputStream);
                bk.a(bufferedOutputStream2);
            } catch (Throwable unused4) {
                inputStream = null;
                outputStream2 = inputStream;
                outputStream = outputStream2;
                akVar.g();
                bk.a(bufferedOutputStream2);
                bk.a(outputStream);
                bk.a(outputStream2);
                bk.a(inputStream);
                bk.a(httpURLConnection);
            }
        } catch (Throwable unused5) {
            httpURLConnection = null;
            inputStream = null;
            outputStream2 = inputStream;
            outputStream = outputStream2;
            akVar.g();
            bk.a(bufferedOutputStream2);
            bk.a(outputStream);
            bk.a(outputStream2);
            bk.a(inputStream);
            bk.a(httpURLConnection);
        }
        bk.a(outputStream2);
        bk.a(inputStream);
        bk.a(httpURLConnection);
    }

    /* access modifiers changed from: private */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final ak f957a;
        private final String b;

        /* synthetic */ b(ak akVar, byte b2) {
            this(akVar);
        }

        private b(ak akVar) {
            this.f957a = akVar;
            this.b = akVar.a();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.b.equals(((b) obj).b);
        }

        public int hashCode() {
            return this.b.hashCode();
        }
    }

    private class a implements Runnable {
        private final ak b;

        /* synthetic */ a(aj ajVar, ak akVar, byte b2) {
            this(akVar);
        }

        private a(ak akVar) {
            this.b = akVar;
        }

        public void run() {
            try {
                aj.this.c(this.b);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
