package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzpj implements zzv<Object> {
    private final /* synthetic */ zzacm zzbji;
    private final /* synthetic */ zzpf zzbjj;

    zzpj(zzpf zzpf, zzacm zzacm) {
        this.zzbjj = zzpf;
        this.zzbji = zzacm;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzv
    public final void zza(Object obj, Map<String, String> map) {
        zzaqw zzaqw = (zzaqw) this.zzbjj.zzbjg.get();
        if (zzaqw == null) {
            this.zzbji.zzb("/hideOverlay", this);
        } else {
            zzaqw.getView().setVisibility(8);
        }
    }
}
