package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

/* access modifiers changed from: package-private */
public class cz implements cv {

    /* renamed from: a  reason: collision with root package name */
    private final Context f1078a;

    public cz(Context context) {
        this.f1078a = context;
    }

    @Override // com.yandex.metrica.impl.ob.cv
    public List<cw> a() {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : this.f1078a.getPackageManager().getPackageInfo(this.f1078a.getPackageName(), 4096).requestedPermissions) {
                arrayList.add(new cw(str, true));
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return arrayList;
    }
}
