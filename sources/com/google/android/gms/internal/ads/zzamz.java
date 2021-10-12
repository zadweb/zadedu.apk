package com.google.android.gms.internal.ads;

import android.util.JsonWriter;
import java.util.Map;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzamz implements zzand {
    private final Map zzbpq;
    private final String zzcva;
    private final byte[] zzcvb;
    private final String zzzo;

    zzamz(String str, String str2, Map map, byte[] bArr) {
        this.zzcva = str;
        this.zzzo = str2;
        this.zzbpq = map;
        this.zzcvb = bArr;
    }

    @Override // com.google.android.gms.internal.ads.zzand
    public final void zza(JsonWriter jsonWriter) {
        zzamy.zza(this.zzcva, this.zzzo, this.zzbpq, this.zzcvb, jsonWriter);
    }
}
