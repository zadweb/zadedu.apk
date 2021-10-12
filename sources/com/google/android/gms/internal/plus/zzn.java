package com.google.android.gms.internal.plus;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.internal.zzh;
import java.util.Collection;

final class zzn extends zzp {
    private final /* synthetic */ Collection zzal;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzn(zzj zzj, GoogleApiClient googleApiClient, Collection collection) {
        super(googleApiClient, null);
        this.zzal = collection;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.common.api.Api$AnyClient] */
    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzh zzh) throws RemoteException {
        zzh.zza(this, this.zzal);
    }
}
