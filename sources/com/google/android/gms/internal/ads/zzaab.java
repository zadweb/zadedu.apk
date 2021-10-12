package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Map;
import java.util.Set;

@zzadh
public final class zzaab extends zzaal {
    private static final Set<String> zzbvy = CollectionUtils.setOf("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center");
    private final Object mLock = new Object();
    private zzaam zzbmy;
    private final zzaqw zzbnd;
    private final Activity zzbvp;
    private String zzbvz = "top-right";
    private boolean zzbwa = true;
    private int zzbwb = 0;
    private int zzbwc = 0;
    private int zzbwd = 0;
    private int zzbwe = 0;
    private zzasi zzbwf;
    private ImageView zzbwg;
    private LinearLayout zzbwh;
    private PopupWindow zzbwi;
    private RelativeLayout zzbwj;
    private ViewGroup zzbwk;
    private int zzuq = -1;
    private int zzur = -1;

    public zzaab(zzaqw zzaqw, zzaam zzaam) {
        super(zzaqw, "resize");
        this.zzbnd = zzaqw;
        this.zzbvp = zzaqw.zzto();
        this.zzbmy = zzaam;
    }

    private final void zza(int i, int i2) {
        zzb(i, i2 - zzbv.zzek().zzh(this.zzbvp)[0], this.zzuq, this.zzur);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f2, code lost:
        if ((r5 + 50) <= r1[1]) goto L_0x00f5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0102 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0104  */
    private final int[] zzne() {
        boolean z;
        String str;
        int i;
        int i2;
        int[] zzg = zzbv.zzek().zzg(this.zzbvp);
        int[] zzh = zzbv.zzek().zzh(this.zzbvp);
        int i3 = zzg[0];
        int i4 = zzg[1];
        int i5 = this.zzuq;
        if (i5 < 50 || i5 > i3) {
            str = "Width is too small or too large.";
        } else {
            int i6 = this.zzur;
            if (i6 < 50 || i6 > i4) {
                str = "Height is too small or too large.";
            } else if (i6 == i4 && i5 == i3) {
                str = "Cannot resize to a full-screen ad.";
            } else {
                if (this.zzbwa) {
                    String str2 = this.zzbvz;
                    char c = 65535;
                    switch (str2.hashCode()) {
                        case -1364013995:
                            if (str2.equals("center")) {
                                c = 2;
                                break;
                            }
                            break;
                        case -1012429441:
                            if (str2.equals("top-left")) {
                                c = 0;
                                break;
                            }
                            break;
                        case -655373719:
                            if (str2.equals("bottom-left")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 1163912186:
                            if (str2.equals("bottom-right")) {
                                c = 5;
                                break;
                            }
                            break;
                        case 1288627767:
                            if (str2.equals("bottom-center")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 1755462605:
                            if (str2.equals("top-center")) {
                                c = 1;
                                break;
                            }
                            break;
                    }
                    if (c == 0) {
                        i2 = this.zzbwb + this.zzbwd;
                    } else if (c != 1) {
                        if (c != 2) {
                            if (c != 3) {
                                int i7 = this.zzbwb;
                                if (c == 4) {
                                    i2 = ((i7 + this.zzbwd) + (this.zzuq / 2)) - 25;
                                } else if (c != 5) {
                                    i2 = ((i7 + this.zzbwd) + this.zzuq) - 50;
                                } else {
                                    i2 = ((i7 + this.zzbwd) + this.zzuq) - 50;
                                }
                            } else {
                                i2 = this.zzbwb + this.zzbwd;
                            }
                            i = ((this.zzbwc + this.zzbwe) + this.zzur) - 50;
                        } else {
                            i2 = ((this.zzbwb + this.zzbwd) + (this.zzuq / 2)) - 25;
                            i = ((this.zzbwc + this.zzbwe) + (this.zzur / 2)) - 25;
                        }
                        if (i2 >= 0) {
                            if (i2 + 50 <= i3) {
                                if (i >= zzh[0]) {
                                }
                            }
                        }
                        z = false;
                        if (z) {
                            return null;
                        }
                        if (this.zzbwa) {
                            return new int[]{this.zzbwb + this.zzbwd, this.zzbwc + this.zzbwe};
                        }
                        int[] zzg2 = zzbv.zzek().zzg(this.zzbvp);
                        int[] zzh2 = zzbv.zzek().zzh(this.zzbvp);
                        int i8 = zzg2[0];
                        int i9 = this.zzbwb + this.zzbwd;
                        int i10 = this.zzbwc + this.zzbwe;
                        if (i9 < 0) {
                            i9 = 0;
                        } else {
                            int i11 = this.zzuq;
                            if (i9 + i11 > i8) {
                                i9 = i8 - i11;
                            }
                        }
                        if (i10 < zzh2[0]) {
                            i10 = zzh2[0];
                        } else {
                            int i12 = this.zzur;
                            if (i10 + i12 > zzh2[1]) {
                                i10 = zzh2[1] - i12;
                            }
                        }
                        return new int[]{i9, i10};
                    } else {
                        i2 = ((this.zzbwb + this.zzbwd) + (this.zzuq / 2)) - 25;
                    }
                    i = this.zzbwc + this.zzbwe;
                    if (i2 >= 0) {
                    }
                    z = false;
                    if (z) {
                    }
                }
                z = true;
                if (z) {
                }
            }
        }
        zzakb.zzdk(str);
        z = false;
        if (z) {
        }
    }

    public final void zza(int i, int i2, boolean z) {
        synchronized (this.mLock) {
            this.zzbwb = i;
            this.zzbwc = i2;
            if (this.zzbwi != null && z) {
                int[] zzne = zzne();
                if (zzne != null) {
                    PopupWindow popupWindow = this.zzbwi;
                    zzkb.zzif();
                    int zza = zzamu.zza(this.zzbvp, zzne[0]);
                    zzkb.zzif();
                    popupWindow.update(zza, zzamu.zza(this.zzbvp, zzne[1]), this.zzbwi.getWidth(), this.zzbwi.getHeight());
                    zza(zzne[0], zzne[1]);
                } else {
                    zzm(true);
                }
            }
        }
    }

    public final void zzb(int i, int i2) {
        this.zzbwb = i;
        this.zzbwc = i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:118:0x02ad  */
    public final void zzk(Map<String, String> map) {
        synchronized (this.mLock) {
            if (this.zzbvp == null) {
                zzbw("Not an activity context. Cannot resize.");
            } else if (this.zzbnd.zzud() == null) {
                zzbw("Webview is not yet available, size is not set.");
            } else if (this.zzbnd.zzud().zzvs()) {
                zzbw("Is interstitial. Cannot resize an interstitial.");
            } else if (this.zzbnd.zzuj()) {
                zzbw("Cannot resize an expanded banner.");
            } else {
                if (!TextUtils.isEmpty(map.get("width"))) {
                    zzbv.zzek();
                    this.zzuq = zzakk.zzcv(map.get("width"));
                }
                if (!TextUtils.isEmpty(map.get("height"))) {
                    zzbv.zzek();
                    this.zzur = zzakk.zzcv(map.get("height"));
                }
                if (!TextUtils.isEmpty(map.get("offsetX"))) {
                    zzbv.zzek();
                    this.zzbwd = zzakk.zzcv(map.get("offsetX"));
                }
                if (!TextUtils.isEmpty(map.get("offsetY"))) {
                    zzbv.zzek();
                    this.zzbwe = zzakk.zzcv(map.get("offsetY"));
                }
                if (!TextUtils.isEmpty(map.get("allowOffscreen"))) {
                    this.zzbwa = Boolean.parseBoolean(map.get("allowOffscreen"));
                }
                String str = map.get("customClosePosition");
                if (!TextUtils.isEmpty(str)) {
                    this.zzbvz = str;
                }
                if (!(this.zzuq >= 0 && this.zzur >= 0)) {
                    zzbw("Invalid width and height options. Cannot resize.");
                    return;
                }
                Window window = this.zzbvp.getWindow();
                if (window != null) {
                    if (window.getDecorView() != null) {
                        int[] zzne = zzne();
                        if (zzne == null) {
                            zzbw("Resize location out of screen or close button is not visible.");
                            return;
                        }
                        zzkb.zzif();
                        int zza = zzamu.zza(this.zzbvp, this.zzuq);
                        zzkb.zzif();
                        int zza2 = zzamu.zza(this.zzbvp, this.zzur);
                        ViewParent parent = this.zzbnd.getView().getParent();
                        if (parent == null || !(parent instanceof ViewGroup)) {
                            zzbw("Webview is detached, probably in the middle of a resize or expand.");
                            return;
                        }
                        ((ViewGroup) parent).removeView(this.zzbnd.getView());
                        if (this.zzbwi == null) {
                            this.zzbwk = (ViewGroup) parent;
                            zzbv.zzek();
                            Bitmap zzs = zzakk.zzs(this.zzbnd.getView());
                            ImageView imageView = new ImageView(this.zzbvp);
                            this.zzbwg = imageView;
                            imageView.setImageBitmap(zzs);
                            this.zzbwf = this.zzbnd.zzud();
                            this.zzbwk.addView(this.zzbwg);
                        } else {
                            this.zzbwi.dismiss();
                        }
                        RelativeLayout relativeLayout = new RelativeLayout(this.zzbvp);
                        this.zzbwj = relativeLayout;
                        relativeLayout.setBackgroundColor(0);
                        this.zzbwj.setLayoutParams(new ViewGroup.LayoutParams(zza, zza2));
                        zzbv.zzek();
                        PopupWindow zza3 = zzakk.zza((View) this.zzbwj, zza, zza2, false);
                        this.zzbwi = zza3;
                        zza3.setOutsideTouchable(true);
                        this.zzbwi.setTouchable(true);
                        this.zzbwi.setClippingEnabled(!this.zzbwa);
                        char c = 65535;
                        this.zzbwj.addView(this.zzbnd.getView(), -1, -1);
                        this.zzbwh = new LinearLayout(this.zzbvp);
                        zzkb.zzif();
                        int zza4 = zzamu.zza(this.zzbvp, 50);
                        zzkb.zzif();
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(zza4, zzamu.zza(this.zzbvp, 50));
                        String str2 = this.zzbvz;
                        switch (str2.hashCode()) {
                            case -1364013995:
                                if (str2.equals("center")) {
                                    c = 2;
                                    break;
                                }
                                break;
                            case -1012429441:
                                if (str2.equals("top-left")) {
                                    c = 0;
                                    break;
                                }
                                break;
                            case -655373719:
                                if (str2.equals("bottom-left")) {
                                    c = 3;
                                    break;
                                }
                                break;
                            case 1163912186:
                                if (str2.equals("bottom-right")) {
                                    c = 5;
                                    break;
                                }
                                break;
                            case 1288627767:
                                if (str2.equals("bottom-center")) {
                                    c = 4;
                                    break;
                                }
                                break;
                            case 1755462605:
                                if (str2.equals("top-center")) {
                                    c = 1;
                                    break;
                                }
                                break;
                        }
                        if (c != 0) {
                            int i = 14;
                            if (c == 1) {
                                layoutParams.addRule(10);
                            } else if (c == 2) {
                                layoutParams.addRule(13);
                                this.zzbwh.setOnClickListener(new zzaac(this));
                                this.zzbwh.setContentDescription("Close button");
                                this.zzbwj.addView(this.zzbwh, layoutParams);
                                PopupWindow popupWindow = this.zzbwi;
                                View decorView = window.getDecorView();
                                zzkb.zzif();
                                int zza5 = zzamu.zza(this.zzbvp, zzne[0]);
                                zzkb.zzif();
                                popupWindow.showAtLocation(decorView, 0, zza5, zzamu.zza(this.zzbvp, zzne[1]));
                                int i2 = zzne[0];
                                int i3 = zzne[1];
                                if (this.zzbmy != null) {
                                    this.zzbmy.zza(i2, i3, this.zzuq, this.zzur);
                                }
                                this.zzbnd.zza(zzasi.zzi(zza, zza2));
                                zza(zzne[0], zzne[1]);
                                zzby("resized");
                                return;
                            } else if (c == 3) {
                                layoutParams.addRule(12);
                            } else if (c != 4) {
                                i = 11;
                                if (c != 5) {
                                    layoutParams.addRule(10);
                                } else {
                                    layoutParams.addRule(12);
                                }
                            } else {
                                layoutParams.addRule(12);
                            }
                            layoutParams.addRule(i);
                            this.zzbwh.setOnClickListener(new zzaac(this));
                            this.zzbwh.setContentDescription("Close button");
                            this.zzbwj.addView(this.zzbwh, layoutParams);
                            PopupWindow popupWindow2 = this.zzbwi;
                            View decorView2 = window.getDecorView();
                            zzkb.zzif();
                            int zza52 = zzamu.zza(this.zzbvp, zzne[0]);
                            zzkb.zzif();
                            popupWindow2.showAtLocation(decorView2, 0, zza52, zzamu.zza(this.zzbvp, zzne[1]));
                            int i22 = zzne[0];
                            int i32 = zzne[1];
                            if (this.zzbmy != null) {
                            }
                            this.zzbnd.zza(zzasi.zzi(zza, zza2));
                            zza(zzne[0], zzne[1]);
                            zzby("resized");
                            return;
                        }
                        layoutParams.addRule(10);
                        layoutParams.addRule(9);
                        this.zzbwh.setOnClickListener(new zzaac(this));
                        this.zzbwh.setContentDescription("Close button");
                        this.zzbwj.addView(this.zzbwh, layoutParams);
                        try {
                            PopupWindow popupWindow22 = this.zzbwi;
                            View decorView22 = window.getDecorView();
                            zzkb.zzif();
                            int zza522 = zzamu.zza(this.zzbvp, zzne[0]);
                            zzkb.zzif();
                            popupWindow22.showAtLocation(decorView22, 0, zza522, zzamu.zza(this.zzbvp, zzne[1]));
                            int i222 = zzne[0];
                            int i322 = zzne[1];
                            if (this.zzbmy != null) {
                            }
                            this.zzbnd.zza(zzasi.zzi(zza, zza2));
                            zza(zzne[0], zzne[1]);
                            zzby("resized");
                            return;
                        } catch (RuntimeException e) {
                            String valueOf = String.valueOf(e.getMessage());
                            zzbw(valueOf.length() != 0 ? "Cannot show popup window: ".concat(valueOf) : new String("Cannot show popup window: "));
                            this.zzbwj.removeView(this.zzbnd.getView());
                            if (this.zzbwk != null) {
                                this.zzbwk.removeView(this.zzbwg);
                                this.zzbwk.addView(this.zzbnd.getView());
                                this.zzbnd.zza(this.zzbwf);
                            }
                            return;
                        }
                    }
                }
                zzbw("Activity context is not ready, cannot get window or decor view.");
            }
        }
    }

    public final void zzm(boolean z) {
        synchronized (this.mLock) {
            if (this.zzbwi != null) {
                this.zzbwi.dismiss();
                this.zzbwj.removeView(this.zzbnd.getView());
                if (this.zzbwk != null) {
                    this.zzbwk.removeView(this.zzbwg);
                    this.zzbwk.addView(this.zzbnd.getView());
                    this.zzbnd.zza(this.zzbwf);
                }
                if (z) {
                    zzby(RewardedVideo.VIDEO_MODE_DEFAULT);
                    if (this.zzbmy != null) {
                        this.zzbmy.zzcq();
                    }
                }
                this.zzbwi = null;
                this.zzbwj = null;
                this.zzbwk = null;
                this.zzbwh = null;
            }
        }
    }

    public final boolean zznf() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbwi != null;
        }
        return z;
    }
}
