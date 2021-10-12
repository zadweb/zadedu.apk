package com.yandex.metrica.impl.ob;

import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.yandex.metrica.impl.ob.bm;

public class bs {

    /* renamed from: a  reason: collision with root package name */
    private final bm.l f1044a;
    private final bm.l b;
    private final SparseArray<bm.l> c;
    private final bt d;

    public bs(bm.l lVar, bm.l lVar2, SparseArray<bm.l> sparseArray, bt btVar) {
        this.f1044a = lVar;
        this.b = lVar2;
        this.c = sparseArray;
        this.d = btVar;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            if (this.d != null && !this.d.a(sQLiteDatabase)) {
                a(sQLiteDatabase, this.f1044a, this.b);
            }
        } catch (Exception unused) {
        }
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
        a(this.f1044a, sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public void a(bm.l lVar, SQLiteDatabase sQLiteDatabase) {
        try {
            lVar.a(sQLiteDatabase);
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        boolean z;
        if (i2 > i) {
            z = false;
            for (int i3 = i + 1; i3 <= i2; i3++) {
                try {
                    bm.l lVar = this.c.get(i3);
                    if (lVar != null) {
                        lVar.a(sQLiteDatabase);
                    }
                } catch (Exception unused) {
                }
            }
            if (!(this.d.a(sQLiteDatabase) ^ true) && !z) {
                a(sQLiteDatabase, this.f1044a, this.b);
                return;
            }
            return;
        }
        z = true;
        if (!(!this.d.a(sQLiteDatabase)) && !z) {
        }
    }

    /* access modifiers changed from: package-private */
    public void a(SQLiteDatabase sQLiteDatabase, bm.l lVar, bm.l lVar2) {
        try {
            lVar2.a(sQLiteDatabase);
        } catch (Exception unused) {
        }
        a(lVar, sQLiteDatabase);
    }
}
