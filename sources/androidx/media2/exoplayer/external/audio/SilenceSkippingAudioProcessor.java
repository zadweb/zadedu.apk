package androidx.media2.exoplayer.external.audio;

import androidx.media2.exoplayer.external.audio.AudioProcessor;
import androidx.media2.exoplayer.external.util.Util;
import java.nio.ByteBuffer;

public final class SilenceSkippingAudioProcessor extends BaseAudioProcessor {
    private int bytesPerFrame;
    private boolean enabled;
    private boolean hasOutputNoise;
    private byte[] maybeSilenceBuffer = Util.EMPTY_BYTE_ARRAY;
    private int maybeSilenceBufferSize;
    private byte[] paddingBuffer = Util.EMPTY_BYTE_ARRAY;
    private int paddingSize;
    private long skippedFrames;
    private int state;

    public void setEnabled(boolean z) {
        this.enabled = z;
        flush();
    }

    public long getSkippedFrames() {
        return this.skippedFrames;
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioProcessor
    public boolean configure(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        if (i3 == 2) {
            this.bytesPerFrame = i2 * 2;
            return setInputFormat(i, i2, i3);
        }
        throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
    }

    @Override // androidx.media2.exoplayer.external.audio.BaseAudioProcessor, androidx.media2.exoplayer.external.audio.AudioProcessor
    public boolean isActive() {
        return super.isActive() && this.enabled;
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining() && !hasPendingOutput()) {
            int i = this.state;
            if (i == 0) {
                processNoisy(byteBuffer);
            } else if (i == 1) {
                processMaybeSilence(byteBuffer);
            } else if (i == 2) {
                processSilence(byteBuffer);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.media2.exoplayer.external.audio.BaseAudioProcessor
    public void onQueueEndOfStream() {
        int i = this.maybeSilenceBufferSize;
        if (i > 0) {
            output(this.maybeSilenceBuffer, i);
        }
        if (!this.hasOutputNoise) {
            this.skippedFrames += (long) (this.paddingSize / this.bytesPerFrame);
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.media2.exoplayer.external.audio.BaseAudioProcessor
    public void onFlush() {
        if (isActive()) {
            int durationUsToFrames = durationUsToFrames(150000) * this.bytesPerFrame;
            if (this.maybeSilenceBuffer.length != durationUsToFrames) {
                this.maybeSilenceBuffer = new byte[durationUsToFrames];
            }
            int durationUsToFrames2 = durationUsToFrames(20000) * this.bytesPerFrame;
            this.paddingSize = durationUsToFrames2;
            if (this.paddingBuffer.length != durationUsToFrames2) {
                this.paddingBuffer = new byte[durationUsToFrames2];
            }
        }
        this.state = 0;
        this.skippedFrames = 0;
        this.maybeSilenceBufferSize = 0;
        this.hasOutputNoise = false;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.media2.exoplayer.external.audio.BaseAudioProcessor
    public void onReset() {
        this.enabled = false;
        this.paddingSize = 0;
        this.maybeSilenceBuffer = Util.EMPTY_BYTE_ARRAY;
        this.paddingBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    private void processNoisy(ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        byteBuffer.limit(Math.min(limit, byteBuffer.position() + this.maybeSilenceBuffer.length));
        int findNoiseLimit = findNoiseLimit(byteBuffer);
        if (findNoiseLimit == byteBuffer.position()) {
            this.state = 1;
        } else {
            byteBuffer.limit(findNoiseLimit);
            output(byteBuffer);
        }
        byteBuffer.limit(limit);
    }

    private void processMaybeSilence(ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int findNoisePosition = findNoisePosition(byteBuffer);
        int position = findNoisePosition - byteBuffer.position();
        byte[] bArr = this.maybeSilenceBuffer;
        int length = bArr.length;
        int i = this.maybeSilenceBufferSize;
        int i2 = length - i;
        if (findNoisePosition >= limit || position >= i2) {
            int min = Math.min(position, i2);
            byteBuffer.limit(byteBuffer.position() + min);
            byteBuffer.get(this.maybeSilenceBuffer, this.maybeSilenceBufferSize, min);
            int i3 = this.maybeSilenceBufferSize + min;
            this.maybeSilenceBufferSize = i3;
            byte[] bArr2 = this.maybeSilenceBuffer;
            if (i3 == bArr2.length) {
                if (this.hasOutputNoise) {
                    output(bArr2, this.paddingSize);
                    this.skippedFrames += (long) ((this.maybeSilenceBufferSize - (this.paddingSize * 2)) / this.bytesPerFrame);
                } else {
                    this.skippedFrames += (long) ((i3 - this.paddingSize) / this.bytesPerFrame);
                }
                updatePaddingBuffer(byteBuffer, this.maybeSilenceBuffer, this.maybeSilenceBufferSize);
                this.maybeSilenceBufferSize = 0;
                this.state = 2;
            }
            byteBuffer.limit(limit);
            return;
        }
        output(bArr, i);
        this.maybeSilenceBufferSize = 0;
        this.state = 0;
    }

    private void processSilence(ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int findNoisePosition = findNoisePosition(byteBuffer);
        byteBuffer.limit(findNoisePosition);
        this.skippedFrames += (long) (byteBuffer.remaining() / this.bytesPerFrame);
        updatePaddingBuffer(byteBuffer, this.paddingBuffer, this.paddingSize);
        if (findNoisePosition < limit) {
            output(this.paddingBuffer, this.paddingSize);
            this.state = 0;
            byteBuffer.limit(limit);
        }
    }

    private void output(byte[] bArr, int i) {
        replaceOutputBuffer(i).put(bArr, 0, i).flip();
        if (i > 0) {
            this.hasOutputNoise = true;
        }
    }

    private void output(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        replaceOutputBuffer(remaining).put(byteBuffer).flip();
        if (remaining > 0) {
            this.hasOutputNoise = true;
        }
    }

    private void updatePaddingBuffer(ByteBuffer byteBuffer, byte[] bArr, int i) {
        int min = Math.min(byteBuffer.remaining(), this.paddingSize);
        int i2 = this.paddingSize - min;
        System.arraycopy(bArr, i - i2, this.paddingBuffer, 0, i2);
        byteBuffer.position(byteBuffer.limit() - min);
        byteBuffer.get(this.paddingBuffer, i2, min);
    }

    private int durationUsToFrames(long j) {
        return (int) ((j * ((long) this.sampleRateHz)) / 1000000);
    }

    private int findNoisePosition(ByteBuffer byteBuffer) {
        for (int position = byteBuffer.position() + 1; position < byteBuffer.limit(); position += 2) {
            if (Math.abs((int) byteBuffer.get(position)) > 4) {
                int i = this.bytesPerFrame;
                return i * (position / i);
            }
        }
        return byteBuffer.limit();
    }

    private int findNoiseLimit(ByteBuffer byteBuffer) {
        for (int limit = byteBuffer.limit() - 1; limit >= byteBuffer.position(); limit -= 2) {
            if (Math.abs((int) byteBuffer.get(limit)) > 4) {
                int i = this.bytesPerFrame;
                return ((limit / i) * i) + i;
            }
        }
        return byteBuffer.position();
    }
}
