package com.yandex.metrica.impl;

import android.os.Bundle;
import com.yandex.metrica.impl.utils.f;
import org.json.JSONException;
import org.json.JSONObject;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private JSONObject f938a = new JSONObject();
    private long b;
    private boolean c;
    private f.a d = f.a.d();
    private final f e = new f();

    /* renamed from: com.yandex.metrica.impl.a$a  reason: collision with other inner class name */
    public static final class C0046a {

        /* renamed from: a  reason: collision with root package name */
        public final String f939a;
        public final long b;

        public C0046a(String str, long j) {
            this.f939a = str;
            this.b = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                C0046a aVar = (C0046a) obj;
                if (this.b != aVar.b) {
                    return false;
                }
                String str = this.f939a;
                String str2 = aVar.f939a;
                return str == null ? str2 == null : str.equals(str2);
            }
        }

        public int hashCode() {
            String str = this.f939a;
            int hashCode = str != null ? str.hashCode() : 0;
            long j = this.b;
            return (hashCode * 31) + ((int) (j ^ (j >>> 32)));
        }
    }

    public a(String str, long j) {
        this.b = j;
        try {
            this.f938a = new JSONObject(str);
        } catch (JSONException unused) {
            this.f938a = new JSONObject();
            this.b = 0;
        }
    }

    public synchronized void a() {
        this.f938a = new JSONObject();
        this.b = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public synchronized void a(String str, String str2) {
        String a2 = this.e.a(str, this.d.b(), "App Environment");
        String a3 = this.e.a(str2, this.d.c(), "App Environment");
        if (this.f938a.has(a2)) {
            String string = this.f938a.getString(a2);
            if (a3 == null || !a3.equals(string)) {
                b(a2, a3);
            }
            return;
        }
        if (a3 != null) {
            b(a2, a3);
        }
    }

    public synchronized void a(Bundle bundle) {
        for (String str : bundle.keySet()) {
            a(str, bundle.getString(str));
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(String str, String str2) throws JSONException {
        if (this.f938a.length() >= this.d.a()) {
            if (this.d.a() != this.f938a.length() || !this.f938a.has(str)) {
                this.e.b(str, this.d.a(), "App Environment");
                return;
            }
        }
        this.f938a.put(str, str2);
        this.c = true;
    }

    public synchronized C0046a b() {
        if (this.c) {
            this.b++;
            this.c = false;
        }
        return new C0046a(this.f938a.toString(), this.b);
    }

    public synchronized String toString() {
        return "Map size " + this.f938a.length() + ". Is changed " + this.c + ". Current revision " + this.b;
    }
}
