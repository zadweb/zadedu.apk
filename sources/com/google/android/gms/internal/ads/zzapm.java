package com.google.android.gms.internal.ads;

final class zzapm implements Runnable {
    private final /* synthetic */ zzapi zzcyd;
    private final /* synthetic */ boolean zzcye;

    zzapm(zzapi zzapi, boolean z) {
        this.zzcyd = zzapi;
        this.zzcye = z;
    }

    public final void run() {
        zzapi.zza(this.zzcyd, "windowVisibilityChanged", new String[]{"isVisible", String.valueOf(this.zzcye)});
    }
}
