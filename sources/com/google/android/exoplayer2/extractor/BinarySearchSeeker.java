package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

public abstract class BinarySearchSeeker {
    private final int minimumSearchRange;
    protected final BinarySearchSeekMap seekMap;
    protected SeekOperationParams seekOperationParams;
    protected final TimestampSeeker timestampSeeker;

    public static final class DefaultSeekTimestampConverter implements SeekTimestampConverter {
        @Override // com.google.android.exoplayer2.extractor.BinarySearchSeeker.SeekTimestampConverter
        public long timeUsToTargetTime(long j) {
            return j;
        }
    }

    public static final class OutputFrameHolder {
    }

    /* access modifiers changed from: protected */
    public interface SeekTimestampConverter {
        long timeUsToTargetTime(long j);
    }

    /* access modifiers changed from: protected */
    public interface TimestampSeeker {

        /* renamed from: com.google.android.exoplayer2.extractor.BinarySearchSeeker$TimestampSeeker$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onSeekFinished(TimestampSeeker timestampSeeker) {
            }
        }

        void onSeekFinished();

        TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j, OutputFrameHolder outputFrameHolder) throws IOException, InterruptedException;
    }

    /* access modifiers changed from: protected */
    public void onSeekOperationFinished(boolean z, long j) {
    }

    protected BinarySearchSeeker(SeekTimestampConverter seekTimestampConverter, TimestampSeeker timestampSeeker2, long j, long j2, long j3, long j4, long j5, long j6, int i) {
        this.timestampSeeker = timestampSeeker2;
        this.minimumSearchRange = i;
        this.seekMap = new BinarySearchSeekMap(seekTimestampConverter, j, j2, j3, j4, j5, j6);
    }

    public final SeekMap getSeekMap() {
        return this.seekMap;
    }

    public final void setSeekTargetUs(long j) {
        SeekOperationParams seekOperationParams2 = this.seekOperationParams;
        if (seekOperationParams2 == null || seekOperationParams2.getSeekTimeUs() != j) {
            this.seekOperationParams = createSeekParamsForTargetTimeUs(j);
        }
    }

    public final boolean isSeeking() {
        return this.seekOperationParams != null;
    }

