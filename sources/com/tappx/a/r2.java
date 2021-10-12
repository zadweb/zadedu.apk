package com.tappx.a;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import com.tappx.sdk.android.TappxPrivacyManager;

/* access modifiers changed from: package-private */
public final class r2 implements TappxPrivacyManager {

    /* renamed from: a  reason: collision with root package name */
    private final n2 f806a;

    r2(n2 n2Var) {
        new Handler(Looper.getMainLooper());
        this.f806a = n2Var;
    }

    @Override // com.tappx.sdk.android.TappxPrivacyManager
    public void checkAndShowPrivacyDisclaimer(Activity activity) {
        checkAndShowPrivacyDisclaimer(activity, null);
    }

    @Override // com.tappx.sdk.android.TappxPrivacyManager
    public void denyPersonalInfoConsent() {
        this.f806a.h();
    }

    @Override // com.tappx.sdk.android.TappxPrivacyManager
    public void grantPersonalInfoConsent() {
        this.f806a.i();
    }

    @Override // com.tappx.sdk.android.TappxPrivacyManager
    public void renewPrivacyConsent(Activity activity) {
        this.f806a.a(activity);
    }

    @Override // com.tappx.sdk.android.TappxPrivacyManager
    public void setAutoPrivacyDisclaimerEnabled(boolean z) {
        this.f806a.a(z);
    }

    @Override // com.tappx.sdk.android.TappxPrivacyManager
    public void setGDPRConsent(String str) {
        this.f806a.a(str);
    }

    @Override // com.tappx.sdk.android.TappxPrivacyManager
    public void setUSPrivacy(String str) {
        this.f806a.b(str);
    }

    @Override // com.tappx.sdk.android.TappxPrivacyManager
    public void checkAndShowPrivacyDisclaimer(Activity activity, Runnable runnable) {
        this.f806a.a(activity, runnable);
    }
}
