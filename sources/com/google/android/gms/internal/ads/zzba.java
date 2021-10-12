package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdRequest;
import com.mopub.mobileads.MoPubView;
import java.io.IOException;

public final class zzba extends zzbfc<zzba> {
    public String zzcw = null;
    public String zzcx = null;
    public String zzcz = null;
    public String zzda = null;
    public String zzdb = null;
    public String zzdc = null;
    public Long zzdd = null;
    private Long zzde = null;
    public Long zzdf = null;
    public Long zzdg = null;
    private Long zzdh = null;
    private Long zzdi = null;
    private Long zzdj = null;
    private Long zzdk = null;
    private Long zzdl = null;
    public Long zzdm = null;
    private String zzdn = null;
    public Long zzdo = null;
    public Long zzdp = null;
    public Long zzdq = null;
    public Long zzdr = null;
    private Long zzds = null;
    private Long zzdt = null;
    public Long zzdu = null;
    public Long zzdv = null;
    public Long zzdw = null;
    public String zzdx = null;
    public Long zzdy = null;
    public Long zzdz = null;
    public Long zzea = null;
    public Long zzeb = null;
    public Long zzec = null;
    public Long zzed = null;
    private zzbd zzee = null;
    public Long zzef = null;
    public Long zzeg = null;
    public Long zzeh = null;
    public Long zzei = null;
    public Long zzej = null;
    public Long zzek = null;
    public Integer zzel;
    public Integer zzem;
    public Long zzen = null;
    public Long zzeo = null;
    public Long zzep = null;
    private Long zzeq = null;
    private Long zzer = null;
    public Integer zzes;
    public zzbb zzet = null;
    public zzbb[] zzeu = zzbb.zzs();
    public zzbc zzev = null;
    private Long zzew = null;
    public Long zzex = null;
    public Long zzey = null;
    public Long zzez = null;
    public Long zzfa = null;
    public Long zzfb = null;
    public String zzfc = null;
    private Long zzfd = null;
    private Integer zzfe;
    private Integer zzff;
    private Integer zzfg;
    private Long zzfh = null;
    public String zzfi = null;
    public Integer zzfj;
    public Boolean zzfk = null;
    private String zzfl = null;
    public Long zzfm = null;
    public zzbf zzfn = null;

