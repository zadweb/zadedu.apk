package androidx.media2.exoplayer.external.drm;

import androidx.media2.exoplayer.external.util.EventDispatcher;

/* access modifiers changed from: package-private */
public final /* synthetic */ class DefaultDrmSession$$Lambda$0 implements EventDispatcher.Event {
    static final EventDispatcher.Event $instance = new DefaultDrmSession$$Lambda$0();

    private DefaultDrmSession$$Lambda$0() {
    }

    @Override // androidx.media2.exoplayer.external.util.EventDispatcher.Event
    public void sendTo(Object obj) {
        ((DefaultDrmSessionEventListener) obj).onDrmSessionReleased();
    }
}
