package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.r;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class bw implements bv {

    /* renamed from: a  reason: collision with root package name */
    private final Context f1046a;
    private final String b;
    private File c;
    private bo d;
    private FileLock e;
    private RandomAccessFile f;
    private FileChannel g;

    public bw(Context context, String str) {
        this.f1046a = context;
        this.b = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x005b, code lost:
        return null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    @Override // com.yandex.metrica.impl.ob.bv
    public synchronized SQLiteDatabase a() {
        bo boVar;
        File filesDir = this.f1046a.getFilesDir();
        this.c = new File(filesDir, new File(this.b).getName() + ".lock");
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.c, "rw");
        this.f = randomAccessFile;
        FileChannel channel = randomAccessFile.getChannel();
        this.g = channel;
        this.e = channel.lock();
        boVar = new bo(this.f1046a, this.b, bm.c());
        this.d = boVar;
        return boVar.getWritableDatabase();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0009 */
    @Override // com.yandex.metrica.impl.ob.bv
    public synchronized void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
        bk.a(this.d);
        this.c.getAbsolutePath();
        r.a(this.e);
        bk.a(this.f);
        bk.a(this.g);
        this.d = null;
        this.f = null;
        this.e = null;
        this.g = null;
    }
}
