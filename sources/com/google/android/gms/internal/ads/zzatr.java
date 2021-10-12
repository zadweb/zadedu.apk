package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

final class zzatr implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    private final String packageName;
    private zzats zzdgz;
    private final String zzdha;
    private final LinkedBlockingQueue<zzba> zzdhb = new LinkedBlockingQueue<>();
    private final HandlerThread zzdhc;

    public zzatr(Context context, String str, String str2) {
        this.packageName = str;
        this.zzdha = str2;
        HandlerThread handlerThread = new HandlerThread("GassClient");
        this.zzdhc = handlerThread;
        handlerThread.start();
        this.zzdgz = new zzats(context, this.zzdhc.getLooper(), this, this);
        this.zzdgz.checkAvailabilityAndConnect();
    }

    private final void zznz() {
        zzats zzats = this.zzdgz;
        if (zzats == null) {
            return;
        }
        if (zzats.isConnected() || this.zzdgz.isConnecting()) {
            this.zzdgz.disconnect();
        }
    }

    private final zzatx zzwb() {
        try {
            return this.zzdgz.zzwd();
        } catch (DeadObjectException | IllegalStateException unused) {
            return null;
        }
    }

    private static zzba zzwc() {
        zzba zzba = new zzba();
        zzba.zzdu = 32768L;
        return zzba;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        zznz();
        r3.zzdhc.quit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r3.zzdhb.put(zzwc());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0025 */
    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        zzatx zzwb = zzwb();
        if (zzwb != null) {
            this.zzdhb.put(zzwb.zza(new zzatt(this.packageName, this.zzdha)).zzwe());
            zznz();
            this.zzdhc.quit();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            this.zzdhb.put(zzwc());
        } catch (InterruptedException unused) {
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        try {
            this.zzdhb.put(zzwc());
        } catch (InterruptedException unused) {
        }
    }

    public final zzba zzak(int i) {
        zzba zzba;
        try {
            zzba = this.zzdhb.poll(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            zzba = null;
        }
        return zzba == null ? zzwc() : zzba;
    }
}
