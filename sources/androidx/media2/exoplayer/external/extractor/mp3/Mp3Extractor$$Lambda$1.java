package androidx.media2.exoplayer.external.extractor.mp3;

import androidx.media2.exoplayer.external.metadata.id3.Id3Decoder;

final /* synthetic */ class Mp3Extractor$$Lambda$1 implements Id3Decoder.FramePredicate {
    static final Id3Decoder.FramePredicate $instance = new Mp3Extractor$$Lambda$1();

    private Mp3Extractor$$Lambda$1() {
    }

    @Override // androidx.media2.exoplayer.external.metadata.id3.Id3Decoder.FramePredicate
    public boolean evaluate(int i, int i2, int i3, int i4, int i5) {
        return Mp3Extractor.lambda$static$1$Mp3Extractor(i, i2, i3, i4, i5);
    }
}
