package androidx.media2.exoplayer.external.drm;

import androidx.media2.exoplayer.external.util.EventDispatcher;

final /* synthetic */ class DefaultDrmSession$$Lambda$5 implements EventDispatcher.Event {
    private final Exception arg$1;

    DefaultDrmSession$$Lambda$5(Exception exc) {
        this.arg$1 = exc;
    }

    @Override // androidx.media2.exoplayer.external.util.EventDispatcher.Event
    public void sendTo(Object obj) {
        DefaultDrmSession.lambda$onError$0$DefaultDrmSession(this.arg$1, (DefaultDrmSessionEventListener) obj);
    }
}
