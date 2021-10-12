package a.a.b.b;

import a.a.d.c;

/* compiled from: StartAppSDK */
public class l extends k {
    private final String name;
    private final c owner;
    private final String signature;

    public l(c cVar, String str, String str2) {
        this.owner = cVar;
        this.name = str;
        this.signature = str2;
    }

    @Override // a.a.b.b.a
    public c a() {
        return this.owner;
    }

    @Override // a.a.b.b.a
    public String b() {
        return this.name;
    }

    @Override // a.a.b.b.a
    public String c() {
        return this.signature;
    }
}
