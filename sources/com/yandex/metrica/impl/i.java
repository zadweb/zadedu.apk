package com.yandex.metrica.impl;

/* access modifiers changed from: package-private */
public abstract class i {

    /* renamed from: a  reason: collision with root package name */
    private final a f1006a;

    public interface a {
        boolean a(Throwable th);
    }

    /* access modifiers changed from: package-private */
    public abstract void b(Throwable th);

    i(a aVar) {
        this.f1006a = aVar;
    }

    /* access modifiers changed from: package-private */
    public void a(Throwable th) {
        if (this.f1006a.a(th)) {
            b(th);
        }
    }
}
