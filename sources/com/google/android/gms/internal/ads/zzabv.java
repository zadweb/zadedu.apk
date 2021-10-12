package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.ads.internal.zzbv;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.common.AdType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzabv implements Callable<zzajh> {
    private static long zzbzx = 10;
    private final Context mContext;
    private int mErrorCode;
    private final Object mLock = new Object();
    private final zzacm zzaad;
    private final zzci zzbjc;
    private final zzaji zzbze;
    private final zzalt zzbzy;
    private final zzbc zzbzz;
    private boolean zzcaa;
    private List<String> zzcab;
    private JSONObject zzcac;
    private String zzcad;
    private String zzcae;
    private final zznx zzvr;

    public zzabv(Context context, zzbc zzbc, zzalt zzalt, zzci zzci, zzaji zzaji, zznx zznx) {
        this.mContext = context;
        this.zzbzz = zzbc;
        this.zzbzy = zzalt;
        this.zzbze = zzaji;
        this.zzbjc = zzci;
        this.zzvr = zznx;
        this.zzaad = zzbc.zzdr();
        this.zzcaa = false;
        this.mErrorCode = -2;
        this.zzcab = null;
        this.zzcad = null;
        this.zzcae = null;
    }

    private final zzajh zza(zzpb zzpb, boolean z) {
        int i;
        synchronized (this.mLock) {
            i = (zzpb == null && this.mErrorCode == -2) ? 0 : this.mErrorCode;
        }
        return new zzajh(this.zzbze.zzcgs.zzccv, null, this.zzbze.zzcos.zzbsn, i, this.zzbze.zzcos.zzbso, this.zzcab, this.zzbze.zzcos.orientation, this.zzbze.zzcos.zzbsu, this.zzbze.zzcgs.zzccy, false, null, null, null, null, null, 0, this.zzbze.zzacv, this.zzbze.zzcos.zzcep, this.zzbze.zzcoh, this.zzbze.zzcoi, this.zzbze.zzcos.zzcev, this.zzcac, i != -2 ? null : zzpb, null, null, null, this.zzbze.zzcos.zzcfh, this.zzbze.zzcos.zzcfi, null, this.zzbze.zzcos.zzbsr, this.zzcad, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, z, this.zzbze.zzcos.zzbsp, this.zzbze.zzcos.zzzm, this.zzcae);
    }

    private final zzanz<zzon> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString("url") : jSONObject.optString("url");
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (!TextUtils.isEmpty(string)) {
            return z2 ? zzano.zzi(new zzon(null, Uri.parse(string), optDouble)) : this.zzbzy.zza(string, new zzacb(this, z, optDouble, optBoolean, string));
        }
        zzd(0, z);
        return zzano.zzi(null);
    }

    private final void zzab(int i) {
        synchronized (this.mLock) {
            this.zzcaa = true;
            this.mErrorCode = i;
        }
    }

    private static zzaqw zzb(zzanz<zzaqw> zzanz) {
        try {
            return (zzaqw) zzanz.get((long) ((Integer) zzkb.zzik().zzd(zznk.zzbby)).intValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zzane.zzc("", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzane.zzc("", e2);
            return null;
        }
    }

    private static Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException unused) {
            return null;
        }
    }

    static zzaqw zzc(zzanz<zzaqw> zzanz) {
        try {
            return (zzaqw) zzanz.get((long) ((Integer) zzkb.zzik().zzd(zznk.zzbbx)).intValue(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            zzakb.zzc("InterruptedException occurred while waiting for video to load", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzakb.zzc("Exception occurred while waiting for video to load", e2);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final void zzc(zzqs zzqs, String str) {
        try {
            zzrc zzr = this.zzbzz.zzr(zzqs.getCustomTemplateId());
            if (zzr != null) {
                zzr.zzb(zzqs, str);
            }
        } catch (RemoteException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40);
            sb.append("Failed to call onCustomClick for asset ");
            sb.append(str);
            sb.append(".");
            zzakb.zzc(sb.toString(), e);
        }
    }

    /* access modifiers changed from: private */
    public static <V> List<V> zzk(List<zzanz<V>> list) throws ExecutionException, InterruptedException {
        ArrayList arrayList = new ArrayList();
        for (zzanz<V> zzanz : list) {
            Object obj = zzanz.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        if (r4.length() != 0) goto L_0x0046;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x014e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01f0  */
    /* renamed from: zznw */
    public final zzajh call() {
        String str;
        Throwable e;
        JSONObject jSONObject;
        zzany zzany;
        zzacd zzacd;
        zzpb zzpb;
        String[] strArr;
        String string;
        JSONObject optJSONObject;
        String optString;
        try {
            String uuid = this.zzbzz.getUuid();
            if (!zznx()) {
                JSONObject jSONObject2 = new JSONObject(this.zzbze.zzcos.zzceo);
                JSONObject jSONObject3 = new JSONObject(this.zzbze.zzcos.zzceo);
                if (jSONObject3.length() != 0) {
                    JSONArray optJSONArray = jSONObject3.optJSONArray("ads");
                    JSONObject optJSONObject2 = optJSONArray != null ? optJSONArray.optJSONObject(0) : null;
                    if (optJSONObject2 != null) {
                    }
                }
                zzab(3);
                JSONObject jSONObject4 = (JSONObject) this.zzaad.zzh(jSONObject2).get(zzbzx, TimeUnit.SECONDS);
                if (jSONObject4.optBoolean("success", false)) {
                    jSONObject = jSONObject4.getJSONObject(AdType.STATIC_NATIVE).optJSONArray("ads").getJSONObject(0);
                    boolean optBoolean = jSONObject.optBoolean("enable_omid");
                    if (optBoolean && (optJSONObject = jSONObject.optJSONObject("omid_settings")) != null) {
                        optString = optJSONObject.optString("omid_html");
                        if (!TextUtils.isEmpty(optString)) {
                            zzaoj zzaoj = new zzaoj();
                            zzaoe.zzcvy.execute(new zzabw(this, zzaoj, optString));
                            zzany = zzaoj;
                            if (!zznx() && jSONObject != null) {
                                string = jSONObject.getString("template_id");
                                boolean z = this.zzbze.zzcgs.zzadj != null ? this.zzbze.zzcgs.zzadj.zzbjn : false;
                                boolean z2 = this.zzbze.zzcgs.zzadj != null ? this.zzbze.zzcgs.zzadj.zzbjp : false;
                                if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(string)) {
                                    zzacd = new zzacn(z, z2, this.zzbze.zzcor);
                                } else if ("1".equals(string)) {
                                    zzacd = new zzaco(z, z2, this.zzbze.zzcor);
                                } else if ("3".equals(string)) {
                                    String string2 = jSONObject.getString("custom_template_id");
                                    zzaoj zzaoj2 = new zzaoj();
                                    zzakk.zzcrm.post(new zzaby(this, zzaoj2, string2));
                                    if (zzaoj2.get(zzbzx, TimeUnit.SECONDS) != null) {
                                        zzacd = new zzacp(z);
                                    } else {
                                        String valueOf = String.valueOf(jSONObject.getString("custom_template_id"));
                                        zzakb.e(valueOf.length() != 0 ? "No handler for custom template: ".concat(valueOf) : new String("No handler for custom template: "));
                                    }
                                } else {
                                    zzab(0);
                                }
                                if (!zznx() || zzacd == null || jSONObject == null) {
                                    zzpb = null;
                                } else {
                                    JSONObject jSONObject5 = jSONObject.getJSONObject("tracking_urls_and_actions");
                                    JSONArray optJSONArray2 = jSONObject5.optJSONArray("impression_tracking_urls");
                                    if (optJSONArray2 == null) {
                                        strArr = null;
                                    } else {
                                        strArr = new String[optJSONArray2.length()];
                                        for (int i = 0; i < optJSONArray2.length(); i++) {
                                            strArr[i] = optJSONArray2.getString(i);
                                        }
                                    }
                                    this.zzcab = strArr == null ? null : Arrays.asList(strArr);
                                    this.zzcac = jSONObject5.optJSONObject("active_view");
                                    this.zzcad = jSONObject.optString("debug_signals");
                                    this.zzcae = jSONObject.optString("omid_settings");
                                    zzpb = zzacd.zza(this, jSONObject);
                                    zzpb.zzb(new zzpd(this.mContext, this.zzbzz, this.zzaad, this.zzbjc, jSONObject, zzpb, this.zzbze.zzcgs.zzacr, uuid));
                                }
                                if (zzpb instanceof zzos) {
                                    this.zzaad.zza("/nativeAdCustomClick", new zzabz(this, (zzos) zzpb));
                                }
                                zzajh zza = zza(zzpb, optBoolean);
                                this.zzbzz.zzg(zzb(zzany));
                                return zza;
                            }
                            zzacd = null;
                            if (!zznx()) {
                            }
                            zzpb = null;
                            if (zzpb instanceof zzos) {
                            }
                            zzajh zza2 = zza(zzpb, optBoolean);
                            this.zzbzz.zzg(zzb(zzany));
                            return zza2;
                        }
                    }
                    zzany = zzano.zzi(null);
                    string = jSONObject.getString("template_id");
                    if (this.zzbze.zzcgs.zzadj != null) {
                    }
                    if (this.zzbze.zzcgs.zzadj != null) {
                    }
                    if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(string)) {
                    }
                    if (!zznx()) {
                    }
                    zzpb = null;
                    if (zzpb instanceof zzos) {
                    }
                    zzajh zza22 = zza(zzpb, optBoolean);
                    this.zzbzz.zzg(zzb(zzany));
                    return zza22;
                }
            }
            jSONObject = null;
            boolean optBoolean2 = jSONObject.optBoolean("enable_omid");
            optString = optJSONObject.optString("omid_html");
            if (!TextUtils.isEmpty(optString)) {
            }
            zzany = zzano.zzi(null);
            string = jSONObject.getString("template_id");
            if (this.zzbze.zzcgs.zzadj != null) {
            }
            if (this.zzbze.zzcgs.zzadj != null) {
            }
            if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(string)) {
            }
            if (!zznx()) {
            }
            zzpb = null;
            if (zzpb instanceof zzos) {
            }
            zzajh zza222 = zza(zzpb, optBoolean2);
            this.zzbzz.zzg(zzb(zzany));
            return zza222;
        } catch (InterruptedException | CancellationException | ExecutionException | JSONException e2) {
            e = e2;
            str = "Malformed native JSON response.";
            zzakb.zzc(str, e);
            if (!this.zzcaa) {
                zzab(0);
            }
            return zza((zzpb) null, false);
        } catch (TimeoutException e3) {
            e = e3;
            str = "Timeout when loading native ad.";
            zzakb.zzc(str, e);
            if (!this.zzcaa) {
            }
            return zza((zzpb) null, false);
        } catch (Exception e4) {
            e = e4;
            str = "Error occured while doing native ads initialization.";
            zzakb.zzc(str, e);
            if (!this.zzcaa) {
            }
            return zza((zzpb) null, false);
        }
    }

    private final boolean zznx() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcaa;
        }
        return z;
    }

    public final zzanz<zzon> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public final List<zzanz<zzon>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            zzd(0, false);
            return arrayList;
        }
        int length = z3 ? optJSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, false, z2));
        }
        return arrayList;
    }

    public final Future<zzon> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzaoj zzaoj, String str) {
        try {
            zzbv.zzel();
            zzaqw zza = zzarc.zza(this.mContext, zzasi.zzvq(), "native-omid", false, false, this.zzbjc, this.zzbze.zzcgs.zzacr, this.zzvr, null, this.zzbzz.zzbi(), this.zzbze.zzcoq);
            zza.zzuf().zza(new zzabx(zzaoj, zza));
            zza.loadData(str, "text/html", "UTF-8");
        } catch (Exception e) {
            zzaoj.set(null);
            zzane.zzc("", e);
        }
    }

    public final zzanz<zzaqw> zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return zzano.zzi(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            zzakb.zzdk("Required field 'vast_xml' is missing");
            return zzano.zzi(null);
        }
        zzace zzace = new zzace(this.mContext, this.zzbjc, this.zzbze, this.zzvr, this.zzbzz);
        zzaoj zzaoj = new zzaoj();
        zzaoe.zzcvy.execute(new zzacf(zzace, optJSONObject, zzaoj));
        return zzaoj;
    }

    public final void zzd(int i, boolean z) {
        if (z) {
            zzab(i);
        }
    }

    public final zzanz<zzoj> zzg(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return zzano.zzi(null);
        }
        String optString = optJSONObject.optString("text");
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        int i = (this.zzbze.zzcgs.zzadj == null || this.zzbze.zzcgs.zzadj.versionCode < 2) ? 1 : this.zzbze.zzcgs.zzadj.zzbjq;
        boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List<zzanz<zzon>> arrayList = new ArrayList<>();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, "image", false, false));
        }
        zzaoj zzaoj = new zzaoj();
        int size = arrayList.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzanz<zzon> zzanz : arrayList) {
            zzanz.zza(new zzacc(atomicInteger, size, zzaoj, arrayList), zzaki.zzcrj);
            arrayList = arrayList;
        }
        return zzano.zza(zzaoj, new zzaca(this, optString, zzb2, zzb, optInt, optInt3, optInt2, i, optBoolean), zzaki.zzcrj);
    }
}
