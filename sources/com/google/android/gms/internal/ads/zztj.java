package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztj implements zzts {
    zztj(zzti zzti) {
    }

    @Override // com.google.android.gms.internal.ads.zzts
    public final void zzb(zztt zztt) throws RemoteException {
        if (zztt.zzboh != null) {
            zztt.zzboh.onRewardedVideoAdLoaded();
        }
    }
}
