package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class en implements ev {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1120a = en.class.getSimpleName();
    private final es b = new es();
    private File c;

    public en(String str, String str2) throws IOException {
        b(str, str2);
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public synchronized Set<String> a(String str) {
        return this.b.a(str);
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public synchronized void a(String str, String[] strArr) {
        if (this.b.a(str) == null) {
            long lastModified = this.c.lastModified();
            a(str, new HashSet(Arrays.asList(strArr)));
            this.c.setLastModified(lastModified);
        }
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public synchronized boolean a(String str, String str2) {
        boolean a2;
        a2 = this.b.a(str, str2);
        d();
        return a2;
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public synchronized void a(String str, Set<String> set) {
        this.b.a(str, set);
        d();
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public synchronized long a() {
        return this.c.lastModified();
    }

    @Override // com.yandex.metrica.impl.ob.ev
    public void b() {
        this.c.setLastModified(System.currentTimeMillis());
    }

    private synchronized void b(String str, String str2) throws IOException {
        Map<String, Set<String>> map;
        File file = new File(str, "sslpinningv1-" + str2);
        this.c = file;
        if (file.createNewFile()) {
            map = new HashMap<>();
            a(map);
            this.c.setLastModified(0);
        } else {
            map = c();
        }
        this.b.a(map);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0059 A[SYNTHETIC, Splitter:B:26:0x0059] */
    private synchronized Map<String, Set<String>> c() throws IOException {
        HashMap hashMap;
        Throwable th;
        hashMap = new HashMap();
        BufferedReader bufferedReader = null;
        HashSet hashSet = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(this.c)));
            try {
                for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                    if (readLine.contains("type-")) {
                        String substring = readLine.substring(5);
                        HashSet hashSet2 = new HashSet();
                        hashMap.put(substring, hashSet2);
                        hashSet = hashSet2;
                    } else if (!TextUtils.isEmpty(readLine)) {
                        hashSet.add(readLine);
                    }
                }
                try {
                    bufferedReader2.close();
                } catch (IOException e) {
                    Log.e(f1120a, e.getMessage());
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        Log.e(f1120a, e2.getMessage());
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader != null) {
            }
            throw th;
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007d A[SYNTHETIC, Splitter:B:30:0x007d] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008e A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0092 A[SYNTHETIC, Splitter:B:41:0x0092] */
    private synchronized void a(Map<String, Set<String>> map) {
        Throwable th;
        IOException e;
        BufferedWriter bufferedWriter = null;
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.c)));
            try {
                for (String str : map.keySet()) {
                    bufferedWriter2.write("type-" + str);
                    bufferedWriter2.newLine();
                    for (String str2 : map.get(str)) {
                        bufferedWriter2.write(str2);
                        bufferedWriter2.newLine();
                    }
                }
                try {
                    bufferedWriter2.close();
                } catch (IOException e2) {
                    Log.e(f1120a, e2.getMessage());
                }
            } catch (IOException e3) {
                e = e3;
                bufferedWriter = bufferedWriter2;
                try {
                    Log.e(f1120a, e.getMessage());
                    if (bufferedWriter == null) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e4) {
                            Log.e(f1120a, e4.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = bufferedWriter2;
                if (bufferedWriter != null) {
                }
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            Log.e(f1120a, e.getMessage());
            if (bufferedWriter == null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e6) {
                    Log.e(f1120a, e6.getMessage());
                }
            }
        }
    }

    private synchronized void d() {
        a(this.b.c());
    }
}
