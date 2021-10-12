package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzth implements zzts {
    zzth(zztg zztg) {
    }

    @Override // com.google.android.gms.internal.ads.zzts
    public final void zzb(zztt zztt) throws RemoteException {
        if (zztt.zzbog != null) {
            zztt.zzbog.onAdClicked();
        }
    }
}
