package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzsu extends zzki {
    private final /* synthetic */ zzst zzbnw;

    zzsu(zzst zzst) {
        this.zzbnw = zzst;
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdClicked() throws RemoteException {
        this.zzbnw.zzxo.add(new zztb(this));
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdClosed() throws RemoteException {
        this.zzbnw.zzxo.add(new zzsv(this));
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdFailedToLoad(int i) throws RemoteException {
        this.zzbnw.zzxo.add(new zzsw(this, i));
        zzakb.v("Pooled interstitial failed to load.");
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdImpression() throws RemoteException {
        this.zzbnw.zzxo.add(new zzta(this));
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdLeftApplication() throws RemoteException {
        this.zzbnw.zzxo.add(new zzsx(this));
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdLoaded() throws RemoteException {
        this.zzbnw.zzxo.add(new zzsy(this));
        zzakb.v("Pooled interstitial loaded.");
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdOpened() throws RemoteException {
        this.zzbnw.zzxo.add(new zzsz(this));
    }
}
