package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

/* access modifiers changed from: package-private */
public final class zzack implements ViewTreeObserver.OnGlobalLayoutListener {
    private final /* synthetic */ zzace zzcbi;
    private final /* synthetic */ WeakReference zzcbj;

    zzack(zzace zzace, WeakReference weakReference) {
        this.zzcbi = zzace;
        this.zzcbj = weakReference;
    }

    public final void onGlobalLayout() {
        this.zzcbi.zza((zzace) this.zzcbj, (WeakReference) false);
    }
}
