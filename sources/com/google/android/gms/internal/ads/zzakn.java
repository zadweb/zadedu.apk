package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;

/* access modifiers changed from: package-private */
public final class zzakn implements zzamx {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzcrv;

    zzakn(zzakk zzakk, Context context, String str) {
        this.val$context = context;
        this.zzcrv = str;
    }

    @Override // com.google.android.gms.internal.ads.zzamx
    public final void zzcz(String str) {
        zzbv.zzek();
        zzakk.zzd(this.val$context, this.zzcrv, str);
    }
}
