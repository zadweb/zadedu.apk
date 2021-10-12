package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

/* access modifiers changed from: package-private */
public final class zzalv implements zzanj<Throwable, T> {
    private final /* synthetic */ zzalz zzcti;

    zzalv(zzalt zzalt, zzalz zzalz) {
        this.zzcti = zzalz;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.internal.ads.zzanj
    public final /* synthetic */ zzanz zzc(Throwable th) throws Exception {
        Throwable th2 = th;
        zzakb.zzb("Error occurred while dispatching http response in getter.", th2);
        zzbv.zzeo().zza(th2, "HttpGetter.deliverResponse.1");
        return zzano.zzi(this.zzcti.zzny());
    }
}
