package androidx.media2.exoplayer.external;

import androidx.media2.exoplayer.external.BasePlayer;
import androidx.media2.exoplayer.external.ExoPlayerImpl;
import androidx.media2.exoplayer.external.Player;

final /* synthetic */ class ExoPlayerImpl$PlaybackInfoUpdate$$Lambda$1 implements BasePlayer.ListenerInvocation {
    private final ExoPlayerImpl.PlaybackInfoUpdate arg$1;

    ExoPlayerImpl$PlaybackInfoUpdate$$Lambda$1(ExoPlayerImpl.PlaybackInfoUpdate playbackInfoUpdate) {
        this.arg$1 = playbackInfoUpdate;
    }

    @Override // androidx.media2.exoplayer.external.BasePlayer.ListenerInvocation
    public void invokeListener(Player.EventListener eventListener) {
        this.arg$1.lambda$run$1$ExoPlayerImpl$PlaybackInfoUpdate(eventListener);
    }
}
