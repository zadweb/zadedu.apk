package com.startapp.a.a.d;

import com.startapp.a.a.c.d;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/* compiled from: StartAppSDK */
public class a implements e {

    /* renamed from: a  reason: collision with root package name */
    private final c f248a;

    public a(c cVar) {
        this.f248a = cVar;
    }

    @Override // com.startapp.a.a.d.e
    public String a(String str) {
        GZIPOutputStream gZIPOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream2.write(str.getBytes());
                d.a((OutputStream) gZIPOutputStream2);
                String a2 = this.f248a.a(com.startapp.a.a.c.a.a(byteArrayOutputStream.toByteArray()));
                d.a((OutputStream) gZIPOutputStream2);
                return a2;
            } catch (Exception unused) {
                gZIPOutputStream = gZIPOutputStream2;
                d.a((OutputStream) gZIPOutputStream);
                return "";
            } catch (Throwable th) {
                d.a((OutputStream) gZIPOutputStream2);
                throw th;
            }
        } catch (Exception unused2) {
            d.a((OutputStream) gZIPOutputStream);
            return "";
        }
    }
}
