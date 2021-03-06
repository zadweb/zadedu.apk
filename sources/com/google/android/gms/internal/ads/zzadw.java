package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.DeviceProperties;

/* access modifiers changed from: package-private */
public final class zzadw implements zzady {
    private final /* synthetic */ Context val$context;

    zzadw(Context context) {
        this.val$context = context;
    }

    @Override // com.google.android.gms.internal.ads.zzady
    public final boolean zza(zzang zzang) {
        zzkb.zzif();
        boolean zzbe = zzamu.zzbe(this.val$context);
        boolean z = ((Boolean) zzkb.zzik().zzd(zznk.zzbeq)).booleanValue() && zzang.zzcvg;
        if (zzadv.zzd(this.val$context, zzang.zzcvg) && zzbe && !z) {
            if (DeviceProperties.isSidewinder(this.val$context)) {
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzawa)).booleanValue()) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
