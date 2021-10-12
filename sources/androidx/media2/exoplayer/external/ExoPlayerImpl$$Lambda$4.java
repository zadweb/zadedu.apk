package androidx.media2.exoplayer.external;

import androidx.media2.exoplayer.external.BasePlayer;
import androidx.media2.exoplayer.external.Player;

final /* synthetic */ class ExoPlayerImpl$$Lambda$4 implements BasePlayer.ListenerInvocation {
    private final PlaybackParameters arg$1;

    ExoPlayerImpl$$Lambda$4(PlaybackParameters playbackParameters) {
        this.arg$1 = playbackParameters;
    }

    @Override // androidx.media2.exoplayer.external.BasePlayer.ListenerInvocation
    public void invokeListener(Player.EventListener eventListener) {
        ExoPlayerImpl.lambda$handleEvent$4$ExoPlayerImpl(this.arg$1, eventListener);
    }
}
