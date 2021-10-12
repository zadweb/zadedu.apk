package com.truenet.android;

import a.a.a.g;
import a.a.b.b.e;
import a.a.b.b.h;
import a.a.b.b.i;
import a.a.b.b.m;
import a.a.b.b.n;
import a.a.j;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import com.mopub.common.AdType;
import com.startapp.common.b.a.b;
import com.startapp.common.b.b;
import com.truenet.android.b;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public final class TrueNetSDK implements com.startapp.common.b.a.a {
    private static final String BASE_INIT_URL = "https://validation-engine.truenet.ai";
    private static final String BASE_RESULT_URL = "https://result-api.truenet.ai";
    public static final a Companion = new a(null);
    private static final String INIT_URL = (BASE_INIT_URL + "/api/initial");
    private static final int JOB_ID = 97764;
    public static final String JOB_TAG = "TruenetCheckLinksJob";
    private static final String PREFS_ENABLED = "PrefsEnabled";
    private static final String PREFS_PUBLISHER_ID = "PrefsPublisherId";
    public static final String PREFS_TAG = "TruenetJobKey";
    private static final String RESULT_URL = (BASE_RESULT_URL + "/api/result");
    private static final URL initUrl = new URL(INIT_URL);
    private static int intervalPosition;
    private static final List<Long> intervals = g.a((Object[]) new Long[]{15L, 30L, 60L, 120L, 240L, 480L});
    private static long requestDelay;
    private static final URL resultUrl = new URL(RESULT_URL);
    private static ThreadFactory threadFactory = b.f901a;
    private static boolean wasInitCalled;

    public static final void enable(Context context, boolean z) {
        Companion.a(context, z);
    }

    public static final void with(Context context, String str) {
        Companion.a(context, str);
    }

    /* compiled from: StartAppSDK */
    static final class c implements com.startapp.common.b.a.b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ TrueNetSDK f902a;

        c(TrueNetSDK trueNetSDK) {
            this.f902a = trueNetSDK;
        }

        /* compiled from: StartAppSDK */
        static final class a extends i implements a.a.b.a.a<j> {
            final /* synthetic */ Context $context$inlined;
            final /* synthetic */ Map $extras$inlined;
            final /* synthetic */ int $jobId$inlined;
            final /* synthetic */ b.AbstractC0027b $runnerListener$inlined;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            a(Map map, Context context, int i, b.AbstractC0027b bVar) {
                super(0);
                this.$extras$inlined = map;
                this.$context$inlined = context;
                this.$jobId$inlined = i;
                this.$runnerListener$inlined = bVar;
            }

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // a.a.b.a.a
            public /* synthetic */ j a() {
                b();
                return j.f13a;
            }

            public final void b() {
                Log.d("JobManager", "finished " + String.valueOf(this.$jobId$inlined));
                this.$runnerListener$inlined.a(b.a.SUCCESS);
            }
        }

        @Override // com.startapp.common.b.a.b
        public final void a(Context context, int i, Map<String, String> map, b.AbstractC0027b bVar) {
            synchronized (this.f902a) {
                if (h.a((Object) map.get(TrueNetSDK.JOB_TAG), (Object) TrueNetSDK.PREFS_TAG)) {
                    a aVar = TrueNetSDK.Companion;
                    h.a((Object) context, "context");
                    aVar.a(context, new a(map, context, i, bVar));
                }
                j jVar = j.f13a;
            }
        }
    }

    /* compiled from: StartAppSDK */
    public static final class a {

        /* access modifiers changed from: package-private */
        /* renamed from: com.truenet.android.TrueNetSDK$a$a  reason: collision with other inner class name */
        /* compiled from: StartAppSDK */
        public static final class C0035a extends i implements a.a.b.a.a<j> {
            final /* synthetic */ ConcurrentLinkedQueue $bulkQueue;
            final /* synthetic */ Context $context;
            final /* synthetic */ a.a.b.a.a $finish;
            final /* synthetic */ LinksData $links;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            C0035a(LinksData linksData, ConcurrentLinkedQueue concurrentLinkedQueue, Context context, a.a.b.a.a aVar) {
                super(0);
                this.$links = linksData;
                this.$bulkQueue = concurrentLinkedQueue;
                this.$context = context;
                this.$finish = aVar;
            }

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // a.a.b.a.a
            public /* synthetic */ j a() {
                b();
                return j.f13a;
            }

            public final void b() {
                if (this.$links.getBulkResponse()) {
                    String a2 = com.startapp.common.c.b.a(new ValidationResults(g.a((Iterable) this.$bulkQueue)));
                    URL url = TrueNetSDK.resultUrl;
                    h.a((Object) a2, AdType.STATIC_NATIVE);
                    com.truenet.android.a.g.b(url, a2, this.$context);
                }
                TrueNetSDK.Companion.a((a) this.$context, (Context) this.$links.getSleep());
                if (this.$links.getSleep() != 0) {
                    this.$finish.a();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* compiled from: StartAppSDK */
        public static final class b extends i implements a.a.b.a.b<b, Integer, j> {
            final /* synthetic */ ConcurrentLinkedQueue $bulkQueue;
            final /* synthetic */ Context $context;
            final /* synthetic */ LinksData $links;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            b(LinksData linksData, Context context, ConcurrentLinkedQueue concurrentLinkedQueue) {
                super(2);
                this.$links = linksData;
                this.$context = context;
                this.$bulkQueue = concurrentLinkedQueue;
            }

            /* Return type fixed from 'java.lang.Object' to match base method */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            @Override // a.a.b.a.b
            public /* synthetic */ j a(b bVar, Integer num) {
                a(bVar, num.intValue());
                return j.f13a;
            }

            public final void a(b bVar, int i) {
                String str;
                h.b(bVar, "info");
                List<b.C0037b> d = bVar.d();
                ArrayList arrayList = new ArrayList(g.a(d, 10));
                for (T t : d) {
                    String a2 = t.a();
                    long b = t.b();
                    int c = t.c();
                    List<String> d2 = t.d();
                    if (d2 == null) {
                        d2 = g.a();
                    }
                    arrayList.add(new RedirectsResult(a2, b, c, d2));
                }
                ArrayList arrayList2 = arrayList;
                Link link = this.$links.getValidation().get(i);
                String instanceId = link.getInstanceId();
                int b2 = bVar.b();
                long c2 = bVar.c();
                String e = bVar.e();
                String f = bVar.f();
                String htmlStorage = (f == null || !com.truenet.android.a.g.a(new URL(link.getHtmlStorage()), f, this.$context)) ? "" : link.getHtmlStorage();
                Bitmap a3 = bVar.a();
                if (a3 == null || !com.truenet.android.a.a.a(a3, link.getImageStorage())) {
                    str = "";
                } else {
                    str = link.getImageStorage();
                }
                ValidationResult validationResult = new ValidationResult(instanceId, b2, c2, arrayList2, e, htmlStorage, str, TrueNetSDK.Companion.c(this.$context), link.getMetaData());
                if (this.$links.getBulkResponse()) {
                    this.$bulkQueue.add(validationResult);
                    return;
                }
                String a4 = com.startapp.common.c.b.a(new ValidationResults(g.a(validationResult)));
                URL url = TrueNetSDK.resultUrl;
                h.a((Object) a4, AdType.STATIC_NATIVE);
                com.truenet.android.a.g.b(url, a4, this.$context);
            }
        }

        private a() {
        }

        public /* synthetic */ a(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final void a(Thread thread, Throwable th) {
            Log.e("TrueNetSDK", "Something went wrong in thread: " + String.valueOf(thread.getId()), th);
        }

        public final void a(Context context, String str) {
            h.b(context, "context");
            h.b(str, "publisherID");
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0);
                sharedPreferences.edit().putString(TrueNetSDK.PREFS_PUBLISHER_ID, str).apply();
                if (sharedPreferences.getBoolean(TrueNetSDK.PREFS_ENABLED, true) && !TrueNetSDK.wasInitCalled) {
                    a(context);
                    TrueNetSDK.wasInitCalled = true;
                }
            } catch (Throwable th) {
                Thread currentThread = Thread.currentThread();
                h.a((Object) currentThread, "Thread.currentThread()");
                a(currentThread, th);
            }
        }

        public final void a(Context context, boolean z) {
            h.b(context, "context");
            try {
                context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0).edit().putBoolean(TrueNetSDK.PREFS_ENABLED, z).apply();
                if (z && !TrueNetSDK.wasInitCalled) {
                    a(context);
                    TrueNetSDK.wasInitCalled = true;
                }
            } catch (Throwable th) {
                Thread currentThread = Thread.currentThread();
                h.a((Object) currentThread, "Thread.currentThread()");
                a(currentThread, th);
            }
        }

        private final void a(Context context) {
            com.startapp.common.b.a.a(context);
            com.startapp.common.b.a.a(new TrueNetSDK());
            a(this, context, 0, 2, null);
        }

        static /* bridge */ /* synthetic */ void a(a aVar, Context context, long j, int i, Object obj) {
            if ((i & 2) != 0) {
                j = 0;
            }
            aVar.a(context, j);
        }

        /* access modifiers changed from: package-private */
        /* compiled from: StartAppSDK */
        public static final class d implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ long f899a;
            final /* synthetic */ Context b;

            d(long j, Context context) {
                this.f899a = j;
                this.b = context;
            }

            public final void run() {
                final m.a aVar = new m.a();
                aVar.element = (T) null;
                if (this.f899a != 0 || new a.a.b.a.a<String>(this) {
                    /* class com.truenet.android.TrueNetSDK.a.d.AnonymousClass1 */
                    final /* synthetic */ d this$0;

                    {
                        this.this$0 = r1;
                    }

                    /* renamed from: b */
                    public final String a() {
                        aVar.element = (T) com.truenet.android.a.g.b(TrueNetSDK.initUrl, TrueNetSDK.Companion.b(this.this$0.b), this.this$0.b);
                        return aVar.element;
                    }
                }.a() == null) {
                    TrueNetSDK.Companion.a((a) 0, (int) this.f899a);
                    return;
                }
                a aVar2 = TrueNetSDK.Companion;
                Context context = this.b;
                T t = aVar.element;
                if (t == null) {
                    h.a();
                }
                aVar2.a((a) context, (Context) t, (String) AnonymousClass2.f900a);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final void a(Context context, long j) {
            Executors.newSingleThreadExecutor(TrueNetSDK.threadFactory).execute(new d(j, context));
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final void a(int i, long j) {
            TrueNetSDK.requestDelay = j;
            TrueNetSDK.intervalPosition = a.a.c.a.a(i, TrueNetSDK.intervals.size() - 1);
            if (!(j != 0)) {
                j = TimeUnit.MINUTES.toMillis(((Number) TrueNetSDK.intervals.get(TrueNetSDK.intervalPosition)).longValue());
            }
            Log.d("JobManager", "scheduled millis: " + String.valueOf(j));
            com.startapp.common.b.a.a((int) TrueNetSDK.JOB_ID, false);
            com.startapp.common.b.a.a(new b.a(TrueNetSDK.JOB_ID).a(j).a(false).a(TrueNetSDK.JOB_TAG, TrueNetSDK.PREFS_TAG).b(true).a());
        }

        public final void a(Context context, a.a.b.a.a<j> aVar) {
            h.b(context, "context");
            h.b(aVar, "finish");
            try {
                if (!context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0).getBoolean(TrueNetSDK.PREFS_ENABLED, true)) {
                    com.startapp.common.b.a.a((int) TrueNetSDK.JOB_ID, false);
                    aVar.a();
                    return;
                }
                Executors.newSingleThreadExecutor(TrueNetSDK.threadFactory).execute(new c(context, aVar));
            } catch (Throwable th) {
                Thread currentThread = Thread.currentThread();
                h.a((Object) currentThread, "Thread.currentThread()");
                a(currentThread, th);
            }
        }

        /* access modifiers changed from: package-private */
        /* compiled from: StartAppSDK */
        public static final class c implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Context f898a;
            final /* synthetic */ a.a.b.a.a b;

            c(Context context, a.a.b.a.a aVar) {
                this.f898a = context;
                this.b = aVar;
            }

            public final void run() {
                boolean z = TrueNetSDK.requestDelay != 0;
                Log.d("JobManager", "sending initial request");
                String b2 = com.truenet.android.a.g.b(TrueNetSDK.initUrl, TrueNetSDK.Companion.b(this.f898a), this.f898a);
                if (b2 != null) {
                    TrueNetSDK.Companion.a((a) this.f898a, (Context) b2, (String) this.b);
                    return;
                }
                TrueNetSDK.Companion.a((a) (z ? TrueNetSDK.intervalPosition : TrueNetSDK.intervalPosition + 1), 0);
                this.b.a();
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final String b(Context context) {
            DeviceInfo a2 = new a(context, null, 2, null).a();
            a2.setPublisherId(c(context));
            String a3 = com.startapp.common.c.b.a(a2);
            h.a((Object) a3, "JSONParser.toJson(info)");
            return a3;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final String c(Context context) {
            String string = context.getSharedPreferences(TrueNetSDK.PREFS_TAG, 0).getString(TrueNetSDK.PREFS_PUBLISHER_ID, "Undefined");
            h.a((Object) string, "prefs.getString(PREFS_PUBLISHER_ID, \"Undefined\")");
            return string;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private final void a(Context context, String str, a.a.b.a.a<j> aVar) {
            TrueNetSDK.intervalPosition = 0;
            TrueNetSDK.requestDelay = 0;
            LinksData linksData = (LinksData) com.startapp.common.c.b.a(str, LinksData.class);
            if (linksData.getValidation().size() != 0) {
                h.a((Object) linksData, "response");
                a(context, linksData, aVar);
                return;
            }
            a(context, linksData.getSleep());
            if (linksData.getSleep() != 0) {
                aVar.a();
            }
        }

        private final void a(Context context, LinksData linksData, a.a.b.a.a<j> aVar) {
            List<Link> validation = linksData.getValidation();
            ArrayList arrayList = new ArrayList(g.a(validation, 10));
            Iterator<T> it = validation.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getValidationUrl());
            }
            c cVar = new c(context, arrayList, TrueNetSDK.threadFactory, linksData.getMaxRedirectTime(), linksData.getNumOfRedirect(), linksData.getValidateParallel());
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            cVar.a(new C0035a(linksData, concurrentLinkedQueue, context, aVar));
            cVar.a(new b(linksData, context, concurrentLinkedQueue));
        }
    }

    /* compiled from: StartAppSDK */
    static final class b implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        public static final b f901a = new b();

        b() {
        }

        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setUncaughtExceptionHandler(new d(new a.a.b.a.b<Thread, Throwable, j>(TrueNetSDK.Companion) {
                /* class com.truenet.android.TrueNetSDK.b.AnonymousClass1 */

                @Override // a.a.b.b.a
                public final a.a.d.c a() {
                    return n.a(a.class);
                }

                @Override // a.a.b.b.a
                public final String b() {
                    return "uncaughtExceptionHandler";
                }

                @Override // a.a.b.b.a
                public final String c() {
                    return "uncaughtExceptionHandler(Ljava/lang/Thread;Ljava/lang/Throwable;)V";
                }

                /* Return type fixed from 'java.lang.Object' to match base method */
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                @Override // a.a.b.a.b
                public /* bridge */ /* synthetic */ j a(Thread thread, Throwable th) {
                    a(thread, th);
                    return j.f13a;
                }

                public final void a(Thread thread, Throwable th) {
                    h.b(thread, "p1");
                    h.b(th, "p2");
                    ((a) this.receiver).a((a) thread, (Thread) th);
                }
            }));
            return thread;
        }
    }

    @Override // com.startapp.common.b.a.a
    public com.startapp.common.b.a.b create(int i) {
        if (i != JOB_ID) {
            return null;
        }
        Log.d("JobManager", "addJobCreator");
        return new c(this);
    }
}
