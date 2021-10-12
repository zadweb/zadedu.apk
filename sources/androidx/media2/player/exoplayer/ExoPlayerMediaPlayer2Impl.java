package androidx.media2.player.exoplayer;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.SubtitleData;
import androidx.media2.player.MediaPlayer2;
import androidx.media2.player.MediaTimestamp;
import androidx.media2.player.PlaybackParams;
import androidx.media2.player.TimedMetaData;
import androidx.media2.player.exoplayer.ExoPlayerWrapper;
import androidx.media2.player.futures.ResolvableFuture;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public final class ExoPlayerMediaPlayer2Impl extends MediaPlayer2 implements ExoPlayerWrapper.Listener {
    Task mCurrentTask;
    private Pair<Executor, MediaPlayer2.DrmEventCallback> mExecutorAndDrmEventCallback;
    private Pair<Executor, MediaPlayer2.EventCallback> mExecutorAndEventCallback;
    private HandlerThread mHandlerThread;
    final Object mLock = new Object();
    final ArrayDeque<Task> mPendingTasks = new ArrayDeque<>();
    final ExoPlayerWrapper mPlayer;
    private final Handler mTaskHandler = new Handler(this.mPlayer.getLooper());
    final Object mTaskLock = new Object();

    /* access modifiers changed from: private */
    public interface Mp2EventNotifier {
        void notify(MediaPlayer2.EventCallback eventCallback);
    }

    public ExoPlayerMediaPlayer2Impl(Context context) {
        HandlerThread handlerThread = new HandlerThread("ExoMediaPlayer2Thread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mPlayer = new ExoPlayerWrapper(context.getApplicationContext(), this, this.mHandlerThread.getLooper());
        resetPlayer();
    }

    public void clearPendingCommands() {
        synchronized (this.mTaskLock) {
            this.mPendingTasks.clear();
        }
    }

    @Override // androidx.media2.player.MediaPlayer2
    public boolean cancel(Object obj) {
        boolean remove;
        synchronized (this.mTaskLock) {
            remove = this.mPendingTasks.remove(obj);
        }
        return remove;
    }

    private Object addTask(Task task) {
        synchronized (this.mTaskLock) {
            this.mPendingTasks.add(task);
            processPendingTask();
        }
        return task;
    }

    /* access modifiers changed from: package-private */
    public void processPendingTask() {
        if (this.mCurrentTask == null && !this.mPendingTasks.isEmpty()) {
            Task removeFirst = this.mPendingTasks.removeFirst();
            this.mCurrentTask = removeFirst;
            this.mTaskHandler.post(removeFirst);
        }
    }

    @Override // androidx.media2.player.MediaPlayer2
    public void setEventCallback(Executor executor, MediaPlayer2.EventCallback eventCallback) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(eventCallback);
        synchronized (this.mLock) {
            this.mExecutorAndEventCallback = Pair.create(executor, eventCallback);
        }
    }

    public void clearEventCallback() {
        synchronized (this.mLock) {
            this.mExecutorAndEventCallback = null;
        }
    }

    @Override // androidx.media2.player.MediaPlayer2
    public void setDrmEventCallback(Executor executor, MediaPlayer2.DrmEventCallback drmEventCallback) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(drmEventCallback);
        synchronized (this.mLock) {
            this.mExecutorAndDrmEventCallback = Pair.create(executor, drmEventCallback);
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyMediaPlayer2Event(final Mp2EventNotifier mp2EventNotifier) {
        Pair<Executor, MediaPlayer2.EventCallback> pair;
        synchronized (this.mLock) {
            pair = this.mExecutorAndEventCallback;
        }
        if (pair != null) {
            Executor executor = (Executor) pair.first;
            final MediaPlayer2.EventCallback eventCallback = (MediaPlayer2.EventCallback) pair.second;
            try {
                executor.execute(new Runnable() {
                    /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass2 */

                    public void run() {
                        mp2EventNotifier.notify(eventCallback);
                    }
                });
            } catch (RejectedExecutionException unused) {
                Log.w("ExoPlayerMediaPlayer2", "The given executor is shutting down. Ignoring the player event.");
            }
        }
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object setMediaItem(final MediaItem mediaItem) {
        return addTask(new Task(19, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass4 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setMediaItem(mediaItem);
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public MediaItem getCurrentMediaItem() {
        return (MediaItem) runPlayerCallableBlocking(new Callable<MediaItem>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass5 */

            @Override // java.util.concurrent.Callable
            public MediaItem call() throws Exception {
                return ExoPlayerMediaPlayer2Impl.this.mPlayer.getCurrentMediaItem();
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object prepare() {
        return addTask(new Task(6, true) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass6 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.prepare();
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object play() {
        return addTask(new Task(5, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass7 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.play();
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object pause() {
        return addTask(new Task(4, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass8 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.pause();
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object seekTo(final long j, final int i) {
        return addTask(new Task(14, true) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass9 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.seekTo(j, i);
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public long getCurrentPosition() {
        return ((Long) runPlayerCallableBlocking(new Callable<Long>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass10 */

            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                return Long.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getCurrentPosition());
            }
        })).longValue();
    }

    @Override // androidx.media2.player.MediaPlayer2
    public long getDuration() {
        return ((Long) runPlayerCallableBlocking(new Callable<Long>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass11 */

            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                return Long.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getDuration());
            }
        })).longValue();
    }

    @Override // androidx.media2.player.MediaPlayer2
    public long getBufferedPosition() {
        return ((Long) runPlayerCallableBlocking(new Callable<Long>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass12 */

            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                return Long.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getBufferedPosition());
            }
        })).longValue();
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object skipToNext() {
        return addTask(new Task(29, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass15 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.skipToNext();
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object setNextMediaItem(final MediaItem mediaItem) {
        return addTask(new Task(22, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass16 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setNextMediaItem(mediaItem);
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object setAudioAttributes(final AudioAttributesCompat audioAttributesCompat) {
        return addTask(new Task(16, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass18 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setAudioAttributes(audioAttributesCompat);
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public AudioAttributesCompat getAudioAttributes() {
        return (AudioAttributesCompat) runPlayerCallableBlocking(new Callable<AudioAttributesCompat>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass19 */

            @Override // java.util.concurrent.Callable
            public AudioAttributesCompat call() throws Exception {
                return ExoPlayerMediaPlayer2Impl.this.mPlayer.getAudioAttributes();
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object setPlaybackParams(final PlaybackParams playbackParams) {
        return addTask(new Task(24, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass23 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setPlaybackParams(playbackParams);
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public PlaybackParams getPlaybackParams() {
        return (PlaybackParams) runPlayerCallableBlocking(new Callable<PlaybackParams>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass24 */

            @Override // java.util.concurrent.Callable
            public PlaybackParams call() throws Exception {
                return ExoPlayerMediaPlayer2Impl.this.mPlayer.getPlaybackParams();
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public int getVideoWidth() {
        return ((Integer) runPlayerCallableBlocking(new Callable<Integer>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass25 */

            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                return Integer.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getVideoWidth());
            }
        })).intValue();
    }

    @Override // androidx.media2.player.MediaPlayer2
    public int getVideoHeight() {
        return ((Integer) runPlayerCallableBlocking(new Callable<Integer>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass26 */

            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                return Integer.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getVideoHeight());
            }
        })).intValue();
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object setSurface(final Surface surface) {
        return addTask(new Task(27, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass27 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setSurface(surface);
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object setPlayerVolume(final float f) {
        return addTask(new Task(26, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass28 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setVolume(f);
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public float getPlayerVolume() {
        return ((Float) runPlayerCallableBlocking(new Callable<Float>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass29 */

            @Override // java.util.concurrent.Callable
            public Float call() throws Exception {
                return Float.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getVolume());
            }
        })).floatValue();
    }

    @Override // androidx.media2.player.MediaPlayer2
    public List<MediaPlayer2.TrackInfo> getTrackInfo() {
        return (List) runPlayerCallableBlocking(new Callable<List<MediaPlayer2.TrackInfo>>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass30 */

            @Override // java.util.concurrent.Callable
            public List<MediaPlayer2.TrackInfo> call() throws Exception {
                return ExoPlayerMediaPlayer2Impl.this.mPlayer.getTrackInfo();
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public int getSelectedTrack(final int i) {
        return ((Integer) runPlayerCallableBlocking(new Callable<Integer>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass31 */

            @Override // java.util.concurrent.Callable
            public Integer call() {
                return Integer.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getSelectedTrack(i));
            }
        })).intValue();
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object selectTrack(final int i) {
        return addTask(new Task(15, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass32 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.selectTrack(i);
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public Object deselectTrack(final int i) {
        return addTask(new Task(2, false) {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass33 */

            /* access modifiers changed from: package-private */
            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.deselectTrack(i);
            }
        });
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0016 */
    @Override // androidx.media2.player.MediaPlayer2
    public void reset() {
        Task task;
        clearPendingCommands();
        synchronized (this.mTaskLock) {
            task = this.mCurrentTask;
        }
        if (task != null) {
            synchronized (task) {
                while (true) {
                    if (!task.mDone) {
                        task.wait();
                    }
                }
            }
            break;
        }
        this.mTaskHandler.removeCallbacksAndMessages(null);
        runPlayerCallableBlocking(new Callable<Void>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass36 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.reset();
                return null;
            }
        });
    }

    @Override // androidx.media2.player.MediaPlayer2
    public void close() {
        clearEventCallback();
        synchronized (this.mLock) {
            HandlerThread handlerThread = this.mHandlerThread;
            if (handlerThread != null) {
                this.mHandlerThread = null;
                runPlayerCallableBlocking(new Callable<Void>() {
                    /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass37 */

                    @Override // java.util.concurrent.Callable
                    public Void call() {
                        ExoPlayerMediaPlayer2Impl.this.mPlayer.close();
                        return null;
                    }
                });
                handlerThread.quit();
            }
        }
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onPrepared(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 100);
        synchronized (this.mTaskLock) {
            if (this.mCurrentTask != null && this.mCurrentTask.mMediaCallType == 6 && ObjectsCompat.equals(this.mCurrentTask.mDSD, mediaItem) && this.mCurrentTask.mNeedToWaitForEventToComplete) {
                this.mCurrentTask.sendCompleteNotification(0);
                this.mCurrentTask = null;
                processPendingTask();
            }
        }
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onMetadataChanged(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 802);
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onSeekCompleted() {
        synchronized (this.mTaskLock) {
            if (this.mCurrentTask != null && this.mCurrentTask.mMediaCallType == 14 && this.mCurrentTask.mNeedToWaitForEventToComplete) {
                this.mCurrentTask.sendCompleteNotification(0);
                this.mCurrentTask = null;
                processPendingTask();
            }
        }
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onBufferingStarted(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 701);
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onBufferingEnded(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 702);
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onBufferingUpdate(MediaItem mediaItem, int i) {
        notifyOnInfo(mediaItem, 704, i);
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onBandwidthSample(MediaItem mediaItem, int i) {
        notifyOnInfo(mediaItem, 703, i);
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onVideoRenderingStart(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 3);
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onVideoSizeChanged(final MediaItem mediaItem, final int i, final int i2) {
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass38 */

            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Mp2EventNotifier
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onVideoSizeChanged(ExoPlayerMediaPlayer2Impl.this, mediaItem, i, i2);
            }
        });
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onSubtitleData(final MediaItem mediaItem, final int i, final SubtitleData subtitleData) {
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass39 */

            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Mp2EventNotifier
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onSubtitleData(ExoPlayerMediaPlayer2Impl.this, mediaItem, i, subtitleData);
            }
        });
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onTimedMetadata(final MediaItem mediaItem, final TimedMetaData timedMetaData) {
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass40 */

            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Mp2EventNotifier
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onTimedMetaDataAvailable(ExoPlayerMediaPlayer2Impl.this, mediaItem, timedMetaData);
            }
        });
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onMediaItemStartedAsNext(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 2);
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onMediaItemEnded(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 5);
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onLoop(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 7);
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onMediaTimeDiscontinuity(final MediaItem mediaItem, final MediaTimestamp mediaTimestamp) {
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass41 */

            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Mp2EventNotifier
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onMediaTimeDiscontinuity(ExoPlayerMediaPlayer2Impl.this, mediaItem, mediaTimestamp);
            }
        });
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onPlaybackEnded(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 6);
    }

    @Override // androidx.media2.player.exoplayer.ExoPlayerWrapper.Listener
    public void onError(final MediaItem mediaItem, final int i) {
        synchronized (this.mTaskLock) {
            if (this.mCurrentTask != null && this.mCurrentTask.mNeedToWaitForEventToComplete) {
                this.mCurrentTask.sendCompleteNotification(RecyclerView.UNDEFINED_DURATION);
                this.mCurrentTask = null;
                processPendingTask();
            }
        }
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass42 */

            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Mp2EventNotifier
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onError(ExoPlayerMediaPlayer2Impl.this, mediaItem, i, 0);
            }
        });
    }

    private void notifyOnInfo(MediaItem mediaItem, int i) {
        notifyOnInfo(mediaItem, i, 0);
    }

    private void notifyOnInfo(final MediaItem mediaItem, final int i, final int i2) {
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass43 */

            @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Mp2EventNotifier
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onInfo(ExoPlayerMediaPlayer2Impl.this, mediaItem, i, i2);
            }
        });
    }

    private void resetPlayer() {
        runPlayerCallableBlocking(new Callable<Void>() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass44 */

            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.reset();
                return null;
            }
        });
    }

    private <T> T runPlayerCallableBlocking(final Callable<T> callable) {
        T t;
        final ResolvableFuture create = ResolvableFuture.create();
        Preconditions.checkState(this.mTaskHandler.post(new Runnable() {
            /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.AnonymousClass45 */

            public void run() {
                try {
                    create.set(callable.call());
                } catch (Throwable th) {
                    create.setException(th);
                }
            }
        }));
        boolean z = false;
        while (true) {
            try {
                t = (T) create.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            try {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                Log.e("ExoPlayerMediaPlayer2", "Internal player error", new RuntimeException(cause));
                throw new IllegalStateException(cause);
            }
        }
        return t;
    }

    /* access modifiers changed from: private */
    public abstract class Task implements Runnable {
        MediaItem mDSD;
        boolean mDone;
        final int mMediaCallType;
        final boolean mNeedToWaitForEventToComplete;

        /* access modifiers changed from: package-private */
        public abstract void process() throws IOException, MediaPlayer2.NoDrmSchemeException;

        Task(int i, boolean z) {
            this.mMediaCallType = i;
            this.mNeedToWaitForEventToComplete = z;
        }

        public void run() {
            boolean z;
            int i = 0;
            if (this.mMediaCallType == 14) {
                synchronized (ExoPlayerMediaPlayer2Impl.this.mTaskLock) {
                    Task peekFirst = ExoPlayerMediaPlayer2Impl.this.mPendingTasks.peekFirst();
                    z = peekFirst != null && peekFirst.mMediaCallType == 14;
                }
            } else {
                z = false;
            }
            if (!z) {
                try {
                    if (this.mMediaCallType == 1000 || !ExoPlayerMediaPlayer2Impl.this.mPlayer.hasError()) {
                        process();
                    } else {
                        i = 1;
                    }
                } catch (IllegalStateException unused) {
                } catch (IllegalArgumentException unused2) {
                    i = 2;
                } catch (SecurityException unused3) {
                    i = 3;
                } catch (IOException unused4) {
                    i = 4;
                } catch (Exception unused5) {
                    i = RecyclerView.UNDEFINED_DURATION;
                }
            } else {
                i = 5;
            }
            this.mDSD = ExoPlayerMediaPlayer2Impl.this.mPlayer.getCurrentMediaItem();
            if (!this.mNeedToWaitForEventToComplete || i != 0 || z) {
                sendCompleteNotification(i);
                synchronized (ExoPlayerMediaPlayer2Impl.this.mTaskLock) {
                    ExoPlayerMediaPlayer2Impl.this.mCurrentTask = null;
                    ExoPlayerMediaPlayer2Impl.this.processPendingTask();
                }
            }
            synchronized (this) {
                this.mDone = true;
                notifyAll();
            }
        }

        /* access modifiers changed from: package-private */
        public void sendCompleteNotification(final int i) {
            if (this.mMediaCallType < 1000) {
                ExoPlayerMediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    /* class androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Task.AnonymousClass1 */

                    @Override // androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.Mp2EventNotifier
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onCallCompleted(ExoPlayerMediaPlayer2Impl.this, Task.this.mDSD, Task.this.mMediaCallType, i);
                    }
                });
            }
        }
    }
}
