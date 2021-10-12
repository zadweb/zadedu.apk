package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public final class zzpp extends zzqg implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    static final String[] zzbjs = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    private zzoz zzbij;
    private View zzbjx;
    private Point zzbjz = new Point();
    private Point zzbka = new Point();
    private WeakReference<zzfp> zzbkb = new WeakReference<>(null);
    private final WeakReference<View> zzbke;
    private final Map<String, WeakReference<View>> zzbkf = new HashMap();
    private final Map<String, WeakReference<View>> zzbkg = new HashMap();
    private final Map<String, WeakReference<View>> zzbkh = new HashMap();

    public zzpp(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        zzbv.zzfg();
        zzaor.zza(view, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzbv.zzfg();
        zzaor.zza(view, (ViewTreeObserver.OnScrollChangedListener) this);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        this.zzbke = new WeakReference<>(view);
        for (Map.Entry<String, View> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            View value = entry.getValue();
            if (value != null) {
                this.zzbkf.put(key, new WeakReference<>(value));
                if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(key) && !UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(key)) {
                    value.setOnTouchListener(this);
                    value.setClickable(true);
                    value.setOnClickListener(this);
                }
            }
        }
        this.zzbkh.putAll(this.zzbkf);
        for (Map.Entry<String, View> entry2 : hashMap2.entrySet()) {
            View value2 = entry2.getValue();
            if (value2 != null) {
                this.zzbkg.put(entry2.getKey(), new WeakReference<>(value2));
                value2.setOnTouchListener(this);
            }
        }
        this.zzbkh.putAll(this.zzbkg);
        zznk.initialize(view.getContext());
    }

    /* access modifiers changed from: private */
    public final void zza(zzpd zzpd) {
        View view;
        synchronized (this.mLock) {
            String[] strArr = zzbjs;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    view = null;
                    break;
                }
                WeakReference<View> weakReference = this.zzbkh.get(strArr[i]);
                if (weakReference != null) {
                    view = weakReference.get();
                    break;
                }
                i++;
            }
            if (!(view instanceof FrameLayout)) {
                zzpd.zzkq();
                return;
            }
            zzpr zzpr = new zzpr(this, view);
            if (zzpd instanceof zzoy) {
                zzpd.zzb(view, zzpr);
            } else {
                zzpd.zza(view, zzpr);
            }
        }
    }

    /* access modifiers changed from: private */
    public final boolean zza(String[] strArr) {
        for (String str : strArr) {
            if (this.zzbkf.get(str) != null) {
                return true;
            }
        }
        for (String str2 : strArr) {
            if (this.zzbkg.get(str2) != null) {
                return false;
            }
        }
        return false;
    }

    private final void zzl(View view) {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                zzoz zzkn = this.zzbij instanceof zzoy ? ((zzoy) this.zzbij).zzkn() : this.zzbij;
                if (zzkn != null) {
                    zzkn.zzl(view);
                }
            }
        }
    }

    private final int zzv(int i) {
        int zzb;
        synchronized (this.mLock) {
            zzkb.zzif();
            zzb = zzamu.zzb(this.zzbij.getContext(), i);
        }
        return zzb;
    }

    public final void onClick(View view) {
        zzoz zzoz;
        String str;
        Map<String, WeakReference<View>> map;
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                View view2 = this.zzbke.get();
                if (view2 != null) {
                    Bundle bundle = new Bundle();
                    bundle.putFloat(AvidJSONUtil.KEY_X, (float) zzv(this.zzbjz.x));
                    bundle.putFloat(AvidJSONUtil.KEY_Y, (float) zzv(this.zzbjz.y));
                    bundle.putFloat("start_x", (float) zzv(this.zzbka.x));
                    bundle.putFloat("start_y", (float) zzv(this.zzbka.y));
                    if (this.zzbjx == null || !this.zzbjx.equals(view)) {
                        this.zzbij.zza(view, this.zzbkh, bundle, view2);
                    } else {
                        if (!(this.zzbij instanceof zzoy)) {
                            zzoz = this.zzbij;
                            str = NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
                            map = this.zzbkh;
                        } else if (((zzoy) this.zzbij).zzkn() != null) {
                            zzoz = ((zzoy) this.zzbij).zzkn();
                            str = NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
                            map = this.zzbkh;
                        }
                        zzoz.zza(view, str, bundle, map, view2);
                    }
                }
            }
        }
    }

    public final void onGlobalLayout() {
        View view;
        synchronized (this.mLock) {
            if (!(this.zzbij == null || (view = this.zzbke.get()) == null)) {
                this.zzbij.zzc(view, this.zzbkh);
            }
        }
    }

    public final void onScrollChanged() {
        View view;
        synchronized (this.mLock) {
            if (!(this.zzbij == null || (view = this.zzbke.get()) == null)) {
                this.zzbij.zzc(view, this.zzbkh);
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                return false;
            }
            View view2 = this.zzbke.get();
            if (view2 == null) {
                return false;
            }
            int[] iArr = new int[2];
            view2.getLocationOnScreen(iArr);
            Point point = new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
            this.zzbjz = point;
            if (motionEvent.getAction() == 0) {
                this.zzbka = point;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setLocation((float) point.x, (float) point.y);
            this.zzbij.zzd(obtain);
            obtain.recycle();
            return false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzqf
    public final void unregisterNativeAd() {
        synchronized (this.mLock) {
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzqf
    public final void zza(IObjectWrapper iObjectWrapper) {
        int i;
        View view;
        synchronized (this.mLock) {
            ViewGroup viewGroup = null;
            zzl(null);
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (!(unwrap instanceof zzpd)) {
                zzakb.zzdk("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            zzpd zzpd = (zzpd) unwrap;
            if (!zzpd.zzkk()) {
                zzakb.e("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
                return;
            }
            View view2 = this.zzbke.get();
            if (!(this.zzbij == null || view2 == null)) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbbu)).booleanValue()) {
                    this.zzbij.zzb(view2, this.zzbkh);
                }
            }
            synchronized (this.mLock) {
                i = 0;
                if (this.zzbij instanceof zzpd) {
                    zzpd zzpd2 = (zzpd) this.zzbij;
                    View view3 = this.zzbke.get();
                    if (!(zzpd2 == null || zzpd2.getContext() == null || view3 == null || !zzbv.zzfh().zzu(view3.getContext()))) {
                        zzaix zzks = zzpd2.zzks();
                        if (zzks != null) {
                            zzks.zzx(false);
                        }
                        zzfp zzfp = this.zzbkb.get();
                        if (!(zzfp == null || zzks == null)) {
                            zzfp.zzb(zzks);
                        }
                    }
                }
            }
            if (!(this.zzbij instanceof zzoy) || !((zzoy) this.zzbij).zzkm()) {
                this.zzbij = zzpd;
                if (zzpd instanceof zzoy) {
                    ((zzoy) zzpd).zzc(null);
                }
            } else {
                ((zzoy) this.zzbij).zzc(zzpd);
            }
            String[] strArr = {NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW};
            while (true) {
                if (i >= 2) {
                    view = null;
                    break;
                }
                WeakReference<View> weakReference = this.zzbkh.get(strArr[i]);
                if (weakReference != null) {
                    view = weakReference.get();
                    break;
                }
                i++;
            }
            if (view == null) {
                zzakb.zzdk("Ad choices asset view is not provided.");
            } else {
                if (view instanceof ViewGroup) {
                    viewGroup = (ViewGroup) view;
                }
                if (viewGroup != null) {
                    View zza = zzpd.zza((View.OnClickListener) this, true);
                    this.zzbjx = zza;
                    if (zza != null) {
                        this.zzbkh.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference<>(this.zzbjx));
                        this.zzbkf.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference<>(this.zzbjx));
                        viewGroup.removeAllViews();
                        viewGroup.addView(this.zzbjx);
                    }
                }
            }
            zzpd.zza(view2, this.zzbkf, this.zzbkg, this, this);
            zzakk.zzcrm.post(new zzpq(this, zzpd));
            zzl(view2);
            this.zzbij.zzj(view2);
            synchronized (this.mLock) {
                if (this.zzbij instanceof zzpd) {
                    zzpd zzpd3 = (zzpd) this.zzbij;
                    View view4 = this.zzbke.get();
                    if (!(zzpd3 == null || zzpd3.getContext() == null || view4 == null || !zzbv.zzfh().zzu(view4.getContext()))) {
                        zzfp zzfp2 = this.zzbkb.get();
                        if (zzfp2 == null) {
                            zzfp2 = new zzfp(view4.getContext(), view4);
                            this.zzbkb = new WeakReference<>(zzfp2);
                        }
                        zzfp2.zza(zzpd3.zzks());
                    }
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzqf
    public final void zzc(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzbij.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }
}
