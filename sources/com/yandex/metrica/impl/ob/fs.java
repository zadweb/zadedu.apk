package com.yandex.metrica.impl.ob;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.yandex.metrica.impl.ob.fu;
import java.lang.Thread;

public class fs {

    /* renamed from: a  reason: collision with root package name */
    private fq f1148a;
    private HandlerThread b;
    private b c;
    private volatile Handler d;

    public fs(fq fqVar) {
        this(fqVar, null);
    }

    public fs(fq fqVar, Handler handler) {
        this.f1148a = fqVar;
        this.b = new HandlerThread(fs.class.getSimpleName() + '@' + Integer.toHexString(hashCode()));
        this.d = handler;
    }

    public <T> void a(fu<T> fuVar, fu.b<T> bVar, fu.a aVar) {
        a();
        fuVar.a(bVar);
        fuVar.a(aVar);
        this.c.a(fuVar);
    }

    private synchronized void a() {
        if (this.b.getState() == Thread.State.NEW) {
            this.b.start();
            Looper looper = this.b.getLooper();
            this.c = new b(this, looper, (byte) 0);
            if (this.d == null) {
                this.d = new Handler(looper);
            }
        }
    }

    /* access modifiers changed from: private */
    public class b extends Handler {
        /* synthetic */ b(fs fsVar, Looper looper, byte b) {
            this(looper);
        }

        private b(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            fu<?> fuVar = (fu) message.obj;
            fu.b<?> e = fuVar.e();
            try {
                fs.this.d.post(new c(e, fuVar.b(fs.this.f1148a.a(fuVar)), (byte) 0));
            } catch (fr e2) {
                fs.this.d.post(new a(fuVar.f(), e2, (byte) 0));
            }
        }

        public <T> void a(fu<T> fuVar) {
            Message message = new Message();
            message.obj = fuVar;
            sendMessage(message);
        }
    }

    private static class c<T> implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private fu.b<T> f1151a;
        private T b;

        /* synthetic */ c(fu.b bVar, Object obj, byte b2) {
            this(bVar, obj);
        }

        private c(fu.b bVar, T t) {
            this.f1151a = bVar;
            this.b = t;
        }

        public void run() {
            fu.b<T> bVar = this.f1151a;
            if (bVar != null) {
                bVar.a(this.b);
            }
        }
    }

    private static class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private fu.a f1149a;
        private fr b;

        /* synthetic */ a(fu.a aVar, fr frVar, byte b2) {
            this(aVar, frVar);
        }

        private a(fu.a aVar, fr frVar) {
            this.f1149a = aVar;
            this.b = frVar;
        }

        public void run() {
            fu.a aVar = this.f1149a;
            if (aVar != null) {
                aVar.a(this.b);
            }
        }
    }
}
