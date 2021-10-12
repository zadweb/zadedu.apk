package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
final class zzaiw {
    private static final Map<String, String> zzcnl;
    private final List<String> zzcnm;
    private final zzaii zzcnn;
    private final Context zzrt;

    static {
        HashMap hashMap = new HashMap();
        if (PlatformVersion.isAtLeastLollipop()) {
            hashMap.put("android.webkit.resource.AUDIO_CAPTURE", "android.permission.RECORD_AUDIO");
            hashMap.put("android.webkit.resource.VIDEO_CAPTURE", "android.permission.CAMERA");
        }
        zzcnl = hashMap;
    }

    zzaiw(Context context, List<String> list, zzaii zzaii) {
        this.zzrt = context;
        this.zzcnm = list;
        this.zzcnn = zzaii;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        r5 = true;
     */
    public final List<String> zzc(String[] strArr) {
        boolean z;
        boolean z2;
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Iterator<String> it = this.zzcnm.iterator();
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                String next = it.next();
                if (next.equals(str)) {
                    break;
                }
                String valueOf = String.valueOf(next);
                if ((valueOf.length() != 0 ? "android.webkit.resource.".concat(valueOf) : new String("android.webkit.resource.")).equals(str)) {
                    break;
                }
            }
            if (z2) {
                if (zzcnl.containsKey(str)) {
                    zzbv.zzek();
                    if (!zzakk.zzl(this.zzrt, zzcnl.get(str))) {
                        z = false;
                    }
                }
                if (z) {
                    arrayList.add(str);
                } else {
                    this.zzcnn.zzch(str);
                }
            } else {
                this.zzcnn.zzcg(str);
            }
        }
        return arrayList;
    }
}
