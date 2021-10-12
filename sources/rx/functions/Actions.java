package rx.functions;

public final class Actions {
    private static final EmptyAction EMPTY_ACTION = new EmptyAction();

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> empty() {
        return EMPTY_ACTION;
    }

    /* access modifiers changed from: package-private */
    public static final class EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> implements Action0, Action1<T0> {
        @Override // rx.functions.Action0
        public void call() {
        }

        @Override // rx.functions.Action1
        public void call(T0 t0) {
        }

        EmptyAction() {
        }
    }
}
