package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

@zzadh
final class zzst {
    private final List<zzts> zzxo = new ArrayList();

    zzst() {
    }

    /* access modifiers changed from: package-private */
    public final void zza(zztt zztt) {
        Handler handler = zzakk.zzcrm;
        for (zzts zzts : this.zzxo) {
            handler.post(new zztr(this, zzts, zztt));
        }
        this.zzxo.clear();
    }
}
