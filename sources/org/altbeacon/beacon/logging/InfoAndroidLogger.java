package org.altbeacon.beacon.logging;

import android.util.Log;

final class InfoAndroidLogger extends AbstractAndroidLogger {
    @Override // org.altbeacon.beacon.logging.Logger
    public void d(String str, String str2, Object... objArr) {
    }

    InfoAndroidLogger() {
    }

    @Override // org.altbeacon.beacon.logging.Logger
    public void i(String str, String str2, Object... objArr) {
        Log.i(str, formatString(str2, objArr));
    }

    @Override // org.altbeacon.beacon.logging.Logger
    public void w(String str, String str2, Object... objArr) {
        Log.w(str, formatString(str2, objArr));
    }

    @Override // org.altbeacon.beacon.logging.Logger
    public void w(Throwable th, String str, String str2, Object... objArr) {
        Log.w(str, formatString(str2, objArr), th);
    }

    @Override // org.altbeacon.beacon.logging.Logger
    public void e(String str, String str2, Object... objArr) {
        Log.e(str, formatString(str2, objArr));
    }

    @Override // org.altbeacon.beacon.logging.Logger
    public void e(Throwable th, String str, String str2, Object... objArr) {
        Log.e(str, formatString(str2, objArr), th);
    }
}
