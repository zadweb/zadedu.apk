package com.tappx.a;

import android.content.SharedPreferences;

/* access modifiers changed from: package-private */
public final class q2 {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f798a;

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f799a;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            int[] iArr = new int[p2.values().length];
            f799a = iArr;
            iArr[p2.DENIED_DEVELOPER.ordinal()] = 1;
            f799a[p2.DENIED_USER.ordinal()] = 2;
            f799a[p2.GRANTED_DEVELOPER.ordinal()] = 3;
            f799a[p2.GRANTED_USER.ordinal()] = 4;
            f799a[p2.MISSING_ANSWER.ordinal()] = 5;
        }
    }

    q2(SharedPreferences sharedPreferences) {
        this.f798a = sharedPreferences;
    }

    private p2 c(int i) {
        if (i == -2) {
            return p2.DENIED_DEVELOPER;
        }
        if (i == -1) {
            return p2.DENIED_USER;
        }
        if (i == 1) {
            return p2.GRANTED_USER;
        }
        if (i != 2) {
            return p2.MISSING_ANSWER;
        }
        return p2.GRANTED_DEVELOPER;
    }

    public void a(Boolean bool, String str) {
        this.f798a.edit().putInt("tappx_privacy_applies", b(bool)).putString("tappx_privacy_consent_html", str).apply();
    }

    public Boolean d() {
        return b(this.f798a.getInt("tappx_privacy_applies", 0));
    }

    public long e() {
        return this.f798a.getLong("tappx_consent_timestamp", -1);
    }

    public String f() {
        return this.f798a.getString("tappx_privacy_gdpr_consent", null);
    }

    public p2 g() {
        return c(this.f798a.getInt("tappx_privacy_accepted", 0));
    }

    public String h() {
        return this.f798a.getString("tappx_mark_choice", null);
    }

    public String i() {
        return this.f798a.getString("tappx_privacy_consent_html", null);
    }

    public int j() {
        return this.f798a.getInt("tappx_privacy_version", 0);
    }

    public String k() {
        return this.f798a.getString("tappx_usprivacy_string", null);
    }

    public boolean l() {
        return this.f798a.getBoolean("tappx_privacy_autoDisclaimer", false);
    }

    public boolean m() {
        return this.f798a.getBoolean("tappx_privacy_renew", false);
    }

    private Boolean b(int i) {
        return (i == -1 || i == 1) ? true : null;
    }

    private int b(Boolean bool) {
        if (bool == null) {
            return 0;
        }
        return bool.booleanValue() ? 1 : -1;
    }

    public void a(Boolean bool) {
        this.f798a.edit().putInt("tappx_privacy_applies", b(bool)).apply();
    }

    public void b() {
        this.f798a.edit().remove("tappx_privacy_accepted").remove("tappx_sync_required").apply();
    }

    public void c(boolean z) {
        if (z) {
            this.f798a.edit().putBoolean("tappx_privacy_renew", true).apply();
        } else {
            this.f798a.edit().remove("tappx_privacy_renew").apply();
        }
    }

    private int b(p2 p2Var) {
        int i = a.f799a[p2Var.ordinal()];
        if (i == 1) {
            return -2;
        }
        if (i == 2) {
            return -1;
        }
        if (i != 3) {
            return i != 4 ? 0 : 1;
        }
        return 2;
    }

    public void a(p2 p2Var) {
        this.f798a.edit().putInt("tappx_privacy_accepted", b(p2Var)).apply();
    }

    public void b(String str) {
        this.f798a.edit().putString("tappx_privacy_gdpr_consent", str).apply();
    }

    public void a(boolean z) {
        this.f798a.edit().putBoolean("tappx_privacy_autoDisclaimer", z).apply();
    }

    public void b(boolean z) {
        this.f798a.edit().putBoolean("tappx_sync_required", z).apply();
    }

    public void c(String str) {
        this.f798a.edit().putString("tappx_usprivacy_string", str).apply();
    }

    public void a() {
        this.f798a.edit().remove("tappx_privacy_applies").remove("tappx_privacy_autoDisclaimer").remove("tappx_consent_timestamp").remove("tappx_privacy_renew").remove("tappx_privacy_consent_html").apply();
    }

    public boolean c() {
        return this.f798a.getBoolean("tappx_sync_required", false);
    }

    public void a(int i) {
        this.f798a.edit().putInt("tappx_privacy_version", i).apply();
    }

    public void a(long j) {
        this.f798a.edit().putLong("tappx_consent_timestamp", j).apply();
    }

    public void a(String str) {
        this.f798a.edit().putString("tappx_mark_choice", str).apply();
    }
}
