package com.google.android.play.core.assetpacks;

import android.os.Bundle;

/* access modifiers changed from: package-private */
public final /* synthetic */ class ap implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final ar f70a;
    private final Bundle b;
    private final AssetPackState c;

    ap(ar arVar, Bundle bundle, AssetPackState assetPackState) {
        this.f70a = arVar;
        this.b = bundle;
        this.c = assetPackState;
    }

    public final void run() {
        this.f70a.d(this.b, this.c);
    }
}
