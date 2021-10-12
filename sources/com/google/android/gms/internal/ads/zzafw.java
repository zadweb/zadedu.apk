package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzafw implements zzv<Object> {
    private final /* synthetic */ zzaft zzchv;

    zzafw(zzaft zzaft) {
        this.zzchv = zzaft;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzv
    public final void zza(Object obj, Map<String, String> map) {
        synchronized (this.zzchv.mLock) {
            if (!this.zzchv.zzchr.isDone()) {
                zzafz zzafz = new zzafz(-2, map);
                if (this.zzchv.zzchp.equals(zzafz.zzol())) {
                    this.zzchv.zzchr.set(zzafz);
                }
            }
        }
    }
}
