package com.yandex.metrica.impl.ob;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.appnext.base.a.c.d;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.bk;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;

public class dp implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final ServiceConnection f1086a = new ServiceConnection() {
        /* class com.yandex.metrica.impl.ob.dp.AnonymousClass1 */

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    };
    private final Handler b = new Handler(Looper.getMainLooper()) {
        /* class com.yandex.metrica.impl.ob.dp.AnonymousClass3 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 100) {
                dp.this.e();
                try {
                    dp.this.d.unbindService(dp.this.f1086a);
                } catch (Exception unused) {
                    YandexMetrica.getReporter(dp.this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_unbind_has_thrown_exception");
                }
            }
        }
    };
    private HashMap<String, c> c = new HashMap<String, c>() {
        /* class com.yandex.metrica.impl.ob.dp.AnonymousClass4 */

        {
            put("p", new c() {
                /* class com.yandex.metrica.impl.ob.dp.AnonymousClass4.AnonymousClass1 */

                @Override // com.yandex.metrica.impl.ob.dp.c
                public b a(Uri uri, Socket socket) {
                    return new a(uri, socket);
                }
            });
        }
    };
    private final Context d;
    private boolean e;
    private ServerSocket f;
    private final dq g = new dq();
    private ds h;
    private Thread i;

    abstract class b {
        Uri b;
        Socket c;

        public abstract void a();

        /* access modifiers changed from: protected */
        public void a(Throwable th) {
        }

        /* access modifiers changed from: protected */
        public void b() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Map<java.lang.String, java.lang.String> */
        /* JADX WARN: Multi-variable type inference failed */
        b(Uri uri, Map<String, String> map) {
            this.b = uri;
            this.c = map;
        }

        private static void a(OutputStream outputStream) throws IOException {
            outputStream.write("\r\n".getBytes());
        }

        /* access modifiers changed from: package-private */
        public void a(String str, Map<String, String> map, byte[] bArr) {
            Throwable th;
            IOException e;
            BufferedOutputStream bufferedOutputStream = null;
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(this.c.getOutputStream());
                try {
                    bufferedOutputStream2.write(str.getBytes());
                    a(bufferedOutputStream2);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        a(bufferedOutputStream2, entry.getKey(), entry.getValue());
                    }
                    a(bufferedOutputStream2, "Content-Length", String.valueOf(bArr.length));
                    a(bufferedOutputStream2);
                    bufferedOutputStream2.write(bArr);
                    bufferedOutputStream2.flush();
                    b();
                    bk.a(bufferedOutputStream2);
                } catch (IOException e2) {
                    e = e2;
                    bufferedOutputStream = bufferedOutputStream2;
                    try {
                        a(e);
                        bk.a(bufferedOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        bk.a(bufferedOutputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream = bufferedOutputStream2;
                    bk.a(bufferedOutputStream);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                a(e);
                bk.a(bufferedOutputStream);
            }
        }

        private static void a(OutputStream outputStream, String str, String str2) throws IOException {
            outputStream.write((str + ": " + str2).getBytes());
            a(outputStream);
        }
    }

    static abstract class c {
        public abstract b a(Uri uri, Socket socket);

        c() {
        }
    }

    class a extends b {
        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.Map<java.lang.String, java.lang.String> */
        /* JADX WARN: Multi-variable type inference failed */
        a(Uri uri, Map<String, String> map) {
            super(uri, map);
        }

        @Override // com.yandex.metrica.impl.ob.dp.b
        public void a() {
            if (dp.this.h.b().equals(this.b.getQueryParameter(d.COLUMN_TYPE))) {
                a("HTTP/1.1 200 OK", new HashMap<String, String>() {
                    /* class com.yandex.metrica.impl.ob.dp.a.AnonymousClass1 */

                    {
                        put("Content-Type", "text/plain; charset=utf-8");
                        put("Access-Control-Allow-Origin", "*");
                        put("Access-Control-Allow-Methods", "GET");
                    }
                }, c());
            } else {
                YandexMetrica.getReporter(dp.this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_request_with_wrong_token");
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.yandex.metrica.impl.ob.dp.b
        public void b() {
            YandexMetrica.getReporter(dp.this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_sync_succeed", dp.c(this.c.getLocalPort()));
        }

        /* access modifiers changed from: protected */
        @Override // com.yandex.metrica.impl.ob.dp.b
        public void a(Throwable th) {
            YandexMetrica.getReporter(dp.this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("socket_io_exception_during_sync", th);
        }

        /* access modifiers changed from: protected */
        public byte[] c() {
            try {
                return Base64.encode(new com.yandex.metrica.impl.utils.b().a(dp.this.g.a().getBytes()), 0);
            } catch (JSONException unused) {
                return new byte[0];
            }
        }
    }

    dp(Context context) {
        this.d = context;
        g.a().a(this, p.class, k.a(new j<p>() {
            /* class com.yandex.metrica.impl.ob.dp.AnonymousClass6 */

            public void a(p pVar) {
                dp.this.g.a(pVar.f1167a);
            }
        }).a(new h<p>() {
            /* class com.yandex.metrica.impl.ob.dp.AnonymousClass5 */

            public boolean a(p pVar) {
                return !dp.this.d.getPackageName().equals(pVar.b);
            }
        }).a());
        g.a().a(this, n.class, k.a(new j<n>() {
            /* class com.yandex.metrica.impl.ob.dp.AnonymousClass7 */

            public void a(n nVar) {
                dp.this.g.b(nVar.f1165a);
            }
        }).a());
        g.a().a(this, l.class, k.a(new j<l>() {
            /* class com.yandex.metrica.impl.ob.dp.AnonymousClass8 */

            public void a(l lVar) {
                dp.this.g.c(lVar.f1163a);
            }
        }).a());
        g.a().a(this, m.class, k.a(new j<m>() {
            /* class com.yandex.metrica.impl.ob.dp.AnonymousClass9 */

            public void a(m mVar) {
                dp.this.g.d(mVar.f1164a);
            }
        }).a());
        g.a().a(this, o.class, k.a(new j<o>() {
            /* class com.yandex.metrica.impl.ob.dp.AnonymousClass10 */

            public void a(o oVar) {
                dp.this.a(oVar.f1166a);
                dp.this.c();
            }
        }).a());
    }

    public void a() {
        if (this.e) {
            b();
            Handler handler = this.b;
            handler.sendMessageDelayed(handler.obtainMessage(100), this.h.a() * 1000);
        }
    }

    public void b() {
        this.b.removeMessages(100);
    }

    public synchronized void c() {
        if (!(this.e || this.h == null)) {
            this.e = true;
            d();
            Thread thread = new Thread(this);
            this.i = thread;
            thread.start();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ds dsVar) {
        this.h = dsVar;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        Intent intent = new Intent(this.d, MetricaService.class);
        intent.setAction("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER");
        try {
            if (!this.d.bindService(intent, this.f1086a, 1)) {
                YandexMetrica.getReporter(this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_bind_has_failed");
            }
        } catch (Exception unused) {
            YandexMetrica.getReporter(this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_bind_has_thrown_exception");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0021, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public synchronized void e() {
        this.e = false;
        if (this.i != null) {
            this.i.interrupt();
            this.i = null;
        }
        if (this.f != null) {
            this.f.close();
            this.f = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bc A[SYNTHETIC, Splitter:B:42:0x00bc] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0008 A[SYNTHETIC] */
    public void run() {
        Socket socket;
        Throwable th;
        BufferedReader bufferedReader;
        Throwable th2;
        ServerSocket f2 = f();
        this.f = f2;
        if (f2 != null) {
            while (this.e) {
                Socket socket2 = null;
                final String str = null;
                try {
                    socket = this.f.accept();
                    try {
                        socket.setSoTimeout(1000);
                        HashMap hashMap = new HashMap();
                        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        try {
                            String readLine = bufferedReader.readLine();
                            if (!TextUtils.isEmpty(readLine)) {
                                if (readLine.startsWith("GET /")) {
                                    int indexOf = readLine.indexOf(47) + 1;
                                    str = readLine.substring(indexOf, readLine.indexOf(32, indexOf));
                                }
                                Uri parse = Uri.parse(str);
                                while (true) {
                                    String readLine2 = bufferedReader.readLine();
                                    if (TextUtils.isEmpty(readLine2)) {
                                        break;
                                    }
                                    int indexOf2 = readLine2.indexOf(": ");
                                    hashMap.put(readLine2.substring(0, indexOf2), readLine2.substring(indexOf2 + 2));
                                }
                                c cVar = this.c.get(parse.getPath());
                                if (cVar != null) {
                                    cVar.a(parse, socket).a();
                                } else {
                                    YandexMetrica.getReporter(this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_request_to_unknown_path", new HashMap<String, Object>() {
                                        /* class com.yandex.metrica.impl.ob.dp.AnonymousClass2 */

                                        {
                                            put("uri", str);
                                        }
                                    });
                                }
                            }
                        } catch (Throwable th3) {
                            th2 = th3;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th2;
                        }
                    } catch (Throwable th4) {
                        bufferedReader = null;
                        th2 = th4;
                        if (bufferedReader != null) {
                        }
                        throw th2;
                    }
                    try {
                        bufferedReader.close();
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (IOException unused) {
                            }
                        }
                    } catch (IOException unused2) {
                        socket2 = socket;
                        if (socket2 == null) {
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        if (socket != null) {
                        }
                        throw th;
                    }
                } catch (IOException unused3) {
                    if (socket2 == null) {
                        socket2.close();
                    }
                } catch (Throwable th6) {
                    socket = null;
                    th = th6;
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException unused4) {
                        }
                    }
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ServerSocket f() {
        Iterator<Integer> it = this.h.c().iterator();
        ServerSocket serverSocket = null;
        Integer num = null;
        while (serverSocket == null && it.hasNext()) {
            try {
                Integer next = it.next();
                if (next != null) {
                    try {
                        serverSocket = a(next.intValue());
                    } catch (SocketException unused) {
                        num = next;
                    } catch (IOException unused2) {
                    }
                }
                num = next;
            } catch (SocketException unused3) {
                YandexMetrica.getReporter(this.d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_port_already_in_use", c(num.intValue()));
            } catch (IOException unused4) {
            }
        }
        return serverSocket;
    }

    /* access modifiers changed from: package-private */
    public ServerSocket a(int i2) throws IOException {
        return new ServerSocket(i2);
    }

    /* access modifiers changed from: private */
    public static HashMap<String, Object> c(int i2) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("port", String.valueOf(i2));
        return hashMap;
    }
}
