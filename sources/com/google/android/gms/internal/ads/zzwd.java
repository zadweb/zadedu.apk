package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

@zzadh
public final class zzwd implements zzuo, zzwc {
    private final zzwb zzbqz;
    private final HashSet<AbstractMap.SimpleEntry<String, zzv<? super zzwb>>> zzbra = new HashSet<>();

    public zzwd(zzwb zzwb) {
        this.zzbqz = zzwb;
    }

    @Override // com.google.android.gms.internal.ads.zzwb
    public final void zza(String str, zzv<? super zzwb> zzv) {
        this.zzbqz.zza(str, zzv);
        this.zzbra.add(new AbstractMap.SimpleEntry<>(str, zzv));
    }

    @Override // com.google.android.gms.internal.ads.zzue
    public final void zza(String str, Map map) {
        zzup.zza(this, str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzue, com.google.android.gms.internal.ads.zzuo
    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzwb
    public final void zzb(String str, zzv<? super zzwb> zzv) {
        this.zzbqz.zzb(str, zzv);
        this.zzbra.remove(new AbstractMap.SimpleEntry(str, zzv));
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzve, com.google.android.gms.internal.ads.zzuo
    public final void zzbe(String str) {
        this.zzbqz.zzbe(str);
    }

    @Override // com.google.android.gms.internal.ads.zzuo
    public final void zzf(String str, String str2) {
        zzup.zza(this, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzwc
    public final void zzmd() {
        Iterator<AbstractMap.SimpleEntry<String, zzv<? super zzwb>>> it = this.zzbra.iterator();
        while (it.hasNext()) {
            AbstractMap.SimpleEntry<String, zzv<? super zzwb>> next = it.next();
            String valueOf = String.valueOf(next.getValue().toString());
            zzakb.v(valueOf.length() != 0 ? "Unregistering eventhandler: ".concat(valueOf) : new String("Unregistering eventhandler: "));
            this.zzbqz.zzb(next.getKey(), next.getValue());
        }
        this.zzbra.clear();
    }
}
