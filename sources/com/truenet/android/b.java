package com.truenet.android;

import a.a.b.b.h;
import a.a.b.b.i;
import a.a.b.b.l;
import a.a.b.b.m;
import a.a.b.b.n;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.truenet.android.a.j;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ a.a.d.e[] f907a = {n.a(new l(n.a(b.class), "queue", "getQueue()Ljava/util/concurrent/SynchronousQueue;")), n.a(new l(n.a(b.class), "webView", "getWebView()Landroid/webkit/WebView;"))};
    public static final a b;
    private static final String n;
    private static final a.a.e.b o = new a.a.e.b("^\\w+(://){1}.+$");
    private Bitmap c;
    private long d;
    private String e;
    private String f;
    private final List<C0037b> g = new ArrayList();
    private final a.a.c h = a.a.d.a(f.f912a);
    private final a.a.c i = a.a.d.a(new g(this));
    private final Context j;
    private final String k;
    private final int l;
    private final long m;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final SynchronousQueue<String> j() {
        a.a.c cVar = this.h;
        a.a.d.e eVar = f907a[0];
        return (SynchronousQueue) cVar.a();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final WebView k() {
        a.a.c cVar = this.i;
        a.a.d.e eVar = f907a[1];
        return (WebView) cVar.a();
    }

    public b(Context context, String str, int i2, long j2) {
        h.b(context, "context");
        h.b(str, "url");
        this.j = context;
        this.k = str;
        this.l = i2;
        this.m = j2;
        this.e = str;
    }

    public final Bitmap a() {
        return this.c;
    }

    public final int b() {
        return this.g.size();
    }

    public final long c() {
        return this.d;
    }

    public final List<C0037b> d() {
        return a.a.a.g.a((Iterable) this.g);
    }

    public final String e() {
        return this.e;
    }

    public final String f() {
        return this.f;
    }

    /* compiled from: StartAppSDK */
    static final class f extends i implements a.a.b.a.a<SynchronousQueue<String>> {

        /* renamed from: a  reason: collision with root package name */
        public static final f f912a = new f();

        f() {
            super(0);
        }

        /* renamed from: b */
        public final SynchronousQueue<String> a() {
            return new SynchronousQueue<>();
        }
    }

    /* compiled from: StartAppSDK */
    static final class g extends i implements a.a.b.a.a<WebView> {
        final /* synthetic */ b this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        g(b bVar) {
            super(0);
            this.this$0 = bVar;
        }

        /* renamed from: b */
        public final WebView a() {
            try {
                WebView webView = new WebView(this.this$0.j);
                if (Build.VERSION.SDK_INT >= 11) {
                    webView.setLayerType(1, null);
                }
                WebSettings settings = webView.getSettings();
                h.a((Object) settings, com.appnext.core.a.b.hW);
                settings.setJavaScriptEnabled(true);
                webView.setWebChromeClient(new WebChromeClient());
                webView.setWebViewClient(new c());
                return webView;
            } catch (Exception e) {
                Log.e(b.n, e.getMessage());
                return null;
            }
        }
    }

    /* compiled from: StartAppSDK */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(a.a.b.b.e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final boolean a(String str) {
            return a.a.e.c.a(str, "http://play.google.com", false, 2, null) || a.a.e.c.a(str, "https://play.google.com", false, 2, null) || a.a.e.c.a(str, "http://itunes.apple.com", false, 2, null) || a.a.e.c.a(str, "https://itunes.apple.com", false, 2, null) || (!a.a.e.c.a(str, "http://", false, 2, null) && !a.a.e.c.a(str, "https://", false, 2, null) && b.o.a(str));
        }
    }

    static {
        a aVar = new a(null);
        b = aVar;
        n = aVar.getClass().getSimpleName();
    }

    /* renamed from: com.truenet.android.b$b  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    public static final class C0037b {

        /* renamed from: a  reason: collision with root package name */
        private final String f908a;
        private final long b;
        private final int c;
        private final List<String> d;
        private final String e;

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.truenet.android.b$b */
        /* JADX WARN: Multi-variable type inference failed */
        public static /* bridge */ /* synthetic */ C0037b a(C0037b bVar, String str, long j, int i, List list, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = bVar.f908a;
            }
            if ((i2 & 2) != 0) {
                j = bVar.b;
            }
            if ((i2 & 4) != 0) {
                i = bVar.c;
            }
            if ((i2 & 8) != 0) {
                list = bVar.d;
            }
            if ((i2 & 16) != 0) {
                str2 = bVar.e;
            }
            return bVar.a(str, j, i, list, str2);
        }

        public final C0037b a(String str, long j, int i, List<String> list, String str2) {
            h.b(str, "url");
            return new C0037b(str, j, i, list, str2);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof C0037b) {
                    C0037b bVar = (C0037b) obj;
                    if (h.a((Object) this.f908a, (Object) bVar.f908a)) {
                        if (this.b == bVar.b) {
                            if (!(this.c == bVar.c) || !h.a(this.d, bVar.d) || !h.a((Object) this.e, (Object) bVar.e)) {
                                return false;
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            String str = this.f908a;
            int i = 0;
            int hashCode = str != null ? str.hashCode() : 0;
            long j = this.b;
            int i2 = ((((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.c) * 31;
            List<String> list = this.d;
            int hashCode2 = (i2 + (list != null ? list.hashCode() : 0)) * 31;
            String str2 = this.e;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            return "ConnectionInfo(url=" + this.f908a + ", loadTime=" + this.b + ", httpCode=" + this.c + ", cookie=" + this.d + ", redirectUrl=" + this.e + ")";
        }

        public C0037b(String str, long j, int i, List<String> list, String str2) {
            h.b(str, "url");
            this.f908a = str;
            this.b = j;
            this.c = i;
            this.d = list;
            this.e = str2;
        }

        public final String a() {
            return this.f908a;
        }

        public final long b() {
            return this.b;
        }

        public final int c() {
            return this.c;
        }

        public final List<String> d() {
            return this.d;
        }

        public final String e() {
            return this.e;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.truenet.android.b */
    /* JADX WARN: Multi-variable type inference failed */
    static /* bridge */ /* synthetic */ C0037b a(b bVar, String str, List list, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            list = null;
        }
        return bVar.a(str, list);
    }

    private final C0037b a(String str, List<String> list) {
        String str2;
        this.f = null;
        if (b.a(str)) {
            return new C0037b(str, 0, 200, null, null);
        }
        try {
            URL url = new URL(str);
            URLConnection openConnection = url.openConnection();
            if (openConnection != null) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                boolean z = false;
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setConnectTimeout(((int) this.m) * 1000);
                httpURLConnection.setReadTimeout(((int) this.m) * 1000);
                httpURLConnection.addRequestProperty("User-Agent", com.truenet.android.a.i.f905a.a(this.j));
                if (list != null) {
                    List<String> list2 = list;
                    ArrayList arrayList = new ArrayList(a.a.a.g.a(list2, 10));
                    Iterator<T> it = list2.iterator();
                    while (it.hasNext()) {
                        List<HttpCookie> parse = HttpCookie.parse(it.next());
                        h.a((Object) parse, "HttpCookie.parse(it)");
                        arrayList.add((HttpCookie) a.a.a.g.c(parse));
                    }
                    httpURLConnection.setRequestProperty("Cookie", TextUtils.join(r3, arrayList));
                }
                long currentTimeMillis = System.currentTimeMillis();
                httpURLConnection.connect();
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                String headerField = httpURLConnection.getHeaderField("Location");
                if (headerField != null) {
                    a.a.e.b bVar = o;
                    h.a((Object) headerField, "nextUrl");
                    if (!bVar.a(headerField)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(url.getProtocol());
                        sb.append("://");
                        sb.append(url.getHost());
                        h.a((Object) headerField, "nextUrl");
                        if (!a.a.e.c.a(headerField, "/", false, 2, null)) {
                            headerField = '/' + headerField;
                        }
                        sb.append(headerField);
                        headerField = sb.toString();
                    }
                    str2 = headerField;
                } else {
                    str2 = null;
                }
                C0037b bVar2 = new C0037b(str, currentTimeMillis2, httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields().get("Set-Cookie"), str2);
                int responseCode = httpURLConnection.getResponseCode();
                if (200 <= responseCode) {
                    if (299 >= responseCode) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        h.a((Object) inputStream, "inputStream");
                        this.f = a(inputStream);
                        long currentTimeMillis3 = System.currentTimeMillis() - currentTimeMillis;
                        new Handler(Looper.getMainLooper()).post(new e(httpURLConnection, this, list, url, str));
                        String take = j().take();
                        h.a((Object) take, "jsRedirectUrl");
                        if (take.length() == 0) {
                            z = true;
                        }
                        if (z) {
                            return C0037b.a(bVar2, null, currentTimeMillis3, 0, null, null, 29, null);
                        }
                        return C0037b.a(bVar2, null, currentTimeMillis3, 0, null, take, 13, null);
                    }
                }
                if (300 <= responseCode) {
                    if (399 >= responseCode) {
                        return bVar2;
                    }
                }
                return C0037b.a(bVar2, null, 0, 0, null, null, 15, null);
            }
            throw new a.a.h("null cannot be cast to non-null type java.net.HttpURLConnection");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: StartAppSDK */
    public static final class e implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ HttpURLConnection f911a;
        final /* synthetic */ b b;
        final /* synthetic */ List c;
        final /* synthetic */ URL d;
        final /* synthetic */ String e;

        e(HttpURLConnection httpURLConnection, b bVar, List list, URL url, String str) {
            this.f911a = httpURLConnection;
            this.b = bVar;
            this.c = list;
            this.d = url;
            this.e = str;
        }

        public final void run() {
            WebView k = this.b.k();
            if (k != null) {
                k.loadDataWithBaseURL(this.e, this.b.f(), this.f911a.getContentType(), this.f911a.getContentEncoding(), null);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0076 A[Catch:{ all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b A[Catch:{ all -> 0x007f }] */
    private final String a(InputStream inputStream) {
        Throwable th;
        T t;
        BufferedInputStream bufferedInputStream = null;
        m.a aVar = new m.a();
        aVar.element = (T) null;
        try {
            StringBuilder sb = new StringBuilder();
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            try {
                aVar.element = (T) new BufferedReader(new InputStreamReader(bufferedInputStream2));
                m.a aVar2 = new m.a();
                aVar2.element = (T) null;
                while (new d(aVar2, aVar).a() != null) {
                    sb.append((String) aVar2.element);
                }
                String sb2 = sb.toString();
                h.a((Object) sb2, "result.toString()");
                try {
                    T t2 = aVar.element;
                    if (t2 != null) {
                        t2.close();
                    }
                    bufferedInputStream2.close();
                } catch (Throwable th2) {
                    Log.e(getClass().getCanonicalName(), "stream closed with error!", th2);
                }
                return sb2;
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = bufferedInputStream2;
                try {
                    t = aVar.element;
                    if (t != null) {
                        t.close();
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                } catch (Throwable th4) {
                    Log.e(getClass().getCanonicalName(), "stream closed with error!", th4);
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            t = aVar.element;
            if (t != null) {
            }
            if (bufferedInputStream != null) {
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: StartAppSDK */
    public static final class d extends i implements a.a.b.a.a<String> {
        final /* synthetic */ m.a $line;
        final /* synthetic */ m.a $reader;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        d(m.a aVar, m.a aVar2) {
            super(0);
            this.$line = aVar;
            this.$reader = aVar2;
        }

        /* renamed from: b */
        public final String a() {
            this.$line.element = (T) this.$reader.element.readLine();
            return this.$line.element;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0055  */
    public final void g() {
        int c2;
        WebView k2;
        long currentTimeMillis = System.currentTimeMillis();
        Bitmap bitmap = null;
        C0037b a2 = a(this, this.k, null, 2, null);
        if (a2 != null) {
            this.g.add(a2);
            while (true) {
                if ((a2 != null ? a2.e() : null) != null && a(currentTimeMillis)) {
                    String e2 = a2.e();
                    if (e2 == null) {
                        h.a();
                    }
                    a2 = a(e2, a2.d());
                    if (a2 != null) {
                        this.g.add(a2);
                    }
                } else if (a2 != null && 200 <= (c2 = a2.c()) && 299 >= c2 && this.f != null) {
                    k2 = k();
                    if (k2 != null) {
                        bitmap = j.a(k2);
                    }
                    this.c = bitmap;
                }
            }
            k2 = k();
            if (k2 != null) {
            }
            this.c = bitmap;
            long j2 = 0;
            Iterator<T> it = this.g.iterator();
            while (it.hasNext()) {
                j2 += it.next().b();
            }
            this.d = j2;
            this.e = ((C0037b) a.a.a.g.e(this.g)).a();
        }
        if (this.g.isEmpty()) {
            this.d = System.currentTimeMillis() - currentTimeMillis;
        }
    }

    private final boolean a(long j2) {
        int size = this.g.size();
        int i2 = this.l;
        return (size < i2 || i2 == -1) && System.currentTimeMillis() - j2 < this.m * ((long) 1000);
    }

    /* compiled from: StartAppSDK */
    public final class c extends WebViewClient {
        private ScheduledExecutorService b = Executors.newScheduledThreadPool(1);
        private ScheduledFuture<?> c;

        /* JADX WARN: Incorrect args count in method signature: ()V */
        public c() {
        }

        private final void a(WebView webView, String str) {
            a();
            if (str != null) {
                if (webView != null) {
                    webView.stopLoading();
                }
                b.this.j().offer(str);
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            a(webView, String.valueOf(webResourceRequest != null ? webResourceRequest.getUrl() : null));
            return true;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            a(webView, str);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            a();
            this.c = this.b.schedule(new a(this), 1, TimeUnit.SECONDS);
            super.onPageFinished(webView, str);
        }

        /* compiled from: StartAppSDK */
        static final class a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ c f910a;

            a(c cVar) {
                this.f910a = cVar;
            }

            public final void run() {
                b.this.j().offer("");
            }
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            a();
            if (webView != null) {
                webView.stopLoading();
            }
            b.this.j().offer("");
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        private final void a() {
            ScheduledFuture<?> scheduledFuture = this.c;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.c = null;
        }
    }
}
