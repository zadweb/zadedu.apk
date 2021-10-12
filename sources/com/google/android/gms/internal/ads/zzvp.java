package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final class zzvp implements zzaom {
    private final /* synthetic */ zzvf zzbqk;
    private final /* synthetic */ zzvw zzbqn;

    zzvp(zzvf zzvf, zzvw zzvw) {
        this.zzbqk = zzvf;
        this.zzbqn = zzvw;
    }

    @Override // com.google.android.gms.internal.ads.zzaom
    public final void run() {
        synchronized (this.zzbqk.mLock) {
            this.zzbqk.zzbqb = 1;
            zzakb.v("Failed loading new engine. Marking new engine destroyable.");
            this.zzbqn.zzmb();
        }
    }
}
