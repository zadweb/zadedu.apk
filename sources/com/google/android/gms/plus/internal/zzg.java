package com.google.android.gms.plus.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.internal.plus.zza;
import com.google.android.gms.internal.plus.zzc;
import java.util.List;

public final class zzg extends zza implements zzf {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.plus.internal.IPlusService");
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final String getAccountName() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final ICancelToken zza(zzb zzb, int i, int i2, int i3, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzb);
        obtainAndWriteInterfaceToken.writeInt(i);
        obtainAndWriteInterfaceToken.writeInt(i2);
        obtainAndWriteInterfaceToken.writeInt(-1);
        obtainAndWriteInterfaceToken.writeString(str);
        Parcel transactAndReadException = transactAndReadException(16, obtainAndWriteInterfaceToken);
        ICancelToken asInterface = ICancelToken.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final void zza() throws RemoteException {
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final void zza(zzb zzb) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzb);
        transactAndReadExceptionReturnVoid(19, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final void zza(zzb zzb, List<String> list) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzb);
        obtainAndWriteInterfaceToken.writeStringList(list);
        transactAndReadExceptionReturnVoid(34, obtainAndWriteInterfaceToken);
    }
}
