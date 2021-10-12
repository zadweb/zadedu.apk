package androidx.media2.exoplayer.external.offline;

import android.net.Uri;
import androidx.media2.exoplayer.external.offline.FilterableManifest;
import androidx.media2.exoplayer.external.upstream.ParsingLoadable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class FilteringManifestParser<T extends FilterableManifest<T>> implements ParsingLoadable.Parser<T> {
    private final ParsingLoadable.Parser<? extends T> parser;
    private final List<StreamKey> streamKeys;

    public FilteringManifestParser(ParsingLoadable.Parser<? extends T> parser2, List<StreamKey> list) {
        this.parser = parser2;
        this.streamKeys = list;
    }

    @Override // androidx.media2.exoplayer.external.upstream.ParsingLoadable.Parser
    public T parse(Uri uri, InputStream inputStream) throws IOException {
        T t = (T) ((FilterableManifest) this.parser.parse(uri, inputStream));
        List<StreamKey> list = this.streamKeys;
        return (list == null || list.isEmpty()) ? t : (T) ((FilterableManifest) t.copy(this.streamKeys));
    }
}
