package com.google.android.gms.internal.ads;

import android.view.View;

public final class zzex implements zzgd {
    private zzoz zzafm;

    public zzex(zzoz zzoz) {
        this.zzafm = zzoz;
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final View zzgh() {
        zzoz zzoz = this.zzafm;
        if (zzoz != null) {
            return zzoz.zzkr();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final boolean zzgi() {
        return this.zzafm == null;
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final zzgd zzgj() {
        return this;
    }
}
