package com.b.a.a.a.h.a;

import com.b.a.a.a.b.i;
import com.b.a.a.a.c.a;
import com.b.a.a.a.h.a.b;
import java.util.HashSet;
import org.json.JSONObject;

public class e extends a {
    public e(b.AbstractC0010b bVar, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(bVar, hashSet, jSONObject, d);
    }

    private void b(String str) {
        a a2 = a.a();
        if (a2 != null) {
            for (i iVar : a2.b()) {
                if (this.f47a.contains(iVar.g())) {
                    iVar.f().b(str, this.c);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String doInBackground(Object... objArr) {
        return this.b.toString();
    }

    /* access modifiers changed from: protected */
    @Override // com.b.a.a.a.h.a.b
    /* renamed from: a */
    public void onPostExecute(String str) {
        b(str);
        super.onPostExecute(str);
    }
}
