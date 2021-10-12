package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
@zzadh
public final class zzgk extends Thread {
    private final Object mLock;
    private boolean mStarted = false;
    private final int zzagx;
    private final int zzagz;
    private boolean zzahy = false;
    private final zzgf zzahz;
    private final zzadf zzaia;
    private final int zzaib;
    private final int zzaic;
    private final int zzaid;
    private final int zzaie;
    private final int zzaif;
    private final int zzaig;
    private final String zzaih;
    private final boolean zzaii;
    private boolean zzbm = false;

    public zzgk(zzgf zzgf, zzadf zzadf) {
        this.zzahz = zzgf;
        this.zzaia = zzadf;
        this.mLock = new Object();
        this.zzagx = ((Integer) zzkb.zzik().zzd(zznk.zzawl)).intValue();
        this.zzaic = ((Integer) zzkb.zzik().zzd(zznk.zzawm)).intValue();
        this.zzagz = ((Integer) zzkb.zzik().zzd(zznk.zzawn)).intValue();
        this.zzaid = ((Integer) zzkb.zzik().zzd(zznk.zzawo)).intValue();
        this.zzaie = ((Integer) zzkb.zzik().zzd(zznk.zzawr)).intValue();
        this.zzaif = ((Integer) zzkb.zzik().zzd(zznk.zzawt)).intValue();
        this.zzaig = ((Integer) zzkb.zzik().zzd(zznk.zzawu)).intValue();
        this.zzaib = ((Integer) zzkb.zzik().zzd(zznk.zzawp)).intValue();
        this.zzaih = (String) zzkb.zzik().zzd(zznk.zzaww);
        this.zzaii = ((Boolean) zzkb.zzik().zzd(zznk.zzawy)).booleanValue();
        setName("ContentFetchTask");
    }

    private final zzgo zza(View view, zzge zzge) {
        boolean z;
        if (view == null) {
            return new zzgo(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zzgo(this, 0, 0);
            }
            zzge.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new zzgo(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzaqw)) {
            zzge.zzgs();
            WebView webView = (WebView) view;
            if (!PlatformVersion.isAtLeastKitKat()) {
                z = false;
            } else {
                zzge.zzgs();
                webView.post(new zzgm(this, zzge, webView, globalVisibleRect));
                z = true;
            }
            return z ? new zzgo(this, 0, 1) : new zzgo(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zzgo(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                zzgo zza = zza(viewGroup.getChildAt(i3), zzge);
                i += zza.zzaiq;
                i2 += zza.zzair;
            }
            return new zzgo(this, i, i2);
        }
    }

    private static boolean zzgx() {
        try {
            Context context = zzbv.zzen().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null) {
                return false;
            }
            if (keyguardManager == null) {
                return false;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode()) {
                        return false;
                    }
                    PowerManager powerManager = (PowerManager) context.getSystemService("power");
                    return powerManager == null ? false : powerManager.isScreenOn();
                }
            }
            return false;
        } catch (Throwable th) {
            zzbv.zzeo().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    private final void zzgz() {
        synchronized (this.mLock) {
            this.zzahy = true;
            StringBuilder sb = new StringBuilder(42);
            sb.append("ContentFetchThread: paused, mPause = ");
            sb.append(true);
            zzakb.zzck(sb.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0068, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0069, code lost:
        com.google.android.gms.internal.ads.zzakb.zzb("Error in ContentFetchTask", r0);
        r4.zzaia.zza(r0, "ContentFetchTask.run");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0077, code lost:
        com.google.android.gms.internal.ads.zzakb.zzb("Error in ContentFetchTask", r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007f */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0076 A[ExcHandler: InterruptedException (r0v1 'e' java.lang.InterruptedException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007f A[LOOP:1: B:29:0x007f->B:40:0x007f, LOOP_START, SYNTHETIC] */
    public final void run() {
        while (true) {
            try {
                if (zzgx()) {
                    Activity activity = zzbv.zzen().getActivity();
                    if (activity == null) {
                        zzakb.zzck("ContentFetchThread: no activity. Sleeping.");
                    } else {
                        if (activity != null) {
                            View view = null;
                            if (!(activity.getWindow() == null || activity.getWindow().getDecorView() == null)) {
                                view = activity.getWindow().getDecorView().findViewById(16908290);
                            }
                            if (!(view == null || view == null)) {
                                view.post(new zzgl(this, view));
                            }
                        }
                        Thread.sleep((long) (this.zzaib * 1000));
                        synchronized (this.mLock) {
                            while (this.zzahy) {
                                zzakb.zzck("ContentFetchTask: waiting");
                                this.mLock.wait();
                            }
                        }
                    }
                } else {
                    zzakb.zzck("ContentFetchTask: sleeping");
                }
                zzgz();
            } catch (Exception e) {
                zzbv.zzeo().zza(e, "ContentFetchTask.extractContent");
                zzakb.zzck("Failed getting root view of activity. Content not extracted.");
            } catch (InterruptedException e2) {
            }
            Thread.sleep((long) (this.zzaib * 1000));
            synchronized (this.mLock) {
            }
        }
    }

    public final void wakeup() {
        synchronized (this.mLock) {
            this.zzahy = false;
            this.mLock.notifyAll();
            zzakb.zzck("ContentFetchThread: wakeup");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzge zzge, WebView webView, String str, boolean z) {
        zzge.zzgr();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (this.zzaii || TextUtils.isEmpty(webView.getTitle())) {
                    zzge.zza(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    String title = webView.getTitle();
                    StringBuilder sb = new StringBuilder(String.valueOf(title).length() + 1 + String.valueOf(optString).length());
                    sb.append(title);
                    sb.append("\n");
                    sb.append(optString);
                    zzge.zza(sb.toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (zzge.zzgn()) {
                this.zzahz.zzb(zzge);
            }
        } catch (JSONException unused) {
            zzakb.zzck("Json string may be malformed.");
        } catch (Throwable th) {
            zzakb.zza("Failed to get webview content.", th);
            this.zzaia.zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    public final void zzgw() {
        synchronized (this.mLock) {
            if (this.mStarted) {
                zzakb.zzck("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    public final zzge zzgy() {
        return this.zzahz.zzgv();
    }

    public final boolean zzha() {
        return this.zzahy;
    }

    /* access modifiers changed from: package-private */
    public final void zzk(View view) {
        try {
            zzge zzge = new zzge(this.zzagx, this.zzaic, this.zzagz, this.zzaid, this.zzaie, this.zzaif, this.zzaig);
            Context context = zzbv.zzen().getContext();
            if (context != null && !TextUtils.isEmpty(this.zzaih)) {
                String str = (String) view.getTag(context.getResources().getIdentifier((String) zzkb.zzik().zzd(zznk.zzawv), "id", context.getPackageName()));
                if (str != null && str.equals(this.zzaih)) {
                    return;
                }
            }
            zzgo zza = zza(view, zzge);
            zzge.zzgt();
            if (zza.zzaiq != 0 || zza.zzair != 0) {
                if (zza.zzair != 0 || zzge.zzgu() != 0) {
                    if (zza.zzair != 0 || !this.zzahz.zza(zzge)) {
                        this.zzahz.zzc(zzge);
                    }
                }
            }
        } catch (Exception e) {
            zzakb.zzb("Exception in fetchContentOnUIThread", e);
            this.zzaia.zza(e, "ContentFetchTask.fetchContent");
        }
    }
}
