package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* access modifiers changed from: package-private */
public final class zznj implements Callable<T> {
    private final /* synthetic */ zzna zzaty;
    private final /* synthetic */ zzni zzatz;

    zznj(zzni zzni, zzna zzna) {
        this.zzatz = zzni;
        this.zzaty = zzna;
    }

    @Override // java.util.concurrent.Callable
    public final T call() {
        return (T) this.zzaty.zza(this.zzatz.zzatw);
    }
}
