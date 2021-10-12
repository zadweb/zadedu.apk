package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzan {
    final String zza;
    final long zzb;
    final long zzc;
    long zzca;
    final String zzcb;
    final long zzd;
    final long zze;
    final List<zzl> zzg;

    /* JADX WARNING: Illegal instructions before constructor call */
    zzan(String str, zzc zzc2) {
        this(str, r3, r4, r6, r8, r10, r12);
        List<zzl> list;
        String str2 = zzc2.zza;
        long j = zzc2.zzb;
        long j2 = zzc2.zzc;
        long j3 = zzc2.zzd;
        long j4 = zzc2.zze;
        if (zzc2.zzg != null) {
            list = zzc2.zzg;
        } else {
            Map<String, String> map = zzc2.zzf;
            ArrayList arrayList = new ArrayList(map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                arrayList.add(new zzl(entry.getKey(), entry.getValue()));
            }
            list = arrayList;
        }
        this.zzca = (long) zzc2.data.length;
    }

    private zzan(String str, String str2, long j, long j2, long j3, long j4, List<zzl> list) {
        this.zzcb = str;
        this.zza = "".equals(str2) ? null : str2;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = j3;
        this.zze = j4;
        this.zzg = list;
    }

    static zzan zzc(zzao zzao) throws IOException {
        if (zzam.zzb((InputStream) zzao) == 538247942) {
            return new zzan(zzam.zza(zzao), zzam.zza(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzb(zzao));
        }
        throw new IOException();
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(OutputStream outputStream) {
        try {
            zzam.zza(outputStream, 538247942);
            zzam.zza(outputStream, this.zzcb);
            zzam.zza(outputStream, this.zza == null ? "" : this.zza);
            zzam.zza(outputStream, this.zzb);
            zzam.zza(outputStream, this.zzc);
            zzam.zza(outputStream, this.zzd);
            zzam.zza(outputStream, this.zze);
            List<zzl> list = this.zzg;
            if (list != null) {
                zzam.zza(outputStream, list.size());
                for (zzl zzl : list) {
                    zzam.zza(outputStream, zzl.getName());
                    zzam.zza(outputStream, zzl.getValue());
                }
            } else {
                zzam.zza(outputStream, 0);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            zzaf.d("%s", e.toString());
            return false;
        }
    }
}
