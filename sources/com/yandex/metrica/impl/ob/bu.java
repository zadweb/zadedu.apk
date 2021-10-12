package com.yandex.metrica.impl.ob;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.yandex.metrica.impl.bk;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class bu implements bt {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<String, String[]> f1045a;

    public bu(HashMap<String, String[]> hashMap) {
        this.f1045a = hashMap;
    }

    @Override // com.yandex.metrica.impl.ob.bt
    public boolean a(SQLiteDatabase sQLiteDatabase) {
        boolean z = true;
        try {
            for (Map.Entry<String, String[]> entry : this.f1045a.entrySet()) {
                Cursor cursor = null;
                try {
                    cursor = sQLiteDatabase.query(entry.getKey(), null, null, null, null, null, null);
                    if (cursor == null) {
                        bk.a(cursor);
                        return false;
                    }
                    z &= a(cursor, entry.getValue());
                } finally {
                    bk.a(cursor);
                }
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(Cursor cursor, String[] strArr) {
        String[] columnNames = cursor.getColumnNames();
        Arrays.sort(columnNames);
        Arrays.sort(strArr);
        return Arrays.equals(columnNames, strArr);
    }
}
