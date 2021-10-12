package com.google.android.gms.common.api.internal;

/* access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class zacm implements zaco {
    private final /* synthetic */ zacn zaa;

    zacm(zacn zacn) {
        this.zaa = zacn;
    }

    @Override // com.google.android.gms.common.api.internal.zaco
    public final void zaa(BasePendingResult<?> basePendingResult) {
        this.zaa.zab.remove(basePendingResult);
    }
}
