package androidx.media2.exoplayer.external.source;

import androidx.media2.exoplayer.external.Timeline;

public abstract class ForwardingTimeline extends Timeline {
    protected final Timeline timeline;

    public ForwardingTimeline(Timeline timeline2) {
        this.timeline = timeline2;
    }

    @Override // androidx.media2.exoplayer.external.Timeline
    public int getWindowCount() {
        return this.timeline.getWindowCount();
    }

    @Override // androidx.media2.exoplayer.external.Timeline
    public int getNextWindowIndex(int i, int i2, boolean z) {
        return this.timeline.getNextWindowIndex(i, i2, z);
    }

    @Override // androidx.media2.exoplayer.external.Timeline
    public int getLastWindowIndex(boolean z) {
        return this.timeline.getLastWindowIndex(z);
    }

    @Override // androidx.media2.exoplayer.external.Timeline
    public int getFirstWindowIndex(boolean z) {
        return this.timeline.getFirstWindowIndex(z);
    }

    @Override // androidx.media2.exoplayer.external.Timeline
    public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
        return this.timeline.getWindow(i, window, z, j);
    }

    @Override // androidx.media2.exoplayer.external.Timeline
    public int getPeriodCount() {
        return this.timeline.getPeriodCount();
    }

    @Override // androidx.media2.exoplayer.external.Timeline
    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        return this.timeline.getPeriod(i, period, z);
    }

    @Override // androidx.media2.exoplayer.external.Timeline
    public int getIndexOfPeriod(Object obj) {
        return this.timeline.getIndexOfPeriod(obj);
    }

    @Override // androidx.media2.exoplayer.external.Timeline
    public Object getUidOfPeriod(int i) {
        return this.timeline.getUidOfPeriod(i);
    }
}
