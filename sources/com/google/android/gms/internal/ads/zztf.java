package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztf implements zzts {
    private final /* synthetic */ zzoa zzbnz;

    zztf(zzte zzte, zzoa zzoa) {
        this.zzbnz = zzoa;
    }

    @Override // com.google.android.gms.internal.ads.zzts
    public final void zzb(zztt zztt) throws RemoteException {
        if (zztt.zzbof != null) {
            zztt.zzbof.zza(this.zzbnz);
        }
    }
}
