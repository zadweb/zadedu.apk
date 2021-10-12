package com.google.android.gms.common.api.internal;

/* access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public abstract class zaay {
    private final zaaw zaa;

    protected zaay(zaaw zaaw) {
        this.zaa = zaaw;
    }

    /* access modifiers changed from: protected */
    public abstract void zaa();

    public final void zaa(zaaz zaaz) {
        zaaz.zaa(zaaz).lock();
        try {
            if (zaaz.zab(zaaz) == this.zaa) {
                zaa();
                zaaz.zaa(zaaz).unlock();
            }
        } finally {
            zaaz.zaa(zaaz).unlock();
        }
    }
}
