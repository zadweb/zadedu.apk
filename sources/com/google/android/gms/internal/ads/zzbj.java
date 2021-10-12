package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzbj extends zzbh<Integer, Object> {
    public String zzcx;
    public String zzcz;
    public String zzda;
    public String zzdb;
    public long zzhx;

    public zzbj() {
        this.zzcx = "E";
        this.zzhx = -1;
        this.zzcz = "E";
        this.zzda = "E";
        this.zzdb = "E";
    }

    public zzbj(String str) {
        this();
        zzj(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzbh
    public final void zzj(String str) {
        HashMap zzk = zzk(str);
        if (zzk != null) {
            String str2 = "E";
            this.zzcx = zzk.get(0) == null ? str2 : (String) zzk.get(0);
            this.zzhx = zzk.get(1) == null ? -1 : ((Long) zzk.get(1)).longValue();
            this.zzcz = zzk.get(2) == null ? str2 : (String) zzk.get(2);
            this.zzda = zzk.get(3) == null ? str2 : (String) zzk.get(3);
            if (zzk.get(4) != null) {
                str2 = (String) zzk.get(4);
            }
            this.zzdb = str2;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzbh
    public final HashMap<Integer, Object> zzu() {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(0, this.zzcx);
        hashMap.put(4, this.zzdb);
        hashMap.put(3, this.zzda);
        hashMap.put(2, this.zzcz);
        hashMap.put(1, Long.valueOf(this.zzhx));
        return hashMap;
    }
}
