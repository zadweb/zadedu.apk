package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.yandex.metrica.impl.bk;
import java.util.ArrayList;
import java.util.List;

public class br {

    /* renamed from: a  reason: collision with root package name */
    private final bv f1043a;
    private String b;

    public br(bo boVar, String str) {
        this.f1043a = new bx(boVar);
        this.b = str;
    }

    public List<cw> a() {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Throwable th;
        try {
            sQLiteDatabase = this.f1043a.a();
            try {
                cursor = sQLiteDatabase.query(this.b, null, null, null, null, null, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            ArrayList arrayList = new ArrayList();
                            do {
                                arrayList.add(new cw(cursor.getString(cursor.getColumnIndex("name")), cursor.getLong(cursor.getColumnIndex("granted")) == 1));
                            } while (cursor.moveToNext());
                            this.f1043a.a(sQLiteDatabase);
                            bk.a(cursor);
                            return arrayList;
                        }
                    } catch (Exception unused) {
                    } catch (Throwable th2) {
                        th = th2;
                        this.f1043a.a(sQLiteDatabase);
                        bk.a(cursor);
                        throw th;
                    }
                }
            } catch (Exception unused2) {
                cursor = null;
            } catch (Throwable th3) {
                cursor = null;
                th = th3;
                this.f1043a.a(sQLiteDatabase);
                bk.a(cursor);
                throw th;
            }
        } catch (Exception unused3) {
            sQLiteDatabase = null;
            cursor = null;
        } catch (Throwable th4) {
            cursor = null;
            th = th4;
            sQLiteDatabase = null;
            this.f1043a.a(sQLiteDatabase);
            bk.a(cursor);
            throw th;
        }
        this.f1043a.a(sQLiteDatabase);
        bk.a(cursor);
        return null;
    }

    public void a(List<cw> list) {
        SQLiteDatabase a2 = this.f1043a.a();
        try {
            a2.beginTransaction();
            a2.execSQL("delete from permissions");
            for (cw cwVar : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", cwVar.b());
                contentValues.put("granted", Integer.valueOf(cwVar.a() ? 1 : 0));
                a2.insert("permissions", null, contentValues);
            }
            a2.setTransactionSuccessful();
        } catch (SQLException unused) {
        } catch (Throwable th) {
            a2.endTransaction();
            this.f1043a.a(a2);
            throw th;
        }
        a2.endTransaction();
        this.f1043a.a(a2);
    }
}
