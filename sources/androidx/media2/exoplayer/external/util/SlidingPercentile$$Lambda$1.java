package androidx.media2.exoplayer.external.util;

import androidx.media2.exoplayer.external.util.SlidingPercentile;
import java.util.Comparator;

final /* synthetic */ class SlidingPercentile$$Lambda$1 implements Comparator {
    static final Comparator $instance = new SlidingPercentile$$Lambda$1();

    private SlidingPercentile$$Lambda$1() {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Float.compare(((SlidingPercentile.Sample) obj).value, ((SlidingPercentile.Sample) obj2).value);
    }
}
