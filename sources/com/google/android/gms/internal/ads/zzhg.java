package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.internal.BaseGmsClient;

/* access modifiers changed from: package-private */
public final class zzhg implements BaseGmsClient.BaseConnectionCallbacks {
    private final /* synthetic */ zzhd zzajt;

    zzhg(zzhd zzhd) {
        this.zzajt = zzhd;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        synchronized (this.zzajt.mLock) {
            try {
                if (this.zzajt.zzajr != null) {
                    this.zzajt.zzajs = this.zzajt.zzajr.zzhl();
                }
            } catch (DeadObjectException e) {
                zzakb.zzb("Unable to obtain a cache service instance.", e);
                this.zzajt.disconnect();
            }
            this.zzajt.mLock.notifyAll();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        synchronized (this.zzajt.mLock) {
            this.zzajt.zzajs = null;
            this.zzajt.mLock.notifyAll();
        }
    }
}
