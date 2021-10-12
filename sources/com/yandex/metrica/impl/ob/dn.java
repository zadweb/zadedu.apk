package com.yandex.metrica.impl.ob;

public class dn extends Cdo {
    private dm b;

    public dn(int i) {
        super(i);
        this.b = new dm(i);
    }

    @Override // com.yandex.metrica.impl.ob.Cdo
    public void a() {
        try {
            Thread.sleep((long) this.b.a());
        } catch (InterruptedException unused) {
        }
        super.a();
    }
}
