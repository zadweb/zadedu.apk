package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzaiz implements Callable {
    private final zzaiy zzcnx;
    private final Context zzcny;

    zzaiz(zzaiy zzaiy, Context context) {
        this.zzcnx = zzaiy;
        this.zzcny = context;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return this.zzcnx.zzad(this.zzcny);
    }
}
