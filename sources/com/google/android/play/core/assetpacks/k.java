package com.google.android.play.core.assetpacks;

import java.util.concurrent.ThreadFactory;

/* access modifiers changed from: package-private */
public final /* synthetic */ class k implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    static final ThreadFactory f136a = new k();
    static final ThreadFactory b = new k(null);
    private final /* synthetic */ int c = 0;

    private k() {
    }

    private k(byte[] bArr) {
    }

    public final Thread newThread(Runnable runnable) {
        return this.c != 0 ? new Thread(runnable, "AssetPackBackgroundExecutor") : new Thread(runnable, "UpdateListenerExecutor");
    }
}