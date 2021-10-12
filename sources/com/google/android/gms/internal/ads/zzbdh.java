package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

final class zzbdh<E> extends zzbab<E> {
    private static final zzbdh<Object> zzdxd;
    private final List<E> zzdvp;

    static {
        zzbdh<Object> zzbdh = new zzbdh<>();
        zzdxd = zzbdh;
        zzbdh.zzaaz();
    }

    zzbdh() {
        this(new ArrayList(10));
    }

    private zzbdh(List<E> list) {
        this.zzdvp = list;
    }

    public static <E> zzbdh<E> zzaep() {
        return (zzbdh<E>) zzdxd;
    }

    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final void add(int i, E e) {
        zzaba();
        this.zzdvp.add(i, e);
        this.modCount++;
    }

    @Override // java.util.List, java.util.AbstractList
    public final E get(int i) {
        return this.zzdvp.get(i);
    }

    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final E remove(int i) {
        zzaba();
        E remove = this.zzdvp.remove(i);
        this.modCount++;
        return remove;
    }

    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final E set(int i, E e) {
        zzaba();
        E e2 = this.zzdvp.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zzdvp.size();
    }

    @Override // com.google.android.gms.internal.ads.zzbbt
    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzdvp);
            return new zzbdh(arrayList);
        }
        throw new IllegalArgumentException();
    }
}
