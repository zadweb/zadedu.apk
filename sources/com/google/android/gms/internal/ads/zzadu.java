package com.google.android.gms.internal.ads;

/* access modifiers changed from: package-private */
@zzadh
public final class zzadu extends Exception {
    private final int mErrorCode;

    public zzadu(String str, int i) {
        super(str);
        this.mErrorCode = i;
    }

    public final int getErrorCode() {
        return this.mErrorCode;
    }
}
