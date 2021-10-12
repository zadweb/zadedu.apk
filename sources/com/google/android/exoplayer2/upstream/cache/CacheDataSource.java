package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.TeeDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.ContentMetadata;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class CacheDataSource implements DataSource {
    private Uri actualUri;
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final CacheKeyFactory cacheKeyFactory;
    private final DataSource cacheReadDataSource;
    private final DataSource cacheWriteDataSource;
    private long checkCachePosition;
    private DataSource currentDataSource;
    private boolean currentDataSpecLengthUnset;
    private CacheSpan currentHoleSpan;
    private boolean currentRequestIgnoresCache;
    private final EventListener eventListener;
    private int flags;
    private byte[] httpBody;
    private int httpMethod;
    private final boolean ignoreCacheForUnsetLengthRequests;
    private final boolean ignoreCacheOnError;
    private String key;
    private long readPosition;
    private boolean seenCacheError;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;
    private Uri uri;

    public interface EventListener {
        void onCacheIgnored(int i);

        void onCachedBytesRead(long j, long j2);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource) {
        this(cache2, dataSource, 0);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, int i) {
        this(cache2, dataSource, new FileDataSource(), new CacheDataSink(cache2, 5242880), i, null);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, EventListener eventListener2) {
        this(cache2, dataSource, dataSource2, dataSink, i, eventListener2, null);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, EventListener eventListener2, CacheKeyFactory cacheKeyFactory2) {
        this.cache = cache2;
        this.cacheReadDataSource = dataSource2;
        this.cacheKeyFactory = cacheKeyFactory2 == null ? CacheUtil.DEFAULT_CACHE_KEY_FACTORY : cacheKeyFactory2;
        boolean z = false;
        this.blockOnCache = (i & 1) != 0;
        this.ignoreCacheOnError = (i & 2) != 0;
        this.ignoreCacheForUnsetLengthRequests = (i & 4) != 0 ? true : z;
        this.upstreamDataSource = dataSource;
        if (dataSink != null) {
            this.cacheWriteDataSource = new TeeDataSource(dataSource, dataSink);
        } else {
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener2;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void addTransferListener(TransferListener transferListener) {
        this.cacheReadDataSource.addTransferListener(transferListener);
        this.upstreamDataSource.addTransferListener(transferListener);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        try {
            this.key = this.cacheKeyFactory.buildCacheKey(dataSpec);
            Uri uri2 = dataSpec.uri;
            this.uri = uri2;
            this.actualUri = getRedirectedUriOrDefault(this.cache, this.key, uri2);
            this.httpMethod = dataSpec.httpMethod;
            this.httpBody = dataSpec.httpBody;
            this.flags = dataSpec.flags;
            this.readPosition = dataSpec.position;
            int shouldIgnoreCacheForRequest = shouldIgnoreCacheForRequest(dataSpec);
            boolean z = shouldIgnoreCacheForRequest != -1;
            this.currentRequestIgnoresCache = z;
            if (z) {
                notifyCacheIgnored(shouldIgnoreCacheForRequest);
            }
            if (dataSpec.length == -1) {
                if (!this.currentRequestIgnoresCache) {
                    long contentLength = ContentMetadata.CC.getContentLength(this.cache.getContentMetadata(this.key));
                    this.bytesRemaining = contentLength;
                    if (contentLength != -1) {
                        long j = contentLength - dataSpec.position;
                        this.bytesRemaining = j;
                        if (j <= 0) {
                            throw new DataSourceException(0);
                        }
                    }
                    openNextSource(false);
                    return this.bytesRemaining;
                }
            }
            this.bytesRemaining = dataSpec.length;
            openNextSource(false);
            return this.bytesRemaining;
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            if (this.readPosition >= this.checkCachePosition) {
                openNextSource(true);
            }
            int read = this.currentDataSource.read(bArr, i, i2);
            if (read != -1) {
                if (isReadingFromCache()) {
                    this.totalCachedBytesRead += (long) read;
                }
                long j = (long) read;
                this.readPosition += j;
                if (this.bytesRemaining != -1) {
                    this.bytesRemaining -= j;
                }
            } else if (this.currentDataSpecLengthUnset) {
                setNoBytesRemainingAndMaybeStoreLength();
            } else {
                if (this.bytesRemaining <= 0) {
                    if (this.bytesRemaining == -1) {
                    }
                }
                closeCurrentSource();
                openNextSource(false);
                return read(bArr, i, i2);
            }
            return read;
        } catch (IOException e) {
            if (!this.currentDataSpecLengthUnset || !CacheUtil.isCausedByPositionOutOfRange(e)) {
                handleBeforeThrow(e);
                throw e;
            }
            setNoBytesRemainingAndMaybeStoreLength();
            return -1;
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.actualUri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        if (isReadingFromUpstream()) {
            return this.upstreamDataSource.getResponseHeaders();
        }
        return Collections.emptyMap();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        this.uri = null;
        this.actualUri = null;
        this.httpMethod = 1;
        this.httpBody = null;
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    private void openNextSource(boolean z) throws IOException {
        CacheSpan cacheSpan;
        DataSource dataSource;
        CacheSpan cacheSpan2;
        DataSpec dataSpec;
        long open;
        ContentMetadataMutations contentMetadataMutations;
        long j;
        DataSpec dataSpec2;
        DataSource dataSource2;
        Uri uri2 = null;
        if (this.currentRequestIgnoresCache) {
            cacheSpan = null;
        } else if (this.blockOnCache) {
            try {
                cacheSpan = this.cache.startReadWrite(this.key, this.readPosition);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
        } else {
            cacheSpan = this.cache.startReadWriteNonBlocking(this.key, this.readPosition);
        }
        if (cacheSpan == null) {
            dataSource2 = this.upstreamDataSource;
            Uri uri3 = this.uri;
            int i = this.httpMethod;
            byte[] bArr = this.httpBody;
            long j2 = this.readPosition;
            dataSpec2 = new DataSpec(uri3, i, bArr, j2, j2, this.bytesRemaining, this.key, this.flags);
        } else if (cacheSpan.isCached) {
            Uri fromFile = Uri.fromFile(cacheSpan.file);
            long j3 = this.readPosition - cacheSpan.position;
            long j4 = cacheSpan.length - j3;
            long j5 = this.bytesRemaining;
            if (j5 != -1) {
                j4 = Math.min(j4, j5);
            }
            dataSpec2 = new DataSpec(fromFile, this.readPosition, j3, j4, this.key, this.flags);
            dataSource2 = this.cacheReadDataSource;
        } else {
            if (cacheSpan.isOpenEnded()) {
                j = this.bytesRemaining;
            } else {
                j = cacheSpan.length;
                long j6 = this.bytesRemaining;
                if (j6 != -1) {
                    j = Math.min(j, j6);
                }
            }
            Uri uri4 = this.uri;
            int i2 = this.httpMethod;
            byte[] bArr2 = this.httpBody;
            long j7 = this.readPosition;
            DataSpec dataSpec3 = new DataSpec(uri4, i2, bArr2, j7, j7, j, this.key, this.flags);
            dataSource = this.cacheWriteDataSource;
            if (dataSource != null) {
                cacheSpan2 = cacheSpan;
                dataSpec = dataSpec3;
            } else {
                dataSource = this.upstreamDataSource;
                this.cache.releaseHoleSpan(cacheSpan);
                dataSpec = dataSpec3;
                cacheSpan2 = null;
            }
            this.checkCachePosition = (!this.currentRequestIgnoresCache || dataSource != this.upstreamDataSource) ? Long.MAX_VALUE : this.readPosition + 102400;
            if (z) {
                Assertions.checkState(isBypassingCache());
                if (dataSource != this.upstreamDataSource) {
                    try {
                        closeCurrentSource();
                    } catch (Throwable th) {
                        if (cacheSpan2.isHoleSpan()) {
                            this.cache.releaseHoleSpan(cacheSpan2);
                        }
                        throw th;
                    }
                } else {
                    return;
                }
            }
            if (cacheSpan2 != null && cacheSpan2.isHoleSpan()) {
                this.currentHoleSpan = cacheSpan2;
            }
            this.currentDataSource = dataSource;
            this.currentDataSpecLengthUnset = dataSpec.length != -1;
            open = dataSource.open(dataSpec);
            contentMetadataMutations = new ContentMetadataMutations();
            if (this.currentDataSpecLengthUnset && open != -1) {
                this.bytesRemaining = open;
                ContentMetadataMutations.setContentLength(contentMetadataMutations, this.readPosition + open);
            }
            if (isReadingFromUpstream()) {
                Uri uri5 = this.currentDataSource.getUri();
                this.actualUri = uri5;
                if (!this.uri.equals(uri5)) {
                    uri2 = this.actualUri;
                }
                ContentMetadataMutations.setRedirectedUri(contentMetadataMutations, uri2);
            }
            if (!isWritingToCache()) {
                this.cache.applyContentMetadataMutations(this.key, contentMetadataMutations);
                return;
            }
            return;
        }
        dataSource = dataSource2;
        cacheSpan2 = cacheSpan;
        dataSpec = dataSpec2;
        this.checkCachePosition = (!this.currentRequestIgnoresCache || dataSource != this.upstreamDataSource) ? Long.MAX_VALUE : this.readPosition + 102400;
        if (z) {
        }
        this.currentHoleSpan = cacheSpan2;
        this.currentDataSource = dataSource;
        this.currentDataSpecLengthUnset = dataSpec.length != -1;
        open = dataSource.open(dataSpec);
        contentMetadataMutations = new ContentMetadataMutations();
        this.bytesRemaining = open;
        ContentMetadataMutations.setContentLength(contentMetadataMutations, this.readPosition + open);
        if (isReadingFromUpstream()) {
        }
        if (!isWritingToCache()) {
        }
    }

    private void setNoBytesRemainingAndMaybeStoreLength() throws IOException {
        this.bytesRemaining = 0;
        if (isWritingToCache()) {
            ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
            ContentMetadataMutations.setContentLength(contentMetadataMutations, this.readPosition);
            this.cache.applyContentMetadataMutations(this.key, contentMetadataMutations);
        }
    }

    private static Uri getRedirectedUriOrDefault(Cache cache2, String str, Uri uri2) {
        Uri redirectedUri = ContentMetadata.CC.getRedirectedUri(cache2.getContentMetadata(str));
        return redirectedUri != null ? redirectedUri : uri2;
    }

    private boolean isReadingFromUpstream() {
        return !isReadingFromCache();
    }

    private boolean isBypassingCache() {
        return this.currentDataSource == this.upstreamDataSource;
    }

    private boolean isReadingFromCache() {
        return this.currentDataSource == this.cacheReadDataSource;
    }

    private boolean isWritingToCache() {
        return this.currentDataSource == this.cacheWriteDataSource;
    }

    private void closeCurrentSource() throws IOException {
        DataSource dataSource = this.currentDataSource;
        if (dataSource != null) {
            try {
                dataSource.close();
            } finally {
                this.currentDataSource = null;
                this.currentDataSpecLengthUnset = false;
                CacheSpan cacheSpan = this.currentHoleSpan;
                if (cacheSpan != null) {
                    this.cache.releaseHoleSpan(cacheSpan);
                    this.currentHoleSpan = null;
                }
            }
        }
    }

    private void handleBeforeThrow(Throwable th) {
        if (isReadingFromCache() || (th instanceof Cache.CacheException)) {
            this.seenCacheError = true;
        }
    }

    private int shouldIgnoreCacheForRequest(DataSpec dataSpec) {
        if (!this.ignoreCacheOnError || !this.seenCacheError) {
            return (!this.ignoreCacheForUnsetLengthRequests || dataSpec.length != -1) ? -1 : 1;
        }
        return 0;
    }

    private void notifyCacheIgnored(int i) {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null) {
            eventListener2.onCacheIgnored(i);
        }
    }

    private void notifyBytesRead() {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null && this.totalCachedBytesRead > 0) {
            eventListener2.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
            this.totalCachedBytesRead = 0;
        }
    }
}
