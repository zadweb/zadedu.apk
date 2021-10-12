package com.startapp.common.c;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class b {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0014 A[SYNTHETIC, Splitter:B:13:0x0014] */
    public static <T> T a(String str, Class<T> cls) {
        Throwable th;
        a aVar = null;
        try {
            a aVar2 = new a(str);
            try {
                T t = (T) aVar2.a(cls, (JSONObject) null);
                try {
                    aVar2.close();
                } catch (IOException unused) {
                }
                return t;
            } catch (Throwable th2) {
                th = th2;
                aVar = aVar2;
                if (aVar != null) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (aVar != null) {
                try {
                    aVar.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public static String a(Object obj) {
        return c(obj).toString();
    }

    private static JSONObject c(Object obj) {
        Class<?> cls = obj.getClass();
        ArrayList<Field> arrayList = new ArrayList();
        while (cls != null && !Object.class.equals(cls)) {
            arrayList.addAll(Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
        }
        JSONObject jSONObject = new JSONObject();
        for (Field field : arrayList) {
            int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                try {
                    field.setAccessible(true);
                    if (field.get(obj) != null) {
                        String a2 = c.a(field);
                        if (c.e(field)) {
                            jSONObject.put(a2, c(field.get(obj)));
                        } else if (c.c(field)) {
                            JSONArray jSONArray = new JSONArray();
                            for (Object obj2 : (List) field.get(obj)) {
                                jSONArray.put(b(obj2));
                            }
                            jSONObject.put(a2, jSONArray);
                        } else if (c.d(field)) {
                            JSONArray jSONArray2 = new JSONArray();
                            for (Object obj3 : (Set) field.get(obj)) {
                                jSONArray2.put(b(obj3));
                            }
                            jSONObject.put(a2, jSONArray2);
                        } else if (c.b(field)) {
                            JSONObject jSONObject2 = new JSONObject();
                            for (Map.Entry entry : ((Map) field.get(obj)).entrySet()) {
                                jSONObject2.put(entry.getKey().toString(), b(entry.getValue()));
                            }
                            jSONObject.put(a2, jSONObject2);
                        } else {
                            jSONObject.put(a2, field.get(obj));
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | JSONException unused) {
                }
            }
        }
        return jSONObject;
    }

    public static Object b(Object obj) {
        if (c.a(obj)) {
            return obj;
        }
        return c(obj);
    }
}
