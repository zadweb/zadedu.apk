package com.google.android.gms.internal.ads;

final class zzcd implements Runnable {
    private final /* synthetic */ zzcc zzpx;

    zzcd(zzcc zzcc) {
        this.zzpx = zzcc;
    }

    public final void run() {
        if (this.zzpx.zzpv == null) {
            synchronized (zzcc.zzz()) {
                if (this.zzpx.zzpv == null) {
                    boolean booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzbap)).booleanValue();
                    if (booleanValue) {
                        try {
                            zzcc.zzpu = new zzhx(zzcc.zza(this.zzpx).zzrt, "ADSHIELD", null);
                        } catch (Throwable unused) {
                            booleanValue = false;
                        }
                    }
                    this.zzpx.zzpv = Boolean.valueOf(booleanValue);
                    zzcc.zzz().open();
                }
            }
        }
    }
}
