package com.appnext.core;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.appnext.core.e;
import com.google.android.gms.plus.PlusShare;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.common.Constants;
import java.io.IOException;
import java.util.HashMap;

public final class q {
    private static final String y = "error_no_market";
    private String banner = "";
    private e click;
    private Context context;
    private String gj = "";
    private String guid = "";
    private h hH;
    private a hI;
    private e.a hJ = new e.a() {
        /* class com.appnext.core.q.AnonymousClass3 */

        @Override // com.appnext.core.e.a
        public final void onMarket(String str) {
            AppnextAd f = q.this.hI.f();
            Ad e = q.this.hI.e();
            if (e != null && f != null && q.this.context != null) {
                if (!f.c(q.this.context, f.getAdPackage())) {
                    try {
                        if (!str.startsWith("market://details?id=" + f.getAdPackage()) && !str.startsWith(Constants.HTTP)) {
                            q.this.b(f.m("admin.appnext.com", "applink"), f.getBannerID(), e.getPlacementID(), e.getTID(), str, "SetROpenV1");
                        }
                    } catch (Throwable unused) {
                    }
                    if (q.this.hH == null) {
                        q.this.hH = new h();
                    }
                    h hVar = q.this.hH;
                    String adPackage = f.getAdPackage();
                    String m = f.m("admin.appnext.com", "applink");
                    String bannerID = f.getBannerID();
                    String placementID = e.getPlacementID();
                    String tid = e.getTID();
                    String vid = e.getVID();
                    String auid = e.getAUID();
                    hVar.am = adPackage;
                    hVar.an = str;
                    hVar.guid = m;
                    hVar.ao = bannerID;
                    hVar.ap = placementID;
                    hVar.at = null;
                    hVar.aq = tid;
                    hVar.ar = vid;
                    hVar.as = auid;
                    q.this.hH.t(q.this.context.getApplicationContext());
                } else if (str.startsWith("market://")) {
                    try {
                        Intent launchIntentForPackage = q.this.context.getPackageManager().getLaunchIntentForPackage(f.getAdPackage());
                        launchIntentForPackage.addFlags(268435456);
                        q.this.context.startActivity(launchIntentForPackage);
                    } catch (Throwable unused2) {
                        q.this.hI.report("error_no_market");
                    }
                } else {
                    try {
                        q.d(q.this, str);
                    } catch (Throwable unused3) {
                        q.this.hI.report("error_no_market");
                    }
                }
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:6|7|20)(1:(1:11)(3:12|13|21))) */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x003a */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x007e  */
        /* JADX WARNING: Removed duplicated region for block: B:6:0x0052 A[SYNTHETIC, Splitter:B:6:0x0052] */
        @Override // com.appnext.core.e.a
        public final void error(String str) {
            q.this.b(f.m("admin.appnext.com", "applink"), q.this.hI.f().getBannerID(), q.this.hI.e().getPlacementID(), q.this.hI.e().getTID(), str, "SetDOpenV1");
            if (!Boolean.parseBoolean(q.this.hI.g().get("urlApp_protection"))) {
                try {
                    q qVar = q.this;
                    q.d(qVar, "market://details?id=" + q.this.hI.f().getAdPackage());
                } catch (Throwable unused) {
                    q.this.hI.report("error_no_market");
                }
            } else if (str != null) {
                try {
                    q.d(q.this, str);
                } catch (Throwable unused2) {
                    q.this.hI.report("error_no_market");
                }
            }
        }
    };

    public interface a {
        Ad e();

        AppnextAd f();

        p g();

        void report(String str);
    }

    public q(Context context2, a aVar) {
        this.context = context2;
        this.click = e.k(context2);
        this.hI = aVar;
    }

