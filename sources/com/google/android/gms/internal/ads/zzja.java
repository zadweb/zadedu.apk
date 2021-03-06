package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzja extends zzbfc<zzja> {
    private Integer zzanu = null;
    private Integer zzape = null;
    private Integer zzapf = null;
    private zziw zzapn = null;
    private Integer zzapr = null;
    private Long zzaps = null;

    public zzja() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzx */
    public final zzja zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                int position = zzbez.getPosition();
                try {
                    this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(position);
                    zza(zzbez, zzabk);
                }
            } else if (zzabk == 18) {
                if (this.zzapn == null) {
                    this.zzapn = new zziw();
                }
                zzbez.zza(this.zzapn);
            } else if (zzabk == 24) {
                this.zzape = Integer.valueOf(zzbez.zzacc());
            } else if (zzabk == 32) {
                this.zzapf = Integer.valueOf(zzbez.zzacc());
            } else if (zzabk == 40) {
                this.zzapr = Integer.valueOf(zzbez.zzacc());
            } else if (zzabk == 48) {
                this.zzaps = Long.valueOf(zzbez.zzacd());
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzanu;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        zziw zziw = this.zzapn;
        if (zziw != null) {
            zzbfa.zza(2, zziw);
        }
        Integer num2 = this.zzape;
        if (num2 != null) {
            zzbfa.zzm(3, num2.intValue());
        }
        Integer num3 = this.zzapf;
        if (num3 != null) {
            zzbfa.zzm(4, num3.intValue());
        }
        Integer num4 = this.zzapr;
        if (num4 != null) {
            zzbfa.zzm(5, num4.intValue());
        }
        Long l = this.zzaps;
        if (l != null) {
            zzbfa.zza(6, l.longValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzanu;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        zziw zziw = this.zzapn;
        if (zziw != null) {
            zzr += zzbfa.zzb(2, zziw);
        }
        Integer num2 = this.zzape;
        if (num2 != null) {
            zzr += zzbfa.zzq(3, num2.intValue());
        }
        Integer num3 = this.zzapf;
        if (num3 != null) {
            zzr += zzbfa.zzq(4, num3.intValue());
        }
        Integer num4 = this.zzapr;
        if (num4 != null) {
            zzr += zzbfa.zzq(5, num4.intValue());
        }
        Long l = this.zzaps;
        return l != null ? zzr + zzbfa.zze(6, l.longValue()) : zzr;
    }
}
