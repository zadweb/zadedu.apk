package androidx.media2.common;

import androidx.versionedparcelable.ParcelImpl;
import androidx.versionedparcelable.ParcelUtils;
import androidx.versionedparcelable.VersionedParcelable;

public class MediaParcelUtils {
    public static ParcelImpl toParcelable(VersionedParcelable versionedParcelable) {
        if (versionedParcelable instanceof MediaItem) {
            return new MediaItemParcelImpl((MediaItem) versionedParcelable);
        }
        return (ParcelImpl) ParcelUtils.toParcelable(versionedParcelable);
    }

    public static <T extends VersionedParcelable> T fromParcelable(ParcelImpl parcelImpl) {
        return (T) ParcelUtils.fromParcelable(parcelImpl);
    }

    /* access modifiers changed from: private */
    public static class MediaItemParcelImpl extends ParcelImpl {
        private final MediaItem mItem;

        MediaItemParcelImpl(MediaItem mediaItem) {
            super(new MediaItem(mediaItem));
            this.mItem = mediaItem;
        }

        @Override // androidx.versionedparcelable.ParcelImpl
        public MediaItem getVersionedParcel() {
            return this.mItem;
        }
    }
}
