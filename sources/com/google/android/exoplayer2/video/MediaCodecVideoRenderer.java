package com.google.android.exoplayer2.video;

import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Pair;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.mediacodec.MediaFormatUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.google.android.gms.common.Scopes;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private boolean codecHandlesHdr10PlusOutOfBandMetadata;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private int currentHeight;
    private MediaFormat currentMediaFormat;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private Surface dummySurface;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private VideoFrameMetadataListener frameMetadataListener;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private long initialPositionUs;
    private long joiningDeadlineMs;
    private long lastInputTimeUs;
    private long lastRenderTimeUs;
    private final int maxDroppedFramesToNotify;
    private long outputStreamOffsetUs;
    private int pendingOutputStreamOffsetCount;
    private final long[] pendingOutputStreamOffsetsUs;
    private final long[] pendingOutputStreamSwitchTimesUs;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean renderedFirstFrame;
    private int reportedHeight;
    private float reportedPixelWidthHeightRatio;
    private int reportedUnappliedRotationDegrees;
    private int reportedWidth;
    private int scalingMode;
    private Surface surface;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;

    private static boolean isBufferLate(long j) {
        return j < -30000;
    }

    private static boolean isBufferVeryLate(long j) {
        return j < -500000;
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, mediaCodecSelector, j, null, false, handler, videoRendererEventListener, i);
    }

    @Deprecated
    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, mediaCodecSelector, j, drmSessionManager, z, false, handler, videoRendererEventListener, i);
    }

    @Deprecated
    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, boolean z2, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        super(2, mediaCodecSelector, drmSessionManager, z, z2, 30.0f);
        this.allowedJoiningTimeMs = j;
        this.maxDroppedFramesToNotify = i;
        Context applicationContext = context2.getApplicationContext();
        this.context = applicationContext;
        this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(applicationContext);
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.pendingOutputStreamOffsetsUs = new long[10];
        this.pendingOutputStreamSwitchTimesUs = new long[10];
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.lastInputTimeUs = -9223372036854775807L;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.scalingMode = 1;
        clearReportedVideoSize();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Format format) throws MediaCodecUtil.DecoderQueryException {
        int i = 0;
        if (!MimeTypes.isVideo(format.sampleMimeType)) {
            return RendererCapabilities.CC.create(0);
        }
        DrmInitData drmInitData = format.drmInitData;
        boolean z = drmInitData != null;
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(mediaCodecSelector, format, z, false);
        if (z && decoderInfos.isEmpty()) {
            decoderInfos = getDecoderInfos(mediaCodecSelector, format, false, false);
        }
        if (decoderInfos.isEmpty()) {
            return RendererCapabilities.CC.create(1);
        }
        if (!(drmInitData == null || FrameworkMediaCrypto.class.equals(format.exoMediaCryptoType) || (format.exoMediaCryptoType == null && supportsFormatDrm(drmSessionManager, drmInitData)))) {
            return RendererCapabilities.CC.create(2);
        }
        MediaCodecInfo mediaCodecInfo = decoderInfos.get(0);
        boolean isFormatSupported = mediaCodecInfo.isFormatSupported(format);
        int i2 = mediaCodecInfo.isSeamlessAdaptationSupported(format) ? 16 : 8;
        if (isFormatSupported) {
            List<MediaCodecInfo> decoderInfos2 = getDecoderInfos(mediaCodecSelector, format, z, true);
            if (!decoderInfos2.isEmpty()) {
                MediaCodecInfo mediaCodecInfo2 = decoderInfos2.get(0);
                if (mediaCodecInfo2.isFormatSupported(format) && mediaCodecInfo2.isSeamlessAdaptationSupported(format)) {
                    i = 32;
                }
            }
        }
        return RendererCapabilities.CC.create(isFormatSupported ? 4 : 3, i2, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return getDecoderInfos(mediaCodecSelector, format, z, this.tunneling);
    }

    private static List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        Pair<Integer, Integer> codecProfileAndLevel;
        String str = format.sampleMimeType;
        if (str == null) {
            return Collections.emptyList();
        }
        List<MediaCodecInfo> decoderInfosSortedByFormatSupport = MediaCodecUtil.getDecoderInfosSortedByFormatSupport(mediaCodecSelector.getDecoderInfos(str, z, z2), format);
        if ("video/dolby-vision".equals(str) && (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format)) != null) {
            int intValue = ((Integer) codecProfileAndLevel.first).intValue();
            if (intValue == 16 || intValue == 256) {
                decoderInfosSortedByFormatSupport.addAll(mediaCodecSelector.getDecoderInfos("video/hevc", z, z2));
            } else if (intValue == 512) {
                decoderInfosSortedByFormatSupport.addAll(mediaCodecSelector.getDecoderInfos("video/avc", z, z2));
            }
        }
        return Collections.unmodifiableList(decoderInfosSortedByFormatSupport);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onEnabled(boolean z) throws ExoPlaybackException {
        super.onEnabled(z);
        int i = this.tunnelingAudioSessionId;
        int i2 = getConfiguration().tunnelingAudioSessionId;
        this.tunnelingAudioSessionId = i2;
        this.tunneling = i2 != 0;
        if (this.tunnelingAudioSessionId != i) {
            releaseCodec();
        }
        this.eventDispatcher.enabled(this.decoderCounters);
        this.frameReleaseTimeHelper.enable();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j) throws ExoPlaybackException {
        if (this.outputStreamOffsetUs == -9223372036854775807L) {
            this.outputStreamOffsetUs = j;
        } else {
            int i = this.pendingOutputStreamOffsetCount;
            if (i == this.pendingOutputStreamOffsetsUs.length) {
                Log.w("MediaCodecVideoRenderer", "Too many stream changes, so dropping offset: " + this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1]);
            } else {
                this.pendingOutputStreamOffsetCount = i + 1;
            }
            long[] jArr = this.pendingOutputStreamOffsetsUs;
            int i2 = this.pendingOutputStreamOffsetCount;
            jArr[i2 - 1] = j;
            this.pendingOutputStreamSwitchTimesUs[i2 - 1] = this.lastInputTimeUs;
        }
        super.onStreamChanged(formatArr, j);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        clearRenderedFirstFrame();
        this.initialPositionUs = -9223372036854775807L;
        this.consecutiveDroppedFrameCount = 0;
        this.lastInputTimeUs = -9223372036854775807L;
        int i = this.pendingOutputStreamOffsetCount;
        if (i != 0) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[i - 1];
            this.pendingOutputStreamOffsetCount = 0;
        }
        if (z) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = -9223372036854775807L;
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        Surface surface2;
        if (super.isReady() && (this.renderedFirstFrame || (((surface2 = this.dummySurface) != null && this.surface == surface2) || getCodec() == null || this.tunneling))) {
            this.joiningDeadlineMs = -9223372036854775807L;
            return true;
        } else if (this.joiningDeadlineMs == -9223372036854775807L) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
                return true;
            }
            this.joiningDeadlineMs = -9223372036854775807L;
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onStopped() {
        this.joiningDeadlineMs = -9223372036854775807L;
        maybeNotifyDroppedFrames();
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        this.lastInputTimeUs = -9223372036854775807L;
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.pendingOutputStreamOffsetCount = 0;
        this.currentMediaFormat = null;
        clearReportedVideoSize();
        clearRenderedFirstFrame();
        this.frameReleaseTimeHelper.disable();
        this.tunnelingOnFrameRenderedListener = null;
        try {
            super.onDisabled();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onReset() {
        try {
            super.onReset();
        } finally {
            Surface surface2 = this.dummySurface;
            if (surface2 != null) {
                if (this.surface == surface2) {
                    this.surface = null;
                }
                this.dummySurface.release();
                this.dummySurface = null;
            }
        }
    }

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setSurface((Surface) obj);
        } else if (i == 4) {
            this.scalingMode = ((Integer) obj).intValue();
            MediaCodec codec = getCodec();
            if (codec != null) {
                codec.setVideoScalingMode(this.scalingMode);
            }
        } else if (i == 6) {
            this.frameMetadataListener = (VideoFrameMetadataListener) obj;
        } else {
            super.handleMessage(i, obj);
        }
    }

    private void setSurface(Surface surface2) throws ExoPlaybackException {
        if (surface2 == null) {
            Surface surface3 = this.dummySurface;
            if (surface3 != null) {
                surface2 = surface3;
            } else {
                MediaCodecInfo codecInfo = getCodecInfo();
                if (codecInfo != null && shouldUseDummySurface(codecInfo)) {
                    surface2 = DummySurface.newInstanceV17(this.context, codecInfo.secure);
                    this.dummySurface = surface2;
                }
            }
        }
        if (this.surface != surface2) {
            this.surface = surface2;
            int state = getState();
            MediaCodec codec = getCodec();
            if (codec != null) {
                if (Util.SDK_INT < 23 || surface2 == null || this.codecNeedsSetOutputSurfaceWorkaround) {
                    releaseCodec();
                    maybeInitCodec();
                } else {
                    setOutputSurfaceV23(codec, surface2);
                }
            }
            if (surface2 == null || surface2 == this.dummySurface) {
                clearReportedVideoSize();
                clearRenderedFirstFrame();
                return;
            }
            maybeRenotifyVideoSizeChanged();
            clearRenderedFirstFrame();
            if (state == 2) {
                setJoiningDeadlineMs();
            }
        } else if (surface2 != null && surface2 != this.dummySurface) {
            maybeRenotifyVideoSizeChanged();
            maybeRenotifyRenderedFirstFrame();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.surface != null || shouldUseDummySurface(mediaCodecInfo);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean getCodecNeedsEosPropagation() {
        return this.tunneling && Util.SDK_INT < 23;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto, float f) {
        String str = mediaCodecInfo.codecMimeType;
        CodecMaxValues codecMaxValues2 = getCodecMaxValues(mediaCodecInfo, format, getStreamFormats());
        this.codecMaxValues = codecMaxValues2;
        MediaFormat mediaFormat = getMediaFormat(format, str, codecMaxValues2, f, this.deviceNeedsNoPostProcessWorkaround, this.tunnelingAudioSessionId);
        if (this.surface == null) {
            Assertions.checkState(shouldUseDummySurface(mediaCodecInfo));
            if (this.dummySurface == null) {
                this.dummySurface = DummySurface.newInstanceV17(this.context, mediaCodecInfo.secure);
            }
            this.surface = this.dummySurface;
        }
        mediaCodec.configure(mediaFormat, this.surface, mediaCrypto, 0);
        if (Util.SDK_INT >= 23 && this.tunneling) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(mediaCodec);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public int canKeepCodec(MediaCodec mediaCodec, MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        if (!mediaCodecInfo.isSeamlessAdaptationSupported(format, format2, true) || format2.width > this.codecMaxValues.width || format2.height > this.codecMaxValues.height || getMaxInputSize(mediaCodecInfo, format2) > this.codecMaxValues.inputSize) {
            return 0;
        }
        return format.initializationDataEquals(format2) ? 3 : 2;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void releaseCodec() {
        try {
            super.releaseCodec();
        } finally {
            this.buffersInCodecCount = 0;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean flushOrReleaseCodec() {
        try {
            return super.flushOrReleaseCodec();
        } finally {
            this.buffersInCodecCount = 0;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public float getCodecOperatingRateV23(float f, Format format, Format[] formatArr) {
        float f2 = -1.0f;
        for (Format format2 : formatArr) {
            float f3 = format2.frameRate;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onCodecInitialized(String str, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
        this.codecHandlesHdr10PlusOutOfBandMetadata = ((MediaCodecInfo) Assertions.checkNotNull(getCodecInfo())).isHdr10PlusOutOfBandMetadataSupported();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        super.onInputFormatChanged(formatHolder);
        Format format = formatHolder.format;
        this.eventDispatcher.inputFormatChanged(format);
        this.pendingPixelWidthHeightRatio = format.pixelWidthHeightRatio;
        this.pendingRotationDegrees = format.rotationDegrees;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        this.buffersInCodecCount++;
        this.lastInputTimeUs = Math.max(decoderInputBuffer.timeUs, this.lastInputTimeUs);
        if (Util.SDK_INT < 23 && this.tunneling) {
            onProcessedTunneledBuffer(decoderInputBuffer.timeUs);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int i;
        int i2;
        this.currentMediaFormat = mediaFormat;
        boolean z = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        if (z) {
            i = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            i = mediaFormat.getInteger("width");
        }
        if (z) {
            i2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            i2 = mediaFormat.getInteger("height");
        }
        processOutputFormat(mediaCodec, i, i2);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        if (this.codecHandlesHdr10PlusOutOfBandMetadata) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.supplementalData);
            if (byteBuffer.remaining() >= 7) {
                byte b = byteBuffer.get();
                short s = byteBuffer.getShort();
                short s2 = byteBuffer.getShort();
                byte b2 = byteBuffer.get();
                byte b3 = byteBuffer.get();
                byteBuffer.position(0);
                if (b == -75 && s == 60 && s2 == 1 && b2 == 4 && b3 == 0) {
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    byteBuffer.position(0);
                    setHdr10PlusInfoV29(getCodec(), bArr);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z, boolean z2, Format format) throws ExoPlaybackException {
        if (this.initialPositionUs == -9223372036854775807L) {
            this.initialPositionUs = j;
        }
        long j4 = j3 - this.outputStreamOffsetUs;
        if (!z || z2) {
            long j5 = j3 - j;
            if (this.surface != this.dummySurface) {
                long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
                long j6 = elapsedRealtime - this.lastRenderTimeUs;
                boolean z3 = getState() == 2;
                if (this.joiningDeadlineMs == -9223372036854775807L && j >= this.outputStreamOffsetUs && (!this.renderedFirstFrame || (z3 && shouldForceRenderOutputBuffer(j5, j6)))) {
                    long nanoTime = System.nanoTime();
                    notifyFrameMetadataListener(j4, nanoTime, format, this.currentMediaFormat);
                    if (Util.SDK_INT >= 21) {
                        renderOutputBufferV21(mediaCodec, i, j4, nanoTime);
                        return true;
                    }
                    renderOutputBuffer(mediaCodec, i, j4);
                    return true;
                }
                if (z3 && j != this.initialPositionUs) {
                    long nanoTime2 = System.nanoTime();
                    long adjustReleaseTime = this.frameReleaseTimeHelper.adjustReleaseTime(j3, ((j5 - (elapsedRealtime - j2)) * 1000) + nanoTime2);
                    long j7 = (adjustReleaseTime - nanoTime2) / 1000;
                    boolean z4 = this.joiningDeadlineMs != -9223372036854775807L;
                    if (shouldDropBuffersToKeyframe(j7, j2, z2) && maybeDropBuffersToKeyframe(mediaCodec, i, j4, j, z4)) {
                        return false;
                    }
                    if (shouldDropOutputBuffer(j7, j2, z2)) {
                        if (z4) {
                            skipOutputBuffer(mediaCodec, i, j4);
                            return true;
                        }
                        dropOutputBuffer(mediaCodec, i, j4);
                        return true;
                    } else if (Util.SDK_INT >= 21) {
                        if (j7 < 50000) {
                            notifyFrameMetadataListener(j4, adjustReleaseTime, format, this.currentMediaFormat);
                            renderOutputBufferV21(mediaCodec, i, j4, adjustReleaseTime);
                            return true;
                        }
                    } else if (j7 < 30000) {
                        if (j7 > 11000) {
                            try {
                                Thread.sleep((j7 - 10000) / 1000);
                            } catch (InterruptedException unused) {
                                Thread.currentThread().interrupt();
                                return false;
                            }
                        }
                        notifyFrameMetadataListener(j4, adjustReleaseTime, format, this.currentMediaFormat);
                        renderOutputBuffer(mediaCodec, i, j4);
                        return true;
                    }
                }
                return false;
            } else if (!isBufferLate(j5)) {
                return false;
            } else {
                skipOutputBuffer(mediaCodec, i, j4);
                return true;
            }
        } else {
            skipOutputBuffer(mediaCodec, i, j4);
            return true;
        }
    }

    private void processOutputFormat(MediaCodec mediaCodec, int i, int i2) {
        this.currentWidth = i;
        this.currentHeight = i2;
        this.currentPixelWidthHeightRatio = this.pendingPixelWidthHeightRatio;
        if (Util.SDK_INT >= 21) {
            int i3 = this.pendingRotationDegrees;
            if (i3 == 90 || i3 == 270) {
                int i4 = this.currentWidth;
                this.currentWidth = this.currentHeight;
                this.currentHeight = i4;
                this.currentPixelWidthHeightRatio = 1.0f / this.currentPixelWidthHeightRatio;
            }
        } else {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        }
        mediaCodec.setVideoScalingMode(this.scalingMode);
    }

    private void notifyFrameMetadataListener(long j, long j2, Format format, MediaFormat mediaFormat) {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j, j2, format, mediaFormat);
        }
    }

    /* access modifiers changed from: protected */
    public void onProcessedTunneledBuffer(long j) {
        Format updateOutputFormatForTime = updateOutputFormatForTime(j);
        if (updateOutputFormatForTime != null) {
            processOutputFormat(getCodec(), updateOutputFormatForTime.width, updateOutputFormatForTime.height);
        }
        maybeNotifyVideoSizeChanged();
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(j);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onProcessedTunneledEndOfStream() {
        setPendingOutputEndOfStream();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onProcessedOutputBuffer(long j) {
        this.buffersInCodecCount--;
        while (true) {
            int i = this.pendingOutputStreamOffsetCount;
            if (i != 0 && j >= this.pendingOutputStreamSwitchTimesUs[0]) {
                long[] jArr = this.pendingOutputStreamOffsetsUs;
                this.outputStreamOffsetUs = jArr[0];
                int i2 = i - 1;
                this.pendingOutputStreamOffsetCount = i2;
                System.arraycopy(jArr, 1, jArr, 0, i2);
                long[] jArr2 = this.pendingOutputStreamSwitchTimesUs;
                System.arraycopy(jArr2, 1, jArr2, 0, this.pendingOutputStreamOffsetCount);
                clearRenderedFirstFrame();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropOutputBuffer(long j, long j2, boolean z) {
        return isBufferLate(j) && !z;
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropBuffersToKeyframe(long j, long j2, boolean z) {
        return isBufferVeryLate(j) && !z;
    }

    /* access modifiers changed from: protected */
    public boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return isBufferLate(j) && j2 > 100000;
    }

    /* access modifiers changed from: protected */
    public void skipOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    /* access modifiers changed from: protected */
    public void dropOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(1);
    }

    /* access modifiers changed from: protected */
    public boolean maybeDropBuffersToKeyframe(MediaCodec mediaCodec, int i, long j, long j2, boolean z) throws ExoPlaybackException {
        int skipSource = skipSource(j2);
        if (skipSource == 0) {
            return false;
        }
        this.decoderCounters.droppedToKeyframeCount++;
        int i2 = this.buffersInCodecCount + skipSource;
        if (z) {
            this.decoderCounters.skippedOutputBufferCount += i2;
        } else {
            updateDroppedBufferCounters(i2);
        }
        flushOrReinitializeCodec();
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateDroppedBufferCounters(int i) {
        this.decoderCounters.droppedBufferCount += i;
        this.droppedFrames += i;
        this.consecutiveDroppedFrameCount += i;
        this.decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.decoderCounters.maxConsecutiveDroppedBufferCount);
        int i2 = this.maxDroppedFramesToNotify;
        if (i2 > 0 && this.droppedFrames >= i2) {
            maybeNotifyDroppedFrames();
        }
    }

    /* access modifiers changed from: protected */
    public void renderOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    /* access modifiers changed from: protected */
    public void renderOutputBufferV21(MediaCodec mediaCodec, int i, long j, long j2) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j2);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    private boolean shouldUseDummySurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name) && (!mediaCodecInfo.secure || DummySurface.isSecureSupported(this.context));
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : -9223372036854775807L;
    }

    private void clearRenderedFirstFrame() {
        MediaCodec codec;
        this.renderedFirstFrame = false;
        if (Util.SDK_INT >= 23 && this.tunneling && (codec = getCodec()) != null) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeNotifyRenderedFirstFrame() {
        if (!this.renderedFirstFrame) {
            this.renderedFirstFrame = true;
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.renderedFirstFrame) {
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void clearReportedVideoSize() {
        this.reportedWidth = -1;
        this.reportedHeight = -1;
        this.reportedPixelWidthHeightRatio = -1.0f;
        this.reportedUnappliedRotationDegrees = -1;
    }

    private void maybeNotifyVideoSizeChanged() {
        if (this.currentWidth != -1 || this.currentHeight != -1) {
            if (this.reportedWidth != this.currentWidth || this.reportedHeight != this.currentHeight || this.reportedUnappliedRotationDegrees != this.currentUnappliedRotationDegrees || this.reportedPixelWidthHeightRatio != this.currentPixelWidthHeightRatio) {
                this.eventDispatcher.videoSizeChanged(this.currentWidth, this.currentHeight, this.currentUnappliedRotationDegrees, this.currentPixelWidthHeightRatio);
                this.reportedWidth = this.currentWidth;
                this.reportedHeight = this.currentHeight;
                this.reportedUnappliedRotationDegrees = this.currentUnappliedRotationDegrees;
                this.reportedPixelWidthHeightRatio = this.currentPixelWidthHeightRatio;
            }
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        if (this.reportedWidth != -1 || this.reportedHeight != -1) {
            this.eventDispatcher.videoSizeChanged(this.reportedWidth, this.reportedHeight, this.reportedUnappliedRotationDegrees, this.reportedPixelWidthHeightRatio);
        }
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }

    private static void setHdr10PlusInfoV29(MediaCodec mediaCodec, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("hdr10-plus-info", bArr);
        mediaCodec.setParameters(bundle);
    }

    private static void setOutputSurfaceV23(MediaCodec mediaCodec, Surface surface2) {
        mediaCodec.setOutputSurface(surface2);
    }

    private static void configureTunnelingV21(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i);
    }

    /* access modifiers changed from: protected */
    public MediaFormat getMediaFormat(Format format, String str, CodecMaxValues codecMaxValues2, float f, boolean z, int i) {
        Pair<Integer, Integer> codecProfileAndLevel;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        if ("video/dolby-vision".equals(format.sampleMimeType) && (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format)) != null) {
            MediaFormatUtil.maybeSetInteger(mediaFormat, Scopes.PROFILE, ((Integer) codecProfileAndLevel.first).intValue());
        }
        mediaFormat.setInteger("max-width", codecMaxValues2.width);
        mediaFormat.setInteger("max-height", codecMaxValues2.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues2.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (z) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            configureTunnelingV21(mediaFormat, i);
        }
        return mediaFormat;
    }

    /* access modifiers changed from: protected */
    public CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) {
        int codecMaxInputSize;
        int i = format.width;
        int i2 = format.height;
        int maxInputSize = getMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            if (!(maxInputSize == -1 || (codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, format.width, format.height)) == -1)) {
                maxInputSize = Math.min((int) (((float) maxInputSize) * 1.5f), codecMaxInputSize);
            }
            return new CodecMaxValues(i, i2, maxInputSize);
        }
        boolean z = false;
        for (Format format2 : formatArr) {
            if (mediaCodecInfo.isSeamlessAdaptationSupported(format, format2, false)) {
                z |= format2.width == -1 || format2.height == -1;
                i = Math.max(i, format2.width);
                i2 = Math.max(i2, format2.height);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(mediaCodecInfo, format2));
            }
        }
        if (z) {
            Log.w("MediaCodecVideoRenderer", "Resolutions unknown. Codec max resolution: " + i + AvidJSONUtil.KEY_X + i2);
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                i = Math.max(i, codecMaxSize.x);
                i2 = Math.max(i2, codecMaxSize.y);
                maxInputSize = Math.max(maxInputSize, getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, i, i2));
                Log.w("MediaCodecVideoRenderer", "Codec max resolution adjusted to: " + i + AvidJSONUtil.KEY_X + i2);
            }
        }
        return new CodecMaxValues(i, i2, maxInputSize);
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) {
        boolean z = format.height > format.width;
        int i = z ? format.height : format.width;
        int i2 = z ? format.width : format.height;
        float f = ((float) i2) / ((float) i);
        int[] iArr = STANDARD_LONG_EDGE_VIDEO_PX;
        for (int i3 : iArr) {
            int i4 = (int) (((float) i3) * f);
            if (i3 <= i || i4 <= i2) {
                break;
            }
            if (Util.SDK_INT >= 21) {
                int i5 = z ? i4 : i3;
                if (!z) {
                    i3 = i4;
                }
                Point alignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i5, i3);
                if (mediaCodecInfo.isVideoSizeAndRateSupportedV21(alignVideoSizeV21.x, alignVideoSizeV21.y, (double) format.frameRate)) {
                    return alignVideoSizeV21;
                }
            } else {
                try {
                    int ceilDivide = Util.ceilDivide(i3, 16) * 16;
                    int ceilDivide2 = Util.ceilDivide(i4, 16) * 16;
                    if (ceilDivide * ceilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                        int i6 = z ? ceilDivide2 : ceilDivide;
                        if (!z) {
                            ceilDivide = ceilDivide2;
                        }
                        return new Point(i6, ceilDivide);
                    }
                } catch (MediaCodecUtil.DecoderQueryException unused) {
                }
            }
        }
        return null;
    }

    private static int getMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.maxInputSize == -1) {
            return getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, format.width, format.height);
        }
        int size = format.initializationData.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += format.initializationData.get(i2).length;
        }
        return format.maxInputSize + i;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static int getCodecMaxInputSize(MediaCodecInfo mediaCodecInfo, String str, int i, int i2) {
        char c;
        int i3;
        if (i == -1 || i2 == -1) {
            return -1;
        }
        int i4 = 4;
        switch (str.hashCode()) {
            case -1664118616:
                if (str.equals("video/3gpp")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1662541442:
                if (str.equals("video/hevc")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1187890754:
                if (str.equals("video/mp4v-es")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1331836730:
                if (str.equals("video/avc")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1599127256:
                if (str.equals("video/x-vnd.on2.vp8")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1599127257:
                if (str.equals("video/x-vnd.on2.vp9")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (!(c == 0 || c == 1)) {
            if (c != 2) {
                if (c != 3) {
                    if (c != 4 && c != 5) {
                        return -1;
                    }
                    i3 = i * i2;
                    return (i3 * 3) / (i4 * 2);
                }
            } else if ("BRAVIA 4K 2015".equals(Util.MODEL) || ("Amazon".equals(Util.MANUFACTURER) && ("KFSOWI".equals(Util.MODEL) || ("AFTS".equals(Util.MODEL) && mediaCodecInfo.secure)))) {
                return -1;
            } else {
                i3 = Util.ceilDivide(i, 16) * Util.ceilDivide(i2, 16) * 16 * 16;
                i4 = 2;
                return (i3 * 3) / (i4 * 2);
            }
        }
        i3 = i * i2;
        i4 = 2;
        return (i3 * 3) / (i4 * 2);
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:421:0x0655 A[ADDED_TO_REGION] */
    public boolean codecNeedsSetOutputSurfaceWorkaround(String str) {
        char c = 0;
        if (str.startsWith("OMX.google")) {
            return false;
        }
        synchronized (MediaCodecVideoRenderer.class) {
            if (!evaluatedDeviceNeedsSetOutputSurfaceWorkaround) {
                char c2 = 27;
                if (Util.SDK_INT <= 27 && ("dangal".equals(Util.DEVICE) || "HWEML".equals(Util.DEVICE))) {
                    deviceNeedsSetOutputSurfaceWorkaround = true;
                } else if (Util.SDK_INT < 27) {
                    String str2 = Util.DEVICE;
                    switch (str2.hashCode()) {
                        case -2144781245:
                            if (str2.equals("GIONEE_SWW1609")) {
                                c2 = '+';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -2144781185:
                            if (str2.equals("GIONEE_SWW1627")) {
                                c2 = ',';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -2144781160:
                            if (str2.equals("GIONEE_SWW1631")) {
                                c2 = '-';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -2097309513:
                            if (str2.equals("K50a40")) {
                                c2 = '?';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -2022874474:
                            if (str2.equals("CP8676_I02")) {
                                c2 = 19;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1978993182:
                            if (str2.equals("NX541J")) {
                                c2 = 'M';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1978990237:
                            if (str2.equals("NX573J")) {
                                c2 = 'N';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1936688988:
                            if (str2.equals("PGN528")) {
                                c2 = 'X';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1936688066:
                            if (str2.equals("PGN610")) {
                                c2 = 'Y';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1936688065:
                            if (str2.equals("PGN611")) {
                                c2 = 'Z';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1931988508:
                            if (str2.equals("AquaPowerM")) {
                                c2 = 11;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1696512866:
                            if (str2.equals("XT1663")) {
                                c2 = '{';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1680025915:
                            if (str2.equals("ComioS1")) {
                                c2 = 18;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1615810839:
                            if (str2.equals("Phantom6")) {
                                c2 = '[';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1554255044:
                            if (str2.equals("vernee_M5")) {
                                c2 = 't';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1481772737:
                            if (str2.equals("panell_dl")) {
                                c2 = 'T';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1481772730:
                            if (str2.equals("panell_ds")) {
                                c2 = 'U';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1481772729:
                            if (str2.equals("panell_dt")) {
                                c2 = 'V';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1320080169:
                            if (str2.equals("GiONEE_GBL7319")) {
                                c2 = ')';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1217592143:
                            if (str2.equals("BRAVIA_ATV2")) {
                                c2 = 15;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1180384755:
                            if (str2.equals("iris60")) {
                                c2 = ';';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1139198265:
                            if (str2.equals("Slate_Pro")) {
                                c2 = 'h';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1052835013:
                            if (str2.equals("namath")) {
                                c2 = 'K';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -993250464:
                            if (str2.equals("A10-70F")) {
                                c2 = 3;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -993250458:
                            if (str2.equals("A10-70L")) {
                                c2 = 4;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -965403638:
                            if (str2.equals("s905x018")) {
                                c2 = 'j';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -958336948:
                            if (str2.equals("ELUGA_Ray_X")) {
                                c2 = 29;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -879245230:
                            if (str2.equals("tcl_eu")) {
                                c2 = 'p';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -842500323:
                            if (str2.equals("nicklaus_f")) {
                                c2 = 'L';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -821392978:
                            if (str2.equals("A7000-a")) {
                                c2 = 7;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -797483286:
                            if (str2.equals("SVP-DTV15")) {
                                c2 = 'i';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -794946968:
                            if (str2.equals("watson")) {
                                c2 = 'u';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -788334647:
                            if (str2.equals("whyred")) {
                                c2 = 'v';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -782144577:
                            if (str2.equals("OnePlus5T")) {
                                c2 = 'O';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -575125681:
                            if (str2.equals("GiONEE_CBL7513")) {
                                c2 = '(';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -521118391:
                            if (str2.equals("GIONEE_GBL7360")) {
                                c2 = '*';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -430914369:
                            if (str2.equals("Pixi4-7_3G")) {
                                c2 = '\\';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -290434366:
                            if (str2.equals("taido_row")) {
                                c2 = 'k';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -282781963:
                            if (str2.equals("BLACK-1X")) {
                                c2 = 14;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -277133239:
                            if (str2.equals("Z12_PRO")) {
                                c2 = '|';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -173639913:
                            if (str2.equals("ELUGA_A3_Pro")) {
                                c2 = 26;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -56598463:
                            if (str2.equals("woods_fn")) {
                                c2 = 'x';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2126:
                            if (str2.equals("C1")) {
                                c2 = 17;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2564:
                            if (str2.equals("Q5")) {
                                c2 = 'd';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2715:
                            if (str2.equals("V1")) {
                                c2 = 'q';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2719:
                            if (str2.equals("V5")) {
                                c2 = 's';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 3483:
                            if (str2.equals("mh")) {
                                c2 = 'H';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 73405:
                            if (str2.equals("JGZ")) {
                                c2 = '>';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 75739:
                            if (str2.equals("M5c")) {
                                c2 = 'D';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 76779:
                            if (str2.equals("MX6")) {
                                c2 = 'J';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 78669:
                            if (str2.equals("P85")) {
                                c2 = 'R';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 79305:
                            if (str2.equals("PLE")) {
                                c2 = '^';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 80618:
                            if (str2.equals("QX1")) {
                                c2 = 'f';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 88274:
                            if (str2.equals("Z80")) {
                                c2 = '}';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 98846:
                            if (str2.equals("cv1")) {
                                c2 = 22;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 98848:
                            if (str2.equals("cv3")) {
                                c2 = 23;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 99329:
                            if (str2.equals("deb")) {
                                c2 = 24;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 101481:
                            if (str2.equals("flo")) {
                                c2 = '&';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1513190:
                            if (str2.equals("1601")) {
                                c2 = 0;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1514184:
                            if (str2.equals("1713")) {
                                c2 = 1;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1514185:
                            if (str2.equals("1714")) {
                                c2 = 2;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2436959:
                            if (str2.equals("P681")) {
                                c2 = 'Q';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2463773:
                            if (str2.equals("Q350")) {
                                c2 = '`';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2464648:
                            if (str2.equals("Q427")) {
                                c2 = 'b';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2689555:
                            if (str2.equals("XE2X")) {
                                c2 = 'z';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 3154429:
                            if (str2.equals("fugu")) {
                                c2 = '\'';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 3284551:
                            if (str2.equals("kate")) {
                                c2 = '@';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 3351335:
                            if (str2.equals("mido")) {
                                c2 = 'I';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 3386211:
                            if (str2.equals("p212")) {
                                c2 = 'P';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 41325051:
                            if (str2.equals("MEIZU_M5")) {
                                c2 = 'G';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 55178625:
                            if (str2.equals("Aura_Note_2")) {
                                c2 = '\r';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 61542055:
                            if (str2.equals("A1601")) {
                                c2 = 5;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 65355429:
                            if (str2.equals("E5643")) {
                                c2 = 25;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 66214468:
                            if (str2.equals("F3111")) {
                                c2 = 31;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 66214470:
                            if (str2.equals("F3113")) {
                                c2 = ' ';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 66214473:
                            if (str2.equals("F3116")) {
                                c2 = '!';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 66215429:
                            if (str2.equals("F3211")) {
                                c2 = '\"';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 66215431:
                            if (str2.equals("F3213")) {
                                c2 = '#';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 66215433:
                            if (str2.equals("F3215")) {
                                c2 = '$';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 66216390:
                            if (str2.equals("F3311")) {
                                c2 = '%';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 76402249:
                            if (str2.equals("PRO7S")) {
                                c2 = '_';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 76404105:
                            if (str2.equals("Q4260")) {
                                c2 = 'a';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 76404911:
                            if (str2.equals("Q4310")) {
                                c2 = 'c';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 80963634:
                            if (str2.equals("V23GB")) {
                                c2 = 'r';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 82882791:
                            if (str2.equals("X3_HK")) {
                                c2 = 'y';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 98715550:
                            if (str2.equals("i9031")) {
                                c2 = '8';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 101370885:
                            if (str2.equals("l5460")) {
                                c2 = 'A';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 102844228:
                            if (str2.equals("le_x6")) {
                                c2 = 'B';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 165221241:
                            if (str2.equals("A2016a40")) {
                                c2 = 6;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 182191441:
                            if (str2.equals("CPY83_I00")) {
                                c2 = 21;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 245388979:
                            if (str2.equals("marino_f")) {
                                c2 = 'F';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 287431619:
                            if (str2.equals("griffin")) {
                                c2 = '1';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 307593612:
                            if (str2.equals("A7010a48")) {
                                c2 = '\t';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 308517133:
                            if (str2.equals("A7020a48")) {
                                c2 = '\n';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 316215098:
                            if (str2.equals("TB3-730F")) {
                                c2 = 'l';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 316215116:
                            if (str2.equals("TB3-730X")) {
                                c2 = 'm';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 316246811:
                            if (str2.equals("TB3-850F")) {
                                c2 = 'n';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 316246818:
                            if (str2.equals("TB3-850M")) {
                                c2 = 'o';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 407160593:
                            if (str2.equals("Pixi5-10_4G")) {
                                c2 = ']';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 507412548:
                            if (str2.equals("QM16XE_U")) {
                                c2 = 'e';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 793982701:
                            if (str2.equals("GIONEE_WBL5708")) {
                                c2 = '.';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 794038622:
                            if (str2.equals("GIONEE_WBL7365")) {
                                c2 = '/';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 794040393:
                            if (str2.equals("GIONEE_WBL7519")) {
                                c2 = '0';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 835649806:
                            if (str2.equals("manning")) {
                                c2 = 'E';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 917340916:
                            if (str2.equals("A7000plus")) {
                                c2 = '\b';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 958008161:
                            if (str2.equals("j2xlteins")) {
                                c2 = '=';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1060579533:
                            if (str2.equals("panell_d")) {
                                c2 = 'S';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1150207623:
                            if (str2.equals("LS-5017")) {
                                c2 = 'C';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1176899427:
                            if (str2.equals("itel_S41")) {
                                c2 = '<';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1280332038:
                            if (str2.equals("hwALE-H")) {
                                c2 = '3';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1306947716:
                            if (str2.equals("EverStar_S")) {
                                c2 = 30;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1349174697:
                            if (str2.equals("htc_e56ml_dtul")) {
                                c2 = '2';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1522194893:
                            if (str2.equals("woods_f")) {
                                c2 = 'w';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1691543273:
                            if (str2.equals("CPH1609")) {
                                c2 = 20;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1709443163:
                            if (str2.equals("iball8735_9806")) {
                                c2 = '9';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1865889110:
                            if (str2.equals("santoni")) {
                                c2 = 'g';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1906253259:
                            if (str2.equals("PB2-670M")) {
                                c2 = 'W';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1977196784:
                            if (str2.equals("Infinix-X572")) {
                                c2 = ':';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2006372676:
                            if (str2.equals("BRAVIA_ATV3_4K")) {
                                c2 = 16;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2029784656:
                            if (str2.equals("HWBLN-H")) {
                                c2 = '4';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2030379515:
                            if (str2.equals("HWCAM-H")) {
                                c2 = '5';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2033393791:
                            if (str2.equals("ASUS_X00AD_2")) {
                                c2 = '\f';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2047190025:
                            if (str2.equals("ELUGA_Note")) {
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2047252157:
                            if (str2.equals("ELUGA_Prim")) {
                                c2 = 28;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2048319463:
                            if (str2.equals("HWVNS-H")) {
                                c2 = '6';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 2048855701:
                            if (str2.equals("HWWAS-H")) {
                                c2 = '7';
                                break;
                            }
                            c2 = 65535;
                            break;
                        default:
                            c2 = 65535;
                            break;
                    }
                    switch (c2) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case '\b':
                        case '\t':
                        case '\n':
                        case 11:
                        case '\f':
                        case '\r':
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case ' ':
                        case '!':
                        case '\"':
                        case '#':
                        case '$':
                        case '%':
                        case '&':
                        case '\'':
                        case '(':
                        case ')':
                        case '*':
                        case '+':
                        case ',':
                        case '-':
                        case '.':
                        case '/':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                        case ':':
                        case ';':
                        case '<':
                        case '=':
                        case '>':
                        case '?':
                        case '@':
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                        case 'G':
                        case 'H':
                        case 'I':
                        case 'J':
                        case 'K':
                        case 'L':
                        case 'M':
                        case 'N':
                        case 'O':
                        case 'P':
                        case 'Q':
                        case 'R':
                        case 'S':
                        case 'T':
                        case 'U':
                        case 'V':
                        case 'W':
                        case 'X':
                        case 'Y':
                        case 'Z':
                        case '[':
                        case '\\':
                        case ']':
                        case '^':
                        case '_':
                        case '`':
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                        case 'g':
                        case 'h':
                        case 'i':
                        case 'j':
                        case 'k':
                        case 'l':
                        case 'm':
                        case 'n':
                        case 'o':
                        case 'p':
                        case 'q':
                        case 'r':
                        case 's':
                        case 't':
                        case 'u':
                        case 'v':
                        case 'w':
                        case 'x':
                        case 'y':
                        case 'z':
                        case '{':
                        case '|':
                        case '}':
                            deviceNeedsSetOutputSurfaceWorkaround = true;
                            break;
                    }
                    String str3 = Util.MODEL;
                    int hashCode = str3.hashCode();
                    if (hashCode != -594534941) {
                        if (hashCode != 2006354) {
                            if (hashCode == 2006367) {
                                if (str3.equals("AFTN")) {
                                    c = 1;
                                    if (c != 0 || c == 1 || c == 2) {
                                        deviceNeedsSetOutputSurfaceWorkaround = true;
                                    }
                                }
                            }
                        } else if (str3.equals("AFTA")) {
                            if (c != 0) {
                            }
                            deviceNeedsSetOutputSurfaceWorkaround = true;
                        }
                    } else if (str3.equals("JSN-L21")) {
                        c = 2;
                        if (c != 0) {
                        }
                        deviceNeedsSetOutputSurfaceWorkaround = true;
                    }
                    c = 65535;
                    if (c != 0) {
                    }
                    deviceNeedsSetOutputSurfaceWorkaround = true;
                }
                evaluatedDeviceNeedsSetOutputSurfaceWorkaround = true;
            }
        }
        return deviceNeedsSetOutputSurfaceWorkaround;
    }

    /* access modifiers changed from: protected */
    public static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    /* access modifiers changed from: private */
    public final class OnFrameRenderedListenerV23 implements MediaCodec.OnFrameRenderedListener {
        private OnFrameRenderedListenerV23(MediaCodec mediaCodec) {
            mediaCodec.setOnFrameRenderedListener(this, new Handler());
        }

        public void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
            if (this == MediaCodecVideoRenderer.this.tunnelingOnFrameRenderedListener) {
                if (j == Long.MAX_VALUE) {
                    MediaCodecVideoRenderer.this.onProcessedTunneledEndOfStream();
                } else {
                    MediaCodecVideoRenderer.this.onProcessedTunneledBuffer(j);
                }
            }
        }
    }
}
