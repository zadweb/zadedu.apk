package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.ads.internal.zzbv;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzadh
public final class zzsm implements zzm {
    private final Context mContext;
    private final Object mLock = new Object();
    private zzsf zzbnl;
    private boolean zzbnm;

    public zzsm(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzbnl != null) {
                this.zzbnl.disconnect();
                this.zzbnl = null;
                Binder.flushPendingCommands();
            }
        }
    }

    private final Future<ParcelFileDescriptor> zzb(zzsg zzsg) {
        zzsn zzsn = new zzsn(this);
        zzso zzso = new zzso(this, zzsn, zzsg);
        zzsr zzsr = new zzsr(this, zzsn);
        synchronized (this.mLock) {
            zzsf zzsf = new zzsf(this.mContext, zzbv.zzez().zzsa(), zzso, zzsr);
            this.zzbnl = zzsf;
            zzsf.checkAvailabilityAndConnect();
        }
        return zzsn;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00be, code lost:
        r0 = new java.lang.StringBuilder(52);
        r0.append("Http assets remote cache took ");
        r0.append(com.google.android.gms.ads.internal.zzbv.zzer().elapsedRealtime() - r5);
        r0.append("ms");
        com.google.android.gms.internal.ads.zzakb.v(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00df, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[ExcHandler: InterruptedException | ExecutionException | TimeoutException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:15:0x0094] */
    @Override // com.google.android.gms.internal.ads.zzm
    public final zzp zzc(zzr<?> zzr) throws zzae {
        zzp zzp;
        zzsg zzh = zzsg.zzh(zzr);
        long intValue = (long) ((Integer) zzkb.zzik().zzd(zznk.zzbdx)).intValue();
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        try {
            zzsi zzsi = (zzsi) new zzaev(zzb(zzh).get(intValue, TimeUnit.MILLISECONDS)).zza(zzsi.CREATOR);
            if (!zzsi.zzbnj) {
                if (zzsi.zzbnh.length != zzsi.zzbni.length) {
                    zzp = null;
                } else {
                    HashMap hashMap = new HashMap();
                    for (int i = 0; i < zzsi.zzbnh.length; i++) {
                        hashMap.put(zzsi.zzbnh[i], zzsi.zzbni[i]);
                    }
                    zzp = new zzp(zzsi.statusCode, zzsi.data, hashMap, zzsi.zzac, zzsi.zzad);
                }
                return zzp;
            }
            try {
                throw new zzae(zzsi.zzbnk);
            } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            }
        } finally {
            StringBuilder sb = new StringBuilder(52);
            sb.append("Http assets remote cache took ");
            sb.append(zzbv.zzer().elapsedRealtime() - elapsedRealtime);
            sb.append("ms");
            zzakb.v(sb.toString());
        }
    }
}
