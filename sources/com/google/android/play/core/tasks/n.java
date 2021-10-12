package com.google.android.play.core.tasks;

import java.util.concurrent.CountDownLatch;

/* access modifiers changed from: package-private */
public final class n implements OnFailureListener, OnSuccessListener {

    /* renamed from: a  reason: collision with root package name */
    private final CountDownLatch f183a = new CountDownLatch(1);

    private n() {
    }

    /* synthetic */ n(byte[] bArr) {
    }

    public final void a() throws InterruptedException {
        this.f183a.await();
    }

    @Override // com.google.android.play.core.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        this.f183a.countDown();
    }

    @Override // com.google.android.play.core.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        this.f183a.countDown();
    }
}
