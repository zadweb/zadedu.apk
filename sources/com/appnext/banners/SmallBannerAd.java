package com.appnext.banners;

import android.content.Context;
import com.appnext.core.Ad;
import com.appnext.core.callbacks.OnECPMLoaded;

public class SmallBannerAd extends BannerAd {
    @Override // com.appnext.core.Ad, com.appnext.banners.BannerAd
    public String getAUID() {
        return "1000";
    }

    @Override // com.appnext.core.Ad, com.appnext.banners.BannerAd
    public /* bridge */ /* synthetic */ void getECPM(OnECPMLoaded onECPMLoaded) {
        super.getECPM(onECPMLoaded);
    }

    @Override // com.appnext.core.Ad, com.appnext.banners.BannerAd
    public /* bridge */ /* synthetic */ String getTID() {
        return super.getTID();
    }

    @Override // com.appnext.banners.BannerAd
    public /* bridge */ /* synthetic */ String getTemId(BannerAdData bannerAdData) {
        return super.getTemId(bannerAdData);
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

    public SmallBannerAd(Context context, String str) {
        super(context, str);
    }

    protected SmallBannerAd(Ad ad) {
        super(ad);
    }
}
