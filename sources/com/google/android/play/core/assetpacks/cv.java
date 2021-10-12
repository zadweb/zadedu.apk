package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.play.core.internal.ag;

/* access modifiers changed from: package-private */
public final class cv {

    /* renamed from: a  reason: collision with root package name */
    private static final ag f121a = new ag("PackageStateCache");
    private final Context b;
    private int c = -1;

    cv(Context context) {
        this.b = context;
    }

    public final synchronized int a() {
        if (this.c == -1) {
            try {
                this.c = this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
                f121a.b("The current version of the app could not be retrieved", new Object[0]);
            }
        }
        return this.c;
    }
}
