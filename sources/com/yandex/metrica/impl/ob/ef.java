package com.yandex.metrica.impl.ob;

import android.content.Context;

public class ef extends dy {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f1114a = new Object();
    private static volatile ef b;
    private dy c;

    public static ef a(Context context) {
        if (b == null) {
            synchronized (f1114a) {
                if (b == null) {
                    b = new ef(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    ef(Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
            this.c = new eb(context);
        } else {
            this.c = new ec();
        }
    }

    @Override // com.yandex.metrica.impl.ob.ed
    public synchronized void a() {
        this.c.a();
    }

    @Override // com.yandex.metrica.impl.ob.ed
    public synchronized void b() {
        this.c.b();
    }

    @Override // com.yandex.metrica.impl.ob.dy
    public synchronized void a(eh ehVar) {
        this.c.a(ehVar);
    }

    @Override // com.yandex.metrica.impl.ob.dy
    public synchronized void a(ea eaVar) {
        this.c.a(eaVar);
    }
}
