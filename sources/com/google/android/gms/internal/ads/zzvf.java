package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Executor;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public final class zzvf {
    private final Context mContext;
    private final Object mLock;
    private final String zzbpx;
    private zzalo<zzuu> zzbpy;
    private zzalo<zzuu> zzbpz;
    private zzvw zzbqa;
    private int zzbqb;
    private final zzang zzyf;

    public zzvf(Context context, zzang zzang, String str) {
        this.mLock = new Object();
        this.zzbqb = 1;
        this.zzbpx = str;
        this.mContext = context.getApplicationContext();
        this.zzyf = zzang;
        this.zzbpy = new zzvr();
        this.zzbpz = new zzvr();
    }

    public zzvf(Context context, zzang zzang, String str, zzalo<zzuu> zzalo, zzalo<zzuu> zzalo2) {
        this(context, zzang, str);
        this.zzbpy = zzalo;
        this.zzbpz = zzalo2;
    }

    /* access modifiers changed from: protected */
    public final zzvw zza(zzci zzci) {
        zzvw zzvw = new zzvw(this.zzbpz);
        zzaoe.zzcvy.execute(new zzvg(this, zzci, zzvw));
        zzvw.zza(new zzvo(this, zzvw), new zzvp(this, zzvw));
        return zzvw;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzci zzci, zzvw zzvw) {
        try {
            Context context = this.mContext;
            zzang zzang = this.zzyf;
            zzuu zzuf = ((Boolean) zzkb.zzik().zzd(zznk.zzaxz)).booleanValue() ? new zzuf(context, zzang) : new zzuw(context, zzang, zzci, null);
            zzuf.zza(new zzvh(this, zzvw, zzuf));
            zzuf.zza("/jsLoaded", new zzvk(this, zzvw, zzuf));
            zzamk zzamk = new zzamk();
            zzvl zzvl = new zzvl(this, zzci, zzuf, zzamk);
            zzamk.set(zzvl);
            zzuf.zza("/requestReload", zzvl);
            if (this.zzbpx.endsWith(".js")) {
                zzuf.zzbb(this.zzbpx);
            } else if (this.zzbpx.startsWith("<html>")) {
                zzuf.zzbc(this.zzbpx);
            } else {
                zzuf.zzbd(this.zzbpx);
            }
            zzakk.zzcrm.postDelayed(new zzvm(this, zzvw, zzuf), (long) zzvq.zzbqo);
        } catch (Throwable th) {
            zzakb.zzb("Error creating webview.", th);
            zzbv.zzeo().zza(th, "SdkJavascriptFactory.loadJavascriptEngine");
            zzvw.reject();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzvw zzvw, zzuu zzuu) {
        synchronized (this.mLock) {
            if (zzvw.getStatus() != -1) {
                if (zzvw.getStatus() != 1) {
                    zzvw.reject();
                    Executor executor = zzaoe.zzcvy;
                    zzuu.getClass();
                    executor.execute(zzvj.zza(zzuu));
                    zzakb.v("Could not receive loaded message in a timely manner. Rejecting.");
                }
            }
        }
    }

    public final zzvs zzb(zzci zzci) {
        synchronized (this.mLock) {
            if (this.zzbqa != null) {
                if (this.zzbqa.getStatus() != -1) {
                    if (this.zzbqb == 0) {
                        return this.zzbqa.zzlz();
                    } else if (this.zzbqb == 1) {
                        this.zzbqb = 2;
                        zza((zzci) null);
                        return this.zzbqa.zzlz();
                    } else if (this.zzbqb == 2) {
                        return this.zzbqa.zzlz();
                    } else {
                        return this.zzbqa.zzlz();
                    }
                }
            }
            this.zzbqb = 2;
            zzvw zza = zza((zzci) null);
            this.zzbqa = zza;
            return zza.zzlz();
        }
    }
}
