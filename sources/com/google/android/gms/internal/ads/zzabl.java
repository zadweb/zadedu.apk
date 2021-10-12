package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public final class zzabl {
    public static zzalc zza(Context context, zza zza, zzaji zzaji, zzci zzci, zzaqw zzaqw, zzxn zzxn, zzabm zzabm, zznx zznx) {
        zzalc zzalc;
        zzaej zzaej = zzaji.zzcos;
        if (zzaej.zzceq) {
            zzalc = new zzabr(context, zzaji, zzxn, zzabm, zznx, zzaqw);
        } else if (zzaej.zzare || (zza instanceof zzbc)) {
            zzalc = (!zzaej.zzare || !(zza instanceof zzbc)) ? new zzabo(zzaji, zzabm) : new zzabt(context, (zzbc) zza, zzaji, zzci, zzabm, zznx);
        } else {
            zzalc = (!((Boolean) zzkb.zzik().zzd(zznk.zzaxd)).booleanValue() || !PlatformVersion.isAtLeastKitKat() || PlatformVersion.isAtLeastLollipop() || zzaqw == null || !zzaqw.zzud().zzvs()) ? new zzabn(context, zzaji, zzaqw, zzabm) : new zzabq(context, zzaji, zzaqw, zzabm);
        }
        String valueOf = String.valueOf(zzalc.getClass().getName());
        zzakb.zzck(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
        zzalc.zznt();
        return zzalc;
    }
}
