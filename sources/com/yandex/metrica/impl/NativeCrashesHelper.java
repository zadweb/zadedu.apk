package com.yandex.metrica.impl;

import android.content.Context;
import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.ExecutorService;

/* access modifiers changed from: package-private */
public class NativeCrashesHelper {

    /* renamed from: a  reason: collision with root package name */
    private String f936a;
    private final Context b;
    private boolean c;
    private boolean d;

    private static native void cancelSetUpNativeUncaughtExceptionHandler();

    private static native void logsEnabled(boolean z);

    private static native void setUpNativeUncaughtExceptionHandler(String str);

    NativeCrashesHelper(Context context) {
        this.b = context;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(ay ayVar, ExecutorService executorService) {
        if (c()) {
            executorService.execute(new a(ayVar, this));
            this.c = false;
        }
    }

    private boolean b() {
        return this.f936a != null;
    }

    private boolean c() {
        return b() && this.c;
    }

    private static boolean b(boolean z) {
        try {
            logsEnabled(z);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final ay f937a;
        private final NativeCrashesHelper b;

        a(ay ayVar, NativeCrashesHelper nativeCrashesHelper) {
            this.b = nativeCrashesHelper;
            this.f937a = ayVar;
        }

        public void run() {
            File file;
            String str = this.b.f936a;
            for (String str2 : NativeCrashesHelper.a(str)) {
                String str3 = str + "/" + str2;
                try {
                    String b2 = r.b(r.a(str3));
                    if (b2 != null) {
                        this.f937a.a(b2);
                    }
                    file = new File(str3);
                } catch (Exception unused) {
                    file = new File(str3);
                } catch (Throwable th) {
                    new File(str3).delete();
                    throw th;
                }
                file.delete();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        try {
            System.loadLibrary("YandexMetricaNativeModule");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2.c = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0040 */
    public synchronized void a(boolean z) {
        if (z) {
            if (!this.d && a()) {
                b(false);
                this.f936a = this.b.getFilesDir().getAbsolutePath() + "/YandexMetricaNativeCrashes";
            }
            this.d = true;
            if (b()) {
                setUpNativeUncaughtExceptionHandler(this.f936a);
                this.c = true;
            }
            return;
        }
        try {
            if (c()) {
                cancelSetUpNativeUncaughtExceptionHandler();
            }
        } catch (Throwable unused) {
        }
        this.c = false;
    }

    static /* synthetic */ String[] a(String str) {
        File file = new File(str + "/");
        if (!file.mkdir() && !file.exists()) {
            return new String[0];
        }
        String[] list = file.list(new FilenameFilter() {
            /* class com.yandex.metrica.impl.NativeCrashesHelper.AnonymousClass1 */

            public boolean accept(File file, String str) {
                return str.endsWith(".dmp");
            }
        });
        return list != null ? list : new String[0];
    }
}
