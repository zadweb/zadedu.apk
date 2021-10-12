package com.startapp.android.b;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final Context f261a;

    public a(Context context) {
        this.f261a = context;
    }

    public boolean a() {
        return c() || d() || a("su") || a("busybox") || f() || g() || b() || h() || e();
    }

    public boolean b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    public boolean c() {
        return a((String[]) null);
    }

    public boolean a(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(b.f262a));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return a(arrayList);
    }

    public boolean d() {
        return b(null);
    }

    public boolean b(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(b.b));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return a(arrayList);
    }

    public boolean e() {
        return a("magisk");
    }

    public boolean a(String str) {
        boolean z = false;
        for (String str2 : b.d) {
            if (new File(str2, str).exists()) {
                z = true;
            }
        }
        return z;
    }

    private String[] i() {
        String[] strArr = new String[0];
        try {
            return new Scanner(Runtime.getRuntime().exec("getprop").getInputStream()).useDelimiter("\\A").next().split("\n");
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
            return strArr;
        }
    }

    private String[] j() {
        String[] strArr = new String[0];
        try {
            return new Scanner(Runtime.getRuntime().exec("mount").getInputStream()).useDelimiter("\\A").next().split("\n");
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
            return strArr;
        }
    }

    private boolean a(List<String> list) {
        PackageManager packageManager = this.f261a.getPackageManager();
        boolean z = false;
        for (String str : list) {
            try {
                packageManager.getPackageInfo(str, 0);
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return z;
    }

    public boolean f() {
        HashMap hashMap = new HashMap();
        hashMap.put("ro.debuggable", "1");
        hashMap.put("ro.secure", "0");
        String[] i = i();
        boolean z = false;
        for (String str : i) {
            for (String str2 : hashMap.keySet()) {
                if (str.contains(str2)) {
                    if (str.contains("[" + ((String) hashMap.get(str2)) + "]")) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public boolean g() {
        boolean z = false;
        for (String str : j()) {
            String[] split = str.split(" ");
            if (split.length >= 4) {
                String str2 = split[1];
                String str3 = split[3];
                for (String str4 : b.e) {
                    if (str2.equalsIgnoreCase(str4)) {
                        String[] split2 = str3.split(",");
                        int length = split2.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            } else if (split2[i].equalsIgnoreCase("rw")) {
                                z = true;
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                }
            }
        }
        return z;
    }

    public boolean h() {
        boolean z = false;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"which", "su"});
            if (new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null) {
                z = true;
            }
            if (process != null) {
                process.destroy();
            }
            return z;
        } catch (Throwable unused) {
            if (process != null) {
                process.destroy();
            }
            return false;
        }
    }
}
