package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

final class zzail implements Runnable {
    private final /* synthetic */ Bitmap val$bitmap;
    private final /* synthetic */ zzaii zzcna;

    zzail(zzaii zzaii, Bitmap bitmap) {
        this.zzcna = zzaii;
        this.val$bitmap = bitmap;
    }

    public final void run() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.val$bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        synchronized (zzaii.zza(this.zzcna)) {
            zzaii.zzb(this.zzcna).zzecm = new zzbft();
            zzaii.zzb(this.zzcna).zzecm.zzedl = byteArrayOutputStream.toByteArray();
            zzaii.zzb(this.zzcna).zzecm.mimeType = "image/png";
            zzaii.zzb(this.zzcna).zzecm.zzamf = 1;
        }
    }
}
