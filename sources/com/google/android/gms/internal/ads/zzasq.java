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
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
@ParametersAreNonnullByDefault
@zzadh
public final class zzasq extends zzasv implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzaqw, zzuo {
    private zzamt zzaee;
    private final WindowManager zzaeu;
    private final zzci zzbjc;
    private int zzbwy = -1;
    private int zzbwz = -1;
    private int zzbxb = -1;
    private int zzbxc = -1;
    private String zzchp = "";
    private zznv zzdad;
    private final zzbo zzddb;
    private zzd zzddg;
    private zzasi zzddh;
    private boolean zzddi;
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
    private Map<String, zzaqh> zzdeb;
    private zzasj zzdet;
    private float zzdeu;
    private String zzus;
    private final zzw zzwc;
    private final zzang zzyf;

    private zzasq(zzash zzash, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        super(zzash);
        this.zzddh = zzasi;
        this.zzus = str;
        this.zzddk = z;
        this.zzddm = -1;
        this.zzbjc = zzci;
        this.zzyf = zzang;
        this.zzddb = zzbo;
        this.zzwc = zzw;
        this.zzaeu = (WindowManager) getContext().getSystemService("window");
        this.zzaee = new zzamt(zzvv().zzto(), this, this, null);
        zzbv.zzek().zza(zzash, zzang.zzcw, getSettings());
        setDownloadListener(this);
        this.zzdeu = zzvv().getResources().getDisplayMetrics().density;
        zzvk();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(zzaro.zzk(this), "googleAdsJsInterface");
        }
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
    }

    static /* synthetic */ int zza(zzasq zzasq) {
        return zzasq.zzddu;
    }

    static /* synthetic */ int zza(zzasq zzasq, int i) {
        zzasq.zzddu = i;
        return i;
    }

    private final void zzal(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? "1" : "0");
        zzup.zza(this, "onAdVisibilityChanged", hashMap);
    }

    static zzasq zzc(Context context, zzasi zzasi, String str, boolean z, boolean z2, zzci zzci, zzang zzang, zznx zznx, zzbo zzbo, zzw zzw, zzhs zzhs) {
        return new zzasq(new zzash(context), zzasi, str, z, z2, zzci, zzang, zznx, zzbo, zzw, zzhs);
    }

    private final boolean zzvh() {
        int i;
        int i2;
        boolean z = false;
        if (!this.zzdet.zzfz() && !this.zzdet.zzuu()) {
            return false;
        }
        zzbv.zzek();
        DisplayMetrics zza = zzakk.zza(this.zzaeu);
        zzkb.zzif();
        int zzb = zzamu.zzb(zza, zza.widthPixels);
        zzkb.zzif();
        int zzb2 = zzamu.zzb(zza, zza.heightPixels);
        Activity zzto = zzvv().zzto();
        if (zzto == null || zzto.getWindow() == null) {
            i2 = zzb;
            i = zzb2;
        } else {
            zzbv.zzek();
            int[] zzf = zzakk.zzf(zzto);
            zzkb.zzif();
            i2 = zzamu.zzb(zza, zzf[0]);
            zzkb.zzif();
            i = zzamu.zzb(zza, zzf[1]);
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
        new zzaal(this).zza(zzb, zzb2, i2, i, zza.density, this.zzaeu.getDefaultDisplay().getRotation());
        return z;
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

    /* access modifiers changed from: protected */
    public final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzaee.onAttachedToWindow();
        }
        boolean z = this.zzddq;
        if (this.zzdet != null && this.zzdet.zzuu()) {
            if (!this.zzddr) {
                ViewTreeObserver.OnGlobalLayoutListener zzuv = this.zzdet.zzuv();
                if (zzuv != null) {
                    zzbv.zzfg();
                    zzaor.zza(this, zzuv);
                }
                ViewTreeObserver.OnScrollChangedListener zzuw = this.zzdet.zzuw();
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

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        synchronized (this) {
            if (!isDestroyed()) {
                this.zzaee.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzddr && this.zzdet != null && this.zzdet.zzuu() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                ViewTreeObserver.OnGlobalLayoutListener zzuv = this.zzdet.zzuv();
                if (zzuv != null) {
                    zzbv.zzem().zza(getViewTreeObserver(), zzuv);
                }
                ViewTreeObserver.OnScrollChangedListener zzuw = this.zzdet.zzuw();
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

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzasv
    public final void onDraw(Canvas canvas) {
        if (Build.VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
            super.onDraw(canvas);
            zzasj zzasj = this.zzdet;
            if (zzasj != null && zzasj.zzve() != null) {
                this.zzdet.zzve().zzda();
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

    /* access modifiers changed from: protected */
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
            zza("/contentHeight", new zzasr(this));
            zzbe("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
            setMeasuredDimension(View.MeasureSpec.getSize(i), this.zzddu != -1 ? (int) (((float) this.zzddu) * this.zzdeu) : View.MeasureSpec.getSize(i2));
        } else if (this.zzddh.zzvs()) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.zzaeu.getDefaultDisplay().getMetrics(displayMetrics);
            setMeasuredDimension(displayMetrics.widthPixels, displayMetrics.heightPixels);
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
            boolean z = true;
            boolean z2 = this.zzddh.widthPixels > i6 || this.zzddh.heightPixels > i5;
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbfe)).booleanValue()) {
                if (((float) this.zzddh.widthPixels) / this.zzdeu > ((float) i6) / this.zzdeu || ((float) this.zzddh.heightPixels) / this.zzdeu > ((float) i5) / this.zzdeu) {
                    z = false;
                }
                if (z2) {
                    z2 = z;
                }
            }
            if (z2) {
                StringBuilder sb = new StringBuilder(103);
                sb.append("Not enough space to show ad. Needs ");
                sb.append((int) (((float) this.zzddh.widthPixels) / this.zzdeu));
                sb.append(AvidJSONUtil.KEY_X);
                sb.append((int) (((float) this.zzddh.heightPixels) / this.zzdeu));
                sb.append(" dp, but only has ");
                sb.append((int) (((float) size3) / this.zzdeu));
                sb.append(AvidJSONUtil.KEY_X);
                sb.append((int) (((float) size4) / this.zzdeu));
                sb.append(" dp.");
                zzakb.zzdk(sb.toString());
                if (getVisibility() != 8) {
                    setVisibility(4);
                }
                setMeasuredDimension(0, 0);
                return;
            }
            if (getVisibility() != 8) {
                setVisibility(0);
            }
            setMeasuredDimension(this.zzddh.widthPixels, this.zzddh.heightPixels);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzasv, com.google.android.gms.internal.ads.zzaqw
    public final void onPause() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onPause();
            }
        } catch (Exception e) {
            zzakb.zzb("Could not pause webview.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzasv, com.google.android.gms.internal.ads.zzaqw
    public final void onResume() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onResume();
            }
        } catch (Exception e) {
            zzakb.zzb("Could not resume webview.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzasv
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzdet.zzuu()) {
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

    @Override // com.google.android.gms.internal.ads.zzasv, com.google.android.gms.internal.ads.zzaqw
    public final void stopLoading() {
        try {
            super.stopLoading();
        } catch (Exception e) {
            zzakb.zzb("Could not stop loading webview.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    public final void zza(zzc zzc) {
        this.zzdet.zza(zzc);
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

    public final void zza(zzasj zzasj) {
        this.zzdet = zzasj;
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
        zzasj zzasj = this.zzdet;
        if (zzasj != null) {
            zzasj.zza(str, zzv);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zza(String str, Predicate<zzv<? super zzaqw>> predicate) {
        zzasj zzasj = this.zzdet;
        if (zzasj != null) {
            zzasj.zza(str, predicate);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzue
    public final void zza(String str, Map map) {
        zzup.zza(this, str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzuo, com.google.android.gms.internal.ads.zzue
    public final void zza(String str, JSONObject jSONObject) {
        zzup.zzb(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    public final void zza(boolean z, int i) {
        this.zzdet.zza(z, i);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    public final void zza(boolean z, int i, String str) {
        this.zzdet.zza(z, i, str);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    public final void zza(boolean z, int i, String str, String str2) {
        this.zzdet.zza(z, i, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzapw
    public final void zzah(boolean z) {
        this.zzdet.zzah(z);
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
        zzup.zza(this, "onhide", hashMap);
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

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzasv
    public final synchronized void zzam(boolean z) {
        if (!z) {
            zzvo();
            this.zzaee.zzsd();
            if (this.zzddg != null) {
                this.zzddg.close();
                this.zzddg.onDestroy();
                this.zzddg = null;
            }
        }
        this.zzdet.reset();
        zzbv.zzff();
        zzaqg.zzb(this);
        zzvn();
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
        zzasj zzasj = this.zzdet;
        if (zzasj != null) {
            zzasj.zzb(str, zzv);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzb(String str, JSONObject jSONObject) {
        zzup.zza(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzass, com.google.android.gms.internal.ads.zzuo, com.google.android.gms.internal.ads.zzve
    public final synchronized void zzbe(String str) {
        if (!isDestroyed()) {
            super.zzbe(str);
        } else {
            zzakb.zzdk("The webview is destroyed. Ignoring action.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapw, com.google.android.gms.internal.ads.zzaqw
    public final zzw zzbi() {
        return this.zzwc;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zzbm(Context context) {
        zzvv().setBaseContext(context);
        this.zzaee.zzi(zzvv().zzto());
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzc(String str, String str2, String str3) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaya)).booleanValue()) {
            str2 = zzarx.zzb(str2, zzarx.zzvp());
        }
        super.loadDataWithBaseURL(str, str2, "text/html", "UTF-8", str3);
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

    @Override // com.google.android.gms.internal.ads.zzuo
    public final void zzf(String str, String str2) {
        zzup.zza(this, str, str2);
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
        zzup.zza(this, "onshow", hashMap);
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
        return zzvv().zzto();
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
        zzup.zza(this, "onhide", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final void zztz() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzbv.zzfj().zzdp()));
        hashMap.put("app_volume", String.valueOf(zzbv.zzfj().zzdo()));
        hashMap.put("device_volume", String.valueOf(zzalb.zzay(getContext())));
        zzup.zza(this, AvidVideoPlaybackListenerImpl.VOLUME, hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final synchronized void zzu(boolean z) {
        if (this.zzddg != null) {
            this.zzddg.zza(this.zzdet.zzfz(), z);
        } else {
            this.zzddi = z;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final Context zzua() {
        return zzvv().zzua();
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
        return this.zzdet;
    }

    @Override // com.google.android.gms.internal.ads.zzaqw
    public final WebViewClient zzug() {
        return ((zzasv) this).zzdfb;
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
