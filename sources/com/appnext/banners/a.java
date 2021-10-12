package com.appnext.banners;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.ECPM;
import com.appnext.core.a.b;
import com.appnext.core.callbacks.OnECPMLoaded;
import com.appnext.core.d;
import com.appnext.core.e;
import com.appnext.core.f;
import com.appnext.core.j;
import com.appnext.core.k;
import com.appnext.core.p;
import com.appnext.core.q;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.ArrayList;
import java.util.HashMap;

public class a extends e {
    private final int TICK = 330;
    private BannerAdRequest adRequest;
    private ArrayList<AppnextAd> ads;
    private BannerAd bannerAd;
    private boolean clickEnabled = true;
    private BannerAdData currentAd;
    private int currentPosition = 0;
    private boolean finished = false;
    private int lastProgress = 0;
    private boolean loaded = false;
    private Handler mHandler;
    private MediaPlayer mediaPlayer;
    private boolean reportedImpression = false;
    private i serviceHelper;
    private boolean started = false;
    private String template = "";
    private Runnable tick = new Runnable() {
        /* class com.appnext.banners.a.AnonymousClass17 */

        public final void run() {
            try {
                a.this.checkProgress();
                a.this.currentPosition = a.this.mediaPlayer.getCurrentPosition();
                if (a.this.mediaPlayer.getCurrentPosition() < a.this.mediaPlayer.getDuration() && !a.this.finished) {
                    a.this.mHandler.postDelayed(a.this.tick, 330);
                }
            } catch (Throwable unused) {
            }
        }
    };
    private q userAction;
    private boolean userMute = true;
    private VideoView videoView;

