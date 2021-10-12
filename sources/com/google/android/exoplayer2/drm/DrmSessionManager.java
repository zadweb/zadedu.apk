package com.google.android.exoplayer2.drm;

import android.os.Looper;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;

public interface DrmSessionManager<T extends ExoMediaCrypto> {
    public static final DrmSessionManager<ExoMediaCrypto> DUMMY = new DrmSessionManager<ExoMediaCrypto>() {
        /* class com.google.android.exoplayer2.drm.DrmSessionManager.AnonymousClass1 */

        @Override // com.google.android.exoplayer2.drm.DrmSessionManager
        public /* synthetic */ DrmSession<T> acquirePlaceholderSession(Looper looper, int i) {
            return CC.$default$acquirePlaceholderSession(this, looper, i);
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionManager
        public boolean canAcquireSession(DrmInitData drmInitData) {
            return false;
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionManager
        public Class<ExoMediaCrypto> getExoMediaCryptoType(DrmInitData drmInitData) {
            return null;
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionManager
        public /* synthetic */ void prepare() {
            CC.$default$prepare(this);
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionManager
        public /* synthetic */ void release() {
            CC.$default$release(this);
        }

        @Override // com.google.android.exoplayer2.drm.DrmSessionManager
        public DrmSession<ExoMediaCrypto> acquireSession(Looper looper, DrmInitData drmInitData) {
            return new ErrorStateDrmSession(new DrmSession.DrmSessionException(new UnsupportedDrmException(1)));
        }
    };

    DrmSession<T> acquirePlaceholderSession(Looper looper, int i);

    DrmSession<T> acquireSession(Looper looper, DrmInitData drmInitData);

    boolean canAcquireSession(DrmInitData drmInitData);

    Class<? extends ExoMediaCrypto> getExoMediaCryptoType(DrmInitData drmInitData);

    void prepare();

    void release();

    /* renamed from: com.google.android.exoplayer2.drm.DrmSessionManager$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        /* JADX WARN: Incorrect args count in method signature: (Landroid/os/Looper;I)Lcom/google/android/exoplayer2/drm/DrmSession<TT;>; */
        public static DrmSession $default$acquirePlaceholderSession(DrmSessionManager drmSessionManager, Looper looper, int i) {
            return null;
        }

        public static void $default$prepare(DrmSessionManager drmSessionManager) {
        }

        public static void $default$release(DrmSessionManager drmSessionManager) {
        }

        public static <T extends ExoMediaCrypto> DrmSessionManager<T> getDummyDrmSessionManager() {
            return (DrmSessionManager<T>) DrmSessionManager.DUMMY;
        }
    }
}
