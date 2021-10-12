package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzbcc extends zzbab<String> implements zzbcd, RandomAccess {
    private static final zzbcc zzdvn;
    private static final zzbcd zzdvo = zzdvn;
    private final List<Object> zzdvp;

    static {
        zzbcc zzbcc = new zzbcc();
        zzdvn = zzbcc;
        zzbcc.zzaaz();
    }

    public zzbcc() {
        this(10);
    }

    public zzbcc(int i) {
        this(new ArrayList(i));
    }

    private zzbcc(ArrayList<Object> arrayList) {
        this.zzdvp = arrayList;
    }

    private static String zzq(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzbah ? ((zzbah) obj).zzabd() : zzbbq.zzt((byte[]) obj);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [int, java.lang.Object] */
    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final /* synthetic */ void add(int i, String str) {
        zzaba();
        this.zzdvp.add(i, str);
        this.modCount++;
    }

    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzaba();
        if (collection instanceof zzbcd) {
            collection = ((zzbcd) collection).zzadw();
        }
        boolean addAll = this.zzdvp.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    @Override // java.util.AbstractCollection, java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.Collection
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.ads.zzbab
    public final void clear() {
        zzaba();
        this.zzdvp.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzbab
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.List, java.util.AbstractList
    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzdvp.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbah) {
            zzbah zzbah = (zzbah) obj;
            String zzabd = zzbah.zzabd();
            if (zzbah.zzabe()) {
                this.zzdvp.set(i, zzabd);
            }
            return zzabd;
        }
        byte[] bArr = (byte[]) obj;
        String zzt = zzbbq.zzt(bArr);
        if (zzbbq.zzs(bArr)) {
            this.zzdvp.set(i, zzt);
        }
        return zzt;
    }

    @Override // com.google.android.gms.internal.ads.zzbab
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final /* synthetic */ String remove(int i) {
        zzaba();
        Object remove = this.zzdvp.remove(i);
        this.modCount++;
        return zzq(remove);
    }

    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.Collection
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.Collection
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [int, java.lang.Object] */
    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final /* synthetic */ String set(int i, String str) {
        zzaba();
        return zzq(this.zzdvp.set(i, str));
    }

    public final int size() {
        return this.zzdvp.size();
    }

    @Override // com.google.android.gms.internal.ads.zzbbt, com.google.android.gms.internal.ads.zzbab
    public final /* bridge */ /* synthetic */ boolean zzaay() {
        return super.zzaay();
    }

    @Override // com.google.android.gms.internal.ads.zzbcd
    public final List<?> zzadw() {
        return Collections.unmodifiableList(this.zzdvp);
    }

    @Override // com.google.android.gms.internal.ads.zzbcd
    public final zzbcd zzadx() {
        return zzaay() ? new zzbeh(this) : this;
    }

    @Override // com.google.android.gms.internal.ads.zzbcd
    public final void zzap(zzbah zzbah) {
        zzaba();
        this.zzdvp.add(zzbah);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzbbt
    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzdvp);
            return new zzbcc(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.ads.zzbcd
    public final Object zzcp(int i) {
        return this.zzdvp.get(i);
    }
}
