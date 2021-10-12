package com.yandex.metrica.impl.ob;

import android.content.Context;
import com.appnext.base.b.c;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ck {

    /* renamed from: a  reason: collision with root package name */
    private ci f1064a;

    public ck(ci ciVar) {
        this.f1064a = ciVar;
    }

    public String a(Context context) {
        return b(context);
    }

    /* access modifiers changed from: package-private */
    public String b(Context context) {
        cj cjVar = new cj(context);
        List<be.a> c = c(context);
        ArrayList<ce> arrayList = new ArrayList(c.size());
        LinkedList linkedList = new LinkedList();
        Iterator<be.a> it = c.iterator();
        while (true) {
            Object obj = null;
            if (!it.hasNext()) {
                break;
            }
            be.a next = it.next();
            if (be.a(next.d) < 29) {
                linkedList.add(next);
            } else {
                if (this.f1064a.e()) {
                    String str = next.d.applicationInfo.packageName;
                    ch a2 = this.f1064a.a(context, str);
                    ch b = this.f1064a.b(context, str);
                    if (!(a2 == null && b == null)) {
                        obj = new cg(next, b, a2);
                    }
                } else {
                    ch a3 = this.f1064a.a(context, next.d.applicationInfo.packageName);
                    if (a3 != null && !bi.a(a3.c())) {
                        obj = new ce(next, a3);
                    }
                }
                if (obj != null) {
                    arrayList.add(obj);
                }
            }
        }
        Iterator it2 = linkedList.iterator();
        while (it2.hasNext()) {
            be.a aVar = (be.a) it2.next();
            String f = this.f1064a.f(context, aVar.d.packageName);
            if (!bi.a(f)) {
                arrayList.add(new ce(aVar, new ch(f, null, -1)));
            }
        }
        if (!arrayList.isEmpty()) {
            HashMap hashMap = new HashMap();
            for (ce ceVar : arrayList) {
                String a4 = ceVar.a();
                cf cfVar = (cf) hashMap.get(a4);
                if (cfVar == null) {
                    cfVar = new cf(a4, cjVar);
                    hashMap.put(a4, cfVar);
                }
                cfVar.a(ceVar);
            }
            if (hashMap.size() == 1) {
                Iterator it3 = hashMap.values().iterator();
                if (it3.hasNext()) {
                    return ((cf) it3.next()).c();
                }
                final StringBuilder sb = new StringBuilder();
                sb.append("Smth wrong when iterate through initial candidates list");
                sb.append('\n');
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", new HashMap<String, Object>() {
                    /* class com.yandex.metrica.impl.ob.ck.AnonymousClass2 */

                    {
                        put("error", sb.toString());
                    }
                });
            } else {
                c(context, arrayList);
                return a(context, hashMap);
            }
        }
        return "";
    }

    private String a(Context context, Map<String, cf> map) {
        a(context, "method_carriers_count", map.size());
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (cf cfVar : map.values()) {
            int b = cfVar.b();
            if (b > i) {
                arrayList.clear();
                arrayList.add(cfVar);
                i = b;
            } else if (b == i) {
                arrayList.add(cfVar);
            }
        }
        if (arrayList.size() == 1) {
            return ((cf) arrayList.get(0)).c();
        }
        String a2 = ((cf) arrayList.get(0)).b() == 1 ? a(context, (ArrayList<cf>) arrayList) : null;
        if (a2 != null) {
            return a2;
        }
        List<cf> a3 = a(arrayList);
        if (a3 == null) {
            return a(context, (List<cf>) arrayList);
        }
        return a(context, a3);
    }

    private static List<cf> a(List<cf> list) {
        ArrayList arrayList = new ArrayList();
        for (cf cfVar : list) {
            if (!cfVar.a()) {
                arrayList.add(cfVar);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    private static String a(Context context, ArrayList<cf> arrayList) {
        String packageName = context.getPackageName();
        Iterator<cf> it = arrayList.iterator();
        cf cfVar = null;
        cf cfVar2 = null;
        while (it.hasNext()) {
            cf next = it.next();
            if (packageName.equals(next.d().get(0).c().e)) {
                cfVar = next;
            } else {
                cfVar2 = next;
            }
        }
        if (cfVar == null) {
            return null;
        }
        if (!cfVar2.a()) {
            return cfVar2.c();
        }
        if (cfVar.a()) {
            return cfVar2.c();
        }
        return cfVar.c();
    }

    /* access modifiers changed from: package-private */
    public String a(Context context, List<cf> list) {
        a(context, "method_first_installed", list.size());
        ArrayList arrayList = new ArrayList();
        long j = Long.MAX_VALUE;
        for (cf cfVar : list) {
            Long e = cfVar.e();
            int compareTo = e.compareTo(j);
            if (compareTo < 0) {
                arrayList.clear();
                arrayList.add(cfVar);
                j = e;
            } else if (compareTo == 0) {
                arrayList.add(cfVar);
            }
        }
        if (arrayList.size() == 1) {
            return ((cf) arrayList.get(0)).c();
        }
        return b(context, arrayList);
    }

    private static String b(Context context, List<cf> list) {
        a(context, "method_device_id_comparing", list.size());
        String str = "";
        for (cf cfVar : list) {
            if (cfVar.c().compareTo(str) > 0) {
                str = cfVar.c();
            }
        }
        return str;
    }

    private static void c(Context context, List<ce> list) {
        final StringBuilder sb = new StringBuilder();
        for (ce ceVar : list) {
            sb.append(ceVar.c().d.packageName);
            sb.append(" ");
            sb.append(ceVar.toString());
            sb.append('\n');
        }
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", new HashMap<String, Object>() {
            /* class com.yandex.metrica.impl.ob.ck.AnonymousClass1 */

            {
                put(c.DATA, sb.toString());
            }
        });
    }

    /* access modifiers changed from: package-private */
    public List<be.a> c(Context context) {
        return be.b(context);
    }

    private static void a(Context context, final String str, final int i) {
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", new HashMap<String, Object>() {
            /* class com.yandex.metrica.impl.ob.ck.AnonymousClass3 */

            {
                put(str, new HashMap<String, Object>() {
                    /* class com.yandex.metrica.impl.ob.ck.AnonymousClass3.AnonymousClass1 */

                    {
                        put("candidates_count", Integer.valueOf(i));
                    }
                });
            }
        });
    }
}
