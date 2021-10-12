package org.altbeacon.beacon.service.scanner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import java.util.Date;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.startup.StartupBroadcastReceiver;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

public abstract class CycledLeScanner {
    protected boolean mBackgroundFlag = false;
    protected long mBetweenScanPeriod;
    private BluetoothAdapter mBluetoothAdapter;
    protected final BluetoothCrashResolver mBluetoothCrashResolver;
    protected final Context mContext;
    private long mCurrentScanStartTime = 0;
    protected final CycledLeScanCallback mCycledLeScanCallback;
    private volatile boolean mDistinctPacketsDetectedPerScan = false;
    protected final Handler mHandler = new Handler(Looper.getMainLooper());
    private long mLastScanCycleEndTime = 0;
    private long mLastScanCycleStartTime = 0;
    private boolean mLongScanForcingEnabled = false;
    protected long mNextScanCycleStartTime = 0;
    protected boolean mRestartNeeded = false;
    private long mScanCycleStopTime = 0;
    private boolean mScanCyclerStarted = false;
    protected final Handler mScanHandler;
    private long mScanPeriod;
    private final HandlerThread mScanThread;
    private boolean mScanning;
    private boolean mScanningEnabled = false;
    private boolean mScanningLeftOn = false;
    protected boolean mScanningPaused;
    private PendingIntent mWakeUpOperation = null;

    /* access modifiers changed from: protected */
    public abstract boolean deferScanIfNeeded();

    /* access modifiers changed from: protected */
    public abstract void finishScan();

    /* access modifiers changed from: protected */
    public abstract void startScan();

    /* access modifiers changed from: protected */
    public abstract void stopScan();

