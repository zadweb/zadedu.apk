package com.google.android.gms.internal.ads;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public final class zzxa extends zzxu {
    private final Object mLock = new Object();
    private zzxf zzbtf;
    private zzwz zzbtg;

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void onAdClicked() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzce();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void onAdClosed() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcf();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void onAdFailedToLoad(int i) {
        synchronized (this.mLock) {
            if (this.zzbtf != null) {
                this.zzbtf.zzx(i == 3 ? 1 : 2);
                this.zzbtf = null;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void onAdImpression() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcj();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void onAdLeftApplication() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcg();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void onAdLoaded() {
        synchronized (this.mLock) {
            if (this.zzbtf != null) {
                this.zzbtf.zzx(0);
                this.zzbtf = null;
                return;
            }
            if (this.zzbtg != null) {
                this.zzbtg.zzci();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void onAdOpened() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzch();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void onAppEvent(String str, String str2) {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzb(str, str2);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void onVideoEnd() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcd();
            }
        }
    }

    public final void zza(zzwz zzwz) {
        synchronized (this.mLock) {
            this.zzbtg = zzwz;
        }
    }

    public final void zza(zzxf zzxf) {
        synchronized (this.mLock) {
            this.zzbtf = zzxf;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void zza(zzxw zzxw) {
        synchronized (this.mLock) {
            if (this.zzbtf != null) {
                this.zzbtf.zza(0, zzxw);
                this.zzbtf = null;
                return;
            }
            if (this.zzbtg != null) {
                this.zzbtg.zzci();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void zzb(zzqs zzqs, String str) {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zza(zzqs, str);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzxt
    public final void zzbj(String str) {
    }
}
