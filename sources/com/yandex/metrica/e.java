package com.yandex.metrica;

import android.location.Location;
import android.text.TextUtils;
import com.yandex.metrica.YandexMetricaConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class e extends YandexMetricaConfig {

    /* renamed from: a  reason: collision with root package name */
    private final a f929a;
    private final Map<String, String> b;
    private final String c;
    private final String d;
    private final List<String> e;
    private final Integer f;
    private final Integer g;
    private final Integer h;
    private final Map<String, String> i;
    private final Boolean j;
    private final Boolean k;

    /* synthetic */ e(a aVar, byte b2) {
        this(aVar);
    }

    public static a a(String str) {
        return new a(str);
    }

    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public String f930a;
        Boolean b;
        private YandexMetricaConfig.Builder c;
        private a d;
        private String e;
        private String f;
        private List<String> g;
        private Integer h;
        private Map<String, String> i;
        private Integer j;
        private Integer k;
        private Map<String, String> l = new HashMap();
        private Boolean m;

        protected a(String str) {
            this.c = YandexMetricaConfig.newConfigBuilder(str);
        }

        public a a(String str) {
            this.c.setAppVersion(str);
            return this;
        }

        public a a(int i2) {
            this.c.setSessionTimeout(i2);
            return this;
        }

        public a b(String str) {
            this.f930a = str;
            return this;
        }

        public a a(boolean z) {
            this.c.setReportCrashesEnabled(z);
            return this;
        }

        public a b(boolean z) {
            this.c.setReportNativeCrashesEnabled(z);
            return this;
        }

        public a a() {
            this.c.setLogEnabled();
            return this;
        }

        public a a(Location location) {
            this.c.setLocation(location);
            return this;
        }

        public a c(boolean z) {
            this.c.setTrackLocationEnabled(z);
            return this;
        }

        public a d(boolean z) {
            this.c.setCollectInstalledApps(z);
            return this;
        }

        public a a(String str, String str2) {
            this.c.putErrorEnvironmentValue(str, str2);
            return this;
        }

        public a a(a aVar) {
            this.d = aVar;
            return this;
        }

        public a c(String str) {
            this.e = str;
            return this;
        }

        public a a(List<String> list) {
            this.g = list;
            return this;
        }

        public a b(int i2) {
            if (i2 >= 0) {
                this.h = Integer.valueOf(i2);
                return this;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %1$s. %1$s should be positive.", "App Build Number"));
        }

        public a a(Map<String, String> map, Boolean bool) {
            this.m = bool;
            this.i = map;
            return this;
        }

        public a c(int i2) {
            this.k = Integer.valueOf(i2);
            return this;
        }

        public a d(int i2) {
            this.j = Integer.valueOf(i2);
            return this;
        }

        public a a(PreloadInfo preloadInfo) {
            this.c.setPreloadInfo(preloadInfo);
            return this;
        }

        public a e(boolean z) {
            this.c.handleFirstActivationAsUpdate(z);
            return this;
        }

        public a b(String str, String str2) {
            this.l.put(str, str2);
            return this;
        }

        public e b() {
            return new e(this, (byte) 0);
        }
    }

    private e(a aVar) {
        super(aVar.c);
        List list;
        this.d = aVar.e;
        this.f = aVar.h;
        List<String> list2 = null;
        if (aVar.g != null && !aVar.g.isEmpty()) {
            list = aVar.g;
        } else if (!TextUtils.isEmpty(aVar.f)) {
            list = new ArrayList();
            list.add(aVar.f);
        } else {
            list = null;
        }
        this.e = list != null ? Collections.unmodifiableList(list) : list2;
        this.f929a = aVar.d;
        this.b = aVar.i;
        this.h = aVar.k;
        this.g = aVar.j;
        this.c = aVar.f930a;
        this.i = aVar.l;
        this.j = aVar.m;
        this.k = aVar.b;
    }

    public String a() {
        return this.d;
    }

    public List<String> c() {
        return this.e;
    }

    public Integer d() {
        return this.f;
    }

    public a e() {
        return this.f929a;
    }

    public Map<String, String> f() {
        return this.b;
    }

    public String g() {
        return this.c;
    }

    public Integer h() {
        return this.h;
    }

    public Integer i() {
        return this.g;
    }

    public Map<String, String> j() {
        return this.i;
    }

    public Boolean k() {
        return this.j;
    }

    public Boolean l() {
        return this.k;
    }
}
