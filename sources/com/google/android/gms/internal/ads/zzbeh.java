package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzbeh extends AbstractList<String> implements zzbcd, RandomAccess {
    private final zzbcd zzdyz;

    public zzbeh(zzbcd zzbcd) {
        this.zzdyz = zzbcd;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.List, java.util.AbstractList
    public final /* synthetic */ String get(int i) {
        return (String) this.zzdyz.get(i);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public final Iterator<String> iterator() {
        return new zzbej(this);
    }

    @Override // java.util.List, java.util.AbstractList
    public final ListIterator<String> listIterator(int i) {
        return new zzbei(this, i);
    }

    public final int size() {
        return this.zzdyz.size();
    }

    @Override // com.google.android.gms.internal.ads.zzbcd
    public final List<?> zzadw() {
        return this.zzdyz.zzadw();
    }

    @Override // com.google.android.gms.internal.ads.zzbcd
    public final zzbcd zzadx() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbcd
    public final void zzap(zzbah zzbah) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.ads.zzbcd
    public final Object zzcp(int i) {
        return this.zzdyz.zzcp(i);
    }
}
