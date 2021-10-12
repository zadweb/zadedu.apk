package com.appnext.base.b;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.appnext.base.a.b.c;
import com.appnext.base.b.d;
import com.appnext.base.operations.a;
import com.appnext.base.operations.b;
import com.appnext.core.f;
import com.appnext.core.i;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpRetryException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public final class j {
    private static final String TAG = "SdkHelper";
    private static final long eI = 1000;
    private static final long eJ = 60000;
    private static final long eK = 3600000;
    private static final long eL = 86400000;

    public static boolean a(String str, String str2, c cVar) {
        char c = 65535;
        try {
            if (str2.hashCode() == 570418373) {
                if (str2.equals(d.fn)) {
                    c = 0;
                }
            }
            if (c != 0) {
                return false;
            }
            return ((a) Class.forName(b.B(str)).getConstructor(c.class, Bundle.class).newInstance(cVar, null)).aE();
        } catch (InvocationTargetException e) {
            e.getCause().printStackTrace();
            e.getCause();
            return false;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean a(Class cls) {
        try {
            if (e.getContext().getPackageManager().queryIntentServices(new Intent(e.getContext(), cls), 65536).size() > 0) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
        }
    }

    public static List<String> a(Context context, long j, long j2) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ArrayList arrayList = new ArrayList();
        if (context == null) {
            return arrayList;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (Build.VERSION.SDK_INT < 21) {
                if (f.a(e.getContext(), "android.permission.GET_TASKS") && (runningTasks = activityManager.getRunningTasks(20)) != null && !runningTasks.isEmpty()) {
                    for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
                        if (!b(context, runningTaskInfo.baseActivity.getPackageName())) {
                            arrayList.add(runningTaskInfo.baseActivity.getPackageName());
                        }
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 21 && f(context.getApplicationContext())) {
                UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService("usagestats");
                long currentTimeMillis = System.currentTimeMillis();
                List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(4, currentTimeMillis - j, currentTimeMillis);
                if (queryUsageStats == null) {
                    return arrayList;
                }
                ListIterator<UsageStats> listIterator = queryUsageStats.listIterator();
                while (listIterator.hasNext()) {
                    UsageStats next = listIterator.next();
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (!usageStatsManager.isAppInactive(next.getPackageName()) && next.getTotalTimeInForeground() >= j2) {
                            if (!b(context, next.getPackageName())) {
                                arrayList.add(next.getPackageName());
                            }
                        }
                        listIterator.remove();
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    private static boolean b(Context context, String str) {
        try {
            if (str.contains("com.android")) {
                return true;
            }
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            if (queryIntentActivities != null) {
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName.equals(str)) {
                        return true;
                    }
                }
            }
            Intent intent2 = new Intent("android.intent.action.MAIN", (Uri) null);
            intent2.addCategory("android.intent.category.LAUNCHER");
            List<ResolveInfo> queryIntentActivities2 = context.getPackageManager().queryIntentActivities(intent2, 0);
            if (queryIntentActivities2 != null) {
                Iterator<ResolveInfo> it = queryIntentActivities2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ResolveInfo next = it.next();
                    if (next.activityInfo != null && next.activityInfo.packageName.equals(str)) {
                        if ((next.activityInfo.flags & 129) != 0) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Throwable unused) {
        }
    }

    public static boolean f(Context context) {
        return ((AppOpsManager) context.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), context.getPackageName()) == 0;
    }

    public static void g(Context context) {
        try {
            List<c> as = com.appnext.base.a.a.X().ab().as();
            if (as != null && as.size() == 0) {
                c cVar = new c(d.fe, "1", d.fj, "1", d.fn, "cdm", "cdm" + System.currentTimeMillis(), null);
                com.appnext.base.a.a.X().ab().a(cVar);
                com.appnext.base.services.b.a.d(context).a(cVar, true);
            }
        } catch (Throwable unused) {
        }
    }

    public static boolean h(Context context) throws Exception {
        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        return advertisingIdInfo != null && advertisingIdInfo.isLimitAdTrackingEnabled();
    }

    public static boolean i(Context context) {
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo == null || advertisingIdInfo.isLimitAdTrackingEnabled()) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
        }
    }

    public static boolean b(String str, Map<String, String> map) {
        c t = com.appnext.base.a.a.X().ab().t(str);
        if (t == null || d.ff.equalsIgnoreCase(t.ak()) || map.isEmpty()) {
            return true;
        }
        String str2 = i.hm + "/data";
        HashMap hashMap = new HashMap();
        String b = f.b(e.getContext(), true);
        if (TextUtils.isEmpty(b)) {
            b = i.aR().getString(i.fB, "");
        }
        if (TextUtils.isEmpty(b)) {
            return false;
        }
        hashMap.put("aid", b);
        hashMap.put("cuid", b + "_" + System.currentTimeMillis());
        hashMap.put("lvid", "4.7.2");
        try {
            hashMap.put("localdate", a(new Date()));
            hashMap.put("timezone", aT());
            hashMap.put("app_package", e.getPackageName());
        } catch (Throwable unused) {
            hashMap.put("app_package", "");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        StringBuilder sb = new StringBuilder("-------Sending to server data for key = ");
        sb.append(str);
        sb.append(" ----------");
        for (Map.Entry entry2 : hashMap.entrySet()) {
            StringBuilder sb2 = new StringBuilder("---- ");
            sb2.append((String) entry2.getKey());
            sb2.append(" : ");
            sb2.append((String) entry2.getValue());
            sb2.append(" ----");
        }
        try {
            byte[] a2 = f.a(str2, (Object) hashMap, false, (int) d.fd, d.a.HashMap);
            if (a2 != null) {
                new StringBuilder("result send data: ").append(new String(a2, "UTF-8"));
            }
            return true;
        } catch (HttpRetryException e) {
            int responseCode = e.responseCode();
            String message = e.getMessage();
            StringBuilder sb3 = new StringBuilder("(Type:HttpRetryException)");
            sb3.append(message);
            sb3.append("  ");
            sb3.append(responseCode);
            return false;
        } catch (Throwable th) {
            new StringBuilder("(Type:Throwable) ").append(th.getMessage());
            return false;
        }
    }

    public static String aT() {
        StringBuilder sb = new StringBuilder(9);
        try {
            Calendar instance = Calendar.getInstance(TimeZone.getDefault(), Locale.US);
            int i = (instance.get(15) + instance.get(16)) / 60000;
            char c = '+';
            if (i < 0) {
                c = '-';
                i = -i;
            }
            sb.append("GMT");
            sb.append(c);
            a(sb, 2, i / 60);
            sb.append(':');
            a(sb, 2, i % 60);
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    private static void a(StringBuilder sb, int i, int i2) {
        try {
            String num = Integer.toString(i2);
            for (int i3 = 0; i3 < 2 - num.length(); i3++) {
                sb.append('0');
            }
            sb.append(num);
        } catch (Throwable unused) {
        }
    }

    public static String a(Date date) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.US).format(date));
            sb.append(" ");
            sb.append(aT());
            sb.append(" ");
            sb.append(new SimpleDateFormat("yyyy", Locale.US).format(date));
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    public static int g(String str, String str2) {
        long j;
        long j2;
        try {
            if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
                if (!TextUtils.isEmpty(str2)) {
                    int intValue = Integer.valueOf(str).intValue();
                    if (d.fh.equalsIgnoreCase(str2)) {
                        return intValue;
                    }
                    if (d.fi.equalsIgnoreCase(str2)) {
                        j = (long) intValue;
                        j2 = eJ;
                    } else if (d.fj.equalsIgnoreCase(str2)) {
                        j = (long) intValue;
                        j2 = eK;
                    } else if (d.fk.equalsIgnoreCase(str2)) {
                        j = (long) intValue;
                        j2 = eL;
                    }
                    return (int) (j * j2);
                }
            }
        } catch (Throwable unused) {
        }
        return -1;
    }

    public static void a(String str, String str2, d.a aVar) {
        com.appnext.base.a.a.X().aa().b(new com.appnext.base.a.b.b(str, str2, aVar.getType()));
    }

    public static Object a(String str, d.a aVar) {
        try {
            List<com.appnext.base.a.b.b> v = com.appnext.base.a.a.X().aa().v(str);
            if (v == null || v.isEmpty()) {
                return null;
            }
            return b(v.get(0).ai(), aVar);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.appnext.base.b.j$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] fF;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            int[] iArr = new int[d.a.values().length];
            fF = iArr;
            iArr[d.a.Integer.ordinal()] = 1;
            fF[d.a.Double.ordinal()] = 2;
            fF[d.a.Long.ordinal()] = 3;
            fF[d.a.Boolean.ordinal()] = 4;
            fF[d.a.Set.ordinal()] = 5;
            fF[d.a.JSONArray.ordinal()] = 6;
            try {
                fF[d.a.JSONObject.ordinal()] = 7;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static Object b(String str, d.a aVar) {
        try {
            switch (AnonymousClass1.fF[aVar.ordinal()]) {
                case 1:
                    return Integer.valueOf(str);
                case 2:
                    return Double.valueOf(str);
                case 3:
                    return Long.valueOf(str);
                case 4:
                    return Boolean.valueOf(str);
                case 5:
                    return new HashSet(Arrays.asList(str.split(",")));
                case 6:
                    return new JSONArray(str);
                case 7:
                    return new JSONObject(str);
                default:
                    return str;
            }
        } catch (Throwable unused) {
            return null;
        }
    }
}
