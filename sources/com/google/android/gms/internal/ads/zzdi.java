package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import java.lang.ref.WeakReference;

public final class zzdi implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final Handler zzsy = new Handler(Looper.getMainLooper());
    private final zzcz zzps;
    private Application zzrk;
    private final Context zzsz;
    private final PowerManager zzta;
    private final KeyguardManager zztb;
    private BroadcastReceiver zztc;
    private WeakReference<ViewTreeObserver> zztd;
    private WeakReference<View> zzte;
    private zzcn zztf;
    private boolean zztg = false;
    private int zzth = -1;
    private long zzti = -3;

    public zzdi(zzcz zzcz, View view) {
        this.zzps = zzcz;
        Context context = zzcz.zzrt;
        this.zzsz = context;
        this.zzta = (PowerManager) context.getSystemService("power");
        this.zztb = (KeyguardManager) this.zzsz.getSystemService("keyguard");
        Context context2 = this.zzsz;
        if (context2 instanceof Application) {
            this.zzrk = (Application) context2;
            this.zztf = new zzcn((Application) context2, this);
        }
        zzd(view);
    }

    private final void zza(Activity activity, int i) {
        Window window;
        if (this.zzte != null && (window = activity.getWindow()) != null) {
            View peekDecorView = window.peekDecorView();
            View view = this.zzte.get();
            if (view != null && peekDecorView != null && view.getRootView() == peekDecorView.getRootView()) {
                this.zzth = i;
            }
        }
    }

    private final void zzao() {
        zzsy.post(new zzdj(this));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0056, code lost:
        if (r4 == false) goto L_0x0059;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    public final void zzaq() {
        boolean z;
        int i;
        boolean z2;
        WeakReference<View> weakReference = this.zzte;
        if (weakReference != null) {
            View view = weakReference.get();
            boolean z3 = false;
            if (view == null) {
                this.zzti = -3;
                this.zztg = false;
                return;
            }
            boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
            boolean localVisibleRect = view.getLocalVisibleRect(new Rect());
            if (!this.zzps.zzai()) {
                if (this.zztb.inKeyguardRestrictedInputMode()) {
                    Activity zzc = zzdg.zzc(view);
                    if (zzc != null) {
                        Window window = zzc.getWindow();
                        WindowManager.LayoutParams attributes = window == null ? null : window.getAttributes();
                        if (!(attributes == null || (attributes.flags & 524288) == 0)) {
                            z2 = true;
                        }
                    }
                    z2 = false;
                }
                z = false;
                int windowVisibility = view.getWindowVisibility();
                i = this.zzth;
                if (i != -1) {
                    windowVisibility = i;
                }
                if (view.getVisibility() == 0 && view.isShown() && this.zzta.isScreenOn() && z && localVisibleRect && globalVisibleRect && windowVisibility == 0) {
                    z3 = true;
                }
                if (this.zztg == z3) {
                    this.zzti = z3 ? SystemClock.elapsedRealtime() : -2;
                    this.zztg = z3;
                    return;
                }
                return;
            }
            z = true;
            int windowVisibility2 = view.getWindowVisibility();
            i = this.zzth;
            if (i != -1) {
            }
            z3 = true;
            if (this.zztg == z3) {
            }
        }
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zztd = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zztc == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            zzdk zzdk = new zzdk(this);
            this.zztc = zzdk;
            this.zzsz.registerReceiver(zzdk, intentFilter);
        }
        Application application = this.zzrk;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zztf);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|(3:4|(1:8)|9)|10|11|(1:13)|15|(3:17|18|19)|21|(3:23|24|26)(1:28)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027 A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0033 A[SYNTHETIC, Splitter:B:17:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003e A[SYNTHETIC, Splitter:B:23:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    private final void zzf(View view) {
        if (this.zztd != null) {
            ViewTreeObserver viewTreeObserver = this.zztd.get();
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnScrollChangedListener(this);
                viewTreeObserver.removeGlobalOnLayoutListener(this);
            }
            this.zztd = null;
        }
        ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
        if (viewTreeObserver2.isAlive()) {
            viewTreeObserver2.removeOnScrollChangedListener(this);
            viewTreeObserver2.removeGlobalOnLayoutListener(this);
        }
        BroadcastReceiver broadcastReceiver = this.zztc;
        if (broadcastReceiver != null) {
            try {
                this.zzsz.unregisterReceiver(broadcastReceiver);
            } catch (Exception unused) {
            }
            this.zztc = null;
        }
        Application application = this.zzrk;
        if (application == null) {
            try {
                application.unregisterActivityLifecycleCallbacks(this.zztf);
            } catch (Exception unused2) {
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzaq();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzaq();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzaq();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzaq();
        zzao();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzaq();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzaq();
    }

    public final void onActivityStopped(Activity activity) {
        zzaq();
    }

    public final void onGlobalLayout() {
        zzaq();
    }

    public final void onScrollChanged() {
        zzaq();
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzth = -1;
        zze(view);
        zzaq();
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzth = -1;
        zzaq();
        zzao();
        zzf(view);
    }

    public final long zzap() {
        if (this.zzti == -2 && this.zzte.get() == null) {
            this.zzti = -3;
        }
        return this.zzti;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(View view) {
        long j;
        WeakReference<View> weakReference = this.zzte;
        View view2 = weakReference != null ? weakReference.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzte = new WeakReference<>(view);
        if (view != null) {
            if ((view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
            j = -2;
        } else {
            j = -3;
        }
        this.zzti = j;
    }
}
