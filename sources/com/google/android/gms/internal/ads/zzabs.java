package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzas;
import java.util.concurrent.CountDownLatch;

final class zzabs implements Runnable {
    private final /* synthetic */ zzabr zzbzt;
    private final /* synthetic */ CountDownLatch zzwd;

    zzabs(zzabr zzabr, CountDownLatch countDownLatch) {
        this.zzbzt = zzabr;
        this.zzwd = countDownLatch;
    }

    public final void run() {
        synchronized (this.zzbzt.zzbzh) {
            zzabr.zza(this.zzbzt, zzas.zza(zzabr.zza(this.zzbzt), this.zzbzt.zzbzr, this.zzwd));
        }
    }
}
