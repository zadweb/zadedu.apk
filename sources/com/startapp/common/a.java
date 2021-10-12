package com.startapp.common;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.startapp.common.a.d;
import com.startapp.common.g;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    String f564a;
    AbstractC0025a b;
    int c;

    /* renamed from: com.startapp.common.a$a  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    public interface AbstractC0025a {
        void a(Bitmap bitmap, int i);
    }

    public a(String str, AbstractC0025a aVar, int i) {
        this.f564a = str;
        this.b = aVar;
        this.c = i;
    }

    public void a() {
        g.a(g.a.HIGH, new Runnable() {
            /* class com.startapp.common.a.AnonymousClass1 */

            public void run() {
                final Bitmap b = d.b(a.this.f564a);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    /* class com.startapp.common.a.AnonymousClass1.AnonymousClass1 */

                    public void run() {
                        if (a.this.b != null) {
                            a.this.b.a(b, a.this.c);
                        }
                    }
                });
            }
        });
    }
}
