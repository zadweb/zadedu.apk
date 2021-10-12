package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzjr;

/* access modifiers changed from: package-private */
public final class zzjw extends zzjr.zza<zzlj> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzjr zzart;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjw(zzjr zzjr, Context context) {
        super();
        this.zzart = zzjr;
        this.val$context = context;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzjr.zza
    public final /* synthetic */ zzlj zza(zzld zzld) throws RemoteException {
        return zzld.getMobileAdsSettingsManagerWithClientJarVersion(ObjectWrapper.wrap(this.val$context), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzjr.zza
    public final /* synthetic */ zzlj zzib() throws RemoteException {
        zzlj zzg = this.zzart.zzarl.zzg(this.val$context);
        if (zzg != null) {
            return zzg;
        }
        zzjr zzjr = this.zzart;
        zzjr.zza(this.val$context, "mobile_ads_settings");
        return new zzml();
    }
}
