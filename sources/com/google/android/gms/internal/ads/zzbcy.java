package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;
import com.yandex.metrica.impl.r;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class zzbcy<T> implements zzbdm<T> {
    private static final Unsafe zzdwf = zzbek.zzagh();
    private final int[] zzdwg;
    private final Object[] zzdwh;
    private final int zzdwi;
    private final int zzdwj;
    private final int zzdwk;
    private final zzbcu zzdwl;
    private final boolean zzdwm;
    private final boolean zzdwn;
    private final boolean zzdwo;
    private final boolean zzdwp;
    private final int[] zzdwq;
    private final int[] zzdwr;
    private final int[] zzdws;
    private final zzbdc zzdwt;
    private final zzbce zzdwu;
    private final zzbee<?, ?> zzdwv;
    private final zzbbd<?> zzdww;
    private final zzbcp zzdwx;

    private zzbcy(int[] iArr, Object[] objArr, int i, int i2, int i3, zzbcu zzbcu, boolean z, boolean z2, int[] iArr2, int[] iArr3, int[] iArr4, zzbdc zzbdc, zzbce zzbce, zzbee<?, ?> zzbee, zzbbd<?> zzbbd, zzbcp zzbcp) {
        this.zzdwg = iArr;
        this.zzdwh = objArr;
        this.zzdwi = i;
        this.zzdwj = i2;
        this.zzdwk = i3;
        this.zzdwn = zzbcu instanceof zzbbo;
        this.zzdwo = z;
        this.zzdwm = zzbbd != null && zzbbd.zzh(zzbcu);
        this.zzdwp = false;
        this.zzdwq = iArr2;
        this.zzdwr = iArr3;
        this.zzdws = iArr4;
        this.zzdwt = zzbdc;
        this.zzdwu = zzbce;
        this.zzdwv = zzbee;
        this.zzdww = zzbbd;
        this.zzdwl = zzbcu;
        this.zzdwx = zzbcp;
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzbae zzbae) throws IOException {
        return zzbad.zza(i, bArr, i2, i3, zzz(obj), zzbae);
    }

    private static int zza(zzbdm<?> zzbdm, int i, byte[] bArr, int i2, int i3, zzbbt<?> zzbbt, zzbae zzbae) throws IOException {
        int zza = zza((zzbdm) zzbdm, bArr, i2, i3, zzbae);
        while (true) {
            zzbbt.add(zzbae.zzdpn);
            if (zza >= i3) {
                break;
            }
            int zza2 = zzbad.zza(bArr, zza, zzbae);
            if (i != zzbae.zzdpl) {
                break;
            }
            zza = zza((zzbdm) zzbdm, bArr, zza2, i3, zzbae);
        }
        return zza;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v1, resolved type: com.google.android.gms.internal.ads.zzbcy */
    /* JADX WARN: Multi-variable type inference failed */
    private static int zza(zzbdm zzbdm, byte[] bArr, int i, int i2, int i3, zzbae zzbae) throws IOException {
        zzbcy zzbcy = (zzbcy) zzbdm;
        Object newInstance = zzbcy.newInstance();
        int zza = zzbcy.zza(newInstance, bArr, i, i2, i3, zzbae);
        zzbcy.zzo(newInstance);
        zzbae.zzdpn = newInstance;
        return zza;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: com.google.android.gms.internal.ads.zzbdm */
    /* JADX WARN: Multi-variable type inference failed */
    private static int zza(zzbdm zzbdm, byte[] bArr, int i, int i2, zzbae zzbae) throws IOException {
        int i3 = i + 1;
        byte b = bArr[i];
        byte b2 = b;
        if (b < 0) {
            i3 = zzbad.zza(b, bArr, i3, zzbae);
            b2 = zzbae.zzdpl;
        }
        if (b2 < 0 || b2 > i2 - i3) {
            throw zzbbu.zzadl();
        }
        Object newInstance = zzbdm.newInstance();
        int i4 = (b2 == 1 ? 1 : 0) + i3;
        zzbdm.zza(newInstance, bArr, i3, i4, zzbae);
        zzbdm.zzo(newInstance);
        zzbae.zzdpn = newInstance;
        return i4;
    }

    private static <UT, UB> int zza(zzbee<UT, UB> zzbee, T t) {
        return zzbee.zzy(zzbee.zzac(t));
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzbae zzbae) throws IOException {
        int i9;
        Object obj;
        Object obj2;
        Object obj3;
        long j2;
        int i10;
        int i11;
        Unsafe unsafe = zzdwf;
        long j3 = (long) (this.zzdwg[i8 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    obj = Double.valueOf(zzbad.zzg(bArr, i));
                    unsafe.putObject(t, j, obj);
                    i9 = i + 8;
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    obj2 = Float.valueOf(zzbad.zzh(bArr, i));
                    unsafe.putObject(t, j, obj2);
                    i9 = i + 4;
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    i9 = zzbad.zzb(bArr, i, zzbae);
                    j2 = zzbae.zzdpm;
                    obj3 = Long.valueOf(j2);
                    unsafe.putObject(t, j, obj3);
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    i9 = zzbad.zza(bArr, i, zzbae);
                    i10 = zzbae.zzdpl;
                    obj3 = Integer.valueOf(i10);
                    unsafe.putObject(t, j, obj3);
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    obj = Long.valueOf(zzbad.zzf(bArr, i));
                    unsafe.putObject(t, j, obj);
                    i9 = i + 8;
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    obj2 = Integer.valueOf(zzbad.zze(bArr, i));
                    unsafe.putObject(t, j, obj2);
                    i9 = i + 4;
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    i9 = zzbad.zzb(bArr, i, zzbae);
                    obj3 = Boolean.valueOf(zzbae.zzdpm != 0);
                    unsafe.putObject(t, j, obj3);
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    i9 = zzbad.zza(bArr, i, zzbae);
                    i11 = zzbae.zzdpl;
                    if (i11 == 0) {
                        obj3 = "";
                        unsafe.putObject(t, j, obj3);
                        unsafe.putInt(t, j3, i4);
                        return i9;
                    } else if ((i6 & 536870912) == 0 || zzbem.zzf(bArr, i9, i9 + i11)) {
                        unsafe.putObject(t, j, new String(bArr, i9, i11, zzbbq.UTF_8));
                        i9 += i11;
                        unsafe.putInt(t, j3, i4);
                        return i9;
                    } else {
                        throw zzbbu.zzads();
                    }
                }
                return i;
            case 60:
                if (i5 == 2) {
                    i9 = zza(zzcq(i8), bArr, i, i2, zzbae);
                    Object object = unsafe.getInt(t, j3) == i4 ? unsafe.getObject(t, j) : null;
                    obj3 = zzbae.zzdpn;
                    if (object != null) {
                        obj3 = zzbbq.zza(object, obj3);
                    }
                    unsafe.putObject(t, j, obj3);
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    i9 = zzbad.zza(bArr, i, zzbae);
                    i11 = zzbae.zzdpl;
                    if (i11 == 0) {
                        obj3 = zzbah.zzdpq;
                        unsafe.putObject(t, j, obj3);
                        unsafe.putInt(t, j3, i4);
                        return i9;
                    }
                    unsafe.putObject(t, j, zzbah.zzc(bArr, i9, i11));
                    i9 += i11;
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int zza = zzbad.zza(bArr, i, zzbae);
                    int i12 = zzbae.zzdpl;
                    zzbbs<?> zzcs = zzcs(i8);
                    if (zzcs == null || zzcs.zzq(i12) != null) {
                        unsafe.putObject(t, j, Integer.valueOf(i12));
                        i9 = zza;
                        unsafe.putInt(t, j3, i4);
                        return i9;
                    }
                    zzz(t).zzb(i3, Long.valueOf((long) i12));
                    return zza;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    i9 = zzbad.zza(bArr, i, zzbae);
                    i10 = zzbaq.zzbu(zzbae.zzdpl);
                    obj3 = Integer.valueOf(i10);
                    unsafe.putObject(t, j, obj3);
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    i9 = zzbad.zzb(bArr, i, zzbae);
                    j2 = zzbaq.zzl(zzbae.zzdpm);
                    obj3 = Long.valueOf(j2);
                    unsafe.putObject(t, j, obj3);
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    i9 = zza(zzcq(i8), bArr, i, i2, (i3 & -8) | 4, zzbae);
                    Object object2 = unsafe.getInt(t, j3) == i4 ? unsafe.getObject(t, j) : null;
                    obj3 = zzbae.zzdpn;
                    if (object2 != null) {
                        obj3 = zzbbq.zza(object2, obj3);
                    }
                    unsafe.putObject(t, j, obj3);
                    unsafe.putInt(t, j3, i4);
                    return i9;
                }
                return i;
            default:
                return i;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0232, code lost:
        if (r30.zzdpm != 0) goto L_0x0234;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0234, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0236, code lost:
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0237, code lost:
        r11.addBoolean(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x023a, code lost:
        if (r4 >= r20) goto L_0x039b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x023c, code lost:
        r6 = com.google.android.gms.internal.ads.zzbad.zza(r18, r4, r30);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0242, code lost:
        if (r21 != r30.zzdpl) goto L_0x039b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0244, code lost:
        r4 = com.google.android.gms.internal.ads.zzbad.zzb(r18, r6, r30);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x024c, code lost:
        if (r30.zzdpm == 0) goto L_0x0236;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x013a, code lost:
        if (r4 == 0) goto L_0x013c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x013c, code lost:
        r11.add(com.google.android.gms.internal.ads.zzbah.zzdpq);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0142, code lost:
        r11.add(com.google.android.gms.internal.ads.zzbah.zzc(r18, r1, r4));
        r1 = r1 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x014a, code lost:
        if (r1 >= r20) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x014c, code lost:
        r4 = com.google.android.gms.internal.ads.zzbad.zza(r18, r1, r30);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0152, code lost:
        if (r21 != r30.zzdpl) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0154, code lost:
        r1 = com.google.android.gms.internal.ads.zzbad.zza(r18, r4, r30);
        r4 = r30.zzdpl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x015a, code lost:
        if (r4 != 0) goto L_0x0142;
     */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01d3  */
    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzbae zzbae) throws IOException {
        int zzb;
        int zza;
        int zza2;
        int zzb2;
        int i8 = i;
        zzbbt zzbbt = (zzbbt) zzdwf.getObject(t, j2);
        if (!zzbbt.zzaay()) {
            int size = zzbbt.size();
            zzbbt = zzbbt.zzbm(size == 0 ? 10 : size << 1);
            zzdwf.putObject(t, j2, zzbbt);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzbay zzbay = (zzbay) zzbbt;
                    int zza3 = zzbad.zza(bArr, i8, zzbae);
                    int i9 = zzbae.zzdpl + zza3;
                    while (zza3 < i9) {
                        zzbay.zzd(zzbad.zzg(bArr, zza3));
                        zza3 += 8;
                    }
                    if (zza3 == i9) {
                        return zza3;
                    }
                    throw zzbbu.zzadl();
                }
                if (i5 == 1) {
                    zzbay zzbay2 = (zzbay) zzbbt;
                    zzbay2.zzd(zzbad.zzg(bArr, i));
                    while (true) {
                        int i10 = i8 + 8;
                        if (i10 >= i2) {
                            return i10;
                        }
                        i8 = zzbad.zza(bArr, i10, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return i10;
                        }
                        zzbay2.zzd(zzbad.zzg(bArr, i8));
                    }
                }
                return i8;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzbbm zzbbm = (zzbbm) zzbbt;
                    int zza4 = zzbad.zza(bArr, i8, zzbae);
                    int i11 = zzbae.zzdpl + zza4;
                    while (zza4 < i11) {
                        zzbbm.zzd(zzbad.zzh(bArr, zza4));
                        zza4 += 4;
                    }
                    if (zza4 == i11) {
                        return zza4;
                    }
                    throw zzbbu.zzadl();
                }
                if (i5 == 5) {
                    zzbbm zzbbm2 = (zzbbm) zzbbt;
                    zzbbm2.zzd(zzbad.zzh(bArr, i));
                    while (true) {
                        int i12 = i8 + 4;
                        if (i12 >= i2) {
                            return i12;
                        }
                        i8 = zzbad.zza(bArr, i12, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return i12;
                        }
                        zzbbm2.zzd(zzbad.zzh(bArr, i8));
                    }
                }
                return i8;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzbci zzbci = (zzbci) zzbbt;
                    int zza5 = zzbad.zza(bArr, i8, zzbae);
                    int i13 = zzbae.zzdpl + zza5;
                    while (zza5 < i13) {
                        zza5 = zzbad.zzb(bArr, zza5, zzbae);
                        zzbci.zzw(zzbae.zzdpm);
                    }
                    if (zza5 == i13) {
                        return zza5;
                    }
                    throw zzbbu.zzadl();
                }
                if (i5 == 0) {
                    zzbci zzbci2 = (zzbci) zzbbt;
                    do {
                        zzb = zzbad.zzb(bArr, i8, zzbae);
                        zzbci2.zzw(zzbae.zzdpm);
                        if (zzb >= i2) {
                            return zzb;
                        }
                        i8 = zzbad.zza(bArr, zzb, zzbae);
                    } while (i3 == zzbae.zzdpl);
                    return zzb;
                }
                return i8;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzbad.zza(bArr, i8, zzbbt, zzbae);
                }
                if (i5 == 0) {
                    return zzbad.zza(i3, bArr, i, i2, zzbbt, zzbae);
                }
                return i8;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzbci zzbci3 = (zzbci) zzbbt;
                    int zza6 = zzbad.zza(bArr, i8, zzbae);
                    int i14 = zzbae.zzdpl + zza6;
                    while (zza6 < i14) {
                        zzbci3.zzw(zzbad.zzf(bArr, zza6));
                        zza6 += 8;
                    }
                    if (zza6 == i14) {
                        return zza6;
                    }
                    throw zzbbu.zzadl();
                }
                if (i5 == 1) {
                    zzbci zzbci4 = (zzbci) zzbbt;
                    zzbci4.zzw(zzbad.zzf(bArr, i));
                    while (true) {
                        int i15 = i8 + 8;
                        if (i15 >= i2) {
                            return i15;
                        }
                        i8 = zzbad.zza(bArr, i15, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return i15;
                        }
                        zzbci4.zzw(zzbad.zzf(bArr, i8));
                    }
                }
                return i8;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzbbp zzbbp = (zzbbp) zzbbt;
                    int zza7 = zzbad.zza(bArr, i8, zzbae);
                    int i16 = zzbae.zzdpl + zza7;
                    while (zza7 < i16) {
                        zzbbp.zzco(zzbad.zze(bArr, zza7));
                        zza7 += 4;
                    }
                    if (zza7 == i16) {
                        return zza7;
                    }
                    throw zzbbu.zzadl();
                }
                if (i5 == 5) {
                    zzbbp zzbbp2 = (zzbbp) zzbbt;
                    zzbbp2.zzco(zzbad.zze(bArr, i));
                    while (true) {
                        int i17 = i8 + 4;
                        if (i17 >= i2) {
                            return i17;
                        }
                        i8 = zzbad.zza(bArr, i17, zzbae);
                        if (i3 != zzbae.zzdpl) {
                            return i17;
                        }
                        zzbbp2.zzco(zzbad.zze(bArr, i8));
                    }
                }
                return i8;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzbaf zzbaf = (zzbaf) zzbbt;
                    zza = zzbad.zza(bArr, i8, zzbae);
                    int i18 = zzbae.zzdpl + zza;
                    while (zza < i18) {
                        zza = zzbad.zzb(bArr, zza, zzbae);
                        zzbaf.addBoolean(zzbae.zzdpm != 0);
                    }
                    if (zza != i18) {
                        throw zzbbu.zzadl();
                    }
                    return zza;
                }
                if (i5 == 0) {
                    zzbaf zzbaf2 = (zzbaf) zzbbt;
                    i8 = zzbad.zzb(bArr, i8, zzbae);
                    break;
                }
                return i8;
            case 26:
                if (i5 == 2) {
                    int i19 = ((j & 536870912) > 0 ? 1 : ((j & 536870912) == 0 ? 0 : -1));
                    i8 = zzbad.zza(bArr, i8, zzbae);
                    if (i19 == 0) {
                        int i20 = zzbae.zzdpl;
                        if (i20 != 0) {
                            String str = new String(bArr, i8, i20, zzbbq.UTF_8);
                            zzbbt.add(str);
                            i8 += i20;
                            if (i8 < i2) {
                                int zza8 = zzbad.zza(bArr, i8, zzbae);
                                if (i3 == zzbae.zzdpl) {
                                    i8 = zzbad.zza(bArr, zza8, zzbae);
                                    i20 = zzbae.zzdpl;
                                    if (i20 != 0) {
                                        str = new String(bArr, i8, i20, zzbbq.UTF_8);
                                        zzbbt.add(str);
                                        i8 += i20;
                                        if (i8 < i2) {
                                        }
                                    }
                                }
                            }
                        }
                        zzbbt.add("");
                        if (i8 < i2) {
                        }
                    } else {
                        int i21 = zzbae.zzdpl;
                        if (i21 != 0) {
                            int i22 = i8 + i21;
                            if (zzbem.zzf(bArr, i8, i22)) {
                                String str2 = new String(bArr, i8, i21, zzbbq.UTF_8);
                                zzbbt.add(str2);
                                i8 = i22;
                                if (i8 < i2) {
                                    int zza9 = zzbad.zza(bArr, i8, zzbae);
                                    if (i3 == zzbae.zzdpl) {
                                        i8 = zzbad.zza(bArr, zza9, zzbae);
                                        int i23 = zzbae.zzdpl;
                                        if (i23 != 0) {
                                            i22 = i8 + i23;
                                            if (zzbem.zzf(bArr, i8, i22)) {
                                                str2 = new String(bArr, i8, i23, zzbbq.UTF_8);
                                                zzbbt.add(str2);
                                                i8 = i22;
                                                if (i8 < i2) {
                                                }
                                            }
                                            throw zzbbu.zzads();
                                        }
                                    }
                                }
                            } else {
                                throw zzbbu.zzads();
                            }
                        }
                        zzbbt.add("");
                        if (i8 < i2) {
                        }
                    }
                }
                return i8;
            case 27:
                if (i5 == 2) {
                    return zza(zzcq(i6), i3, bArr, i, i2, zzbbt, zzbae);
                }
                return i8;
            case 28:
                if (i5 == 2) {
                    int zza10 = zzbad.zza(bArr, i8, zzbae);
                    int i24 = zzbae.zzdpl;
                    break;
                }
                return i8;
            case 30:
            case 44:
                if (i5 == 2) {
                    zza = zzbad.zza(bArr, i8, zzbbt, zzbae);
                } else {
                    if (i5 == 0) {
                        zza = zzbad.zza(i3, bArr, i, i2, zzbbt, zzbae);
                    }
                    return i8;
                }
                T t2 = t;
                zzbef zzbef = t2.zzdtt;
                if (zzbef == zzbef.zzagc()) {
                    zzbef = null;
                }
                zzbef zzbef2 = (zzbef) zzbdo.zza(i4, zzbbt, zzcs(i6), zzbef, this.zzdwv);
                if (zzbef2 != null) {
                    t2.zzdtt = zzbef2;
                }
                return zza;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzbbp zzbbp3 = (zzbbp) zzbbt;
                    int zza11 = zzbad.zza(bArr, i8, zzbae);
                    int i25 = zzbae.zzdpl + zza11;
                    while (zza11 < i25) {
                        zza11 = zzbad.zza(bArr, zza11, zzbae);
                        zzbbp3.zzco(zzbaq.zzbu(zzbae.zzdpl));
                    }
                    if (zza11 == i25) {
                        return zza11;
                    }
                    throw zzbbu.zzadl();
                }
                if (i5 == 0) {
                    zzbbp zzbbp4 = (zzbbp) zzbbt;
                    do {
                        zza2 = zzbad.zza(bArr, i8, zzbae);
                        zzbbp4.zzco(zzbaq.zzbu(zzbae.zzdpl));
                        if (zza2 >= i2) {
                            return zza2;
                        }
                        i8 = zzbad.zza(bArr, zza2, zzbae);
                    } while (i3 == zzbae.zzdpl);
                    return zza2;
                }
                return i8;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzbci zzbci5 = (zzbci) zzbbt;
                    int zza12 = zzbad.zza(bArr, i8, zzbae);
                    int i26 = zzbae.zzdpl + zza12;
                    while (zza12 < i26) {
                        zza12 = zzbad.zzb(bArr, zza12, zzbae);
                        zzbci5.zzw(zzbaq.zzl(zzbae.zzdpm));
                    }
                    if (zza12 == i26) {
                        return zza12;
                    }
                    throw zzbbu.zzadl();
                }
                if (i5 == 0) {
                    zzbci zzbci6 = (zzbci) zzbbt;
                    do {
                        zzb2 = zzbad.zzb(bArr, i8, zzbae);
                        zzbci6.zzw(zzbaq.zzl(zzbae.zzdpm));
                        if (zzb2 >= i2) {
                            return zzb2;
                        }
                        i8 = zzbad.zza(bArr, zzb2, zzbae);
                    } while (i3 == zzbae.zzdpl);
                    return zzb2;
                }
                return i8;
            case 49:
                if (i5 == 3) {
                    zzbdm zzcq = zzcq(i6);
                    int i27 = (i3 & -8) | 4;
                    i8 = zza(zzcq, bArr, i, i2, i27, zzbae);
                    while (true) {
                        zzbbt.add(zzbae.zzdpn);
                        if (i8 < i2) {
                            int zza13 = zzbad.zza(bArr, i8, zzbae);
                            if (i3 == zzbae.zzdpl) {
                                i8 = zza(zzcq, bArr, zza13, i2, i27, zzbae);
                            }
                        }
                    }
                }
                return i8;
            default:
                return i8;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x003e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x003e */
    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, long j, zzbae zzbae) throws IOException {
        Unsafe unsafe = zzdwf;
        Object zzcr = zzcr(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzdwx.zzu(object)) {
            Object zzw = this.zzdwx.zzw(zzcr);
            this.zzdwx.zzb(zzw, object);
            unsafe.putObject(t, j, zzw);
            object = zzw;
        }
        zzbcn<?, ?> zzx = this.zzdwx.zzx(zzcr);
        Map<?, ?> zzs = this.zzdwx.zzs(object);
        int zza = zzbad.zza(bArr, i, zzbae);
        int i5 = zzbae.zzdpl;
        if (i5 < 0 || i5 > i2 - zza) {
            throw zzbbu.zzadl();
        }
        int i6 = i5 + zza;
        r.AnonymousClass1 r13 = (K) zzx.zzdvz;
        r.AnonymousClass1 r14 = (V) zzx.zzdwb;
        while (zza < i6) {
            int i7 = zza + 1;
            byte b = bArr[zza];
            int i8 = b;
            if (b < 0) {
                i7 = zzbad.zza(b, bArr, i7, zzbae);
                i8 = zzbae.zzdpl;
            }
            int i9 = (i8 == 1 ? 1 : 0) >>> 3;
            int i10 = (i8 == 1 ? 1 : 0) & 7;
            if (i9 != 1) {
                if (i9 == 2 && i10 == zzx.zzdwa.zzagm()) {
                    zza = zza(bArr, i7, i2, zzx.zzdwa, zzx.zzdwb.getClass(), zzbae);
                    r14 = (V) zzbae.zzdpn;
                }
            } else if (i10 == zzx.zzdvy.zzagm()) {
                zza = zza(bArr, i7, i2, zzx.zzdvy, (Class<?>) null, zzbae);
                r13 = (K) zzbae.zzdpn;
            }
            zza = zzbad.zza(i8, bArr, i7, i2, zzbae);
        }
        if (zza == i6) {
            zzs.put(r13, r14);
            return i6;
        }
        throw zzbbu.zzadr();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02f9, code lost:
        if (r0 == r4) goto L_0x035f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0341, code lost:
        if (r0 == r15) goto L_0x035f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x035d, code lost:
        if (r0 == r15) goto L_0x035f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x035f, code lost:
        r8 = r31;
        r2 = r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0377 A[ADDED_TO_REGION] */
    private final int zza(T t, byte[] bArr, int i, int i2, int i3, zzbae zzbae) throws IOException {
        Unsafe unsafe;
        int i4;
        T t2;
        byte b;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        zzbae zzbae2;
        long j;
        Object obj;
        zzbae zzbae3;
        int i14;
        zzbcy<T> zzbcy = this;
        T t3 = t;
        byte[] bArr2 = bArr;
        int i15 = i2;
        int i16 = i3;
        zzbae zzbae4 = zzbae;
        Unsafe unsafe2 = zzdwf;
        int i17 = -1;
        int i18 = i;
        int i19 = 0;
        int i20 = 0;
        int i21 = -1;
        while (true) {
            if (i18 < i15) {
                int i22 = i18 + 1;
                byte b2 = bArr2[i18];
                if (b2 < 0) {
                    i5 = zzbad.zza(b2, bArr2, i22, zzbae4);
                    b = zzbae4.zzdpl;
                } else {
                    b = b2;
                    i5 = i22;
                }
                int i23 = b >>> 3;
                int i24 = b & 7;
                int zzcw = zzbcy.zzcw(i23);
                if (zzcw != i17) {
                    int[] iArr = zzbcy.zzdwg;
                    int i25 = iArr[zzcw + 1];
                    int i26 = (i25 & 267386880) >>> 20;
                    long j2 = (long) (i25 & 1048575);
                    if (i26 <= 17) {
                        int i27 = iArr[zzcw + 2];
                        int i28 = 1 << (i27 >>> 20);
                        int i29 = i27 & 1048575;
                        if (i29 != i21) {
                            if (i21 != -1) {
                                unsafe2.putInt(t3, (long) i21, i20);
                            }
                            i20 = unsafe2.getInt(t3, (long) i29);
                            i21 = i29;
                        }
                        switch (i26) {
                            case 0:
                                i6 = b;
                                zzbae2 = zzbae;
                                i13 = i5;
                                i12 = i21;
                                bArr2 = bArr;
                                i15 = i2;
                                if (i24 == 1) {
                                    zzbek.zza(t3, j2, zzbad.zzg(bArr2, i13));
                                    i18 = i13 + 8;
                                    i20 |= i28;
                                    i21 = i12;
                                    i19 = i6;
                                    zzbae4 = zzbae2;
                                    i17 = -1;
                                    i16 = i3;
                                    break;
                                }
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4 && i4 != 0) {
                                    i18 = i7;
                                    i19 = i6;
                                    break;
                                } else {
                                    i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                    zzbcy = this;
                                    t3 = t;
                                    bArr2 = bArr;
                                    i15 = i2;
                                    i16 = i4;
                                    i19 = i6;
                                    unsafe2 = unsafe;
                                    i17 = -1;
                                    zzbae4 = zzbae;
                                    break;
                                }
                            case 1:
                                i6 = b;
                                zzbae2 = zzbae;
                                i13 = i5;
                                i12 = i21;
                                bArr2 = bArr;
                                i15 = i2;
                                if (i24 == 5) {
                                    zzbek.zza((Object) t3, j2, zzbad.zzh(bArr2, i13));
                                    i18 = i13 + 4;
                                    i20 |= i28;
                                    i21 = i12;
                                    i19 = i6;
                                    zzbae4 = zzbae2;
                                    i17 = -1;
                                    i16 = i3;
                                    break;
                                }
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 2:
                            case 3:
                                i6 = b;
                                i13 = i5;
                                i12 = i21;
                                bArr2 = bArr;
                                i15 = i2;
                                if (i24 == 0) {
                                    int zzb = zzbad.zzb(bArr2, i13, zzbae);
                                    unsafe2.putLong(t, j2, zzbae.zzdpm);
                                    i20 |= i28;
                                    i21 = i12;
                                    i19 = i6;
                                    zzbae4 = zzbae;
                                    i18 = zzb;
                                    i17 = -1;
                                    i16 = i3;
                                    break;
                                }
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 4:
                            case 11:
                                i6 = b;
                                zzbae2 = zzbae;
                                i13 = i5;
                                i12 = i21;
                                bArr2 = bArr;
                                i15 = i2;
                                if (i24 == 0) {
                                    i18 = zzbad.zza(bArr2, i13, zzbae2);
                                    unsafe2.putInt(t3, j2, zzbae2.zzdpl);
                                    i20 |= i28;
                                    i21 = i12;
                                    i19 = i6;
                                    zzbae4 = zzbae2;
                                    i17 = -1;
                                    i16 = i3;
                                    break;
                                }
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 5:
                            case 14:
                                i6 = b;
                                zzbae2 = zzbae;
                                i12 = i21;
                                bArr2 = bArr;
                                i15 = i2;
                                if (i24 == 1) {
                                    unsafe2.putLong(t, j2, zzbad.zzf(bArr2, i5));
                                    i18 = i5 + 8;
                                    i20 |= i28;
                                    i21 = i12;
                                    i19 = i6;
                                    zzbae4 = zzbae2;
                                    i17 = -1;
                                    i16 = i3;
                                    break;
                                }
                                i13 = i5;
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 6:
                            case 13:
                                i6 = b;
                                zzbae2 = zzbae;
                                i12 = i21;
                                bArr2 = bArr;
                                i15 = i2;
                                if (i24 == 5) {
                                    unsafe2.putInt(t3, j2, zzbad.zze(bArr2, i5));
                                    i18 = i5 + 4;
                                    i20 |= i28;
                                    i21 = i12;
                                    i19 = i6;
                                    zzbae4 = zzbae2;
                                    i17 = -1;
                                    i16 = i3;
                                    break;
                                }
                                i13 = i5;
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 7:
                                i6 = b;
                                zzbae2 = zzbae;
                                i12 = i21;
                                bArr2 = bArr;
                                i15 = i2;
                                if (i24 == 0) {
                                    i18 = zzbad.zzb(bArr2, i5, zzbae2);
                                    zzbek.zza(t3, j2, zzbae2.zzdpm != 0);
                                    i20 |= i28;
                                    i21 = i12;
                                    i19 = i6;
                                    zzbae4 = zzbae2;
                                    i17 = -1;
                                    i16 = i3;
                                    break;
                                }
                                i13 = i5;
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 8:
                                i6 = b;
                                zzbae2 = zzbae;
                                i12 = i21;
                                j = j2;
                                bArr2 = bArr;
                                i15 = i2;
                                if (i24 == 2) {
                                    i18 = (i25 & 536870912) == 0 ? zzbad.zzc(bArr2, i5, zzbae2) : zzbad.zzd(bArr2, i5, zzbae2);
                                    obj = zzbae2.zzdpn;
                                    unsafe2.putObject(t3, j, obj);
                                    i20 |= i28;
                                    i21 = i12;
                                    i19 = i6;
                                    zzbae4 = zzbae2;
                                    i17 = -1;
                                    i16 = i3;
                                    break;
                                }
                                i13 = i5;
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 9:
                                i6 = b;
                                zzbae2 = zzbae;
                                i12 = i21;
                                j = j2;
                                bArr2 = bArr;
                                if (i24 == 2) {
                                    i15 = i2;
                                    i18 = zza(zzbcy.zzcq(zzcw), bArr2, i5, i15, zzbae2);
                                    obj = (i20 & i28) == 0 ? zzbae2.zzdpn : zzbbq.zza(unsafe2.getObject(t3, j), zzbae2.zzdpn);
                                    unsafe2.putObject(t3, j, obj);
                                    i20 |= i28;
                                    i21 = i12;
                                    i19 = i6;
                                    zzbae4 = zzbae2;
                                    i17 = -1;
                                    i16 = i3;
                                    break;
                                } else {
                                    i13 = i5;
                                    i21 = i12;
                                    i4 = i3;
                                    i7 = i13;
                                    unsafe = unsafe2;
                                    if (i6 != i4) {
                                    }
                                    i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                    zzbcy = this;
                                    t3 = t;
                                    bArr2 = bArr;
                                    i15 = i2;
                                    i16 = i4;
                                    i19 = i6;
                                    unsafe2 = unsafe;
                                    i17 = -1;
                                    zzbae4 = zzbae;
                                    break;
                                }
                                break;
                            case 10:
                                i6 = b;
                                zzbae3 = zzbae;
                                i17 = -1;
                                bArr2 = bArr;
                                if (i24 == 2) {
                                    i14 = zzbad.zze(bArr2, i5, zzbae3);
                                    unsafe2.putObject(t3, j2, zzbae3.zzdpn);
                                    i20 |= i28;
                                    i15 = i2;
                                    i18 = i14;
                                    i19 = i6;
                                    zzbae4 = zzbae3;
                                    i16 = i3;
                                    break;
                                }
                                i13 = i5;
                                i12 = i21;
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 12:
                                i6 = b;
                                zzbae3 = zzbae;
                                i17 = -1;
                                bArr2 = bArr;
                                if (i24 == 0) {
                                    i18 = zzbad.zza(bArr2, i5, zzbae3);
                                    int i30 = zzbae3.zzdpl;
                                    zzbbs<?> zzcs = zzbcy.zzcs(zzcw);
                                    if (zzcs == null || zzcs.zzq(i30) != null) {
                                        unsafe2.putInt(t3, j2, i30);
                                        i20 |= i28;
                                    } else {
                                        zzz(t).zzb(i6, Long.valueOf((long) i30));
                                    }
                                    i15 = i2;
                                    i19 = i6;
                                    zzbae4 = zzbae3;
                                    i16 = i3;
                                    break;
                                }
                                i13 = i5;
                                i12 = i21;
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 15:
                                i6 = b;
                                zzbae3 = zzbae;
                                i17 = -1;
                                bArr2 = bArr;
                                if (i24 == 0) {
                                    i14 = zzbad.zza(bArr2, i5, zzbae3);
                                    unsafe2.putInt(t3, j2, zzbaq.zzbu(zzbae3.zzdpl));
                                    i20 |= i28;
                                    i15 = i2;
                                    i18 = i14;
                                    i19 = i6;
                                    zzbae4 = zzbae3;
                                    i16 = i3;
                                    break;
                                }
                                i13 = i5;
                                i12 = i21;
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 16:
                                i6 = b;
                                i17 = -1;
                                if (i24 == 0) {
                                    bArr2 = bArr;
                                    int zzb2 = zzbad.zzb(bArr2, i5, zzbae);
                                    unsafe2.putLong(t, j2, zzbaq.zzl(zzbae.zzdpm));
                                    i20 |= i28;
                                    i19 = i6;
                                    zzbae4 = zzbae;
                                    i18 = zzb2;
                                    i15 = i2;
                                    i16 = i3;
                                    break;
                                }
                                i13 = i5;
                                i12 = i21;
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                            case 17:
                                if (i24 == 3) {
                                    i6 = b;
                                    i17 = -1;
                                    i18 = zza(zzbcy.zzcq(zzcw), bArr, i5, i2, (i23 << 3) | 4, zzbae);
                                    zzbae3 = zzbae;
                                    unsafe2.putObject(t3, j2, (i20 & i28) == 0 ? zzbae3.zzdpn : zzbbq.zza(unsafe2.getObject(t3, j2), zzbae3.zzdpn));
                                    i20 |= i28;
                                    bArr2 = bArr;
                                    i15 = i2;
                                    i19 = i6;
                                    zzbae4 = zzbae3;
                                    i16 = i3;
                                    break;
                                } else {
                                    i6 = b;
                                    i13 = i5;
                                    i12 = i21;
                                    i21 = i12;
                                    i4 = i3;
                                    i7 = i13;
                                    unsafe = unsafe2;
                                    if (i6 != i4) {
                                    }
                                    i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                    zzbcy = this;
                                    t3 = t;
                                    bArr2 = bArr;
                                    i15 = i2;
                                    i16 = i4;
                                    i19 = i6;
                                    unsafe2 = unsafe;
                                    i17 = -1;
                                    zzbae4 = zzbae;
                                    break;
                                }
                                break;
                            default:
                                i6 = b;
                                i13 = i5;
                                i12 = i21;
                                i21 = i12;
                                i4 = i3;
                                i7 = i13;
                                unsafe = unsafe2;
                                if (i6 != i4) {
                                }
                                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                zzbcy = this;
                                t3 = t;
                                bArr2 = bArr;
                                i15 = i2;
                                i16 = i4;
                                i19 = i6;
                                unsafe2 = unsafe;
                                i17 = -1;
                                zzbae4 = zzbae;
                                break;
                        }
                    } else {
                        i10 = i21;
                        bArr2 = bArr;
                        i15 = i2;
                        if (i26 != 27) {
                            i8 = i20;
                            if (i26 <= 49) {
                                i9 = b;
                                unsafe = unsafe2;
                                i18 = zza(t, bArr, i5, i2, b, i23, i24, zzcw, (long) i25, i26, j2, zzbae);
                            } else {
                                i11 = i5;
                                i9 = b;
                                unsafe = unsafe2;
                                if (i26 == 50) {
                                    if (i24 == 2) {
                                        i18 = zza(t, bArr, i11, i2, zzcw, i23, j2, zzbae);
                                    }
                                    i4 = i3;
                                    i7 = i11;
                                    i21 = i10;
                                    i6 = i9;
                                    i20 = i8;
                                    if (i6 != i4) {
                                    }
                                    i18 = zza(i6, bArr, i7, i2, t, zzbae);
                                    zzbcy = this;
                                    t3 = t;
                                    bArr2 = bArr;
                                    i15 = i2;
                                    i16 = i4;
                                    i19 = i6;
                                    unsafe2 = unsafe;
                                    i17 = -1;
                                    zzbae4 = zzbae;
                                } else {
                                    i18 = zza(t, bArr, i11, i2, i9, i23, i24, i25, i26, j2, zzcw, zzbae);
                                }
                            }
                            zzbcy = this;
                            t3 = t;
                            bArr2 = bArr;
                            i15 = i2;
                            i16 = i3;
                            zzbae4 = zzbae;
                            i21 = i10;
                            i19 = i9;
                            i20 = i8;
                            unsafe2 = unsafe;
                            i17 = -1;
                        } else if (i24 == 2) {
                            zzbbt zzbbt = (zzbbt) unsafe2.getObject(t3, j2);
                            if (!zzbbt.zzaay()) {
                                int size = zzbbt.size();
                                zzbbt = zzbbt.zzbm(size == 0 ? 10 : size << 1);
                                unsafe2.putObject(t3, j2, zzbbt);
                            }
                            zzbdm zzcq = zzbcy.zzcq(zzcw);
                            i19 = b;
                            i18 = zza(zzcq, i19, bArr, i5, i2, zzbbt, zzbae);
                            i16 = i3;
                            i21 = i10;
                            i20 = i20;
                            i17 = -1;
                            zzbae4 = zzbae;
                        } else {
                            i8 = i20;
                            i11 = i5;
                            i9 = b;
                        }
                    }
                } else {
                    i11 = i5;
                    i9 = b;
                    i8 = i20;
                    i10 = i21;
                }
                unsafe = unsafe2;
                i4 = i3;
                i7 = i11;
                i21 = i10;
                i6 = i9;
                i20 = i8;
                if (i6 != i4) {
                }
                i18 = zza(i6, bArr, i7, i2, t, zzbae);
                zzbcy = this;
                t3 = t;
                bArr2 = bArr;
                i15 = i2;
                i16 = i4;
                i19 = i6;
                unsafe2 = unsafe;
                i17 = -1;
                zzbae4 = zzbae;
            } else {
                unsafe = unsafe2;
                i4 = i16;
            }
        }
        if (i21 != -1) {
            t2 = t;
            unsafe.putInt(t2, (long) i21, i20);
        } else {
            t2 = t;
        }
        int[] iArr2 = this.zzdwr;
        if (iArr2 != null) {
            zzbef zzbef = null;
            for (int i31 : iArr2) {
                zzbef = (zzbef) zza(t2, i31, zzbef, (zzbee<UT, UB>) this.zzdwv);
            }
            if (zzbef != null) {
                this.zzdwv.zzf(t2, zzbef);
            }
        }
        if (i4 == 0) {
            if (i18 != i2) {
                throw zzbbu.zzadr();
            }
        } else if (i18 > i2 || i19 != i4) {
            throw zzbbu.zzadr();
        }
        return i18;
    }

    private static int zza(byte[] bArr, int i, int i2, zzbes zzbes, Class<?> cls, zzbae zzbae) throws IOException {
        int zzb;
        Object valueOf;
        Object obj;
        Object obj2;
        int i3;
        long j;
        switch (zzbcz.zzdql[zzbes.ordinal()]) {
            case 1:
                zzb = zzbad.zzb(bArr, i, zzbae);
                valueOf = Boolean.valueOf(zzbae.zzdpm != 0);
                zzbae.zzdpn = valueOf;
                return zzb;
            case 2:
                return zzbad.zze(bArr, i, zzbae);
            case 3:
                obj = Double.valueOf(zzbad.zzg(bArr, i));
                zzbae.zzdpn = obj;
                return i + 8;
            case 4:
            case 5:
                obj2 = Integer.valueOf(zzbad.zze(bArr, i));
                zzbae.zzdpn = obj2;
                return i + 4;
            case 6:
            case 7:
                obj = Long.valueOf(zzbad.zzf(bArr, i));
                zzbae.zzdpn = obj;
                return i + 8;
            case 8:
                obj2 = Float.valueOf(zzbad.zzh(bArr, i));
                zzbae.zzdpn = obj2;
                return i + 4;
            case 9:
            case 10:
            case 11:
                zzb = zzbad.zza(bArr, i, zzbae);
                i3 = zzbae.zzdpl;
                valueOf = Integer.valueOf(i3);
                zzbae.zzdpn = valueOf;
                return zzb;
            case 12:
            case 13:
                zzb = zzbad.zzb(bArr, i, zzbae);
                j = zzbae.zzdpm;
                valueOf = Long.valueOf(j);
                zzbae.zzdpn = valueOf;
                return zzb;
            case 14:
                return zza((zzbdm) zzbdg.zzaeo().zze(cls), bArr, i, i2, zzbae);
            case 15:
                zzb = zzbad.zza(bArr, i, zzbae);
                i3 = zzbaq.zzbu(zzbae.zzdpl);
                valueOf = Integer.valueOf(i3);
                zzbae.zzdpn = valueOf;
                return zzb;
            case 16:
                zzb = zzbad.zzb(bArr, i, zzbae);
                j = zzbaq.zzl(zzbae.zzdpm);
                valueOf = Long.valueOf(j);
                zzbae.zzdpn = valueOf;
                return zzb;
            case 17:
                return zzbad.zzd(bArr, i, zzbae);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    static <T> zzbcy<T> zza(Class<T> cls, zzbcs zzbcs, zzbdc zzbdc, zzbce zzbce, zzbee<?, ?> zzbee, zzbbd<?> zzbbd, zzbcp zzbcp) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (zzbcs instanceof zzbdi) {
            zzbdi zzbdi = (zzbdi) zzbcs;
            boolean z = zzbdi.zzaeh() == zzbbo.zze.zzduj;
            if (zzbdi.getFieldCount() == 0) {
                i3 = 0;
                i2 = 0;
                i = 0;
            } else {
                int zzaer = zzbdi.zzaer();
                int zzaes = zzbdi.zzaes();
                i3 = zzbdi.zzaew();
                i2 = zzaer;
                i = zzaes;
            }
            int[] iArr = new int[(i3 << 2)];
            Object[] objArr = new Object[(i3 << 1)];
            int[] iArr2 = zzbdi.zzaet() > 0 ? new int[zzbdi.zzaet()] : null;
            int[] iArr3 = zzbdi.zzaeu() > 0 ? new int[zzbdi.zzaeu()] : null;
            zzbdj zzaeq = zzbdi.zzaeq();
            if (zzaeq.next()) {
                int zzaci = zzaeq.zzaci();
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;
                while (true) {
                    if (zzaci >= zzbdi.zzaex() || i7 >= ((zzaci - i2) << 2)) {
                        if (zzaeq.zzafb()) {
                            i6 = (int) zzbek.zza(zzaeq.zzafc());
                            i4 = (int) zzbek.zza(zzaeq.zzafd());
                            i5 = 0;
                        } else {
                            i6 = (int) zzbek.zza(zzaeq.zzafe());
                            if (zzaeq.zzaff()) {
                                i4 = (int) zzbek.zza(zzaeq.zzafg());
                                i5 = zzaeq.zzafh();
                            } else {
                                i5 = 0;
                                i4 = 0;
                            }
                        }
                        iArr[i7] = zzaeq.zzaci();
                        int i10 = i7 + 1;
                        iArr[i10] = (zzaeq.zzafj() ? 536870912 : 0) | (zzaeq.zzafi() ? 268435456 : 0) | (zzaeq.zzaez() << 20) | i6;
                        iArr[i7 + 2] = (i5 << 20) | i4;
                        if (zzaeq.zzafm() != null) {
                            int i11 = (i7 / 4) << 1;
                            objArr[i11] = zzaeq.zzafm();
                            if (zzaeq.zzafk() != null) {
                                objArr[i11 + 1] = zzaeq.zzafk();
                            } else if (zzaeq.zzafl() != null) {
                                objArr[i11 + 1] = zzaeq.zzafl();
                            }
                        } else if (zzaeq.zzafk() != null) {
                            objArr[((i7 / 4) << 1) + 1] = zzaeq.zzafk();
                        } else if (zzaeq.zzafl() != null) {
                            objArr[((i7 / 4) << 1) + 1] = zzaeq.zzafl();
                        }
                        int zzaez = zzaeq.zzaez();
                        if (zzaez == zzbbj.MAP.ordinal()) {
                            iArr2[i8] = i7;
                            i8++;
                        } else if (zzaez >= 18 && zzaez <= 49) {
                            iArr3[i9] = iArr[i10] & 1048575;
                            i9++;
                        }
                        if (!zzaeq.next()) {
                            break;
                        }
                        zzaci = zzaeq.zzaci();
                    } else {
                        for (int i12 = 0; i12 < 4; i12++) {
                            iArr[i7 + i12] = -1;
                        }
                    }
                    i7 += 4;
                }
            }
            return new zzbcy<>(iArr, objArr, i2, i, zzbdi.zzaex(), zzbdi.zzaej(), z, false, zzbdi.zzaev(), iArr2, iArr3, zzbdc, zzbce, zzbee, zzbbd, zzbcp);
        }
        ((zzbdz) zzbcs).zzaeh();
        throw new NoSuchMethodError();
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzbbs<?> zzbbs, UB ub, zzbee<UT, UB> zzbee) {
        zzbcn<?, ?> zzx = this.zzdwx.zzx(zzcr(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (zzbbs.zzq(next.getValue().intValue()) == null) {
                if (ub == null) {
                    ub = zzbee.zzagb();
                }
                zzbam zzbo = zzbah.zzbo(zzbcm.zza(zzx, next.getKey(), next.getValue()));
                try {
                    zzbcm.zza(zzbo.zzabj(), zzx, next.getKey(), next.getValue());
                    zzbee.zza(ub, i2, zzbo.zzabi());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzbee<UT, UB> zzbee) {
        zzbbs<?> zzcs;
        int i2 = this.zzdwg[i];
        Object zzp = zzbek.zzp(obj, (long) (zzct(i) & 1048575));
        return (zzp == null || (zzcs = zzcs(i)) == null) ? ub : (UB) zza(i, i2, (Map<K, V>) this.zzdwx.zzs(zzp), zzcs, ub, zzbee);
    }

    private static void zza(int i, Object obj, zzbey zzbey) throws IOException {
        if (obj instanceof String) {
            zzbey.zzf(i, (String) obj);
        } else {
            zzbey.zza(i, (zzbah) obj);
        }
    }

    private static <UT, UB> void zza(zzbee<UT, UB> zzbee, T t, zzbey zzbey) throws IOException {
        zzbee.zza(zzbee.zzac(t), zzbey);
    }

    private final <K, V> void zza(zzbey zzbey, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzbey.zza(i, this.zzdwx.zzx(zzcr(i2)), this.zzdwx.zzt(obj));
        }
    }

    private final void zza(Object obj, int i, zzbdl zzbdl) throws IOException {
        long j;
        Object zzabs;
        if (zzcv(i)) {
            j = (long) (i & 1048575);
            zzabs = zzbdl.zzabr();
        } else {
            int i2 = i & 1048575;
            if (this.zzdwn) {
                j = (long) i2;
                zzabs = zzbdl.readString();
            } else {
                j = (long) i2;
                zzabs = zzbdl.zzabs();
            }
        }
        zzbek.zza(obj, j, zzabs);
    }

    private final void zza(T t, T t2, int i) {
        long zzct = (long) (zzct(i) & 1048575);
        if (zza(t2, i)) {
            Object zzp = zzbek.zzp(t, zzct);
            Object zzp2 = zzbek.zzp(t2, zzct);
            if (zzp != null && zzp2 != null) {
                zzbek.zza(t, zzct, zzbbq.zza(zzp, zzp2));
                zzb(t, i);
            } else if (zzp2 != null) {
                zzbek.zza(t, zzct, zzp2);
                zzb(t, i);
            }
        }
    }

    private final boolean zza(T t, int i) {
        if (this.zzdwo) {
            int zzct = zzct(i);
            long j = (long) (zzct & 1048575);
            switch ((zzct & 267386880) >>> 20) {
                case 0:
                    return zzbek.zzo(t, j) != 0.0d;
                case 1:
                    return zzbek.zzn(t, j) != 0.0f;
                case 2:
                    return zzbek.zzl(t, j) != 0;
                case 3:
                    return zzbek.zzl(t, j) != 0;
                case 4:
                    return zzbek.zzk(t, j) != 0;
                case 5:
                    return zzbek.zzl(t, j) != 0;
                case 6:
                    return zzbek.zzk(t, j) != 0;
                case 7:
                    return zzbek.zzm(t, j);
                case 8:
                    Object zzp = zzbek.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzbah) {
                        return !zzbah.zzdpq.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzbek.zzp(t, j) != null;
                case 10:
                    return !zzbah.zzdpq.equals(zzbek.zzp(t, j));
                case 11:
                    return zzbek.zzk(t, j) != 0;
                case 12:
                    return zzbek.zzk(t, j) != 0;
                case 13:
                    return zzbek.zzk(t, j) != 0;
                case 14:
                    return zzbek.zzl(t, j) != 0;
                case 15:
                    return zzbek.zzk(t, j) != 0;
                case 16:
                    return zzbek.zzl(t, j) != 0;
                case 17:
                    return zzbek.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzcu = zzcu(i);
            return (zzbek.zzk(t, (long) (zzcu & 1048575)) & (1 << (zzcu >>> 20))) != 0;
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzbek.zzk(t, (long) (zzcu(i2) & 1048575)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zzdwo ? zza(t, i) : (i2 & i3) != 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.android.gms.internal.ads.zzbdm */
    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzbdm zzbdm) {
        return zzbdm.zzaa(zzbek.zzp(obj, (long) (i & 1048575)));
    }

    private final void zzb(T t, int i) {
        if (!this.zzdwo) {
            int zzcu = zzcu(i);
            long j = (long) (zzcu & 1048575);
            zzbek.zzb(t, j, zzbek.zzk(t, j) | (1 << (zzcu >>> 20)));
        }
    }

    private final void zzb(T t, int i, int i2) {
        zzbek.zzb(t, (long) (zzcu(i2) & 1048575), i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:170:0x0494  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    private final void zzb(T t, zzbey zzbey) throws IOException {
        Map.Entry<?, Object> entry;
        Iterator<Map.Entry<?, Object>> it;
        int length;
        int i;
        int i2;
        int i3;
        if (this.zzdwm) {
            zzbbg<?> zzm = this.zzdww.zzm(t);
            if (!zzm.isEmpty()) {
                it = zzm.iterator();
                entry = it.next();
                int i4 = -1;
                length = this.zzdwg.length;
                Unsafe unsafe = zzdwf;
                int i5 = 0;
                for (i = 0; i < length; i = i2 + 4) {
                    int zzct = zzct(i);
                    int[] iArr = this.zzdwg;
                    int i6 = iArr[i];
                    int i7 = (267386880 & zzct) >>> 20;
                    if (this.zzdwo || i7 > 17) {
                        i2 = i;
                        i3 = 0;
                    } else {
                        int i8 = iArr[i + 2];
                        int i9 = i8 & 1048575;
                        i2 = i;
                        if (i9 != i4) {
                            i5 = unsafe.getInt(t, (long) i9);
                            i4 = i9;
                        }
                        i3 = 1 << (i8 >>> 20);
                    }
                    while (entry != null && this.zzdww.zza(entry) <= i6) {
                        this.zzdww.zza(zzbey, entry);
                        entry = it.hasNext() ? it.next() : null;
                    }
                    long j = (long) (zzct & 1048575);
                    switch (i7) {
                        case 0:
                            if ((i3 & i5) != 0) {
                                zzbey.zza(i6, zzbek.zzo(t, j));
                                continue;
                            }
                        case 1:
                            if ((i3 & i5) != 0) {
                                zzbey.zza(i6, zzbek.zzn(t, j));
                            } else {
                                continue;
                            }
                        case 2:
                            if ((i3 & i5) != 0) {
                                zzbey.zzi(i6, unsafe.getLong(t, j));
                            } else {
                                continue;
                            }
                        case 3:
                            if ((i3 & i5) != 0) {
                                zzbey.zza(i6, unsafe.getLong(t, j));
                            } else {
                                continue;
                            }
                        case 4:
                            if ((i3 & i5) != 0) {
                                zzbey.zzm(i6, unsafe.getInt(t, j));
                            } else {
                                continue;
                            }
                        case 5:
                            if ((i3 & i5) != 0) {
                                zzbey.zzc(i6, unsafe.getLong(t, j));
                            } else {
                                continue;
                            }
                        case 6:
                            if ((i3 & i5) != 0) {
                                zzbey.zzp(i6, unsafe.getInt(t, j));
                            } else {
                                continue;
                            }
                        case 7:
                            if ((i3 & i5) != 0) {
                                zzbey.zzf(i6, zzbek.zzm(t, j));
                            } else {
                                continue;
                            }
                        case 8:
                            if ((i3 & i5) != 0) {
                                zza(i6, unsafe.getObject(t, j), zzbey);
                            } else {
                                continue;
                            }
                        case 9:
                            if ((i3 & i5) != 0) {
                                zzbey.zza(i6, unsafe.getObject(t, j), zzcq(i2));
                            } else {
                                continue;
                            }
                        case 10:
                            if ((i3 & i5) != 0) {
                                zzbey.zza(i6, (zzbah) unsafe.getObject(t, j));
                            } else {
                                continue;
                            }
                        case 11:
                            if ((i3 & i5) != 0) {
                                zzbey.zzn(i6, unsafe.getInt(t, j));
                            } else {
                                continue;
                            }
                        case 12:
                            if ((i3 & i5) != 0) {
                                zzbey.zzx(i6, unsafe.getInt(t, j));
                            } else {
                                continue;
                            }
                        case 13:
                            if ((i3 & i5) != 0) {
                                zzbey.zzw(i6, unsafe.getInt(t, j));
                            } else {
                                continue;
                            }
                        case 14:
                            if ((i3 & i5) != 0) {
                                zzbey.zzj(i6, unsafe.getLong(t, j));
                            } else {
                                continue;
                            }
                        case 15:
                            if ((i3 & i5) != 0) {
                                zzbey.zzo(i6, unsafe.getInt(t, j));
                            } else {
                                continue;
                            }
                        case 16:
                            if ((i3 & i5) != 0) {
                                zzbey.zzb(i6, unsafe.getLong(t, j));
                            } else {
                                continue;
                            }
                        case 17:
                            if ((i3 & i5) != 0) {
                                zzbey.zzb(i6, unsafe.getObject(t, j), zzcq(i2));
                            } else {
                                continue;
                            }
                        case 18:
                            zzbdo.zza(this.zzdwg[i2], (List<Double>) ((List) unsafe.getObject(t, j)), zzbey, false);
                            continue;
                        case 19:
                            zzbdo.zzb(this.zzdwg[i2], (List<Float>) ((List) unsafe.getObject(t, j)), zzbey, false);
                            continue;
                        case 20:
                            zzbdo.zzc(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 21:
                            zzbdo.zzd(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 22:
                            zzbdo.zzh(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 23:
                            zzbdo.zzf(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 24:
                            zzbdo.zzk(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 25:
                            zzbdo.zzn(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 26:
                            zzbdo.zza(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey);
                            break;
                        case 27:
                            zzbdo.zza(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, zzcq(i2));
                            break;
                        case 28:
                            zzbdo.zzb(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey);
                            break;
                        case 29:
                            zzbdo.zzi(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 30:
                            zzbdo.zzm(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 31:
                            zzbdo.zzl(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 32:
                            zzbdo.zzg(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 33:
                            zzbdo.zzj(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 34:
                            zzbdo.zze(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, false);
                            continue;
                        case 35:
                            zzbdo.zza(this.zzdwg[i2], (List<Double>) ((List) unsafe.getObject(t, j)), zzbey, true);
                            break;
                        case 36:
                            zzbdo.zzb(this.zzdwg[i2], (List<Float>) ((List) unsafe.getObject(t, j)), zzbey, true);
                            break;
                        case 37:
                            zzbdo.zzc(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 38:
                            zzbdo.zzd(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 39:
                            zzbdo.zzh(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 40:
                            zzbdo.zzf(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 41:
                            zzbdo.zzk(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 42:
                            zzbdo.zzn(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 43:
                            zzbdo.zzi(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 44:
                            zzbdo.zzm(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 45:
                            zzbdo.zzl(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 46:
                            zzbdo.zzg(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 47:
                            zzbdo.zzj(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 48:
                            zzbdo.zze(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, true);
                            break;
                        case 49:
                            zzbdo.zzb(this.zzdwg[i2], (List) unsafe.getObject(t, j), zzbey, zzcq(i2));
                            break;
                        case 50:
                            zza(zzbey, i6, unsafe.getObject(t, j), i2);
                            break;
                        case 51:
                            if (zza(t, i6, i2)) {
                                zzbey.zza(i6, zzf(t, j));
                                break;
                            }
                            break;
                        case 52:
                            if (zza(t, i6, i2)) {
                                zzbey.zza(i6, zzg(t, j));
                                break;
                            }
                            break;
                        case 53:
                            if (zza(t, i6, i2)) {
                                zzbey.zzi(i6, zzi(t, j));
                                break;
                            }
                            break;
                        case 54:
                            if (zza(t, i6, i2)) {
                                zzbey.zza(i6, zzi(t, j));
                                break;
                            }
                            break;
                        case 55:
                            if (zza(t, i6, i2)) {
                                zzbey.zzm(i6, zzh(t, j));
                                break;
                            }
                            break;
                        case 56:
                            if (zza(t, i6, i2)) {
                                zzbey.zzc(i6, zzi(t, j));
                                break;
                            }
                            break;
                        case 57:
                            if (zza(t, i6, i2)) {
                                zzbey.zzp(i6, zzh(t, j));
                                break;
                            }
                            break;
                        case 58:
                            if (zza(t, i6, i2)) {
                                zzbey.zzf(i6, zzj(t, j));
                                break;
                            }
                            break;
                        case 59:
                            if (zza(t, i6, i2)) {
                                zza(i6, unsafe.getObject(t, j), zzbey);
                                break;
                            }
                            break;
                        case 60:
                            if (zza(t, i6, i2)) {
                                zzbey.zza(i6, unsafe.getObject(t, j), zzcq(i2));
                                break;
                            }
                            break;
                        case 61:
                            if (zza(t, i6, i2)) {
                                zzbey.zza(i6, (zzbah) unsafe.getObject(t, j));
                                break;
                            }
                            break;
                        case 62:
                            if (zza(t, i6, i2)) {
                                zzbey.zzn(i6, zzh(t, j));
                                break;
                            }
                            break;
                        case 63:
                            if (zza(t, i6, i2)) {
                                zzbey.zzx(i6, zzh(t, j));
                                break;
                            }
                            break;
                        case 64:
                            if (zza(t, i6, i2)) {
                                zzbey.zzw(i6, zzh(t, j));
                                break;
                            }
                            break;
                        case 65:
                            if (zza(t, i6, i2)) {
                                zzbey.zzj(i6, zzi(t, j));
                                break;
                            }
                            break;
                        case 66:
                            if (zza(t, i6, i2)) {
                                zzbey.zzo(i6, zzh(t, j));
                                break;
                            }
                            break;
                        case 67:
                            if (zza(t, i6, i2)) {
                                zzbey.zzb(i6, zzi(t, j));
                                break;
                            }
                            break;
                        case 68:
                            if (zza(t, i6, i2)) {
                                zzbey.zzb(i6, unsafe.getObject(t, j), zzcq(i2));
                                break;
                            }
                            break;
                    }
                }
                while (entry != null) {
                    this.zzdww.zza(zzbey, entry);
                    entry = it.hasNext() ? it.next() : null;
                }
                zza(this.zzdwv, t, zzbey);
            }
        }
        it = null;
        entry = null;
        int i42 = -1;
        length = this.zzdwg.length;
        Unsafe unsafe2 = zzdwf;
        int i52 = 0;
        while (i < length) {
        }
        while (entry != null) {
        }
        zza(this.zzdwv, t, zzbey);
    }

    private final void zzb(T t, T t2, int i) {
        int zzct = zzct(i);
        int i2 = this.zzdwg[i];
        long j = (long) (zzct & 1048575);
        if (zza(t2, i2, i)) {
            Object zzp = zzbek.zzp(t, j);
            Object zzp2 = zzbek.zzp(t2, j);
            if (zzp != null && zzp2 != null) {
                zzbek.zza(t, j, zzbbq.zza(zzp, zzp2));
                zzb(t, i2, i);
            } else if (zzp2 != null) {
                zzbek.zza(t, j, zzp2);
                zzb(t, i2, i);
            }
        }
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final zzbdm zzcq(int i) {
        int i2 = (i / 4) << 1;
        zzbdm zzbdm = (zzbdm) this.zzdwh[i2];
        if (zzbdm != null) {
            return zzbdm;
        }
        zzbdm<T> zze = zzbdg.zzaeo().zze((Class) this.zzdwh[i2 + 1]);
        this.zzdwh[i2] = zze;
        return zze;
    }

    private final Object zzcr(int i) {
        return this.zzdwh[(i / 4) << 1];
    }

    private final zzbbs<?> zzcs(int i) {
        return (zzbbs) this.zzdwh[((i / 4) << 1) + 1];
    }

    private final int zzct(int i) {
        return this.zzdwg[i + 1];
    }

    private final int zzcu(int i) {
        return this.zzdwg[i + 2];
    }

    private static boolean zzcv(int i) {
        return (i & 536870912) != 0;
    }

    private final int zzcw(int i) {
        int i2 = this.zzdwi;
        if (i >= i2) {
            int i3 = this.zzdwk;
            if (i < i3) {
                int i4 = (i - i2) << 2;
                if (this.zzdwg[i4] == i) {
                    return i4;
                }
                return -1;
            } else if (i <= this.zzdwj) {
                int i5 = i3 - i2;
                int length = (this.zzdwg.length / 4) - 1;
                while (i5 <= length) {
                    int i6 = (length + i5) >>> 1;
                    int i7 = i6 << 2;
                    int i8 = this.zzdwg[i7];
                    if (i == i8) {
                        return i7;
                    }
                    if (i < i8) {
                        length = i6 - 1;
                    } else {
                        i5 = i6 + 1;
                    }
                }
            }
        }
        return -1;
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zzbek.zzp(obj, j);
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzbek.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzbek.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzbek.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzbek.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzbek.zzp(t, j)).booleanValue();
    }

    private static zzbef zzz(Object obj) {
        zzbbo zzbbo = (zzbbo) obj;
        zzbef zzbef = zzbbo.zzdtt;
        if (zzbef != zzbef.zzagc()) {
            return zzbef;
        }
        zzbef zzagd = zzbef.zzagd();
        zzbbo.zzdtt = zzagd;
        return zzagd;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005c, code lost:
        if (com.google.android.gms.internal.ads.zzbdo.zzd(com.google.android.gms.internal.ads.zzbek.zzp(r10, r6), com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0070, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0096, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a8, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ba, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00cc, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e2, code lost:
        if (com.google.android.gms.internal.ads.zzbdo.zzd(com.google.android.gms.internal.ads.zzbek.zzp(r10, r6), com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f8, code lost:
        if (com.google.android.gms.internal.ads.zzbdo.zzd(com.google.android.gms.internal.ads.zzbek.zzp(r10, r6), com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010e, code lost:
        if (com.google.android.gms.internal.ads.zzbdo.zzd(com.google.android.gms.internal.ads.zzbek.zzp(r10, r6), com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0120, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzm(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzm(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0132, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0145, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0156, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0169, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x017c, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x018d, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01a0, code lost:
        if (com.google.android.gms.internal.ads.zzbek.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbek.zzl(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.ads.zzbdo.zzd(com.google.android.gms.internal.ads.zzbek.zzp(r10, r6), com.google.android.gms.internal.ads.zzbek.zzp(r11, r6)) != false) goto L_0x01a3;
     */
    @Override // com.google.android.gms.internal.ads.zzbdm
    public final boolean equals(T t, T t2) {
        int length = this.zzdwg.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                int zzct = zzct(i);
                long j = (long) (zzct & 1048575);
                switch ((zzct & 267386880) >>> 20) {
                    case 0:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 1:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 2:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 3:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 4:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 5:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 6:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 7:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 8:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 9:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 10:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 11:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 12:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 13:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 14:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 15:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 16:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 17:
                        if (zzc(t, t2, i)) {
                            break;
                        }
                        z = false;
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                        z = zzbdo.zzd(zzbek.zzp(t, j), zzbek.zzp(t2, j));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                        long zzcu = (long) (zzcu(i) & 1048575);
                        if (zzbek.zzk(t, zzcu) == zzbek.zzk(t2, zzcu)) {
                            break;
                        }
                        z = false;
                        break;
                }
                if (!z) {
                    return false;
                }
                i += 4;
            } else if (!this.zzdwv.zzac(t).equals(this.zzdwv.zzac(t2))) {
                return false;
            } else {
                if (this.zzdwm) {
                    return this.zzdww.zzm(t).equals(this.zzdww.zzm(t2));
                }
                return true;
            }
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ce, code lost:
        if (r3 != null) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e0, code lost:
        if (r3 != null) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e2, code lost:
        r7 = r3.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e6, code lost:
        r2 = (r2 * 53) + r7;
     */
    @Override // com.google.android.gms.internal.ads.zzbdm
    public final int hashCode(T t) {
        int i;
        int i2;
        long j;
        double d;
        float f;
        boolean z;
        Object obj;
        Object obj2;
        int length = this.zzdwg.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 4) {
            int zzct = zzct(i4);
            int i5 = this.zzdwg[i4];
            long j2 = (long) (1048575 & zzct);
            int i6 = 37;
            switch ((zzct & 267386880) >>> 20) {
                case 0:
                    i2 = i3 * 53;
                    d = zzbek.zzo(t, j2);
                    j = Double.doubleToLongBits(d);
                    i = zzbbq.zzv(j);
                    i3 = i2 + i;
                    break;
                case 1:
                    i2 = i3 * 53;
                    f = zzbek.zzn(t, j2);
                    i = Float.floatToIntBits(f);
                    i3 = i2 + i;
                    break;
                case 2:
                case 3:
                case 5:
                case 14:
                case 16:
                    i2 = i3 * 53;
                    j = zzbek.zzl(t, j2);
                    i = zzbbq.zzv(j);
                    i3 = i2 + i;
                    break;
                case 4:
                case 6:
                case 11:
                case 12:
                case 13:
                case 15:
                    i2 = i3 * 53;
                    i = zzbek.zzk(t, j2);
                    i3 = i2 + i;
                    break;
                case 7:
                    i2 = i3 * 53;
                    z = zzbek.zzm(t, j2);
                    i = zzbbq.zzar(z);
                    i3 = i2 + i;
                    break;
                case 8:
                    i2 = i3 * 53;
                    i = ((String) zzbek.zzp(t, j2)).hashCode();
                    i3 = i2 + i;
                    break;
                case 9:
                    obj = zzbek.zzp(t, j2);
                    break;
                case 10:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                    i2 = i3 * 53;
                    obj2 = zzbek.zzp(t, j2);
                    i = obj2.hashCode();
                    i3 = i2 + i;
                    break;
                case 17:
                    obj = zzbek.zzp(t, j2);
                    break;
                case 51:
                    if (zza(t, i5, i4)) {
                        i2 = i3 * 53;
                        d = zzf(t, j2);
                        j = Double.doubleToLongBits(d);
                        i = zzbbq.zzv(j);
                        i3 = i2 + i;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza(t, i5, i4)) {
                        i2 = i3 * 53;
                        f = zzg(t, j2);
                        i = Float.floatToIntBits(f);
                        i3 = i2 + i;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    j = zzi(t, j2);
                    i = zzbbq.zzv(j);
                    i3 = i2 + i;
                    break;
                case 54:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    j = zzi(t, j2);
                    i = zzbbq.zzv(j);
                    i3 = i2 + i;
                    break;
                case 55:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    i = zzh(t, j2);
                    i3 = i2 + i;
                    break;
                case 56:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    j = zzi(t, j2);
                    i = zzbbq.zzv(j);
                    i3 = i2 + i;
                    break;
                case 57:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    i = zzh(t, j2);
                    i3 = i2 + i;
                    break;
                case 58:
                    if (zza(t, i5, i4)) {
                        i2 = i3 * 53;
                        z = zzj(t, j2);
                        i = zzbbq.zzar(z);
                        i3 = i2 + i;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    i = ((String) zzbek.zzp(t, j2)).hashCode();
                    i3 = i2 + i;
                    break;
                case 60:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    obj2 = zzbek.zzp(t, j2);
                    i2 = i3 * 53;
                    i = obj2.hashCode();
                    i3 = i2 + i;
                    break;
                case 61:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    obj2 = zzbek.zzp(t, j2);
                    i = obj2.hashCode();
                    i3 = i2 + i;
                    break;
                case 62:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    i = zzh(t, j2);
                    i3 = i2 + i;
                    break;
                case 63:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    i = zzh(t, j2);
                    i3 = i2 + i;
                    break;
                case 64:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    i = zzh(t, j2);
                    i3 = i2 + i;
                    break;
                case 65:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    j = zzi(t, j2);
                    i = zzbbq.zzv(j);
                    i3 = i2 + i;
                    break;
                case 66:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    i = zzh(t, j2);
                    i3 = i2 + i;
                    break;
                case 67:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    j = zzi(t, j2);
                    i = zzbbq.zzv(j);
                    i3 = i2 + i;
                    break;
                case 68:
                    if (!zza(t, i5, i4)) {
                        break;
                    }
                    obj2 = zzbek.zzp(t, j2);
                    i2 = i3 * 53;
                    i = obj2.hashCode();
                    i3 = i2 + i;
                    break;
            }
        }
        int hashCode = (i3 * 53) + this.zzdwv.zzac(t).hashCode();
        return this.zzdwm ? (hashCode * 53) + this.zzdww.zzm(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.ads.zzbdm
    public final T newInstance() {
        return (T) this.zzdwt.newInstance(this.zzdwl);
    }

    /*  JADX ERROR: StackOverflowError in pass: MarkFinallyVisitor
        java.lang.StackOverflowError
        	at jadx.core.dex.instructions.IfNode.isSame(IfNode.java:122)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.sameInsns(MarkFinallyVisitor.java:451)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.compareBlocks(MarkFinallyVisitor.java:436)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.checkBlocksTree(MarkFinallyVisitor.java:408)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.checkBlocksTree(MarkFinallyVisitor.java:411)
        */
    @Override // com.google.android.gms.internal.ads.zzbdm
    public final void zza(T r18, com.google.android.gms.internal.ads.zzbdl r19, com.google.android.gms.internal.ads.zzbbb r20) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1486
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zza(java.lang.Object, com.google.android.gms.internal.ads.zzbdl, com.google.android.gms.internal.ads.zzbbb):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x04b9  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x04f6  */
    /* JADX WARNING: Removed duplicated region for block: B:351:0x0976  */
    @Override // com.google.android.gms.internal.ads.zzbdm
    public final void zza(T t, zzbey zzbey) throws IOException {
        Map.Entry<?, Object> entry;
        Iterator<Map.Entry<?, Object>> it;
        int length;
        int i;
        double d;
        float f;
        long j;
        long j2;
        int i2;
        long j3;
        int i3;
        boolean z;
        int i4;
        int i5;
        int i6;
        long j4;
        int i7;
        long j5;
        Map.Entry<?, Object> entry2;
        Iterator<Map.Entry<?, Object>> it2;
        int length2;
        double d2;
        float f2;
        long j6;
        long j7;
        int i8;
        long j8;
        int i9;
        boolean z2;
        int i10;
        int i11;
        int i12;
        long j9;
        int i13;
        long j10;
        if (zzbey.zzacn() == zzbbo.zze.zzdum) {
            zza(this.zzdwv, t, zzbey);
            if (this.zzdwm) {
                zzbbg<?> zzm = this.zzdww.zzm(t);
                if (!zzm.isEmpty()) {
                    it2 = zzm.descendingIterator();
                    entry2 = it2.next();
                    for (length2 = this.zzdwg.length - 4; length2 >= 0; length2 -= 4) {
                        int zzct = zzct(length2);
                        int i14 = this.zzdwg[length2];
                        while (entry2 != null && this.zzdww.zza(entry2) > i14) {
                            this.zzdww.zza(zzbey, entry2);
                            entry2 = it2.hasNext() ? it2.next() : null;
                        }
                        switch ((zzct & 267386880) >>> 20) {
                            case 0:
                                if (zza(t, length2)) {
                                    d2 = zzbek.zzo(t, (long) (zzct & 1048575));
                                    zzbey.zza(i14, d2);
                                    break;
                                } else {
                                    break;
                                }
                            case 1:
                                if (zza(t, length2)) {
                                    f2 = zzbek.zzn(t, (long) (zzct & 1048575));
                                    zzbey.zza(i14, f2);
                                    break;
                                } else {
                                    break;
                                }
                            case 2:
                                if (zza(t, length2)) {
                                    j6 = zzbek.zzl(t, (long) (zzct & 1048575));
                                    zzbey.zzi(i14, j6);
                                    break;
                                } else {
                                    break;
                                }
                            case 3:
                                if (zza(t, length2)) {
                                    j7 = zzbek.zzl(t, (long) (zzct & 1048575));
                                    zzbey.zza(i14, j7);
                                    break;
                                } else {
                                    break;
                                }
                            case 4:
                                if (zza(t, length2)) {
                                    i8 = zzbek.zzk(t, (long) (zzct & 1048575));
                                    zzbey.zzm(i14, i8);
                                    break;
                                } else {
                                    break;
                                }
                            case 5:
                                if (zza(t, length2)) {
                                    j8 = zzbek.zzl(t, (long) (zzct & 1048575));
                                    zzbey.zzc(i14, j8);
                                    break;
                                } else {
                                    break;
                                }
                            case 6:
                                if (zza(t, length2)) {
                                    i9 = zzbek.zzk(t, (long) (zzct & 1048575));
                                    zzbey.zzp(i14, i9);
                                    break;
                                } else {
                                    break;
                                }
                            case 7:
                                if (zza(t, length2)) {
                                    z2 = zzbek.zzm(t, (long) (zzct & 1048575));
                                    zzbey.zzf(i14, z2);
                                    break;
                                } else {
                                    break;
                                }
                            case 8:
                                if (!zza(t, length2)) {
                                    break;
                                }
                                zza(i14, zzbek.zzp(t, (long) (zzct & 1048575)), zzbey);
                                break;
                            case 9:
                                if (!zza(t, length2)) {
                                    break;
                                }
                                zzbey.zza(i14, zzbek.zzp(t, (long) (zzct & 1048575)), zzcq(length2));
                                break;
                            case 10:
                                if (!zza(t, length2)) {
                                    break;
                                }
                                zzbey.zza(i14, (zzbah) zzbek.zzp(t, (long) (zzct & 1048575)));
                                break;
                            case 11:
                                if (zza(t, length2)) {
                                    i10 = zzbek.zzk(t, (long) (zzct & 1048575));
                                    zzbey.zzn(i14, i10);
                                    break;
                                } else {
                                    break;
                                }
                            case 12:
                                if (zza(t, length2)) {
                                    i11 = zzbek.zzk(t, (long) (zzct & 1048575));
                                    zzbey.zzx(i14, i11);
                                    break;
                                } else {
                                    break;
                                }
                            case 13:
                                if (zza(t, length2)) {
                                    i12 = zzbek.zzk(t, (long) (zzct & 1048575));
                                    zzbey.zzw(i14, i12);
                                    break;
                                } else {
                                    break;
                                }
                            case 14:
                                if (zza(t, length2)) {
                                    j9 = zzbek.zzl(t, (long) (zzct & 1048575));
                                    zzbey.zzj(i14, j9);
                                    break;
                                } else {
                                    break;
                                }
                            case 15:
                                if (zza(t, length2)) {
                                    i13 = zzbek.zzk(t, (long) (zzct & 1048575));
                                    zzbey.zzo(i14, i13);
                                    break;
                                } else {
                                    break;
                                }
                            case 16:
                                if (zza(t, length2)) {
                                    j10 = zzbek.zzl(t, (long) (zzct & 1048575));
                                    zzbey.zzb(i14, j10);
                                    break;
                                } else {
                                    break;
                                }
                            case 17:
                                if (!zza(t, length2)) {
                                    break;
                                }
                                zzbey.zzb(i14, zzbek.zzp(t, (long) (zzct & 1048575)), zzcq(length2));
                                break;
                            case 18:
                                zzbdo.zza(this.zzdwg[length2], (List<Double>) ((List) zzbek.zzp(t, (long) (zzct & 1048575))), zzbey, false);
                                break;
                            case 19:
                                zzbdo.zzb(this.zzdwg[length2], (List<Float>) ((List) zzbek.zzp(t, (long) (zzct & 1048575))), zzbey, false);
                                break;
                            case 20:
                                zzbdo.zzc(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 21:
                                zzbdo.zzd(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 22:
                                zzbdo.zzh(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 23:
                                zzbdo.zzf(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 24:
                                zzbdo.zzk(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 25:
                                zzbdo.zzn(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 26:
                                zzbdo.zza(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey);
                                break;
                            case 27:
                                zzbdo.zza(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, zzcq(length2));
                                break;
                            case 28:
                                zzbdo.zzb(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey);
                                break;
                            case 29:
                                zzbdo.zzi(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 30:
                                zzbdo.zzm(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 31:
                                zzbdo.zzl(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 32:
                                zzbdo.zzg(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 33:
                                zzbdo.zzj(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 34:
                                zzbdo.zze(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, false);
                                break;
                            case 35:
                                zzbdo.zza(this.zzdwg[length2], (List<Double>) ((List) zzbek.zzp(t, (long) (zzct & 1048575))), zzbey, true);
                                break;
                            case 36:
                                zzbdo.zzb(this.zzdwg[length2], (List<Float>) ((List) zzbek.zzp(t, (long) (zzct & 1048575))), zzbey, true);
                                break;
                            case 37:
                                zzbdo.zzc(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 38:
                                zzbdo.zzd(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 39:
                                zzbdo.zzh(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 40:
                                zzbdo.zzf(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 41:
                                zzbdo.zzk(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 42:
                                zzbdo.zzn(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 43:
                                zzbdo.zzi(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 44:
                                zzbdo.zzm(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 45:
                                zzbdo.zzl(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 46:
                                zzbdo.zzg(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 47:
                                zzbdo.zzj(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 48:
                                zzbdo.zze(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, true);
                                break;
                            case 49:
                                zzbdo.zzb(this.zzdwg[length2], (List) zzbek.zzp(t, (long) (zzct & 1048575)), zzbey, zzcq(length2));
                                break;
                            case 50:
                                zza(zzbey, i14, zzbek.zzp(t, (long) (zzct & 1048575)), length2);
                                break;
                            case 51:
                                if (zza(t, i14, length2)) {
                                    d2 = zzf(t, (long) (zzct & 1048575));
                                    zzbey.zza(i14, d2);
                                    break;
                                } else {
                                    break;
                                }
                            case 52:
                                if (zza(t, i14, length2)) {
                                    f2 = zzg(t, (long) (zzct & 1048575));
                                    zzbey.zza(i14, f2);
                                    break;
                                } else {
                                    break;
                                }
                            case 53:
                                if (zza(t, i14, length2)) {
                                    j6 = zzi(t, (long) (zzct & 1048575));
                                    zzbey.zzi(i14, j6);
                                    break;
                                } else {
                                    break;
                                }
                            case 54:
                                if (zza(t, i14, length2)) {
                                    j7 = zzi(t, (long) (zzct & 1048575));
                                    zzbey.zza(i14, j7);
                                    break;
                                } else {
                                    break;
                                }
                            case 55:
                                if (zza(t, i14, length2)) {
                                    i8 = zzh(t, (long) (zzct & 1048575));
                                    zzbey.zzm(i14, i8);
                                    break;
                                } else {
                                    break;
                                }
                            case 56:
                                if (zza(t, i14, length2)) {
                                    j8 = zzi(t, (long) (zzct & 1048575));
                                    zzbey.zzc(i14, j8);
                                    break;
                                } else {
                                    break;
                                }
                            case 57:
                                if (zza(t, i14, length2)) {
                                    i9 = zzh(t, (long) (zzct & 1048575));
                                    zzbey.zzp(i14, i9);
                                    break;
                                } else {
                                    break;
                                }
                            case 58:
                                if (zza(t, i14, length2)) {
                                    z2 = zzj(t, (long) (zzct & 1048575));
                                    zzbey.zzf(i14, z2);
                                    break;
                                } else {
                                    break;
                                }
                            case 59:
                                if (!zza(t, i14, length2)) {
                                    break;
                                }
                                zza(i14, zzbek.zzp(t, (long) (zzct & 1048575)), zzbey);
                                break;
                            case 60:
                                if (!zza(t, i14, length2)) {
                                    break;
                                }
                                zzbey.zza(i14, zzbek.zzp(t, (long) (zzct & 1048575)), zzcq(length2));
                                break;
                            case 61:
                                if (!zza(t, i14, length2)) {
                                    break;
                                }
                                zzbey.zza(i14, (zzbah) zzbek.zzp(t, (long) (zzct & 1048575)));
                                break;
                            case 62:
                                if (zza(t, i14, length2)) {
                                    i10 = zzh(t, (long) (zzct & 1048575));
                                    zzbey.zzn(i14, i10);
                                    break;
                                } else {
                                    break;
                                }
                            case 63:
                                if (zza(t, i14, length2)) {
                                    i11 = zzh(t, (long) (zzct & 1048575));
                                    zzbey.zzx(i14, i11);
                                    break;
                                } else {
                                    break;
                                }
                            case 64:
                                if (zza(t, i14, length2)) {
                                    i12 = zzh(t, (long) (zzct & 1048575));
                                    zzbey.zzw(i14, i12);
                                    break;
                                } else {
                                    break;
                                }
                            case 65:
                                if (zza(t, i14, length2)) {
                                    j9 = zzi(t, (long) (zzct & 1048575));
                                    zzbey.zzj(i14, j9);
                                    break;
                                } else {
                                    break;
                                }
                            case 66:
                                if (zza(t, i14, length2)) {
                                    i13 = zzh(t, (long) (zzct & 1048575));
                                    zzbey.zzo(i14, i13);
                                    break;
                                } else {
                                    break;
                                }
                            case 67:
                                if (zza(t, i14, length2)) {
                                    j10 = zzi(t, (long) (zzct & 1048575));
                                    zzbey.zzb(i14, j10);
                                    break;
                                } else {
                                    break;
                                }
                            case 68:
                                if (!zza(t, i14, length2)) {
                                    break;
                                }
                                zzbey.zzb(i14, zzbek.zzp(t, (long) (zzct & 1048575)), zzcq(length2));
                                break;
                        }
                    }
                    while (entry2 != null) {
                        this.zzdww.zza(zzbey, entry2);
                        entry2 = it2.hasNext() ? it2.next() : null;
                    }
                }
            }
            it2 = null;
            entry2 = null;
            while (length2 >= 0) {
            }
            while (entry2 != null) {
            }
        } else if (this.zzdwo) {
            if (this.zzdwm) {
                zzbbg<?> zzm2 = this.zzdww.zzm(t);
                if (!zzm2.isEmpty()) {
                    it = zzm2.iterator();
                    entry = it.next();
                    length = this.zzdwg.length;
                    for (i = 0; i < length; i += 4) {
                        int zzct2 = zzct(i);
                        int i15 = this.zzdwg[i];
                        while (entry != null && this.zzdww.zza(entry) <= i15) {
                            this.zzdww.zza(zzbey, entry);
                            entry = it.hasNext() ? it.next() : null;
                        }
                        switch ((zzct2 & 267386880) >>> 20) {
                            case 0:
                                if (zza(t, i)) {
                                    d = zzbek.zzo(t, (long) (zzct2 & 1048575));
                                    zzbey.zza(i15, d);
                                    break;
                                } else {
                                    break;
                                }
                            case 1:
                                if (zza(t, i)) {
                                    f = zzbek.zzn(t, (long) (zzct2 & 1048575));
                                    zzbey.zza(i15, f);
                                    break;
                                } else {
                                    break;
                                }
                            case 2:
                                if (zza(t, i)) {
                                    j = zzbek.zzl(t, (long) (zzct2 & 1048575));
                                    zzbey.zzi(i15, j);
                                    break;
                                } else {
                                    break;
                                }
                            case 3:
                                if (zza(t, i)) {
                                    j2 = zzbek.zzl(t, (long) (zzct2 & 1048575));
                                    zzbey.zza(i15, j2);
                                    break;
                                } else {
                                    break;
                                }
                            case 4:
                                if (zza(t, i)) {
                                    i2 = zzbek.zzk(t, (long) (zzct2 & 1048575));
                                    zzbey.zzm(i15, i2);
                                    break;
                                } else {
                                    break;
                                }
                            case 5:
                                if (zza(t, i)) {
                                    j3 = zzbek.zzl(t, (long) (zzct2 & 1048575));
                                    zzbey.zzc(i15, j3);
                                    break;
                                } else {
                                    break;
                                }
                            case 6:
                                if (zza(t, i)) {
                                    i3 = zzbek.zzk(t, (long) (zzct2 & 1048575));
                                    zzbey.zzp(i15, i3);
                                    break;
                                } else {
                                    break;
                                }
                            case 7:
                                if (zza(t, i)) {
                                    z = zzbek.zzm(t, (long) (zzct2 & 1048575));
                                    zzbey.zzf(i15, z);
                                    break;
                                } else {
                                    break;
                                }
                            case 8:
                                if (!zza(t, i)) {
                                    break;
                                }
                                zza(i15, zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey);
                                break;
                            case 9:
                                if (!zza(t, i)) {
                                    break;
                                }
                                zzbey.zza(i15, zzbek.zzp(t, (long) (zzct2 & 1048575)), zzcq(i));
                                break;
                            case 10:
                                if (!zza(t, i)) {
                                    break;
                                }
                                zzbey.zza(i15, (zzbah) zzbek.zzp(t, (long) (zzct2 & 1048575)));
                                break;
                            case 11:
                                if (zza(t, i)) {
                                    i4 = zzbek.zzk(t, (long) (zzct2 & 1048575));
                                    zzbey.zzn(i15, i4);
                                    break;
                                } else {
                                    break;
                                }
                            case 12:
                                if (zza(t, i)) {
                                    i5 = zzbek.zzk(t, (long) (zzct2 & 1048575));
                                    zzbey.zzx(i15, i5);
                                    break;
                                } else {
                                    break;
                                }
                            case 13:
                                if (zza(t, i)) {
                                    i6 = zzbek.zzk(t, (long) (zzct2 & 1048575));
                                    zzbey.zzw(i15, i6);
                                    break;
                                } else {
                                    break;
                                }
                            case 14:
                                if (zza(t, i)) {
                                    j4 = zzbek.zzl(t, (long) (zzct2 & 1048575));
                                    zzbey.zzj(i15, j4);
                                    break;
                                } else {
                                    break;
                                }
                            case 15:
                                if (zza(t, i)) {
                                    i7 = zzbek.zzk(t, (long) (zzct2 & 1048575));
                                    zzbey.zzo(i15, i7);
                                    break;
                                } else {
                                    break;
                                }
                            case 16:
                                if (zza(t, i)) {
                                    j5 = zzbek.zzl(t, (long) (zzct2 & 1048575));
                                    zzbey.zzb(i15, j5);
                                    break;
                                } else {
                                    break;
                                }
                            case 17:
                                if (!zza(t, i)) {
                                    break;
                                }
                                zzbey.zzb(i15, zzbek.zzp(t, (long) (zzct2 & 1048575)), zzcq(i));
                                break;
                            case 18:
                                zzbdo.zza(this.zzdwg[i], (List<Double>) ((List) zzbek.zzp(t, (long) (zzct2 & 1048575))), zzbey, false);
                                break;
                            case 19:
                                zzbdo.zzb(this.zzdwg[i], (List<Float>) ((List) zzbek.zzp(t, (long) (zzct2 & 1048575))), zzbey, false);
                                break;
                            case 20:
                                zzbdo.zzc(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 21:
                                zzbdo.zzd(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 22:
                                zzbdo.zzh(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 23:
                                zzbdo.zzf(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 24:
                                zzbdo.zzk(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 25:
                                zzbdo.zzn(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 26:
                                zzbdo.zza(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey);
                                break;
                            case 27:
                                zzbdo.zza(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, zzcq(i));
                                break;
                            case 28:
                                zzbdo.zzb(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey);
                                break;
                            case 29:
                                zzbdo.zzi(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 30:
                                zzbdo.zzm(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 31:
                                zzbdo.zzl(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 32:
                                zzbdo.zzg(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 33:
                                zzbdo.zzj(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 34:
                                zzbdo.zze(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, false);
                                break;
                            case 35:
                                zzbdo.zza(this.zzdwg[i], (List<Double>) ((List) zzbek.zzp(t, (long) (zzct2 & 1048575))), zzbey, true);
                                break;
                            case 36:
                                zzbdo.zzb(this.zzdwg[i], (List<Float>) ((List) zzbek.zzp(t, (long) (zzct2 & 1048575))), zzbey, true);
                                break;
                            case 37:
                                zzbdo.zzc(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 38:
                                zzbdo.zzd(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 39:
                                zzbdo.zzh(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 40:
                                zzbdo.zzf(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 41:
                                zzbdo.zzk(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 42:
                                zzbdo.zzn(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 43:
                                zzbdo.zzi(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 44:
                                zzbdo.zzm(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 45:
                                zzbdo.zzl(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 46:
                                zzbdo.zzg(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 47:
                                zzbdo.zzj(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 48:
                                zzbdo.zze(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, true);
                                break;
                            case 49:
                                zzbdo.zzb(this.zzdwg[i], (List) zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey, zzcq(i));
                                break;
                            case 50:
                                zza(zzbey, i15, zzbek.zzp(t, (long) (zzct2 & 1048575)), i);
                                break;
                            case 51:
                                if (zza(t, i15, i)) {
                                    d = zzf(t, (long) (zzct2 & 1048575));
                                    zzbey.zza(i15, d);
                                    break;
                                } else {
                                    break;
                                }
                            case 52:
                                if (zza(t, i15, i)) {
                                    f = zzg(t, (long) (zzct2 & 1048575));
                                    zzbey.zza(i15, f);
                                    break;
                                } else {
                                    break;
                                }
                            case 53:
                                if (zza(t, i15, i)) {
                                    j = zzi(t, (long) (zzct2 & 1048575));
                                    zzbey.zzi(i15, j);
                                    break;
                                } else {
                                    break;
                                }
                            case 54:
                                if (zza(t, i15, i)) {
                                    j2 = zzi(t, (long) (zzct2 & 1048575));
                                    zzbey.zza(i15, j2);
                                    break;
                                } else {
                                    break;
                                }
                            case 55:
                                if (zza(t, i15, i)) {
                                    i2 = zzh(t, (long) (zzct2 & 1048575));
                                    zzbey.zzm(i15, i2);
                                    break;
                                } else {
                                    break;
                                }
                            case 56:
                                if (zza(t, i15, i)) {
                                    j3 = zzi(t, (long) (zzct2 & 1048575));
                                    zzbey.zzc(i15, j3);
                                    break;
                                } else {
                                    break;
                                }
                            case 57:
                                if (zza(t, i15, i)) {
                                    i3 = zzh(t, (long) (zzct2 & 1048575));
                                    zzbey.zzp(i15, i3);
                                    break;
                                } else {
                                    break;
                                }
                            case 58:
                                if (zza(t, i15, i)) {
                                    z = zzj(t, (long) (zzct2 & 1048575));
                                    zzbey.zzf(i15, z);
                                    break;
                                } else {
                                    break;
                                }
                            case 59:
                                if (!zza(t, i15, i)) {
                                    break;
                                }
                                zza(i15, zzbek.zzp(t, (long) (zzct2 & 1048575)), zzbey);
                                break;
                            case 60:
                                if (!zza(t, i15, i)) {
                                    break;
                                }
                                zzbey.zza(i15, zzbek.zzp(t, (long) (zzct2 & 1048575)), zzcq(i));
                                break;
                            case 61:
                                if (!zza(t, i15, i)) {
                                    break;
                                }
                                zzbey.zza(i15, (zzbah) zzbek.zzp(t, (long) (zzct2 & 1048575)));
                                break;
                            case 62:
                                if (zza(t, i15, i)) {
                                    i4 = zzh(t, (long) (zzct2 & 1048575));
                                    zzbey.zzn(i15, i4);
                                    break;
                                } else {
                                    break;
                                }
                            case 63:
                                if (zza(t, i15, i)) {
                                    i5 = zzh(t, (long) (zzct2 & 1048575));
                                    zzbey.zzx(i15, i5);
                                    break;
                                } else {
                                    break;
                                }
                            case 64:
                                if (zza(t, i15, i)) {
                                    i6 = zzh(t, (long) (zzct2 & 1048575));
                                    zzbey.zzw(i15, i6);
                                    break;
                                } else {
                                    break;
                                }
                            case 65:
                                if (zza(t, i15, i)) {
                                    j4 = zzi(t, (long) (zzct2 & 1048575));
                                    zzbey.zzj(i15, j4);
                                    break;
                                } else {
                                    break;
                                }
                            case 66:
                                if (zza(t, i15, i)) {
                                    i7 = zzh(t, (long) (zzct2 & 1048575));
                                    zzbey.zzo(i15, i7);
                                    break;
                                } else {
                                    break;
                                }
                            case 67:
                                if (zza(t, i15, i)) {
                                    j5 = zzi(t, (long) (zzct2 & 1048575));
                                    zzbey.zzb(i15, j5);
                                    break;
                                } else {
                                    break;
                                }
                            case 68:
                                if (!zza(t, i15, i)) {
                                    break;
                                }
                                zzbey.zzb(i15, zzbek.zzp(t, (long) (zzct2 & 1048575)), zzcq(i));
                                break;
                        }
                    }
                    while (entry != null) {
                        this.zzdww.zza(zzbey, entry);
                        entry = it.hasNext() ? it.next() : null;
                    }
                    zza(this.zzdwv, t, zzbey);
                }
            }
            it = null;
            entry = null;
            length = this.zzdwg.length;
            while (i < length) {
            }
            while (entry != null) {
            }
            zza(this.zzdwv, t, zzbey);
        } else {
            zzb(t, zzbey);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v25, types: [int] */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0069, code lost:
        if (r7 == 0) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ce, code lost:
        if (r7 == 0) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d0, code lost:
        r0 = com.google.android.gms.internal.ads.zzbad.zza(r12, r10, r11);
        r1 = r11.zzdpl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0164, code lost:
        if (r0 == r10) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0188, code lost:
        if (r0 == r15) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01a1, code lost:
        if (r0 == r15) goto L_0x01a3;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // com.google.android.gms.internal.ads.zzbdm
    public final void zza(T t, byte[] bArr, int i, int i2, zzbae zzbae) throws IOException {
        byte b;
        int i3;
        Unsafe unsafe;
        int i4;
        int i5;
        int i6;
        long j;
        Object zza;
        zzbcy<T> zzbcy = this;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i7 = i2;
        zzbae zzbae2 = zzbae;
        if (zzbcy.zzdwo) {
            Unsafe unsafe2 = zzdwf;
            int i8 = i;
            while (i8 < i7) {
                int i9 = i8 + 1;
                byte b2 = bArr2[i8];
                if (b2 < 0) {
                    i3 = zzbad.zza(b2, bArr2, i9, zzbae2);
                    b = zzbae2.zzdpl;
                } else {
                    b = b2;
                    i3 = i9;
                }
                int i10 = b >>> 3;
                int i11 = b & 7;
                int zzcw = zzbcy.zzcw(i10);
                if (zzcw >= 0) {
                    int i12 = zzbcy.zzdwg[zzcw + 1];
                    int i13 = (267386880 & i12) >>> 20;
                    long j2 = (long) (1048575 & i12);
                    if (i13 <= 17) {
                        boolean z = true;
                        switch (i13) {
                            case 0:
                                if (i11 == 1) {
                                    zzbek.zza(t2, j2, zzbad.zzg(bArr2, i3));
                                    i8 = i3 + 8;
                                    break;
                                }
                                break;
                            case 1:
                                if (i11 == 5) {
                                    zzbek.zza((Object) t2, j2, zzbad.zzh(bArr2, i3));
                                    i8 = i3 + 4;
                                    break;
                                }
                                break;
                            case 2:
                            case 3:
                                if (i11 == 0) {
                                    i6 = zzbad.zzb(bArr2, i3, zzbae2);
                                    j = zzbae2.zzdpm;
                                    unsafe2.putLong(t, j2, j);
                                    i8 = i6;
                                    break;
                                }
                                break;
                            case 5:
                            case 14:
                                if (i11 == 1) {
                                    unsafe2.putLong(t, j2, zzbad.zzf(bArr2, i3));
                                    i8 = i3 + 8;
                                    break;
                                }
                                break;
                            case 6:
                            case 13:
                                if (i11 == 5) {
                                    unsafe2.putInt(t2, j2, zzbad.zze(bArr2, i3));
                                    i8 = i3 + 4;
                                    break;
                                }
                                break;
                            case 7:
                                if (i11 == 0) {
                                    i8 = zzbad.zzb(bArr2, i3, zzbae2);
                                    if (zzbae2.zzdpm == 0) {
                                        z = false;
                                    }
                                    zzbek.zza(t2, j2, z);
                                    break;
                                }
                                break;
                            case 8:
                                if (i11 == 2) {
                                    i8 = (536870912 & i12) == 0 ? zzbad.zzc(bArr2, i3, zzbae2) : zzbad.zzd(bArr2, i3, zzbae2);
                                    zza = zzbae2.zzdpn;
                                    unsafe2.putObject(t2, j2, zza);
                                    break;
                                }
                                break;
                            case 9:
                                if (i11 == 2) {
                                    i8 = zza(zzbcy.zzcq(zzcw), bArr2, i3, i7, zzbae2);
                                    Object object = unsafe2.getObject(t2, j2);
                                    if (object != null) {
                                        zza = zzbbq.zza(object, zzbae2.zzdpn);
                                        unsafe2.putObject(t2, j2, zza);
                                        break;
                                    }
                                    zza = zzbae2.zzdpn;
                                    unsafe2.putObject(t2, j2, zza);
                                }
                                break;
                            case 10:
                                if (i11 == 2) {
                                    i8 = zzbad.zze(bArr2, i3, zzbae2);
                                    zza = zzbae2.zzdpn;
                                    unsafe2.putObject(t2, j2, zza);
                                    break;
                                }
                                break;
                            case 15:
                                if (i11 == 0) {
                                    i8 = zzbad.zza(bArr2, i3, zzbae2);
                                    int i14 = zzbaq.zzbu(zzbae2.zzdpl);
                                    unsafe2.putInt(t2, j2, i14);
                                    break;
                                }
                                break;
                            case 16:
                                if (i11 == 0) {
                                    i6 = zzbad.zzb(bArr2, i3, zzbae2);
                                    j = zzbaq.zzl(zzbae2.zzdpm);
                                    unsafe2.putLong(t, j2, j);
                                    i8 = i6;
                                    break;
                                }
                                break;
                        }
                    } else if (i13 != 27) {
                        if (i13 <= 49) {
                            unsafe = unsafe2;
                            i8 = zza(t, bArr, i3, i2, b, i10, i11, zzcw, (long) i12, i13, j2, zzbae);
                        } else {
                            unsafe = unsafe2;
                            i5 = i3;
                            if (i13 == 50) {
                                if (i11 == 2) {
                                    i8 = zza(t, bArr, i5, i2, zzcw, i10, j2, zzbae);
                                }
                                i4 = i5;
                                i8 = zza(b, bArr, i4, i2, t, zzbae);
                                zzbcy = this;
                                t2 = t;
                                bArr2 = bArr;
                                i7 = i2;
                                zzbae2 = zzbae;
                                unsafe2 = unsafe;
                            } else {
                                i8 = zza(t, bArr, i5, i2, b, i10, i11, i12, i13, j2, zzcw, zzbae);
                            }
                        }
                        i4 = i8;
                        i8 = zza(b, bArr, i4, i2, t, zzbae);
                        zzbcy = this;
                        t2 = t;
                        bArr2 = bArr;
                        i7 = i2;
                        zzbae2 = zzbae;
                        unsafe2 = unsafe;
                    } else if (i11 == 2) {
                        zzbbt zzbbt = (zzbbt) unsafe2.getObject(t2, j2);
                        if (!zzbbt.zzaay()) {
                            int size = zzbbt.size();
                            zzbbt = zzbbt.zzbm(size == 0 ? 10 : size << 1);
                            unsafe2.putObject(t2, j2, zzbbt);
                        }
                        i8 = zza(zzbcy.zzcq(zzcw), b, bArr, i3, i2, zzbbt, zzbae);
                    }
                }
                unsafe = unsafe2;
                i5 = i3;
                i4 = i5;
                i8 = zza(b, bArr, i4, i2, t, zzbae);
                zzbcy = this;
                t2 = t;
                bArr2 = bArr;
                i7 = i2;
                zzbae2 = zzbae;
                unsafe2 = unsafe;
            }
            if (i8 != i7) {
                throw zzbbu.zzadr();
            }
            return;
        }
        zza((Object) t, bArr, i, i2, 0, zzbae);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10, types: [com.google.android.gms.internal.ads.zzbdm] */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v27, types: [com.google.android.gms.internal.ads.zzbdm] */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0109 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x011d A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // com.google.android.gms.internal.ads.zzbdm
    public final boolean zzaa(T t) {
        int i;
        int i2;
        boolean z;
        boolean z2;
        int[] iArr = this.zzdwq;
        if (!(iArr == null || iArr.length == 0)) {
            int i3 = -1;
            int length = iArr.length;
            int i4 = 0;
            for (int i5 = 0; i5 < length; i5 = i + 1) {
                int i6 = iArr[i5];
                int zzcw = zzcw(i6);
                int zzct = zzct(zzcw);
                if (!this.zzdwo) {
                    int i7 = this.zzdwg[zzcw + 2];
                    int i8 = i7 & 1048575;
                    i2 = 1 << (i7 >>> 20);
                    if (i8 != i3) {
                        i = i5;
                        i4 = zzdwf.getInt(t, (long) i8);
                        i3 = i8;
                    } else {
                        i = i5;
                    }
                } else {
                    i = i5;
                    i2 = 0;
                }
                if (((268435456 & zzct) != 0) && !zza(t, zzcw, i4, i2)) {
                    return false;
                }
                int i9 = (267386880 & zzct) >>> 20;
                if (i9 != 9 && i9 != 17) {
                    if (i9 != 27) {
                        if (i9 == 60 || i9 == 68) {
                            if (zza(t, i6, zzcw) && !zza(t, zzct, zzcq(zzcw))) {
                                return false;
                            }
                        } else if (i9 != 49) {
                            if (i9 != 50) {
                                continue;
                            } else {
                                Map<?, ?> zzt = this.zzdwx.zzt(zzbek.zzp(t, (long) (zzct & 1048575)));
                                if (!zzt.isEmpty()) {
                                    if (this.zzdwx.zzx(zzcr(zzcw)).zzdwa.zzagl() == zzbex.MESSAGE) {
                                        zzbdm<T> zzbdm = 0;
                                        Iterator<?> it = zzt.values().iterator();
                                        while (true) {
                                            if (!it.hasNext()) {
                                                break;
                                            }
                                            Object next = it.next();
                                            if (zzbdm == null) {
                                                zzbdm = zzbdg.zzaeo().zze(next.getClass());
                                            }
                                            boolean zzaa = zzbdm.zzaa(next);
                                            zzbdm = zzbdm;
                                            if (!zzaa) {
                                                z2 = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                                z2 = true;
                                if (!z2) {
                                    return false;
                                }
                            }
                        }
                    }
                    List list = (List) zzbek.zzp(t, (long) (zzct & 1048575));
                    if (!list.isEmpty()) {
                        ?? zzcq = zzcq(zzcw);
                        int i10 = 0;
                        while (true) {
                            if (i10 >= list.size()) {
                                break;
                            } else if (!zzcq.zzaa(list.get(i10))) {
                                z = false;
                                break;
                            } else {
                                i10++;
                            }
                        }
                        if (z) {
                            return false;
                        }
                    }
                    z = true;
                    if (z) {
                    }
                } else if (zza(t, zzcw, i4, i2) && !zza(t, zzct, zzcq(zzcw))) {
                    return false;
                }
            }
            return !this.zzdwm || this.zzdww.zzm(t).isInitialized();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdm
    public final void zzc(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzdwg.length; i += 4) {
                int zzct = zzct(i);
                long j = (long) (1048575 & zzct);
                int i2 = this.zzdwg[i];
                switch ((zzct & 267386880) >>> 20) {
                    case 0:
                        if (zza(t2, i)) {
                            zzbek.zza(t, j, zzbek.zzo(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza(t2, i)) {
                            zzbek.zza((Object) t, j, zzbek.zzn(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                        zzb(t, i);
                        break;
                    case 3:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                        zzb(t, i);
                        break;
                    case 4:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zzb(t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    case 5:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                        zzb(t, i);
                        break;
                    case 6:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zzb(t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    case 7:
                        if (zza(t2, i)) {
                            zzbek.zza(t, j, zzbek.zzm(t2, j));
                            zzb(t, i);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zza(t, j, zzbek.zzp(t2, j));
                        zzb(t, i);
                        break;
                    case 9:
                    case 17:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zza(t, j, zzbek.zzp(t2, j));
                        zzb(t, i);
                        break;
                    case 11:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zzb(t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    case 12:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zzb(t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    case 13:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zzb(t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    case 14:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                        zzb(t, i);
                        break;
                    case 15:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zzb(t, j, zzbek.zzk(t2, j));
                        zzb(t, i);
                        break;
                    case 16:
                        if (!zza(t2, i)) {
                            break;
                        }
                        zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                        zzb(t, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzdwu.zza(t, t2, j);
                        break;
                    case 50:
                        zzbdo.zza(this.zzdwx, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zza(t2, i2, i)) {
                            break;
                        }
                        zzbek.zza(t, j, zzbek.zzp(t2, j));
                        zzb(t, i2, i);
                        break;
                    case 60:
                    case 68:
                        zzb(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zza(t2, i2, i)) {
                            break;
                        }
                        zzbek.zza(t, j, zzbek.zzp(t2, j));
                        zzb(t, i2, i);
                        break;
                }
            }
            if (!this.zzdwo) {
                zzbdo.zza(this.zzdwv, t, t2);
                if (this.zzdwm) {
                    zzbdo.zza(this.zzdww, t, t2);
                    return;
                }
                return;
            }
            return;
        }
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbdm
    public final void zzo(T t) {
        int[] iArr = this.zzdwr;
        if (iArr != null) {
            for (int i : iArr) {
                long zzct = (long) (zzct(i) & 1048575);
                Object zzp = zzbek.zzp(t, zzct);
                if (zzp != null) {
                    zzbek.zza(t, zzct, this.zzdwx.zzv(zzp));
                }
            }
        }
        int[] iArr2 = this.zzdws;
        if (iArr2 != null) {
            for (int i2 : iArr2) {
                this.zzdwu.zzb(t, (long) i2);
            }
        }
        this.zzdwv.zzo(t);
        if (this.zzdwm) {
            this.zzdww.zzo(t);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d8, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01e9, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01fa, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x020b, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x020d, code lost:
        r2.putInt(r20, (long) r14, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0211, code lost:
        r3 = (com.google.android.gms.internal.ads.zzbav.zzcd(r3) + com.google.android.gms.internal.ads.zzbav.zzcf(r5)) + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0331, code lost:
        if ((r5 instanceof com.google.android.gms.internal.ads.zzbah) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0334, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.zzg(r3, (java.lang.String) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0414, code lost:
        if (zza(r20, r15, r5) != false) goto L_0x06b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0434, code lost:
        if (zza(r20, r15, r5) != false) goto L_0x06e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x043c, code lost:
        if (zza(r20, r15, r5) != false) goto L_0x06ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x045c, code lost:
        if (zza(r20, r15, r5) != false) goto L_0x0713;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0464, code lost:
        if (zza(r20, r15, r5) != false) goto L_0x0722;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x0474, code lost:
        if ((r4 instanceof com.google.android.gms.internal.ads.zzbah) != false) goto L_0x0717;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x047c, code lost:
        if (zza(r20, r15, r5) != false) goto L_0x0749;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x0514, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x0526, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x0538, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x054a, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x055c, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x056e, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:296:0x0580, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x0592, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x05a3, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:308:0x05b4, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:312:0x05c5, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x05d6, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x05e7, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x05f8, code lost:
        if (r19.zzdwp != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x05fa, code lost:
        r2.putInt(r20, (long) r9, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x05fe, code lost:
        r9 = (com.google.android.gms.internal.ads.zzbav.zzcd(r15) + com.google.android.gms.internal.ads.zzbav.zzcf(r4)) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x06b4, code lost:
        if ((r12 & r18) != 0) goto L_0x06b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x06b6, code lost:
        r4 = com.google.android.gms.internal.ads.zzbav.zzc(r15, (com.google.android.gms.internal.ads.zzbcu) r2.getObject(r20, r10), zzcq(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x06e1, code lost:
        if ((r12 & r18) != 0) goto L_0x06e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x06e3, code lost:
        r4 = com.google.android.gms.internal.ads.zzbav.zzh(r15, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:0x06ec, code lost:
        if ((r12 & r18) != 0) goto L_0x06ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x06ee, code lost:
        r9 = com.google.android.gms.internal.ads.zzbav.zzu(r15, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x0711, code lost:
        if ((r12 & r18) != 0) goto L_0x0713;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x0713, code lost:
        r4 = r2.getObject(r20, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x0717, code lost:
        r4 = com.google.android.gms.internal.ads.zzbav.zzc(r15, (com.google.android.gms.internal.ads.zzbah) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x0720, code lost:
        if ((r12 & r18) != 0) goto L_0x0722;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x0722, code lost:
        r4 = com.google.android.gms.internal.ads.zzbdo.zzc(r15, r2.getObject(r20, r10), zzcq(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x073a, code lost:
        if ((r4 instanceof com.google.android.gms.internal.ads.zzbah) != false) goto L_0x0717;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ab, code lost:
        if ((r5 instanceof com.google.android.gms.internal.ads.zzbah) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x073d, code lost:
        r4 = com.google.android.gms.internal.ads.zzbav.zzg(r15, (java.lang.String) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:0x0747, code lost:
        if ((r12 & r18) != 0) goto L_0x0749;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x0749, code lost:
        r4 = com.google.android.gms.internal.ads.zzbav.zzg(r15, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0127, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0139, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x014b, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x015d, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x016f, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0181, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0193, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01a5, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01b6, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01c7, code lost:
        if (r19.zzdwp != false) goto L_0x020d;
     */
    @Override // com.google.android.gms.internal.ads.zzbdm
    public final int zzy(T t) {
        int i;
        int i2;
        long j;
        int i3;
        int zzg;
        Object obj;
        int i4;
        int i5;
        int i6;
        int i7;
        long j2;
        int i8;
        int zzb;
        long j3;
        long j4;
        int i9;
        Object obj2;
        int i10;
        int i11;
        int i12;
        long j5;
        int i13;
        int i14 = 267386880;
        if (this.zzdwo) {
            Unsafe unsafe = zzdwf;
            int i15 = 0;
            int i16 = 0;
            while (i15 < this.zzdwg.length) {
                int zzct = zzct(i15);
                int i17 = (zzct & i14) >>> 20;
                int i18 = this.zzdwg[i15];
                long j6 = (long) (zzct & 1048575);
                int i19 = (i17 < zzbbj.DOUBLE_LIST_PACKED.id() || i17 > zzbbj.SINT64_LIST_PACKED.id()) ? 0 : this.zzdwg[i15 + 2] & 1048575;
                switch (i17) {
                    case 0:
                        if (!zza(t, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzb(i18, 0.0d);
                        break;
                    case 1:
                        if (!zza(t, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzb(i18, 0.0f);
                        break;
                    case 2:
                        if (zza(t, i15)) {
                            j3 = zzbek.zzl(t, j6);
                            zzb = zzbav.zzd(i18, j3);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 3:
                        if (zza(t, i15)) {
                            j4 = zzbek.zzl(t, j6);
                            zzb = zzbav.zze(i18, j4);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 4:
                        if (zza(t, i15)) {
                            i9 = zzbek.zzk(t, j6);
                            zzb = zzbav.zzq(i18, i9);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 5:
                        if (!zza(t, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzg(i18, 0);
                        break;
                    case 6:
                        if (!zza(t, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzt(i18, 0);
                        break;
                    case 7:
                        if (!zza(t, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzg(i18, true);
                        break;
                    case 8:
                        if (zza(t, i15)) {
                            obj2 = zzbek.zzp(t, j6);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 9:
                        if (!zza(t, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbdo.zzc(i18, zzbek.zzp(t, j6), zzcq(i15));
                        break;
                    case 10:
                        if (!zza(t, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        obj2 = zzbek.zzp(t, j6);
                        zzb = zzbav.zzc(i18, (zzbah) obj2);
                        break;
                    case 11:
                        if (zza(t, i15)) {
                            i10 = zzbek.zzk(t, j6);
                            zzb = zzbav.zzr(i18, i10);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 12:
                        if (zza(t, i15)) {
                            i11 = zzbek.zzk(t, j6);
                            zzb = zzbav.zzv(i18, i11);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 13:
                        if (!zza(t, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzu(i18, 0);
                        break;
                    case 14:
                        if (!zza(t, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzh(i18, 0);
                        break;
                    case 15:
                        if (zza(t, i15)) {
                            i12 = zzbek.zzk(t, j6);
                            zzb = zzbav.zzs(i18, i12);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 16:
                        if (zza(t, i15)) {
                            j5 = zzbek.zzl(t, j6);
                            zzb = zzbav.zzf(i18, j5);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 17:
                        if (!zza(t, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzc(i18, (zzbcu) zzbek.zzp(t, j6), zzcq(i15));
                        break;
                    case 18:
                    case 23:
                    case 32:
                        zzb = zzbdo.zzw(i18, zze(t, j6), false);
                        break;
                    case 19:
                    case 24:
                    case 31:
                        zzb = zzbdo.zzv(i18, zze(t, j6), false);
                        break;
                    case 20:
                        zzb = zzbdo.zzo(i18, zze(t, j6), false);
                        break;
                    case 21:
                        zzb = zzbdo.zzp(i18, zze(t, j6), false);
                        break;
                    case 22:
                        zzb = zzbdo.zzs(i18, zze(t, j6), false);
                        break;
                    case 25:
                        zzb = zzbdo.zzx(i18, zze(t, j6), false);
                        break;
                    case 26:
                        zzb = zzbdo.zzc(i18, zze(t, j6));
                        break;
                    case 27:
                        zzb = zzbdo.zzc(i18, (List<?>) zze(t, j6), zzcq(i15));
                        break;
                    case 28:
                        zzb = zzbdo.zzd(i18, zze(t, j6));
                        break;
                    case 29:
                        zzb = zzbdo.zzt(i18, zze(t, j6), false);
                        break;
                    case 30:
                        zzb = zzbdo.zzr(i18, zze(t, j6), false);
                        break;
                    case 33:
                        zzb = zzbdo.zzu(i18, zze(t, j6), false);
                        break;
                    case 34:
                        zzb = zzbdo.zzq(i18, zze(t, j6), false);
                        break;
                    case 35:
                        i13 = zzbdo.zzan((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 36:
                        i13 = zzbdo.zzam((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 37:
                        i13 = zzbdo.zzaf((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 38:
                        i13 = zzbdo.zzag((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 39:
                        i13 = zzbdo.zzaj((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 40:
                        i13 = zzbdo.zzan((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 41:
                        i13 = zzbdo.zzam((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 42:
                        i13 = zzbdo.zzao((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 43:
                        i13 = zzbdo.zzak((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 44:
                        i13 = zzbdo.zzai((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 45:
                        i13 = zzbdo.zzam((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 46:
                        i13 = zzbdo.zzan((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 47:
                        i13 = zzbdo.zzal((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 48:
                        i13 = zzbdo.zzah((List) unsafe.getObject(t, j6));
                        if (i13 > 0) {
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 49:
                        zzb = zzbdo.zzd(i18, zze(t, j6), zzcq(i15));
                        break;
                    case 50:
                        zzb = this.zzdwx.zzb(i18, zzbek.zzp(t, j6), zzcr(i15));
                        break;
                    case 51:
                        if (!zza(t, i18, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzb(i18, 0.0d);
                        break;
                    case 52:
                        if (!zza(t, i18, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzb(i18, 0.0f);
                        break;
                    case 53:
                        if (zza(t, i18, i15)) {
                            j3 = zzi(t, j6);
                            zzb = zzbav.zzd(i18, j3);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 54:
                        if (zza(t, i18, i15)) {
                            j4 = zzi(t, j6);
                            zzb = zzbav.zze(i18, j4);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 55:
                        if (zza(t, i18, i15)) {
                            i9 = zzh(t, j6);
                            zzb = zzbav.zzq(i18, i9);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 56:
                        if (!zza(t, i18, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzg(i18, 0);
                        break;
                    case 57:
                        if (!zza(t, i18, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzt(i18, 0);
                        break;
                    case 58:
                        if (!zza(t, i18, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzg(i18, true);
                        break;
                    case 59:
                        if (zza(t, i18, i15)) {
                            obj2 = zzbek.zzp(t, j6);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 60:
                        if (!zza(t, i18, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbdo.zzc(i18, zzbek.zzp(t, j6), zzcq(i15));
                        break;
                    case 61:
                        if (!zza(t, i18, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        obj2 = zzbek.zzp(t, j6);
                        zzb = zzbav.zzc(i18, (zzbah) obj2);
                        break;
                    case 62:
                        if (zza(t, i18, i15)) {
                            i10 = zzh(t, j6);
                            zzb = zzbav.zzr(i18, i10);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 63:
                        if (zza(t, i18, i15)) {
                            i11 = zzh(t, j6);
                            zzb = zzbav.zzv(i18, i11);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 64:
                        if (!zza(t, i18, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzu(i18, 0);
                        break;
                    case 65:
                        if (!zza(t, i18, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzh(i18, 0);
                        break;
                    case 66:
                        if (zza(t, i18, i15)) {
                            i12 = zzh(t, j6);
                            zzb = zzbav.zzs(i18, i12);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 67:
                        if (zza(t, i18, i15)) {
                            j5 = zzi(t, j6);
                            zzb = zzbav.zzf(i18, j5);
                            break;
                        } else {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                    case 68:
                        if (!zza(t, i18, i15)) {
                            continue;
                            i15 += 4;
                            i14 = 267386880;
                        }
                        zzb = zzbav.zzc(i18, (zzbcu) zzbek.zzp(t, j6), zzcq(i15));
                        break;
                    default:
                        i15 += 4;
                        i14 = 267386880;
                }
                i16 += zzb;
                i15 += 4;
                i14 = 267386880;
            }
            return i16 + zza((zzbee) this.zzdwv, (Object) t);
        }
        Unsafe unsafe2 = zzdwf;
        int i20 = -1;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        while (i21 < this.zzdwg.length) {
            int zzct2 = zzct(i21);
            int[] iArr = this.zzdwg;
            int i24 = iArr[i21];
            int i25 = (zzct2 & 267386880) >>> 20;
            if (i25 <= 17) {
                int i26 = iArr[i21 + 2];
                int i27 = i26 & 1048575;
                i = 1 << (i26 >>> 20);
                if (i27 != i20) {
                    i23 = unsafe2.getInt(t, (long) i27);
                    i20 = i27;
                }
                i2 = i26;
            } else {
                i2 = (!this.zzdwp || i25 < zzbbj.DOUBLE_LIST_PACKED.id() || i25 > zzbbj.SINT64_LIST_PACKED.id()) ? 0 : this.zzdwg[i21 + 2] & 1048575;
                i = 0;
            }
            long j7 = (long) (zzct2 & 1048575);
            switch (i25) {
                case 0:
                    j = 0;
                    if ((i23 & i) != 0) {
                        i22 += zzbav.zzb(i24, 0.0d);
                        break;
                    }
                    break;
                case 1:
                    j = 0;
                    if ((i23 & i) != 0) {
                        i22 += zzbav.zzb(i24, 0.0f);
                        break;
                    }
                case 2:
                    j = 0;
                    if ((i23 & i) != 0) {
                        i3 = zzbav.zzd(i24, unsafe2.getLong(t, j7));
                        i22 += i3;
                    }
                    break;
                case 3:
                    j = 0;
                    if ((i23 & i) != 0) {
                        i3 = zzbav.zze(i24, unsafe2.getLong(t, j7));
                        i22 += i3;
                    }
                    break;
                case 4:
                    j = 0;
                    if ((i23 & i) != 0) {
                        i3 = zzbav.zzq(i24, unsafe2.getInt(t, j7));
                        i22 += i3;
                    }
                    break;
                case 5:
                    j = 0;
                    if ((i23 & i) != 0) {
                        i3 = zzbav.zzg(i24, 0);
                        i22 += i3;
                    }
                    break;
                case 6:
                    if ((i23 & i) != 0) {
                        i22 += zzbav.zzt(i24, 0);
                        j = 0;
                        break;
                    }
                    j = 0;
                case 7:
                    break;
                case 8:
                    if ((i23 & i) != 0) {
                        obj = unsafe2.getObject(t, j7);
                        break;
                    }
                    j = 0;
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    if ((i23 & i) != 0) {
                        i4 = unsafe2.getInt(t, j7);
                        zzg = zzbav.zzr(i24, i4);
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 12:
                    if ((i23 & i) != 0) {
                        i5 = unsafe2.getInt(t, j7);
                        zzg = zzbav.zzv(i24, i5);
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    if ((i23 & i) != 0) {
                        i7 = unsafe2.getInt(t, j7);
                        zzg = zzbav.zzs(i24, i7);
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 16:
                    if ((i23 & i) != 0) {
                        j2 = unsafe2.getLong(t, j7);
                        zzg = zzbav.zzf(i24, j2);
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 17:
                    break;
                case 18:
                case 23:
                case 32:
                    zzg = zzbdo.zzw(i24, (List) unsafe2.getObject(t, j7), false);
                    i22 += zzg;
                    j = 0;
                    break;
                case 19:
                case 24:
                case 31:
                    zzg = zzbdo.zzv(i24, (List) unsafe2.getObject(t, j7), false);
                    i22 += zzg;
                    j = 0;
                    break;
                case 20:
                    zzg = zzbdo.zzo(i24, (List) unsafe2.getObject(t, j7), false);
                    i22 += zzg;
                    j = 0;
                    break;
                case 21:
                    zzg = zzbdo.zzp(i24, (List) unsafe2.getObject(t, j7), false);
                    i22 += zzg;
                    j = 0;
                    break;
                case 22:
                    zzg = zzbdo.zzs(i24, (List) unsafe2.getObject(t, j7), false);
                    i22 += zzg;
                    j = 0;
                    break;
                case 25:
                    zzg = zzbdo.zzx(i24, (List) unsafe2.getObject(t, j7), false);
                    i22 += zzg;
                    j = 0;
                    break;
                case 26:
                    zzg = zzbdo.zzc(i24, (List) unsafe2.getObject(t, j7));
                    i22 += zzg;
                    j = 0;
                    break;
                case 27:
                    zzg = zzbdo.zzc(i24, (List<?>) ((List) unsafe2.getObject(t, j7)), zzcq(i21));
                    i22 += zzg;
                    j = 0;
                    break;
                case 28:
                    zzg = zzbdo.zzd(i24, (List) unsafe2.getObject(t, j7));
                    i22 += zzg;
                    j = 0;
                    break;
                case 29:
                    zzg = zzbdo.zzt(i24, (List) unsafe2.getObject(t, j7), false);
                    i22 += zzg;
                    j = 0;
                    break;
                case 30:
                    zzg = zzbdo.zzr(i24, (List) unsafe2.getObject(t, j7), false);
                    i22 += zzg;
                    j = 0;
                    break;
                case 33:
                    zzg = zzbdo.zzu(i24, (List) unsafe2.getObject(t, j7), false);
                    i22 += zzg;
                    j = 0;
                    break;
                case 34:
                    zzg = zzbdo.zzq(i24, (List) unsafe2.getObject(t, j7), false);
                    i22 += zzg;
                    j = 0;
                    break;
                case 35:
                    i8 = zzbdo.zzan((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 36:
                    i8 = zzbdo.zzam((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 37:
                    i8 = zzbdo.zzaf((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 38:
                    i8 = zzbdo.zzag((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 39:
                    i8 = zzbdo.zzaj((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 40:
                    i8 = zzbdo.zzan((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 41:
                    i8 = zzbdo.zzam((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 42:
                    i8 = zzbdo.zzao((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 43:
                    i8 = zzbdo.zzak((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 44:
                    i8 = zzbdo.zzai((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 45:
                    i8 = zzbdo.zzam((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 46:
                    i8 = zzbdo.zzan((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 47:
                    i8 = zzbdo.zzal((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 48:
                    i8 = zzbdo.zzah((List) unsafe2.getObject(t, j7));
                    if (i8 > 0) {
                        break;
                    }
                    j = 0;
                    break;
                case 49:
                    zzg = zzbdo.zzd(i24, (List) unsafe2.getObject(t, j7), zzcq(i21));
                    i22 += zzg;
                    j = 0;
                    break;
                case 50:
                    zzg = this.zzdwx.zzb(i24, unsafe2.getObject(t, j7), zzcr(i21));
                    i22 += zzg;
                    j = 0;
                    break;
                case 51:
                    if (zza(t, i24, i21)) {
                        zzg = zzbav.zzb(i24, 0.0d);
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 52:
                    if (zza(t, i24, i21)) {
                        i6 = zzbav.zzb(i24, 0.0f);
                        i22 += i6;
                    }
                    j = 0;
                    break;
                case 53:
                    if (zza(t, i24, i21)) {
                        zzg = zzbav.zzd(i24, zzi(t, j7));
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 54:
                    if (zza(t, i24, i21)) {
                        zzg = zzbav.zze(i24, zzi(t, j7));
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 55:
                    if (zza(t, i24, i21)) {
                        zzg = zzbav.zzq(i24, zzh(t, j7));
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 56:
                    if (zza(t, i24, i21)) {
                        zzg = zzbav.zzg(i24, 0);
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 57:
                    if (zza(t, i24, i21)) {
                        i6 = zzbav.zzt(i24, 0);
                        i22 += i6;
                    }
                    j = 0;
                    break;
                case 58:
                    break;
                case 59:
                    if (zza(t, i24, i21)) {
                        obj = unsafe2.getObject(t, j7);
                        break;
                    }
                    j = 0;
                    break;
                case 60:
                    break;
                case 61:
                    break;
                case 62:
                    if (zza(t, i24, i21)) {
                        i4 = zzh(t, j7);
                        zzg = zzbav.zzr(i24, i4);
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 63:
                    if (zza(t, i24, i21)) {
                        i5 = zzh(t, j7);
                        zzg = zzbav.zzv(i24, i5);
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 64:
                    break;
                case 65:
                    break;
                case 66:
                    if (zza(t, i24, i21)) {
                        i7 = zzh(t, j7);
                        zzg = zzbav.zzs(i24, i7);
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 67:
                    if (zza(t, i24, i21)) {
                        j2 = zzi(t, j7);
                        zzg = zzbav.zzf(i24, j2);
                        i22 += zzg;
                    }
                    j = 0;
                    break;
                case 68:
                    break;
                default:
                    j = 0;
                    break;
            }
            i21 += 4;
        }
        int zza = i22 + zza((zzbee) this.zzdwv, (Object) t);
        return this.zzdwm ? zza + this.zzdww.zzm(t).zzacw() : zza;
    }
}
