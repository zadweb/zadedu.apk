package com.onesignal;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;
import com.onesignal.OneSignal;
import com.onesignal.PushRegistrator;

/* access modifiers changed from: package-private */
public class PushRegistratorHMS implements PushRegistrator {
    private static boolean callbackSuccessful;
    private static PushRegistrator.RegisteredHandler registeredHandler;

    PushRegistratorHMS() {
    }

    @Override // com.onesignal.PushRegistrator
    public void registerForPush(final Context context, String str, final PushRegistrator.RegisteredHandler registeredHandler2) {
        registeredHandler = registeredHandler2;
        new Thread(new Runnable() {
            /* class com.onesignal.PushRegistratorHMS.AnonymousClass1 */

            public void run() {
                try {
                    PushRegistratorHMS.this.getHMSTokenTask(context, registeredHandler2);
                } catch (ApiException e) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "HMS ApiException getting Huawei push token!", e);
                    registeredHandler2.complete(null, e.getStatusCode() == 907135000 ? -26 : -27);
                }
            }
        }, "OS_HMS_GET_TOKEN").start();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void getHMSTokenTask(Context context, PushRegistrator.RegisteredHandler registeredHandler2) throws ApiException {
        if (!OSUtils.hasAllHMSLibrariesForPushKit()) {
            registeredHandler2.complete(null, -28);
            return;
        }
        String token = HmsInstanceId.getInstance(context).getToken(AGConnectServicesConfig.fromContext(context).getString("client/app_id"), "HCM");
        if (!TextUtils.isEmpty(token)) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
            OneSignal.Log(log_level, "Device registered for HMS, push token = " + token);
            registeredHandler2.complete(token, 1);
        } else {
            waitForOnNewPushTokenEvent(registeredHandler2);
        }
    }

    private static void doTimeOutWait() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void waitForOnNewPushTokenEvent(PushRegistrator.RegisteredHandler registeredHandler2) {
        doTimeOutWait();
        if (!callbackSuccessful) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "HmsMessageServiceOneSignal.onNewToken timed out.");
            registeredHandler2.complete(null, -25);
        }
    }
}
