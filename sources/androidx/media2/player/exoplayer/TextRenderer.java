package androidx.media2.player.exoplayer;

import android.os.Handler;
import android.os.Looper;
import androidx.core.util.Preconditions;
import androidx.media2.exoplayer.external.BaseRenderer;
import androidx.media2.exoplayer.external.ExoPlaybackException;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.FormatHolder;
import androidx.media2.exoplayer.external.text.SubtitleInputBuffer;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

/* access modifiers changed from: package-private */
public class TextRenderer extends BaseRenderer {
    private final ParsableByteArray mCcData = new ParsableByteArray();
    private final SortedMap<Long, byte[]> mCcMap = new TreeMap();
    private final DataBuilder mDtvDataBuilder = new DataBuilder();
    private final FormatHolder mFormatHolder = new FormatHolder();
    private final Handler mHandler = new Handler(Looper.myLooper());
    private boolean mHasPendingInputBuffer;
    private final SubtitleInputBuffer mInputBuffer = new SubtitleInputBuffer();
    private boolean mInputStreamEnded;
    private boolean[] mIsTypeAndChannelAdvertised;
    private final int[] mLine21Channels = new int[2];
    private final DataBuilder mLine21DataBuilder = new DataBuilder();
    final Output mOutput;
    private final ParsableByteArray mScratch = new ParsableByteArray();
    private int mSelectedChannel = -1;
    private int mSelectedType = -1;

    public interface Output {
        void onCcData(byte[] bArr, long j);

        void onChannelAvailable(int i, int i2);
    }

    @Override // androidx.media2.exoplayer.external.Renderer
    public boolean isReady() {
        return true;
    }

    TextRenderer(Output output) {
        super(3);
        this.mOutput = output;
    }

    @Override // androidx.media2.exoplayer.external.RendererCapabilities
    public int supportsFormat(Format format) {
        String str = format.sampleMimeType;
        return ("application/cea-608".equals(str) || "application/cea-708".equals(str) || "text/vtt".equals(str)) ? 4 : 0;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.media2.exoplayer.external.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j) throws ExoPlaybackException {
        super.onStreamChanged(formatArr, j);
        this.mIsTypeAndChannelAdvertised = new boolean[128];
    }

    /* access modifiers changed from: protected */
    @Override // androidx.media2.exoplayer.external.BaseRenderer
    public synchronized void onPositionReset(long j, boolean z) {
        flush();
    }

    @Override // androidx.media2.exoplayer.external.Renderer
    public synchronized void render(long j, long j2) {
        if (getState() == 2) {
            display(j);
            if (!this.mHasPendingInputBuffer) {
                this.mInputBuffer.clear();
                int readSource = readSource(this.mFormatHolder, this.mInputBuffer, false);
                if (readSource != -3 && readSource != -5) {
                    if (this.mInputBuffer.isEndOfStream()) {
                        this.mInputStreamEnded = true;
                        return;
                    } else {
                        this.mHasPendingInputBuffer = true;
                        this.mInputBuffer.flip();
                    }
                } else {
                    return;
                }
            }
            if (this.mInputBuffer.timeUs - j <= 110000) {
                this.mHasPendingInputBuffer = false;
                this.mCcData.reset(this.mInputBuffer.data.array(), this.mInputBuffer.data.limit());
                this.mLine21DataBuilder.clear();
                while (this.mCcData.bytesLeft() >= 3) {
                    byte readUnsignedByte = (byte) this.mCcData.readUnsignedByte();
                    byte readUnsignedByte2 = (byte) this.mCcData.readUnsignedByte();
                    byte readUnsignedByte3 = (byte) this.mCcData.readUnsignedByte();
                    int i = readUnsignedByte & 3;
                    if ((readUnsignedByte & 4) != 0) {
                        if (i == 3) {
                            if (this.mDtvDataBuilder.hasData()) {
                                handleDtvPacket(this.mDtvDataBuilder, this.mInputBuffer.timeUs);
                            }
                            this.mDtvDataBuilder.append(readUnsignedByte2, readUnsignedByte3);
                        } else if (this.mDtvDataBuilder.mLength > 0 && i == 2) {
                            this.mDtvDataBuilder.append(readUnsignedByte2, readUnsignedByte3);
                        } else if (i == 0 || i == 1) {
                            byte b = (byte) (readUnsignedByte2 & Byte.MAX_VALUE);
                            byte b2 = (byte) (readUnsignedByte3 & Byte.MAX_VALUE);
                            if (b >= 16 || b2 >= 16) {
                                if (b >= 16 && b <= 31) {
                                    int i2 = (b >= 24 ? 1 : 0) + (readUnsignedByte != 0 ? 2 : 0);
                                    this.mLine21Channels[i] = i2;
                                    maybeAdvertiseChannel(0, i2);
                                }
                                if (this.mSelectedType == 0 && this.mSelectedChannel == this.mLine21Channels[i]) {
                                    this.mLine21DataBuilder.append((byte) i, b, b2);
                                }
                            }
                        }
                    } else if ((i == 3 || i == 2) && this.mDtvDataBuilder.hasData()) {
                        handleDtvPacket(this.mDtvDataBuilder, this.mInputBuffer.timeUs);
                    }
                }
                if (this.mSelectedType == 0 && this.mLine21DataBuilder.hasData()) {
                    handleLine21Packet(this.mLine21DataBuilder, this.mInputBuffer.timeUs);
                }
            }
        }
    }

