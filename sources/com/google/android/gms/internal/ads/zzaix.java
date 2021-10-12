package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzaix implements zzft {
    private final Object mLock;
    private final Context zzatx;
    private boolean zzcno;
    private String zzye;

    public zzaix(Context context, String str) {
        this.zzatx = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzye = str;
        this.zzcno = false;
        this.mLock = new Object();
    }

    public final void setAdUnitId(String str) {
        this.zzye = str;
    }

    @Override // com.google.android.gms.internal.ads.zzft
    public final void zza(zzfs zzfs) {
        zzx(zzfs.zztg);
    }

    public final void zzx(boolean z) {
        if (zzbv.zzfh().zzs(this.zzatx)) {
            synchronized (this.mLock) {
                if (this.zzcno != z) {
                    this.zzcno = z;
                    if (!TextUtils.isEmpty(this.zzye)) {
                        if (this.zzcno) {
                            zzbv.zzfh().zzb(this.zzatx, this.zzye);
                        } else {
                            zzbv.zzfh().zzc(this.zzatx, this.zzye);
                        }
                    }
                }
            }
        }
    }
}
