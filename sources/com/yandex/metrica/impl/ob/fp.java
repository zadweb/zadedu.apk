package com.yandex.metrica.impl.ob;

import java.io.UnsupportedEncodingException;

public abstract class fp<T> extends fu<T> {

    /* renamed from: a  reason: collision with root package name */
    private final String f1145a;

    /* access modifiers changed from: protected */
    @Override // com.yandex.metrica.impl.ob.fu
    public abstract T b(ft ftVar) throws fr;

    static {
        String.format("application/json; charset=%s", "utf-8");
    }

    public fp(int i, String str, String str2) {
        super(i, str);
        this.f1145a = str2;
    }

    @Override // com.yandex.metrica.impl.ob.fu
    public byte[] c() {
        try {
            if (this.f1145a == null) {
                return null;
            }
            return this.f1145a.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }
}
