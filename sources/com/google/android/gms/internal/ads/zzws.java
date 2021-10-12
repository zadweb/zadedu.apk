package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final class zzws implements zzaom {
    private final /* synthetic */ zzvs zzbrl;
    private final /* synthetic */ zzaoj zzbrn;

    zzws(zzwq zzwq, zzaoj zzaoj, zzvs zzvs) {
        this.zzbrn = zzaoj;
        this.zzbrl = zzvs;
    }

    @Override // com.google.android.gms.internal.ads.zzaom
    public final void run() {
        this.zzbrn.setException(new zzwe("Unable to obtain a JavascriptEngine."));
        this.zzbrl.release();
    }
}
