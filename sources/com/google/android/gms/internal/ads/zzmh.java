package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzmh extends zzkl {
    final /* synthetic */ zzmf zzati;

    private zzmh(zzmf zzmf) {
        this.zzati = zzmf;
    }

    @Override // com.google.android.gms.internal.ads.zzkk
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzkk
    public final boolean isLoading() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzkk
    public final void zza(zzjj zzjj, int i) throws RemoteException {
        zzane.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzamu.zzsy.post(new zzmi(this));
    }

    @Override // com.google.android.gms.internal.ads.zzkk
    public final String zzck() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzkk
    public final void zzd(zzjj zzjj) throws RemoteException {
        zza(zzjj, 1);
    }
}
