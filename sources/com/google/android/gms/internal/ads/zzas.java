package com.google.android.gms.internal.ads;

import com.mopub.common.Constants;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

public final class zzas extends zzai {
    private final zzat zzci;
    private final SSLSocketFactory zzcj;

    public zzas() {
        this(null);
    }

    private zzas(zzat zzat) {
        this(null, null);
    }

    private zzas(zzat zzat, SSLSocketFactory sSLSocketFactory) {
        this.zzci = null;
        this.zzcj = null;
    }

    private static InputStream zza(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    private static List<zzl> zza(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                for (String str : entry.getValue()) {
                    arrayList.add(new zzl(entry.getKey(), str));
                }
            }
        }
        return arrayList;
    }

    private static void zza(HttpURLConnection httpURLConnection, zzr<?> zzr) throws IOException, zza {
        byte[] zzg = zzr.zzg();
        if (zzg != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", "UTF-8".length() != 0 ? "application/x-www-form-urlencoded; charset=".concat("UTF-8") : new String("application/x-www-form-urlencoded; charset="));
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzg);
            dataOutputStream.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f8  */
    @Override // com.google.android.gms.internal.ads.zzai
    public final zzaq zza(zzr<?> zzr, Map<String, String> map) throws IOException, zza {
        int responseCode;
        zzaq zzaq;
        String str;
        String str2;
        String url = zzr.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(zzr.getHeaders());
        hashMap.putAll(map);
        zzat zzat = this.zzci;
        if (zzat != null) {
            String zzg = zzat.zzg(url);
            if (zzg == null) {
                String valueOf = String.valueOf(url);
                throw new IOException(valueOf.length() != 0 ? "URL blocked by rewriter: ".concat(valueOf) : new String("URL blocked by rewriter: "));
            }
            url = zzg;
        }
        URL url2 = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int zzi = zzr.zzi();
        httpURLConnection.setConnectTimeout(zzi);
        httpURLConnection.setReadTimeout(zzi);
        boolean z = false;
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        Constants.HTTPS.equals(url2.getProtocol());
        for (String str3 : hashMap.keySet()) {
            httpURLConnection.addRequestProperty(str3, (String) hashMap.get(str3));
        }
        switch (zzr.getMethod()) {
            case -1:
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode != -1) {
                    if (!(zzr.getMethod() == 4 || ((100 <= responseCode && responseCode < 200) || responseCode == 204 || responseCode == 304))) {
                        z = true;
                    }
                    if (!z) {
                        zza(httpURLConnection.getHeaderFields());
                        return zzaq;
                    }
                    zzaq = new zzaq(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), zza(httpURLConnection));
                    return zzaq;
                }
                throw new IOException("Could not retrieve response code from HttpUrlConnection.");
            case 0:
                str = "GET";
                httpURLConnection.setRequestMethod(str);
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode != -1) {
                }
                break;
            case 1:
                str2 = "POST";
                httpURLConnection.setRequestMethod(str2);
                zza(httpURLConnection, zzr);
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode != -1) {
                }
                break;
            case 2:
                str2 = "PUT";
                httpURLConnection.setRequestMethod(str2);
                zza(httpURLConnection, zzr);
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode != -1) {
                }
                break;
            case 3:
                str = "DELETE";
                httpURLConnection.setRequestMethod(str);
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode != -1) {
                }
                break;
            case 4:
                str = "HEAD";
                httpURLConnection.setRequestMethod(str);
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode != -1) {
                }
                break;
            case 5:
                str = "OPTIONS";
                httpURLConnection.setRequestMethod(str);
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode != -1) {
                }
                break;
            case 6:
                str = "TRACE";
                httpURLConnection.setRequestMethod(str);
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode != -1) {
                }
                break;
            case 7:
                str2 = "PATCH";
                httpURLConnection.setRequestMethod(str2);
                zza(httpURLConnection, zzr);
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode != -1) {
                }
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }
}
