package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
final class zzh<TResult> implements zzr<TResult> {
    private final Executor zza;
    private final Object zzb = new Object();
    private OnCanceledListener zzc;

    public zzh(Executor executor, OnCanceledListener onCanceledListener) {
        this.zza = executor;
        this.zzc = onCanceledListener;
    }

    @Override // com.google.android.gms.tasks.zzr
    public final void zza(Task<TResult> task) {
        if (task.isCanceled()) {
            synchronized (this.zzb) {
                if (this.zzc != null) {
                    this.zza.execute(new zzg(this));
                }
            }
        }
    }

    @Override // com.google.android.gms.tasks.zzr
    public final void zza() {
        synchronized (this.zzb) {
            this.zzc = null;
        }
    }
}
