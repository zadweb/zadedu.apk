package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzti extends zzahf {
    private final /* synthetic */ zzst zzbnw;

    zzti(zzst zzst) {
        this.zzbnw = zzst;
    }

    @Override // com.google.android.gms.internal.ads.zzahe
    public final void onRewardedVideoAdClosed() throws RemoteException {
        this.zzbnw.zzxo.add(new zztm(this));
    }

    @Override // com.google.android.gms.internal.ads.zzahe
    public final void onRewardedVideoAdFailedToLoad(int i) throws RemoteException {
        this.zzbnw.zzxo.add(new zztp(this, i));
    }

    @Override // com.google.android.gms.internal.ads.zzahe
    public final void onRewardedVideoAdLeftApplication() throws RemoteException {
        this.zzbnw.zzxo.add(new zzto(this));
    }

    @Override // com.google.android.gms.internal.ads.zzahe
    public final void onRewardedVideoAdLoaded() throws RemoteException {
        this.zzbnw.zzxo.add(new zztj(this));
    }

    @Override // com.google.android.gms.internal.ads.zzahe
    public final void onRewardedVideoAdOpened() throws RemoteException {
        this.zzbnw.zzxo.add(new zztk(this));
    }

    @Override // com.google.android.gms.internal.ads.zzahe
    public final void onRewardedVideoCompleted() throws RemoteException {
        this.zzbnw.zzxo.add(new zztq(this));
    }

    @Override // com.google.android.gms.internal.ads.zzahe
    public final void onRewardedVideoStarted() throws RemoteException {
        this.zzbnw.zzxo.add(new zztl(this));
    }

    @Override // com.google.android.gms.internal.ads.zzahe
    public final void zza(zzagu zzagu) throws RemoteException {
        this.zzbnw.zzxo.add(new zztn(this, zzagu));
    }
}
