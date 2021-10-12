package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;

final class zzava implements zzaug<zzaue> {
    zzava() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzf */
    public final zzaue zza(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzaws zzx = zzaws.zzx(zzbah);
            if (zzx instanceof zzaws) {
                zzaws zzaws = zzx;
                zzazq.zzj(zzaws.getVersion(), 0);
                zzavh.zza(zzaws.zzxz().zzxs());
                zzawq zzxs = zzaws.zzxz().zzxs();
                zzaww zzxu = zzxs.zzxu();
                zzayv zza = zzavh.zza(zzxu.zzyh());
                byte[] byteArray = zzaws.zzwv().toByteArray();
                return new zzayo((ECPrivateKey) zzayy.zzdof.zzek("EC").generatePrivate(new ECPrivateKeySpec(new BigInteger(1, byteArray), zzayt.zza(zza))), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPrivateKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final int getVersion() {
        return 0;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzaug
    public final /* synthetic */ zzaue zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzaws) {
            zzaws zzaws = (zzaws) zzbcu;
            zzazq.zzj(zzaws.getVersion(), 0);
            zzavh.zza(zzaws.zzxz().zzxs());
            zzawq zzxs = zzaws.zzxz().zzxs();
            zzaww zzxu = zzxs.zzxu();
            zzayv zza = zzavh.zza(zzxu.zzyh());
            byte[] byteArray = zzaws.zzwv().toByteArray();
            return new zzayo((ECPrivateKey) zzayy.zzdof.zzek("EC").generatePrivate(new ECPrivateKeySpec(new BigInteger(1, byteArray), zzayt.zza(zza))), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzawo.zzw(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("invalid EciesAeadHkdf key format", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzawo) {
            zzawo zzawo = (zzawo) zzbcu;
            zzavh.zza(zzawo.zzxs());
            KeyPair zza = zzayt.zza(zzayt.zza(zzavh.zza(zzawo.zzxs().zzxu().zzyh())));
            ECPoint w = ((ECPublicKey) zza.getPublic()).getW();
            return zzaws.zzya().zzar(0).zzb((zzawu) zzawu.zzye().zzas(0).zzc(zzawo.zzxs()).zzac(zzbah.zzo(w.getAffineX().toByteArray())).zzad(zzbah.zzo(w.getAffineY().toByteArray())).zzadi()).zzy(zzbah.zzo(((ECPrivateKey) zza.getPrivate()).getS().toByteArray())).zzadi();
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfKeyFormat proto");
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").zzai(((zzaws) zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.ASYMMETRIC_PRIVATE).zzadi();
    }
}
