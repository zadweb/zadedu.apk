package com.b.a.a.a.c;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.b.a.a.a.b.i;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static b f29a = new b();
    private Context b;
    private BroadcastReceiver c;
    private boolean d;
    private boolean e;
    private a f;

    public interface a {
        void a(boolean z);
    }

    private b() {
    }

    public static b a() {
        return f29a;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(boolean z) {
        if (this.e != z) {
            this.e = z;
            if (this.d) {
                g();
                a aVar = this.f;
                if (aVar != null) {
                    aVar.a(d());
                }
            }
        }
    }

    private void e() {
        this.c = new BroadcastReceiver() {
            /* class com.b.a.a.a.c.b.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                KeyguardManager keyguardManager;
                if (intent != null) {
                    if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                        b.this.a((b) true);
                    } else if ("android.intent.action.USER_PRESENT".equals(intent.getAction()) || ("android.intent.action.SCREEN_ON".equals(intent.getAction()) && (keyguardManager = (KeyguardManager) context.getSystemService("keyguard")) != null && !keyguardManager.inKeyguardRestrictedInputMode())) {
                        b.this.a((b) false);
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.b.registerReceiver(this.c, intentFilter);
    }

    private void f() {
        BroadcastReceiver broadcastReceiver;
        Context context = this.b;
        if (context != null && (broadcastReceiver = this.c) != null) {
            context.unregisterReceiver(broadcastReceiver);
            this.c = null;
        }
    }

    private void g() {
        boolean z = !this.e;
        for (i iVar : a.a().b()) {
            iVar.f().a(z);
        }
    }

    public void a(Context context) {
        this.b = context.getApplicationContext();
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public void b() {
        e();
        this.d = true;
        g();
    }

    public void c() {
        f();
        this.d = false;
        this.e = false;
        this.f = null;
    }

    public boolean d() {
        return !this.e;
    }
}
