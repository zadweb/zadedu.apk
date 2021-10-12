package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaty extends zzej implements zzatx {
    zzaty(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gass.internal.IGassService");
    }

    @Override // com.google.android.gms.internal.ads.zzatx
    public final zzatv zza(zzatt zzatt) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, zzatt);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzatv zzatv = (zzatv) zzel.zza(transactAndReadException, zzatv.CREATOR);
        transactAndReadException.recycle();
        return zzatv;
    }
}
