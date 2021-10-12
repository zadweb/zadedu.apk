package androidx.media2.exoplayer.external.metadata.icy;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.Util;
import java.util.List;
import java.util.Map;

public final class IcyHeaders implements Metadata.Entry {
    public static final Parcelable.Creator<IcyHeaders> CREATOR = new Parcelable.Creator<IcyHeaders>() {
        /* class androidx.media2.exoplayer.external.metadata.icy.IcyHeaders.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public IcyHeaders createFromParcel(Parcel parcel) {
            return new IcyHeaders(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public IcyHeaders[] newArray(int i) {
            return new IcyHeaders[i];
        }
    };
    public final int bitrate;
    public final String genre;
    public final boolean isPublic;
    public final int metadataInterval;
    public final String name;
    public final String url;

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    public static IcyHeaders parse(Map<String, List<String>> map) {
        int i;
        boolean z;
        List<String> list;
        String str;
        List<String> list2;
        String str2;
        List<String> list3;
        String str3;
        List<String> list4;
        boolean z2;
        List<String> list5;
        int i2;
        List<String> list6 = map.get("icy-br");
        int i3 = -1;
        boolean z3 = true;
        if (list6 != null) {
            String str4 = list6.get(0);
            try {
                i2 = Integer.parseInt(str4) * 1000;
                if (i2 > 0) {
                    z = true;
                } else {
                    try {
                        String valueOf = String.valueOf(str4);
                        Log.w("IcyHeaders", valueOf.length() != 0 ? "Invalid bitrate: ".concat(valueOf) : new String("Invalid bitrate: "));
                        z = false;
                        i2 = -1;
                    } catch (NumberFormatException unused) {
                        String valueOf2 = String.valueOf(str4);
                        Log.w("IcyHeaders", valueOf2.length() == 0 ? "Invalid bitrate header: ".concat(valueOf2) : new String("Invalid bitrate header: "));
                        i = i2;
                        z = false;
                        list = map.get("icy-genre");
                        if (list != null) {
                        }
                        list2 = map.get("icy-name");
                        if (list2 != null) {
                        }
                        list3 = map.get("icy-url");
                        if (list3 != null) {
                        }
                        list4 = map.get("icy-pub");
                        if (list4 != null) {
                        }
                        list5 = map.get("icy-metaint");
                        if (list5 != null) {
                        }
                        if (z) {
                        }
                    }
                }
                i = i2;
            } catch (NumberFormatException unused2) {
                i2 = -1;
                String valueOf22 = String.valueOf(str4);
                Log.w("IcyHeaders", valueOf22.length() == 0 ? "Invalid bitrate header: ".concat(valueOf22) : new String("Invalid bitrate header: "));
                i = i2;
                z = false;
                list = map.get("icy-genre");
                if (list != null) {
                }
                list2 = map.get("icy-name");
                if (list2 != null) {
                }
                list3 = map.get("icy-url");
                if (list3 != null) {
                }
                list4 = map.get("icy-pub");
                if (list4 != null) {
                }
                list5 = map.get("icy-metaint");
                if (list5 != null) {
                }
                if (z) {
                }
            }
        } else {
            z = false;
            i = -1;
        }
        list = map.get("icy-genre");
        if (list != null) {
            str = list.get(0);
            z = true;
        } else {
            str = null;
        }
        list2 = map.get("icy-name");
        if (list2 != null) {
            str2 = list2.get(0);
            z = true;
        } else {
            str2 = null;
        }
        list3 = map.get("icy-url");
        if (list3 != null) {
            str3 = list3.get(0);
            z = true;
        } else {
            str3 = null;
        }
        list4 = map.get("icy-pub");
        if (list4 != null) {
            z2 = list4.get(0).equals("1");
            z = true;
        } else {
            z2 = false;
        }
        list5 = map.get("icy-metaint");
        if (list5 != null) {
            String str5 = list5.get(0);
            try {
                int parseInt = Integer.parseInt(str5);
                if (parseInt > 0) {
                    i3 = parseInt;
                } else {
                    try {
                        String valueOf3 = String.valueOf(str5);
                        Log.w("IcyHeaders", valueOf3.length() != 0 ? "Invalid metadata interval: ".concat(valueOf3) : new String("Invalid metadata interval: "));
                        z3 = z;
                    } catch (NumberFormatException unused3) {
                        i3 = parseInt;
                        String valueOf4 = String.valueOf(str5);
                        Log.w("IcyHeaders", valueOf4.length() == 0 ? "Invalid metadata interval: ".concat(valueOf4) : new String("Invalid metadata interval: "));
                        if (z) {
                        }
                    }
                }
                z = z3;
            } catch (NumberFormatException unused4) {
                String valueOf42 = String.valueOf(str5);
                Log.w("IcyHeaders", valueOf42.length() == 0 ? "Invalid metadata interval: ".concat(valueOf42) : new String("Invalid metadata interval: "));
                if (z) {
                }
            }
        }
        if (z) {
            return new IcyHeaders(i, str, str2, str3, z2, i3);
        }
        return null;
    }

    public IcyHeaders(int i, String str, String str2, String str3, boolean z, int i2) {
        Assertions.checkArgument(i2 == -1 || i2 > 0);
        this.bitrate = i;
        this.genre = str;
        this.name = str2;
        this.url = str3;
        this.isPublic = z;
        this.metadataInterval = i2;
    }

    IcyHeaders(Parcel parcel) {
        this.bitrate = parcel.readInt();
        this.genre = parcel.readString();
        this.name = parcel.readString();
        this.url = parcel.readString();
        this.isPublic = Util.readBoolean(parcel);
        this.metadataInterval = parcel.readInt();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IcyHeaders icyHeaders = (IcyHeaders) obj;
        if (this.bitrate != icyHeaders.bitrate || !Util.areEqual(this.genre, icyHeaders.genre) || !Util.areEqual(this.name, icyHeaders.name) || !Util.areEqual(this.url, icyHeaders.url) || this.isPublic != icyHeaders.isPublic || this.metadataInterval != icyHeaders.metadataInterval) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = (527 + this.bitrate) * 31;
        String str = this.genre;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.url;
        if (str3 != null) {
            i2 = str3.hashCode();
        }
        return ((((hashCode2 + i2) * 31) + (this.isPublic ? 1 : 0)) * 31) + this.metadataInterval;
    }

    public String toString() {
        String str = this.name;
        String str2 = this.genre;
        int i = this.bitrate;
        int i2 = this.metadataInterval;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 80 + String.valueOf(str2).length());
        sb.append("IcyHeaders: name=\"");
        sb.append(str);
        sb.append("\", genre=\"");
        sb.append(str2);
        sb.append("\", bitrate=");
        sb.append(i);
        sb.append(", metadataInterval=");
        sb.append(i2);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.bitrate);
        parcel.writeString(this.genre);
        parcel.writeString(this.name);
        parcel.writeString(this.url);
        Util.writeBoolean(parcel, this.isPublic);
        parcel.writeInt(this.metadataInterval);
    }
}
