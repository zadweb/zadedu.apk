package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.appnext.base.b.i;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.mopub.network.ImpressionData;
import com.yandex.metrica.impl.a;
import com.yandex.metrica.impl.ob.dz;
import com.yandex.metrica.impl.ob.ea;
import com.yandex.metrica.impl.ob.ef;
import com.yandex.metrica.impl.ob.eg;
import com.yandex.metrica.impl.ob.eh;
import com.yandex.metrica.impl.ob.u;
import org.json.JSONArray;
import org.json.JSONObject;

public final class k {

    /* renamed from: a  reason: collision with root package name */
    private Context f1009a;
    private ContentValues b;
    private u c;

    public k(Context context) {
        this.f1009a = context;
    }

    public k a(ContentValues contentValues) {
        this.b = contentValues;
        return this;
    }

    public k a(u uVar) {
        this.c = uVar;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Location b() {
        if (!this.c.j().m()) {
            return null;
        }
        Location t = this.c.j().t();
        if (t != null) {
            return t;
        }
        Location c2 = y.a(this.f1009a).c();
        return c2 == null ? y.a(this.f1009a).d() : c2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x00a9 A[SYNTHETIC, Splitter:B:12:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0176  */
    public void a(h hVar, a.C0046a aVar) {
        Location b2;
        JSONArray g;
        JSONArray a2;
        NetworkInfo activeNetworkInfo;
        this.b.put("name", hVar.a());
        this.b.put("value", hVar.b());
        this.b.put("type", Integer.valueOf(hVar.c()));
        this.b.put("custom_type", Integer.valueOf(hVar.d()));
        this.b.put("error_environment", hVar.i());
        this.b.put("user_info", hVar.k());
        this.b.put("truncated", Integer.valueOf(hVar.o()));
        ContentValues contentValues = this.b;
        Context context = this.f1009a;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        int i = 1;
        if (al.a(context, "android.permission.ACCESS_NETWORK_STATE") && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
            if (activeNetworkInfo.getType() != 1) {
                if (activeNetworkInfo.getType() == 0) {
                    i = 0;
                }
            }
            contentValues.put("connection_type", Integer.valueOf(i));
            this.b.put("app_environment", aVar.f939a);
            this.b.put("app_environment_revision", Long.valueOf(aVar.b));
            b2 = b();
            if (b2 != null) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(i.fC, b2.getLatitude());
                    jSONObject.put("lon", b2.getLongitude());
                    jSONObject.putOpt(AvidJSONUtil.KEY_TIMESTAMP, Long.valueOf(b2.getTime()));
                    jSONObject.putOpt(ImpressionData.PRECISION, b2.hasAccuracy() ? Float.valueOf(b2.getAccuracy()) : null);
                    jSONObject.putOpt("direction", b2.hasBearing() ? Float.valueOf(b2.getBearing()) : null);
                    jSONObject.putOpt("speed", b2.hasSpeed() ? Float.valueOf(b2.getSpeed()) : null);
                    jSONObject.putOpt("altitude", b2.hasAltitude() ? Double.valueOf(b2.getAltitude()) : null);
                    jSONObject.putOpt("provider", bi.c(b2.getProvider(), null));
                    this.b.put("location_info", jSONObject.toString());
                } catch (Exception unused) {
                }
            }
            ef a3 = ef.a(this.f1009a);
            a3.a(new eh() {
                /* class com.yandex.metrica.impl.k.AnonymousClass2 */

                @Override // com.yandex.metrica.impl.ob.eh
                public void a(eg egVar) {
                    k.this.b.put("cellular_connection_type", egVar.b().g());
                }
            });
            a3.a(new ea() {
                /* class com.yandex.metrica.impl.k.AnonymousClass1 */

                @Override // com.yandex.metrica.impl.ob.ea
                public void a(dz[] dzVarArr) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        for (dz dzVar : dzVarArr) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.putOpt("cell_id", dzVar.e());
                            jSONObject.putOpt("signal_strength", dzVar.a());
                            jSONObject.putOpt("lac", dzVar.d());
                            jSONObject.putOpt("country_code", dzVar.b());
                            jSONObject.putOpt("operator_id", dzVar.c());
                            jSONObject.putOpt("operator_name", dzVar.f());
                            jSONObject.putOpt("is_connected", Boolean.valueOf(dzVar.h()));
                            jSONObject.putOpt("cell_type", Integer.valueOf(dzVar.i()));
                            jSONObject.putOpt("pci", dzVar.j());
                            jSONArray.put(jSONObject);
                        }
                        k.this.b.put("cell_info", jSONArray.toString());
                    } catch (Exception unused) {
                    }
                }
            });
            bm a4 = bm.a(this.f1009a);
            g = hVar.g();
            a2 = a4.a();
            if (a2.length() <= g.length()) {
                this.b.put("wifi_network_info", a2.toString());
            } else {
                this.b.put("wifi_network_info", g.toString());
            }
            a(a4);
        }
        i = 2;
        contentValues.put("connection_type", Integer.valueOf(i));
        this.b.put("app_environment", aVar.f939a);
        this.b.put("app_environment_revision", Long.valueOf(aVar.b));
        b2 = b();
        if (b2 != null) {
        }
        ef a32 = ef.a(this.f1009a);
        a32.a(new eh() {
            /* class com.yandex.metrica.impl.k.AnonymousClass2 */

            @Override // com.yandex.metrica.impl.ob.eh
            public void a(eg egVar) {
                k.this.b.put("cellular_connection_type", egVar.b().g());
            }
        });
        a32.a(new ea() {
            /* class com.yandex.metrica.impl.k.AnonymousClass1 */

            @Override // com.yandex.metrica.impl.ob.ea
            public void a(dz[] dzVarArr) {
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (dz dzVar : dzVarArr) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.putOpt("cell_id", dzVar.e());
                        jSONObject.putOpt("signal_strength", dzVar.a());
                        jSONObject.putOpt("lac", dzVar.d());
                        jSONObject.putOpt("country_code", dzVar.b());
                        jSONObject.putOpt("operator_id", dzVar.c());
                        jSONObject.putOpt("operator_name", dzVar.f());
                        jSONObject.putOpt("is_connected", Boolean.valueOf(dzVar.h()));
                        jSONObject.putOpt("cell_type", Integer.valueOf(dzVar.i()));
                        jSONObject.putOpt("pci", dzVar.j());
                        jSONArray.put(jSONObject);
                    }
                    k.this.b.put("cell_info", jSONArray.toString());
                } catch (Exception unused) {
                }
            }
        });
        bm a42 = bm.a(this.f1009a);
        g = hVar.g();
        a2 = a42.a();
        if (a2.length() <= g.length()) {
        }
        a(a42);
    }

    /* access modifiers changed from: package-private */
    public void a(bm bmVar) {
        String b2 = bmVar.b(this.f1009a);
        if (!TextUtils.isEmpty(b2)) {
            int c2 = bmVar.c(this.f1009a);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ssid", b2);
                jSONObject.put("state", c2);
                this.b.put("wifi_access_point", jSONObject.toString());
            } catch (Exception unused) {
            }
        }
    }

    public void a() {
        ba h = this.c.h();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("dId", h.c());
            jSONObject.putOpt("uId", h.b());
            jSONObject.putOpt("appVer", h.x());
            jSONObject.putOpt("appBuild", h.z());
            jSONObject.putOpt("kitVer", h.h());
            jSONObject.putOpt("clientKitVer", h.i());
            jSONObject.putOpt("kitBuildNumber", h.k());
            jSONObject.putOpt("kitBuildType", h.l());
            jSONObject.putOpt("osVer", h.q());
            jSONObject.putOpt("osApiLev", Integer.valueOf(h.r()));
            jSONObject.putOpt("lang", h.w());
            jSONObject.putOpt("root", h.F());
            jSONObject.putOpt("app_debuggable", h.N());
        } catch (Exception unused) {
            jSONObject = new JSONObject();
        }
        this.b.put("report_request_parameters", jSONObject.toString());
    }
}
