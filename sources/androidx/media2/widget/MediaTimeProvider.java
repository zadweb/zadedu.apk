package androidx.media2.widget;

/* access modifiers changed from: package-private */
public interface MediaTimeProvider {

    public interface OnMediaTimeListener {
    }

    void cancelNotifications(OnMediaTimeListener onMediaTimeListener);

    void scheduleUpdate(OnMediaTimeListener onMediaTimeListener);
}
