package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.utils.i;
import java.util.HashMap;
import java.util.Map;

public class bq {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, Object> f1041a;
    private final Map<String, Object> b;
    private final String c;
    private final a d;
    private volatile boolean e;
    private final bv f;

    static {
        bq.class.getSimpleName();
    }

    public bq(bo boVar, String str) {
        this(str, new bx(boVar));
    }

    protected bq(String str, bv bvVar) {
        this.f1041a = new HashMap();
        this.b = new HashMap();
        this.f = bvVar;
        this.c = str;
        a aVar = new a(this, (byte) 0);
        this.d = aVar;
        aVar.start();
    }

    /* access modifiers changed from: private */
    public class a extends Thread {
        private a() {
        }

        /* synthetic */ a(bq bqVar, byte b) {
            this();
        }

        public void run() {
            HashMap hashMap;
            synchronized (bq.this.f1041a) {
                bq.b(bq.this);
                bq.this.e = true;
                bq.this.f1041a.notifyAll();
            }
            while (!isInterrupted()) {
                synchronized (this) {
                    if (bq.this.b.size() == 0) {
                        try {
                            wait();
                        } catch (InterruptedException unused) {
                            interrupt();
                        }
                    }
                    hashMap = new HashMap(bq.this.b);
                    bq.this.b.clear();
                }
                if (hashMap.size() > 0) {
                    bq.a(bq.this, hashMap);
                    hashMap.clear();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.c;
    }

    public void b() {
        synchronized (this.d) {
            this.d.notifyAll();
        }
    }

    private void a(ContentValues[] contentValuesArr) {
        Throwable th;
        if (contentValuesArr != null) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                SQLiteDatabase a2 = this.f.a();
                try {
                    a2.beginTransaction();
                    for (ContentValues contentValues : contentValuesArr) {
                        if (contentValues.getAsString("value") == null) {
                            a2.delete(a(), "key = ?", new String[]{contentValues.getAsString("key")});
                        } else {
                            a2.insertWithOnConflict(a(), null, contentValues, 5);
                        }
                    }
                    a2.setTransactionSuccessful();
                    bk.a(a2);
                    this.f.a(a2);
                } catch (Exception unused) {
                    sQLiteDatabase = a2;
                    bk.a(sQLiteDatabase);
                    this.f.a(sQLiteDatabase);
                } catch (Throwable th2) {
                    th = th2;
                    sQLiteDatabase = a2;
                    bk.a(sQLiteDatabase);
                    this.f.a(sQLiteDatabase);
                    throw th;
                }
            } catch (Exception unused2) {
                bk.a(sQLiteDatabase);
                this.f.a(sQLiteDatabase);
            } catch (Throwable th3) {
                th = th3;
                bk.a(sQLiteDatabase);
                this.f.a(sQLiteDatabase);
                throw th;
            }
        }
    }

    public String a(String str, String str2) {
        Object b2 = b(str);
        return b2 instanceof String ? (String) b2 : str2;
    }

    public int a(String str, int i) {
        Object b2 = b(str);
        return b2 instanceof Integer ? ((Integer) b2).intValue() : i;
    }

    public long a(String str, long j) {
        Object b2 = b(str);
        return b2 instanceof Long ? ((Long) b2).longValue() : j;
    }

    public boolean a(String str, boolean z) {
        Object b2 = b(str);
        return b2 instanceof Boolean ? ((Boolean) b2).booleanValue() : z;
    }

    public bq a(String str) {
        synchronized (this.f1041a) {
            c();
            this.f1041a.remove(str);
        }
        synchronized (this.d) {
            this.b.put(str, this);
            this.d.notifyAll();
        }
        return this;
    }

    public synchronized bq b(String str, String str2) {
        a(str, (Object) str2);
        return this;
    }

    public bq b(String str, long j) {
        a(str, Long.valueOf(j));
        return this;
    }

    public synchronized bq b(String str, int i) {
        a(str, Integer.valueOf(i));
        return this;
    }

    public bq b(String str, boolean z) {
        a(str, Boolean.valueOf(z));
        return this;
    }

