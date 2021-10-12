package androidx.media2.exoplayer.external.audio;

import androidx.media2.exoplayer.external.audio.AudioProcessor;
import java.nio.ByteBuffer;

final class ResamplingAudioProcessor extends BaseAudioProcessor {
    @Override // androidx.media2.exoplayer.external.audio.BaseAudioProcessor, androidx.media2.exoplayer.external.audio.AudioProcessor
    public int getOutputEncoding() {
        return 2;
    }

    ResamplingAudioProcessor() {
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioProcessor
    public boolean configure(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        if (i3 == 3 || i3 == 2 || i3 == Integer.MIN_VALUE || i3 == 1073741824) {
            return setInputFormat(i, i2, i3);
        }
        throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
    }

    @Override // androidx.media2.exoplayer.external.audio.BaseAudioProcessor, androidx.media2.exoplayer.external.audio.AudioProcessor
    public boolean isActive() {
        return (this.encoding == 0 || this.encoding == 2) ? false : true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061 A[LOOP:2: B:19:0x0061->B:20:0x0063, LOOP_START, PHI: r0 
      PHI: (r0v2 int) = (r0v0 int), (r0v3 int) binds: [B:10:0x0029, B:20:0x0063] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.media2.exoplayer.external.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int i;
        int i2;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i3 = limit - position;
        int i4 = this.encoding;
        if (i4 == Integer.MIN_VALUE) {
            i3 /= 3;
        } else if (i4 != 3) {
            if (i4 == 1073741824) {
                i = i3 / 2;
                ByteBuffer replaceOutputBuffer = replaceOutputBuffer(i);
                i2 = this.encoding;
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
        i2 = this.encoding;
        if (i2 != Integer.MIN_VALUE) {
        }
        byteBuffer.position(byteBuffer.limit());
        replaceOutputBuffer2.flip();
    }
}
