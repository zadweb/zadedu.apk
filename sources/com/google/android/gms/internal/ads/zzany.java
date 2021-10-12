package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
@zzadh
public final class zzany<T> implements zzanz<T> {
    private final T value;
    private final zzaoa zzcvt;

    zzany(T t) {
        this.value = t;
        zzaoa zzaoa = new zzaoa();
        this.zzcvt = zzaoa;
        zzaoa.zzsm();
    }

    public final boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final T get() {
        return this.value;
    }

    @Override // java.util.concurrent.Future
    public final T get(long j, TimeUnit timeUnit) {
        return this.value;
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzanz
    public final void zza(Runnable runnable, Executor executor) {
        this.zzcvt.zza(runnable, executor);
    }
}
