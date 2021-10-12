package com.yandex.metrica.impl;

import android.content.Context;
import android.os.Handler;
import com.yandex.metrica.b;
import com.yandex.metrica.e;
import com.yandex.metrica.impl.ob.dw;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public class ax {

    /* renamed from: a  reason: collision with root package name */
    private Context f971a;
    private ay b;
    private j c;
    private Handler d;
    private dw e;
    private Map<String, b> f;

    /* synthetic */ ax(byte b2) {
        this();
    }

    private ax() {
        this.f = new HashMap();
    }

    /* access modifiers changed from: package-private */
    public z a(e eVar, boolean z) {
        if (!this.f.containsKey(eVar.getApiKey())) {
            z zVar = new z(this.f971a, eVar, this.b);
            a(zVar);
            zVar.a(eVar, z);
            zVar.a();
            this.b.a(zVar);
            this.f.put(eVar.getApiKey(), zVar);
            return zVar;
        }
        throw new IllegalArgumentException(String.format("Failed to activate AppMetrica with provided API Key. API Key %s has already been used by another reporter.", eVar.getApiKey()));
    }

    /* access modifiers changed from: package-private */
    public synchronized b a(String str) {
        aa aaVar;
        b bVar = this.f.get(str);
        aaVar = bVar;
        if (bVar == null) {
            aa aaVar2 = new aa(this.f971a, aw.f970a.get(str), str, this.b);
            a(aaVar2);
            aaVar2.a();
            this.f.put(str, aaVar2);
            aaVar = aaVar2;
        }
        return aaVar;
    }

    private void a(b bVar) {
        bVar.a(new w(this.d, bVar));
        bVar.a(this.c);
        bVar.a(this.e);
    }

    /* access modifiers changed from: package-private */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        ax f972a = new ax((byte) 0);

        a() {
        }

        /* access modifiers changed from: package-private */
        public a a(Context context) {
            this.f972a.f971a = context;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(ay ayVar) {
            this.f972a.b = ayVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(j jVar) {
            this.f972a.c = jVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(Handler handler) {
            this.f972a.d = handler;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(dw dwVar) {
            this.f972a.e = dwVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ax a() {
            return this.f972a;
        }
    }
}
