package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.i;

final class ak extends ag<Void> {
    final int c;
    final String d;
    final int e;
    final /* synthetic */ an f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ak(an anVar, i<Void> iVar, int i, String str, int i2) {
        super(anVar, iVar);
        this.f = anVar;
        this.c = i;
        this.d = str;
        this.e = i2;
    }

    @Override // com.google.android.play.core.internal.v, com.google.android.play.core.assetpacks.ag
    public final void g(Bundle bundle) {
        an.o(this.f).b();
        int i = bundle.getInt("error_code");
        an.p().b("onError(%d), retrying notifyModuleCompleted...", Integer.valueOf(i));
        int i2 = this.e;
        if (i2 > 0) {
            an.w(this.f, this.c, this.d, i2 - 1);
        }
    }
}
