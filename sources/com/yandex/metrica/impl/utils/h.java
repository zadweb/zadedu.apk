package com.yandex.metrica.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import com.yandex.metrica.MetricaService;

public class h {

    public static final class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final Context f1192a;

        public a(Context context) {
            this.f1192a = context;
        }

        public void run() {
            Context context = this.f1192a;
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 516);
                if (packageInfo.services != null) {
                    ServiceInfo[] serviceInfoArr = packageInfo.services;
                    for (ServiceInfo serviceInfo : serviceInfoArr) {
                        if (MetricaService.class.getName().equals(serviceInfo.name) && !serviceInfo.enabled) {
                            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, MetricaService.class), 1, 1);
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }
}
