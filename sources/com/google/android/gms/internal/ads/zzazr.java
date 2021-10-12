package com.google.android.gms.internal.ads;

import java.io.PrintStream;
import java.io.PrintWriter;

public final class zzazr {
    private static final zzazs zzdov;
    private static final int zzdow;

    static final class zza extends zzazs {
        zza() {
        }

        @Override // com.google.android.gms.internal.ads.zzazs
        public final void zza(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0068  */
    static {
        zzazs zzazs;
        Integer num;
        Throwable th;
        int i = 1;
        try {
            num = zzaau();
            if (num != null) {
                try {
                    if (num.intValue() >= 19) {
                        zzazs = new zzazw();
                        zzdov = zzazs;
                        if (num != null) {
                            i = num.intValue();
                        }
                        zzdow = i;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    PrintStream printStream = System.err;
                    String name = zza.class.getName();
                    StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 132);
                    sb.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
                    sb.append(name);
                    sb.append("will be used. The error is: ");
                    printStream.println(sb.toString());
                    th.printStackTrace(System.err);
                    zzazs = new zza();
                    zzdov = zzazs;
                    if (num != null) {
                    }
                    zzdow = i;
                }
            }
            zzazs = Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ true ? new zzazv() : new zza();
        } catch (Throwable th3) {
            th = th3;
            num = null;
            PrintStream printStream2 = System.err;
            String name2 = zza.class.getName();
            StringBuilder sb2 = new StringBuilder(String.valueOf(name2).length() + 132);
            sb2.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
            sb2.append(name2);
            sb2.append("will be used. The error is: ");
            printStream2.println(sb2.toString());
            th.printStackTrace(System.err);
            zzazs = new zza();
            zzdov = zzazs;
            if (num != null) {
            }
            zzdow = i;
        }
        zzdov = zzazs;
        if (num != null) {
        }
        zzdow = i;
    }

    public static void zza(Throwable th, PrintWriter printWriter) {
        zzdov.zza(th, printWriter);
    }

    private static Integer zzaau() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
