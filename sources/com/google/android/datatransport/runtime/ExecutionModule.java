package com.google.android.datatransport.runtime;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* access modifiers changed from: package-private */
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public abstract class ExecutionModule {
    static Executor executor() {
        return Executors.newSingleThreadExecutor();
    }
}
