package com.tappx.a;

import android.content.Context;
import com.mopub.common.GpsHelper;
import java.lang.reflect.Method;

public class y0 {

    /* renamed from: a  reason: collision with root package name */
    private final Context f864a;
    private final d<a> b;

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final String f865a;
        private final boolean b;

        public a(String str, boolean z) {
            this.f865a = str;
            this.b = z;
        }

        public String a() {
            return this.f865a;
        }

        public boolean b() {
            return this.b;
        }
    }

    public y0(Context context) {
        this(context, new j(n.c));
    }

    private a b() {
        Object invoke = a(Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient"), "getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, this.f864a);
        return new a(a(invoke), b(invoke));
    }

    public a a() {
        a a2 = this.b.a();
        if (a2 != null) {
            return a2;
        }
        try {
            a b2 = b();
            this.b.a(b2);
            return b2;
        } catch (Exception unused) {
            return null;
        }
    }

    y0(Context context, d<a> dVar) {
        this.f864a = context;
        this.b = dVar;
    }

    private String a(Object obj) {
        try {
            return (String) a(obj.getClass(), "getId", null).invoke(obj, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    private boolean b(Object obj) {
        try {
            Boolean bool = (Boolean) a(obj.getClass(), GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, null).invoke(obj, new Object[0]);
            if (bool == null) {
                return false;
            }
            return bool.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?>[] clsArr) {
        while (cls != null) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchMethodException();
    }
}
