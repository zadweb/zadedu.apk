package com.tappx.a;

import android.content.Context;
import com.tappx.a.e6;
import java.io.File;

public class j6 {

    /* access modifiers changed from: package-private */
    public static class a implements e6.c {

        /* renamed from: a  reason: collision with root package name */
        private File f721a = null;
        final /* synthetic */ Context b;

        a(Context context) {
            this.b = context;
        }

        @Override // com.tappx.a.e6.c
        public File a() {
            if (this.f721a == null) {
                this.f721a = new File(this.b.getCacheDir(), "volley");
            }
            return this.f721a;
        }
    }

    public static t5 a(Context context, b6 b6Var) {
        c6 c6Var;
        if (b6Var == null) {
            c6Var = new c6(new h6());
        } else {
            c6Var = new c6(b6Var);
        }
        return a(context, c6Var);
    }

    private static t5 a(Context context, n5 n5Var) {
        t5 t5Var = new t5(new e6(new a(context.getApplicationContext())), n5Var);
        t5Var.b();
        return t5Var;
    }

    public static t5 a(Context context) {
        return a(context, (b6) null);
    }
}
