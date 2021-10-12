package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzbcg extends zzbce {
    private static final Class<?> zzdvs = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzbcg() {
        super();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: java.util.ArrayList */
    /* JADX WARN: Multi-variable type inference failed */
    private static <L> List<L> zza(Object obj, long j, int i) {
        zzbcc zzbcc;
        List<L> zzc = zzc(obj, j);
        if (zzc.isEmpty()) {
            List<L> zzbcc2 = zzc instanceof zzbcd ? new zzbcc(i) : new ArrayList<>(i);
            zzbek.zza(obj, j, zzbcc2);
            return zzbcc2;
        }
        if (zzdvs.isAssignableFrom(zzc.getClass())) {
            ArrayList arrayList = new ArrayList(zzc.size() + i);
            arrayList.addAll(zzc);
            zzbcc = arrayList;
        } else if (!(zzc instanceof zzbeh)) {
            return zzc;
        } else {
            zzbcc zzbcc3 = new zzbcc(zzc.size() + i);
            zzbcc3.addAll((zzbeh) zzc);
            zzbcc = zzbcc3;
        }
        zzbek.zza(obj, j, zzbcc);
        return zzbcc;
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzbek.zzp(obj, j);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzbce
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzbce
    public final <E> void zza(Object obj, Object obj2, long j) {
        List zzc = zzc(obj2, j);
        List zza = zza(obj, j, zzc.size());
        int size = zza.size();
        int size2 = zzc.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(zzc);
        }
        if (size > 0) {
            zzc = zza;
        }
        zzbek.zza(obj, j, zzc);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzbce
    public final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzbek.zzp(obj, j);
        if (list instanceof zzbcd) {
            obj2 = ((zzbcd) list).zzadx();
        } else if (!zzdvs.isAssignableFrom(list.getClass())) {
            obj2 = Collections.unmodifiableList(list);
        } else {
            return;
        }
        zzbek.zza(obj, j, obj2);
    }
}
