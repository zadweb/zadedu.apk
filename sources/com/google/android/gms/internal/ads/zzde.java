package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;

final class zzde {
    static zzauf zzso;

    static boolean zzb(zzcz zzcz) throws IllegalAccessException, InvocationTargetException {
        Method zza;
        if (zzso != null) {
            return true;
        }
        String str = (String) zzkb.zzik().zzd(zznk.zzbax);
        if (str == null || str.length() == 0) {
            str = null;
            if (!(zzcz == null || (zza = zzcz.zza("4o7tecxtkw7XaNt5hPj+0H1LvOi0SgxCIJTY9VcbazM/HSl/sFlxBFwnc8glnvoB", "RgSY6YxU2k1vLXOV3vapBnQwJDzYDlmX50wbm2tDcnw=")) == null)) {
                str = (String) zza.invoke(null, new Object[0]);
            }
            if (str == null) {
                return false;
            }
        }
        try {
            zzauh zzh = zzaul.zzh(zzbi.zza(str, true));
            for (zzaxp zzaxp : zzavc.zzdht.zzaal()) {
                if (zzaxp.zzyw().isEmpty()) {
                    throw new GeneralSecurityException("Missing type_url.");
                } else if (zzaxp.zzze().isEmpty()) {
                    throw new GeneralSecurityException("Missing primitive_name.");
                } else if (!zzaxp.zzzh().isEmpty()) {
                    zzauo.zza(zzaxp.zzyw(), zzauo.zzdy(zzaxp.zzzh()).zzb(zzaxp.zzyw(), zzaxp.zzze(), zzaxp.zzzf()), zzaxp.zzzg());
                } else {
                    throw new GeneralSecurityException("Missing catalogue_name.");
                }
            }
            zzauf zza2 = zzavf.zza(zzh);
            zzso = zza2;
            return zza2 != null;
        } catch (IllegalArgumentException unused) {
        }
    }
}
