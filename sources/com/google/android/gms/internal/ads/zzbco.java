package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class zzbco<K, V> extends LinkedHashMap<K, V> {
    private static final zzbco zzdwc;
    private boolean zzdpi = true;

    static {
        zzbco zzbco = new zzbco();
        zzdwc = zzbco;
        zzbco.zzdpi = false;
    }

    private zzbco() {
    }

    private zzbco(Map<K, V> map) {
        super(map);
    }

    public static <K, V> zzbco<K, V> zzaeb() {
        return zzdwc;
    }

    private final void zzaed() {
        if (!this.zzdpi) {
            throw new UnsupportedOperationException();
        }
    }

    private static int zzr(Object obj) {
        if (obj instanceof byte[]) {
            return zzbbq.hashCode((byte[]) obj);
        }
        if (!(obj instanceof zzbbr)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        zzaed();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.AbstractMap, java.util.Map, java.util.HashMap
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x005c A[RETURN] */
    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this != map) {
                if (size() == map.size()) {
                    Iterator<Map.Entry<K, V>> it = entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry<K, V> next = it.next();
                        if (map.containsKey(next.getKey())) {
                            V value = next.getValue();
                            Object obj2 = map.get(next.getKey());
                            if (!(value instanceof byte[]) || !(obj2 instanceof byte[])) {
                                z2 = value.equals(obj2);
                                continue;
                            } else {
                                z2 = Arrays.equals((byte[]) value, (byte[]) obj2);
                                continue;
                            }
                            if (!z2) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                z = false;
                if (!z) {
                    return true;
                }
            }
            z = true;
            if (!z) {
                return false;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            i += zzr(entry.getValue()) ^ zzr(entry.getKey());
        }
        return i;
    }

    public final boolean isMutable() {
        return this.zzdpi;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public final V put(K k, V v) {
        zzaed();
        zzbbq.checkNotNull(k);
        zzbbq.checkNotNull(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public final void putAll(Map<? extends K, ? extends V> map) {
        zzaed();
        for (Object obj : map.keySet()) {
            zzbbq.checkNotNull(obj);
            zzbbq.checkNotNull(map.get(obj));
        }
        super.putAll(map);
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public final V remove(Object obj) {
        zzaed();
        return (V) super.remove(obj);
    }

    public final void zza(zzbco<K, V> zzbco) {
        zzaed();
        if (!zzbco.isEmpty()) {
            putAll(zzbco);
        }
    }

    public final void zzaaz() {
        this.zzdpi = false;
    }

    public final zzbco<K, V> zzaec() {
        return isEmpty() ? new zzbco<>() : new zzbco<>(this);
    }
}
