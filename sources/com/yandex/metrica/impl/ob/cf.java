package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ob.ce;
import java.util.ArrayList;
import java.util.List;

class cf {

    /* renamed from: a  reason: collision with root package name */
    private final String f1057a;
    private final cj b;
    private int c;
    private final List<ce> d = new ArrayList();
    private final List<ce> e = new ArrayList();
    private final List<ce> f = new ArrayList();

    cf(String str, cj cjVar) {
        this.f1057a = str;
        this.b = cjVar;
    }

    public void a(ce ceVar) {
        this.c += ceVar.c().b;
        this.d.add(ceVar);
        int i = AnonymousClass1.f1058a[ceVar.a(this.b).ordinal()];
        if (i == 1) {
            this.e.add(ceVar);
        } else if (i == 2) {
            this.f.add(ceVar);
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.cf$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1058a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            int[] iArr = new int[ce.a.values().length];
            f1058a = iArr;
            iArr[ce.a.THIS.ordinal()] = 1;
            f1058a[ce.a.OTHER.ordinal()] = 2;
        }
    }

    public boolean a() {
        return !this.f.isEmpty();
    }

    public int b() {
        return this.d.size();
    }

    public String c() {
        return this.f1057a;
    }

    public List<ce> d() {
        return this.d;
    }

    public Long e() {
        long j = Long.MAX_VALUE;
        for (ce ceVar : this.d) {
            Long valueOf = Long.valueOf(ceVar.c().c);
            if (valueOf.compareTo(j) < 0) {
                j = valueOf;
            }
        }
        return j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f1057a.equals(((cf) obj).f1057a);
    }

    public int hashCode() {
        return this.f1057a.hashCode();
    }
}
