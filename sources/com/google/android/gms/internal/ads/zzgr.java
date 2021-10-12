package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

@zzadh
public final class zzgr {
    private final int zzaiy;
    private final int zzaiz;
    private final int zzaja;
    private final zzgq zzajb = new zzgv();

    public zzgr(int i) {
        this.zzaiz = i;
        this.zzaiy = 6;
        this.zzaja = 0;
    }

    private final String zzy(String str) {
        String[] split = str.split("\n");
        if (split.length == 0) {
            return "";
        }
        zzgt zzgt = new zzgt();
        PriorityQueue priorityQueue = new PriorityQueue(this.zzaiz, new zzgs(this));
        for (String str2 : split) {
            String[] zzb = zzgu.zzb(str2, false);
            if (zzb.length != 0) {
                zzgx.zza(zzb, this.zzaiz, this.zzaiy, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zzgt.write(this.zzajb.zzx(((zzgy) it.next()).zzajf));
            } catch (IOException e) {
                zzakb.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzgt.toString();
    }

    public final String zza(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            String str = arrayList2.get(i);
            i++;
            sb.append(str.toLowerCase(Locale.US));
            sb.append('\n');
        }
        return zzy(sb.toString());
    }
}
