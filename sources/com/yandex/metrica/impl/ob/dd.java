package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

public abstract class dd {
    private static final dk c = new dk("UNDEFINED_");

    /* renamed from: a  reason: collision with root package name */
    protected final String f1082a;
    protected final SharedPreferences b;
    private final Map<String, Object> d = new HashMap();
    private boolean e = false;

    /* access modifiers changed from: protected */
    public abstract String f();

    public dd(Context context, String str) {
        this.f1082a = str;
        this.b = a(context);
        h();
    }

    /* access modifiers changed from: protected */
    public void h() {
        new dk(c.a(), this.f1082a);
    }

    /* access modifiers changed from: protected */
    public SharedPreferences a(Context context) {
        return dl.a(context, f());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.yandex.metrica.impl.ob.dd */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public <T extends dd> T a(String str, Object obj) {
        synchronized (this) {
            if (obj != null) {
                this.d.put(str, obj);
            }
        }
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.yandex.metrica.impl.ob.dd */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public <T extends dd> T h(String str) {
        synchronized (this) {
            this.d.put(str, this);
        }
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.yandex.metrica.impl.ob.dd */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public <T extends dd> T i() {
        synchronized (this) {
            this.e = true;
            this.d.clear();
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public String j() {
        return this.f1082a;
    }

    public void k() {
        synchronized (this) {
            SharedPreferences.Editor edit = this.b.edit();
            if (this.e) {
                edit.clear();
                edit.apply();
            } else {
                for (Map.Entry<String, Object> entry : this.d.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value == this) {
                        edit.remove(key);
                    } else if (value instanceof String) {
                        edit.putString(key, (String) value);
                    } else if (value instanceof Long) {
                        edit.putLong(key, ((Long) value).longValue());
                    } else if (value instanceof Integer) {
                        edit.putInt(key, ((Integer) value).intValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(key, ((Boolean) value).booleanValue());
                    } else if (value != null) {
                        throw new UnsupportedOperationException();
                    }
                }
                edit.apply();
            }
            this.d.clear();
            this.e = false;
        }
    }
}
