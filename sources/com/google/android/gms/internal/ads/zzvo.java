package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final class zzvo implements zzaoo<zzuu> {
    private final /* synthetic */ zzvf zzbqk;
    private final /* synthetic */ zzvw zzbqn;

    zzvo(zzvf zzvf, zzvw zzvw) {
        this.zzbqk = zzvf;
        this.zzbqn = zzvw;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.internal.ads.zzaoo
    public final /* synthetic */ void zze(zzuu zzuu) {
        synchronized (this.zzbqk.mLock) {
            this.zzbqk.zzbqb = 0;
            if (!(this.zzbqk.zzbqa == null || this.zzbqn == this.zzbqk.zzbqa)) {
                zzakb.v("New JS engine is loaded, marking previous one as destroyable.");
                this.zzbqk.zzbqa.zzmb();
            }
            this.zzbqk.zzbqa = this.zzbqn;
        }
    }
}
