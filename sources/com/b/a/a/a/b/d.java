package com.b.a.a.a.b;

import android.webkit.WebView;
import com.b.a.a.a.e.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class d {

    /* renamed from: a  reason: collision with root package name */
    private final g f21a;
    private final WebView b;
    private final List<h> c;
    private final String d;
    private final String e;
    private final e f;

    private d(g gVar, WebView webView, String str, List<h> list, String str2) {
        e eVar;
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        this.f21a = gVar;
        this.b = webView;
        this.d = str;
        if (list != null) {
            arrayList.addAll(list);
            eVar = e.NATIVE;
        } else {
            eVar = e.HTML;
        }
        this.f = eVar;
        this.e = str2;
    }

    public static d a(g gVar, WebView webView, String str) {
        e.a(gVar, "Partner is null");
        e.a(webView, "WebView is null");
        if (str != null) {
            e.a(str, 256, "CustomReferenceData is greater than 256 characters");
        }
        return new d(gVar, webView, null, null, str);
    }

    public static d a(g gVar, String str, List<h> list, String str2) {
        e.a(gVar, "Partner is null");
        e.a((Object) str, "OMID JS script content is null");
        e.a(list, "VerificationScriptResources is null");
        if (str2 != null) {
            e.a(str2, 256, "CustomReferenceData is greater than 256 characters");
        }
        return new d(gVar, null, str, list, str2);
    }

    public g a() {
        return this.f21a;
    }

    public List<h> b() {
        return Collections.unmodifiableList(this.c);
    }

    public WebView c() {
        return this.b;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.d;
    }

    public e f() {
        return this.f;
    }
}
