package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;

public class ab extends v<af> {
    public ab(aa<af> aaVar) {
        super(aaVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.yandex.metrica.impl.ob.v
    public boolean a(h hVar, x<af> xVar) {
        for (af afVar : xVar.a()) {
            if (afVar.a(hVar)) {
                return true;
            }
        }
        return false;
    }
}
