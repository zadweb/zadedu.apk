package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.Predicate;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public interface zzaqw extends zzbo, zzapw, zzarr, zzars, zzarw, zzarz, zzasa, zzasb, zzft, zzue, zzve {
    void destroy();

    @Override // com.google.android.gms.internal.ads.zzarr, com.google.android.gms.internal.ads.zzapw
    Context getContext();

    int getHeight();

    ViewGroup.LayoutParams getLayoutParams();

    void getLocationOnScreen(int[] iArr);

    View.OnClickListener getOnClickListener();

    ViewParent getParent();

    int getRequestedOrientation();

    @Override // com.google.android.gms.internal.ads.zzasb
    View getView();

    WebView getWebView();

    int getWidth();

    boolean isDestroyed();

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5);

    void loadUrl(String str);

    void measure(int i, int i2);

    void onPause();

    void onResume();

    @Override // com.google.android.gms.internal.ads.zzapw
    void setBackgroundColor(int i);

    void setOnClickListener(View.OnClickListener onClickListener);

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    void setRequestedOrientation(int i);

    void setWebChromeClient(WebChromeClient webChromeClient);

    void setWebViewClient(WebViewClient webViewClient);

    void stopLoading();

    void zza(zzd zzd);

    @Override // com.google.android.gms.internal.ads.zzapw
    void zza(zzarl zzarl);

    void zza(zzasi zzasi);

    void zza(String str, zzv<? super zzaqw> zzv);

    void zza(String str, Predicate<zzv<? super zzaqw>> predicate);

    void zzai(int i);

    void zzai(boolean z);

    void zzaj(boolean z);

    void zzak(boolean z);

    void zzb(zzd zzd);

    void zzb(zzox zzox);

    void zzb(String str, zzv<? super zzaqw> zzv);

    @Override // com.google.android.gms.internal.ads.zzapw
    zzw zzbi();

    void zzbm(Context context);

    void zzc(String str, String str2, String str3);

    void zzdr(String str);

    void zzno();

    @Override // com.google.android.gms.internal.ads.zzapw
    zzarl zztm();

    @Override // com.google.android.gms.internal.ads.zzarr, com.google.android.gms.internal.ads.zzapw
    Activity zzto();

    @Override // com.google.android.gms.internal.ads.zzapw
    zznw zztp();

    @Override // com.google.android.gms.internal.ads.zzasa, com.google.android.gms.internal.ads.zzapw
    zzang zztq();

    void zzty();

    void zztz();

    void zzu(boolean z);

    Context zzua();

    zzd zzub();

    zzd zzuc();

    @Override // com.google.android.gms.internal.ads.zzary
    zzasi zzud();

    String zzue();

    zzasc zzuf();

    WebViewClient zzug();

    boolean zzuh();

    @Override // com.google.android.gms.internal.ads.zzarz
    zzci zzui();

    @Override // com.google.android.gms.internal.ads.zzars
    boolean zzuj();

    void zzuk();

    boolean zzul();

    boolean zzum();

    boolean zzun();

    void zzuo();

    void zzup();

    zzox zzuq();

    void zzur();

    void zzus();
}
