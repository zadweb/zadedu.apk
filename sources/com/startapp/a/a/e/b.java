package com.startapp.a.a.e;

import com.startapp.a.a.a.c;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final c f251a = new c();

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0052 A[SYNTHETIC, Splitter:B:30:0x0052] */
    public String a(c cVar) {
        Throwable th;
        Exception e;
        int b = cVar.b();
        int c = cVar.c();
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream2);
                for (int i = 0; i < c; i++) {
                    long[] a2 = cVar.a(i);
                    int i2 = 0;
                    while (true) {
                        if (i2 >= 4096) {
                            break;
                        }
                        int i3 = b - 1;
                        if (b <= 0) {
                            b = i3;
                            break;
                        }
                        dataOutputStream.writeLong(a2[i2]);
                        i2++;
                        b = i3;
                    }
                }
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException unused) {
                }
                return this.f251a.a(byteArrayOutputStream2.toByteArray());
            } catch (Exception e2) {
                e = e2;
                byteArrayOutputStream = byteArrayOutputStream2;
                try {
                    throw new RuntimeException("problem serializing bitSet", e);
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = byteArrayOutputStream2;
                if (byteArrayOutputStream != null) {
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            throw new RuntimeException("problem serializing bitSet", e);
        }
    }
}
