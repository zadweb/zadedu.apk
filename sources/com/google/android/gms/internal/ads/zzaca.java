package com.google.android.gms.internal.ads;

import java.util.List;

final class zzaca implements zzank<List<zzon>, zzoj> {
    private final /* synthetic */ String zzcan;
    private final /* synthetic */ Integer zzcao;
    private final /* synthetic */ Integer zzcap;
    private final /* synthetic */ int zzcaq;
    private final /* synthetic */ int zzcar;
    private final /* synthetic */ int zzcas;
    private final /* synthetic */ int zzcat;
    private final /* synthetic */ boolean zzcau;

    zzaca(zzabv zzabv, String str, Integer num, Integer num2, int i, int i2, int i3, int i4, boolean z) {
        this.zzcan = str;
        this.zzcao = num;
        this.zzcap = num2;
        this.zzcaq = i;
        this.zzcar = i2;
        this.zzcas = i3;
        this.zzcat = i4;
        this.zzcau = z;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.internal.ads.zzank
    public final /* synthetic */ zzoj apply(List<zzon> list) {
        List<zzon> list2 = list;
        Integer num = null;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        String str = this.zzcan;
        Integer num2 = this.zzcao;
        Integer num3 = this.zzcap;
        int i = this.zzcaq;
        if (i > 0) {
            num = Integer.valueOf(i);
        }
        return new zzoj(str, list2, num2, num3, num, this.zzcar + this.zzcas, this.zzcat, this.zzcau);
    }
}
