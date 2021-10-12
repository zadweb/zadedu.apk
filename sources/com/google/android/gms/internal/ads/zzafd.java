package com.google.android.gms.internal.ads;

final class zzafd implements zzaoo<zzwb> {
    private final /* synthetic */ zzafc zzcgm;

    zzafd(zzafc zzafc) {
        this.zzcgm = zzafc;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.internal.ads.zzaoo
    public final /* synthetic */ void zze(zzwb zzwb) {
        try {
            zzwb.zzb("AFMA_getAdapterLessMediationAd", this.zzcgm.zzcgk);
        } catch (Exception e) {
            zzakb.zzb("Error requesting an ad url", e);
            zzafa.zzcgg.zzat(this.zzcgm.zzcgl);
        }
    }
}
