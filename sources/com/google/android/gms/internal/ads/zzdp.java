package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdp extends zzei {
    public zzdp(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 5);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzei
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdf = -1L;
        this.zztq.zzdg = -1L;
        int[] iArr = (int[]) this.zztz.invoke(null, this.zzps.getContext());
        synchronized (this.zztq) {
            this.zztq.zzdf = Long.valueOf((long) iArr[0]);
            this.zztq.zzdg = Long.valueOf((long) iArr[1]);
            if (iArr[2] != Integer.MIN_VALUE) {
                this.zztq.zzex = Long.valueOf((long) iArr[2]);
            }
        }
    }
}
