package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.mopub.common.Constants;
import com.onesignal.NotificationBundleProcessor;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    private static boolean isGcmMessage(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            return false;
        }
        String stringExtra = intent.getStringExtra(Constants.VAST_TRACKER_MESSAGE_TYPE);
        if (stringExtra == null || "gcm".equals(stringExtra)) {
            return true;
        }
        return false;
    }

    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null && !"google.com/iid".equals(extras.getString("from"))) {
            OneSignal.setAppContext(context);
            NotificationBundleProcessor.ProcessedBundleResult processOrderBroadcast = processOrderBroadcast(context, intent, extras);
            if (processOrderBroadcast == null) {
                setSuccessfulResultCode();
            } else if (processOrderBroadcast.isDup || processOrderBroadcast.hasExtenderService) {
                setAbort();
            } else if (!processOrderBroadcast.isOneSignalPayload || !OneSignal.getFilterOtherGCMReceivers(context)) {
                setSuccessfulResultCode();
            } else {
                setAbort();
            }
        }
    }

    private void setSuccessfulResultCode() {
        if (isOrderedBroadcast()) {
            setResultCode(-1);
        }
    }

    private void setAbort() {
        if (isOrderedBroadcast()) {
            abortBroadcast();
            setResultCode(-1);
        }
    }

    private static NotificationBundleProcessor.ProcessedBundleResult processOrderBroadcast(Context context, Intent intent, Bundle bundle) {
        if (!isGcmMessage(intent)) {
            return null;
        }
        NotificationBundleProcessor.ProcessedBundleResult processBundleFromReceiver = NotificationBundleProcessor.processBundleFromReceiver(context, bundle);
        if (processBundleFromReceiver.processed()) {
            return processBundleFromReceiver;
        }
        startGCMService(context, bundle);
        return processBundleFromReceiver;
    }

    static void startGCMService(Context context, Bundle bundle) {
        if (!NotificationBundleProcessor.hasRemoteResource(bundle)) {
            NotificationBundleProcessor.ProcessFromGCMIntentService(context, setCompatBundleForServer(bundle, BundleCompatFactory.getInstance()), null);
            return;
        }
        if ((Integer.parseInt(bundle.getString("pri", "0")) > 9) || Build.VERSION.SDK_INT < 26) {
            try {
                startGCMServiceWithWakefulService(context, bundle);
            } catch (IllegalStateException e) {
                if (Build.VERSION.SDK_INT >= 21) {
                    startGCMServiceWithJobIntentService(context, bundle);
                    return;
                }
                throw e;
            }
        } else {
            startGCMServiceWithJobIntentService(context, bundle);
        }
    }

    private static void startGCMServiceWithJobIntentService(Context context, Bundle bundle) {
        BundleCompat compatBundleForServer = setCompatBundleForServer(bundle, BundleCompatFactory.getInstance());
        Intent intent = new Intent(context, GcmIntentJobService.class);
        intent.putExtra("Bundle:Parcelable:Extras", (Parcelable) compatBundleForServer.getBundle());
        GcmIntentJobService.enqueueWork(context, intent);
    }

    private static void startGCMServiceWithWakefulService(Context context, Bundle bundle) {
        startWakefulService(context, new Intent().replaceExtras((Bundle) setCompatBundleForServer(bundle, new BundleCompatBundle()).getBundle()).setComponent(new ComponentName(context.getPackageName(), GcmIntentService.class.getName())));
    }

    private static BundleCompat setCompatBundleForServer(Bundle bundle, BundleCompat bundleCompat) {
        bundleCompat.putString("json_payload", NotificationBundleProcessor.bundleAsJSONObject(bundle).toString());
        bundleCompat.putLong(AvidJSONUtil.KEY_TIMESTAMP, Long.valueOf(System.currentTimeMillis() / 1000));
        return bundleCompat;
    }
}
