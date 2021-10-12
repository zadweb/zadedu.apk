package com.truenet.android.a;

import a.a.b.b.h;
import a.a.b.b.i;
import a.a.b.b.m;
import android.content.Context;
import android.util.Log;
import com.appnext.base.b.c;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: StartAppSDK */
public final class g {
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094 A[SYNTHETIC, Splitter:B:31:0x0094] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a6  */
    public static final boolean a(URL url, String str, Context context) {
        byte[] bytes;
        BufferedOutputStream bufferedOutputStream;
        h.b(url, "$receiver");
        h.b(str, c.DATA);
        HttpURLConnection httpURLConnection = null;
        BufferedOutputStream bufferedOutputStream2 = null;
        boolean z = false;
        try {
            URLConnection openConnection = url.openConnection();
            if (openConnection != null) {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) openConnection;
                try {
                    httpURLConnection2.setRequestProperty("Cache-Control", "no-cache");
                    if (context != null) {
                        httpURLConnection2.setRequestProperty("User-Agent", i.f905a.a(context));
                    }
                    httpURLConnection2.setRequestMethod("PUT");
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setRequestProperty("Content-Type", "text/html");
                    bytes = str.getBytes(a.a.e.a.f10a);
                    h.a((Object) bytes, "(this as java.lang.String).getBytes(charset)");
                    httpURLConnection2.setFixedLengthStreamingMode(bytes.length);
                    httpURLConnection2.setConnectTimeout(50000);
                    bufferedOutputStream = new BufferedOutputStream(httpURLConnection2.getOutputStream());
                } catch (Throwable unused) {
                    httpURLConnection = httpURLConnection2;
                    if (bufferedOutputStream2 != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    return false;
                }
                try {
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.flush();
                    int responseCode = httpURLConnection2.getResponseCode();
                    if (200 <= responseCode && 299 >= responseCode) {
                        z = true;
                    }
                    try {
                        bufferedOutputStream.close();
                    } catch (Throwable th) {
                        Log.e(url.getClass().getCanonicalName(), "stream closed with error!", th);
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return z;
                } catch (Throwable unused2) {
                    bufferedOutputStream2 = bufferedOutputStream;
                    httpURLConnection = httpURLConnection2;
                    if (bufferedOutputStream2 != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    return false;
                }
            } else {
                throw new a.a.h("null cannot be cast to non-null type java.net.HttpURLConnection");
            }
        } catch (Throwable unused3) {
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (Throwable th2) {
                    Log.e(url.getClass().getCanonicalName(), "stream closed with error!", th2);
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00de A[SYNTHETIC, Splitter:B:41:0x00de] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e6 A[Catch:{ all -> 0x00e2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f7  */
    public static final String b(URL url, String str, Context context) {
        h.b(url, "$receiver");
        h.b(str, c.DATA);
        String str2 = null;
        HttpURLConnection httpURLConnection = null;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            URLConnection openConnection = url.openConnection();
            if (openConnection != null) {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) openConnection;
                try {
                    httpURLConnection2.setRequestProperty("Cache-Control", "no-cache");
                    if (context != null) {
                        httpURLConnection2.setRequestProperty("User-Agent", i.f905a.a(context));
                    }
                    httpURLConnection2.setRequestMethod("POST");
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setDoInput(true);
                    byte[] bytes = str.getBytes(a.a.e.a.f10a);
                    h.a((Object) bytes, "(this as java.lang.String).getBytes(charset)");
                    httpURLConnection2.setFixedLengthStreamingMode(bytes.length);
                    httpURLConnection2.setRequestProperty("Content-Type", "application/json");
                    httpURLConnection2.setConnectTimeout(50000);
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(httpURLConnection2.getOutputStream());
                    try {
                        bufferedOutputStream2.write(bytes);
                        bufferedOutputStream2.flush();
                        if (httpURLConnection2.getResponseCode() == 200) {
                            StringBuilder sb = new StringBuilder();
                            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpURLConnection2.getInputStream());
                            try {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream2));
                                m.a aVar = new m.a();
                                aVar.element = (T) null;
                                while (new a(aVar, bufferedReader).a() != null) {
                                    sb.append((String) aVar.element);
                                }
                                str2 = sb.toString();
                                bufferedInputStream = bufferedInputStream2;
                            } catch (Throwable unused) {
                                bufferedOutputStream = bufferedOutputStream2;
                                bufferedInputStream = bufferedInputStream2;
                                httpURLConnection = httpURLConnection2;
                                if (bufferedOutputStream != null) {
                                }
                                if (bufferedInputStream != null) {
                                }
                                if (httpURLConnection != null) {
                                }
                                return null;
                            }
                        }
                        try {
                            bufferedOutputStream2.close();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                        } catch (Throwable th) {
                            Log.e(url.getClass().getCanonicalName(), "stream closed with error!", th);
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return str2;
                    } catch (Throwable unused2) {
                        bufferedOutputStream = bufferedOutputStream2;
                        httpURLConnection = httpURLConnection2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (Throwable th2) {
                                Log.e(url.getClass().getCanonicalName(), "stream closed with error!", th2);
                                if (httpURLConnection != null) {
                                }
                                return null;
                            }
                        }
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return null;
                    }
                } catch (Throwable unused3) {
                    httpURLConnection = httpURLConnection2;
                    if (bufferedOutputStream != null) {
                    }
                    if (bufferedInputStream != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    return null;
                }
            } else {
                throw new a.a.h("null cannot be cast to non-null type java.net.HttpURLConnection");
            }
        } catch (Throwable unused4) {
            if (bufferedOutputStream != null) {
            }
            if (bufferedInputStream != null) {
            }
            if (httpURLConnection != null) {
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: StartAppSDK */
    public static final class a extends i implements a.a.b.a.a<String> {
        final /* synthetic */ m.a $line;
        final /* synthetic */ BufferedReader $reader;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(m.a aVar, BufferedReader bufferedReader) {
            super(0);
            this.$line = aVar;
            this.$reader = bufferedReader;
        }

        /* renamed from: b */
        public final String a() {
            this.$line.element = (T) this.$reader.readLine();
            return this.$line.element;
        }
    }
}
