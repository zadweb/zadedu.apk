package org.altbeacon.beacon.service;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;

public class ScanState implements Serializable {
    public static int MIN_SCAN_JOB_INTERVAL_MILLIS = 300000;
    private static final String TAG = ScanState.class.getSimpleName();
    private long mBackgroundBetweenScanPeriod;
    private boolean mBackgroundMode;
    private long mBackgroundScanPeriod;
    private Set<BeaconParser> mBeaconParsers = new HashSet();
    private transient Context mContext;
    private ExtraDataBeaconTracker mExtraBeaconDataTracker = new ExtraDataBeaconTracker();
    private long mForegroundBetweenScanPeriod;
    private long mForegroundScanPeriod;
    private long mLastScanStartTimeMillis = 0;
    private transient MonitoringStatus mMonitoringStatus;
    private Map<Region, RangeState> mRangedRegionState = new HashMap();

    public Boolean getBackgroundMode() {
        return Boolean.valueOf(this.mBackgroundMode);
    }

    public Long getBackgroundBetweenScanPeriod() {
        return Long.valueOf(this.mBackgroundBetweenScanPeriod);
    }

    public Long getBackgroundScanPeriod() {
        return Long.valueOf(this.mBackgroundScanPeriod);
    }

    public Long getForegroundBetweenScanPeriod() {
        return Long.valueOf(this.mForegroundBetweenScanPeriod);
    }

    public Long getForegroundScanPeriod() {
        return Long.valueOf(this.mForegroundScanPeriod);
    }

    public ScanState(Context context) {
        this.mContext = context;
    }

    public MonitoringStatus getMonitoringStatus() {
        return this.mMonitoringStatus;
    }

    public Map<Region, RangeState> getRangedRegionState() {
        return this.mRangedRegionState;
    }

    public ExtraDataBeaconTracker getExtraBeaconDataTracker() {
        return this.mExtraBeaconDataTracker;
    }

    public Set<BeaconParser> getBeaconParsers() {
        return this.mBeaconParsers;
    }

