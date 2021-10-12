package com.startapp.a.a.c;

import java.io.Serializable;
import java.io.Writer;

/* compiled from: StartAppSDK */
public class e extends Writer implements Serializable {
    private final StringBuilder b;

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    public e() {
        this.b = new StringBuilder();
    }

    public e(int i) {
        this.b = new StringBuilder(i);
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public Writer append(char c) {
        this.b.append(c);
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public Writer append(CharSequence charSequence) {
        this.b.append(charSequence);
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public Writer append(CharSequence charSequence, int i, int i2) {
        this.b.append(charSequence, i, i2);
        return this;
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str != null) {
            this.b.append(str);
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        if (cArr != null) {
            this.b.append(cArr, i, i2);
        }
    }

    public String toString() {
        return this.b.toString();
    }
}
