package com.truenet.android;

import a.a.a.g;
import a.a.b.b.e;
import a.a.b.b.h;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.startapp.common.a.b;
import com.startapp.common.c;
import com.truenet.android.a.b;
import com.truenet.android.a.d;
import com.truenet.android.a.f;
import com.truenet.android.a.i;
import java.util.List;
import java.util.Locale;

/* compiled from: StartAppSDK */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final Context f903a;
    private final TelephonyManager b;

    public a(Context context, TelephonyManager telephonyManager) {
        h.b(context, "context");
        h.b(telephonyManager, "telephonyManager");
        this.f903a = context;
        this.b = telephonyManager;
        c.c(context);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(Context context, TelephonyManager telephonyManager, int i, e eVar) {
        this(context, (i & 2) != 0 ? com.truenet.android.a.c.a(context) : telephonyManager);
    }

    private final boolean b() {
        Resources system = Resources.getSystem();
        h.a((Object) system, "Resources.getSystem()");
        DisplayMetrics displayMetrics = system.getDisplayMetrics();
        double d = (double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi);
        double d2 = (double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi);
        Double.isNaN(d);
        Double.isNaN(d);
        Double.isNaN(d2);
        Double.isNaN(d2);
        return Math.sqrt((d * d) + (d2 * d2)) > 6.5d;
    }

    public final DeviceInfo a() {
        String str;
        String str2;
        String networkOperatorName;
        List a2 = g.a();
        boolean z = true;
        String str3 = "";
        if (!a2.isEmpty()) {
            String valueOf = String.valueOf(((Location) g.c(a2)).getLatitude());
            str = String.valueOf(((Location) g.c(a2)).getLongitude());
            str2 = valueOf;
        } else {
            str2 = str3;
            str = str2;
        }
        Resources resources = this.f903a.getResources();
        h.a((Object) resources, "context.resources");
        Configuration configuration = resources.getConfiguration();
        h.a((Object) configuration, "context.resources.configuration");
        Locale a3 = b.a(configuration);
        b.c a4 = com.startapp.common.a.b.a().a(this.f903a);
        h.a((Object) a4, "AdvertisingIdSingleton.g…getAdvertisingId(context)");
        String a5 = a4.a();
        int phoneType = this.b.getPhoneType();
        String str4 = (phoneType == 0 || phoneType == 2 || (networkOperatorName = this.b.getNetworkOperatorName()) == null) ? str3 : networkOperatorName;
        TelephonyManager telephonyManager = this.b;
        String simOperator = telephonyManager.getSimState() == 5 ? telephonyManager.getSimOperator() : str3;
        TelephonyManager telephonyManager2 = this.b;
        String simOperatorName = telephonyManager2.getSimState() == 5 ? telephonyManager2.getSimOperatorName() : str3;
        if (!com.truenet.android.a.h.a(this.f903a, "android.permission.ACCESS_FINE_LOCATION") && !com.truenet.android.a.h.a(this.f903a, "android.permission.ACCESS_COARSE_LOCATION")) {
            z = false;
        }
        String valueOf2 = z ? String.valueOf(Integer.valueOf(f.a(this.b))) : str3;
        if (z) {
            str3 = String.valueOf(Integer.valueOf(f.b(this.b)));
        }
        c a6 = c.a();
        h.a((Object) a6, "NetworkStats.get()");
        String b2 = a6.b();
        String a7 = i.f905a.a(this.f903a);
        String str5 = b() ? "tablet" : "phone";
        String locale = a3.toString();
        h.a((Object) locale, "locale.toString()");
        h.a((Object) a5, "advertisingId");
        String valueOf3 = String.valueOf(Build.VERSION.SDK_INT);
        String str6 = Build.MODEL;
        h.a((Object) str6, "Build.MODEL");
        String str7 = Build.MANUFACTURER;
        h.a((Object) str7, "Build.MANUFACTURER");
        String str8 = Build.VERSION.RELEASE;
        h.a((Object) str8, "Build.VERSION.RELEASE");
        String packageName = this.f903a.getPackageName();
        h.a((Object) packageName, "context.packageName");
        h.a((Object) simOperator, "ips");
        h.a((Object) simOperatorName, "ipsName");
        String a8 = d.b(this.f903a).a();
        h.a((Object) b2, "signalLevel");
        return new DeviceInfo(str2, str, a7, locale, a5, "android", valueOf3, str6, str7, str8, packageName, str4, simOperator, simOperatorName, valueOf2, str3, a8, b2, str5, "1.0.16", "");
    }
}
