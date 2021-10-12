package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.mopub.common.Constants;
import java.lang.ref.WeakReference;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
@zzadh
public class zzpd implements zzoz {
    private final Context mContext;
    private final Object mLock = new Object();
    private final zzacm zzaad;
    private String zzaae;
    private final zzpa zzbiw;
    private final zzok zzbiz;
    private final JSONObject zzbja;
    private final zzpb zzbjb;
    private final zzci zzbjc;
    boolean zzbjd;
    boolean zzbje;
    private WeakReference<View> zzbjf = null;
    private final zzang zzyf;
    private zzaix zzyv;

    public zzpd(Context context, zzpa zzpa, zzacm zzacm, zzci zzci, JSONObject jSONObject, zzpb zzpb, zzang zzang, String str) {
        this.mContext = context;
        this.zzbiw = zzpa;
        this.zzaad = zzacm;
        this.zzbjc = zzci;
        this.zzbja = jSONObject;
        this.zzbjb = zzpb;
        this.zzyf = zzang;
        this.zzaae = str;
        this.zzbiz = new zzok(zzacm);
    }

    private final JSONObject zza(Map<String, WeakReference<View>> map, View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (map == null || view == null) {
            return jSONObject2;
        }
        int[] zzn = zzn(view);
        synchronized (map) {
            for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                View view2 = entry.getValue().get();
                if (view2 != null) {
                    int[] zzn2 = zzn(view2);
                    JSONObject jSONObject3 = new JSONObject();
                    JSONObject jSONObject4 = new JSONObject();
                    try {
                        jSONObject4.put("width", zzv(view2.getMeasuredWidth()));
                        jSONObject4.put("height", zzv(view2.getMeasuredHeight()));
                        jSONObject4.put(AvidJSONUtil.KEY_X, zzv(zzn2[0] - zzn[0]));
                        jSONObject4.put(AvidJSONUtil.KEY_Y, zzv(zzn2[1] - zzn[1]));
                        jSONObject4.put("relative_to", "ad_view");
                        jSONObject3.put("frame", jSONObject4);
                        Rect rect = new Rect();
                        if (view2.getLocalVisibleRect(rect)) {
                            jSONObject = zzb(rect);
                        } else {
                            JSONObject jSONObject5 = new JSONObject();
                            jSONObject5.put("width", 0);
                            jSONObject5.put("height", 0);
                            jSONObject5.put(AvidJSONUtil.KEY_X, zzv(zzn2[0] - zzn[0]));
                            jSONObject5.put(AvidJSONUtil.KEY_Y, zzv(zzn2[1] - zzn[1]));
                            jSONObject5.put("relative_to", "ad_view");
                            jSONObject = jSONObject5;
                        }
                        jSONObject3.put("visible_bounds", jSONObject);
                        if (view2 instanceof TextView) {
                            TextView textView = (TextView) view2;
                            jSONObject3.put("text_color", textView.getCurrentTextColor());
                            jSONObject3.put("font_size", (double) textView.getTextSize());
                            jSONObject3.put("text", textView.getText());
                        }
                        jSONObject2.put(entry.getKey(), jSONObject3);
                    } catch (JSONException unused) {
                        zzakb.zzdk("Unable to get asset views information");
                    }
                }
            }
        }
        return jSONObject2;
    }

    private final void zza(View view, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, JSONObject jSONObject6) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        try {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("ad", this.zzbja);
            if (jSONObject2 != null) {
                jSONObject7.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject7.put("ad_view_signal", jSONObject);
            }
            if (jSONObject5 != null) {
                jSONObject7.put("click_signal", jSONObject5);
            }
            if (jSONObject3 != null) {
                jSONObject7.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject7.put("lock_screen_signal", jSONObject4);
            }
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("asset_id", str);
            jSONObject8.put("template", this.zzbjb.zzkb());
            zzbv.zzem();
            jSONObject8.put("is_privileged_process", zzakq.zzrp());
            boolean z = true;
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbcf)).booleanValue() && this.zzbiz.zzjw() != null && this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
                jSONObject8.put("custom_one_point_five_click_eligible", true);
            }
            jSONObject8.put(AvidJSONUtil.KEY_TIMESTAMP, zzbv.zzer().currentTimeMillis());
            jSONObject8.put("has_custom_click_handler", this.zzbiw.zzr(this.zzbjb.getCustomTemplateId()) != null);
            if (this.zzbiw.zzr(this.zzbjb.getCustomTemplateId()) == null) {
                z = false;
            }
            jSONObject7.put("has_custom_click_handler", z);
            try {
                JSONObject optJSONObject = this.zzbja.optJSONObject("tracking_urls_and_actions");
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
                jSONObject8.put("click_signals", this.zzbjc.zzaa().zza(this.mContext, optJSONObject.optString("click_string"), view));
            } catch (Exception e) {
                zzakb.zzb("Exception obtaining click signals", e);
            }
            jSONObject7.put("click", jSONObject8);
            if (jSONObject6 != null) {
                jSONObject7.put("provided_signals", jSONObject6);
            }
            jSONObject7.put("ads_id", this.zzaae);
            zzanm.zza(this.zzaad.zzi(jSONObject7), "NativeAdEngineImpl.performClick");
        } catch (JSONException e2) {
            zzakb.zzb("Unable to create click JSON.", e2);
        }
    }

    private final boolean zza(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (this.zzbjd) {
            return true;
        }
        this.zzbjd = true;
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.zzbja);
            jSONObject6.put("ads_id", this.zzaae);
            if (jSONObject2 != null) {
                jSONObject6.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject6.put("ad_view_signal", jSONObject);
            }
            if (jSONObject3 != null) {
                jSONObject6.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject6.put("lock_screen_signal", jSONObject4);
            }
            if (jSONObject5 != null) {
                jSONObject6.put("provided_signals", jSONObject5);
            }
            zzanm.zza(this.zzaad.zzj(jSONObject6), "NativeAdEngineImpl.recordImpression");
            this.zzbiw.zza(this);
            this.zzbiw.zzbv();
            zzcr();
            return true;
        } catch (JSONException e) {
            zzakb.zzb("Unable to create impression JSON.", e);
            return false;
        }
    }

    private final boolean zzaq(String str) {
        JSONObject jSONObject = this.zzbja;
        JSONObject optJSONObject = jSONObject == null ? null : jSONObject.optJSONObject("allow_pub_event_reporting");
        if (optJSONObject == null) {
            return false;
        }
        return optJSONObject.optBoolean(str, false);
    }

    private final JSONObject zzb(Rect rect) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("width", zzv(rect.right - rect.left));
        jSONObject.put("height", zzv(rect.bottom - rect.top));
        jSONObject.put(AvidJSONUtil.KEY_X, zzv(rect.left));
        jSONObject.put(AvidJSONUtil.KEY_Y, zzv(rect.top));
        jSONObject.put("relative_to", "self");
        return jSONObject;
    }

    private static boolean zzm(View view) {
        return view.isShown() && view.getGlobalVisibleRect(new Rect(), null);
    }

    private static int[] zzn(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    private final JSONObject zzo(View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (view == null) {
            return jSONObject2;
        }
        try {
            int[] zzn = zzn(view);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("width", zzv(view.getMeasuredWidth()));
            jSONObject3.put("height", zzv(view.getMeasuredHeight()));
            jSONObject3.put(AvidJSONUtil.KEY_X, zzv(zzn[0]));
            jSONObject3.put(AvidJSONUtil.KEY_Y, zzv(zzn[1]));
            jSONObject3.put("relative_to", "window");
            jSONObject2.put("frame", jSONObject3);
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                jSONObject = zzb(rect);
            } else {
                jSONObject = new JSONObject();
                jSONObject.put("width", 0);
                jSONObject.put("height", 0);
                jSONObject.put(AvidJSONUtil.KEY_X, zzv(zzn[0]));
                jSONObject.put(AvidJSONUtil.KEY_Y, zzv(zzn[1]));
                jSONObject.put("relative_to", "window");
            }
            jSONObject2.put("visible_bounds", jSONObject);
        } catch (Exception unused) {
            zzakb.zzdk("Unable to get native ad view bounding box");
        }
        return jSONObject2;
    }

    private static JSONObject zzp(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            zzbv.zzek();
            jSONObject.put("contained_in_scroll_view", zzakk.zzx(view) != -1);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private final JSONObject zzq(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            zzbv.zzek();
            jSONObject.put("can_show_on_lock_screen", zzakk.zzw(view));
            zzbv.zzek();
            jSONObject.put("is_keyguard_locked", zzakk.zzau(this.mContext));
        } catch (JSONException unused) {
            zzakb.zzdk("Unable to get lock screen information");
        }
        return jSONObject;
    }

    private final int zzv(int i) {
        zzkb.zzif();
        return zzamu.zzb(this.mContext, i);
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void cancelUnconfirmedClick() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbcf)).booleanValue()) {
            if (!this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
                zzakb.zzdk("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            } else {
                this.zzbiz.cancelUnconfirmedClick();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final void performClick(Bundle bundle) {
        if (bundle == null) {
            zzakb.zzck("Click data is null. No click is reported.");
        } else if (!zzaq("click_reporting")) {
            zzakb.e("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
        } else {
            zza(null, null, null, null, null, bundle.getBundle("click_signal").getString("asset_id"), null, zzbv.zzek().zza(bundle, (JSONObject) null));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final boolean recordImpression(Bundle bundle) {
        if (zzaq("impression_reporting")) {
            return zza((JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, zzbv.zzek().zza(bundle, (JSONObject) null));
        }
        zzakb.e("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final void reportTouchEvent(Bundle bundle) {
        if (bundle == null) {
            zzakb.zzck("Touch event data is null. No touch event is reported.");
        } else if (!zzaq("touch_reporting")) {
            zzakb.e("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
        } else {
            int i = bundle.getInt(Constants.VAST_DURATION_MS);
            this.zzbjc.zzaa().zza((int) bundle.getFloat(AvidJSONUtil.KEY_X), (int) bundle.getFloat(AvidJSONUtil.KEY_Y), i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void setClickConfirmingView(View view) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbcf)).booleanValue()) {
            if (!this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
                zzakb.zzdk("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
                return;
            }
            zzok zzok = this.zzbiz;
            if (view != null) {
                view.setOnClickListener(zzok);
                view.setClickable(true);
                zzok.zzbhq = new WeakReference<>(view);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public View zza(View.OnClickListener onClickListener, boolean z) {
        zzoj zzkc = this.zzbjb.zzkc();
        if (zzkc == null) {
            return null;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (!z) {
            int zzju = zzkc.zzju();
            if (zzju != 0) {
                if (zzju == 2) {
                    layoutParams.addRule(12);
                } else if (zzju != 3) {
                    layoutParams.addRule(10);
                } else {
                    layoutParams.addRule(12);
                }
                layoutParams.addRule(11);
            } else {
                layoutParams.addRule(10);
            }
            layoutParams.addRule(9);
        }
        zzom zzom = new zzom(this.mContext, zzkc, layoutParams);
        zzom.setOnClickListener(onClickListener);
        zzom.setContentDescription((CharSequence) zzkb.zzik().zzd(zznk.zzbbz));
        return zzom;
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final void zza(View view, zzox zzox) {
        if (!zzb(view, zzox)) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            ((FrameLayout) view).removeAllViews();
            zzpb zzpb = this.zzbjb;
            if (zzpb instanceof zzpc) {
                zzpc zzpc = (zzpc) zzpb;
                if (zzpc.getImages() != null && zzpc.getImages().size() > 0) {
                    Object obj = zzpc.getImages().get(0);
                    zzpw zzh = obj instanceof IBinder ? zzpx.zzh((IBinder) obj) : null;
                    if (zzh != null) {
                        try {
                            IObjectWrapper zzjy = zzh.zzjy();
                            if (zzjy != null) {
                                ImageView imageView = new ImageView(this.mContext);
                                imageView.setImageDrawable((Drawable) ObjectWrapper.unwrap(zzjy));
                                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                ((FrameLayout) view).addView(imageView, layoutParams);
                            }
                        } catch (RemoteException unused) {
                            zzakb.zzdk("Could not get drawable from image");
                        }
                    }
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final void zza(View view, String str, Bundle bundle, Map<String, WeakReference<View>> map, View view2) {
        JSONObject jSONObject;
        Exception e;
        JSONObject zza = zza(map, view2);
        JSONObject zzo = zzo(view2);
        JSONObject zzp = zzp(view2);
        JSONObject zzq = zzq(view2);
        JSONObject jSONObject2 = null;
        try {
            JSONObject zza2 = zzbv.zzek().zza(bundle, (JSONObject) null);
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("click_point", zza2);
                jSONObject3.put("asset_id", str);
                jSONObject = jSONObject3;
            } catch (Exception e2) {
                e = e2;
                jSONObject2 = jSONObject3;
                zzakb.zzb("Error occurred while grabbing click signals.", e);
                jSONObject = jSONObject2;
                zza(view, zzo, zza, zzp, zzq, str, jSONObject, null);
            }
        } catch (Exception e3) {
            e = e3;
            zzakb.zzb("Error occurred while grabbing click signals.", e);
            jSONObject = jSONObject2;
            zza(view, zzo, zza, zzp, zzq, str, jSONObject, null);
        }
        zza(view, zzo, zza, zzp, zzq, str, jSONObject, null);
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void zza(View view, Map<String, WeakReference<View>> map) {
        zza(zzo(view), zza(map, view), zzp(view), zzq(view), (JSONObject) null);
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        String str;
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (map != null) {
            synchronized (map) {
                for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                    if (view.equals(entry.getValue().get())) {
                        zza(view, entry.getKey(), bundle, map, view2);
                        return;
                    }
                }
            }
        }
        if ("6".equals(this.zzbjb.zzkb())) {
            str = "3099";
        } else if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(this.zzbjb.zzkb())) {
            str = "2099";
        } else if ("1".equals(this.zzbjb.zzkb())) {
            zza(view, "1099", bundle, map, view2);
            return;
        } else {
            return;
        }
        zza(view, str, bundle, map, view2);
    }

    public void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbbw)).booleanValue()) {
            view.setOnTouchListener(onTouchListener);
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
            if (map != null) {
                synchronized (map) {
                    for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                        View view2 = entry.getValue().get();
                        if (view2 != null) {
                            view2.setOnTouchListener(onTouchListener);
                            view2.setClickable(true);
                            view2.setOnClickListener(onClickListener);
                        }
                    }
                }
            }
            if (map2 != null) {
                synchronized (map2) {
                    for (Map.Entry<String, WeakReference<View>> entry2 : map2.entrySet()) {
                        View view3 = entry2.getValue().get();
                        if (view3 != null) {
                            view3.setOnTouchListener(onTouchListener);
                        }
                    }
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void zza(zzro zzro) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbcf)).booleanValue()) {
            if (!this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
                zzakb.zzdk("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            } else {
                this.zzbiz.zza(zzro);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void zzb(View view, Map<String, WeakReference<View>> map) {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbbv)).booleanValue()) {
            view.setOnTouchListener(null);
            view.setClickable(false);
            view.setOnClickListener(null);
            if (map != null) {
                synchronized (map) {
                    for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                        View view2 = entry.getValue().get();
                        if (view2 != null) {
                            view2.setOnTouchListener(null);
                            view2.setClickable(false);
                            view2.setOnClickListener(null);
                        }
                    }
                }
            }
        }
    }

    public final boolean zzb(View view, zzox zzox) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        View zzkd = this.zzbjb.zzkd();
        if (zzkd == null) {
            return false;
        }
        ViewParent parent = zzkd.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(zzkd);
        }
        FrameLayout frameLayout = (FrameLayout) view;
        frameLayout.removeAllViews();
        frameLayout.addView(zzkd, layoutParams);
        this.zzbiw.zza(zzox);
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final void zzc(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.mLock) {
            if (!this.zzbjd) {
                if (zzm(view)) {
                    zza(view, map);
                    return;
                }
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbce)).booleanValue() && map != null) {
                    synchronized (map) {
                        for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                            View view2 = entry.getValue().get();
                            if (view2 != null && zzm(view2)) {
                                zza(view, map);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void zzcr() {
        this.zzbiw.zzcr();
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void zzcs() {
        this.zzbiw.zzcs();
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final void zzd(MotionEvent motionEvent) {
        this.zzbjc.zza(motionEvent);
    }

    public final void zzf(Map<String, WeakReference<View>> map) {
        if (this.zzbjb.zzkd() == null) {
            return;
        }
        if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(this.zzbjb.zzkb())) {
            zzbv.zzeo().zzqh().zza(this.zzbiw.getAdUnitId(), this.zzbjb.zzkb(), map.containsKey(NativeAppInstallAd.ASSET_MEDIA_VIDEO));
        } else if ("1".equals(this.zzbjb.zzkb())) {
            zzbv.zzeo().zzqh().zza(this.zzbiw.getAdUnitId(), this.zzbjb.zzkb(), map.containsKey(NativeContentAd.ASSET_MEDIA_VIDEO));
        }
    }

    public final void zzi(View view) {
        this.zzbiw.zzi(view);
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final void zzj(View view) {
        zzci zzci;
        zzce zzaa;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbat)).booleanValue() && (zzci = this.zzbjc) != null && (zzaa = zzci.zzaa()) != null) {
            zzaa.zzb(view);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public boolean zzkj() {
        zzoj zzkc = this.zzbjb.zzkc();
        return zzkc != null && zzkc.zzjv();
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public boolean zzkk() {
        JSONObject jSONObject = this.zzbja;
        return jSONObject != null && jSONObject.optBoolean("allow_pub_owned_ad_view", false);
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void zzkl() {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (!this.zzbje) {
            this.zzbje = true;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ad", this.zzbja);
                jSONObject.put("ads_id", this.zzaae);
                zzanm.zza(this.zzaad.zzk(jSONObject), "NativeAdEngineImpl.recordDownloadedImpression");
            } catch (JSONException e) {
                zzane.zzb("", e);
            }
        }
    }

    public zzaqw zzko() throws zzarg {
        JSONObject jSONObject = this.zzbja;
        if (jSONObject == null || jSONObject.optJSONObject("overlay") == null) {
            return null;
        }
        zzbv.zzel();
        Context context = this.mContext;
        zzjn zzf = zzjn.zzf(context);
        zzaqw zza = zzarc.zza(context, zzasi.zzb(zzf), zzf.zzarb, false, false, this.zzbjc, this.zzyf, null, null, null, zzhs.zzhm());
        if (zza != null) {
            zza.getView().setVisibility(8);
            new zzpf(zza).zza(this.zzaad);
        }
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void zzkp() {
        this.zzaad.zzmc();
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public void zzkq() {
        this.zzbiw.zzct();
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final View zzkr() {
        WeakReference<View> weakReference = this.zzbjf;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public final zzaix zzks() {
        if (!zzbv.zzfh().zzu(this.mContext)) {
            return null;
        }
        if (this.zzyv == null) {
            this.zzyv = new zzaix(this.mContext, this.zzbiw.getAdUnitId());
        }
        return this.zzyv;
    }

    @Override // com.google.android.gms.internal.ads.zzoz
    public final void zzl(View view) {
        this.zzbjf = new WeakReference<>(view);
    }
}
