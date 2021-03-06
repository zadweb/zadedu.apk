package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzard implements zzanj {
    private final zzci zzbqd;
    private final Context zzdck;
    private final zzang zzdcl;
    private final zzw zzdcm;
    private final String zzdcn;

    zzard(Context context, zzci zzci, zzang zzang, zzw zzw, String str) {
        this.zzdck = context;
        this.zzbqd = zzci;
        this.zzdcl = zzang;
        this.zzdcm = zzw;
        this.zzdcn = str;
    }

    @Override // com.google.android.gms.internal.ads.zzanj
    public final zzanz zzc(Object obj) {
        Context context = this.zzdck;
        zzci zzci = this.zzbqd;
        zzang zzang = this.zzdcl;
        zzw zzw = this.zzdcm;
        String str = this.zzdcn;
        zzbv.zzel();
        zzaqw zza = zzarc.zza(context, zzasi.zzvq(), "", false, false, zzci, zzang, null, null, zzw, zzhs.zzhm());
        zzaoi zzj = zzaoi.zzj(zza);
        zza.zzuf().zza(new zzarf(zzj));
        zza.loadUrl(str);
        return zzj;
    }
}
