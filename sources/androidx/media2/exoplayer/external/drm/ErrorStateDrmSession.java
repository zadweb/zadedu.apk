package androidx.media2.exoplayer.external.drm;

import androidx.media2.exoplayer.external.drm.DrmSession;
import androidx.media2.exoplayer.external.drm.ExoMediaCrypto;
import androidx.media2.exoplayer.external.util.Assertions;
import java.util.Map;

public final class ErrorStateDrmSession<T extends ExoMediaCrypto> implements DrmSession<T> {
    private final DrmSession.DrmSessionException error;

    @Override // androidx.media2.exoplayer.external.drm.DrmSession
    public T getMediaCrypto() {
        return null;
    }

    @Override // androidx.media2.exoplayer.external.drm.DrmSession
    public int getState() {
        return 1;
    }

    @Override // androidx.media2.exoplayer.external.drm.DrmSession
    public Map<String, String> queryKeyStatus() {
        return null;
    }

    public ErrorStateDrmSession(DrmSession.DrmSessionException drmSessionException) {
        this.error = (DrmSession.DrmSessionException) Assertions.checkNotNull(drmSessionException);
    }

    @Override // androidx.media2.exoplayer.external.drm.DrmSession
    public DrmSession.DrmSessionException getError() {
        return this.error;
    }
}
