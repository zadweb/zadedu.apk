package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zaai extends zaay {
    private final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks zaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zaai(zaag zaag, zaaw zaaw, BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zaaw);
        this.zaa = connectionProgressReportCallbacks;
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa() {
        this.zaa.onReportServiceBinding(new ConnectionResult(16, null));
    }
}
