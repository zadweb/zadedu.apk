package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;

/* access modifiers changed from: package-private */
public final class zzpr implements zzox {
    private final /* synthetic */ View zzbkc;
    private final /* synthetic */ zzpp zzbkj;

    zzpr(zzpp zzpp, View view) {
        this.zzbkj = zzpp;
        this.zzbkc = view;
    }

    @Override // com.google.android.gms.internal.ads.zzox
    public final void zzc(MotionEvent motionEvent) {
        this.zzbkj.onTouch(null, motionEvent);
    }

    @Override // com.google.android.gms.internal.ads.zzox
    public final void zzki() {
        if (this.zzbkj.zza(zzpp.zzbjs)) {
            this.zzbkj.onClick(this.zzbkc);
        }
    }
}
