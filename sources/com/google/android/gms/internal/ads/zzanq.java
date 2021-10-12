package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzanq implements Runnable {
    private final zzaoj zzbnu;
    private final zzank zzcvl;
    private final zzanz zzcvm;

    zzanq(zzaoj zzaoj, zzank zzank, zzanz zzanz) {
        this.zzbnu = zzaoj;
        this.zzcvl = zzank;
        this.zzcvm = zzanz;
    }

    public final void run() {
        zzaoj zzaoj = this.zzbnu;
        try {
            zzaoj.set(this.zzcvl.apply(this.zzcvm.get()));
        } catch (CancellationException unused) {
            zzaoj.cancel(true);
        } catch (ExecutionException e) {
            e = e;
            Throwable cause = e.getCause();
            if (cause != null) {
                e = cause;
            }
            zzaoj.setException(e);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            zzaoj.setException(e2);
        } catch (Exception e3) {
            zzaoj.setException(e3);
        }
    }
}
