package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzzk extends zzek implements zzzj {
    public zzzk() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzzj zzt(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        return queryLocalInterface instanceof zzzj ? (zzzj) queryLocalInterface : new zzzl(iBinder);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzek
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzzt zzzt;
        zzzm zzzm = null;
        zzzh zzzi = null;
        zzzf zzzg = null;
        switch (i) {
            case 1:
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                String readString = parcel.readString();
                Bundle bundle = (Bundle) zzel.zza(parcel, Bundle.CREATOR);
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
                    zzzm = queryLocalInterface instanceof zzzm ? (zzzm) queryLocalInterface : new zzzn(readStrongBinder);
                }
                zza(asInterface, readString, bundle, zzzm);
                parcel2.writeNoException();
                return true;
            case 2:
                zzzt = zznc();
                parcel2.writeNoException();
                zzel.zzb(parcel2, zzzt);
                return true;
            case 3:
                zzzt = zznd();
                parcel2.writeNoException();
                zzel.zzb(parcel2, zzzt);
                return true;
            case 4:
                byte[] createByteArray = parcel.createByteArray();
                String readString2 = parcel.readString();
                Bundle bundle2 = (Bundle) zzel.zza(parcel, Bundle.CREATOR);
                IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                    zzzg = queryLocalInterface2 instanceof zzzf ? (zzzf) queryLocalInterface2 : new zzzg(readStrongBinder2);
                }
                zza(createByteArray, readString2, bundle2, asInterface2, zzzg, zzxu.zzs(parcel.readStrongBinder()), (zzjn) zzel.zza(parcel, zzjn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 5:
                zzlo videoController = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, videoController);
                return true;
            case 6:
                byte[] createByteArray2 = parcel.createByteArray();
                String readString3 = parcel.readString();
                Bundle bundle3 = (Bundle) zzel.zza(parcel, Bundle.CREATOR);
                IObjectWrapper asInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback");
                    zzzi = queryLocalInterface3 instanceof zzzh ? (zzzh) queryLocalInterface3 : new zzzi(readStrongBinder3);
                }
                zza(createByteArray2, readString3, bundle3, asInterface3, zzzi, zzxu.zzs(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 7:
                showInterstitial();
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
