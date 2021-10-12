package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.FrameLayout;
import java.util.HashMap;

@zzadh
public class zzjr {
    private final Object mLock = new Object();
    private zzld zzari;
    private final zzjh zzarj;
    private final zzjg zzark;
    private final zzme zzarl;
    private final zzrv zzarm;
    private final zzahi zzarn;
    private final zzaao zzaro;
    private final zzrw zzarp;

    /* access modifiers changed from: package-private */
    public abstract class zza<T> {
        zza() {
        }

        /* access modifiers changed from: protected */
        public abstract T zza(zzld zzld) throws RemoteException;

        /* access modifiers changed from: protected */
        public abstract T zzib() throws RemoteException;

        /* access modifiers changed from: protected */
        public final T zzic() {
            zzld zzia = zzjr.this.zzia();
            if (zzia == null) {
                zzane.zzdk("ClientApi class cannot be loaded.");
                return null;
            }
            try {
                return zza(zzia);
            } catch (RemoteException e) {
                zzane.zzc("Cannot invoke local loader using ClientApi class", e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public final T zzid() {
            try {
                return zzib();
            } catch (RemoteException e) {
                zzane.zzc("Cannot invoke remote loader", e);
                return null;
            }
        }
    }

    public zzjr(zzjh zzjh, zzjg zzjg, zzme zzme, zzrv zzrv, zzahi zzahi, zzaao zzaao, zzrw zzrw) {
        this.zzarj = zzjh;
        this.zzark = zzjg;
        this.zzarl = zzme;
        this.zzarm = zzrv;
        this.zzarn = zzahi;
        this.zzaro = zzaao;
        this.zzarp = zzrw;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: com.google.android.gms.internal.ads.zzni */
    /* JADX WARN: Multi-variable type inference failed */
    static <T> T zza(Context context, boolean z, zza<T> zza2) {
        boolean z2 = true;
        if (!z) {
            zzkb.zzif();
            if (!zzamu.zzbe(context)) {
                zzane.zzck("Google Play Services is not available");
                z = true;
            }
        }
        zzkb.zzif();
        int zzbg = zzamu.zzbg(context);
        zzkb.zzif();
        if (zzbg <= zzamu.zzbf(context)) {
            z2 = z;
        }
        zznk.initialize(context);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzber)).booleanValue()) {
            z2 = false;
        }
        if (z2) {
            T zzic = zza2.zzic();
            return zzic == null ? zza2.zzid() : zzic;
        }
        T zzid = zza2.zzid();
        return zzid == null ? zza2.zzic() : zzid;
    }

    /* access modifiers changed from: private */
    public static void zza(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", str);
        zzkb.zzif().zza(context, (String) null, "gmob-apps", bundle, true);
    }

    private static zzld zzhz() {
        try {
            Object newInstance = zzjr.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zzle.asInterface((IBinder) newInstance);
            }
            zzane.zzdk("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Exception e) {
            zzane.zzc("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final zzld zzia() {
        zzld zzld;
        synchronized (this.mLock) {
            if (this.zzari == null) {
                this.zzari = zzhz();
            }
            zzld = this.zzari;
        }
        return zzld;
    }

    public final zzqa zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzqa) zza(context, false, (zza) new zzjx(this, frameLayout, frameLayout2, context));
    }

    public final zzqf zza(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        return (zzqf) zza(view.getContext(), false, (zza) new zzjy(this, view, hashMap, hashMap2));
    }

    public final zzaap zzb(Activity activity) {
        Intent intent = activity.getIntent();
        boolean z = false;
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            zzane.e("useClientJar flag not found in activity intent extras.");
        } else {
            z = intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        }
        return (zzaap) zza(activity, z, new zzka(this, activity));
    }

    public final zzkn zzb(Context context, String str, zzxn zzxn) {
        return (zzkn) zza(context, false, (zza) new zzjv(this, context, str, zzxn));
    }
}
