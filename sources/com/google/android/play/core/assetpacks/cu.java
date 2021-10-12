package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.common.a;
import com.google.android.play.core.internal.ca;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;
import java.io.File;
import java.util.concurrent.Executor;

public final class cu implements ce<ct> {

    /* renamed from: a  reason: collision with root package name */
    private final ce f120a;
    private final ce b;
    private final ce c;
    private final ce d;
    private final ce e;
    private final ce f;
    private final /* synthetic */ int g = 0;

    public cu(ce<au> ceVar, ce<t> ceVar2, ce<ca> ceVar3, ce<Executor> ceVar4, ce<bo> ceVar5, ce<a> ceVar6) {
        this.f120a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
        this.e = ceVar5;
        this.f = ceVar6;
    }

    public cu(ce<String> ceVar, ce<ar> ceVar2, ce<bo> ceVar3, ce<Context> ceVar4, ce<cv> ceVar5, ce<Executor> ceVar6, byte[] bArr) {
        this.f = ceVar;
        this.b = ceVar2;
        this.e = ceVar3;
        this.d = ceVar4;
        this.c = ceVar5;
        this.f120a = ceVar6;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ ct a() {
        if (this.g != 0) {
            String str = (String) this.f.a();
            Object a2 = this.b.a();
            Object a3 = this.e.a();
            Context b2 = ((p) this.d).a();
            Object a4 = this.c.a();
            return new cj(str != null ? new File(b2.getExternalFilesDir(null), str) : b2.getExternalFilesDir(null), (ar) a2, (bo) a3, b2, (cv) a4, cc.c(this.f120a));
        }
        Object a5 = this.f120a.a();
        ca c2 = cc.c(this.b);
        Object a6 = this.c.a();
        return new ct((au) a5, c2, (ca) a6, cc.c(this.d), (bo) this.e.a(), (a) this.f.a());
    }
}
