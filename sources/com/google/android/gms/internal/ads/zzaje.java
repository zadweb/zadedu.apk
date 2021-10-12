package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

@zzadh
public final class zzaje implements zzajg {
    @Override // com.google.android.gms.internal.ads.zzajg
    public final zzanz<String> zza(String str, PackageInfo packageInfo) {
        return zzano.zzi(str);
    }

    @Override // com.google.android.gms.internal.ads.zzajg
    public final zzanz<AdvertisingIdClient.Info> zzae(Context context) {
        zzaoj zzaoj = new zzaoj();
        zzkb.zzif();
        if (zzamu.zzbh(context)) {
            zzaki.zzb(new zzajf(this, context, zzaoj));
        }
        return zzaoj;
    }
}
