package com.google.android.gms.internal.ads;

final class zzapc implements Runnable {
    private final /* synthetic */ zzaov zzcxf;

    zzapc(zzaov zzaov) {
        this.zzcxf = zzaov;
    }

    public final void run() {
        if (zzaov.zza(this.zzcxf) != null) {
            zzaov.zza(this.zzcxf).onPaused();
            zzaov.zza(this.zzcxf).zzsy();
        }
    }
}
