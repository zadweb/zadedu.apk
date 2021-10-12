package com.b.a.a.a.g;

import android.os.Handler;
import android.webkit.WebView;
import com.b.a.a.a.b.h;
import com.b.a.a.a.c.d;
import java.util.List;

public class c extends a {

    /* renamed from: a  reason: collision with root package name */
    private WebView f43a;
    private List<h> b;
    private final String c;

    public c(List<h> list, String str) {
        this.b = list;
        this.c = str;
    }

    @Override // com.b.a.a.a.g.a
    public void a() {
        super.a();
        j();
    }

    @Override // com.b.a.a.a.g.a
    public void b() {
        super.b();
        new Handler().postDelayed(new Runnable() {
            /* class com.b.a.a.a.g.c.AnonymousClass1 */
            private WebView b = c.this.f43a;

            public void run() {
                this.b.destroy();
            }
        }, 2000);
        this.f43a = null;
    }

    /* access modifiers changed from: package-private */
    public void j() {
        WebView webView = new WebView(com.b.a.a.a.c.c.a().b());
        this.f43a = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        a(this.f43a);
        d.a().a(this.f43a, this.c);
        for (h hVar : this.b) {
            d.a().b(this.f43a, hVar.b().toExternalForm());
        }
    }
}
