package com.appnext.core;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class e {
    private static e gA = null;
    private static final long go = 8000;
    private static final long gp = 15000;
    private static final String gq = "com.android.vending";
    private static final String gr = "market://";
    private static final String gs = "https://play.google.com/store";
    private Context context;
    private WebView gt;
    private WebView gu;
    private a gv = new a() {
        /* class com.appnext.core.e.AnonymousClass1 */

        @Override // com.appnext.core.e.a
        public final void onMarket(String str) {
            e.this.gz = 0;
            if (e.this.gy.size() != 0) {
                b bVar = (b) e.this.gy.get(0);
                if (bVar.gL != null) {
                    bVar.gL.onMarket(str);
                }
                try {
                    String str2 = "https://admin.appnext.com/tools/navtac.html?bid=" + ((b) e.this.gy.get(0)).gM + "&guid=" + f.m("admin.appnext.com", "applink") + "&url=" + URLEncoder.encode(str, "UTF-8");
                    if (e.this.gu == null) {
                        e.this.gu = new WebView(e.this.context);
                        e.this.gu.getSettings().setJavaScriptEnabled(true);
                        e.this.gu.getSettings().setDomStorageEnabled(true);
                        if (Build.VERSION.SDK_INT >= 21) {
                            e.this.gu.getSettings().setMixedContentMode(0);
                        }
                        e.this.gu.setWebViewClient(new WebViewClient() {
                            /* class com.appnext.core.e.AnonymousClass1.AnonymousClass1 */

                            @Override // android.webkit.WebViewClient
                            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                                if (str == null || str.contains("about:blank")) {
                                    return false;
                                }
                                webView.loadUrl(str);
                                return true;
                            }
                        });
                    }
                    e.this.gu.loadUrl("about:blank");
                    e.this.gu.loadUrl(str2);
                    new StringBuilder("store url: ").append(str2);
                    e.this.bc();
                    e.this.bb();
                } catch (UnsupportedEncodingException unused) {
                    e.this.bb();
                }
            }
        }

        @Override // com.appnext.core.e.a
        public final void error(String str) {
            e.this.gz = 0;
            if (e.this.gy.size() != 0) {
                b bVar = (b) e.this.gy.get(0);
                if (bVar.gL != null) {
                    bVar.gL.error(str);
                }
                e.this.bb();
            }
        }
    };
    private Runnable gw = new Runnable() {
        /* class com.appnext.core.e.AnonymousClass2 */

        public final void run() {
            if (!(e.this.gv == null || e.this.gt == null)) {
                e.this.gv.error(e.this.gt.getUrl());
                e.this.gt.stopLoading();
            }
            e.this.bb();
        }
    };
    private List gx;
    private final ArrayList<b> gy = new ArrayList<>();
    private int gz = 0;
    private Handler handler = new Handler(Looper.getMainLooper());

    public interface a {
        void error(String str);

        void onMarket(String str);
    }

    /* access modifiers changed from: private */
    public class b {
        String aQ;
        String gJ;
        String gK;
        a gL;
        String gM;
        long gN;

        b(String str, String str2, String str3, String str4, a aVar, long j) {
            this.gJ = str;
            this.gK = str2;
            this.aQ = str3;
            this.gL = aVar;
            this.gM = str4;
            this.gN = j;
        }

        public final void onMarket(String str) {
            a aVar = this.gL;
            if (aVar != null) {
                aVar.onMarket(str);
            }
        }

        public final void error(String str) {
            a aVar = this.gL;
            if (aVar != null) {
                aVar.error(str);
            }
        }
    }

    public static e k(Context context2) {
        if (gA == null) {
            synchronized (e.class) {
                if (gA == null) {
                    gA = new e(context2);
                }
            }
        }
        return gA;
    }

    private e(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public final void a(String str, String str2, String str3, String str4, a aVar) {
        a(str, str2, str3, str4, aVar, go);
    }

    public final void a(String str, String str2, String str3, String str4, a aVar, long j) {
        if (this.context != null) {
            if (str3 != null) {
                Iterator<b> it = this.gy.iterator();
                while (it.hasNext()) {
                    if (it.next().aQ.equals(str3)) {
                        return;
                    }
                }
                if (str3.endsWith("&ox=0")) {
                    this.gy.add(new b(str, str2, str3, str4, aVar, j));
                    new StringBuilder("--ck-- in ").append(str3);
                } else {
                    this.gz = 0;
                    if (this.gy.size() > 0 && !this.gy.get(0).aQ.endsWith("&ox=0")) {
                        new StringBuilder("--ck-- out ").append(this.gy.get(0).aQ);
                        this.gy.get(0).gL = null;
                        this.gy.remove(0);
                        new StringBuilder("--ck-- size ").append(this.gy.size());
                    }
                    this.gy.add(0, new b(str, str2, str3, str4, aVar, j));
                    new StringBuilder("--ck-- in ").append(str3);
                }
            }
            if (this.gy.size() <= 0 || this.gz == 1) {
                StringBuilder sb = new StringBuilder("vta waiting -  ");
                sb.append(str4);
                sb.append(" - ");
                sb.append(str3);
                return;
            }
            this.gz = 1;
            StringBuilder sb2 = new StringBuilder("vta load -  ");
            sb2.append(this.gy.get(0).gM);
            sb2.append(" - ");
            sb2.append(this.gy.get(0).aQ);
            a(this.gy.get(0));
        }
    }

    private void setState(int i) {
        this.gz = i;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void bc() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
    }

    private boolean R(String str) {
        try {
            this.context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private void S(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        for (ResolveInfo resolveInfo : this.context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                intent.addFlags(268435456);
                intent.addFlags(2097152);
                intent.addFlags(67108864);
                intent.setComponent(componentName);
                this.context.startActivity(intent);
                return;
            }
        }
    }

    private void T(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setFlags(268435456);
        this.context.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void openMarket(String str) {
        try {
            if ((str.startsWith(gr) || str.startsWith(gs)) && R("com.android.vending")) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                Iterator<ResolveInfo> it = this.context.getPackageManager().queryIntentActivities(intent, 0).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ResolveInfo next = it.next();
                    if (next.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                        ActivityInfo activityInfo = next.activityInfo;
                        ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                        intent.addFlags(268435456);
                        intent.addFlags(2097152);
                        intent.addFlags(67108864);
                        intent.setComponent(componentName);
                        this.context.startActivity(intent);
                        break;
                    }
                }
            } else {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent2.setFlags(268435456);
                this.context.startActivity(intent2);
            }
            if (this.gv != null) {
                this.gv.onMarket(str);
            }
        } catch (Throwable unused) {
            a aVar = this.gv;
            if (aVar != null) {
                aVar.error(str);
            }
        }
    }

    private void a(final b bVar) {
        try {
            new StringBuilder("ClickMarketUrl - ").append(bVar.gK);
            if (!TextUtils.isEmpty(bVar.gK)) {
                openMarket(bVar.gK);
                new Thread(new Runnable() {
                    /* class com.appnext.core.e.AnonymousClass3 */

                    public final void run() {
                        try {
                            f.a(bVar.gJ, (HashMap<String, String>) null);
                        } catch (Throwable unused) {
                        }
                    }
                }).start();
                return;
            }
            bc();
            if (this.gt == null) {
                WebView webView = new WebView(this.context);
                this.gt = webView;
                webView.getSettings().setJavaScriptEnabled(true);
                this.gt.getSettings().setDomStorageEnabled(true);
                if (Build.VERSION.SDK_INT >= 21) {
                    this.gt.getSettings().setMixedContentMode(0);
                }
                this.gt.setWebViewClient(new WebViewClient() {
                    /* class com.appnext.core.e.AnonymousClass4 */

                    @Override // android.webkit.WebViewClient
                    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                        String str2;
                        if (str == null) {
                            return false;
                        }
                        new StringBuilder("redirect url: ").append(str);
                        if (str.startsWith("https://play.google.com/store/apps/")) {
                            str = str.replace("https://play.google.com/store/apps/", e.gr);
                        }
                        if (str.contains("about:blank")) {
                            return false;
                        }
                        if (str.startsWith("http://") || str.startsWith("https://")) {
                            Intent b = e.this.b(e.U(str).setComponent(null));
                            if (b != null) {
                                e.this.bc();
                                if (e.this.gv != null) {
                                    e.this.gv.onMarket(str);
                                }
                                e.this.context.startActivity(b);
                                return true;
                            }
                            webView.loadUrl(str);
                            return true;
                        } else if (str.startsWith("intent://")) {
                            try {
                                Intent parseUri = Intent.parseUri(str, 1);
                                if (e.this.context.getPackageManager().resolveActivity(parseUri, 65536) != null) {
                                    e.this.bc();
                                    if (e.this.gv != null) {
                                        e.this.gv.onMarket(parseUri.getData().toString());
                                    }
                                    return true;
                                }
                                if (parseUri.getExtras() != null && parseUri.getExtras().containsKey("browser_fallback_url") && !parseUri.getExtras().getString("browser_fallback_url").equals("")) {
                                    str2 = parseUri.getExtras().getString("browser_fallback_url");
                                } else if (!parseUri.getExtras().containsKey("market_referrer") || parseUri.getExtras().getString("market_referrer").equals("")) {
                                    e.this.bc();
                                    if (e.this.gv != null) {
                                        e.this.gv.error(str);
                                    }
                                    return true;
                                } else {
                                    str2 = "market://details?id=" + parseUri.getPackage() + "&referrer=" + parseUri.getExtras().getString("market_referrer");
                                }
                                e.this.bc();
                                if (e.this.gv != null) {
                                    e.this.gv.onMarket(str2);
                                }
                                return true;
                            } catch (Throwable unused) {
                                return false;
                            }
                        } else {
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setData(Uri.parse(str));
                            try {
                                if (e.this.context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                                    e.this.bc();
                                    e.this.openMarket(str);
                                    if (e.this.gv != null) {
                                        e.this.gv.onMarket(str);
                                    }
                                    return true;
                                }
                                webView.loadUrl(str);
                                return false;
                            } catch (Throwable unused2) {
                            }
                        }
                    }
                });
            }
            this.gt.stopLoading();
            this.gt.loadUrl("about:blank");
            this.gx = a(this.context, U(bVar.aQ).setComponent(null));
            this.gt.loadUrl(bVar.aQ);
            new StringBuilder("appurl: ").append(bVar.aQ);
            this.handler.postDelayed(this.gw, bVar.aQ.endsWith("&ox=0") ? gp : bVar.gN);
        } catch (Throwable unused) {
            a aVar = this.gv;
            if (aVar != null) {
                aVar.error(bVar.aQ);
            }
            bb();
        }
    }

    public final void e(final AppnextAd appnextAd) {
        new Thread(new Runnable() {
            /* class com.appnext.core.e.AnonymousClass5 */

            public final void run() {
                try {
                    f.a(appnextAd.getImpressionURL(), (HashMap<String, String>) null);
                } catch (Throwable unused) {
                }
            }
        }).start();
    }

    public final void f(final AppnextAd appnextAd) {
        new Thread(new Runnable() {
            /* class com.appnext.core.e.AnonymousClass6 */

            public final void run() {
                try {
                    f.a(appnextAd.getImpressionURL() + "&device=" + f.be() + "&ox=0", (HashMap<String, String>) null);
                } catch (Throwable unused) {
                }
            }
        }).start();
    }

    public final void a(final String str, final String str2, final String str3, final String str4, final String str5, final String str6) {
        new Thread(new Runnable() {
            /* class com.appnext.core.e.AnonymousClass7 */

            public final void run() {
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("guid", str);
                    hashMap.put("bannerId", str2);
                    hashMap.put("placementId", str3);
                    hashMap.put("vid", str4);
                    hashMap.put("url", str5);
                    f.a("https://admin.appnext.com/AdminService.asmx/" + str6, hashMap);
                } catch (Throwable unused) {
                }
            }
        }).start();
    }

    private static List a(Context context2, Intent intent) {
        List<ResolveInfo> queryIntentActivities = context2.getPackageManager().queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            arrayList.add(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
        }
        return arrayList;
    }

    public final Intent b(Intent intent) {
        List<ComponentName> a2 = a(this.context, intent);
        new HashSet();
        for (ComponentName componentName : a2) {
            if (!this.gx.contains(componentName)) {
                Intent intent2 = new Intent();
                intent2.setComponent(componentName);
                return intent2;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static Intent U(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        return intent;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void bb() {
        this.gz = 0;
        if (this.gy.size() != 0) {
            new StringBuilder("--ck-- out ").append(this.gy.get(0).aQ);
            this.gy.get(0).gL = null;
            this.gy.remove(0);
            new StringBuilder("--ck-- size ").append(this.gy.size());
            a(null, null, null, null, null);
        }
    }
}
