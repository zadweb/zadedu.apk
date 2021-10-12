package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zzadh
public final class zzsa extends zzrg {
    private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzble;

    public zzsa(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener) {
        this.zzble = onCustomTemplateAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzrf
    public final void zzb(zzqs zzqs) {
        this.zzble.onCustomTemplateAdLoaded(zzqv.zza(zzqs));
    }
}
