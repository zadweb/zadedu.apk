package androidx.media2.exoplayer.external.util;

import androidx.media2.exoplayer.external.util.EventDispatcher;

/* access modifiers changed from: package-private */
public final /* synthetic */ class EventDispatcher$HandlerAndListener$$Lambda$0 implements Runnable {
    private final EventDispatcher.HandlerAndListener arg$1;
    private final EventDispatcher.Event arg$2;

    EventDispatcher$HandlerAndListener$$Lambda$0(EventDispatcher.HandlerAndListener handlerAndListener, EventDispatcher.Event event) {
        this.arg$1 = handlerAndListener;
        this.arg$2 = event;
    }

    public void run() {
        this.arg$1.lambda$dispatch$0$EventDispatcher$HandlerAndListener(this.arg$2);
    }
}
