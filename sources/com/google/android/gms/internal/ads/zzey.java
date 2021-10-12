package com.google.android.gms.internal.ads;

import android.view.View;

public final class zzey implements zzgd {
    private final View mView;
    private final zzajh zzafn;

    public zzey(View view, zzajh zzajh) {
        this.mView = view;
        this.zzafn = zzajh;
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final View zzgh() {
        return this.mView;
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final boolean zzgi() {
        return this.zzafn == null || this.mView == null;
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final zzgd zzgj() {
        return this;
    }
}
