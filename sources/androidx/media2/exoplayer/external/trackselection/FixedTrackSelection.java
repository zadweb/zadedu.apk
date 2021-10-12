package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.source.TrackGroup;
import androidx.media2.exoplayer.external.source.chunk.MediaChunk;
import androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator;
import java.util.List;

public final class FixedTrackSelection extends BaseTrackSelection {
    private final Object data;
    private final int reason;

    @Override // androidx.media2.exoplayer.external.trackselection.TrackSelection
    public int getSelectedIndex() {
        return 0;
    }

    @Override // androidx.media2.exoplayer.external.trackselection.TrackSelection, androidx.media2.exoplayer.external.trackselection.BaseTrackSelection
    public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i, int i2, Object obj) {
        super(trackGroup, i);
        this.reason = i2;
        this.data = obj;
    }

    @Override // androidx.media2.exoplayer.external.trackselection.TrackSelection
    public int getSelectionReason() {
        return this.reason;
    }

    @Override // androidx.media2.exoplayer.external.trackselection.TrackSelection
    public Object getSelectionData() {
        return this.data;
    }
}
