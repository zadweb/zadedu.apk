package com.appnext.base.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import com.appnext.base.b.d;
import com.appnext.base.services.a.c;

public class OperationService extends IntentService {
    public OperationService() {
        super(OperationService.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra(d.fg);
            Bundle bundleExtra = intent.getBundleExtra(c.eH);
            new b().a(getApplicationContext(), stringExtra, null, bundleExtra, (Intent) intent.clone(), null);
        } catch (Throwable unused) {
        }
    }
}
