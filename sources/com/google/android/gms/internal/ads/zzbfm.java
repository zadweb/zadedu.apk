package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfm extends zzbfc<zzbfm> {
    public String url = null;
    public Integer zzamf = null;
    private Integer zzecg = null;
    public String zzech = null;
    private String zzeci = null;
    public zzbfn zzecj = null;
    public zzbfu[] zzeck = zzbfu.zzagu();
    public String zzecl = null;
    public zzbft zzecm = null;
    private Boolean zzecn = null;
    private String[] zzeco = zzbfl.zzecd;
    private String zzecp = null;
    private Boolean zzecq = null;
    private Boolean zzecr = null;
    private byte[] zzecs = null;
    public zzbfv zzect = null;
    public String[] zzecu = zzbfl.zzecd;
    public String[] zzecv = zzbfl.zzecd;

    public zzbfm() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00da, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* renamed from: zzaa */
    public final zzbfm zza(zzbez zzbez) throws IOException {
        int zzabn;
        zzbfi zzbfi;
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    return this;
                case 10:
                    this.url = zzbez.readString();
                    continue;
                case 18:
                    this.zzech = zzbez.readString();
                    continue;
                case 26:
                    this.zzeci = zzbez.readString();
                    continue;
                case 34:
                    int zzb = zzbfl.zzb(zzbez, 34);
                    zzbfu[] zzbfuArr = this.zzeck;
                    int length = zzbfuArr == null ? 0 : zzbfuArr.length;
                    int i = zzb + length;
                    zzbfu[] zzbfuArr2 = new zzbfu[i];
                    if (length != 0) {
                        System.arraycopy(this.zzeck, 0, zzbfuArr2, 0, length);
                    }
                    while (length < i - 1) {
                        zzbfuArr2[length] = new zzbfu();
                        zzbez.zza(zzbfuArr2[length]);
                        zzbez.zzabk();
                        length++;
                    }
                    zzbfuArr2[length] = new zzbfu();
                    zzbez.zza(zzbfuArr2[length]);
                    this.zzeck = zzbfuArr2;
                    continue;
                case 40:
                    this.zzecn = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 50:
                    int zzb2 = zzbfl.zzb(zzbez, 50);
                    String[] strArr = this.zzeco;
                    int length2 = strArr == null ? 0 : strArr.length;
                    int i2 = zzb2 + length2;
                    String[] strArr2 = new String[i2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzeco, 0, strArr2, 0, length2);
                    }
                    while (length2 < i2 - 1) {
                        strArr2[length2] = zzbez.readString();
                        zzbez.zzabk();
                        length2++;
                    }
                    strArr2[length2] = zzbez.readString();
                    this.zzeco = strArr2;
                    continue;
                case 58:
                    this.zzecp = zzbez.readString();
                    continue;
                case 64:
                    this.zzecq = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 72:
                    this.zzecr = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 80:
                    zzbez.getPosition();
                    zzabn = zzbez.zzabn();
                    if (zzabn < 0 || zzabn > 9) {
                        StringBuilder sb = new StringBuilder(42);
                        sb.append(zzabn);
                        sb.append(" is not a valid enum ReportType");
                        break;
                    } else {
                        this.zzamf = Integer.valueOf(zzabn);
                        continue;
                    }
                    break;
                case 88:
                    try {
                        int zzabn2 = zzbez.zzabn();
                        if (zzabn2 < 0 || zzabn2 > 4) {
                            StringBuilder sb2 = new StringBuilder(39);
                            sb2.append(zzabn2);
                            sb2.append(" is not a valid enum Verdict");
                            break;
                        } else {
                            this.zzecg = Integer.valueOf(zzabn2);
                            continue;
                        }
                    } catch (IllegalArgumentException unused) {
                        zzbez.zzdc(zzbez.getPosition());
                        zza(zzbez, zzabk);
                    }
                    break;
                case 98:
                    if (this.zzecj == null) {
                        this.zzecj = new zzbfn();
                    }
                    zzbfi = this.zzecj;
                    break;
                case 106:
                    this.zzecl = zzbez.readString();
                    continue;
                case 114:
                    if (this.zzecm == null) {
                        this.zzecm = new zzbft();
                    }
                    zzbfi = this.zzecm;
                    break;
                case 122:
                    this.zzecs = zzbez.readBytes();
                    continue;
                case 138:
                    if (this.zzect == null) {
                        this.zzect = new zzbfv();
                    }
                    zzbfi = this.zzect;
                    break;
                case 162:
                    int zzb3 = zzbfl.zzb(zzbez, 162);
                    String[] strArr3 = this.zzecu;
                    int length3 = strArr3 == null ? 0 : strArr3.length;
                    int i3 = zzb3 + length3;
                    String[] strArr4 = new String[i3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzecu, 0, strArr4, 0, length3);
                    }
                    while (length3 < i3 - 1) {
                        strArr4[length3] = zzbez.readString();
                        zzbez.zzabk();
                        length3++;
                    }
                    strArr4[length3] = zzbez.readString();
                    this.zzecu = strArr4;
                    continue;
                case 170:
                    int zzb4 = zzbfl.zzb(zzbez, 170);
                    String[] strArr5 = this.zzecv;
                    int length4 = strArr5 == null ? 0 : strArr5.length;
                    int i4 = zzb4 + length4;
                    String[] strArr6 = new String[i4];
                    if (length4 != 0) {
                        System.arraycopy(this.zzecv, 0, strArr6, 0, length4);
                    }
                    while (length4 < i4 - 1) {
                        strArr6[length4] = zzbez.readString();
                        zzbez.zzabk();
                        length4++;
                    }
                    strArr6[length4] = zzbez.readString();
                    this.zzecv = strArr6;
                    continue;
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        return this;
                    }
                    continue;
            }
            zzbez.zza(zzbfi);
        }
        StringBuilder sb3 = new StringBuilder(42);
        sb3.append(zzabn);
        sb3.append(" is not a valid enum ReportType");
        throw new IllegalArgumentException(sb3.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.url;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        String str2 = this.zzech;
        if (str2 != null) {
            zzbfa.zzf(2, str2);
        }
        String str3 = this.zzeci;
        if (str3 != null) {
            zzbfa.zzf(3, str3);
        }
        zzbfu[] zzbfuArr = this.zzeck;
        int i = 0;
        if (zzbfuArr != null && zzbfuArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzbfu[] zzbfuArr2 = this.zzeck;
                if (i2 >= zzbfuArr2.length) {
                    break;
                }
                zzbfu zzbfu = zzbfuArr2[i2];
                if (zzbfu != null) {
                    zzbfa.zza(4, zzbfu);
                }
                i2++;
            }
        }
        Boolean bool = this.zzecn;
        if (bool != null) {
            zzbfa.zzf(5, bool.booleanValue());
        }
        String[] strArr = this.zzeco;
        if (strArr != null && strArr.length > 0) {
            int i3 = 0;
            while (true) {
                String[] strArr2 = this.zzeco;
                if (i3 >= strArr2.length) {
                    break;
                }
                String str4 = strArr2[i3];
                if (str4 != null) {
                    zzbfa.zzf(6, str4);
                }
                i3++;
            }
        }
        String str5 = this.zzecp;
        if (str5 != null) {
            zzbfa.zzf(7, str5);
        }
        Boolean bool2 = this.zzecq;
        if (bool2 != null) {
            zzbfa.zzf(8, bool2.booleanValue());
        }
        Boolean bool3 = this.zzecr;
        if (bool3 != null) {
            zzbfa.zzf(9, bool3.booleanValue());
        }
        Integer num = this.zzamf;
        if (num != null) {
            zzbfa.zzm(10, num.intValue());
        }
        Integer num2 = this.zzecg;
        if (num2 != null) {
            zzbfa.zzm(11, num2.intValue());
        }
        zzbfn zzbfn = this.zzecj;
        if (zzbfn != null) {
            zzbfa.zza(12, zzbfn);
        }
        String str6 = this.zzecl;
        if (str6 != null) {
            zzbfa.zzf(13, str6);
        }
        zzbft zzbft = this.zzecm;
        if (zzbft != null) {
            zzbfa.zza(14, zzbft);
        }
        byte[] bArr = this.zzecs;
        if (bArr != null) {
            zzbfa.zza(15, bArr);
        }
        zzbfv zzbfv = this.zzect;
        if (zzbfv != null) {
            zzbfa.zza(17, zzbfv);
        }
        String[] strArr3 = this.zzecu;
        if (strArr3 != null && strArr3.length > 0) {
            int i4 = 0;
            while (true) {
                String[] strArr4 = this.zzecu;
                if (i4 >= strArr4.length) {
                    break;
                }
                String str7 = strArr4[i4];
                if (str7 != null) {
                    zzbfa.zzf(20, str7);
                }
                i4++;
            }
        }
        String[] strArr5 = this.zzecv;
        if (strArr5 != null && strArr5.length > 0) {
            while (true) {
                String[] strArr6 = this.zzecv;
                if (i >= strArr6.length) {
                    break;
                }
                String str8 = strArr6[i];
                if (str8 != null) {
                    zzbfa.zzf(21, str8);
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
        String str = this.url;
        if (str != null) {
            zzr += zzbfa.zzg(1, str);
        }
        String str2 = this.zzech;
        if (str2 != null) {
            zzr += zzbfa.zzg(2, str2);
        }
        String str3 = this.zzeci;
        if (str3 != null) {
            zzr += zzbfa.zzg(3, str3);
        }
        zzbfu[] zzbfuArr = this.zzeck;
        int i = 0;
        if (zzbfuArr != null && zzbfuArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzbfu[] zzbfuArr2 = this.zzeck;
                if (i2 >= zzbfuArr2.length) {
                    break;
                }
                zzbfu zzbfu = zzbfuArr2[i2];
                if (zzbfu != null) {
                    zzr += zzbfa.zzb(4, zzbfu);
                }
                i2++;
            }
        }
        Boolean bool = this.zzecn;
        if (bool != null) {
            bool.booleanValue();
            zzr += zzbfa.zzcd(5) + 1;
        }
        String[] strArr = this.zzeco;
        if (strArr != null && strArr.length > 0) {
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                String[] strArr2 = this.zzeco;
                if (i3 >= strArr2.length) {
                    break;
                }
                String str4 = strArr2[i3];
                if (str4 != null) {
                    i5++;
                    i4 += zzbfa.zzeo(str4);
                }
                i3++;
            }
            zzr = zzr + i4 + (i5 * 1);
        }
        String str5 = this.zzecp;
        if (str5 != null) {
            zzr += zzbfa.zzg(7, str5);
        }
        Boolean bool2 = this.zzecq;
        if (bool2 != null) {
            bool2.booleanValue();
            zzr += zzbfa.zzcd(8) + 1;
        }
        Boolean bool3 = this.zzecr;
        if (bool3 != null) {
            bool3.booleanValue();
            zzr += zzbfa.zzcd(9) + 1;
        }
        Integer num = this.zzamf;
        if (num != null) {
            zzr += zzbfa.zzq(10, num.intValue());
        }
        Integer num2 = this.zzecg;
        if (num2 != null) {
            zzr += zzbfa.zzq(11, num2.intValue());
        }
        zzbfn zzbfn = this.zzecj;
        if (zzbfn != null) {
            zzr += zzbfa.zzb(12, zzbfn);
        }
        String str6 = this.zzecl;
        if (str6 != null) {
            zzr += zzbfa.zzg(13, str6);
        }
        zzbft zzbft = this.zzecm;
        if (zzbft != null) {
            zzr += zzbfa.zzb(14, zzbft);
        }
        byte[] bArr = this.zzecs;
        if (bArr != null) {
            zzr += zzbfa.zzb(15, bArr);
        }
        zzbfv zzbfv = this.zzect;
        if (zzbfv != null) {
            zzr += zzbfa.zzb(17, zzbfv);
        }
        String[] strArr3 = this.zzecu;
        if (strArr3 != null && strArr3.length > 0) {
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            while (true) {
                String[] strArr4 = this.zzecu;
                if (i6 >= strArr4.length) {
                    break;
                }
                String str7 = strArr4[i6];
                if (str7 != null) {
                    i8++;
                    i7 += zzbfa.zzeo(str7);
                }
                i6++;
            }
            zzr = zzr + i7 + (i8 * 2);
        }
        String[] strArr5 = this.zzecv;
        if (strArr5 == null || strArr5.length <= 0) {
            return zzr;
        }
        int i9 = 0;
        int i10 = 0;
        while (true) {
            String[] strArr6 = this.zzecv;
            if (i >= strArr6.length) {
                return zzr + i9 + (i10 * 2);
            }
            String str8 = strArr6[i];
            if (str8 != null) {
                i10++;
                i9 += zzbfa.zzeo(str8);
            }
            i++;
        }
    }
}
