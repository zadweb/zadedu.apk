package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzn;

final class zzasn implements zzn {
    private zzn zzbnc;
    private zzaqw zzdcj;

    public zzasn(zzaqw zzaqw, zzn zzn) {
        this.zzdcj = zzaqw;
        this.zzbnc = zzn;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void onPause() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void onResume() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzcb() {
        this.zzbnc.zzcb();
        this.zzdcj.zzty();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzn
    public final void zzcc() {
        this.zzbnc.zzcc();
        this.zzdcj.zzno();
    }
}
