package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.IOUtils;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafn extends zzaeo {
    private static final Object sLock = new Object();
    private static zzafn zzchh;
    private final Context mContext;
    private final zzafm zzchi;
    private final ScheduledExecutorService zzchj = Executors.newSingleThreadScheduledExecutor();

    private zzafn(Context context, zzafm zzafm) {
        this.mContext = context;
        this.zzchi = zzafm;
    }

    private static zzaej zza(Context context, zzafm zzafm, zzaef zzaef, ScheduledExecutorService scheduledExecutorService) {
        zznx zznx;
        String string;
        zzakb.zzck("Starting ad request from service using: google.afma.request.getAdDictionary");
        zznx zznx2 = new zznx(((Boolean) zzkb.zzik().zzd(zznk.zzawh)).booleanValue(), "load_ad", zzaef.zzacv.zzarb);
        if (zzaef.versionCode > 10 && zzaef.zzcdl != -1) {
            zznx2.zza(zznx2.zzd(zzaef.zzcdl), "cts");
        }
        zznv zzjj = zznx2.zzjj();
        zzanz zza = zzano.zza(zzafm.zzche.zzk(context), ((Long) zzkb.zzik().zzd(zznk.zzbdf)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        zzanz zza2 = zzano.zza(zzafm.zzchd.zzr(context), ((Long) zzkb.zzik().zzd(zznk.zzbah)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        zzanz<String> zzcl = zzafm.zzcgy.zzcl(zzaef.zzccw.packageName);
        zzanz<String> zzcm = zzafm.zzcgy.zzcm(zzaef.zzccw.packageName);
        zzanz<String> zza3 = zzafm.zzchf.zza(zzaef.zzccx, zzaef.zzccw);
        Future<zzaga> zzq = zzbv.zzev().zzq(context);
        zzanz<Location> zzi = zzano.zzi(null);
        Bundle bundle = zzaef.zzccv.extras;
        boolean z = (bundle == null || bundle.getString("_ad") == null) ? false : true;
        if (zzaef.zzcdr && !z) {
            zzi = zzafm.zzchb.zza(zzaef.applicationInfo);
        }
        zzanz zza4 = zzano.zza(zzi, ((Long) zzkb.zzik().zzd(zznk.zzbco)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        Future zzi2 = zzano.zzi(null);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayj)).booleanValue()) {
            zznx = zznx2;
            zzi2 = zzano.zza(zzafm.zzchf.zzae(context), ((Long) zzkb.zzik().zzd(zznk.zzayk)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        } else {
            zznx = zznx2;
        }
        Bundle bundle2 = (zzaef.versionCode < 4 || zzaef.zzcdc == null) ? null : zzaef.zzcdc;
        ((Boolean) zzkb.zzik().zzd(zznk.zzawx)).booleanValue();
        zzbv.zzek();
        if (zzakk.zzl(context, "android.permission.ACCESS_NETWORK_STATE") && ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            zzakb.zzck("Device is offline.");
        }
        String uuid = zzaef.versionCode >= 7 ? zzaef.zzcdi : UUID.randomUUID().toString();
        new zzaft(context, uuid, zzaef.applicationInfo.packageName);
        if (zzaef.zzccv.extras != null && (string = zzaef.zzccv.extras.getString("_ad")) != null) {
            return zzafs.zza(context, zzaef, string);
        }
        List<String> zzf = zzafm.zzcgz.zzf(zzaef.zzcdj);
        Bundle bundle3 = (Bundle) zzano.zza(zza, (Object) null, ((Long) zzkb.zzik().zzd(zznk.zzbdf)).longValue(), TimeUnit.MILLISECONDS);
        zzagk zzagk = (zzagk) zzano.zza(zza2, (Object) null);
        Location location = (Location) zzano.zza(zza4, (Object) null);
        AdvertisingIdClient.Info info = (AdvertisingIdClient.Info) zzano.zza(zzi2, (Object) null);
        String str = (String) zzano.zza(zza3, (Object) null);
        String str2 = (String) zzano.zza(zzcl, (Object) null);
        String str3 = (String) zzano.zza(zzcm, (Object) null);
        zzaga zzaga = (zzaga) zzano.zza(zzq, (Object) null);
        if (zzaga == null) {
            zzakb.zzdk("Error fetching device info. This is not recoverable.");
            return new zzaej(0);
        }
        zzafl zzafl = new zzafl();
        zzafl.zzcgs = zzaef;
        zzafl.zzcgt = zzaga;
        zzafl.zzcgo = zzagk;
        zzafl.zzaqe = location;
        zzafl.zzcgn = bundle3;
        zzafl.zzccx = str;
        zzafl.zzcgr = info;
        if (zzf == null) {
            zzafl.zzcdj.clear();
        }
        zzafl.zzcdj = zzf;
        zzafl.zzcdc = bundle2;
        zzafl.zzcgp = str2;
        zzafl.zzcgq = str3;
        zzafl.zzcgu = zzafm.zzcgx.zze(context);
        zzafl.zzcgv = zzafm.zzcgv;
        JSONObject zza5 = zzafs.zza(context, zzafl);
        if (zza5 == null) {
            return new zzaej(0);
        }
        if (zzaef.versionCode < 7) {
            try {
                zza5.put("request_id", uuid);
            } catch (JSONException unused) {
            }
        }
        zznx.zza(zzjj, "arc");
        zznx.zzjj();
        zzanz zza6 = zzano.zza(zzano.zza(zzafm.zzchg.zzof().zzf(zza5), zzafo.zzxn, scheduledExecutorService), 10, TimeUnit.SECONDS, scheduledExecutorService);
        zzanz<Void> zzop = zzafm.zzcha.zzop();
        if (zzop != null) {
            zzanm.zza(zzop, "AdRequestServiceImpl.loadAd.flags");
        }
        zzafz zzafz = (zzafz) zzano.zza(zza6, (Object) null);
        if (zzafz == null) {
            return new zzaej(0);
        }
        if (zzafz.getErrorCode() != -2) {
            return new zzaej(zzafz.getErrorCode());
        }
        zznx.zzjm();
        zzaej zza7 = !TextUtils.isEmpty(zzafz.zzom()) ? zzafs.zza(context, zzaef, zzafz.zzom()) : null;
        if (zza7 == null && !TextUtils.isEmpty(zzafz.getUrl())) {
            zza7 = zza(zzaef, context, zzaef.zzacr.zzcw, zzafz.getUrl(), str2, str3, zzafz, zznx, zzafm);
        }
        if (zza7 == null) {
            zza7 = new zzaej(0);
        }
        zznx.zza(zzjj, "tts");
        zza7.zzcfd = zznx.zzjk();
        return zza7;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x008a A[Catch:{ all -> 0x01c5 }] */
    public static zzaej zza(zzaef zzaef, Context context, String str, String str2, String str3, String str4, zzafz zzafz, zznx zznx, zzafm zzafm) {
        HttpURLConnection httpURLConnection;
        String str5;
        byte[] bArr;
        int responseCode;
        Map<String, List<String>> headerFields;
        InputStreamReader inputStreamReader;
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        Throwable th2;
        zzaef zzaef2 = zzaef;
        zznv zzjj = zznx != null ? zznx.zzjj() : null;
        try {
            zzafx zzafx = new zzafx(zzaef2, zzafz.zzoi());
            String valueOf = String.valueOf(str2);
            zzakb.zzck(valueOf.length() != 0 ? "AdRequestServiceImpl: Sending request: ".concat(valueOf) : new String("AdRequestServiceImpl: Sending request: "));
            URL url = new URL(str2);
            long elapsedRealtime = zzbv.zzer().elapsedRealtime();
            boolean z = false;
            int i = 0;
            while (true) {
                if (zzafm != null) {
                    zzafm.zzchc.zzoq();
                }
                httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    zzbv.zzek().zza(context, str, z, httpURLConnection);
                    if (zzafz.zzok()) {
                        if (!TextUtils.isEmpty(str3)) {
                            httpURLConnection.addRequestProperty("x-afma-drt-cookie", str3);
                        }
                        if (!TextUtils.isEmpty(str4)) {
                            httpURLConnection.addRequestProperty("x-afma-drt-v2-cookie", str4);
                            str5 = zzaef2.zzcds;
                            if (!TextUtils.isEmpty(str5)) {
                                zzakb.zzck("Sending webview cookie in ad request header.");
                                httpURLConnection.addRequestProperty("Cookie", str5);
                            }
                            if (zzafz != null || TextUtils.isEmpty(zzafz.zzoj())) {
                                bArr = null;
                            } else {
                                httpURLConnection.setDoOutput(true);
                                bArr = zzafz.zzoj().getBytes();
                                httpURLConnection.setFixedLengthStreamingMode(bArr.length);
                                try {
                                    bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                                    try {
                                        bufferedOutputStream.write(bArr);
                                        IOUtils.closeQuietly(bufferedOutputStream);
                                    } catch (Throwable th3) {
                                        th2 = th3;
                                        IOUtils.closeQuietly(bufferedOutputStream);
                                        throw th2;
                                    }
                                } catch (Throwable th4) {
                                    th2 = th4;
                                    bufferedOutputStream = null;
                                    IOUtils.closeQuietly(bufferedOutputStream);
                                    throw th2;
                                }
                            }
                            zzamy zzamy = new zzamy(zzaef2.zzcdi);
                            zzamy.zza(httpURLConnection, bArr);
                            responseCode = httpURLConnection.getResponseCode();
                            headerFields = httpURLConnection.getHeaderFields();
                            zzamy.zza(httpURLConnection, responseCode);
                            if (responseCode >= 200 || responseCode >= 300) {
                                zza(url.toString(), headerFields, (String) null, responseCode);
                                if (responseCode < 300 || responseCode >= 400) {
                                    StringBuilder sb = new StringBuilder(46);
                                    sb.append("Received error HTTP response code: ");
                                    sb.append(responseCode);
                                    zzakb.zzdk(sb.toString());
                                    zzaej zzaej = new zzaej(0);
                                    httpURLConnection.disconnect();
                                } else {
                                    String headerField = httpURLConnection.getHeaderField("Location");
                                    if (TextUtils.isEmpty(headerField)) {
                                        zzakb.zzdk("No location header to follow redirect.");
                                        zzaej zzaej2 = new zzaej(0);
                                        httpURLConnection.disconnect();
                                        if (zzafm != null) {
                                            zzafm.zzchc.zzor();
                                        }
                                        return zzaej2;
                                    }
                                    url = new URL(headerField);
                                    i++;
                                    if (i > ((Integer) zzkb.zzik().zzd(zznk.zzbes)).intValue()) {
                                        zzakb.zzdk("Too many redirects.");
                                        zzaej zzaej3 = new zzaej(0);
                                        httpURLConnection.disconnect();
                                        if (zzafm != null) {
                                            zzafm.zzchc.zzor();
                                        }
                                        return zzaej3;
                                    }
                                    zzafx.zzl(headerFields);
                                    zzaef2 = zzaef;
                                    z = false;
                                }
                            } else {
                                String url2 = url.toString();
                                try {
                                    InputStreamReader inputStreamReader2 = new InputStreamReader(httpURLConnection.getInputStream());
                                    try {
                                        zzbv.zzek();
                                        String zza = zzakk.zza(inputStreamReader2);
                                        IOUtils.closeQuietly(inputStreamReader2);
                                        zzamy.zzdg(zza);
                                        zza(url2, headerFields, zza, responseCode);
                                        zzafx.zza(url2, headerFields, zza);
                                        if (zznx != null) {
                                            zznx.zza(zzjj, "ufe");
                                        }
                                        zzaej zza2 = zzafx.zza(elapsedRealtime, zzafz.zzon());
                                        httpURLConnection.disconnect();
                                        if (zzafm != null) {
                                            zzafm.zzchc.zzor();
                                        }
                                        return zza2;
                                    } catch (Throwable th5) {
                                        th = th5;
                                        inputStreamReader = inputStreamReader2;
                                        IOUtils.closeQuietly(inputStreamReader);
                                        throw th;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    inputStreamReader = null;
                                    IOUtils.closeQuietly(inputStreamReader);
                                    throw th;
                                }
                            }
                        }
                    }
                    str5 = zzaef2.zzcds;
                    if (!TextUtils.isEmpty(str5)) {
                    }
                    if (zzafz != null) {
                    }
                    bArr = null;
                    zzamy zzamy2 = new zzamy(zzaef2.zzcdi);
                    zzamy2.zza(httpURLConnection, bArr);
                    responseCode = httpURLConnection.getResponseCode();
                    headerFields = httpURLConnection.getHeaderFields();
                    zzamy2.zza(httpURLConnection, responseCode);
                    if (responseCode >= 200) {
                    }
                    zza(url.toString(), headerFields, (String) null, responseCode);
                    if (responseCode < 300) {
                        break;
                    }
                    break;
                } finally {
                    httpURLConnection.disconnect();
                    if (zzafm != null) {
                        zzafm.zzchc.zzor();
                    }
                }
            }
            StringBuilder sb2 = new StringBuilder(46);
            sb2.append("Received error HTTP response code: ");
            sb2.append(responseCode);
            zzakb.zzdk(sb2.toString());
            zzaej zzaej4 = new zzaej(0);
            httpURLConnection.disconnect();
            if (zzafm != null) {
                zzafm.zzchc.zzor();
            }
            return zzaej4;
        } catch (IOException e) {
            String valueOf2 = String.valueOf(e.getMessage());
            zzakb.zzdk(valueOf2.length() != 0 ? "Error while connecting to ad server: ".concat(valueOf2) : new String("Error while connecting to ad server: "));
            return new zzaej(2);
        }
    }

    public static zzafn zza(Context context, zzafm zzafm) {
        zzafn zzafn;
        synchronized (sLock) {
            if (zzchh == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zznk.initialize(context);
                zzchh = new zzafn(context, zzafm);
                if (context.getApplicationContext() != null) {
                    zzbv.zzek().zzal(context);
                }
                zzajz.zzai(context);
            }
            zzafn = zzchh;
        }
        return zzafn;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzakb.isLoggable(2)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39);
            sb.append("Http Response: {\n  URL:\n    ");
            sb.append(str);
            sb.append("\n  Headers:");
            zzakb.v(sb.toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + 5);
                    sb2.append("    ");
                    sb2.append(str3);
                    sb2.append(":");
                    zzakb.v(sb2.toString());
                    for (String str4 : map.get(str3)) {
                        String valueOf = String.valueOf(str4);
                        zzakb.v(valueOf.length() != 0 ? "      ".concat(valueOf) : new String("      "));
                    }
                }
            }
            zzakb.v("  Body:");
            if (str2 != null) {
                int i2 = 0;
                while (i2 < Math.min(str2.length(), 100000)) {
                    int i3 = i2 + 1000;
                    zzakb.v(str2.substring(i2, Math.min(str2.length(), i3)));
                    i2 = i3;
                }
            } else {
                zzakb.v("    null");
            }
            StringBuilder sb3 = new StringBuilder(34);
            sb3.append("  Response Code:\n    ");
            sb3.append(i);
            sb3.append("\n}");
            zzakb.v(sb3.toString());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaen
    public final void zza(zzaef zzaef, zzaeq zzaeq) {
        zzbv.zzeo().zzd(this.mContext, zzaef.zzacr);
        zzanz<?> zzb = zzaki.zzb(new zzafp(this, zzaef, zzaeq));
        zzbv.zzez().zzsa();
        zzbv.zzez().getHandler().postDelayed(new zzafq(this, zzb), 60000);
    }

    @Override // com.google.android.gms.internal.ads.zzaen
    public final void zza(zzaey zzaey, zzaet zzaet) {
        zzakb.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.ads.zzaen
    public final zzaej zzb(zzaef zzaef) {
        return zza(this.mContext, this.zzchi, zzaef, this.zzchj);
    }

    @Override // com.google.android.gms.internal.ads.zzaen
    public final void zzb(zzaey zzaey, zzaet zzaet) {
        zzakb.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }
}
