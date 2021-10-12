package org.altbeacon.beacon.service;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;

public class RangingData {
    private final Collection<Beacon> mBeacons;
    private final Region mRegion;

    public RangingData(Collection<Beacon> collection, Region region) {
        synchronized (collection) {
            this.mBeacons = collection;
        }
        this.mRegion = region;
    }

    public Collection<Beacon> getBeacons() {
        return this.mBeacons;
    }

    public Region getRegion() {
        return this.mRegion;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("region", this.mRegion);
        ArrayList arrayList = new ArrayList();
        for (Beacon beacon : this.mBeacons) {
            arrayList.add(beacon);
        }
        bundle.putSerializable("beacons", arrayList);
        return bundle;
    }

    public static RangingData fromBundle(Bundle bundle) {
        bundle.setClassLoader(Region.class.getClassLoader());
        Region region = null;
        Collection collection = bundle.get("beacons") != null ? (Collection) bundle.getSerializable("beacons") : null;
        if (bundle.get("region") != null) {
            region = (Region) bundle.getSerializable("region");
        }
        return new RangingData(collection, region);
    }
}
