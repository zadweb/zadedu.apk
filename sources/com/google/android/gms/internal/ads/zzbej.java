package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzbej implements Iterator<String> {
    private final /* synthetic */ zzbeh zzdzc;
    private Iterator<String> zzdzd = this.zzdzc.zzdyz.iterator();

    zzbej(zzbeh zzbeh) {
        this.zzdzc = zzbeh;
    }

    public final boolean hasNext() {
        return this.zzdzd.hasNext();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzdzd.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
