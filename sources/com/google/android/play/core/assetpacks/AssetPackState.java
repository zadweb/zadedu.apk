package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.internal.i;

public abstract class AssetPackState {
    public static AssetPackState c(String str, int i, int i2, long j, long j2, double d, int i3, String str2) {
        return new az(str, i, i2, j, j2, (int) Math.rint(100.0d * d), i3, str2);
    }

    static AssetPackState d(Bundle bundle, String str, bo boVar, as asVar) {
        int a2 = asVar.a(bundle.getInt(i.e("status", str)), str);
        int i = bundle.getInt(i.e("error_code", str));
        long j = bundle.getLong(i.e("bytes_downloaded", str));
        long j2 = bundle.getLong(i.e("total_bytes_to_download", str));
        double b = boVar.b(str);
        long j3 = bundle.getLong(i.e("pack_version", str));
        long j4 = bundle.getLong(i.e("pack_base_version", str));
        int i2 = 1;
        if (!(a2 != 4 || j4 == 0 || j4 == j3)) {
            i2 = 2;
        }
        return c(str, a2, i, j, j2, b, i2, bundle.getString(i.e("pack_version_tag", str), ""));
    }

    public abstract int a();

    public abstract String b();

    public abstract long bytesDownloaded();

    public abstract int errorCode();

    public abstract String name();

    public abstract int status();

    public abstract long totalBytesToDownload();

    public abstract int transferProgressPercentage();
}
