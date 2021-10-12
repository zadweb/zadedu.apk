package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public final class zzgg {
    private final Object zzaho = new Object();
    private zzgh zzahp = null;
    private boolean zzahq = false;

    public final Activity getActivity() {
        synchronized (this.zzaho) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            }
            if (this.zzahp == null) {
                return null;
            }
            return this.zzahp.getActivity();
        }
    }

    public final Context getContext() {
        synchronized (this.zzaho) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            }
            if (this.zzahp == null) {
                return null;
            }
            return this.zzahp.getContext();
        }
    }

    public final void initialize(Context context) {
        synchronized (this.zzaho) {
            if (!this.zzahq) {
                if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzayg)).booleanValue()) {
                        Application application = null;
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext == null) {
                            applicationContext = context;
                        }
                        if (applicationContext instanceof Application) {
                            application = (Application) applicationContext;
                        }
                        if (application == null) {
                            zzakb.zzdk("Can not cast Context to Application");
                            return;
                        }
                        if (this.zzahp == null) {
                            this.zzahp = new zzgh();
                        }
                        this.zzahp.zza(application, context);
                        this.zzahq = true;
                    }
                }
            }
        }
    }

    public final void zza(zzgj zzgj) {
        synchronized (this.zzaho) {
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzayg)).booleanValue()) {
                    if (this.zzahp == null) {
                        this.zzahp = new zzgh();
                    }
                    this.zzahp.zza(zzgj);
                }
            }
        }
    }
}
