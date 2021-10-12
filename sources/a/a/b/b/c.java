package a.a.b.b;

import a.a.b.a;
import a.a.d.b;

/* compiled from: StartAppSDK */
public final class c implements b, b<Object> {

    /* renamed from: a  reason: collision with root package name */
    private final Class<?> f7a;

    public c(Class<?> cls) {
        h.b(cls, "jClass");
        this.f7a = cls;
    }

    @Override // a.a.b.b.b
    public Class<?> a() {
        return this.f7a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof c) && h.a(a.a(this), a.a((b) obj));
    }

    public int hashCode() {
        return a.a(this).hashCode();
    }

    public String toString() {
        return a().toString() + " (Kotlin reflection is not available)";
    }
}