    public final void b(String str, String str2, String str3, String str4, String str5, String str6) {
        e eVar = this.click;
        if (eVar != null) {
            new Thread(
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: INVOKE  
                  (wrap: java.lang.Thread : 0x0016: CONSTRUCTOR  (r9v0 java.lang.Thread) = 
                  (wrap: com.appnext.core.e$7 : 0x0013: CONSTRUCTOR  (r10v0 com.appnext.core.e$7) = 
                  (r2v0 'eVar' com.appnext.core.e)
                  (r12v0 'str' java.lang.String)
                  (r13v0 'str2' java.lang.String)
                  (r14v0 'str3' java.lang.String)
                  (r15v0 'str4' java.lang.String)
                  (r16v0 'str5' java.lang.String)
                  (r17v0 'str6' java.lang.String)
                 call: com.appnext.core.e.7.<init>(com.appnext.core.e, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
                 call: java.lang.Thread.<init>(java.lang.Runnable):void type: CONSTRUCTOR)
                 type: VIRTUAL call: java.lang.Thread.start():void in method: com.appnext.core.q.b(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.util.ArrayList.forEach(Unknown Source)
                	at java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.util.stream.Sink$ChainedReference.end(Unknown Source)
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0016: CONSTRUCTOR  (r9v0 java.lang.Thread) = 
                  (wrap: com.appnext.core.e$7 : 0x0013: CONSTRUCTOR  (r10v0 com.appnext.core.e$7) = 
                  (r2v0 'eVar' com.appnext.core.e)
                  (r12v0 'str' java.lang.String)
                  (r13v0 'str2' java.lang.String)
                  (r14v0 'str3' java.lang.String)
                  (r15v0 'str4' java.lang.String)
                  (r16v0 'str5' java.lang.String)
                  (r17v0 'str6' java.lang.String)
                 call: com.appnext.core.e.7.<init>(com.appnext.core.e, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
                 call: java.lang.Thread.<init>(java.lang.Runnable):void type: CONSTRUCTOR in method: com.appnext.core.q.b(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:87)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:715)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                	... 21 more
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: CONSTRUCTOR  (r10v0 com.appnext.core.e$7) = 
                  (r2v0 'eVar' com.appnext.core.e)
                  (r12v0 'str' java.lang.String)
                  (r13v0 'str2' java.lang.String)
                  (r14v0 'str3' java.lang.String)
                  (r15v0 'str4' java.lang.String)
                  (r16v0 'str5' java.lang.String)
                  (r17v0 'str6' java.lang.String)
                 call: com.appnext.core.e.7.<init>(com.appnext.core.e, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void type: CONSTRUCTOR in method: com.appnext.core.q.b(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:663)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                	... 27 more
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.appnext.core.e, state: GENERATED_AND_UNLOADED
                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                	... 33 more
                */
            /*
                this = this;
                r0 = r11
                com.appnext.core.e r2 = r0.click
                if (r2 != 0) goto L_0x0006
                return
            L_0x0006:
                java.lang.Thread r9 = new java.lang.Thread
                com.appnext.core.e$7 r10 = new com.appnext.core.e$7
                r1 = r10
                r3 = r12
                r4 = r13
                r5 = r14
                r6 = r15
                r7 = r16
                r8 = r17
                r1.<init>(r3, r4, r5, r6, r7, r8)
                r9.<init>(r10)
                r9.start()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.q.b(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
        }

        public final void a(AppnextAd appnextAd, String str, e.a aVar) {
            e eVar = this.click;
            if (eVar != null) {
                new Thread(
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: INVOKE  
                      (wrap: java.lang.Thread : 0x000c: CONSTRUCTOR  (r4v1 java.lang.Thread) = 
                      (wrap: com.appnext.core.e$6 : 0x0009: CONSTRUCTOR  (r0v0 com.appnext.core.e$6) = (r3v1 'eVar' com.appnext.core.e), (r2v0 'appnextAd' com.appnext.core.AppnextAd) call: com.appnext.core.e.6.<init>(com.appnext.core.e, com.appnext.core.AppnextAd):void type: CONSTRUCTOR)
                     call: java.lang.Thread.<init>(java.lang.Runnable):void type: CONSTRUCTOR)
                     type: VIRTUAL call: java.lang.Thread.start():void in method: com.appnext.core.q.a(com.appnext.core.AppnextAd, java.lang.String, com.appnext.core.e$a):void, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                    	at java.util.ArrayList.forEach(Unknown Source)
                    	at java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                    	at java.util.stream.Sink$ChainedReference.end(Unknown Source)
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000c: CONSTRUCTOR  (r4v1 java.lang.Thread) = 
                      (wrap: com.appnext.core.e$6 : 0x0009: CONSTRUCTOR  (r0v0 com.appnext.core.e$6) = (r3v1 'eVar' com.appnext.core.e), (r2v0 'appnextAd' com.appnext.core.AppnextAd) call: com.appnext.core.e.6.<init>(com.appnext.core.e, com.appnext.core.AppnextAd):void type: CONSTRUCTOR)
                     call: java.lang.Thread.<init>(java.lang.Runnable):void type: CONSTRUCTOR in method: com.appnext.core.q.a(com.appnext.core.AppnextAd, java.lang.String, com.appnext.core.e$a):void, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                    	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:87)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:715)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                    	... 21 more
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0009: CONSTRUCTOR  (r0v0 com.appnext.core.e$6) = (r3v1 'eVar' com.appnext.core.e), (r2v0 'appnextAd' com.appnext.core.AppnextAd) call: com.appnext.core.e.6.<init>(com.appnext.core.e, com.appnext.core.AppnextAd):void type: CONSTRUCTOR in method: com.appnext.core.q.a(com.appnext.core.AppnextAd, java.lang.String, com.appnext.core.e$a):void, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:663)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                    	... 27 more
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.appnext.core.e, state: GENERATED_AND_UNLOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                    	... 33 more
                    */
                /*
                    this = this;
                    com.appnext.core.e r3 = r1.click
                    if (r3 != 0) goto L_0x0005
                    return
                L_0x0005:
                    java.lang.Thread r4 = new java.lang.Thread
                    com.appnext.core.e$6 r0 = new com.appnext.core.e$6
                    r0.<init>(r2)
                    r4.<init>(r0)
                    r4.start()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.q.a(com.appnext.core.AppnextAd, java.lang.String, com.appnext.core.e$a):void");
            }

            public final void b(final AppnextAd appnextAd, final String str, final e.a aVar) {
                if (this.click != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        /* class com.appnext.core.q.AnonymousClass1 */

                        public final void run() {
                            try {
                                q.this.click.a(appnextAd.getAppURL(), appnextAd.getMarketUrl(), str + "&device=" + f.be() + "&ox=0", appnextAd.getBannerID(), new e.a() {
                                    /* class com.appnext.core.q.AnonymousClass1.AnonymousClass1 */

                                    @Override // com.appnext.core.e.a
                                    public final void onMarket(String str) {
                                        StringBuilder sb = new StringBuilder("Vta - success - ");
                                        sb.append(appnextAd.getAdTitle());
                                        sb.append(" -- ");
                                        sb.append(appnextAd.getBannerID());
                                        q.this.gj = str;
                                        q.this.guid = f.m("admin.appnext.com", "applink");
                                        q.this.banner = appnextAd.getBannerID();
                                        if (aVar != null) {
                                            aVar.onMarket(str);
                                        }
                                    }

                                    @Override // com.appnext.core.e.a
                                    public final void error(String str) {
                                        StringBuilder sb = new StringBuilder("Vta - failed - ");
                                        sb.append(appnextAd.getAdTitle());
                                        sb.append(" -- ");
                                        sb.append(appnextAd.getBannerID());
                                        q.this.gj = "";
                                        q.this.guid = "";
                                        q.this.banner = "";
                                        if (aVar != null) {
                                            aVar.error(str);
                                        }
                                    }
                                });
                            } catch (Throwable unused) {
                            }
                        }
                    });
                }
            }

            public final void a(final AppnextAd appnextAd, final String str, final String str2, final e.a aVar) {
                new Thread(new Runnable() {
                    /* class com.appnext.core.q.AnonymousClass2 */

                    public final void run() {
                        if (!q.this.bo()) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                /* class com.appnext.core.q.AnonymousClass2.AnonymousClass1 */

                                public final void run() {
                                    e.a aVar = q.this.hJ;
                                    aVar.error(str + "&device=" + f.be());
                                    if (aVar != null) {
                                        e.a aVar2 = aVar;
                                        aVar2.error(str + "&device=" + f.be());
                                    }
                                }
                            });
                        } else if (appnextAd != null) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                /* class com.appnext.core.q.AnonymousClass2.AnonymousClass2 */

                                public final void run() {
                                    if (q.this.gj.equals("") || !q.this.gj.contains(appnextAd.getAdPackage())) {
                                        new StringBuilder("click url ").append(str);
                                        String str = str + "&device=" + f.be();
                                        String webview = appnextAd.getWebview();
                                        char c = 65535;
                                        switch (webview.hashCode()) {
                                            case 48:
                                                if (webview.equals("0")) {
                                                    c = 2;
                                                    break;
                                                }
                                                break;
                                            case 49:
                                                if (webview.equals("1")) {
                                                    c = 1;
                                                    break;
                                                }
                                                break;
                                            case 50:
                                                if (webview.equals(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                                                    c = 0;
                                                    break;
                                                }
                                                break;
                                        }
                                        if (c == 0) {
                                            try {
                                                q.this.hJ.onMarket(str);
                                                if (aVar != null) {
                                                    aVar.onMarket(str);
                                                }
                                            } catch (Throwable unused) {
                                            }
                                        } else if (c == 1) {
                                            Intent intent = new Intent(q.this.context, ResultActivity.class);
                                            intent.putExtra("url", str);
                                            intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, appnextAd.getAdTitle());
                                            intent.addFlags(268435456);
                                            q.this.context.startActivity(intent);
                                            if (aVar != null) {
                                                aVar.onMarket(str);
                                            }
                                        } else if (q.this.click != null) {
                                            q.this.click.a(appnextAd.getAppURL(), appnextAd.getMarketUrl(), str, appnextAd.getBannerID(), new e.a() {
                                                /* class com.appnext.core.q.AnonymousClass2.AnonymousClass2.AnonymousClass2 */

                                                @Override // com.appnext.core.e.a
                                                public final void onMarket(String str) {
                                                    q.this.hJ.onMarket(str);
                                                    if (aVar != null) {
                                                        aVar.onMarket(str);
                                                    }
                                                }

                                                @Override // com.appnext.core.e.a
                                                public final void error(String str) {
                                                    q.this.hJ.error(str);
                                                    if (aVar != null) {
                                                        aVar.error(str);
                                                    }
                                                }
                                            }, 1000 * Long.parseLong(q.this.hI.g().get("resolve_timeout")));
                                        }
                                    } else {
                                        new Thread(new Runnable() {
                                            /* class com.appnext.core.q.AnonymousClass2.AnonymousClass2.AnonymousClass1 */

                                            public final void run() {
                                                try {
                                                    f.a("https://admin.appnext.com/AdminService.asmx/SetRL?guid=" + q.this.guid + "&bid=" + q.this.banner + "&pid=" + str2, (HashMap<String, String>) null);
                                                } catch (Throwable unused) {
                                                }
                                            }
                                        }).start();
                                        q.this.hJ.onMarket(q.this.gj);
                                        if (aVar != null) {
                                            aVar.onMarket(q.this.gj);
                                        }
                                        q.this.gj = "";
                                    }
                                }
                            });
                        }
                    }
                }).start();
            }

            private void openLink(String str) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                intent.addFlags(268435456);
                this.context.startActivity(intent);
            }

            public final void e(AppnextAd appnextAd) {
                try {
                    if (this.click != null) {
                        this.click.e(appnextAd);
                    }
                } catch (Throwable unused) {
                }
            }

            /* access modifiers changed from: protected */
            public final boolean bo() {
                try {
                    if (this.context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
                        f.a("http://www.appnext.com/myid.html", (HashMap<String, String>) null);
                        return true;
                    }
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        return true;
                    }
                    throw new IOException();
                } catch (Throwable unused) {
                    return false;
                }
            }

            public final void destroy() {
                try {
                    if (this.hH != null) {
                        this.hH.a(this.context);
                    }
                    this.hH = null;
                } catch (Throwable unused) {
                }
                this.context = null;
                if (this.click != null) {
                    this.click = null;
                }
            }

            static /* synthetic */ void d(q qVar, String str) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                intent.addFlags(268435456);
                qVar.context.startActivity(intent);
            }
        }
