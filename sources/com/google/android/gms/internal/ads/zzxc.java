package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final class zzxc implements Runnable {
    private final /* synthetic */ zzxa zzbts;
    private final /* synthetic */ zzxb zzbtt;

    zzxc(zzxb zzxb, zzxa zzxa) {
        this.zzbtt = zzxb;
        this.zzbts = zzxa;
    }

    public final void run() {
        synchronized (zzxb.zza(this.zzbtt)) {
            if (zzxb.zzb(this.zzbtt) == -2) {
                zzxb.zza(this.zzbtt, zzxb.zzc(this.zzbtt));
                if (zzxb.zzd(this.zzbtt) == null) {
                    this.zzbtt.zzx(4);
                } else if (!zzxb.zze(this.zzbtt) || zzxb.zza(this.zzbtt, 1)) {
                    this.zzbts.zza(this.zzbtt);
                    zzxb.zza(this.zzbtt, this.zzbts);
                } else {
                    String zzf = zzxb.zzf(this.zzbtt);
                    StringBuilder sb = new StringBuilder(String.valueOf(zzf).length() + 56);
                    sb.append("Ignoring adapter ");
                    sb.append(zzf);
                    sb.append(" as delayed impression is not supported");
                    zzakb.zzdk(sb.toString());
                    this.zzbtt.zzx(2);
                }
            }
        }
    }
}
