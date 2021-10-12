package androidx.media2.exoplayer.external.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class SystemHandlerWrapper implements HandlerWrapper {
    private final Handler handler;

    public SystemHandlerWrapper(Handler handler2) {
        this.handler = handler2;
    }

    @Override // androidx.media2.exoplayer.external.util.HandlerWrapper
    public Looper getLooper() {
        return this.handler.getLooper();
    }

    @Override // androidx.media2.exoplayer.external.util.HandlerWrapper
    public Message obtainMessage(int i, Object obj) {
        return this.handler.obtainMessage(i, obj);
    }

    @Override // androidx.media2.exoplayer.external.util.HandlerWrapper
    public Message obtainMessage(int i, int i2, int i3) {
        return this.handler.obtainMessage(i, i2, i3);
    }

    @Override // androidx.media2.exoplayer.external.util.HandlerWrapper
    public Message obtainMessage(int i, int i2, int i3, Object obj) {
        return this.handler.obtainMessage(i, i2, i3, obj);
    }

    @Override // androidx.media2.exoplayer.external.util.HandlerWrapper
    public boolean sendEmptyMessage(int i) {
        return this.handler.sendEmptyMessage(i);
    }

    @Override // androidx.media2.exoplayer.external.util.HandlerWrapper
    public boolean sendEmptyMessageAtTime(int i, long j) {
        return this.handler.sendEmptyMessageAtTime(i, j);
    }

    @Override // androidx.media2.exoplayer.external.util.HandlerWrapper
    public void removeMessages(int i) {
        this.handler.removeMessages(i);
    }
}
