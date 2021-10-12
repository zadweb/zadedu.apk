package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* access modifiers changed from: package-private */
public final class zzavm implements zzaua<zzauk> {
    @Override // com.google.android.gms.internal.ads.zzaua
    public final zzaug<zzauk> zzb(String str, String str2, int i) throws GeneralSecurityException {
        String lowerCase = str2.toLowerCase();
        char c = 65535;
        if (((lowerCase.hashCode() == 107855 && lowerCase.equals("mac")) ? (char) 0 : 65535) == 0) {
            if (str.hashCode() == 836622442 && str.equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
                c = 0;
            }
            if (c == 0) {
                zzavk zzavk = new zzavk();
                if (i <= 0) {
                    return zzavk;
                }
                throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", str, Integer.valueOf(i)));
            }
            throw new GeneralSecurityException(String.format("No support for primitive 'Mac' with key type '%s'.", str));
        }
        throw new GeneralSecurityException(String.format("No support for primitive '%s'.", str2));
    }
}
