package com.google.firebase.iid;

import android.os.Bundle;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

/* access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
public final /* synthetic */ class Rpc$$Lambda$4 implements SuccessContinuation {
    static final SuccessContinuation $instance = new Rpc$$Lambda$4();

    private Rpc$$Lambda$4() {
    }

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object obj) {
        return Rpc.lambda$registerRpcViaIntent$1$Rpc((Bundle) obj);
    }
}
