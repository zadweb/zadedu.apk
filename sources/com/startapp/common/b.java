package com.startapp.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.appnext.base.b.c;
import com.startapp.android.mediation.admob.StartAppNative;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/* compiled from: StartAppSDK */
public class b {
    private static final Object f = new Object();
    private static b g;

    /* renamed from: a  reason: collision with root package name */
    private final Context f581a;
    private final HashMap<BroadcastReceiver, ArrayList<IntentFilter>> b = new HashMap<>();
    private final HashMap<String, ArrayList<C0028b>> c = new HashMap<>();
    private final ArrayList<a> d = new ArrayList<>();
    private final Handler e;

    /* access modifiers changed from: private */
    /* renamed from: com.startapp.common.b$b  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    public static class C0028b {

        /* renamed from: a  reason: collision with root package name */
        final IntentFilter f590a;
        final BroadcastReceiver b;
        boolean c;

        C0028b(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.f590a = intentFilter;
            this.b = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.b);
            sb.append(" filter=");
            sb.append(this.f590a);
            sb.append("}");
            return sb.toString();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: StartAppSDK */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final Intent f583a;
        final ArrayList<C0028b> b;

        a(Intent intent, ArrayList<C0028b> arrayList) {
            this.f583a = intent;
            this.b = arrayList;
        }
    }

    public static b a(Context context) {
        b bVar;
        synchronized (f) {
            if (g == null) {
                g = new b(context.getApplicationContext());
            }
            bVar = g;
        }
        return bVar;
    }

    private b(Context context) {
        this.f581a = context;
        this.e = new Handler(context.getMainLooper()) {
            /* class com.startapp.common.b.AnonymousClass1 */

            public void handleMessage(Message message) {
                if (message.what != 1) {
                    super.handleMessage(message);
                } else {
                    b.this.a();
                }
            }
        };
    }

    public void a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.b) {
            C0028b bVar = new C0028b(intentFilter, broadcastReceiver);
            ArrayList<IntentFilter> arrayList = this.b.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.b.put(broadcastReceiver, arrayList);
            }
            arrayList.add(intentFilter);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                ArrayList<C0028b> arrayList2 = this.c.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.c.put(action, arrayList2);
                }
                arrayList2.add(bVar);
            }
        }
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        synchronized (this.b) {
            ArrayList<IntentFilter> remove = this.b.remove(broadcastReceiver);
            if (remove != null) {
                for (int i = 0; i < remove.size(); i++) {
                    IntentFilter intentFilter = remove.get(i);
                    for (int i2 = 0; i2 < intentFilter.countActions(); i2++) {
                        String action = intentFilter.getAction(i2);
                        ArrayList<C0028b> arrayList = this.c.get(action);
                        if (arrayList != null) {
                            int i3 = 0;
                            while (i3 < arrayList.size()) {
                                if (arrayList.get(i3).b == broadcastReceiver) {
                                    arrayList.remove(i3);
                                    i3--;
                                }
                                i3++;
                            }
                            if (arrayList.size() <= 0) {
                                this.c.remove(action);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean a(Intent intent) {
        String str;
        ArrayList<C0028b> arrayList;
        String str2;
        int i;
        ArrayList arrayList2;
        synchronized (this.b) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.f581a.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z = (intent.getFlags() & 8) != 0;
            if (z) {
                Log.v("LocalBroadcastManager", "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
            }
            ArrayList<C0028b> arrayList3 = this.c.get(intent.getAction());
            if (arrayList3 != null) {
                if (z) {
                    Log.v("LocalBroadcastManager", "Action list: " + arrayList3);
                }
                ArrayList arrayList4 = null;
                int i2 = 0;
                while (i2 < arrayList3.size()) {
                    C0028b bVar = arrayList3.get(i2);
                    if (z) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + bVar.f590a);
                    }
                    if (bVar.c) {
                        if (z) {
                            Log.v("LocalBroadcastManager", "  Filter's target already added");
                        }
                        i = i2;
                        arrayList = arrayList3;
                        str2 = action;
                        str = resolveTypeIfNeeded;
                        arrayList2 = arrayList4;
                    } else {
                        i = i2;
                        str2 = action;
                        arrayList2 = arrayList4;
                        arrayList = arrayList3;
                        str = resolveTypeIfNeeded;
                        int match = bVar.f590a.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                        if (match >= 0) {
                            if (z) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(match));
                            }
                            arrayList4 = arrayList2 == null ? new ArrayList() : arrayList2;
                            arrayList4.add(bVar);
                            bVar.c = true;
                            i2 = i + 1;
                            action = str2;
                            arrayList3 = arrayList;
                            resolveTypeIfNeeded = str;
                        } else if (z) {
                            Log.v("LocalBroadcastManager", "  Filter did not match: " + (match != -4 ? match != -3 ? match != -2 ? match != -1 ? "unknown reason" : "type" : c.DATA : "action" : StartAppNative.EXTRAS_CATEGORY));
                        }
                    }
                    arrayList4 = arrayList2;
                    i2 = i + 1;
                    action = str2;
                    arrayList3 = arrayList;
                    resolveTypeIfNeeded = str;
                }
                if (arrayList4 != null) {
                    for (int i3 = 0; i3 < arrayList4.size(); i3++) {
                        ((C0028b) arrayList4.get(i3)).c = false;
                    }
                    this.d.add(new a(intent, arrayList4));
                    if (!this.e.hasMessages(1)) {
                        this.e.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r3 >= r1) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        r4 = r2[r3];
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        if (r5 >= r4.b.size()) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        r4.b.get(r5).b.onReceive(r9.f581a, r4.f583a);
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        r3 = 0;
     */
    public void a() {
        while (true) {
            synchronized (this.b) {
                int size = this.d.size();
                if (size > 0) {
                    a[] aVarArr = new a[size];
                    this.d.toArray(aVarArr);
                    this.d.clear();
                } else {
                    return;
                }
            }
        }
    }
}
