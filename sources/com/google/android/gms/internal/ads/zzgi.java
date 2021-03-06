package com.google.android.gms.internal.ads;

final class zzgi implements Runnable {
    private final /* synthetic */ zzgh zzahx;

    zzgi(zzgh zzgh) {
        this.zzahx = zzgh;
    }

    public final void run() {
        synchronized (this.zzahx.mLock) {
            if (!(this.zzahx.zzahr) || !(this.zzahx.zzahs)) {
                zzakb.zzck("App is still foreground");
            } else {
                this.zzahx.zzahr = false;
                zzakb.zzck("App went background");
                for (zzgj zzgj : this.zzahx.zzaht) {
                    try {
                        zzgj.zzh(false);
                    } catch (Exception e) {
                        zzane.zzb("", e);
                    }
                }
            }
        }
    }
}
