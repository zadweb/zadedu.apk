package androidx.media2.exoplayer.external.mediacodec;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.mediacodec.MediaCodecUtil;

/* access modifiers changed from: package-private */
public final /* synthetic */ class MediaCodecUtil$$Lambda$0 implements MediaCodecUtil.ScoreProvider {
    private final Format arg$1;

    MediaCodecUtil$$Lambda$0(Format format) {
        this.arg$1 = format;
    }

    @Override // androidx.media2.exoplayer.external.mediacodec.MediaCodecUtil.ScoreProvider
    public int getScore(Object obj) {
        return MediaCodecUtil.lambda$getDecoderInfosSortedByFormatSupport$0$MediaCodecUtil(this.arg$1, (MediaCodecInfo) obj);
    }
}
