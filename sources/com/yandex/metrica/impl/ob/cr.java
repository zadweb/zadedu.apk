package com.yandex.metrica.impl.ob;

import android.net.Uri;
import android.text.TextUtils;
import com.mopub.common.Constants;

public class cr extends cq {
    @Override // com.yandex.metrica.impl.ob.cq
    public boolean b() {
        return true;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public cr(String str) {
        super(str);
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (Constants.HTTP.equals(parse.getScheme())) {
                str = parse.buildUpon().scheme(Constants.HTTPS).build().toString();
            }
        }
    }
}