    public int handlePendingSeek(ExtractorInput extractorInput, PositionHolder positionHolder, OutputFrameHolder outputFrameHolder) throws InterruptedException, IOException {
        TimestampSeeker timestampSeeker2 = (TimestampSeeker) Assertions.checkNotNull(this.timestampSeeker);
        while (true) {
            SeekOperationParams seekOperationParams2 = (SeekOperationParams) Assertions.checkNotNull(this.seekOperationParams);
            long floorBytePosition = seekOperationParams2.getFloorBytePosition();
            long ceilingBytePosition = seekOperationParams2.getCeilingBytePosition();
            long nextSearchBytePosition = seekOperationParams2.getNextSearchBytePosition();
            if (ceilingBytePosition - floorBytePosition <= ((long) this.minimumSearchRange)) {
                markSeekOperationFinished(false, floorBytePosition);
                return seekToPosition(extractorInput, floorBytePosition, positionHolder);
            } else if (!skipInputUntilPosition(extractorInput, nextSearchBytePosition)) {
                return seekToPosition(extractorInput, nextSearchBytePosition, positionHolder);
            } else {
                extractorInput.resetPeekPosition();
                TimestampSearchResult searchForTimestamp = timestampSeeker2.searchForTimestamp(extractorInput, seekOperationParams2.getTargetTimePosition(), outputFrameHolder);
                int i = searchForTimestamp.type;
                if (i == -3) {
                    markSeekOperationFinished(false, nextSearchBytePosition);
                    return seekToPosition(extractorInput, nextSearchBytePosition, positionHolder);
                } else if (i == -2) {
                    seekOperationParams2.updateSeekFloor(searchForTimestamp.timestampToUpdate, searchForTimestamp.bytePositionToUpdate);
                } else if (i == -1) {
                    seekOperationParams2.updateSeekCeiling(searchForTimestamp.timestampToUpdate, searchForTimestamp.bytePositionToUpdate);
                } else if (i == 0) {
                    markSeekOperationFinished(true, searchForTimestamp.bytePositionToUpdate);
                    skipInputUntilPosition(extractorInput, searchForTimestamp.bytePositionToUpdate);
                    return seekToPosition(extractorInput, searchForTimestamp.bytePositionToUpdate, positionHolder);
                } else {
                    throw new IllegalStateException("Invalid case");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public SeekOperationParams createSeekParamsForTargetTimeUs(long j) {
        return new SeekOperationParams(j, this.seekMap.timeUsToTargetTime(j), this.seekMap.floorTimePosition, this.seekMap.ceilingTimePosition, this.seekMap.floorBytePosition, this.seekMap.ceilingBytePosition, this.seekMap.approxBytesPerFrame);
    }

    /* access modifiers changed from: protected */
    public final void markSeekOperationFinished(boolean z, long j) {
        this.seekOperationParams = null;
        this.timestampSeeker.onSeekFinished();
        onSeekOperationFinished(z, j);
    }

    /* access modifiers changed from: protected */
    public final boolean skipInputUntilPosition(ExtractorInput extractorInput, long j) throws IOException, InterruptedException {
        long position = j - extractorInput.getPosition();
        if (position < 0 || position > 262144) {
            return false;
        }
        extractorInput.skipFully((int) position);
        return true;
    }

    /* access modifiers changed from: protected */
    public final int seekToPosition(ExtractorInput extractorInput, long j, PositionHolder positionHolder) {
        if (j == extractorInput.getPosition()) {
            return 0;
        }
        positionHolder.position = j;
        return 1;
    }

    /* access modifiers changed from: protected */
    public static class SeekOperationParams {
        private final long approxBytesPerFrame;
        private long ceilingBytePosition;
        private long ceilingTimePosition;
        private long floorBytePosition;
        private long floorTimePosition;
        private long nextSearchBytePosition;
        private final long seekTimeUs;
        private final long targetTimePosition;

        protected static long calculateNextSearchBytePosition(long j, long j2, long j3, long j4, long j5, long j6) {
            if (j4 + 1 >= j5 || j2 + 1 >= j3) {
                return j4;
            }
            long j7 = (long) (((float) (j - j2)) * (((float) (j5 - j4)) / ((float) (j3 - j2))));
            return Util.constrainValue(((j7 + j4) - j6) - (j7 / 20), j4, j5 - 1);
        }

        protected SeekOperationParams(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
            this.seekTimeUs = j;
            this.targetTimePosition = j2;
            this.floorTimePosition = j3;
            this.ceilingTimePosition = j4;
            this.floorBytePosition = j5;
            this.ceilingBytePosition = j6;
            this.approxBytesPerFrame = j7;
            this.nextSearchBytePosition = calculateNextSearchBytePosition(j2, j3, j4, j5, j6, j7);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private long getFloorBytePosition() {
            return this.floorBytePosition;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private long getCeilingBytePosition() {
            return this.ceilingBytePosition;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private long getTargetTimePosition() {
            return this.targetTimePosition;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private long getSeekTimeUs() {
            return this.seekTimeUs;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void updateSeekFloor(long j, long j2) {
            this.floorTimePosition = j;
            this.floorBytePosition = j2;
            updateNextSearchBytePosition();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void updateSeekCeiling(long j, long j2) {
            this.ceilingTimePosition = j;
            this.ceilingBytePosition = j2;
            updateNextSearchBytePosition();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private long getNextSearchBytePosition() {
            return this.nextSearchBytePosition;
        }

        private void updateNextSearchBytePosition() {
            this.nextSearchBytePosition = calculateNextSearchBytePosition(this.targetTimePosition, this.floorTimePosition, this.ceilingTimePosition, this.floorBytePosition, this.ceilingBytePosition, this.approxBytesPerFrame);
        }
    }

    public static final class TimestampSearchResult {
        public static final TimestampSearchResult NO_TIMESTAMP_IN_RANGE_RESULT = new TimestampSearchResult(-3, -9223372036854775807L, -1);
        private final long bytePositionToUpdate;
        private final long timestampToUpdate;
        private final int type;

        private TimestampSearchResult(int i, long j, long j2) {
            this.type = i;
            this.timestampToUpdate = j;
            this.bytePositionToUpdate = j2;
        }

        public static TimestampSearchResult overestimatedResult(long j, long j2) {
            return new TimestampSearchResult(-1, j, j2);
        }

        public static TimestampSearchResult underestimatedResult(long j, long j2) {
            return new TimestampSearchResult(-2, j, j2);
        }

        public static TimestampSearchResult targetFoundResult(long j) {
            return new TimestampSearchResult(0, -9223372036854775807L, j);
        }
    }

    public static class BinarySearchSeekMap implements SeekMap {
        private final long approxBytesPerFrame;
        private final long ceilingBytePosition;
        private final long ceilingTimePosition;
        private final long durationUs;
        private final long floorBytePosition;
        private final long floorTimePosition;
        private final SeekTimestampConverter seekTimestampConverter;

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }

        public BinarySearchSeekMap(SeekTimestampConverter seekTimestampConverter2, long j, long j2, long j3, long j4, long j5, long j6) {
            this.seekTimestampConverter = seekTimestampConverter2;
            this.durationUs = j;
            this.floorTimePosition = j2;
            this.ceilingTimePosition = j3;
            this.floorBytePosition = j4;
            this.ceilingBytePosition = j5;
            this.approxBytesPerFrame = j6;
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j) {
            return new SeekMap.SeekPoints(new SeekPoint(j, SeekOperationParams.calculateNextSearchBytePosition(this.seekTimestampConverter.timeUsToTargetTime(j), this.floorTimePosition, this.ceilingTimePosition, this.floorBytePosition, this.ceilingBytePosition, this.approxBytesPerFrame)));
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        public long timeUsToTargetTime(long j) {
            return this.seekTimestampConverter.timeUsToTargetTime(j);
        }
    }
}
