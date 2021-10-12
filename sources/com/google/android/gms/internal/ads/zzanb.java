package com.google.android.gms.internal.ads;

import android.util.JsonWriter;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzanb implements zzand {
    private final byte[] zzcvd;

    zzanb(byte[] bArr) {
        this.zzcvd = bArr;
    }

    @Override // com.google.android.gms.internal.ads.zzand
    public final void zza(JsonWriter jsonWriter) {
        zzamy.zza(this.zzcvd, jsonWriter);
    }
}
