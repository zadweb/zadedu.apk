package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

public final class zzdo extends zzei {
    private static final Object zztn = new Object();
    private static volatile zzbj zzto;
    private zzax zztp = null;

    public zzdo(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2, zzax zzax) {
        super(zzcz, str, str2, zzba, i, 27);
        this.zztp = zzax;
    }

    private final String zzas() {
        try {
            if (this.zzps.zzak() != null) {
                this.zzps.zzak().get();
            }
            zzba zzaj = this.zzps.zzaj();
            if (zzaj == null || zzaj.zzcx == null) {
                return null;
            }
            return zzaj.zzcx;
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007b  */
    @Override // com.google.android.gms.internal.ads.zzei
    public final void zzar() throws IllegalAccessException, InvocationTargetException {
        char c;
        boolean z;
        boolean z2 = false;
        if (zzto == null || zzdg.zzo(zzto.zzcx) || zzto.zzcx.equals("E") || zzto.zzcx.equals("0000000000000000000000000000000000000000000000000000000000000000")) {
            synchronized (zztn) {
                zzay zzay = null;
                if (!zzdg.zzo(null)) {
                    c = 4;
                } else {
                    zzdg.zzo(null);
                    Boolean bool = false;
                    if (bool.booleanValue()) {
                        if (this.zzps.zzah()) {
                            if (((Boolean) zzkb.zzik().zzd(zznk.zzbbb)).booleanValue()) {
                                if (((Boolean) zzkb.zzik().zzd(zznk.zzbbc)).booleanValue()) {
                                    z = true;
                                    if (z) {
                                        c = 3;
                                    }
                                }
                            }
                        }
                        z = false;
                        if (z) {
                        }
                    }
                    c = 2;
                }
                Method method = this.zztz;
                Object[] objArr = new Object[3];
                objArr[0] = this.zzps.getContext();
                if (c == 2) {
                    z2 = true;
                }
                objArr[1] = Boolean.valueOf(z2);
                objArr[2] = zzkb.zzik().zzd(zznk.zzbav);
                zzbj zzbj = new zzbj((String) method.invoke(null, objArr));
                zzto = zzbj;
                if (zzdg.zzo(zzbj.zzcx) || zzto.zzcx.equals("E")) {
                    if (c == 3) {
                        String zzas = zzas();
                        if (!zzdg.zzo(zzas)) {
                            zzto.zzcx = zzas;
                        }
                    } else if (c == 4) {
                        zzto.zzcx = zzay.zzcx;
                    }
                }
            }
        }
        synchronized (this.zztq) {
            if (zzto != null) {
                this.zztq.zzcx = zzto.zzcx;
                this.zztq.zzeb = Long.valueOf(zzto.zzhx);
                this.zztq.zzcz = zzto.zzcz;
                this.zztq.zzda = zzto.zzda;
                this.zztq.zzdb = zzto.zzdb;
            }
        }
    }
}
