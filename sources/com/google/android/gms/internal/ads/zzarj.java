package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzarj implements zzv<zzaqw> {
    private final /* synthetic */ zzari zzdec;

    zzarj(zzari zzari) {
        this.zzdec = zzari;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.util.Map] */
    @Override // com.google.android.gms.ads.internal.gmsg.zzv
    public final /* synthetic */ void zza(zzaqw zzaqw, Map map) {
        if (map != null) {
            String str = (String) map.get("height");
            if (!TextUtils.isEmpty(str)) {
                try {
                    int parseInt = Integer.parseInt(str);
                    synchronized (this.zzdec) {
                        if (zzari.zza(this.zzdec) != parseInt) {
                            zzari.zza(this.zzdec, parseInt);
                            this.zzdec.requestLayout();
                        }
                    }
                } catch (Exception e) {
                    zzakb.zzc("Exception occurred while getting webview content height", e);
                }
            }
        }
    }
}
