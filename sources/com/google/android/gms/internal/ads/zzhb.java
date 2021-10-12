package com.google.android.gms.internal.ads;

import java.util.Comparator;

public final class zzhb implements Comparator<zzgp> {
    public zzhb(zzha zzha) {
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzgp zzgp, zzgp zzgp2) {
        zzgp zzgp3 = zzgp;
        zzgp zzgp4 = zzgp2;
        if (zzgp3.zzhc() < zzgp4.zzhc()) {
            return -1;
        }
        if (zzgp3.zzhc() > zzgp4.zzhc()) {
            return 1;
        }
        if (zzgp3.zzhb() < zzgp4.zzhb()) {
            return -1;
        }
        if (zzgp3.zzhb() > zzgp4.zzhb()) {
            return 1;
        }
        float zzhe = (zzgp3.zzhe() - zzgp3.zzhc()) * (zzgp3.zzhd() - zzgp3.zzhb());
        float zzhe2 = (zzgp4.zzhe() - zzgp4.zzhc()) * (zzgp4.zzhd() - zzgp4.zzhb());
        if (zzhe > zzhe2) {
            return -1;
        }
        return zzhe < zzhe2 ? 1 : 0;
    }
}
