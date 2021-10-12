package com.google.android.gms.internal.ads;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.util.Map;

@zzadh
public final class zzart extends zzaru {
    public zzart(zzaqw zzaqw, boolean z) {
        super(zzaqw, z);
    }

    @Override // android.webkit.WebViewClient, com.google.android.gms.internal.ads.zzaqx
    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zza(webView, str, (Map<String, String>) null);
    }
}
