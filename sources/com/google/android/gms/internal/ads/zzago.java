package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzbw;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Map;

@zzadh
public final class zzago {
    private static final zzxm zzcku = new zzxm();
    private final zzxn zzckv;
    private final zzbw zzckw;
    private final Map<String, zzaib> zzckx = new HashMap();
    private final zzahu zzcky;
    private final zzb zzckz;
    private final zzabm zzcla;

    public zzago(zzbw zzbw, zzxn zzxn, zzahu zzahu, zzb zzb, zzabm zzabm) {
        this.zzckw = zzbw;
        this.zzckv = zzxn;
        this.zzcky = zzahu;
        this.zzckz = zzb;
        this.zzcla = zzabm;
    }

    public static boolean zza(zzajh zzajh, zzajh zzajh2) {
        return true;
    }

    public final void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib zzaib = this.zzckx.get(str);
                if (!(zzaib == null || zzaib.zzpe() == null)) {
                    zzaib.zzpe().destroy();
                }
            } catch (RemoteException e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onContextChanged(Context context) {
        for (zzaib zzaib : this.zzckx.values()) {
            try {
                zzaib.zzpe().zzi(ObjectWrapper.wrap(context));
            } catch (RemoteException e) {
                zzakb.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public final void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib zzaib = this.zzckx.get(str);
                if (!(zzaib == null || zzaib.zzpe() == null)) {
                    zzaib.zzpe().pause();
                }
            } catch (RemoteException e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        for (String str : this.zzckx.keySet()) {
            try {
                zzaib zzaib = this.zzckx.get(str);
                if (!(zzaib == null || zzaib.zzpe() == null)) {
                    zzaib.zzpe().resume();
                }
            } catch (RemoteException e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
    public final zzaib zzca(String str) {
        Exception e;
        zzaib zzaib = this.zzckx.get(str);
        if (zzaib != null) {
            return zzaib;
        }
        try {
            zzxn zzxn = this.zzckv;
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                zzxn = zzcku;
            }
            zzaib zzaib2 = new zzaib(zzxn.zzbm(str), this.zzcky);
            try {
                this.zzckx.put(str, zzaib2);
                return zzaib2;
            } catch (Exception e2) {
                e = e2;
                zzaib = zzaib2;
                String valueOf = String.valueOf(str);
                zzakb.zzc(valueOf.length() == 0 ? "Fail to instantiate adapter ".concat(valueOf) : new String("Fail to instantiate adapter "), e);
                return zzaib;
            }
        } catch (Exception e3) {
            e = e3;
            String valueOf2 = String.valueOf(str);
            zzakb.zzc(valueOf2.length() == 0 ? "Fail to instantiate adapter ".concat(valueOf2) : new String("Fail to instantiate adapter "), e);
            return zzaib;
        }
    }

    public final zzaig zzd(zzaig zzaig) {
        if (!(this.zzckw.zzacw == null || this.zzckw.zzacw.zzcod == null || TextUtils.isEmpty(this.zzckw.zzacw.zzcod.zzbsv))) {
            zzaig = new zzaig(this.zzckw.zzacw.zzcod.zzbsv, this.zzckw.zzacw.zzcod.zzbsw);
        }
        if (!(this.zzckw.zzacw == null || this.zzckw.zzacw.zzbtw == null)) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw.zzbtw.zzbsd, this.zzckw.zzadr, zzaig);
        }
        return zzaig;
    }

    public final zzb zzos() {
        return this.zzckz;
    }

    public final zzabm zzot() {
        return this.zzcla;
    }

    public final void zzou() {
        this.zzckw.zzadv = 0;
        zzbw zzbw = this.zzckw;
        zzbv.zzej();
        zzahx zzahx = new zzahx(this.zzckw.zzrt, this.zzckw.zzacx, this);
        String valueOf = String.valueOf(zzahx.getClass().getName());
        zzakb.zzck(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
        zzahx.zznt();
        zzbw.zzacu = zzahx;
    }

    public final void zzov() {
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw, this.zzckw.zzacp, false, this.zzckw.zzacw.zzbtw.zzbsc);
        }
    }

    public final void zzow() {
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw, this.zzckw.zzacp, false, this.zzckw.zzacw.zzbtw.zzbse);
        }
    }

    public final void zzw(boolean z) {
        zzaib zzca = zzca(this.zzckw.zzacw.zzbty);
        if (zzca != null && zzca.zzpe() != null) {
            try {
                zzca.zzpe().setImmersiveMode(z);
                zzca.zzpe().showVideo();
            } catch (RemoteException e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }
}
