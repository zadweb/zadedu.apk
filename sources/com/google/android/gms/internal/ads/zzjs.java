package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzjr;

/* access modifiers changed from: package-private */
public final class zzjs extends zzjr.zza<zzks> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzjn zzarq;
    private final /* synthetic */ String zzarr;
    private final /* synthetic */ zzxn zzars;
    private final /* synthetic */ zzjr zzart;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjs(zzjr zzjr, Context context, zzjn zzjn, String str, zzxn zzxn) {
        super();
        this.zzart = zzjr;
        this.val$context = context;
        this.zzarq = zzjn;
        this.zzarr = str;
        this.zzars = zzxn;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzjr.zza
    public final /* synthetic */ zzks zza(zzld zzld) throws RemoteException {
        return zzld.createBannerAdManager(ObjectWrapper.wrap(this.val$context), this.zzarq, this.zzarr, this.zzars, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzjr.zza
    public final /* synthetic */ zzks zzib() throws RemoteException {
        zzks zza = this.zzart.zzarj.zza(this.val$context, this.zzarq, this.zzarr, this.zzars, 1);
        if (zza != null) {
            return zza;
        }
        zzjr zzjr = this.zzart;
        zzjr.zza(this.val$context, "banner");
        return new zzmj();
    }
}