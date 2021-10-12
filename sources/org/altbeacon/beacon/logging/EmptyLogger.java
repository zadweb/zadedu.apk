package org.altbeacon.beacon.logging;

final class EmptyLogger implements Logger {
    @Override // org.altbeacon.beacon.logging.Logger
    public void d(String str, String str2, Object... objArr) {
    }

    @Override // org.altbeacon.beacon.logging.Logger
    public void e(String str, String str2, Object... objArr) {
    }

    @Override // org.altbeacon.beacon.logging.Logger
    public void e(Throwable th, String str, String str2, Object... objArr) {
    }

    @Override // org.altbeacon.beacon.logging.Logger
    public void i(String str, String str2, Object... objArr) {
    }

    @Override // org.altbeacon.beacon.logging.Logger
    public void w(String str, String str2, Object... objArr) {
    }

    @Override // org.altbeacon.beacon.logging.Logger
    public void w(Throwable th, String str, String str2, Object... objArr) {
    }

    EmptyLogger() {
    }
}
