package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;

/* access modifiers changed from: package-private */
public final class zzsn extends zzaoj<ParcelFileDescriptor> {
    private final /* synthetic */ zzsm zzbnn;

    zzsn(zzsm zzsm) {
        this.zzbnn = zzsm;
    }

    @Override // com.google.android.gms.internal.ads.zzaoj
    public final boolean cancel(boolean z) {
        this.zzbnn.disconnect();
        return super.cancel(z);
    }
}
