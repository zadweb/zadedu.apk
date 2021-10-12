package com.appnext.base.b;

import android.os.BaseBundle;
import android.os.Bundle;
import android.os.PersistableBundle;

public final class a {
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004b A[Catch:{ all -> 0x00f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0011 A[SYNTHETIC] */
    public static PersistableBundle a(Bundle bundle) {
        boolean z;
        if (bundle == null) {
            return null;
        }
        try {
            PersistableBundle persistableBundle = new PersistableBundle();
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (!(obj instanceof PersistableBundle) && !(obj instanceof Integer) && !(obj instanceof int[]) && !(obj instanceof Long) && !(obj instanceof long[]) && !(obj instanceof Double) && !(obj instanceof double[]) && !(obj instanceof String)) {
                    if (!(obj instanceof String[])) {
                        z = false;
                        if (z) {
                            if (obj == null) {
                                throw new IllegalArgumentException("Unable to determine type of null values");
                            } else if (obj instanceof Integer) {
                                persistableBundle.putInt(str, ((Integer) obj).intValue());
                            } else if (obj instanceof int[]) {
                                persistableBundle.putIntArray(str, (int[]) obj);
                            } else if (obj instanceof Long) {
                                persistableBundle.putLong(str, ((Long) obj).longValue());
                            } else if (obj instanceof long[]) {
                                persistableBundle.putLongArray(str, (long[]) obj);
                            } else if (obj instanceof Double) {
                                persistableBundle.putDouble(str, ((Double) obj).doubleValue());
                            } else if (obj instanceof double[]) {
                                persistableBundle.putDoubleArray(str, (double[]) obj);
                            } else if (obj instanceof String) {
                                persistableBundle.putString(str, (String) obj);
                            } else if (obj instanceof String[]) {
                                persistableBundle.putStringArray(str, (String[]) obj);
                            } else if (obj instanceof PersistableBundle) {
                                persistableBundle.putAll((PersistableBundle) obj);
                            } else {
                                throw new IllegalArgumentException("Objects of type " + obj.getClass().getSimpleName() + " can not be put into a " + BaseBundle.class.getSimpleName());
                            }
                        }
                    }
                }
                z = true;
                if (z) {
                }
            }
            return persistableBundle;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean b(Object obj) {
        return (obj instanceof PersistableBundle) || (obj instanceof Integer) || (obj instanceof int[]) || (obj instanceof Long) || (obj instanceof long[]) || (obj instanceof Double) || (obj instanceof double[]) || (obj instanceof String) || (obj instanceof String[]);
    }

    private static void a(PersistableBundle persistableBundle, String str, Object obj) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("Unable to determine type of null values");
        } else if (obj instanceof Integer) {
            persistableBundle.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof int[]) {
            persistableBundle.putIntArray(str, (int[]) obj);
        } else if (obj instanceof Long) {
            persistableBundle.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof long[]) {
            persistableBundle.putLongArray(str, (long[]) obj);
        } else if (obj instanceof Double) {
            persistableBundle.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof double[]) {
            persistableBundle.putDoubleArray(str, (double[]) obj);
        } else if (obj instanceof String) {
            persistableBundle.putString(str, (String) obj);
        } else if (obj instanceof String[]) {
            persistableBundle.putStringArray(str, (String[]) obj);
        } else if (obj instanceof PersistableBundle) {
            persistableBundle.putAll((PersistableBundle) obj);
        } else {
            throw new IllegalArgumentException("Objects of type " + obj.getClass().getSimpleName() + " can not be put into a " + BaseBundle.class.getSimpleName());
        }
    }
}
