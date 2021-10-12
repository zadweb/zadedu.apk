package com.google.android.play.core.internal;

import java.io.PrintStream;

public final class bz {

    /* renamed from: a  reason: collision with root package name */
    static final bt f167a;

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0080 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0081  */
    static {
        bt btVar;
        Integer num = null;
        try {
            num = (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = bx.class.getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 133);
            sb.append("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ");
            sb.append(name);
            sb.append("will be used. The error is: ");
            printStream.println(sb.toString());
            th.printStackTrace(System.err);
            btVar = new bx();
        }
        if (num == null || num.intValue() < 19) {
            btVar = !Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? new bw() : new bx();
            f167a = btVar;
            if (num == null) {
                num.intValue();
                return;
            }
            return;
        }
        btVar = new by();
        f167a = btVar;
        if (num == null) {
        }
    }

    public static void a(Throwable th, Throwable th2) {
        f167a.a(th, th2);
    }
}
