package com.onesignal;

import com.onesignal.OneSignal;
import com.onesignal.OneSignalStateSynchronizer;
import com.onesignal.UserStateSynchronizer;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class UserStateEmailSynchronizer extends UserStateSynchronizer {
    /* access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public boolean getSubscribed() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public UserStateSynchronizer.GetTagsResult getTags(boolean z) {
        return null;
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public void updateState(JSONObject jSONObject) {
    }

    UserStateEmailSynchronizer() {
        super(OneSignalStateSynchronizer.UserStateSynchronizerType.EMAIL);
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public UserState newUserState(String str, boolean z) {
        return new UserStateEmail(str, z);
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public OneSignal.LOG_LEVEL getLogLevel() {
        return OneSignal.LOG_LEVEL.INFO;
    }

    /* access modifiers changed from: package-private */
    public void refresh() {
        scheduleSyncToServer();
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public void scheduleSyncToServer() {
        if (!(getId() == null && getRegistrationId() == null) && OneSignal.getUserId() != null) {
            getNetworkHandlerThread(0).runNewJobDelayed();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public String getId() {
        return OneSignal.getEmailId();
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public void updateIdDependents(String str) {
        OneSignal.updateEmailIdDependents(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public void addOnSessionOrCreateExtras(JSONObject jSONObject) {
        try {
            jSONObject.put("device_type", 11);
            jSONObject.putOpt("device_player_id", OneSignal.getUserId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public void fireEventsForUpdateFailure(JSONObject jSONObject) {
        if (jSONObject.has("identifier")) {
            OneSignal.fireEmailUpdateFailure();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.UserStateSynchronizer
    public void onSuccessfulSync(JSONObject jSONObject) {
        if (jSONObject.has("identifier")) {
            OneSignal.fireEmailUpdateSuccess();
        }
    }
}
