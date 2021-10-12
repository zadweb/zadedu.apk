package com.yandex.metrica;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMetricaService extends IInterface {
    void reportData(Bundle bundle) throws RemoteException;

    void reportEvent(String str, int i, String str2, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IMetricaService {
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.yandex.metrica.IMetricaService");
        }

        public static IMetricaService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.yandex.metrica.IMetricaService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMetricaService)) {
                return new Proxy(iBinder);
            }
            return (IMetricaService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Bundle bundle = null;
            if (i == 1) {
                parcel.enforceInterface("com.yandex.metrica.IMetricaService");
                String readString = parcel.readString();
                int readInt = parcel.readInt();
                String readString2 = parcel.readString();
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                reportEvent(readString, readInt, readString2, bundle);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.yandex.metrica.IMetricaService");
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                reportData(bundle);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.yandex.metrica.IMetricaService");
                return true;
            }
        }

        private static class Proxy implements IMetricaService {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f919a;

            Proxy(IBinder iBinder) {
                this.f919a = iBinder;
            }

            public IBinder asBinder() {
                return this.f919a;
            }

            @Override // com.yandex.metrica.IMetricaService
            public void reportEvent(String str, int i, String str2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.yandex.metrica.IMetricaService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f919a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.yandex.metrica.IMetricaService
            public void reportData(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.yandex.metrica.IMetricaService");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f919a.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
