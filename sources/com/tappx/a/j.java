package com.tappx.a;

public class j<T> implements d<T> {

    /* renamed from: a  reason: collision with root package name */
    private final long f713a;
    private T b;
    private long c;

    public j(long j) {
        this.f713a = j < 0 ? 0 : j;
    }

    private long b() {
        return e.b();
    }

    @Override // com.tappx.a.d
    public T a() {
        T t;
        long j;
        synchronized (this) {
            t = this.b;
            j = this.c;
        }
        if (t == null || j == 0 || Math.abs(b() - j) > this.f713a) {
            return null;
        }
        return t;
    }

    @Override // com.tappx.a.d
    public void a(T t) {
        synchronized (this) {
            this.b = t;
            this.c = b();
        }
    }
}
