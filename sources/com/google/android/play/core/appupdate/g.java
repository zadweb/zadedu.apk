package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.internal.bh;
import com.google.android.play.core.internal.ce;
import com.google.android.play.core.splitinstall.p;

public final class g implements ce<Object> {

    /* renamed from: a  reason: collision with root package name */
    private final ce f55a;
    private final /* synthetic */ int b = 3;

    public g(ce<Context> ceVar, short[] sArr) {
        this.f55a = ceVar;
    }

    public static g b(ce<Context> ceVar) {
        return new g(ceVar, null);
    }

    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.b;
        if (i != 0) {
            return i != 1 ? i != 2 ? new p((Context) this.f55a.a()) : new q(((h) this.f55a).a()) : new a(((h) this.f55a).a());
        }
        d dVar = (d) this.f55a.a();
        bh.k(dVar);
        return dVar;
    }
}
