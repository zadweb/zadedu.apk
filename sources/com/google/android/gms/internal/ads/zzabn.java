package com.google.android.gms.internal.ads;

import android.content.Context;

@zzadh
public class zzabn extends zzabf {
    zzabn(Context context, zzaji zzaji, zzaqw zzaqw, zzabm zzabm) {
        super(context, zzaji, zzaqw, zzabm);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzabf
    public final void zzns() {
        if (this.zzbzf.errorCode == -2) {
            this.zzbnd.zzuf().zza(this);
            zznu();
            zzakb.zzck("Loading HTML in WebView.");
            this.zzbnd.zzc(this.zzbzf.zzbyq, this.zzbzf.zzceo, null);
        }
    }

    /* access modifiers changed from: protected */
    public void zznu() {
    }
}
