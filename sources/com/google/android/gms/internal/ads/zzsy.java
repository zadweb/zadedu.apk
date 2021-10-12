package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzsy implements zzts {
    zzsy(zzsu zzsu) {
    }

    @Override // com.google.android.gms.internal.ads.zzts
    public final void zzb(zztt zztt) throws RemoteException {
        if (zztt.zzxs != null) {
            zztt.zzxs.onAdLoaded();
        }
    }
}
