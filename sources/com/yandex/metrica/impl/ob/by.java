package com.yandex.metrica.impl.ob;

public class by extends cb {

    /* renamed from: a  reason: collision with root package name */
    private final dk f1048a = new dk("init_event_pref_key");
    private final dk b = new dk("first_event_pref_key");
    private final dk c = new dk("first_event_description_key");

    public by(bq bqVar) {
        super(bqVar);
    }

    public void a() {
        a(this.f1048a.b(), "DONE").h();
    }

    public void b() {
        a(this.b.b(), "DONE").h();
    }

    public String a(String str) {
        return b(this.f1048a.b(), str);
    }

    public String b(String str) {
        return b(this.b.b(), str);
    }

    public boolean c() {
        return a(null) != null;
    }

    public boolean d() {
        return b(null) != null;
    }

    public void c(String str) {
        a(this.c.b(), str).h();
    }

    public String d(String str) {
        return b(this.c.b(), str);
    }

    public void e() {
        r(this.c.b()).h();
    }
}
