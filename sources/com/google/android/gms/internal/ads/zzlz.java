package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final class zzlz extends zzkd {
    private final /* synthetic */ zzly zzatc;

    zzlz(zzly zzly) {
        this.zzatc = zzly;
    }

    @Override // com.google.android.gms.internal.ads.zzkd, com.google.android.gms.ads.AdListener
    public final void onAdFailedToLoad(int i) {
        this.zzatc.zzasv.zza(this.zzatc.zzbc());
        super.onAdFailedToLoad(i);
    }

    @Override // com.google.android.gms.internal.ads.zzkd, com.google.android.gms.ads.AdListener
    public final void onAdLoaded() {
        this.zzatc.zzasv.zza(this.zzatc.zzbc());
        super.onAdLoaded();
    }
}
