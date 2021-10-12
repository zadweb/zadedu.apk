package com.onesignal;

import android.location.Location;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.location.FusedLocationProviderClient;
import com.huawei.hms.location.LocationCallback;
import com.huawei.hms.location.LocationRequest;
import com.huawei.hms.location.LocationServices;
import com.onesignal.OneSignal;

/* access modifiers changed from: package-private */
public class HMSLocationController extends LocationController {
    private static FusedLocationProviderClient hmsFusedLocationClient;
    static LocationUpdateListener locationUpdateListener;

    static void startGetLocation() {
        initHuaweiLocation();
    }

    private static void initHuaweiLocation() {
        synchronized (syncLock) {
            if (hmsFusedLocationClient == null) {
                try {
                    hmsFusedLocationClient = LocationServices.getFusedLocationProviderClient(classContext);
                } catch (Exception e) {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level, "Huawei LocationServices getFusedLocationProviderClient failed! " + e);
                    fireFailedComplete();
                    return;
                }
            }
            if (lastLocation != null) {
                fireCompleteForLocation(lastLocation);
            } else {
                hmsFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    /* class com.onesignal.HMSLocationController.AnonymousClass2 */
                }).addOnFailureListener(new OnFailureListener() {
                    /* class com.onesignal.HMSLocationController.AnonymousClass1 */
                });
            }
        }
    }

    static void fireFailedComplete() {
        synchronized (syncLock) {
            hmsFusedLocationClient = null;
        }
    }

    static void onFocusChange() {
        synchronized (syncLock) {
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "HMSLocationController onFocusChange!");
            if (!isHMSAvailable() || hmsFusedLocationClient != null) {
                if (hmsFusedLocationClient != null) {
                    if (locationUpdateListener != null) {
                        hmsFusedLocationClient.removeLocationUpdates(locationUpdateListener);
                    }
                    locationUpdateListener = new LocationUpdateListener(hmsFusedLocationClient);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class LocationUpdateListener extends LocationCallback {
        private FusedLocationProviderClient huaweiFusedLocationProviderClient;

        LocationUpdateListener(FusedLocationProviderClient fusedLocationProviderClient) {
            this.huaweiFusedLocationProviderClient = fusedLocationProviderClient;
            init();
        }

        private void init() {
            long j = OneSignal.isForeground() ? 270000 : 570000;
            LocationRequest interval = LocationRequest.create().setFastestInterval(j).setInterval(j);
            double d = (double) j;
            Double.isNaN(d);
            LocationRequest priority = interval.setMaxWaitTime((long) (d * 1.5d)).setPriority(102);
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "HMSLocationController Huawei LocationServices requestLocationUpdates!");
            this.huaweiFusedLocationProviderClient.requestLocationUpdates(priority, this, LocationController.locationHandlerThread.getLooper());
        }
    }
}
