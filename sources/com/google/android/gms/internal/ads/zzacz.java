package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzacz implements zzanl<zzaqw> {
    private final /* synthetic */ String val$message;
    private final /* synthetic */ JSONObject zzcbv;

    zzacz(zzacq zzacq, String str, JSONObject jSONObject) {
        this.val$message = str;
        this.zzcbv = jSONObject;
    }

    @Override // com.google.android.gms.internal.ads.zzanl
    public final void zzb(Throwable th) {
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.internal.ads.zzanl
    public final /* synthetic */ void zzh(zzaqw zzaqw) {
        zzaqw.zza(this.val$message, this.zzcbv);
    }
}
