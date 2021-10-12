package androidx.media2.exoplayer.external.extractor.wav;

import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorsFactory;

final /* synthetic */ class WavExtractor$$Lambda$0 implements ExtractorsFactory {
    static final ExtractorsFactory $instance = new WavExtractor$$Lambda$0();

    private WavExtractor$$Lambda$0() {
    }

    @Override // androidx.media2.exoplayer.external.extractor.ExtractorsFactory
    public Extractor[] createExtractors() {
        return WavExtractor.lambda$static$0$WavExtractor();
    }
}
