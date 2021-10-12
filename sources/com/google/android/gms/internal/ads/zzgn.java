package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;

/* access modifiers changed from: package-private */
public final class zzgn implements ValueCallback<String> {
    private final /* synthetic */ zzgm zzaip;

    zzgn(zzgm zzgm) {
        this.zzaip = zzgm;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // android.webkit.ValueCallback
    public final /* synthetic */ void onReceiveValue(String str) {
        this.zzaip.zzaik.zza(this.zzaip.zzaim, this.zzaip.zzain, str, this.zzaip.zzaio);
    }
}
