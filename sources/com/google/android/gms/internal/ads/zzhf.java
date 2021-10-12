package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final class zzhf implements zzgj {
    private final /* synthetic */ zzhd zzajt;

    zzhf(zzhd zzhd) {
        this.zzajt = zzhd;
    }

    @Override // com.google.android.gms.internal.ads.zzgj
    public final void zzh(boolean z) {
        if (z) {
            this.zzajt.connect();
        } else {
            this.zzajt.disconnect();
        }
    }
}
