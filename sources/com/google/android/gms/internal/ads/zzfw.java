package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;

final class zzfw implements zzgc {
    private final /* synthetic */ Activity val$activity;

    zzfw(zzfu zzfu, Activity activity) {
        this.val$activity = activity;
    }

    @Override // com.google.android.gms.internal.ads.zzgc
    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityStarted(this.val$activity);
    }
}
