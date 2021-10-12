package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import org.json.JSONObject;

@zzadh
public final class zzfb implements zzfo {
    private final zzet zzafq;
    private final zzaqw zzafr;
    private final zzv<zzaqw> zzafs = new zzfc(this);
    private final zzv<zzaqw> zzaft = new zzfd(this);
    private final zzv<zzaqw> zzafu = new zzfe(this);

    public zzfb(zzet zzet, zzaqw zzaqw) {
        this.zzafq = zzet;
        this.zzafr = zzaqw;
        zzaqw.zza("/updateActiveView", this.zzafs);
        zzaqw.zza("/untrackActiveViewUnit", this.zzaft);
        zzaqw.zza("/visibilityChanged", this.zzafu);
        String valueOf = String.valueOf(this.zzafq.zzaet.zzfy());
        zzakb.zzck(valueOf.length() != 0 ? "Custom JS tracking ad unit: ".concat(valueOf) : new String("Custom JS tracking ad unit: "));
    }

    @Override // com.google.android.gms.internal.ads.zzfo
    public final void zzb(JSONObject jSONObject, boolean z) {
        if (!z) {
            this.zzafr.zzb("AFMA_updateActiveView", jSONObject);
        } else {
            this.zzafq.zzb(this);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfo
    public final boolean zzgk() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzfo
    public final void zzgl() {
        zzaqw zzaqw = this.zzafr;
        zzaqw.zzb("/visibilityChanged", this.zzafu);
        zzaqw.zzb("/untrackActiveViewUnit", this.zzaft);
        zzaqw.zzb("/updateActiveView", this.zzafs);
    }
}
