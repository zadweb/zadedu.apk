package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzhu;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzadk extends zzajx implements zzadx {
    private final Context mContext;
    private zzwy zzbtj;
    private zzaef zzbuc;
    private zzaej zzbzf;
    private Runnable zzbzg;
    private final Object zzbzh = new Object();
    private final zzadj zzccf;
    private final zzaeg zzccg;
    private final zzhs zzcch;
    private final zzhx zzcci;
    zzalc zzccj;

    public zzadk(Context context, zzaeg zzaeg, zzadj zzadj, zzhx zzhx) {
        zzhs zzhs;
        zzht zzht;
        this.zzccf = zzadj;
        this.mContext = context;
        this.zzccg = zzaeg;
        this.zzcci = zzhx;
        zzhs zzhs2 = new zzhs(zzhx);
        this.zzcch = zzhs2;
        zzhs2.zza(new zzadl(this));
        zzit zzit = new zzit();
        zzit.zzaot = Integer.valueOf(this.zzccg.zzacr.zzcve);
        zzit.zzaou = Integer.valueOf(this.zzccg.zzacr.zzcvf);
        zzit.zzaov = Integer.valueOf(this.zzccg.zzacr.zzcvg ? 0 : 2);
        this.zzcch.zza(new zzadm(zzit));
        if (this.zzccg.zzccw != null) {
            this.zzcch.zza(new zzadn(this));
        }
        zzjn zzjn = this.zzccg.zzacv;
        if (zzjn.zzarc && "interstitial_mb".equals(zzjn.zzarb)) {
            zzhs = this.zzcch;
            zzht = zzado.zzccm;
        } else if (zzjn.zzarc && "reward_mb".equals(zzjn.zzarb)) {
            zzhs = this.zzcch;
            zzht = zzadp.zzccm;
        } else if (zzjn.zzare || zzjn.zzarc) {
            zzhs = this.zzcch;
            zzht = zzadr.zzccm;
        } else {
            zzhs = this.zzcch;
            zzht = zzadq.zzccm;
        }
        zzhs.zza(zzht);
        this.zzcch.zza(zzhu.zza.zzb.AD_REQUEST);
    }

    private final zzjn zza(zzaef zzaef) throws zzadu {
        zzwy zzwy;
        zzaef zzaef2 = this.zzbuc;
        if (!(!((zzaef2 == null || zzaef2.zzadn == null || this.zzbuc.zzadn.size() <= 1) ? false : true) || (zzwy = this.zzbtj) == null || zzwy.zzbte)) {
            return null;
        }
        if (this.zzbzf.zzarf) {
            zzjn[] zzjnArr = zzaef.zzacv.zzard;
            for (zzjn zzjn : zzjnArr) {
                if (zzjn.zzarf) {
                    return new zzjn(zzjn, zzaef.zzacv.zzard);
                }
            }
        }
        if (this.zzbzf.zzcet != null) {
            String[] split = this.zzbzf.zzcet.split(AvidJSONUtil.KEY_X);
            if (split.length != 2) {
                String valueOf = String.valueOf(this.zzbzf.zzcet);
                throw new zzadu(valueOf.length() != 0 ? "Invalid ad size format from the ad response: ".concat(valueOf) : new String("Invalid ad size format from the ad response: "), 0);
            }
            try {
                int parseInt = Integer.parseInt(split[0]);
                int parseInt2 = Integer.parseInt(split[1]);
                zzjn[] zzjnArr2 = zzaef.zzacv.zzard;
                for (zzjn zzjn2 : zzjnArr2) {
                    float f = this.mContext.getResources().getDisplayMetrics().density;
                    int i = zzjn2.width == -1 ? (int) (((float) zzjn2.widthPixels) / f) : zzjn2.width;
                    int i2 = zzjn2.height == -2 ? (int) (((float) zzjn2.heightPixels) / f) : zzjn2.height;
                    if (parseInt == i && parseInt2 == i2 && !zzjn2.zzarf) {
                        return new zzjn(zzjn2, zzaef.zzacv.zzard);
                    }
                }
                String valueOf2 = String.valueOf(this.zzbzf.zzcet);
                throw new zzadu(valueOf2.length() != 0 ? "The ad size from the ad response was not one of the requested sizes: ".concat(valueOf2) : new String("The ad size from the ad response was not one of the requested sizes: "), 0);
            } catch (NumberFormatException unused) {
                String valueOf3 = String.valueOf(this.zzbzf.zzcet);
                throw new zzadu(valueOf3.length() != 0 ? "Invalid ad size number from the ad response: ".concat(valueOf3) : new String("Invalid ad size number from the ad response: "), 0);
            }
        } else {
            throw new zzadu("The ad response must specify one of the supported ad sizes.", 0);
        }
    }

    /* access modifiers changed from: private */
    public final void zzc(int i, String str) {
        if (i == 3 || i == -1) {
            zzakb.zzdj(str);
        } else {
            zzakb.zzdk(str);
        }
        zzaej zzaej = this.zzbzf;
        if (zzaej == null) {
            this.zzbzf = new zzaej(i);
        } else {
            this.zzbzf = new zzaej(i, zzaej.zzbsu);
        }
        zzaef zzaef = this.zzbuc;
        if (zzaef == null) {
            zzaef = new zzaef(this.zzccg, -1, null, null, null);
        }
        zzaej zzaej2 = this.zzbzf;
        this.zzccf.zza(new zzaji(zzaef, zzaej2, this.zzbtj, null, i, -1, zzaej2.zzceu, null, this.zzcch, null));
    }

    @Override // com.google.android.gms.internal.ads.zzajx
    public final void onStop() {
        synchronized (this.zzbzh) {
            if (this.zzccj != null) {
                this.zzccj.cancel();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final zzalc zza(zzang zzang, zzaol<zzaef> zzaol) {
        Context context = this.mContext;
        if (new zzadw(context).zza(zzang)) {
            zzakb.zzck("Fetching ad response from local ad request service.");
            zzaec zzaec = new zzaec(context, zzaol, this);
            zzaec.zznt();
            return zzaec;
        }
        zzakb.zzck("Fetching ad response from remote ad request service.");
        zzkb.zzif();
        if (zzamu.zzbe(context)) {
            return new zzaed(context, zzang, zzaol, this);
        }
        zzakb.zzdk("Failed to connect to remote ad request service.");
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01eb  */
    @Override // com.google.android.gms.internal.ads.zzadx
    public final void zza(zzaej zzaej) {
        boolean z;
        JSONObject jSONObject;
        Bundle bundle;
        zzakb.zzck("Received ad response.");
        this.zzbzf = zzaej;
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        synchronized (this.zzbzh) {
            z = null;
            this.zzccj = null;
        }
        zzbv.zzeo().zzqh().zzae(this.zzbzf.zzcdr);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayy)).booleanValue()) {
            if (this.zzbzf.zzced) {
                zzbv.zzeo().zzqh().zzcp(this.zzbuc.zzacp);
            } else {
                zzbv.zzeo().zzqh().zzcq(this.zzbuc.zzacp);
            }
        }
        try {
            if (this.zzbzf.errorCode != -2) {
                if (this.zzbzf.errorCode != -3) {
                    int i = this.zzbzf.errorCode;
                    StringBuilder sb = new StringBuilder(66);
                    sb.append("There was a problem getting an ad response. ErrorCode: ");
                    sb.append(i);
                    throw new zzadu(sb.toString(), this.zzbzf.errorCode);
                }
            }
            if (this.zzbzf.errorCode != -3) {
                if (!TextUtils.isEmpty(this.zzbzf.zzceo)) {
                    zzbv.zzeo().zzqh().zzab(this.zzbzf.zzcdd);
                    if (this.zzbzf.zzceq) {
                        try {
                            this.zzbtj = new zzwy(this.zzbzf.zzceo);
                            zzbv.zzeo().zzaa(this.zzbtj.zzbss);
                        } catch (JSONException e) {
                            zzakb.zzb("Could not parse mediation config.", e);
                            String valueOf = String.valueOf(this.zzbzf.zzceo);
                            throw new zzadu(valueOf.length() != 0 ? "Could not parse mediation config: ".concat(valueOf) : new String("Could not parse mediation config: "), 0);
                        }
                    } else {
                        zzbv.zzeo().zzaa(this.zzbzf.zzbss);
                    }
                    if (!TextUtils.isEmpty(this.zzbzf.zzcds)) {
                        if (((Boolean) zzkb.zzik().zzd(zznk.zzbdj)).booleanValue()) {
                            zzakb.zzck("Received cookie from server. Setting webview cookie in CookieManager.");
                            CookieManager zzax = zzbv.zzem().zzax(this.mContext);
                            if (zzax != null) {
                                zzax.setCookie("googleads.g.doubleclick.net", this.zzbzf.zzcds);
                            }
                        }
                    }
                } else {
                    throw new zzadu("No fill from ad server.", 3);
                }
            }
            zzjn zza = this.zzbuc.zzacv.zzard != null ? zza(this.zzbuc) : null;
            zzbv.zzeo().zzqh().zzac(this.zzbzf.zzcfa);
            zzbv.zzeo().zzqh().zzad(this.zzbzf.zzcfm);
            if (!TextUtils.isEmpty(this.zzbzf.zzcey)) {
                try {
                    jSONObject = new JSONObject(this.zzbzf.zzcey);
                } catch (Exception e2) {
                    zzakb.zzb("Error parsing the JSON for Active View.", e2);
                }
                if (this.zzbzf.zzcfo == 2) {
                    z = true;
                    zzjj zzjj = this.zzbuc.zzccv;
                    Bundle bundle2 = zzjj.zzaqg != null ? zzjj.zzaqg : new Bundle();
                    if (bundle2.getBundle(AdMobAdapter.class.getName()) != null) {
                        bundle = bundle2.getBundle(AdMobAdapter.class.getName());
                    } else {
                        Bundle bundle3 = new Bundle();
                        bundle2.putBundle(AdMobAdapter.class.getName(), bundle3);
                        bundle = bundle3;
                    }
                    bundle.putBoolean("render_test_label", true);
                }
                if (this.zzbzf.zzcfo == 1) {
                    z = false;
                }
                Boolean valueOf2 = this.zzbzf.zzcfo != 0 ? Boolean.valueOf(zzamm.zzo(this.zzbuc.zzccv)) : z;
                zzaef zzaef = this.zzbuc;
                zzaej zzaej2 = this.zzbzf;
                this.zzccf.zza(new zzaji(zzaef, zzaej2, this.zzbtj, zza, -2, elapsedRealtime, zzaej2.zzceu, jSONObject, this.zzcch, valueOf2));
                zzakk.zzcrm.removeCallbacks(this.zzbzg);
            }
            jSONObject = null;
            if (this.zzbzf.zzcfo == 2) {
            }
            if (this.zzbzf.zzcfo == 1) {
            }
            if (this.zzbzf.zzcfo != 0) {
            }
            zzaef zzaef2 = this.zzbuc;
            zzaej zzaej22 = this.zzbzf;
            this.zzccf.zza(new zzaji(zzaef2, zzaej22, this.zzbtj, zza, -2, elapsedRealtime, zzaej22.zzceu, jSONObject, this.zzcch, valueOf2));
        } catch (zzadu e3) {
            zzc(e3.getErrorCode(), e3.getMessage());
        }
        zzakk.zzcrm.removeCallbacks(this.zzbzg);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzii zzii) {
        zzii.zzanm.zzamu = this.zzccg.zzccw.packageName;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzii zzii) {
        zzii.zzanh = this.zzccg.zzcdi;
    }

    @Override // com.google.android.gms.internal.ads.zzajx
    public final void zzdn() {
        String string;
        zzakb.zzck("AdLoaderBackgroundTask started.");
        this.zzbzg = new zzads(this);
        zzakk.zzcrm.postDelayed(this.zzbzg, ((Long) zzkb.zzik().zzd(zznk.zzban)).longValue());
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbak)).booleanValue() || this.zzccg.zzccv.extras == null || (string = this.zzccg.zzccv.extras.getString("_ad")) == null) {
            zzaop zzaop = new zzaop();
            zzaki.zzb(new zzadt(this, zzaop));
            String zzz = zzbv.zzfh().zzz(this.mContext);
            String zzaa = zzbv.zzfh().zzaa(this.mContext);
            String zzab = zzbv.zzfh().zzab(this.mContext);
            zzbv.zzfh().zzg(this.mContext, zzab);
            zzaef zzaef = new zzaef(this.zzccg, elapsedRealtime, zzz, zzaa, zzab);
            this.zzbuc = zzaef;
            zzaop.zzk(zzaef);
            return;
        }
        zzaef zzaef2 = new zzaef(this.zzccg, elapsedRealtime, null, null, null);
        this.zzbuc = zzaef2;
        zza(zzafs.zza(this.mContext, zzaef2, string));
    }
}
