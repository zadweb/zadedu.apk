package com.appnext.base.a.c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import com.appnext.base.a.a.a;
import com.appnext.base.a.b.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class e<MODEL extends d> {
    private static final int eh = -1;
    private static final String ei = " DESC";

    /* access modifiers changed from: protected */
    public abstract String[] at();

    /* access modifiers changed from: protected */
    public abstract MODEL b(Cursor cursor);

    /* access modifiers changed from: protected */
    public enum a {
        Equals(" = ? "),
        GreaterThan(" >= ? "),
        LessThan(" <= ? ");
        
        private String mOperator;

        private a(String str) {
            this.mOperator = str;
        }

        public final String au() {
            return this.mOperator;
        }
    }

    protected static long a(String str, ContentValues contentValues) {
        try {
            long insert = com.appnext.base.a.a.a.ac().ad().insert(str, null, contentValues);
            com.appnext.base.a.a.a.ac().ae();
            return insert;
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.Global$53629b42, th);
            return -1;
        }
    }

    protected static long b(String str, ContentValues contentValues) {
        try {
            long insertWithOnConflict = com.appnext.base.a.a.a.ac().ad().insertWithOnConflict(str, null, contentValues, 5);
            com.appnext.base.a.a.a.ac().ae();
            return insertWithOnConflict;
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.Global$53629b42, th);
            return -1;
        }
    }

    protected static long a(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        try {
            return sQLiteDatabase.insertWithOnConflict(str, null, contentValues, 5);
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.Global$53629b42, th);
            return -1;
        }
    }

    protected static long b(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        try {
            return sQLiteDatabase.insert(str, null, contentValues);
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.Global$53629b42, th);
            return -1;
        }
    }

    private static ContentValues b(JSONObject jSONObject) {
        try {
            ContentValues contentValues = new ContentValues();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                contentValues.put(next, jSONObject.getString(next));
            }
            return contentValues;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final long a(String str, JSONObject jSONObject) {
        try {
            long insertWithOnConflict = com.appnext.base.a.a.a.ac().ad().insertWithOnConflict(str, null, b(jSONObject), 5);
            com.appnext.base.a.a.a.ac().ae();
            return insertWithOnConflict;
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.Global$53629b42, th);
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        if (0 == 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        r2.endTransaction();
        com.appnext.base.a.a.a.ac().ae();
     */
    public final long a(String str, JSONArray jSONArray) {
        long j = -1;
        if (jSONArray != null) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                int length = jSONArray.length();
                sQLiteDatabase = com.appnext.base.a.a.a.ac().ad();
                sQLiteDatabase.beginTransaction();
                for (int i = 0; i < length; i++) {
                    j = a(sQLiteDatabase, str, b(jSONArray.getJSONObject(i)));
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Throwable unused) {
            }
        }
        return j;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        if (0 == 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        r2.endTransaction();
        com.appnext.base.a.a.a.ac().ae();
     */
    public final long b(String str, JSONArray jSONArray) {
        long j = -1;
        if (jSONArray != null) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                int length = jSONArray.length();
                sQLiteDatabase = com.appnext.base.a.a.a.ac().ad();
                sQLiteDatabase.beginTransaction();
                for (int i = 0; i < length; i++) {
                    j = b(sQLiteDatabase, str, b(jSONArray.getJSONObject(i)));
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Throwable unused) {
            }
        }
        return j;
    }

    /* access modifiers changed from: protected */
    public final void delete(String str) {
        a(str, null, null, null);
    }

    /* access modifiers changed from: protected */
    public final int a(String str, String[] strArr, String[] strArr2, List<a> list) {
        try {
            SQLiteDatabase ad = com.appnext.base.a.a.a.ac().ad();
            String str2 = null;
            if (strArr != null) {
                str2 = a(strArr, list);
            }
            int delete = ad.delete(str, str2, strArr2);
            com.appnext.base.a.a.a.ac().ae();
            return delete;
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return 0;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.Global$53629b42, new Exception(th.getMessage()));
            return 0;
        }
    }

    protected static void e(String str, String str2) {
        try {
            com.appnext.base.a.a.a.ac().ad().delete(str, str2, null);
            com.appnext.base.a.a.a.ac().ae();
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.EnumC0005a.Global$53629b42, new Exception(th.getMessage()));
        }
    }

    /* access modifiers changed from: protected */
    public final List<MODEL> y(String str) {
        try {
            List<MODEL> e = e(com.appnext.base.a.a.a.ac().ad().query(str, at(), null, null, null, null, null));
            com.appnext.base.a.a.a.ac().ae();
            return e;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final List<MODEL> a(String str, String[] strArr, String[] strArr2, String str2, List<a> list) {
        try {
            List<MODEL> e = e(com.appnext.base.a.a.a.ac().ad().query(str, at(), a(strArr, list), strArr2, null, null, str2));
            com.appnext.base.a.a.a.ac().ae();
            return e;
        } catch (Throwable unused) {
            return null;
        }
    }

    protected static String z(String str) {
        return str + ei;
    }

    private List<MODEL> e(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                arrayList.add(b(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    private static String a(String[] strArr, List<a> list) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    sb.append(" AND ");
                }
                sb.append(strArr[i]);
                sb.append(list.get(i).au());
            }
        } catch (Throwable unused) {
        }
        return sb.toString();
    }
}
