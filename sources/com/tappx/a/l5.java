package com.tappx.a;

import android.os.Handler;
import java.util.concurrent.Executor;

public class l5 implements v5 {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f748a;

    class a implements Executor {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Handler f749a;

        a(l5 l5Var, Handler handler) {
            this.f749a = handler;
        }

        public void execute(Runnable runnable) {
            this.f749a.post(runnable);
        }
    }

    /* access modifiers changed from: private */
    public static class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final s5 f750a;
        private final u5 b;
        private final Runnable c;

        public b(s5 s5Var, u5 u5Var, Runnable runnable) {
            this.f750a = s5Var;
            this.b = u5Var;
            this.c = runnable;
        }

        public void run() {
            if (this.f750a.t()) {
                this.f750a.b("canceled-at-delivery");
                return;
            }
            if (this.b.a()) {
                this.f750a.a((Object) this.b.f835a);
            } else {
                this.f750a.a(this.b.c);
            }
            if (this.b.d) {
                this.f750a.a("intermediate-response");
            } else {
                this.f750a.b("done");
            }
            Runnable runnable = this.c;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public l5(Handler handler) {
        this.f748a = new a(this, handler);
    }

    @Override // com.tappx.a.v5
    public void a(s5<?> s5Var, u5<?> u5Var) {
        a(s5Var, u5Var, null);
    }

    @Override // com.tappx.a.v5
    public void a(s5<?> s5Var, u5<?> u5Var, Runnable runnable) {
        s5Var.u();
        s5Var.a("post-response");
        this.f748a.execute(new b(s5Var, u5Var, runnable));
    }

    @Override // com.tappx.a.v5
    public void a(s5<?> s5Var, z5 z5Var) {
        s5Var.a("post-error");
        this.f748a.execute(new b(s5Var, u5.a(z5Var), null));
    }
}
