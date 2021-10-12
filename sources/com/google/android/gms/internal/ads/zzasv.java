package com.google.android.gms.internal.ads;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.webkit.ValueCallback;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public class zzasv extends zzass {
    private boolean zzdfh;
    private boolean zzdfi;

    public zzasv(zzash zzash) {
        super(zzash);
        zzbv.zzeo().zzqe();
    }

    private final synchronized void zzqf() {
        if (!this.zzdfi) {
            this.zzdfi = true;
            zzbv.zzeo().zzqf();
        }
    }

    public synchronized void destroy() {
        if (!this.zzdfh) {
            this.zzdfh = true;
            zzam(false);
            zzakb.v("Initiating WebView self destruct sequence in 3...");
            zzakb.v("Loading blank page in WebView, 2...");
            try {
                super.loadUrl("about:blank");
            } catch (UnsatisfiedLinkError e) {
                zzbv.zzeo().zza(e, "AdWebViewImpl.loadUrlUnsafe");
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // android.webkit.WebView
    public synchronized void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (isDestroyed()) {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
            }
            return;
        }
        super.evaluateJavascript(str, valueCallback);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.Object
    public void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!isDestroyed()) {
                    zzam(true);
                }
                zzqf();
            }
        } finally {
            super.finalize();
        }
    }

    public final synchronized boolean isDestroyed() {
        return this.zzdfh;
    }

    public synchronized void loadData(String str, String str2, String str3) {
        if (!isDestroyed()) {
            super.loadData(str, str2, str3);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!isDestroyed()) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzass
    public synchronized void loadUrl(String str) {
        if (!isDestroyed()) {
            super.loadUrl(str);
        } else {
            zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            super.onDraw(canvas);
        }
    }

    public void onPause() {
        if (!isDestroyed()) {
            super.onPause();
        }
    }

    public void onResume() {
        if (!isDestroyed()) {
            super.onResume();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !isDestroyed() && super.onTouchEvent(motionEvent);
    }

    public void stopLoading() {
        if (!isDestroyed()) {
            super.stopLoading();
        }
    }

    /* access modifiers changed from: protected */
    public void zzam(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzass, com.google.android.gms.internal.ads.zzata
    public final synchronized void zzc(zzasu zzasu) {
        if (isDestroyed()) {
            zzakb.v("Blank page loaded, 1...");
            zzuk();
            return;
        }
        super.zzc(zzasu);
    }

    public final synchronized void zzuk() {
        zzakb.v("Destroying WebView!");
        zzqf();
        zzaoe.zzcvy.execute(new zzasw(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzvw() {
        super.destroy();
    }
}
