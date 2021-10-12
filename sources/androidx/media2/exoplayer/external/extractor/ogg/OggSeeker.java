package androidx.media2.exoplayer.external.extractor.ogg;

import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.extractor.SeekMap;
import java.io.IOException;

/* access modifiers changed from: package-private */
public interface OggSeeker {
    SeekMap createSeekMap();

    long read(ExtractorInput extractorInput) throws IOException, InterruptedException;

    long startSeek(long j);
}
