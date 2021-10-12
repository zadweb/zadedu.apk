package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;

final class TrimmingAudioProcessor extends BaseAudioProcessor {
    private byte[] endBuffer = Util.EMPTY_BYTE_ARRAY;
    private int endBufferSize;
    private int pendingTrimStartBytes;
    private boolean reconfigurationPending;
    private int trimEndFrames;
    private int trimStartFrames;
    private long trimmedFrameCount;

    public void setTrimFrameCount(int i, int i2) {
        this.trimStartFrames = i;
        this.trimEndFrames = i2;
    }

    public void resetTrimmedFrameCount() {
        this.trimmedFrameCount = 0;
    }

    public long getTrimmedFrameCount() {
        return this.trimmedFrameCount;
    }

    @Override // com.google.android.exoplayer2.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (audioFormat.encoding == 2) {
            this.reconfigurationPending = true;
            return (this.trimStartFrames == 0 && this.trimEndFrames == 0) ? AudioProcessor.AudioFormat.NOT_SET : audioFormat;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i = limit - position;
        if (i != 0) {
            int min = Math.min(i, this.pendingTrimStartBytes);
            this.trimmedFrameCount += (long) (min / this.inputAudioFormat.bytesPerFrame);
            this.pendingTrimStartBytes -= min;
            byteBuffer.position(position + min);
            if (this.pendingTrimStartBytes <= 0) {
                int i2 = i - min;
                int length = (this.endBufferSize + i2) - this.endBuffer.length;
                ByteBuffer replaceOutputBuffer = replaceOutputBuffer(length);
                int constrainValue = Util.constrainValue(length, 0, this.endBufferSize);
                replaceOutputBuffer.put(this.endBuffer, 0, constrainValue);
                int constrainValue2 = Util.constrainValue(length - constrainValue, 0, i2);
                byteBuffer.limit(byteBuffer.position() + constrainValue2);
                replaceOutputBuffer.put(byteBuffer);
                byteBuffer.limit(limit);
                int i3 = i2 - constrainValue2;
                int i4 = this.endBufferSize - constrainValue;
                this.endBufferSize = i4;
                byte[] bArr = this.endBuffer;
                System.arraycopy(bArr, constrainValue, bArr, 0, i4);
                byteBuffer.get(this.endBuffer, this.endBufferSize, i3);
                this.endBufferSize += i3;
                replaceOutputBuffer.flip();
            }
        }
    }

    @Override // com.google.android.exoplayer2.audio.BaseAudioProcessor, com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer getOutput() {
        int i;
        if (super.isEnded() && (i = this.endBufferSize) > 0) {
            replaceOutputBuffer(i).put(this.endBuffer, 0, this.endBufferSize).flip();
            this.endBufferSize = 0;
        }
        return super.getOutput();
    }

    @Override // com.google.android.exoplayer2.audio.BaseAudioProcessor, com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isEnded() {
        return super.isEnded() && this.endBufferSize == 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.audio.BaseAudioProcessor
    public void onQueueEndOfStream() {
        if (this.reconfigurationPending) {
            int i = this.endBufferSize;
            if (i > 0) {
                this.trimmedFrameCount += (long) (i / this.inputAudioFormat.bytesPerFrame);
            }
            this.endBufferSize = 0;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.audio.BaseAudioProcessor
    public void onFlush() {
        if (this.reconfigurationPending) {
            this.reconfigurationPending = false;
            this.endBuffer = new byte[(this.trimEndFrames * this.inputAudioFormat.bytesPerFrame)];
            this.pendingTrimStartBytes = this.trimStartFrames * this.inputAudioFormat.bytesPerFrame;
        } else {
            this.pendingTrimStartBytes = 0;
        }
        this.endBufferSize = 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.audio.BaseAudioProcessor
    public void onReset() {
        this.endBuffer = Util.EMPTY_BYTE_ARRAY;
    }
}