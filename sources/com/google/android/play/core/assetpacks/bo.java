package com.google.android.play.core.assetpacks;

import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class bo {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, Double> f92a = new HashMap();

    bo() {
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(String str) {
        this.f92a.put(str, Double.valueOf(0.0d));
    }

    /* access modifiers changed from: package-private */
    public final synchronized double b(String str) {
        Double d = this.f92a.get(str);
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    /* access modifiers changed from: package-private */
    public final synchronized double c(String str, cc ccVar) {
        double d;
        double d2 = (double) ((bi) ccVar).e;
        Double.isNaN(d2);
        double d3 = (double) ((bi) ccVar).f;
        Double.isNaN(d3);
        d = (d2 + 1.0d) / d3;
        this.f92a.put(str, Double.valueOf(d));
        return d;
    }
}
