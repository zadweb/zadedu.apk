package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.yandex.metrica.c;
import com.yandex.metrica.impl.a;
import com.yandex.metrica.impl.ak;
import com.yandex.metrica.impl.ap;
import com.yandex.metrica.impl.ob.bl;
import com.yandex.metrica.impl.ob.bn;
import com.yandex.metrica.impl.ob.cq;
import com.yandex.metrica.impl.ob.ct;
import com.yandex.metrica.impl.ob.d;
import com.yandex.metrica.impl.ob.ee;
import com.yandex.metrica.impl.ob.ef;
import com.yandex.metrica.impl.ob.eg;
import com.yandex.metrica.impl.ob.eh;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.utils.e;
import com.yandex.metrica.impl.utils.f;
import com.yandex.metrica.impl.utils.j;
import com.yandex.metrica.impl.utils.m;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class at extends l {
    c.a m;
    ba n;
    bn o;
    t p;
    List<Long> q;
    int r = 0;
    int s = -1;
    private c t;
    private final f u = new f();
    private boolean v;

    /* access modifiers changed from: protected */
    public boolean a(long j) {
        return -2 == j;
    }

    public at(t tVar) {
        this.p = tVar;
        this.o = tVar.i();
        this.n = tVar.h();
        this.r = com.yandex.metrica.impl.ob.b.b(1, ap.a(Long.valueOf(System.currentTimeMillis() / 1000), Long.valueOf(m.a())));
    }

    /* access modifiers changed from: package-private */
    public void v() {
        Uri.Builder buildUpon = Uri.parse(this.n.C()).buildUpon();
        buildUpon.path("report");
        buildUpon.appendQueryParameter("deviceid", bi.c(this.c.c(), this.n.c()));
        buildUpon.appendQueryParameter("uuid", bi.c(this.c.b(), this.n.b()));
        buildUpon.appendQueryParameter("analytics_sdk_version", bi.c(this.c.h(), this.n.h()));
        buildUpon.appendQueryParameter("client_analytics_sdk_version", bi.c(this.c.i(), this.n.i()));
        buildUpon.appendQueryParameter("app_version_name", bi.c(this.c.x(), this.n.x()));
        buildUpon.appendQueryParameter("app_build_number", bi.c(this.c.z(), this.n.z()));
        buildUpon.appendQueryParameter("os_version", bi.c(this.c.q(), this.n.q()));
        if (this.c.r() > 0) {
            buildUpon.appendQueryParameter("os_api_level", String.valueOf(this.c.r()));
        }
        if (!TextUtils.isEmpty(this.c.k())) {
            buildUpon.appendQueryParameter("analytics_sdk_build_number", this.c.k());
        }
        if (!TextUtils.isEmpty(this.c.l())) {
            buildUpon.appendQueryParameter("analytics_sdk_build_type", this.c.l());
        }
        if (!TextUtils.isEmpty(this.c.N())) {
            buildUpon.appendQueryParameter("app_debuggable", this.c.N());
        }
        buildUpon.appendQueryParameter("locale", bi.c(this.c.w(), this.n.w()));
        buildUpon.appendQueryParameter("is_rooted", bi.c(this.c.F(), this.n.F()));
        buildUpon.appendQueryParameter("app_framework", bi.c(this.c.d(), this.n.d()));
        buildUpon.appendQueryParameter(this.n.j() >= 200 ? "api_key_128" : "api_key", y());
        buildUpon.appendQueryParameter("app_id", this.p.l().b());
        buildUpon.appendQueryParameter("app_platform", this.n.m());
        buildUpon.appendQueryParameter("protocol_version", this.n.f());
        buildUpon.appendQueryParameter("model", this.n.p());
        buildUpon.appendQueryParameter("manufacturer", this.n.o());
        buildUpon.appendQueryParameter("screen_width", String.valueOf(this.n.s()));
        buildUpon.appendQueryParameter("screen_height", String.valueOf(this.n.t()));
        buildUpon.appendQueryParameter("screen_dpi", String.valueOf(this.n.u()));
        buildUpon.appendQueryParameter("scalefactor", String.valueOf(this.n.v()));
        buildUpon.appendQueryParameter("device_type", this.n.G());
        buildUpon.appendQueryParameter("android_id", this.n.n());
        String a2 = this.n.a(this.p.m());
        if (!TextUtils.isEmpty(a2)) {
            buildUpon.appendQueryParameter("adv_id", a2);
        }
        String y = this.n.y();
        if (!TextUtils.isEmpty(y)) {
            buildUpon.appendQueryParameter("clids_set", y);
        }
        a(buildUpon.build().toString());
    }

    /* access modifiers changed from: package-private */
    public c.a a(c cVar, c.a.C0040c[] cVarArr) {
        c.a aVar = new c.a();
        a(aVar);
        aVar.c = (c.a.d[]) cVar.f968a.toArray(new c.a.d[cVar.f968a.size()]);
        aVar.d = a(cVar.c);
        aVar.e = cVarArr;
        this.r += com.yandex.metrica.impl.ob.b.g(8);
        return aVar;
    }

    /* access modifiers changed from: package-private */
    public void a(final c.a aVar) {
        ef.a(this.p.m()).a(new eh() {
            /* class com.yandex.metrica.impl.at.AnonymousClass1 */

            @Override // com.yandex.metrica.impl.ob.eh
            public void a(eg egVar) {
                c.a aVar = aVar;
                List<String> c = egVar.c();
                if (!bk.a(c)) {
                    aVar.f = new String[c.size()];
                    for (int i = 0; i < c.size(); i++) {
                        String str = c.get(i);
                        if (!TextUtils.isEmpty(str)) {
                            aVar.f[i] = str;
                            at.this.r += com.yandex.metrica.impl.ob.b.b(aVar.f[i]);
                            at.this.r += com.yandex.metrica.impl.ob.b.g(9);
                        }
                    }
                }
                c.a aVar2 = aVar;
                List<ee> a2 = egVar.a();
                if (!bk.a(a2)) {
                    aVar2.g = new c.a.e[a2.size()];
                    for (int i2 = 0; i2 < a2.size(); i2++) {
                        aVar2.g[i2] = ap.a(a2.get(i2));
                        at.this.r += com.yandex.metrica.impl.ob.b.b(aVar2.g[i2]);
                        at.this.r += com.yandex.metrica.impl.ob.b.g(10);
                    }
                }
            }
        });
    }

    @Override // com.yandex.metrica.impl.ak
    public boolean b() {
        if (!this.n.M()) {
            return false;
        }
        this.q = null;
        this.v = this.p.H();
        c.a.C0040c[] w = w();
        c x = x();
        this.t = x;
        if (x.f968a.isEmpty()) {
            return false;
        }
        this.m = a(this.t, w);
        v();
        this.q = this.t.b;
        return true;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0051 */
    @Override // com.yandex.metrica.impl.ak
    public void e() {
        Throwable th;
        super.e();
        this.m.b = ap.a(Long.valueOf(System.currentTimeMillis() / 1000), Long.valueOf(m.a()));
        byte[] a2 = d.a(this.m);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = null;
        GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
        try {
            gZIPOutputStream2.write(a2, 0, a2.length);
            gZIPOutputStream2.finish();
            a(byteArrayOutputStream.toByteArray());
            b("gzip");
            bk.a(byteArrayOutputStream);
            bk.a(gZIPOutputStream2);
        } catch (Exception unused) {
            gZIPOutputStream = gZIPOutputStream2;
            try {
                a(a2);
                b("identity");
                bk.a(byteArrayOutputStream);
                bk.a(gZIPOutputStream);
            } catch (Throwable th2) {
                th = th2;
                bk.a(byteArrayOutputStream);
                bk.a(gZIPOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream = gZIPOutputStream2;
            bk.a(byteArrayOutputStream);
            bk.a(gZIPOutputStream);
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public c.a.C0040c[] w() {
        c.a.C0040c[] a2 = ap.a(this.p.m());
        if (a2 != null) {
            int length = a2.length;
            for (int i = 0; i < length; i++) {
                this.r += com.yandex.metrica.impl.ob.b.b(a2[i]);
            }
        }
        return a2;
    }

    private static c.a.C0039a[] a(JSONObject jSONObject) {
        int length = jSONObject.length();
        if (length <= 0) {
            return null;
        }
        c.a.C0039a[] aVarArr = new c.a.C0039a[length];
        Iterator<String> keys = jSONObject.keys();
        int i = 0;
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                c.a.C0039a aVar = new c.a.C0039a();
                aVar.b = next;
                aVar.c = jSONObject.getString(next);
                aVarArr[i] = aVar;
            } catch (JSONException unused) {
            }
            i++;
        }
        return aVarArr;
    }

    @Override // com.yandex.metrica.impl.ak
    public boolean c() {
        boolean z = true;
        this.k = k() == 200;
        boolean z2 = k() == 400;
        if (!this.k && !z2) {
            z = false;
        }
        if (z) {
            c.a.d[] dVarArr = this.m.c;
            for (int i = 0; i < dVarArr.length; i++) {
                c.a.d dVar = dVarArr[i];
                this.o.a(this.q.get(i).longValue(), ap.a(dVar.c.d).a(), dVar.d.length);
                ap.a();
            }
            this.o.a(this.p.a().c());
        }
        return this.k;
    }

    /* access modifiers changed from: package-private */
    @Override // com.yandex.metrica.impl.ak
    public cq d() {
        return new ct().a(h());
    }

    @Override // com.yandex.metrica.impl.ak
    public void f() {
        if (this.k) {
            j p2 = this.p.p();
            if (p2.b()) {
                for (int i = 0; i < this.t.f968a.size(); i++) {
                    p2.a(this.t.f968a.get(i), "Event sent");
                }
            }
        }
        this.t = null;
    }

    @Override // com.yandex.metrica.impl.ak
    public boolean o() {
        boolean z = true;
        boolean z2 = (!r()) & (q() < 3);
        if (400 == k()) {
            z = false;
        }
        return z2 & z;
    }

    @Override // com.yandex.metrica.impl.ak
    public long p() {
        return (q() + 1) % 3 != 0 ? ak.a.f958a : ak.a.b;
    }

    /* access modifiers changed from: protected */
    public c x() {
        Throwable th;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        Cursor cursor = null;
        a.C0046a aVar = null;
        Cursor cursor2 = null;
        try {
            Cursor z = z();
            while (z.moveToNext()) {
                try {
                    ContentValues contentValues = new ContentValues();
                    e.a(z, contentValues);
                    long longValue = contentValues.getAsLong("id").longValue();
                    bl a2 = bl.a(contentValues.getAsInteger("type"));
                    if (!a(longValue)) {
                        c.a.f a3 = ap.a(contentValues);
                        c.a.d.b a4 = ap.a(this.n.w(), ap.a(a2), a3);
                        int c2 = this.r + com.yandex.metrica.impl.ob.b.c(1, Long.MAX_VALUE);
                        this.r = c2;
                        int b2 = c2 + com.yandex.metrica.impl.ob.b.b(2, a4);
                        this.r = b2;
                        if (b2 < 250880) {
                            b a5 = a(longValue, a4);
                            if (a5 != null) {
                                if (aVar != null) {
                                    if (!aVar.equals(a5.b)) {
                                        break;
                                    }
                                } else {
                                    aVar = a5.b;
                                }
                                arrayList2.add(Long.valueOf(longValue));
                                arrayList.add(a5.f967a);
                                try {
                                    jSONObject = new JSONObject(a5.b.f939a);
                                } catch (JSONException unused) {
                                }
                                if (a5.c) {
                                    break;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            break;
                        }
                    }
                } catch (Exception unused2) {
                    cursor = z;
                    bk.a(cursor);
                    return new c(arrayList, arrayList2, jSONObject);
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = z;
                    bk.a(cursor2);
                    throw th;
                }
            }
            bk.a(z);
        } catch (Exception unused3) {
            bk.a(cursor);
            return new c(arrayList, arrayList2, jSONObject);
        } catch (Throwable th3) {
            th = th3;
            bk.a(cursor2);
            throw th;
        }
        return new c(arrayList, arrayList2, jSONObject);
    }

    private static int a(a.C0046a aVar) {
        try {
            c.a.C0039a[] a2 = a(new JSONObject(aVar.f939a));
            if (a2 == null) {
                return 0;
            }
            int i = 0;
            for (c.a.C0039a aVar2 : a2) {
                i += com.yandex.metrica.impl.ob.b.b(7, aVar2);
            }
            return i;
        } catch (JSONException unused) {
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x010b, code lost:
        r1 = r10;
        r12 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0165, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0166, code lost:
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0168, code lost:
        r12 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0165 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0015] */
    public b a(long j, c.a.d.b bVar) {
        a.C0046a aVar;
        c.a.d.C0041a aVar2;
        c.a.d dVar = new c.a.d();
        dVar.b = j;
        dVar.c = bVar;
        Cursor cursor = null;
        boolean z = false;
        try {
            Cursor a2 = a(j, ap.a(bVar.d));
            try {
                ArrayList arrayList = new ArrayList();
                aVar = null;
                while (true) {
                    if (!a2.moveToNext()) {
                        break;
                    }
                    ContentValues contentValues = new ContentValues();
                    e.a(a2, contentValues);
                    ap.b f = ap.b.a(contentValues.getAsInteger("type").intValue(), this.v).b(contentValues.getAsInteger("custom_type")).a(contentValues.getAsString("name")).b(contentValues.getAsString("value")).a(contentValues.getAsLong(com.appnext.base.b.d.fl).longValue()).a(contentValues.getAsInteger("number").intValue()).e(contentValues.getAsString("cell_info")).c(contentValues.getAsString("location_info")).d(contentValues.getAsString("wifi_network_info")).g(contentValues.getAsString("error_environment")).h(contentValues.getAsString("user_info")).b(contentValues.getAsInteger("truncated").intValue()).c(contentValues.getAsInteger("connection_type").intValue()).i(contentValues.getAsString("cellular_connection_type")).f(contentValues.getAsString("wifi_access_point"));
                    if (f.c() != null) {
                        aVar2 = f.e();
                    } else {
                        aVar2 = null;
                    }
                    if (aVar2 != null) {
                        a.C0046a aVar3 = new a.C0046a(contentValues.getAsString("app_environment"), contentValues.getAsLong("app_environment_revision").longValue());
                        if (aVar != null) {
                            if (!aVar.equals(aVar3)) {
                                z = true;
                                break;
                            }
                        } else {
                            if (this.s < 0) {
                                int a3 = a(aVar3);
                                this.s = a3;
                                this.r += a3;
                            }
                            aVar = aVar3;
                        }
                        byte[] a4 = this.u.a(aVar2.f, 245760);
                        if (!aVar2.f.equals(a4)) {
                            aVar2.f = a4;
                            aVar2.k += aVar2.f.length - a4.length;
                        }
                        int b2 = this.r + com.yandex.metrica.impl.ob.b.b(3, aVar2);
                        this.r = b2;
                        if (b2 >= 250880) {
                            break;
                        }
                        arrayList.add(aVar2);
                    }
                }
                if (arrayList.size() > 0) {
                    dVar.d = (c.a.d.C0041a[]) arrayList.toArray(new c.a.d.C0041a[arrayList.size()]);
                    bk.a(a2);
                    return new b(dVar, aVar, z);
                }
                bk.a(a2);
                return null;
            } catch (Exception unused) {
                cursor = a2;
                bk.a(cursor);
                return new b(dVar, aVar, z);
            } catch (Throwable th) {
            }
        } catch (Exception unused2) {
            aVar = null;
            bk.a(cursor);
            return new b(dVar, aVar, z);
        } catch (Throwable th2) {
            Throwable th3 = th2;
            bk.a(cursor);
            throw th3;
        }
    }

    /* access modifiers changed from: protected */
    public String y() {
        return this.n.a();
    }

    /* access modifiers changed from: protected */
    public Cursor z() {
        return this.o.a(this.b);
    }

    /* access modifiers changed from: protected */
    public Cursor a(long j, bl blVar) {
        return this.o.b(j, blVar);
    }

    /* access modifiers changed from: package-private */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        final List<c.a.d> f968a;
        final List<Long> b;
        final JSONObject c;

        c(List<c.a.d> list, List<Long> list2, JSONObject jSONObject) {
            this.f968a = list;
            this.b = list2;
            this.c = jSONObject;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        final c.a.d f967a;
        final a.C0046a b;
        final boolean c;

        b(c.a.d dVar, a.C0046a aVar, boolean z) {
            this.f967a = dVar;
            this.b = aVar;
            this.c = z;
        }
    }

    public static a A() {
        return new a();
    }

    /* access modifiers changed from: package-private */
    public static class a {
        a() {
        }

        /* access modifiers changed from: package-private */
        public at a(t tVar) {
            return new at(tVar);
        }
    }
}
