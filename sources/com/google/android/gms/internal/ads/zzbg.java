package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbg extends zzbfc<zzbg> {
    public Integer zzfe;
    private Integer zzff;
    public byte[] zzgq = null;
    public byte[][] zzgv = zzbfl.zzece;

    public zzbg() {
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzbg zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                int zzb = zzbfl.zzb(zzbez, 10);
                byte[][] bArr = this.zzgv;
                int length = bArr == null ? 0 : bArr.length;
                int i = zzb + length;
                byte[][] bArr2 = new byte[i][];
                if (length != 0) {
                    System.arraycopy(this.zzgv, 0, bArr2, 0, length);
                }
                while (length < i - 1) {
                    bArr2[length] = zzbez.readBytes();
                    zzbez.zzabk();
                    length++;
                }
                bArr2[length] = zzbez.readBytes();
                this.zzgv = bArr2;
            } else if (zzabk == 18) {
                this.zzgq = zzbez.readBytes();
            } else if (zzabk == 24) {
                zzbez.getPosition();
                this.zzff = Integer.valueOf(zzaz.zze(zzbez.zzacc()));
            } else if (zzabk == 32) {
                int position = zzbez.getPosition();
                try {
                    this.zzfe = Integer.valueOf(zzaz.zzf(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(position);
                    zza(zzbez, zzabk);
                }
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final void zza(zzbfa zzbfa) throws IOException {
        byte[][] bArr = this.zzgv;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            while (true) {
                byte[][] bArr2 = this.zzgv;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    zzbfa.zza(1, bArr3);
                }
                i++;
            }
        }
        byte[] bArr4 = this.zzgq;
        if (bArr4 != null) {
            zzbfa.zza(2, bArr4);
        }
        Integer num = this.zzff;
        if (num != null) {
            zzbfa.zzm(3, num.intValue());
        }
        Integer num2 = this.zzfe;
        if (num2 != null) {
            zzbfa.zzm(4, num2.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzbfc, com.google.android.gms.internal.ads.zzbfi
    public final int zzr() {
        int zzr = super.zzr();
        byte[][] bArr = this.zzgv;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte[][] bArr2 = this.zzgv;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    i3++;
                    i2 += zzbfa.zzv(bArr3);
                }
                i++;
            }
            zzr = zzr + i2 + (i3 * 1);
        }
        byte[] bArr4 = this.zzgq;
        if (bArr4 != null) {
            zzr += zzbfa.zzb(2, bArr4);
        }
        Integer num = this.zzff;
        if (num != null) {
            zzr += zzbfa.zzq(3, num.intValue());
        }
        Integer num2 = this.zzfe;
        return num2 != null ? zzr + zzbfa.zzq(4, num2.intValue()) : zzr;
    }
}
