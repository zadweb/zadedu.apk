package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@zzadh
public final class zzajm implements zzakh {
    private Context mContext;
    private final Object mLock = new Object();
    @Nullable
    private zzgf zzahz = null;
    private final zzajt zzcpl = new zzajt();
    private final zzakd zzcpm = new zzakd();
    @Nullable
    private zznn zzcpn = null;
    @Nullable
    private zzgk zzcpo = null;
    @Nullable
    private Boolean zzcpp = null;
    private String zzcpq;
    private final AtomicInteger zzcpr = new AtomicInteger(0);
    private final zzajp zzcps = new zzajp(null);
    private final Object zzcpt = new Object();
    private zzanz<ArrayList<String>> zzcpu;
    private zzes zzvy;
    private zzang zzyf;
    private boolean zzzv = false;

    @Nullable
    private final zzgk zza(@Nullable Context context, boolean z, boolean z2) {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzawk)).booleanValue() || !PlatformVersion.isAtLeastIceCreamSandwich()) {
            return null;
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzaws)).booleanValue()) {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzawq)).booleanValue()) {
                return null;
            }
        }
        if (z && z2) {
            return null;
        }
        synchronized (this.mLock) {
            if (Looper.getMainLooper() != null) {
                if (context != null) {
                    if (this.zzahz == null) {
                        this.zzahz = new zzgf();
                    }
                    if (this.zzcpo == null) {
                        this.zzcpo = new zzgk(this.zzahz, zzadb.zzc(context, this.zzyf));
                    }
                    this.zzcpo.zzgw();
                    zzakb.zzdj("start fetching content...");
                    return this.zzcpo;
                }
            }
            return null;
        }
    }

    private static ArrayList<String> zzag(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getApplicationInfo().packageName, 4096);
            if (!(packageInfo.requestedPermissions == null || packageInfo.requestedPermissionsFlags == null)) {
                for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                    if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                        arrayList.add(packageInfo.requestedPermissions[i]);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return arrayList;
    }

    @Nullable
    public final Context getApplicationContext() {
        return this.mContext;
    }

    @Nullable
    public final Resources getResources() {
        if (this.zzyf.zzcvg) {
            return this.mContext.getResources();
        }
        try {
            DynamiteModule load = DynamiteModule.load(this.mContext, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID);
            if (load != null) {
                return load.getModuleContext().getResources();
            }
            return null;
        } catch (DynamiteModule.LoadingException e) {
            zzakb.zzc("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public final void zza(Boolean bool) {
        synchronized (this.mLock) {
            this.zzcpp = bool;
        }
    }

    public final void zza(Throwable th, String str) {
        zzadb.zzc(this.mContext, this.zzyf).zza(th, str);
    }

    public final void zzaa(boolean z) {
        this.zzcps.zzaa(z);
    }

    @Nullable
    public final zzgk zzaf(@Nullable Context context) {
        return zza(context, this.zzcpm.zzqu(), this.zzcpm.zzqw());
    }

    public final void zzb(Throwable th, String str) {
        zzadb.zzc(this.mContext, this.zzyf).zza(th, str, ((Float) zzkb.zzik().zzd(zznk.zzaul)).floatValue());
    }

    public final void zzd(Context context, zzang zzang) {
        zznn zznn;
        synchronized (this.mLock) {
            if (!this.zzzv) {
                this.mContext = context.getApplicationContext();
                this.zzyf = zzang;
                zzbv.zzen().zza(zzbv.zzep());
                this.zzcpm.initialize(this.mContext);
                this.zzcpm.zza(this);
                zzadb.zzc(this.mContext, this.zzyf);
                this.zzcpq = zzbv.zzek().zzm(context, zzang.zzcw);
                this.zzvy = new zzes(context.getApplicationContext(), this.zzyf);
                zzbv.zzet();
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzawh)).booleanValue()) {
                    zzakb.v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                    zznn = null;
                } else {
                    zznn = new zznn();
                }
                this.zzcpn = zznn;
                zzanm.zza((zzanz) new zzajo(this).zznt(), "AppState.registerCsiReporter");
                this.zzzv = true;
                zzqi();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzakh
    public final void zzd(Bundle bundle) {
        if (bundle.containsKey("content_url_opted_out") && bundle.containsKey("content_vertical_opted_out")) {
            zza(this.mContext, bundle.getBoolean("content_url_opted_out"), bundle.getBoolean("content_vertical_opted_out"));
        }
    }

    public final zzajt zzpx() {
        return this.zzcpl;
    }

    @Nullable
    public final zznn zzpy() {
        zznn zznn;
        synchronized (this.mLock) {
            zznn = this.zzcpn;
        }
        return zznn;
    }

    public final Boolean zzpz() {
        Boolean bool;
        synchronized (this.mLock) {
            bool = this.zzcpp;
        }
        return bool;
    }

    public final boolean zzqa() {
        return this.zzcps.zzqa();
    }

    public final boolean zzqb() {
        return this.zzcps.zzqb();
    }

    public final void zzqc() {
        this.zzcps.zzqc();
    }

    public final zzes zzqd() {
        return this.zzvy;
    }

    public final void zzqe() {
        this.zzcpr.incrementAndGet();
    }

    public final void zzqf() {
        this.zzcpr.decrementAndGet();
    }

    public final int zzqg() {
        return this.zzcpr.get();
    }

    public final zzakd zzqh() {
        zzakd zzakd;
        synchronized (this.mLock) {
            zzakd = this.zzcpm;
        }
        return zzakd;
    }

    public final zzanz<ArrayList<String>> zzqi() {
        if (this.mContext != null && PlatformVersion.isAtLeastJellyBean()) {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzbau)).booleanValue()) {
                synchronized (this.zzcpt) {
                    if (this.zzcpu != null) {
                        return this.zzcpu;
                    }
                    zzanz<ArrayList<String>> zza = zzaki.zza(new zzajn(this));
                    this.zzcpu = zza;
                    return zza;
                }
            }
        }
        return zzano.zzi(new ArrayList());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ ArrayList zzqj() throws Exception {
        return zzag(this.mContext);
    }
}
