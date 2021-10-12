package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzbds extends zzbdy {
    private final /* synthetic */ zzbdp zzdyq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzbds(zzbdp zzbdp) {
        super(zzbdp, null);
        this.zzdyq = zzbdp;
    }

    /* synthetic */ zzbds(zzbdp zzbdp, zzbdq zzbdq) {
        this(zzbdp);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, com.google.android.gms.internal.ads.zzbdy, java.lang.Iterable
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzbdr(this.zzdyq, null);
    }
}
