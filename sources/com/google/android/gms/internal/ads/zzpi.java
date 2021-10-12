package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzpi implements zzv<Object> {
    private final /* synthetic */ zzacm zzbji;
    private final /* synthetic */ zzpf zzbjj;

    zzpi(zzpf zzpf, zzacm zzacm) {
        this.zzbjj = zzpf;
        this.zzbji = zzacm;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzv
    public final void zza(Object obj, Map<String, String> map) {
        zzaqw zzaqw = (zzaqw) this.zzbjj.zzbjg.get();
        if (zzaqw == null) {
            this.zzbji.zzb("/showOverlay", this);
        } else {
            zzaqw.getView().setVisibility(0);
        }
    }
}
