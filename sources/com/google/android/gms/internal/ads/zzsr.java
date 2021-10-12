package com.google.android.gms.internal.ads;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* access modifiers changed from: package-private */
public final class zzsr implements BaseGmsClient.BaseOnConnectionFailedListener {
    private final /* synthetic */ zzsm zzbnn;
    private final /* synthetic */ zzaoj zzbno;

    zzsr(zzsm zzsm, zzaoj zzaoj) {
        this.zzbnn = zzsm;
        this.zzbno = zzaoj;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        synchronized (this.zzbnn.mLock) {
            this.zzbno.setException(new RuntimeException("Connection failed."));
        }
    }
}
