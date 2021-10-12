package com.google.android.gms.internal.ads;

import java.io.ByteArrayInputStream;

/* access modifiers changed from: package-private */
public final class zzalw implements zzank<zzp, T> {
    private final /* synthetic */ zzalz zzcti;

    zzalw(zzalt zzalt, zzalz zzalz) {
        this.zzcti = zzalz;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.internal.ads.zzank
    public final /* synthetic */ Object apply(zzp zzp) {
        return this.zzcti.zze(new ByteArrayInputStream(zzp.data));
    }
}
