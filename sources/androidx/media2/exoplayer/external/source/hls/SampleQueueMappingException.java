package androidx.media2.exoplayer.external.source.hls;

import java.io.IOException;

public final class SampleQueueMappingException extends IOException {
    /* JADX WARNING: Illegal instructions before constructor call */
    public SampleQueueMappingException(String str) {
        super(r1.toString());
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 60);
        sb.append("Unable to bind a sample queue to TrackGroup with mime type ");
        sb.append(str);
        sb.append(".");
    }
}
