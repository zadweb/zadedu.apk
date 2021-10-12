package com.appnext.core;

import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Pair;
import com.google.android.gms.common.internal.AccountType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class d {
    private static final int fP = 50;
    protected final int fQ = 0;
    protected final int fR = 1;
    protected final int fS = 2;
    protected final int fT = 3;
    private final HashMap<b, a> fU = new HashMap<>();

    public interface a {
        <T> void a(T t);

        void error(String str);
    }

    protected static int aV() {
        return 8000;
    }

    /* access modifiers changed from: protected */
    public abstract int a(Context context, g gVar);

    /* access modifiers changed from: protected */
    public abstract String a(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList);

    /* access modifiers changed from: protected */
    public abstract void a(Context context, Ad ad, a aVar) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void a(Ad ad, String str, String str2);

    /* access modifiers changed from: protected */
    public abstract <T> void a(String str, Ad ad, T t);

    /* access modifiers changed from: protected */
    public abstract boolean a(Context context, Ad ad, ArrayList<?> arrayList);

    /* access modifiers changed from: protected */
    public abstract p c(Ad ad);

    public final void a(Context context, Ad ad, String str, a aVar) {
        a(context, ad, str, aVar, true);
    }

    public final void a(final Context context, final Ad ad, final String str, final a aVar, final boolean z) {
        new Thread(new Runnable() {
            /* class com.appnext.core.d.AnonymousClass1 */

            public final void run() {
                try {
                    if (d.this.a(ad) || (d.this.h(ad) && d.this.i(ad))) {
                        d.this.a(context, ad, d.this.k(ad));
                    }
                } catch (Throwable unused) {
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    /* class com.appnext.core.d.AnonymousClass1.AnonymousClass1 */

                    public final void run() {
                        try {
                            if (d.this.a(ad) || (d.this.h(ad) && d.this.i(ad))) {
                                ArrayList<?> ads = d.this.k(ad).getAds();
                                if (ads.size() == 0) {
                                    aVar.error(AppnextError.NO_ADS);
                                    return;
                                } else if (!d.this.a(context, ad, ads)) {
                                    d.this.l(str);
                                } else if (aVar != null) {
                                    aVar.a(ads);
                                    return;
                                } else {
                                    return;
                                }
                            }
                        } catch (Throwable unused) {
                            if (aVar != null) {
                                aVar.error(AppnextError.NO_ADS);
                            }
                        }
                        try {
                            if (!f.bd().equals("")) {
                                d.this.b(context, ad, str, aVar, z);
                                return;
                            }
                            f.m(context);
                            d.this.b(context, ad, str, aVar, z);
                        } catch (Throwable unused2) {
                            if (aVar != null) {
                                aVar.error(AppnextError.NO_ADS);
                            }
                        }
                    }
                });
            }
        }).start();
    }

    private String b(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        StringBuilder sb = new StringBuilder("https://global.appnext.com/offerWallApi.aspx?ext=t&pimp=1&igroup=sdk&m=1&osid=100&s2s=1&type=json&id=");
        sb.append(str);
        sb.append("&cnt=50");
        sb.append("&tid=");
        sb.append(ad != null ? ad.getTID() : "301");
        sb.append("&vid=");
        sb.append(ad != null ? ad.getVID() : "2.5.1.472");
        sb.append("&cat=");
        String str2 = "";
        sb.append(ad != null ? ad.getCategories() : str2);
        sb.append("&pbk=");
        sb.append(ad != null ? ad.getPostback() : str2);
        sb.append("&did=");
        sb.append(f.b(context, Boolean.parseBoolean(c(ad).get("didPrivacy"))));
        sb.append("&devn=");
        sb.append(f.be());
        sb.append("&dosv=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("&dct=");
        sb.append(f.Z(f.o(context)));
        sb.append("&lang=");
        sb.append(Locale.getDefault().getLanguage());
        sb.append("&dcc=");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getSimState() == 5) {
            String simOperator = telephonyManager.getSimOperator();
            str2 = simOperator.substring(0, 3) + "_" + simOperator.substring(3);
        }
        sb.append(str2);
        sb.append("&dds=0");
        sb.append("&packageId=");
        sb.append(context.getPackageName());
        sb.append("&g=");
        sb.append(j(context));
        sb.append("&rnd=");
        sb.append(new Random().nextInt());
        return sb.toString();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(final Context context, final Ad ad, final String str, final a aVar, final boolean z) {
        new Thread() {
            /* class com.appnext.core.d.AnonymousClass2 */

            /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|14) */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x006b, code lost:
                r0 = 0;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
                r1 = new java.util.ArrayList<>();
                r2 = com.appnext.core.d.a(r7.fY, r9, r10, r11, r1) + r7.fY.a(r9, r10, r11, r1);
                new java.lang.StringBuilder("AdsManager request url: ").append(r2);
                r1 = com.appnext.core.f.a(r2, r1, 8000);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ae, code lost:
                if (r1.equals("{}") != false) goto L_0x013f;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b4, code lost:
                if (com.appnext.core.d.P(r1) == false) goto L_0x00b8;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
                r1 = r7.fY.a(r9, r10, r1, 50);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c8, code lost:
                if (r1.size() != 0) goto L_0x00d4;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ca, code lost:
                r7.fY.b(com.appnext.core.AppnextError.NO_ADS, r10);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d3, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d4, code lost:
                r7.fY.k(r10).d(r1);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e2, code lost:
                if (r13 == false) goto L_0x0113;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ee, code lost:
                if (r7.fY.a(r9, r10, r1) != false) goto L_0x00f7;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:35:0x00f0, code lost:
                r7.fY.l(r11);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x00f8, code lost:
                if (r0 >= 3) goto L_0x0113;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
                r7.fY.a(r9, r10, r7.fY.k(r10));
             */
            /* JADX WARNING: Code restructure failed: missing block: B:40:0x010c, code lost:
                r1 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:41:0x010d, code lost:
                if (r0 != 2) goto L_0x010f;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:42:0x010f, code lost:
                r0 = r0 + 1;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:43:0x0112, code lost:
                throw r1;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:44:0x0113, code lost:
                r7.fY.k(r10).setState(2);
                new android.os.Handler(android.os.Looper.getMainLooper()).post(new com.appnext.core.d.AnonymousClass2.AnonymousClass1(r7));
             */
            /* JADX WARNING: Code restructure failed: missing block: B:45:0x012f, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:46:0x0130, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:47:0x0131, code lost:
                r7.fY.a(com.appnext.core.AppnextError.INTERNAL_ERROR, com.appnext.core.f.b(r0), r10);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:48:0x013e, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:49:0x013f, code lost:
                r7.fY.a(com.appnext.core.AppnextError.NO_ADS, r1, r10);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:50:0x0148, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:51:0x0149, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:52:0x014a, code lost:
                r7.fY.a(com.appnext.core.AppnextError.INTERNAL_ERROR, com.appnext.core.f.b(r0), r10);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:53:0x0157, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:54:0x0158, code lost:
                r1 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:55:0x0159, code lost:
                r7.fY.a(com.appnext.core.AppnextError.CONNECTION_ERROR, com.appnext.core.f.b(r1), r10, 0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:56:0x0166, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:57:0x0167, code lost:
                r1 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:58:0x0168, code lost:
                r7.fY.a(com.appnext.core.AppnextError.TIMEOUT, com.appnext.core.f.b(r1), r10, 0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:59:0x0175, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:63:0x0179, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:64:0x017a, code lost:
                new java.lang.StringBuilder("finished custom after load with error ").append(com.appnext.core.f.b(r0));
                r7.fY.b(r0.getMessage(), r10);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:65:0x0193, code lost:
                return;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000e */
            /* JADX WARNING: Removed duplicated region for block: B:6:0x0015 A[SYNTHETIC] */
            public final void run() {
                super.run();
                ad.setSessionId(f.r(context));
                synchronized (d.this.fU) {
                    if (d.this.k(ad) == null || d.this.k(ad).getState() != 1) {
                        a aVar = new a();
                        aVar.a(aVar);
                        aVar.setPlacementID(str);
                        aVar.setState(1);
                        d.this.fU.remove(new b(ad));
                        d.this.a(ad, aVar);
                    } else {
                        if (aVar != null) {
                            d.this.k(ad).a(aVar);
                        }
                    }
                }
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    public boolean a(Ad ad) {
        try {
            return h(ad) && i(ad);
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public final boolean h(Ad ad) {
        return (this.fU == null || k(ad) == null || k(ad).getState() != 2 || k(ad).getAds() == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    public final boolean i(Ad ad) {
        return j(ad) == 0 ? this.fU != null && k(ad) != null && k(ad).getAds().size() == 0 && k(ad).aU().longValue() + 60000 > System.currentTimeMillis() : (this.fU == null || k(ad) == null || k(ad).aU().longValue() + j(ad) <= System.currentTimeMillis()) ? false : true;
    }

    /* access modifiers changed from: protected */
    public final long j(Ad ad) {
        try {
            return c(ad).get("_cachingRequest") == null ? a(ad, "ads_caching_time_minutes") * 60000 : a(ad, "_cachingRequest") * 1000;
        } catch (Throwable unused) {
            return a(ad, "ads_caching_time_minutes") * 60000;
        }
    }

    private long a(Ad ad, String str) {
        return Long.valueOf(c(ad).get(str)).longValue();
    }

    public final void c(Context context, Ad ad, String str) {
        this.fU.remove(new b(ad));
        b(context, ad, str, null, true);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x007f, code lost:
        if (r7.getRevenueType().equals("cpc") != false) goto L_0x0081;
     */
    public final ArrayList<? extends g> a(final Context context, Ad ad, String str, int i) throws JSONException {
        k(ad).N(str);
        ArrayList<? extends g> arrayList = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();
        JSONArray jSONArray = new JSONObject(str).getJSONArray("apps");
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < jSONArray.length(); i6++) {
            try {
                AppnextAd appnextAd = (AppnextAd) parseAd(jSONArray.getJSONObject(i6).toString());
                appnextAd.setAdID(arrayList.size());
                appnextAd.setPlacementID(ad.getPlacementID());
                int a2 = a(context, appnextAd);
                if (a2 == 0) {
                    AppnextAd b = b((ArrayList<AppnextAd>) arrayList, appnextAd);
                    if (b != null) {
                        arrayList.remove(b);
                        if (b.getRevenueType().equals(appnextAd.getRevenueType())) {
                            if (Float.parseFloat(b.getRevenueRate()) >= Float.parseFloat(appnextAd.getRevenueRate())) {
                            }
                            i5++;
                        }
                        appnextAd = b;
                        i5++;
                    } else {
                        i2++;
                    }
                    arrayList.add(appnextAd);
                } else {
                    sb.append(appnextAd.getBannerID());
                    sb.append(",");
                    if (a2 == 1) {
                        i3++;
                    } else if (a2 == 2) {
                        i4++;
                    } else if (a2 == 3) {
                        i5++;
                    }
                }
                if (arrayList.size() == 50) {
                    break;
                }
            } catch (Throwable unused) {
            }
        }
        StringBuilder sb2 = new StringBuilder("Filtering values {count = ");
        sb2.append(i2);
        sb2.append(", new filtered = ");
        sb2.append(i3);
        sb2.append(", existing  filtered = ");
        sb2.append(i4);
        sb2.append(",  other = ");
        sb2.append(i5);
        new Thread(new Runnable() {
            /* class com.appnext.core.d.AnonymousClass3 */

            public final void run() {
                try {
                    String sb = sb.toString();
                    if (sb.length() != 0) {
                        HashMap hashMap = new HashMap();
                        String b = f.b(context, true);
                        if (!b.equals("")) {
                            hashMap.put("aid", b);
                            hashMap.put("bids", sb.substring(0, sb.length() - 1));
                            new StringBuilder("https://admin.appnext.com/AdminService.asmx/bns - ").append(hashMap.toString());
                            f.a("https://admin.appnext.com/AdminService.asmx/bns", hashMap);
                        }
                    }
                } catch (Throwable unused) {
                }
            }
        }).start();
        return arrayList;
    }

    private static AppnextAd a(AppnextAd appnextAd, AppnextAd appnextAd2) {
        return appnextAd.getRevenueType().equals(appnextAd2.getRevenueType()) ? Float.parseFloat(appnextAd.getRevenueRate()) < Float.parseFloat(appnextAd2.getRevenueRate()) ? appnextAd2 : appnextAd : appnextAd.getRevenueType().equals("cpc") ? appnextAd : appnextAd2;
    }

    private static AppnextAd b(ArrayList<AppnextAd> arrayList, AppnextAd appnextAd) {
        Iterator<AppnextAd> it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd next = it.next();
            if (next.getAdPackage().equals(appnextAd.getAdPackage())) {
                return next;
            }
        }
        return null;
    }

    protected static boolean P(String str) {
        try {
            return new JSONObject(str).has("rnd");
        } catch (Throwable unused) {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public String a(ArrayList<AppnextAd> arrayList) {
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<AppnextAd> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(new JSONObject(it.next().getAdJSON()));
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("apps", jSONArray);
            return jSONObject.toString().replace(" ", "\\u2028").replace(" ", "\\u2029");
        } catch (Throwable unused) {
            return "";
        }
    }

    /* access modifiers changed from: protected */
    public final void b(String str, Ad ad) {
        a(str, "", ad);
    }

    /* access modifiers changed from: protected */
    public final void a(String str, String str2, Ad ad) {
        a(str, str2, ad, 2);
    }

    /* access modifiers changed from: protected */
    public final void a(final String str, final String str2, final Ad ad, final int i) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            /* class com.appnext.core.d.AnonymousClass4 */

            public final void run() {
                a k = d.this.k(ad);
                if (k != null) {
                    if (k.getAds() == null) {
                        k.d(new ArrayList<>());
                    } else {
                        k.d(k.getAds());
                    }
                    k.setState(i);
                    k.O(str);
                    d dVar = d.this;
                    Ad ad = ad;
                    dVar.a(ad, str + " " + str2, k.getPlacementID());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public final a k(Ad ad) {
        return this.fU.get(new b(ad));
    }

    /* access modifiers changed from: protected */
    public final HashMap<b, a> aW() {
        return this.fU;
    }

    /* access modifiers changed from: protected */
    public final void a(Ad ad, a aVar) {
        this.fU.put(new b(ad), aVar);
    }

    public static String d(AppnextAd appnextAd) {
        return appnextAd.getAdJSON();
    }

    public final String l(Ad ad) {
        return k(ad).A();
    }

    public static g parseAd(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            AppnextAd appnextAd = (AppnextAd) l.a(AppnextAd.class, jSONObject);
            if (appnextAd != null) {
                appnextAd.setAdJSON(jSONObject.toString());
                if (jSONObject.has("sid")) {
                    appnextAd.setSession(jSONObject.getString("sid"));
                }
                if (appnextAd.getStoreRating().equals("")) {
                    appnextAd.setStoreRating("0");
                }
            }
            return appnextAd;
        } catch (Throwable unused) {
            return null;
        }
    }

    protected static boolean a(String str, String str2) {
        return j.bj().o(str, str2);
    }

    /* access modifiers changed from: protected */
    public void l(String str) {
        j.bj().ab(str);
    }

    /* access modifiers changed from: protected */
    public void a(String str, Ad ad) {
        j.bj().n(str, ad.getPlacementID());
    }

    protected static int j(Context context) {
        try {
            if (f.a(context, "android.permission.READ_CONTACTS") && f.a(context, "android.permission.GET_ACCOUNTS")) {
                return AccountManager.get(context).getAccountsByType(AccountType.GOOGLE).length > 0 ? 0 : 1;
            }
        } catch (Throwable unused) {
        }
        return 2;
    }

    static /* synthetic */ String a(d dVar, Context context, Ad ad, String str, ArrayList arrayList) {
        StringBuilder sb = new StringBuilder("https://global.appnext.com/offerWallApi.aspx?ext=t&pimp=1&igroup=sdk&m=1&osid=100&s2s=1&type=json&id=");
        sb.append(str);
        sb.append("&cnt=50");
        sb.append("&tid=");
        sb.append(ad != null ? ad.getTID() : "301");
        sb.append("&vid=");
        sb.append(ad != null ? ad.getVID() : "2.5.1.472");
        sb.append("&cat=");
        String str2 = "";
        sb.append(ad != null ? ad.getCategories() : str2);
        sb.append("&pbk=");
        sb.append(ad != null ? ad.getPostback() : str2);
        sb.append("&did=");
        sb.append(f.b(context, Boolean.parseBoolean(dVar.c(ad).get("didPrivacy"))));
        sb.append("&devn=");
        sb.append(f.be());
        sb.append("&dosv=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("&dct=");
        sb.append(f.Z(f.o(context)));
        sb.append("&lang=");
        sb.append(Locale.getDefault().getLanguage());
        sb.append("&dcc=");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getSimState() == 5) {
            String simOperator = telephonyManager.getSimOperator();
            str2 = simOperator.substring(0, 3) + "_" + simOperator.substring(3);
        }
        sb.append(str2);
        sb.append("&dds=0");
        sb.append("&packageId=");
        sb.append(context.getPackageName());
        sb.append("&g=");
        sb.append(j(context));
        sb.append("&rnd=");
        sb.append(new Random().nextInt());
        return sb.toString();
    }
}
