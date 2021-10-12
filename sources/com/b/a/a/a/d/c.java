package com.b.a.a.a.d;

import android.view.View;
import com.b.a.a.a.b.i;
import com.b.a.a.a.c.a;
import com.b.a.a.a.d.a;
import com.b.a.a.a.e.b;
import com.b.a.a.a.e.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class c implements a {

    /* renamed from: a  reason: collision with root package name */
    private final a f37a;

    public c(a aVar) {
        this.f37a = aVar;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<View> a() {
        View rootView;
        ArrayList<View> arrayList = new ArrayList<>();
        a a2 = a.a();
        if (a2 != null) {
            Collection<i> c = a2.c();
            IdentityHashMap identityHashMap = new IdentityHashMap((c.size() * 2) + 3);
            for (i iVar : c) {
                View h = iVar.h();
                if (h != null && f.c(h) && (rootView = h.getRootView()) != null && !identityHashMap.containsKey(rootView)) {
                    identityHashMap.put(rootView, rootView);
                    float a3 = f.a(rootView);
                    int size = arrayList.size();
                    while (size > 0 && f.a(arrayList.get(size - 1)) > a3) {
                        size--;
                    }
                    arrayList.add(size, rootView);
                }
            }
        }
        return arrayList;
    }

    @Override // com.b.a.a.a.d.a
    public JSONObject a(View view) {
        return b.a(0, 0, 0, 0);
    }

    @Override // com.b.a.a.a.d.a
    public void a(View view, JSONObject jSONObject, a.AbstractC0007a aVar, boolean z) {
        Iterator<View> it = a().iterator();
        while (it.hasNext()) {
            aVar.a(it.next(), this.f37a, jSONObject);
        }
    }
}
