package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzjr;
import java.util.HashMap;

/* access modifiers changed from: package-private */
public final class zzjy extends zzjr.zza<zzqf> {
    private final /* synthetic */ zzjr zzart;
    private final /* synthetic */ View zzarw;
    private final /* synthetic */ HashMap zzarx;
    private final /* synthetic */ HashMap zzary;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjy(zzjr zzjr, View view, HashMap hashMap, HashMap hashMap2) {
        super();
        this.zzart = zzjr;
        this.zzarw = view;
        this.zzarx = hashMap;
        this.zzary = hashMap2;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzjr.zza
    public final /* synthetic */ zzqf zza(zzld zzld) throws RemoteException {
        return zzld.createNativeAdViewHolderDelegate(ObjectWrapper.wrap(this.zzarw), ObjectWrapper.wrap(this.zzarx), ObjectWrapper.wrap(this.zzary));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.ads.zzjr.zza
    public final /* synthetic */ zzqf zzib() throws RemoteException {
        zzqf zzb = this.zzart.zzarp.zzb(this.zzarw, this.zzarx, this.zzary);
        if (zzb != null) {
            return zzb;
        }
        zzjr zzjr = this.zzart;
        zzjr.zza(this.zzarw.getContext(), "native_ad_view_holder_delegate");
        return new zzmn();
    }
}
