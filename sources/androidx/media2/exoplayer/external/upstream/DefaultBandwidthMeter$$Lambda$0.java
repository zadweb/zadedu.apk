package androidx.media2.exoplayer.external.upstream;

import androidx.media2.exoplayer.external.upstream.BandwidthMeter;
import androidx.media2.exoplayer.external.util.EventDispatcher;

final /* synthetic */ class DefaultBandwidthMeter$$Lambda$0 implements EventDispatcher.Event {
    private final int arg$1;
    private final long arg$2;
    private final long arg$3;

    DefaultBandwidthMeter$$Lambda$0(int i, long j, long j2) {
        this.arg$1 = i;
        this.arg$2 = j;
        this.arg$3 = j2;
    }

    @Override // androidx.media2.exoplayer.external.util.EventDispatcher.Event
    public void sendTo(Object obj) {
        DefaultBandwidthMeter.lambda$maybeNotifyBandwidthSample$0$DefaultBandwidthMeter(this.arg$1, this.arg$2, this.arg$3, (BandwidthMeter.EventListener) obj);
    }
}
