package rx.internal.util;

import rx.functions.Func1;

public final class UtilityFunctions {
    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    public enum AlwaysTrue implements Func1<Object, Boolean> {
        INSTANCE;

        @Override // rx.functions.Func1
        public Boolean call(Object obj) {
            return true;
        }
    }
}
