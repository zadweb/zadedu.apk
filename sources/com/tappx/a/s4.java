package com.tappx.a;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class s4 {

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final Object f815a;
        private final String b;
        private Class<?> c;
        private List<Class<?>> d = new ArrayList();
        private List<Object> e = new ArrayList();
        private boolean f;
        private boolean g;

        public a(Object obj, String str) {
            this.f815a = obj;
            this.b = str;
            this.c = obj != null ? obj.getClass() : null;
        }

        public Object a() {
            List<Class<?>> list = this.d;
            Method a2 = s4.a(this.c, this.b, (Class[]) list.toArray(new Class[this.d.size()]));
            if (this.f) {
                a2.setAccessible(true);
            }
            Object[] array = this.e.toArray();
            if (this.g) {
                return a2.invoke(null, array);
            }
            return a2.invoke(this.f815a, array);
        }

        public a b() {
            this.f = true;
            return this;
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
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
