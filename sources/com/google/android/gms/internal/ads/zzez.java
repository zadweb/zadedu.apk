package com.google.android.gms.internal.ads;

import android.view.View;
import java.lang.ref.WeakReference;

public final class zzez implements zzgd {
    private final WeakReference<View> zzafo;
    private final WeakReference<zzajh> zzafp;

    public zzez(View view, zzajh zzajh) {
        this.zzafo = new WeakReference<>(view);
        this.zzafp = new WeakReference<>(zzajh);
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final View zzgh() {
        return this.zzafo.get();
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final boolean zzgi() {
        return this.zzafo.get() == null || this.zzafp.get() == null;
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final zzgd zzgj() {
        return new zzey(this.zzafo.get(), this.zzafp.get());
    }
}
