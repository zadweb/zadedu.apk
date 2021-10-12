package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzya extends zzek implements zzxz {
    public zzya() {
        super("com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzek
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String str;
        IInterface iInterface;
        boolean z;
        switch (i) {
            case 2:
                str = getHeadline();
                parcel2.writeNoException();
                parcel2.writeString(str);
                return true;
            case 3:
                List images = getImages();
                parcel2.writeNoException();
                parcel2.writeList(images);
                return true;
            case 4:
                str = getBody();
                parcel2.writeNoException();
                parcel2.writeString(str);
                return true;
            case 5:
                iInterface = zzjz();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 6:
                str = getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(str);
                return true;
            case 7:
                double starRating = getStarRating();
                parcel2.writeNoException();
                parcel2.writeDouble(starRating);
                return true;
            case 8:
                str = getStore();
                parcel2.writeNoException();
                parcel2.writeString(str);
                return true;
            case 9:
                str = getPrice();
                parcel2.writeNoException();
                parcel2.writeString(str);
                return true;
            case 10:
                recordImpression();
                parcel2.writeNoException();
                return true;
            case 11:
                zzj(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 12:
                zzk(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 13:
                z = getOverrideImpressionRecording();
                parcel2.writeNoException();
                zzel.zza(parcel2, z);
                return true;
            case 14:
                z = getOverrideClickHandling();
                parcel2.writeNoException();
                zzel.zza(parcel2, z);
                return true;
            case 15:
                Bundle extras = getExtras();
                parcel2.writeNoException();
                zzel.zzb(parcel2, extras);
                return true;
            case 16:
                zzl(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 17:
                iInterface = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 18:
                iInterface = zzmv();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 19:
                iInterface = zzkf();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 20:
                iInterface = zzmw();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 21:
                iInterface = zzke();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 22:
                zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
