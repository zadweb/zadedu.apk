package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public final class zzabr extends zzabh {
    private final zzaqw zzbnd;
    private zzwy zzbtj;
    private zzww zzbzq;
    protected zzxe zzbzr;
    private boolean zzbzs;
    private final zznx zzvr;
    private zzxn zzwh;

    zzabr(Context context, zzaji zzaji, zzxn zzxn, zzabm zzabm, zznx zznx, zzaqw zzaqw) {
        super(context, zzaji, zzabm);
        this.zzwh = zzxn;
        this.zzbtj = zzaji.zzcod;
        this.zzvr = zznx;
        this.zzbnd = zzaqw;
    }

    @Override // com.google.android.gms.internal.ads.zzajx, com.google.android.gms.internal.ads.zzabh
    public final void onStop() {
        synchronized (this.zzbzh) {
            super.onStop();
            if (this.zzbzq != null) {
                this.zzbzq.cancel();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzabh
    public final zzajh zzaa(int i) {
        String str;
        boolean z;
        zzwy zzwy;
        long j;
        zzael zzael;
        String str2;
        String str3;
        boolean z2;
        zzwy zzwy2;
        long j2;
        Iterator<zzxe> it;
        String str4;
        int i2;
        zzaef zzaef = this.zzbze.zzcgs;
        zzjj zzjj = zzaef.zzccv;
        zzaqw zzaqw = this.zzbnd;
        List<String> list = this.zzbzf.zzbsn;
        List<String> list2 = this.zzbzf.zzbso;
        List<String> list3 = this.zzbzf.zzces;
        int i3 = this.zzbzf.orientation;
        long j3 = this.zzbzf.zzbsu;
        String str5 = zzaef.zzccy;
        boolean z3 = this.zzbzf.zzceq;
        zzxe zzxe = this.zzbzr;
        zzwx zzwx = zzxe != null ? zzxe.zzbtw : null;
        zzxe zzxe2 = this.zzbzr;
        zzxq zzxq = zzxe2 != null ? zzxe2.zzbtx : null;
        zzxe zzxe3 = this.zzbzr;
        String name = zzxe3 != null ? zzxe3.zzbty : AdMobAdapter.class.getName();
        zzwy zzwy3 = this.zzbtj;
        zzxe zzxe4 = this.zzbzr;
        zzxa zzxa = zzxe4 != null ? zzxe4.zzbtz : null;
        long j4 = this.zzbzf.zzcer;
        zzjn zzjn = this.zzbze.zzacv;
        long j5 = this.zzbzf.zzcep;
        long j6 = this.zzbze.zzcoh;
        long j7 = this.zzbzf.zzceu;
        String str6 = this.zzbzf.zzcev;
        JSONObject jSONObject = this.zzbze.zzcob;
        zzaig zzaig = this.zzbzf.zzcfe;
        List<String> list4 = this.zzbzf.zzcff;
        List<String> list5 = this.zzbzf.zzcfg;
        zzwy zzwy4 = this.zzbtj;
        boolean z4 = zzwy4 != null ? zzwy4.zzbsz : false;
        zzael zzael2 = this.zzbzf.zzcfi;
        zzww zzww = this.zzbzq;
        if (zzww != null) {
            List<zzxe> zzme = zzww.zzme();
            if (zzme == null) {
                zzwy = zzwy3;
                zzael = zzael2;
                str = str5;
                z = z3;
                j = j7;
                str2 = "";
            } else {
                Iterator<zzxe> it2 = zzme.iterator();
                String str7 = "";
                while (true) {
                    zzael = zzael2;
                    if (!it2.hasNext()) {
                        break;
                    }
                    zzxe next = it2.next();
                    if (next != null) {
                        it = it2;
                        if (next.zzbtw == null || TextUtils.isEmpty(next.zzbtw.zzbru)) {
                            zzwy2 = zzwy3;
                        } else {
                            String valueOf = String.valueOf(str7);
                            j2 = j7;
                            String str8 = next.zzbtw.zzbru;
                            int i4 = next.zzbtv;
                            zzwy2 = zzwy3;
                            if (i4 != -1) {
                                if (i4 == 0) {
                                    str4 = str5;
                                    z2 = z3;
                                    i2 = 0;
                                } else if (i4 == 1) {
                                    str4 = str5;
                                    z2 = z3;
                                    i2 = 1;
                                } else if (i4 == 3) {
                                    i2 = 2;
                                } else if (i4 != 4) {
                                    i2 = 5;
                                    if (i4 != 5) {
                                        i2 = 6;
                                    }
                                } else {
                                    str4 = str5;
                                    z2 = z3;
                                    i2 = 3;
                                }
                                long j8 = next.zzbub;
                                str3 = str4;
                                StringBuilder sb = new StringBuilder(String.valueOf(str8).length() + 33);
                                sb.append(str8);
                                sb.append(".");
                                sb.append(i2);
                                sb.append(".");
                                sb.append(j8);
                                String sb2 = sb.toString();
                                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(sb2).length());
                                sb3.append(valueOf);
                                sb3.append(sb2);
                                sb3.append("_");
                                str7 = sb3.toString();
                                it2 = it;
                                zzael2 = zzael;
                                j7 = j2;
                                zzwy3 = zzwy2;
                                z3 = z2;
                                str5 = str3;
                            } else {
                                i2 = 4;
                            }
                            str4 = str5;
                            z2 = z3;
                            long j82 = next.zzbub;
                            str3 = str4;
                            StringBuilder sb4 = new StringBuilder(String.valueOf(str8).length() + 33);
                            sb4.append(str8);
                            sb4.append(".");
                            sb4.append(i2);
                            sb4.append(".");
                            sb4.append(j82);
                            String sb22 = sb4.toString();
                            StringBuilder sb32 = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(sb22).length());
                            sb32.append(valueOf);
                            sb32.append(sb22);
                            sb32.append("_");
                            str7 = sb32.toString();
                            it2 = it;
                            zzael2 = zzael;
                            j7 = j2;
                            zzwy3 = zzwy2;
                            z3 = z2;
                            str5 = str3;
                        }
                    } else {
                        zzwy2 = zzwy3;
                        it = it2;
                    }
                    str3 = str5;
                    z2 = z3;
                    j2 = j7;
                    it2 = it;
                    zzael2 = zzael;
                    j7 = j2;
                    zzwy3 = zzwy2;
                    z3 = z2;
                    str5 = str3;
                }
                zzwy = zzwy3;
                str = str5;
                z = z3;
                j = j7;
                str2 = str7.substring(0, Math.max(0, str7.length() - 1));
            }
        } else {
            zzwy = zzwy3;
            zzael = zzael2;
            str = str5;
            z = z3;
            j = j7;
            str2 = null;
        }
        return new zzajh(zzjj, zzaqw, list, i, list2, list3, i3, j3, str, z, zzwx, zzxq, name, zzwy, zzxa, j4, zzjn, j5, j6, j, str6, jSONObject, null, zzaig, list4, list5, z4, zzael, str2, this.zzbzf.zzbsr, this.zzbzf.zzcfl, this.zzbze.zzcoq, this.zzbzf.zzzl, this.zzbze.zzcor, this.zzbzf.zzcfp, this.zzbzf.zzbsp, this.zzbzf.zzzm, this.zzbzf.zzcfq);
    }

    @Override // com.google.android.gms.internal.ads.zzabh
    public final void zze(long j) {
        Bundle bundle;
        synchronized (this.zzbzh) {
            this.zzbzq = this.zzbtj.zzbsx != -1 ? new zzxh(this.mContext, this.zzbze.zzcgs, this.zzwh, this.zzbtj, this.zzbzf.zzare, this.zzbzf.zzarg, this.zzbzf.zzcfj, j, ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), 2, this.zzbze.zzcor) : new zzxk(this.mContext, this.zzbze.zzcgs, this.zzwh, this.zzbtj, this.zzbzf.zzare, this.zzbzf.zzarg, this.zzbzf.zzcfj, j, ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), this.zzvr, this.zzbze.zzcor);
        }
        ArrayList arrayList = new ArrayList(this.zzbtj.zzbsm);
        Bundle bundle2 = this.zzbze.zzcgs.zzccv.zzaqg;
        if ((bundle2 == null || (bundle = bundle2.getBundle("com.google.ads.mediation.admob.AdMobAdapter")) == null) ? false : bundle.getBoolean("_skipMediation")) {
            ListIterator listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                if (!((zzwx) listIterator.next()).zzbrt.contains("com.google.ads.mediation.admob.AdMobAdapter")) {
                    listIterator.remove();
                }
            }
        }
        zzxe zzh = this.zzbzq.zzh(arrayList);
        this.zzbzr = zzh;
        int i = zzh.zzbtv;
        if (i != 0) {
            if (i != 1) {
                int i2 = this.zzbzr.zzbtv;
                StringBuilder sb = new StringBuilder(40);
                sb.append("Unexpected mediation result: ");
                sb.append(i2);
                throw new zzabk(sb.toString(), 0);
            }
            throw new zzabk("No fill from any mediation ad networks.", 3);
        } else if (this.zzbzr.zzbtw != null && this.zzbzr.zzbtw.zzbsf != null) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            zzakk.zzcrm.post(new zzabs(this, countDownLatch));
            try {
                countDownLatch.await(10, TimeUnit.SECONDS);
                synchronized (this.zzbzh) {
                    if (!this.zzbzs) {
                        throw new zzabk("View could not be prepared", 0);
                    } else if (this.zzbnd.isDestroyed()) {
                        throw new zzabk("Assets not loaded, web view is destroyed", 0);
                    }
                }
            } catch (InterruptedException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 38);
                sb2.append("Interrupted while waiting for latch : ");
                sb2.append(valueOf);
                throw new zzabk(sb2.toString(), 0);
            }
        }
    }
}
