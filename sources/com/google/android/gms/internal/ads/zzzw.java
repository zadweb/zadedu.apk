package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzn;

final class zzzw implements zzn {
    private final /* synthetic */ zzzv zzbvr;

    zzzw(zzzv zzzv) {
        this.zzbvr = zzzv;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void onPause() {
        zzane.zzck("AdMobCustomTabsAdapter overlay is paused.");
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void onResume() {
        zzane.zzck("AdMobCustomTabsAdapter overlay is resumed.");
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzcb() {
        zzane.zzck("AdMobCustomTabsAdapter overlay is closed.");
        this.zzbvr.zzbvq.onAdClosed(this.zzbvr);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzcc() {
        zzane.zzck("Opening AdMobCustomTabsAdapter overlay.");
        this.zzbvr.zzbvq.onAdOpened(this.zzbvr);
    }
}
