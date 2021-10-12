package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

public abstract class zzko extends zzek implements zzkn {
    public zzko() {
        super("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzek
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzkh zzkh = null;
        zzlg zzlg = null;
        switch (i) {
            case 1:
                zzkk zzdh = zzdh();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzdh);
                return true;
            case 2:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzkh = queryLocalInterface instanceof zzkh ? (zzkh) queryLocalInterface : new zzkj(readStrongBinder);
                }
                zzb(zzkh);
                break;
            case 3:
                zza(zzqx.zzl(parcel.readStrongBinder()));
                break;
            case 4:
                zza(zzra.zzm(parcel.readStrongBinder()));
                break;
            case 5:
                zza(parcel.readString(), zzrg.zzo(parcel.readStrongBinder()), zzrd.zzn(parcel.readStrongBinder()));
                break;
            case 6:
                zza((zzpl) zzel.zza(parcel, zzpl.CREATOR));
                break;
            case 7:
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzlg = queryLocalInterface2 instanceof zzlg ? (zzlg) queryLocalInterface2 : new zzli(readStrongBinder2);
                }
                zzb(zzlg);
                break;
            case 8:
                zza(zzrj.zzp(parcel.readStrongBinder()), (zzjn) zzel.zza(parcel, zzjn.CREATOR));
                break;
            case 9:
                zza((PublisherAdViewOptions) zzel.zza(parcel, PublisherAdViewOptions.CREATOR));
                break;
            case 10:
                zza(zzrm.zzq(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
