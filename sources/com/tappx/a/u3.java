package com.tappx.a;

import android.content.Context;
import android.view.View;
import com.tappx.a.t3;
import com.tappx.a.x3;

public class u3 implements t3 {

    /* renamed from: a  reason: collision with root package name */
    private t3.b f833a;
    private final Context b;
    private x3 c;
    private x3.d d = new a();

    class a implements x3.d {
        a() {
        }

        @Override // com.tappx.a.x3.d
        public void a() {
            j0.d("EO6JnLxOUsi6kIdAfPMA//Kib626NzkhHKkXIfYGxxc", new Object[0]);
            if (u3.this.f833a != null) {
                u3.this.f833a.d();
            }
        }

        @Override // com.tappx.a.x3.d
        public void b() {
            if (u3.this.f833a != null) {
                u3.this.f833a.b();
            }
        }

        @Override // com.tappx.a.x3.d
        public void c() {
            j0.d("xhf99ytwwl8bVeOsPAy3pg", new Object[0]);
            if (u3.this.f833a != null) {
                u3.this.f833a.a(u3.this.c);
            }
        }
    }

    u3(Context context) {
        this.b = context;
    }

    @Override // com.tappx.a.t3
    public void a() {
    }

    @Override // com.tappx.a.t3
    public void a(boolean z) {
    }

    @Override // com.tappx.a.t3
    public void destroy() {
        x3 x3Var = this.c;
        if (x3Var != null) {
            x3Var.setListener(null);
            this.c.destroy();
            this.c = null;
        }
    }

    @Override // com.tappx.a.t3
    public void a(t3.b bVar) {
        this.f833a = bVar;
    }

    @Override // com.tappx.a.t3
    public View a(b4 b4Var, String str, t3.a aVar) {
        j0.d("h0fTNqXwKZ+DG4kdf/GC5w", new Object[0]);
        if (this.c == null) {
            x3 x3Var = new x3(this.b, a(aVar));
            this.c = x3Var;
            x3Var.setListener(this.d);
            if (b4Var == b4.INLINE || o.f772a) {
                this.c.a();
            }
            this.c.a(str);
        }
        return this.c;
    }

    private boolean a(t3.a aVar) {
        if (aVar == null) {
            return false;
        }
        return aVar.a();
    }
}
