package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicBoolean;

@zzadh
public abstract class zzabf implements zzalc<Void>, zzasd {
    protected final Context mContext;
    protected final zzaqw zzbnd;
    private final zzabm zzbzd;
    private final zzaji zzbze;
    protected zzaej zzbzf;
    private Runnable zzbzg;
    private final Object zzbzh = new Object();
    private AtomicBoolean zzbzi = new AtomicBoolean(true);

    protected zzabf(Context context, zzaji zzaji, zzaqw zzaqw, zzabm zzabm) {
        this.mContext = context;
        this.zzbze = zzaji;
        this.zzbzf = zzaji.zzcos;
        this.zzbnd = zzaqw;
        this.zzbzd = zzabm;
    }

    @Override // com.google.android.gms.internal.ads.zzalc
    public void cancel() {
        if (this.zzbzi.getAndSet(false)) {
            this.zzbnd.stopLoading();
            zzbv.zzem();
            zzakq.zzi(this.zzbnd);
            zzz(-1);
            zzakk.zzcrm.removeCallbacks(this.zzbzg);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzasd
    public final void zze(boolean z) {
        zzakb.zzck("WebView finished loading.");
        int i = 0;
        if (this.zzbzi.getAndSet(false)) {
            if (z) {
                i = -2;
            }
            zzz(i);
            zzakk.zzcrm.removeCallbacks(this.zzbzg);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzns();

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzalc
    public final /* synthetic */ Void zznt() {
        Preconditions.checkMainThread("Webview render task needs to be called on UI thread.");
        this.zzbzg = new zzabg(this);
        zzakk.zzcrm.postDelayed(this.zzbzg, ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue());
        zzns();
        return null;
    }

    /* access modifiers changed from: protected */
    public void zzz(int i) {
        if (i != -2) {
            this.zzbzf = new zzaej(i, this.zzbzf.zzbsu);
        }
        this.zzbnd.zztz();
        zzabm zzabm = this.zzbzd;
        zzaef zzaef = this.zzbze.zzcgs;
        zzabm.zzb(new zzajh(zzaef.zzccv, this.zzbnd, this.zzbzf.zzbsn, i, this.zzbzf.zzbso, this.zzbzf.zzces, this.zzbzf.orientation, this.zzbzf.zzbsu, zzaef.zzccy, this.zzbzf.zzceq, null, null, null, null, null, this.zzbzf.zzcer, this.zzbze.zzacv, this.zzbzf.zzcep, this.zzbze.zzcoh, this.zzbzf.zzceu, this.zzbzf.zzcev, this.zzbze.zzcob, null, this.zzbzf.zzcfe, this.zzbzf.zzcff, this.zzbzf.zzcfg, this.zzbzf.zzcfh, this.zzbzf.zzcfi, null, this.zzbzf.zzbsr, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, this.zzbze.zzcos.zzcfp, this.zzbzf.zzbsp, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq));
    }
}
