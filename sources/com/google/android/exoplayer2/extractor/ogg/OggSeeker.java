package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;
import java.io.IOException;

/* access modifiers changed from: package-private */
public interface OggSeeker {
    SeekMap createSeekMap();

    long read(ExtractorInput extractorInput) throws IOException, InterruptedException;

    void startSeek(long j);
}
