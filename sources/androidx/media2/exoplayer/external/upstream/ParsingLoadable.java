package androidx.media2.exoplayer.external.upstream;

import android.net.Uri;
import androidx.media2.exoplayer.external.upstream.Loader;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public final class ParsingLoadable<T> implements Loader.Loadable {
    private final StatsDataSource dataSource;
    public final DataSpec dataSpec;
    private final Parser<? extends T> parser;
    private volatile T result;
    public final int type;

    public interface Parser<T> {
        T parse(Uri uri, InputStream inputStream) throws IOException;
    }

    @Override // androidx.media2.exoplayer.external.upstream.Loader.Loadable
    public final void cancelLoad() {
    }

    public ParsingLoadable(DataSource dataSource2, Uri uri, int i, Parser<? extends T> parser2) {
        this(dataSource2, new DataSpec(uri, 1), i, parser2);
    }

    public ParsingLoadable(DataSource dataSource2, DataSpec dataSpec2, int i, Parser<? extends T> parser2) {
        this.dataSource = new StatsDataSource(dataSource2);
        this.dataSpec = dataSpec2;
        this.type = i;
        this.parser = parser2;
    }

    public final T getResult() {
        return this.result;
    }

    public long bytesLoaded() {
        return this.dataSource.getBytesRead();
    }

    public Uri getUri() {
        return this.dataSource.getLastOpenedUri();
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.dataSource.getLastResponseHeaders();
    }

    @Override // androidx.media2.exoplayer.external.upstream.Loader.Loadable
    public final void load() throws IOException {
        this.dataSource.resetBytesRead();
        DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(this.dataSource, this.dataSpec);
        try {
            dataSourceInputStream.open();
            this.result = (T) this.parser.parse((Uri) Assertions.checkNotNull(this.dataSource.getUri()), dataSourceInputStream);
        } finally {
            Util.closeQuietly(dataSourceInputStream);
        }
    }
}
