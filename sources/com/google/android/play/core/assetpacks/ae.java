package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.ah;
import com.google.android.play.core.internal.t;
import com.google.android.play.core.tasks.i;

final class ae extends ah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f65a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ i e;
    final /* synthetic */ an f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ae(an anVar, i iVar, int i, String str, String str2, int i2, i iVar2) {
        super(iVar);
        this.f = anVar;
        this.f65a = i;
        this.b = str;
        this.c = str2;
        this.d = i2;
        this.e = iVar2;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.play.core.internal.ah
    public final void a() {
        try {
            ((t) this.f.e.c()).j(this.f.c, an.r(this.f65a, this.b, this.c, this.d), an.C(), new ah(this.f, this.e));
        } catch (RemoteException e2) {
            an.f68a.b("getChunkFileDescriptor(%s, %s, %d, session=%d)", this.b, this.c, Integer.valueOf(this.d), Integer.valueOf(this.f65a));
            this.e.d(new RuntimeException(e2));
        }
    }
}
