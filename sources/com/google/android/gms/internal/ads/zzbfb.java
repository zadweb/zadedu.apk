package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfb extends IOException {
    /* JADX WARNING: Illegal instructions before constructor call */
    zzbfb(int i, int i2) {
        super(r0.toString());
        StringBuilder sb = new StringBuilder(108);
        sb.append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ");
        sb.append(i);
        sb.append(" limit ");
        sb.append(i2);
        sb.append(").");
    }
}
