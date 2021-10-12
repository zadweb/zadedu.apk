package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.bz;
import com.yandex.metrica.impl.ob.dt;
import com.yandex.metrica.impl.utils.i;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class g {

    /* renamed from: a  reason: collision with root package name */
    private final bz f1002a;
    private final Executor b;
    private final c c;
    private volatile Map<String, Long> d = null;

    public g(bz bzVar, c cVar, Executor executor) {
        this.f1002a = bzVar;
        this.b = executor;
        this.c = cVar;
        b();
        this.b.execute(new Runnable() {
            /* class com.yandex.metrica.impl.g.AnonymousClass1 */

            public void run() {
                HashMap<String, String> a2 = g.this.c.a();
                HashMap hashMap = new HashMap();
                if (!bk.a(a2)) {
                    for (Map.Entry<String, String> entry : a2.entrySet()) {
                        hashMap.put(entry.getKey(), Long.valueOf(i.a(entry.getValue(), 0L)));
                    }
                }
                g.this.d = hashMap;
            }
        });
    }

    private void b() {
        String l = this.f1002a.l(null);
        dt dtVar = new dt();
        HashMap<String, String> a2 = com.yandex.metrica.impl.utils.g.a(l);
        if (!bk.a(a2)) {
            for (Map.Entry<String, String> entry : a2.entrySet()) {
                dtVar.a(entry.getKey(), i.a(entry.getValue(), Integer.MAX_VALUE));
            }
        }
        this.f1002a.b((String) null);
        this.f1002a.a((String) null);
        this.f1002a.n(null);
    }

    public void a() {
        b();
    }
}