    public void setLastScanStartTimeMillis(long j) {
        this.mLastScanStartTimeMillis = j;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:2|(5:3|4|5|6|(5:7|8|9|10|(2:12|13)))|14|15|(2:56|57)|58|(1:60)|61|62) */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0078, code lost:
        if (r4 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0090, code lost:
        if (r4 != null) goto L_0x001d;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x001d */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0057 A[Catch:{ all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0061 A[Catch:{ all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0073 A[SYNTHETIC, Splitter:B:40:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x008b A[SYNTHETIC, Splitter:B:51:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e8 A[SYNTHETIC, Splitter:B:66:0x00e8] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f1 A[SYNTHETIC, Splitter:B:71:0x00f1] */
    public static ScanState restore(Context context) {
        Throwable th;
        ObjectInputStream objectInputStream;
        ScanState scanState;
        FileInputStream fileInputStream;
        Throwable e;
        synchronized (ScanState.class) {
            FileInputStream fileInputStream2 = null;
            try {
                fileInputStream = context.openFileInput("android-beacon-library-scan-state");
                try {
                    objectInputStream = new ObjectInputStream(fileInputStream);
                } catch (FileNotFoundException unused) {
                    objectInputStream = null;
                    scanState = null;
                    fileInputStream2 = fileInputStream;
                    try {
                        LogManager.w(TAG, "Serialized ScanState does not exist.  This may be normal on first run.", new Object[0]);
                        if (fileInputStream2 != null) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream2 != null) {
                        }
                        if (objectInputStream != null) {
                        }
                        throw th;
                    }
                } catch (IOException | ClassCastException | ClassNotFoundException e2) {
                    scanState = null;
                    e = e2;
                    objectInputStream = null;
                    try {
                        if (!(e instanceof InvalidClassException)) {
                        }
                        if (fileInputStream != null) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                        }
                        if (objectInputStream != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    objectInputStream = null;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                    }
                    if (objectInputStream != null) {
                    }
                    throw th;
                }
                try {
                    scanState = (ScanState) objectInputStream.readObject();
                    try {
                        scanState.mContext = context;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    } catch (FileNotFoundException unused2) {
                        fileInputStream2 = fileInputStream;
                        LogManager.w(TAG, "Serialized ScanState does not exist.  This may be normal on first run.", new Object[0]);
                        if (fileInputStream2 != null) {
                        }
                    } catch (IOException | ClassCastException | ClassNotFoundException e3) {
                        e = e3;
                        if (!(e instanceof InvalidClassException)) {
                        }
                        if (fileInputStream != null) {
                        }
                    }
                } catch (FileNotFoundException unused3) {
                    scanState = null;
                    fileInputStream2 = fileInputStream;
                    LogManager.w(TAG, "Serialized ScanState does not exist.  This may be normal on first run.", new Object[0]);
                    if (fileInputStream2 != null) {
                    }
                } catch (IOException | ClassCastException | ClassNotFoundException e4) {
                    scanState = null;
                    e = e4;
                    if (!(e instanceof InvalidClassException)) {
                    }
                    if (fileInputStream != null) {
                    }
                }
            } catch (FileNotFoundException unused4) {
                objectInputStream = null;
                scanState = null;
                LogManager.w(TAG, "Serialized ScanState does not exist.  This may be normal on first run.", new Object[0]);
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException unused5) {
                    }
                }
            } catch (IOException | ClassCastException | ClassNotFoundException e5) {
                objectInputStream = null;
                scanState = null;
                e = e5;
                fileInputStream = null;
                if (!(e instanceof InvalidClassException)) {
                    LogManager.d(TAG, "Serialized ScanState has wrong class. Just ignoring saved state...", new Object[0]);
                } else {
                    LogManager.e(TAG, "Deserialization exception", new Object[0]);
                    Log.e(TAG, "error: ", e);
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused6) {
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                objectInputStream = null;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException unused7) {
                    }
                }
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException unused8) {
                    }
                }
                throw th;
            }
            objectInputStream.close();
            if (scanState == null) {
                scanState = new ScanState(context);
            }
            if (scanState.mExtraBeaconDataTracker == null) {
                scanState.mExtraBeaconDataTracker = new ExtraDataBeaconTracker();
            }
            scanState.mMonitoringStatus = MonitoringStatus.getInstanceForApplication(context);
            LogManager.d(TAG, "Scan state restore regions: monitored=" + scanState.getMonitoringStatus().regions().size() + " ranged=" + scanState.getRangedRegionState().keySet().size(), new Object[0]);
        }
        return scanState;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:2|(5:3|4|5|6|(6:7|8|(1:10)|11|(1:13)|(2:15|16)))|17|18|36|37|38) */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b8, code lost:
        if (r4 == null) goto L_0x00bb;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x008a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x00bb */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b3 A[SYNTHETIC, Splitter:B:32:0x00b3] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c5 A[SYNTHETIC, Splitter:B:42:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ce A[SYNTHETIC, Splitter:B:47:0x00ce] */
    public void save() {
        ObjectOutputStream objectOutputStream;
        FileOutputStream fileOutputStream;
        Throwable th;
        IOException e;
        Throwable th2;
        synchronized (ScanState.class) {
            try {
                fileOutputStream = this.mContext.openFileOutput("android-beacon-library-scan-state-temp", 0);
                try {
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                } catch (IOException e2) {
                    objectOutputStream = null;
                    e = e2;
                    try {
                        LogManager.e(TAG, "Error while saving scan status to file: ", e.getMessage());
                        if (fileOutputStream != null) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    objectOutputStream = null;
                    th = th2;
                    if (fileOutputStream != null) {
                    }
                    if (objectOutputStream != null) {
                    }
                    throw th;
                }
                try {
                    objectOutputStream.writeObject(this);
                    File file = new File(this.mContext.getFilesDir(), "android-beacon-library-scan-state");
                    File file2 = new File(this.mContext.getFilesDir(), "android-beacon-library-scan-state-temp");
                    LogManager.d(TAG, "Temp file is " + file2.getAbsolutePath(), new Object[0]);
                    LogManager.d(TAG, "Perm file is " + file.getAbsolutePath(), new Object[0]);
                    if (!file.delete()) {
                        LogManager.e(TAG, "Error while saving scan status to file: Cannot delete existing file.", new Object[0]);
                    }
                    if (!file2.renameTo(file)) {
                        LogManager.e(TAG, "Error while saving scan status to file: Cannot rename temp file.", new Object[0]);
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e3) {
                    e = e3;
                    LogManager.e(TAG, "Error while saving scan status to file: ", e.getMessage());
                    if (fileOutputStream != null) {
                    }
                }
            } catch (IOException e4) {
                objectOutputStream = null;
                e = e4;
                fileOutputStream = null;
                LogManager.e(TAG, "Error while saving scan status to file: ", e.getMessage());
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                    }
                }
            } catch (Throwable th5) {
                th2 = th5;
                fileOutputStream = null;
                objectOutputStream = null;
                th = th2;
                if (fileOutputStream != null) {
                }
                if (objectOutputStream != null) {
                }
                throw th;
            }
            objectOutputStream.close();
            this.mMonitoringStatus.saveMonitoringStatusIfOn();
        }
    }

    public int getScanJobIntervalMillis() {
        long j;
        long j2;
        if (getBackgroundMode().booleanValue()) {
            j2 = getBackgroundScanPeriod().longValue();
            j = getBackgroundBetweenScanPeriod().longValue();
        } else {
            j2 = getForegroundScanPeriod().longValue();
            j = getForegroundBetweenScanPeriod().longValue();
        }
        long j3 = j2 + j;
        int i = MIN_SCAN_JOB_INTERVAL_MILLIS;
        return j3 > ((long) i) ? (int) j3 : i;
    }

    public int getScanJobRuntimeMillis() {
        long j;
        String str = TAG;
        LogManager.d(str, "ScanState says background mode for ScanJob is " + getBackgroundMode(), new Object[0]);
        if (getBackgroundMode().booleanValue()) {
            j = getBackgroundScanPeriod().longValue();
        } else {
            j = getForegroundScanPeriod().longValue();
        }
        if (!getBackgroundMode().booleanValue()) {
            int i = MIN_SCAN_JOB_INTERVAL_MILLIS;
            if (j < ((long) i)) {
                return i;
            }
        }
        return (int) j;
    }

    public void applyChanges(BeaconManager beaconManager) {
        this.mBeaconParsers = new HashSet(beaconManager.getBeaconParsers());
        this.mForegroundScanPeriod = beaconManager.getForegroundScanPeriod();
        this.mForegroundBetweenScanPeriod = beaconManager.getForegroundBetweenScanPeriod();
        this.mBackgroundScanPeriod = beaconManager.getBackgroundScanPeriod();
        this.mBackgroundBetweenScanPeriod = beaconManager.getBackgroundBetweenScanPeriod();
        this.mBackgroundMode = beaconManager.getBackgroundMode();
        ArrayList arrayList = new ArrayList(this.mMonitoringStatus.regions());
        ArrayList arrayList2 = new ArrayList(this.mRangedRegionState.keySet());
        ArrayList arrayList3 = new ArrayList(beaconManager.getMonitoredRegions());
        ArrayList arrayList4 = new ArrayList(beaconManager.getRangedRegions());
        String str = TAG;
        LogManager.d(str, "ranged regions: old=" + arrayList2.size() + " new=" + arrayList4.size(), new Object[0]);
        String str2 = TAG;
        LogManager.d(str2, "monitored regions: old=" + arrayList.size() + " new=" + arrayList3.size(), new Object[0]);
        Iterator it = arrayList4.iterator();
        while (it.hasNext()) {
            Region region = (Region) it.next();
            if (!arrayList2.contains(region)) {
                String str3 = TAG;
                LogManager.d(str3, "Starting ranging region: " + region, new Object[0]);
                this.mRangedRegionState.put(region, new RangeState(new Callback(this.mContext.getPackageName())));
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            Region region2 = (Region) it2.next();
            if (!arrayList4.contains(region2)) {
                String str4 = TAG;
                LogManager.d(str4, "Stopping ranging region: " + region2, new Object[0]);
                this.mRangedRegionState.remove(region2);
            }
        }
        String str5 = TAG;
        LogManager.d(str5, "Updated state with " + arrayList4.size() + " ranging regions and " + arrayList3.size() + " monitoring regions.", new Object[0]);
        save();
    }
}
