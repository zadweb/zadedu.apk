package com.google.android.gms.internal.ads;

final class zzape implements Runnable {
    private final /* synthetic */ zzaov zzcxf;

    zzape(zzaov zzaov) {
        this.zzcxf = zzaov;
    }

    public final void run() {
        if (zzaov.zza(this.zzcxf) != null) {
            zzaov.zza(this.zzcxf).onPaused();
        }
    }
}
