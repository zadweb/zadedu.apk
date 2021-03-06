package com.onesignal;

import com.google.android.gms.common.Scopes;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import com.onesignal.OneSignalStateSynchronizer;
import com.onesignal.UserStateSynchronizer;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class UserStatePushSynchronizer extends UserStateSynchronizer {
    private static boolean serverSuccess;

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public void addOnSessionOrCreateExtras(JSONObject jSONObject) {
    }

    UserStatePushSynchronizer() {
        super(OneSignalStateSynchronizer.UserStateSynchronizerType.PUSH);
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public UserState newUserState(String str, boolean z) {
        return new UserStatePush(str, z);
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public OneSignal.LOG_LEVEL getLogLevel() {
        return OneSignal.LOG_LEVEL.ERROR;
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public boolean getSubscribed() {
        return getToSyncUserState().isSubscribed();
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public UserStateSynchronizer.GetTagsResult getTags(boolean z) {
        UserStateSynchronizer.GetTagsResult getTagsResult;
        if (z) {
            String userId = OneSignal.getUserId();
            String savedAppId = OneSignal.getSavedAppId();
            OneSignalRestClient.getSync("players/" + userId + "?app_id=" + savedAppId, new OneSignalRestClient.ResponseHandler() {
                /* class com.onesignal.UserStatePushSynchronizer.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                public void onSuccess(String str) {
                    boolean unused = UserStatePushSynchronizer.serverSuccess = true;
                    if (str == null || str.isEmpty()) {
                        str = "{}";
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.has("tags")) {
                            synchronized (UserStatePushSynchronizer.this.syncLock) {
                                JSONObject generateJsonDiff = UserStatePushSynchronizer.this.generateJsonDiff(UserStatePushSynchronizer.this.currentUserState.syncValues.optJSONObject("tags"), UserStatePushSynchronizer.this.getToSyncUserState().syncValues.optJSONObject("tags"), null, null);
                                UserStatePushSynchronizer.this.currentUserState.syncValues.put("tags", jSONObject.optJSONObject("tags"));
                                UserStatePushSynchronizer.this.currentUserState.persistState();
                                UserStatePushSynchronizer.this.getToSyncUserState().mergeTags(jSONObject, generateJsonDiff);
                                UserStatePushSynchronizer.this.getToSyncUserState().persistState();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, "CACHE_KEY_GET_TAGS");
        }
        synchronized (this.syncLock) {
            getTagsResult = new UserStateSynchronizer.GetTagsResult(serverSuccess, JSONUtils.getJSONObjectWithoutBlankValues(this.toSyncUserState.syncValues, "tags"));
        }
        return getTagsResult;
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public void scheduleSyncToServer() {
        getNetworkHandlerThread(0).runNewJobDelayed();
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public void updateState(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("identifier", jSONObject.optString("identifier", null));
            if (jSONObject.has("device_type")) {
                jSONObject2.put("device_type", jSONObject.optInt("device_type"));
            }
            jSONObject2.putOpt("parent_player_id", jSONObject.optString("parent_player_id", null));
            JSONObject jSONObject3 = getUserStateForModification().syncValues;
            generateJsonDiff(jSONObject3, jSONObject2, jSONObject3, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jSONObject4 = new JSONObject();
            if (jSONObject.has("subscribableStatus")) {
                jSONObject4.put("subscribableStatus", jSONObject.optInt("subscribableStatus"));
            }
            if (jSONObject.has("androidPermission")) {
                jSONObject4.put("androidPermission", jSONObject.optBoolean("androidPermission"));
            }
            JSONObject jSONObject5 = getUserStateForModification().dependValues;
            generateJsonDiff(jSONObject5, jSONObject4, jSONObject5, null);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public boolean getUserSubscribePreference() {
        return getToSyncUserState().dependValues.optBoolean("userSubscribePref", true);
    }

    public void setPermission(boolean z) {
        try {
            getUserStateForModification().dependValues.put("androidPermission", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public String getId() {
        return OneSignal.getUserId();
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public void updateIdDependents(String str) {
        OneSignal.updateUserIdDependents(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public void fireEventsForUpdateFailure(JSONObject jSONObject) {
        if (jSONObject.has(Scopes.EMAIL)) {
            OneSignal.fireEmailUpdateFailure();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public void onSuccessfulSync(JSONObject jSONObject) {
        if (jSONObject.has(Scopes.EMAIL)) {
            OneSignal.fireEmailUpdateSuccess();
        }
        if (jSONObject.has("identifier")) {
            OneSignal.fireIdsAvailableCallback();
        }
    }
}
