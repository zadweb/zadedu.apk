package com.appnext.base.operations.imp;

import android.os.Build;
import android.os.Bundle;
import com.appnext.base.a.a;
import com.appnext.base.a.b.b;
import com.appnext.base.b.d;
import com.appnext.base.b.e;
import com.appnext.base.b.i;
import com.appnext.base.b.j;
import com.appnext.base.operations.c;
import com.appnext.core.f;
import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class cdm extends c {
    private final String TAG = "cdm";
    private final String ey = "[ { \"status\": \"on\", \"sample\":\"1\", \"sample_type\":\"hour\", \"cycle\":\"1\", \"cycle_type\":\"interval\", \"key\":\"cdm\" } ]";

    /* access modifiers changed from: protected */
    @Override // com.appnext.base.operations.a
    public final boolean aA() {
        return false;
    }

    @Override // com.appnext.base.operations.a
    public final boolean aF() {
        return true;
    }

    public cdm(com.appnext.base.a.b.c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
        i.aR().init(e.getContext());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x003c */
    @Override // com.appnext.base.operations.a
    public List<b> getData() {
        String str = "[ { \"status\": \"on\", \"sample\":\"1\", \"sample_type\":\"hour\", \"cycle\":\"1\", \"cycle_type\":\"interval\", \"key\":\"cdm\" } ]";
        if (j.i(e.getContext())) {
            return null;
        }
        try {
            str = f.a("http://cdn.appnext.com/tools/services/4.7.2/config.json?packageId=" + e.getContext().getPackageName() + "&dosv=" + Build.VERSION.SDK_INT + "&lvid=4.7.2", (HashMap<String, String>) null);
        } catch (HttpRetryException e) {
            e.responseCode();
        } catch (Throwable unknown) {
        }
        try {
            List<com.appnext.base.a.b.c> as = a.X().ab().as();
            if (as != null) {
                com.appnext.base.services.b.a.d(e.getContext()).h(as);
            }
            for (com.appnext.base.a.b.c cVar : C(str)) {
                com.appnext.base.services.b.a.d(e.getContext()).a(cVar, false);
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    private static List<com.appnext.base.a.b.c> C(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.getString("status").equals(d.fe)) {
                    jSONObject.put("service_key", jSONObject.optString("key") + System.currentTimeMillis());
                    arrayList.add(com.appnext.base.b.c.c(jSONObject));
                }
            }
            a.X().ab().delete();
            a.X().ab().b(jSONArray);
            return arrayList;
        } catch (Throwable th) {
            th.getMessage();
            return new ArrayList();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.appnext.base.operations.a
    public final String getKey() {
        return cdm.class.getSimpleName();
    }
}