    private void a(String str, Object obj) {
        synchronized (this.f1041a) {
            c();
            this.f1041a.put(str, obj);
        }
        synchronized (this.d) {
            this.b.put(str, obj);
            this.d.notifyAll();
        }
    }

    private Object b(String str) {
        Object obj;
        synchronized (this.f1041a) {
            c();
            obj = this.f1041a.get(str);
        }
        return obj;
    }

    private void c() {
        if (!this.e) {
            try {
                this.f1041a.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        if (r8 != 4) goto L_0x004f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072 A[Catch:{ Exception -> 0x0084, all -> 0x0081 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x001f A[SYNTHETIC] */
    static /* synthetic */ void b(bq bqVar) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Object obj;
        Cursor cursor = null;
        try {
            sQLiteDatabase = bqVar.f.a();
            try {
                Cursor query = sQLiteDatabase.query(bqVar.a(), new String[]{"key", "value", "type"}, null, null, null, null, null);
                while (query.moveToNext()) {
                    try {
                        String string = query.getString(query.getColumnIndex("key"));
                        String string2 = query.getString(query.getColumnIndex("value"));
                        int i = query.getInt(query.getColumnIndex("type"));
                        if (!TextUtils.isEmpty(string)) {
                            if (i != 1) {
                                if (i != 2) {
                                    obj = i != 3 ? string2 : i.a(string2);
                                } else {
                                    obj = i.b(string2);
                                }
                                if (obj != null) {
                                    bqVar.f1041a.put(string, obj == 1 ? 1 : 0);
                                }
                            } else {
                                if ("true".equals(string2)) {
                                    obj = Boolean.TRUE;
                                } else if ("false".equals(string2)) {
                                    obj = Boolean.FALSE;
                                }
                                if (obj != null) {
                                }
                            }
                            obj = null;
                            if (obj != null) {
                            }
                        }
                    } catch (Exception unused) {
                        cursor = query;
                        bk.a(cursor);
                        bqVar.f.a(sQLiteDatabase);
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        bk.a(cursor);
                        bqVar.f.a(sQLiteDatabase);
                        throw th;
                    }
                }
                bk.a(query);
            } catch (Exception unused2) {
                bk.a(cursor);
                bqVar.f.a(sQLiteDatabase);
            } catch (Throwable th3) {
                th = th3;
                bk.a(cursor);
                bqVar.f.a(sQLiteDatabase);
                throw th;
            }
        } catch (Exception unused3) {
            sQLiteDatabase = null;
            bk.a(cursor);
            bqVar.f.a(sQLiteDatabase);
        } catch (Throwable th4) {
            th = th4;
            sQLiteDatabase = null;
            bk.a(cursor);
            bqVar.f.a(sQLiteDatabase);
            throw th;
        }
        bqVar.f.a(sQLiteDatabase);
    }

    static /* synthetic */ void a(bq bqVar, Map map) {
        ContentValues[] contentValuesArr = new ContentValues[map.size()];
        int i = 0;
        for (Map.Entry entry : map.entrySet()) {
            ContentValues contentValues = new ContentValues();
            Object value = entry.getValue();
            contentValues.put("key", (String) entry.getKey());
            if (value == bqVar) {
                contentValues.putNull("value");
            } else if (value instanceof String) {
                contentValues.put("value", (String) value);
                contentValues.put("type", (Integer) 4);
            } else if (value instanceof Long) {
                contentValues.put("value", (Long) value);
                contentValues.put("type", (Integer) 3);
            } else if (value instanceof Integer) {
                contentValues.put("value", (Integer) value);
                contentValues.put("type", (Integer) 2);
            } else if (value instanceof Boolean) {
                contentValues.put("value", String.valueOf(((Boolean) value).booleanValue()));
                contentValues.put("type", (Integer) 1);
            } else if (value != null) {
                throw new UnsupportedOperationException();
            }
            contentValuesArr[i] = contentValues;
            i++;
        }
        bqVar.a(contentValuesArr);
    }
}
