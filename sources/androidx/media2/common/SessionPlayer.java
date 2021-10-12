package androidx.media2.common;

import android.media.MediaFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import androidx.core.util.Pair;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.futures.ResolvableFuture;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

public abstract class SessionPlayer implements AutoCloseable {
    private final List<Pair<PlayerCallback, Executor>> mCallbacks = new ArrayList();
    private final Object mLock = new Object();

    public static abstract class PlayerCallback {
        public void onAudioAttributesChanged(SessionPlayer sessionPlayer, AudioAttributesCompat audioAttributesCompat) {
        }

        public void onBufferingStateChanged(SessionPlayer sessionPlayer, MediaItem mediaItem, int i) {
        }

        public void onCurrentMediaItemChanged(SessionPlayer sessionPlayer, MediaItem mediaItem) {
        }

        public void onPlaybackCompleted(SessionPlayer sessionPlayer) {
        }

        public void onPlaybackSpeedChanged(SessionPlayer sessionPlayer, float f) {
        }

        public void onPlayerStateChanged(SessionPlayer sessionPlayer, int i) {
        }

        public void onPlaylistChanged(SessionPlayer sessionPlayer, List<MediaItem> list, MediaMetadata mediaMetadata) {
        }

        public void onPlaylistMetadataChanged(SessionPlayer sessionPlayer, MediaMetadata mediaMetadata) {
        }

        public void onRepeatModeChanged(SessionPlayer sessionPlayer, int i) {
        }

        public void onSeekCompleted(SessionPlayer sessionPlayer, long j) {
        }

        public void onShuffleModeChanged(SessionPlayer sessionPlayer, int i) {
        }

        public void onSubtitleData(SessionPlayer sessionPlayer, MediaItem mediaItem, TrackInfo trackInfo, SubtitleData subtitleData) {
        }

        public void onTrackDeselected(SessionPlayer sessionPlayer, TrackInfo trackInfo) {
        }

        public void onTrackInfoChanged(SessionPlayer sessionPlayer, List<TrackInfo> list) {
        }

        public void onTrackSelected(SessionPlayer sessionPlayer, TrackInfo trackInfo) {
        }

        public void onVideoSizeChangedInternal(SessionPlayer sessionPlayer, MediaItem mediaItem, VideoSize videoSize) {
        }
    }

    public abstract long getBufferedPosition();

    public abstract MediaItem getCurrentMediaItem();

    public abstract long getCurrentPosition();

    public abstract long getDuration();

    public abstract int getNextMediaItemIndex();

    public abstract float getPlaybackSpeed();

    public abstract int getPlayerState();

    public abstract int getPreviousMediaItemIndex();

    public abstract ListenableFuture<PlayerResult> pause();

    public abstract ListenableFuture<PlayerResult> play();

    public abstract ListenableFuture<PlayerResult> seekTo(long j);

    public abstract ListenableFuture<PlayerResult> setPlaybackSpeed(float f);

    public abstract ListenableFuture<PlayerResult> skipToNextPlaylistItem();

    public abstract ListenableFuture<PlayerResult> skipToPreviousPlaylistItem();

    public VideoSize getVideoSizeInternal() {
        throw new UnsupportedOperationException("getVideoSizeInternal is internal use only");
    }

    public ListenableFuture<PlayerResult> setSurfaceInternal(Surface surface) {
        return PlayerResult.createFuture(-6);
    }

    public final void registerPlayerCallback(Executor executor, PlayerCallback playerCallback) {
        if (executor == null) {
            throw new NullPointerException("executor shouldn't be null");
        } else if (playerCallback != null) {
            synchronized (this.mLock) {
                for (Pair<PlayerCallback, Executor> pair : this.mCallbacks) {
                    if (pair.first == playerCallback && pair.second != null) {
                        Log.w("SessionPlayer", "callback is already added. Ignoring.");
                        return;
                    }
                }
                this.mCallbacks.add(new Pair<>(playerCallback, executor));
            }
        } else {
            throw new NullPointerException("callback shouldn't be null");
        }
    }

