package com.google.android.gms.internal.ads;

import java.util.ListIterator;

final class zzbei implements ListIterator<String> {
    private ListIterator<String> zzdza = this.zzdzc.zzdyz.listIterator(this.zzdzb);
    private final /* synthetic */ int zzdzb;
    private final /* synthetic */ zzbeh zzdzc;

    zzbei(zzbeh zzbeh, int i) {
        this.zzdzc = zzbeh;
        this.zzdzb = i;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.util.ListIterator
    public final /* synthetic */ void add(String str) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.zzdza.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzdza.hasPrevious();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator, java.util.ListIterator
    public final /* synthetic */ String next() {
        return this.zzdza.next();
    }

    public final int nextIndex() {
        return this.zzdza.nextIndex();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.ListIterator
    public final /* synthetic */ String previous() {
        return this.zzdza.previous();
    }

    public final int previousIndex() {
        return this.zzdza.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.util.ListIterator
    public final /* synthetic */ void set(String str) {
        throw new UnsupportedOperationException();
    }
}
