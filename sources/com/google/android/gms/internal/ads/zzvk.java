package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzvk implements zzv<zzwb> {
    private final /* synthetic */ zzvw zzbqi;
    private final /* synthetic */ zzuu zzbqj;
    private final /* synthetic */ zzvf zzbqk;

    zzvk(zzvf zzvf, zzvw zzvw, zzuu zzuu) {
        this.zzbqk = zzvf;
        this.zzbqi = zzvw;
        this.zzbqj = zzuu;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.util.Map] */
    @Override // com.google.android.gms.ads.internal.gmsg.zzv
    public final /* synthetic */ void zza(zzwb zzwb, Map map) {
        synchronized (this.zzbqk.mLock) {
            if (this.zzbqi.getStatus() != -1) {
                if (this.zzbqi.getStatus() != 1) {
                    this.zzbqk.zzbqb = 0;
                    this.zzbqk.zzbpy.zze(this.zzbqj);
                    this.zzbqi.zzk(this.zzbqj);
                    this.zzbqk.zzbqa = this.zzbqi;
                    zzakb.v("Successfully loaded JS Engine.");
                }
            }
        }
    }
}
