package com.appnext.banners;

import android.content.Context;
import com.appnext.core.Ad;
import com.appnext.core.callbacks.OnECPMLoaded;

public class MediumRectangleAd extends BannerAd {
    @Override // com.appnext.core.Ad, com.appnext.banners.BannerAd
    public String getAUID() {
        return "1020";
    }

    @Override // com.appnext.core.Ad, com.appnext.banners.BannerAd
    public /* bridge */ /* synthetic */ void getECPM(OnECPMLoaded onECPMLoaded) {
        super.getECPM(onECPMLoaded);
    }

    @Override // com.appnext.core.Ad, com.appnext.banners.BannerAd
    public /* bridge */ /* synthetic */ String getTID() {
        return super.getTID();
    }

    @Override // com.appnext.core.Ad, com.appnext.banners.BannerAd
    public /* bridge */ /* synthetic */ String getVID() {
        return super.getVID();
    }

    @Override // com.appnext.core.Ad, com.appnext.banners.BannerAd
    public /* bridge */ /* synthetic */ boolean isAdLoaded() {
        return super.isAdLoaded();
    }

    @Override // com.appnext.core.Ad, com.appnext.banners.BannerAd
    public /* bridge */ /* synthetic */ void loadAd() {
        super.loadAd();
    }

    @Override // com.appnext.core.Ad, com.appnext.banners.BannerAd
    public /* bridge */ /* synthetic */ void showAd() {
        super.showAd();
    }

    public MediumRectangleAd(Context context, String str) {
        super(context, str);
    }

    protected MediumRectangleAd(Ad ad) {
        super(ad);
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
    @Override // com.appnext.banners.BannerAd
    public String getTemId(BannerAdData bannerAdData) {
        char c = 0;
        if (bannerAdData.getRevenueType().equals("cpi")) {
            String lowerCase = d.S().get("MEDIUM_RECTANGLE_cpiActiveFlow").toLowerCase();
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
                return "102";
            }
            if (c != 1) {
                return c != 2 ? "111" : "108";
            }
            return "105";
        }
        String lowerCase2 = d.S().get("MEDIUM_RECTANGLE_cpcActiveFlow").toLowerCase();
        int hashCode = lowerCase2.hashCode();
        if (hashCode != 97) {
            if (hashCode == 98 && lowerCase2.equals("b")) {
                c = 1;
                return c == 0 ? "205" : "202";
            }
        }
        c = 65535;
        if (c == 0) {
        }
    }
}
