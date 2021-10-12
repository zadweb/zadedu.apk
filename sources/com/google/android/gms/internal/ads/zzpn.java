package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.AdChoicesView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public final class zzpn extends zzqb implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final String[] zzbjs = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    private zzoz zzbij;
    private final FrameLayout zzbjt;
    private View zzbju;
    private final boolean zzbjv;
    private Map<String, WeakReference<View>> zzbjw = Collections.synchronizedMap(new HashMap());
    private View zzbjx;
    private boolean zzbjy = false;
    private Point zzbjz = new Point();
    private Point zzbka = new Point();
    private WeakReference<zzfp> zzbkb = new WeakReference<>(null);
    private FrameLayout zzvh;

    public zzpn(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzbjt = frameLayout;
        this.zzvh = frameLayout2;
        zzbv.zzfg();
        zzaor.zza((View) this.zzbjt, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzbv.zzfg();
        zzaor.zza((View) this.zzbjt, (ViewTreeObserver.OnScrollChangedListener) this);
        this.zzbjt.setOnTouchListener(this);
        this.zzbjt.setOnClickListener(this);
        if (frameLayout2 != null && PlatformVersion.isAtLeastLollipop()) {
            frameLayout2.setElevation(Float.MAX_VALUE);
        }
        zznk.initialize(this.zzbjt.getContext());
        this.zzbjv = ((Boolean) zzkb.zzik().zzd(zznk.zzbcd)).booleanValue();
    }

    private final void zzkt() {
        synchronized (this.mLock) {
            if (!this.zzbjv && this.zzbjy) {
                int measuredWidth = this.zzbjt.getMeasuredWidth();
                int measuredHeight = this.zzbjt.getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzvh == null)) {
                    this.zzvh.setLayoutParams(new FrameLayout.LayoutParams(measuredWidth, measuredHeight));
                    this.zzbjy = false;
                }
            }
        }
    }

    private final void zzl(View view) {
        zzoz zzoz = this.zzbij;
        if (zzoz != null) {
            if (zzoz instanceof zzoy) {
                zzoz = ((zzoy) zzoz).zzkn();
            }
            if (zzoz != null) {
                zzoz.zzl(view);
            }
        }
    }

    private final int zzv(int i) {
        zzkb.zzif();
        return zzamu.zzb(this.zzbij.getContext(), i);
    }

    @Override // com.google.android.gms.internal.ads.zzqa
    public final void destroy() {
        synchronized (this.mLock) {
            if (this.zzvh != null) {
                this.zzvh.removeAllViews();
            }
            this.zzvh = null;
            this.zzbjw = null;
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
            this.zzbkb = null;
            this.zzbju = null;
        }
    }

    public final void onClick(View view) {
        zzoz zzoz;
        String str;
        Map<String, WeakReference<View>> map;
        FrameLayout frameLayout;
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                this.zzbij.cancelUnconfirmedClick();
                Bundle bundle = new Bundle();
                bundle.putFloat(AvidJSONUtil.KEY_X, (float) zzv(this.zzbjz.x));
                bundle.putFloat(AvidJSONUtil.KEY_Y, (float) zzv(this.zzbjz.y));
                bundle.putFloat("start_x", (float) zzv(this.zzbka.x));
                bundle.putFloat("start_y", (float) zzv(this.zzbka.y));
                if (this.zzbjx == null || !this.zzbjx.equals(view)) {
                    this.zzbij.zza(view, this.zzbjw, bundle, this.zzbjt);
                } else {
                    if (!(this.zzbij instanceof zzoy)) {
                        zzoz = this.zzbij;
                        str = NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
                        map = this.zzbjw;
                        frameLayout = this.zzbjt;
                    } else if (((zzoy) this.zzbij).zzkn() != null) {
                        zzoz = ((zzoy) this.zzbij).zzkn();
                        str = NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
                        map = this.zzbjw;
                        frameLayout = this.zzbjt;
                    }
                    zzoz.zza(view, str, bundle, map, frameLayout);
                }
            }
        }
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            zzkt();
            if (this.zzbij != null) {
                this.zzbij.zzc(this.zzbjt, this.zzbjw);
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                this.zzbij.zzc(this.zzbjt, this.zzbjw);
            }
            zzkt();
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                return false;
            }
            int[] iArr = new int[2];
            this.zzbjt.getLocationOnScreen(iArr);
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

    /* JADX WARNING: Removed duplicated region for block: B:101:0x01b8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0161  */
    @Override // com.google.android.gms.internal.ads.zzqa
    public final void zza(IObjectWrapper iObjectWrapper) {
        ViewGroup viewGroup;
        View zza;
        zzaqw zzaqw;
        zzpd zzpd;
        View view;
        zzpd zzpd2;
        synchronized (this.mLock) {
            View view2 = null;
            zzl(null);
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (!(unwrap instanceof zzpd)) {
                zzakb.zzdk("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            int i = 0;
            if (!this.zzbjv && this.zzvh != null) {
                this.zzvh.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
                this.zzbjt.requestLayout();
            }
            boolean z = true;
            this.zzbjy = true;
            zzpd zzpd3 = (zzpd) unwrap;
            if (this.zzbij != null) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbbu)).booleanValue()) {
                    this.zzbij.zzb(this.zzbjt, this.zzbjw);
                }
            }
            if ((this.zzbij instanceof zzpd) && (zzpd2 = (zzpd) this.zzbij) != null && zzpd2.getContext() != null && zzbv.zzfh().zzu(this.zzbjt.getContext())) {
                zzaix zzks = zzpd2.zzks();
                if (zzks != null) {
                    zzks.zzx(false);
                }
                zzfp zzfp = this.zzbkb.get();
                if (!(zzfp == null || zzks == null)) {
                    zzfp.zzb(zzks);
                }
            }
            if (!(this.zzbij instanceof zzoy) || !((zzoy) this.zzbij).zzkm()) {
                this.zzbij = zzpd3;
                if (zzpd3 instanceof zzoy) {
                    ((zzoy) zzpd3).zzc(null);
                }
            } else {
                ((zzoy) this.zzbij).zzc(zzpd3);
            }
            if (this.zzvh != null) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbbu)).booleanValue()) {
                    this.zzvh.setClickable(false);
                }
                this.zzvh.removeAllViews();
                boolean zzkj = zzpd3.zzkj();
                if (zzkj) {
                    if (this.zzbjw != null) {
                        String[] strArr = {NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW};
                        int i2 = 0;
                        while (true) {
                            if (i2 >= 2) {
                                break;
                            }
                            WeakReference<View> weakReference = this.zzbjw.get(strArr[i2]);
                            if (weakReference != null) {
                                view = weakReference.get();
                                break;
                            }
                            i2++;
                        }
                    }
                    view = null;
                    if (view instanceof ViewGroup) {
                        viewGroup = (ViewGroup) view;
                        if (zzkj || viewGroup == null) {
                            z = false;
                        }
                        zza = zzpd3.zza(this, z);
                        this.zzbjx = zza;
                        if (zza != null) {
                            if (this.zzbjw != null) {
                                this.zzbjw.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference<>(this.zzbjx));
                            }
                            if (z) {
                                viewGroup.removeAllViews();
                                viewGroup.addView(this.zzbjx);
                            } else {
                                AdChoicesView adChoicesView = new AdChoicesView(zzpd3.getContext());
                                adChoicesView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                                adChoicesView.addView(this.zzbjx);
                                if (this.zzvh != null) {
                                    this.zzvh.addView(adChoicesView);
                                }
                            }
                        }
                        zzpd3.zza(this.zzbjt, this.zzbjw, (Map<String, WeakReference<View>>) null, this, this);
                        if (this.zzbjv) {
                            if (this.zzbju == null) {
                                View view3 = new View(this.zzbjt.getContext());
                                this.zzbju = view3;
                                view3.setLayoutParams(new FrameLayout.LayoutParams(-1, 0));
                            }
                            if (this.zzbjt != this.zzbju.getParent()) {
                                this.zzbjt.addView(this.zzbju);
                            }
                        }
                        zzaqw = zzpd3.zzko();
                        if (!(zzaqw == null || this.zzvh == null)) {
                            this.zzvh.addView(zzaqw.getView());
                        }
                        synchronized (this.mLock) {
                            zzpd3.zzf(this.zzbjw);
                            if (this.zzbjw != null) {
                                String[] strArr2 = zzbjs;
                                int length = strArr2.length;
                                while (true) {
                                    if (i >= length) {
                                        break;
                                    }
                                    WeakReference<View> weakReference2 = this.zzbjw.get(strArr2[i]);
                                    if (weakReference2 != null) {
                                        view2 = weakReference2.get();
                                        break;
                                    }
                                    i++;
                                }
                            }
                            if (!(view2 instanceof FrameLayout)) {
                                zzpd3.zzkq();
                            } else {
                                zzpo zzpo = new zzpo(this, view2);
                                if (zzpd3 instanceof zzoy) {
                                    zzpd3.zzb(view2, zzpo);
                                } else {
                                    zzpd3.zza(view2, zzpo);
                                }
                            }
                        }
                        zzpd3.zzi(this.zzbjt);
                        zzl(this.zzbjt);
                        this.zzbij.zzj(this.zzbjt);
                        if ((this.zzbij instanceof zzpd) && (zzpd = (zzpd) this.zzbij) != null && zzpd.getContext() != null && zzbv.zzfh().zzu(this.zzbjt.getContext())) {
                            zzfp zzfp2 = this.zzbkb.get();
                            if (zzfp2 == null) {
                                zzfp2 = new zzfp(this.zzbjt.getContext(), this.zzbjt);
                                this.zzbkb = new WeakReference<>(zzfp2);
                            }
                            zzfp2.zza(zzpd.zzks());
                        }
                        return;
                    }
                }
                viewGroup = null;
                if (zzkj) {
                }
                z = false;
                zza = zzpd3.zza(this, z);
                this.zzbjx = zza;
                if (zza != null) {
                }
                zzpd3.zza(this.zzbjt, this.zzbjw, (Map<String, WeakReference<View>>) null, this, this);
                if (this.zzbjv) {
                }
                try {
                    zzaqw = zzpd3.zzko();
                } catch (Exception e) {
                    zzbv.zzem();
                    if (zzakq.zzrp()) {
                        zzakb.zzdk("Privileged processes cannot create HTML overlays.");
                    } else {
                        zzakb.zzb("Error obtaining overlay.", e);
                    }
                    zzaqw = null;
                }
                this.zzvh.addView(zzaqw.getView());
                synchronized (this.mLock) {
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzqa
    public final IObjectWrapper zzak(String str) {
        synchronized (this.mLock) {
            View view = null;
            if (this.zzbjw == null) {
                return null;
            }
            WeakReference<View> weakReference = this.zzbjw.get(str);
            if (weakReference != null) {
                view = weakReference.get();
            }
            return ObjectWrapper.wrap(view);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzqa
    public final void zzb(IObjectWrapper iObjectWrapper, int i) {
        WeakReference<zzfp> weakReference;
        zzfp zzfp;
        if (!(!zzbv.zzfh().zzu(this.zzbjt.getContext()) || (weakReference = this.zzbkb) == null || (zzfp = weakReference.get()) == null)) {
            zzfp.zzgm();
        }
        zzkt();
    }

    @Override // com.google.android.gms.internal.ads.zzqa
    public final void zzb(String str, IObjectWrapper iObjectWrapper) {
        View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        synchronized (this.mLock) {
            if (this.zzbjw != null) {
                if (view == null) {
                    this.zzbjw.remove(str);
                } else {
                    this.zzbjw.put(str, new WeakReference<>(view));
                    if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str)) {
                        if (!UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str)) {
                            view.setOnTouchListener(this);
                            view.setClickable(true);
                            view.setOnClickListener(this);
                        }
                    }
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzqa
    public final void zzc(IObjectWrapper iObjectWrapper) {
        this.zzbij.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
