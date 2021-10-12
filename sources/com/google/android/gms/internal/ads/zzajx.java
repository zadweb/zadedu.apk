package com.google.android.gms.internal.ads;

@zzadh
public abstract class zzajx implements zzalc<zzanz> {
    private volatile Thread zzcqr;
    private boolean zzcqs = false;
    private final Runnable zzy = new zzajy(this);

    public zzajx() {
    }

    public zzajx(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzalc
    public final void cancel() {
        onStop();
        if (this.zzcqr != null) {
            this.zzcqr.interrupt();
        }
    }

    public abstract void onStop();

    public abstract void zzdn();

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzalc
    public final /* synthetic */ zzanz zznt() {
        return this.zzcqs ? zzaki.zzc(this.zzy) : zzaki.zzb(this.zzy);
    }

    public final zzanz zzqo() {
        return this.zzcqs ? zzaki.zzc(this.zzy) : zzaki.zzb(this.zzy);
    }
}
