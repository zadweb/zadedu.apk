package org.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable;

public class AltBeacon extends Beacon {
    public static final Parcelable.Creator<AltBeacon> CREATOR = new Parcelable.Creator<AltBeacon>() {
        /* class org.altbeacon.beacon.AltBeacon.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public AltBeacon createFromParcel(Parcel parcel) {
            return new AltBeacon(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public AltBeacon[] newArray(int i) {
            return new AltBeacon[i];
        }
    };

    @Override // org.altbeacon.beacon.Beacon
    public int describeContents() {
        return 0;
    }

    protected AltBeacon() {
    }

    protected AltBeacon(Parcel parcel) {
        super(parcel);
    }

    @Override // org.altbeacon.beacon.Beacon
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
