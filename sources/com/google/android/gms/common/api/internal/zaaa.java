package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zaz;
import java.util.Set;

public final class zaaa implements zaaw {
    private final zaaz zaa;
    private boolean zab = false;

    public zaaa(zaaz zaaz) {
        this.zaa = zaaz;
    }

    @Override // com.google.android.gms.common.api.internal.zaaw
    public final void zaa() {
    }

    @Override // com.google.android.gms.common.api.internal.zaaw
    public final void zaa(Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zaaw
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zaaw
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T zaa(T t) {
        return (T) zab(t);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: T extends com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.zaaw
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zab(T t) {
        try {
            this.zaa.zad.zae.zaa(t);
            zaar zaar = this.zaa.zad;
            Api.Client client = zaar.zab.get(t.getClientKey());
            Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
            if (client.isConnected() || !this.zaa.zab.containsKey(t.getClientKey())) {
                boolean z = client instanceof zaz;
                Object obj = client;
                if (z) {
                    zaz zaz = (zaz) client;
                    obj = zaz.zaa();
                }
                t.run(obj == 1 ? 1 : 0);
                return t;
            }
            t.setFailedResult(new Status(17));
            return t;
        } catch (DeadObjectException unused) {
            this.zaa.zaa(new zaad(this, this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaaw
    public final boolean zab() {
        if (this.zab) {
            return false;
        }
        Set<zaci> set = this.zaa.zad.zad;
        if (set == null || set.isEmpty()) {
            this.zaa.zaa((ConnectionResult) null);
            return true;
        }
        this.zab = true;
        for (zaci zaci : set) {
            zaci.zaa();
        }
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.zaaw
    public final void zac() {
        if (this.zab) {
            this.zab = false;
            this.zaa.zaa(new zaac(this, this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaaw
    public final void zaa(int i) {
        this.zaa.zaa((ConnectionResult) null);
        this.zaa.zae.zaa(i, this.zab);
    }

    public final void zad() {
        if (this.zab) {
            this.zab = false;
            this.zaa.zad.zae.zaa();
            zab();
        }
    }
}
