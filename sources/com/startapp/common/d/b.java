package com.startapp.common.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: StartAppSDK */
public class b implements CookieStore {

    /* renamed from: a  reason: collision with root package name */
    private final CookieStore f597a = new CookieManager().getCookieStore();
    private final SharedPreferences b;

    public b(Context context) {
        HttpCookie httpCookie;
        this.b = context.getApplicationContext().getSharedPreferences("com.startapp.android.publish.CookiePrefsFile", 0);
        String string = this.b.getString("names", null);
        if (string != null) {
            String[] split = TextUtils.split(string, ";");
            for (String str : split) {
                String string2 = this.b.getString("cookie_" + str, null);
                if (!(string2 == null || (httpCookie = (HttpCookie) com.startapp.common.c.b.a(string2, HttpCookie.class)) == null)) {
                    if (httpCookie.hasExpired()) {
                        a(httpCookie);
                        b();
                    } else {
                        this.f597a.add(URI.create(httpCookie.getDomain()), httpCookie);
                    }
                }
            }
        }
    }

    public void add(URI uri, HttpCookie httpCookie) {
        String b2 = b(httpCookie);
        this.f597a.add(uri, httpCookie);
        a(httpCookie, b2);
        b();
    }

    @Override // java.net.CookieStore
    public List<HttpCookie> get(URI uri) {
        return this.f597a.get(uri);
    }

    @Override // java.net.CookieStore
    public List<HttpCookie> getCookies() {
        return this.f597a.getCookies();
    }

    @Override // java.net.CookieStore
    public List<URI> getURIs() {
        return this.f597a.getURIs();
    }

    public boolean remove(URI uri, HttpCookie httpCookie) {
        if (!this.f597a.remove(uri, httpCookie)) {
            return false;
        }
        a(httpCookie);
        b();
        return true;
    }

    public boolean removeAll() {
        if (!this.f597a.removeAll()) {
            return false;
        }
        a();
        b();
        return true;
    }

    private void a(HttpCookie httpCookie, String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("cookie_" + str, com.startapp.common.c.b.a(httpCookie));
        edit.commit();
    }

    private void a() {
        SharedPreferences.Editor edit = this.b.edit();
        edit.clear();
        edit.commit();
    }

    private void a(HttpCookie httpCookie) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove("cookie_" + b(httpCookie));
        edit.commit();
    }

    private void b() {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("names", TextUtils.join(";", c()));
        edit.commit();
    }

    private String b(HttpCookie httpCookie) {
        return httpCookie.getDomain() + "_" + httpCookie.getName();
    }

    private Set<String> c() {
        HashSet hashSet = new HashSet();
        for (HttpCookie httpCookie : this.f597a.getCookies()) {
            hashSet.add(b(httpCookie));
        }
        return hashSet;
    }
}
