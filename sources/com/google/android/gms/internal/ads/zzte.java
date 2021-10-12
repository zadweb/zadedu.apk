package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzte extends zzoe {
    private final /* synthetic */ zzst zzbnw;

    zzte(zzst zzst) {
        this.zzbnw = zzst;
    }

    @Override // com.google.android.gms.internal.ads.zzod
    public final void zza(zzoa zzoa) throws RemoteException {
        this.zzbnw.zzxo.add(new zztf(this, zzoa));
    }
}
