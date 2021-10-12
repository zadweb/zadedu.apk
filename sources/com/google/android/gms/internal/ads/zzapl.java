package com.google.android.gms.internal.ads;

final class zzapl implements Runnable {
    private final /* synthetic */ zzapi zzcyd;

    zzapl(zzapi zzapi) {
        this.zzcyd = zzapi;
    }

    public final void run() {
        zzapi.zza(this.zzcyd, "surfaceDestroyed", new String[0]);
    }
}
