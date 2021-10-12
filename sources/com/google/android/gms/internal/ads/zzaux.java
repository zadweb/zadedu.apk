package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi;
import java.security.GeneralSecurityException;

final class zzaux implements zzaug<zzatz> {
    zzaux() {
    }

    private static zzatz zzc(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzaxv) {
            zzaxv zzaxv = (zzaxv) zzbcu;
            zzazq.zzj(zzaxv.getVersion(), 0);
            String zzaab = zzaxv.zzzy().zzaab();
            return zzauj.zzdx(zzaab).zzdw(zzaab);
        }
        throw new GeneralSecurityException("expected KmsAeadKey proto");
    }

    private static zzatz zzd(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzc(zzaxv.zzaj(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected KmsAeadKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final int getVersion() {
        return 0;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzaug
    public final /* synthetic */ zzatz zza(zzbah zzbah) throws GeneralSecurityException {
        return zzd(zzbah);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzaug
    public final /* synthetic */ zzatz zza(zzbcu zzbcu) throws GeneralSecurityException {
        return zzc(zzbcu);
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzaxx.zzak(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized KmsAeadKeyFormat proto", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzaxx) {
            return zzaxv.zzzz().zzb((zzaxx) zzbcu).zzbe(0).zzadi();
        }
        throw new GeneralSecurityException("expected KmsAeadKeyFormat proto");
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.KmsAeadKey").zzai(((zzaxv) zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.REMOTE).zzadi();
    }
}
