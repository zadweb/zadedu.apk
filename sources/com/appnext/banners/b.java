package com.appnext.banners;

import android.content.Context;
import android.util.Pair;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.a;
import com.appnext.core.d;
import com.appnext.core.f;
import com.appnext.core.g;
import com.appnext.core.p;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;

/* access modifiers changed from: package-private */
public final class b extends d {
    private static b de;
    private final int aM = 50;

    /* access modifiers changed from: protected */
    @Override // com.appnext.core.d
    public final <T> void a(String str, Ad ad, T t) {
    }

    public static synchronized b R() {
        b bVar;
        synchronized (b.class) {
            if (de == null) {
                de = new b();
            }
            bVar = de;
        }
        return bVar;
    }

    private b() {
    }

    /* access modifiers changed from: protected */
    @Override // com.appnext.core.d
    public final String a(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        StringBuilder sb = new StringBuilder("&auid=");
        sb.append(ad != null ? ad.getAUID() : "1000");
        return sb.toString();
    }

    public final void a(Context context, Ad ad, String str, d.a aVar, BannerAdRequest bannerAdRequest) {
        ((BannerAd) ad).setAdRequest(new BannerAdRequest(bannerAdRequest));
        super.a(context, ad, str, aVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.appnext.core.d
    public final void a(Context context, Ad ad, a aVar) throws Exception {
        AppnextAd a2 = a(context, ad, ((BannerAdRequest) ((BannerAd) ad).getAdRequest()).getCreativeType());
        if (a2 != null) {
            f.Y(a2.getImageURL());
            if (ad instanceof MediumRectangleAd) {
                f.Y(a2.getWideImageURL());
                return;
            }
            return;
        }
        throw new Exception(AppnextError.NO_ADS);
    }

    /* access modifiers changed from: protected */
    @Override // com.appnext.core.d
    public final int a(Context context, g gVar) {
        BannerAdData bannerAdData = new BannerAdData((AppnextAd) gVar);
        int a2 = a(context, bannerAdData);
        if (a2 != 0) {
            return a2;
        }
        if (!bannerAdData.getCampaignGoal().equals(com.appnext.core.a.b.hX) || !f.c(context, bannerAdData.getAdPackage())) {
            return (!bannerAdData.getCampaignGoal().equals(com.appnext.core.a.b.hY) || f.c(context, bannerAdData.getAdPackage())) ? 0 : 1;
        }
        return 2;
    }

    /* access modifiers changed from: protected */
    @Override // com.appnext.core.d
    public final void a(Ad ad, String str, String str2) {
        new StringBuilder("error ").append(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.appnext.core.d
    public final p c(Ad ad) {
        return d.S();
    }

    /* access modifiers changed from: protected */
    @Override // com.appnext.core.d
    public final boolean a(Context context, Ad ad, ArrayList<?> arrayList) {
        return a(context, ad, arrayList, ((BannerAdRequest) ((BannerAd) ad).getAdRequest()).getCreativeType()) != null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0068, code lost:
        if (hasVideo(r0) == false) goto L_0x006b;
     */
    public final AppnextAd a(Context context, Ad ad, ArrayList<?> arrayList, String str, ArrayList<String> arrayList2) {
        if (arrayList == null) {
            return null;
        }
        Iterator<?> it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd appnextAd = (AppnextAd) it.next();
            boolean z = false;
            if (ad instanceof MediumRectangleAd) {
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -892481938) {
                    if (hashCode != 96673) {
                        if (hashCode == 112202875 && str.equals("video")) {
                            c = 2;
                        }
                    } else if (str.equals(BannerAdRequest.TYPE_ALL)) {
                        c = 0;
                    }
                } else if (str.equals("static")) {
                    c = 1;
                }
                if (c != 0) {
                    if (c == 1) {
                        z = c(appnextAd);
                    } else if (c == 2) {
                        z = hasVideo(appnextAd);
                    }
                } else if (!c(appnextAd)) {
                }
                if (!z && !a(appnextAd.getBannerID(), ad.getPlacementID()) && !arrayList2.contains(appnextAd.getBannerID())) {
                    return appnextAd;
                }
            }
            z = true;
            if (!z) {
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final AppnextAd a(Context context, Ad ad, ArrayList<?> arrayList, String str) {
        return a(context, ad, arrayList, str, new ArrayList<>());
    }

    /* access modifiers changed from: protected */
    public final AppnextAd a(Context context, Ad ad, String str) {
        ArrayList<?> ads;
        if (k(ad) == null || (ads = k(ad).getAds()) == null) {
            return null;
        }
        return a(context, ad, ads, str);
    }

    private static boolean a(Ad ad, AppnextAd appnextAd, String str) {
        if (ad instanceof MediumRectangleAd) {
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -892481938) {
                if (hashCode != 96673) {
                    if (hashCode == 112202875 && str.equals("video")) {
                        c = 2;
                    }
                } else if (str.equals(BannerAdRequest.TYPE_ALL)) {
                    c = 0;
                }
            } else if (str.equals("static")) {
                c = 1;
            }
            if (c != 0) {
                if (c == 1) {
                    return c(appnextAd);
                }
                if (c != 2) {
                    return false;
                }
                return hasVideo(appnextAd);
            } else if (c(appnextAd) || hasVideo(appnextAd)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /* JADX DEBUG: Type inference failed for r1v2. Raw type applied. Possible types: java.util.ArrayList<?>, java.util.ArrayList<com.appnext.core.AppnextAd> */
    public final ArrayList<AppnextAd> f(Ad ad) {
        return k(ad).getAds();
    }

    private static int a(Context context, BannerAdData bannerAdData) {
        if (!bannerAdData.getCptList().equals("") && !bannerAdData.getCptList().equals("[]")) {
            try {
                JSONArray jSONArray = new JSONArray(bannerAdData.getCptList());
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (f.c(context, jSONArray.getString(i))) {
                        return 0;
                    }
                }
                return 3;
            } catch (JSONException unused) {
            }
        }
        return 0;
    }

    static boolean hasVideo(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrl().equals("") || !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrl30Sec().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    static boolean c(AppnextAd appnextAd) {
        return !appnextAd.getWideImageURL().equals("");
    }
}
