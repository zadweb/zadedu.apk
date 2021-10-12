package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzbat implements zzbdl {
    private int tag;
    private final zzbaq zzdqi;
    private int zzdqj;
    private int zzdqk = 0;

    private zzbat(zzbaq zzbaq) {
        zzbaq zzbaq2 = (zzbaq) zzbbq.zza((Object) zzbaq, "input");
        this.zzdqi = zzbaq2;
        zzbaq2.zzdqa = this;
    }

    public static zzbat zza(zzbaq zzbaq) {
        return zzbaq.zzdqa != null ? zzbaq.zzdqa : new zzbat(zzbaq);
    }

    private final Object zza(zzbes zzbes, Class<?> cls, zzbbb zzbbb) throws IOException {
        switch (zzbau.zzdql[zzbes.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzabq());
            case 2:
                return zzabs();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzabu());
            case 5:
                return Integer.valueOf(zzabp());
            case 6:
                return Long.valueOf(zzabo());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzabn());
            case 9:
                return Long.valueOf(zzabm());
            case 10:
                zzbv(2);
                return zzc(zzbdg.zzaeo().zze(cls), zzbbb);
            case 11:
                return Integer.valueOf(zzabv());
            case 12:
                return Long.valueOf(zzabw());
            case 13:
                return Integer.valueOf(zzabx());
            case 14:
                return Long.valueOf(zzaby());
            case 15:
                return zzabr();
            case 16:
                return Integer.valueOf(zzabt());
            case 17:
                return Long.valueOf(zzabl());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zzabk;
        int zzabk2;
        if ((this.tag & 7) != 2) {
            throw zzbbu.zzadq();
        } else if (!(list instanceof zzbcd) || z) {
            do {
                list.add(z ? zzabr() : readString());
                if (!this.zzdqi.zzaca()) {
                    zzabk = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk == this.tag);
            this.zzdqk = zzabk;
        } else {
            zzbcd zzbcd = (zzbcd) list;
            do {
                zzbcd.zzap(zzabs());
                if (!this.zzdqi.zzaca()) {
                    zzabk2 = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk2 == this.tag);
            this.zzdqk = zzabk2;
        }
    }

    private final void zzbv(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzbbu.zzadq();
        }
    }

    private static void zzbw(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzbbu.zzadr();
        }
    }

    private static void zzbx(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzbbu.zzadr();
        }
    }

    private final void zzby(int i) throws IOException {
        if (this.zzdqi.zzacb() != i) {
            throw zzbbu.zzadl();
        }
    }

    private final <T> T zzc(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int zzabt = this.zzdqi.zzabt();
        if (this.zzdqi.zzdpx < this.zzdqi.zzdpy) {
            int zzbr = this.zzdqi.zzbr(zzabt);
            T newInstance = zzbdm.newInstance();
            this.zzdqi.zzdpx++;
            zzbdm.zza(newInstance, this, zzbbb);
            zzbdm.zzo(newInstance);
            this.zzdqi.zzbp(0);
            zzbaq zzbaq = this.zzdqi;
            zzbaq.zzdpx--;
            this.zzdqi.zzbs(zzbr);
            return newInstance;
        }
        throw new zzbbu("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final <T> T zzd(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int i = this.zzdqj;
        this.zzdqj = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzbdm.newInstance();
            zzbdm.zza(newInstance, this, zzbbb);
            zzbdm.zzo(newInstance);
            if (this.tag == this.zzdqj) {
                return newInstance;
            }
            throw zzbbu.zzadr();
        } finally {
            this.zzdqj = i;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final int getTag() {
        return this.tag;
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final double readDouble() throws IOException {
        zzbv(1);
        return this.zzdqi.readDouble();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final float readFloat() throws IOException {
        zzbv(5);
        return this.zzdqi.readFloat();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final String readString() throws IOException {
        zzbv(2);
        return this.zzdqi.readString();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final <T> T zza(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        zzbv(2);
        return (T) zzc(zzbdm, zzbbb);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.List<T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzbdl
    public final <T> void zza(List<T> list, zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int zzabk;
        int i = this.tag;
        if ((i & 7) == 2) {
            do {
                list.add(zzc(zzbdm, zzbbb));
                if (!this.zzdqi.zzaca() && this.zzdqk == 0) {
                    zzabk = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk == i);
            this.zzdqk = zzabk;
            return;
        }
        throw zzbbu.zzadq();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: java.util.Map<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzbdl
    public final <K, V> void zza(Map<K, V> map, zzbcn<K, V> zzbcn, zzbbb zzbbb) throws IOException {
        zzbv(2);
        int zzbr = this.zzdqi.zzbr(this.zzdqi.zzabt());
        Object obj = zzbcn.zzdvz;
        Object obj2 = zzbcn.zzdwb;
        while (true) {
            try {
                int zzaci = zzaci();
                if (zzaci == Integer.MAX_VALUE || this.zzdqi.zzaca()) {
                    map.put(obj, obj2);
                } else if (zzaci == 1) {
                    obj = zza(zzbcn.zzdvy, (Class<?>) null, (zzbbb) null);
                } else if (zzaci != 2) {
                    try {
                        if (!zzacj()) {
                            throw new zzbbu("Unable to parse map entry.");
                        }
                    } catch (zzbbv unused) {
                        if (!zzacj()) {
                            throw new zzbbu("Unable to parse map entry.");
                        }
                    }
                } else {
                    obj2 = zza(zzbcn.zzdwa, zzbcn.zzdwb.getClass(), zzbbb);
                }
            } finally {
                this.zzdqi.zzbs(zzbr);
            }
        }
        map.put(obj, obj2);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzaa(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabu());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbbp.zzco(this.zzdqi.zzabu());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabu()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabu()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzab(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbx(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbbp.zzco(this.zzdqi.zzabv());
                } while (this.zzdqi.zzacb() < zzacb);
            } else if (i == 5) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabv());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbx(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabv()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabv()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final long zzabl() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabl();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final long zzabm() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabm();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final int zzabn() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabn();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final long zzabo() throws IOException {
        zzbv(1);
        return this.zzdqi.zzabo();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final int zzabp() throws IOException {
        zzbv(5);
        return this.zzdqi.zzabp();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final boolean zzabq() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabq();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final String zzabr() throws IOException {
        zzbv(2);
        return this.zzdqi.zzabr();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final zzbah zzabs() throws IOException {
        zzbv(2);
        return this.zzdqi.zzabs();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final int zzabt() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabt();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final int zzabu() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabu();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final int zzabv() throws IOException {
        zzbv(5);
        return this.zzdqi.zzabv();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final long zzabw() throws IOException {
        zzbv(1);
        return this.zzdqi.zzabw();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final int zzabx() throws IOException {
        zzbv(0);
        return this.zzdqi.zzabx();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final long zzaby() throws IOException {
        zzbv(0);
        return this.zzdqi.zzaby();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzac(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzbci.zzw(this.zzdqi.zzabw());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbw(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbci.zzw(this.zzdqi.zzabw());
                } while (this.zzdqi.zzacb() < zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabw()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbw(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabw()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final int zzaci() throws IOException {
        int i = this.zzdqk;
        if (i != 0) {
            this.tag = i;
            this.zzdqk = 0;
        } else {
            this.tag = this.zzdqi.zzabk();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzdqj) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final boolean zzacj() throws IOException {
        int i;
        if (this.zzdqi.zzaca() || (i = this.tag) == this.zzdqj) {
            return false;
        }
        return this.zzdqi.zzbq(i);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzad(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabx());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbbp.zzco(this.zzdqi.zzabx());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabx()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabx()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzae(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbci.zzw(this.zzdqi.zzaby());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbci.zzw(this.zzdqi.zzaby());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzdqi.zzaby()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzaby()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final <T> T zzb(zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        zzbv(3);
        return (T) zzd(zzbdm, zzbbb);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.List<T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzbdl
    public final <T> void zzb(List<T> list, zzbdm<T> zzbdm, zzbbb zzbbb) throws IOException {
        int zzabk;
        int i = this.tag;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzbdm, zzbbb));
                if (!this.zzdqi.zzaca() && this.zzdqk == 0) {
                    zzabk = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk == i);
            this.zzdqk = zzabk;
            return;
        }
        throw zzbbu.zzadq();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzp(List<Double> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbay) {
            zzbay zzbay = (zzbay) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzbay.zzd(this.zzdqi.readDouble());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbw(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbay.zzd(this.zzdqi.readDouble());
                } while (this.zzdqi.zzacb() < zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Double.valueOf(this.zzdqi.readDouble()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbw(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Double.valueOf(this.zzdqi.readDouble()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzq(List<Float> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbm) {
            zzbbm zzbbm = (zzbbm) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbx(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbbm.zzd(this.zzdqi.readFloat());
                } while (this.zzdqi.zzacb() < zzacb);
            } else if (i == 5) {
                do {
                    zzbbm.zzd(this.zzdqi.readFloat());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbx(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Float.valueOf(this.zzdqi.readFloat()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zzdqi.readFloat()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzr(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbci.zzw(this.zzdqi.zzabl());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbci.zzw(this.zzdqi.zzabl());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabl()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabl()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzs(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbci.zzw(this.zzdqi.zzabm());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbci.zzw(this.zzdqi.zzabm());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabm()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabm()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzt(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabn());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbbp.zzco(this.zzdqi.zzabn());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabn()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabn()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzu(List<Long> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzbci.zzw(this.zzdqi.zzabo());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbw(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbci.zzw(this.zzdqi.zzabo());
                } while (this.zzdqi.zzacb() < zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabo()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbw(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Long.valueOf(this.zzdqi.zzabo()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzv(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzabt = this.zzdqi.zzabt();
                zzbx(zzabt);
                int zzacb = this.zzdqi.zzacb() + zzabt;
                do {
                    zzbbp.zzco(this.zzdqi.zzabp());
                } while (this.zzdqi.zzacb() < zzacb);
            } else if (i == 5) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabp());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzabt2 = this.zzdqi.zzabt();
                zzbx(zzabt2);
                int zzacb2 = this.zzdqi.zzacb() + zzabt2;
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabp()));
                } while (this.zzdqi.zzacb() < zzacb2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabp()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzw(List<Boolean> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbaf) {
            zzbaf zzbaf = (zzbaf) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbaf.addBoolean(this.zzdqi.zzabq());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbaf.addBoolean(this.zzdqi.zzabq());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzdqi.zzabq()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Boolean.valueOf(this.zzdqi.zzabq()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzx(List<String> list) throws IOException {
        zza(list, true);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzy(List<zzbah> list) throws IOException {
        int zzabk;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzabs());
                if (!this.zzdqi.zzaca()) {
                    zzabk = this.zzdqi.zzabk();
                } else {
                    return;
                }
            } while (zzabk == this.tag);
            this.zzdqk = zzabk;
            return;
        }
        throw zzbbu.zzadq();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzz(List<Integer> list) throws IOException {
        int zzabk;
        int zzabk2;
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbbp.zzco(this.zzdqi.zzabt());
                    if (!this.zzdqi.zzaca()) {
                        zzabk2 = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk2 == this.tag);
                this.zzdqk = zzabk2;
            } else if (i == 2) {
                int zzacb = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    zzbbp.zzco(this.zzdqi.zzabt());
                } while (this.zzdqi.zzacb() < zzacb);
                zzby(zzacb);
            } else {
                throw zzbbu.zzadq();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabt()));
                    if (!this.zzdqi.zzaca()) {
                        zzabk = this.zzdqi.zzabk();
                    } else {
                        return;
                    }
                } while (zzabk == this.tag);
                this.zzdqk = zzabk;
            } else if (i2 == 2) {
                int zzacb2 = this.zzdqi.zzacb() + this.zzdqi.zzabt();
                do {
                    list.add(Integer.valueOf(this.zzdqi.zzabt()));
                } while (this.zzdqi.zzacb() < zzacb2);
                zzby(zzacb2);
            } else {
                throw zzbbu.zzadq();
            }
        }
    }
}
