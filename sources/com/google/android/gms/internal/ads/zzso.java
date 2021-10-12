package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.common.internal.BaseGmsClient;

/* access modifiers changed from: package-private */
public final class zzso implements BaseGmsClient.BaseConnectionCallbacks {
    final /* synthetic */ zzsm zzbnn;
    private final /* synthetic */ zzaoj zzbno;
    private final /* synthetic */ zzsg zzbnp;

    zzso(zzsm zzsm, zzaoj zzaoj, zzsg zzsg) {
        this.zzbnn = zzsm;
        this.zzbno = zzaoj;
        this.zzbnp = zzsg;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        synchronized (this.zzbnn.mLock) {
            if (!(this.zzbnn.zzbnm)) {
                this.zzbnn.zzbnm = true;
                zzsf zzsf = this.zzbnn.zzbnl;
                if (zzsf != null) {
                    this.zzbno.zza(new zzsq(this.zzbno, zzaki.zzb(new zzsp(this, zzsf, this.zzbno, this.zzbnp))), zzaoe.zzcvz);
                }
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }
}
