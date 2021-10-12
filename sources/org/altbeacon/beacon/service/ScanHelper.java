package org.altbeacon.beacon.service;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.scanner.CycledLeScanCallback;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;
import org.altbeacon.beacon.service.scanner.DistinctPacketDetector;
import org.altbeacon.beacon.service.scanner.NonBeaconLeScanCallback;
import org.altbeacon.beacon.service.scanner.ScanFilterUtils;
import org.altbeacon.beacon.startup.StartupBroadcastReceiver;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

/* access modifiers changed from: package-private */
public class ScanHelper {
    private static final String TAG = ScanHelper.class.getSimpleName();
    private BeaconManager mBeaconManager;
    private Set<BeaconParser> mBeaconParsers = new HashSet();
    private Context mContext;
    private final CycledLeScanCallback mCycledLeScanCallback = new CycledLeScanCallback() {
        /* class org.altbeacon.beacon.service.ScanHelper.AnonymousClass1 */

        @Override // org.altbeacon.beacon.service.scanner.CycledLeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            ScanHelper.this.processScanResult(bluetoothDevice, i, bArr);
        }

        @Override // org.altbeacon.beacon.service.scanner.CycledLeScanCallback
        public void onCycleEnd() {
            ScanHelper.this.mDistinctPacketDetector.clearDetections();
            ScanHelper.this.mMonitoringStatus.updateNewlyOutside();
            ScanHelper.this.processRangeData();
            if (ScanHelper.this.mSimulatedScanData != null) {
                LogManager.w(ScanHelper.TAG, "Simulated scan data is deprecated and will be removed in a future release. Please use the new BeaconSimulator interface instead.", new Object[0]);
                ApplicationInfo applicationInfo = ScanHelper.this.mContext.getApplicationInfo();
                int i = applicationInfo.flags & 2;
                applicationInfo.flags = i;
                if (i != 0) {
                    for (Beacon beacon : ScanHelper.this.mSimulatedScanData) {
                        ScanHelper.this.processBeaconFromScan(beacon);
                    }
                } else {
                    LogManager.w(ScanHelper.TAG, "Simulated scan data provided, but ignored because we are not running in debug mode.  Please remove simulated scan data for production.", new Object[0]);
                }
            }
            if (BeaconManager.getBeaconSimulator() == null) {
                return;
            }
            if (BeaconManager.getBeaconSimulator().getBeacons() != null) {
                ApplicationInfo applicationInfo2 = ScanHelper.this.mContext.getApplicationInfo();
                int i2 = applicationInfo2.flags & 2;
                applicationInfo2.flags = i2;
                if (i2 != 0) {
                    for (Beacon beacon2 : BeaconManager.getBeaconSimulator().getBeacons()) {
                        ScanHelper.this.processBeaconFromScan(beacon2);
                    }
                    return;
                }
                LogManager.w(ScanHelper.TAG, "Beacon simulations provided, but ignored because we are not running in debug mode.  Please remove beacon simulations for production.", new Object[0]);
                return;
            }
            LogManager.w(ScanHelper.TAG, "getBeacons is returning null. No simulated beacons to report.", new Object[0]);
        }
    };
    private CycledLeScanner mCycledScanner;
    private DistinctPacketDetector mDistinctPacketDetector = new DistinctPacketDetector();
    private ExecutorService mExecutor;
    private ExtraDataBeaconTracker mExtraDataBeaconTracker = new ExtraDataBeaconTracker();
    private MonitoringStatus mMonitoringStatus;
    private final Map<Region, RangeState> mRangedRegionState = new HashMap();
    private List<Beacon> mSimulatedScanData = null;

    ScanHelper(Context context) {
        this.mContext = context;
        this.mBeaconManager = BeaconManager.getInstanceForApplication(context);
        this.mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
    }

    /* access modifiers changed from: package-private */
    public CycledLeScanner getCycledScanner() {
        return this.mCycledScanner;
    }

    /* access modifiers changed from: package-private */
    public MonitoringStatus getMonitoringStatus() {
        return this.mMonitoringStatus;
    }

    /* access modifiers changed from: package-private */
    public void setMonitoringStatus(MonitoringStatus monitoringStatus) {
        this.mMonitoringStatus = monitoringStatus;
    }

    /* access modifiers changed from: package-private */
    public Map<Region, RangeState> getRangedRegionState() {
        return this.mRangedRegionState;
    }

    /* access modifiers changed from: package-private */
    public void setRangedRegionState(Map<Region, RangeState> map) {
        synchronized (this.mRangedRegionState) {
            this.mRangedRegionState.clear();
            this.mRangedRegionState.putAll(map);
        }
    }

    /* access modifiers changed from: package-private */
    public void setExtraDataBeaconTracker(ExtraDataBeaconTracker extraDataBeaconTracker) {
        this.mExtraDataBeaconTracker = extraDataBeaconTracker;
    }

    /* access modifiers changed from: package-private */
    public void setBeaconParsers(Set<BeaconParser> set) {
        this.mBeaconParsers = set;
    }

    /* access modifiers changed from: package-private */
    public void setSimulatedScanData(List<Beacon> list) {
        this.mSimulatedScanData = list;
    }

    /* access modifiers changed from: package-private */
    public void createCycledLeScanner(boolean z, BluetoothCrashResolver bluetoothCrashResolver) {
        this.mCycledScanner = CycledLeScanner.createScanner(this.mContext, 1100, 0, z, this.mCycledLeScanCallback, bluetoothCrashResolver);
    }

    /* access modifiers changed from: package-private */
    public void processScanResult(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        try {
            new ScanProcessor(this.mBeaconManager.getNonBeaconLeScanCallback()).executeOnExecutor(this.mExecutor, new ScanData(bluetoothDevice, i, bArr));
        } catch (RejectedExecutionException unused) {
            LogManager.w(TAG, "Ignoring scan result because we cannot keep up.", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void reloadParsers() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.mBeaconManager.getBeaconParsers());
        boolean z = true;
        for (BeaconParser beaconParser : this.mBeaconManager.getBeaconParsers()) {
            if (beaconParser.getExtraDataParsers().size() > 0) {
                z = false;
                hashSet.addAll(beaconParser.getExtraDataParsers());
            }
        }
        this.mBeaconParsers = hashSet;
        this.mExtraDataBeaconTracker = new ExtraDataBeaconTracker(z);
    }

    /* access modifiers changed from: package-private */
    public void startAndroidOBackgroundScan(Set<BeaconParser> set) {
        ScanSettings build = new ScanSettings.Builder().setScanMode(0).build();
        List<ScanFilter> createScanFiltersForBeaconParsers = new ScanFilterUtils().createScanFiltersForBeaconParsers(new ArrayList(set));
        try {
            BluetoothAdapter adapter = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService("bluetooth")).getAdapter();
            if (adapter == null) {
                LogManager.w(TAG, "Failed to construct a BluetoothAdapter", new Object[0]);
            } else if (!adapter.isEnabled()) {
                LogManager.w(TAG, "Failed to start background scan on Android O: BluetoothAdapter is not enabled", new Object[0]);
            } else {
                int startScan = adapter.getBluetoothLeScanner().startScan(createScanFiltersForBeaconParsers, build, getScanCallbackIntent());
                if (startScan != 0) {
                    String str = TAG;
                    LogManager.e(str, "Failed to start background scan on Android O.  Code: " + startScan, new Object[0]);
                    return;
                }
                LogManager.d(TAG, "Started passive beacon scan", new Object[0]);
            }
        } catch (SecurityException unused) {
            LogManager.e(TAG, "SecurityException making Android O background scanner", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void stopAndroidOBackgroundScan() {
        try {
            BluetoothAdapter adapter = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService("bluetooth")).getAdapter();
            if (adapter == null) {
                LogManager.w(TAG, "Failed to construct a BluetoothAdapter", new Object[0]);
            } else if (!adapter.isEnabled()) {
                LogManager.w(TAG, "BluetoothAdapter is not enabled", new Object[0]);
            } else {
                BluetoothLeScanner bluetoothLeScanner = adapter.getBluetoothLeScanner();
                if (bluetoothLeScanner != null) {
                    bluetoothLeScanner.stopScan(getScanCallbackIntent());
                }
            }
        } catch (SecurityException unused) {
            LogManager.e(TAG, "SecurityException stopping Android O background scanner", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public PendingIntent getScanCallbackIntent() {
        Intent intent = new Intent(this.mContext, StartupBroadcastReceiver.class);
        intent.putExtra("o-scan", true);
        return PendingIntent.getBroadcast(this.mContext, 0, intent, 134217728);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void processRangeData() {
        synchronized (this.mRangedRegionState) {
            for (Region region : this.mRangedRegionState.keySet()) {
                RangeState rangeState = this.mRangedRegionState.get(region);
                LogManager.d(TAG, "Calling ranging callback", new Object[0]);
                rangeState.getCallback().call(this.mContext, "rangingData", new RangingData(rangeState.finalizeBeacons(), region).toBundle());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void processBeaconFromScan(Beacon beacon) {
        if (Stats.getInstance().isEnabled()) {
            Stats.getInstance().log(beacon);
        }
        if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.d(TAG, "beacon detected : %s", beacon.toString());
        }
        Beacon track = this.mExtraDataBeaconTracker.track(beacon);
        if (track != null) {
            this.mMonitoringStatus.updateNewlyInsideInRegionsContaining(track);
            LogManager.d(TAG, "looking for ranging region matches for this beacon", new Object[0]);
            synchronized (this.mRangedRegionState) {
                for (Region region : matchingRegions(track, this.mRangedRegionState.keySet())) {
                    LogManager.d(TAG, "matches ranging region: %s", region);
                    RangeState rangeState = this.mRangedRegionState.get(region);
                    if (rangeState != null) {
                        rangeState.addBeacon(track);
                    }
                }
            }
        } else if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.d(TAG, "not processing detections for GATT extra data beacon", new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public class ScanData {
        BluetoothDevice device;
        final int rssi;
        byte[] scanRecord;

        ScanData(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            this.device = bluetoothDevice;
            this.rssi = i;
            this.scanRecord = bArr;
        }
    }

    /* access modifiers changed from: private */
    public class ScanProcessor extends AsyncTask<ScanData, Void, Void> {
        final DetectionTracker mDetectionTracker = DetectionTracker.getInstance();
        private final NonBeaconLeScanCallback mNonBeaconLeScanCallback;

        /* access modifiers changed from: protected */
        public void onPostExecute(Void r1) {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(Void... voidArr) {
        }

        ScanProcessor(NonBeaconLeScanCallback nonBeaconLeScanCallback) {
            this.mNonBeaconLeScanCallback = nonBeaconLeScanCallback;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:3:0x0015  */
        public Void doInBackground(ScanData... scanDataArr) {
            ScanData scanData = scanDataArr[0];
            Iterator it = ScanHelper.this.mBeaconParsers.iterator();
            Beacon beacon = null;
            while (it.hasNext() && (beacon = ((BeaconParser) it.next()).fromScanData(scanData.scanRecord, scanData.rssi, scanData.device)) == null) {
                while (it.hasNext()) {
                    while (it.hasNext()) {
                    }
                }
            }
            if (beacon != null) {
                if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.d(ScanHelper.TAG, "Beacon packet detected for: " + beacon + " with rssi " + beacon.getRssi(), new Object[0]);
                }
                this.mDetectionTracker.recordDetection();
                if (ScanHelper.this.mCycledScanner != null && !ScanHelper.this.mCycledScanner.getDistinctPacketsDetectedPerScan() && !ScanHelper.this.mDistinctPacketDetector.isPacketDistinct(scanData.device.getAddress(), scanData.scanRecord)) {
                    LogManager.i(ScanHelper.TAG, "Non-distinct packets detected in a single scan.  Restarting scans unecessary.", new Object[0]);
                    ScanHelper.this.mCycledScanner.setDistinctPacketsDetectedPerScan(true);
                }
                ScanHelper.this.processBeaconFromScan(beacon);
            } else {
                NonBeaconLeScanCallback nonBeaconLeScanCallback = this.mNonBeaconLeScanCallback;
                if (nonBeaconLeScanCallback != null) {
                    nonBeaconLeScanCallback.onNonBeaconLeScan(scanData.device, scanData.rssi, scanData.scanRecord);
                }
            }
            return null;
        }
    }

    private List<Region> matchingRegions(Beacon beacon, Collection<Region> collection) {
        ArrayList arrayList = new ArrayList();
        for (Region region : collection) {
            if (region.matchesBeacon(beacon)) {
                arrayList.add(region);
            } else {
                LogManager.d(TAG, "This region (%s) does not match beacon: %s", region, beacon);
            }
        }
        return arrayList;
    }
}
