package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class zau implements zabn {
    private final /* synthetic */ zas zaa;

    private zau(zas zas) {
        this.zaa = zas;
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final void zaa(Bundle bundle) {
        this.zaa.zam.lock();
        try {
            this.zaa.zaa((zas) bundle);
            this.zaa.zaj = ConnectionResult.RESULT_SUCCESS;
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final void zaa(ConnectionResult connectionResult) {
        this.zaa.zam.lock();
        try {
            this.zaa.zaj = connectionResult;
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabn
    public final void zaa(int i, boolean z) {
        this.zaa.zam.lock();
        try {
            if (!(this.zaa.zal) && this.zaa.zak != null) {
                if (this.zaa.zak.isSuccess()) {
                    this.zaa.zal = true;
                    this.zaa.zae.onConnectionSuspended(i);
                    this.zaa.zam.unlock();
                    return;
                }
            }
            this.zaa.zal = false;
            this.zaa.zaa((zas) i, (int) z);
        } finally {
            this.zaa.zam.unlock();
        }
    }

    /* synthetic */ zau(zas zas, zar zar) {
        this(zas);
    }
}
