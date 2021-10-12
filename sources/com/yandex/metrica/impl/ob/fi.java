package com.yandex.metrica.impl.ob;

import android.net.Uri;
import android.os.Build;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* access modifiers changed from: package-private */
public class fi extends fo {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, String> f1142a;

    public fi(String str, Map<String, String> map) {
        super(0, str, null);
        this.f1142a = map;
    }

    @Override // com.yandex.metrica.impl.ob.fu
    public String a() {
        String a2 = super.a();
        Map<String, String> map = this.f1142a;
        Uri.Builder buildUpon = Uri.parse(a2).buildUpon();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return buildUpon.build().toString();
    }

    @Override // com.yandex.metrica.impl.ob.fu
    public Map<String, String> b() {
        HashMap hashMap = new HashMap();
        String format = String.format(Locale.US, "%s.%s.%s", 2, 12, 20);
        String str = Build.DEVICE;
        String str2 = Build.VERSION.RELEASE;
        hashMap.put("User-agent", String.format(Locale.US, "com.yandex.mobile.pinning/%s (%s; Android %s)", format, str, str2));
        return hashMap;
    }
}
