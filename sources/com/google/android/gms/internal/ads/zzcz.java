package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzcz {
    private static final String TAG = zzcz.class.getSimpleName();
    private volatile boolean zzqt;
    protected Context zzrt;
    private ExecutorService zzru;
    private DexClassLoader zzrv;
    private zzck zzrw;
    private byte[] zzrx;
    private volatile AdvertisingIdClient zzry = null;
    private Future zzrz;
    private boolean zzsa;
    private volatile zzba zzsb;
    private Future zzsc;
    private zzcc zzsd;
    private boolean zzse;
    private boolean zzsf;
    private Map<Pair<String, String>, zzeg> zzsg;
    private boolean zzsh;
    private boolean zzsi;
    private boolean zzsj;

    final class zza extends BroadcastReceiver {
        private zza() {
        }

        /* synthetic */ zza(zzcz zzcz, zzda zzda) {
            this();
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                zzcz.this.zzsi = true;
            } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                zzcz.this.zzsi = false;
            }
        }
    }

    private zzcz(Context context) {
        boolean z = false;
        this.zzqt = false;
        this.zzrz = null;
        this.zzsb = null;
        this.zzsc = null;
        this.zzse = false;
        this.zzsf = false;
        this.zzsh = false;
        this.zzsi = true;
        this.zzsj = false;
        Context applicationContext = context.getApplicationContext();
        z = applicationContext != null ? true : z;
        this.zzsa = z;
        this.zzrt = z ? applicationContext : context;
        this.zzsg = new HashMap();
    }

    public static zzcz zza(Context context, String str, String str2, boolean z) {
        zzcz zzcz = new zzcz(context);
        try {
            zzcz.zzru = Executors.newCachedThreadPool(new zzda());
            zzcz.zzqt = z;
            if (z) {
                zzcz.zzrz = zzcz.zzru.submit(new zzdb(zzcz));
            }
            zzcz.zzru.execute(new zzdd(zzcz));
            try {
                GoogleApiAvailabilityLight instance = GoogleApiAvailabilityLight.getInstance();
                zzcz.zzse = instance.getApkVersion(zzcz.zzrt) > 0;
                zzcz.zzsf = instance.isGooglePlayServicesAvailable(zzcz.zzrt) == 0;
            } catch (Throwable unused) {
            }
            zzcz.zza(0, true);
            if (zzdg.isMainThread()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbaz)).booleanValue()) {
                    throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
                }
            }
            zzck zzck = new zzck(null);
            zzcz.zzrw = zzck;
            try {
                zzcz.zzrx = zzck.zzl(str);
                try {
                    File cacheDir = zzcz.zzrt.getCacheDir();
                    if (cacheDir == null) {
                        cacheDir = zzcz.zzrt.getDir("dex", 0);
                        if (cacheDir == null) {
                            throw new zzcw();
                        }
                    }
                    File file = new File(String.format("%s/%s.jar", cacheDir, "1521499837408"));
                    if (!file.exists()) {
                        byte[] zza2 = zzcz.zzrw.zza(zzcz.zzrx, str2);
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(zza2, 0, zza2.length);
                        fileOutputStream.close();
                    }
                    zzcz.zzb(cacheDir, "1521499837408");
                    try {
                        zzcz.zzrv = new DexClassLoader(file.getAbsolutePath(), cacheDir.getAbsolutePath(), null, zzcz.zzrt.getClassLoader());
                        zzb(file);
                        zzcz.zza(cacheDir, "1521499837408");
                        zzm(String.format("%s/%s.dex", cacheDir, "1521499837408"));
                        if (!zzcz.zzsj) {
                            IntentFilter intentFilter = new IntentFilter();
                            intentFilter.addAction("android.intent.action.USER_PRESENT");
                            intentFilter.addAction("android.intent.action.SCREEN_OFF");
                            zzcz.zzrt.registerReceiver(new zza(zzcz, null), intentFilter);
                            zzcz.zzsj = true;
                        }
                        zzcz.zzsd = new zzcc(zzcz);
                        zzcz.zzsh = true;
                        return zzcz;
                    } catch (Throwable th) {
                        zzb(file);
                        zzcz.zza(cacheDir, "1521499837408");
                        zzm(String.format("%s/%s.dex", cacheDir, "1521499837408"));
                        throw th;
                    }
                } catch (FileNotFoundException e) {
                    throw new zzcw(e);
                } catch (IOException e2) {
                    throw new zzcw(e2);
                } catch (zzcl e3) {
                    throw new zzcw(e3);
                } catch (NullPointerException e4) {
                    throw new zzcw(e4);
                }
            } catch (zzcl e5) {
                throw new zzcw(e5);
            }
        } catch (zzcw unused2) {
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:20|21|22|23|24|25|26|27|28|30) */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009e, code lost:
        r9 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0091 */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[ExcHandler: zzcl | IOException | NoSuchAlgorithmException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:20:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a5 A[SYNTHETIC, Splitter:B:42:0x00a5] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ac A[SYNTHETIC, Splitter:B:46:0x00ac] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00b6 A[SYNTHETIC, Splitter:B:54:0x00b6] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00bd A[SYNTHETIC, Splitter:B:58:0x00bd] */
    private final void zza(File file, String str) {
        FileOutputStream fileOutputStream;
        Throwable th;
        zzbe zzbe;
        File file2 = new File(String.format("%s/%s.tmp", file, str));
        if (!file2.exists()) {
            File file3 = new File(String.format("%s/%s.dex", file, str));
            if (file3.exists()) {
                long length = file3.length();
                if (length > 0) {
                    byte[] bArr = new byte[((int) length)];
                    FileInputStream fileInputStream = null;
                    try {
                        FileInputStream fileInputStream2 = new FileInputStream(file3);
                        try {
                            if (fileInputStream2.read(bArr) <= 0) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException unused) {
                                }
                                zzb(file3);
                                return;
                            }
                            try {
                                zzbe = new zzbe();
                                zzbe.zzgs = Build.VERSION.SDK.getBytes();
                                zzbe.zzgr = str.getBytes();
                                byte[] bytes = this.zzrw.zzb(this.zzrx, bArr).getBytes();
                                zzbe.data = bytes;
                                zzbe.zzgq = zzbk.zzb(bytes);
                                file2.createNewFile();
                                fileOutputStream = new FileOutputStream(file2);
                            } catch (zzcl | IOException | NoSuchAlgorithmException unused2) {
                            }
                            try {
                                byte[] zzb = zzbfi.zzb(zzbe);
                                fileOutputStream.write(zzb, 0, zzb.length);
                                fileOutputStream.close();
                                fileInputStream2.close();
                                fileOutputStream.close();
                                zzb(file3);
                            } catch (zzcl | IOException | NoSuchAlgorithmException unused3) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException unused4) {
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException unused5) {
                                    }
                                }
                                zzb(file3);
                            } catch (Throwable th2) {
                                th = th2;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException unused6) {
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException unused7) {
                                    }
                                }
                                zzb(file3);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream = null;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                            }
                            if (fileOutputStream != null) {
                            }
                            zzb(file3);
                            throw th;
                        }
                    } catch (zzcl | IOException | NoSuchAlgorithmException unused8) {
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        zzb(file3);
                    } catch (Throwable th4) {
                        th = th4;
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        zzb(file3);
                        throw th;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean zza(int i, zzba zzba) {
        if (i >= 4) {
            return false;
        }
        if (zzba == null) {
            return true;
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbbc)).booleanValue() && (zzba.zzcx == null || zzba.zzcx.equals("0000000000000000000000000000000000000000000000000000000000000000"))) {
            return true;
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbbd)).booleanValue()) {
            return zzba.zzfn == null || zzba.zzfn.zzgl == null || zzba.zzfn.zzgl.longValue() == -2;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public final void zzal() {
        try {
            if (this.zzry == null && this.zzsa) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzrt);
                advertisingIdClient.start();
                this.zzry = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.zzry = null;
        }
    }

    private final zzba zzam() {
        try {
            return zzatq.zzl(this.zzrt, this.zzrt.getPackageName(), Integer.toString(this.zzrt.getPackageManager().getPackageInfo(this.zzrt.getPackageName(), 0).versionCode));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static void zzb(File file) {
        if (!file.exists()) {
            Log.d(TAG, String.format("File %s not found. No need for deletion", file.getAbsolutePath()));
            return;
        }
        file.delete();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:29|30|31|32|33|34|35|36) */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c2, code lost:
        r0 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00b1 */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[ExcHandler: zzcl | IOException | NoSuchAlgorithmException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:21:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c9 A[SYNTHETIC, Splitter:B:54:0x00c9] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d0 A[SYNTHETIC, Splitter:B:58:0x00d0] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00d7 A[SYNTHETIC, Splitter:B:65:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00de A[SYNTHETIC, Splitter:B:69:0x00de] */
    private final boolean zzb(File file, String str) {
        FileOutputStream fileOutputStream;
        Throwable th;
        File file2 = new File(String.format("%s/%s.tmp", file, str));
        if (!file2.exists()) {
            return false;
        }
        File file3 = new File(String.format("%s/%s.dex", file, str));
        if (file3.exists()) {
            return false;
        }
        FileInputStream fileInputStream = null;
        try {
            long length = file2.length();
            if (length <= 0) {
                zzb(file2);
                return false;
            }
            byte[] bArr = new byte[((int) length)];
            FileInputStream fileInputStream2 = new FileInputStream(file2);
            try {
                if (fileInputStream2.read(bArr) <= 0) {
                    Log.d(TAG, "Cannot read the cache data.");
                    zzb(file2);
                    try {
                        fileInputStream2.close();
                    } catch (IOException unused) {
                    }
                    return false;
                }
                try {
                    zzbe zzbe = (zzbe) zzbfi.zza(new zzbe(), bArr);
                    if (str.equals(new String(zzbe.zzgr)) && Arrays.equals(zzbe.zzgq, zzbk.zzb(zzbe.data))) {
                        if (Arrays.equals(zzbe.zzgs, Build.VERSION.SDK.getBytes())) {
                            byte[] zza2 = this.zzrw.zza(this.zzrx, new String(zzbe.data));
                            file3.createNewFile();
                            fileOutputStream = new FileOutputStream(file3);
                            try {
                                fileOutputStream.write(zza2, 0, zza2.length);
                                fileInputStream2.close();
                                fileOutputStream.close();
                                return true;
                            } catch (zzcl | IOException | NoSuchAlgorithmException unused2) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                }
                                if (fileOutputStream != null) {
                                }
                                return false;
                            } catch (Throwable th2) {
                                th = th2;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                }
                                if (fileOutputStream != null) {
                                }
                                throw th;
                            }
                        }
                    }
                    zzb(file2);
                    try {
                        fileInputStream2.close();
                    } catch (IOException unused3) {
                    }
                    return false;
                } catch (zzcl | IOException | NoSuchAlgorithmException unused4) {
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                }
                if (fileOutputStream != null) {
                }
                throw th;
            }
        } catch (zzcl | IOException | NoSuchAlgorithmException unused5) {
            fileOutputStream = null;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused6) {
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused7) {
                }
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused8) {
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused9) {
                }
            }
            throw th;
        }
    }

    private static void zzm(String str) {
        zzb(new File(str));
    }

    public final Context getContext() {
        return this.zzrt;
    }

    public final boolean isInitialized() {
        return this.zzsh;
    }

    public final Method zza(String str, String str2) {
        zzeg zzeg = this.zzsg.get(new Pair(str, str2));
        if (zzeg == null) {
            return null;
        }
        return zzeg.zzaw();
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, boolean z) {
        if (this.zzsf) {
            Future<?> submit = this.zzru.submit(new zzdc(this, i, z));
            if (i == 0) {
                this.zzsc = submit;
            }
        }
    }

    public final boolean zza(String str, String str2, Class<?>... clsArr) {
        if (this.zzsg.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzsg.put(new Pair<>(str, str2), new zzeg(this, str, str2, clsArr));
        return true;
    }

    public final ExecutorService zzab() {
        return this.zzru;
    }

    public final DexClassLoader zzac() {
        return this.zzrv;
    }

    public final zzck zzad() {
        return this.zzrw;
    }

    public final byte[] zzae() {
        return this.zzrx;
    }

    public final boolean zzaf() {
        return this.zzse;
    }

    public final zzcc zzag() {
        return this.zzsd;
    }

    public final boolean zzah() {
        return this.zzsf;
    }

    public final boolean zzai() {
        return this.zzsi;
    }

    public final zzba zzaj() {
        return this.zzsb;
    }

    public final Future zzak() {
        return this.zzsc;
    }

    public final AdvertisingIdClient zzan() {
        if (!this.zzqt) {
            return null;
        }
        if (this.zzry != null) {
            return this.zzry;
        }
        Future future = this.zzrz;
        if (future != null) {
            try {
                future.get(2000, TimeUnit.MILLISECONDS);
                this.zzrz = null;
            } catch (InterruptedException | ExecutionException unused) {
            } catch (TimeoutException unused2) {
                this.zzrz.cancel(true);
            }
        }
        return this.zzry;
    }

    /* access modifiers changed from: package-private */
    public final zzba zzb(int i, boolean z) {
        if (i > 0 && z) {
            try {
                Thread.sleep((long) (i * 1000));
            } catch (InterruptedException unused) {
            }
        }
        return zzam();
    }

    public final int zzx() {
        return this.zzsd != null ? zzcc.zzx() : RecyclerView.UNDEFINED_DURATION;
    }
}
