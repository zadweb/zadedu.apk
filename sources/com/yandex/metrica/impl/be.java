package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import com.yandex.metrica.IMetricaService;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class be {

    public static class a implements Comparable<a> {

        /* renamed from: a  reason: collision with root package name */
        public final int f985a;
        public final int b;
        public final long c;
        public final ServiceInfo d;
        public final String e;

        public a(ServiceInfo serviceInfo, int i, int i2, long j) {
            this.f985a = i2;
            this.b = i;
            this.d = serviceInfo;
            this.c = j;
            this.e = serviceInfo.applicationInfo.packageName;
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            int i = this.b;
            if (i != aVar.b) {
                return Integer.valueOf(i).compareTo(Integer.valueOf(aVar.b));
            }
            long j = this.c;
            if (j != aVar.c) {
                return Long.valueOf(j).compareTo(Long.valueOf(aVar.c));
            }
            return 0;
        }

        public String toString() {
            return "MetricaServiceDescriptor{apiLevel=" + this.f985a + ", score=" + this.b + ", timeInstalled=" + this.c + '}';
        }
    }

    public static Intent a(Context context) {
        Intent intent = new Intent(IMetricaService.class.getName(), Uri.parse("metrica://" + context.getPackageName()));
        if (bk.b(11)) {
            intent.addFlags(32);
        }
        return intent;
    }

    public static List<ResolveInfo> a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        if (queryIntentServices != null) {
            return queryIntentServices;
        }
        return new ArrayList();
    }

    public static List<a> b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : a(context, a(context))) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if ((!(!serviceInfo.enabled) && !(!serviceInfo.exported)) && !(!bi.a(serviceInfo.permission))) {
                long a2 = a(packageManager, serviceInfo.packageName);
                if (a(packageManager, serviceInfo.packageName, "android.permission.INTERNET")) {
                    int a3 = a(serviceInfo);
                    arrayList.add(new a(resolveInfo.serviceInfo, (a3 << 5) + ((a(packageManager, ((PackageItemInfo) serviceInfo).packageName, "android.permission.ACCESS_COARSE_LOCATION") ? 1 : 0) * 16) + ((a(packageManager, ((PackageItemInfo) serviceInfo).packageName, "android.permission.ACCESS_FINE_LOCATION") ? 1 : 0) * 8) + ((a(packageManager, ((PackageItemInfo) serviceInfo).packageName, "android.permission.ACCESS_WIFI_STATE") ? 1 : 0) * 4) + ((a(packageManager, ((PackageItemInfo) serviceInfo).packageName, "android.permission.ACCESS_NETWORK_STATE") ? 1 : 0) * 2) + ((a(packageManager, ((PackageItemInfo) serviceInfo).packageName, "android.permission.READ_PHONE_STATE") ? 1 : 0) * 1), a3, a2));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002a A[Catch:{ Exception -> 0x002e }] */
    public static long a(PackageManager packageManager, String str) {
        long j;
        File file;
        long j2 = -1;
        try {
            if (bk.b(8)) {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                j = Math.max(packageInfo.firstInstallTime, packageInfo.lastUpdateTime);
                file = new File(packageManager.getApplicationInfo(str, 0).sourceDir);
                if (file.exists()) {
                    j2 = file.lastModified();
                }
                return Math.max(j, j2);
            }
        } catch (Exception unused) {
        }
        j = -1;
        try {
            file = new File(packageManager.getApplicationInfo(str, 0).sourceDir);
            if (file.exists()) {
            }
        } catch (Exception unused2) {
        }
        return Math.max(j, j2);
    }

    private static boolean a(PackageManager packageManager, String str, String str2) {
        return str2 == null || packageManager.checkPermission(str2, str) == 0;
    }

    public static int a(PackageItemInfo packageItemInfo) {
        if (packageItemInfo.metaData != null) {
            try {
                return packageItemInfo.metaData.getInt("metrica:api:level");
            } catch (Exception unused) {
            }
        }
        return -1;
    }

    public static Intent c(Context context) {
        return a(context).putExtras(e(context)).setPackage(context.getApplicationContext().getPackageName());
    }

    private static Bundle e(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            return bundle == null ? new Bundle() : bundle;
        } catch (Exception unused) {
            return new Bundle();
        }
    }
}
