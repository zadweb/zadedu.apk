package com.tappx.a;

import java.io.ByteArrayOutputStream;

public class i6 extends ByteArrayOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private final d6 f712a;

    public i6(d6 d6Var, int i) {
        this.f712a = d6Var;
        ((ByteArrayOutputStream) this).buf = d6Var.a(Math.max(i, 256));
    }

    private void a(int i) {
        int i2 = ((ByteArrayOutputStream) this).count + i;
        if (i2 > ((ByteArrayOutputStream) this).buf.length) {
            byte[] a2 = this.f712a.a(i2 * 2);
            System.arraycopy(((ByteArrayOutputStream) this).buf, 0, a2, 0, ((ByteArrayOutputStream) this).count);
            this.f712a.a(((ByteArrayOutputStream) this).buf);
            ((ByteArrayOutputStream) this).buf = a2;
        }
    }

    @Override // java.io.OutputStream, java.io.ByteArrayOutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f712a.a(((ByteArrayOutputStream) this).buf);
        ((ByteArrayOutputStream) this).buf = null;
        super.close();
    }

    @Override // java.lang.Object
    public void finalize() {
        this.f712a.a(((ByteArrayOutputStream) this).buf);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) {
        a(i2);
        super.write(bArr, i, i2);
    }

    @Override // java.io.OutputStream, java.io.ByteArrayOutputStream
    public synchronized void write(int i) {
        a(1);
        super.write(i);
    }
}
