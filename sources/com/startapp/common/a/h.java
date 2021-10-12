package com.startapp.common.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.appnext.base.b.d;
import com.startapp.common.e;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.nio.ByteOrder;
import java.util.Map;

/* compiled from: StartAppSDK */
public class h {

    /* compiled from: StartAppSDK */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private String f579a;
        private String b;
        private String c;

        public String a() {
            return this.f579a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public void a(String str) {
            this.f579a = str;
        }

        public void b(String str) {
            this.b = str;
        }

        public void c(String str) {
            this.c = str;
        }
    }

    /* compiled from: StartAppSDK */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f578a;
        private String b;

        public String a() {
            return this.f578a;
        }

        public void a(String str) {
            this.f578a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public String toString() {
            return "HttpResult: " + this.b + " " + this.f578a;
        }
    }

    static {
        if (Build.VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    public static String a(Context context, String str, byte[] bArr, Map<String, String> map, String str2, boolean z) {
        return a(context, str, bArr, map, str2, z, "application/json");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b3, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b4, code lost:
        r0 = r5;
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b7, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00e9, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0021 A[SYNTHETIC, Splitter:B:18:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b7 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x0008] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00e2 A[SYNTHETIC, Splitter:B:69:0x00e2] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00e9  */
    public static String a(Context context, String str, byte[] bArr, Map<String, String> map, String str2, boolean z, String str3) {
        Throwable th;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        Exception e;
        Throwable th2;
        OutputStream outputStream;
        int i = 0;
        HttpURLConnection httpURLConnection2 = null;
        String str4 = null;
        r1 = null;
        InputStream inputStream2 = null;
        try {
            httpURLConnection = b(context, str, bArr, map, str2, z, str3);
            if (bArr != null) {
                try {
                    if (bArr.length > 0) {
                        try {
                            outputStream = httpURLConnection.getOutputStream();
                            try {
                                outputStream.write(bArr);
                                if (outputStream != null) {
                                    try {
                                        outputStream.flush();
                                        outputStream.close();
                                    } catch (IOException unused) {
                                    }
                                }
                            } catch (Throwable th3) {
                                th2 = th3;
                                if (outputStream != null) {
                                    try {
                                        outputStream.flush();
                                        outputStream.close();
                                    } catch (IOException unused2) {
                                    }
                                }
                                throw th2;
                            }
                        } catch (Throwable th4) {
                            th2 = th4;
                            outputStream = null;
                            if (outputStream != null) {
                            }
                            throw th2;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    inputStream = null;
                    httpURLConnection2 = httpURLConnection;
                    try {
                        throw new e("Error execute Exception " + e.getMessage(), e, i);
                    } catch (Throwable th5) {
                        th = th5;
                        httpURLConnection = httpURLConnection2;
                        inputStream2 = inputStream;
                        if (inputStream2 != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th6) {
                }
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error code = [");
                sb.append(responseCode);
                sb.append(']');
                InputStream errorStream = httpURLConnection.getErrorStream();
                if (errorStream != null) {
                    StringWriter stringWriter = new StringWriter();
                    char[] cArr = new char[d.fb];
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, "UTF-8"));
                    while (true) {
                        int read = bufferedReader.read(cArr);
                        if (read == -1) {
                            break;
                        }
                        stringWriter.write(cArr, 0, read);
                    }
                    sb.append(stringWriter.toString());
                }
                throw new Exception(sb.toString());
            }
            inputStream = httpURLConnection.getInputStream();
            if (inputStream != null) {
                try {
                    StringWriter stringWriter2 = new StringWriter();
                    char[] cArr2 = new char[d.fb];
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    while (true) {
                        int read2 = bufferedReader2.read(cArr2);
                        if (read2 == -1) {
                            break;
                        }
                        stringWriter2.write(cArr2, 0, read2);
                    }
                    str4 = stringWriter2.toString();
                } catch (Exception e3) {
                    httpURLConnection2 = httpURLConnection;
                    i = responseCode;
                    e = e3;
                    throw new e("Error execute Exception " + e.getMessage(), e, i);
                } catch (Throwable th7) {
                    th = th7;
                    inputStream2 = inputStream;
                    if (inputStream2 != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    throw th;
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return str4;
        } catch (Exception e4) {
            e = e4;
            inputStream = null;
            throw new e("Error execute Exception " + e.getMessage(), e, i);
        } catch (Throwable th8) {
            th = th8;
            httpURLConnection = null;
            if (inputStream2 != null) {
            }
            if (httpURLConnection != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ae, code lost:
        r11 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00af, code lost:
        r9 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b1, code lost:
        r11 = th;
        r10 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b3, code lost:
        r11 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b4, code lost:
        r13 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b1 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00dc A[SYNTHETIC, Splitter:B:55:0x00dc] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00e3  */
    /* JADX WARNING: Unknown variable types count: 2 */
    public static a a(Context context, String str, Map<String, String> map, String str2, boolean z) {
        Throwable th;
        ?? r10;
        InputStream inputStream;
        InputStream inputStream2;
        Exception e;
        int responseCode;
        boolean z2 = true;
        InputStream inputStream3 = null;
        int i = 0;
        try {
            ?? b2 = b(context, str, null, map, str2, z, null);
            try {
                responseCode = b2.getResponseCode();
                if (responseCode == 200) {
                    com.startapp.common.d.a.b(b2, str);
                    InputStream inputStream4 = b2.getInputStream();
                    a aVar = new a();
                    aVar.b(b2.getContentType());
                    if (inputStream4 != null) {
                        StringWriter stringWriter = new StringWriter();
                        char[] cArr = new char[d.fb];
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream4, "UTF-8"));
                        while (true) {
                            int read = bufferedReader.read(cArr);
                            if (read == -1) {
                                break;
                            }
                            stringWriter.write(cArr, 0, read);
                        }
                        aVar.a(stringWriter.toString());
                    }
                    if (inputStream4 != null) {
                        try {
                            inputStream4.close();
                        } catch (IOException unused) {
                        }
                    }
                    if (b2 != 0) {
                        b2.disconnect();
                    }
                    return aVar;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Error sendGetWithResponse code = [");
                sb.append(responseCode);
                sb.append(']');
                inputStream = b2.getErrorStream();
                if (inputStream != null) {
                    try {
                        StringWriter stringWriter2 = new StringWriter();
                        char[] cArr2 = new char[d.fb];
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                        while (true) {
                            int read2 = bufferedReader2.read(cArr2);
                            if (read2 == -1) {
                                break;
                            }
                            stringWriter2.write(cArr2, 0, read2);
                        }
                        sb.append(stringWriter2.toString());
                    } catch (Exception e2) {
                        e = e2;
                        inputStream3 = b2;
                        i = responseCode;
                        try {
                            throw new e("Error execute Exception " + e.getMessage(), e, z2, i);
                        } catch (Throwable th2) {
                            th = th2;
                            inputStream2 = inputStream3;
                            inputStream3 = inputStream;
                            r10 = inputStream2;
                            if (inputStream3 != null) {
                                try {
                                    inputStream3.close();
                                } catch (IOException unused2) {
                                }
                            }
                            if (r10 != 0) {
                                r10.disconnect();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream2 = b2;
                        inputStream3 = inputStream;
                        r10 = inputStream2;
                        if (inputStream3 != null) {
                        }
                        if (r10 != 0) {
                        }
                        throw th;
                    }
                }
                throw new e(sb.toString(), null, true, responseCode);
            } catch (Exception e3) {
                e = e3;
                i = responseCode;
                inputStream = null;
                z2 = false;
                inputStream3 = b2;
                throw new e("Error execute Exception " + e.getMessage(), e, z2, i);
            } catch (Throwable th4) {
            }
        } catch (Exception e4) {
            e = e4;
            inputStream = null;
            throw new e("Error execute Exception " + e.getMessage(), e, z2, i);
        } catch (Throwable th5) {
            th = th5;
            r10 = 0;
            if (inputStream3 != null) {
            }
            if (r10 != 0) {
            }
            throw th;
        }
    }

    private static HttpURLConnection b(Context context, String str, byte[] bArr, Map<String, String> map, String str2, boolean z, String str3) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.addRequestProperty("Cache-Control", "no-cache");
        com.startapp.common.d.a.a(httpURLConnection, str);
        if (!(str2 == null || str2.compareTo("-1") == 0)) {
            httpURLConnection.addRequestProperty("User-Agent", str2);
        }
        httpURLConnection.setRequestProperty("Accept", "application/json;text/html;text/plain");
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setConnectTimeout(10000);
        if (bArr != null) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setFixedLengthStreamingMode(bArr.length);
            httpURLConnection.setRequestProperty("Content-Type", str3);
            if (z) {
                httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
            }
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
        return httpURLConnection;
    }

    public static String a(Context context) {
        String str = "WIFI";
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "e100";
            }
            if (!c.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                return "e105";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                str = "e102";
            } else {
                String typeName = activeNetworkInfo.getTypeName();
                if (typeName.toLowerCase().compareTo(str.toLowerCase()) != 0) {
                    if (typeName.toLowerCase().compareTo("MOBILE".toLowerCase()) == 0) {
                        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                        str = telephonyManager != null ? Integer.toString(telephonyManager.getNetworkType()) : "e101";
                    } else {
                        str = "e100";
                    }
                }
            }
            return str;
        } catch (Exception unused) {
            return "e100";
        }
    }

    public static b a(Context context, String str) {
        if (str.toLowerCase().compareTo("WIFI".toLowerCase()) == 0) {
            return b(context);
        }
        return null;
    }

    public static b b(Context context) {
        String str = "e100";
        b bVar = new b();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            String str2 = "e105";
            if (connectivityManager != null) {
                if (c.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                        str2 = "e102";
                    } else if (activeNetworkInfo.getTypeName().compareTo("WIFI") != 0) {
                        str2 = "e103";
                    } else if (c.a(context, "android.permission.ACCESS_WIFI_STATE")) {
                        int rssi = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getRssi();
                        bVar.c(Integer.toString(WifiManager.calculateSignalLevel(rssi, 5)));
                        bVar.b(Integer.toString(rssi));
                        str2 = null;
                    }
                }
                str = str2;
            }
        } catch (Exception unused) {
        }
        bVar.a(str);
        return bVar;
    }

    public static Boolean c(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || !c.a(context, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return null;
        }
        return Boolean.valueOf(activeNetworkInfo.isRoaming());
    }

    public static String a(WifiInfo wifiInfo) {
        int ipAddress = wifiInfo.getIpAddress();
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }
        try {
            return InetAddress.getByAddress(BigInteger.valueOf((long) ipAddress).toByteArray()).getHostAddress();
        } catch (Exception unused) {
            return null;
        }
    }
}
