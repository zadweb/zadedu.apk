package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
@zzadh
public final class zzaoa {
    private final Object zzcvu = new Object();
    private final List<Runnable> zzcvv = new ArrayList();
    private boolean zzcvw = false;

    public final void zza(Runnable runnable, Executor executor) {
        synchronized (this.zzcvu) {
            if (this.zzcvw) {
                executor.execute(runnable);
            } else {
                this.zzcvv.add(new zzaob(executor, runnable));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0023, code lost:
        if (r2 >= r1) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        r3 = r0.get(r2);
        r2 = r2 + 1;
        ((java.lang.Runnable) r3).run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        r0 = r0;
        r1 = r0.size();
        r2 = 0;
     */
    public final void zzsm() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.zzcvu) {
            if (!this.zzcvw) {
                arrayList.addAll(this.zzcvv);
                this.zzcvv.clear();
                this.zzcvw = true;
            }
        }
    }
}
