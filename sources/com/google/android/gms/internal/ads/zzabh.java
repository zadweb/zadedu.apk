package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.SystemClock;

@zzadh
public abstract class zzabh extends zzajx {
    protected final Context mContext;
    protected final Object mLock = new Object();
    protected final zzabm zzbzd;
    protected final zzaji zzbze;
    protected zzaej zzbzf;
    protected final Object zzbzh = new Object();

    protected zzabh(Context context, zzaji zzaji, zzabm zzabm) {
        super(true);
        this.mContext = context;
        this.zzbze = zzaji;
        this.zzbzf = zzaji.zzcos;
        this.zzbzd = zzabm;
    }

    @Override // com.google.android.gms.internal.ads.zzajx
    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public abstract zzajh zzaa(int i);

    @Override // com.google.android.gms.internal.ads.zzajx
    public final void zzdn() {
        synchronized (this.mLock) {
            zzakb.zzck("AdRendererBackgroundTask started.");
            int i = this.zzbze.errorCode;
            try {
                zze(SystemClock.elapsedRealtime());
            } catch (zzabk e) {
                int errorCode = e.getErrorCode();
                if (errorCode == 3 || errorCode == -1) {
                    zzakb.zzdj(e.getMessage());
                } else {
                    zzakb.zzdk(e.getMessage());
                }
                this.zzbzf = this.zzbzf == null ? new zzaej(errorCode) : new zzaej(errorCode, this.zzbzf.zzbsu);
                zzakk.zzcrm.post(new zzabi(this));
                i = errorCode;
            }
            zzakk.zzcrm.post(new zzabj(this, zzaa(i)));
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zze(long j) throws zzabk;
}
