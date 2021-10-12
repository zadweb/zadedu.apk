package androidx.media2.exoplayer.external.mediacodec;

import androidx.media2.exoplayer.external.mediacodec.MediaCodecUtil;

/* access modifiers changed from: package-private */
public final /* synthetic */ class MediaCodecUtil$$Lambda$2 implements MediaCodecUtil.ScoreProvider {
    static final MediaCodecUtil.ScoreProvider $instance = new MediaCodecUtil$$Lambda$2();

    private MediaCodecUtil$$Lambda$2() {
    }

    @Override // androidx.media2.exoplayer.external.mediacodec.MediaCodecUtil.ScoreProvider
    public int getScore(Object obj) {
        return MediaCodecUtil.lambda$applyWorkarounds$2$MediaCodecUtil((MediaCodecInfo) obj);
    }
}
