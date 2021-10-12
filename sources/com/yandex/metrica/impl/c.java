package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Base64;
import com.yandex.metrica.impl.utils.g;
import com.yandex.metrica.impl.utils.i;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final long f996a = TimeUnit.SECONDS.toMillis(15);
    private final Context b;
    private long c = 0;

    public c(Context context) {
        this.b = context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00de, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00df, code lost:
        r2 = null;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00eb, code lost:
        r0 = null;
        r2 = null;
        r3 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00de A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x000e] */
    public synchronized HashMap<String, String> a() {
        HashMap<String, String> hashMap;
        HashMap<String, String> hashMap2;
        FileChannel fileChannel;
        FileChannel fileChannel2;
        Throwable th;
        File file;
        boolean z;
        FileLock lock;
        String absolutePath = this.b.getFileStreamPath("b_meta.dat").getAbsolutePath();
        FileLock fileLock = null;
        try {
            file = new File(absolutePath);
            z = false;
            if (!file.exists()) {
                file.createNewFile();
                file.setReadable(true, false);
                File absoluteFile = this.b.getFileStreamPath("browsers.dat").getAbsoluteFile();
                if (absoluteFile.exists() && absoluteFile.canWrite()) {
                    absoluteFile.delete();
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th2) {
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(absolutePath, "rw");
        try {
            fileChannel = randomAccessFile.getChannel();
            try {
                lock = fileChannel.lock();
            } catch (Exception unused2) {
                hashMap2 = null;
                r.a(fileLock);
                bk.a(randomAccessFile);
                bk.a(fileChannel);
                hashMap = hashMap2;
                return hashMap;
            } catch (Throwable th3) {
                fileChannel2 = fileChannel;
                th = th3;
                r.a(fileLock);
                bk.a(randomAccessFile);
                bk.a(fileChannel2);
                throw th;
            }
        } catch (Exception unused3) {
            fileChannel = null;
            hashMap2 = null;
            r.a(fileLock);
            bk.a(randomAccessFile);
            bk.a(fileChannel);
            hashMap = hashMap2;
            return hashMap;
        } catch (Throwable th4) {
            th = th4;
            fileChannel2 = null;
            r.a(fileLock);
            bk.a(randomAccessFile);
            bk.a(fileChannel2);
            throw th;
        }
        try {
            ByteBuffer allocate = ByteBuffer.allocate((int) file.length());
            fileChannel.read(allocate);
            allocate.flip();
            hashMap = b(a(allocate.array()));
            if (System.currentTimeMillis() - this.c > f996a) {
                z = true;
            }
            if (z) {
                a(hashMap);
                this.c = System.currentTimeMillis();
                JSONObject jSONObject = new JSONObject();
                jSONObject.putOpt("browser_open_times", g.a((Map) hashMap));
                jSONObject.putOpt("last_sync_time", Long.valueOf(this.c));
                byte[] bytes = a(jSONObject.toString()).getBytes("UTF-8");
                ByteBuffer allocate2 = ByteBuffer.allocate(bytes.length);
                allocate2.put(bytes);
                allocate2.flip();
                fileChannel.position(0L);
                fileChannel.truncate(0L);
                fileChannel.write(allocate2);
                fileChannel.force(true);
            }
            r.a(lock);
            bk.a(randomAccessFile);
            bk.a(fileChannel);
        } catch (Exception unused4) {
            hashMap2 = null;
            fileLock = lock;
            r.a(fileLock);
            bk.a(randomAccessFile);
            bk.a(fileChannel);
            hashMap = hashMap2;
            return hashMap;
        } catch (Throwable th5) {
            fileChannel2 = fileChannel;
            th = th5;
            fileLock = lock;
            r.a(fileLock);
            bk.a(randomAccessFile);
            bk.a(fileChannel2);
            throw th;
        }
        return hashMap;
    }

    private HashMap<String, String> b(String str) {
        HashMap<String, String> hashMap = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = g.a(jSONObject.optString("browser_open_times"));
                this.c = jSONObject.optLong("last_sync_time", 0);
            }
        } catch (JSONException unused) {
        }
        if (hashMap != null) {
            return hashMap;
        }
        return new HashMap<>();
    }

    private void a(HashMap<String, String> hashMap) {
        for (String str : b()) {
            a(hashMap, a(new File(str)));
        }
    }

    private HashMap<String, String> a(File file) {
        byte[] b2;
        try {
            if (file.exists() && (b2 = r.b(this.b, file)) != null) {
                String a2 = a(b2);
                file.getName();
                return b(a2);
            }
        } catch (UnsupportedEncodingException unused) {
        }
        return new HashMap<>(0);
    }

    /* access modifiers changed from: package-private */
    public List<String> b() {
        Context context = this.b;
        List<ResolveInfo> a2 = be.a(context, be.a(context));
        ArrayList arrayList = new ArrayList();
        String packageName = this.b.getPackageName();
        for (ResolveInfo resolveInfo : a2) {
            String str = resolveInfo.serviceInfo.applicationInfo.packageName;
            if (!packageName.equals(str) && be.a(resolveInfo.serviceInfo) >= 47) {
                try {
                    arrayList.add(this.b.getFileStreamPath("b_meta.dat").getAbsolutePath().replace(this.b.getApplicationInfo().dataDir, this.b.getPackageManager().getApplicationInfo(str, 8192).dataDir));
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
        }
        return arrayList;
    }

    private static boolean a(Map<String, String> map, Map<String, String> map2) {
        boolean z = false;
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            String key = entry.getKey();
            long a2 = i.a(entry.getValue(), 0L);
            long a3 = i.a(map.get(key), 0L);
            if (a2 > 0 && a3 < a2) {
                map.put(key, String.valueOf(a2));
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public String a(String str) throws UnsupportedEncodingException {
        return Base64.encodeToString(b(r.b(str).getBytes("UTF-8")), 0);
    }

    /* access modifiers changed from: package-private */
    public String a(byte[] bArr) throws UnsupportedEncodingException {
        return r.c(new String(b(Base64.decode(bArr, 0)), "UTF-8"));
    }

    private byte[] b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(this.b.getPackageName().getBytes("UTF-8"));
            byte[] digest = instance.digest();
            byte[] bArr2 = new byte[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                bArr2[i] = (byte) (bArr[i] ^ digest[i % digest.length]);
            }
            return bArr2;
        } catch (Exception unused) {
            return null;
        }
    }
}
