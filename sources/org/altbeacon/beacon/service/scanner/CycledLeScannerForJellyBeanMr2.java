package org.altbeacon.beacon.service.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

public class CycledLeScannerForJellyBeanMr2 extends CycledLeScanner {
    private BluetoothAdapter.LeScanCallback leScanCallback;

    public CycledLeScannerForJellyBeanMr2(Context context, long j, long j2, boolean z, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver bluetoothCrashResolver) {
        super(context, j, j2, z, cycledLeScanCallback, bluetoothCrashResolver);
    }

    /* access modifiers changed from: protected */
    @Override // org.altbeacon.beacon.service.scanner.CycledLeScanner
    public void stopScan() {
        postStopLeScan();
    }

    /* access modifiers changed from: protected */
    @Override // org.altbeacon.beacon.service.scanner.CycledLeScanner
    public boolean deferScanIfNeeded() {
        long elapsedRealtime = this.mNextScanCycleStartTime - SystemClock.elapsedRealtime();
        if (elapsedRealtime <= 0) {
            return false;
        }
        LogManager.d("CycledLeScannerForJellyBeanMr2", "Waiting to start next Bluetooth scan for another %s milliseconds", Long.valueOf(elapsedRealtime));
        if (this.mBackgroundFlag) {
            setWakeUpAlarm();
        }
        Handler handler = this.mHandler;
        AnonymousClass1 r4 = new Runnable() {
            /* class org.altbeacon.beacon.service.scanner.CycledLeScannerForJellyBeanMr2.AnonymousClass1 */

            public void run() {
                CycledLeScannerForJellyBeanMr2.this.scanLeDevice(true);
            }
        };
        if (elapsedRealtime > 1000) {
            elapsedRealtime = 1000;
        }
        handler.postDelayed(r4, elapsedRealtime);
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // org.altbeacon.beacon.service.scanner.CycledLeScanner
    public void startScan() {
        postStartLeScan();
    }

    /* access modifiers changed from: protected */
    @Override // org.altbeacon.beacon.service.scanner.CycledLeScanner
    public void finishScan() {
        postStopLeScan();
        this.mScanningPaused = true;
    }

    private void postStartLeScan() {
        final BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
        if (bluetoothAdapter != null) {
            final BluetoothAdapter.LeScanCallback leScanCallback2 = getLeScanCallback();
            this.mScanHandler.removeCallbacksAndMessages(null);
            this.mScanHandler.post(new Runnable() {
                /* class org.altbeacon.beacon.service.scanner.CycledLeScannerForJellyBeanMr2.AnonymousClass2 */

                public void run() {
                    try {
                        bluetoothAdapter.startLeScan(leScanCallback2);
                    } catch (Exception e) {
                        LogManager.e(e, "CycledLeScannerForJellyBeanMr2", "Internal Android exception in startLeScan()", new Object[0]);
                    }
                }
            });
        }
    }

    private void postStopLeScan() {
        final BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
        if (bluetoothAdapter != null) {
            final BluetoothAdapter.LeScanCallback leScanCallback2 = getLeScanCallback();
            this.mScanHandler.removeCallbacksAndMessages(null);
            this.mScanHandler.post(new Runnable() {
                /* class org.altbeacon.beacon.service.scanner.CycledLeScannerForJellyBeanMr2.AnonymousClass3 */

                public void run() {
                    try {
                        bluetoothAdapter.stopLeScan(leScanCallback2);
                    } catch (Exception e) {
                        LogManager.e(e, "CycledLeScannerForJellyBeanMr2", "Internal Android exception in stopLeScan()", new Object[0]);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private BluetoothAdapter.LeScanCallback getLeScanCallback() {
        if (this.leScanCallback == null) {
            this.leScanCallback = new BluetoothAdapter.LeScanCallback() {
                /* class org.altbeacon.beacon.service.scanner.CycledLeScannerForJellyBeanMr2.AnonymousClass4 */

                public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                    LogManager.d("CycledLeScannerForJellyBeanMr2", "got record", new Object[0]);
                    CycledLeScannerForJellyBeanMr2.this.mCycledLeScanCallback.onLeScan(bluetoothDevice, i, bArr);
                    if (CycledLeScannerForJellyBeanMr2.this.mBluetoothCrashResolver != null) {
                        CycledLeScannerForJellyBeanMr2.this.mBluetoothCrashResolver.notifyScannedDevice(bluetoothDevice, CycledLeScannerForJellyBeanMr2.this.getLeScanCallback());
                    }
                }
            };
        }
        return this.leScanCallback;
    }
}
