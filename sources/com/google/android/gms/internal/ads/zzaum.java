package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxr;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class zzaum<P> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private ConcurrentMap<String, List<zzaun<P>>> zzdhk = new ConcurrentHashMap();
    private zzaun<P> zzdhl;

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0070  */
    public final zzaun<P> zza(P p, zzaxr.zzb zzb) throws GeneralSecurityException {
        byte[] bArr;
        List<zzaun<P>> put;
        ByteBuffer byteBuffer;
        int i = zzaud.zzdhh[zzb.zzzs().ordinal()];
        if (i == 1 || i == 2) {
            byteBuffer = ByteBuffer.allocate(5).put((byte) 0);
        } else if (i == 3) {
            byteBuffer = ByteBuffer.allocate(5).put((byte) 1);
        } else if (i == 4) {
            bArr = zzauc.zzdhg;
            zzaun<P> zzaun = new zzaun<>(p, bArr, zzb.zzzq(), zzb.zzzs());
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzaun);
            String str = new String(zzaun.zzwj(), UTF_8);
            put = this.zzdhk.put(str, Collections.unmodifiableList(arrayList));
            if (put != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(put);
                arrayList2.add(zzaun);
                this.zzdhk.put(str, Collections.unmodifiableList(arrayList2));
            }
            return zzaun;
        } else {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        bArr = byteBuffer.putInt(zzb.zzzr()).array();
        zzaun<P> zzaun2 = new zzaun<>(p, bArr, zzb.zzzq(), zzb.zzzs());
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(zzaun2);
        String str2 = new String(zzaun2.zzwj(), UTF_8);
        put = this.zzdhk.put(str2, Collections.unmodifiableList(arrayList3));
        if (put != null) {
        }
        return zzaun2;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzaun<P> zzaun) {
        this.zzdhl = zzaun;
    }

    public final zzaun<P> zzwh() {
        return this.zzdhl;
    }
}
