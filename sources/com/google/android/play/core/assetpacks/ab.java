package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.ah;
import com.google.android.play.core.internal.t;
import com.google.android.play.core.tasks.i;

final class ab extends ah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f62a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ i e;
    final /* synthetic */ an f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ab(an anVar, i iVar, int i, String str, String str2, int i2, i iVar2) {
        super(iVar);
        this.f = anVar;
        this.f62a = i;
        this.b = str;
        this.c = str2;
        this.d = i2;
        this.e = iVar2;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.play.core.internal.ah
    public final void a() {
        try {
            ((t) this.f.e.c()).f(this.f.c, an.r(this.f62a, this.b, this.c, this.d), an.C(), new ag(this.f, this.e, (char[]) null));
        } catch (RemoteException e2) {
            an.f68a.c(e2, "notifyChunkTransferred", new Object[0]);
        }
    }
}
