package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/* access modifiers changed from: package-private */
public class fe {

    /* renamed from: a  reason: collision with root package name */
    private String f1137a;
    private String b;
    private String c;

    fe(Context context) {
        try {
            this.f1137a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            this.f1137a = "0.0";
        }
        this.b = context.getFilesDir().getAbsolutePath();
        this.c = context.getPackageName();
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.f1137a;
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public fs a(List<X509Certificate> list) throws GeneralSecurityException, IOException {
        return fg.a(list);
    }

    /* access modifiers changed from: package-private */
    public fs d() throws GeneralSecurityException, IOException {
        ArrayList arrayList = new ArrayList();
        for (String str : a.a()) {
            arrayList.add(ex.a(str));
        }
        return fg.a(arrayList);
    }
}
