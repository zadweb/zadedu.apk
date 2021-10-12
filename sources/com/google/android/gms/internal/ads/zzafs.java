package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.text.TextUtils;
import com.appnext.base.b.d;
import com.appnext.base.b.i;
import com.appnext.core.Ad;
import com.google.android.gms.ads.internal.zzbv;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafs {
    private static final SimpleDateFormat zzcho = new SimpleDateFormat("yyyyMMdd", Locale.US);

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e1 A[Catch:{ JSONException -> 0x0269 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e7 A[Catch:{ JSONException -> 0x0269 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0161 A[Catch:{ JSONException -> 0x0269 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x016a A[Catch:{ JSONException -> 0x0269 }] */
    public static zzaej zza(Context context, zzaef zzaef, String str) {
        int i;
        long j;
        int i2;
        String str2;
        zzaej zzaej;
        long j2;
        int zzrl;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("ad_base_url", null);
            String optString2 = jSONObject.optString("ad_url", null);
            String optString3 = jSONObject.optString("ad_size", null);
            String optString4 = jSONObject.optString("ad_slot_size", optString3);
            boolean z = (zzaef == null || zzaef.zzcdb == 0) ? false : true;
            String optString5 = jSONObject.optString("ad_json", null);
            if (optString5 == null) {
                optString5 = jSONObject.optString("ad_html", null);
            }
            if (optString5 == null) {
                optString5 = jSONObject.optString("body", null);
            }
            if (optString5 == null && jSONObject.has("ads")) {
                optString5 = jSONObject.toString();
            }
            String optString6 = jSONObject.optString("debug_dialog", null);
            String optString7 = jSONObject.optString("debug_signals", null);
            long j3 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1;
            String optString8 = jSONObject.optString("orientation", null);
            if (Ad.ORIENTATION_PORTRAIT.equals(optString8)) {
                zzrl = zzbv.zzem().zzrm();
            } else if (Ad.ORIENTATION_LANDSCAPE.equals(optString8)) {
                zzrl = zzbv.zzem().zzrl();
            } else {
                i = -1;
                if (TextUtils.isEmpty(optString5) || TextUtils.isEmpty(optString2)) {
                    i2 = -1;
                    str2 = optString5;
                    zzaej = null;
                    j = -1;
                } else {
                    i2 = -1;
                    zzaej = zzafn.zza(zzaef, context, zzaef.zzacr.zzcw, optString2, null, null, null, null, null);
                    optString = zzaej.zzbyq;
                    String str3 = zzaej.zzceo;
                    j = zzaej.zzceu;
                    str2 = str3;
                }
                if (str2 != null) {
                    return new zzaej(0);
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("click_urls");
                List<String> list = zzaej == null ? null : zzaej.zzbsn;
                if (optJSONArray != null) {
                    list = zza(optJSONArray, list);
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("impression_urls");
                List<String> list2 = zzaej == null ? null : zzaej.zzbso;
                if (optJSONArray2 != null) {
                    list2 = zza(optJSONArray2, list2);
                }
                JSONArray optJSONArray3 = jSONObject.optJSONArray("downloaded_impression_urls");
                List<String> list3 = zzaej == null ? null : zzaej.zzbsp;
                List<String> zza = optJSONArray3 != null ? zza(optJSONArray3, list3) : list3;
                JSONArray optJSONArray4 = jSONObject.optJSONArray("manual_impression_urls");
                List<String> list4 = zzaej == null ? null : zzaej.zzces;
                List<String> zza2 = optJSONArray4 != null ? zza(optJSONArray4, list4) : list4;
                if (zzaej != null) {
                    if (zzaej.orientation != i2) {
                        i = zzaej.orientation;
                    }
                    if (zzaej.zzcep > 0) {
                        j2 = zzaej.zzcep;
                        String optString9 = jSONObject.optString("active_view");
                        boolean optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
                        return new zzaej(zzaef, optString, str2, list, list2, j2, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), zza2, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString3, j, optString6, optBoolean, !optBoolean ? jSONObject.optString("ad_passback_url", null) : null, optString9, jSONObject.optBoolean("custom_render_allowed", false), z, zzaef.zzcdd, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzaig.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), (List<String>) null), zza(jSONObject.optJSONArray("video_complete_urls"), (List<String>) null), jSONObject.optBoolean("use_displayed_impression", false), zzael.zzl(jSONObject.optJSONObject("auto_protection_configuration")), zzaef.zzcdr, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), (List<String>) null), jSONObject.optBoolean("render_in_browser", zzaef.zzbss), optString4, zzaiq.zzo(jSONObject.optJSONObject("safe_browsing")), optString7, jSONObject.optBoolean("content_vertical_opted_out", true), zzaef.zzced, jSONObject.optBoolean("custom_close_blocked"), 0, jSONObject.optBoolean("enable_omid", false), zza, jSONObject.optBoolean("disable_closable_area", false), jSONObject.optString("omid_settings", null));
                    }
                }
                j2 = j3;
                String optString92 = jSONObject.optString("active_view");
                boolean optBoolean2 = jSONObject.optBoolean("ad_is_javascript", false);
                return new zzaej(zzaef, optString, str2, list, list2, j2, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), zza2, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString3, j, optString6, optBoolean2, !optBoolean2 ? jSONObject.optString("ad_passback_url", null) : null, optString92, jSONObject.optBoolean("custom_render_allowed", false), z, zzaef.zzcdd, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzaig.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), (List<String>) null), zza(jSONObject.optJSONArray("video_complete_urls"), (List<String>) null), jSONObject.optBoolean("use_displayed_impression", false), zzael.zzl(jSONObject.optJSONObject("auto_protection_configuration")), zzaef.zzcdr, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), (List<String>) null), jSONObject.optBoolean("render_in_browser", zzaef.zzbss), optString4, zzaiq.zzo(jSONObject.optJSONObject("safe_browsing")), optString7, jSONObject.optBoolean("content_vertical_opted_out", true), zzaef.zzced, jSONObject.optBoolean("custom_close_blocked"), 0, jSONObject.optBoolean("enable_omid", false), zza, jSONObject.optBoolean("disable_closable_area", false), jSONObject.optString("omid_settings", null));
            }
            i = zzrl;
            if (TextUtils.isEmpty(optString5)) {
            }
            i2 = -1;
            str2 = optString5;
            zzaej = null;
            j = -1;
            if (str2 != null) {
            }
        } catch (JSONException e) {
            String valueOf = String.valueOf(e.getMessage());
            zzakb.zzdk(valueOf.length() != 0 ? "Could not parse the inline ad response: ".concat(valueOf) : new String("Could not parse the inline ad response: "));
            return new zzaej(0);
        }
    }

    private static List<String> zza(JSONArray jSONArray, List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    public static JSONObject zza(Context context, zzafl zzafl) {
        Bundle bundle;
        Integer valueOf;
        String str;
        Object obj;
        boolean z;
        String str2;
        String str3;
        String str4;
        String str5;
        boolean z2;
        int i;
        int i2;
        String str6;
        boolean z3;
        zzaef zzaef = zzafl.zzcgs;
        Location location = zzafl.zzaqe;
        zzaga zzaga = zzafl.zzcgt;
        Bundle bundle2 = zzafl.zzcdc;
        JSONObject jSONObject = zzafl.zzcgu;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("extra_caps", zzkb.zzik().zzd(zznk.zzbbk));
            if (zzafl.zzcdj.size() > 0) {
                hashMap.put("eid", TextUtils.join(",", zzafl.zzcdj));
            }
            if (zzaef.zzccu != null) {
                hashMap.put("ad_pos", zzaef.zzccu);
            }
            zzjj zzjj = zzaef.zzccv;
            String zzqn = zzajw.zzqn();
            if (zzqn != null) {
                hashMap.put("abf", zzqn);
            }
            if (zzjj.zzapw != -1) {
                hashMap.put("cust_age", zzcho.format(new Date(zzjj.zzapw)));
            }
            if (zzjj.extras != null) {
                hashMap.put("extras", zzjj.extras);
            }
            if (zzjj.zzapx != -1) {
                hashMap.put("cust_gender", Integer.valueOf(zzjj.zzapx));
            }
            if (zzjj.zzapy != null) {
                hashMap.put("kw", zzjj.zzapy);
            }
            if (zzjj.zzaqa != -1) {
                hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(zzjj.zzaqa));
            }
            if (zzjj.zzapz) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbfp)).booleanValue()) {
                    str6 = "test_request";
                    z3 = true;
                } else {
                    str6 = "adtest";
                    z3 = d.fe;
                }
                hashMap.put(str6, z3);
            }
            if (zzjj.versionCode >= 2) {
                if (zzjj.zzaqb) {
                    hashMap.put("d_imp_hdr", 1);
                }
                if (!TextUtils.isEmpty(zzjj.zzaqc)) {
                    hashMap.put("ppid", zzjj.zzaqc);
                }
            }
            if (zzjj.versionCode >= 3 && zzjj.zzaqf != null) {
                hashMap.put("url", zzjj.zzaqf);
            }
            if (zzjj.versionCode >= 5) {
                if (zzjj.zzaqh != null) {
                    hashMap.put("custom_targeting", zzjj.zzaqh);
                }
                if (zzjj.zzaqi != null) {
                    hashMap.put("category_exclusions", zzjj.zzaqi);
                }
                if (zzjj.zzaqj != null) {
                    hashMap.put("request_agent", zzjj.zzaqj);
                }
            }
            if (zzjj.versionCode >= 6 && zzjj.zzaqk != null) {
                hashMap.put("request_pkg", zzjj.zzaqk);
            }
            if (zzjj.versionCode >= 7) {
                hashMap.put("is_designed_for_families", Boolean.valueOf(zzjj.zzaql));
            }
            if (zzaef.zzacv.zzard != null) {
                zzjn[] zzjnArr = zzaef.zzacv.zzard;
                int length = zzjnArr.length;
                int i3 = 0;
                boolean z4 = false;
                boolean z5 = false;
                while (i3 < length) {
                    zzjn zzjn = zzjnArr[i3];
                    if (!zzjn.zzarf && !z4) {
                        hashMap.put("format", zzjn.zzarb);
                        z4 = true;
                    }
                    if (zzjn.zzarf && !z5) {
                        hashMap.put("fluid", "height");
                        z5 = true;
                    }
                    if (z4 && z5) {
                        break;
                    }
                    i3++;
                    length = length;
                    zzjnArr = zzjnArr;
                }
            } else {
                hashMap.put("format", zzaef.zzacv.zzarb);
                if (zzaef.zzacv.zzarf) {
                    hashMap.put("fluid", "height");
                }
            }
            if (zzaef.zzacv.width == -1) {
                hashMap.put("smart_w", "full");
            }
            if (zzaef.zzacv.height == -2) {
                hashMap.put("smart_h", "auto");
            }
            if (zzaef.zzacv.zzard != null) {
                StringBuilder sb = new StringBuilder();
                zzjn[] zzjnArr2 = zzaef.zzacv.zzard;
                int length2 = zzjnArr2.length;
                boolean z6 = false;
                int i4 = 0;
                while (i4 < length2) {
                    zzjn zzjn2 = zzjnArr2[i4];
                    if (zzjn2.zzarf) {
                        z6 = true;
                    } else {
                        if (sb.length() != 0) {
                            sb.append("|");
                        }
                        sb.append(zzjn2.width == -1 ? (int) (((float) zzjn2.widthPixels) / zzaga.zzagu) : zzjn2.width);
                        sb.append(AvidJSONUtil.KEY_X);
                        sb.append(zzjn2.height == -2 ? (int) (((float) zzjn2.heightPixels) / zzaga.zzagu) : zzjn2.height);
                    }
                    i4++;
                    length2 = length2;
                    zzjnArr2 = zzjnArr2;
                }
                if (z6) {
                    if (sb.length() != 0) {
                        i2 = 0;
                        sb.insert(0, "|");
                    } else {
                        i2 = 0;
                    }
                    sb.insert(i2, "320x50");
                }
                hashMap.put("sz", sb);
            }
            if (zzaef.zzcdb != 0) {
                hashMap.put("native_version", Integer.valueOf(zzaef.zzcdb));
                hashMap.put("native_templates", zzaef.zzads);
                zzpl zzpl = zzaef.zzadj;
                String str7 = "any";
                if (!(zzpl == null || (i = zzpl.zzbjo) == 0)) {
                    str7 = i != 1 ? i != 2 ? Ad.ORIENTATION_DEFAULT : Ad.ORIENTATION_LANDSCAPE : Ad.ORIENTATION_PORTRAIT;
                }
                hashMap.put("native_image_orientation", str7);
                if (!zzaef.zzcdk.isEmpty()) {
                    hashMap.put("native_custom_templates", zzaef.zzcdk);
                }
                if (zzaef.versionCode >= 24) {
                    hashMap.put("max_num_ads", Integer.valueOf(zzaef.zzceg));
                }
                if (!TextUtils.isEmpty(zzaef.zzcee)) {
                    try {
                        hashMap.put("native_advanced_settings", new JSONArray(zzaef.zzcee));
                    } catch (JSONException e) {
                        zzakb.zzc("Problem creating json from native advanced settings", e);
                    }
                }
            }
            if (zzaef.zzadn != null && zzaef.zzadn.size() > 0) {
                for (Integer num : zzaef.zzadn) {
                    if (num.intValue() == 2) {
                        str5 = "iba";
                        z2 = true;
                    } else if (num.intValue() == 1) {
                        str5 = "ina";
                        z2 = true;
                    }
                    hashMap.put(str5, z2);
                }
            }
            if (zzaef.zzacv.zzarg) {
                hashMap.put("ene", true);
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaxv)).booleanValue()) {
                hashMap.put("xsrve", true);
            }
            if (zzaef.zzadl != null) {
                hashMap.put("is_icon_ad", true);
                hashMap.put("icon_ad_expansion_behavior", Integer.valueOf(zzaef.zzadl.zzasj));
            }
            hashMap.put("slotname", zzaef.zzacp);
            hashMap.put("pn", zzaef.applicationInfo.packageName);
            if (zzaef.zzccw != null) {
                hashMap.put("vc", Integer.valueOf(zzaef.zzccw.versionCode));
            }
            hashMap.put("ms", zzafl.zzccx);
            hashMap.put("seq_num", zzaef.zzccy);
            hashMap.put("session_id", zzaef.zzccz);
            hashMap.put("js", zzaef.zzacr.zzcw);
            zzagk zzagk = zzafl.zzcgo;
            Bundle bundle3 = zzaef.zzcdw;
            Bundle bundle4 = zzafl.zzcgn;
            hashMap.put("am", Integer.valueOf(zzaga.zzcjk));
            hashMap.put("cog", zzv(zzaga.zzcjl));
            hashMap.put("coh", zzv(zzaga.zzcjm));
            if (!TextUtils.isEmpty(zzaga.zzcjn)) {
                hashMap.put("carrier", zzaga.zzcjn);
            }
            hashMap.put("gl", zzaga.zzcjo);
            if (zzaga.zzcjp) {
                hashMap.put("simulator", 1);
            }
            if (zzaga.zzcjq) {
                hashMap.put("is_sidewinder", 1);
            }
            hashMap.put("ma", zzv(zzaga.zzcjr));
            hashMap.put("sp", zzv(zzaga.zzcjs));
            hashMap.put("hl", zzaga.zzcjt);
            if (!TextUtils.isEmpty(zzaga.zzcju)) {
                hashMap.put("mv", zzaga.zzcju);
            }
            hashMap.put("muv", Integer.valueOf(zzaga.zzcjw));
            if (zzaga.zzcjx != -2) {
                hashMap.put("cnt", Integer.valueOf(zzaga.zzcjx));
            }
            hashMap.put("gnt", Integer.valueOf(zzaga.zzcjy));
            hashMap.put("pt", Integer.valueOf(zzaga.zzcjz));
            hashMap.put("rm", Integer.valueOf(zzaga.zzcka));
            hashMap.put("riv", Integer.valueOf(zzaga.zzckb));
            Bundle bundle5 = new Bundle();
            bundle5.putString("build_build", zzaga.zzckg);
            bundle5.putString("build_device", zzaga.zzckh);
            Bundle bundle6 = new Bundle();
            bundle6.putBoolean("is_charging", zzaga.zzckd);
            bundle6.putDouble("battery_level", zzaga.zzckc);
            bundle5.putBundle("battery", bundle6);
            Bundle bundle7 = new Bundle();
            bundle7.putInt("active_network_state", zzaga.zzckf);
            bundle7.putBoolean("active_network_metered", zzaga.zzcke);
            if (zzagk != null) {
                Bundle bundle8 = new Bundle();
                bundle8.putInt("predicted_latency_micros", zzagk.zzckq);
                bundle = bundle2;
                bundle8.putLong("predicted_down_throughput_bps", zzagk.zzckr);
                bundle8.putLong("predicted_up_throughput_bps", zzagk.zzcks);
                bundle7.putBundle("predictions", bundle8);
            } else {
                bundle = bundle2;
            }
            bundle5.putBundle("network", bundle7);
            Bundle bundle9 = new Bundle();
            bundle9.putBoolean("is_browser_custom_tabs_capable", zzaga.zzcki);
            bundle5.putBundle("browser", bundle9);
            if (bundle3 != null) {
                Bundle bundle10 = new Bundle();
                bundle10.putString("runtime_free", Long.toString(bundle3.getLong("runtime_free_memory", -1)));
                bundle10.putString("runtime_max", Long.toString(bundle3.getLong("runtime_max_memory", -1)));
                bundle10.putString("runtime_total", Long.toString(bundle3.getLong("runtime_total_memory", -1)));
                bundle10.putString("web_view_count", Integer.toString(bundle3.getInt("web_view_count", 0)));
                Debug.MemoryInfo memoryInfo = (Debug.MemoryInfo) bundle3.getParcelable("debug_memory_info");
                if (memoryInfo != null) {
                    bundle10.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
                    bundle10.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
                    bundle10.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
                    bundle10.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
                    bundle10.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
                    bundle10.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
                    bundle10.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
                    bundle10.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
                    bundle10.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
                }
                bundle5.putBundle("android_mem_info", bundle10);
            }
            Bundle bundle11 = new Bundle();
            bundle11.putBundle("parental_controls", bundle4);
            if (!TextUtils.isEmpty(zzaga.zzcjv)) {
                bundle11.putString("package_version", zzaga.zzcjv);
            }
            bundle5.putBundle("play_store", bundle11);
            hashMap.put("device", bundle5);
            Bundle bundle12 = new Bundle();
            bundle12.putString("doritos", zzafl.zzcgp);
            bundle12.putString("doritos_v2", zzafl.zzcgq);
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayj)).booleanValue()) {
                if (zzafl.zzcgr != null) {
                    str2 = zzafl.zzcgr.getId();
                    z = zzafl.zzcgr.isLimitAdTrackingEnabled();
                } else {
                    str2 = null;
                    z = false;
                }
                if (!TextUtils.isEmpty(str2)) {
                    bundle12.putString("rdid", str2);
                    bundle12.putBoolean("is_lat", z);
                    str3 = "idtype";
                    str4 = "adid";
                } else {
                    zzkb.zzif();
                    bundle12.putString("pdid", zzamu.zzbd(context));
                    str3 = "pdidtype";
                    str4 = "ssaid";
                }
                bundle12.putString(str3, str4);
            }
            hashMap.put("pii", bundle12);
            hashMap.put("platform", Build.MANUFACTURER);
            hashMap.put("submodel", Build.MODEL);
            if (location != null) {
                zza(hashMap, location);
            } else if (zzaef.zzccv.versionCode >= 2 && zzaef.zzccv.zzaqe != null) {
                zza(hashMap, zzaef.zzccv.zzaqe);
            }
            if (zzaef.versionCode >= 2) {
                hashMap.put("quality_signals", zzaef.zzcda);
            }
            if (zzaef.versionCode >= 4 && zzaef.zzcdd) {
                hashMap.put("forceHttps", Boolean.valueOf(zzaef.zzcdd));
            }
            if (bundle != null) {
                hashMap.put("content_info", bundle);
            }
            if (zzaef.versionCode >= 5) {
                hashMap.put("u_sd", Float.valueOf(zzaef.zzagu));
                hashMap.put("sh", Integer.valueOf(zzaef.zzcdf));
                valueOf = Integer.valueOf(zzaef.zzcde);
            } else {
                hashMap.put("u_sd", Float.valueOf(zzaga.zzagu));
                hashMap.put("sh", Integer.valueOf(zzaga.zzcdf));
                valueOf = Integer.valueOf(zzaga.zzcde);
            }
            hashMap.put("sw", valueOf);
            if (zzaef.versionCode >= 6) {
                if (!TextUtils.isEmpty(zzaef.zzcdg)) {
                    try {
                        hashMap.put("view_hierarchy", new JSONObject(zzaef.zzcdg));
                    } catch (JSONException e2) {
                        zzakb.zzc("Problem serializing view hierarchy to JSON", e2);
                    }
                }
                hashMap.put("correlation_id", Long.valueOf(zzaef.zzcdh));
            }
            if (zzaef.versionCode >= 7) {
                hashMap.put("request_id", zzaef.zzcdi);
            }
            if (zzaef.versionCode >= 12 && !TextUtils.isEmpty(zzaef.zzcdm)) {
                hashMap.put("anchor", zzaef.zzcdm);
            }
            if (zzaef.versionCode >= 13) {
                hashMap.put("android_app_volume", Float.valueOf(zzaef.zzcdn));
            }
            if (zzaef.versionCode >= 18) {
                hashMap.put("android_app_muted", Boolean.valueOf(zzaef.zzcdt));
            }
            if (zzaef.versionCode >= 14 && zzaef.zzcdo > 0) {
                hashMap.put("target_api", Integer.valueOf(zzaef.zzcdo));
            }
            if (zzaef.versionCode >= 15) {
                hashMap.put("scroll_index", Integer.valueOf(zzaef.zzcdp == -1 ? -1 : zzaef.zzcdp));
            }
            if (zzaef.versionCode >= 16) {
                hashMap.put("_activity_context", Boolean.valueOf(zzaef.zzcdq));
            }
            if (zzaef.versionCode >= 18) {
                if (!TextUtils.isEmpty(zzaef.zzcdu)) {
                    try {
                        hashMap.put("app_settings", new JSONObject(zzaef.zzcdu));
                    } catch (JSONException e3) {
                        zzakb.zzc("Problem creating json from app settings", e3);
                    }
                }
                hashMap.put("render_in_browser", Boolean.valueOf(zzaef.zzbss));
            }
            if (zzaef.versionCode >= 18) {
                hashMap.put("android_num_video_cache_tasks", Integer.valueOf(zzaef.zzcdv));
            }
            zzang zzang = zzaef.zzacr;
            boolean z7 = zzaef.zzceh;
            boolean z8 = zzafl.zzcgv;
            boolean z9 = zzaef.zzcej;
            Bundle bundle13 = new Bundle();
            Bundle bundle14 = new Bundle();
            bundle14.putString("cl", "193400285");
            bundle14.putString("rapid_rc", "dev");
            bundle14.putString("rapid_rollup", "HEAD");
            bundle13.putBundle("build_meta", bundle14);
            bundle13.putString("mf", Boolean.toString(((Boolean) zzkb.zzik().zzd(zznk.zzbbm)).booleanValue()));
            bundle13.putBoolean("instant_app", z7);
            bundle13.putBoolean("lite", zzang.zzcvh);
            bundle13.putBoolean("local_service", z8);
            bundle13.putBoolean("is_privileged_process", z9);
            hashMap.put("sdk_env", bundle13);
            hashMap.put("cache_state", jSONObject);
            if (zzaef.versionCode >= 19) {
                hashMap.put("gct", zzaef.zzcdx);
            }
            if (zzaef.versionCode >= 21 && zzaef.zzcdy) {
                hashMap.put("de", "1");
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayy)).booleanValue()) {
                String str8 = zzaef.zzacv.zzarb;
                boolean z10 = str8.equals("interstitial_mb") || str8.equals("reward_mb");
                Bundle bundle15 = zzaef.zzcdz;
                boolean z11 = bundle15 != null;
                if (z10 && z11) {
                    Bundle bundle16 = new Bundle();
                    bundle16.putBundle("interstitial_pool", bundle15);
                    hashMap.put("counters", bundle16);
                }
            }
            if (zzaef.zzcea != null) {
                hashMap.put("gmp_app_id", zzaef.zzcea);
            }
            if (zzaef.zzceb == null) {
                str = "fbs_aiid";
                obj = "";
            } else if ("TIME_OUT".equals(zzaef.zzceb)) {
                str = "sai_timeout";
                obj = zzkb.zzik().zzd(zznk.zzaxt);
            } else {
                str = "fbs_aiid";
                obj = zzaef.zzceb;
            }
            hashMap.put(str, obj);
            if (zzaef.zzcec != null) {
                hashMap.put("fbs_aeid", zzaef.zzcec);
            }
            if (zzaef.versionCode >= 24) {
                hashMap.put("disable_ml", Boolean.valueOf(zzaef.zzcei));
            }
            String str9 = (String) zzkb.zzik().zzd(zznk.zzavo);
            if (str9 != null && !str9.isEmpty()) {
                if (Build.VERSION.SDK_INT >= ((Integer) zzkb.zzik().zzd(zznk.zzavp)).intValue()) {
                    HashMap hashMap2 = new HashMap();
                    String[] split = str9.split(",");
                    for (String str10 : split) {
                        hashMap2.put(str10, zzams.zzdd(str10));
                    }
                    hashMap.put("video_decoders", hashMap2);
                }
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbet)).booleanValue()) {
                hashMap.put("omid_v", zzbv.zzfa().getVersion(context));
            }
            if (zzaef.zzcek != null && !zzaef.zzcek.isEmpty()) {
                hashMap.put("android_permissions", zzaef.zzcek);
            }
            if (zzakb.isLoggable(2)) {
                String valueOf2 = String.valueOf(zzbv.zzek().zzn(hashMap).toString(2));
                zzakb.v(valueOf2.length() != 0 ? "Ad Request JSON: ".concat(valueOf2) : new String("Ad Request JSON: "));
            }
            return zzbv.zzek().zzn(hashMap);
        } catch (JSONException e4) {
            String valueOf3 = String.valueOf(e4.getMessage());
            zzakb.zzdk(valueOf3.length() != 0 ? "Problem serializing ad request to JSON: ".concat(valueOf3) : new String("Problem serializing ad request to JSON: "));
            return null;
        }
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put(i.fC, valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put(d.fl, valueOf2);
        hashMap.put("uule", hashMap2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0160  */
    public static JSONObject zzb(zzaej zzaej) throws JSONException {
        String str;
        String str2;
        String str3;
        JSONObject jSONObject = new JSONObject();
        if (zzaej.zzbyq != null) {
            jSONObject.put("ad_base_url", zzaej.zzbyq);
        }
        if (zzaej.zzcet != null) {
            jSONObject.put("ad_size", zzaej.zzcet);
        }
        jSONObject.put("native", zzaej.zzare);
        if (zzaej.zzare) {
            str2 = zzaej.zzceo;
            str = "ad_json";
        } else {
            str2 = zzaej.zzceo;
            str = "ad_html";
        }
        jSONObject.put(str, str2);
        if (zzaej.zzcev != null) {
            jSONObject.put("debug_dialog", zzaej.zzcev);
        }
        if (zzaej.zzcfl != null) {
            jSONObject.put("debug_signals", zzaej.zzcfl);
        }
        if (zzaej.zzcep != -1) {
            double d = (double) zzaej.zzcep;
            Double.isNaN(d);
            jSONObject.put("interstitial_timeout", d / 1000.0d);
        }
        if (zzaej.orientation == zzbv.zzem().zzrm()) {
            str3 = Ad.ORIENTATION_PORTRAIT;
        } else {
            if (zzaej.orientation == zzbv.zzem().zzrl()) {
                str3 = Ad.ORIENTATION_LANDSCAPE;
            }
            if (zzaej.zzbsn != null) {
                jSONObject.put("click_urls", zzm(zzaej.zzbsn));
            }
            if (zzaej.zzbso != null) {
                jSONObject.put("impression_urls", zzm(zzaej.zzbso));
            }
            if (zzaej.zzbsp != null) {
                jSONObject.put("downloaded_impression_urls", zzm(zzaej.zzbsp));
            }
            if (zzaej.zzces != null) {
                jSONObject.put("manual_impression_urls", zzm(zzaej.zzces));
            }
            if (zzaej.zzcey != null) {
                jSONObject.put("active_view", zzaej.zzcey);
            }
            jSONObject.put("ad_is_javascript", zzaej.zzcew);
            if (zzaej.zzcex != null) {
                jSONObject.put("ad_passback_url", zzaej.zzcex);
            }
            jSONObject.put("mediation", zzaej.zzceq);
            jSONObject.put("custom_render_allowed", zzaej.zzcez);
            jSONObject.put("content_url_opted_out", zzaej.zzcfa);
            jSONObject.put("content_vertical_opted_out", zzaej.zzcfm);
            jSONObject.put("prefetch", zzaej.zzcfb);
            if (zzaej.zzbsu != -1) {
                jSONObject.put("refresh_interval_milliseconds", zzaej.zzbsu);
            }
            if (zzaej.zzcer != -1) {
                jSONObject.put("mediation_config_cache_time_milliseconds", zzaej.zzcer);
            }
            if (!TextUtils.isEmpty(zzaej.zzamj)) {
                jSONObject.put("gws_query_id", zzaej.zzamj);
            }
            jSONObject.put("fluid", !zzaej.zzarf ? "height" : "");
            jSONObject.put("native_express", zzaej.zzarg);
            if (zzaej.zzcff != null) {
                jSONObject.put("video_start_urls", zzm(zzaej.zzcff));
            }
            if (zzaej.zzcfg != null) {
                jSONObject.put("video_complete_urls", zzm(zzaej.zzcfg));
            }
            if (zzaej.zzcfe != null) {
                zzaig zzaig = zzaej.zzcfe;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("rb_type", zzaig.type);
                jSONObject2.put("rb_amount", zzaig.zzcmk);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject2);
                jSONObject.put("rewards", jSONArray);
            }
            jSONObject.put("use_displayed_impression", zzaej.zzcfh);
            jSONObject.put("auto_protection_configuration", zzaej.zzcfi);
            jSONObject.put("render_in_browser", zzaej.zzbss);
            jSONObject.put("disable_closable_area", zzaej.zzzm);
            return jSONObject;
        }
        jSONObject.put("orientation", str3);
        if (zzaej.zzbsn != null) {
        }
        if (zzaej.zzbso != null) {
        }
        if (zzaej.zzbsp != null) {
        }
        if (zzaej.zzces != null) {
        }
        if (zzaej.zzcey != null) {
        }
        jSONObject.put("ad_is_javascript", zzaej.zzcew);
        if (zzaej.zzcex != null) {
        }
        jSONObject.put("mediation", zzaej.zzceq);
        jSONObject.put("custom_render_allowed", zzaej.zzcez);
        jSONObject.put("content_url_opted_out", zzaej.zzcfa);
        jSONObject.put("content_vertical_opted_out", zzaej.zzcfm);
        jSONObject.put("prefetch", zzaej.zzcfb);
        if (zzaej.zzbsu != -1) {
        }
        if (zzaej.zzcer != -1) {
        }
        if (!TextUtils.isEmpty(zzaej.zzamj)) {
        }
        jSONObject.put("fluid", !zzaej.zzarf ? "height" : "");
        jSONObject.put("native_express", zzaej.zzarg);
        if (zzaej.zzcff != null) {
        }
        if (zzaej.zzcfg != null) {
        }
        if (zzaej.zzcfe != null) {
        }
        jSONObject.put("use_displayed_impression", zzaej.zzcfh);
        jSONObject.put("auto_protection_configuration", zzaej.zzcfi);
        jSONObject.put("render_in_browser", zzaej.zzbss);
        jSONObject.put("disable_closable_area", zzaej.zzzm);
        return jSONObject;
    }

    private static JSONArray zzm(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String str : list) {
            jSONArray.put(str);
        }
        return jSONArray;
    }

    private static Integer zzv(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }
}
