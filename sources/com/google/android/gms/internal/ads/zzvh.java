package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzvh implements zzuv {
    private final zzvf zzbqc;
    private final zzvw zzbqf;
    private final zzuu zzbqg;

    zzvh(zzvf zzvf, zzvw zzvw, zzuu zzuu) {
        this.zzbqc = zzvf;
        this.zzbqf = zzvw;
        this.zzbqg = zzuu;
    }

    @Override // com.google.android.gms.internal.ads.zzuv
    public final void zzlx() {
        zzakk.zzcrm.postDelayed(new zzvi(this.zzbqc, this.zzbqf, this.zzbqg), (long) zzvq.zzbqp);
    }
}
