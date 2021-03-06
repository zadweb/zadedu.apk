package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Pair;
import com.mopub.common.GpsHelper;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.l;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class GoogleAdvertisingIdGetter {

    /* renamed from: a  reason: collision with root package name */
    private volatile String f931a = null;
    private volatile Boolean b = null;
    private final Object c = new Object();
    private volatile FutureTask<Pair<String, Boolean>> d;

    /* access modifiers changed from: private */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        static final GoogleAdvertisingIdGetter f935a = new GoogleAdvertisingIdGetter();
    }

    /* access modifiers changed from: private */
    public interface c<T> {
        T b(Future<Pair<String, Boolean>> future) throws InterruptedException, ExecutionException;
    }

    public void a(final Context context) {
        if (this.d == null) {
            synchronized (this.c) {
                if (this.d == null) {
                    this.d = new FutureTask<>(new Callable<Pair<String, Boolean>>() {
                        /* class com.yandex.metrica.impl.GoogleAdvertisingIdGetter.AnonymousClass1 */

                        /* renamed from: a */
                        public Pair<String, Boolean> call() {
                            Context applicationContext = context.getApplicationContext();
                            if (GoogleAdvertisingIdGetter.d(applicationContext)) {
                                GoogleAdvertisingIdGetter.a(b.f935a, applicationContext);
                            }
                            if (!GoogleAdvertisingIdGetter.this.c()) {
                                GoogleAdvertisingIdGetter.b(b.f935a, applicationContext);
                            }
                            return new Pair<>(GoogleAdvertisingIdGetter.this.f931a, GoogleAdvertisingIdGetter.this.b);
                        }
                    });
                    new Thread(this.d).start();
                }
            }
        }
    }

    private void a(String str) {
        g.a().b(new l(str));
        this.f931a = str;
    }

    private <T> T a(Context context, c<T> cVar) {
        a(context);
        try {
            return cVar.b(this.d);
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    public String b(Context context) {
        return (String) a(context, new c<String>() {
            /* class com.yandex.metrica.impl.GoogleAdvertisingIdGetter.AnonymousClass2 */

            /* renamed from: a */
            public String b(Future<Pair<String, Boolean>> future) throws InterruptedException, ExecutionException {
                return (String) future.get().first;
            }
        });
    }

    public synchronized boolean c() {
        return (this.f931a == null || this.b == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public static boolean d(Context context) {
        try {
            return Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", Context.class).invoke(null, context).equals(0);
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public interface GoogleAdvertisingInfo extends IInterface {
        boolean getEnabled(boolean z) throws RemoteException;

        String getId() throws RemoteException;

        public static abstract class GoogleAdvertisingInfoBinder extends Binder implements GoogleAdvertisingInfo {
            public static GoogleAdvertisingInfo Create(IBinder iBinder) {
                if (iBinder == null) {
                    return null;
                }
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof GoogleAdvertisingInfo)) {
                    return new GoogleAdvertisingInfoImplementation(iBinder);
                }
                return (GoogleAdvertisingInfo) queryLocalInterface;
            }

            @Override // android.os.Binder
            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
                if (i == 1) {
                    parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    String id = getId();
                    parcel2.writeNoException();
                    parcel2.writeString(id);
                    return true;
                } else if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    boolean enabled = getEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(enabled ? 1 : 0);
                    return true;
                }
            }

            /* access modifiers changed from: private */
            public static class GoogleAdvertisingInfoImplementation implements GoogleAdvertisingInfo {

                /* renamed from: a  reason: collision with root package name */
                private IBinder f933a;

                GoogleAdvertisingInfoImplementation(IBinder iBinder) {
                    this.f933a = iBinder;
                }

                public IBinder asBinder() {
                    return this.f933a;
                }

                @Override // com.yandex.metrica.impl.GoogleAdvertisingIdGetter.GoogleAdvertisingInfo
                public String getId() throws RemoteException {
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        this.f933a.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        return obtain2.readString();
                    } finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }

                @Override // com.yandex.metrica.impl.GoogleAdvertisingIdGetter.GoogleAdvertisingInfo
                public boolean getEnabled(boolean z) throws RemoteException {
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        boolean z2 = true;
                        obtain.writeInt(z ? 1 : 0);
                        this.f933a.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() == 0) {
                            z2 = false;
                        }
                        return z2;
                    } finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public class a implements ServiceConnection {

        /* renamed from: a  reason: collision with root package name */
        private boolean f934a;
        private final BlockingQueue<IBinder> b;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        private a() {
            this.f934a = false;
            this.b = new LinkedBlockingQueue();
        }

        /* synthetic */ a(byte b2) {
            this();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public IBinder a() throws InterruptedException {
            if (!this.f934a) {
                this.f934a = true;
                return this.b.take();
            }
            throw new IllegalStateException();
        }
    }

    static /* synthetic */ void a(GoogleAdvertisingIdGetter googleAdvertisingIdGetter, Context context) {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", Context.class).invoke(null, context);
            Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            String str = (String) cls.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
            Boolean bool = (Boolean) cls.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0]);
            synchronized (googleAdvertisingIdGetter) {
                googleAdvertisingIdGetter.a(str);
                googleAdvertisingIdGetter.b = bool;
            }
        } catch (Exception unused) {
        }
    }

    static /* synthetic */ void b(GoogleAdvertisingIdGetter googleAdvertisingIdGetter, Context context) {
        a aVar = new a((byte) 0);
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, aVar, 1)) {
            try {
                GoogleAdvertisingInfo Create = GoogleAdvertisingInfo.GoogleAdvertisingInfoBinder.Create(aVar.a());
                String id = Create.getId();
                Boolean valueOf = Boolean.valueOf(Create.getEnabled(true));
                synchronized (googleAdvertisingIdGetter) {
                    googleAdvertisingIdGetter.a(id);
                    googleAdvertisingIdGetter.b = valueOf;
                }
            } catch (Exception unused) {
            } finally {
                context.unbindService(aVar);
            }
        }
    }
}
