package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzyd extends zzek implements zzyc {
    public zzyd() {
        super("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
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
                iInterface = zzkg();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 6:
                str = getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(str);
                return true;
            case 7:
                str = getAdvertiser();
                parcel2.writeNoException();
                parcel2.writeString(str);
                return true;
            case 8:
                recordImpression();
                parcel2.writeNoException();
                return true;
            case 9:
                zzj(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 10:
                zzk(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 11:
                z = getOverrideImpressionRecording();
                parcel2.writeNoException();
                zzel.zza(parcel2, z);
                return true;
            case 12:
                z = getOverrideClickHandling();
                parcel2.writeNoException();
                zzel.zza(parcel2, z);
                return true;
            case 13:
                Bundle extras = getExtras();
                parcel2.writeNoException();
                zzel.zzb(parcel2, extras);
                return true;
            case 14:
                zzl(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 15:
                iInterface = zzmv();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 16:
                iInterface = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 17:
            case 18:
            default:
                return false;
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
        }
    }
}
