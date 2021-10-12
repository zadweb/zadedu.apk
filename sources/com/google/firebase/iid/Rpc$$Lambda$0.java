package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
public final /* synthetic */ class Rpc$$Lambda$0 implements Continuation {
    static final Continuation $instance = new Rpc$$Lambda$0();

    private Rpc$$Lambda$0() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return Rpc.lambda$registerRpc$0$Rpc(task);
    }
}
