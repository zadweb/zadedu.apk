package com.tappx.a;

import android.os.AsyncTask;

/* access modifiers changed from: package-private */
public class x2 {
    public static void a(Runnable runnable) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(runnable);
    }
}
