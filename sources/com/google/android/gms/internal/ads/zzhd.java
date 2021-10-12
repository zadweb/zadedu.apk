package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzhd {
    private Context mContext;
    private final Object mLock = new Object();
    private final Runnable zzajq = new zzhe(this);
    private zzhk zzajr;
    private zzho zzajs;

    /* access modifiers changed from: private */
    public final void connect() {
        synchronized (this.mLock) {
            if (this.mContext != null) {
                if (this.zzajr == null) {
                    zzhk zzhk = new zzhk(this.mContext, zzbv.zzez().zzsa(), new zzhg(this), new zzhh(this));
                    this.zzajr = zzhk;
                    zzhk.checkAvailabilityAndConnect();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzajr != null) {
                if (this.zzajr.isConnected() || this.zzajr.isConnecting()) {
                    this.zzajr.disconnect();
                }
                this.zzajr = null;
                this.zzajs = null;
                Binder.flushPendingCommands();
            }
        }
    }

    public final void initialize(Context context) {
        if (context != null) {
            synchronized (this.mLock) {
                if (this.mContext == null) {
                    this.mContext = context.getApplicationContext();
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzbdo)).booleanValue()) {
                        connect();
                    } else {
                        if (((Boolean) zzkb.zzik().zzd(zznk.zzbdn)).booleanValue()) {
                            zzbv.zzen().zza(new zzhf(this));
                        }
                    }
                }
            }
        }
    }

    public final zzhi zza(zzhl zzhl) {
        synchronized (this.mLock) {
            if (this.zzajs == null) {
                return new zzhi();
            }
            try {
                return this.zzajs.zza(zzhl);
            } catch (RemoteException e) {
                zzakb.zzb("Unable to call into cache service.", e);
                return new zzhi();
            }
        }
    }

    public final void zzhh() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbdp)).booleanValue()) {
            synchronized (this.mLock) {
                connect();
                zzbv.zzek();
                zzakk.zzcrm.removeCallbacks(this.zzajq);
                zzbv.zzek();
                zzakk.zzcrm.postDelayed(this.zzajq, ((Long) zzkb.zzik().zzd(zznk.zzbdq)).longValue());
            }
        }
    }
}
