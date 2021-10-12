package com.appnext.banners;

import android.content.Context;
import com.appnext.core.Ad;
import com.appnext.core.c;
import com.appnext.core.callbacks.OnECPMLoaded;

/* access modifiers changed from: package-private */
public class BannerAd extends Ad {
    protected static final String TID = "301";
    protected static final String VID = "2.5.1.472";

    @Override // com.appnext.core.Ad
    public String getAUID() {
        return "1000";
    }

    @Override // com.appnext.core.Ad
    public void getECPM(OnECPMLoaded onECPMLoaded) {
    }

    @Override // com.appnext.core.Ad
    public String getTID() {
        return TID;
    }

    @Override // com.appnext.core.Ad
    public String getVID() {
        return "2.5.1.472";
    }

    @Override // com.appnext.core.Ad
    public boolean isAdLoaded() {
        return false;
    }

    @Override // com.appnext.core.Ad
    public void loadAd() {
    }

    @Override // com.appnext.core.Ad
    public void showAd() {
    }

    protected BannerAd(Ad ad) {
        super(ad);
    }

    public BannerAd(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.appnext.core.Ad
    public String getSessionId() {
        return super.getSessionId();
    }

    /* access modifiers changed from: protected */
    @Override // com.appnext.core.Ad
    public void setAdRequest(c cVar) {
        super.setAdRequest(cVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.appnext.core.Ad
    public c getAdRequest() {
        return super.getAdRequest();
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        if (r8.equals("a") != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0087, code lost:
        if (r8.equals("a") != false) goto L_0x008b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0090 A[RETURN] */
    public String getTemId(BannerAdData bannerAdData) {
        char c = 0;
        if (bannerAdData.getRevenueType().equals("cpi")) {
            String lowerCase = d.S().get("BANNER_cpiActiveFlow").toLowerCase();
            switch (lowerCase.hashCode()) {
                case 97:
                    break;
                case 98:
                    if (lowerCase.equals("b")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 99:
                    if (lowerCase.equals("c")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 100:
                    if (lowerCase.equals("d")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                return "100";
            }
            if (c != 1) {
                return c != 2 ? "109" : "106";
            }
            return "103";
        }
        String lowerCase2 = d.S().get("BANNER_cpcActiveFlow").toLowerCase();
        int hashCode = lowerCase2.hashCode();
        if (hashCode != 97) {
            if (hashCode == 98 && lowerCase2.equals("b")) {
                c = 1;
                return c == 0 ? "203" : "200";
            }
        }
        c = 65535;
        if (c == 0) {
        }
    }
}
