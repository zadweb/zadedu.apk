package com.yandex.metrica.impl.ob;

import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class g {

    /* renamed from: a  reason: collision with root package name */
    private final Thread f1156a;
    private volatile boolean b = true;
    private final BlockingQueue<a> c = new LinkedBlockingQueue();
    private ConcurrentHashMap<Class, CopyOnWriteArrayList<k<? extends i>>> d = new ConcurrentHashMap<>();
    private WeakHashMap<Object, CopyOnWriteArrayList<c>> e = new WeakHashMap<>();
    private ConcurrentHashMap<Class, i> f = new ConcurrentHashMap<>();

    /* access modifiers changed from: private */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private static final g f1159a = new g();
    }

    private static class c {

        /* renamed from: a  reason: collision with root package name */
        final CopyOnWriteArrayList<k<? extends i>> f1160a;
        final k<? extends i> b;

        /* synthetic */ c(CopyOnWriteArrayList copyOnWriteArrayList, k kVar, byte b2) {
            this(copyOnWriteArrayList, kVar);
        }

        private c(CopyOnWriteArrayList<k<? extends i>> copyOnWriteArrayList, k<? extends i> kVar) {
            this.f1160a = copyOnWriteArrayList;
            this.b = kVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.f1160a.remove(this.b);
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            super.finalize();
            a();
        }
    }

    /* access modifiers changed from: private */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final i f1158a;
        private final k<? extends i> b;

        /* synthetic */ a(i iVar, k kVar, byte b2) {
            this(iVar, kVar);
        }

        private a(i iVar, k<? extends i> kVar) {
            this.f1158a = iVar;
            this.b = kVar;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            try {
                if (!this.b.b(this.f1158a)) {
                    this.b.a(this.f1158a);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static final g a() {
        return b.f1159a;
    }

    g() {
        Thread thread = new Thread(new Runnable() {
            /* class com.yandex.metrica.impl.ob.g.AnonymousClass1 */

            public void run() {
                while (g.this.b) {
                    try {
                        ((a) g.this.c.take()).a();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }, "Bus Dispatcher");
        this.f1156a = thread;
        thread.start();
    }

    public synchronized void a(i iVar) {
        CopyOnWriteArrayList<k<? extends i>> copyOnWriteArrayList = this.d.get(iVar.getClass());
        if (copyOnWriteArrayList != null) {
            Iterator<k<? extends i>> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                a(iVar, it.next());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(i iVar, k<? extends i> kVar) {
        this.c.add(new a(iVar, kVar, (byte) 0));
    }

    public synchronized void b(i iVar) {
        a(iVar);
        this.f.put(iVar.getClass(), iVar);
    }

    public synchronized void a(Class<? extends i> cls) {
        this.f.remove(cls);
    }

    public synchronized void a(Object obj, Class cls, k<? extends i> kVar) {
        CopyOnWriteArrayList<k<? extends i>> copyOnWriteArrayList = this.d.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.d.put(cls, copyOnWriteArrayList);
        }
        copyOnWriteArrayList.add(kVar);
        CopyOnWriteArrayList<c> copyOnWriteArrayList2 = this.e.get(obj);
        if (copyOnWriteArrayList2 == null) {
            copyOnWriteArrayList2 = new CopyOnWriteArrayList<>();
            this.e.put(obj, copyOnWriteArrayList2);
        }
        copyOnWriteArrayList2.add(new c(copyOnWriteArrayList, kVar, (byte) 0));
        i iVar = this.f.get(cls);
        if (iVar != null) {
            a(iVar, kVar);
        }
    }

    public synchronized void a(Object obj) {
        CopyOnWriteArrayList<c> remove = this.e.remove(obj);
        if (remove != null) {
            for (c cVar : remove) {
                cVar.a();
            }
        }
    }
}
