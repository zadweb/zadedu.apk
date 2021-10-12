package org.altbeacon.beacon.distance;

import android.os.Build;
import org.altbeacon.beacon.logging.LogManager;

public class AndroidModel {
    String mBuildNumber;
    String mManufacturer;
    String mModel;
    String mVersion;

    public AndroidModel(String str, String str2, String str3, String str4) {
        this.mVersion = str;
        this.mBuildNumber = str2;
        this.mModel = str3;
        this.mManufacturer = str4;
    }

    public static AndroidModel forThisDevice() {
        return new AndroidModel(Build.VERSION.RELEASE, Build.ID, Build.MODEL, Build.MANUFACTURER);
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String getBuildNumber() {
        return this.mBuildNumber;
    }

    public String getModel() {
        return this.mModel;
    }

    public String getManufacturer() {
        return this.mManufacturer;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:10:0x0025 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARNING: Unknown variable types count: 1 */
    public int matchScore(AndroidModel androidModel) {
        boolean equalsIgnoreCase = this.mManufacturer.equalsIgnoreCase(androidModel.mManufacturer);
        ?? r0 = equalsIgnoreCase;
        if (equalsIgnoreCase) {
            r0 = equalsIgnoreCase;
            if (this.mModel.equals(androidModel.mModel)) {
                r0 = 2;
            }
        }
        int i = r0;
        if (r0 == 2) {
            i = r0;
            if (this.mBuildNumber.equals(androidModel.mBuildNumber)) {
                i = 3;
            }
        }
        if (i == 3 && this.mVersion.equals(androidModel.mVersion)) {
            i = 4;
        }
        LogManager.d("AndroidModel", "Score is %s for %s compared to %s", Integer.valueOf(i == 1 ? 1 : 0), toString(), androidModel);
        return i;
    }

    public String toString() {
        return "" + this.mManufacturer + ";" + this.mModel + ";" + this.mBuildNumber + ";" + this.mVersion;
    }
}
