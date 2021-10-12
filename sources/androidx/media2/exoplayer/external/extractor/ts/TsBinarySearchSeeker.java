package androidx.media2.exoplayer.external.extractor.ts;

import androidx.media2.exoplayer.external.extractor.BinarySearchSeeker;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.TimestampAdjuster;
import androidx.media2.exoplayer.external.util.Util;
import java.io.IOException;

/* access modifiers changed from: package-private */
public final class TsBinarySearchSeeker extends BinarySearchSeeker {
    public TsBinarySearchSeeker(TimestampAdjuster timestampAdjuster, long j, long j2, int i) {
        super(new BinarySearchSeeker.DefaultSeekTimestampConverter(), new TsPcrSeeker(i, timestampAdjuster), j, 0, j + 1, 0, j2, 188, 940);
    }

    private static final class TsPcrSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final ParsableByteArray packetBuffer = new ParsableByteArray();
        private final int pcrPid;
        private final TimestampAdjuster pcrTimestampAdjuster;

        public TsPcrSeeker(int i, TimestampAdjuster timestampAdjuster) {
            this.pcrPid = i;
            this.pcrTimestampAdjuster = timestampAdjuster;
        }

        @Override // androidx.media2.exoplayer.external.extractor.BinarySearchSeeker.TimestampSeeker
        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j, BinarySearchSeeker.OutputFrameHolder outputFrameHolder) throws IOException, InterruptedException {
            long position = extractorInput.getPosition();
            int min = (int) Math.min(112800L, extractorInput.getLength() - position);
            this.packetBuffer.reset(min);
            extractorInput.peekFully(this.packetBuffer.data, 0, min);
            return searchForPcrValueInBuffer(this.packetBuffer, j, position);
        }

        private BinarySearchSeeker.TimestampSearchResult searchForPcrValueInBuffer(ParsableByteArray parsableByteArray, long j, long j2) {
            int findSyncBytePosition;
            int findSyncBytePosition2;
            int limit = parsableByteArray.limit();
            long j3 = -1;
            long j4 = -1;
            long j5 = -9223372036854775807L;
            while (parsableByteArray.bytesLeft() >= 188 && (findSyncBytePosition2 = (findSyncBytePosition = TsUtil.findSyncBytePosition(parsableByteArray.data, parsableByteArray.getPosition(), limit)) + 188) <= limit) {
                long readPcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray, findSyncBytePosition, this.pcrPid);
                if (readPcrFromPacket != -9223372036854775807L) {
                    long adjustTsTimestamp = this.pcrTimestampAdjuster.adjustTsTimestamp(readPcrFromPacket);
                    if (adjustTsTimestamp > j) {
                        if (j5 == -9223372036854775807L) {
                            return BinarySearchSeeker.TimestampSearchResult.overestimatedResult(adjustTsTimestamp, j2);
                        }
                        return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(j2 + j4);
                    } else if (100000 + adjustTsTimestamp > j) {
                        return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(j2 + ((long) findSyncBytePosition));
                    } else {
                        j4 = (long) findSyncBytePosition;
                        j5 = adjustTsTimestamp;
                    }
                }
                parsableByteArray.setPosition(findSyncBytePosition2);
                j3 = (long) findSyncBytePosition2;
            }
            if (j5 != -9223372036854775807L) {
                return BinarySearchSeeker.TimestampSearchResult.underestimatedResult(j5, j2 + j3);
            }
            return BinarySearchSeeker.TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
        }

        @Override // androidx.media2.exoplayer.external.extractor.BinarySearchSeeker.TimestampSeeker
        public void onSeekFinished() {
            this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        }
    }
}
