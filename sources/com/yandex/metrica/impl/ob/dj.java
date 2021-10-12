package com.yandex.metrica.impl.ob;

import android.content.Context;

public class dj extends dd {
    private dk c;

    /* access modifiers changed from: protected */
    @Override // com.yandex.metrica.impl.ob.dd
    public String f() {
        return "_serviceproviderspreferences";
    }

    public dj(Context context) {
        this(context, null);
    }

    public dj(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.yandex.metrica.impl.ob.dd
    public void h() {
        super.h();
        this.c = new dk("LOCATION_TRACKING_ENABLED");
    }

    public boolean a() {
        return this.b.getBoolean(this.c.b(), false);
    }

    public void b() {
        h(this.c.b()).k();
    }
}
