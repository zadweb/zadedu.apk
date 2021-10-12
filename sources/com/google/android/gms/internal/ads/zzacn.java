package com.google.android.gms.internal.ads;

import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzacn implements zzacd<zzoo> {
    private final boolean zzbto;
    private final boolean zzcbk;
    private final boolean zzcbl;

    public zzacn(boolean z, boolean z2, boolean z3) {
        this.zzcbk = z;
        this.zzcbl = z2;
        this.zzbto = z3;
    }

    /* Return type fixed from 'com.google.android.gms.internal.ads.zzpb' to match base method */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00ed  */
    @Override // com.google.android.gms.internal.ads.zzacd
    public final /* synthetic */ zzoo zza(zzabv zzabv, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        String str;
        List<zzanz<zzon>> zza = zzabv.zza(jSONObject, "images", false, this.zzcbk, this.zzcbl);
        zzanz<zzon> zza2 = zzabv.zza(jSONObject, "app_icon", true, this.zzcbk);
        zzanz<zzaqw> zzc = zzabv.zzc(jSONObject, "video");
        zzanz<zzoj> zzg = zzabv.zzg(jSONObject);
        ArrayList arrayList = new ArrayList();
        for (zzanz<zzon> zzanz : zza) {
            arrayList.add((zzon) zzanz.get());
        }
        zzaqw zzc2 = zzabv.zzc(zzc);
        String string = jSONObject.getString("headline");
        if (this.zzbto) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbfr)).booleanValue()) {
                Resources resources = zzbv.zzeo().getResources();
                str = resources != null ? resources.getString(R.string.s7) : "Test Ad";
                if (string != null) {
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(string).length());
                    sb.append(str);
                    sb.append(" : ");
                    sb.append(string);
                    string = sb.toString();
                }
                return new zzoo(str, arrayList, jSONObject.getString("body"), (zzpw) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.optDouble("rating", -1.0d), jSONObject.optString("store"), jSONObject.optString("price"), (zzoj) zzg.get(), new Bundle(), zzc2 == null ? zzc2.zztm() : null, zzc2 == null ? zzc2.getView() : null, null, null);
            }
        }
        str = string;
        return new zzoo(str, arrayList, jSONObject.getString("body"), (zzpw) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.optDouble("rating", -1.0d), jSONObject.optString("store"), jSONObject.optString("price"), (zzoj) zzg.get(), new Bundle(), zzc2 == null ? zzc2.zztm() : null, zzc2 == null ? zzc2.getView() : null, null, null);
    }
}
