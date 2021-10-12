package com.onesignal;

import android.os.Handler;
import android.os.HandlerThread;
import com.appnext.base.b.d;
import com.google.android.gms.common.Scopes;
import com.onesignal.LocationController;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import com.onesignal.OneSignalStateSynchronizer;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public abstract class UserStateSynchronizer {
    private boolean canMakeUpdates;
    private OneSignalStateSynchronizer.UserStateSynchronizerType channel;
    protected UserState currentUserState;
    private final Queue<OneSignal.OSInternalExternalUserIdUpdateCompletionHandler> externalUserIdUpdateHandlers = new ConcurrentLinkedQueue();
    private final Object networkHandlerSyncLock = new Object() {
        /* class com.onesignal.UserStateSynchronizer.AnonymousClass2 */
    };
    HashMap<Integer, NetworkHandlerThread> networkHandlerThreads = new HashMap<>();
    private AtomicBoolean runningSyncUserState = new AtomicBoolean();
    private final Queue<OneSignal.ChangeTagsUpdateHandler> sendTagsHandlers = new ConcurrentLinkedQueue();
    protected final Object syncLock = new Object() {
        /* class com.onesignal.UserStateSynchronizer.AnonymousClass1 */
    };
    protected UserState toSyncUserState;
    protected boolean waitingForSessionResponse = false;

    /* access modifiers changed from: protected */
    public abstract void addOnSessionOrCreateExtras(JSONObject jSONObject);

    /* access modifiers changed from: protected */
    public abstract void fireEventsForUpdateFailure(JSONObject jSONObject);

    /* access modifiers changed from: protected */
    public abstract String getId();

    /* access modifiers changed from: protected */
    public abstract OneSignal.LOG_LEVEL getLogLevel();

    /* access modifiers changed from: package-private */
    public abstract boolean getSubscribed();

    /* access modifiers changed from: package-private */
    public abstract GetTagsResult getTags(boolean z);

    /* access modifiers changed from: protected */
    public abstract UserState newUserState(String str, boolean z);

    /* access modifiers changed from: protected */
    public abstract void onSuccessfulSync(JSONObject jSONObject);

    /* access modifiers changed from: protected */
    public abstract void scheduleSyncToServer();

    /* access modifiers changed from: package-private */
    public abstract void updateIdDependents(String str);

    /* access modifiers changed from: package-private */
    public abstract void updateState(JSONObject jSONObject);

    UserStateSynchronizer(OneSignalStateSynchronizer.UserStateSynchronizerType userStateSynchronizerType) {
        this.channel = userStateSynchronizerType;
    }

    /* access modifiers changed from: package-private */
    public String getChannelString() {
        return this.channel.name().toLowerCase();
    }

    /* access modifiers changed from: package-private */
    public static class GetTagsResult {
        JSONObject result;
        boolean serverSuccess;

        GetTagsResult(boolean z, JSONObject jSONObject) {
            this.serverSuccess = z;
            this.result = jSONObject;
        }
    }

    /* access modifiers changed from: package-private */
    public String getRegistrationId() {
        return getToSyncUserState().syncValues.optString("identifier", null);
    }

    /* access modifiers changed from: package-private */
    public class NetworkHandlerThread extends HandlerThread {
        int currentRetry;
        Handler mHandler = null;
        int mType;

        NetworkHandlerThread(int i) {
            super("OSH_NetworkHandlerThread");
            this.mType = i;
            start();
            this.mHandler = new Handler(getLooper());
        }

        /* access modifiers changed from: package-private */
        public void runNewJobDelayed() {
            if (UserStateSynchronizer.this.canMakeUpdates) {
                synchronized (this.mHandler) {
                    this.currentRetry = 0;
                    this.mHandler.removeCallbacksAndMessages(null);
                    this.mHandler.postDelayed(getNewRunnable(), 5000);
                }
            }
        }

        private Runnable getNewRunnable() {
            if (this.mType != 0) {
                return null;
            }
            return new Runnable() {
                /* class com.onesignal.UserStateSynchronizer.NetworkHandlerThread.AnonymousClass1 */

                public void run() {
                    if (!UserStateSynchronizer.this.runningSyncUserState.get()) {
                        UserStateSynchronizer.this.syncUserState(false);
                    }
                }
            };
        }

        /* access modifiers changed from: package-private */
        public boolean doRetry() {
            boolean hasMessages;
            synchronized (this.mHandler) {
                boolean z = this.currentRetry < 3;
                boolean hasMessages2 = this.mHandler.hasMessages(0);
                if (z && !hasMessages2) {
                    this.currentRetry++;
                    this.mHandler.postDelayed(getNewRunnable(), (long) (this.currentRetry * d.fd));
                }
                hasMessages = this.mHandler.hasMessages(0);
            }
            return hasMessages;
        }
    }

    /* access modifiers changed from: protected */
    public JSONObject generateJsonDiff(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, Set<String> set) {
        JSONObject generateJsonDiff;
        synchronized (this.syncLock) {
            generateJsonDiff = JSONUtils.generateJsonDiff(jSONObject, jSONObject2, jSONObject3, set);
        }
        return generateJsonDiff;
    }

    /* access modifiers changed from: protected */
    public UserState getCurrentUserState() {
        synchronized (this.syncLock) {
            if (this.currentUserState == null) {
                this.currentUserState = newUserState("CURRENT_STATE", true);
            }
        }
        return this.currentUserState;
    }

    /* access modifiers changed from: protected */
    public UserState getToSyncUserState() {
        synchronized (this.syncLock) {
            if (this.toSyncUserState == null) {
                this.toSyncUserState = newUserState("TOSYNC_STATE", true);
            }
        }
        return this.toSyncUserState;
    }

    /* access modifiers changed from: package-private */
    public void initUserState() {
        synchronized (this.syncLock) {
            if (this.currentUserState == null) {
                this.currentUserState = newUserState("CURRENT_STATE", true);
            }
        }
        getToSyncUserState();
    }

    /* access modifiers changed from: package-private */
    public boolean persist() {
        boolean z = false;
        if (this.toSyncUserState == null) {
            return false;
        }
        synchronized (this.syncLock) {
            if (this.currentUserState.generateJsonDiff(this.toSyncUserState, isSessionCall()) != null) {
                z = true;
            }
            this.toSyncUserState.persistState();
        }
        return z;
    }

    private boolean isSessionCall() {
        return (getToSyncUserState().dependValues.optBoolean("session") || getId() == null) && !this.waitingForSessionResponse;
    }

    private boolean syncEmailLogout() {
        return getToSyncUserState().dependValues.optBoolean("logoutEmail", false);
    }

    /* access modifiers changed from: package-private */
    public void syncUserState(boolean z) {
        this.runningSyncUserState.set(true);
        internalSyncUserState(z);
        this.runningSyncUserState.set(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        if (r7 != false) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0057, code lost:
        doPutSync(r0, r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005b, code lost:
        doCreateOrNewSession(r0, r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    private void internalSyncUserState(boolean z) {
        String id = getId();
        if (!syncEmailLogout() || id == null) {
            if (this.currentUserState == null) {
                initUserState();
            }
            boolean z2 = !z && isSessionCall();
            synchronized (this.syncLock) {
                JSONObject generateJsonDiff = this.currentUserState.generateJsonDiff(getToSyncUserState(), z2);
                JSONObject generateJsonDiff2 = generateJsonDiff(this.currentUserState.dependValues, getToSyncUserState().dependValues, null, null);
                if (generateJsonDiff == null) {
                    this.currentUserState.persistStateAfterSync(generateJsonDiff2, null);
                    sendTagsHandlersPerformOnSuccess();
                    externalUserIdUpdateHandlersPerformOnSuccess();
                    return;
                }
                getToSyncUserState().persistState();
            }
        } else {
            doEmailLogout(id);
        }
    }

    private void doEmailLogout(String str) {
        String str2 = "players/" + str + "/email_logout";
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = this.currentUserState.dependValues;
            if (jSONObject2.has("email_auth_hash")) {
                jSONObject.put("email_auth_hash", jSONObject2.optString("email_auth_hash"));
            }
            JSONObject jSONObject3 = this.currentUserState.syncValues;
            if (jSONObject3.has("parent_player_id")) {
                jSONObject.put("parent_player_id", jSONObject3.optString("parent_player_id"));
            }
            jSONObject.put("app_id", jSONObject3.optString("app_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OneSignalRestClient.postSync(str2, jSONObject, new OneSignalRestClient.ResponseHandler() {
            /* class com.onesignal.UserStateSynchronizer.AnonymousClass3 */

            /* access modifiers changed from: package-private */
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            public void onFailure(int i, String str, Throwable th) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.WARN;
                OneSignal.Log(log_level, "Failed last request. statusCode: " + i + "\nresponse: " + str);
                if (UserStateSynchronizer.this.response400WithErrorsContaining(i, str, "already logged out of email")) {
                    UserStateSynchronizer.this.logoutEmailSyncSuccess();
                } else if (UserStateSynchronizer.this.response400WithErrorsContaining(i, str, "not a valid device_type")) {
                    UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                } else {
                    UserStateSynchronizer.this.handleNetworkFailure(i);
                }
            }

            /* access modifiers changed from: package-private */
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            public void onSuccess(String str) {
                UserStateSynchronizer.this.logoutEmailSyncSuccess();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logoutEmailSyncSuccess() {
        getToSyncUserState().dependValues.remove("logoutEmail");
        this.toSyncUserState.dependValues.remove("email_auth_hash");
        this.toSyncUserState.syncValues.remove("parent_player_id");
        this.toSyncUserState.persistState();
        this.currentUserState.dependValues.remove("email_auth_hash");
        this.currentUserState.syncValues.remove("parent_player_id");
        String optString = this.currentUserState.syncValues.optString(Scopes.EMAIL);
        this.currentUserState.syncValues.remove(Scopes.EMAIL);
        OneSignalStateSynchronizer.setNewSessionForEmail();
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
        OneSignal.Log(log_level, "Device successfully logged out of email: " + optString);
        OneSignal.handleSuccessfulEmailLogout();
    }

    private void doPutSync(String str, final JSONObject jSONObject, final JSONObject jSONObject2) {
        if (str == null) {
            OneSignal.onesignalLog(getLogLevel(), "Error updating the user record because of the null user id");
            sendTagsHandlersPerformOnFailure(new OneSignal.SendTagsError(-1, "Unable to update tags: the current user is not registered with OneSignal"));
            externalUserIdUpdateHandlersPerformOnFailure();
            return;
        }
        OneSignalRestClient.putSync("players/" + str, jSONObject, new OneSignalRestClient.ResponseHandler() {
            /* class com.onesignal.UserStateSynchronizer.AnonymousClass4 */

            /* access modifiers changed from: package-private */
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            public void onFailure(int i, String str, Throwable th) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.Log(log_level, "Failed PUT sync request with status code: " + i + " and response: " + str);
                synchronized (UserStateSynchronizer.this.syncLock) {
                    if (UserStateSynchronizer.this.response400WithErrorsContaining(i, str, "No user with this id found")) {
                        UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                    } else {
                        UserStateSynchronizer.this.handleNetworkFailure(i);
                    }
                }
                if (jSONObject.has("tags")) {
                    UserStateSynchronizer.this.sendTagsHandlersPerformOnFailure(new OneSignal.SendTagsError(i, str));
                }
                if (jSONObject.has("external_user_id")) {
                    OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.onesignalLog(log_level2, "Error setting external user id for push with status code: " + i + " and message: " + str);
                    UserStateSynchronizer.this.externalUserIdUpdateHandlersPerformOnFailure();
                }
            }

            /* access modifiers changed from: package-private */
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            public void onSuccess(String str) {
                synchronized (UserStateSynchronizer.this.syncLock) {
                    UserStateSynchronizer.this.currentUserState.persistStateAfterSync(jSONObject2, jSONObject);
                    UserStateSynchronizer.this.onSuccessfulSync(jSONObject);
                }
                if (jSONObject.has("tags")) {
                    UserStateSynchronizer.this.sendTagsHandlersPerformOnSuccess();
                }
                if (jSONObject.has("external_user_id")) {
                    UserStateSynchronizer.this.externalUserIdUpdateHandlersPerformOnSuccess();
                }
            }
        });
    }

    private void doCreateOrNewSession(final String str, final JSONObject jSONObject, final JSONObject jSONObject2) {
        String str2;
        if (str == null) {
            str2 = "players";
        } else {
            str2 = "players/" + str + "/on_session";
        }
        this.waitingForSessionResponse = true;
        addOnSessionOrCreateExtras(jSONObject);
        OneSignalRestClient.postSync(str2, jSONObject, new OneSignalRestClient.ResponseHandler() {
            /* class com.onesignal.UserStateSynchronizer.AnonymousClass5 */

            /* access modifiers changed from: package-private */
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            public void onFailure(int i, String str, Throwable th) {
                synchronized (UserStateSynchronizer.this.syncLock) {
                    UserStateSynchronizer.this.waitingForSessionResponse = false;
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.WARN;
                    OneSignal.Log(log_level, "Failed last request. statusCode: " + i + "\nresponse: " + str);
                    if (UserStateSynchronizer.this.response400WithErrorsContaining(i, str, "not a valid device_type")) {
                        UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                    } else {
                        UserStateSynchronizer.this.handleNetworkFailure(i);
                    }
                }
            }

            /* access modifiers changed from: package-private */
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            public void onSuccess(String str) {
                synchronized (UserStateSynchronizer.this.syncLock) {
                    UserStateSynchronizer.this.waitingForSessionResponse = false;
                    UserStateSynchronizer.this.currentUserState.persistStateAfterSync(jSONObject2, jSONObject);
                    try {
                        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                        OneSignal.onesignalLog(log_level, "doCreateOrNewSession:response: " + str);
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.has("id")) {
                            String optString = jSONObject.optString("id");
                            UserStateSynchronizer.this.updateIdDependents(optString);
                            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.INFO;
                            OneSignal.Log(log_level2, "Device registered, UserId = " + optString);
                        } else {
                            OneSignal.LOG_LEVEL log_level3 = OneSignal.LOG_LEVEL.INFO;
                            OneSignal.Log(log_level3, "session sent, UserId = " + str);
                        }
                        UserStateSynchronizer.this.getUserStateForModification().dependValues.put("session", false);
                        UserStateSynchronizer.this.getUserStateForModification().persistState();
                        if (jSONObject.has("in_app_messages")) {
                            OSInAppMessageController.getController().receivedInAppMessageJson(jSONObject.getJSONArray("in_app_messages"));
                        }
                        UserStateSynchronizer.this.onSuccessfulSync(jSONObject);
                    } catch (JSONException e) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "ERROR parsing on_session or create JSON Response.", e);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleNetworkFailure(int i) {
        if (i == 403) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "403 error updating player, omitting further retries!");
            fireNetworkFailureEvents();
        } else if (!getNetworkHandlerThread(0).doRetry()) {
            fireNetworkFailureEvents();
        }
    }

    private void fireNetworkFailureEvents() {
        JSONObject generateJsonDiff = this.currentUserState.generateJsonDiff(this.toSyncUserState, false);
        if (generateJsonDiff != null) {
            fireEventsForUpdateFailure(generateJsonDiff);
        }
        if (getToSyncUserState().dependValues.optBoolean("logoutEmail", false)) {
            OneSignal.handleFailedEmailLogout();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean response400WithErrorsContaining(int i, String str, String str2) {
        if (i == 400 && str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.has("errors") || !jSONObject.optString("errors").contains(str2)) {
                    return false;
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public NetworkHandlerThread getNetworkHandlerThread(Integer num) {
        NetworkHandlerThread networkHandlerThread;
        synchronized (this.networkHandlerSyncLock) {
            if (!this.networkHandlerThreads.containsKey(num)) {
                this.networkHandlerThreads.put(num, new NetworkHandlerThread(num.intValue()));
            }
            networkHandlerThread = this.networkHandlerThreads.get(num);
        }
        return networkHandlerThread;
    }

    /* access modifiers changed from: protected */
    public UserState getUserStateForModification() {
        if (this.toSyncUserState == null) {
            this.toSyncUserState = getCurrentUserState().deepClone("TOSYNC_STATE");
        }
        scheduleSyncToServer();
        return this.toSyncUserState;
    }

    /* access modifiers changed from: package-private */
    public void updateDeviceInfo(JSONObject jSONObject) {
        JSONObject jSONObject2 = getUserStateForModification().syncValues;
        generateJsonDiff(jSONObject2, jSONObject, jSONObject2, null);
    }

    /* access modifiers changed from: package-private */
    public void setNewSession() {
        try {
            synchronized (this.syncLock) {
                getUserStateForModification().dependValues.put("session", true);
                getUserStateForModification().persistState();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getSyncAsNewSession() {
        return getUserStateForModification().dependValues.optBoolean("session");
    }

    /* access modifiers changed from: package-private */
    public void sendTags(JSONObject jSONObject, OneSignal.ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        if (changeTagsUpdateHandler != null) {
            this.sendTagsHandlers.add(changeTagsUpdateHandler);
        }
        JSONObject jSONObject2 = getUserStateForModification().syncValues;
        generateJsonDiff(jSONObject2, jSONObject, jSONObject2, null);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handlePlayerDeletedFromServer() {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Creating new player based on missing player_id noted above.");
        OneSignal.handleSuccessfulEmailLogout();
        resetCurrentState();
        updateIdDependents(null);
        scheduleSyncToServer();
    }

    /* access modifiers changed from: package-private */
    public void resetCurrentState() {
        this.currentUserState.syncValues = new JSONObject();
        this.currentUserState.persistState();
    }

    /* access modifiers changed from: package-private */
    public void updateLocation(LocationController.LocationPoint locationPoint) {
        getUserStateForModification().setLocation(locationPoint);
    }

    /* access modifiers changed from: package-private */
    public void readyToUpdate(boolean z) {
        boolean z2 = this.canMakeUpdates != z;
        this.canMakeUpdates = z;
        if (z2 && z) {
            scheduleSyncToServer();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendTagsHandlersPerformOnSuccess() {
        JSONObject jSONObject = OneSignalStateSynchronizer.getTags(false).result;
        while (true) {
            OneSignal.ChangeTagsUpdateHandler poll = this.sendTagsHandlers.poll();
            if (poll != null) {
                poll.onSuccess(jSONObject);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendTagsHandlersPerformOnFailure(OneSignal.SendTagsError sendTagsError) {
        while (true) {
            OneSignal.ChangeTagsUpdateHandler poll = this.sendTagsHandlers.poll();
            if (poll != null) {
                poll.onFailure(sendTagsError);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void externalUserIdUpdateHandlersPerformOnSuccess() {
        while (true) {
            OneSignal.OSInternalExternalUserIdUpdateCompletionHandler poll = this.externalUserIdUpdateHandlers.poll();
            if (poll != null) {
                poll.onComplete(getChannelString(), true);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void externalUserIdUpdateHandlersPerformOnFailure() {
        while (true) {
            OneSignal.OSInternalExternalUserIdUpdateCompletionHandler poll = this.externalUserIdUpdateHandlers.poll();
            if (poll != null) {
                poll.onComplete(getChannelString(), false);
            } else {
                return;
            }
        }
    }
}
