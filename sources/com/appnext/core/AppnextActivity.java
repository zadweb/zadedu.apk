package com.appnext.core;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.appnext.base.b.d;
import com.appnext.core.e;

public abstract class AppnextActivity extends Activity {
    protected String banner = "";
    protected String gj = "";
    private RelativeLayout gk;
    protected RelativeLayout gl;
    protected String guid = "";
    protected Handler handler;
    protected String placementID;
    protected q userAction;

    /* access modifiers changed from: protected */
    public abstract p getConfig();

    /* access modifiers changed from: protected */
    public abstract void onError(String str);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        new Thread(new Runnable() {
            /* class com.appnext.core.AppnextActivity.AnonymousClass1 */

            public final void run() {
                if (!f.s(AppnextActivity.this)) {
                    AppnextActivity.this.finish();
                    AppnextActivity.this.runOnUiThread(new Runnable() {
                        /* class com.appnext.core.AppnextActivity.AnonymousClass1.AnonymousClass1 */

                        public final void run() {
                            AppnextActivity.this.onError(AppnextError.CONNECTION_ERROR);
                        }
                    });
                }
            }
        }).start();
        requestWindowFeature(1);
        getWindow().setFlags(d.fb, d.fb);
        getWindow().addFlags(128);
        super.onCreate(bundle);
        this.handler = new Handler();
    }

    /* access modifiers changed from: protected */
    public final void aY() {
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() ^ 2;
        if (Build.VERSION.SDK_INT >= 16) {
            systemUiVisibility ^= 4;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            systemUiVisibility ^= 4096;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    /* access modifiers changed from: protected */
    public final void aZ() {
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() | 2;
        if (Build.VERSION.SDK_INT >= 16) {
            systemUiVisibility |= 4;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            systemUiVisibility |= 4096;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    /* access modifiers changed from: protected */
    public void a(AppnextAd appnextAd, e.a aVar) {
        q qVar = this.userAction;
        if (qVar != null && appnextAd != null) {
            qVar.a(appnextAd, appnextAd.getImpressionURL(), aVar);
        }
    }

    /* access modifiers changed from: protected */
    public void b(AppnextAd appnextAd, e.a aVar) {
        q qVar = this.userAction;
        if (qVar != null && appnextAd != null) {
            qVar.a(appnextAd, appnextAd.getAppURL(), this.placementID, aVar);
        }
    }

    /* access modifiers changed from: protected */
    public final void a(ViewGroup viewGroup, Drawable drawable) {
        if (this.gk != null) {
            ba();
        }
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.gk = relativeLayout;
        relativeLayout.setBackgroundColor(Color.parseColor("#77ffffff"));
        viewGroup.addView(this.gk);
        this.gk.getLayoutParams().height = -1;
        this.gk.getLayoutParams().width = -1;
        ProgressBar progressBar = new ProgressBar(this, null, 16842871);
        progressBar.setIndeterminateDrawable(drawable);
        progressBar.setIndeterminate(true);
        this.gk.addView(progressBar);
        RotateAnimation rotateAnimation = new RotateAnimation(360.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(-1);
        progressBar.setAnimation(rotateAnimation);
        ((RelativeLayout.LayoutParams) progressBar.getLayoutParams()).addRule(13, -1);
        this.gk.setOnClickListener(new View.OnClickListener() {
            /* class com.appnext.core.AppnextActivity.AnonymousClass2 */

            public final void onClick(View view) {
            }
        });
        this.handler.postDelayed(new Runnable() {
            /* class com.appnext.core.AppnextActivity.AnonymousClass3 */

            public final void run() {
                AppnextActivity.this.ba();
            }
        }, 8000);
    }

    /* access modifiers changed from: protected */
    public final void ba() {
        RelativeLayout relativeLayout = this.gk;
        if (relativeLayout != null) {
            relativeLayout.removeAllViews();
            this.gk.removeAllViewsInLayout();
            if (this.gk.getParent() != null) {
                ((RelativeLayout) this.gk.getParent()).removeView(this.gk);
            }
        }
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacks(null);
        }
        this.gk = null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000b */
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(null);
        this.handler = null;
        this.userAction.destroy();
        this.userAction = null;
    }
}
