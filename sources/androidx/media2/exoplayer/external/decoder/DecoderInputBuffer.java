package androidx.media2.exoplayer.external.decoder;

import java.nio.ByteBuffer;

public class DecoderInputBuffer extends Buffer {
    private final int bufferReplacementMode;
    public final CryptoInfo cryptoInfo = new CryptoInfo();
    public ByteBuffer data;
    public long timeUs;

    public static DecoderInputBuffer newFlagsOnlyInstance() {
        return new DecoderInputBuffer(0);
    }

    public DecoderInputBuffer(int i) {
        this.bufferReplacementMode = i;
    }

    public void ensureSpaceForWrite(int i) {
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer == null) {
            this.data = createReplacementByteBuffer(i);
            return;
        }
        int capacity = byteBuffer.capacity();
        int position = this.data.position();
        int i2 = i + position;
        if (capacity < i2) {
            ByteBuffer createReplacementByteBuffer = createReplacementByteBuffer(i2);
            if (position > 0) {
                this.data.position(0);
                this.data.limit(position);
                createReplacementByteBuffer.put(this.data);
            }
            this.data = createReplacementByteBuffer;
        }
    }

    public final boolean isFlagsOnly() {
        return this.data == null && this.bufferReplacementMode == 0;
    }

    public final boolean isEncrypted() {
        return getFlag(1073741824);
    }

    public final void flip() {
        this.data.flip();
    }

    @Override // androidx.media2.exoplayer.external.decoder.Buffer
    public void clear() {
        super.clear();
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
    }

    private ByteBuffer createReplacementByteBuffer(int i) {
        int i2 = this.bufferReplacementMode;
        if (i2 == 1) {
            return ByteBuffer.allocate(i);
        }
        if (i2 == 2) {
            return ByteBuffer.allocateDirect(i);
        }
        ByteBuffer byteBuffer = this.data;
        int capacity = byteBuffer == null ? 0 : byteBuffer.capacity();
        StringBuilder sb = new StringBuilder(44);
        sb.append("Buffer too small (");
        sb.append(capacity);
        sb.append(" < ");
        sb.append(i);
        sb.append(")");
        throw new IllegalStateException(sb.toString());
    }
}
