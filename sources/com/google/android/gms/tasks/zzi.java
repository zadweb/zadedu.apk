package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
public final class zzi<TResult> implements zzr<TResult> {
    private final Executor zza;
    private final Object zzb = new Object();
    private OnCompleteListener<TResult> zzc;

    public zzi(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.zza = executor;
        this.zzc = onCompleteListener;
    }

    @Override // com.google.android.gms.tasks.zzr
    public final void zza(Task<TResult> task) {
        synchronized (this.zzb) {
            if (this.zzc != null) {
                this.zza.execute(new zzj(this, task));
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
