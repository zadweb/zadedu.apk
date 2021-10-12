package com.b.a.a.a.h;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.b.a.a.a.d.a;
import com.b.a.a.a.d.b;
import com.b.a.a.a.e.d;
import com.b.a.a.a.e.f;
import com.b.a.a.a.h.a.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class a implements a.AbstractC0007a {

    /* renamed from: a  reason: collision with root package name */
    private static a f45a = new a();
    private static Handler b = new Handler(Looper.getMainLooper());
    private static Handler c = null;
    private static final Runnable j = new Runnable() {
        /* class com.b.a.a.a.h.a.AnonymousClass2 */

        public void run() {
            a.a().i();
        }
    };
    private static final Runnable k = new Runnable() {
        /* class com.b.a.a.a.h.a.AnonymousClass3 */

        public void run() {
            if (a.c != null) {
                a.c.post(a.j);
                a.c.postDelayed(a.k, 200);
            }
        }
    };
    private List<AbstractC0009a> d = new ArrayList();
    private int e;
    private b f = new b();
    private b g = new b();
    private c h = new c(new c());
    private double i;

    /* renamed from: com.b.a.a.a.h.a$a  reason: collision with other inner class name */
    public interface AbstractC0009a {
        void a(int i, long j);
    }

    a() {
    }

    public static a a() {
        return f45a;
    }

    private void a(long j2) {
        if (this.d.size() > 0) {
            for (AbstractC0009a aVar : this.d) {
                aVar.a(this.e, j2);
            }
        }
    }

    private void a(View view, com.b.a.a.a.d.a aVar, JSONObject jSONObject, d dVar) {
        aVar.a(view, jSONObject, this, dVar == d.PARENT_VIEW);
    }

    private boolean a(View view, JSONObject jSONObject) {
        String a2 = this.g.a(view);
        if (a2 == null) {
            return false;
        }
        com.b.a.a.a.e.b.a(jSONObject, a2);
        this.g.e();
        return true;
    }

    private void b(View view, JSONObject jSONObject) {
        ArrayList<String> b2 = this.g.b(view);
        if (b2 != null) {
            com.b.a.a.a.e.b.a(jSONObject, b2);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void i() {
        j();
        e();
        k();
    }

    private void j() {
        this.e = 0;
        this.i = d.a();
    }

    private void k() {
        a((long) (d.a() - this.i));
    }

    private void l() {
        if (c == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            c = handler;
            handler.post(j);
            c.postDelayed(k, 200);
        }
    }

    private void m() {
        Handler handler = c;
        if (handler != null) {
            handler.removeCallbacks(k);
            c = null;
        }
    }

    @Override // com.b.a.a.a.d.a.AbstractC0007a
    public void a(View view, com.b.a.a.a.d.a aVar, JSONObject jSONObject) {
        d c2;
        if (f.d(view) && (c2 = this.g.c(view)) != d.UNDERLYING_VIEW) {
            JSONObject a2 = aVar.a(view);
            com.b.a.a.a.e.b.a(jSONObject, a2);
            if (!a(view, a2)) {
                b(view, a2);
                a(view, aVar, a2, c2);
            }
            this.e++;
        }
    }

    public void b() {
        l();
    }

    public void c() {
        d();
        this.d.clear();
        b.post(new Runnable() {
            /* class com.b.a.a.a.h.a.AnonymousClass1 */

            public void run() {
                a.this.h.b();
            }
        });
    }

    public void d() {
        m();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.g.c();
        double a2 = d.a();
        com.b.a.a.a.d.a a3 = this.f.a();
        if (this.g.b().size() > 0) {
            this.h.b(a3.a(null), this.g.b(), a2);
        }
        if (this.g.a().size() > 0) {
            JSONObject a4 = a3.a(null);
            a(null, a3, a4, d.PARENT_VIEW);
            com.b.a.a.a.e.b.a(a4);
            this.h.a(a4, this.g.a(), a2);
        } else {
            this.h.b();
        }
        this.g.d();
    }
}
