package com.google.android.gms.internal.ads;

import android.content.Context;

@zzadh
public final class zzaec extends zzadz {
    private final Context mContext;

    public zzaec(Context context, zzaol<zzaef> zzaol, zzadx zzadx) {
        super(zzaol, zzadx);
        this.mContext = context;
    }

    @Override // com.google.android.gms.internal.ads.zzadz
    public final void zznz() {
    }

    @Override // com.google.android.gms.internal.ads.zzadz
    public final zzaen zzoa() {
        Context context = this.mContext;
        return zzafn.zza(context, zzafm.zzm(context));
    }
}
