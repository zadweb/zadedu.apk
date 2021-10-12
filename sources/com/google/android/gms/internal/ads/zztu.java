package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Random;

/* access modifiers changed from: package-private */
public final class zztu extends zzki {
    private final zzkh zzboi;

    zztu(zzkh zzkh) {
        this.zzboi = zzkh;
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdClicked() throws RemoteException {
        this.zzboi.onAdClicked();
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdClosed() throws RemoteException {
        if (zzud.zzlv()) {
            int intValue = ((Integer) zzkb.zzik().zzd(zznk.zzazg)).intValue();
            int intValue2 = ((Integer) zzkb.zzik().zzd(zznk.zzazh)).intValue();
            if (intValue <= 0 || intValue2 < 0) {
                zzbv.zzex().zzld();
            } else {
                zzakk.zzcrm.postDelayed(zztv.zzboj, (long) (intValue + new Random().nextInt(intValue2 + 1)));
            }
        }
        this.zzboi.onAdClosed();
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdFailedToLoad(int i) throws RemoteException {
        this.zzboi.onAdFailedToLoad(i);
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdImpression() throws RemoteException {
        this.zzboi.onAdImpression();
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdLeftApplication() throws RemoteException {
        this.zzboi.onAdLeftApplication();
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdLoaded() throws RemoteException {
        this.zzboi.onAdLoaded();
    }

    @Override // com.google.android.gms.internal.ads.zzkh
    public final void onAdOpened() throws RemoteException {
        this.zzboi.onAdOpened();
    }
}
