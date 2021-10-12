package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.internal.ads.zzhu;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzari extends WebView implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzaqw {
    private zzamt zzaee;
    private final WindowManager zzaeu;
    private final DisplayMetrics zzagj;
    private final zzci zzbjc;
    private int zzbwy = -1;
    private int zzbwz = -1;
    private int zzbxb = -1;
    private int zzbxc = -1;
    private final zzhs zzcch;
    private String zzchp = "";
    private Boolean zzcpp;
    private zznv zzdad;
    private final zzash zzdda;
    private final zzbo zzddb;
    private final float zzddc;
    private boolean zzddd = false;
    private boolean zzdde = false;
    private zzaqx zzddf;
    private zzd zzddg;
    private zzasi zzddh;
    private boolean zzddi;
    private boolean zzddj;
    private boolean zzddk;
    private boolean zzddl;
    private int zzddm;
    private boolean zzddn = true;
    private boolean zzddo = false;
    private zzarl zzddp;
    private boolean zzddq;
    private boolean zzddr;
    private zzox zzdds;
    private int zzddt;
    private int zzddu;
    private zznv zzddv;
    private zznv zzddw;
    private zznw zzddx;
    private WeakReference<View.OnClickListener> zzddy;
    private zzd zzddz;
    private boolean zzdea;
    private Map<String, zzaqh> zzdeb;
    private String zzus;
    private final zzw zzwc;
    private final zzang zzyf;

    private zzari(zzash zzash, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        super(zzash);
        this.zzdda = zzash;
        this.zzddh = zzasi;
        this.zzus = str;
        this.zzddk = z;
        this.zzddm = -1;
        this.zzbjc = zzci;
        this.zzyf = zzang;
        this.zzddb = zzbo;
        this.zzwc = zzw;
        this.zzaeu = (WindowManager) getContext().getSystemService("window");
        zzbv.zzek();
        DisplayMetrics zza = zzakk.zza(this.zzaeu);
        this.zzagj = zza;
        this.zzddc = zza.density;
        this.zzcch = zzhs;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        try {
            settings.setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            zzakb.zzb("Unable to enable Javascript.", e);
        }
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzbv.zzek().zza(zzash, zzang.zzcw, settings);
        zzbv.zzem().zza(getContext(), settings);
        setDownloadListener(this);
        zzvk();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(zzaro.zzk(this), "googleAdsJsInterface");
        }
        if (PlatformVersion.isAtLeastHoneycomb()) {
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }
        this.zzaee = new zzamt(this.zzdda.zzto(), this, this, null);
        zzvo();
        zznw zznw = new zznw(new zznx(true, "make_wv", this.zzus));
        this.zzddx = zznw;
        zznw.zzji().zzc(zznx);
        zznv zzb = zznq.zzb(this.zzddx.zzji());
        this.zzdad = zzb;
        this.zzddx.zza("native:view_create", zzb);
        this.zzddw = null;
        this.zzddv = null;
        zzbv.zzem().zzaw(zzash);
        zzbv.zzeo().zzqe();
    }

    private final void zza(Boolean bool) {
        synchronized (this) {
            this.zzcpp = bool;
        }
        zzbv.zzeo().zza(bool);
    }

    private final synchronized void zza(String str, ValueCallback<String> valueCallback) {
        if (!isDestroyed()) {
            evaluateJavascript(str, null);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    private final void zzal(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? "1" : "0");
        zza("onAdVisibilityChanged", hashMap);
    }

    static zzari zzb(Context context, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        return new zzari(new zzash(context), zzasi, str, z, z2, zzci, zzang, zznx, zzbo, zzw, zzhs);
    }

    private final synchronized void zzds(String str) {
        if (!isDestroyed()) {
            loadUrl(str);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    private final synchronized void zzdt(String str) {
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError | UnsatisfiedLinkError e) {
            zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzakb.zzc("Could not call loadUrl. ", e);
        }
    }

    private final void zzdu(String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            if (zzpz() == null) {
                zzvi();
            }
            if (zzpz().booleanValue()) {
                zza(str, (ValueCallback<String>) null);
                return;
            }
            String valueOf = String.valueOf(str);
            zzds(valueOf.length() != 0 ? "javascript:".concat(valueOf) : new String("javascript:"));
            return;
        }
        String valueOf2 = String.valueOf(str);
        zzds(valueOf2.length() != 0 ? "javascript:".concat(valueOf2) : new String("javascript:"));
    }

    private final synchronized Boolean zzpz() {
        return this.zzcpp;
    }

    private final synchronized void zzqf() {
        if (!this.zzdea) {
            this.zzdea = true;
            zzbv.zzeo().zzqf();
        }
    }

    private final boolean zzvh() {
        int i;
        int i2;
        boolean z = false;
        if (!this.zzddf.zzfz() && !this.zzddf.zzuu()) {
            return false;
        }
        zzkb.zzif();
        DisplayMetrics displayMetrics = this.zzagj;
        int zzb = zzamu.zzb(displayMetrics, displayMetrics.widthPixels);
        zzkb.zzif();
        DisplayMetrics displayMetrics2 = this.zzagj;
        int zzb2 = zzamu.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity zzto = this.zzdda.zzto();
        if (zzto == null || zzto.getWindow() == null) {
            i2 = zzb;
            i = zzb2;
        } else {
            zzbv.zzek();
            int[] zzf = zzakk.zzf(zzto);
            zzkb.zzif();
            int zzb3 = zzamu.zzb(this.zzagj, zzf[0]);
            zzkb.zzif();
            i = zzamu.zzb(this.zzagj, zzf[1]);
            i2 = zzb3;
        }
        if (this.zzbwy == zzb && this.zzbwz == zzb2 && this.zzbxb == i2 && this.zzbxc == i) {
            return false;
        }
        if (!(this.zzbwy == zzb && this.zzbwz == zzb2)) {
            z = true;
        }
        this.zzbwy = zzb;
        this.zzbwz = zzb2;
        this.zzbxb = i2;
        this.zzbxc = i;
        new zzaal(this).zza(zzb, zzb2, i2, i, this.zzagj.density, this.zzaeu.getDefaultDisplay().getRotation());
        return z;
    }

    private final synchronized void zzvi() {
        Boolean zzpz = zzbv.zzeo().zzpz();
        this.zzcpp = zzpz;
        if (zzpz == null) {
            try {
                evaluateJavascript("(function(){})()", null);
                zza((Boolean) true);
            } catch (IllegalStateException unused) {
                zza((Boolean) false);
            }
        }
    }

    private final void zzvj() {
        zznq.zza(this.zzddx.zzji(), this.zzdad, "aeh2");
    }

    private final synchronized void zzvk() {
        if (!this.zzddk) {
            if (!this.zzddh.zzvs()) {
                if (Build.VERSION.SDK_INT < 18) {
                    zzakb.zzck("Disabling hardware acceleration on an AdView.");
                    zzvl();
                    return;
                }
                zzakb.zzck("Enabling hardware acceleration on an AdView.");
                zzvm();
                return;
            }
        }
        zzakb.zzck("Enabling hardware acceleration on an overlay.");
        zzvm();
    }

    private final synchronized void zzvl() {
        if (!this.zzddl) {
            zzbv.zzem().zzz(this);
        }
        this.zzddl = true;
    }

    private final synchronized void zzvm() {
        if (this.zzddl) {
            zzbv.zzem().zzy(this);
        }
        this.zzddl = false;
    }

    private final synchronized void zzvn() {
        this.zzdeb = null;
    }

    private final void zzvo() {
        zznx zzji;
        zznw zznw = this.zzddx;
        if (zznw != null && (zzji = zznw.zzji()) != null && zzbv.zzeo().zzpy() != null) {
            zzbv.zzeo().zzpy().zza(zzji);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void destroy() {
        zzvo();
        this.zzaee.zzsd();
        if (this.zzddg != null) {
            this.zzddg.close();
            this.zzddg.onDestroy();
            this.zzddg = null;
        }
        this.zzddf.reset();
        if (!this.zzddj) {
            zzbv.zzff();
            zzaqg.zzb(this);
            zzvn();
            this.zzddj = true;
            zzakb.v("Initiating WebView self destruct sequence in 3...");
            zzakb.v("Loading blank page in WebView, 2...");
            zzdt("about:blank");
        }
    }

    @Override // android.webkit.WebView
    public final synchronized void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (isDestroyed()) {
            zzakb.zzdm("#004 The webview is destroyed. Ignoring action.");
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
            }
            return;
        }
        super.evaluateJavascript(str, valueCallback);
    }

    @Override // java.lang.Object
    public final void finalize() {
        try {
            synchronized (this) {
                if (!this.zzddj) {
                    this.zzddf.reset();
                    zzbv.zzff();
                    zzaqg.zzb(this);
                    zzvn();
                    zzqf();
                }
            }
        } finally {
            super.finalize();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final View.OnClickListener getOnClickListener() {
        return this.zzddy.get();
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized int getRequestedOrientation() {
        return this.zzddm;
    }

    @Override // com.google.android.gms.internal.ads.zzasb, com.google.android.gms.internal.ads.zzaqw
    public final View getView() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final WebView getWebView() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized boolean isDestroyed() {
        return this.zzddj;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void loadData(String str, String str2, String str3) {
        if (!isDestroyed()) {
            super.loadData(str, str2, str3);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!isDestroyed()) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void loadUrl(String str) {
        if (!isDestroyed()) {
            try {
                super.loadUrl(str);
            } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError e) {
                zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrl");
                zzakb.zzc("Could not call loadUrl. ", e);
            }
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzaee.onAttachedToWindow();
        }
        boolean z = this.zzddq;
        if (this.zzddf != null && this.zzddf.zzuu()) {
            if (!this.zzddr) {
                ViewTreeObserver.OnGlobalLayoutListener zzuv = this.zzddf.zzuv();
                if (zzuv != null) {
                    zzbv.zzfg();
                    zzaor.zza(this, zzuv);
                }
                ViewTreeObserver.OnScrollChangedListener zzuw = this.zzddf.zzuw();
                if (zzuw != null) {
                    zzbv.zzfg();
                    zzaor.zza(this, zzuw);
                }
                this.zzddr = true;
            }
            zzvh();
            z = true;
        }
        zzal(z);
    }

    public final void onDetachedFromWindow() {
        synchronized (this) {
            if (!isDestroyed()) {
                this.zzaee.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzddr && this.zzddf != null && this.zzddf.zzuu() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                ViewTreeObserver.OnGlobalLayoutListener zzuv = this.zzddf.zzuv();
                if (zzuv != null) {
                    zzbv.zzem().zza(getViewTreeObserver(), zzuv);
                }
                ViewTreeObserver.OnScrollChangedListener zzuw = this.zzddf.zzuw();
                if (zzuw != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(zzuw);
                }
                this.zzddr = false;
            }
        }
        zzal(false);
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzbv.zzek();
            zzakk.zza(getContext(), intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51 + String.valueOf(str4).length());
            sb.append("Couldn't find an Activity to view url/mimetype: ");
            sb.append(str);
            sb.append(" / ");
            sb.append(str4);
            zzakb.zzck(sb.toString());
        }
    }

    public final void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            if (Build.VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                zzaqx zzaqx = this.zzddf;
                if (zzaqx != null && zzaqx.zzve() != null) {
                    this.zzddf.zzve().zzda();
                }
            }
        }
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxx)).booleanValue()) {
            float axisValue = motionEvent.getAxisValue(9);
            float axisValue2 = motionEvent.getAxisValue(10);
            if (motionEvent.getActionMasked() == 8) {
                if (axisValue > 0.0f && !canScrollVertically(-1)) {
                    return false;
                }
                if (axisValue < 0.0f && !canScrollVertically(1)) {
                    return false;
                }
                if (axisValue2 > 0.0f && !canScrollHorizontally(-1)) {
                    return false;
                }
                if (axisValue2 < 0.0f && !canScrollHorizontally(1)) {
                    return false;
                }
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public final void onGlobalLayout() {
        boolean zzvh = zzvh();
        zzd zzub = zzub();
        if (zzub != null && zzvh) {
            zzub.zznn();
        }
    }

    public final synchronized void onMeasure(int i, int i2) {
        if (isDestroyed()) {
            setMeasuredDimension(0, 0);
        } else if (isInEditMode() || this.zzddk || this.zzddh.zzvt()) {
            super.onMeasure(i, i2);
        } else if (this.zzddh.zzvu()) {
            zzarl zztm = zztm();
            float aspectRatio = zztm != null ? zztm.getAspectRatio() : 0.0f;
            if (aspectRatio == 0.0f) {
                super.onMeasure(i, i2);
                return;
            }
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            int i3 = (int) (((float) size2) * aspectRatio);
            int i4 = (int) (((float) size) / aspectRatio);
            if (size2 == 0 && i4 != 0) {
                i3 = (int) (((float) i4) * aspectRatio);
                size2 = i4;
            } else if (size == 0 && i3 != 0) {
                i4 = (int) (((float) i3) / aspectRatio);
                size = i3;
            }
            setMeasuredDimension(Math.min(i3, size), Math.min(i4, size2));
        } else if (this.zzddh.isFluid()) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbch)).booleanValue() || !PlatformVersion.isAtLeastJellyBeanMR1()) {
                super.onMeasure(i, i2);
                return;
            }
            zza("/contentHeight", new zzarj(this));
            zzdu("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
            setMeasuredDimension(View.MeasureSpec.getSize(i), this.zzddu != -1 ? (int) (((float) this.zzddu) * this.zzagj.density) : View.MeasureSpec.getSize(i2));
        } else if (this.zzddh.zzvs()) {
            setMeasuredDimension(this.zzagj.widthPixels, this.zzagj.heightPixels);
        } else {
            int mode = View.MeasureSpec.getMode(i);
            int size3 = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size4 = View.MeasureSpec.getSize(i2);
            int i5 = Integer.MAX_VALUE;
            int i6 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size3 : Integer.MAX_VALUE;
            if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                i5 = size4;
            }
            boolean z = this.zzddh.widthPixels > i6 || this.zzddh.heightPixels > i5;
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbfe)).booleanValue()) {
                boolean z2 = ((float) this.zzddh.widthPixels) / this.zzddc <= ((float) i6) / this.zzddc && ((float) this.zzddh.heightPixels) / this.zzddc <= ((float) i5) / this.zzddc;
                if (z) {
                    z = z2;
                }
            }
            if (z) {
                StringBuilder sb = new StringBuilder(103);
                sb.append("Not enough space to show ad. Needs ");
                sb.append((int) (((float) this.zzddh.widthPixels) / this.zzddc));
                sb.append(AvidJSONUtil.KEY_X);
                sb.append((int) (((float) this.zzddh.heightPixels) / this.zzddc));
                sb.append(" dp, but only has ");
                sb.append((int) (((float) size3) / this.zzddc));
                sb.append(AvidJSONUtil.KEY_X);
                sb.append((int) (((float) size4) / this.zzddc));
                sb.append(" dp.");
                zzakb.zzdk(sb.toString());
                if (getVisibility() != 8) {
                    setVisibility(4);
                }
                setMeasuredDimension(0, 0);
                if (!this.zzddd) {
                    this.zzcch.zza(zzhu.zza.zzb.BANNER_SIZE_INVALID);
                    this.zzddd = true;
                }
            } else {
                if (getVisibility() != 8) {
                    setVisibility(0);
                }
                if (!this.zzdde) {
                    this.zzcch.zza(zzhu.zza.zzb.BANNER_SIZE_VALID);
                    this.zzdde = true;
                }
                setMeasuredDimension(this.zzddh.widthPixels, this.zzddh.heightPixels);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void onPause() {
        if (!isDestroyed()) {
            try {
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    super.onPause();
                }
            } catch (Exception e) {
                zzakb.zzb("Could not pause webview.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void onResume() {
        if (!isDestroyed()) {
            try {
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    super.onResume();
                }
            } catch (Exception e) {
                zzakb.zzb("Could not resume webview.", e);
            }
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzddf.zzuu()) {
            synchronized (this) {
                if (this.zzdds != null) {
                    this.zzdds.zzc(motionEvent);
                }
            }
        } else {
            zzci zzci = this.zzbjc;
            if (zzci != null) {
                zzci.zza(motionEvent);
            }
        }
        if (isDestroyed()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzddy = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void setRequestedOrientation(int i) {
        this.zzddm = i;
        if (this.zzddg != null) {
            this.zzddg.setRequestedOrientation(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzaqx) {
            this.zzddf = (zzaqx) webViewClient;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Exception e) {
                zzakb.zzb("Could not stop loading webview.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    public final void zza(zzc zzc) {
        this.zzddf.zza(zzc);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zza(zzd zzd) {
        this.zzddg = zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzapw, com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zza(zzarl zzarl) {
        if (this.zzddp != null) {
            zzakb.e("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzddp = zzarl;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zza(zzasi zzasi) {
        this.zzddh = zzasi;
        requestLayout();
    }

    @Override // com.google.android.gms.internal.ads.zzft
    public final void zza(zzfs zzfs) {
        synchronized (this) {
            this.zzddq = zzfs.zztg;
        }
        zzal(zzfs.zztg);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zza(String str, zzv<? super zzaqw> zzv) {
        zzaqx zzaqx = this.zzddf;
        if (zzaqx != null) {
            zzaqx.zza(str, zzv);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zza(String str, Predicate<zzv<? super zzaqw>> predicate) {
        zzaqx zzaqx = this.zzddf;
        if (zzaqx != null) {
            zzaqx.zza(str, predicate);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzue
    public final void zza(String str, Map<String, ?> map) {
        try {
            zza(str, zzbv.zzek().zzn(map));
        } catch (JSONException unused) {
            zzakb.zzdk("Could not convert parameters to JSON.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzue
    public final void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String valueOf = String.valueOf(sb.toString());
        zzakb.zzck(valueOf.length() != 0 ? "Dispatching AFMA event: ".concat(valueOf) : new String("Dispatching AFMA event: "));
        zzdu(sb.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    public final void zza(boolean z, int i) {
        this.zzddf.zza(z, i);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    public final void zza(boolean z, int i, String str) {
        this.zzddf.zza(z, i, str);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    public final void zza(boolean z, int i, String str, String str2) {
        this.zzddf.zza(z, i, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzapw
    public final void zzah(boolean z) {
        this.zzddf.zzah(z);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zzai(int i) {
        if (i == 0) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aebb2");
        }
        zzvj();
        if (this.zzddx.zzji() != null) {
            this.zzddx.zzji().zze("close_type", String.valueOf(i));
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zzyf.zzcw);
        zza("onhide", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzai(boolean z) {
        boolean z2 = z != this.zzddk;
        this.zzddk = z;
        zzvk();
        if (z2) {
            new zzaal(this).zzby(z ? "expanded" : RewardedVideo.VIDEO_MODE_DEFAULT);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzaj(boolean z) {
        this.zzddn = z;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzak(boolean z) {
        int i = this.zzddt + (z ? 1 : -1);
        this.zzddt = i;
        if (i <= 0 && this.zzddg != null) {
            this.zzddg.zznq();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzb(zzd zzd) {
        this.zzddz = zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzb(zzox zzox) {
        this.zzdds = zzox;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zzb(String str, zzv<? super zzaqw> zzv) {
        zzaqx zzaqx = this.zzddf;
        if (zzaqx != null) {
            zzaqx.zzb(str, zzv);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(jSONObject2).length());
        sb.append(str);
        sb.append("(");
        sb.append(jSONObject2);
        sb.append(");");
        zzdu(sb.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzbe(String str) {
        zzdu(str);
    }

    @Override // com.google.android.gms.internal.ads.zzapw, com.google.android.gms.internal.ads.zzaqw
    public final zzw zzbi() {
        return this.zzwc;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zzbm(Context context) {
        this.zzdda.setBaseContext(context);
        this.zzaee.zzi(this.zzdda.zzto());
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzc(String str, String str2, String str3) {
        if (!isDestroyed()) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaya)).booleanValue()) {
                str2 = zzarx.zzb(str2, zzarx.zzvp());
            }
            super.loadDataWithBaseURL(str, str2, "text/html", "UTF-8", str3);
            return;
        }
        zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
    }

    @Override // com.google.android.gms.ads.internal.zzbo
    public final synchronized void zzcl() {
        this.zzddo = true;
        if (this.zzddb != null) {
            this.zzddb.zzcl();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzbo
    public final synchronized void zzcm() {
        this.zzddo = false;
        if (this.zzddb != null) {
            this.zzddb.zzcm();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzdr(String str) {
        if (str == null) {
            str = "";
        }
        this.zzchp = str;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zzno() {
        if (this.zzddv == null) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aes2");
            zznv zzb = zznq.zzb(this.zzddx.zzji());
            this.zzddv = zzb;
            this.zzddx.zza("native:view_show", zzb);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzyf.zzcw);
        zza("onshow", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzapw
    public final void zznp() {
        zzd zzub = zzub();
        if (zzub != null) {
            zzub.zznp();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapw
    public final synchronized String zzol() {
        return this.zzchp;
    }

    @Override // com.google.android.gms.internal.ads.zzapw
    public final zzapn zztl() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzapw, com.google.android.gms.internal.ads.zzaqw
    public final synchronized zzarl zztm() {
        return this.zzddp;
    }

    @Override // com.google.android.gms.internal.ads.zzapw
    public final zznv zztn() {
        return this.zzdad;
    }

    @Override // com.google.android.gms.internal.ads.zzapw, com.google.android.gms.internal.ads.zzarr, com.google.android.gms.internal.ads.zzaqw
    public final Activity zzto() {
        return this.zzdda.zzto();
    }

    @Override // com.google.android.gms.internal.ads.zzapw, com.google.android.gms.internal.ads.zzaqw
    public final zznw zztp() {
        return this.zzddx;
    }

    @Override // com.google.android.gms.internal.ads.zzasa, com.google.android.gms.internal.ads.zzapw, com.google.android.gms.internal.ads.zzaqw
    public final zzang zztq() {
        return this.zzyf;
    }

    @Override // com.google.android.gms.internal.ads.zzapw
    public final int zztr() {
        return getMeasuredHeight();
    }

    @Override // com.google.android.gms.internal.ads.zzapw
    public final int zzts() {
        return getMeasuredWidth();
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zzty() {
        zzvj();
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzyf.zzcw);
        zza("onhide", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zztz() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzbv.zzfj().zzdp()));
        hashMap.put("app_volume", String.valueOf(zzbv.zzfj().zzdo()));
        hashMap.put("device_volume", String.valueOf(zzalb.zzay(getContext())));
        zza(AvidVideoPlaybackListenerImpl.VOLUME, hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzu(boolean z) {
        if (this.zzddg != null) {
            this.zzddg.zza(this.zzddf.zzfz(), z);
        } else {
            this.zzddi = z;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final Context zzua() {
        return this.zzdda.zzua();
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized zzd zzub() {
        return this.zzddg;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized zzd zzuc() {
        return this.zzddz;
    }

    @Override // com.google.android.gms.internal.ads.zzary, com.google.android.gms.internal.ads.zzaqw
    public final synchronized zzasi zzud() {
        return this.zzddh;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized String zzue() {
        return this.zzus;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final /* synthetic */ zzasc zzuf() {
        return this.zzddf;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final WebViewClient zzug() {
        return this.zzddf;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized boolean zzuh() {
        return this.zzddi;
    }

    @Override // com.google.android.gms.internal.ads.zzarz, com.google.android.gms.internal.ads.zzaqw
    public final zzci zzui() {
        return this.zzbjc;
    }

    @Override // com.google.android.gms.internal.ads.zzars, com.google.android.gms.internal.ads.zzaqw
    public final synchronized boolean zzuj() {
        return this.zzddk;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzuk() {
        zzakb.v("Destroying WebView!");
        zzqf();
        zzakk.zzcrm.post(new zzark(this));
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized boolean zzul() {
        return this.zzddn;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized boolean zzum() {
        return this.zzddo;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized boolean zzun() {
        return this.zzddt > 0;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zzuo() {
        this.zzaee.zzsc();
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zzup() {
        if (this.zzddw == null) {
            zznv zzb = zznq.zzb(this.zzddx.zzji());
            this.zzddw = zzb;
            this.zzddx.zza("native:view_load", zzb);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized zzox zzuq() {
        return this.zzdds;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zzur() {
        setBackgroundColor(0);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zzus() {
        zzakb.v("Cannot add text view to inner AdWebView");
    }
}
