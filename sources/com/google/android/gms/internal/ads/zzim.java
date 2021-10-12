package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzim extends zzbfc<zzim> {
    private Integer zzamf = null;
    private Integer zzanx = null;

    public zzim() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0073, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* renamed from: zzo */
    public final zzim zza(zzbez zzbez) throws IOException {
        int zzacc;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                zzbez.getPosition();
                int zzacc2 = zzbez.zzacc();
                if (zzacc2 < 0 || zzacc2 > 2) {
                    StringBuilder sb = new StringBuilder(43);
                    sb.append(zzacc2);
                    sb.append(" is not a valid enum NetworkType");
                } else {
                    this.zzamf = Integer.valueOf(zzacc2);
                }
            } else if (zzabk == 16) {
                try {
                    zzacc = zzbez.zzacc();
                    if (zzacc < 0 || zzacc > 2) {
                        if (zzacc < 4 || zzacc > 4) {
                            StringBuilder sb2 = new StringBuilder(51);
                            sb2.append(zzacc);
                            sb2.append(" is not a valid enum CellularNetworkType");
                        }
                    }
                    this.zzanx = Integer.valueOf(zzacc);
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(zzbez.getPosition());
                    zza(zzbez, zzabk);
                }
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
        StringBuilder sb22 = new StringBuilder(51);
        sb22.append(zzacc);
        sb22.append(" is not a valid enum CellularNetworkType");
        throw new IllegalArgumentException(sb22.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzamf;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        Integer num2 = this.zzanx;
        if (num2 != null) {
            zzbfa.zzm(2, num2.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzamf;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        Integer num2 = this.zzanx;
        return num2 != null ? zzr + zzbfa.zzq(2, num2.intValue()) : zzr;
    }
}
