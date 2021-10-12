package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.p;
import java.util.LinkedList;

public class z extends aa<af> {

    /* renamed from: a  reason: collision with root package name */
    private final bb f1181a;
    private final ba b;
    private final az c;
    private final ap d;
    private final ax e;
    private final ak f;

    public z(t tVar) {
        this.f1181a = new bb(tVar);
        this.b = new ba(tVar);
        this.c = new az(tVar);
        this.d = new ap(tVar);
        this.e = new ax(tVar);
        this.f = new ak(tVar);
    }

    /* access modifiers changed from: package-private */
    @Override // com.yandex.metrica.impl.ob.aa
    public x<af> a(int i) {
        LinkedList linkedList = new LinkedList();
        switch (AnonymousClass1.f1182a[p.a.a(i).ordinal()]) {
            case 1:
                linkedList.add(this.e);
                break;
            case 2:
                linkedList.add(this.e);
                linkedList.add(this.d);
                break;
            case 3:
                linkedList.add(this.f1181a);
                linkedList.add(this.b);
                linkedList.add(this.c);
                break;
            case 4:
            case 5:
                linkedList.add(this.d);
                break;
            case 6:
                linkedList.add(this.f);
                break;
        }
        return new w(linkedList);
    }

    /* renamed from: com.yandex.metrica.impl.ob.z$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1182a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            int[] iArr = new int[p.a.values().length];
            f1182a = iArr;
            iArr[p.a.EVENT_TYPE_ACTIVITY_START_DEPRECATED.ordinal()] = 1;
            f1182a[p.a.EVENT_TYPE_START.ordinal()] = 2;
            f1182a[p.a.EVENT_TYPE_SESSION_START_MANUALLY.ordinal()] = 3;
            f1182a[p.a.EVENT_TYPE_INIT.ordinal()] = 4;
            f1182a[p.a.EVENT_TYPE_INIT_BACKGROUND.ordinal()] = 5;
            try {
                f1182a[p.a.EVENT_TYPE_ACTIVITY_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused) {
            }
        }
    }
}
