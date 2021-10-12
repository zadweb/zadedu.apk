package androidx.media2.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;
import androidx.media.AudioAttributesCompat;

public class AudioFocusHandler {
    private final AudioFocusHandlerImpl mImpl;

    interface AudioFocusHandlerImpl {
        void close();

        void onPause();

        boolean onPlay();

        void onReset();
    }

    AudioFocusHandler(Context context, MediaPlayer mediaPlayer) {
        this.mImpl = new AudioFocusHandlerImplBase(context, mediaPlayer);
    }

    public boolean onPlay() {
        return this.mImpl.onPlay();
    }

    public void onPause() {
        this.mImpl.onPause();
    }

    public void onReset() {
        this.mImpl.onReset();
    }

    public void close() {
        this.mImpl.close();
    }

    private static class AudioFocusHandlerImplBase implements AudioFocusHandlerImpl {
        AudioAttributesCompat mAudioAttributes;
        private final AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioFocusListener();
        private final AudioManager mAudioManager;
        private final BroadcastReceiver mBecomingNoisyReceiver = new BecomingNoisyReceiver();
        boolean mBecomingNoisyReceiverRegistered;
        private final Context mContext;
        private int mCurrentFocusGainType;
        private final IntentFilter mIntentFilter = new IntentFilter("android.media.AUDIO_BECOMING_NOISY");
        final Object mLock = new Object();
        final MediaPlayer mPlayer;
        boolean mResumeWhenAudioFocusGain;

        AudioFocusHandlerImplBase(Context context, MediaPlayer mediaPlayer) {
            this.mContext = context;
            this.mPlayer = mediaPlayer;
            this.mAudioManager = (AudioManager) context.getSystemService("audio");
        }

        @Override // androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImpl
        public boolean onPlay() {
            boolean z;
            AudioAttributesCompat audioAttributes = this.mPlayer.getAudioAttributes();
            synchronized (this.mLock) {
                this.mAudioAttributes = audioAttributes;
                if (audioAttributes == null) {
                    abandonAudioFocusLocked();
                    unregisterBecomingNoisyReceiverLocked();
                    z = true;
                } else {
                    z = requestAudioFocusLocked();
                    if (z) {
                        registerBecomingNoisyReceiverLocked();
                    }
                }
            }
            return z;
        }

        @Override // androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImpl
        public void onPause() {
            synchronized (this.mLock) {
                this.mResumeWhenAudioFocusGain = false;
                unregisterBecomingNoisyReceiverLocked();
            }
        }

        @Override // androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImpl
        public void onReset() {
            synchronized (this.mLock) {
                abandonAudioFocusLocked();
                unregisterBecomingNoisyReceiverLocked();
            }
        }

        @Override // androidx.media2.player.AudioFocusHandler.AudioFocusHandlerImpl
        public void close() {
            synchronized (this.mLock) {
                unregisterBecomingNoisyReceiverLocked();
                abandonAudioFocusLocked();
            }
        }

