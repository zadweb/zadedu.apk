package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.LocalServerSocket;
import android.net.Uri;
import android.text.TextUtils;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.r;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class ci {

    /* renamed from: a  reason: collision with root package name */
    private final Object f1060a;
    private final a b;
    private final ck c;
    private ch d;

    /* access modifiers changed from: private */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private static final ci f1062a = new ci((byte) 0);
    }

    /* synthetic */ ci(byte b2) {
        this();
    }

    public static ci a() {
        return b.f1062a;
    }

    private ci() {
        this.f1060a = new Object();
        this.b = new a(this, (byte) 0);
        this.c = new ck(this);
    }

    /* access modifiers changed from: package-private */
    public a b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public ch c() {
        return this.d;
    }

    public String d() {
        ch c2 = c();
        if (c2 == null) {
            return null;
        }
        return c2.c();
    }

    /* access modifiers changed from: package-private */
    public ch a(Context context, String str) {
        return a(context, str, context.getFileStreamPath("credentials.dat"));
    }

    /* access modifiers changed from: package-private */
    public ch b(Context context, String str) {
        return a(context, str, new File(context.getNoBackupFilesDir(), "credentials.dat"));
    }

    private ch a(Context context, String str, File file) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (applicationInfo == null) {
                return null;
            }
            return h(context, file.getAbsolutePath().replace(context.getApplicationInfo().dataDir, applicationInfo.dataDir));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private ch h(Context context, String str) {
        String a2;
        File file = new File(str);
        if (file.exists()) {
            synchronized (this.f1060a) {
                a2 = r.a(context, file);
            }
            if (a2 == null) {
                return null;
            }
            try {
                return new ch(new JSONObject(a2), file.lastModified());
            } catch (Exception | JSONException unused) {
            }
        }
        return null;
    }

    public String c(Context context, String str) {
        return i(context, str);
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return bk.a(21);
    }

    /* access modifiers changed from: package-private */
    public void d(Context context, String str) {
        try {
            synchronized (this.f1060a) {
                ch chVar = new ch(str, new cj(context), System.currentTimeMillis());
                this.d = chVar;
                String a2 = chVar.a();
                if (e()) {
                    e(context, a2);
                }
                synchronized (this.f1060a) {
                    r.a(context, "credentials.dat", a2);
                }
            }
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void e(Context context, String str) {
        synchronized (this.f1060a) {
            r.b(context, "credentials.dat", str);
        }
    }

    public String a(Context context) {
        return i(context, null);
    }

    private String i(Context context, String str) {
        synchronized (this.f1060a) {
            if (c() == null) {
                ch a2 = a(context, context.getPackageName());
                if (a2 == null) {
                    return b().a(context, str);
                } else if (e()) {
                    ch b2 = b(context, context.getPackageName());
                    if (!a2.a(b2) || !b2.e()) {
                        return b().a(context, a2.c());
                    }
                    this.d = a2;
                    return b2.c();
                } else if (a2.e()) {
                    this.d = a2;
                    return a2.c();
                } else {
                    return b().a(context, a2.c());
                }
            } else {
                return c().c();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String f(Context context, String str) {
        Cursor cursor;
        Throwable th;
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(str + ".MetricaContentProvider", 0);
        String str2 = null;
        str2 = null;
        str2 = null;
        str2 = null;
        Cursor cursor2 = null;
        str2 = null;
        str2 = null;
        if (resolveContentProvider != null && resolveContentProvider.enabled) {
            try {
                cursor = context.getContentResolver().query(Uri.parse(String.format(Locale.US, "content://%s.MetricaContentProvider/DEVICE_ID", str)), null, null, null, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            str2 = cursor.getString(cursor.getColumnIndex("DEVICE_ID"));
                        }
                    } catch (Exception unused) {
                    } catch (Throwable th2) {
                        th = th2;
                        cursor2 = cursor;
                        bk.a(cursor2);
                        throw th;
                    }
                }
            } catch (Exception unused2) {
                cursor = null;
            } catch (Throwable th3) {
                th = th3;
                bk.a(cursor2);
                throw th;
            }
            bk.a(cursor);
        }
        return str2;
    }

    /* access modifiers changed from: package-private */
    public ck f() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        ci f1061a;
        private LocalServerSocket b;

        /* synthetic */ a(ci ciVar, byte b2) {
            this(ciVar);
        }

        private a(ci ciVar) {
            this.f1061a = ciVar;
        }

        /* access modifiers changed from: package-private */
        public ci a() {
            return this.f1061a;
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            try {
                this.b = new LocalServerSocket("com.yandex.metrica.synchronization.deviceid");
                return true;
            } catch (IOException unused) {
                return false;
            }
        }

        public String a(Context context, String str) {
            TextUtils.isEmpty(str);
            a().f().a(context);
            dn dnVar = new dn(12);
            String str2 = null;
            do {
                if (b()) {
                    str2 = a(context, str, a().f().a(context));
                    LocalServerSocket localServerSocket = this.b;
                    if (localServerSocket != null) {
                        try {
                            localServerSocket.close();
                            this.b = null;
                        } catch (IOException unused) {
                        }
                    }
                } else {
                    dnVar.a();
                    dnVar.c();
                }
            } while (dnVar.b());
            return str2;
        }

        /* access modifiers changed from: package-private */
        public String a(Context context, String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    return null;
                }
                ci.a(a(), context, str2);
                return str2;
            } else if (str.equals(str2)) {
                ci.a(a(), context, str);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("update_snapshot", new c(context, str2, str));
                return str;
            } else if (TextUtils.isEmpty(str2)) {
                ci.a(a(), context, str);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("wtf_situation. App has id and elector hasn't", new c(context, str2, str));
                return str;
            } else {
                ci.a(a(), context, str2);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("overlapping_device_id", new c(context, str2, str));
                return str2;
            }
        }
    }

    /* access modifiers changed from: private */
    public static class c extends HashMap<String, Object> {
        public c(Context context, String str) {
            String packageName = context.getPackageName();
            put("passed_id", str);
            put("package_name", packageName);
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                put("version_code", Integer.valueOf(packageInfo.versionCode));
                put("version_name", packageInfo.versionName);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }

        public c(Context context, String str, String str2) {
            this(context, str);
            put("stored_device_id", str2);
        }
    }

    public static String g(Context context, String str) {
        String a2 = b.f1062a.a(context);
        if (!bi.a(a2)) {
            Intent a3 = be.a(context);
            a3.setPackage(str);
            for (ResolveInfo resolveInfo : be.a(context, a3)) {
                int a4 = be.a(resolveInfo.serviceInfo);
                if (a4 > 0 && a4 < 29) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("DEVICE_ID", a2);
                        if (!bi.a(a2)) {
                            context.getContentResolver().update(Uri.parse(String.format(Locale.US, "content://%s.MetricaContentProvider/DEVICE_ID", str)), contentValues, null, null);
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return a2;
    }

    static /* synthetic */ void a(ci ciVar, Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("saving_empty_device_id", new c(context, str));
        } else {
            ciVar.d(context, str);
        }
    }
}
