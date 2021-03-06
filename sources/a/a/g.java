package a.a;

import a.a.b.a.a;
import a.a.b.b.e;
import a.a.b.b.h;
import java.io.Serializable;

/* compiled from: StartAppSDK */
final class g<T> implements c<T>, Serializable {
    private volatile Object _value;
    private a<? extends T> initializer;
    private final Object lock;

    public g(a<? extends T> aVar, Object obj) {
        h.b(aVar, "initializer");
        this.initializer = aVar;
        this._value = i.f12a;
        this.lock = obj == null ? this : obj;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(a aVar, Object obj, int i, e eVar) {
        this(aVar, (i & 2) != 0 ? null : obj);
    }

    @Override // a.a.c
    public T a() {
        T t;
        T t2 = (T) this._value;
        if (t2 != i.f12a) {
            return t2;
        }
        synchronized (this.lock) {
            t = (T) this._value;
            if (t == i.f12a) {
                a<? extends T> aVar = this.initializer;
                if (aVar == null) {
                    h.a();
                }
                t = (T) aVar.a();
                this._value = t;
                this.initializer = null;
            }
        }
        return t;
    }

    public boolean b() {
        return this._value != i.f12a;
    }

    public String toString() {
        return b() ? String.valueOf(a()) : "Lazy value not initialized yet.";
    }
}