    public final void unregisterPlayerCallback(PlayerCallback playerCallback) {
        if (playerCallback != null) {
            synchronized (this.mLock) {
                for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
                    if (this.mCallbacks.get(size).first == playerCallback) {
                        this.mCallbacks.remove(size);
                    }
                }
            }
            return;
        }
        throw new NullPointerException("callback shouldn't be null");
    }

    /* access modifiers changed from: protected */
    public final List<Pair<PlayerCallback, Executor>> getCallbacks() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mLock) {
            arrayList.addAll(this.mCallbacks);
        }
        return arrayList;
    }

    public List<TrackInfo> getTrackInfoInternal() {
        throw new UnsupportedOperationException("getTrackInfoInternal is for internal use only");
    }

    public ListenableFuture<PlayerResult> selectTrackInternal(TrackInfo trackInfo) {
        throw new UnsupportedOperationException("selectTrackInternal is for internal use only");
    }

    public ListenableFuture<PlayerResult> deselectTrackInternal(TrackInfo trackInfo) {
        throw new UnsupportedOperationException("deselectTrackInternal is for internal use only");
    }

    public TrackInfo getSelectedTrackInternal(int i) {
        throw new UnsupportedOperationException("getSelectedTrackInternal is for internal use only.");
    }

    public static final class TrackInfo extends CustomVersionedParcelable {
        MediaFormat mFormat;
        int mId;
        MediaItem mMediaItem;
        Bundle mParcelledFormat;
        int mTrackType;
        MediaItem mUpCastMediaItem;

        TrackInfo() {
        }

        public TrackInfo(int i, MediaItem mediaItem, int i2, MediaFormat mediaFormat) {
            this.mId = i;
            this.mMediaItem = mediaItem;
            this.mTrackType = i2;
            this.mFormat = mediaFormat;
        }

        public int getTrackType() {
            return this.mTrackType;
        }

        public Locale getLanguage() {
            MediaFormat mediaFormat = this.mFormat;
            String string = mediaFormat != null ? mediaFormat.getString("language") : null;
            if (string == null) {
                string = "und";
            }
            return new Locale(string);
        }

        public MediaFormat getFormat() {
            if (this.mTrackType == 4) {
                return this.mFormat;
            }
            return null;
        }

        public int getId() {
            return this.mId;
        }

        public MediaItem getMediaItem() {
            return this.mMediaItem;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append(getClass().getName());
            sb.append(", id: ");
            sb.append(this.mId);
            sb.append(", MediaItem: " + this.mMediaItem);
            sb.append(", TrackType: ");
            int i = this.mTrackType;
            if (i == 1) {
                sb.append("VIDEO");
            } else if (i == 2) {
                sb.append("AUDIO");
            } else if (i != 4) {
                sb.append("UNKNOWN");
            } else {
                sb.append("SUBTITLE");
            }
            sb.append(", Format: " + this.mFormat);
            return sb.toString();
        }

        public int hashCode() {
            int i;
            int i2 = this.mId + 31;
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem != null) {
                i = mediaItem.getMediaId() != null ? this.mMediaItem.getMediaId().hashCode() : this.mMediaItem.hashCode();
            } else {
                i = 0;
            }
            return (i2 * 31) + i;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0078, code lost:
            if (intEquals("is-default", r5.mFormat, r6.mFormat) == false) goto L_0x00a7;
         */
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TrackInfo trackInfo = (TrackInfo) obj;
            if (this.mId != trackInfo.mId || this.mTrackType != trackInfo.mTrackType) {
                return false;
            }
            if (!(this.mFormat == null && trackInfo.mFormat == null)) {
                if (this.mFormat == null && trackInfo.mFormat != null) {
                    return false;
                }
                if (this.mFormat != null) {
                    if (trackInfo.mFormat == null) {
                        return false;
                    }
                }
                if (stringEquals("language", this.mFormat, trackInfo.mFormat)) {
                    if (stringEquals("mime", this.mFormat, trackInfo.mFormat)) {
                        if (intEquals("is-forced-subtitle", this.mFormat, trackInfo.mFormat)) {
                            if (intEquals("is-autoselect", this.mFormat, trackInfo.mFormat)) {
                            }
                        }
                    }
                }
                return false;
            }
            if (this.mMediaItem == null && trackInfo.mMediaItem == null) {
                return true;
            }
            MediaItem mediaItem = this.mMediaItem;
            if (!(mediaItem == null || trackInfo.mMediaItem == null)) {
                String mediaId = mediaItem.getMediaId();
                if (mediaId != null) {
                    return mediaId.equals(trackInfo.mMediaItem.getMediaId());
                }
                return this.mMediaItem.equals(trackInfo.mMediaItem);
            }
            return false;
        }

        @Override // androidx.versionedparcelable.CustomVersionedParcelable
        public void onPreParceling(boolean z) {
            if (this.mFormat != null) {
                this.mParcelledFormat = new Bundle();
                parcelStringValue("language");
                parcelStringValue("mime");
                parcelIntValue("is-forced-subtitle");
                parcelIntValue("is-autoselect");
                parcelIntValue("is-default");
            }
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem != null && this.mUpCastMediaItem == null) {
                this.mUpCastMediaItem = new MediaItem(mediaItem);
            }
        }

        @Override // androidx.versionedparcelable.CustomVersionedParcelable
        public void onPostParceling() {
            if (this.mParcelledFormat != null) {
                this.mFormat = new MediaFormat();
                unparcelStringValue("language");
                unparcelStringValue("mime");
                unparcelIntValue("is-forced-subtitle");
                unparcelIntValue("is-autoselect");
                unparcelIntValue("is-default");
            }
            if (this.mMediaItem == null) {
                this.mMediaItem = this.mUpCastMediaItem;
            }
        }

        private boolean stringEquals(String str, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
            return TextUtils.equals(mediaFormat.getString(str), mediaFormat2.getString(str));
        }

        private boolean intEquals(String str, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
            boolean containsKey = mediaFormat.containsKey(str);
            boolean containsKey2 = mediaFormat2.containsKey(str);
            if (!containsKey || !containsKey2) {
                if (containsKey || containsKey2) {
                    return false;
                }
                return true;
            } else if (mediaFormat.getInteger(str) == mediaFormat2.getInteger(str)) {
                return true;
            } else {
                return false;
            }
        }

        private void parcelIntValue(String str) {
            if (this.mFormat.containsKey(str)) {
                this.mParcelledFormat.putInt(str, this.mFormat.getInteger(str));
            }
        }

        private void parcelStringValue(String str) {
            if (this.mFormat.containsKey(str)) {
                this.mParcelledFormat.putString(str, this.mFormat.getString(str));
            }
        }

        private void unparcelIntValue(String str) {
            if (this.mParcelledFormat.containsKey(str)) {
                this.mFormat.setInteger(str, this.mParcelledFormat.getInt(str));
            }
        }

        private void unparcelStringValue(String str) {
            if (this.mParcelledFormat.containsKey(str)) {
                this.mFormat.setString(str, this.mParcelledFormat.getString(str));
            }
        }
    }

    public static class PlayerResult implements BaseResult {
        private final long mCompletionTime;
        private final MediaItem mItem;
        private final int mResultCode;

        public PlayerResult(int i, MediaItem mediaItem) {
            this(i, mediaItem, SystemClock.elapsedRealtime());
        }

        private PlayerResult(int i, MediaItem mediaItem, long j) {
            this.mResultCode = i;
            this.mItem = mediaItem;
            this.mCompletionTime = j;
        }

        public static ListenableFuture<PlayerResult> createFuture(int i) {
            ResolvableFuture create = ResolvableFuture.create();
            create.set(new PlayerResult(i, null));
            return create;
        }

        @Override // androidx.media2.common.BaseResult
        public int getResultCode() {
            return this.mResultCode;
        }
    }
}
