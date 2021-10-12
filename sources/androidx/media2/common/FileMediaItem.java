package androidx.media2.common;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;

public class FileMediaItem extends MediaItem {
    boolean mClosed;
    long mFDLength = 576460752303423487L;
    long mFDOffset = 0;
    ParcelFileDescriptor mPFD;
    Integer mRefCount = new Integer(0);

    FileMediaItem() {
    }

    public ParcelFileDescriptor getParcelFileDescriptor() {
        return this.mPFD;
    }

    public long getFileDescriptorOffset() {
        return this.mFDOffset;
    }

    public long getFileDescriptorLength() {
        return this.mFDLength;
    }

    public void increaseRefCount() {
        synchronized (this.mRefCount) {
            if (this.mClosed) {
                Log.w("FileMediaItem", "ParcelFileDescriptorClient is already closed.");
            } else {
                this.mRefCount = Integer.valueOf(this.mRefCount.intValue() + 1);
            }
        }
    }

    public void decreaseRefCount() {
        synchronized (this.mRefCount) {
            if (this.mClosed) {
                Log.w("FileMediaItem", "ParcelFileDescriptorClient is already closed.");
                return;
            }
            Integer valueOf = Integer.valueOf(this.mRefCount.intValue() - 1);
            this.mRefCount = valueOf;
            if (valueOf.intValue() <= 0) {
                try {
                    if (this.mPFD != null) {
                        this.mPFD.close();
                    }
                } catch (IOException e) {
                    Log.e("FileMediaItem", "Failed to close the ParcelFileDescriptor " + this.mPFD, e);
                } catch (Throwable th) {
                    this.mClosed = true;
                    throw th;
                }
                this.mClosed = true;
            }
        }
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this.mRefCount) {
            z = this.mClosed;
        }
        return z;
    }
}
