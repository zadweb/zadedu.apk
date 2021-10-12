package com.yandex.metrica;

import android.location.Location;
import com.yandex.metrica.impl.bk;
import java.util.HashMap;
import java.util.Map;

public class YandexMetricaConfig {

    /* renamed from: a  reason: collision with root package name */
    private final String f925a;
    private final String b;
    private final Integer c;
    private final Boolean d;
    private final Boolean e;
    private final Location f;
    private final Boolean g;
    private final Boolean h;
    private final Boolean i;
    private final PreloadInfo j;
    private final Map<String, String> k;
    private final boolean l;

    public static Builder newConfigBuilder(String str) {
        return new Builder(str);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private final String f926a;
        private String b;
        private Integer c;
        private Boolean d;
        private Boolean e;
        private Location f;
        private Boolean g;
        private Boolean h;
        private Boolean i;
        private PreloadInfo j;
        private Map<String, String> k = new HashMap();
        private boolean l;

        protected Builder(String str) {
            bk.a(str);
            this.f926a = str;
        }

        public Builder setAppVersion(String str) {
            bk.a(str, "App Version");
            this.b = str;
            return this;
        }

        public Builder setSessionTimeout(int i2) {
            this.c = Integer.valueOf(i2);
            return this;
        }

        public Builder setReportCrashesEnabled(boolean z) {
            this.d = Boolean.valueOf(z);
            return this;
        }

        public Builder setReportNativeCrashesEnabled(boolean z) {
            this.e = Boolean.valueOf(z);
            return this;
        }

        public Builder setLogEnabled() {
            this.i = true;
            return this;
        }

        public Builder setLocation(Location location) {
            this.f = location;
            return this;
        }

        public Builder setTrackLocationEnabled(boolean z) {
            this.g = Boolean.valueOf(z);
            return this;
        }

        public Builder setCollectInstalledApps(boolean z) {
            this.h = Boolean.valueOf(z);
            return this;
        }

        public Builder setPreloadInfo(PreloadInfo preloadInfo) {
            this.j = preloadInfo;
            return this;
        }

        public Builder putErrorEnvironmentValue(String str, String str2) {
            this.k.put(str, str2);
            return this;
        }

        public Builder handleFirstActivationAsUpdate(boolean z) {
            this.l = z;
            return this;
        }
    }

    protected YandexMetricaConfig(Builder builder) {
        this.f925a = builder.f926a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
        this.l = builder.l;
    }

    public String getApiKey() {
        return this.f925a;
    }

    public String getAppVersion() {
        return this.b;
    }

    public Integer getSessionTimeout() {
        return this.c;
    }

    public Boolean isReportCrashEnabled() {
        return this.d;
    }

    public Boolean isReportNativeCrashEnabled() {
        return this.e;
    }

    public Location getLocation() {
        return this.f;
    }

    public Boolean isTrackLocationEnabled() {
        return this.g;
    }

    public Boolean isLogEnabled() {
        return this.i;
    }

    public Boolean isCollectInstalledApps() {
        return this.h;
    }

    public PreloadInfo getPreloadInfo() {
        return this.j;
    }

    public Map<String, String> getErrorEnvironment() {
        return this.k;
    }

    public boolean isFirstActivationAsUpdate() {
        return this.l;
    }
}
