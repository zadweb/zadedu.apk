package com.onesignal;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class GcmIntentJobService extends JobIntentService {
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
    public void onHandleWork(Intent intent) {
        BundleCompat instance = BundleCompatFactory.getInstance();
        instance.setBundle(intent.getExtras().getParcelable("Bundle:Parcelable:Extras"));
        NotificationBundleProcessor.ProcessFromGCMIntentService(this, instance, null);
    }

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, GcmIntentJobService.class, 123890, intent, false);
    }
}