    @Override // com.appnext.banners.e
    public void init(ViewGroup viewGroup) {
        super.init(viewGroup);
        this.userAction = new q(this.context, new q.a() {
            /* class com.appnext.banners.a.AnonymousClass1 */

            @Override // com.appnext.core.q.a
            public final void report(String str) {
                a.this.report(str);
            }

            @Override // com.appnext.core.q.a
            public final Ad e() {
                return a.this.bannerAd;
            }

            @Override // com.appnext.core.q.a
            public final AppnextAd f() {
                return a.this.getSelectedAd();
            }

            @Override // com.appnext.core.q.a
            public final p g() {
                return d.S();
            }
        });
        this.mHandler = new Handler();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    public Ad createAd(Context context, String str) {
        char c;
        String bannerSize = getBannerSize().toString();
        int hashCode = bannerSize.hashCode();
        if (hashCode != -1966536496) {
            if (hashCode != -96588539) {
                if (hashCode == 1951953708 && bannerSize.equals(AdPreferences.TYPE_BANNER)) {
                    c = 0;
                    if (c != 0) {
                        return new SmallBannerAd(context, str);
                    }
                    if (c == 1) {
                        return new LargeBannerAd(context, str);
                    }
                    if (c == 2) {
                        return new MediumRectangleAd(context, str);
                    }
                    throw new IllegalArgumentException("Wrong banner size " + getBannerSize());
                }
            } else if (bannerSize.equals("MEDIUM_RECTANGLE")) {
                c = 2;
                if (c != 0) {
                }
            }
        } else if (bannerSize.equals("LARGE_BANNER")) {
            c = 1;
            if (c != 0) {
            }
        }
        c = 65535;
        if (c != 0) {
        }
    }

    @Override // com.appnext.banners.e
    public void loadAd(BannerAdRequest bannerAdRequest) {
        if (bannerAdRequest == null) {
            throw new IllegalStateException("BannerAdRequest cannot be null.");
        } else if (getPlacementId() == null) {
            throw new IllegalStateException("Missing placement id.");
        } else if (getBannerSize() != null) {
            if (this.bannerAd == null) {
                this.bannerAd = (BannerAd) createAd(this.context, getPlacementId());
            }
            this.bannerAd.setCategories(bannerAdRequest.getCategories());
            this.bannerAd.setPostback(bannerAdRequest.getPostback());
            this.adRequest = new BannerAdRequest(bannerAdRequest);
            setClickEnabled(bannerAdRequest.isClickEnabled());
            this.loaded = false;
            this.reportedImpression = false;
            if (f.Z(f.o(this.context)) == 0) {
                this.adRequest.setCreativeType("static");
            }
            d.S().a(this.context, new p.a() {
                /* class com.appnext.banners.a.AnonymousClass12 */

                @Override // com.appnext.core.p.a
                public final void b(HashMap<String, Object> hashMap) {
                    a.this.load();
                }

                @Override // com.appnext.core.p.a
                public final void error(String str) {
                    a.this.load();
                }
            });
        } else {
            throw new IllegalStateException("Missing banner size.");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void load() {
        if (this.adRequest != null) {
            j.bj().b(Integer.parseInt(d.S().get("banner_expiration_time")));
            b.R().a(this.context, this.bannerAd, getPlacementId(), new d.a() {
                /* class com.appnext.banners.a.AnonymousClass18 */

                /* JADX WARNING: Removed duplicated region for block: B:38:0x0123  */
                /* JADX WARNING: Removed duplicated region for block: B:43:0x0130  */
                @Override // com.appnext.core.d.a
                public final <T> void a(T t) {
                    char c;
                    if (a.this.adRequest != null) {
                        AppnextAd a2 = b.R().a(a.this.context, a.this.bannerAd, t, a.this.adRequest.getCreativeType());
                        if (a2 == null) {
                            a.this.report(com.appnext.ads.a.w);
                            if (a.this.getBannerListener() != null) {
                                a.this.getBannerListener().onError(new AppnextError(AppnextError.NO_ADS));
                            }
                        } else if (a.this.rootView != null) {
                            if (a.this.context == null) {
                                a.this.rootView.removeAllViews();
                                return;
                            }
                            try {
                                if (a.this.ads == null) {
                                    a.this.ads = new ArrayList();
                                }
                                a.this.ads.clear();
                                a.this.ads.addAll(t);
                            } catch (Throwable unused) {
                            }
                            a.this.currentAd = new BannerAdData(a2);
                            String a3 = j.a(d.S().get(a.this.getBannerSize().toString()));
                            a aVar = a.this;
                            aVar.template = ("apnxt_" + a3).toLowerCase();
                            int identifier = a.this.rootView.getContext().getResources().getIdentifier(a.this.template, "layout", a.this.context.getPackageName());
                            char c2 = 65535;
                            if (identifier == 0) {
                                String bannerSize = a.this.getBannerSize().toString();
                                int hashCode = bannerSize.hashCode();
                                if (hashCode != -1966536496) {
                                    if (hashCode != -96588539) {
                                        if (hashCode == 1951953708 && bannerSize.equals(AdPreferences.TYPE_BANNER)) {
                                            c = 0;
                                            if (c != 0) {
                                                identifier = R.layout.apnxt_banner_1;
                                            } else if (c == 1) {
                                                identifier = R.layout.apnxt_large_banner_1;
                                            } else if (c != 2) {
                                                identifier = R.layout.apnxt_banner_1;
                                            } else {
                                                identifier = R.layout.apnxt_medium_rectangle_1;
                                            }
                                        }
                                    } else if (bannerSize.equals("MEDIUM_RECTANGLE")) {
                                        c = 2;
                                        if (c != 0) {
                                        }
                                    }
                                } else if (bannerSize.equals("LARGE_BANNER")) {
                                    c = 1;
                                    if (c != 0) {
                                    }
                                }
                                c = 65535;
                                if (c != 0) {
                                }
                            }
                            a.this.loaded = true;
                            a.this.reportedImpression = false;
                            a.this.inflateView(identifier, a2);
                            if (a.this.getBannerListener() != null) {
                                a.this.getBannerListener().onAdLoaded(a2.getBannerID(), a2.getCreativeType());
                            }
                            String bannerSize2 = a.this.getBannerSize().toString();
                            int hashCode2 = bannerSize2.hashCode();
                            if (hashCode2 != -1966536496) {
                                if (hashCode2 != -96588539) {
                                    if (hashCode2 == 1951953708 && bannerSize2.equals(AdPreferences.TYPE_BANNER)) {
                                        c2 = 0;
                                    }
                                } else if (bannerSize2.equals("MEDIUM_RECTANGLE")) {
                                    c2 = 2;
                                }
                            } else if (bannerSize2.equals("LARGE_BANNER")) {
                                c2 = 1;
                            }
                            if (c2 == 0) {
                                a.this.report(com.appnext.ads.a.W);
                            } else if (c2 == 1) {
                                a.this.report(com.appnext.ads.a.X);
                            } else if (c2 == 2) {
                                a.this.report(com.appnext.ads.a.Y);
                            }
                        }
                    }
                }

                /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
                @Override // com.appnext.core.d.a
                public final void error(String str) {
                    char c;
                    switch (str.hashCode()) {
                        case -2026653947:
                            if (str.equals(AppnextError.INTERNAL_ERROR)) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1958363695:
                            if (str.equals(AppnextError.NO_ADS)) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1477010874:
                            if (str.equals(AppnextError.CONNECTION_ERROR)) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case -507110949:
                            if (str.equals(AppnextError.NO_MARKET)) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 350741825:
                            if (str.equals(AppnextError.TIMEOUT)) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case 844170097:
                            if (str.equals(AppnextError.SLOW_CONNECTION)) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    a.this.report(c != 0 ? c != 1 ? c != 2 ? c != 3 ? c != 4 ? c != 5 ? "" : com.appnext.ads.a.z : com.appnext.ads.a.u : com.appnext.ads.a.y : com.appnext.ads.a.w : com.appnext.ads.a.x : com.appnext.ads.a.t);
                    if (a.this.getBannerListener() != null) {
                        a.this.getBannerListener().onError(new AppnextError(str));
                    }
                }
            }, this.adRequest);
        }
    }

    @Override // com.appnext.banners.e
    public void getECPM(final BannerAdRequest bannerAdRequest, final OnECPMLoaded onECPMLoaded) {
        if (bannerAdRequest == null) {
            throw new IllegalStateException("BannerAdRequest cannot be null.");
        } else if (getPlacementId() == null) {
            throw new IllegalStateException("Missing placement id.");
        } else if (getBannerSize() == null) {
            throw new IllegalStateException("Missing banner size.");
        } else if (onECPMLoaded != null) {
            if (this.bannerAd == null) {
                this.bannerAd = (BannerAd) createAd(this.context, getPlacementId());
            }
            this.bannerAd.setCategories(bannerAdRequest.getCategories());
            this.bannerAd.setPostback(bannerAdRequest.getPostback());
            b.R().a(this.context, this.bannerAd, getPlacementId(), new d.a() {
                /* class com.appnext.banners.a.AnonymousClass19 */

                @Override // com.appnext.core.d.a
                public final <T> void a(T t) {
                    AppnextAd a2 = b.R().a(a.this.context, a.this.bannerAd, bannerAdRequest.getCreativeType());
                    if (a2 == null) {
                        a.this.report(com.appnext.ads.a.w);
                        OnECPMLoaded onECPMLoaded = onECPMLoaded;
                        if (onECPMLoaded != null) {
                            onECPMLoaded.error(AppnextError.NO_ADS);
                            return;
                        }
                        return;
                    }
                    OnECPMLoaded onECPMLoaded2 = onECPMLoaded;
                    if (onECPMLoaded2 != null) {
                        onECPMLoaded2.ecpm(new ECPM(a2.getECPM(), a2.getPPR(), a2.getBannerID()));
                    }
                }

                @Override // com.appnext.core.d.a
                public final void error(String str) {
                    OnECPMLoaded onECPMLoaded = onECPMLoaded;
                    if (onECPMLoaded != null) {
                        onECPMLoaded.error(str);
                    }
                }
            }, bannerAdRequest);
        } else {
            throw new IllegalStateException("callback cannot be null.");
        }
    }

    /* access modifiers changed from: protected */
    public void inflateView(int i, final AppnextAd appnextAd) {
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(i, this.rootView, false);
        inflate.setOnClickListener(new View.OnClickListener() {
            /* class com.appnext.banners.a.AnonymousClass20 */

            public final void onClick(View view) {
                a.this.report(com.appnext.ads.a.ab);
                a.this.click();
            }
        });
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.icon);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                /* class com.appnext.banners.a.AnonymousClass21 */

                public final void onClick(View view) {
                    a.this.report(com.appnext.ads.a.Z);
                    a.this.click();
                }
            });
            new Thread(new Runnable() {
                /* class com.appnext.banners.a.AnonymousClass22 */

                public final void run() {
                    final Bitmap Y = f.Y(appnextAd.getImageURL());
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        /* class com.appnext.banners.a.AnonymousClass22.AnonymousClass1 */

                        public final void run() {
                            imageView.setImageBitmap(Y);
                        }
                    });
                }
            }).start();
        }
        TextView textView = (TextView) inflate.findViewById(R.id.title);
        if (textView != null) {
            textView.setText(appnextAd.getAdTitle());
            textView.setOnClickListener(new View.OnClickListener() {
                /* class com.appnext.banners.a.AnonymousClass23 */

                public final void onClick(View view) {
                    a.this.report(com.appnext.ads.a.ab);
                    a.this.click();
                }
            });
        }
        RatingBar ratingBar = (RatingBar) inflate.findViewById(R.id.ratingBar);
        if (ratingBar != null) {
            try {
                ratingBar.setRating(Float.parseFloat(appnextAd.getStoreRating()));
            } catch (Throwable unused) {
                ratingBar.setVisibility(4);
            }
            ratingBar.setOnClickListener(new View.OnClickListener() {
                /* class com.appnext.banners.a.AnonymousClass24 */

                public final void onClick(View view) {
                    a.this.report(com.appnext.ads.a.ab);
                    a.this.click();
                }
            });
        }
        TextView textView2 = (TextView) inflate.findViewById(R.id.description);
        if (textView2 != null) {
            textView2.setText(appnextAd.getAdDescription());
            textView2.setOnClickListener(new View.OnClickListener() {
                /* class com.appnext.banners.a.AnonymousClass2 */

                public final void onClick(View view) {
                    a.this.report(com.appnext.ads.a.ab);
                    a.this.click();
                }
            });
        }
        View findViewById = inflate.findViewById(R.id.install);
        ((TextView) findViewById).setText(getButtonText(appnextAd));
        findViewById.setOnClickListener(new View.OnClickListener() {
            /* class com.appnext.banners.a.AnonymousClass3 */

            public final void onClick(View view) {
                a.this.report(com.appnext.ads.a.aa);
                a.this.click();
            }
        });
        View findViewById2 = inflate.findViewById(R.id.media);
        if (findViewById2 != null) {
            if (getCreativeType(appnextAd) != 0) {
                createWideImage((ImageView) inflate.findViewById(R.id.wide_image));
            } else {
                createVideo((ViewGroup) findViewById2);
            }
        }
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.privacy);
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class com.appnext.banners.a.AnonymousClass4 */

            public final void onClick(View view) {
                a.this.openLink(f.g(appnextAd));
            }
        });
        if (k.a(appnextAd, d.S())) {
            k.a(this.context, imageView2);
        }
        if (this.rootView.getChildCount() > 0) {
            this.rootView.removeViewAt(0);
        }
        this.rootView.addView(inflate);
    }

    /* access modifiers changed from: protected */
    public String getButtonText(AppnextAd appnextAd) {
        String buttonText = new BannerAdData(appnextAd).getButtonText();
        boolean c = f.c(this.context, getSelectedAd().getAdPackage());
        String str = b.hY;
        if (appnextAd == null || !buttonText.equals("")) {
            b bp = b.bp();
            String language = getLanguage();
            if (!c) {
                str = b.hX;
            }
            return bp.b(language, str, buttonText);
        } else if (c) {
            return b.bp().b(getLanguage(), str, d.S().get("existing_button_text"));
        } else {
            return b.bp().b(getLanguage(), b.hX, d.S().get("new_button_text"));
        }
    }

    /* access modifiers changed from: protected */
    public int getCreativeType(AppnextAd appnextAd) {
        return this.adRequest.getCreativeType().equals(BannerAdRequest.TYPE_ALL) ? b.hasVideo(appnextAd) ? 0 : 1 : (!this.adRequest.getCreativeType().equals("video") || !b.hasVideo(appnextAd)) ? 1 : 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        if (getSelectedAd() == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        r4.mHandler.postDelayed(new com.appnext.banners.a.AnonymousClass5(r4), (long) (java.lang.Integer.parseInt(com.appnext.banners.d.S().get("postpone_impression_sec")) * 1000));
        report(com.appnext.ads.a.G);
        com.appnext.core.j.bj().n(getSelectedAd().getBannerID(), getPlacementId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0063, code lost:
        if (java.lang.Boolean.parseBoolean(com.appnext.banners.d.S().get("pview")) == false) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0065, code lost:
        r4.mHandler.postDelayed(new com.appnext.banners.a.AnonymousClass6(r4), (long) (java.lang.Integer.parseInt(com.appnext.banners.d.S().get("postpone_vta_sec")) * 1000));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0084, code lost:
        if (getBannerListener() == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0086, code lost:
        getBannerListener().adImpression();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    @Override // com.appnext.banners.e
    public void impression() {
        synchronized (this) {
            if (this.loaded && !this.reportedImpression && getVisiblePercent(this.rootView) >= 50) {
                if (this.userAction != null) {
                    this.reportedImpression = true;
                }
            }
        }
    }

    @Override // com.appnext.banners.e
    public void click() {
        report(com.appnext.ads.a.V);
        if (getBannerListener() != null) {
            getBannerListener().onAdClicked();
        }
        this.userAction.a(getSelectedAd(), getSelectedAd().getAppURL(), getPlacementId(), new e.a() {
            /* class com.appnext.banners.a.AnonymousClass7 */

            @Override // com.appnext.core.e.a
            public final void error(String str) {
            }

            @Override // com.appnext.core.e.a
            public final void onMarket(String str) {
                try {
                    if (a.this.mediaPlayer != null && a.this.mediaPlayer.isPlaying()) {
                        a.this.pause();
                        ((ImageView) a.this.rootView.findViewById(R.id.media).findViewById(R.id.play)).setImageResource(R.drawable.apnxt_banner_pause);
                        a.this.rootView.findViewById(R.id.media).findViewById(R.id.play).setVisibility(0);
                    }
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0036 */
    @Override // com.appnext.banners.e
    public void openLink(String str) {
        if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
            pause();
            ((ImageView) this.rootView.findViewById(R.id.media).findViewById(R.id.play)).setImageResource(R.drawable.apnxt_banner_pause);
            this.rootView.findViewById(R.id.media).findViewById(R.id.play).setVisibility(0);
        }
        try {
            super.openLink(str);
        } catch (Throwable unused) {
            report(com.appnext.ads.a.y);
        }
    }

    private void createWideImage(final ImageView imageView) {
        report(com.appnext.ads.a.ac);
        new Thread(new Runnable() {
            /* class com.appnext.banners.a.AnonymousClass8 */

            public final void run() {
                final Bitmap Y = f.Y(a.this.getSelectedAd().getWideImageURL());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    /* class com.appnext.banners.a.AnonymousClass8.AnonymousClass1 */

                    public final void run() {
                        imageView.setImageBitmap(Y);
                        imageView.setVisibility(0);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            /* class com.appnext.banners.a.AnonymousClass8.AnonymousClass1.AnonymousClass1 */

                            public final void onClick(View view) {
                                a.this.report(com.appnext.ads.a.ab);
                                a.this.click();
                            }
                        });
                    }
                });
            }
        }).start();
    }

    private void createVideo(final ViewGroup viewGroup) {
        try {
            if (this.adRequest.isAutoPlay()) {
                report(com.appnext.ads.a.ae);
            } else {
                report(com.appnext.ads.a.af);
            }
            if (this.adRequest.isMute()) {
                report(com.appnext.ads.a.ag);
            } else {
                report(com.appnext.ads.a.ah);
            }
            this.userMute = this.adRequest.isMute();
            ((ImageView) viewGroup.findViewById(R.id.mute)).setImageResource(this.userMute ? R.drawable.apnxt_banner_unmute : R.drawable.apnxt_banner_mute);
            viewGroup.findViewById(R.id.mute).setVisibility(0);
            viewGroup.findViewById(R.id.mute).setOnClickListener(new View.OnClickListener() {
                /* class com.appnext.banners.a.AnonymousClass9 */

                public final void onClick(View view) {
                    a aVar = a.this;
                    aVar.userMute = !aVar.userMute;
                    if (a.this.mediaPlayer != null) {
                        try {
                            MediaPlayer mediaPlayer = a.this.mediaPlayer;
                            float f = 0.0f;
                            float f2 = a.this.userMute ? 0.0f : 1.0f;
                            if (!a.this.userMute) {
                                f = 1.0f;
                            }
                            mediaPlayer.setVolume(f2, f);
                            ((ImageView) viewGroup.findViewById(R.id.mute)).setImageResource(a.this.userMute ? R.drawable.apnxt_banner_unmute : R.drawable.apnxt_banner_mute);
                        } catch (Throwable unused) {
                        }
                    }
                }
            });
            try {
                this.videoView = new VideoView(this.context.getApplicationContext());
            } catch (Throwable unused) {
                this.videoView = new VideoView(this.context);
            }
            this.videoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
            viewGroup.addView(this.videoView, 0);
            viewGroup.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
                /* class com.appnext.banners.a.AnonymousClass10 */

                public final void onClick(View view) {
                    if (!a.this.isClickEnabled() || a.this.mediaPlayer == null || !a.this.mediaPlayer.isPlaying()) {
                        ((ImageView) viewGroup.findViewById(R.id.play)).setImageResource(R.drawable.apnxt_banner_pause);
                        viewGroup.findViewById(R.id.play).setVisibility(0);
                        a.this.pause();
                        return;
                    }
                    a.this.click();
                }
            });
            this.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /* class com.appnext.banners.a.AnonymousClass11 */

                public final void onPrepared(MediaPlayer mediaPlayer) {
                    a.this.mediaPlayer = mediaPlayer;
                    a.this.mediaPlayer.seekTo(a.this.currentPosition);
                    a.this.mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                        /* class com.appnext.banners.a.AnonymousClass11.AnonymousClass1 */

                        public final void onSeekComplete(MediaPlayer mediaPlayer) {
                            if (a.this.adRequest.isAutoPlay() && !a.this.finished && a.this.getVisiblePercent(a.this.rootView) > 90 && !a.this.mediaPlayer.isPlaying()) {
                                a.this.play();
                                try {
                                    a.this.rootView.findViewById(R.id.media).findViewById(R.id.play).setVisibility(8);
                                } catch (Throwable unused) {
                                }
                                if (!a.this.started) {
                                    a.this.report(com.appnext.ads.a.I);
                                    a.this.started = true;
                                }
                            }
                        }
                    });
                    if (a.this.adRequest.isAutoPlay() && !a.this.finished) {
                        a aVar = a.this;
                        if (aVar.getVisiblePercent(aVar.rootView) > 90) {
                            a.this.play();
                            try {
                                a.this.rootView.findViewById(R.id.media).findViewById(R.id.play).setVisibility(8);
                            } catch (Throwable unused) {
                            }
                            if (!a.this.started) {
                                a.this.report(com.appnext.ads.a.I);
                                a.this.started = true;
                            }
                        }
                    }
                    a.this.mHandler.postDelayed(a.this.tick, 33);
                    if (a.this.userMute) {
                        a.this.mediaPlayer.setVolume(0.0f, 0.0f);
                    } else {
                        a.this.mediaPlayer.setVolume(1.0f, 1.0f);
                    }
                }
            });
            this.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                /* class com.appnext.banners.a.AnonymousClass13 */

                public final void onCompletion(MediaPlayer mediaPlayer) {
                    if (a.this.mediaPlayer != null && a.this.mediaPlayer.getCurrentPosition() != 0 && a.this.mediaPlayer.getDuration() != 0 && !a.this.finished) {
                        StringBuilder sb = new StringBuilder("onCompletion. ");
                        sb.append(a.this.mediaPlayer.getCurrentPosition());
                        sb.append("/");
                        sb.append(a.this.mediaPlayer.getDuration());
                        a.this.finished = true;
                        a.this.report(com.appnext.ads.a.M);
                    }
                }
            });
            this.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                /* class com.appnext.banners.a.AnonymousClass14 */

                public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    if (i == -38 && i2 == 0) {
                        return true;
                    }
                    StringBuilder sb = new StringBuilder("mp error: what: ");
                    sb.append(i);
                    sb.append(" extra: ");
                    sb.append(i2);
                    return true;
                }
            });
            this.videoView.setVideoURI(Uri.parse(getVideoUrl(getSelectedAd(), this.adRequest.getVideoLength())));
            viewGroup.findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
                /* class com.appnext.banners.a.AnonymousClass15 */

                public final void onClick(View view) {
                    viewGroup.findViewById(R.id.wide_image).setVisibility(8);
                    viewGroup.findViewById(R.id.play).setVisibility(8);
                    a.this.play();
                }
            });
            if (!this.adRequest.isAutoPlay()) {
                viewGroup.findViewById(R.id.play).setVisibility(0);
                new Thread(new Runnable() {
                    /* class com.appnext.banners.a.AnonymousClass16 */

                    public final void run() {
                        final Bitmap Y = f.Y(a.this.getSelectedAd().getWideImageURL());
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            /* class com.appnext.banners.a.AnonymousClass16.AnonymousClass1 */

                            public final void run() {
                                ((ImageView) viewGroup.findViewById(R.id.wide_image)).setImageBitmap(Y);
                                viewGroup.findViewById(R.id.wide_image).setVisibility(0);
                            }
                        });
                    }
                }).start();
            }
        } catch (Throwable unused2) {
        }
    }

    /* access modifiers changed from: protected */
    public String getVideoUrl(AppnextAd appnextAd, String str) {
        if (str.equals("30")) {
            String videoUrl30Sec = appnextAd.getVideoUrl30Sec();
            if (videoUrl30Sec.equals("")) {
                videoUrl30Sec = appnextAd.getVideoUrl();
            }
            if (videoUrl30Sec.equals("")) {
                videoUrl30Sec = appnextAd.getVideoUrlHigh30Sec();
            }
            if (videoUrl30Sec.equals("")) {
                return appnextAd.getVideoUrlHigh();
            }
            return videoUrl30Sec;
        }
        String videoUrl = appnextAd.getVideoUrl();
        if (videoUrl.equals("")) {
            videoUrl = appnextAd.getVideoUrl30Sec();
        }
        if (videoUrl.equals("")) {
            videoUrl = appnextAd.getVideoUrlHigh();
        }
        return videoUrl.equals("") ? appnextAd.getVideoUrlHigh30Sec() : videoUrl;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkProgress() {
        try {
            if (this.mediaPlayer != null) {
                int currentPosition2 = (int) ((((float) this.mediaPlayer.getCurrentPosition()) / ((float) this.mediaPlayer.getDuration())) * 100.0f);
                if (currentPosition2 > 25 && this.lastProgress == 0) {
                    this.lastProgress = 25;
                    report(com.appnext.ads.a.J);
                } else if (currentPosition2 > 50 && this.lastProgress == 25) {
                    this.lastProgress = 50;
                    report(com.appnext.ads.a.K);
                } else if (currentPosition2 > 75 && this.lastProgress == 50) {
                    this.lastProgress = 75;
                    report(com.appnext.ads.a.L);
                }
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.appnext.banners.e
    public void onScrollChanged(int i) {
        try {
            if (this.mediaPlayer != null && !this.finished) {
                if (this.mediaPlayer.isPlaying() && i < 90) {
                    try {
                        this.rootView.findViewById(R.id.media).findViewById(R.id.play).setVisibility(0);
                    } catch (Throwable unused) {
                    }
                    pause();
                }
                if (!this.mediaPlayer.isPlaying() && i > 90 && this.adRequest.isAutoPlay()) {
                    StringBuilder sb = new StringBuilder("start. ");
                    sb.append(this.mediaPlayer.getCurrentPosition());
                    sb.append("/");
                    sb.append(this.mediaPlayer.getDuration());
                    play();
                    try {
                        this.rootView.findViewById(R.id.media).findViewById(R.id.play).setVisibility(8);
                    } catch (Throwable unused2) {
                    }
                    if (!this.started) {
                        report(com.appnext.ads.a.I);
                        this.started = true;
                    }
                }
            }
        } catch (Throwable unused3) {
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|5|6|(1:8)|9|10|(1:12)|13|14|15|16|(3:18|19|21)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0037 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002a */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002e A[Catch:{ all -> 0x0037 }] */
    @Override // com.appnext.banners.e
    public void destroy() {
        super.destroy();
        try {
            this.userAction.destroy();
        } catch (Throwable unused) {
        }
        if (this.videoView != null) {
            this.videoView.setOnCompletionListener(null);
            this.videoView.setOnErrorListener(null);
            this.videoView.setOnPreparedListener(null);
            this.videoView.suspend();
            this.videoView = null;
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        if (this.serviceHelper != null) {
            this.serviceHelper.a(this.context);
        }
        this.serviceHelper = null;
        try {
            this.bannerAd.destroy();
            this.bannerAd = null;
        } catch (Throwable unused2) {
        }
        this.adRequest = null;
        try {
            this.mHandler.removeCallbacksAndMessages(null);
        } catch (Throwable unused3) {
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        destroy();
    }

    @Override // com.appnext.banners.e
    public void play() {
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null && !mediaPlayer2.isPlaying()) {
            this.mediaPlayer.start();
        }
    }

    @Override // com.appnext.banners.e
    public void pause() {
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null && mediaPlayer2.isPlaying()) {
            this.mediaPlayer.pause();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void report(String str) {
        try {
            if (this.bannerAd != null) {
                String tid = this.bannerAd.getTID();
                String vid = this.bannerAd.getVID();
                String auid = this.bannerAd.getAUID();
                String str2 = "";
                String placementId = getPlacementId() == null ? str2 : getPlacementId();
                String sessionId = this.bannerAd.getSessionId();
                String str3 = this.template;
                String bannerID = getSelectedAd() != null ? getSelectedAd().getBannerID() : str2;
                if (getSelectedAd() != null) {
                    str2 = getSelectedAd().getCampaignID();
                }
                f.a(tid, vid, auid, placementId, sessionId, str, str3, bannerID, str2);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.appnext.banners.e
    public boolean isClickEnabled() {
        return this.clickEnabled;
    }

    @Override // com.appnext.banners.e
    public void setClickEnabled(boolean z) {
        this.clickEnabled = z;
    }

    /* access modifiers changed from: protected */
    public BannerAd getBannerAd() {
        return this.bannerAd;
    }

    /* access modifiers changed from: protected */
    public BannerAdData getSelectedAd() {
        return this.currentAd;
    }

    /* access modifiers changed from: protected */
    public void setSelectedAd(BannerAdData bannerAdData) {
        this.currentAd = bannerAdData;
    }

    /* access modifiers changed from: protected */
    public BannerAdRequest getAdRequest() {
        return this.adRequest;
    }

    /* access modifiers changed from: protected */
    public void setReportedImpression(boolean z) {
        this.reportedImpression = z;
    }

    /* access modifiers changed from: protected */
    public boolean isReportedImpression() {
        return this.reportedImpression;
    }

    /* access modifiers changed from: protected */
    public q getUserAction() {
        return this.userAction;
    }

    /* access modifiers changed from: protected */
    public ArrayList<AppnextAd> getAds() {
        return this.ads;
    }
}
