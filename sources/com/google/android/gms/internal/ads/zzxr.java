package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzxr extends zzek implements zzxq {
    public zzxr() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzek
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IInterface iInterface;
        boolean z;
        Bundle bundle;
        zzxt zzxt = null;
        switch (i) {
            case 1:
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzjn zzjn = (zzjn) zzel.zza(parcel, zzjn.CREATOR);
                zzjj zzjj = (zzjj) zzel.zza(parcel, zzjj.CREATOR);
                String readString = parcel.readString();
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzxt = queryLocalInterface instanceof zzxt ? (zzxt) queryLocalInterface : new zzxv(readStrongBinder);
                }
                zza(asInterface, zzjn, zzjj, readString, zzxt);
                parcel2.writeNoException();
                return true;
            case 2:
                iInterface = getView();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 3:
                IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzjj zzjj2 = (zzjj) zzel.zza(parcel, zzjj.CREATOR);
                String readString2 = parcel.readString();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzxt = queryLocalInterface2 instanceof zzxt ? (zzxt) queryLocalInterface2 : new zzxv(readStrongBinder2);
                }
                zza(asInterface2, zzjj2, readString2, zzxt);
                parcel2.writeNoException();
                return true;
            case 4:
                showInterstitial();
                parcel2.writeNoException();
                return true;
            case 5:
                destroy();
                parcel2.writeNoException();
                return true;
            case 6:
                IObjectWrapper asInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzjn zzjn2 = (zzjn) zzel.zza(parcel, zzjn.CREATOR);
                zzjj zzjj3 = (zzjj) zzel.zza(parcel, zzjj.CREATOR);
                String readString3 = parcel.readString();
                String readString4 = parcel.readString();
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzxt = queryLocalInterface3 instanceof zzxt ? (zzxt) queryLocalInterface3 : new zzxv(readStrongBinder3);
                }
                zza(asInterface3, zzjn2, zzjj3, readString3, readString4, zzxt);
                parcel2.writeNoException();
                return true;
            case 7:
                IObjectWrapper asInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzjj zzjj4 = (zzjj) zzel.zza(parcel, zzjj.CREATOR);
                String readString5 = parcel.readString();
                String readString6 = parcel.readString();
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 != null) {
                    IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzxt = queryLocalInterface4 instanceof zzxt ? (zzxt) queryLocalInterface4 : new zzxv(readStrongBinder4);
                }
                zza(asInterface4, zzjj4, readString5, readString6, zzxt);
                parcel2.writeNoException();
                return true;
            case 8:
                pause();
                parcel2.writeNoException();
                return true;
            case 9:
                resume();
                parcel2.writeNoException();
                return true;
            case 10:
                zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzjj) zzel.zza(parcel, zzjj.CREATOR), parcel.readString(), zzaid.zzaa(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 11:
                zzc((zzjj) zzel.zza(parcel, zzjj.CREATOR), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 12:
                showVideo();
                parcel2.writeNoException();
                return true;
            case 13:
                z = isInitialized();
                parcel2.writeNoException();
                zzel.zza(parcel2, z);
                return true;
            case 14:
                IObjectWrapper asInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzjj zzjj5 = (zzjj) zzel.zza(parcel, zzjj.CREATOR);
                String readString7 = parcel.readString();
                String readString8 = parcel.readString();
                IBinder readStrongBinder5 = parcel.readStrongBinder();
                if (readStrongBinder5 != null) {
                    IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzxt = queryLocalInterface5 instanceof zzxt ? (zzxt) queryLocalInterface5 : new zzxv(readStrongBinder5);
                }
                zza(asInterface5, zzjj5, readString7, readString8, zzxt, (zzpl) zzel.zza(parcel, zzpl.CREATOR), parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            case 15:
                iInterface = zzmo();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 16:
                iInterface = zzmp();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 17:
                bundle = zzmq();
                parcel2.writeNoException();
                zzel.zzb(parcel2, bundle);
                return true;
            case 18:
                bundle = getInterstitialAdapterInfo();
                parcel2.writeNoException();
                zzel.zzb(parcel2, bundle);
                return true;
            case 19:
                bundle = zzmr();
                parcel2.writeNoException();
                zzel.zzb(parcel2, bundle);
                return true;
            case 20:
                zza((zzjj) zzel.zza(parcel, zzjj.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 21:
                zzi(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 22:
                z = zzms();
                parcel2.writeNoException();
                zzel.zza(parcel2, z);
                return true;
            case 23:
                zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzaid.zzaa(parcel.readStrongBinder()), parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            case 24:
                iInterface = zzmt();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 25:
                setImmersiveMode(zzel.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 26:
                iInterface = getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            case 27:
                iInterface = zzmu();
                parcel2.writeNoException();
                zzel.zza(parcel2, iInterface);
                return true;
            default:
                return false;
        }
    }
}
