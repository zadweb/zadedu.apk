package org.altbeacon.beacon.service;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;

public class MonitoringStatus {
    private static final Object SINGLETON_LOCK = new Object();
    private static final String TAG = MonitoringStatus.class.getSimpleName();
    private static volatile MonitoringStatus sInstance;
    private Context mContext;
    private Map<Region, RegionMonitoringState> mRegionsStatesMap;
    private boolean mStatePreservationIsOn = true;

    public static MonitoringStatus getInstanceForApplication(Context context) {
        MonitoringStatus monitoringStatus = sInstance;
        if (monitoringStatus == null) {
            synchronized (SINGLETON_LOCK) {
                monitoringStatus = sInstance;
                if (monitoringStatus == null) {
                    monitoringStatus = new MonitoringStatus(context.getApplicationContext());
                    sInstance = monitoringStatus;
                }
            }
        }
        return monitoringStatus;
    }

    public MonitoringStatus(Context context) {
        this.mContext = context;
    }

    public synchronized void addRegion(Region region, Callback callback) {
        addLocalRegion(region, callback);
        saveMonitoringStatusIfOn();
    }

    public synchronized void removeRegion(Region region) {
        removeLocalRegion(region);
        saveMonitoringStatusIfOn();
    }

    public synchronized Set<Region> regions() {
        return getRegionsStateMap().keySet();
    }

    public synchronized int regionsCount() {
        return regions().size();
    }

    public synchronized RegionMonitoringState stateOf(Region region) {
        return getRegionsStateMap().get(region);
    }

    public synchronized void updateNewlyOutside() {
        boolean z = false;
        for (Region region : regions()) {
            RegionMonitoringState stateOf = stateOf(region);
            if (stateOf.markOutsideIfExpired()) {
                LogManager.d(TAG, "found a monitor that expired: %s", region);
                stateOf.getCallback().call(this.mContext, "monitoringData", new MonitoringData(stateOf.getInside(), region).toBundle());
                z = true;
            }
        }
        if (z) {
            saveMonitoringStatusIfOn();
        } else {
            updateMonitoringStatusTime(System.currentTimeMillis());
        }
    }

    public synchronized void updateNewlyInsideInRegionsContaining(Beacon beacon) {
        boolean z = false;
        for (Region region : regionsMatchingTo(beacon)) {
            RegionMonitoringState regionMonitoringState = getRegionsStateMap().get(region);
            if (regionMonitoringState != null && regionMonitoringState.markInside()) {
                z = true;
                regionMonitoringState.getCallback().call(this.mContext, "monitoringData", new MonitoringData(regionMonitoringState.getInside(), region).toBundle());
            }
        }
        if (z) {
            saveMonitoringStatusIfOn();
        } else {
            updateMonitoringStatusTime(System.currentTimeMillis());
        }
    }

    private Map<Region, RegionMonitoringState> getRegionsStateMap() {
        if (this.mRegionsStatesMap == null) {
            restoreOrInitializeMonitoringStatus();
        }
        return this.mRegionsStatesMap;
    }

    private void restoreOrInitializeMonitoringStatus() {
        long currentTimeMillis = System.currentTimeMillis() - getLastMonitoringStatusUpdateTime();
        this.mRegionsStatesMap = new ConcurrentHashMap();
        if (!this.mStatePreservationIsOn) {
            LogManager.d(TAG, "Not restoring monitoring state because persistence is disabled", new Object[0]);
        } else if (currentTimeMillis > 900000) {
            String str = TAG;
            LogManager.d(str, "Not restoring monitoring state because it was recorded too many milliseconds ago: " + currentTimeMillis, new Object[0]);
        } else {
            restoreMonitoringStatus();
            LogManager.d(TAG, "Done restoring monitoring status", new Object[0]);
        }
    }

