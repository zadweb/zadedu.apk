package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeoutException;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzans implements Runnable {
    private final zzaoj zzbnu;

    zzans(zzaoj zzaoj) {
        this.zzbnu = zzaoj;
    }

    public final void run() {
        this.zzbnu.setException(new TimeoutException());
    }
}
