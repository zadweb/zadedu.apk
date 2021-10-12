package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* access modifiers changed from: package-private */
public final class zzgs implements Comparator<zzgy> {
    zzgs(zzgr zzgr) {
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzgy zzgy, zzgy zzgy2) {
        zzgy zzgy3 = zzgy;
        zzgy zzgy4 = zzgy2;
        int i = zzgy3.zzajg - zzgy4.zzajg;
        return i != 0 ? i : (int) (zzgy3.value - zzgy4.value);
    }
}
