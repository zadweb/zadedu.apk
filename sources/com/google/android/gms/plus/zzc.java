package com.google.android.gms.plus;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.ScopeUtil;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.zzh;
import com.google.android.gms.plus.internal.zzn;

final class zzc extends Api.AbstractClientBuilder<zzh, Plus.PlusOptions> {
    zzc() {
    }

    /* Return type fixed from 'com.google.android.gms.common.api.Api$Client' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.content.Context, android.os.Looper, com.google.android.gms.common.internal.ClientSettings, java.lang.Object, com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener] */
    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ zzh buildClient(Context context, Looper looper, ClientSettings clientSettings, Plus.PlusOptions plusOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Plus.PlusOptions plusOptions2 = plusOptions;
        if (plusOptions2 == null) {
            plusOptions2 = new Plus.PlusOptions((zzc) null);
        }
        return new zzh(context, looper, clientSettings, new zzn(clientSettings.getAccountOrDefault().name, ScopeUtil.toScopeString(clientSettings.getAllRequestedScopes()), (String[]) plusOptions2.zzh.toArray(new String[0]), new String[0], context.getPackageName(), context.getPackageName(), null, new PlusCommonExtras()), connectionCallbacks, onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.Api.BaseClientBuilder
    public final int getPriority() {
        return 2;
    }
}