    protected CycledLeScanner(Context context, long j, long j2, boolean z, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver bluetoothCrashResolver) {
        this.mScanPeriod = j;
        this.mBetweenScanPeriod = j2;
        this.mContext = context;
        this.mCycledLeScanCallback = cycledLeScanCallback;
        this.mBluetoothCrashResolver = bluetoothCrashResolver;
        this.mBackgroundFlag = z;
        HandlerThread handlerThread = new HandlerThread("CycledLeScannerThread");
        this.mScanThread = handlerThread;
        handlerThread.start();
        this.mScanHandler = new Handler(this.mScanThread.getLooper());
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a  */
    public static CycledLeScanner createScanner(Context context, long j, long j2, boolean z, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver bluetoothCrashResolver) {
        boolean z2 = false;
        if (Build.VERSION.SDK_INT < 18) {
            LogManager.w("CycledLeScanner", "Not supported prior to API 18.", new Object[0]);
            return null;
        }
        boolean z3 = true;
        if (Build.VERSION.SDK_INT < 21) {
            LogManager.i("CycledLeScanner", "This is pre Android 5.0.  We are using old scanning APIs", new Object[0]);
        } else if (Build.VERSION.SDK_INT >= 26) {
            LogManager.i("CycledLeScanner", "Using Android O scanner", new Object[0]);
            z2 = true;
        } else if (BeaconManager.isAndroidLScanningDisabled()) {
            LogManager.i("CycledLeScanner", "This is Android 5.0, but L scanning is disabled. We are using old scanning APIs", new Object[0]);
        } else {
            LogManager.i("CycledLeScanner", "This is Android 5.0.  We are using new scanning APIs", new Object[0]);
            if (!z2) {
                return new CycledLeScannerForAndroidO(context, j, j2, z, cycledLeScanCallback, bluetoothCrashResolver);
            }
            if (z3) {
                return new CycledLeScannerForLollipop(context, j, j2, z, cycledLeScanCallback, bluetoothCrashResolver);
            }
            return new CycledLeScannerForJellyBeanMr2(context, j, j2, z, cycledLeScanCallback, bluetoothCrashResolver);
        }
        z3 = false;
        if (!z2) {
        }
    }

    public void setLongScanForcingEnabled(boolean z) {
        this.mLongScanForcingEnabled = z;
    }

    public void setScanPeriods(long j, long j2, boolean z) {
        LogManager.d("CycledLeScanner", "Set scan periods called with %s, %s Background mode must have changed.", Long.valueOf(j), Long.valueOf(j2));
        if (this.mBackgroundFlag != z) {
            this.mRestartNeeded = true;
        }
        this.mBackgroundFlag = z;
        this.mScanPeriod = j;
        this.mBetweenScanPeriod = j2;
        if (z) {
            LogManager.d("CycledLeScanner", "We are in the background.  Setting wakeup alarm", new Object[0]);
            setWakeUpAlarm();
        } else {
            LogManager.d("CycledLeScanner", "We are not in the background.  Cancelling wakeup alarm", new Object[0]);
            cancelWakeUpAlarm();
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j3 = this.mNextScanCycleStartTime;
        if (j3 > elapsedRealtime) {
            long j4 = this.mLastScanCycleEndTime + j2;
            if (j4 < j3) {
                this.mNextScanCycleStartTime = j4;
                LogManager.i("CycledLeScanner", "Adjusted nextScanStartTime to be %s", new Date((this.mNextScanCycleStartTime - SystemClock.elapsedRealtime()) + System.currentTimeMillis()));
            }
        }
        long j5 = this.mScanCycleStopTime;
        if (j5 > elapsedRealtime) {
            long j6 = this.mLastScanCycleStartTime + j;
            if (j6 < j5) {
                this.mScanCycleStopTime = j6;
                LogManager.i("CycledLeScanner", "Adjusted scanStopTime to be %s", Long.valueOf(j6));
            }
        }
    }

    public void start() {
        LogManager.d("CycledLeScanner", "start called", new Object[0]);
        this.mScanningEnabled = true;
        if (!this.mScanCyclerStarted) {
            scanLeDevice(true);
        } else {
            LogManager.d("CycledLeScanner", "scanning already started", new Object[0]);
        }
    }

    public void stop() {
        LogManager.d("CycledLeScanner", "stop called", new Object[0]);
        this.mScanningEnabled = false;
        if (this.mScanCyclerStarted) {
            scanLeDevice(false);
            if (this.mScanningLeftOn) {
                LogManager.d("CycledLeScanner", "Stopping scanning previously left on.", new Object[0]);
                this.mScanningLeftOn = false;
                try {
                    LogManager.d("CycledLeScanner", "stopping bluetooth le scan", new Object[0]);
                    finishScan();
                } catch (Exception e) {
                    LogManager.w(e, "CycledLeScanner", "Internal Android exception scanning for beacons", new Object[0]);
                }
            }
        } else {
            LogManager.d("CycledLeScanner", "scanning already stopped", new Object[0]);
        }
    }

    public boolean getDistinctPacketsDetectedPerScan() {
        return this.mDistinctPacketsDetectedPerScan;
    }

    public void setDistinctPacketsDetectedPerScan(boolean z) {
        this.mDistinctPacketsDetectedPerScan = z;
    }

    public void destroy() {
        LogManager.d("CycledLeScanner", "Destroying", new Object[0]);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mScanHandler.post(new Runnable() {
            /* class org.altbeacon.beacon.service.scanner.CycledLeScanner.AnonymousClass1 */

            public void run() {
                LogManager.d("CycledLeScanner", "Quitting scan thread", new Object[0]);
                CycledLeScanner.this.mScanThread.quit();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void scanLeDevice(Boolean bool) {
        try {
            this.mScanCyclerStarted = true;
            if (getBluetoothAdapter() == null) {
                LogManager.e("CycledLeScanner", "No Bluetooth adapter.  beaconService cannot scan.", new Object[0]);
            }
            if (!this.mScanningEnabled || !bool.booleanValue()) {
                LogManager.d("CycledLeScanner", "disabling scan", new Object[0]);
                this.mScanning = false;
                this.mScanCyclerStarted = false;
                stopScan();
                this.mCurrentScanStartTime = 0;
                this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                this.mHandler.removeCallbacksAndMessages(null);
                finishScanCycle();
            } else if (!deferScanIfNeeded()) {
                LogManager.d("CycledLeScanner", "starting a new scan cycle", new Object[0]);
                if (this.mScanning && !this.mScanningPaused) {
                    if (!this.mRestartNeeded) {
                        LogManager.d("CycledLeScanner", "We are already scanning and have been for " + (SystemClock.elapsedRealtime() - this.mCurrentScanStartTime) + " millis", new Object[0]);
                        this.mScanCycleStopTime = SystemClock.elapsedRealtime() + this.mScanPeriod;
                        scheduleScanCycleStop();
                        LogManager.d("CycledLeScanner", "Scan started", new Object[0]);
                    }
                }
                this.mScanning = true;
                this.mScanningPaused = false;
                try {
                    if (getBluetoothAdapter() != null) {
                        if (getBluetoothAdapter().isEnabled()) {
                            if (this.mBluetoothCrashResolver != null && this.mBluetoothCrashResolver.isRecoveryInProgress()) {
                                LogManager.w("CycledLeScanner", "Skipping scan because crash recovery is in progress.", new Object[0]);
                            } else if (this.mScanningEnabled) {
                                if (this.mRestartNeeded) {
                                    this.mRestartNeeded = false;
                                    LogManager.d("CycledLeScanner", "restarting a bluetooth le scan", new Object[0]);
                                } else {
                                    LogManager.d("CycledLeScanner", "starting a new bluetooth le scan", new Object[0]);
                                }
                                try {
                                    if (Build.VERSION.SDK_INT < 23 || checkLocationPermission()) {
                                        this.mCurrentScanStartTime = SystemClock.elapsedRealtime();
                                        startScan();
                                    }
                                } catch (Exception e) {
                                    LogManager.e(e, "CycledLeScanner", "Internal Android exception scanning for beacons", new Object[0]);
                                }
                            } else {
                                LogManager.d("CycledLeScanner", "Scanning unnecessary - no monitoring or ranging active.", new Object[0]);
                            }
                            this.mLastScanCycleStartTime = SystemClock.elapsedRealtime();
                        } else {
                            LogManager.d("CycledLeScanner", "Bluetooth is disabled.  Cannot scan for beacons.", new Object[0]);
                        }
                    }
                } catch (Exception e2) {
                    LogManager.e(e2, "CycledLeScanner", "Exception starting Bluetooth scan.  Perhaps Bluetooth is disabled or unavailable?", new Object[0]);
                }
                this.mScanCycleStopTime = SystemClock.elapsedRealtime() + this.mScanPeriod;
                scheduleScanCycleStop();
                LogManager.d("CycledLeScanner", "Scan started", new Object[0]);
            }
        } catch (SecurityException unused) {
            LogManager.w("CycledLeScanner", "SecurityException working accessing bluetooth.", new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void scheduleScanCycleStop() {
        long elapsedRealtime = this.mScanCycleStopTime - SystemClock.elapsedRealtime();
        if (!this.mScanningEnabled || elapsedRealtime <= 0) {
            finishScanCycle();
            return;
        }
        LogManager.d("CycledLeScanner", "Waiting to stop scan cycle for another %s milliseconds", Long.valueOf(elapsedRealtime));
        if (this.mBackgroundFlag) {
            setWakeUpAlarm();
        }
        Handler handler = this.mHandler;
        AnonymousClass2 r3 = new Runnable() {
            /* class org.altbeacon.beacon.service.scanner.CycledLeScanner.AnonymousClass2 */

            public void run() {
                CycledLeScanner.this.scheduleScanCycleStop();
            }
        };
        if (elapsedRealtime > 1000) {
            elapsedRealtime = 1000;
        }
        handler.postDelayed(r3, elapsedRealtime);
    }

    private void finishScanCycle() {
        LogManager.d("CycledLeScanner", "Done with scan cycle", new Object[0]);
        try {
            this.mCycledLeScanCallback.onCycleEnd();
            if (this.mScanning) {
                if (getBluetoothAdapter() != null) {
                    if (getBluetoothAdapter().isEnabled()) {
                        if (this.mDistinctPacketsDetectedPerScan && this.mBetweenScanPeriod == 0) {
                            if (!mustStopScanToPreventAndroidNScanTimeout()) {
                                LogManager.d("CycledLeScanner", "Not stopping scanning.  Device capable of multiple indistinct detections per scan.", new Object[0]);
                                this.mScanningLeftOn = true;
                                this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                            }
                        }
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        if (Build.VERSION.SDK_INT < 24 || this.mBetweenScanPeriod + this.mScanPeriod >= 6000 || elapsedRealtime - this.mLastScanCycleStartTime >= 6000) {
                            try {
                                LogManager.d("CycledLeScanner", "stopping bluetooth le scan", new Object[0]);
                                finishScan();
                                this.mScanningLeftOn = false;
                            } catch (Exception e) {
                                LogManager.w(e, "CycledLeScanner", "Internal Android exception scanning for beacons", new Object[0]);
                            }
                            this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                        } else {
                            LogManager.d("CycledLeScanner", "Not stopping scan because this is Android N and we keep scanning for a minimum of 6 seconds at a time. We will stop in " + (6000 - (elapsedRealtime - this.mLastScanCycleStartTime)) + " millisconds.", new Object[0]);
                            this.mScanningLeftOn = true;
                            this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                        }
                    } else {
                        LogManager.d("CycledLeScanner", "Bluetooth is disabled.  Cannot scan for beacons.", new Object[0]);
                        this.mRestartNeeded = true;
                    }
                }
                this.mNextScanCycleStartTime = getNextScanStartTime();
                if (this.mScanningEnabled) {
                    scanLeDevice(true);
                }
            }
            if (!this.mScanningEnabled) {
                LogManager.d("CycledLeScanner", "Scanning disabled. ", new Object[0]);
                this.mScanCyclerStarted = false;
                cancelWakeUpAlarm();
            }
        } catch (SecurityException unused) {
            LogManager.w("CycledLeScanner", "SecurityException working accessing bluetooth.", new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public BluetoothAdapter getBluetoothAdapter() {
        try {
            if (this.mBluetoothAdapter == null) {
                BluetoothAdapter adapter = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService("bluetooth")).getAdapter();
                this.mBluetoothAdapter = adapter;
                if (adapter == null) {
                    LogManager.w("CycledLeScanner", "Failed to construct a BluetoothAdapter", new Object[0]);
                }
            }
        } catch (SecurityException unused) {
            LogManager.e("CycledLeScanner", "Cannot consruct bluetooth adapter.  Security Exception", new Object[0]);
        }
        return this.mBluetoothAdapter;
    }

    /* access modifiers changed from: protected */
    public void setWakeUpAlarm() {
        long j = this.mBetweenScanPeriod;
        if (300000 >= j) {
            j = 300000;
        }
        long j2 = this.mScanPeriod;
        if (j < j2) {
            j = j2;
        }
        ((AlarmManager) this.mContext.getSystemService("alarm")).set(2, SystemClock.elapsedRealtime() + j, getWakeUpOperation());
        LogManager.d("CycledLeScanner", "Set a wakeup alarm to go off in %s ms: %s", Long.valueOf(j), getWakeUpOperation());
    }

    /* access modifiers changed from: protected */
    public PendingIntent getWakeUpOperation() {
        if (this.mWakeUpOperation == null) {
            Intent intent = new Intent(this.mContext, StartupBroadcastReceiver.class);
            intent.putExtra("wakeup", true);
            this.mWakeUpOperation = PendingIntent.getBroadcast(this.mContext, 0, intent, 134217728);
        }
        return this.mWakeUpOperation;
    }

    /* access modifiers changed from: protected */
    public void cancelWakeUpAlarm() {
        LogManager.d("CycledLeScanner", "cancel wakeup alarm: %s", this.mWakeUpOperation);
        ((AlarmManager) this.mContext.getSystemService("alarm")).set(2, Long.MAX_VALUE, getWakeUpOperation());
        LogManager.d("CycledLeScanner", "Set a wakeup alarm to go off in %s ms: %s", Long.valueOf(Long.MAX_VALUE - SystemClock.elapsedRealtime()), getWakeUpOperation());
    }

    private long getNextScanStartTime() {
        long j = this.mBetweenScanPeriod;
        if (j == 0) {
            return SystemClock.elapsedRealtime();
        }
        long elapsedRealtime = j - (SystemClock.elapsedRealtime() % (this.mScanPeriod + j));
        LogManager.d("CycledLeScanner", "Normalizing between scan period from %s to %s", Long.valueOf(this.mBetweenScanPeriod), Long.valueOf(elapsedRealtime));
        return SystemClock.elapsedRealtime() + elapsedRealtime;
    }

    private boolean checkLocationPermission() {
        return checkPermission("android.permission.ACCESS_COARSE_LOCATION") || checkPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    private boolean checkPermission(String str) {
        return this.mContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0027  */
    private boolean mustStopScanToPreventAndroidNScanTimeout() {
        boolean z;
        long elapsedRealtime = SystemClock.elapsedRealtime() + this.mBetweenScanPeriod + this.mScanPeriod;
        if (Build.VERSION.SDK_INT >= 24) {
            long j = this.mCurrentScanStartTime;
            if (j > 0 && elapsedRealtime - j > 1800000) {
                z = true;
                if (z) {
                    LogManager.d("CycledLeScanner", "The next scan cycle would go over the Android N max duration.", new Object[0]);
                    if (this.mLongScanForcingEnabled) {
                        LogManager.d("CycledLeScanner", "Stopping scan to prevent Android N scan timeout.", new Object[0]);
                        return true;
                    }
                    LogManager.w("CycledLeScanner", "Allowing a long running scan to be stopped by the OS.  To prevent this, set longScanForcingEnabled in the AndroidBeaconLibrary.", new Object[0]);
                }
                return false;
            }
        }
        z = false;
        if (z) {
        }
        return false;
    }
}
