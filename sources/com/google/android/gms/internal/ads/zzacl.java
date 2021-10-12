package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

/* access modifiers changed from: package-private */
public final class zzacl implements ViewTreeObserver.OnScrollChangedListener {
    private final /* synthetic */ zzace zzcbi;
    private final /* synthetic */ WeakReference zzcbj;

    zzacl(zzace zzace, WeakReference weakReference) {
        this.zzcbi = zzace;
        this.zzcbj = weakReference;
    }

    public final void onScrollChanged() {
        this.zzcbi.zza((zzace) this.zzcbj, (WeakReference) true);
    }
}
