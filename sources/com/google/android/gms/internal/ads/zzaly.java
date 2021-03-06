package com.google.android.gms.internal.ads;

import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzaly extends zzav {
    private final /* synthetic */ byte[] zzctk;
    private final /* synthetic */ Map zzctl;
    private final /* synthetic */ zzamy zzctm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaly(zzalt zzalt, int i, String str, zzz zzz, zzy zzy, byte[] bArr, Map map, zzamy zzamy) {
        super(i, str, zzz, zzy);
        this.zzctk = bArr;
        this.zzctl = map;
        this.zzctm = zzamy;
    }

    @Override // com.google.android.gms.internal.ads.zzr
    public final Map<String, String> getHeaders() throws zza {
        Map<String, String> map = this.zzctl;
        return map == null ? super.getHeaders() : map;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzr, com.google.android.gms.internal.ads.zzav
    public final /* synthetic */ void zza(String str) {
        zza(str);
    }

    @Override // com.google.android.gms.internal.ads.zzr
    public final byte[] zzg() throws zza {
        byte[] bArr = this.zzctk;
        return bArr == null ? super.zzg() : bArr;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzav
    public final void zzh(String str) {
        this.zzctm.zzdg(str);
        super.zza(str);
    }
}
