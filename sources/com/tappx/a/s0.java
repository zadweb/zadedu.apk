package com.tappx.a;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.tappx.a.m0;

public class s0 implements q0 {
    private static final AdSize[] d = {AdSize.BANNER, AdSize.FULL_BANNER, AdSize.LARGE_BANNER, AdSize.LEADERBOARD, AdSize.MEDIUM_RECTANGLE, AdSize.WIDE_SKYSCRAPER};

    /* renamed from: a  reason: collision with root package name */
    private final AdView f809a;
    private boolean b = false;
    private m0.c c;

    class a extends AdListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ m0.c f810a;
        final /* synthetic */ Runnable b;

        a(m0.c cVar, Runnable runnable) {
            this.f810a = cVar;
            this.b = runnable;
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdClosed() {
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdFailedToLoad(int i) {
            j0.d("1HPYA2lkbaNURYCXsP4iRrPA2bcLu2GoZBfTi2x2iws", new Object[0]);
            m0.c cVar = this.f810a;
            if (cVar != null) {
                cVar.a(a2.NO_FILL);
            }
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdLeftApplication() {
            m0.c cVar = this.f810a;
            if (cVar != null) {
                cVar.a();
            }
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdLoaded() {
            j0.d("sQBMFfIvnZat9SH496KzHfKib626NzkhHKkXIfYGxxc", new Object[0]);
            this.b.run();
            m0.c cVar = this.f810a;
            if (cVar != null) {
                cVar.a(s0.this.f809a);
            }
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdOpened() {
            m0.c cVar = this.f810a;
            if (cVar != null) {
                cVar.d();
            }
        }
    }

    public s0(Context context) {
        this.f809a = new AdView(context);
    }

    @Override // com.tappx.a.q0
    public void destroy() {
        AdView adView = this.f809a;
        if (adView != null) {
            adView.destroy();
        }
    }

    @Override // com.tappx.a.q0
    public void loadAd() {
        if (this.b) {
            m0.c cVar = this.c;
            if (cVar != null) {
                cVar.a(a2.INTERNAL_ERROR);
                return;
            }
            return;
        }
        try {
            this.f809a.loadAd(new AdRequest.Builder().build());
        } catch (Throwable unused) {
        }
    }

    @Override // com.tappx.a.q0
    public void a(m0.c cVar, Runnable runnable) {
        this.c = cVar;
        if (cVar == null) {
            try {
                this.f809a.setAdListener(null);
            } catch (Throwable unused) {
                this.b = true;
            }
        } else {
            this.f809a.setAdListener(new a(cVar, runnable));
        }
    }

    @Override // com.tappx.a.q0
    public void a(String str, int i, int i2) {
        try {
            this.f809a.setAdSize(a(i, i2));
            this.f809a.setAdUnitId(str);
        } catch (Throwable unused) {
            this.b = true;
        }
    }

    private AdSize a(int i, int i2) {
        AdSize[] adSizeArr = d;
        for (AdSize adSize : adSizeArr) {
            if (adSize.getWidth() == i && adSize.getHeight() == i2) {
                return adSize;
            }
        }
        return AdSize.BANNER;
    }

    @Override // com.tappx.a.q0
    public View a() {
        return this.f809a;
    }
}
