package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.zad;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zaam implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final /* synthetic */ zaaf zaa;

    private zaam(zaaf zaaf) {
        this.zaa = zaaf;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        if (((ClientSettings) Preconditions.checkNotNull(this.zaa.zar)).zae()) {
            this.zaa.zab.lock();
            try {
                zad zaf = zaaf.zaf(this.zaa);
                if (zaf != null) {
                    zaf.zaa(new zaak(this.zaa));
                    this.zaa.zab.unlock();
                }
            } finally {
                this.zaa.zab.unlock();
            }
        } else {
            ((zad) Preconditions.checkNotNull(zaaf.zaf(this.zaa))).zaa(new zaak(this.zaa));
        }
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zaa.zab.lock();
        try {
            if (this.zaa.zaa((zaaf) connectionResult)) {
                this.zaa.zag();
                this.zaa.zae();
            } else {
                this.zaa.zab((zaaf) connectionResult);
            }
        } finally {
            this.zaa.zab.unlock();
        }
    }

    /* synthetic */ zaam(zaaf zaaf, zaae zaae) {
        this(zaaf);
    }
}
