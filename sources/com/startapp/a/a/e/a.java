package com.startapp.a.a.e;

import com.startapp.a.a.a.c;
import java.io.DataInput;

/* compiled from: StartAppSDK */
public class a extends d {

    /* renamed from: a  reason: collision with root package name */
    private final int f250a;
    private final int b;

    public a(int i, int i2) {
        this.f250a = i;
        this.b = i2;
    }

    /* access modifiers changed from: protected */
    @Override // com.startapp.a.a.e.d
    public c a(DataInput dataInput) {
        c cVar = new c((long) (this.f250a * this.b));
        a(dataInput, cVar, (long) cVar.b());
        return cVar;
    }
}
