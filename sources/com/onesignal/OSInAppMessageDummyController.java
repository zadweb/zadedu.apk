package com.onesignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class OSInAppMessageDummyController extends OSInAppMessageController {
    /* access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void displayPreviewMessage(String str) {
    }

    @Override // com.onesignal.OSInAppMessageController
    public void initRedisplayData(OneSignalDbHelper oneSignalDbHelper) {
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void initWithCachedInAppMessages() {
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public boolean isInAppMessageShowing() {
        return false;
    }

    @Override // com.onesignal.OSDynamicTriggerController.OSDynamicTriggerControllerObserver, com.onesignal.OSInAppMessageController
    public void messageTriggerConditionChanged() {
    }

    @Override // com.onesignal.OSInAppMessageController
    public void messageWasDismissed(OSInAppMessage oSInAppMessage) {
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void onMessageActionOccurredOnMessage(OSInAppMessage oSInAppMessage, JSONObject jSONObject) {
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void onMessageActionOccurredOnPreview(OSInAppMessage oSInAppMessage, JSONObject jSONObject) {
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void receivedInAppMessageJson(JSONArray jSONArray) throws JSONException {
    }

    OSInAppMessageDummyController(OneSignalDbHelper oneSignalDbHelper) {
        super(oneSignalDbHelper);
    }
}
