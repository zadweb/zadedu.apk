package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzlp extends zzek implements zzlo {
    public zzlp() {
        super("com.google.android.gms.ads.internal.client.IVideoController");
    }

    public static zzlo zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
        return queryLocalInterface instanceof zzlo ? (zzlo) queryLocalInterface : new zzlq(iBinder);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzek
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        boolean z;
        float f;
        zzlr zzlr;
        switch (i) {
            case 1:
                play();
                parcel2.writeNoException();
                return true;
            case 2:
                pause();
                parcel2.writeNoException();
                return true;
            case 3:
                mute(zzel.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 4:
                z = isMuted();
                parcel2.writeNoException();
                zzel.zza(parcel2, z);
                return true;
            case 5:
                int playbackState = getPlaybackState();
                parcel2.writeNoException();
                parcel2.writeInt(playbackState);
                return true;
            case 6:
                f = zzim();
                parcel2.writeNoException();
                parcel2.writeFloat(f);
                return true;
            case 7:
                f = zzin();
                parcel2.writeNoException();
                parcel2.writeFloat(f);
                return true;
            case 8:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzlr = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
                    zzlr = queryLocalInterface instanceof zzlr ? (zzlr) queryLocalInterface : new zzlt(readStrongBinder);
                }
                zza(zzlr);
                parcel2.writeNoException();
                return true;
            case 9:
                f = getAspectRatio();
                parcel2.writeNoException();
                parcel2.writeFloat(f);
                return true;
            case 10:
                z = isCustomControlsEnabled();
                parcel2.writeNoException();
                zzel.zza(parcel2, z);
                return true;
            case 11:
                zzlr zzio = zzio();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzio);
                return true;
            case 12:
                z = isClickToExpandEnabled();
                parcel2.writeNoException();
                zzel.zza(parcel2, z);
                return true;
            default:
                return false;
        }
    }
}
