package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnFailureListener;

/* access modifiers changed from: package-private */
public final /* synthetic */ class g implements OnFailureListener {

    /* renamed from: a  reason: collision with root package name */
    static final OnFailureListener f133a = new g();

    private g() {
    }

    @Override // com.google.android.play.core.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        i.f134a.e(String.format("Could not sync active asset packs. %s", exc), new Object[0]);
    }
}
