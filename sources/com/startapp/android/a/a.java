package com.startapp.android.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.appnext.base.b.d;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f259a = {"15555215554", "15555215556", "15555215558", "15555215560", "15555215562", "15555215564", "15555215566", "15555215568", "15555215570", "15555215572", "15555215574", "15555215576", "15555215578", "15555215580", "15555215582", "15555215584"};
    private static final String[] b = {"000000000000000", "e21833235b6eef10", "012345678912345"};
    private static final String[] c = {"310260000000000"};
    private static final String[] d = {"/dev/socket/genyd", "/dev/socket/baseband_genyd"};
    private static final String[] e = {"goldfish"};
    private static final String[] f = {"/dev/socket/qemud", "/dev/qemu_pipe"};
    private static final String[] g = {"ueventd.android_x86.rc", "x86.prop", "ueventd.ttVM_x86.rc", "init.ttVM_x86.rc", "fstab.ttVM_x86", "fstab.vbox86", "init.vbox86.rc", "ueventd.vbox86.rc"};
    private static final String[] h = {"fstab.andy", "ueventd.andy.rc"};
    private static final String[] i = {"fstab.nox", "init.nox.rc", "ueventd.nox.rc", "/BigNoxGameHD", "/YSLauncher"};
    private static final b[] j = {new b("init.svc.qemud", null), new b("init.svc.qemu-props", null), new b("qemu.hw.mainkeys", null), new b("qemu.sf.fake_camera", null), new b("qemu.sf.lcd_density", null), new b("ro.bootloader", "unknown"), new b("ro.bootmode", "unknown"), new b("ro.hardware", "goldfish"), new b("ro.kernel.android.qemud", null), new b("ro.kernel.qemu.gles", null), new b("ro.kernel.qemu", "1"), new b("ro.product.device", "generic"), new b("ro.product.model", "sdk"), new b("ro.product.name", "sdk"), new b("ro.serialno", null), new b("ro.build.description", "72656C656173652D6B657973"), new b("ro.build.fingerprint", "3A757365722F72656C656173652D6B657973"), new b("net.eth0.dns1", null), new b("rild.libpath", "2F73797374656D2F6C69622F6C69627265666572656E63652D72696C2E736F"), new b("ro.radio.use-ppp", null), new b("gsm.version.baseband", null), new b("ro.build.tags", "72656C656173652D6B65"), new b("ro.build.display.id", "746573742D"), new b("init.svc.console", null)};
    private static a o;
    private static Boolean p;
    private final Context k;
    private boolean l = false;
    private boolean m = true;
    private List<String> n;

    public static boolean a(Context context) {
        if (p == null) {
            p = Boolean.valueOf(b(context).a());
        }
        return p.booleanValue();
    }

    private static a b(Context context) {
        if (context != null) {
            if (o == null) {
                o = new a(context.getApplicationContext());
            }
            return o;
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    private a(Context context) {
        ArrayList arrayList = new ArrayList();
        this.n = arrayList;
        this.k = context;
        arrayList.add("com.google.android.launcher.layouts.genymotion");
        this.n.add("com.bluestacks");
        this.n.add("com.bignox.app");
        this.n.add("com.vphone.launcher");
    }

    private boolean a() {
        boolean b2 = b();
        if (!b2) {
            b2 = c();
        }
        return !b2 ? d() : b2;
    }

    private boolean b() {
        return Build.FINGERPRINT.startsWith("generic") || Build.MODEL.contains("google_sdk") || Build.MODEL.toLowerCase().contains("droid4x") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for") || Build.MANUFACTURER.contains("Genymotion") || Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("vbox86") || Build.PRODUCT.equals("sdk") || Build.PRODUCT.equals("google_sdk") || Build.PRODUCT.equals("sdk_x86") || Build.PRODUCT.equals("vbox86p") || Build.BOARD.toLowerCase().contains("nox") || Build.BOOTLOADER.toLowerCase().contains("nox") || Build.HARDWARE.toLowerCase().contains("nox") || Build.PRODUCT.toLowerCase().contains("nox") || Build.SERIAL.toLowerCase().contains("nox") || Build.FINGERPRINT.startsWith("unknown") || Build.FINGERPRINT.contains("Andy") || Build.FINGERPRINT.contains("ttVM_Hdragon") || Build.FINGERPRINT.contains("vbox86p") || Build.HARDWARE.contains("ttVM_x86") || Build.MODEL.equals("sdk") || Build.MODEL.contains("Droid4X") || Build.MODEL.contains("TiantianVM") || Build.MODEL.contains("Andy") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"));
    }

    private boolean c() {
        return e() || a(d, "Geny") || a(h, "Andy") || a(i, "Nox") || j() || a(f, "Pipes") || l() || (k() && a(g, "X86"));
    }

    private boolean d() {
        if (this.m && !this.n.isEmpty()) {
            PackageManager packageManager = this.k.getPackageManager();
            for (String str : this.n) {
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
                if (!(launchIntentForPackage == null || packageManager.queryIntentActivities(launchIntentForPackage, 65536).isEmpty())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean e() {
        if (!b(this.k, "android.permission.READ_PHONE_STATE") || !this.l || !m()) {
            return false;
        }
        if (f() || g() || h() || i()) {
            return true;
        }
        return false;
    }

    private boolean f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.k.getSystemService("phone");
        if (telephonyManager != null) {
            String line1Number = telephonyManager.getLine1Number();
            for (String str : f259a) {
                if (str.equalsIgnoreCase(line1Number)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean g() {
        TelephonyManager telephonyManager = (TelephonyManager) this.k.getSystemService("phone");
        if (telephonyManager != null) {
            String deviceId = telephonyManager.getDeviceId();
            for (String str : b) {
                if (str.equalsIgnoreCase(deviceId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean h() {
        TelephonyManager telephonyManager = (TelephonyManager) this.k.getSystemService("phone");
        if (telephonyManager != null) {
            String subscriberId = telephonyManager.getSubscriberId();
            for (String str : c) {
                if (str.equalsIgnoreCase(subscriberId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean i() {
        TelephonyManager telephonyManager = (TelephonyManager) this.k.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName().equalsIgnoreCase("android");
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006d A[SYNTHETIC, Splitter:B:28:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0074 A[SYNTHETIC, Splitter:B:34:0x0074] */
    private boolean j() {
        Throwable th;
        File[] fileArr = {new File("/proc/tty/drivers"), new File("/proc/cpuinfo")};
        for (int i2 = 0; i2 < 2; i2++) {
            File file = fileArr[i2];
            if (file.exists() && file.canRead()) {
                char[] cArr = new char[d.fb];
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = null;
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    while (true) {
                        try {
                            int read = bufferedReader2.read(cArr);
                            if (read != -1) {
                                sb.append(cArr, 0, read);
                            } else {
                                try {
                                    break;
                                } catch (IOException unused) {
                                }
                            }
                        } catch (Exception unused2) {
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                            }
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                            }
                            throw th;
                        }
                    }
                    bufferedReader2.close();
                    String sb2 = sb.toString();
                    for (String str : e) {
                        if (sb2.contains(str)) {
                            return true;
                        }
                    }
                    continue;
                } catch (Exception unused3) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused4) {
                        }
                    }
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused5) {
                        }
                    }
                    throw th;
                }
            }
        }
        return false;
    }

    private boolean a(String[] strArr, String str) {
        File file;
        for (String str2 : strArr) {
            if (!b(this.k, "android.permission.READ_EXTERNAL_STORAGE") || !str2.contains("/") || !str.equals("Nox")) {
                file = new File(str2);
            } else {
                file = new File(Environment.getExternalStorageDirectory() + str2);
            }
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }

    private boolean k() {
        b[] bVarArr = j;
        int i2 = 0;
        for (b bVar : bVarArr) {
            String a2 = a(this.k, bVar.f260a);
            if (bVar.b == null && a2 != null) {
                i2++;
            }
            if (!(bVar.b == null || a2 == null || !a2.contains(bVar.b))) {
                i2++;
            }
        }
        if (i2 >= 5) {
            return true;
        }
        return false;
    }

    private boolean l() {
        if (!b(this.k, "android.permission.INTERNET")) {
            return false;
        }
        String[] strArr = {"/system/bin/netcfg"};
        StringBuilder sb = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(strArr);
            processBuilder.directory(new File("/system/bin/"));
            processBuilder.redirectErrorStream(true);
            InputStream inputStream = processBuilder.start().getInputStream();
            byte[] bArr = new byte[d.fb];
            while (inputStream.read(bArr) != -1) {
                sb.append(new String(bArr));
            }
            inputStream.close();
        } catch (Exception unused) {
        }
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            return false;
        }
        String[] split = sb2.split("\n");
        for (String str : split) {
            if ((str.contains("wlan0") || str.contains("tunl0") || str.contains("eth0")) && str.contains("10.0.2.15")) {
                return true;
            }
        }
        return false;
    }

    private String a(Context context, String str) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) loadClass.getMethod("get", String.class).invoke(loadClass, str);
        } catch (Exception unused) {
            return null;
        }
    }

    private boolean m() {
        return this.k.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean b(Context context, String str) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                if (context.checkSelfPermission(str) == 0) {
                    return true;
                }
                return false;
            } else if (context.checkCallingOrSelfPermission(str) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
