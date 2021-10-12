package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.a;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;
import com.google.android.play.core.splitinstall.p;
import java.util.concurrent.Executor;

public final class j implements ce<i> {

    /* renamed from: a  reason: collision with root package name */
    private final ce<au> f135a;
    private final ce<t> b;
    private final ce<ar> c;
    private final ce<p> d;
    private final ce<ca> e;
    private final ce<bo> f;
    private final ce<be> g;
    private final ce<Executor> h;
    private final ce<a> i;

    public j(ce<au> ceVar, ce<t> ceVar2, ce<ar> ceVar3, ce<p> ceVar4, ce<ca> ceVar5, ce<bo> ceVar6, ce<be> ceVar7, ce<Executor> ceVar8, ce<a> ceVar9) {
        this.f135a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
        this.e = ceVar5;
        this.f = ceVar6;
        this.g = ceVar7;
        this.h = ceVar8;
        this.i = ceVar9;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ i a() {
        return new i(this.f135a.a(), cc.c(this.b), this.c.a(), this.d.a(), this.e.a(), this.f.a(), this.g.a(), cc.c(this.h), this.i.a());
    }
}
