package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public final class zzann implements zzanl {
    private final /* synthetic */ String zzcvi;

    zzann(String str) {
        this.zzcvi = str;
    }

    @Override // com.google.android.gms.internal.ads.zzanl
    public final void zzb(Throwable th) {
        zzbv.zzeo().zza(th, this.zzcvi);
    }

    @Override // com.google.android.gms.internal.ads.zzanl
    public final void zzh(@Nullable Object obj) {
    }
}
