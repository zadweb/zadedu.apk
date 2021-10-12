package androidx.media2.session.futures;

public final class ResolvableFuture<V> extends AbstractResolvableFuture<V> {
    public static <V> ResolvableFuture<V> create() {
        return new ResolvableFuture<>();
    }

    @Override // androidx.media2.session.futures.AbstractResolvableFuture
    public boolean set(V v) {
        return super.set(v);
    }

    private ResolvableFuture() {
    }
}
