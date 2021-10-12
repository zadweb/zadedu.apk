package com.yandex.metrica.impl.ob;

import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.IIdentifierCallback;
import com.yandex.metrica.impl.ay;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.ob.dx;
import com.yandex.metrica.impl.utils.i;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class dv implements dw {

    /* renamed from: a  reason: collision with root package name */
    static final Map<du, IIdentifierCallback.Reason> f1103a = Collections.unmodifiableMap(new HashMap<du, IIdentifierCallback.Reason>() {
        /* class com.yandex.metrica.impl.ob.dv.AnonymousClass1 */

        {
            put(du.UNKNOWN, IIdentifierCallback.Reason.UNKNOWN);
            put(du.NETWORK, IIdentifierCallback.Reason.NETWORK);
            put(du.PARSE, IIdentifierCallback.Reason.INVALID_RESPONSE);
        }
    });
    private final ay b;
    private final dx c;
    private final bz d;
    private final Object e = new Object();
    private final Map<IIdentifierCallback, Object> f = new WeakHashMap();
    private final Map<IIdentifierCallback, Object> g = new WeakHashMap();

    public dv(ay ayVar, String str, bz bzVar) {
        this.b = ayVar;
        this.d = bzVar;
        this.c = new dx(bzVar, str);
        e();
    }

    @Override // com.yandex.metrica.impl.ob.dw
    public String a() {
        return this.c.c();
    }

    @Override // com.yandex.metrica.impl.ob.dw
    public String b() {
        return this.d.a();
    }

    @Override // com.yandex.metrica.impl.ob.dw
    public String c() {
        return this.c.d();
    }

    public void a(Bundle bundle) {
        synchronized (this.e) {
            this.c.a(bundle);
            this.c.a(System.currentTimeMillis() / 1000);
        }
        e();
    }

    public void d() {
        if (!this.c.a(dx.a.ALL) || this.c.a()) {
            this.b.c();
        }
    }

    public void a(List<String> list) {
        List<String> b2 = this.c.b();
        if (bk.a(list)) {
            if (!bk.a(b2)) {
                this.c.a((List<String>) null);
                this.b.a((List<String>) null);
            }
        } else if (!bk.a(list, b2)) {
            this.c.a(list);
            this.b.a(list);
        } else {
            this.b.a(b2);
        }
    }

    public void a(String str) {
        this.b.c(str);
    }

    /* access modifiers changed from: package-private */
    public void e() {
        WeakHashMap weakHashMap = new WeakHashMap();
        HashMap hashMap = new HashMap();
        WeakHashMap weakHashMap2 = new WeakHashMap();
        HashMap hashMap2 = new HashMap();
        synchronized (this.e) {
            if (this.c.a(dx.a.IDENTIFIERS)) {
                weakHashMap.putAll(this.f);
                this.f.clear();
                this.c.b(hashMap);
            }
            if (this.c.a(dx.a.ALL)) {
                weakHashMap2.putAll(this.g);
                this.g.clear();
                this.c.a(hashMap2);
            }
        }
        for (IIdentifierCallback iIdentifierCallback : weakHashMap.keySet()) {
            iIdentifierCallback.onReceive(new HashMap(hashMap));
        }
        for (IIdentifierCallback iIdentifierCallback2 : weakHashMap2.keySet()) {
            iIdentifierCallback2.onReceive(new HashMap(hashMap2));
        }
        weakHashMap.clear();
        hashMap.clear();
        weakHashMap2.clear();
        hashMap2.clear();
    }

    public void b(Bundle bundle) {
        IIdentifierCallback.Reason reason = f1103a.get(du.b(bundle));
        WeakHashMap weakHashMap = new WeakHashMap();
        WeakHashMap weakHashMap2 = new WeakHashMap();
        synchronized (this.e) {
            weakHashMap.putAll(this.f);
            weakHashMap2.putAll(this.g);
            this.f.clear();
            this.g.clear();
        }
        for (IIdentifierCallback iIdentifierCallback : weakHashMap.keySet()) {
            iIdentifierCallback.onRequestError(reason);
        }
        for (IIdentifierCallback iIdentifierCallback2 : weakHashMap2.keySet()) {
            iIdentifierCallback2.onRequestError(reason);
        }
        weakHashMap.clear();
        weakHashMap2.clear();
    }

    public void a(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                boolean z = true;
                if (!TextUtils.isEmpty(key) && !key.contains(":") && !key.contains(",") && !key.contains("&")) {
                    String value = entry.getValue();
                    if (TextUtils.isEmpty(value) || i.a(value, -1L) == -1) {
                        z = false;
                    }
                    if (z) {
                        hashMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        this.b.a(hashMap);
    }
}
