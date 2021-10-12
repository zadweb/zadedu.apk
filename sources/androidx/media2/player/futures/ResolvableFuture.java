package androidx.media2.player.futures;

public final class ResolvableFuture<V> extends AbstractResolvableFuture<V> {
    public static <V> ResolvableFuture<V> create() {
        return new ResolvableFuture<>();
    }

    @Override // androidx.media2.player.futures.AbstractResolvableFuture
    public boolean set(V v) {
        return super.set(v);
    }

    @Override // androidx.media2.player.futures.AbstractResolvableFuture
    public boolean setException(Throwable th) {
        return super.setException(th);
    }

    private ResolvableFuture() {
    }
}
