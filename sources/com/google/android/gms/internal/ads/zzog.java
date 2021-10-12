package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

@zzadh
public final class zzog extends zzoe {
    private final OnCustomRenderedAdLoadedListener zzasz;

    public zzog(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzasz = onCustomRenderedAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzod
    public final void zza(zzoa zzoa) {
        this.zzasz.onCustomRenderedAdLoaded(new zznz(zzoa));
    }
}
