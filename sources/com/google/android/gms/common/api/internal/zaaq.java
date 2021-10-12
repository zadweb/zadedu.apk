package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.zai;

/* access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class zaaq implements zai {
    private final /* synthetic */ zaar zaa;

    zaaq(zaar zaar) {
        this.zaa = zaar;
    }

    @Override // com.google.android.gms.common.internal.zai
    public final Bundle getConnectionHint() {
        return null;
    }

    @Override // com.google.android.gms.common.internal.zai
    public final boolean isConnected() {
        return this.zaa.isConnected();
    }
}
