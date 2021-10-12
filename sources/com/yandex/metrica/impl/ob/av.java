package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import com.yandex.metrica.impl.h;

public class av extends af {
    public av(t tVar) {
        super(tVar);
    }

    @Override // com.yandex.metrica.impl.ob.af
    public boolean a(h hVar) {
        if (!TextUtils.isEmpty(hVar.k()) && TextUtils.isEmpty(a().g())) {
            a().a(hVar.k());
        }
        return false;
    }
}
