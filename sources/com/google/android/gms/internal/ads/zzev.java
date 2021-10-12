package com.google.android.gms.internal.ads;

import android.view.View;
import java.lang.ref.WeakReference;

public final class zzev implements zzgd {
    private WeakReference<zzoz> zzafl;

    public zzev(zzoz zzoz) {
        this.zzafl = new WeakReference<>(zzoz);
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final View zzgh() {
        zzoz zzoz = this.zzafl.get();
        if (zzoz != null) {
            return zzoz.zzkr();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final boolean zzgi() {
        return this.zzafl.get() == null;
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final zzgd zzgj() {
        return new zzex(this.zzafl.get());
    }
}
