package com.google.android.gms.internal.ads;

import java.util.List;

final class zzbch extends zzbce {
    private zzbch() {
        super();
    }

    private static <E> zzbbt<E> zzd(Object obj, long j) {
        return (zzbbt) zzbek.zzp(obj, j);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzbce
    public final <L> List<L> zza(Object obj, long j) {
        zzbbt zzd = zzd(obj, j);
        if (zzd.zzaay()) {
            return zzd;
        }
        int size = zzd.size();
        zzbbt zzbm = zzd.zzbm(size == 0 ? 10 : size << 1);
        zzbek.zza(obj, j, zzbm);
        return zzbm;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.ads.zzbbt] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzbce
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzbbt<E> zzd = zzd(obj, j);
        zzbbt<E> zzd2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = zzd2.size();
        zzbbt<E> zzbbt = zzd;
        zzbbt = zzd;
        if (size > 0 && size2 > 0) {
            boolean zzaay = zzd.zzaay();
            zzbbt<E> zzbbt2 = zzd;
            if (!zzaay) {
                zzbbt2 = zzd.zzbm(size2 + size);
            }
            zzbbt2.addAll(zzd2);
            zzbbt = zzbbt2;
        }
        if (size > 0) {
            zzd2 = zzbbt;
        }
        zzbek.zza(obj, j, zzd2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzbce
    public final void zzb(Object obj, long j) {
        zzd(obj, j).zzaaz();
    }
}