    public zzba() {
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* renamed from: zzb */
    public final zzba zza(zzbez zzbez) throws IOException {
        int zzacc;
        zzbfi zzbfi;
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    return this;
                case 10:
                    this.zzdc = zzbez.readString();
                    continue;
                case 18:
                    this.zzcw = zzbez.readString();
                    continue;
                case 24:
                    this.zzdd = Long.valueOf(zzbez.zzacd());
                    continue;
                case 32:
                    this.zzde = Long.valueOf(zzbez.zzacd());
                    continue;
                case 40:
                    this.zzdf = Long.valueOf(zzbez.zzacd());
                    continue;
                case 48:
                    this.zzdg = Long.valueOf(zzbez.zzacd());
                    continue;
                case 56:
                    this.zzdh = Long.valueOf(zzbez.zzacd());
                    continue;
                case 64:
                    this.zzdi = Long.valueOf(zzbez.zzacd());
                    continue;
                case 72:
                    this.zzdj = Long.valueOf(zzbez.zzacd());
                    continue;
                case 80:
                    this.zzdk = Long.valueOf(zzbez.zzacd());
                    continue;
                case 88:
                    this.zzdl = Long.valueOf(zzbez.zzacd());
                    continue;
                case 96:
                    this.zzdm = Long.valueOf(zzbez.zzacd());
                    continue;
                case 106:
                    this.zzdn = zzbez.readString();
                    continue;
                case 112:
                    this.zzdo = Long.valueOf(zzbez.zzacd());
                    continue;
                case 120:
                    this.zzdp = Long.valueOf(zzbez.zzacd());
                    continue;
                case 128:
                    this.zzdq = Long.valueOf(zzbez.zzacd());
                    continue;
                case 136:
                    this.zzdr = Long.valueOf(zzbez.zzacd());
                    continue;
                case 144:
                    this.zzds = Long.valueOf(zzbez.zzacd());
                    continue;
                case 152:
                    this.zzdt = Long.valueOf(zzbez.zzacd());
                    continue;
                case 160:
                    this.zzdu = Long.valueOf(zzbez.zzacd());
                    continue;
                case 168:
                    this.zzfh = Long.valueOf(zzbez.zzacd());
                    continue;
                case 176:
                    this.zzdv = Long.valueOf(zzbez.zzacd());
                    continue;
                case 184:
                    this.zzdw = Long.valueOf(zzbez.zzacd());
                    continue;
                case 194:
                    this.zzfi = zzbez.readString();
                    continue;
                case 200:
                    this.zzfm = Long.valueOf(zzbez.zzacd());
                    continue;
                case 208:
                    zzbez.getPosition();
                    zzacc = zzbez.zzacc();
                    if (zzacc < 0 || zzacc > 6) {
                        StringBuilder sb = new StringBuilder(44);
                        sb.append(zzacc);
                        sb.append(" is not a valid enum DeviceIdType");
                        break;
                    } else {
                        this.zzfj = Integer.valueOf(zzacc);
                        continue;
                    }
                    break;
                case 218:
                    this.zzcx = zzbez.readString();
                    continue;
                case 224:
                    this.zzfk = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 234:
                    this.zzdx = zzbez.readString();
                    continue;
                case 242:
                    this.zzfl = zzbez.readString();
                    continue;
                case 248:
                    this.zzdy = Long.valueOf(zzbez.zzacd());
                    continue;
                case 256:
                    this.zzdz = Long.valueOf(zzbez.zzacd());
                    continue;
                case 264:
                    this.zzea = Long.valueOf(zzbez.zzacd());
                    continue;
                case 274:
                    this.zzcz = zzbez.readString();
                    continue;
                case MoPubView.MoPubAdSizeInt.HEIGHT_280_INT /*{ENCODED_INT: 280}*/:
                    this.zzeb = Long.valueOf(zzbez.zzacd());
                    continue;
                case 288:
                    this.zzec = Long.valueOf(zzbez.zzacd());
                    continue;
                case 296:
                    this.zzed = Long.valueOf(zzbez.zzacd());
                    continue;
                case 306:
                    if (this.zzee == null) {
                        this.zzee = new zzbd();
                    }
                    zzbfi = this.zzee;
                    break;
                case 312:
                    this.zzef = Long.valueOf(zzbez.zzacd());
                    continue;
                case 320:
                    this.zzeg = Long.valueOf(zzbez.zzacd());
                    continue;
                case 328:
                    this.zzeh = Long.valueOf(zzbez.zzacd());
                    continue;
                case 336:
                    this.zzei = Long.valueOf(zzbez.zzacd());
                    continue;
                case 346:
                    int zzb = zzbfl.zzb(zzbez, 346);
                    zzbb[] zzbbArr = this.zzeu;
                    int length = zzbbArr == null ? 0 : zzbbArr.length;
                    int i = zzb + length;
                    zzbb[] zzbbArr2 = new zzbb[i];
                    if (length != 0) {
                        System.arraycopy(this.zzeu, 0, zzbbArr2, 0, length);
                    }
                    while (length < i - 1) {
                        zzbbArr2[length] = new zzbb();
                        zzbez.zza(zzbbArr2[length]);
                        zzbez.zzabk();
                        length++;
                    }
                    zzbbArr2[length] = new zzbb();
                    zzbez.zza(zzbbArr2[length]);
                    this.zzeu = zzbbArr2;
                    continue;
                case 352:
                    this.zzej = Long.valueOf(zzbez.zzacd());
                    continue;
                case 360:
                    this.zzek = Long.valueOf(zzbez.zzacd());
                    continue;
                case 370:
                    this.zzda = zzbez.readString();
                    continue;
                case 378:
                    this.zzdb = zzbez.readString();
                    continue;
                case 384:
                    zzbez.getPosition();
                    this.zzel = Integer.valueOf(zzaz.zzd(zzbez.zzacc()));
                    continue;
                case 392:
                    zzbez.getPosition();
                    this.zzem = Integer.valueOf(zzaz.zzd(zzbez.zzacc()));
                    continue;
                case 402:
                    if (this.zzet == null) {
                        this.zzet = new zzbb();
                    }
                    zzbfi = this.zzet;
                    break;
                case 408:
                    this.zzen = Long.valueOf(zzbez.zzacd());
                    continue;
                case 416:
                    this.zzeo = Long.valueOf(zzbez.zzacd());
                    continue;
                case 424:
                    this.zzep = Long.valueOf(zzbez.zzacd());
                    continue;
                case 432:
                    this.zzeq = Long.valueOf(zzbez.zzacd());
                    continue;
                case 440:
                    this.zzer = Long.valueOf(zzbez.zzacd());
                    continue;
                case 448:
                    zzbez.getPosition();
                    this.zzes = Integer.valueOf(zzaz.zzd(zzbez.zzacc()));
                    continue;
                case 458:
                    if (this.zzev == null) {
                        this.zzev = new zzbc();
                    }
                    zzbfi = this.zzev;
                    break;
                case 464:
                    this.zzew = Long.valueOf(zzbez.zzacd());
                    continue;
                case 472:
                    this.zzex = Long.valueOf(zzbez.zzacd());
                    continue;
                case 480:
                    this.zzey = Long.valueOf(zzbez.zzacd());
                    continue;
                case 488:
                    this.zzez = Long.valueOf(zzbez.zzacd());
                    continue;
                case 496:
                    this.zzfa = Long.valueOf(zzbez.zzacd());
                    continue;
                case 504:
                    this.zzfb = Long.valueOf(zzbez.zzacd());
                    continue;
                case AdRequest.MAX_CONTENT_URL_LENGTH:
                    this.zzfd = Long.valueOf(zzbez.zzacd());
                    continue;
                case 520:
                    zzbez.getPosition();
                    this.zzfe = Integer.valueOf(zzaz.zzf(zzbez.zzacc()));
                    continue;
                case 528:
                    zzbez.getPosition();
                    this.zzff = Integer.valueOf(zzaz.zze(zzbez.zzacc()));
                    continue;
                case 538:
                    this.zzfc = zzbez.readString();
                    continue;
                case 544:
                    try {
                        int zzacc2 = zzbez.zzacc();
                        if (zzacc2 < 0 || zzacc2 > 3) {
                            StringBuilder sb2 = new StringBuilder(45);
                            sb2.append(zzacc2);
                            sb2.append(" is not a valid enum DebuggerState");
                            break;
                        } else {
                            this.zzfg = Integer.valueOf(zzacc2);
                            continue;
                        }
                    } catch (IllegalArgumentException unused) {
                        zzbez.zzdc(zzbez.getPosition());
                        zza(zzbez, zzabk);
                    }
                    break;
                case 1610:
                    if (this.zzfn == null) {
                        this.zzfn = new zzbf();
                    }
                    zzbfi = this.zzfn;
                    break;
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        return this;
                    }
                    continue;
            }
            zzbez.zza(zzbfi);
        }
        StringBuilder sb3 = new StringBuilder(44);
        sb3.append(zzacc);
        sb3.append(" is not a valid enum DeviceIdType");
        throw new IllegalArgumentException(sb3.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.zzdc;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        String str2 = this.zzcw;
        if (str2 != null) {
            zzbfa.zzf(2, str2);
        }
        Long l = this.zzdd;
        if (l != null) {
            zzbfa.zzi(3, l.longValue());
        }
        Long l2 = this.zzde;
        if (l2 != null) {
            zzbfa.zzi(4, l2.longValue());
        }
        Long l3 = this.zzdf;
        if (l3 != null) {
            zzbfa.zzi(5, l3.longValue());
        }
        Long l4 = this.zzdg;
        if (l4 != null) {
            zzbfa.zzi(6, l4.longValue());
        }
        Long l5 = this.zzdh;
        if (l5 != null) {
            zzbfa.zzi(7, l5.longValue());
        }
        Long l6 = this.zzdi;
        if (l6 != null) {
            zzbfa.zzi(8, l6.longValue());
        }
        Long l7 = this.zzdj;
        if (l7 != null) {
            zzbfa.zzi(9, l7.longValue());
        }
        Long l8 = this.zzdk;
        if (l8 != null) {
            zzbfa.zzi(10, l8.longValue());
        }
        Long l9 = this.zzdl;
        if (l9 != null) {
            zzbfa.zzi(11, l9.longValue());
        }
        Long l10 = this.zzdm;
        if (l10 != null) {
            zzbfa.zzi(12, l10.longValue());
        }
        String str3 = this.zzdn;
        if (str3 != null) {
            zzbfa.zzf(13, str3);
        }
        Long l11 = this.zzdo;
        if (l11 != null) {
            zzbfa.zzi(14, l11.longValue());
        }
        Long l12 = this.zzdp;
        if (l12 != null) {
            zzbfa.zzi(15, l12.longValue());
        }
        Long l13 = this.zzdq;
        if (l13 != null) {
            zzbfa.zzi(16, l13.longValue());
        }
        Long l14 = this.zzdr;
        if (l14 != null) {
            zzbfa.zzi(17, l14.longValue());
        }
        Long l15 = this.zzds;
        if (l15 != null) {
            zzbfa.zzi(18, l15.longValue());
        }
        Long l16 = this.zzdt;
        if (l16 != null) {
            zzbfa.zzi(19, l16.longValue());
        }
        Long l17 = this.zzdu;
        if (l17 != null) {
            zzbfa.zzi(20, l17.longValue());
        }
        Long l18 = this.zzfh;
        if (l18 != null) {
            zzbfa.zzi(21, l18.longValue());
        }
        Long l19 = this.zzdv;
        if (l19 != null) {
            zzbfa.zzi(22, l19.longValue());
        }
        Long l20 = this.zzdw;
        if (l20 != null) {
            zzbfa.zzi(23, l20.longValue());
        }
        String str4 = this.zzfi;
        if (str4 != null) {
            zzbfa.zzf(24, str4);
        }
        Long l21 = this.zzfm;
        if (l21 != null) {
            zzbfa.zzi(25, l21.longValue());
        }
        Integer num = this.zzfj;
        if (num != null) {
            zzbfa.zzm(26, num.intValue());
        }
        String str5 = this.zzcx;
        if (str5 != null) {
            zzbfa.zzf(27, str5);
        }
        Boolean bool = this.zzfk;
        if (bool != null) {
            zzbfa.zzf(28, bool.booleanValue());
        }
        String str6 = this.zzdx;
        if (str6 != null) {
            zzbfa.zzf(29, str6);
        }
        String str7 = this.zzfl;
        if (str7 != null) {
            zzbfa.zzf(30, str7);
        }
        Long l22 = this.zzdy;
        if (l22 != null) {
            zzbfa.zzi(31, l22.longValue());
        }
        Long l23 = this.zzdz;
        if (l23 != null) {
            zzbfa.zzi(32, l23.longValue());
        }
        Long l24 = this.zzea;
        if (l24 != null) {
            zzbfa.zzi(33, l24.longValue());
        }
        String str8 = this.zzcz;
        if (str8 != null) {
            zzbfa.zzf(34, str8);
        }
        Long l25 = this.zzeb;
        if (l25 != null) {
            zzbfa.zzi(35, l25.longValue());
        }
        Long l26 = this.zzec;
        if (l26 != null) {
            zzbfa.zzi(36, l26.longValue());
        }
        Long l27 = this.zzed;
        if (l27 != null) {
            zzbfa.zzi(37, l27.longValue());
        }
        zzbd zzbd = this.zzee;
        if (zzbd != null) {
            zzbfa.zza(38, zzbd);
        }
        Long l28 = this.zzef;
        if (l28 != null) {
            zzbfa.zzi(39, l28.longValue());
        }
        Long l29 = this.zzeg;
        if (l29 != null) {
            zzbfa.zzi(40, l29.longValue());
        }
        Long l30 = this.zzeh;
        if (l30 != null) {
            zzbfa.zzi(41, l30.longValue());
        }
        Long l31 = this.zzei;
        if (l31 != null) {
            zzbfa.zzi(42, l31.longValue());
        }
        zzbb[] zzbbArr = this.zzeu;
        if (zzbbArr != null && zzbbArr.length > 0) {
            int i = 0;
            while (true) {
                zzbb[] zzbbArr2 = this.zzeu;
                if (i >= zzbbArr2.length) {
                    break;
                }
                zzbb zzbb = zzbbArr2[i];
                if (zzbb != null) {
                    zzbfa.zza(43, zzbb);
                }
                i++;
            }
        }
        Long l32 = this.zzej;
        if (l32 != null) {
            zzbfa.zzi(44, l32.longValue());
        }
        Long l33 = this.zzek;
        if (l33 != null) {
            zzbfa.zzi(45, l33.longValue());
        }
        String str9 = this.zzda;
        if (str9 != null) {
            zzbfa.zzf(46, str9);
        }
        String str10 = this.zzdb;
        if (str10 != null) {
            zzbfa.zzf(47, str10);
        }
        Integer num2 = this.zzel;
        if (num2 != null) {
            zzbfa.zzm(48, num2.intValue());
        }
        Integer num3 = this.zzem;
        if (num3 != null) {
            zzbfa.zzm(49, num3.intValue());
        }
        zzbb zzbb2 = this.zzet;
        if (zzbb2 != null) {
            zzbfa.zza(50, zzbb2);
        }
        Long l34 = this.zzen;
        if (l34 != null) {
            zzbfa.zzi(51, l34.longValue());
        }
        Long l35 = this.zzeo;
        if (l35 != null) {
            zzbfa.zzi(52, l35.longValue());
        }
        Long l36 = this.zzep;
        if (l36 != null) {
            zzbfa.zzi(53, l36.longValue());
        }
        Long l37 = this.zzeq;
        if (l37 != null) {
            zzbfa.zzi(54, l37.longValue());
        }
        Long l38 = this.zzer;
        if (l38 != null) {
            zzbfa.zzi(55, l38.longValue());
        }
        Integer num4 = this.zzes;
        if (num4 != null) {
            zzbfa.zzm(56, num4.intValue());
        }
        zzbc zzbc = this.zzev;
        if (zzbc != null) {
            zzbfa.zza(57, zzbc);
        }
        Long l39 = this.zzew;
        if (l39 != null) {
            zzbfa.zzi(58, l39.longValue());
        }
        Long l40 = this.zzex;
        if (l40 != null) {
            zzbfa.zzi(59, l40.longValue());
        }
        Long l41 = this.zzey;
        if (l41 != null) {
            zzbfa.zzi(60, l41.longValue());
        }
        Long l42 = this.zzez;
        if (l42 != null) {
            zzbfa.zzi(61, l42.longValue());
        }
        Long l43 = this.zzfa;
        if (l43 != null) {
            zzbfa.zzi(62, l43.longValue());
        }
        Long l44 = this.zzfb;
        if (l44 != null) {
            zzbfa.zzi(63, l44.longValue());
        }
        Long l45 = this.zzfd;
        if (l45 != null) {
            zzbfa.zzi(64, l45.longValue());
        }
        Integer num5 = this.zzfe;
        if (num5 != null) {
            zzbfa.zzm(65, num5.intValue());
        }
        Integer num6 = this.zzff;
        if (num6 != null) {
            zzbfa.zzm(66, num6.intValue());
        }
        String str11 = this.zzfc;
        if (str11 != null) {
            zzbfa.zzf(67, str11);
        }
        Integer num7 = this.zzfg;
        if (num7 != null) {
            zzbfa.zzm(68, num7.intValue());
        }
        zzbf zzbf = this.zzfn;
        if (zzbf != null) {
            zzbfa.zza(201, zzbf);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final int zzr() {
        int zzr = super.zzr();
        String str = this.zzdc;
        if (str != null) {
            zzr += zzbfa.zzg(1, str);
        }
        String str2 = this.zzcw;
        if (str2 != null) {
            zzr += zzbfa.zzg(2, str2);
        }
        Long l = this.zzdd;
        if (l != null) {
            zzr += zzbfa.zzd(3, l.longValue());
        }
        Long l2 = this.zzde;
        if (l2 != null) {
            zzr += zzbfa.zzd(4, l2.longValue());
        }
        Long l3 = this.zzdf;
        if (l3 != null) {
            zzr += zzbfa.zzd(5, l3.longValue());
        }
        Long l4 = this.zzdg;
        if (l4 != null) {
            zzr += zzbfa.zzd(6, l4.longValue());
        }
        Long l5 = this.zzdh;
        if (l5 != null) {
            zzr += zzbfa.zzd(7, l5.longValue());
        }
        Long l6 = this.zzdi;
        if (l6 != null) {
            zzr += zzbfa.zzd(8, l6.longValue());
        }
        Long l7 = this.zzdj;
        if (l7 != null) {
            zzr += zzbfa.zzd(9, l7.longValue());
        }
        Long l8 = this.zzdk;
        if (l8 != null) {
            zzr += zzbfa.zzd(10, l8.longValue());
        }
        Long l9 = this.zzdl;
        if (l9 != null) {
            zzr += zzbfa.zzd(11, l9.longValue());
        }
        Long l10 = this.zzdm;
        if (l10 != null) {
            zzr += zzbfa.zzd(12, l10.longValue());
        }
        String str3 = this.zzdn;
        if (str3 != null) {
            zzr += zzbfa.zzg(13, str3);
        }
        Long l11 = this.zzdo;
        if (l11 != null) {
            zzr += zzbfa.zzd(14, l11.longValue());
        }
        Long l12 = this.zzdp;
        if (l12 != null) {
            zzr += zzbfa.zzd(15, l12.longValue());
        }
        Long l13 = this.zzdq;
        if (l13 != null) {
            zzr += zzbfa.zzd(16, l13.longValue());
        }
        Long l14 = this.zzdr;
        if (l14 != null) {
            zzr += zzbfa.zzd(17, l14.longValue());
        }
        Long l15 = this.zzds;
        if (l15 != null) {
            zzr += zzbfa.zzd(18, l15.longValue());
        }
        Long l16 = this.zzdt;
        if (l16 != null) {
            zzr += zzbfa.zzd(19, l16.longValue());
        }
        Long l17 = this.zzdu;
        if (l17 != null) {
            zzr += zzbfa.zzd(20, l17.longValue());
        }
        Long l18 = this.zzfh;
        if (l18 != null) {
            zzr += zzbfa.zzd(21, l18.longValue());
        }
        Long l19 = this.zzdv;
        if (l19 != null) {
            zzr += zzbfa.zzd(22, l19.longValue());
        }
        Long l20 = this.zzdw;
        if (l20 != null) {
            zzr += zzbfa.zzd(23, l20.longValue());
        }
        String str4 = this.zzfi;
        if (str4 != null) {
            zzr += zzbfa.zzg(24, str4);
        }
        Long l21 = this.zzfm;
        if (l21 != null) {
            zzr += zzbfa.zzd(25, l21.longValue());
        }
        Integer num = this.zzfj;
        if (num != null) {
            zzr += zzbfa.zzq(26, num.intValue());
        }
        String str5 = this.zzcx;
        if (str5 != null) {
            zzr += zzbfa.zzg(27, str5);
        }
        Boolean bool = this.zzfk;
        if (bool != null) {
            bool.booleanValue();
            zzr += zzbfa.zzcd(28) + 1;
        }
        String str6 = this.zzdx;
        if (str6 != null) {
            zzr += zzbfa.zzg(29, str6);
        }
        String str7 = this.zzfl;
        if (str7 != null) {
            zzr += zzbfa.zzg(30, str7);
        }
        Long l22 = this.zzdy;
        if (l22 != null) {
            zzr += zzbfa.zzd(31, l22.longValue());
        }
        Long l23 = this.zzdz;
        if (l23 != null) {
            zzr += zzbfa.zzd(32, l23.longValue());
        }
        Long l24 = this.zzea;
        if (l24 != null) {
            zzr += zzbfa.zzd(33, l24.longValue());
        }
        String str8 = this.zzcz;
        if (str8 != null) {
            zzr += zzbfa.zzg(34, str8);
        }
        Long l25 = this.zzeb;
        if (l25 != null) {
            zzr += zzbfa.zzd(35, l25.longValue());
        }
        Long l26 = this.zzec;
        if (l26 != null) {
            zzr += zzbfa.zzd(36, l26.longValue());
        }
        Long l27 = this.zzed;
        if (l27 != null) {
            zzr += zzbfa.zzd(37, l27.longValue());
        }
        zzbd zzbd = this.zzee;
        if (zzbd != null) {
            zzr += zzbfa.zzb(38, zzbd);
        }
        Long l28 = this.zzef;
        if (l28 != null) {
            zzr += zzbfa.zzd(39, l28.longValue());
        }
        Long l29 = this.zzeg;
        if (l29 != null) {
            zzr += zzbfa.zzd(40, l29.longValue());
        }
        Long l30 = this.zzeh;
        if (l30 != null) {
            zzr += zzbfa.zzd(41, l30.longValue());
        }
        Long l31 = this.zzei;
        if (l31 != null) {
            zzr += zzbfa.zzd(42, l31.longValue());
        }
        zzbb[] zzbbArr = this.zzeu;
        if (zzbbArr != null && zzbbArr.length > 0) {
            int i = 0;
            while (true) {
                zzbb[] zzbbArr2 = this.zzeu;
                if (i >= zzbbArr2.length) {
                    break;
                }
                zzbb zzbb = zzbbArr2[i];
                if (zzbb != null) {
                    zzr += zzbfa.zzb(43, zzbb);
                }
                i++;
            }
        }
        Long l32 = this.zzej;
        if (l32 != null) {
            zzr += zzbfa.zzd(44, l32.longValue());
        }
        Long l33 = this.zzek;
        if (l33 != null) {
            zzr += zzbfa.zzd(45, l33.longValue());
        }
        String str9 = this.zzda;
        if (str9 != null) {
            zzr += zzbfa.zzg(46, str9);
        }
        String str10 = this.zzdb;
        if (str10 != null) {
            zzr += zzbfa.zzg(47, str10);
        }
        Integer num2 = this.zzel;
        if (num2 != null) {
            zzr += zzbfa.zzq(48, num2.intValue());
        }
        Integer num3 = this.zzem;
        if (num3 != null) {
            zzr += zzbfa.zzq(49, num3.intValue());
        }
        zzbb zzbb2 = this.zzet;
        if (zzbb2 != null) {
            zzr += zzbfa.zzb(50, zzbb2);
        }
        Long l34 = this.zzen;
        if (l34 != null) {
            zzr += zzbfa.zzd(51, l34.longValue());
        }
        Long l35 = this.zzeo;
        if (l35 != null) {
            zzr += zzbfa.zzd(52, l35.longValue());
        }
        Long l36 = this.zzep;
        if (l36 != null) {
            zzr += zzbfa.zzd(53, l36.longValue());
        }
        Long l37 = this.zzeq;
        if (l37 != null) {
            zzr += zzbfa.zzd(54, l37.longValue());
        }
        Long l38 = this.zzer;
        if (l38 != null) {
            zzr += zzbfa.zzd(55, l38.longValue());
        }
        Integer num4 = this.zzes;
        if (num4 != null) {
            zzr += zzbfa.zzq(56, num4.intValue());
        }
        zzbc zzbc = this.zzev;
        if (zzbc != null) {
            zzr += zzbfa.zzb(57, zzbc);
        }
        Long l39 = this.zzew;
        if (l39 != null) {
            zzr += zzbfa.zzd(58, l39.longValue());
        }
        Long l40 = this.zzex;
        if (l40 != null) {
            zzr += zzbfa.zzd(59, l40.longValue());
        }
        Long l41 = this.zzey;
        if (l41 != null) {
            zzr += zzbfa.zzd(60, l41.longValue());
        }
        Long l42 = this.zzez;
        if (l42 != null) {
            zzr += zzbfa.zzd(61, l42.longValue());
        }
        Long l43 = this.zzfa;
        if (l43 != null) {
            zzr += zzbfa.zzd(62, l43.longValue());
        }
        Long l44 = this.zzfb;
        if (l44 != null) {
            zzr += zzbfa.zzd(63, l44.longValue());
        }
        Long l45 = this.zzfd;
        if (l45 != null) {
            zzr += zzbfa.zzd(64, l45.longValue());
        }
        Integer num5 = this.zzfe;
        if (num5 != null) {
            zzr += zzbfa.zzq(65, num5.intValue());
        }
        Integer num6 = this.zzff;
        if (num6 != null) {
            zzr += zzbfa.zzq(66, num6.intValue());
        }
        String str11 = this.zzfc;
        if (str11 != null) {
            zzr += zzbfa.zzg(67, str11);
        }
        Integer num7 = this.zzfg;
        if (num7 != null) {
            zzr += zzbfa.zzq(68, num7.intValue());
        }
        zzbf zzbf = this.zzfn;
        return zzbf != null ? zzr + zzbfa.zzb(201, zzbf) : zzr;
    }
}
