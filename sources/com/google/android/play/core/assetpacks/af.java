package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.ah;
import com.google.android.play.core.internal.t;
import com.google.android.play.core.tasks.i;

/* access modifiers changed from: package-private */
public final class af extends ah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ i f66a;
    final /* synthetic */ an b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    af(an anVar, i iVar, i iVar2) {
        super(iVar);
        this.b = anVar;
        this.f66a = iVar2;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.play.core.internal.ah
    public final void a() {
        try {
            ((t) this.b.f.c()).i(this.b.c, an.C(), new aj(this.b, this.f66a));
        } catch (RemoteException e) {
            an.f68a.c(e, "keepAlive", new Object[0]);
        }
    }
}
