package com.google.android.gms.internal.ads;

import android.content.Context;

/* access modifiers changed from: package-private */
public final class zzmc implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzmb zzath;

    zzmc(zzmb zzmb, Context context) {
        this.zzath = zzmb;
        this.val$context = context;
    }

    public final void run() {
        this.zzath.getRewardedVideoAdInstance(this.val$context);
    }
}
