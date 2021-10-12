package com.onesignal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class RestoreJobService extends JobIntentService {
    @Override // com.onesignal.JobIntentService
    public /* bridge */ /* synthetic */ IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override // com.onesignal.JobIntentService
    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    @Override // com.onesignal.JobIntentService
    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    @Override // com.onesignal.JobIntentService
    public /* bridge */ /* synthetic */ int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    @Override // com.onesignal.JobIntentService
    public /* bridge */ /* synthetic */ boolean onStopCurrentWork() {
        return super.onStopCurrentWork();
    }

    /* access modifiers changed from: protected */
    @Override // com.onesignal.JobIntentService
    public final void onHandleWork(Intent intent) {
        Bundle extras;
        if (intent != null && (extras = intent.getExtras()) != null) {
            NotificationBundleProcessor.ProcessFromGCMIntentService(getApplicationContext(), new BundleCompatBundle(extras), null);
        }
    }
}
