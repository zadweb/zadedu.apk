package androidx.media2.session;

import androidx.media2.common.MediaItem;
import androidx.media2.common.ParcelImplListSlice;
import androidx.media2.session.MediaLibraryService;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.util.List;

public class LibraryResult extends CustomVersionedParcelable implements RemoteResult {
    long mCompletionTime;
    MediaItem mItem;
    List<MediaItem> mItemList;
    ParcelImplListSlice mItemListSlice;
    MediaLibraryService.LibraryParams mParams;
    MediaItem mParcelableItem;
    int mResultCode;

    LibraryResult() {
    }

    @Override // androidx.media2.common.BaseResult
    public int getResultCode() {
        return this.mResultCode;
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPreParceling(boolean z) {
        this.mParcelableItem = MediaUtils.upcastForPreparceling(this.mItem);
        this.mItemListSlice = MediaUtils.convertMediaItemListToParcelImplListSlice(this.mItemList);
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPostParceling() {
        this.mItem = this.mParcelableItem;
        this.mParcelableItem = null;
        this.mItemList = MediaUtils.convertParcelImplListSliceToMediaItemList(this.mItemListSlice);
        this.mItemListSlice = null;
    }
}
