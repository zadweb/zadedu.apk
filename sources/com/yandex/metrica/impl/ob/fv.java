package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ob.fu;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class fv<T> implements fu.a, fu.b<T>, Future<T> {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1154a = false;
    private T b;
    private fr c;

    public boolean isCancelled() {
        return false;
    }

    public static <E> fv<E> a() {
        return new fv<>();
    }

    private fv() {
    }

    public synchronized boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public T get() throws InterruptedException, ExecutionException {
        try {
            return a((Long) null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return a(Long.valueOf(TimeUnit.MILLISECONDS.convert(j, timeUnit)));
    }

    private synchronized T a(Long l) throws InterruptedException, ExecutionException, TimeoutException {
        if (this.c != null) {
            throw new ExecutionException(this.c);
        } else if (this.f1154a) {
            return this.b;
        } else {
            if (l == null) {
                wait(0);
            } else if (l.longValue() > 0) {
                wait(l.longValue());
            }
            if (this.c != null) {
                throw new ExecutionException(this.c);
            } else if (this.f1154a) {
                return this.b;
            } else {
                throw new TimeoutException();
            }
        }
    }

    public synchronized boolean isDone() {
        return this.f1154a || this.c != null || isCancelled();
    }

    @Override // com.yandex.metrica.impl.ob.fu.b
    public synchronized void a(T t) {
        this.f1154a = true;
        this.b = t;
        notifyAll();
    }

    @Override // com.yandex.metrica.impl.ob.fu.a
    public synchronized void a(fr frVar) {
        this.c = frVar;
        notifyAll();
    }
}
