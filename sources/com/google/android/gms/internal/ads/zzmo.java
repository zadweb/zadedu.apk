package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzmo extends zzaha {
    private zzahe zzatl;

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void destroy() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final boolean isLoaded() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void pause() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void resume() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void setImmersiveMode(boolean z) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void setUserId(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void show() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzagx zzagx) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzahe zzahe) throws RemoteException {
        this.zzatl = zzahe;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzahk zzahk) throws RemoteException {
        zzane.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzamu.zzsy.post(new zzmp(this));
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzkx zzkx) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final Bundle zzba() throws RemoteException {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
    }
}
