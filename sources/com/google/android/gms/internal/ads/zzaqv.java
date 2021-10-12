package com.google.android.gms.internal.ads;

import android.webkit.ConsoleMessage;

final /* synthetic */ class zzaqv {
    static final /* synthetic */ int[] zzdbn;

    /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
    static {
        int[] iArr = new int[ConsoleMessage.MessageLevel.values().length];
        zzdbn = iArr;
        iArr[ConsoleMessage.MessageLevel.ERROR.ordinal()] = 1;
        zzdbn[ConsoleMessage.MessageLevel.WARNING.ordinal()] = 2;
        zzdbn[ConsoleMessage.MessageLevel.LOG.ordinal()] = 3;
        zzdbn[ConsoleMessage.MessageLevel.TIP.ordinal()] = 4;
        zzdbn[ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 5;
    }
}
