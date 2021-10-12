package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.Iterator;

/* access modifiers changed from: package-private */
public final class zzakf extends zzakg {
    private final /* synthetic */ zzakd zzcrh;
    private final /* synthetic */ Bundle zzcri;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzakf(zzakd zzakd, Bundle bundle) {
        super(null);
        this.zzcrh = zzakd;
        this.zzcri = bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzajx
    public final void zzdn() {
        Iterator it = this.zzcrh.zzcqv.iterator();
        while (it.hasNext()) {
            ((zzakh) it.next()).zzd(this.zzcri);
        }
    }
}
