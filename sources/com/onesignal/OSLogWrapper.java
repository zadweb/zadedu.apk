package com.onesignal;

import com.onesignal.OneSignal;

class OSLogWrapper implements OSLogger {
    OSLogWrapper() {
    }

    @Override // com.onesignal.OSLogger
    public void debug(String str) {
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, str);
    }

    @Override // com.onesignal.OSLogger
    public void error(String str, Throwable th) {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, str, th);
    }
}
