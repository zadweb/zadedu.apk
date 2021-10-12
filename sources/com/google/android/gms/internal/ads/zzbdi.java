package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

final class zzbdi implements zzbcs {
    private final String info;
    private final zzbcu zzdwl;
    private final zzbdj zzdxe;

    zzbdi(zzbcu zzbcu, String str, Object[] objArr) {
        this.zzdwl = zzbcu;
        this.info = str;
        this.zzdxe = new zzbdj(zzbcu.getClass(), str, objArr);
    }

    public final int getFieldCount() {
        return zzbdj.zzd(this.zzdxe);
    }

    @Override // com.google.android.gms.internal.ads.zzbcs
    public final int zzaeh() {
        return (zzbdj.zza(this.zzdxe) & 1) == 1 ? zzbbo.zze.zzdui : zzbbo.zze.zzduj;
    }

    @Override // com.google.android.gms.internal.ads.zzbcs
    public final boolean zzaei() {
        return (zzbdj.zza(this.zzdxe) & 2) == 2;
    }

    @Override // com.google.android.gms.internal.ads.zzbcs
    public final zzbcu zzaej() {
        return this.zzdwl;
    }

    /* access modifiers changed from: package-private */
    public final zzbdj zzaeq() {
        return this.zzdxe;
    }

    public final int zzaer() {
        return zzbdj.zzb(this.zzdxe);
    }

    public final int zzaes() {
        return zzbdj.zzc(this.zzdxe);
    }

    public final int zzaet() {
        return zzbdj.zze(this.zzdxe);
    }

    public final int zzaeu() {
        return zzbdj.zzf(this.zzdxe);
    }

    /* access modifiers changed from: package-private */
    public final int[] zzaev() {
        return zzbdj.zzg(this.zzdxe);
    }

    public final int zzaew() {
        return zzbdj.zzh(this.zzdxe);
    }

    public final int zzaex() {
        return zzbdj.zzi(this.zzdxe);
    }
}
