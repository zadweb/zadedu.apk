package com.truenet.android.a;

import a.a.b.b.e;
import a.a.b.b.h;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.concurrent.SynchronousQueue;

/* compiled from: StartAppSDK */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public static final a f905a;
    private static final String b;
    private static String c;

    /* compiled from: StartAppSDK */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(e eVar) {
            this();
        }

        public final String a(Context context) {
            h.b(context, "context");
            if (i.c != null) {
                String str = i.c;
                if (str == null) {
                    h.a();
                }
                return str;
            }
            SynchronousQueue synchronousQueue = new SynchronousQueue();
            new Handler(Looper.getMainLooper()).post(new RunnableC0036a(context, synchronousQueue));
            i.c = (String) synchronousQueue.take();
            String str2 = i.c;
            if (str2 == null) {
                h.a();
            }
            return str2;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.truenet.android.a.i$a$a  reason: collision with other inner class name */
        /* compiled from: StartAppSDK */
        public static final class RunnableC0036a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Context f906a;
            final /* synthetic */ SynchronousQueue b;

            RunnableC0036a(Context context, SynchronousQueue synchronousQueue) {
                this.f906a = context;
                this.b = synchronousQueue;
            }

            public final void run() {
                try {
                    WebSettings settings = new WebView(this.f906a).getSettings();
                    h.a((Object) settings, "WebView(context).settings");
                    this.b.offer(settings.getUserAgentString());
                } catch (Exception e) {
                    Log.e(i.b, e.getMessage());
                    this.b.offer("undefined");
                }
            }
        }
    }

    static {
        a aVar = new a(null);
        f905a = aVar;
        b = aVar.getClass().getSimpleName();
    }
}
