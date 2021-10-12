package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzamb extends zzr<zzp> {
    private final zzaoj<zzp> zzctn;
    private final Map<String, String> zzcto;
    private final zzamy zzctp;

    public zzamb(String str, zzaoj<zzp> zzaoj) {
        this(str, null, zzaoj);
    }

    private zzamb(String str, Map<String, String> map, zzaoj<zzp> zzaoj) {
        super(0, str, new zzamc(zzaoj));
        this.zzcto = null;
        this.zzctn = zzaoj;
        zzamy zzamy = new zzamy();
        this.zzctp = zzamy;
        zzamy.zza(str, "GET", null, null);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzr
    public final zzx<zzp> zza(zzp zzp) {
        return zzx.zza(zzp, zzap.zzb(zzp));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzr
    public final /* synthetic */ void zza(zzp zzp) {
        zzp zzp2 = zzp;
        this.zzctp.zza(zzp2.zzab, zzp2.statusCode);
        zzamy zzamy = this.zzctp;
        byte[] bArr = zzp2.data;
        if (zzamy.isEnabled() && bArr != null) {
            zzamy.zzf(bArr);
        }
        this.zzctn.set(zzp2);
    }
}
