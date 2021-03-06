package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzayf;
import java.security.GeneralSecurityException;

public final class zzavc {
    public static final zzayf zzdht = ((zzayf) ((zzayf.zza) zzayf.zzaam().zza((zzbbo) zzaur.zzdht)).zzb(zzaub.zza("TinkHybridDecrypt", "HybridDecrypt", "EciesAeadHkdfPrivateKey", 0, true)).zzb(zzaub.zza("TinkHybridEncrypt", "HybridEncrypt", "EciesAeadHkdfPublicKey", 0, true)).zzej("TINK_HYBRID_1_0_0").zzadi());
    private static final zzayf zzdhu = ((zzayf) ((zzayf.zza) zzayf.zzaam().zza((zzbbo) zzdht)).zzej("TINK_HYBRID_1_1_0").zzadi());

    static {
        try {
            zzauo.zza("TinkHybridEncrypt", new zzave());
            zzauo.zza("TinkHybridDecrypt", new zzavd());
            zzaur.zzwk();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
