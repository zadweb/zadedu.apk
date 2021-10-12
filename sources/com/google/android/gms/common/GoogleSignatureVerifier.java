package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public class GoogleSignatureVerifier {
    @Nullable
    private static GoogleSignatureVerifier zza;
    private final Context zzb;
    private volatile String zzc;

    private GoogleSignatureVerifier(Context context) {
        this.zzb = context.getApplicationContext();
    }

    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zza == null) {
                zzc.zza(context);
                zza = new GoogleSignatureVerifier(context);
            }
        }
        return zza;
    }

    public boolean isUidGoogleSigned(int i) {
        zzl zzl;
        String[] zza2 = Wrappers.packageManager(this.zzb).zza(i);
        if (zza2 != null && zza2.length != 0) {
            zzl = null;
            int length = zza2.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    zzl = (zzl) Preconditions.checkNotNull(zzl);
                    break;
                }
                zzl = zza(zza2[i2], i);
                if (zzl.zza) {
                    break;
                }
                i2++;
            }
        } else {
            zzl = zzl.zza("no pkgs");
        }
        zzl.zzc();
        return zzl.zza;
    }

    public boolean isPackageGoogleSigned(String str) {
        zzl zza2 = zza(str);
        zza2.zzc();
        return zza2.zza;
    }

    public static boolean zza(PackageInfo packageInfo, boolean z) {
        zzd zzd;
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            if (z) {
                zzd = zza(packageInfo, zzi.zza);
            } else {
                zzd = zza(packageInfo, zzi.zza[0]);
            }
            if (zzd != null) {
                return true;
            }
        }
        return false;
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (zza(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    private final zzl zza(String str, int i) {
        try {
            PackageInfo zza2 = Wrappers.packageManager(this.zzb).zza(str, 64, i);
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb);
            if (zza2 == null) {
                return zzl.zza("null pkg");
            }
            if (zza2.signatures != null) {
                if (zza2.signatures.length == 1) {
                    zzg zzg = new zzg(zza2.signatures[0].toByteArray());
                    String str2 = zza2.packageName;
                    zzl zza3 = zzc.zza(str2, zzg, honorsDebugCertificates, false);
                    if (!zza3.zza || zza2.applicationInfo == null || (zza2.applicationInfo.flags & 2) == 0 || !zzc.zza(str2, zzg, false, true).zza) {
                        return zza3;
                    }
                    return zzl.zza("debuggable release cert app rejected");
                }
            }
            return zzl.zza("single cert required");
        } catch (PackageManager.NameNotFoundException unused) {
            String valueOf = String.valueOf(str);
            return zzl.zza(valueOf.length() != 0 ? "no pkg ".concat(valueOf) : new String("no pkg "));
        }
    }

    private final zzl zza(String str) {
        zzl zzl;
        if (str == null) {
            return zzl.zza("null pkg");
        }
        if (str.equals(this.zzc)) {
            return zzl.zza();
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(this.zzb).getPackageInfo(str, 64);
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb);
            if (packageInfo == null) {
                zzl = zzl.zza("null pkg");
            } else if (packageInfo.signatures == null || packageInfo.signatures.length != 1) {
                zzl = zzl.zza("single cert required");
            } else {
                zzg zzg = new zzg(packageInfo.signatures[0].toByteArray());
                String str2 = packageInfo.packageName;
                zzl zza2 = zzc.zza(str2, zzg, honorsDebugCertificates, false);
                zzl = (!zza2.zza || packageInfo.applicationInfo == null || (packageInfo.applicationInfo.flags & 2) == 0 || !zzc.zza(str2, zzg, false, true).zza) ? zza2 : zzl.zza("debuggable release cert app rejected");
            }
            if (zzl.zza) {
                this.zzc = str;
            }
            return zzl;
        } catch (PackageManager.NameNotFoundException unused) {
            String valueOf = String.valueOf(str);
            return zzl.zza(valueOf.length() != 0 ? "no pkg ".concat(valueOf) : new String("no pkg "));
        }
    }

    @Nullable
    private static zzd zza(PackageInfo packageInfo, zzd... zzdArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzg zzg = new zzg(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzdArr.length; i++) {
            if (zzdArr[i].equals(zzg)) {
                return zzdArr[i];
            }
        }
        return null;
    }
}
