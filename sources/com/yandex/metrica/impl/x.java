package com.yandex.metrica.impl;

import android.os.Handler;
import java.lang.ref.WeakReference;

/* access modifiers changed from: package-private */
public class x implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Handler> f1198a;
    private final WeakReference<b> b;

    x(Handler handler, b bVar) {
        this.f1198a = new WeakReference<>(handler);
        this.b = new WeakReference<>(bVar);
    }

    public void run() {
        Handler handler = this.f1198a.get();
        b bVar = this.b.get();
        if (handler != null && bVar != null && bVar.c()) {
            w.a(handler, bVar, this);
        }
    }
}
