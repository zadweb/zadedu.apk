package com.onesignal;

import com.appnext.base.b.i;
import com.onesignal.LocationController;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public abstract class UserState {
    private static final String[] LOCATION_FIELDS = {i.fC, "long", "loc_acc", "loc_type", "loc_bg", "loc_time_stamp", "ad_id"};
    private static final Set<String> LOCATION_FIELDS_SET = new HashSet(Arrays.asList(LOCATION_FIELDS));
    private static final Object syncLock = new Object() {
        /* class com.onesignal.UserState.AnonymousClass1 */
    };
    JSONObject dependValues;
    private String persistKey;
    JSONObject syncValues;

    /* access modifiers changed from: protected */
    public abstract void addDependFields();

    /* access modifiers changed from: package-private */
    public abstract boolean isSubscribed();

    /* access modifiers changed from: package-private */
    public abstract UserState newInstance(String str);

    UserState(String str, boolean z) {
        this.persistKey = str;
        if (z) {
            loadState();
            return;
        }
        this.dependValues = new JSONObject();
        this.syncValues = new JSONObject();
    }

    /* access modifiers changed from: package-private */
    public UserState deepClone(String str) {
        UserState newInstance = newInstance(str);
        try {
            newInstance.dependValues = new JSONObject(this.dependValues.toString());
            newInstance.syncValues = new JSONObject(this.syncValues.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newInstance;
    }

    private Set<String> getGroupChangeFields(UserState userState) {
        try {
            if (this.dependValues.optLong("loc_time_stamp") == userState.dependValues.getLong("loc_time_stamp")) {
                return null;
            }
            userState.syncValues.put("loc_bg", userState.dependValues.opt("loc_bg"));
            userState.syncValues.put("loc_time_stamp", userState.dependValues.opt("loc_time_stamp"));
            return LOCATION_FIELDS_SET;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void setLocation(LocationController.LocationPoint locationPoint) {
        try {
            this.syncValues.put(i.fC, locationPoint.lat);
            this.syncValues.put("long", locationPoint.log);
            this.syncValues.put("loc_acc", locationPoint.accuracy);
            this.syncValues.put("loc_type", locationPoint.type);
            this.dependValues.put("loc_bg", locationPoint.bg);
            this.dependValues.put("loc_time_stamp", locationPoint.timeStamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public JSONObject generateJsonDiff(UserState userState, boolean z) {
        addDependFields();
        userState.addDependFields();
        JSONObject generateJsonDiff = generateJsonDiff(this.syncValues, userState.syncValues, null, getGroupChangeFields(userState));
        if (!z && generateJsonDiff.toString().equals("{}")) {
            return null;
        }
        try {
            if (!generateJsonDiff.has("app_id")) {
                generateJsonDiff.put("app_id", this.syncValues.optString("app_id"));
            }
            if (this.syncValues.has("email_auth_hash")) {
                generateJsonDiff.put("email_auth_hash", this.syncValues.optString("email_auth_hash"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return generateJsonDiff;
    }

    private void loadState() {
        int i;
        boolean z;
        String str = OneSignalPrefs.PREFS_ONESIGNAL;
        String string = OneSignalPrefs.getString(str, "ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, null);
        if (string == null) {
            this.dependValues = new JSONObject();
            try {
                int i2 = 1;
                if (this.persistKey.equals("CURRENT_STATE")) {
                    i = OneSignalPrefs.getInt(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SUBSCRIPTION", 1);
                } else {
                    i = OneSignalPrefs.getInt(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SYNCED_SUBSCRIPTION", 1);
                }
                if (i == -2) {
                    z = false;
                } else {
                    i2 = i;
                    z = true;
                }
                this.dependValues.put("subscribableStatus", i2);
                this.dependValues.put("userSubscribePref", z);
            } catch (JSONException unused) {
            }
        } else {
            try {
                this.dependValues = new JSONObject(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String str2 = OneSignalPrefs.PREFS_ONESIGNAL;
        String string2 = OneSignalPrefs.getString(str2, "ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, null);
        if (string2 == null) {
            try {
                this.syncValues = new JSONObject();
                this.syncValues.put("identifier", OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_REGISTRATION_ID", null));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            this.syncValues = new JSONObject(string2);
        }
    }

    /* access modifiers changed from: package-private */
    public void persistState() {
        synchronized (syncLock) {
            String str = OneSignalPrefs.PREFS_ONESIGNAL;
            OneSignalPrefs.saveString(str, "ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, this.syncValues.toString());
            String str2 = OneSignalPrefs.PREFS_ONESIGNAL;
            OneSignalPrefs.saveString(str2, "ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, this.dependValues.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void persistStateAfterSync(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null) {
            JSONObject jSONObject3 = this.dependValues;
            generateJsonDiff(jSONObject3, jSONObject, jSONObject3, null);
        }
        if (jSONObject2 != null) {
            JSONObject jSONObject4 = this.syncValues;
            generateJsonDiff(jSONObject4, jSONObject2, jSONObject4, null);
            mergeTags(jSONObject2, null);
        }
        if (jSONObject != null || jSONObject2 != null) {
            persistState();
        }
    }

    /* access modifiers changed from: package-private */
    public void mergeTags(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3;
        synchronized (syncLock) {
            if (jSONObject.has("tags")) {
                if (this.syncValues.has("tags")) {
                    try {
                        jSONObject3 = new JSONObject(this.syncValues.optString("tags"));
                    } catch (JSONException unused) {
                        jSONObject3 = new JSONObject();
                    }
                } else {
                    jSONObject3 = new JSONObject();
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("tags");
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    try {
                        String next = keys.next();
                        if ("".equals(optJSONObject.optString(next))) {
                            jSONObject3.remove(next);
                        } else if (jSONObject2 == null || !jSONObject2.has(next)) {
                            jSONObject3.put(next, optJSONObject.optString(next));
                        }
                    } catch (Throwable unused2) {
                    }
                }
                if (jSONObject3.toString().equals("{}")) {
                    this.syncValues.remove("tags");
                } else {
                    this.syncValues.put("tags", jSONObject3);
                }
            }
        }
    }

    private static JSONObject generateJsonDiff(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, Set<String> set) {
        JSONObject generateJsonDiff;
        synchronized (syncLock) {
            generateJsonDiff = JSONUtils.generateJsonDiff(jSONObject, jSONObject2, jSONObject3, set);
        }
        return generateJsonDiff;
    }
}
