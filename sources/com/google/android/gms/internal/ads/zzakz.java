package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewGroup;

public class zzakz extends zzakx {
    @Override // com.google.android.gms.internal.ads.zzakq, com.google.android.gms.internal.ads.zzakx
    public final boolean isAttachedToWindow(View view) {
        return view.isAttachedToWindow();
    }

    @Override // com.google.android.gms.internal.ads.zzakq
    public final ViewGroup.LayoutParams zzro() {
        return new ViewGroup.LayoutParams(-1, -1);
    }
}
