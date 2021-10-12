package androidx.media2.exoplayer.external.source;

import androidx.media2.exoplayer.external.FormatHolder;
import androidx.media2.exoplayer.external.decoder.DecoderInputBuffer;
import java.io.IOException;

public final class EmptySampleStream implements SampleStream {
    @Override // androidx.media2.exoplayer.external.source.SampleStream
    public boolean isReady() {
        return true;
    }

    @Override // androidx.media2.exoplayer.external.source.SampleStream
    public void maybeThrowError() throws IOException {
    }

    @Override // androidx.media2.exoplayer.external.source.SampleStream
    public int skipData(long j) {
        return 0;
    }

    @Override // androidx.media2.exoplayer.external.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        decoderInputBuffer.setFlags(4);
        return -4;
    }
}
