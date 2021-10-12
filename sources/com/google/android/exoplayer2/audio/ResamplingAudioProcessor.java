package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import java.nio.ByteBuffer;

final class ResamplingAudioProcessor extends BaseAudioProcessor {
    ResamplingAudioProcessor() {
    }

    @Override // com.google.android.exoplayer2.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        int i = audioFormat.encoding;
        if (i == 3 || i == 2 || i == Integer.MIN_VALUE || i == 1073741824) {
            return i != 2 ? new AudioProcessor.AudioFormat(audioFormat.sampleRate, audioFormat.channelCount, 2) : AudioProcessor.AudioFormat.NOT_SET;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0065 A[LOOP:2: B:19:0x0065->B:20:0x0067, LOOP_START, PHI: r0 
      PHI: (r0v2 int) = (r0v0 int), (r0v3 int) binds: [B:10:0x002d, B:20:0x0067] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int i;
        int i2;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i3 = limit - position;
        int i4 = this.inputAudioFormat.encoding;
        if (i4 == Integer.MIN_VALUE) {
            i3 /= 3;
        } else if (i4 != 3) {
            if (i4 == 1073741824) {
                i = i3 / 2;
                ByteBuffer replaceOutputBuffer = replaceOutputBuffer(i);
                i2 = this.inputAudioFormat.encoding;
                if (i2 != Integer.MIN_VALUE) {
                    while (position < limit) {
                        replaceOutputBuffer.put(byteBuffer.get(position + 1));
                        replaceOutputBuffer.put(byteBuffer.get(position + 2));
                        position += 3;
                    }
                } else if (i2 == 3) {
                    while (position < limit) {
                        replaceOutputBuffer.put((byte) 0);
                        replaceOutputBuffer.put((byte) ((byteBuffer.get(position) & 255) - 128));
                        position++;
                    }
                } else if (i2 == 1073741824) {
                    while (position < limit) {
                        replaceOutputBuffer.put(byteBuffer.get(position + 2));
                        replaceOutputBuffer.put(byteBuffer.get(position + 3));
                        position += 4;
                    }
                } else {
                    throw new IllegalStateException();
                }
                byteBuffer.position(byteBuffer.limit());
                replaceOutputBuffer.flip();
            }
            throw new IllegalStateException();
        }
        i = i3 * 2;
        ByteBuffer replaceOutputBuffer2 = replaceOutputBuffer(i);
        i2 = this.inputAudioFormat.encoding;
        if (i2 != Integer.MIN_VALUE) {
        }
        byteBuffer.position(byteBuffer.limit());
        replaceOutputBuffer2.flip();
    }
}
