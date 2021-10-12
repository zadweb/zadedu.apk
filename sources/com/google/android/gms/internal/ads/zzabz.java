package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzabz implements zzv<Object> {
    private final /* synthetic */ zzabv zzcal;
    private final /* synthetic */ zzos zzcam;

    zzabz(zzabv zzabv, zzos zzos) {
        this.zzcal = zzabv;
        this.zzcam = zzos;
    }

    @Override // com.google.android.gms.ads.internal.gmsg.zzv
    public final void zza(Object obj, Map<String, String> map) {
        this.zzcal.zzc((zzabv) this.zzcam, (zzqs) map.get("asset"));
    }
}
