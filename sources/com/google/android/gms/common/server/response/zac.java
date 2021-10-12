package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zac implements FastParser.zaa<Long> {
    zac() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Long zaa(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return Long.valueOf(FastParser.zab(fastParser, bufferedReader));
    }
}
