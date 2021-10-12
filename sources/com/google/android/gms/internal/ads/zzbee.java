package com.google.android.gms.internal.ads;

import java.io.IOException;

abstract class zzbee<T, B> {
    zzbee() {
    }

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, zzbah zzbah);

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, T t);

    /* access modifiers changed from: package-private */
    public abstract void zza(T t, zzbey zzbey) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract boolean zza(zzbdl zzbdl);

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    public final boolean zza(B b, zzbdl zzbdl) throws IOException {
        int tag = zzbdl.getTag();
        int i = tag >>> 3;
        int i2 = tag & 7;
        if (i2 == 0) {
            zza(b, i, zzbdl.zzabm());
            return true;
        } else if (i2 == 1) {
            zzb(b, i, zzbdl.zzabo());
            return true;
        } else if (i2 == 2) {
            zza((Object) b, i, zzbdl.zzabs());
            return true;
        } else if (i2 == 3) {
            B zzagb = zzagb();
            int i3 = 4 | (i << 3);
            while (zzbdl.zzaci() != Integer.MAX_VALUE && zza(zzagb, zzbdl)) {
                while (zzbdl.zzaci() != Integer.MAX_VALUE) {
                    while (zzbdl.zzaci() != Integer.MAX_VALUE) {
                    }
                }
            }
            if (i3 == zzbdl.getTag()) {
                zza((Object) b, i, (Object) zzv(zzagb));
                return true;
            }
            throw zzbbu.zzadp();
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzc(b, i, zzbdl.zzabp());
                return true;
            }
            throw zzbbu.zzadq();
        }
    }

    /* access modifiers changed from: package-private */
    public abstract T zzac(Object obj);

    /* access modifiers changed from: package-private */
    public abstract B zzad(Object obj);

    /* access modifiers changed from: package-private */
    public abstract int zzae(T t);

    /* access modifiers changed from: package-private */
    public abstract B zzagb();

    /* access modifiers changed from: package-private */
    public abstract void zzb(B b, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void zzc(B b, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zzc(T t, zzbey zzbey) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zze(Object obj, T t);

    /* access modifiers changed from: package-private */
    public abstract void zzf(Object obj, B b);

    /* access modifiers changed from: package-private */
    public abstract T zzg(T t, T t2);

    /* access modifiers changed from: package-private */
    public abstract void zzo(Object obj);

    /* access modifiers changed from: package-private */
    public abstract T zzv(B b);

    /* access modifiers changed from: package-private */
    public abstract int zzy(T t);
}
