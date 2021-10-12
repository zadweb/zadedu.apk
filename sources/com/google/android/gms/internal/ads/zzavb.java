package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzavb implements zzaug<zzauf> {
    zzavb() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzg */
    public final zzauf zza(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzawu zzab = zzawu.zzab(zzbah);
            if (zzab instanceof zzawu) {
                zzawu zzawu = zzab;
                zzazq.zzj(zzawu.getVersion(), 0);
                zzavh.zza(zzawu.zzxs());
                zzawq zzxs = zzawu.zzxs();
                zzaww zzxu = zzxs.zzxu();
                return new zzayp(zzayt.zza(zzavh.zza(zzxu.zzyh()), zzawu.zzyc().toByteArray(), zzawu.zzyd().toByteArray()), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPublicKey proto", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final int getVersion() {
        return 0;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzaug
    public final /* synthetic */ zzauf zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzawu) {
            zzawu zzawu = (zzawu) zzbcu;
            zzazq.zzj(zzawu.getVersion(), 0);
            zzavh.zza(zzawu.zzxs());
            zzawq zzxs = zzawu.zzxs();
            zzaww zzxu = zzxs.zzxu();
            return new zzayp(zzayt.zza(zzavh.zza(zzxu.zzyh()), zzawu.zzyc().toByteArray(), zzawu.zzyd().toByteArray()), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    @Override // com.google.android.gms.internal.ads.zzaug
    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }
}
