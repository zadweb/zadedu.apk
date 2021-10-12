package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
public final class zzalx implements zzy {
    private final /* synthetic */ String zzcce;
    private final /* synthetic */ zzama zzctj;

    zzalx(zzalt zzalt, String str, zzama zzama) {
        this.zzcce = str;
        this.zzctj = zzama;
    }

    @Override // com.google.android.gms.internal.ads.zzy
    public final void zzd(zzae zzae) {
        String str = this.zzcce;
        String zzae2 = zzae.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(zzae2).length());
        sb.append("Failed to load URL: ");
        sb.append(str);
        sb.append("\n");
        sb.append(zzae2);
        zzakb.zzdk(sb.toString());
        this.zzctj.zzb(null);
    }
}
