package com.onesignal.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.appnext.base.b.d;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import com.onesignal.shortcutbadger.util.CloseHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class OPPOHomeBader implements Badger {
    private static int ROMVERSION = -1;

    @Override // com.onesignal.shortcutbadger.Badger
    public void executeBadge(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        if (i == 0) {
            i = -1;
        }
        Intent intent = new Intent("com.oppo.unsettledevent");
        intent.putExtra("pakeageName", componentName.getPackageName());
        intent.putExtra("number", i);
        intent.putExtra("upgradeNumber", i);
        if (BroadcastHelper.canResolveBroadcast(context, intent)) {
            context.sendBroadcast(intent);
        } else if (getSupportVersion() == 6) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("app_badge_count", i);
                context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String) null, bundle);
            } catch (Throwable unused) {
                throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
            }
        }
    }

    @Override // com.onesignal.shortcutbadger.Badger
    public List<String> getSupportLaunchers() {
        return Collections.singletonList("com.oppo.launcher");
    }

    private int getSupportVersion() {
        int i;
        int i2 = ROMVERSION;
        if (i2 >= 0) {
            return i2;
        }
        try {
            i = ((Integer) executeClassLoad(getClass("com.color.os.ColorBuild"), "getColorOSVERSION", null, null)).intValue();
        } catch (Exception unused) {
            i = 0;
        }
        if (i == 0) {
            try {
                String systemProperty = getSystemProperty("ro.build.version.opporom");
                if (systemProperty.startsWith("V1.4")) {
                    return 3;
                }
                if (systemProperty.startsWith("V2.0")) {
                    return 4;
                }
                if (systemProperty.startsWith("V2.1")) {
                    return 5;
                }
            } catch (Exception unused2) {
            }
        }
        ROMVERSION = i;
        return i;
    }

    private Object executeClassLoad(Class cls, String str, Class[] clsArr, Object[] objArr) {
        Method method;
        if (cls == null || checkObjExists(str) || (method = getMethod(cls, str, clsArr)) == null) {
            return null;
        }
        method.setAccessible(true);
        try {
            return method.invoke(null, objArr);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:4:0x000a */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Class */
    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.lang.Class */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.reflect.Method] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (r3.getSuperclass() != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return getMethod(r3.getSuperclass(), r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        return r3.getMethod(r4, r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0015 */
    private Method getMethod(Class cls, String str, Class[] clsArr) {
        if (cls == 0 || checkObjExists(str)) {
            return null;
        }
        cls.getMethods();
        cls.getDeclaredMethods();
        cls = cls.getDeclaredMethod(str, clsArr);
        return cls;
    }

    private Class getClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    private boolean checkObjExists(Object obj) {
        return obj == null || obj.toString().equals("") || obj.toString().trim().equals("null");
    }

    private String getSystemProperty(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), d.fb);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                CloseHelper.closeQuietly(bufferedReader);
                return readLine;
            } catch (IOException unused) {
                CloseHelper.closeQuietly(bufferedReader);
                return null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                CloseHelper.closeQuietly(bufferedReader2);
                throw th;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
            CloseHelper.closeQuietly(bufferedReader);
            return null;
        } catch (Throwable th3) {
            th = th3;
            CloseHelper.closeQuietly(bufferedReader2);
            throw th;
        }
    }
}
