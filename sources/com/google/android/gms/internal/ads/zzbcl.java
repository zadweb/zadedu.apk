package com.google.android.gms.internal.ads;

final class zzbcl implements zzbct {
    private zzbct[] zzdvx;

    zzbcl(zzbct... zzbctArr) {
        this.zzdvx = zzbctArr;
    }

    @Override // com.google.android.gms.internal.ads.zzbct
    public final boolean zza(Class<?> cls) {
        for (zzbct zzbct : this.zzdvx) {
            if (zzbct.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzbct
    public final zzbcs zzb(Class<?> cls) {
        zzbct[] zzbctArr = this.zzdvx;
        for (zzbct zzbct : zzbctArr) {
            if (zzbct.zza(cls)) {
                return zzbct.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
