package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

public final class zaah implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final WeakReference<zaaf> zaa;
    private final Api<?> zab;
    private final boolean zac;

    public zaah(zaaf zaaf, Api<?> api, boolean z) {
        this.zaa = new WeakReference<>(zaaf);
        this.zab = api;
        this.zac = z;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(ConnectionResult connectionResult) {
        zaaf zaaf = this.zaa.get();
        if (zaaf != null) {
            Preconditions.checkState(Looper.myLooper() == zaaf.zaa.zad.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zaaf.zab.lock();
            try {
                if (zaaf.zaa(zaaf, 0)) {
                    if (!connectionResult.isSuccess()) {
                        zaaf.zab(connectionResult, this.zab, this.zac);
                    }
                    if (zaaf.zad()) {
                        zaaf.zae();
                    }
                    zaaf.zab.unlock();
                }
            } finally {
                zaaf.zab.unlock();
            }
        }
    }
}
