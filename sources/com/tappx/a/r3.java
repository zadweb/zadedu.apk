package com.tappx.a;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import com.appnext.base.b.d;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class r3 extends AsyncTask<String, Void, Boolean> {

    /* renamed from: a  reason: collision with root package name */
    private final Context f807a;
    private final b b;
    private final c c;

    /* access modifiers changed from: package-private */
    public interface b {
        void a();

        void b();
    }

    /* access modifiers changed from: private */
    public static final class c {
        private c() {
        }

        private String a(String str) {
            return str;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private File a(Context context, String str, File file) {
            Closeable closeable;
            BufferedInputStream bufferedInputStream;
            Throwable th;
            File file2;
            URI create = URI.create(str);
            Closeable closeable2 = null;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a(str)).openConnection();
                httpURLConnection.setConnectTimeout(d.fd);
                httpURLConnection.setReadTimeout(d.fd);
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                try {
                    String headerField = httpURLConnection.getHeaderField("Location");
                    if (!TextUtils.isEmpty(headerField)) {
                        create = URI.create(headerField);
                    }
                    file2 = new File(file, a(create, httpURLConnection.getHeaderFields()));
                    closeable = new FileOutputStream(file2);
                } catch (Exception unused) {
                    closeable = null;
                    a(bufferedInputStream);
                    a(closeable);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    closeable = null;
                    closeable2 = bufferedInputStream;
                    a(closeable2);
                    a(closeable);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[16384];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read != -1) {
                            closeable.write(bArr, 0, read);
                        } else {
                            new a4(context).a(file2, (String) null);
                            a(bufferedInputStream);
                            a(closeable);
                            return file2;
                        }
                    }
                } catch (Exception unused2) {
                    a(bufferedInputStream);
                    a(closeable);
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    closeable2 = bufferedInputStream;
                    a(closeable2);
                    a(closeable);
                    throw th;
                }
            } catch (Exception unused3) {
                closeable = null;
                bufferedInputStream = null;
                a(bufferedInputStream);
                a(closeable);
                return null;
            } catch (Throwable th4) {
                th = th4;
                closeable = null;
                a(closeable2);
                a(closeable);
                throw th;
            }
        }

        private void a(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException unused) {
                }
            }
        }

        private String a(URI uri, Map<String, List<String>> map) {
            String path = uri.getPath();
            if (path == null || map == null) {
                return null;
            }
            String name = new File(path).getName();
            List<String> list = map.get("Content-Type");
            if (list == null || list.isEmpty()) {
                return name;
            }
            if (list.get(0) == null) {
                return name;
            }
            String[] split = list.get(0).split(";");
            for (String str : split) {
                if (str.contains("image/")) {
                    String str2 = "." + str.split("/")[1];
                    if (name.endsWith(str2)) {
                        return name;
                    }
                    return name + str2;
                }
            }
            return name;
        }
    }

    public r3(Context context, b bVar) {
        this(context, bVar, new c());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Boolean doInBackground(String... strArr) {
        boolean z = false;
        if (strArr == null || strArr.length == 0 || strArr[0] == null) {
            return false;
        }
        if (this.c.a(this.f807a, strArr[0], a()) != null) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public r3(Context context, b bVar, c cVar) {
        this.f807a = context;
        this.b = bVar;
        this.c = cVar;
    }

    private File a() {
        File file = new File(Environment.getExternalStorageDirectory(), "Pictures");
        file.mkdirs();
        return file;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        if (bool.booleanValue()) {
            this.b.a();
        } else {
            this.b.b();
        }
    }
}
