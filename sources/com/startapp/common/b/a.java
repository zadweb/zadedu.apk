package com.startapp.common.b;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.SystemClock;
import androidx.recyclerview.widget.RecyclerView;
import com.startapp.android.publish.common.metaData.InfoEventService;
import com.startapp.android.publish.common.metaData.PeriodicJobService;
import com.startapp.common.b.a.b;
import com.startapp.common.b.a.c;
import com.startapp.common.b.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f584a = null;
    private static volatile c b = null;
    private static volatile Integer c = null;
    private static volatile int d = 60000;
    private static final ExecutorService j = Executors.newSingleThreadExecutor();
    private static final ScheduledExecutorService k = Executors.newScheduledThreadPool(1);
    private Context e;
    private List<com.startapp.common.b.a.a> f = Collections.synchronizedList(new ArrayList());
    private Map<Integer, Integer> g = new ConcurrentHashMap();
    private AtomicInteger h = new AtomicInteger(0);
    private boolean i;

    private static int b(int i2) {
        return i2 & Integer.MAX_VALUE;
    }

    private static int b(int i2, boolean z) {
        return z ? i2 | RecyclerView.UNDEFINED_DURATION : i2;
    }

    private a(Context context) {
        this.e = context.getApplicationContext();
        this.i = d(context);
    }

    public static a a(Context context) {
        if (f584a == null) {
            synchronized (a.class) {
                if (f584a == null) {
                    if (context.getApplicationContext() != null) {
                        context = context.getApplicationContext();
                    }
                    f584a = new a(context);
                    try {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("RunnerManager", 0);
                        String str = null;
                        String string = sharedPreferences.getString("RegisteredClassesNames", null);
                        if (string != null) {
                            String[] split = string.split(",");
                            StringBuilder sb = new StringBuilder(string.length());
                            for (String str2 : split) {
                                try {
                                    a(3, "RunnerManager", "create CLS: " + str2);
                                    Class<?> cls = Class.forName(str2);
                                    if (com.startapp.common.b.a.a.class.isAssignableFrom(cls)) {
                                        f584a.f.add((com.startapp.common.b.a.a) cls.newInstance());
                                        if (sb.length() > 0) {
                                            sb.append(',');
                                        }
                                        sb.append(str2);
                                    }
                                } catch (ClassNotFoundException unused) {
                                } catch (Throwable th) {
                                    a(6, "RunnerManager", "create :" + str2, th);
                                }
                            }
                            if (!sb.toString().equals(string)) {
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                if (sb.length() > 0) {
                                    str = sb.toString();
                                }
                                edit.putString("RegisteredClassesNames", str).commit();
                            }
                        }
                    } catch (Exception e2) {
                        a(6, "RunnerManager", "create", e2);
                    }
                }
            }
        }
        return f584a;
    }

    public static void a(com.startapp.common.b.a.a aVar) {
        f584a.f.add(aVar);
        String name = aVar.getClass().getName();
        SharedPreferences sharedPreferences = f584a.e.getSharedPreferences("RunnerManager", 0);
        String string = sharedPreferences.getString("RegisteredClassesNames", null);
        if (string == null) {
            sharedPreferences.edit().putString("RegisteredClassesNames", name).commit();
        } else if (!string.contains(name)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("RegisteredClassesNames", string + "," + name).commit();
        }
    }

    public static void a(c cVar) {
        b = cVar;
    }

    public static boolean a(b bVar) {
        try {
            int b2 = b(bVar.a(), bVar.e());
            a(3, "RunnerManager", "schedule " + b2 + " " + bVar);
            if (!c()) {
                return c(b2, bVar);
            }
            if (b()) {
                return a(b2, bVar);
            }
            return b(b2, bVar);
        } catch (Exception e2) {
            a(6, "RunnerManager", "schedule error", e2);
            return false;
        }
    }

    private static boolean a(int i2, b bVar) {
        JobScheduler c2 = c(f584a.e);
        if (c2 == null) {
            return false;
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        Map<String, String> b2 = bVar.b();
        for (String str : b2.keySet()) {
            persistableBundle.putString(str, b2.get(str));
        }
        persistableBundle.putInt("__RUNNER_RECURRING_ID__", bVar.e() ? 1 : 0);
        persistableBundle.putLong("__RUNNER_TRIGGER_ID__", bVar.c());
        JobInfo.Builder builder = new JobInfo.Builder(i2, new ComponentName(f584a.e, PeriodicJobService.class));
        builder.setExtras(persistableBundle);
        if (bVar.e()) {
            builder.setPeriodic(bVar.c());
        } else {
            builder.setMinimumLatency(bVar.c()).setOverrideDeadline(bVar.c() + ((long) d));
        }
        builder.setRequiredNetworkType(bVar.f() ? 1 : 0);
        int schedule = c2.schedule(builder.build());
        a(3, "RunnerManager", "jobScheduler.schedule " + schedule);
        if (schedule == 1) {
            return true;
        }
        return false;
    }

    private static boolean b(int i2, b bVar) {
        AlarmManager b2 = b(f584a.e);
        if (b2 == null) {
            return false;
        }
        Intent intent = new Intent(f584a.e, InfoEventService.class);
        Map<String, String> b3 = bVar.b();
        for (String str : b3.keySet()) {
            intent.putExtra(str, b3.get(str));
        }
        intent.putExtra("__RUNNER_TASK_ID__", i2);
        intent.putExtra("__RUNNER_RECURRING_ID__", bVar.e());
        intent.putExtra("__RUNNER_TRIGGER_ID__", bVar.c());
        PendingIntent service = PendingIntent.getService(f584a.e, i2, intent, 134217728);
        b2.cancel(service);
        if (bVar.e()) {
            b2.setRepeating(0, System.currentTimeMillis() + bVar.d(), bVar.c(), service);
            return true;
        }
        b2.set(3, SystemClock.elapsedRealtime() + bVar.c(), service);
        return true;
    }

    private static boolean c(final int i2, final b bVar) {
        final int incrementAndGet = f584a.h.incrementAndGet();
        AnonymousClass1 r2 = new Runnable() {
            /* class com.startapp.common.b.a.AnonymousClass1 */

            public void run() {
                Integer num = (Integer) a.f584a.g.get(Integer.valueOf(i2));
                if (num != null && num.intValue() == incrementAndGet) {
                    if (!bVar.e()) {
                        a.f584a.g.remove(Integer.valueOf(i2));
                    }
                    a.b(bVar, new b.AbstractC0027b() {
                        /* class com.startapp.common.b.a.AnonymousClass1.AnonymousClass1 */

                        @Override // com.startapp.common.b.a.b.AbstractC0027b
                        public void a(b.a aVar) {
                        }
                    });
                }
            }
        };
        if (bVar.e()) {
            k.scheduleAtFixedRate(r2, bVar.d(), bVar.d(), TimeUnit.MILLISECONDS);
        } else {
            k.schedule(r2, bVar.c(), TimeUnit.MILLISECONDS);
        }
        f584a.g.put(Integer.valueOf(i2), Integer.valueOf(incrementAndGet));
        return true;
    }

    public static void a(int i2, boolean z) {
        a(3, "RunnerManager", "cancelAlarm " + i2);
        try {
            int b2 = b(i2, z);
            if (!f584a.i) {
                f584a.g.remove(Integer.valueOf(b2));
            } else if (b()) {
                JobScheduler c2 = c(f584a.e);
                if (c2 != null) {
                    c2.cancel(b2);
                }
            } else {
                AlarmManager b3 = b(f584a.e);
                if (b3 != null) {
                    a(f584a.e, new Intent(f584a.e, InfoEventService.class), b3, b2);
                }
            }
        } catch (Exception e2) {
            a(6, "RunnerManager", "cancelAlarm " + i2, e2);
        }
    }

    public static void a(int i2, String str, String str2) {
        a(i2, str, str2, (Throwable) null);
    }

    public static void a(int i2, String str, String str2, Throwable th) {
        if (b != null) {
            b.a(i2, str, str2, th);
        }
    }

    public static boolean a(Intent intent, b.AbstractC0027b bVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("runJob ");
        sb.append(intent != null ? intent : "NULL");
        a(3, "RunnerManager", sb.toString());
        return intent != null && b(a(intent), bVar);
    }

    public static boolean a(JobParameters jobParameters, b.AbstractC0027b bVar) {
        a(3, "RunnerManager", "runJob " + jobParameters);
        return b(a(jobParameters), bVar);
    }

    /* access modifiers changed from: private */
    public static boolean b(final b bVar, final b.AbstractC0027b bVar2) {
        a(3, "RunnerManager", "RunnerJob " + bVar.a() + " " + b(bVar.a()));
        final int b2 = b(bVar.a());
        final b a2 = a(b2);
        if (a2 != null) {
            j.execute(new Runnable() {
                /* class com.startapp.common.b.a.AnonymousClass2 */

                public void run() {
                    a2.a(a.f584a.e, b2, bVar.b(), new b.AbstractC0027b() {
                        /* class com.startapp.common.b.a.AnonymousClass2.AnonymousClass1 */

                        @Override // com.startapp.common.b.a.b.AbstractC0027b
                        public void a(b.a aVar) {
                            a.a(3, "RunnerManager", "job.execute " + bVar.a() + " " + aVar);
                            if (aVar == b.a.RESCHEDULE && !bVar.e()) {
                                a.a(bVar);
                            }
                            bVar2.a(aVar);
                        }
                    });
                }
            });
            return true;
        }
        a(5, "RunnerManager", "runJob: failed to get job for ID " + bVar.a());
        bVar2.a(b.a.FAILED);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000f  */
    private static b a(int i2) {
        Iterator<com.startapp.common.b.a.a> it = f584a.f.iterator();
        b bVar = null;
        while (it.hasNext() && (bVar = it.next().create(i2)) == null) {
            while (it.hasNext()) {
                while (it.hasNext()) {
                }
            }
        }
        return bVar;
    }

    private static void a(Context context, Intent intent, AlarmManager alarmManager, int i2) {
        PendingIntent service = PendingIntent.getService(context, i2, intent, 134217728);
        if (PendingIntent.getService(context, 0, intent, 268435456) != null) {
            alarmManager.cancel(service);
            service.cancel();
        }
    }

    private static b a(Intent intent) {
        HashMap hashMap;
        int intExtra = intent.getIntExtra("__RUNNER_TASK_ID__", -1);
        boolean booleanExtra = intent.getBooleanExtra("__RUNNER_RECURRING_ID__", false);
        long longExtra = intent.getLongExtra("__RUNNER_TRIGGER_ID__", 0);
        if (intent.getExtras() != null) {
            hashMap = new HashMap(intent.getExtras().keySet().size());
            for (String str : intent.getExtras().keySet()) {
                Object obj = intent.getExtras().get(str);
                if (obj instanceof String) {
                    hashMap.put(str, (String) obj);
                }
            }
        } else {
            hashMap = null;
        }
        return new b.a(intExtra).a(hashMap).a(booleanExtra).a(longExtra).a();
    }

    private static b a(JobParameters jobParameters) {
        PersistableBundle extras = jobParameters.getExtras();
        boolean z = true;
        if (extras.getInt("__RUNNER_RECURRING_ID__") != 1) {
            z = false;
        }
        long j2 = extras.getLong("__RUNNER_TRIGGER_ID__", 0);
        HashMap hashMap = new HashMap(extras.keySet().size());
        for (String str : extras.keySet()) {
            Object obj = extras.get(str);
            if (obj instanceof String) {
                hashMap.put(str, (String) obj);
            }
        }
        return new b.a(jobParameters.getJobId()).a(hashMap).a(z).a(j2).a();
    }

    private static AlarmManager b(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (alarmManager == null) {
            a(6, "RunnerManager", "failed to get AlarmManager");
        }
        return alarmManager;
    }

    private static JobScheduler c(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler == null) {
            a(6, "RunnerManager", "failed to get JobScheduler");
        }
        return jobScheduler;
    }

    private static boolean b() {
        int i2 = Build.VERSION.SDK_INT;
        if (c != null) {
            i2 = c.intValue();
        }
        return i2 >= 21;
    }

    private boolean d(Context context) {
        try {
            for (ServiceInfo serviceInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services) {
                if (serviceInfo.name.equals(InfoEventService.class.getName())) {
                    return true;
                }
            }
        } catch (Throwable th) {
            a(6, "RunnerManager", "servicesDefined", th);
        }
        return false;
    }

    private static boolean c() {
        return f584a.i;
    }
}
