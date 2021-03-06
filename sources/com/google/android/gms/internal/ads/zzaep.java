package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzaep extends zzej implements zzaen {
    zzaep(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    @Override // com.google.android.gms.internal.ads.zzaen
    public final void zza(zzaef zzaef, zzaeq zzaeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, zzaef);
        zzel.zza(obtainAndWriteInterfaceToken, zzaeq);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.ads.zzaen
    public final void zza(zzaey zzaey, zzaet zzaet) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, zzaey);
        zzel.zza(obtainAndWriteInterfaceToken, zzaet);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.ads.zzaen
    public final zzaej zzb(zzaef zzaef) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, zzaef);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzaej zzaej = (zzaej) zzel.zza(transactAndReadException, zzaej.CREATOR);
        transactAndReadException.recycle();
        return zzaej;
    }

    @Override // com.google.android.gms.internal.ads.zzaen
    public final void zzb(zzaey zzaey, zzaet zzaet) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, zzaey);
        zzel.zza(obtainAndWriteInterfaceToken, zzaet);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
}
