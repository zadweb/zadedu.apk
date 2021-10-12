package com.google.android.gms.internal.ads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* access modifiers changed from: package-private */
public final class zzbdg {
    private static final zzbdg zzdxa = new zzbdg();
    private final zzbdn zzdxb;
    private final ConcurrentMap<Class<?>, zzbdm<?>> zzdxc = new ConcurrentHashMap();

    private zzbdg() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzbdn zzbdn = null;
        for (int i = 0; i <= 0; i++) {
            zzbdn = zzeq(strArr[0]);
            if (zzbdn != null) {
                break;
            }
        }
        this.zzdxb = zzbdn == null ? new zzbcj() : zzbdn;
    }

    public static zzbdg zzaeo() {
        return zzdxa;
    }

    private static zzbdn zzeq(String str) {
        try {
            return (zzbdn) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final <T> zzbdm<T> zzab(T t) {
        return zze(t.getClass());
    }

    public final <T> zzbdm<T> zze(Class<T> cls) {
        zzbbq.zza((Object) cls, "messageType");
        zzbdm<T> zzbdm = (zzbdm<T>) this.zzdxc.get(cls);
        if (zzbdm != null) {
            return zzbdm;
        }
        zzbdm<T> zzd = this.zzdxb.zzd(cls);
        zzbbq.zza((Object) cls, "messageType");
        zzbbq.zza((Object) zzd, "schema");
        zzbdm<T> zzbdm2 = (zzbdm<T>) this.zzdxc.putIfAbsent(cls, zzd);
        return zzbdm2 != null ? zzbdm2 : zzd;
    }
}
