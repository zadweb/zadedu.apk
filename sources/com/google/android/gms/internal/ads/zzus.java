package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public abstract class zzus<ReferenceT> {
    private final Map<String, CopyOnWriteArrayList<zzv<? super ReferenceT>>> zzbpn = new HashMap();

    private final synchronized void zzb(String str, Map<String, String> map) {
        if (zzakb.isLoggable(2)) {
            String valueOf = String.valueOf(str);
            zzakb.v(valueOf.length() != 0 ? "Received GMSG: ".concat(valueOf) : new String("Received GMSG: "));
            for (String str2 : map.keySet()) {
                String str3 = map.get(str2);
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 4 + String.valueOf(str3).length());
                sb.append("  ");
                sb.append(str2);
                sb.append(": ");
                sb.append(str3);
                zzakb.v(sb.toString());
            }
        }
        CopyOnWriteArrayList<zzv<? super ReferenceT>> copyOnWriteArrayList = this.zzbpn.get(str);
        if (copyOnWriteArrayList != null) {
            Iterator<zzv<? super ReferenceT>> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                zzaoe.zzcvy.execute(new zzut(this, it.next(), map));
            }
        }
    }

    public abstract ReferenceT getReference();

    public synchronized void reset() {
        this.zzbpn.clear();
    }

    public final synchronized void zza(String str, zzv<? super ReferenceT> zzv) {
        CopyOnWriteArrayList<zzv<? super ReferenceT>> copyOnWriteArrayList = this.zzbpn.get(str);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.zzbpn.put(str, copyOnWriteArrayList);
        }
        copyOnWriteArrayList.add(zzv);
    }

    public final synchronized void zza(String str, Predicate<zzv<? super ReferenceT>> predicate) {
        CopyOnWriteArrayList<zzv<? super ReferenceT>> copyOnWriteArrayList = this.zzbpn.get(str);
        if (copyOnWriteArrayList != null) {
            ArrayList arrayList = new ArrayList();
            Iterator<zzv<? super ReferenceT>> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                zzv<? super ReferenceT> next = it.next();
                if (predicate.apply(next)) {
                    arrayList.add(next);
                }
            }
            copyOnWriteArrayList.removeAll(arrayList);
        }
    }

    public final synchronized void zzb(String str, zzv<? super ReferenceT> zzv) {
        CopyOnWriteArrayList<zzv<? super ReferenceT>> copyOnWriteArrayList = this.zzbpn.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(zzv);
        }
    }

    public final boolean zzf(Uri uri) {
        if (!"gmsg".equalsIgnoreCase(uri.getScheme()) || !"mobileads.google.com".equalsIgnoreCase(uri.getHost())) {
            return false;
        }
        String path = uri.getPath();
        zzbv.zzek();
        zzb(path, zzakk.zzg(uri));
        return true;
    }
}
