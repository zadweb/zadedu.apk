package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final class zzhe implements Runnable {
    private final /* synthetic */ zzhd zzajt;

    zzhe(zzhd zzhd) {
        this.zzajt = zzhd;
    }

    public final void run() {
        this.zzajt.disconnect();
    }
}
