package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
@zzadh
public final class zzuf extends zzus<zzwb> implements zzuo, zzuu {
    private final zzasv zzbpj;

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.android.gms.internal.ads.zzuf */
    /* JADX WARN: Multi-variable type inference failed */
    public zzuf(Context context, zzang zzang) throws zzarg {
        try {
            zzasv zzasv = new zzasv(new zzash(context));
            this.zzbpj = zzasv;
            zzasv.setWillNotDraw(true);
            this.zzbpj.zza(new zzug(this));
            this.zzbpj.zza(new zzuh(this));
            this.zzbpj.addJavascriptInterface(new zzun(this), "GoogleJsInterface");
            zzbv.zzek().zza(context, zzang.zzcw, this.zzbpj.getSettings());
        } catch (Throwable th) {
            throw new zzarg("Init failed.", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final void destroy() {
        this.zzbpj.destroy();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzus
    public final /* bridge */ /* synthetic */ zzwb getReference() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final void zza(zzuv zzuv) {
        this.zzbpj.zza(new zzuk(zzuv));
    }

    @Override // com.google.android.gms.internal.ads.zzue
    public final void zza(String str, Map map) {
        zzup.zza(this, str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzue, com.google.android.gms.internal.ads.zzuo
    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final void zzbb(String str) {
        zzbc(String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head></html>", str));
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final void zzbc(String str) {
        zzaoe.zzcvy.execute(new zzui(this, str));
    }

    @Override // com.google.android.gms.internal.ads.zzuu
    public final void zzbd(String str) {
        zzaoe.zzcvy.execute(new zzuj(this, str));
    }

    @Override // com.google.android.gms.internal.ads.zzve, com.google.android.gms.internal.ads.zzuo
    public final void zzbe(String str) {
        zzaoe.zzcvy.execute(new zzul(this, str));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzbf(String str) {
        this.zzbpj.zzbe(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzbg(String str) {
        this.zzbpj.loadUrl(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzbh(String str) {
        this.zzbpj.loadData(str, "text/html", "UTF-8");
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
