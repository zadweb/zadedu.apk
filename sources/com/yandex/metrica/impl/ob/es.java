package com.yandex.metrica.impl.ob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/* access modifiers changed from: package-private */
public class es implements ev {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, Set<String>> f1124a = new ConcurrentHashMap();
    private volatile AtomicLong b = new AtomicLong();

    @Override // com.yandex.metrica.impl.ob.ev
    public void a(String str, String[] strArr) {
        if (!this.f1124a.keySet().contains(str)) {
            this.f1124a.put(str, new HashSet(Arrays.asList(strArr)));
            d();
        }
    }

    public Map<String, Set<String>> c() {
        HashMap hashMap = new HashMap();
        for (String str : this.f1124a.keySet()) {
            hashMap.put(str, a(str));
        }
        return hashMap;
    }

    public void a(Map<String, Set<String>> map) {
        this.f1124a = new ConcurrentHashMap(map);
        d();
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public Set<String> a(String str) {
        Set<String> set = this.f1124a.get(str);
        if (set == null) {
            return null;
        }
        return new HashSet(set);
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public boolean a(String str, String str2) {
        Set<String> set = this.f1124a.get(str);
        if (set == null) {
            set = new HashSet<>();
            this.f1124a.put(str, set);
        }
        d();
        return set.add(str2);
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public void a(String str, Set<String> set) {
        this.f1124a.put(str, new HashSet(set));
        d();
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public long a() {
        return this.b.get();
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public void b() {
        d();
    }

    private void d() {
        this.b.set(System.currentTimeMillis());
    }
}
