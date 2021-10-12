package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzahx extends zzajx implements zzahw {
    private final Context mContext;
    private final Object mLock;
    private final zzaji zzbze;
    private final long zzclp;
    private final ArrayList<zzahn> zzcmd;
    private final List<zzahq> zzcme;
    private final HashSet<String> zzcmf;
    private final zzago zzcmg;

    public zzahx(Context context, zzaji zzaji, zzago zzago) {
        this(context, zzaji, zzago, ((Long) zzkb.zzik().zzd(zznk.zzaye)).longValue());
    }

    private zzahx(Context context, zzaji zzaji, zzago zzago, long j) {
        this.zzcmd = new ArrayList<>();
        this.zzcme = new ArrayList();
        this.zzcmf = new HashSet<>();
        this.mLock = new Object();
        this.mContext = context;
        this.zzbze = zzaji;
        this.zzcmg = zzago;
        this.zzclp = j;
    }

    private final zzajh zza(int i, String str, zzwx zzwx) {
        String str2;
        boolean z;
        long j;
        String str3;
        zzjn zzjn;
        boolean z2;
        int i2;
        zzjj zzjj = this.zzbze.zzcgs.zzccv;
        List<String> list = this.zzbze.zzcos.zzbsn;
        List<String> list2 = this.zzbze.zzcos.zzbso;
        List<String> list3 = this.zzbze.zzcos.zzces;
        int i3 = this.zzbze.zzcos.orientation;
        long j2 = this.zzbze.zzcos.zzbsu;
        String str4 = this.zzbze.zzcgs.zzccy;
        boolean z3 = this.zzbze.zzcos.zzceq;
        zzwy zzwy = this.zzbze.zzcod;
        long j3 = this.zzbze.zzcos.zzcer;
        zzjn zzjn2 = this.zzbze.zzacv;
        long j4 = this.zzbze.zzcos.zzcep;
        long j5 = this.zzbze.zzcoh;
        long j6 = this.zzbze.zzcos.zzceu;
        String str5 = this.zzbze.zzcos.zzcev;
        JSONObject jSONObject = this.zzbze.zzcob;
        zzaig zzaig = this.zzbze.zzcos.zzcfe;
        List<String> list4 = this.zzbze.zzcos.zzcff;
        List<String> list5 = this.zzbze.zzcos.zzcfg;
        boolean z4 = this.zzbze.zzcos.zzcfh;
        zzael zzael = this.zzbze.zzcos.zzcfi;
        StringBuilder sb = new StringBuilder("");
        List<zzahq> list6 = this.zzcme;
        if (list6 == null) {
            str2 = sb.toString();
            zzjn = zzjn2;
            z = z3;
            str3 = str5;
            j = j6;
        } else {
            Iterator<zzahq> it = list6.iterator();
            while (true) {
                zzjn = zzjn2;
                if (!it.hasNext()) {
                    break;
                }
                zzahq next = it.next();
                if (next != null) {
                    if (!TextUtils.isEmpty(next.zzbru)) {
                        String str6 = next.zzbru;
                        int i4 = next.errorCode;
                        if (i4 == 3) {
                            z2 = z3;
                            i2 = 1;
                        } else if (i4 == 4) {
                            z2 = z3;
                            i2 = 2;
                        } else if (i4 != 5) {
                            i2 = 6;
                            if (i4 == 6) {
                                z2 = z3;
                                i2 = 0;
                            } else if (i4 != 7) {
                                z2 = z3;
                            } else {
                                z2 = z3;
                                i2 = 3;
                            }
                        } else {
                            z2 = z3;
                            i2 = 4;
                        }
                        long j7 = next.zzbub;
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str6).length() + 33);
                        sb2.append(str6);
                        sb2.append(".");
                        sb2.append(i2);
                        sb2.append(".");
                        sb2.append(j7);
                        sb.append(String.valueOf(sb2.toString()).concat("_"));
                        it = it;
                        zzjn2 = zzjn;
                        str5 = str5;
                        j6 = j6;
                        z3 = z2;
                    } else {
                        it = it;
                    }
                }
                zzjn2 = zzjn;
            }
            z = z3;
            str3 = str5;
            j = j6;
            str2 = sb.substring(0, Math.max(0, sb.length() - 1));
        }
        return new zzajh(zzjj, null, list, i, list2, list3, i3, j2, str4, z, zzwx, null, str, zzwy, null, j3, zzjn, j4, j5, j, str3, jSONObject, null, zzaig, list4, list5, z4, zzael, str2, this.zzbze.zzcos.zzbsr, this.zzbze.zzcos.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, this.zzbze.zzcos.zzcfp, this.zzbze.zzcos.zzbsp, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq);
    }

    @Override // com.google.android.gms.internal.ads.zzajx
    public final void onStop() {
    }

    @Override // com.google.android.gms.internal.ads.zzahw
    public final void zza(String str, int i) {
    }

    @Override // com.google.android.gms.internal.ads.zzahw
    public final void zzcb(String str) {
        synchronized (this.mLock) {
            this.zzcmf.add(str);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajx
    public final void zzdn() {
        Object obj;
        for (zzwx zzwx : this.zzbze.zzcod.zzbsm) {
            String str = zzwx.zzbsb;
            Iterator<String> it = zzwx.zzbrt.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(next) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(next)) {
                    try {
                        next = new JSONObject(str).getString("class_name");
                    } catch (JSONException e) {
                        zzakb.zzb("Unable to determine custom event class name, skipping...", e);
                    }
                }
                Object obj2 = this.mLock;
                synchronized (obj2) {
                    try {
                        zzaib zzca = this.zzcmg.zzca(next);
                        if (!(zzca == null || zzca.zzpf() == null)) {
                            if (zzca.zzpe() != null) {
                                obj = obj2;
                                zzahn zzahn = new zzahn(this.mContext, next, str, zzwx, this.zzbze, zzca, this, this.zzclp);
                                zzahn.zza(this.zzcmg.zzos());
                                this.zzcmd.add(zzahn);
                            }
                        }
                        obj = obj2;
                        this.zzcme.add(new zzahs().zzcd(zzwx.zzbru).zzcc(next).zzg(0).zzad(7).zzpd());
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList<zzahn> arrayList = this.zzcmd;
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            zzahn zzahn2 = arrayList.get(i2);
            i2++;
            zzahn zzahn3 = zzahn2;
            if (hashSet.add(zzahn3.zzbth)) {
                zzahn3.zzoz();
            }
        }
        ArrayList<zzahn> arrayList2 = this.zzcmd;
        int size2 = arrayList2.size();
        while (i < size2) {
            zzahn zzahn4 = arrayList2.get(i);
            i++;
            zzahn zzahn5 = zzahn4;
            try {
                zzahn5.zzoz().get();
                synchronized (this.mLock) {
                    if (!TextUtils.isEmpty(zzahn5.zzbth)) {
                        this.zzcme.add(zzahn5.zzpa());
                    }
                }
                synchronized (this.mLock) {
                    if (this.zzcmf.contains(zzahn5.zzbth)) {
                        zzamu.zzsy.post(new zzahy(this, zza(-2, zzahn5.zzbth, zzahn5.zzpb())));
                        return;
                    }
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                synchronized (this.mLock) {
                    if (!TextUtils.isEmpty(zzahn5.zzbth)) {
                        this.zzcme.add(zzahn5.zzpa());
                    }
                }
            } catch (Exception e2) {
                try {
                    zzakb.zzc("Unable to resolve rewarded adapter.", e2);
                    synchronized (this.mLock) {
                        if (!TextUtils.isEmpty(zzahn5.zzbth)) {
                            this.zzcme.add(zzahn5.zzpa());
                        }
                    }
                } catch (Throwable th2) {
                    synchronized (this.mLock) {
                        if (!TextUtils.isEmpty(zzahn5.zzbth)) {
                            this.zzcme.add(zzahn5.zzpa());
                        }
                        throw th2;
                    }
                }
            }
        }
        zzamu.zzsy.post(new zzahz(this, zza(3, null, null)));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzl(zzajh zzajh) {
        this.zzcmg.zzot().zzb(zzajh);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzm(zzajh zzajh) {
        this.zzcmg.zzot().zzb(zzajh);
    }
}
