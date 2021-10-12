package com.google.android.gms.internal.ads;

import android.util.JsonWriter;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzanc implements zzand {
    private final String zzcva;

    zzanc(String str) {
        this.zzcva = str;
    }

    @Override // com.google.android.gms.internal.ads.zzand
    public final void zza(JsonWriter jsonWriter) {
        zzamy.zza(this.zzcva, jsonWriter);
    }
}
