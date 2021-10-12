package com.onesignal;

import com.onesignal.OneSignalRestClient;
import org.json.JSONObject;

class OneSignalRestClientWrapper implements OneSignalAPIClient {
    OneSignalRestClientWrapper() {
    }

    @Override // com.onesignal.OneSignalAPIClient
    public void post(String str, JSONObject jSONObject, final OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        OneSignalRestClient.post(str, jSONObject, new OneSignalRestClient.ResponseHandler() {
            /* class com.onesignal.OneSignalRestClientWrapper.AnonymousClass2 */

            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            public void onSuccess(String str) {
                oneSignalApiResponseHandler.onSuccess(str);
            }

            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            public void onFailure(int i, String str, Throwable th) {
                oneSignalApiResponseHandler.onFailure(i, str, th);
            }
        });
    }
}
