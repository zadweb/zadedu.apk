package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final class zzajy implements Runnable {
    private final /* synthetic */ zzajx zzcqt;

    zzajy(zzajx zzajx) {
        this.zzcqt = zzajx;
    }

    public final void run() {
        this.zzcqt.zzcqr = Thread.currentThread();
        this.zzcqt.zzdn();
    }
}
