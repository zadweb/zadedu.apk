package org.altbeacon.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.SystemClock;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import org.altbeacon.beacon.logging.LogManager;

public class BluetoothCrashResolver {
    private Context context = null;
    private int detectedCrashCount = 0;
    private boolean discoveryStartConfirmed = false;
    private final Set<String> distinctBluetoothAddresses = new HashSet();
    private long lastBluetoothCrashDetectionTime = 0;
    private long lastBluetoothOffTime = 0;
    private long lastBluetoothTurningOnTime = 0;
    private boolean lastRecoverySucceeded = false;
    private long lastStateSaveTime = 0;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        /* class org.altbeacon.bluetooth.BluetoothCrashResolver.AnonymousClass1 */

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                if (BluetoothCrashResolver.this.recoveryInProgress) {
                    LogManager.d("BluetoothCrashResolver", "Bluetooth discovery finished", new Object[0]);
                    BluetoothCrashResolver.this.finishRecovery();
                } else {
                    LogManager.d("BluetoothCrashResolver", "Bluetooth discovery finished (external)", new Object[0]);
                }
            }
            if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                if (BluetoothCrashResolver.this.recoveryInProgress) {
                    BluetoothCrashResolver.this.discoveryStartConfirmed = true;
                    LogManager.d("BluetoothCrashResolver", "Bluetooth discovery started", new Object[0]);
                } else {
                    LogManager.d("BluetoothCrashResolver", "Bluetooth discovery started (external)", new Object[0]);
                }
            }
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", RecyclerView.UNDEFINED_DURATION);
                if (intExtra != Integer.MIN_VALUE) {
                    switch (intExtra) {
                        case 10:
                            LogManager.d("BluetoothCrashResolver", "Bluetooth state is OFF", new Object[0]);
                            BluetoothCrashResolver.this.lastBluetoothOffTime = SystemClock.elapsedRealtime();
                            return;
                        case 11:
                            BluetoothCrashResolver.this.lastBluetoothTurningOnTime = SystemClock.elapsedRealtime();
                            LogManager.d("BluetoothCrashResolver", "Bluetooth state is TURNING_ON", new Object[0]);
                            return;
                        case 12:
                            LogManager.d("BluetoothCrashResolver", "Bluetooth state is ON", new Object[0]);
                            LogManager.d("BluetoothCrashResolver", "Bluetooth was turned off for %s milliseconds", Long.valueOf(BluetoothCrashResolver.this.lastBluetoothTurningOnTime - BluetoothCrashResolver.this.lastBluetoothOffTime));
                            if (BluetoothCrashResolver.this.lastBluetoothTurningOnTime - BluetoothCrashResolver.this.lastBluetoothOffTime < 600) {
                                BluetoothCrashResolver.this.crashDetected();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                } else {
                    LogManager.d("BluetoothCrashResolver", "Bluetooth state is ERROR", new Object[0]);
                }
            }
        }
    };
    private int recoveryAttemptCount = 0;
    private boolean recoveryInProgress = false;
    private UpdateNotifier updateNotifier;

    public interface UpdateNotifier {
        void dataUpdated();
    }

    private int getCrashRiskDeviceCount() {
        return 1590;
    }

    public BluetoothCrashResolver(Context context2) {
        this.context = context2.getApplicationContext();
        LogManager.d("BluetoothCrashResolver", "constructed", new Object[0]);
        loadState();
    }

    public void start() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        this.context.registerReceiver(this.receiver, intentFilter);
        LogManager.d("BluetoothCrashResolver", "started listening for BluetoothAdapter events", new Object[0]);
    }

    public void stop() {
        this.context.unregisterReceiver(this.receiver);
        LogManager.d("BluetoothCrashResolver", "stopped listening for BluetoothAdapter events", new Object[0]);
        saveState();
    }

    public void notifyScannedDevice(BluetoothDevice bluetoothDevice, BluetoothAdapter.LeScanCallback leScanCallback) {
        int size = this.distinctBluetoothAddresses.size();
        synchronized (this.distinctBluetoothAddresses) {
            this.distinctBluetoothAddresses.add(bluetoothDevice.getAddress());
        }
        int size2 = this.distinctBluetoothAddresses.size();
        if (size != size2 && size2 % 100 == 0) {
            LogManager.d("BluetoothCrashResolver", "Distinct Bluetooth devices seen: %s", Integer.valueOf(this.distinctBluetoothAddresses.size()));
        }
        if (this.distinctBluetoothAddresses.size() > getCrashRiskDeviceCount() && !this.recoveryInProgress) {
            LogManager.w("BluetoothCrashResolver", "Large number of Bluetooth devices detected: %s Proactively attempting to clear out address list to prevent a crash", Integer.valueOf(this.distinctBluetoothAddresses.size()));
            LogManager.w("BluetoothCrashResolver", "Stopping LE Scan", new Object[0]);
            BluetoothAdapter.getDefaultAdapter().stopLeScan(leScanCallback);
            startRecovery();
            processStateChange();
        }
    }

    public void crashDetected() {
        if (Build.VERSION.SDK_INT < 18) {
            LogManager.d("BluetoothCrashResolver", "Ignoring crashes before API 18, because BLE is unsupported.", new Object[0]);
            return;
        }
        LogManager.w("BluetoothCrashResolver", "BluetoothService crash detected", new Object[0]);
        if (this.distinctBluetoothAddresses.size() > 0) {
            LogManager.d("BluetoothCrashResolver", "Distinct Bluetooth devices seen at crash: %s", Integer.valueOf(this.distinctBluetoothAddresses.size()));
        }
        this.lastBluetoothCrashDetectionTime = SystemClock.elapsedRealtime();
        this.detectedCrashCount++;
        if (this.recoveryInProgress) {
            LogManager.d("BluetoothCrashResolver", "Ignoring Bluetooth crash because recovery is already in progress.", new Object[0]);
        } else {
            startRecovery();
        }
        processStateChange();
    }

    public boolean isRecoveryInProgress() {
        return this.recoveryInProgress;
    }

    private void processStateChange() {
        UpdateNotifier updateNotifier2 = this.updateNotifier;
        if (updateNotifier2 != null) {
            updateNotifier2.dataUpdated();
        }
        if (SystemClock.elapsedRealtime() - this.lastStateSaveTime > 60000) {
            saveState();
        }
    }

    private void startRecovery() {
        this.recoveryAttemptCount++;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        LogManager.d("BluetoothCrashResolver", "about to check if discovery is active", new Object[0]);
        if (!defaultAdapter.isDiscovering()) {
            LogManager.w("BluetoothCrashResolver", "Recovery attempt started", new Object[0]);
            this.recoveryInProgress = true;
            this.discoveryStartConfirmed = false;
            LogManager.d("BluetoothCrashResolver", "about to command discovery", new Object[0]);
            if (!defaultAdapter.startDiscovery()) {
                LogManager.w("BluetoothCrashResolver", "Can't start discovery.  Is Bluetooth turned on?", new Object[0]);
            }
            LogManager.d("BluetoothCrashResolver", "startDiscovery commanded.  isDiscovering()=%s", Boolean.valueOf(defaultAdapter.isDiscovering()));
            LogManager.d("BluetoothCrashResolver", "We will be cancelling this discovery in %s milliseconds.", 5000);
            cancelDiscovery();
            return;
        }
        LogManager.w("BluetoothCrashResolver", "Already discovering.  Recovery attempt abandoned.", new Object[0]);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void finishRecovery() {
        LogManager.w("BluetoothCrashResolver", "Recovery attempt finished", new Object[0]);
        synchronized (this.distinctBluetoothAddresses) {
            this.distinctBluetoothAddresses.clear();
        }
        this.recoveryInProgress = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00bb A[SYNTHETIC, Splitter:B:36:0x00bb] */
    private void saveState() {
        Throwable th;
        this.lastStateSaveTime = SystemClock.elapsedRealtime();
        OutputStreamWriter outputStreamWriter = null;
        try {
            OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(this.context.openFileOutput("BluetoothCrashResolverState.txt", 0));
            try {
                outputStreamWriter2.write(this.lastBluetoothCrashDetectionTime + "\n");
                outputStreamWriter2.write(this.detectedCrashCount + "\n");
                outputStreamWriter2.write(this.recoveryAttemptCount + "\n");
                outputStreamWriter2.write(this.lastRecoverySucceeded ? "1\n" : "0\n");
                synchronized (this.distinctBluetoothAddresses) {
                    for (String str : this.distinctBluetoothAddresses) {
                        outputStreamWriter2.write(str);
                        outputStreamWriter2.write("\n");
                    }
                }
                try {
                    outputStreamWriter2.close();
                } catch (IOException unused) {
                }
            } catch (IOException unused2) {
                outputStreamWriter = outputStreamWriter2;
                try {
                    LogManager.w("BluetoothCrashResolver", "Can't write macs to %s", "BluetoothCrashResolverState.txt");
                    if (outputStreamWriter != null) {
                    }
                    LogManager.d("BluetoothCrashResolver", "Wrote %s Bluetooth addresses", Integer.valueOf(this.distinctBluetoothAddresses.size()));
                } catch (Throwable th2) {
                    th = th2;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                outputStreamWriter = outputStreamWriter2;
                if (outputStreamWriter != null) {
                }
                throw th;
            }
        } catch (IOException unused4) {
            LogManager.w("BluetoothCrashResolver", "Can't write macs to %s", "BluetoothCrashResolverState.txt");
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            LogManager.d("BluetoothCrashResolver", "Wrote %s Bluetooth addresses", Integer.valueOf(this.distinctBluetoothAddresses.size()));
        }
        LogManager.d("BluetoothCrashResolver", "Wrote %s Bluetooth addresses", Integer.valueOf(this.distinctBluetoothAddresses.size()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006f, code lost:
        if (r4 != null) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0071, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007e, code lost:
        if (r4 != null) goto L_0x0071;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0097 A[SYNTHETIC, Splitter:B:43:0x0097] */
    private void loadState() {
        Throwable th;
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(this.context.openFileInput("BluetoothCrashResolverState.txt")));
            try {
                String readLine = bufferedReader2.readLine();
                if (readLine != null) {
                    this.lastBluetoothCrashDetectionTime = Long.parseLong(readLine);
                }
                String readLine2 = bufferedReader2.readLine();
                if (readLine2 != null) {
                    this.detectedCrashCount = Integer.parseInt(readLine2);
                }
                String readLine3 = bufferedReader2.readLine();
                if (readLine3 != null) {
                    this.recoveryAttemptCount = Integer.parseInt(readLine3);
                }
                String readLine4 = bufferedReader2.readLine();
                if (readLine4 != null) {
                    this.lastRecoverySucceeded = false;
                    if (readLine4.equals("1")) {
                        this.lastRecoverySucceeded = true;
                    }
                }
                while (true) {
                    String readLine5 = bufferedReader2.readLine();
                    if (readLine5 != null) {
                        this.distinctBluetoothAddresses.add(readLine5);
                    } else {
                        try {
                            break;
                        } catch (IOException unused) {
                        }
                    }
                }
                bufferedReader2.close();
            } catch (IOException unused2) {
                bufferedReader = bufferedReader2;
                LogManager.w("BluetoothCrashResolver", "Can't read macs from %s", "BluetoothCrashResolverState.txt");
            } catch (NumberFormatException unused3) {
                bufferedReader = bufferedReader2;
                try {
                    LogManager.w("BluetoothCrashResolver", "Can't parse file %s", "BluetoothCrashResolverState.txt");
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                }
                throw th;
            }
        } catch (IOException unused5) {
            LogManager.w("BluetoothCrashResolver", "Can't read macs from %s", "BluetoothCrashResolverState.txt");
        } catch (NumberFormatException unused6) {
            LogManager.w("BluetoothCrashResolver", "Can't parse file %s", "BluetoothCrashResolverState.txt");
        }
        LogManager.d("BluetoothCrashResolver", "Read %s Bluetooth addresses", Integer.valueOf(this.distinctBluetoothAddresses.size()));
    }

    private void cancelDiscovery() {
        try {
            Thread.sleep(5000);
            if (!this.discoveryStartConfirmed) {
                LogManager.w("BluetoothCrashResolver", "BluetoothAdapter.ACTION_DISCOVERY_STARTED never received.  Recovery may fail.", new Object[0]);
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter.isDiscovering()) {
                LogManager.d("BluetoothCrashResolver", "Cancelling discovery", new Object[0]);
                defaultAdapter.cancelDiscovery();
                return;
            }
            LogManager.d("BluetoothCrashResolver", "Discovery not running.  Won't cancel it", new Object[0]);
        } catch (InterruptedException unused) {
            LogManager.d("BluetoothCrashResolver", "DiscoveryCanceller sleep interrupted.", new Object[0]);
        }
    }
}
