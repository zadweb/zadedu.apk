package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

/* access modifiers changed from: package-private */
public final class zzaaa implements DialogInterface.OnClickListener {
    private final /* synthetic */ zzzy zzbvx;

    zzaaa(zzzy zzzy) {
        this.zzbvx = zzzy;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzbvx.zzbw("Operation denied by user.");
    }
}