    private List<Region> regionsMatchingTo(Beacon beacon) {
        ArrayList arrayList = new ArrayList();
        for (Region region : regions()) {
            if (region.matchesBeacon(beacon)) {
                arrayList.add(region);
            } else {
                LogManager.d(TAG, "This region (%s) does not match beacon: %s", region, beacon);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:6|7|8|(8:9|10|11|12|(2:15|13)|50|16|(2:18|19))|20|21|54) */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0066 */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0090 A[SYNTHETIC, Splitter:B:35:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009c A[SYNTHETIC, Splitter:B:42:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a3 A[SYNTHETIC, Splitter:B:46:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    public void saveMonitoringStatusIfOn() {
        ObjectOutputStream objectOutputStream;
        FileOutputStream fileOutputStream;
        Throwable th;
        IOException e;
        Throwable th2;
        if (this.mStatePreservationIsOn) {
            LogManager.d(TAG, "saveMonitoringStatusIfOn()", new Object[0]);
            if (getRegionsStateMap().size() > 50) {
                LogManager.w(TAG, "Too many regions being monitored.  Will not persist region state", new Object[0]);
                this.mContext.deleteFile("org.altbeacon.beacon.service.monitoring_status_state");
                return;
            }
            try {
                fileOutputStream = this.mContext.openFileOutput("org.altbeacon.beacon.service.monitoring_status_state", 0);
                try {
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    try {
                        Map<Region, RegionMonitoringState> regionsStateMap = getRegionsStateMap();
                        HashMap hashMap = new HashMap();
                        for (Region region : regionsStateMap.keySet()) {
                            hashMap.put(region, regionsStateMap.get(region));
                        }
                        objectOutputStream.writeObject(hashMap);
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            LogManager.e(TAG, "Error while saving monitored region states to file ", e);
                            e.printStackTrace(System.err);
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused) {
                                }
                            }
                            if (objectOutputStream == null) {
                                return;
                            }
                            objectOutputStream.close();
                        } catch (Throwable th3) {
                            th = th3;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.close();
                                } catch (IOException unused3) {
                                }
                            }
                            throw th;
                        }
                    }
                } catch (IOException e3) {
                    objectOutputStream = null;
                    e = e3;
                    LogManager.e(TAG, "Error while saving monitored region states to file ", e);
                    e.printStackTrace(System.err);
                    if (fileOutputStream != null) {
                    }
                    if (objectOutputStream == null) {
                    }
                    objectOutputStream.close();
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
            } catch (IOException e4) {
                objectOutputStream = null;
                e = e4;
                fileOutputStream = null;
                LogManager.e(TAG, "Error while saving monitored region states to file ", e);
                e.printStackTrace(System.err);
                if (fileOutputStream != null) {
                }
                if (objectOutputStream == null) {
                }
                objectOutputStream.close();
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
        }
    }

    /* access modifiers changed from: protected */
    public void updateMonitoringStatusTime(long j) {
        this.mContext.getFileStreamPath("org.altbeacon.beacon.service.monitoring_status_state").setLastModified(j);
    }

    /* access modifiers changed from: protected */
    public long getLastMonitoringStatusUpdateTime() {
        return this.mContext.getFileStreamPath("org.altbeacon.beacon.service.monitoring_status_state").lastModified();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x00a3 */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ca A[Catch:{ all -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d4 A[Catch:{ all -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e6 A[SYNTHETIC, Splitter:B:38:0x00e6] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f2 A[SYNTHETIC, Splitter:B:45:0x00f2] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f9 A[SYNTHETIC, Splitter:B:49:0x00f9] */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    public void restoreMonitoringStatus() {
        ObjectInputStream objectInputStream;
        FileInputStream fileInputStream;
        Throwable th;
        Exception e;
        Throwable th2;
        try {
            fileInputStream = this.mContext.openFileInput("org.altbeacon.beacon.service.monitoring_status_state");
            try {
                objectInputStream = new ObjectInputStream(fileInputStream);
                try {
                    Map<? extends Region, ? extends RegionMonitoringState> map = (Map) objectInputStream.readObject();
                    LogManager.d(TAG, "Restored region monitoring state for " + map.size() + " regions.", new Object[0]);
                    for (Region region : map.keySet()) {
                        LogManager.d(TAG, "Region  " + region + " uniqueId: " + region.getUniqueId() + " state: " + map.get(region), new Object[0]);
                    }
                    for (RegionMonitoringState regionMonitoringState : map.values()) {
                        if (regionMonitoringState.getInside()) {
                            regionMonitoringState.markInside();
                        }
                    }
                    this.mRegionsStatesMap.putAll(map);
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (IOException | ClassCastException | ClassNotFoundException e2) {
                    e = e2;
                    try {
                        if (e instanceof InvalidClassException) {
                            LogManager.d(TAG, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
                        } else {
                            LogManager.e(TAG, "Deserialization exception, message: %s", e.getMessage());
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        if (objectInputStream == null) {
                            return;
                        }
                        objectInputStream.close();
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileInputStream != null) {
                        }
                        if (objectInputStream != null) {
                        }
                        throw th;
                    }
                }
            } catch (IOException | ClassCastException | ClassNotFoundException e3) {
                objectInputStream = null;
                e = e3;
                if (e instanceof InvalidClassException) {
                }
                if (fileInputStream != null) {
                }
                if (objectInputStream == null) {
                }
                objectInputStream.close();
            } catch (Throwable th4) {
                th2 = th4;
                objectInputStream = null;
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (IOException | ClassCastException | ClassNotFoundException e4) {
            objectInputStream = null;
            e = e4;
            fileInputStream = null;
            if (e instanceof InvalidClassException) {
            }
            if (fileInputStream != null) {
            }
            if (objectInputStream == null) {
            }
            objectInputStream.close();
        } catch (Throwable th5) {
            th2 = th5;
            fileInputStream = null;
            objectInputStream = null;
            th = th2;
            if (fileInputStream != null) {
            }
            if (objectInputStream != null) {
            }
            throw th;
        }
        try {
            objectInputStream.close();
        } catch (IOException unused4) {
        }
    }

    public synchronized void stopStatusPreservation() {
        this.mContext.deleteFile("org.altbeacon.beacon.service.monitoring_status_state");
        this.mStatePreservationIsOn = false;
    }

    public synchronized void startStatusPreservation() {
        if (!this.mStatePreservationIsOn) {
            this.mStatePreservationIsOn = true;
            saveMonitoringStatusIfOn();
        }
    }

    public boolean isStatePreservationOn() {
        return this.mStatePreservationIsOn;
    }

    public void updateLocalState(Region region, Integer num) {
        RegionMonitoringState regionMonitoringState = getRegionsStateMap().get(region);
        if (regionMonitoringState == null) {
            regionMonitoringState = addLocalRegion(region);
        }
        if (num != null) {
            if (num.intValue() == 0) {
                regionMonitoringState.markOutside();
            }
            if (num.intValue() == 1) {
                regionMonitoringState.markInside();
            }
        }
    }

    public void removeLocalRegion(Region region) {
        getRegionsStateMap().remove(region);
    }

    public RegionMonitoringState addLocalRegion(Region region) {
        return addLocalRegion(region, new Callback(null));
    }

    private RegionMonitoringState addLocalRegion(Region region, Callback callback) {
        if (getRegionsStateMap().containsKey(region)) {
            Iterator<Region> it = getRegionsStateMap().keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Region next = it.next();
                if (next.equals(region)) {
                    if (next.hasSameIdentifiers(region)) {
                        return getRegionsStateMap().get(next);
                    }
                    String str = TAG;
                    LogManager.d(str, "Replacing region with unique identifier " + region.getUniqueId(), new Object[0]);
                    String str2 = TAG;
                    LogManager.d(str2, "Old definition: " + next, new Object[0]);
                    String str3 = TAG;
                    LogManager.d(str3, "New definition: " + region, new Object[0]);
                    LogManager.d(TAG, "clearing state", new Object[0]);
                    getRegionsStateMap().remove(region);
                }
            }
        }
        RegionMonitoringState regionMonitoringState = new RegionMonitoringState(callback);
        getRegionsStateMap().put(region, regionMonitoringState);
        return regionMonitoringState;
    }
}
