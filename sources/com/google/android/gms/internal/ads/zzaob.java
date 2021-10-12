package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzaob implements Runnable {
    private final Executor zzcvx;
    private final Runnable zzxi;

    zzaob(Executor executor, Runnable runnable) {
        this.zzcvx = executor;
        this.zzxi = runnable;
    }

    public final void run() {
        this.zzcvx.execute(this.zzxi);
    }
}
