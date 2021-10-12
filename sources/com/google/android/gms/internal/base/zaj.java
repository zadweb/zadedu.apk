package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;

/* access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class zaj extends Drawable.ConstantState {
    int zaa;
    int zab;

    zaj(zaj zaj) {
        if (zaj != null) {
            this.zaa = zaj.zaa;
            this.zab = zaj.zab;
        }
    }

    public final Drawable newDrawable() {
        return new zaf(this);
    }

    public final int getChangingConfigurations() {
        return this.zaa;
    }
}
