package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;

@zzadh
public class zzjn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzjn> CREATOR = new zzjo();
    public final int height;
    public final int heightPixels;
    public final int width;
    public final int widthPixels;
    public final String zzarb;
    public final boolean zzarc;
    public final zzjn[] zzard;
    public final boolean zzare;
    public final boolean zzarf;
    public boolean zzarg;

    public zzjn() {
        this("interstitial_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public zzjn(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0080  */
    public zzjn(Context context, AdSize[] adSizeArr) {
        int i;
        int i2;
        String str;
        int i3;
        double d;
        double d2;
        AdSize adSize = adSizeArr[0];
        this.zzarc = false;
        boolean isFluid = adSize.isFluid();
        this.zzarf = isFluid;
        if (isFluid) {
            this.width = AdSize.BANNER.getWidth();
            i = AdSize.BANNER.getHeight();
        } else {
            this.width = adSize.getWidth();
            i = adSize.getHeight();
        }
        this.height = i;
        boolean z = this.width == -1;
        boolean z2 = this.height == -2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (z) {
            zzkb.zzif();
            if (zzamu.zzbi(context)) {
                zzkb.zzif();
                if (zzamu.zzbj(context)) {
                    int i4 = displayMetrics.widthPixels;
                    zzkb.zzif();
                    i3 = i4 - zzamu.zzbk(context);
                    this.widthPixels = i3;
                    d = (double) (((float) this.widthPixels) / displayMetrics.density);
                    i2 = (int) d;
                    d2 = (double) i2;
                    Double.isNaN(d);
                    Double.isNaN(d2);
                    if (d - d2 >= 0.01d) {
                        i2++;
                    }
                }
            }
            i3 = displayMetrics.widthPixels;
            this.widthPixels = i3;
            d = (double) (((float) this.widthPixels) / displayMetrics.density);
            i2 = (int) d;
            d2 = (double) i2;
            Double.isNaN(d);
            Double.isNaN(d2);
            if (d - d2 >= 0.01d) {
            }
        } else {
            i2 = this.width;
            zzkb.zzif();
            this.widthPixels = zzamu.zza(displayMetrics, this.width);
        }
        int zzd = z2 ? zzd(displayMetrics) : this.height;
        zzkb.zzif();
        this.heightPixels = zzamu.zza(displayMetrics, zzd);
        if (z || z2) {
            StringBuilder sb = new StringBuilder(26);
            sb.append(i2);
            sb.append(AvidJSONUtil.KEY_X);
            sb.append(zzd);
            sb.append("_as");
            str = sb.toString();
        } else {
            str = this.zzarf ? "320x50_mb" : adSize.toString();
        }
        this.zzarb = str;
        if (adSizeArr.length > 1) {
            this.zzard = new zzjn[adSizeArr.length];
            for (int i5 = 0; i5 < adSizeArr.length; i5++) {
                this.zzard[i5] = new zzjn(context, adSizeArr[i5]);
            }
        } else {
            this.zzard = null;
        }
        this.zzare = false;
        this.zzarg = false;
    }

    public zzjn(zzjn zzjn, zzjn[] zzjnArr) {
        this(zzjn.zzarb, zzjn.height, zzjn.heightPixels, zzjn.zzarc, zzjn.width, zzjn.widthPixels, zzjnArr, zzjn.zzare, zzjn.zzarf, zzjn.zzarg);
    }

    zzjn(String str, int i, int i2, boolean z, int i3, int i4, zzjn[] zzjnArr, boolean z2, boolean z3, boolean z4) {
        this.zzarb = str;
        this.height = i;
        this.heightPixels = i2;
        this.zzarc = z;
        this.width = i3;
        this.widthPixels = i4;
        this.zzard = zzjnArr;
        this.zzare = z2;
        this.zzarf = z3;
        this.zzarg = z4;
    }

    public static int zzb(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzc(DisplayMetrics displayMetrics) {
        return (int) (((float) zzd(displayMetrics)) * displayMetrics.density);
    }

    private static int zzd(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public static zzjn zzf(Context context) {
        return new zzjn("320x50_mb", 0, 0, false, 0, 0, null, true, false, false);
    }

    public static zzjn zzhx() {
        return new zzjn("reward_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzarb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.heightPixels);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzarc);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.writeInt(parcel, 7, this.widthPixels);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzard, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzare);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzarf);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzarg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final AdSize zzhy() {
        return zzb.zza(this.width, this.height, this.zzarb);
    }
}