        private boolean requestAudioFocusLocked() {
            int convertAudioAttributesToFocusGain = convertAudioAttributesToFocusGain(this.mAudioAttributes);
            if (convertAudioAttributesToFocusGain == 0) {
                if (this.mAudioAttributes == null) {
                    Log.e("AudioFocusHandler", "requestAudioFocusLocked() shouldn't be called when AudioAttributes is null");
                }
                return true;
            }
            int requestAudioFocus = this.mAudioManager.requestAudioFocus(this.mAudioFocusListener, this.mAudioAttributes.getVolumeControlStream(), convertAudioAttributesToFocusGain);
            if (requestAudioFocus == 1) {
                this.mCurrentFocusGainType = convertAudioAttributesToFocusGain;
            } else {
                Log.w("AudioFocusHandler", "requestAudioFocus(" + convertAudioAttributesToFocusGain + ") failed (return=" + requestAudioFocus + ") playback wouldn't start.");
                this.mCurrentFocusGainType = 0;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("requestAudioFocus(");
            sb.append(convertAudioAttributesToFocusGain);
            sb.append("), result=");
            sb.append(requestAudioFocus == 1);
            Log.d("AudioFocusHandler", sb.toString());
            this.mResumeWhenAudioFocusGain = false;
            if (this.mCurrentFocusGainType != 0) {
                return true;
            }
            return false;
        }

        private void abandonAudioFocusLocked() {
            if (this.mCurrentFocusGainType != 0) {
                Log.d("AudioFocusHandler", "abandoningAudioFocusLocked, currently=" + this.mCurrentFocusGainType);
                this.mAudioManager.abandonAudioFocus(this.mAudioFocusListener);
                this.mCurrentFocusGainType = 0;
                this.mResumeWhenAudioFocusGain = false;
            }
        }

        private void registerBecomingNoisyReceiverLocked() {
            if (!this.mBecomingNoisyReceiverRegistered) {
                Log.d("AudioFocusHandler", "registering becoming noisy receiver");
                this.mContext.registerReceiver(this.mBecomingNoisyReceiver, this.mIntentFilter);
                this.mBecomingNoisyReceiverRegistered = true;
            }
        }

        private void unregisterBecomingNoisyReceiverLocked() {
            if (this.mBecomingNoisyReceiverRegistered) {
                Log.d("AudioFocusHandler", "unregistering becoming noisy receiver");
                this.mContext.unregisterReceiver(this.mBecomingNoisyReceiver);
                this.mBecomingNoisyReceiverRegistered = false;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        private static int convertAudioAttributesToFocusGain(AudioAttributesCompat audioAttributesCompat) {
            if (audioAttributesCompat == null) {
                return 0;
            }
            switch (audioAttributesCompat.getUsage()) {
                case 0:
                    Log.w("AudioFocusHandler", "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
                    return 1;
                case 1:
                case 14:
                    return 1;
                case 2:
                case 4:
                    return 2;
                case 3:
                    return 0;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 12:
                case 13:
                    break;
                case 11:
                    if (audioAttributesCompat.getContentType() == 1) {
                        return 2;
                    }
                    break;
                case 15:
                default:
                    Log.w("AudioFocusHandler", "Unidentified AudioAttribute " + audioAttributesCompat);
                    return 0;
                case 16:
                    return 4;
            }
            return 3;
        }

        private class BecomingNoisyReceiver extends BroadcastReceiver {
            BecomingNoisyReceiver() {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
                if (r5 == 1) goto L_0x0073;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x005b, code lost:
                if (r5 == 14) goto L_0x005e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x005e, code lost:
                r3.this$0.mPlayer.setPlayerVolume(r3.this$0.mPlayer.getPlayerVolume() * 0.2f);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:0x0073, code lost:
                r3.this$0.mPlayer.pause();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
                return;
             */
            public void onReceive(Context context, Intent intent) {
                if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                    synchronized (AudioFocusHandlerImplBase.this.mLock) {
                        Log.d("AudioFocusHandler", "Received noisy intent, intent=" + intent + ", registered=" + AudioFocusHandlerImplBase.this.mBecomingNoisyReceiverRegistered + ", attr=" + AudioFocusHandlerImplBase.this.mAudioAttributes);
                        if (AudioFocusHandlerImplBase.this.mBecomingNoisyReceiverRegistered) {
                            if (AudioFocusHandlerImplBase.this.mAudioAttributes != null) {
                                int usage = AudioFocusHandlerImplBase.this.mAudioAttributes.getUsage();
                            }
                        }
                    }
                }
            }
        }

        private class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
            private float mPlayerDuckingVolume;
            private float mPlayerVolumeBeforeDucking;

            AudioFocusListener() {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:63:0x009b, code lost:
                if (r1 == false) goto L_0x00a5;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:64:0x009d, code lost:
                r3.this$0.mPlayer.pause();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:65:0x00a5, code lost:
                r4 = r3.this$0.mPlayer.getPlayerVolume();
                r0 = 0.2f * r4;
                r1 = r3.this$0.mLock;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:66:0x00b6, code lost:
                monitor-enter(r1);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
                r3.mPlayerVolumeBeforeDucking = r4;
                r3.mPlayerDuckingVolume = r0;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:69:0x00bb, code lost:
                monitor-exit(r1);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:70:0x00bc, code lost:
                r3.this$0.mPlayer.setPlayerVolume(r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
                return;
             */
            public void onAudioFocusChange(int i) {
                boolean z = false;
                if (i == -3) {
                    synchronized (AudioFocusHandlerImplBase.this.mLock) {
                        if (AudioFocusHandlerImplBase.this.mAudioAttributes != null) {
                            if (AudioFocusHandlerImplBase.this.mAudioAttributes.getContentType() == 1) {
                                z = true;
                            }
                        }
                    }
                } else if (i == -2) {
                    AudioFocusHandlerImplBase.this.mPlayer.pause();
                    synchronized (AudioFocusHandlerImplBase.this.mLock) {
                        AudioFocusHandlerImplBase.this.mResumeWhenAudioFocusGain = true;
                    }
                } else if (i == -1) {
                    AudioFocusHandlerImplBase.this.mPlayer.pause();
                    synchronized (AudioFocusHandlerImplBase.this.mLock) {
                        AudioFocusHandlerImplBase.this.mResumeWhenAudioFocusGain = false;
                    }
                } else if (i == 1) {
                    if (AudioFocusHandlerImplBase.this.mPlayer.getPlayerState() == 1) {
                        synchronized (AudioFocusHandlerImplBase.this.mLock) {
                            if (AudioFocusHandlerImplBase.this.mResumeWhenAudioFocusGain) {
                                AudioFocusHandlerImplBase.this.mPlayer.play();
                                return;
                            }
                            return;
                        }
                    }
                    float playerVolume = AudioFocusHandlerImplBase.this.mPlayer.getPlayerVolume();
                    synchronized (AudioFocusHandlerImplBase.this.mLock) {
                        if (playerVolume == this.mPlayerDuckingVolume) {
                            AudioFocusHandlerImplBase.this.mPlayer.setPlayerVolume(this.mPlayerVolumeBeforeDucking);
                        }
                    }
                }
            }
        }
    }
}
