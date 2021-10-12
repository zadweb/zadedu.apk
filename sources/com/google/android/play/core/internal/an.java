package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;

final class an extends ah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ IBinder f158a;
    final /* synthetic */ ap b;

    an(ap apVar, IBinder iBinder) {
        this.b = apVar;
        this.f158a = iBinder;
    }

    @Override // com.google.android.play.core.internal.ah
    public final void a() {
        aq aqVar = this.b.f160a;
        aqVar.l = (IInterface) aqVar.h.a(this.f158a);
        aq.j(this.b.f160a);
        this.b.f160a.f = false;
        for (Runnable runnable : this.b.f160a.e) {
            runnable.run();
        }
        this.b.f160a.e.clear();
    }
}
