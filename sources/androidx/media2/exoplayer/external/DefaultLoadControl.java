package androidx.media2.exoplayer.external;

import androidx.media2.exoplayer.external.source.TrackGroupArray;
import androidx.media2.exoplayer.external.trackselection.TrackSelectionArray;
import androidx.media2.exoplayer.external.upstream.Allocator;
import androidx.media2.exoplayer.external.upstream.DefaultAllocator;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Util;
import com.appnext.base.b.d;
import com.mopub.volley.DefaultRetryPolicy;

public class DefaultLoadControl implements LoadControl {
    private final DefaultAllocator allocator;
    private final long backBufferDurationUs;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean hasVideo;
    private boolean isBuffering;
    private final long maxBufferUs;
    private final long minBufferAudioUs;
    private final long minBufferVideoUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final boolean retainBackBufferFromKeyframe;
    private final int targetBufferBytesOverwrite;
    private int targetBufferSize;

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536));
    }

    @Deprecated
    public DefaultLoadControl(DefaultAllocator defaultAllocator) {
        this(defaultAllocator, d.fd, 50000, 50000, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 5000, -1, true, 0, false);
    }

    protected DefaultLoadControl(DefaultAllocator defaultAllocator, int i, int i2, int i3, int i4, int i5, int i6, boolean z, int i7, boolean z2) {
        assertGreaterOrEqual(i4, 0, "bufferForPlaybackMs", "0");
        assertGreaterOrEqual(i5, 0, "bufferForPlaybackAfterRebufferMs", "0");
        assertGreaterOrEqual(i, i4, "minBufferAudioMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(i2, i4, "minBufferVideoMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(i, i5, "minBufferAudioMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(i2, i5, "minBufferVideoMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(i3, i, "maxBufferMs", "minBufferAudioMs");
        assertGreaterOrEqual(i3, i2, "maxBufferMs", "minBufferVideoMs");
        assertGreaterOrEqual(i7, 0, "backBufferDurationMs", "0");
        this.allocator = defaultAllocator;
        this.minBufferAudioUs = C.msToUs((long) i);
        this.minBufferVideoUs = C.msToUs((long) i2);
        this.maxBufferUs = C.msToUs((long) i3);
        this.bufferForPlaybackUs = C.msToUs((long) i4);
        this.bufferForPlaybackAfterRebufferUs = C.msToUs((long) i5);
        this.targetBufferBytesOverwrite = i6;
        this.prioritizeTimeOverSizeThresholds = z;
        this.backBufferDurationUs = C.msToUs((long) i7);
        this.retainBackBufferFromKeyframe = z2;
    }

    @Override // androidx.media2.exoplayer.external.LoadControl
    public void onPrepared() {
        reset(false);
    }

    @Override // androidx.media2.exoplayer.external.LoadControl
    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        this.hasVideo = hasVideo(rendererArr, trackSelectionArray);
        int i = this.targetBufferBytesOverwrite;
        if (i == -1) {
            i = calculateTargetBufferSize(rendererArr, trackSelectionArray);
        }
        this.targetBufferSize = i;
        this.allocator.setTargetBufferSize(i);
    }

    @Override // androidx.media2.exoplayer.external.LoadControl
    public void onStopped() {
        reset(true);
    }

    @Override // androidx.media2.exoplayer.external.LoadControl
    public void onReleased() {
        reset(true);
    }

    @Override // androidx.media2.exoplayer.external.LoadControl
    public Allocator getAllocator() {
        return this.allocator;
    }

    @Override // androidx.media2.exoplayer.external.LoadControl
    public long getBackBufferDurationUs() {
        return this.backBufferDurationUs;
    }

    @Override // androidx.media2.exoplayer.external.LoadControl
    public boolean retainBackBufferFromKeyframe() {
        return this.retainBackBufferFromKeyframe;
    }

    @Override // androidx.media2.exoplayer.external.LoadControl
    public boolean shouldContinueLoading(long j, float f) {
        boolean z = true;
        boolean z2 = this.allocator.getTotalBytesAllocated() >= this.targetBufferSize;
        long j2 = this.hasVideo ? this.minBufferVideoUs : this.minBufferAudioUs;
        if (f > 1.0f) {
            j2 = Math.min(Util.getMediaDurationForPlayoutDuration(j2, f), this.maxBufferUs);
        }
        if (j < j2) {
            if (!this.prioritizeTimeOverSizeThresholds && z2) {
                z = false;
            }
            this.isBuffering = z;
        } else if (j >= this.maxBufferUs || z2) {
            this.isBuffering = false;
        }
        return this.isBuffering;
    }

    @Override // androidx.media2.exoplayer.external.LoadControl
    public boolean shouldStartPlayback(long j, float f, boolean z) {
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(j, f);
        long j2 = z ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        return j2 <= 0 || playoutDurationForMediaDuration >= j2 || (!this.prioritizeTimeOverSizeThresholds && this.allocator.getTotalBytesAllocated() >= this.targetBufferSize);
    }

    /* access modifiers changed from: protected */
    public int calculateTargetBufferSize(Renderer[] rendererArr, TrackSelectionArray trackSelectionArray) {
        int i = 0;
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            if (trackSelectionArray.get(i2) != null) {
                i += Util.getDefaultBufferSize(rendererArr[i2].getTrackType());
            }
        }
        return i;
    }

    private void reset(boolean z) {
        this.targetBufferSize = 0;
        this.isBuffering = false;
        if (z) {
            this.allocator.reset();
        }
    }

    private static boolean hasVideo(Renderer[] rendererArr, TrackSelectionArray trackSelectionArray) {
        for (int i = 0; i < rendererArr.length; i++) {
            if (rendererArr[i].getTrackType() == 2 && trackSelectionArray.get(i) != null) {
                return true;
            }
        }
        return false;
    }

    private static void assertGreaterOrEqual(int i, int i2, String str, String str2) {
        boolean z = i >= i2;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" cannot be less than ");
        sb.append(str2);
        Assertions.checkArgument(z, sb.toString());
    }
}
