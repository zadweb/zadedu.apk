package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class zaax extends zabk {
    private WeakReference<zaar> zaa;

    zaax(zaar zaar) {
        this.zaa = new WeakReference<>(zaar);
    }

    @Override // com.google.android.gms.common.api.internal.zabk
    public final void zaa() {
        zaar zaar = this.zaa.get();
        if (zaar != null) {
            zaar.zae();
        }
    }
}
