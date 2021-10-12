package org.altbeacon.bluetooth;

import android.app.NotificationManager;
import android.app.TaskStackBuilder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.List;
import org.altbeacon.beacon.logging.LogManager;

public class BluetoothMedic {
    private static final String TAG = BluetoothMedic.class.getSimpleName();
    private static BluetoothMedic sInstance;
    private BluetoothAdapter mAdapter;
    private BroadcastReceiver mBluetoothEventReceiver = new BroadcastReceiver() {
        /* class org.altbeacon.bluetooth.BluetoothMedic.AnonymousClass1 */

        public void onReceive(Context context, Intent intent) {
            LogManager.d(BluetoothMedic.TAG, "Broadcast notification received.", new Object[0]);
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            if (action.equalsIgnoreCase("onScanFailed")) {
                if (intent.getIntExtra("errorCode", -1) == 2) {
                    BluetoothMedic.this.sendNotification(context, "scan failed", "Power cycling bluetooth");
                    LogManager.d(BluetoothMedic.TAG, "Detected a SCAN_FAILED_APPLICATION_REGISTRATION_FAILED.  We need to cycle bluetooth to recover", new Object[0]);
                    if (!BluetoothMedic.this.cycleBluetoothIfNotTooSoon()) {
                        BluetoothMedic.this.sendNotification(context, "scan failed", "Cannot power cycle bluetooth again");
                    }
                }
            } else if (!action.equalsIgnoreCase("onStartFailed")) {
                LogManager.d(BluetoothMedic.TAG, "Unknown event.", new Object[0]);
            } else if (intent.getIntExtra("errorCode", -1) == 4) {
                BluetoothMedic.this.sendNotification(context, "advertising failed", "Expected failure.  Power cycling.");
                if (!BluetoothMedic.this.cycleBluetoothIfNotTooSoon()) {
                    BluetoothMedic.this.sendNotification(context, "advertising failed", "Cannot power cycle bluetooth again");
                }
            }
        }
    };
    private Handler mHandler = new Handler();
    private long mLastBluetoothPowerCycleTime = 0;
    private LocalBroadcastManager mLocalBroadcastManager;
    private int mNotificationIcon = 0;
    private boolean mNotificationsEnabled = false;
    private Boolean mScanTestResult = null;
    private int mTestType = 0;
    private Boolean mTransmitterTestResult = null;

    public static BluetoothMedic getInstance() {
        if (sInstance == null) {
            sInstance = new BluetoothMedic();
        }
        return sInstance;
    }

    private BluetoothMedic() {
    }

