package com.appnext.base.a.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.appnext.base.a.b;
import com.appnext.base.b.e;
import java.util.concurrent.atomic.AtomicInteger;

public class a {
    private static a dB;
    private static b dC;
    private AtomicInteger dA = new AtomicInteger(0);
    private SQLiteDatabase dD;

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    /* renamed from: com.appnext.base.a.a.a$a  reason: collision with other inner class name */
    public static final class EnumC0005a extends Enum<EnumC0005a> {
        private static final /* synthetic */ int[] $VALUES$40a167d9 = {1, 2};
        public static final int DatabaseOrDiskFull$53629b42 = 2;
        public static final int Global$53629b42 = 1;

        private EnumC0005a(String str, int i) {
        }

        public static int[] af() {
            return (int[]) $VALUES$40a167d9.clone();
        }
    }

    private a(Context context) {
        dC = b.c(context);
    }

    public static a ac() {
        if (dB == null) {
            synchronized (a.class) {
                if (dB == null) {
                    dB = new a(e.getContext().getApplicationContext());
                }
            }
        }
        return dB;
    }

    public final SQLiteDatabase ad() {
        if (this.dA.incrementAndGet() == 1) {
            this.dD = dC.getWritableDatabase();
        }
        return this.dD;
    }

    public final void ae() {
        if (this.dA.decrementAndGet() == 0) {
            this.dD.close();
        }
    }

    /* renamed from: com.appnext.base.a.a.a$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] dE;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000f */
        static {
            int[] iArr = new int[EnumC0005a.af().length];
            dE = iArr;
            iArr[EnumC0005a.DatabaseOrDiskFull$53629b42 - 1] = 1;
            try {
                dE[EnumC0005a.Global$53629b42 - 1] = 2;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static void a(int i, Throwable th) {
        int[] iArr = AnonymousClass1.dE;
    }
}
