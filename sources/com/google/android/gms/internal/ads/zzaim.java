package com.google.android.gms.internal.ads;

final class zzaim implements zzanl<Void> {
    private final /* synthetic */ zzanz zzcnb;

    zzaim(zzaii zzaii, zzanz zzanz) {
        this.zzcnb = zzanz;
    }

    @Override // com.google.android.gms.internal.ads.zzanl
    public final void zzb(Throwable th) {
        zzaii.zzpl().remove(this.zzcnb);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.internal.ads.zzanl
    public final /* synthetic */ void zzh(Void r2) {
        zzaii.zzpl().remove(this.zzcnb);
    }
}
