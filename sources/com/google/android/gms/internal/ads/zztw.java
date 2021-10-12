package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzbv;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public final class zztw {
    private final Map<zztx, zzty> zzbok = new HashMap();
    private final LinkedList<zztx> zzbol = new LinkedList<>();
    private zzss zzbom;

    private static void zza(String str, zztx zztx) {
        if (zzakb.isLoggable(2)) {
            zzakb.v(String.format(str, zztx));
        }
    }

    private static String[] zzax(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), "UTF-8");
            }
            return split;
        } catch (UnsupportedEncodingException unused) {
            return new String[0];
        }
    }

    private static boolean zzay(String str) {
        try {
            return Pattern.matches((String) zzkb.zzik().zzd(zznk.zzazf), str);
        } catch (RuntimeException e) {
            zzbv.zzeo().zza(e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    private static String zzaz(String str) {
        try {
            Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(str);
            return matcher.matches() ? matcher.group(1) : str;
        } catch (RuntimeException unused) {
            return str;
        }
    }

    private static void zzb(Bundle bundle, String str) {
        while (true) {
            String[] split = str.split("/", 2);
            if (split.length != 0) {
                String str2 = split[0];
                if (split.length == 1) {
                    bundle.remove(str2);
                    return;
                }
                bundle = bundle.getBundle(str2);
                if (bundle != null) {
                    str = split[1];
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    static Set<String> zzh(zzjj zzjj) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(zzjj.extras.keySet());
        Bundle bundle = zzjj.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            hashSet.addAll(bundle.keySet());
        }
        return hashSet;
    }

    static zzjj zzi(zzjj zzjj) {
        zzjj zzk = zzk(zzjj);
        Bundle bundle = zzk.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean("_skipMediation", true);
        }
        zzk.extras.putBoolean("_skipMediation", true);
        return zzk;
    }

    private static zzjj zzj(zzjj zzjj) {
        zzjj zzk = zzk(zzjj);
        String[] split = ((String) zzkb.zzik().zzd(zznk.zzazb)).split(",");
        for (String str : split) {
            zzb(zzk.zzaqg, str);
            if (str.startsWith("com.google.ads.mediation.admob.AdMobAdapter/")) {
                zzb(zzk.extras, str.replaceFirst("com.google.ads.mediation.admob.AdMobAdapter/", ""));
            }
        }
        return zzk;
    }

    private static zzjj zzk(zzjj zzjj) {
        Parcel obtain = Parcel.obtain();
        zzjj.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        zzjj createFromParcel = zzjj.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return ((Boolean) zzkb.zzik().zzd(zznk.zzayo)).booleanValue() ? createFromParcel.zzhv() : createFromParcel;
    }

    private final String zzle() {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator<zztx> it = this.zzbol.iterator();
            while (it.hasNext()) {
                sb.append(Base64.encodeToString(it.next().toString().getBytes("UTF-8"), 0));
                if (it.hasNext()) {
                    sb.append("\u0000");
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public final zztz zza(zzjj zzjj, String str) {
        if (zzay(str)) {
            return null;
        }
        int i = new zzagb(this.zzbom.getApplicationContext()).zzoo().zzcjx;
        zzjj zzj = zzj(zzjj);
        String zzaz = zzaz(str);
        zztx zztx = new zztx(zzj, zzaz, i);
        zzty zzty = this.zzbok.get(zztx);
        if (zzty == null) {
            zza("Interstitial pool created at %s.", zztx);
            zzty = new zzty(zzj, zzaz, i);
            this.zzbok.put(zztx, zzty);
        }
        this.zzbol.remove(zztx);
        this.zzbol.add(zztx);
        zzty.zzli();
        while (true) {
            if (this.zzbol.size() <= ((Integer) zzkb.zzik().zzd(zznk.zzazc)).intValue()) {
                break;
            }
            zztx remove = this.zzbol.remove();
            zzty zzty2 = this.zzbok.get(remove);
            zza("Evicting interstitial queue for %s.", remove);
            while (zzty2.size() > 0) {
                zztz zzl = zzty2.zzl(null);
                if (zzl.zzwa) {
                    zzua.zzlk().zzlm();
                }
                zzl.zzbor.zzdj();
            }
            this.zzbok.remove(remove);
        }
        while (zzty.size() > 0) {
            zztz zzl2 = zzty.zzl(zzj);
            if (zzl2.zzwa) {
                if (zzbv.zzer().currentTimeMillis() - zzl2.zzbou > ((long) ((Integer) zzkb.zzik().zzd(zznk.zzaze)).intValue()) * 1000) {
                    zza("Expired interstitial at %s.", zztx);
                    zzua.zzlk().zzll();
                }
            }
            String str2 = zzl2.zzbos != null ? " (inline) " : " ";
            StringBuilder sb = new StringBuilder(str2.length() + 34);
            sb.append("Pooled interstitial");
            sb.append(str2);
            sb.append("returned at %s.");
            zza(sb.toString(), zztx);
            return zzl2;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzss zzss) {
        if (this.zzbom == null) {
            zzss zzlc = zzss.zzlc();
            this.zzbom = zzlc;
            if (zzlc != null) {
                SharedPreferences sharedPreferences = zzlc.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
                while (this.zzbol.size() > 0) {
                    zztx remove = this.zzbol.remove();
                    zzty zzty = this.zzbok.get(remove);
                    zza("Flushing interstitial queue for %s.", remove);
                    while (zzty.size() > 0) {
                        zzty.zzl(null).zzbor.zzdj();
                    }
                    this.zzbok.remove(remove);
                }
                try {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                        if (!entry.getKey().equals("PoolKeys")) {
                            zzuc zzba = zzuc.zzba((String) entry.getValue());
                            zztx zztx = new zztx(zzba.zzaao, zzba.zzye, zzba.zzbop);
                            if (!this.zzbok.containsKey(zztx)) {
                                this.zzbok.put(zztx, new zzty(zzba.zzaao, zzba.zzye, zzba.zzbop));
                                hashMap.put(zztx.toString(), zztx);
                                zza("Restored interstitial queue for %s.", zztx);
                            }
                        }
                    }
                    for (String str : zzax(sharedPreferences.getString("PoolKeys", ""))) {
                        zztx zztx2 = (zztx) hashMap.get(str);
                        if (this.zzbok.containsKey(zztx2)) {
                            this.zzbol.add(zztx2);
                        }
                    }
                } catch (IOException | RuntimeException e) {
                    zzbv.zzeo().zza(e, "InterstitialAdPool.restore");
                    zzakb.zzc("Malformed preferences value for InterstitialAdPool.", e);
                    this.zzbok.clear();
                    this.zzbol.clear();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzjj zzjj, String str) {
        zzss zzss = this.zzbom;
        if (zzss != null) {
            int i = new zzagb(zzss.getApplicationContext()).zzoo().zzcjx;
            zzjj zzj = zzj(zzjj);
            String zzaz = zzaz(str);
            zztx zztx = new zztx(zzj, zzaz, i);
            zzty zzty = this.zzbok.get(zztx);
            if (zzty == null) {
                zza("Interstitial pool created at %s.", zztx);
                zzty = new zzty(zzj, zzaz, i);
                this.zzbok.put(zztx, zzty);
            }
            zzty.zza(this.zzbom, zzjj);
            zzty.zzli();
            zza("Inline entry added to the queue at %s.", zztx);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzld() {
        int size;
        int zzlg;
        if (this.zzbom != null) {
            for (Map.Entry<zztx, zzty> entry : this.zzbok.entrySet()) {
                zztx key = entry.getKey();
                zzty value = entry.getValue();
                if (zzakb.isLoggable(2) && (zzlg = value.zzlg()) < (size = value.size())) {
                    zzakb.v(String.format("Loading %s/%s pooled interstitials for %s.", Integer.valueOf(size - zzlg), Integer.valueOf(size), key));
                }
                int zzlh = value.zzlh() + 0;
                while (true) {
                    if (value.size() >= ((Integer) zzkb.zzik().zzd(zznk.zzazd)).intValue()) {
                        break;
                    }
                    zza("Pooling and loading one new interstitial for %s.", key);
                    if (value.zzb(this.zzbom)) {
                        zzlh++;
                    }
                }
                zzua.zzlk().zzw(zzlh);
            }
            zzss zzss = this.zzbom;
            if (zzss != null) {
                SharedPreferences.Editor edit = zzss.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
                edit.clear();
                for (Map.Entry<zztx, zzty> entry2 : this.zzbok.entrySet()) {
                    zztx key2 = entry2.getKey();
                    zzty value2 = entry2.getValue();
                    if (value2.zzlj()) {
                        edit.putString(key2.toString(), new zzuc(value2).zzlu());
                        zza("Saved interstitial queue for %s.", key2);
                    }
                }
                edit.putString("PoolKeys", zzle());
                edit.apply();
            }
        }
    }
}
