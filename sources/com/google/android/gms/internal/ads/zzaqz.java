package com.google.android.gms.internal.ads;

import android.view.View;

/* access modifiers changed from: package-private */
public final class zzaqz implements Runnable {
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ int zzdch;
    private final /* synthetic */ zzaqx zzdci;

    zzaqz(zzaqx zzaqx, View view, zzait zzait, int i) {
        this.zzdci = zzaqx;
        this.val$view = view;
        this.zzdcg = zzait;
        this.zzdch = i;
    }

    public final void run() {
        this.zzdci.zza((zzaqx) this.val$view, (View) this.zzdcg, (zzait) (this.zzdch - 1));
    }
}
