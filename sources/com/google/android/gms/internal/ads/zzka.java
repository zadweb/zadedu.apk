package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzjr;

/* access modifiers changed from: package-private */
public final class zzka extends zzjr.zza<zzaap> {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zzjr zzart;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzka(zzjr zzjr, Activity activity) {
        super();
        this.zzart = zzjr;
        this.val$activity = activity;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzjr.zza
    public final /* synthetic */ zzaap zza(zzld zzld) throws RemoteException {
        return zzld.createAdOverlay(ObjectWrapper.wrap(this.val$activity));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzjr.zza
    public final /* synthetic */ zzaap zzib() throws RemoteException {
        zzaap zze = this.zzart.zzaro.zze(this.val$activity);
        if (zze != null) {
            return zze;
        }
        zzjr zzjr = this.zzart;
        zzjr.zza(this.val$activity, "ad_overlay");
        return null;
    }
}
