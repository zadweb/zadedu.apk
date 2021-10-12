package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.p;
import java.util.LinkedList;

public class y extends aa<af> {

    /* renamed from: a  reason: collision with root package name */
    private final ao f1179a;
    private final ae b;
    private final am c;
    private final ar d;
    private final at e;
    private final aw f;
    private final bb g;
    private final au h;
    private final as i;
    private final av j;
    private final an k;
    private final aq l;
    private final ad m;
    private final ac n;
    private final ah o;
    private final aj p;

    public y(t tVar) {
        this.f1179a = new ao(tVar);
        this.b = new ae(tVar);
        this.c = new am(tVar);
        this.d = new ar(tVar);
        this.e = new at(tVar);
        this.f = new aw(tVar);
        this.g = new bb(tVar);
        this.h = new au(tVar);
        this.i = new as(tVar);
        this.j = new av(tVar);
        this.k = new an(tVar);
        this.l = new aq(tVar);
        this.m = new ad(tVar);
        this.n = new ac(tVar);
        this.o = new ah(tVar);
        this.p = new aj(tVar);
    }

    /* access modifiers changed from: package-private */
    @Override // com.yandex.metrica.impl.ob.aa
    public x<af> a(int i2) {
        LinkedList linkedList = new LinkedList();
        p.a a2 = p.a.a(i2);
        if (p.b(a2)) {
            linkedList.add(this.j);
        }
        if (p.a(a2)) {
            linkedList.add(this.e);
        }
        switch (AnonymousClass1.f1180a[a2.ordinal()]) {
            case 1:
                linkedList.add(this.o);
                linkedList.add(this.f);
                break;
            case 2:
                linkedList.add(this.f1179a);
                break;
            case 3:
                linkedList.add(this.f1179a);
                linkedList.add(this.d);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                linkedList.add(this.d);
                break;
            case 9:
                linkedList.add(this.f);
                linkedList.add(this.f1179a);
                break;
            case 10:
                linkedList.add(this.c);
                break;
            case 11:
                linkedList.add(this.p);
                break;
            case 12:
            case 13:
                linkedList.add(this.c);
                linkedList.add(this.d);
                linkedList.add(this.b);
                linkedList.add(this.g);
                break;
            case 14:
                linkedList.add(this.f);
                linkedList.add(this.f1179a);
                break;
            case 15:
                linkedList.add(this.h);
                break;
            case 16:
                linkedList.add(this.i);
                break;
            case 17:
                linkedList.add(this.l);
                break;
            case 18:
                linkedList.add(this.m);
                break;
            case 19:
                linkedList.add(this.n);
                break;
        }
        if (p.c(a2)) {
            linkedList.add(this.k);
        }
        return new w(linkedList);
    }

    /* renamed from: com.yandex.metrica.impl.ob.y$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1180a;

        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|(3:37|38|40)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(40:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00d8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            int[] iArr = new int[p.a.values().length];
            f1180a = iArr;
            iArr[p.a.EVENT_TYPE_ACTIVATION.ordinal()] = 1;
            f1180a[p.a.EVENT_TYPE_START.ordinal()] = 2;
            f1180a[p.a.EVENT_TYPE_REGULAR.ordinal()] = 3;
            f1180a[p.a.EVENT_TYPE_EXCEPTION_USER.ordinal()] = 4;
            f1180a[p.a.EVENT_TYPE_REFERRER_DEPRECATED.ordinal()] = 5;
            f1180a[p.a.EVENT_TYPE_STATBOX.ordinal()] = 6;
            f1180a[p.a.EVENT_TYPE_CUSTOM_EVENT.ordinal()] = 7;
            f1180a[p.a.EVENT_TYPE_APP_OPEN.ordinal()] = 8;
            f1180a[p.a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS.ordinal()] = 9;
            f1180a[p.a.EVENT_TYPE_PURGE_BUFFER.ordinal()] = 10;
            f1180a[p.a.EVENT_TYPE_NATIVE_CRASH.ordinal()] = 11;
            f1180a[p.a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED.ordinal()] = 12;
            f1180a[p.a.EVENT_TYPE_EXCEPTION_UNHANDLED.ordinal()] = 13;
            f1180a[p.a.EVENT_TYPE_IDENTITY.ordinal()] = 14;
            f1180a[p.a.EVENT_TYPE_SET_USER_INFO.ordinal()] = 15;
            f1180a[p.a.EVENT_TYPE_REPORT_USER_INFO.ordinal()] = 16;
            f1180a[p.a.EVENT_TYPE_REFERRER_RECEIVED.ordinal()] = 17;
            f1180a[p.a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED.ordinal()] = 18;
            try {
                f1180a[p.a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED.ordinal()] = 19;
            } catch (NoSuchFieldError unused) {
            }
        }
    }
}
