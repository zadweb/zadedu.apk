package a.a.a;

import a.a.b.b.h;
import java.util.List;

/* compiled from: StartAppSDK */
class i extends h {
    public static final <T> List<T> a() {
        return s.f4a;
    }

    public static final <T> List<T> a(T... tArr) {
        h.b(tArr, "elements");
        return tArr.length > 0 ? a.a(tArr) : g.a();
    }

    public static final <T> int a(List<? extends T> list) {
        h.b(list, "$receiver");
        return list.size() - 1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.List<? extends T> */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> List<T> b(List<? extends T> list) {
        h.b(list, "$receiver");
        int size = list.size();
        if (size == 0) {
            return g.a();
        }
        if (size != 1) {
            return list;
        }
        return g.a(list.get(0));
    }
}
