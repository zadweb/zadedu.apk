package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzadh
public final class zzxh implements zzww {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final boolean zzael;
    private final zzwy zzbtj;
    private final boolean zzbtn;
    private final boolean zzbto;
    private final zzaef zzbuc;
    private final long zzbud;
    private final int zzbue;
    private boolean zzbuf = false;
    private final Map<zzanz<zzxe>, zzxb> zzbug = new HashMap();
    private final String zzbuh;
    private List<zzxe> zzbui = new ArrayList();
    private final zzxn zzwh;

    public zzxh(Context context, zzaef zzaef, zzxn zzxn, zzwy zzwy, boolean z, boolean z2, String str, long j, long j2, int i, boolean z3) {
        this.mContext = context;
        this.zzbuc = zzaef;
        this.zzwh = zzxn;
        this.zzbtj = zzwy;
        this.zzael = z;
        this.zzbtn = z2;
        this.zzbuh = str;
        this.mStartTime = j;
        this.zzbud = j2;
        this.zzbue = 2;
        this.zzbto = z3;
    }

    private final void zza(zzanz<zzxe> zzanz) {
        zzakk.zzcrm.post(new zzxj(this, zzanz));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r4.hasNext() == false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = (com.google.android.gms.internal.ads.zzxe) r0.get();
        r3.zzbui.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r1 == null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        if (r1.zzbtv != 0) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        zza(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0038, code lost:
        com.google.android.gms.internal.ads.zzakb.zzc("Exception while processing an adapter; continuing with other adapters", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        zza((com.google.android.gms.internal.ads.zzanz<com.google.android.gms.internal.ads.zzxe>) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0048, code lost:
        return new com.google.android.gms.internal.ads.zzxe(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r4 = r4.iterator();
     */
    private final zzxe zzi(List<zzanz<zzxe>> list) {
        synchronized (this.mLock) {
            if (this.zzbuf) {
                return new zzxe(-1);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r13.zzbtj.zzbsy == -1) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r0 = r13.zzbtj.zzbsy;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r0 = 10000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r14 = r14.iterator();
        r3 = null;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r14.hasNext() == false) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r5 = r14.next();
        r6 = com.google.android.gms.ads.internal.zzbv.zzer().currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        if (r0 != 0) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        if (r5.isDone() == false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        r10 = r5.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        r10 = (com.google.android.gms.internal.ads.zzxe) r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        r10 = r5.get(r0, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0055, code lost:
        r13.zzbui.add(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        if (r10 == null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005e, code lost:
        if (r10.zzbtv != 0) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0060, code lost:
        r11 = r10.zzbua;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0062, code lost:
        if (r11 == null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0068, code lost:
        if (r11.zzmm() <= r2) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006a, code lost:
        r2 = r11.zzmm();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006e, code lost:
        r3 = r5;
        r4 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0071, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0073, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        com.google.android.gms.internal.ads.zzakb.zzc("Exception while processing an adapter; continuing with other adapters", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008e, code lost:
        java.lang.Math.max(r0 - (com.google.android.gms.ads.internal.zzbv.zzer().currentTimeMillis() - r6), 0L);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009b, code lost:
        throw r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009c, code lost:
        zza(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009f, code lost:
        if (r4 != null) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a7, code lost:
        return new com.google.android.gms.internal.ads.zzxe(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a8, code lost:
        return r4;
     */
    private final zzxe zzj(List<zzanz<zzxe>> list) {
        long currentTimeMillis;
        synchronized (this.mLock) {
            int i = -1;
            if (this.zzbuf) {
                return new zzxe(-1);
            }
        }
        long j = Math.max(j - (zzbv.zzer().currentTimeMillis() - currentTimeMillis), 0L);
    }

    @Override // com.google.android.gms.internal.ads.zzww
    public final void cancel() {
        synchronized (this.mLock) {
            this.zzbuf = true;
            for (zzxb zzxb : this.zzbug.values()) {
                zzxb.cancel();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzww
    public final zzxe zzh(List<zzwx> list) {
        zzakb.zzck("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzjn zzjn = this.zzbuc.zzacv;
        int[] iArr = new int[2];
        if (zzjn.zzard != null) {
            zzbv.zzfd();
            if (zzxg.zza(this.zzbuh, iArr)) {
                int i = 0;
                int i2 = iArr[0];
                int i3 = iArr[1];
                zzjn[] zzjnArr = zzjn.zzard;
                int length = zzjnArr.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    zzjn zzjn2 = zzjnArr[i];
                    if (i2 == zzjn2.width && i3 == zzjn2.height) {
                        zzjn = zzjn2;
                        break;
                    }
                    i++;
                }
            }
        }
        Iterator<zzwx> it = list.iterator();
        while (it.hasNext()) {
            zzwx next = it.next();
            String valueOf = String.valueOf(next.zzbrs);
            zzakb.zzdj(valueOf.length() != 0 ? "Trying mediation network: ".concat(valueOf) : new String("Trying mediation network: "));
            for (Iterator<String> it2 = next.zzbrt.iterator(); it2.hasNext(); it2 = it2) {
                zzxb zzxb = new zzxb(this.mContext, it2.next(), this.zzwh, this.zzbtj, next, this.zzbuc.zzccv, zzjn, this.zzbuc.zzacr, this.zzael, this.zzbtn, this.zzbuc.zzadj, this.zzbuc.zzads, this.zzbuc.zzcdk, this.zzbuc.zzcef, this.zzbto);
                zzanz<zzxe> zza = zzaki.zza(new zzxi(this, zzxb));
                this.zzbug.put(zza, zzxb);
                arrayList.add(zza);
                it = it;
                arrayList = arrayList;
            }
        }
        return this.zzbue != 2 ? zzi(arrayList) : zzj(arrayList);
    }

    @Override // com.google.android.gms.internal.ads.zzww
    public final List<zzxe> zzme() {
        return this.zzbui;
    }
}