    @Override // androidx.media2.exoplayer.external.Renderer
    public boolean isEnded() {
        return this.mInputStreamEnded && this.mCcMap.isEmpty();
    }

    public synchronized void clearSelection() {
        select(-1, -1);
    }

    public synchronized void select(int i, int i2) {
        this.mSelectedType = i;
        this.mSelectedChannel = i2;
        flush();
    }

    private void flush() {
        this.mCcMap.clear();
        this.mLine21DataBuilder.clear();
        this.mDtvDataBuilder.clear();
        this.mInputStreamEnded = false;
        this.mHasPendingInputBuffer = false;
    }

    private void maybeAdvertiseChannel(final int i, final int i2) {
        int i3 = (i << 6) + i2;
        boolean[] zArr = this.mIsTypeAndChannelAdvertised;
        if (!zArr[i3]) {
            zArr[i3] = true;
            this.mHandler.post(new Runnable() {
                /* class androidx.media2.player.exoplayer.TextRenderer.AnonymousClass1 */

                public void run() {
                    TextRenderer.this.mOutput.onChannelAvailable(i, i2);
                }
            });
        }
    }

    private void handleDtvPacket(DataBuilder dataBuilder, long j) {
        this.mScratch.reset(dataBuilder.mData, dataBuilder.mLength);
        dataBuilder.clear();
        int readUnsignedByte = this.mScratch.readUnsignedByte() & 31;
        if (readUnsignedByte == 0) {
            readUnsignedByte = 64;
        }
        if (this.mScratch.limit() == readUnsignedByte * 2) {
            while (this.mScratch.bytesLeft() >= 2) {
                int readUnsignedByte2 = this.mScratch.readUnsignedByte();
                int i = (readUnsignedByte2 & 224) >> 5;
                int i2 = readUnsignedByte2 & 31;
                if ((i == 7 && (i = this.mScratch.readUnsignedByte() & 63) < 7) || this.mScratch.bytesLeft() < i2) {
                    return;
                }
                if (i2 > 0) {
                    maybeAdvertiseChannel(1, i);
                    if (this.mSelectedType == 1 && this.mSelectedChannel == i) {
                        byte[] bArr = new byte[i2];
                        this.mScratch.readBytes(bArr, 0, i2);
                        this.mCcMap.put(Long.valueOf(j), bArr);
                    } else {
                        this.mScratch.skipBytes(i2);
                    }
                }
            }
        }
    }

    private void handleLine21Packet(DataBuilder dataBuilder, long j) {
        this.mCcMap.put(Long.valueOf(j), Arrays.copyOf(dataBuilder.mData, dataBuilder.mLength));
        dataBuilder.clear();
    }

    private void display(long j) {
        if (this.mSelectedType != -1 && this.mSelectedChannel != -1) {
            byte[] bArr = new byte[0];
            long j2 = -9223372036854775807L;
            while (!this.mCcMap.isEmpty()) {
                long longValue = this.mCcMap.firstKey().longValue();
                if (j < longValue) {
                    break;
                }
                byte[] bArr2 = (byte[]) Preconditions.checkNotNull(this.mCcMap.get(Long.valueOf(longValue)));
                int length = bArr.length;
                bArr = Arrays.copyOf(bArr, bArr2.length + length);
                System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
                SortedMap<Long, byte[]> sortedMap = this.mCcMap;
                sortedMap.remove(sortedMap.firstKey());
                j2 = longValue;
            }
            if (bArr.length > 0) {
                this.mOutput.onCcData(bArr, j2);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class DataBuilder {
        public byte[] mData = new byte[3];
        public int mLength;

        DataBuilder() {
        }

        public void append(byte b, byte b2) {
            int i = this.mLength + 2;
            byte[] bArr = this.mData;
            if (i > bArr.length) {
                this.mData = Arrays.copyOf(bArr, bArr.length * 2);
            }
            byte[] bArr2 = this.mData;
            int i2 = this.mLength;
            int i3 = i2 + 1;
            this.mLength = i3;
            bArr2[i2] = b;
            this.mLength = i3 + 1;
            bArr2[i3] = b2;
        }

        public void append(byte b, byte b2, byte b3) {
            int i = this.mLength + 3;
            byte[] bArr = this.mData;
            if (i > bArr.length) {
                this.mData = Arrays.copyOf(bArr, bArr.length * 2);
            }
            byte[] bArr2 = this.mData;
            int i2 = this.mLength;
            int i3 = i2 + 1;
            this.mLength = i3;
            bArr2[i2] = b;
            int i4 = i3 + 1;
            this.mLength = i4;
            bArr2[i3] = b2;
            this.mLength = i4 + 1;
            bArr2[i4] = b3;
        }

        public boolean hasData() {
            return this.mLength > 0;
        }

        public void clear() {
            this.mLength = 0;
        }
    }
}
