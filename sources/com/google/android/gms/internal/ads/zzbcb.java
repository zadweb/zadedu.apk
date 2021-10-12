package com.google.android.gms.internal.ads;

public class zzbcb {
    private static final zzbbb zzdph = zzbbb.zzacr();
    private zzbah zzdvk;
    private volatile zzbcu zzdvl;
    private volatile zzbah zzdvm;

    private final zzbcu zzk(zzbcu zzbcu) {
        if (this.zzdvl == null) {
            synchronized (this) {
                if (this.zzdvl == null) {
                    try {
                        this.zzdvl = zzbcu;
                        this.zzdvm = zzbah.zzdpq;
                    } catch (zzbbu unused) {
                        this.zzdvl = zzbcu;
                        this.zzdvm = zzbah.zzdpq;
                    }
                }
            }
        }
        return this.zzdvl;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbcb)) {
            return false;
        }
        zzbcb zzbcb = (zzbcb) obj;
        zzbcu zzbcu = this.zzdvl;
        zzbcu zzbcu2 = zzbcb.zzdvl;
        return (zzbcu == null && zzbcu2 == null) ? zzaav().equals(zzbcb.zzaav()) : (zzbcu == null || zzbcu2 == null) ? zzbcu != null ? zzbcu.equals(zzbcb.zzk(zzbcu.zzadg())) : zzk(zzbcu2.zzadg()).equals(zzbcu2) : zzbcu.equals(zzbcu2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzbah zzaav() {
        if (this.zzdvm != null) {
            return this.zzdvm;
        }
        synchronized (this) {
            if (this.zzdvm != null) {
                return this.zzdvm;
            }
            this.zzdvm = this.zzdvl == null ? zzbah.zzdpq : this.zzdvl.zzaav();
            return this.zzdvm;
        }
    }

    public final int zzacw() {
        if (this.zzdvm != null) {
            return this.zzdvm.size();
        }
        if (this.zzdvl != null) {
            return this.zzdvl.zzacw();
        }
        return 0;
    }

    public final zzbcu zzl(zzbcu zzbcu) {
        zzbcu zzbcu2 = this.zzdvl;
        this.zzdvk = null;
        this.zzdvm = null;
        this.zzdvl = zzbcu;
        return zzbcu2;
    }
}
