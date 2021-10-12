package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import java.io.Closeable;
import java.io.IOException;

/* access modifiers changed from: package-private */
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public abstract class TransportRuntimeComponent implements Closeable {

    /* access modifiers changed from: package-private */
    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    public interface Builder {
        TransportRuntimeComponent build();

        Builder setApplicationContext(Context context);
    }

    /* access modifiers changed from: package-private */
    public abstract EventStore getEventStore();

    /* access modifiers changed from: package-private */
    public abstract TransportRuntime getTransportRuntime();

    TransportRuntimeComponent() {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        getEventStore().close();
    }
}
