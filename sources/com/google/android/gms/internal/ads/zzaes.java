package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaes extends zzej implements zzaeq {
    zzaes(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.request.IAdResponseListener");
    }

    @Override // com.google.android.gms.internal.ads.zzaeq
    public final void zza(zzaej zzaej) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, zzaej);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
