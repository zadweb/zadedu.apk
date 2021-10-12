package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.a;
import com.google.android.play.core.internal.ag;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class cd {

    /* renamed from: a  reason: collision with root package name */
    private static final ag f105a = new ag("ExtractorTaskFinder");
    private final ca b;
    private final au c;
    private final bc d;
    private final a e;

    cd(ca caVar, au auVar, bc bcVar, a aVar) {
        this.b = caVar;
        this.c = auVar;
        this.d = bcVar;
        this.e = aVar;
    }

    private final boolean b(bx bxVar, by byVar) {
        au auVar = this.c;
        bw bwVar = bxVar.c;
        return new cz(auVar, bwVar.f100a, bxVar.b, bwVar.b, byVar.f102a).l();
    }

    private static boolean c(by byVar) {
        int i = byVar.f;
        return i == 1 || i == 2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a7, code lost:
        if (r0 == null) goto L_0x00b0;
     */
    public final cc a() {
        cc ccVar;
        int i;
        try {
            this.b.a();
            ArrayList arrayList = new ArrayList();
            for (bx bxVar : this.b.c().values()) {
                if (ck.h(bxVar.c.c)) {
                    arrayList.add(bxVar);
                }
            }
            bi biVar = null;
            if (!arrayList.isEmpty()) {
                if (this.e.a()) {
                    Map<String, Long> c2 = this.c.c();
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            ccVar = null;
                            break;
                        }
                        bx bxVar2 = (bx) it.next();
                        Long l = c2.get(bxVar2.c.f100a);
                        if (l != null && bxVar2.c.b == l.longValue()) {
                            f105a.a("Found promote pack task for session %s with pack %s.", Integer.valueOf(bxVar2.f101a), bxVar2.c.f100a);
                            int i2 = bxVar2.f101a;
                            String str = bxVar2.c.f100a;
                            this.c.t(str);
                            int i3 = bxVar2.b;
                            bw bwVar = bxVar2.c;
                            ccVar = new cc(i2, str, null);
                            break;
                        }
                    }
                }
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        ccVar = null;
                        break;
                    }
                    bx bxVar3 = (bx) it2.next();
                    try {
                        au auVar = this.c;
                        bw bwVar2 = bxVar3.c;
                        if (auVar.k(bwVar2.f100a, bxVar3.b, bwVar2.b) == bxVar3.c.e.size()) {
                            f105a.a("Found final move task for session %s with pack %s.", Integer.valueOf(bxVar3.f101a), bxVar3.c.f100a);
                            int i4 = bxVar3.f101a;
                            bw bwVar3 = bxVar3.c;
                            ccVar = new cq(i4, bwVar3.f100a, bxVar3.b, bwVar3.b);
                            break;
                        }
                    } catch (IOException e2) {
                        throw new bk(String.format("Failed to check number of completed merges for session %s, pack %s", Integer.valueOf(bxVar3.f101a), bxVar3.c.f100a), e2, bxVar3.f101a);
                    }
                }
                if (ccVar == null) {
                    Iterator it3 = arrayList.iterator();
                    loop3:
                    while (true) {
                        if (!it3.hasNext()) {
                            ccVar = null;
                            break;
                        }
                        bx bxVar4 = (bx) it3.next();
                        if (ck.h(bxVar4.c.c)) {
                            for (by byVar : bxVar4.c.e) {
                                au auVar2 = this.c;
                                bw bwVar4 = bxVar4.c;
                                if (auVar2.i(bwVar4.f100a, bxVar4.b, bwVar4.b, byVar.f102a).exists()) {
                                    f105a.a("Found merge task for session %s with pack %s and slice %s.", Integer.valueOf(bxVar4.f101a), bxVar4.c.f100a, byVar.f102a);
                                    int i5 = bxVar4.f101a;
                                    bw bwVar5 = bxVar4.c;
                                    ccVar = new cn(i5, bwVar5.f100a, bxVar4.b, bwVar5.b, byVar.f102a);
                                    break loop3;
                                }
                            }
                            continue;
                        }
                    }
                    if (ccVar == null) {
                        Iterator it4 = arrayList.iterator();
                        loop5:
                        while (true) {
                            if (!it4.hasNext()) {
                                ccVar = null;
                                break;
                            }
                            bx bxVar5 = (bx) it4.next();
                            if (ck.h(bxVar5.c.c)) {
                                for (by byVar2 : bxVar5.c.e) {
                                    if (b(bxVar5, byVar2)) {
                                        au auVar3 = this.c;
                                        bw bwVar6 = bxVar5.c;
                                        if (auVar3.h(bwVar6.f100a, bxVar5.b, bwVar6.b, byVar2.f102a).exists()) {
                                            f105a.a("Found verify task for session %s with pack %s and slice %s.", Integer.valueOf(bxVar5.f101a), bxVar5.c.f100a, byVar2.f102a);
                                            int i6 = bxVar5.f101a;
                                            bw bwVar7 = bxVar5.c;
                                            String str2 = bwVar7.f100a;
                                            int i7 = bxVar5.b;
                                            long j = bwVar7.b;
                                            String str3 = byVar2.f102a;
                                            String str4 = byVar2.b;
                                            long j2 = byVar2.c;
                                            ccVar = new dc(i6, str2, i7, j, str3, str4);
                                            break loop5;
                                        }
                                    }
                                }
                                continue;
                            }
                        }
                        if (ccVar == null) {
                            Iterator it5 = arrayList.iterator();
                            loop7:
                            while (true) {
                                if (!it5.hasNext()) {
                                    biVar = null;
                                    break;
                                }
                                bx bxVar6 = (bx) it5.next();
                                if (ck.h(bxVar6.c.c)) {
                                    Iterator<by> it6 = bxVar6.c.e.iterator();
                                    while (it6.hasNext()) {
                                        by next = it6.next();
                                        if (!c(next)) {
                                            au auVar4 = this.c;
                                            bw bwVar8 = bxVar6.c;
                                            try {
                                                i = new cz(auVar4, bwVar8.f100a, bxVar6.b, bwVar8.b, next.f102a).k();
                                            } catch (IOException e3) {
                                                f105a.b("Slice checkpoint corrupt, restarting extraction. %s", e3);
                                                i = 0;
                                            }
                                            if (i != -1 && next.d.get(i).f99a) {
                                                f105a.a("Found extraction task using compression format %s for session %s, pack %s, slice %s, chunk %s.", Integer.valueOf(next.e), Integer.valueOf(bxVar6.f101a), bxVar6.c.f100a, next.f102a, Integer.valueOf(i));
                                                InputStream a2 = this.d.a(bxVar6.f101a, bxVar6.c.f100a, next.f102a, i);
                                                int i8 = bxVar6.f101a;
                                                bw bwVar9 = bxVar6.c;
                                                String str5 = bwVar9.f100a;
                                                int i9 = bxVar6.b;
                                                long j3 = bwVar9.b;
                                                String str6 = next.f102a;
                                                int i10 = next.e;
                                                int size = next.d.size();
                                                bw bwVar10 = bxVar6.c;
                                                biVar = new bi(i8, str5, i9, j3, str6, i10, i, size, bwVar10.d, bwVar10.c, a2);
                                                break loop7;
                                            }
                                            it6 = it6;
                                        }
                                    }
                                    continue;
                                }
                            }
                            if (biVar == null) {
                                Iterator it7 = arrayList.iterator();
                                loop9:
                                while (true) {
                                    if (!it7.hasNext()) {
                                        ccVar = null;
                                        break;
                                    }
                                    bx bxVar7 = (bx) it7.next();
                                    if (ck.h(bxVar7.c.c)) {
                                        for (by byVar3 : bxVar7.c.e) {
                                            if (c(byVar3) && byVar3.d.get(0).f99a && !b(bxVar7, byVar3)) {
                                                f105a.a("Found patch slice task using patch format %s for session %s, pack %s, slice %s.", Integer.valueOf(byVar3.f), Integer.valueOf(bxVar7.f101a), bxVar7.c.f100a, byVar3.f102a);
                                                InputStream a3 = this.d.a(bxVar7.f101a, bxVar7.c.f100a, byVar3.f102a, 0);
                                                int i11 = bxVar7.f101a;
                                                String str7 = bxVar7.c.f100a;
                                                ccVar = new cw(i11, str7, this.c.t(str7), this.c.u(bxVar7.c.f100a), bxVar7.b, bxVar7.c.b, byVar3.f, byVar3.f102a, byVar3.c, a3);
                                                break loop9;
                                            }
                                        }
                                        continue;
                                    }
                                }
                                if (ccVar == null) {
                                    this.b.b();
                                    return null;
                                }
                            }
                        }
                    }
                }
                this.b.b();
                return ccVar;
            }
            return biVar;
        } finally {
            this.b.b();
        }
    }
}
