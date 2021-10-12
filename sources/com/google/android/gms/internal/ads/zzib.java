package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzib extends zzbfc<zzib> {
    public Integer zzalt = null;
    private Integer zzalu = null;
    private zzid zzalv = null;
    public zzie zzalw = null;
    private zzic[] zzalx = zzic.zzhr();
    private zzif zzaly = null;
    private zzio zzalz = null;
    private zzin zzama = null;
    private zzik zzamb = null;
    private zzil zzamc = null;
    private zziu[] zzamd = zziu.zzhu();

    public zzib() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zze */
    public final zzib zza(zzbez zzbez) throws IOException {
        int zzacc;
        zzbfi zzbfi;
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    return this;
                case 56:
                    zzbez.getPosition();
                    zzacc = zzbez.zzacc();
                    if (zzacc < 0 || zzacc > 9) {
                        StringBuilder sb = new StringBuilder(43);
                        sb.append(zzacc);
                        sb.append(" is not a valid enum AdInitiater");
                        break;
                    } else {
                        this.zzalt = Integer.valueOf(zzacc);
                        continue;
                    }
                    break;
                case 64:
                    int position = zzbez.getPosition();
                    try {
                        this.zzalu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException unused) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                    }
                case 74:
                    if (this.zzalv == null) {
                        this.zzalv = new zzid();
                    }
                    zzbfi = this.zzalv;
                    break;
                case 82:
                    if (this.zzalw == null) {
                        this.zzalw = new zzie();
                    }
                    zzbfi = this.zzalw;
                    break;
                case 90:
                    int zzb = zzbfl.zzb(zzbez, 90);
                    zzic[] zzicArr = this.zzalx;
                    int length = zzicArr == null ? 0 : zzicArr.length;
                    int i = zzb + length;
                    zzic[] zzicArr2 = new zzic[i];
                    if (length != 0) {
                        System.arraycopy(this.zzalx, 0, zzicArr2, 0, length);
                    }
                    while (length < i - 1) {
                        zzicArr2[length] = new zzic();
                        zzbez.zza(zzicArr2[length]);
                        zzbez.zzabk();
                        length++;
                    }
                    zzicArr2[length] = new zzic();
                    zzbez.zza(zzicArr2[length]);
                    this.zzalx = zzicArr2;
                    continue;
                case 98:
                    if (this.zzaly == null) {
                        this.zzaly = new zzif();
                    }
                    zzbfi = this.zzaly;
                    break;
                case 106:
                    if (this.zzalz == null) {
                        this.zzalz = new zzio();
                    }
                    zzbfi = this.zzalz;
                    break;
                case 114:
                    if (this.zzama == null) {
                        this.zzama = new zzin();
                    }
                    zzbfi = this.zzama;
                    break;
                case 122:
                    if (this.zzamb == null) {
                        this.zzamb = new zzik();
                    }
                    zzbfi = this.zzamb;
                    break;
                case 130:
                    if (this.zzamc == null) {
                        this.zzamc = new zzil();
                    }
                    zzbfi = this.zzamc;
                    break;
                case 138:
                    int zzb2 = zzbfl.zzb(zzbez, 138);
                    zziu[] zziuArr = this.zzamd;
                    int length2 = zziuArr == null ? 0 : zziuArr.length;
                    int i2 = zzb2 + length2;
                    zziu[] zziuArr2 = new zziu[i2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzamd, 0, zziuArr2, 0, length2);
                    }
                    while (length2 < i2 - 1) {
                        zziuArr2[length2] = new zziu();
                        zzbez.zza(zziuArr2[length2]);
                        zzbez.zzabk();
                        length2++;
                    }
                    zziuArr2[length2] = new zziu();
                    zzbez.zza(zziuArr2[length2]);
                    this.zzamd = zziuArr2;
                    continue;
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        return this;
                    }
                    continue;
            }
            zzbez.zza(zzbfi);
        }
        StringBuilder sb2 = new StringBuilder(43);
        sb2.append(zzacc);
        sb2.append(" is not a valid enum AdInitiater");
        throw new IllegalArgumentException(sb2.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzalt;
        if (num != null) {
            zzbfa.zzm(7, num.intValue());
        }
        Integer num2 = this.zzalu;
        if (num2 != null) {
            zzbfa.zzm(8, num2.intValue());
        }
        zzid zzid = this.zzalv;
        if (zzid != null) {
            zzbfa.zza(9, zzid);
        }
        zzie zzie = this.zzalw;
        if (zzie != null) {
            zzbfa.zza(10, zzie);
        }
        zzic[] zzicArr = this.zzalx;
        int i = 0;
        if (zzicArr != null && zzicArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzic[] zzicArr2 = this.zzalx;
                if (i2 >= zzicArr2.length) {
                    break;
                }
                zzic zzic = zzicArr2[i2];
                if (zzic != null) {
                    zzbfa.zza(11, zzic);
                }
                i2++;
            }
        }
        zzif zzif = this.zzaly;
        if (zzif != null) {
            zzbfa.zza(12, zzif);
        }
        zzio zzio = this.zzalz;
        if (zzio != null) {
            zzbfa.zza(13, zzio);
        }
        zzin zzin = this.zzama;
        if (zzin != null) {
            zzbfa.zza(14, zzin);
        }
        zzik zzik = this.zzamb;
        if (zzik != null) {
            zzbfa.zza(15, zzik);
        }
        zzil zzil = this.zzamc;
        if (zzil != null) {
            zzbfa.zza(16, zzil);
        }
        zziu[] zziuArr = this.zzamd;
        if (zziuArr != null && zziuArr.length > 0) {
            while (true) {
                zziu[] zziuArr2 = this.zzamd;
                if (i >= zziuArr2.length) {
                    break;
                }
                zziu zziu = zziuArr2[i];
                if (zziu != null) {
                    zzbfa.zza(17, zziu);
                }
                i++;
            }
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzalt;
        if (num != null) {
            zzr += zzbfa.zzq(7, num.intValue());
        }
        Integer num2 = this.zzalu;
        if (num2 != null) {
            zzr += zzbfa.zzq(8, num2.intValue());
        }
        zzid zzid = this.zzalv;
        if (zzid != null) {
            zzr += zzbfa.zzb(9, zzid);
        }
        zzie zzie = this.zzalw;
        if (zzie != null) {
            zzr += zzbfa.zzb(10, zzie);
        }
        zzic[] zzicArr = this.zzalx;
        int i = 0;
        if (zzicArr != null && zzicArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzic[] zzicArr2 = this.zzalx;
                if (i2 >= zzicArr2.length) {
                    break;
                }
                zzic zzic = zzicArr2[i2];
                if (zzic != null) {
                    zzr += zzbfa.zzb(11, zzic);
                }
                i2++;
            }
        }
        zzif zzif = this.zzaly;
        if (zzif != null) {
            zzr += zzbfa.zzb(12, zzif);
        }
        zzio zzio = this.zzalz;
        if (zzio != null) {
            zzr += zzbfa.zzb(13, zzio);
        }
        zzin zzin = this.zzama;
        if (zzin != null) {
            zzr += zzbfa.zzb(14, zzin);
        }
        zzik zzik = this.zzamb;
        if (zzik != null) {
            zzr += zzbfa.zzb(15, zzik);
        }
        zzil zzil = this.zzamc;
        if (zzil != null) {
            zzr += zzbfa.zzb(16, zzil);
        }
        zziu[] zziuArr = this.zzamd;
        if (zziuArr != null && zziuArr.length > 0) {
            while (true) {
                zziu[] zziuArr2 = this.zzamd;
                if (i >= zziuArr2.length) {
                    break;
                }
                zziu zziu = zziuArr2[i];
                if (zziu != null) {
                    zzr += zzbfa.zzb(17, zziu);
                }
                i++;
            }
        }
        return zzr;
    }
}
