package com.google.android.gms.internal.ads;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;
import javax.annotation.Nullable;

@zzadh
public abstract class zzani extends AbstractExecutorService implements zzaod {
    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.AbstractExecutorService
    public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new zzaoc(runnable, t);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.AbstractExecutorService
    public final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new zzaoc(callable);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public /* synthetic */ Future submit(Runnable runnable, @Nullable Object obj) {
        return (zzanz) super.submit(runnable, obj);
    }

    @Override // com.google.android.gms.internal.ads.zzaod
    /* renamed from: zza */
    public final <T> zzanz<T> submit(Callable<T> callable) throws RejectedExecutionException {
        return (zzanz) super.submit(callable);
    }

    @Override // com.google.android.gms.internal.ads.zzaod
    /* renamed from: zze */
    public final zzanz<?> submit(Runnable runnable) throws RejectedExecutionException {
        return (zzanz) super.submit(runnable);
    }
}
