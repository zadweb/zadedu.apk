package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

final class f<ResultT> implements g<ResultT> {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f178a;
    private final Object b = new Object();
    private final OnSuccessListener<? super ResultT> c;

    public f(Executor executor, OnSuccessListener<? super ResultT> onSuccessListener) {
        this.f178a = executor;
        this.c = onSuccessListener;
    }

    @Override // com.google.android.play.core.tasks.g
    public final void a(Task<ResultT> task) {
        if (task.isSuccessful()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.f178a.execute(new e(this, task));
                }
            }
        }
    }
}
