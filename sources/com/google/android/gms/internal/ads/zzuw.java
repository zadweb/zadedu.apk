package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
@zzadh
public final class zzuw implements zzuo, zzuu {
    private final Context mContext;
    private final zzaqw zzbnd;

    public zzuw(Context context, zzang zzang, zzci zzci, zzw zzw) throws zzarg {
        this.mContext = context;
        zzbv.zzel();
        zzaqw zza = zzarc.zza(context, zzasi.zzvq(), "", false, false, zzci, zzang, null, null, null, zzhs.zzhm());
        this.zzbnd = zza;
        zza.getView().setWillNotDraw(true);
    }

    private static void runOnUiThread(Runnable runnable) {
        zzkb.zzif();
        if (zzamu.zzsh()) {
            runnable.run();
        } else {
            zzakk.zzcrm.post(runnable);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final void destroy() {
        this.zzbnd.destroy();
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final void zza(zzuv zzuv) {
        zzasc zzuf = this.zzbnd.zzuf();
        zzuv.getClass();
        zzuf.zza(zzuz.zzb(zzuv));
    }

    @Override // com.google.android.gms.internal.ads.zzwb
    public final void zza(String str, zzv<? super zzwb> zzv) {
        this.zzbnd.zza(str, new zzvd(this, zzv));
    }

    @Override // com.google.android.gms.internal.ads.zzue
    public final void zza(String str, Map map) {
        zzup.zza(this, str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzue, com.google.android.gms.internal.ads.zzuo
    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzwb
    public final void zzb(String str, zzv<? super zzwb> zzv) {
        this.zzbnd.zza(str, new zzuy(zzv));
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final void zzbb(String str) {
        runOnUiThread(new zzva(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", str)));
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final void zzbc(String str) {
        runOnUiThread(new zzvb(this, str));
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final void zzbd(String str) {
        runOnUiThread(new zzvc(this, str));
    }

    @Override // com.google.android.gms.internal.ads.zzve, com.google.android.gms.internal.ads.zzuo
    public final void zzbe(String str) {
        runOnUiThread(new zzux(this, str));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzbi(String str) {
        this.zzbnd.zzbe(str);
    }

    @Override // com.google.android.gms.internal.ads.zzuo
    public final void zzf(String str, String str2) {
        zzup.zza(this, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final zzwc zzlw() {
        return new zzwd(this);
    }
}
