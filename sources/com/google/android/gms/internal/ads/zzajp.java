package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

/* access modifiers changed from: package-private */
public final class zzajp {
    private final Object mLock;
    private volatile int zzcpx;
    private volatile long zzcpy;

    private zzajp() {
        this.mLock = new Object();
        this.zzcpx = zzajq.zzcpz;
        this.zzcpy = 0;
    }

    /* synthetic */ zzajp(zzajo zzajo) {
        this();
    }

    private final void zzd(int i, int i2) {
        zzqk();
        long currentTimeMillis = zzbv.zzer().currentTimeMillis();
        synchronized (this.mLock) {
            if (this.zzcpx == i) {
                this.zzcpx = i2;
                if (this.zzcpx == zzajq.zzcqb) {
                    this.zzcpy = currentTimeMillis;
                }
            }
        }
    }

    private final void zzqk() {
        long currentTimeMillis = zzbv.zzer().currentTimeMillis();
        synchronized (this.mLock) {
            if (this.zzcpx == zzajq.zzcqb) {
                if (this.zzcpy + ((Long) zzkb.zzik().zzd(zznk.zzbfn)).longValue() <= currentTimeMillis) {
                    this.zzcpx = zzajq.zzcpz;
                }
            }
        }
    }

    public final void zzaa(boolean z) {
        int i;
        int i2;
        if (z) {
            i = zzajq.zzcpz;
            i2 = zzajq.zzcqa;
        } else {
            i = zzajq.zzcqa;
            i2 = zzajq.zzcpz;
        }
        zzd(i, i2);
    }

    public final boolean zzqa() {
        zzqk();
        return this.zzcpx == zzajq.zzcqa;
    }

    public final boolean zzqb() {
        zzqk();
        return this.zzcpx == zzajq.zzcqb;
    }

    public final void zzqc() {
        zzd(zzajq.zzcqa, zzajq.zzcqb);
    }
}
