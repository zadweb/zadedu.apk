package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzbv;

/* access modifiers changed from: package-private */
public final class zztz {
    zzal zzbor;
    zzjj zzbos;
    zzst zzbot;
    long zzbou;
    boolean zzbov;
    private final /* synthetic */ zzty zzbow;
    boolean zzwa;

    zztz(zzty zzty, zzss zzss) {
        this.zzbow = zzty;
        this.zzbor = zzss.zzaw(zzty.zzye);
        zzst zzst = new zzst();
        this.zzbot = zzst;
        zzal zzal = this.zzbor;
        zzal.zza(new zzsu(zzst));
        zzal.zza(new zztc(zzst));
        zzal.zza(new zzte(zzst));
        zzal.zza(new zztg(zzst));
        zzal.zza(new zzti(zzst));
    }

    zztz(zzty zzty, zzss zzss, zzjj zzjj) {
        this(zzty, zzss);
        this.zzbos = zzjj;
    }

    /* access modifiers changed from: package-private */
    public final boolean load() {
        if (this.zzwa) {
            return false;
        }
        zzjj zzjj = this.zzbos;
        if (zzjj == null) {
            zzjj = this.zzbow.zzboo;
        }
        this.zzbov = this.zzbor.zzb(zztw.zzi(zzjj));
        this.zzwa = true;
        this.zzbou = zzbv.zzer().currentTimeMillis();
        return true;
    }
}
