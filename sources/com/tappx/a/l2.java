package com.tappx.a;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.appnext.base.b.d;
import com.tappx.a.p3;
import com.tappx.sdk.android.PrivacyConsentActivity;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class l2 {
    private static final long h = TimeUnit.SECONDS.toMillis(4);

    /* renamed from: a  reason: collision with root package name */
    private final n2 f742a;
    private p3 b;
    private final PrivacyConsentActivity c;
    private WebView d;
    private final m2 e;
    private p3.f f = new b();
    private final WebViewClient g = new c();

    /* access modifiers changed from: package-private */
    public class a implements Runnable {
        a() {
        }

        public void run() {
            l2.this.b.setCloseEnabled(true);
        }
    }

    class b implements p3.f {
        b() {
        }

        @Override // com.tappx.a.p3.f
        public void a() {
            l2.this.d();
        }
    }

    class c extends WebViewClient {
        c() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if ("tappx://consent?yes".equals(str)) {
                l2.this.f742a.g();
                return true;
            } else if ("tappx://consent?no".equals(str)) {
                l2.this.f742a.f();
                return true;
            } else if ("tappx://close".equals(str)) {
                l2.this.d();
                return true;
            } else {
                if (!TextUtils.isEmpty(str)) {
                    l2.this.c.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
        }
    }

    public l2(PrivacyConsentActivity privacyConsentActivity) {
        this.c = privacyConsentActivity;
        o2 a2 = o2.a(privacyConsentActivity);
        this.f742a = a2.c();
        this.e = a2.b();
    }

    private String e() {
        String language;
        Locale locale = Locale.getDefault();
        return (locale == null || (language = locale.getLanguage()) == null) ? "EN" : language.toUpperCase();
    }

    private String f() {
        String stringExtra = this.c.getIntent().getStringExtra("GR8QbFbIwPD6k5hAnMxS6Za9cNsNHXXZzG7GWfNC");
        if (stringExtra == null) {
            return null;
        }
        return Uri.parse(stringExtra).buildUpon().appendQueryParameter(f.b("Atea2vjkWMaKJqXPDr3CPg"), e()).build().toString();
    }

    private View g() {
        this.b = new p3(this.c);
        WebView c2 = c();
        this.d = c2;
        this.b.addView(c2, 0, new FrameLayout.LayoutParams(-1, -1));
        return this.b;
    }

    private void h() {
        this.b.setCloseEnabled(false);
        this.b.setCloseListener(this.f);
        this.b.postDelayed(new a(), h);
    }

    private void i() {
        this.c.requestWindowFeature(1);
        this.c.getWindow().addFlags(d.fb);
        this.c.setContentView(g());
        h();
    }

    private void j() {
        String stringExtra = this.c.getIntent().getStringExtra("kuutYDJOjEGYmzrvCGMIZqwyDXtIZYWxcXzXexLx");
        String f2 = f();
        if (stringExtra != null && !stringExtra.isEmpty()) {
            this.d.loadDataWithBaseURL("https://tappx.com/", stringExtra, "text/html", "UTF-8", null);
        } else if (f2 != null) {
            this.d.loadUrl(f2);
        } else {
            this.c.finish();
        }
    }

    public static Intent a(Context context, String str, String str2) {
        Intent intent = new Intent(context, PrivacyConsentActivity.class);
        intent.putExtra("GR8QbFbIwPD6k5hAnMxS6Za9cNsNHXXZzG7GWfNC", str);
        intent.putExtra("kuutYDJOjEGYmzrvCGMIZqwyDXtIZYWxcXzXexLx", str2);
        return intent;
    }

    private WebView c() {
        WebView webView = new WebView(this.c);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(this.c.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            webView.setId(View.generateViewId());
        }
        webView.setWebViewClient(this.g);
        return webView;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d() {
        this.c.finish();
    }

    public void b() {
        this.e.a();
    }

    public void a(Bundle bundle) {
        i();
        j();
    }

    public boolean a() {
        return !this.b.b();
    }
}
