package androidx.media2.exoplayer.external.audio;

import androidx.media2.exoplayer.external.PlaybackParameters;
import java.nio.ByteBuffer;

public interface AudioSink {

    public interface Listener {
        void onAudioSessionId(int i);

        void onPositionDiscontinuity();

        void onUnderrun(int i, long j, long j2);
    }

    void configure(int i, int i2, int i3, int i4, int[] iArr, int i5, int i6) throws ConfigurationException;

    void disableTunneling();

    void enableTunnelingV21(int i);

    void flush();

    long getCurrentPositionUs(boolean z);

    PlaybackParameters getPlaybackParameters();

    boolean handleBuffer(ByteBuffer byteBuffer, long j) throws InitializationException, WriteException;

    void handleDiscontinuity();

    boolean hasPendingData();

    boolean isEnded();

    void pause();

    void play();

    void playToEndOfStream() throws WriteException;

    void reset();

    void setAudioAttributes(AudioAttributes audioAttributes);

    void setAuxEffectInfo(AuxEffectInfo auxEffectInfo);

    void setListener(Listener listener);

    PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters);

    void setVolume(float f);

    boolean supportsOutput(int i, int i2);

    public static final class ConfigurationException extends Exception {
        public ConfigurationException(Throwable th) {
            super(th);
        }

        public ConfigurationException(String str) {
            super(str);
        }
    }

    public static final class InitializationException extends Exception {
        public final int audioTrackState;

        /* JADX WARNING: Illegal instructions before constructor call */
        public InitializationException(int i, int i2, int i3, int i4) {
            super(r0.toString());
            StringBuilder sb = new StringBuilder(82);
            sb.append("AudioTrack init failed: ");
            sb.append(i);
            sb.append(", Config(");
            sb.append(i2);
            sb.append(", ");
            sb.append(i3);
            sb.append(", ");
            sb.append(i4);
            sb.append(")");
            this.audioTrackState = i;
        }
    }

    public static final class WriteException extends Exception {
        public final int errorCode;

        /* JADX WARNING: Illegal instructions before constructor call */
        public WriteException(int i) {
            super(r0.toString());
            StringBuilder sb = new StringBuilder(36);
            sb.append("AudioTrack write failed: ");
            sb.append(i);
            this.errorCode = i;
        }
    }
}
