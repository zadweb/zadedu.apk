package com.tappx.a;

import android.content.Context;
import com.tappx.a.k1;
import com.tappx.a.m0;
import com.tappx.a.n;
import com.tappx.a.t0;
import com.tappx.a.z0;
import java.util.ArrayList;
import java.util.List;

public class b {
    private static volatile b b;

    /* renamed from: a  reason: collision with root package name */
    private final Context f613a;

    private b(Context context) {
        this.f613a = context;
    }

    public static b a(Context context) {
        b bVar = b;
        if (bVar == null) {
            synchronized (b.class) {
                bVar = b;
                if (bVar == null) {
                    b = new b(context.getApplicationContext());
                    return b;
                }
            }
        }
        return bVar;
    }

    private List<m0.b> e() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new z0.a(d()));
        arrayList.add(new k1.c(d(), c().l()));
        arrayList.add(new t0.b(d()));
        return arrayList;
    }

    public n0 b() {
        return new o0(e());
    }

    /* access modifiers changed from: package-private */
    public c c() {
        return c.a(this.f613a);
    }

    public t2 d() {
        return c().e();
    }

    public f3 a() {
        return new f3(n.a.e, n.a.f, n.a.g);
    }
}
