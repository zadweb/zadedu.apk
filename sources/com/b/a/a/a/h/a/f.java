package com.b.a.a.a.h.a;

import android.text.TextUtils;
import com.b.a.a.a.b.i;
import com.b.a.a.a.c.a;
import com.b.a.a.a.h.a.b;
import java.util.HashSet;
import org.json.JSONObject;

public class f extends a {
    public f(b.AbstractC0010b bVar, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(bVar, hashSet, jSONObject, d);
    }

    private void b(String str) {
        a a2 = a.a();
        if (a2 != null) {
            for (i iVar : a2.b()) {
                if (this.f47a.contains(iVar.g())) {
                    iVar.f().a(str, this.c);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String doInBackground(Object... objArr) {
        if (com.b.a.a.a.e.b.b(this.b, this.d.a())) {
            return null;
        }
        this.d.a(this.b);
        return this.b.toString();
    }

    /* access modifiers changed from: protected */
    @Override // com.b.a.a.a.h.a.b
    /* renamed from: a */
    public void onPostExecute(String str) {
        if (!TextUtils.isEmpty(str)) {
            b(str);
        }
        super.onPostExecute(str);
    }
}
