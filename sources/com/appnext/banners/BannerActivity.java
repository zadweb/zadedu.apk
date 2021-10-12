package com.appnext.banners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.appnext.banners.g;
import com.appnext.base.b.d;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.e;
import com.appnext.core.f;
import com.appnext.core.p;
import com.appnext.core.q;
import com.mopub.common.Constants;
import com.startapp.android.mediation.admob.StartAppNative;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class BannerActivity extends Activity {
    String ap;
    BannerAd bannerAd;
    BannerAdData cF;
    String cG;
    String cH;
    String cI;
    String cJ;
    e cK;
    Banner cL;
    boolean cd;
    BannerAdData selectedAd;
    q userAction;

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x019c  */
    public void onCreate(Bundle bundle) {
        char c;
        BannerAd bannerAd2;
        ArrayList<AppnextAd> f;
        int hashCode;
        BannerSize bannerSize;
        requestWindowFeature(1);
        getWindow().setFlags(d.fb, d.fb);
        super.onCreate(bundle);
        this.cK = new a();
        LinearLayout linearLayout = new LinearLayout(this);
        char c2 = 65535;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        setContentView(linearLayout);
        this.userAction = new q(this, new q.a() {
            /* class com.appnext.banners.BannerActivity.AnonymousClass1 */

            @Override // com.appnext.core.q.a
            public final void report(String str) {
            }

            @Override // com.appnext.core.q.a
            public final Ad e() {
                return BannerActivity.this.bannerAd;
            }

            @Override // com.appnext.core.q.a
            public final AppnextAd f() {
                return BannerActivity.this.cF;
            }

            @Override // com.appnext.core.q.a
            public final p g() {
                return d.S();
            }
        });
        try {
            Bundle extras = getIntent().getExtras();
            this.ap = extras.getString("placement");
            String string = extras.getString("size");
            this.cI = string;
            String str = this.ap;
            int hashCode2 = string.hashCode();
            if (hashCode2 != -1966536496) {
                if (hashCode2 != -96588539) {
                    if (hashCode2 == 1951953708 && string.equals(AdPreferences.TYPE_BANNER)) {
                        c = 0;
                        if (c != 0) {
                            bannerAd2 = new SmallBannerAd(this, str);
                        } else if (c == 1) {
                            bannerAd2 = new LargeBannerAd(this, str);
                        } else if (c == 2) {
                            bannerAd2 = new MediumRectangleAd(this, str);
                        } else {
                            throw new IllegalArgumentException("Wrong banner size " + string);
                        }
                        BannerAd bannerAd3 = bannerAd2;
                        this.bannerAd = bannerAd3;
                        bannerAd3.setPostback(extras.getString("postback"));
                        this.bannerAd.setCategories(extras.getString(StartAppNative.EXTRAS_CATEGORY));
                        this.cH = extras.getString("clicked");
                        this.cd = extras.getBoolean("shouldClose", false);
                        this.selectedAd = (BannerAdData) extras.getSerializable("selected");
                        this.cG = b.R().l(this.bannerAd);
                        f = b.R().f(this.bannerAd);
                        JSONArray jSONArray = new JSONArray();
                        if (f != null) {
                            Iterator<AppnextAd> it = f.iterator();
                            while (it.hasNext()) {
                                jSONArray.put(new JSONObject(new BannerAdData(it.next()).getAdJSON()));
                            }
                        }
                        this.cJ = new JSONObject().put("apps", jSONArray).toString();
                        Banner banner = new Banner(this);
                        this.cL = banner;
                        linearLayout.addView(banner);
                        this.cL.getLayoutParams().height = -1;
                        this.cL.getLayoutParams().width = -1;
                        this.cL.setPlacementId(this.ap);
                        Banner banner2 = this.cL;
                        String str2 = this.cI;
                        hashCode = str2.hashCode();
                        if (hashCode == -1966536496) {
                            if (hashCode != -96588539) {
                                if (hashCode == 1951953708 && str2.equals(AdPreferences.TYPE_BANNER)) {
                                    c2 = 0;
                                }
                            } else if (str2.equals("MEDIUM_RECTANGLE")) {
                                c2 = 2;
                            }
                        } else if (str2.equals("LARGE_BANNER")) {
                            c2 = 1;
                        }
                        if (c2 != 0) {
                            bannerSize = BannerSize.BANNER;
                        } else if (c2 == 1) {
                            bannerSize = BannerSize.LARGE_BANNER;
                        } else if (c2 == 2) {
                            bannerSize = BannerSize.MEDIUM_RECTANGLE;
                        } else {
                            throw new IllegalArgumentException("Wrong banner size " + str2);
                        }
                        banner2.setBannerSize(bannerSize);
                        this.cL.loadAd(null);
                    }
                } else if (string.equals("MEDIUM_RECTANGLE")) {
                    c = 2;
                    if (c != 0) {
                    }
                    BannerAd bannerAd32 = bannerAd2;
                    this.bannerAd = bannerAd32;
                    bannerAd32.setPostback(extras.getString("postback"));
                    this.bannerAd.setCategories(extras.getString(StartAppNative.EXTRAS_CATEGORY));
                    this.cH = extras.getString("clicked");
                    this.cd = extras.getBoolean("shouldClose", false);
                    this.selectedAd = (BannerAdData) extras.getSerializable("selected");
                    this.cG = b.R().l(this.bannerAd);
                    f = b.R().f(this.bannerAd);
                    JSONArray jSONArray2 = new JSONArray();
                    if (f != null) {
                    }
                    this.cJ = new JSONObject().put("apps", jSONArray2).toString();
                    Banner banner3 = new Banner(this);
                    this.cL = banner3;
                    linearLayout.addView(banner3);
                    this.cL.getLayoutParams().height = -1;
                    this.cL.getLayoutParams().width = -1;
                    this.cL.setPlacementId(this.ap);
                    Banner banner22 = this.cL;
                    String str22 = this.cI;
                    hashCode = str22.hashCode();
                    if (hashCode == -1966536496) {
                    }
                    if (c2 != 0) {
                    }
                    banner22.setBannerSize(bannerSize);
                    this.cL.loadAd(null);
                }
            } else if (string.equals("LARGE_BANNER")) {
                c = 1;
                if (c != 0) {
                }
                BannerAd bannerAd322 = bannerAd2;
                this.bannerAd = bannerAd322;
                bannerAd322.setPostback(extras.getString("postback"));
                this.bannerAd.setCategories(extras.getString(StartAppNative.EXTRAS_CATEGORY));
                this.cH = extras.getString("clicked");
                this.cd = extras.getBoolean("shouldClose", false);
                this.selectedAd = (BannerAdData) extras.getSerializable("selected");
                this.cG = b.R().l(this.bannerAd);
                f = b.R().f(this.bannerAd);
                JSONArray jSONArray22 = new JSONArray();
                if (f != null) {
                }
                this.cJ = new JSONObject().put("apps", jSONArray22).toString();
                Banner banner32 = new Banner(this);
                this.cL = banner32;
                linearLayout.addView(banner32);
                this.cL.getLayoutParams().height = -1;
                this.cL.getLayoutParams().width = -1;
                this.cL.setPlacementId(this.ap);
                Banner banner222 = this.cL;
                String str222 = this.cI;
                hashCode = str222.hashCode();
                if (hashCode == -1966536496) {
                }
                if (c2 != 0) {
                }
                banner222.setBannerSize(bannerSize);
                this.cL.loadAd(null);
            }
            c = 65535;
            if (c != 0) {
            }
            BannerAd bannerAd3222 = bannerAd2;
            this.bannerAd = bannerAd3222;
            bannerAd3222.setPostback(extras.getString("postback"));
            this.bannerAd.setCategories(extras.getString(StartAppNative.EXTRAS_CATEGORY));
            this.cH = extras.getString("clicked");
            this.cd = extras.getBoolean("shouldClose", false);
            this.selectedAd = (BannerAdData) extras.getSerializable("selected");
            this.cG = b.R().l(this.bannerAd);
            f = b.R().f(this.bannerAd);
            JSONArray jSONArray222 = new JSONArray();
            if (f != null) {
            }
            this.cJ = new JSONObject().put("apps", jSONArray222).toString();
            Banner banner322 = new Banner(this);
            this.cL = banner322;
            linearLayout.addView(banner322);
            this.cL.getLayoutParams().height = -1;
            this.cL.getLayoutParams().width = -1;
            this.cL.setPlacementId(this.ap);
            Banner banner2222 = this.cL;
            String str2222 = this.cI;
            hashCode = str2222.hashCode();
            if (hashCode == -1966536496) {
            }
            if (c2 != 0) {
            }
            banner2222.setBannerSize(bannerSize);
            this.cL.loadAd(null);
        } catch (Throwable unused) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0008 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000d */
    public void onDestroy() {
        super.onDestroy();
        this.userAction.destroy();
        this.bannerAd.destroy();
        this.cL.destroy();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0055  */
    private static BannerSize n(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -1966536496) {
            if (hashCode != -96588539) {
                if (hashCode == 1951953708 && str.equals(AdPreferences.TYPE_BANNER)) {
                    c = 0;
                    if (c != 0) {
                        return BannerSize.BANNER;
                    }
                    if (c == 1) {
                        return BannerSize.LARGE_BANNER;
                    }
                    if (c == 2) {
                        return BannerSize.MEDIUM_RECTANGLE;
                    }
                    throw new IllegalArgumentException("Wrong banner size " + str);
                }
            } else if (str.equals("MEDIUM_RECTANGLE")) {
                c = 2;
                if (c != 0) {
                }
            }
        } else if (str.equals("LARGE_BANNER")) {
            c = 1;
            if (c != 0) {
            }
        }
        c = 65535;
        if (c != 0) {
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005b  */
    public final Ad c(String str, String str2) {
        char c;
        int hashCode = str2.hashCode();
        if (hashCode != -1966536496) {
            if (hashCode != -96588539) {
                if (hashCode == 1951953708 && str2.equals(AdPreferences.TYPE_BANNER)) {
                    c = 0;
                    if (c != 0) {
                        return new SmallBannerAd(this, str);
                    }
                    if (c == 1) {
                        return new LargeBannerAd(this, str);
                    }
                    if (c == 2) {
                        return new MediumRectangleAd(this, str);
                    }
                    throw new IllegalArgumentException("Wrong banner size " + str2);
                }
            } else if (str2.equals("MEDIUM_RECTANGLE")) {
                c = 2;
                if (c != 0) {
                }
            }
        } else if (str2.equals("LARGE_BANNER")) {
            c = 1;
            if (c != 0) {
            }
        }
        c = 65535;
        if (c != 0) {
        }
    }

    private class a extends g {
        /* access modifiers changed from: protected */
        @Override // com.appnext.banners.g
        public final String getJSurl() {
            return "https://cdn.appnext.com/tools/sdk/banner/2.4.3/result.min.js";
        }

        private a() {
        }

        /* access modifiers changed from: protected */
        @Override // com.appnext.banners.g
        public final String getFallbackScript() {
            return new com.appnext.core.result.b().J();
        }

        /* access modifiers changed from: protected */
        @Override // com.appnext.banners.g
        public final int getLayout() {
            return R.layout.apnxt_full_screen;
        }

        /* access modifiers changed from: protected */
        @Override // com.appnext.banners.a
        public final BannerAd getBannerAd() {
            return BannerActivity.this.bannerAd;
        }

        /* access modifiers changed from: protected */
        @Override // com.appnext.banners.a, com.appnext.banners.g
        public final BannerAdData getSelectedAd() {
            return BannerActivity.this.selectedAd;
        }

        @Override // com.appnext.banners.e, com.appnext.banners.a
        public final void loadAd(BannerAdRequest bannerAdRequest) {
            inflateView(0, null);
        }

        /* access modifiers changed from: protected */
        @Override // com.appnext.banners.a
        public final BannerAdRequest getAdRequest() {
            return new BannerAdRequest();
        }

        /* access modifiers changed from: protected */
        @Override // com.appnext.banners.g
        public final g.a getWebInterface() {
            return new b();
        }

        /* access modifiers changed from: protected */
        @Override // com.appnext.banners.g
        public final WebViewClient getWebViewClient() {
            return new C0003a();
        }

        public class b extends g.a {
            public b() {
                super();
            }

            @JavascriptInterface
            public final void adClicked(String str, int i) {
                BannerActivity bannerActivity = BannerActivity.this;
                b.R();
                bannerActivity.cF = new BannerAdData((AppnextAd) b.parseAd(str));
                q qVar = BannerActivity.this.userAction;
                BannerAdData bannerAdData = BannerActivity.this.cF;
                qVar.a(bannerAdData, BannerActivity.this.cF.getAppURL() + "&tem_id=" + a.this.getBannerAd().getTemId(a.this.getSelectedAd()) + "1", a.this.getPlacementId(), new e.a() {
                    /* class com.appnext.banners.BannerActivity.a.b.AnonymousClass1 */

                    @Override // com.appnext.core.e.a
                    public final void error(String str) {
                    }

                    @Override // com.appnext.core.e.a
                    public final void onMarket(String str) {
                        if (BannerActivity.this.cd) {
                            BannerActivity.this.finish();
                        }
                    }
                });
            }

            @JavascriptInterface
            public final void impression(String str) {
                b.R();
                BannerActivity.this.userAction.e((AppnextAd) b.parseAd(str));
            }

            @JavascriptInterface
            public final void postView(String str) {
                b.R();
                BannerAdData bannerAdData = new BannerAdData((AppnextAd) b.parseAd(str));
                q qVar = BannerActivity.this.userAction;
                qVar.a(bannerAdData, bannerAdData.getImpressionURL() + "&tem_id=" + a.this.getBannerAd().getTemId(a.this.getSelectedAd()) + "1", null);
            }

            @Override // com.appnext.banners.g.a
            @JavascriptInterface
            public final void openLink(String str) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                intent.addFlags(268435456);
                BannerActivity.this.startActivity(intent);
            }
        }

        /* renamed from: com.appnext.banners.BannerActivity$a$a  reason: collision with other inner class name */
        public class C0003a extends WebViewClient {
            public C0003a() {
            }

            @Override // android.webkit.WebViewClient
            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null) {
                    return false;
                }
                if (!str.startsWith(Constants.HTTP)) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                webView.loadUrl(str);
                return true;
            }

            public final void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                new Thread(new Runnable() {
                    /* class com.appnext.banners.BannerActivity.a.C0003a.AnonymousClass1 */

                    public final void run() {
                        final String b = f.b(a.this.context, false);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            /* class com.appnext.banners.BannerActivity.a.C0003a.AnonymousClass1.AnonymousClass1 */

                            public final void run() {
                                try {
                                    a aVar = a.this;
                                    aVar.loadJS("javascript:(function() { try { Appnext.setParams(" + a.this.getConfigParams().put("did", b).toString() + "); } catch(err){ Appnext.jsError(err.message); }})()");
                                } catch (Throwable unused) {
                                }
                                a aVar2 = a.this;
                                aVar2.loadJS("javascript:(function() { try { Appnext.load(" + a.this.getSelectedAd().getAdJSON() + "," + BannerActivity.this.cJ + "," + BannerActivity.this.cG + "); } catch(err){ Appnext.jsError(err.message); }})()");
                            }
                        });
                    }
                }).start();
            }
        }
    }

    private class Banner extends BannerView {
        public Banner(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        @Override // com.appnext.banners.BannerView, com.appnext.banners.BaseBannerView
        public e getBannerAdapter() {
            return BannerActivity.this.cK;
        }
    }
}
