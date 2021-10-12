package com.yandex.metrica.impl;

import android.database.Cursor;
import com.yandex.metrica.c;
import com.yandex.metrica.impl.at;
import com.yandex.metrica.impl.ob.bl;
import com.yandex.metrica.impl.ob.t;

class au extends at {
    /* access modifiers changed from: protected */
    @Override // com.yandex.metrica.impl.at
    public boolean a(long j) {
        return false;
    }

    /* access modifiers changed from: protected */
    public long s() {
        return Long.MIN_VALUE;
    }

    /* access modifiers changed from: protected */
    public long t() {
        return Long.MIN_VALUE;
    }

    public au(t tVar) {
        super(tVar);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        if (r1.getCount() != 0) goto L_0x0038;
     */
    @Override // com.yandex.metrica.impl.at
    public at.c x() {
        Cursor cursor;
        Throwable th;
        Cursor cursor2;
        Cursor cursor3 = null;
        try {
            Cursor z = z();
            if (z != null) {
                try {
                    if (z.moveToFirst()) {
                    }
                } catch (Exception unused) {
                    cursor = null;
                    cursor3 = z;
                    bk.a(cursor3);
                    bk.a(cursor);
                    return super.x();
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = null;
                    cursor3 = z;
                    bk.a(cursor3);
                    bk.a(cursor2);
                    throw th;
                }
            }
            cursor3 = this.o.b(s(), bl.BACKGROUND);
            if (cursor3 != null && cursor3.moveToFirst() && cursor3.getCount() > 0) {
                this.o.a(s(), bl.BACKGROUND);
            }
            bk.a(z);
            bk.a(cursor3);
        } catch (Exception unused2) {
            cursor = null;
            bk.a(cursor3);
            bk.a(cursor);
            return super.x();
        } catch (Throwable th3) {
            th = th3;
            cursor2 = null;
            bk.a(cursor3);
            bk.a(cursor2);
            throw th;
        }
        return super.x();
    }

    /* access modifiers changed from: protected */
    @Override // com.yandex.metrica.impl.at
    public Cursor z() {
        return this.o.a(s(), this.b);
    }

    /* access modifiers changed from: protected */
    @Override // com.yandex.metrica.impl.at
    public Cursor a(long j, bl blVar) {
        return this.o.b(s(), blVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.yandex.metrica.impl.at
    public at.b a(long j, c.a.d.b bVar) {
        return super.a(t(), bVar);
    }

    @Override // com.yandex.metrica.impl.l, com.yandex.metrica.impl.ak
    public String a() {
        return super.a() + " [" + s() + "]";
    }
}
