package com.startapp.common.c;

import android.os.Build;
import com.startapp.common.Constants;
import com.startapp.common.a.g;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class a extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    public static Map<String, Class> f595a;
    private InputStream b = null;
    private String c;

    @Override // java.io.InputStream
    @Deprecated
    public final int read() {
        return 0;
    }

    public a(String str) {
        this.c = str;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() {
        super.close();
        InputStream inputStream = this.b;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public final <T> T a(Class<T> cls, JSONObject jSONObject) {
        try {
            return (T) b(cls, jSONObject);
        } catch (d e) {
            g.a("JSONInputStream", 6, "Error while trying to parse object " + cls.toString(), e);
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r20v0, resolved type: com.startapp.common.c.a */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02d0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02d8, code lost:
        throw new com.startapp.common.c.d("Unknown error occurred ", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02d9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02da, code lost:
        r13 = r3;
        r17 = r4;
        r16 = r5;
        r18 = r6;
        r14 = r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x02d0 A[ExcHandler: all (r0v9 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:63:0x010d] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0163 A[Catch:{ Exception -> 0x02d9, all -> 0x02d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01a2 A[Catch:{ Exception -> 0x02d9, all -> 0x02d0 }] */
    private <T> T b(Class<T> cls, JSONObject jSONObject) {
        JSONObject jSONObject2;
        T t;
        int length;
        int i;
        Field[] fieldArr;
        int i2;
        int i3;
        T t2;
        String str;
        boolean z;
        boolean z2;
        Class cls2;
        Class cls3;
        Class cls4;
        Class cls5;
        T t3;
        if (this.b == null && this.c == null) {
            throw new d("Can't read object, the input is null.");
        }
        if (this.c == null) {
            try {
                this.c = a(this.b, (String) null);
            } catch (Exception e) {
                throw new d("Can't read input stream.", e);
            }
        }
        if (jSONObject == null) {
            try {
                jSONObject2 = new JSONObject(this.c);
            } catch (JSONException e2) {
                throw new d("Can't deserialize the object. Failed to create JSON object.", e2);
            }
        } else {
            jSONObject2 = jSONObject;
        }
        try {
            e eVar = (e) cls.getAnnotation(e.class);
            boolean z3 = true;
            char c2 = 0;
            if (Build.VERSION.SDK_INT >= 9 && cls.equals(HttpCookie.class)) {
                Constructor<?> constructor = cls.getDeclaredConstructors()[0];
                constructor.setAccessible(true);
                t3 = (T) constructor.newInstance("name", "value");
            } else if (cls.isPrimitive()) {
                return cls.newInstance();
            } else {
                if (cls.getAnnotation(e.class) == null || eVar.c()) {
                    Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    t3 = declaredConstructor.newInstance(new Object[0]);
                } else if (!eVar.c()) {
                    try {
                        String string = jSONObject2.getString(eVar.a());
                        return (T) b(Class.forName(eVar.b() + "." + string), jSONObject2);
                    } catch (ClassNotFoundException e3) {
                        throw new d("Problem instantiating object class ", e3);
                    } catch (JSONException e4) {
                        throw new d("Problem instantiating object class ", e4);
                    }
                } else {
                    t = null;
                    Field[] declaredFields = cls.getDeclaredFields();
                    if (eVar != null && eVar.c()) {
                        declaredFields = a(cls, declaredFields);
                    }
                    Field[] fieldArr2 = declaredFields;
                    length = fieldArr2.length;
                    i = 0;
                    while (i < length) {
                        Field field = fieldArr2[i];
                        int modifiers = field.getModifiers();
                        if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                            String a2 = c.a(field);
                            try {
                                if (jSONObject2.has(a2)) {
                                    field.setAccessible(z3);
                                    if (field.getDeclaredAnnotations().length > 0) {
                                        Annotation annotation = field.getDeclaredAnnotations()[c2];
                                        if (annotation.annotationType().equals(f.class)) {
                                            f fVar = (f) annotation;
                                            cls4 = fVar.b();
                                            cls3 = fVar.d();
                                            Class c3 = fVar.c();
                                            z2 = fVar.a();
                                            z = true;
                                            cls2 = fVar.e();
                                            cls5 = c3;
                                            if (field.getType().getAnnotation(e.class) == null) {
                                                e eVar2 = (e) field.getType().getAnnotation(e.class);
                                                String string2 = jSONObject2.getJSONObject(a2).getString(eVar2.a());
                                                field.set(t, b(Class.forName(eVar2.b() + "." + string2), jSONObject2.getJSONObject(a2)));
                                            } else if (z2) {
                                                field.set(t, b(field.getType(), jSONObject2.getJSONObject(a2)));
                                            } else if (!z || (!Map.class.isAssignableFrom(cls4) && !Collection.class.isAssignableFrom(cls4))) {
                                                i2 = i;
                                                i3 = length;
                                                fieldArr = fieldArr2;
                                                t2 = t;
                                                if (field.getType().isEnum()) {
                                                    field.set(t2, a((String) jSONObject2.get(a2), cls4));
                                                } else if (field.getType().isPrimitive()) {
                                                    field.set(t2, a(jSONObject2, field, jSONObject2.get(a2), field.getType()));
                                                } else if (field.getType().isArray()) {
                                                    field.set(t2, a(jSONObject2, cls4, field));
                                                } else {
                                                    Object a3 = a(jSONObject2.get(a2), field.getType());
                                                    if (a3.equals(null)) {
                                                        field.set(t2, null);
                                                    } else {
                                                        field.set(t2, a3);
                                                    }
                                                }
                                            } else if (cls4.equals(HashMap.class)) {
                                                JSONObject jSONObject3 = jSONObject2.getJSONObject(a2);
                                                Iterator<String> keys = jSONObject3.keys();
                                                str = a2;
                                                i2 = i;
                                                i3 = length;
                                                fieldArr = fieldArr2;
                                                t2 = t;
                                                field.set(t2, a(cls3, cls5, cls2, field, jSONObject3, keys));
                                            } else {
                                                i2 = i;
                                                i3 = length;
                                                fieldArr = fieldArr2;
                                                t2 = t;
                                                if (cls4.equals(ArrayList.class)) {
                                                    field.set(t2, b(cls5, field, jSONObject2.getJSONArray(a2)));
                                                } else if (cls4.equals(HashSet.class)) {
                                                    field.set(t2, a(cls5, jSONObject2.getJSONArray(a2)));
                                                } else if (cls4.equals(EnumSet.class)) {
                                                    field.set(t2, a(cls5, field, jSONObject2.getJSONArray(a2)));
                                                }
                                            }
                                        }
                                    }
                                    cls5 = null;
                                    cls4 = null;
                                    cls3 = null;
                                    cls2 = null;
                                    z2 = false;
                                    z = false;
                                    if (field.getType().getAnnotation(e.class) == null) {
                                    }
                                } else {
                                    i2 = i;
                                    i3 = length;
                                    fieldArr = fieldArr2;
                                    t2 = t;
                                    if (Constants.a().booleanValue()) {
                                        g.a("JSONInputStream", 4, String.format("Field [%s] doesn't exist. Keeping default value.", a2));
                                    }
                                }
                            } catch (Exception e5) {
                                Exception e6 = e5;
                                g.a("JSONInputStream", 6, String.format("Failed to get field [%s] %s", str, e6.toString()));
                                i = i2 + 1;
                                t = t2;
                                length = i3;
                                fieldArr2 = fieldArr;
                                z3 = true;
                                c2 = 0;
                            } catch (Throwable th) {
                            }
                            i = i2 + 1;
                            t = t2;
                            length = i3;
                            fieldArr2 = fieldArr;
                            z3 = true;
                            c2 = 0;
                        }
                        i2 = i;
                        i3 = length;
                        fieldArr = fieldArr2;
                        t2 = t;
                        i = i2 + 1;
                        t = t2;
                        length = i3;
                        fieldArr2 = fieldArr;
                        z3 = true;
                        c2 = 0;
                    }
                    return t;
                }
            }
            t = t3;
            Field[] declaredFields2 = cls.getDeclaredFields();
            declaredFields2 = a(cls, declaredFields2);
            Field[] fieldArr22 = declaredFields2;
            length = fieldArr22.length;
            i = 0;
            while (i < length) {
            }
            return t;
        } catch (Exception e7) {
            throw new d("Can't deserialize the object. Failed to instantiate object.", e7);
        }
    }

    private <T> Field[] a(Class<T> cls, Field[] fieldArr) {
        int length = fieldArr.length;
        Field[] declaredFields = cls.getSuperclass().getDeclaredFields();
        int length2 = declaredFields.length;
        Field[] fieldArr2 = new Field[(length + length2)];
        System.arraycopy(fieldArr, 0, fieldArr2, 0, length);
        System.arraycopy(declaredFields, 0, fieldArr2, length, length2);
        return fieldArr2;
    }

    private Enum<?> a(String str, Class cls) {
        return Enum.valueOf(cls, str);
    }

    private Object a(JSONObject jSONObject, Field field) {
        Object obj;
        JSONArray jSONArray = jSONObject.getJSONArray(c.a(field));
        int length = jSONArray.length();
        Class cls = f595a.get(field.getType().getSimpleName());
        Object newInstance = Array.newInstance((Class) cls.getField("TYPE").get(null), length);
        for (int i = 0; i < length; i++) {
            String string = jSONArray.getString(i);
            Class<String> cls2 = String.class;
            if (cls.equals(Character.class)) {
                cls2 = Character.TYPE;
            }
            Constructor constructor = cls.getConstructor(cls2);
            if (cls.equals(Character.class)) {
                obj = constructor.newInstance(Character.valueOf(string.charAt(0)));
            } else {
                obj = constructor.newInstance(string);
            }
            Array.set(newInstance, i, obj);
        }
        return newInstance;
    }

    private <T> Object a(JSONObject jSONObject, Class<T> cls, Field field) {
        if (cls != null) {
            return b(jSONObject, cls, field);
        }
        return a(jSONObject, field);
    }

    private <T> T[] b(JSONObject jSONObject, Class<T> cls, Field field) {
        JSONArray jSONArray = jSONObject.getJSONArray(c.a(field));
        int length = jSONArray.length();
        Object newInstance = Array.newInstance((Class<?>) cls, length);
        for (int i = 0; i < length; i++) {
            Array.set(newInstance, i, b(cls, jSONArray.getJSONObject(i)));
        }
        return (T[]) ((Object[]) newInstance);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v1, resolved type: java.util.HashMap */
    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> Map<K, V> a(Class<K> cls, Class<V> cls2, Class cls3, Field field, JSONObject jSONObject, Iterator<K> it) {
        HashMap hashMap = new HashMap();
        while (it.hasNext()) {
            K next = it.next();
            Enum<?> cast = cls.equals(Integer.class) ? cls.cast(Integer.valueOf(Integer.parseInt(next))) : next;
            if (cls.isEnum()) {
                cast = a(cast.toString(), cls);
            }
            K k = next;
            JSONObject optJSONObject = jSONObject.optJSONObject(k);
            if (optJSONObject == null) {
                JSONArray optJSONArray = jSONObject.optJSONArray(k);
                if (optJSONArray != null) {
                    hashMap.put(cast, a(cls3, optJSONArray));
                } else if (cls2.isEnum()) {
                    hashMap.put(cast, a((String) jSONObject.get(k), cls2));
                } else {
                    hashMap.put(cast, jSONObject.get(k));
                }
            } else {
                hashMap.put(cast, b(cls2, optJSONObject));
            }
        }
        return hashMap;
    }

    private <V> Set<V> a(Class<V> cls, JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                hashSet.add(jSONArray.get(i));
            } else {
                hashSet.add(b(cls, optJSONObject));
            }
        }
        return hashSet;
    }

    private <V> Set a(Class<V> cls, Field field, JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(a(jSONArray.getString(i), cls));
        }
        return hashSet;
    }

    private <V> List<V> b(Class<V> cls, Field field, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                arrayList.add(jSONArray.get(i));
            } else {
                arrayList.add(b(cls, optJSONObject));
            }
        }
        return arrayList;
    }

    private static Object a(Object obj, Class<?> cls) {
        if (obj.getClass().equals(cls)) {
            return obj;
        }
        if (!cls.equals(Integer.class)) {
            return (!cls.equals(Long.class) || !obj.getClass().equals(Integer.class)) ? obj : Long.valueOf(((Integer) obj).longValue());
        }
        if (obj.getClass().equals(Double.class)) {
            return Integer.valueOf(((Double) obj).intValue());
        }
        if (obj.getClass().equals(Long.class)) {
            return Integer.valueOf(((Long) obj).intValue());
        }
        return obj;
    }

    private static Object a(JSONObject jSONObject, Field field, Object obj, Class<?> cls) {
        if (obj.getClass().equals(cls)) {
            return obj;
        }
        if (obj.getClass().equals(String.class)) {
            if (cls.equals(Integer.TYPE)) {
                return Integer.valueOf(jSONObject.getInt(c.a(field)));
            }
            return obj;
        } else if (cls.equals(Integer.TYPE)) {
            return Integer.valueOf(((Number) obj).intValue());
        } else {
            if (cls.equals(Float.TYPE)) {
                return Float.valueOf(((Number) obj).floatValue());
            }
            if (cls.equals(Long.TYPE)) {
                return Long.valueOf(((Number) obj).longValue());
            }
            return cls.equals(Double.TYPE) ? Double.valueOf(((Number) obj).doubleValue()) : obj;
        }
    }

    private static String a(InputStream inputStream, String str) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
            } catch (Exception e) {
                g.a("JSONInputStream", 6, String.format("Can't create BufferedReader [%s]", e.toString()));
                throw e;
            }
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return sb.toString();
                }
                sb.append(readLine);
            } catch (IOException e2) {
                g.a("JSONInputStream", 6, String.format("Can't Can't read input stream [%s]", e2.toString()));
                throw e2;
            }
        }
    }

    static {
        HashMap hashMap = new HashMap();
        f595a = hashMap;
        hashMap.put("int[]", Integer.class);
        f595a.put("long[]", Long.class);
        f595a.put("double[]", Double.class);
        f595a.put("float[]", Float.class);
        f595a.put("bool[]", Boolean.class);
        f595a.put("char[]", Character.class);
        f595a.put("byte[]", Byte.class);
        f595a.put("void[]", Void.class);
        f595a.put("short[]", Short.class);
    }
}
