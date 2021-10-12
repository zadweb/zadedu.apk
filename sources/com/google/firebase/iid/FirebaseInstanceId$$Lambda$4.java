package com.google.firebase.iid;

import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.RequestDeduplicator;

/* access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
public final /* synthetic */ class FirebaseInstanceId$$Lambda$4 implements RequestDeduplicator.GetTokenRequest {
    private final FirebaseInstanceId arg$1;
    private final String arg$2;
    private final String arg$3;
    private final String arg$4;

    FirebaseInstanceId$$Lambda$4(FirebaseInstanceId firebaseInstanceId, String str, String str2, String str3) {
        this.arg$1 = firebaseInstanceId;
        this.arg$2 = str;
        this.arg$3 = str2;
        this.arg$4 = str3;
    }

    @Override // com.google.firebase.iid.RequestDeduplicator.GetTokenRequest
    public final Task start() {
        return this.arg$1.lambda$getInstanceId$2$FirebaseInstanceId(this.arg$2, this.arg$3, this.arg$4);
    }
}
