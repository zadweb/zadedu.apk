package com.truenet.android.a;

import a.a.b.b.h;
import android.graphics.Bitmap;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: StartAppSDK */
public final class a {
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0088 A[SYNTHETIC, Splitter:B:28:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009a  */
    public static final boolean a(Bitmap bitmap, String str) {
        h.b(bitmap, "$receiver");
        h.b(str, "url");
        HttpURLConnection httpURLConnection = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        boolean z = false;
        try {
            URLConnection openConnection = new URL(str).openConnection();
            if (openConnection != null) {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) openConnection;
                try {
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setRequestMethod("PUT");
                    httpURLConnection2.setRequestProperty("Content-Type", "image/jpeg");
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
                        byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        httpURLConnection2.setFixedLengthStreamingMode(byteArray.length);
                        httpURLConnection2.getOutputStream().write(byteArray);
                        httpURLConnection2.getOutputStream().flush();
                        int responseCode = httpURLConnection2.getResponseCode();
                        if (200 <= responseCode && 299 >= responseCode) {
                            z = true;
                        }
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Throwable th) {
                            Log.e(bitmap.getClass().getCanonicalName(), "stream closed with error!", th);
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return z;
                    } catch (Throwable unused) {
                        httpURLConnection = httpURLConnection2;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        if (byteArrayOutputStream != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        return false;
                    }
                } catch (Throwable unused2) {
                    httpURLConnection = httpURLConnection2;
                    if (byteArrayOutputStream != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    return false;
                }
            } else {
                throw new a.a.h("null cannot be cast to non-null type java.net.HttpURLConnection");
            }
        } catch (Throwable unused3) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    Log.e(bitmap.getClass().getCanonicalName(), "stream closed with error!", th2);
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return false;
        }
    }
}
