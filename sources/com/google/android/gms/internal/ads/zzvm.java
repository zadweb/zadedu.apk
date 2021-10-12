package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public final class zzvm implements Runnable {
    private final /* synthetic */ zzvw zzbqi;
    private final /* synthetic */ zzuu zzbqj;
    private final /* synthetic */ zzvf zzbqk;

    zzvm(zzvf zzvf, zzvw zzvw, zzuu zzuu) {
        this.zzbqk = zzvf;
        this.zzbqi = zzvw;
        this.zzbqj = zzuu;
    }

    public final void run() {
        synchronized (this.zzbqk.mLock) {
            if (this.zzbqi.getStatus() != -1) {
                if (this.zzbqi.getStatus() != 1) {
                    this.zzbqi.reject();
                    Executor executor = zzaoe.zzcvy;
                    zzuu zzuu = this.zzbqj;
                    zzuu.getClass();
                    executor.execute(zzvn.zza(zzuu));
                    zzakb.v("Could not receive loaded message in a timely manner. Rejecting.");
                }
            }
        }
    }
}
