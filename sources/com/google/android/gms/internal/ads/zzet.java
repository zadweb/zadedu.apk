package com.google.android.gms.internal.ads;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbv;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
@zzadh
public final class zzet implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private final Object mLock = new Object();
    private boolean zzaaq = false;
    private zzamj zzadz;
    private final Context zzaeo;
    private final WeakReference<zzajh> zzaeq;
    private WeakReference<ViewTreeObserver> zzaer;
    private final zzgd zzaes;
    protected final zzer zzaet;
    private final WindowManager zzaeu;
    private final PowerManager zzaev;
    private final KeyguardManager zzaew;
    private final DisplayMetrics zzaex;
    private zzfa zzaey;
    private boolean zzaez;
    private boolean zzafa = false;
    private boolean zzafb;
    private boolean zzafc;
    private boolean zzafd;
    private BroadcastReceiver zzafe;
    private final HashSet<zzeq> zzaff = new HashSet<>();
    private final HashSet<zzfo> zzafg = new HashSet<>();
    private final Rect zzafh = new Rect();
    private final zzew zzafi;
    private float zzafj;

    public zzet(Context context, zzjn zzjn, zzajh zzajh, zzang zzang, zzgd zzgd) {
        this.zzaeq = new WeakReference<>(zzajh);
        this.zzaes = zzgd;
        this.zzaer = new WeakReference<>(null);
        this.zzafb = true;
        this.zzafd = false;
        this.zzadz = new zzamj(200);
        this.zzaet = new zzer(UUID.randomUUID().toString(), zzang, zzjn.zzarb, zzajh.zzcob, zzajh.zzfz(), zzjn.zzare);
        this.zzaeu = (WindowManager) context.getSystemService("window");
        this.zzaev = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzaew = (KeyguardManager) context.getSystemService("keyguard");
        this.zzaeo = context;
        this.zzafi = new zzew(this, new Handler());
        this.zzaeo.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.zzafi);
        this.zzaex = context.getResources().getDisplayMetrics();
        Display defaultDisplay = this.zzaeu.getDefaultDisplay();
        this.zzafh.right = defaultDisplay.getWidth();
        this.zzafh.bottom = defaultDisplay.getHeight();
        zzgb();
    }

    private final boolean isScreenOn() {
        return Build.VERSION.SDK_INT >= 20 ? this.zzaev.isInteractive() : this.zzaev.isScreenOn();
    }

    private static int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    private final JSONObject zza(View view, Boolean bool) throws JSONException {
        if (view == null) {
            return zzgg().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
        }
        boolean isAttachedToWindow = zzbv.zzem().isAttachedToWindow(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Exception e) {
            zzakb.zzb("Failure getting view location.", e);
        }
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect2, null);
        Rect rect3 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect3);
        Rect rect4 = new Rect();
        view.getHitRect(rect4);
        JSONObject zzgg = zzgg();
        zzgg.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put("top", zza(this.zzafh.top, this.zzaex)).put("bottom", zza(this.zzafh.bottom, this.zzaex)).put("left", zza(this.zzafh.left, this.zzaex)).put("right", zza(this.zzafh.right, this.zzaex))).put("adBox", new JSONObject().put("top", zza(rect.top, this.zzaex)).put("bottom", zza(rect.bottom, this.zzaex)).put("left", zza(rect.left, this.zzaex)).put("right", zza(rect.right, this.zzaex))).put("globalVisibleBox", new JSONObject().put("top", zza(rect2.top, this.zzaex)).put("bottom", zza(rect2.bottom, this.zzaex)).put("left", zza(rect2.left, this.zzaex)).put("right", zza(rect2.right, this.zzaex))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect3.top, this.zzaex)).put("bottom", zza(rect3.bottom, this.zzaex)).put("left", zza(rect3.left, this.zzaex)).put("right", zza(rect3.right, this.zzaex))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect4.top, this.zzaex)).put("bottom", zza(rect4.bottom, this.zzaex)).put("left", zza(rect4.left, this.zzaex)).put("right", zza(rect4.right, this.zzaex))).put("screenDensity", (double) this.zzaex.density);
        zzgg.put("isVisible", (bool == null ? Boolean.valueOf(zzbv.zzek().zza(view, this.zzaev, this.zzaew)) : bool).booleanValue());
        return zzgg;
    }

    private static JSONObject zza(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    private final void zza(JSONObject jSONObject, boolean z) {
        try {
            JSONObject zza = zza(jSONObject);
            ArrayList arrayList = new ArrayList(this.zzafg);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((zzfo) obj).zzb(zza, z);
            }
        } catch (Throwable th) {
            zzakb.zzb("Skipping active view message.", th);
        }
    }

    private final void zzgd() {
        zzfa zzfa = this.zzaey;
        if (zzfa != null) {
            zzfa.zza(this);
        }
    }

    private final void zzgf() {
        ViewTreeObserver viewTreeObserver = this.zzaer.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    private final JSONObject zzgg() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzaet.zzfw()).put("activeViewJSON", this.zzaet.zzfx()).put(AvidJSONUtil.KEY_TIMESTAMP, zzbv.zzer().elapsedRealtime()).put("adFormat", this.zzaet.zzfv()).put("hashCode", this.zzaet.zzfy()).put("isMraid", this.zzaet.zzfz()).put("isStopped", this.zzafa).put("isPaused", this.zzaaq).put("isNative", this.zzaet.zzga()).put("isScreenOn", isScreenOn()).put("appMuted", zzbv.zzfj().zzdp()).put("appVolume", (double) zzbv.zzfj().zzdo()).put("deviceVolume", (double) this.zzafj);
        return jSONObject;
    }

    public final void onGlobalLayout() {
        zzl(2);
    }

    public final void onScrollChanged() {
        zzl(1);
    }

    public final void pause() {
        synchronized (this.mLock) {
            this.zzaaq = true;
            zzl(3);
        }
    }

    public final void resume() {
        synchronized (this.mLock) {
            this.zzaaq = false;
            zzl(3);
        }
    }

    public final void stop() {
        synchronized (this.mLock) {
            this.zzafa = true;
            zzl(3);
        }
    }

    public final void zza(zzfa zzfa) {
        synchronized (this.mLock) {
            this.zzaey = zzfa;
        }
    }

    public final void zza(zzfo zzfo) {
        if (this.zzafg.isEmpty()) {
            synchronized (this.mLock) {
                if (this.zzafe == null) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.SCREEN_ON");
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    this.zzafe = new zzeu(this);
                    zzbv.zzfk().zza(this.zzaeo, this.zzafe, intentFilter);
                }
            }
            zzl(3);
        }
        this.zzafg.add(zzfo);
        try {
            zzfo.zzb(zza(zza(this.zzaes.zzgh(), (Boolean) null)), false);
        } catch (JSONException e) {
            zzakb.zzb("Skipping measurement update for new client.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzfo zzfo, Map<String, String> map) {
        String valueOf = String.valueOf(this.zzaet.zzfy());
        zzakb.zzck(valueOf.length() != 0 ? "Received request to untrack: ".concat(valueOf) : new String("Received request to untrack: "));
        zzb(zzfo);
    }

    public final void zzb(zzfo zzfo) {
        this.zzafg.remove(zzfo);
        zzfo.zzgl();
        if (this.zzafg.isEmpty()) {
            synchronized (this.mLock) {
                zzgf();
                synchronized (this.mLock) {
                    if (this.zzafe != null) {
                        try {
                            zzbv.zzfk().zza(this.zzaeo, this.zzafe);
                        } catch (IllegalStateException e) {
                            zzakb.zzb("Failed trying to unregister the receiver", e);
                        } catch (Exception e2) {
                            zzbv.zzeo().zza(e2, "ActiveViewUnit.stopScreenStatusMonitoring");
                        }
                        this.zzafe = null;
                    }
                }
                this.zzaeo.getContentResolver().unregisterContentObserver(this.zzafi);
                int i = 0;
                this.zzafb = false;
                zzgd();
                ArrayList arrayList = new ArrayList(this.zzafg);
                int size = arrayList.size();
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zzb((zzfo) obj);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.zzaet.zzfy());
    }

    /* access modifiers changed from: package-private */
    public final void zzd(Map<String, String> map) {
        zzl(3);
    }

    /* access modifiers changed from: package-private */
    public final void zze(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            boolean z = "1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"));
            Iterator<zzeq> it = this.zzaff.iterator();
            while (it.hasNext()) {
                it.next().zza(this, z);
            }
        }
    }

    public final void zzgb() {
        this.zzafj = zzalb.zzay(this.zzaeo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003b  */
    public final void zzgc() {
        String str;
        synchronized (this.mLock) {
            if (this.zzafb) {
                this.zzafc = true;
                try {
                    JSONObject zzgg = zzgg();
                    zzgg.put("doneReasonCode", "u");
                    zza(zzgg, true);
                } catch (JSONException e) {
                    e = e;
                    str = "JSON failure while processing active view data.";
                } catch (RuntimeException e2) {
                    e = e2;
                    str = "Failure while processing active view data.";
                }
                String valueOf = String.valueOf(this.zzaet.zzfy());
                zzakb.zzck(valueOf.length() == 0 ? "Untracking ad unit: ".concat(valueOf) : new String("Untracking ad unit: "));
            }
        }
        zzakb.zzb(str, e);
        String valueOf2 = String.valueOf(this.zzaet.zzfy());
        zzakb.zzck(valueOf2.length() == 0 ? "Untracking ad unit: ".concat(valueOf2) : new String("Untracking ad unit: "));
    }

    public final boolean zzge() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzafb;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public final void zzl(int i) {
        boolean z;
        ViewTreeObserver viewTreeObserver;
        ViewTreeObserver viewTreeObserver2;
        synchronized (this.mLock) {
            Iterator<zzfo> it = this.zzafg.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().zzgk()) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                if (this.zzafb) {
                    View zzgh = this.zzaes.zzgh();
                    boolean z2 = zzgh != null && zzbv.zzek().zza(zzgh, this.zzaev, this.zzaew);
                    boolean z3 = zzgh != null && z2 && zzgh.getGlobalVisibleRect(new Rect(), null);
                    if (this.zzaes.zzgi()) {
                        zzgc();
                    } else if (i != 1 || this.zzadz.tryAcquire() || z3 != this.zzafd) {
                        if (z3 || this.zzafd || i != 1) {
                            try {
                                zza(zza(zzgh, Boolean.valueOf(z2)), false);
                                this.zzafd = z3;
                            } catch (RuntimeException | JSONException e) {
                                zzakb.zza("Active view update failed.", e);
                            }
                            View zzgh2 = this.zzaes.zzgj().zzgh();
                            if (!(zzgh2 == null || (viewTreeObserver2 = zzgh2.getViewTreeObserver()) == (viewTreeObserver = this.zzaer.get()))) {
                                zzgf();
                                if (!this.zzaez || (viewTreeObserver != null && viewTreeObserver.isAlive())) {
                                    this.zzaez = true;
                                    viewTreeObserver2.addOnScrollChangedListener(this);
                                    viewTreeObserver2.addOnGlobalLayoutListener(this);
                                }
                                this.zzaer = new WeakReference<>(viewTreeObserver2);
                            }
                            zzgd();
                        }
                    }
                }
            }
        }
    }
}
