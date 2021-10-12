package com.tappx.a;

import android.os.SystemClock;
import android.text.TextUtils;
import com.tappx.a.h5;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class e6 implements h5 {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, a> f668a;
    private long b;
    private final c c;
    private final int d;

    public interface c {
        File a();
    }

    public e6(c cVar, int i) {
        this.f668a = new LinkedHashMap(16, 0.75f, true);
        this.b = 0;
        this.c = cVar;
        this.d = i;
    }

    private String d(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(str.substring(0, length).hashCode());
        return valueOf + String.valueOf(str.substring(length).hashCode());
    }

    private void e(String str) {
        a remove = this.f668a.remove(str);
        if (remove != null) {
            this.b -= remove.f669a;
        }
    }

    @Override // com.tappx.a.h5
    public synchronized h5.a a(String str) {
        a aVar = this.f668a.get(str);
        if (aVar == null) {
            return null;
        }
        File b2 = b(str);
        try {
            b bVar = new b(new BufferedInputStream(a(b2)), b2.length());
            try {
                a a2 = a.a(bVar);
                if (!TextUtils.equals(str, a2.b)) {
                    a6.b("%s: key=%s, found=%s", b2.getAbsolutePath(), str, a2.b);
                    e(str);
                    return null;
                }
                h5.a a3 = aVar.a(a(bVar, bVar.a()));
                bVar.close();
                return a3;
            } finally {
                bVar.close();
            }
        } catch (IOException e) {
            a6.b("%s: %s", b2.getAbsolutePath(), e.toString());
            c(str);
            return null;
        }
    }

    public File b(String str) {
        return new File(this.c.a(), d(str));
    }

    public synchronized void c(String str) {
        boolean delete = b(str).delete();
        e(str);
        if (!delete) {
            a6.b("Could not delete cache entry for key=%s, filename=%s", str, d(str));
        }
    }

    private void b() {
        if (!this.c.a().exists()) {
            a6.b("Re-initializing cache after external clearing.", new Object[0]);
            this.f668a.clear();
            this.b = 0;
            a();
        }
    }

    /* access modifiers changed from: package-private */
    public static class b extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private final long f670a;
        private long b;

        b(InputStream inputStream, long j) {
            super(inputStream);
            this.f670a = j;
        }

        /* access modifiers changed from: package-private */
        public long a() {
            return this.f670a - this.b;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() {
            int read = super.read();
            if (read != -1) {
                this.b++;
            }
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.b += (long) read;
            }
            return read;
        }
    }

    /* access modifiers changed from: package-private */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        long f669a;
        final String b;
        final String c;
        final long d;
        final long e;
        final long f;
        final long g;
        final List<m5> h;

        private a(String str, String str2, long j, long j2, long j3, long j4, List<m5> list) {
            this.b = str;
            this.c = "".equals(str2) ? null : str2;
            this.d = j;
            this.e = j2;
            this.f = j3;
            this.g = j4;
            this.h = list;
        }

        private static List<m5> a(h5.a aVar) {
            List<m5> list = aVar.h;
            if (list != null) {
                return list;
            }
            return f6.a(aVar.g);
        }

        static a a(b bVar) {
            if (e6.b((InputStream) bVar) == 538247942) {
                return new a(e6.b(bVar), e6.b(bVar), e6.c(bVar), e6.c(bVar), e6.c(bVar), e6.c(bVar), e6.a(bVar));
            }
            throw new IOException();
        }

        a(String str, h5.a aVar) {
            this(str, aVar.b, aVar.c, aVar.d, aVar.e, aVar.f, a(aVar));
        }

        /* access modifiers changed from: package-private */
        public h5.a a(byte[] bArr) {
            h5.a aVar = new h5.a();
            aVar.f697a = bArr;
            aVar.b = this.c;
            aVar.c = this.d;
            aVar.d = this.e;
            aVar.e = this.f;
            aVar.f = this.g;
            aVar.g = f6.a(this.h);
            aVar.h = Collections.unmodifiableList(this.h);
            return aVar;
        }

        /* access modifiers changed from: package-private */
        public boolean a(OutputStream outputStream) {
            try {
                e6.a(outputStream, 538247942);
                e6.a(outputStream, this.b);
                e6.a(outputStream, this.c == null ? "" : this.c);
                e6.a(outputStream, this.d);
                e6.a(outputStream, this.e);
                e6.a(outputStream, this.f);
                e6.a(outputStream, this.g);
                e6.a(this.h, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e2) {
                a6.b("%s", e2.toString());
                return false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public OutputStream b(File file) {
        return new FileOutputStream(file);
    }

    static int b(InputStream inputStream) {
        return (a(inputStream) << 24) | (a(inputStream) << 0) | 0 | (a(inputStream) << 8) | (a(inputStream) << 16);
    }

    private void c() {
        if (this.b >= ((long) this.d)) {
            if (a6.b) {
                a6.d("Pruning old cache entries.", new Object[0]);
            }
            long j = this.b;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, a>> it = this.f668a.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                a value = it.next().getValue();
                if (b(value.b).delete()) {
                    this.b -= value.f669a;
                } else {
                    String str = value.b;
                    a6.b("Could not delete cache entry for key=%s, filename=%s", str, d(str));
                }
                it.remove();
                i++;
                if (((float) this.b) < ((float) this.d) * 0.9f) {
                    break;
                }
            }
            if (a6.b) {
                a6.d("pruned %d files, %d bytes, %d ms", Integer.valueOf(i), Long.valueOf(this.b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    static String b(b bVar) {
        return new String(a(bVar, c(bVar)), "UTF-8");
    }

    @Override // com.tappx.a.h5
    public synchronized void a() {
        File a2 = this.c.a();
        if (!a2.exists()) {
            if (!a2.mkdirs()) {
                a6.c("Unable to create cache dir %s", a2.getAbsolutePath());
            }
            return;
        }
        File[] listFiles = a2.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                try {
                    long length = file.length();
                    b bVar = new b(new BufferedInputStream(a(file)), length);
                    try {
                        a a3 = a.a(bVar);
                        a3.f669a = length;
                        a(a3.b, a3);
                    } finally {
                        bVar.close();
                    }
                } catch (IOException unused) {
                    file.delete();
                }
            }
        }
    }

    static long c(InputStream inputStream) {
        return ((((long) a(inputStream)) & 255) << 0) | 0 | ((((long) a(inputStream)) & 255) << 8) | ((((long) a(inputStream)) & 255) << 16) | ((((long) a(inputStream)) & 255) << 24) | ((((long) a(inputStream)) & 255) << 32) | ((((long) a(inputStream)) & 255) << 40) | ((((long) a(inputStream)) & 255) << 48) | ((255 & ((long) a(inputStream))) << 56);
    }

    public e6(c cVar) {
        this(cVar, 5242880);
    }

    @Override // com.tappx.a.h5
    public synchronized void a(String str, h5.a aVar) {
        long j = this.b;
        byte[] bArr = aVar.f697a;
        long length = j + ((long) bArr.length);
        int i = this.d;
        if (length <= ((long) i) || ((float) bArr.length) <= ((float) i) * 0.9f) {
            File b2 = b(str);
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(b(b2));
                a aVar2 = new a(str, aVar);
                if (aVar2.a(bufferedOutputStream)) {
                    bufferedOutputStream.write(aVar.f697a);
                    bufferedOutputStream.close();
                    aVar2.f669a = b2.length();
                    a(str, aVar2);
                    c();
                    return;
                }
                bufferedOutputStream.close();
                a6.b("Failed to write header for %s", b2.getAbsolutePath());
                throw new IOException();
            } catch (IOException unused) {
                if (!b2.delete()) {
                    a6.b("Could not clean up file %s", b2.getAbsolutePath());
                }
                b();
            }
        }
    }

    private void a(String str, a aVar) {
        if (!this.f668a.containsKey(str)) {
            this.b += aVar.f669a;
        } else {
            this.b += aVar.f669a - this.f668a.get(str).f669a;
        }
        this.f668a.put(str, aVar);
    }

    static byte[] a(b bVar, long j) {
        long a2 = bVar.a();
        if (j >= 0 && j <= a2) {
            int i = (int) j;
            if (((long) i) == j) {
                byte[] bArr = new byte[i];
                new DataInputStream(bVar).readFully(bArr);
                return bArr;
            }
        }
        throw new IOException("streamToBytes length=" + j + ", maxLength=" + a2);
    }

    /* access modifiers changed from: package-private */
    public InputStream a(File file) {
        return new FileInputStream(file);
    }

    private static int a(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void a(OutputStream outputStream, int i) {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static void a(OutputStream outputStream, long j) {
        outputStream.write((byte) ((int) (j >>> 0)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static void a(OutputStream outputStream, String str) {
        byte[] bytes = str.getBytes("UTF-8");
        a(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static void a(List<m5> list, OutputStream outputStream) {
        if (list != null) {
            a(outputStream, list.size());
            for (m5 m5Var : list) {
                a(outputStream, m5Var.a());
                a(outputStream, m5Var.b());
            }
            return;
        }
        a(outputStream, 0);
    }

    static List<m5> a(b bVar) {
        int b2 = b((InputStream) bVar);
        if (b2 >= 0) {
            List<m5> emptyList = b2 == 0 ? Collections.emptyList() : new ArrayList<>();
            for (int i = 0; i < b2; i++) {
                emptyList.add(new m5(b(bVar).intern(), b(bVar).intern()));
            }
            return emptyList;
        }
        throw new IOException("readHeaderList size=" + b2);
    }
}
