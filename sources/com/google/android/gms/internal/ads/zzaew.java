package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.IOUtils;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* access modifiers changed from: package-private */
public final class zzaew implements Runnable {
    private final /* synthetic */ OutputStream zzcfw;
    private final /* synthetic */ byte[] zzcfx;

    zzaew(zzaev zzaev, OutputStream outputStream, byte[] bArr) {
        this.zzcfw = outputStream;
        this.zzcfx = bArr;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:21:0x003d */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003f  */
    public final void run() {
        OutputStream outputStream;
        Throwable th;
        DataOutputStream dataOutputStream;
        IOException e;
        try {
            DataOutputStream dataOutputStream2 = new DataOutputStream(this.zzcfw);
            try {
                dataOutputStream2.writeInt(this.zzcfx.length);
                dataOutputStream2.write(this.zzcfx);
                IOUtils.closeQuietly(dataOutputStream2);
            } catch (IOException e2) {
                e = e2;
                dataOutputStream = dataOutputStream2;
                try {
                    zzakb.zzb("Error transporting the ad response", e);
                    zzbv.zzeo().zza(e, "LargeParcelTeleporter.pipeData.1");
                    if (dataOutputStream != null) {
                        IOUtils.closeQuietly(this.zzcfw);
                    } else {
                        IOUtils.closeQuietly(dataOutputStream);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = dataOutputStream;
                    if (outputStream == null) {
                        outputStream = this.zzcfw;
                    }
                    IOUtils.closeQuietly(outputStream);
                    throw th;
                }
            }
        } catch (IOException e3) {
            dataOutputStream = null;
            e = e3;
            zzakb.zzb("Error transporting the ad response", e);
            zzbv.zzeo().zza(e, "LargeParcelTeleporter.pipeData.1");
            if (dataOutputStream != null) {
            }
        } catch (Throwable th3) {
            outputStream = null;
            th = th3;
            if (outputStream == null) {
            }
            IOUtils.closeQuietly(outputStream);
            throw th;
        }
    }
}
