package com.google.android.gms.signin;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.SignInClientImpl;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zab extends Api.AbstractClientBuilder<SignInClientImpl, zae> {
    zab() {
    }

    /* Return type fixed from 'com.google.android.gms.common.api.Api$Client' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.content.Context, android.os.Looper, com.google.android.gms.common.internal.ClientSettings, java.lang.Object, com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener] */
    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ SignInClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, zae zae, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new SignInClientImpl(context, looper, false, clientSettings, zae.zaa(), connectionCallbacks, onConnectionFailedListener);
    }
}
