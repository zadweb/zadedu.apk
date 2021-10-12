package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzadh
public final class zzaql implements zzv<zzapw> {
    private static Integer zze(Map<String, String> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(map.get(str)));
        } catch (NumberFormatException unused) {
            String str2 = map.get(str);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39 + String.valueOf(str2).length());
            sb.append("Precache invalid numeric parameter '");
            sb.append(str);
            sb.append("': ");
            sb.append(str2);
            zzakb.zzdk(sb.toString());
            return null;
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.util.Map] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    @Override // com.google.android.gms.ads.internal.gmsg.zzv
    public final /* synthetic */ void zza(zzapw zzapw, Map map) {
        Integer zze;
        Integer zze2;
        Integer zze3;
        Integer zze4;
        String str;
        zzapw zzapw2 = zzapw;
        zzbv.zzff();
        if (!map.containsKey("abort")) {
            String str2 = (String) map.get("src");
            if (str2 == null) {
                if (zzaqg.zzc(zzapw2) == null) {
                    str = "Precache must specify a source.";
                }
                zze = zze(map, "minBufferMs");
                if (zze != null) {
                }
                zze2 = zze(map, "maxBufferMs");
                if (zze2 != null) {
                }
                zze3 = zze(map, "bufferForPlaybackMs");
                if (zze3 != null) {
                }
                zze4 = zze(map, "bufferForPlaybackAfterRebufferMs");
                if (zze4 == null) {
                }
            } else if (zzaqg.zzc(zzapw2) != null) {
                str = "Precache task is already running.";
            } else if (zzapw2.zzbi() == null) {
                str = "Precache requires a dependency provider.";
            } else {
                zzapv zzapv = new zzapv((String) map.get("flags"));
                Integer zze5 = zze(map, "player");
                if (zze5 == null) {
                    zze5 = 0;
                }
                new zzaqe(zzapw2, zzapw2.zzbi().zzwy.zza(zzapw2, zze5.intValue(), null, zzapv), str2).zznt();
                zze = zze(map, "minBufferMs");
                if (zze != null) {
                    zze.intValue();
                }
                zze2 = zze(map, "maxBufferMs");
                if (zze2 != null) {
                    zze2.intValue();
                }
                zze3 = zze(map, "bufferForPlaybackMs");
                if (zze3 != null) {
                    zze3.intValue();
                }
                zze4 = zze(map, "bufferForPlaybackAfterRebufferMs");
                if (zze4 == null) {
                    zze4.intValue();
                    return;
                }
                return;
            }
            zzakb.zzdk(str);
        } else if (!zzaqg.zzb(zzapw2)) {
            zzakb.zzdk("Precache abort but no precache task running.");
        }
    }
}
