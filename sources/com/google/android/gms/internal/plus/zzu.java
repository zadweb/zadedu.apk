package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.plus.zzr;
import java.util.HashSet;

public final class zzu implements Parcelable.Creator<zzr.zzb> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzb createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        zzr.zzb.zza zza = null;
        zzr.zzb.C0014zzb zzb = null;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            int i3 = 1;
            if (fieldId != 1) {
                i3 = 2;
                if (fieldId != 2) {
                    i3 = 3;
                    if (fieldId != 3) {
                        i3 = 4;
                        if (fieldId != 4) {
                            SafeParcelReader.skipUnknownField(parcel, readHeader);
                        } else {
                            i2 = SafeParcelReader.readInt(parcel, readHeader);
                        }
                    } else {
                        zzb = (zzr.zzb.C0014zzb) SafeParcelReader.createParcelable(parcel, readHeader, zzr.zzb.C0014zzb.CREATOR);
                    }
                } else {
                    zza = (zzr.zzb.zza) SafeParcelReader.createParcelable(parcel, readHeader, zzr.zzb.zza.CREATOR);
                }
            } else {
                i = SafeParcelReader.readInt(parcel, readHeader);
            }
            hashSet.add(Integer.valueOf(i3));
        }
        if (parcel.dataPosition() == validateObjectHeader) {
            return new zzr.zzb(hashSet, i, zza, zzb, i2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(validateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzb[] newArray(int i) {
        return new zzr.zzb[i];
    }
}
