package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

public final class zzaym extends zzazo {
    public zzaym(byte[] bArr) throws InvalidKeyException {
        super(bArr);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzazo
    public final zzazn zzc(byte[] bArr, int i) throws InvalidKeyException {
        return new zzayl(bArr, i);
    }

    @Override // com.google.android.gms.internal.ads.zzazo, com.google.android.gms.internal.ads.zzatz
    public final /* bridge */ /* synthetic */ byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return super.zzc(bArr, bArr2);
    }
}
