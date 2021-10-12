package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import com.appnext.base.b.d;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class zzaj implements zzm {
    private static final boolean DEBUG = zzaf.DEBUG;
    @Deprecated
    private final zzar zzbo;
    private final zzai zzbp;
    private final zzak zzbq;

    public zzaj(zzai zzai) {
        this(zzai, new zzak(4096));
    }

    private zzaj(zzai zzai, zzak zzak) {
        this.zzbp = zzai;
        this.zzbo = zzai;
        this.zzbq = zzak;
    }

    @Deprecated
    public zzaj(zzar zzar) {
        this(zzar, new zzak(4096));
    }

    @Deprecated
    private zzaj(zzar zzar, zzak zzak) {
        this.zzbo = zzar;
        this.zzbp = new zzah(zzar);
        this.zzbq = zzak;
    }

    private static void zza(String str, zzr<?> zzr, zzae zzae) throws zzae {
        zzab zzj = zzr.zzj();
        int zzi = zzr.zzi();
        try {
            zzj.zza(zzae);
            zzr.zzb(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(zzi)));
        } catch (zzae e) {
            zzr.zzb(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(zzi)));
            throw e;
        }
    }

    private final byte[] zza(InputStream inputStream, int i) throws IOException, zzac {
        zzau zzau = new zzau(this.zzbq, i);
        if (inputStream != null) {
            try {
                byte[] zzb = this.zzbq.zzb(d.fb);
                while (true) {
                    int read = inputStream.read(zzb);
                    if (read == -1) {
                        break;
                    }
                    zzau.write(zzb, 0, read);
                }
                byte[] byteArray = zzau.toByteArray();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                        zzaf.v("Error occurred when closing InputStream", new Object[0]);
                    }
                }
                this.zzbq.zza(zzb);
                zzau.close();
                return byteArray;
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                        zzaf.v("Error occurred when closing InputStream", new Object[0]);
                    }
                }
                this.zzbq.zza(null);
                zzau.close();
                throw th;
            }
        } else {
            throw new zzac();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0201, code lost:
        throw new com.google.android.gms.internal.ads.zzq(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0202, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0203, code lost:
        r2 = java.lang.String.valueOf(r22.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0213, code lost:
        if (r2.length() != 0) goto L_0x0215;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0215, code lost:
        r2 = "Bad URL ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x021a, code lost:
        r2 = new java.lang.String("Bad URL ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0222, code lost:
        throw new java.lang.RuntimeException(r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0223, code lost:
        r0 = new com.google.android.gms.internal.ads.zzad();
        r5 = "socket";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x018d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x018e, code lost:
        r17 = r5;
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0192, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0193, code lost:
        r17 = r5;
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0196, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0199, code lost:
        r0 = r8.getStatusCode();
        com.google.android.gms.internal.ads.zzaf.e("Unexpected response code %d for %s", java.lang.Integer.valueOf(r0), r22.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01b0, code lost:
        if (r13 != null) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01b2, code lost:
        r5 = new com.google.android.gms.internal.ads.zzp(r0, r13, false, android.os.SystemClock.elapsedRealtime() - r3, r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01c2, code lost:
        if (r0 == 401) goto L_0x01ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01cb, code lost:
        if (r0 < 400) goto L_0x01d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01d7, code lost:
        throw new com.google.android.gms.internal.ads.zzg(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01da, code lost:
        if (r0 < 500) goto L_0x01e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01e5, code lost:
        throw new com.google.android.gms.internal.ads.zzac(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01eb, code lost:
        throw new com.google.android.gms.internal.ads.zzac(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01ec, code lost:
        r0 = new com.google.android.gms.internal.ads.zza(r5);
        r5 = "auth";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01f4, code lost:
        r0 = new com.google.android.gms.internal.ads.zzo();
        r5 = "network";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0202 A[ExcHandler: MalformedURLException (r0v2 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:110:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01fc A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0199  */
    @Override // com.google.android.gms.internal.ads.zzm
    public zzp zzc(zzr<?> zzr) throws zzae {
        String str;
        zzae zzae;
        Map<String, String> map;
        zzaq zza;
        List<zzl> zzq;
        byte[] zza2;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            List<zzl> emptyList = Collections.emptyList();
            try {
                zzc zzf = zzr.zzf();
                if (zzf == null) {
                    map = Collections.emptyMap();
                } else {
                    HashMap hashMap = new HashMap();
                    if (zzf.zza != null) {
                        hashMap.put("If-None-Match", zzf.zza);
                    }
                    if (zzf.zzc > 0) {
                        hashMap.put("If-Modified-Since", zzap.zzb(zzf.zzc));
                    }
                    map = hashMap;
                }
                zza = this.zzbp.zza(zzr, map);
                int statusCode = zza.getStatusCode();
                zzq = zza.zzq();
                if (statusCode == 304) {
                    zzc zzf2 = zzr.zzf();
                    if (zzf2 == null) {
                        return new zzp(304, (byte[]) null, true, SystemClock.elapsedRealtime() - elapsedRealtime, zzq);
                    }
                    TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                    if (!zzq.isEmpty()) {
                        for (zzl zzl : zzq) {
                            treeSet.add(zzl.getName());
                        }
                    }
                    ArrayList arrayList = new ArrayList(zzq);
                    if (zzf2.zzg != null) {
                        if (!zzf2.zzg.isEmpty()) {
                            for (zzl zzl2 : zzf2.zzg) {
                                if (!treeSet.contains(zzl2.getName())) {
                                    arrayList.add(zzl2);
                                }
                            }
                        }
                    } else if (!zzf2.zzf.isEmpty()) {
                        for (Map.Entry<String, String> entry : zzf2.zzf.entrySet()) {
                            if (!treeSet.contains(entry.getKey())) {
                                arrayList.add(new zzl(entry.getKey(), entry.getValue()));
                            }
                        }
                    }
                    return new zzp(304, zzf2.data, true, SystemClock.elapsedRealtime() - elapsedRealtime, (List<zzl>) arrayList);
                }
                InputStream content = zza.getContent();
                zza2 = content != null ? zza(content, zza.getContentLength()) : new byte[0];
                long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
                if (DEBUG || elapsedRealtime2 > 3000) {
                    Object[] objArr = new Object[5];
                    objArr[0] = zzr;
                    objArr[1] = Long.valueOf(elapsedRealtime2);
                    objArr[2] = zza2 != null ? Integer.valueOf(zza2.length) : "null";
                    objArr[3] = Integer.valueOf(statusCode);
                    objArr[4] = Integer.valueOf(zzr.zzj().zzd());
                    zzaf.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
                }
                if (statusCode >= 200 && statusCode <= 299) {
                    return new zzp(statusCode, zza2, false, SystemClock.elapsedRealtime() - elapsedRealtime, zzq);
                }
                throw new IOException();
            } catch (SocketTimeoutException unused) {
            } catch (MalformedURLException e) {
            } catch (IOException e2) {
                IOException e3 = e2;
                List<zzl> list = zzq;
                byte[] bArr = zza2;
                zzaq zzaq = zza;
                if (zzaq != null) {
                }
            }
            zza(str, zzr, zzae);
        }
    }
}
