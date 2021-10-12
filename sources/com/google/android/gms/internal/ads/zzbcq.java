package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

final class zzbcq implements zzbcp {
    zzbcq() {
    }

    @Override // com.google.android.gms.internal.ads.zzbcp
    public final int zzb(int i, Object obj, Object obj2) {
        zzbco zzbco = (zzbco) obj;
        if (zzbco.isEmpty()) {
            return 0;
        }
        Iterator it = zzbco.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.ads.zzbcp
    public final Object zzb(Object obj, Object obj2) {
        zzbco zzbco = (zzbco) obj;
        zzbco zzbco2 = (zzbco) obj2;
        if (!zzbco2.isEmpty()) {
            if (!zzbco.isMutable()) {
                zzbco = zzbco.zzaec();
            }
            zzbco.zza(zzbco2);
        }
        return zzbco;
    }

    @Override // com.google.android.gms.internal.ads.zzbcp
    public final Map<?, ?> zzs(Object obj) {
        return (zzbco) obj;
    }

    @Override // com.google.android.gms.internal.ads.zzbcp
    public final Map<?, ?> zzt(Object obj) {
        return (zzbco) obj;
    }

    @Override // com.google.android.gms.internal.ads.zzbcp
    public final boolean zzu(Object obj) {
        return !((zzbco) obj).isMutable();
    }

    @Override // com.google.android.gms.internal.ads.zzbcp
    public final Object zzv(Object obj) {
        ((zzbco) obj).zzaaz();
        return obj;
    }

    @Override // com.google.android.gms.internal.ads.zzbcp
    public final Object zzw(Object obj) {
        return zzbco.zzaeb().zzaec();
    }

    @Override // com.google.android.gms.internal.ads.zzbcp
    public final zzbcn<?, ?> zzx(Object obj) {
        throw new NoSuchMethodError();
    }
}
