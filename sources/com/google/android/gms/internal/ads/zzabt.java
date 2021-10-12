package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbc;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zzabt extends zzajx {
    private final Object mLock;
    private final zzabm zzbzd;
    private final zzaji zzbze;
    private final zzaej zzbzf;
    private final zzabv zzbzu;
    private Future<zzajh> zzbzv;

    public zzabt(Context context, zzbc zzbc, zzaji zzaji, zzci zzci, zzabm zzabm, zznx zznx) {
        this(zzaji, zzabm, new zzabv(context, zzbc, new zzalt(context), zzci, zzaji, zznx));
    }

    private zzabt(zzaji zzaji, zzabm zzabm, zzabv zzabv) {
        this.mLock = new Object();
        this.zzbze = zzaji;
        this.zzbzf = zzaji.zzcos;
        this.zzbzd = zzabm;
        this.zzbzu = zzabv;
    }

    @Override // com.google.android.gms.internal.ads.zzajx
    public final void onStop() {
        synchronized (this.mLock) {
            if (this.zzbzv != null) {
                this.zzbzv.cancel(true);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[ExcHandler: InterruptedException | CancellationException | ExecutionException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:8:0x0013] */
    @Override // com.google.android.gms.internal.ads.zzajx
    public final void zzdn() {
        int i;
        zzanz zza;
        zzajh zzajh = null;
        try {
            synchronized (this.mLock) {
                zza = zzaki.zza(this.zzbzu);
                this.zzbzv = zza;
            }
            try {
                zzajh = (zzajh) zza.get(60000, TimeUnit.MILLISECONDS);
                i = -2;
            } catch (InterruptedException | CancellationException | ExecutionException unused) {
            }
        } catch (TimeoutException unused2) {
            zzakb.zzdk("Timed out waiting for native ad.");
            this.zzbzv.cancel(true);
            i = 2;
        }
        if (zzajh == null) {
            zzajh = new zzajh(this.zzbze.zzcgs.zzccv, null, null, i, null, null, this.zzbzf.orientation, this.zzbzf.zzbsu, this.zzbze.zzcgs.zzccy, false, null, null, null, null, null, this.zzbzf.zzcer, this.zzbze.zzacv, this.zzbzf.zzcep, this.zzbze.zzcoh, this.zzbzf.zzceu, this.zzbzf.zzcev, this.zzbze.zzcob, null, null, null, null, this.zzbze.zzcos.zzcfh, this.zzbze.zzcos.zzcfi, null, null, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, false, this.zzbze.zzcos.zzcfp, null, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq);
        }
        zzakk.zzcrm.post(new zzabu(this, zzajh));
    }
}
