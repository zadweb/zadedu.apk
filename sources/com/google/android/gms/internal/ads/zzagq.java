package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@zzadh
public final class zzagq extends zzaha {
    private final Context mContext;
    private final Object mLock;
    private final zzagr zzcld;
    private final zzang zzyf;

    public zzagq(Context context, zzw zzw, zzxn zzxn, zzang zzang) {
        this(context, zzang, new zzagr(context, zzw, zzjn.zzhx(), zzxn, zzang));
    }

    private zzagq(Context context, zzang zzang, zzagr zzagr) {
        this.mLock = new Object();
        this.mContext = context;
        this.zzyf = zzang;
        this.zzcld = zzagr;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void destroy() {
        zzf(null);
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final String getMediationAdapterClassName() {
        String mediationAdapterClassName;
        synchronized (this.mLock) {
            mediationAdapterClassName = this.zzcld.getMediationAdapterClassName();
        }
        return mediationAdapterClassName;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final boolean isLoaded() {
        boolean isLoaded;
        synchronized (this.mLock) {
            isLoaded = this.zzcld.isLoaded();
        }
        return isLoaded;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void pause() {
        zzd(null);
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void resume() {
        zze(null);
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void setImmersiveMode(boolean z) {
        synchronized (this.mLock) {
            this.zzcld.setImmersiveMode(z);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void setUserId(String str) {
        synchronized (this.mLock) {
            this.zzcld.setUserId(str);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void show() {
        synchronized (this.mLock) {
            this.zzcld.zzoy();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzagx zzagx) {
        synchronized (this.mLock) {
            this.zzcld.zza(zzagx);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzahe zzahe) {
        synchronized (this.mLock) {
            this.zzcld.zza(zzahe);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzahk zzahk) {
        synchronized (this.mLock) {
            this.zzcld.zza(zzahk);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzkx zzkx) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayf)).booleanValue()) {
            synchronized (this.mLock) {
                this.zzcld.zza(zzkx);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final Bundle zzba() {
        Bundle zzba;
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzayf)).booleanValue()) {
            return new Bundle();
        }
        synchronized (this.mLock) {
            zzba = this.zzcld.zzba();
        }
        return zzba;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzd(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzcld.pause();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze(IObjectWrapper iObjectWrapper) {
        Context context;
        synchronized (this.mLock) {
            if (iObjectWrapper == null) {
                context = null;
            } else {
                try {
                    context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
                } catch (Exception e) {
                    zzakb.zzc("Unable to extract updated context.", e);
                }
            }
            if (context != null) {
                this.zzcld.onContextChanged(context);
            }
            this.zzcld.resume();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzf(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzcld.destroy();
        }
    }
}
