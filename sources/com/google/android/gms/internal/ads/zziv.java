package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziv extends zzbfc<zziv> {
    private Integer zzanu = null;
    private zziw zzapn = null;
    private zzis zzapo = null;

    public zziv() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzs */
    public final zziv zza(zzbez zzbez) throws IOException {
        zzbfi zzbfi;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk != 8) {
                if (zzabk == 18) {
                    if (this.zzapn == null) {
                        this.zzapn = new zziw();
                    }
                    zzbfi = this.zzapn;
                } else if (zzabk == 26) {
                    if (this.zzapo == null) {
                        this.zzapo = new zzis();
                    }
                    zzbfi = this.zzapo;
                } else if (!super.zza(zzbez, zzabk)) {
                    return this;
                }
                zzbez.zza(zzbfi);
            } else {
                int position = zzbez.getPosition();
                try {
                    this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(position);
                    zza(zzbez, zzabk);
                }
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
        zzis zzis = this.zzapo;
        if (zzis != null) {
            zzbfa.zza(3, zzis);
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
        zzis zzis = this.zzapo;
        return zzis != null ? zzr + zzbfa.zzb(3, zzis) : zzr;
    }
}
