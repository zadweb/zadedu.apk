package com.tappx.a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tappx.a.o3;

public class x3 extends l3 {
    private boolean d;
    private final o3 e;
    private d f;
    private final o3.a g = new b();

    class a implements View.OnTouchListener {
        a() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            x3.this.e.a(motionEvent);
            int action = motionEvent.getAction();
            if ((action != 0 && action != 1) || view.hasFocus()) {
                return false;
            }
            view.requestFocus();
            return false;
        }
    }

    class b implements o3.a {
        b() {
        }

        @Override // com.tappx.a.o3.a
        public void a() {
            x3.this.d = true;
        }
    }

    public interface d {
        void a();

        void b();

        void c();
    }

    public x3(Context context, boolean z) {
        super(context);
        if (!z) {
            c();
        }
        getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 14) {
            a(true);
        }
        setBackgroundColor(0);
        o3 o3Var = new o3();
        this.e = o3Var;
        o3Var.a(this.g);
        setWebViewClient(new c(this, null));
        setOnTouchListener(new a());
    }

    public void setListener(d dVar) {
        this.f = dVar;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
    }

    private final class c extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        private final t4 f861a;

        private c() {
            this.f861a = new t4();
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (this.f861a.a(str, x3.this.f)) {
                return true;
            }
            if (x3.this.d) {
                x3.this.d = false;
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                intent.addFlags(268435456);
                try {
                    x3.this.getContext().startActivity(intent);
                    if (x3.this.f != null) {
                        x3.this.f.b();
                    }
                    return true;
                } catch (ActivityNotFoundException unused) {
                    j4.a("No activity found to handle this URL " + str);
                }
            }
            return false;
        }

        /* synthetic */ c(x3 x3Var, a aVar) {
            this();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
    }
}
