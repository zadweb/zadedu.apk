package com.google.android.play.core.assetpacks;

import android.content.ComponentName;
import android.content.Context;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.internal.bh;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;

public final class n implements ce<Object> {

    /* renamed from: a  reason: collision with root package name */
    private final ce f140a;
    private final ce b;
    private final /* synthetic */ int c = 0;

    public n(ce<i> ceVar, ce<Context> ceVar2) {
        this.f140a = ceVar;
        this.b = ceVar2;
    }

    public n(ce<Context> ceVar, ce<au> ceVar2, byte[] bArr) {
        this.b = ceVar;
        this.f140a = ceVar2;
    }

    public n(ce<Context> ceVar, ce<bo> ceVar2, char[] cArr) {
        this.f140a = ceVar;
        this.b = ceVar2;
    }

    public n(ce<au> ceVar, ce<t> ceVar2, int[] iArr) {
        this.b = ceVar;
        this.f140a = ceVar2;
    }

    public n(ce<Context> ceVar, ce<cv> ceVar2, short[] sArr) {
        this.f140a = ceVar;
        this.b = ceVar2;
    }

    @Override // com.google.android.play.core.internal.ce
    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.c;
        if (i == 0) {
            Object a2 = this.f140a.a();
            Context b2 = ((p) this.b).a();
            i iVar = (i) a2;
            bh.h(b2.getPackageManager(), new ComponentName(b2.getPackageName(), "com.google.android.play.core.assetpacks.AssetPackExtractionService"), 4);
            bh.h(b2.getPackageManager(), new ComponentName(b2.getPackageName(), "com.google.android.play.core.assetpacks.ExtractionForegroundService"), 4);
            PlayCoreDialogWrapperActivity.a(b2);
            bh.k(iVar);
            return iVar;
        } else if (i == 1) {
            return new b(((p) this.b).a(), (au) this.f140a.a());
        } else {
            if (i == 2) {
                return new an(((p) this.f140a).a(), (bo) this.b.a());
            }
            if (i == 3) {
                return new au(((p) this.f140a).a(), (cv) this.b.a());
            }
            return new cx((au) this.b.a(), cc.c(this.f140a));
        }
    }
}
