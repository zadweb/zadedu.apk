package com.startapp.common.a;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: StartAppSDK */
public class d {
    public static Bitmap a(String str) {
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static Drawable a(Resources resources, String str) {
        return new BitmapDrawable(resources, a(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040  */
    public static Bitmap b(String str) {
        Bitmap bitmap;
        HttpURLConnection httpURLConnection;
        Throwable th;
        HttpURLConnection httpURLConnection2 = null;
        Bitmap bitmap2 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                bitmap2 = BitmapFactory.decodeStream(new a(inputStream));
                bufferedInputStream.close();
                inputStream.close();
                if (httpURLConnection == null) {
                    return bitmap2;
                }
                httpURLConnection.disconnect();
                return bitmap2;
            } catch (Exception unused) {
                httpURLConnection2 = httpURLConnection;
                bitmap = bitmap2;
                if (httpURLConnection2 != null) {
                }
                return bitmap;
            } catch (Throwable th2) {
                th = th2;
                if (httpURLConnection != null) {
                }
                throw th;
            }
        } catch (Exception unused2) {
            bitmap = null;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            return bitmap;
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    /* compiled from: StartAppSDK */
    static class a extends FilterInputStream {
        public a(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) {
            long j2 = 0;
            while (j2 < j) {
                long skip = this.in.skip(j - j2);
                if (skip == 0) {
                    if (read() < 0) {
                        break;
                    }
                    skip = 1;
                }
                j2 += skip;
            }
            return j2;
        }
    }
}
