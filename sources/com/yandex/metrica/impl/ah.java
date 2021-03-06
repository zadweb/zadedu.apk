package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.g;
import java.lang.Thread;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* access modifiers changed from: package-private */
public class ah implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private final CopyOnWriteArrayList<i> f954a = new CopyOnWriteArrayList<>();
    private final Thread.UncaughtExceptionHandler b;

    public ah(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.b = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            g.a().b(new ao());
            Iterator<i> it = this.f954a.iterator();
            while (it.hasNext()) {
                it.next().a(th);
            }
        } finally {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.b;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    public void a(i iVar) {
        this.f954a.add(iVar);
    }

    public void b(i iVar) {
        this.f954a.remove(iVar);
    }
}
