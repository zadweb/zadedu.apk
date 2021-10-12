package com.startapp.common.b;

import java.util.HashMap;
import java.util.Map;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final a f591a;

    private b(a aVar) {
        this.f591a = aVar;
    }

    public int a() {
        return this.f591a.f592a;
    }

    public Map<String, String> b() {
        return this.f591a.b;
    }

    public long c() {
        return this.f591a.c;
    }

    public long d() {
        return this.f591a.d;
    }

    public boolean e() {
        return this.f591a.e;
    }

    public boolean f() {
        return this.f591a.f;
    }

    public String toString() {
        return "RunnerRequest: " + this.f591a.f592a + " " + this.f591a.c + " " + this.f591a.e + " " + this.f591a.d + " " + this.f591a.b;
    }

    /* compiled from: StartAppSDK */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private int f592a;
        private Map<String, String> b = new HashMap();
        private long c;
        private long d = 100;
        private boolean e = false;
        private boolean f = false;

        public a(int i) {
            this.f592a = i;
        }

        public a a(Map<String, String> map) {
            if (map != null) {
                this.b.putAll(map);
            }
            return this;
        }

        public a a(String str, String str2) {
            this.b.put(str, str2);
            return this;
        }

        public a a(long j) {
            this.c = j;
            return this;
        }

        public a a(boolean z) {
            this.e = z;
            return this;
        }

        public a b(boolean z) {
            this.f = z;
            return this;
        }

        public b a() {
            return new b(this);
        }
    }
}
