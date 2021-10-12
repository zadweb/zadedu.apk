package androidx.media2.exoplayer.external.audio;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import androidx.media2.exoplayer.external.PlaybackParameters;
import androidx.media2.exoplayer.external.audio.AudioProcessor;
import androidx.media2.exoplayer.external.audio.AudioSink;
import androidx.media2.exoplayer.external.audio.AudioTrackPositionTracker;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class DefaultAudioSink implements AudioSink {
    public static boolean enablePreV21AudioSessionWorkaround = false;
    public static boolean failOnSpuriousAudioTimestamp = false;
    private AudioProcessor[] activeAudioProcessors;
    private PlaybackParameters afterDrainPlaybackParameters;
    private AudioAttributes audioAttributes;
    private final AudioCapabilities audioCapabilities;
    private final AudioProcessorChain audioProcessorChain;
    private int audioSessionId;
    private AudioTrack audioTrack;
    private final AudioTrackPositionTracker audioTrackPositionTracker;
    private AuxEffectInfo auxEffectInfo;
    private ByteBuffer avSyncHeader;
    private int bytesUntilNextAvSync;
    private final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    private Configuration configuration;
    private int drainingAudioProcessorIndex;
    private final boolean enableConvertHighResIntPcmToFloat;
    private int framesPerEncodedSample;
    private boolean handledEndOfStream;
    private ByteBuffer inputBuffer;
    private AudioTrack keepSessionIdAudioTrack;
    private long lastFeedElapsedRealtimeMs;
    private AudioSink.Listener listener;
    private ByteBuffer outputBuffer;
    private ByteBuffer[] outputBuffers;
    private Configuration pendingConfiguration;
    private PlaybackParameters playbackParameters;
    private final ArrayDeque<PlaybackParametersCheckpoint> playbackParametersCheckpoints;
    private long playbackParametersOffsetUs;
    private long playbackParametersPositionUs;
    private boolean playing;
    private byte[] preV21OutputBuffer;
    private int preV21OutputBufferOffset;
    private final ConditionVariable releasingConditionVariable;
    private int startMediaTimeState;
    private long startMediaTimeUs;
    private boolean stoppedAudioTrack;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private final AudioProcessor[] toFloatPcmAvailableAudioProcessors;
    private final AudioProcessor[] toIntPcmAvailableAudioProcessors;
    private final TrimmingAudioProcessor trimmingAudioProcessor;
    private boolean tunneling;
    private float volume;
    private long writtenEncodedFrames;
    private long writtenPcmBytes;

    public interface AudioProcessorChain {
        PlaybackParameters applyPlaybackParameters(PlaybackParameters playbackParameters);

        AudioProcessor[] getAudioProcessors();

        long getMediaDuration(long j);

        long getSkippedOutputFrameCount();
    }

    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        private InvalidAudioTrackTimestampException(String str) {
            super(str);
        }
    }

    public static class DefaultAudioProcessorChain implements AudioProcessorChain {
        private final AudioProcessor[] audioProcessors;
        private final SilenceSkippingAudioProcessor silenceSkippingAudioProcessor = new SilenceSkippingAudioProcessor();
        private final SonicAudioProcessor sonicAudioProcessor;

        public DefaultAudioProcessorChain(AudioProcessor... audioProcessorArr) {
            this.audioProcessors = (AudioProcessor[]) Arrays.copyOf(audioProcessorArr, audioProcessorArr.length + 2);
            SonicAudioProcessor sonicAudioProcessor2 = new SonicAudioProcessor();
            this.sonicAudioProcessor = sonicAudioProcessor2;
            AudioProcessor[] audioProcessorArr2 = this.audioProcessors;
            audioProcessorArr2[audioProcessorArr.length] = this.silenceSkippingAudioProcessor;
            audioProcessorArr2[audioProcessorArr.length + 1] = sonicAudioProcessor2;
        }

        @Override // androidx.media2.exoplayer.external.audio.DefaultAudioSink.AudioProcessorChain
        public AudioProcessor[] getAudioProcessors() {
            return this.audioProcessors;
        }

        @Override // androidx.media2.exoplayer.external.audio.DefaultAudioSink.AudioProcessorChain
        public PlaybackParameters applyPlaybackParameters(PlaybackParameters playbackParameters) {
            this.silenceSkippingAudioProcessor.setEnabled(playbackParameters.skipSilence);
            return new PlaybackParameters(this.sonicAudioProcessor.setSpeed(playbackParameters.speed), this.sonicAudioProcessor.setPitch(playbackParameters.pitch), playbackParameters.skipSilence);
        }

        @Override // androidx.media2.exoplayer.external.audio.DefaultAudioSink.AudioProcessorChain
        public long getMediaDuration(long j) {
            return this.sonicAudioProcessor.scaleDurationForSpeedup(j);
        }

        @Override // androidx.media2.exoplayer.external.audio.DefaultAudioSink.AudioProcessorChain
        public long getSkippedOutputFrameCount() {
            return this.silenceSkippingAudioProcessor.getSkippedFrames();
        }
    }

    public DefaultAudioSink(AudioCapabilities audioCapabilities2, AudioProcessor[] audioProcessorArr) {
        this(audioCapabilities2, audioProcessorArr, false);
    }

    public DefaultAudioSink(AudioCapabilities audioCapabilities2, AudioProcessor[] audioProcessorArr, boolean z) {
        this(audioCapabilities2, new DefaultAudioProcessorChain(audioProcessorArr), z);
    }

    public DefaultAudioSink(AudioCapabilities audioCapabilities2, AudioProcessorChain audioProcessorChain2, boolean z) {
        this.audioCapabilities = audioCapabilities2;
        this.audioProcessorChain = (AudioProcessorChain) Assertions.checkNotNull(audioProcessorChain2);
        this.enableConvertHighResIntPcmToFloat = z;
        this.releasingConditionVariable = new ConditionVariable(true);
        this.audioTrackPositionTracker = new AudioTrackPositionTracker(new PositionTrackerListener());
        this.channelMappingAudioProcessor = new ChannelMappingAudioProcessor();
        this.trimmingAudioProcessor = new TrimmingAudioProcessor();
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, new ResamplingAudioProcessor(), this.channelMappingAudioProcessor, this.trimmingAudioProcessor);
        Collections.addAll(arrayList, audioProcessorChain2.getAudioProcessors());
        this.toIntPcmAvailableAudioProcessors = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[0]);
        this.toFloatPcmAvailableAudioProcessors = new AudioProcessor[]{new FloatResamplingAudioProcessor()};
        this.volume = 1.0f;
        this.startMediaTimeState = 0;
        this.audioAttributes = AudioAttributes.DEFAULT;
        this.audioSessionId = 0;
        this.auxEffectInfo = new AuxEffectInfo(0, 0.0f);
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.drainingAudioProcessorIndex = -1;
        this.activeAudioProcessors = new AudioProcessor[0];
        this.outputBuffers = new ByteBuffer[0];
        this.playbackParametersCheckpoints = new ArrayDeque<>();
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void setListener(AudioSink.Listener listener2) {
        this.listener = listener2;
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public boolean supportsOutput(int i, int i2) {
        if (!Util.isEncodingLinearPcm(i2)) {
            AudioCapabilities audioCapabilities2 = this.audioCapabilities;
            if (audioCapabilities2 == null || !audioCapabilities2.supportsEncoding(i2) || (i != -1 && i > this.audioCapabilities.getMaxChannelCount())) {
                return false;
            }
            return true;
        } else if (i2 != 4 || Util.SDK_INT >= 21) {
            return true;
        } else {
            return false;
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public long getCurrentPositionUs(boolean z) {
        if (!isInitialized() || this.startMediaTimeState == 0) {
            return Long.MIN_VALUE;
        }
        return this.startMediaTimeUs + applySkipping(applySpeedup(Math.min(this.audioTrackPositionTracker.getCurrentPositionUs(z), this.configuration.framesToDurationUs(getWrittenFrames()))));
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void configure(int i, int i2, int i3, int i4, int[] iArr, int i5, int i6) throws AudioSink.ConfigurationException {
        int[] iArr2;
        AudioProcessor[] audioProcessorArr;
        int i7;
        int i8;
        int i9;
        boolean z;
        boolean z2 = false;
        if (Util.SDK_INT < 21 && i2 == 8 && iArr == null) {
            iArr2 = new int[6];
            for (int i10 = 0; i10 < 6; i10++) {
                iArr2[i10] = i10;
            }
        } else {
            iArr2 = iArr;
        }
        boolean isEncodingLinearPcm = Util.isEncodingLinearPcm(i);
        boolean z3 = isEncodingLinearPcm && i != 4;
        boolean z4 = this.enableConvertHighResIntPcmToFloat && supportsOutput(i2, 4) && Util.isEncodingHighResolutionIntegerPcm(i);
        if (z4) {
            audioProcessorArr = this.toFloatPcmAvailableAudioProcessors;
        } else {
            audioProcessorArr = this.toIntPcmAvailableAudioProcessors;
        }
        if (z3) {
            this.trimmingAudioProcessor.setTrimFrameCount(i5, i6);
            this.channelMappingAudioProcessor.setChannelMap(iArr2);
            i8 = i3;
            i9 = i2;
            int i11 = i;
            boolean z5 = false;
            for (AudioProcessor audioProcessor : audioProcessorArr) {
                try {
                    z5 |= audioProcessor.configure(i8, i9, i11);
                    if (audioProcessor.isActive()) {
                        i9 = audioProcessor.getOutputChannelCount();
                        i8 = audioProcessor.getOutputSampleRateHz();
                        i11 = audioProcessor.getOutputEncoding();
                    }
                } catch (AudioProcessor.UnhandledFormatException e) {
                    throw new AudioSink.ConfigurationException(e);
                }
            }
            z = z5;
            i7 = i11;
        } else {
            i8 = i3;
            i9 = i2;
            i7 = i;
            z = false;
        }
        int channelConfig = getChannelConfig(i9, isEncodingLinearPcm);
        if (channelConfig != 0) {
            Configuration configuration2 = new Configuration(isEncodingLinearPcm, isEncodingLinearPcm ? Util.getPcmFrameSize(i, i2) : -1, i3, isEncodingLinearPcm ? Util.getPcmFrameSize(i7, i9) : -1, i8, channelConfig, i7, i4, z3, z3 && !z4, audioProcessorArr);
            if (z || this.pendingConfiguration != null) {
                z2 = true;
            }
            if (!isInitialized() || (configuration2.canReuseAudioTrack(this.configuration) && !z2)) {
                this.configuration = configuration2;
            } else {
                this.pendingConfiguration = configuration2;
            }
        } else {
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unsupported channel count: ");
            sb.append(i9);
            throw new AudioSink.ConfigurationException(sb.toString());
        }
    }

    private void setupAudioProcessors() {
        AudioProcessor[] audioProcessorArr = this.configuration.availableAudioProcessors;
        ArrayList arrayList = new ArrayList();
        for (AudioProcessor audioProcessor : audioProcessorArr) {
            if (audioProcessor.isActive()) {
                arrayList.add(audioProcessor);
            } else {
                audioProcessor.flush();
            }
        }
        int size = arrayList.size();
        this.activeAudioProcessors = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[size]);
        this.outputBuffers = new ByteBuffer[size];
        flushAudioProcessors();
    }

    private void flushAudioProcessors() {
        int i = 0;
        while (true) {
            AudioProcessor[] audioProcessorArr = this.activeAudioProcessors;
            if (i < audioProcessorArr.length) {
                AudioProcessor audioProcessor = audioProcessorArr[i];
                audioProcessor.flush();
                this.outputBuffers[i] = audioProcessor.getOutput();
                i++;
            } else {
                return;
            }
        }
    }

    private void initialize() throws AudioSink.InitializationException {
        PlaybackParameters playbackParameters2;
        this.releasingConditionVariable.block();
        AudioTrack buildAudioTrack = ((Configuration) Assertions.checkNotNull(this.configuration)).buildAudioTrack(this.tunneling, this.audioAttributes, this.audioSessionId);
        this.audioTrack = buildAudioTrack;
        int audioSessionId2 = buildAudioTrack.getAudioSessionId();
        if (enablePreV21AudioSessionWorkaround && Util.SDK_INT < 21) {
            AudioTrack audioTrack2 = this.keepSessionIdAudioTrack;
            if (!(audioTrack2 == null || audioSessionId2 == audioTrack2.getAudioSessionId())) {
                releaseKeepSessionIdAudioTrack();
            }
            if (this.keepSessionIdAudioTrack == null) {
                this.keepSessionIdAudioTrack = initializeKeepSessionIdAudioTrack(audioSessionId2);
            }
        }
        if (this.audioSessionId != audioSessionId2) {
            this.audioSessionId = audioSessionId2;
            AudioSink.Listener listener2 = this.listener;
            if (listener2 != null) {
                listener2.onAudioSessionId(audioSessionId2);
            }
        }
        if (this.configuration.canApplyPlaybackParameters) {
            playbackParameters2 = this.audioProcessorChain.applyPlaybackParameters(this.playbackParameters);
        } else {
            playbackParameters2 = PlaybackParameters.DEFAULT;
        }
        this.playbackParameters = playbackParameters2;
        setupAudioProcessors();
        this.audioTrackPositionTracker.setAudioTrack(this.audioTrack, this.configuration.outputEncoding, this.configuration.outputPcmFrameSize, this.configuration.bufferSize);
        setVolumeInternal();
        if (this.auxEffectInfo.effectId != 0) {
            this.audioTrack.attachAuxEffect(this.auxEffectInfo.effectId);
            this.audioTrack.setAuxEffectSendLevel(this.auxEffectInfo.sendLevel);
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void play() {
        this.playing = true;
        if (isInitialized()) {
            this.audioTrackPositionTracker.start();
            this.audioTrack.play();
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void handleDiscontinuity() {
        if (this.startMediaTimeState == 1) {
            this.startMediaTimeState = 2;
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public boolean handleBuffer(ByteBuffer byteBuffer, long j) throws AudioSink.InitializationException, AudioSink.WriteException {
        String str;
        String str2;
        PlaybackParameters playbackParameters2;
        ByteBuffer byteBuffer2 = this.inputBuffer;
        Assertions.checkArgument(byteBuffer2 == null || byteBuffer == byteBuffer2);
        if (this.pendingConfiguration != null) {
            if (!drainAudioProcessorsToEndOfStream()) {
                return false;
            }
            if (!this.pendingConfiguration.canReuseAudioTrack(this.configuration)) {
                playPendingData();
                if (hasPendingData()) {
                    return false;
                }
                flush();
            } else {
                this.configuration = this.pendingConfiguration;
                this.pendingConfiguration = null;
            }
            if (this.configuration.canApplyPlaybackParameters) {
                playbackParameters2 = this.audioProcessorChain.applyPlaybackParameters(this.playbackParameters);
            } else {
                playbackParameters2 = PlaybackParameters.DEFAULT;
            }
            this.playbackParameters = playbackParameters2;
            setupAudioProcessors();
        }
        if (!isInitialized()) {
            initialize();
            if (this.playing) {
                play();
            }
        }
        if (!this.audioTrackPositionTracker.mayHandleBuffer(getWrittenFrames())) {
            return false;
        }
        if (this.inputBuffer != null) {
            str = "AudioTrack";
        } else if (!byteBuffer.hasRemaining()) {
            return true;
        } else {
            if (!this.configuration.isInputPcm && this.framesPerEncodedSample == 0) {
                int framesPerEncodedSample2 = getFramesPerEncodedSample(this.configuration.outputEncoding, byteBuffer);
                this.framesPerEncodedSample = framesPerEncodedSample2;
                if (framesPerEncodedSample2 == 0) {
                    return true;
                }
            }
            if (this.afterDrainPlaybackParameters == null) {
                str2 = "AudioTrack";
            } else if (!drainAudioProcessorsToEndOfStream()) {
                return false;
            } else {
                PlaybackParameters playbackParameters3 = this.afterDrainPlaybackParameters;
                this.afterDrainPlaybackParameters = null;
                str2 = "AudioTrack";
                this.playbackParametersCheckpoints.add(new PlaybackParametersCheckpoint(this.audioProcessorChain.applyPlaybackParameters(playbackParameters3), Math.max(0L, j), this.configuration.framesToDurationUs(getWrittenFrames())));
                setupAudioProcessors();
            }
            if (this.startMediaTimeState == 0) {
                this.startMediaTimeUs = Math.max(0L, j);
                this.startMediaTimeState = 1;
                str = str2;
            } else {
                long inputFramesToDurationUs = this.startMediaTimeUs + this.configuration.inputFramesToDurationUs(getSubmittedFrames() - this.trimmingAudioProcessor.getTrimmedFrameCount());
                if (this.startMediaTimeState != 1 || Math.abs(inputFramesToDurationUs - j) <= 200000) {
                    str = str2;
                } else {
                    StringBuilder sb = new StringBuilder(80);
                    sb.append("Discontinuity detected [expected ");
                    sb.append(inputFramesToDurationUs);
                    sb.append(", got ");
                    sb.append(j);
                    sb.append("]");
                    String sb2 = sb.toString();
                    str = str2;
                    Log.e(str, sb2);
                    this.startMediaTimeState = 2;
                }
                if (this.startMediaTimeState == 2) {
                    long j2 = j - inputFramesToDurationUs;
                    this.startMediaTimeUs += j2;
                    this.startMediaTimeState = 1;
                    AudioSink.Listener listener2 = this.listener;
                    if (!(listener2 == null || j2 == 0)) {
                        listener2.onPositionDiscontinuity();
                    }
                }
            }
            if (this.configuration.isInputPcm) {
                this.submittedPcmBytes += (long) byteBuffer.remaining();
            } else {
                this.submittedEncodedFrames += (long) this.framesPerEncodedSample;
            }
            this.inputBuffer = byteBuffer;
        }
        if (this.configuration.processingEnabled) {
            processBuffers(j);
        } else {
            writeBuffer(this.inputBuffer, j);
        }
        if (!this.inputBuffer.hasRemaining()) {
            this.inputBuffer = null;
            return true;
        } else if (!this.audioTrackPositionTracker.isStalled(getWrittenFrames())) {
            return false;
        } else {
            Log.w(str, "Resetting stalled audio track");
            flush();
            return true;
        }
    }

    private void processBuffers(long j) throws AudioSink.WriteException {
        ByteBuffer byteBuffer;
        int length = this.activeAudioProcessors.length;
        int i = length;
        while (i >= 0) {
            if (i > 0) {
                byteBuffer = this.outputBuffers[i - 1];
            } else {
                byteBuffer = this.inputBuffer;
                if (byteBuffer == null) {
                    byteBuffer = AudioProcessor.EMPTY_BUFFER;
                }
            }
            if (i == length) {
                writeBuffer(byteBuffer, j);
            } else {
                AudioProcessor audioProcessor = this.activeAudioProcessors[i];
                audioProcessor.queueInput(byteBuffer);
                ByteBuffer output = audioProcessor.getOutput();
                this.outputBuffers[i] = output;
                if (output.hasRemaining()) {
                    i++;
                }
            }
            if (!byteBuffer.hasRemaining()) {
                i--;
            } else {
                return;
            }
        }
    }

    private void writeBuffer(ByteBuffer byteBuffer, long j) throws AudioSink.WriteException {
        if (byteBuffer.hasRemaining()) {
            ByteBuffer byteBuffer2 = this.outputBuffer;
            boolean z = true;
            int i = 0;
            if (byteBuffer2 != null) {
                Assertions.checkArgument(byteBuffer2 == byteBuffer);
            } else {
                this.outputBuffer = byteBuffer;
                if (Util.SDK_INT < 21) {
                    int remaining = byteBuffer.remaining();
                    byte[] bArr = this.preV21OutputBuffer;
                    if (bArr == null || bArr.length < remaining) {
                        this.preV21OutputBuffer = new byte[remaining];
                    }
                    int position = byteBuffer.position();
                    byteBuffer.get(this.preV21OutputBuffer, 0, remaining);
                    byteBuffer.position(position);
                    this.preV21OutputBufferOffset = 0;
                }
            }
            int remaining2 = byteBuffer.remaining();
            if (Util.SDK_INT < 21) {
                int availableBufferSize = this.audioTrackPositionTracker.getAvailableBufferSize(this.writtenPcmBytes);
                if (availableBufferSize > 0 && (i = this.audioTrack.write(this.preV21OutputBuffer, this.preV21OutputBufferOffset, Math.min(remaining2, availableBufferSize))) > 0) {
                    this.preV21OutputBufferOffset += i;
                    byteBuffer.position(byteBuffer.position() + i);
                }
            } else if (this.tunneling) {
                if (j == -9223372036854775807L) {
                    z = false;
                }
                Assertions.checkState(z);
                i = writeNonBlockingWithAvSyncV21(this.audioTrack, byteBuffer, remaining2, j);
            } else {
                i = writeNonBlockingV21(this.audioTrack, byteBuffer, remaining2);
            }
            this.lastFeedElapsedRealtimeMs = SystemClock.elapsedRealtime();
            if (i >= 0) {
                if (this.configuration.isInputPcm) {
                    this.writtenPcmBytes += (long) i;
                }
                if (i == remaining2) {
                    if (!this.configuration.isInputPcm) {
                        this.writtenEncodedFrames += (long) this.framesPerEncodedSample;
                    }
                    this.outputBuffer = null;
                    return;
                }
                return;
            }
            throw new AudioSink.WriteException(i);
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void playToEndOfStream() throws AudioSink.WriteException {
        if (!this.handledEndOfStream && isInitialized() && drainAudioProcessorsToEndOfStream()) {
            playPendingData();
            this.handledEndOfStream = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e  */
    private boolean drainAudioProcessorsToEndOfStream() throws AudioSink.WriteException {
        boolean z;
        int i;
        AudioProcessor[] audioProcessorArr;
        ByteBuffer byteBuffer;
        if (this.drainingAudioProcessorIndex == -1) {
            this.drainingAudioProcessorIndex = this.configuration.processingEnabled ? 0 : this.activeAudioProcessors.length;
        } else {
            z = false;
            i = this.drainingAudioProcessorIndex;
            audioProcessorArr = this.activeAudioProcessors;
            if (i >= audioProcessorArr.length) {
                AudioProcessor audioProcessor = audioProcessorArr[i];
                if (z) {
                    audioProcessor.queueEndOfStream();
                }
                processBuffers(-9223372036854775807L);
                if (!audioProcessor.isEnded()) {
                    return false;
                }
                this.drainingAudioProcessorIndex++;
                return false;
            }
            byteBuffer = this.outputBuffer;
            if (byteBuffer != null) {
                writeBuffer(byteBuffer, -9223372036854775807L);
                if (this.outputBuffer != null) {
                    return false;
                }
            }
            this.drainingAudioProcessorIndex = -1;
            return true;
        }
        z = true;
        i = this.drainingAudioProcessorIndex;
        audioProcessorArr = this.activeAudioProcessors;
        if (i >= audioProcessorArr.length) {
            byteBuffer = this.outputBuffer;
        }
        byteBuffer = this.outputBuffer;
        if (byteBuffer != null) {
        }
        this.drainingAudioProcessorIndex = -1;
        return true;
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public boolean isEnded() {
        return !isInitialized() || (this.handledEndOfStream && !hasPendingData());
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public boolean hasPendingData() {
        return isInitialized() && this.audioTrackPositionTracker.hasPendingData(getWrittenFrames());
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters2) {
        Configuration configuration2 = this.configuration;
        if (configuration2 == null || configuration2.canApplyPlaybackParameters) {
            PlaybackParameters playbackParameters3 = this.afterDrainPlaybackParameters;
            if (playbackParameters3 == null) {
                if (!this.playbackParametersCheckpoints.isEmpty()) {
                    playbackParameters3 = this.playbackParametersCheckpoints.getLast().playbackParameters;
                } else {
                    playbackParameters3 = this.playbackParameters;
                }
            }
            if (!playbackParameters2.equals(playbackParameters3)) {
                if (isInitialized()) {
                    this.afterDrainPlaybackParameters = playbackParameters2;
                } else {
                    this.playbackParameters = this.audioProcessorChain.applyPlaybackParameters(playbackParameters2);
                }
            }
            return this.playbackParameters;
        }
        PlaybackParameters playbackParameters4 = PlaybackParameters.DEFAULT;
        this.playbackParameters = playbackParameters4;
        return playbackParameters4;
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void setAudioAttributes(AudioAttributes audioAttributes2) {
        if (!this.audioAttributes.equals(audioAttributes2)) {
            this.audioAttributes = audioAttributes2;
            if (!this.tunneling) {
                flush();
                this.audioSessionId = 0;
            }
        }
    }

    public void setAudioSessionId(int i) {
        if (this.audioSessionId != i) {
            this.audioSessionId = i;
            flush();
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo2) {
        if (!this.auxEffectInfo.equals(auxEffectInfo2)) {
            int i = auxEffectInfo2.effectId;
            float f = auxEffectInfo2.sendLevel;
            if (this.audioTrack != null) {
                if (this.auxEffectInfo.effectId != i) {
                    this.audioTrack.attachAuxEffect(i);
                }
                if (i != 0) {
                    this.audioTrack.setAuxEffectSendLevel(f);
                }
            }
            this.auxEffectInfo = auxEffectInfo2;
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void enableTunnelingV21(int i) {
        Assertions.checkState(Util.SDK_INT >= 21);
        if (!this.tunneling || this.audioSessionId != i) {
            this.tunneling = true;
            this.audioSessionId = i;
            flush();
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void disableTunneling() {
        if (this.tunneling) {
            this.tunneling = false;
            this.audioSessionId = 0;
            flush();
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void setVolume(float f) {
        if (this.volume != f) {
            this.volume = f;
            setVolumeInternal();
        }
    }

    private void setVolumeInternal() {
        if (isInitialized()) {
            if (Util.SDK_INT >= 21) {
                setVolumeInternalV21(this.audioTrack, this.volume);
            } else {
                setVolumeInternalV3(this.audioTrack, this.volume);
            }
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void pause() {
        this.playing = false;
        if (isInitialized() && this.audioTrackPositionTracker.pause()) {
            this.audioTrack.pause();
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void flush() {
        if (isInitialized()) {
            this.submittedPcmBytes = 0;
            this.submittedEncodedFrames = 0;
            this.writtenPcmBytes = 0;
            this.writtenEncodedFrames = 0;
            this.framesPerEncodedSample = 0;
            PlaybackParameters playbackParameters2 = this.afterDrainPlaybackParameters;
            if (playbackParameters2 != null) {
                this.playbackParameters = playbackParameters2;
                this.afterDrainPlaybackParameters = null;
            } else if (!this.playbackParametersCheckpoints.isEmpty()) {
                this.playbackParameters = this.playbackParametersCheckpoints.getLast().playbackParameters;
            }
            this.playbackParametersCheckpoints.clear();
            this.playbackParametersOffsetUs = 0;
            this.playbackParametersPositionUs = 0;
            this.trimmingAudioProcessor.resetTrimmedFrameCount();
            flushAudioProcessors();
            this.inputBuffer = null;
            this.outputBuffer = null;
            this.stoppedAudioTrack = false;
            this.handledEndOfStream = false;
            this.drainingAudioProcessorIndex = -1;
            this.avSyncHeader = null;
            this.bytesUntilNextAvSync = 0;
            this.startMediaTimeState = 0;
            if (this.audioTrackPositionTracker.isPlaying()) {
                this.audioTrack.pause();
            }
            final AudioTrack audioTrack2 = this.audioTrack;
            this.audioTrack = null;
            Configuration configuration2 = this.pendingConfiguration;
            if (configuration2 != null) {
                this.configuration = configuration2;
                this.pendingConfiguration = null;
            }
            this.audioTrackPositionTracker.reset();
            this.releasingConditionVariable.close();
            new Thread() {
                /* class androidx.media2.exoplayer.external.audio.DefaultAudioSink.AnonymousClass1 */

                public void run() {
                    try {
                        audioTrack2.flush();
                        audioTrack2.release();
                    } finally {
                        DefaultAudioSink.this.releasingConditionVariable.open();
                    }
                }
            }.start();
        }
    }

    @Override // androidx.media2.exoplayer.external.audio.AudioSink
    public void reset() {
        flush();
        releaseKeepSessionIdAudioTrack();
        for (AudioProcessor audioProcessor : this.toIntPcmAvailableAudioProcessors) {
            audioProcessor.reset();
        }
        for (AudioProcessor audioProcessor2 : this.toFloatPcmAvailableAudioProcessors) {
            audioProcessor2.reset();
        }
        this.audioSessionId = 0;
        this.playing = false;
    }

    private void releaseKeepSessionIdAudioTrack() {
        final AudioTrack audioTrack2 = this.keepSessionIdAudioTrack;
        if (audioTrack2 != null) {
            this.keepSessionIdAudioTrack = null;
            new Thread(this) {
                /* class androidx.media2.exoplayer.external.audio.DefaultAudioSink.AnonymousClass2 */

                public void run() {
                    audioTrack2.release();
                }
            }.start();
        }
    }

    private long applySpeedup(long j) {
        long j2;
        long mediaDurationForPlayoutDuration;
        PlaybackParametersCheckpoint playbackParametersCheckpoint = null;
        while (!this.playbackParametersCheckpoints.isEmpty() && j >= this.playbackParametersCheckpoints.getFirst().positionUs) {
            playbackParametersCheckpoint = this.playbackParametersCheckpoints.remove();
        }
        if (playbackParametersCheckpoint != null) {
            this.playbackParameters = playbackParametersCheckpoint.playbackParameters;
            this.playbackParametersPositionUs = playbackParametersCheckpoint.positionUs;
            this.playbackParametersOffsetUs = playbackParametersCheckpoint.mediaTimeUs - this.startMediaTimeUs;
        }
        if (this.playbackParameters.speed == 1.0f) {
            return (j + this.playbackParametersOffsetUs) - this.playbackParametersPositionUs;
        }
        if (this.playbackParametersCheckpoints.isEmpty()) {
            j2 = this.playbackParametersOffsetUs;
            mediaDurationForPlayoutDuration = this.audioProcessorChain.getMediaDuration(j - this.playbackParametersPositionUs);
        } else {
            j2 = this.playbackParametersOffsetUs;
            mediaDurationForPlayoutDuration = Util.getMediaDurationForPlayoutDuration(j - this.playbackParametersPositionUs, this.playbackParameters.speed);
        }
        return j2 + mediaDurationForPlayoutDuration;
    }

    private long applySkipping(long j) {
        return j + this.configuration.framesToDurationUs(this.audioProcessorChain.getSkippedOutputFrameCount());
    }

    private boolean isInitialized() {
        return this.audioTrack != null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long getSubmittedFrames() {
        if (this.configuration.isInputPcm) {
            return this.submittedPcmBytes / ((long) this.configuration.inputPcmFrameSize);
        }
        return this.submittedEncodedFrames;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long getWrittenFrames() {
        if (this.configuration.isInputPcm) {
            return this.writtenPcmBytes / ((long) this.configuration.outputPcmFrameSize);
        }
        return this.writtenEncodedFrames;
    }

    private static AudioTrack initializeKeepSessionIdAudioTrack(int i) {
        return new AudioTrack(3, 4000, 4, 2, 2, 0, i);
    }

    private static int getChannelConfig(int i, boolean z) {
        if (Util.SDK_INT <= 28 && !z) {
            if (i == 7) {
                i = 8;
            } else if (i == 3 || i == 4 || i == 5) {
                i = 6;
            }
        }
        if (Util.SDK_INT <= 26 && "fugu".equals(Util.DEVICE) && !z && i == 1) {
            i = 2;
        }
        return Util.getAudioTrackChannelConfig(i);
    }

    /* access modifiers changed from: private */
    public static int getMaximumEncodedRateBytesPerSecond(int i) {
        if (i == 5) {
            return 80000;
        }
        if (i == 6) {
            return 768000;
        }
        if (i == 7) {
            return 192000;
        }
        if (i == 8) {
            return 2250000;
        }
        if (i == 14) {
            return 3062500;
        }
        if (i == 17) {
            return 336000;
        }
        throw new IllegalArgumentException();
    }

    private static int getFramesPerEncodedSample(int i, ByteBuffer byteBuffer) {
        if (i == 7 || i == 8) {
            return DtsUtil.parseDtsAudioSampleCount(byteBuffer);
        }
        if (i == 5) {
            return Ac3Util.getAc3SyncframeAudioSampleCount();
        }
        if (i == 6) {
            return Ac3Util.parseEAc3SyncframeAudioSampleCount(byteBuffer);
        }
        if (i == 17) {
            return Ac4Util.parseAc4SyncframeAudioSampleCount(byteBuffer);
        }
        if (i == 14) {
            int findTrueHdSyncframeOffset = Ac3Util.findTrueHdSyncframeOffset(byteBuffer);
            if (findTrueHdSyncframeOffset == -1) {
                return 0;
            }
            return Ac3Util.parseTrueHdSyncframeAudioSampleCount(byteBuffer, findTrueHdSyncframeOffset) * 16;
        }
        StringBuilder sb = new StringBuilder(38);
        sb.append("Unexpected audio encoding: ");
        sb.append(i);
        throw new IllegalStateException(sb.toString());
    }

    private static int writeNonBlockingV21(AudioTrack audioTrack2, ByteBuffer byteBuffer, int i) {
        return audioTrack2.write(byteBuffer, i, 1);
    }

    private int writeNonBlockingWithAvSyncV21(AudioTrack audioTrack2, ByteBuffer byteBuffer, int i, long j) {
        if (this.avSyncHeader == null) {
            ByteBuffer allocate = ByteBuffer.allocate(16);
            this.avSyncHeader = allocate;
            allocate.order(ByteOrder.BIG_ENDIAN);
            this.avSyncHeader.putInt(1431633921);
        }
        if (this.bytesUntilNextAvSync == 0) {
            this.avSyncHeader.putInt(4, i);
            this.avSyncHeader.putLong(8, j * 1000);
            this.avSyncHeader.position(0);
            this.bytesUntilNextAvSync = i;
        }
        int remaining = this.avSyncHeader.remaining();
        if (remaining > 0) {
            int write = audioTrack2.write(this.avSyncHeader, remaining, 1);
            if (write < 0) {
                this.bytesUntilNextAvSync = 0;
                return write;
            } else if (write < remaining) {
                return 0;
            }
        }
        int writeNonBlockingV21 = writeNonBlockingV21(audioTrack2, byteBuffer, i);
        if (writeNonBlockingV21 < 0) {
            this.bytesUntilNextAvSync = 0;
            return writeNonBlockingV21;
        }
        this.bytesUntilNextAvSync -= writeNonBlockingV21;
        return writeNonBlockingV21;
    }

    private static void setVolumeInternalV21(AudioTrack audioTrack2, float f) {
        audioTrack2.setVolume(f);
    }

    private static void setVolumeInternalV3(AudioTrack audioTrack2, float f) {
        audioTrack2.setStereoVolume(f, f);
    }

    private void playPendingData() {
        if (!this.stoppedAudioTrack) {
            this.stoppedAudioTrack = true;
            this.audioTrackPositionTracker.handleEndOfStream(getWrittenFrames());
            this.audioTrack.stop();
            this.bytesUntilNextAvSync = 0;
        }
    }

    /* access modifiers changed from: private */
    public static final class PlaybackParametersCheckpoint {
        private final long mediaTimeUs;
        private final PlaybackParameters playbackParameters;
        private final long positionUs;

        private PlaybackParametersCheckpoint(PlaybackParameters playbackParameters2, long j, long j2) {
            this.playbackParameters = playbackParameters2;
            this.mediaTimeUs = j;
            this.positionUs = j2;
        }
    }

    private final class PositionTrackerListener implements AudioTrackPositionTracker.Listener {
        private PositionTrackerListener() {
        }

        @Override // androidx.media2.exoplayer.external.audio.AudioTrackPositionTracker.Listener
        public void onPositionFramesMismatch(long j, long j2, long j3, long j4) {
            long submittedFrames = DefaultAudioSink.this.getSubmittedFrames();
            long writtenFrames = DefaultAudioSink.this.getWrittenFrames();
            StringBuilder sb = new StringBuilder(182);
            sb.append("Spurious audio timestamp (frame position mismatch): ");
            sb.append(j);
            sb.append(", ");
            sb.append(j2);
            sb.append(", ");
            sb.append(j3);
            sb.append(", ");
            sb.append(j4);
            sb.append(", ");
            sb.append(submittedFrames);
            sb.append(", ");
            sb.append(writtenFrames);
            String sb2 = sb.toString();
            if (!DefaultAudioSink.failOnSpuriousAudioTimestamp) {
                Log.w("AudioTrack", sb2);
                return;
            }
            throw new InvalidAudioTrackTimestampException(sb2);
        }

        @Override // androidx.media2.exoplayer.external.audio.AudioTrackPositionTracker.Listener
        public void onSystemTimeUsMismatch(long j, long j2, long j3, long j4) {
            long submittedFrames = DefaultAudioSink.this.getSubmittedFrames();
            long writtenFrames = DefaultAudioSink.this.getWrittenFrames();
            StringBuilder sb = new StringBuilder(180);
            sb.append("Spurious audio timestamp (system clock mismatch): ");
            sb.append(j);
            sb.append(", ");
            sb.append(j2);
            sb.append(", ");
            sb.append(j3);
            sb.append(", ");
            sb.append(j4);
            sb.append(", ");
            sb.append(submittedFrames);
            sb.append(", ");
            sb.append(writtenFrames);
            String sb2 = sb.toString();
            if (!DefaultAudioSink.failOnSpuriousAudioTimestamp) {
                Log.w("AudioTrack", sb2);
                return;
            }
            throw new InvalidAudioTrackTimestampException(sb2);
        }

        @Override // androidx.media2.exoplayer.external.audio.AudioTrackPositionTracker.Listener
        public void onInvalidLatency(long j) {
            StringBuilder sb = new StringBuilder(61);
            sb.append("Ignoring impossibly large audio latency: ");
            sb.append(j);
            Log.w("AudioTrack", sb.toString());
        }

        @Override // androidx.media2.exoplayer.external.audio.AudioTrackPositionTracker.Listener
        public void onUnderrun(int i, long j) {
            if (DefaultAudioSink.this.listener != null) {
                DefaultAudioSink.this.listener.onUnderrun(i, j, SystemClock.elapsedRealtime() - DefaultAudioSink.this.lastFeedElapsedRealtimeMs);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class Configuration {
        public final AudioProcessor[] availableAudioProcessors;
        public final int bufferSize;
        public final boolean canApplyPlaybackParameters;
        public final int inputPcmFrameSize;
        public final int inputSampleRate;
        public final boolean isInputPcm;
        public final int outputChannelConfig;
        public final int outputEncoding;
        public final int outputPcmFrameSize;
        public final int outputSampleRate;
        public final boolean processingEnabled;

        public Configuration(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2, boolean z3, AudioProcessor[] audioProcessorArr) {
            this.isInputPcm = z;
            this.inputPcmFrameSize = i;
            this.inputSampleRate = i2;
            this.outputPcmFrameSize = i3;
            this.outputSampleRate = i4;
            this.outputChannelConfig = i5;
            this.outputEncoding = i6;
            this.bufferSize = i7 == 0 ? getDefaultBufferSize() : i7;
            this.processingEnabled = z2;
            this.canApplyPlaybackParameters = z3;
            this.availableAudioProcessors = audioProcessorArr;
        }

        public boolean canReuseAudioTrack(Configuration configuration) {
            return configuration.outputEncoding == this.outputEncoding && configuration.outputSampleRate == this.outputSampleRate && configuration.outputChannelConfig == this.outputChannelConfig;
        }

        public long inputFramesToDurationUs(long j) {
            return (j * 1000000) / ((long) this.inputSampleRate);
        }

        public long framesToDurationUs(long j) {
            return (j * 1000000) / ((long) this.outputSampleRate);
        }

        public long durationUsToFrames(long j) {
            return (j * ((long) this.outputSampleRate)) / 1000000;
        }

        public AudioTrack buildAudioTrack(boolean z, AudioAttributes audioAttributes, int i) throws AudioSink.InitializationException {
            AudioTrack audioTrack;
            if (Util.SDK_INT >= 21) {
                audioTrack = createAudioTrackV21(z, audioAttributes, i);
            } else {
                int streamTypeForAudioUsage = Util.getStreamTypeForAudioUsage(audioAttributes.usage);
                if (i == 0) {
                    audioTrack = new AudioTrack(streamTypeForAudioUsage, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1);
                } else {
                    audioTrack = new AudioTrack(streamTypeForAudioUsage, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1, i);
                }
            }
            int state = audioTrack.getState();
            if (state == 1) {
                return audioTrack;
            }
            try {
                audioTrack.release();
            } catch (Exception unused) {
            }
            throw new AudioSink.InitializationException(state, this.outputSampleRate, this.outputChannelConfig, this.bufferSize);
        }

        private AudioTrack createAudioTrackV21(boolean z, AudioAttributes audioAttributes, int i) {
            AudioAttributes audioAttributes2;
            if (z) {
                audioAttributes2 = new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
            } else {
                audioAttributes2 = audioAttributes.getAudioAttributesV21();
            }
            return new AudioTrack(audioAttributes2, new AudioFormat.Builder().setChannelMask(this.outputChannelConfig).setEncoding(this.outputEncoding).setSampleRate(this.outputSampleRate).build(), this.bufferSize, 1, i != 0 ? i : 0);
        }

        private int getDefaultBufferSize() {
            if (this.isInputPcm) {
                int minBufferSize = AudioTrack.getMinBufferSize(this.outputSampleRate, this.outputChannelConfig, this.outputEncoding);
                Assertions.checkState(minBufferSize != -2);
                return Util.constrainValue(minBufferSize * 4, ((int) durationUsToFrames(250000)) * this.outputPcmFrameSize, (int) Math.max((long) minBufferSize, durationUsToFrames(750000) * ((long) this.outputPcmFrameSize)));
            }
            int maximumEncodedRateBytesPerSecond = DefaultAudioSink.getMaximumEncodedRateBytesPerSecond(this.outputEncoding);
            if (this.outputEncoding == 5) {
                maximumEncodedRateBytesPerSecond *= 2;
            }
            return (int) ((((long) maximumEncodedRateBytesPerSecond) * 250000) / 1000000);
        }
    }
}
