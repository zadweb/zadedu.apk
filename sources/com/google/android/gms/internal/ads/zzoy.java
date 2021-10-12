package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public final class zzoy extends zzpd {
    private Object mLock;
    private zzxz zzbit;
    private zzyc zzbiu;
    private zzyf zzbiv;
    private final zzpa zzbiw;
    private zzoz zzbix;
    private boolean zzbiy;

    private zzoy(Context context, zzpa zzpa, zzci zzci, zzpb zzpb) {
        super(context, zzpa, null, zzci, null, zzpb, null, null);
        this.zzbiy = false;
        this.mLock = new Object();
        this.zzbiw = zzpa;
    }

    public zzoy(Context context, zzpa zzpa, zzci zzci, zzxz zzxz, zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbit = zzxz;
    }

    public zzoy(Context context, zzpa zzpa, zzci zzci, zzyc zzyc, zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbiu = zzyc;
    }

    public zzoy(Context context, zzpa zzpa, zzci zzci, zzyf zzyf, zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbiv = zzyf;
    }

    private static HashMap<String, View> zzb(Map<String, WeakReference<View>> map) {
        HashMap<String, View> hashMap = new HashMap<>();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                View view = entry.getValue().get();
                if (view != null) {
                    hashMap.put(entry.getKey(), view);
                }
            }
        }
        return hashMap;
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void cancelUnconfirmedClick() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.cancelUnconfirmedClick();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void setClickConfirmingView(View view) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.setClickConfirmingView(view);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final View zza(View.OnClickListener onClickListener, boolean z) {
        IObjectWrapper iObjectWrapper;
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                return this.zzbix.zza(onClickListener, z);
            }
            try {
                if (this.zzbiv != null) {
                    iObjectWrapper = this.zzbiv.zzmv();
                } else if (this.zzbit != null) {
                    iObjectWrapper = this.zzbit.zzmv();
                } else {
                    if (this.zzbiu != null) {
                        iObjectWrapper = this.zzbiu.zzmv();
                    }
                    iObjectWrapper = null;
                }
            } catch (RemoteException e) {
                zzakb.zzc("Failed to call getAdChoicesContent", e);
            }
            if (iObjectWrapper == null) {
                return null;
            }
            return (View) ObjectWrapper.unwrap(iObjectWrapper);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void zza(View view, Map<String, WeakReference<View>> map) {
        zzpa zzpa;
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        synchronized (this.mLock) {
            this.zzbjd = true;
            if (this.zzbix != null) {
                this.zzbix.zza(view, map);
                this.zzbiw.recordImpression();
            } else {
                try {
                    if (this.zzbiv != null && !this.zzbiv.getOverrideImpressionRecording()) {
                        this.zzbiv.recordImpression();
                        zzpa = this.zzbiw;
                    } else if (this.zzbit != null && !this.zzbit.getOverrideImpressionRecording()) {
                        this.zzbit.recordImpression();
                        zzpa = this.zzbiw;
                    } else if (this.zzbiu != null && !this.zzbiu.getOverrideImpressionRecording()) {
                        this.zzbiu.recordImpression();
                        zzpa = this.zzbiw;
                    }
                    zzpa.recordImpression();
                } catch (RemoteException e) {
                    zzakb.zzc("Failed to call recordImpression", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        zzpa zzpa;
        Preconditions.checkMainThread("performClick must be called on the main UI thread.");
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.zza(view, map, bundle, view2);
                this.zzbiw.onAdClicked();
            } else {
                try {
                    if (this.zzbiv != null && !this.zzbiv.getOverrideClickHandling()) {
                        this.zzbiv.zzj(ObjectWrapper.wrap(view));
                        zzpa = this.zzbiw;
                    } else if (this.zzbit != null && !this.zzbit.getOverrideClickHandling()) {
                        this.zzbit.zzj(ObjectWrapper.wrap(view));
                        zzpa = this.zzbiw;
                    } else if (this.zzbiu != null && !this.zzbiu.getOverrideClickHandling()) {
                        this.zzbiu.zzj(ObjectWrapper.wrap(view));
                        zzpa = this.zzbiw;
                    }
                    zzpa.onAdClicked();
                } catch (RemoteException e) {
                    zzakb.zzc("Failed to call performClick", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzpd
    public final void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        synchronized (this.mLock) {
            this.zzbiy = true;
            HashMap<String, View> zzb = zzb(map);
            HashMap<String, View> zzb2 = zzb(map2);
            try {
                if (this.zzbiv != null) {
                    this.zzbiv.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                } else if (this.zzbit != null) {
                    this.zzbit.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzbit.zzk(ObjectWrapper.wrap(view));
                } else if (this.zzbiu != null) {
                    this.zzbiu.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzbiu.zzk(ObjectWrapper.wrap(view));
                }
            } catch (RemoteException e) {
                zzakb.zzc("Failed to call prepareAd", e);
            }
            this.zzbiy = false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void zza(zzro zzro) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.zza(zzro);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void zzb(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.mLock) {
            try {
                if (this.zzbiv != null) {
                    this.zzbiv.zzl(ObjectWrapper.wrap(view));
                } else if (this.zzbit != null) {
                    this.zzbit.zzl(ObjectWrapper.wrap(view));
                } else if (this.zzbiu != null) {
                    this.zzbiu.zzl(ObjectWrapper.wrap(view));
                }
            } catch (RemoteException e) {
                zzakb.zzc("Failed to call untrackView", e);
            }
        }
    }

    public final void zzc(zzoz zzoz) {
        synchronized (this.mLock) {
            this.zzbix = zzoz;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void zzcr() {
        zzoz zzoz = this.zzbix;
        if (zzoz != null) {
            zzoz.zzcr();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void zzcs() {
        zzoz zzoz = this.zzbix;
        if (zzoz != null) {
            zzoz.zzcs();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final boolean zzkj() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                return this.zzbix.zzkj();
            }
            return this.zzbiw.zzcu();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final boolean zzkk() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                return this.zzbix.zzkk();
            }
            return this.zzbiw.zzcv();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void zzkl() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on main UI thread.");
        synchronized (this.mLock) {
            this.zzbje = true;
            if (this.zzbix != null) {
                this.zzbix.zzkl();
            }
        }
    }

    public final boolean zzkm() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbiy;
        }
        return z;
    }

    public final zzoz zzkn() {
        zzoz zzoz;
        synchronized (this.mLock) {
            zzoz = this.zzbix;
        }
        return zzoz;
    }

    @Override // com.google.android.gms.internal.ads.zzpd
    public final zzaqw zzko() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void zzkp() {
    }

    @Override // com.google.android.gms.internal.ads.zzoz, com.google.android.gms.internal.ads.zzpd
    public final void zzkq() {
    }
}
