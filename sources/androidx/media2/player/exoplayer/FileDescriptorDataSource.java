package androidx.media2.player.exoplayer;

import android.net.Uri;
import androidx.core.util.Preconditions;
import androidx.media2.exoplayer.external.upstream.BaseDataSource;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.DataSpec;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* access modifiers changed from: package-private */
public class FileDescriptorDataSource extends BaseDataSource {
    private long mBytesRemaining;
    private final FileDescriptor mFileDescriptor;
    private InputStream mInputStream;
    private final long mLength;
    private final Object mLock;
    private final long mOffset;
    private boolean mOpened;
    private long mPosition;
    private Uri mUri;

    static DataSource.Factory getFactory(final FileDescriptor fileDescriptor, final long j, final long j2, final Object obj) {
        return new DataSource.Factory() {
            /* class androidx.media2.player.exoplayer.FileDescriptorDataSource.AnonymousClass1 */

            @Override // androidx.media2.exoplayer.external.upstream.DataSource.Factory
            public DataSource createDataSource() {
                return new FileDescriptorDataSource(fileDescriptor, j, j2, obj);
            }
        };
    }

    FileDescriptorDataSource(FileDescriptor fileDescriptor, long j, long j2, Object obj) {
        super(false);
        this.mFileDescriptor = fileDescriptor;
        this.mOffset = j;
        this.mLength = j2;
        this.mLock = obj;
    }

    @Override // androidx.media2.exoplayer.external.upstream.DataSource
    public long open(DataSpec dataSpec) {
        this.mUri = dataSpec.uri;
        transferInitializing(dataSpec);
        this.mInputStream = new FileInputStream(this.mFileDescriptor);
        if (dataSpec.length != -1) {
            this.mBytesRemaining = dataSpec.length;
        } else {
            long j = this.mLength;
            if (j != -1) {
                this.mBytesRemaining = j - dataSpec.position;
            } else {
                this.mBytesRemaining = -1;
            }
        }
        this.mPosition = this.mOffset + dataSpec.position;
        this.mOpened = true;
        transferStarted(dataSpec);
        return this.mBytesRemaining;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        r9 = r7.mBytesRemaining;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        if (r9 == -1) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        r7.mBytesRemaining = r9 - r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        bytesTransferred(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0054, code lost:
        return r8;
     */
    @Override // androidx.media2.exoplayer.external.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.mBytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            i2 = (int) Math.min(j, (long) i2);
        }
        synchronized (this.mLock) {
            FileDescriptorUtil.seek(this.mFileDescriptor, this.mPosition);
            int read = ((InputStream) Preconditions.checkNotNull(this.mInputStream)).read(bArr, i, i2);
            if (read != -1) {
                long j2 = (long) read;
                this.mPosition += j2;
            } else if (this.mBytesRemaining == -1) {
                return -1;
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // androidx.media2.exoplayer.external.upstream.DataSource
    public Uri getUri() {
        return (Uri) Preconditions.checkNotNull(this.mUri);
    }

    @Override // androidx.media2.exoplayer.external.upstream.DataSource
    public void close() throws IOException {
        this.mUri = null;
        try {
            if (this.mInputStream != null) {
                this.mInputStream.close();
            }
        } finally {
            this.mInputStream = null;
            if (this.mOpened) {
                this.mOpened = false;
                transferEnded();
            }
        }
    }
}
