package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

@zzadh
public final class zzaei extends zzaer {
    private final WeakReference<zzadx> zzcen;

    public zzaei(zzadx zzadx) {
        this.zzcen = new WeakReference<>(zzadx);
    }

    @Override // com.google.android.gms.internal.ads.zzaeq
    public final void zza(zzaej zzaej) {
        zzadx zzadx = this.zzcen.get();
        if (zzadx != null) {
            zzadx.zza(zzaej);
        }
    }
}
