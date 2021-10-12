package com.google.android.exoplayer2.metadata.emsg;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class EventMessage implements Metadata.Entry {
    public static final Parcelable.Creator<EventMessage> CREATOR = new Parcelable.Creator<EventMessage>() {
        /* class com.google.android.exoplayer2.metadata.emsg.EventMessage.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public EventMessage createFromParcel(Parcel parcel) {
            return new EventMessage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public EventMessage[] newArray(int i) {
            return new EventMessage[i];
        }
    };
    private static final Format ID3_FORMAT = Format.createSampleFormat(null, "application/id3", Long.MAX_VALUE);
    private static final Format SCTE35_FORMAT = Format.createSampleFormat(null, "application/x-scte35", Long.MAX_VALUE);
    public final long durationMs;
    private int hashCode;
    public final long id;
    public final byte[] messageData;
    public final String schemeIdUri;
    public final String value;

    public int describeContents() {
        return 0;
    }

    public EventMessage(String str, String str2, long j, long j2, byte[] bArr) {
        this.schemeIdUri = str;
        this.value = str2;
        this.durationMs = j;
        this.id = j2;
        this.messageData = bArr;
    }

    EventMessage(Parcel parcel) {
        this.schemeIdUri = (String) Util.castNonNull(parcel.readString());
        this.value = (String) Util.castNonNull(parcel.readString());
        this.durationMs = parcel.readLong();
        this.id = parcel.readLong();
        this.messageData = (byte[]) Util.castNonNull(parcel.createByteArray());
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039 A[ADDED_TO_REGION] */
    public Format getWrappedMetadataFormat() {
        char c;
        String str = this.schemeIdUri;
        int hashCode2 = str.hashCode();
        if (hashCode2 != -1468477611) {
            if (hashCode2 != -795945609) {
                if (hashCode2 == 1303648457 && str.equals("https://developer.apple.com/streaming/emsg-id3")) {
                    c = 1;
                    if (c != 0 || c == 1) {
                        return ID3_FORMAT;
                    }
                    if (c != 2) {
                        return null;
                    }
                    return SCTE35_FORMAT;
                }
            } else if (str.equals("https://aomedia.org/emsg/ID3")) {
                c = 0;
                if (c != 0) {
                }
                return ID3_FORMAT;
            }
        } else if (str.equals("urn:scte:scte35:2014:bin")) {
            c = 2;
            if (c != 0) {
            }
            return ID3_FORMAT;
        }
        c = 65535;
        if (c != 0) {
        }
        return ID3_FORMAT;
    }

    public byte[] getWrappedMetadataBytes() {
        if (getWrappedMetadataFormat() != null) {
            return this.messageData;
        }
        return null;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.schemeIdUri;
            int i = 0;
            int hashCode2 = (527 + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.value;
            if (str2 != null) {
                i = str2.hashCode();
            }
            long j = this.durationMs;
            long j2 = this.id;
            this.hashCode = ((((((hashCode2 + i) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + Arrays.hashCode(this.messageData);
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EventMessage eventMessage = (EventMessage) obj;
        if (this.durationMs != eventMessage.durationMs || this.id != eventMessage.id || !Util.areEqual(this.schemeIdUri, eventMessage.schemeIdUri) || !Util.areEqual(this.value, eventMessage.value) || !Arrays.equals(this.messageData, eventMessage.messageData)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "EMSG: scheme=" + this.schemeIdUri + ", id=" + this.id + ", durationMs=" + this.durationMs + ", value=" + this.value;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.schemeIdUri);
        parcel.writeString(this.value);
        parcel.writeLong(this.durationMs);
        parcel.writeLong(this.id);
        parcel.writeByteArray(this.messageData);
    }
}
