package com.tappx.a;

import android.os.Process;
import com.tappx.a.h5;
import com.tappx.a.s5;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class i5 extends Thread {
    private static final boolean g = a6.b;

    /* renamed from: a  reason: collision with root package name */
    private final BlockingQueue<s5<?>> f709a;
    private final BlockingQueue<s5<?>> b;
    private final h5 c;
    private final v5 d;
    private volatile boolean e = false;
    private final b f;

    /* access modifiers changed from: package-private */
    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ s5 f710a;

        a(s5 s5Var) {
            this.f710a = s5Var;
        }

        public void run() {
            try {
                i5.this.b.put(this.f710a);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* access modifiers changed from: private */
    public static class b implements s5.b {

        /* renamed from: a  reason: collision with root package name */
        private final Map<String, List<s5<?>>> f711a = new HashMap();
        private final i5 b;

        b(i5 i5Var) {
            this.b = i5Var;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized boolean b(s5<?> s5Var) {
            String e = s5Var.e();
            if (this.f711a.containsKey(e)) {
                List<s5<?>> list = this.f711a.get(e);
                if (list == null) {
                    list = new ArrayList<>();
                }
                s5Var.a("waiting-for-response");
                list.add(s5Var);
                this.f711a.put(e, list);
                if (a6.b) {
                    a6.b("Request for cacheKey=%s is in flight, putting on hold.", e);
                }
                return true;
            }
            this.f711a.put(e, null);
            s5Var.a((s5.b) this);
            if (a6.b) {
                a6.b("new request, sending to network %s", e);
            }
            return false;
        }

        @Override // com.tappx.a.s5.b
        public void a(s5<?> s5Var, u5<?> u5Var) {
            List<s5<?>> remove;
            h5.a aVar = u5Var.b;
            if (aVar == null || aVar.a()) {
                a(s5Var);
                return;
            }
            String e = s5Var.e();
            synchronized (this) {
                remove = this.f711a.remove(e);
            }
            if (remove != null) {
                if (a6.b) {
                    a6.d("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(remove.size()), e);
                }
                for (s5<?> s5Var2 : remove) {
                    this.b.d.a(s5Var2, u5Var);
                }
            }
        }

        @Override // com.tappx.a.s5.b
        public synchronized void a(s5<?> s5Var) {
            String e = s5Var.e();
            List<s5<?>> remove = this.f711a.remove(e);
            if (remove != null && !remove.isEmpty()) {
                if (a6.b) {
                    a6.d("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(remove.size()), e);
                }
                s5<?> remove2 = remove.remove(0);
                this.f711a.put(e, remove);
                remove2.a((s5.b) this);
                try {
                    this.b.b.put(remove2);
                } catch (InterruptedException e2) {
                    a6.c("Couldn't add request to queue. %s", e2.toString());
                    Thread.currentThread().interrupt();
                    this.b.a();
                }
            }
        }
    }

    public i5(BlockingQueue<s5<?>> blockingQueue, BlockingQueue<s5<?>> blockingQueue2, h5 h5Var, v5 v5Var) {
        this.f709a = blockingQueue;
        this.b = blockingQueue2;
        this.c = h5Var;
        this.d = v5Var;
        this.f = new b(this);
    }

    public void run() {
        if (g) {
            a6.d("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.c.a();
        while (true) {
            try {
                b();
            } catch (InterruptedException unused) {
                if (this.e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                a6.c("Ignoring spurious interrupt of CacheDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }

    private void b() {
        a(this.f709a.take());
    }

    public void a() {
        this.e = true;
        interrupt();
    }

    /* access modifiers changed from: package-private */
    public void a(s5<?> s5Var) {
        s5Var.a("cache-queue-take");
        s5Var.a(1);
        try {
            if (s5Var.t()) {
                s5Var.b("cache-discard-canceled");
                return;
            }
            h5.a a2 = this.c.a(s5Var.e());
            if (a2 == null) {
                s5Var.a("cache-miss");
                if (!this.f.b(s5Var)) {
                    this.b.put(s5Var);
                }
                s5Var.a(2);
            } else if (a2.a()) {
                s5Var.a("cache-hit-expired");
                s5Var.a(a2);
                if (!this.f.b(s5Var)) {
                    this.b.put(s5Var);
                }
                s5Var.a(2);
            } else {
                s5Var.a("cache-hit");
                u5<?> a3 = s5Var.a(new q5(a2.f697a, a2.g));
                s5Var.a("cache-hit-parsed");
                if (!a2.b()) {
                    this.d.a(s5Var, a3);
                } else {
                    s5Var.a("cache-hit-refresh-needed");
                    s5Var.a(a2);
                    a3.d = true;
                    if (!this.f.b(s5Var)) {
                        this.d.a(s5Var, a3, new a(s5Var));
                    } else {
                        this.d.a(s5Var, a3);
                    }
                }
                s5Var.a(2);
            }
        } finally {
            s5Var.a(2);
        }
    }
}