    private void initializeWithContext(Context context) {
        if (this.mAdapter == null || this.mLocalBroadcastManager == null) {
            BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
            if (bluetoothManager != null) {
                this.mAdapter = bluetoothManager.getAdapter();
                this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
                return;
            }
            throw new NullPointerException("Cannot get BluetoothManager");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.stopScan(r4);
     */
    public boolean runScanTest(final Context context) {
        initializeWithContext(context);
        this.mScanTestResult = null;
        LogManager.i(TAG, "Starting scan test", new Object[0]);
        long currentTimeMillis = System.currentTimeMillis();
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter != null) {
            final BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
            AnonymousClass2 r4 = new ScanCallback() {
                /* class org.altbeacon.bluetooth.BluetoothMedic.AnonymousClass2 */

                public void onScanResult(int i, ScanResult scanResult) {
                    super.onScanResult(i, scanResult);
                    BluetoothMedic.this.mScanTestResult = true;
                    LogManager.i(BluetoothMedic.TAG, "Scan test succeeded", new Object[0]);
                    try {
                        bluetoothLeScanner.stopScan(this);
                    } catch (IllegalStateException unused) {
                    }
                }

                @Override // android.bluetooth.le.ScanCallback
                public void onBatchScanResults(List<ScanResult> list) {
                    super.onBatchScanResults(list);
                }

                public void onScanFailed(int i) {
                    super.onScanFailed(i);
                    String str = BluetoothMedic.TAG;
                    LogManager.d(str, "Sending onScanFailed broadcast with " + BluetoothMedic.this.mLocalBroadcastManager, new Object[0]);
                    Intent intent = new Intent("onScanFailed");
                    intent.putExtra("errorCode", i);
                    if (BluetoothMedic.this.mLocalBroadcastManager != null) {
                        BluetoothMedic.this.mLocalBroadcastManager.sendBroadcast(intent);
                    }
                    String str2 = BluetoothMedic.TAG;
                    LogManager.d(str2, "broadcast: " + intent + " should be received by " + BluetoothMedic.this.mBluetoothEventReceiver, new Object[0]);
                    if (i == 2) {
                        LogManager.w(BluetoothMedic.TAG, "Scan test failed in a way we consider a failure", new Object[0]);
                        BluetoothMedic.this.sendNotification(context, "scan failed", "bluetooth not ok");
                        BluetoothMedic.this.mScanTestResult = false;
                        return;
                    }
                    LogManager.i(BluetoothMedic.TAG, "Scan test failed in a way we do not consider a failure", new Object[0]);
                    BluetoothMedic.this.mScanTestResult = true;
                }
            };
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.startScan(r4);
                while (true) {
                    if (this.mScanTestResult == null) {
                        LogManager.d(TAG, "Waiting for scan test to complete...", new Object[0]);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException unused) {
                        }
                        if (System.currentTimeMillis() - currentTimeMillis > 5000) {
                            LogManager.d(TAG, "Timeout running scan test", new Object[0]);
                            break;
                        }
                    }
                }
            } else {
                LogManager.d(TAG, "Cannot get scanner", new Object[0]);
            }
        }
        LogManager.d(TAG, "scan test complete", new Object[0]);
        Boolean bool = this.mScanTestResult;
        if (bool == null || bool.booleanValue()) {
            return true;
        }
        return false;
    }

    public boolean runTransmitterTest(final Context context) {
        initializeWithContext(context);
        this.mTransmitterTestResult = null;
        long currentTimeMillis = System.currentTimeMillis();
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter != null) {
            final BluetoothLeAdvertiser bluetoothLeAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
            if (bluetoothLeAdvertiser != null) {
                AdvertiseSettings build = new AdvertiseSettings.Builder().setAdvertiseMode(0).build();
                AdvertiseData build2 = new AdvertiseData.Builder().addManufacturerData(0, new byte[]{0}).build();
                LogManager.i(TAG, "Starting transmitter test", new Object[0]);
                bluetoothLeAdvertiser.startAdvertising(build, build2, new AdvertiseCallback() {
                    /* class org.altbeacon.bluetooth.BluetoothMedic.AnonymousClass3 */

                    public void onStartSuccess(AdvertiseSettings advertiseSettings) {
                        super.onStartSuccess(advertiseSettings);
                        LogManager.i(BluetoothMedic.TAG, "Transmitter test succeeded", new Object[0]);
                        bluetoothLeAdvertiser.stopAdvertising(this);
                        BluetoothMedic.this.mTransmitterTestResult = true;
                    }

                    public void onStartFailure(int i) {
                        super.onStartFailure(i);
                        Intent intent = new Intent("onStartFailed");
                        intent.putExtra("errorCode", i);
                        String str = BluetoothMedic.TAG;
                        LogManager.d(str, "Sending onStartFailure broadcast with " + BluetoothMedic.this.mLocalBroadcastManager, new Object[0]);
                        if (BluetoothMedic.this.mLocalBroadcastManager != null) {
                            BluetoothMedic.this.mLocalBroadcastManager.sendBroadcast(intent);
                        }
                        if (i == 4) {
                            BluetoothMedic.this.mTransmitterTestResult = false;
                            LogManager.w(BluetoothMedic.TAG, "Transmitter test failed in a way we consider a test failure", new Object[0]);
                            BluetoothMedic.this.sendNotification(context, "transmitter failed", "bluetooth not ok");
                            return;
                        }
                        BluetoothMedic.this.mTransmitterTestResult = true;
                        LogManager.i(BluetoothMedic.TAG, "Transmitter test failed, but not in a way we consider a test failure", new Object[0]);
                    }
                });
            } else {
                LogManager.d(TAG, "Cannot get advertiser", new Object[0]);
            }
            while (true) {
                if (this.mTransmitterTestResult != null) {
                    break;
                }
                LogManager.d(TAG, "Waiting for transmitter test to complete...", new Object[0]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException unused) {
                }
                if (System.currentTimeMillis() - currentTimeMillis > 5000) {
                    LogManager.d(TAG, "Timeout running transmitter test", new Object[0]);
                    break;
                }
            }
        }
        LogManager.d(TAG, "transmitter test complete", new Object[0]);
        Boolean bool = this.mTransmitterTestResult;
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean cycleBluetoothIfNotTooSoon() {
        long currentTimeMillis = System.currentTimeMillis() - this.mLastBluetoothPowerCycleTime;
        if (currentTimeMillis < 60000) {
            String str = TAG;
            LogManager.d(str, "Not cycling bluetooth because we just did so " + currentTimeMillis + " milliseconds ago.", new Object[0]);
            return false;
        }
        this.mLastBluetoothPowerCycleTime = System.currentTimeMillis();
        LogManager.d(TAG, "Power cycling bluetooth", new Object[0]);
        cycleBluetooth();
        return true;
    }

    private void cycleBluetooth() {
        LogManager.d(TAG, "Power cycling bluetooth", new Object[0]);
        LogManager.d(TAG, "Turning Bluetooth off.", new Object[0]);
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.disable();
            this.mHandler.postDelayed(new Runnable() {
                /* class org.altbeacon.bluetooth.BluetoothMedic.AnonymousClass4 */

                public void run() {
                    LogManager.d(BluetoothMedic.TAG, "Turning Bluetooth back on.", new Object[0]);
                    if (BluetoothMedic.this.mAdapter != null) {
                        BluetoothMedic.this.mAdapter.enable();
                    }
                }
            }, 1000);
            return;
        }
        LogManager.w(TAG, "Cannot cycle bluetooth.  Manager is null.", new Object[0]);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendNotification(Context context, String str, String str2) {
        initializeWithContext(context);
        if (this.mNotificationsEnabled) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "err");
            NotificationCompat.Builder contentText = builder.setContentTitle("BluetoothMedic: " + str).setSmallIcon(this.mNotificationIcon).setVibrate(new long[]{200, 100, 200}).setContentText(str2);
            TaskStackBuilder create = TaskStackBuilder.create(context);
            create.addNextIntent(new Intent("NoOperation"));
            contentText.setContentIntent(create.getPendingIntent(0, 134217728));
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.notify(1, contentText.build());
            }
        }
    }
}
