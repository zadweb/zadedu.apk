package androidx.browser.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.ICustomTabsService;
import android.text.TextUtils;

public class CustomTabsClient {
    private final ICustomTabsService mService;
    private final ComponentName mServiceComponentName;

    CustomTabsClient(ICustomTabsService iCustomTabsService, ComponentName componentName) {
        this.mService = iCustomTabsService;
        this.mServiceComponentName = componentName;
    }

    public static boolean bindCustomTabsService(Context context, String str, CustomTabsServiceConnection customTabsServiceConnection) {
        Intent intent = new Intent("android.support.customtabs.action.CustomTabsService");
        if (!TextUtils.isEmpty(str)) {
            intent.setPackage(str);
        }
        return context.bindService(intent, customTabsServiceConnection, 33);
    }

    public boolean warmup(long j) {
        try {
            return this.mService.warmup(j);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public CustomTabsSession newSession(final CustomTabsCallback customTabsCallback) {
        AnonymousClass2 r0 = new ICustomTabsCallback.Stub() {
            /* class androidx.browser.customtabs.CustomTabsClient.AnonymousClass2 */
            private Handler mHandler = new Handler(Looper.getMainLooper());

            @Override // android.support.customtabs.ICustomTabsCallback
            public void onNavigationEvent(final int i, final Bundle bundle) {
                if (customTabsCallback != null) {
                    this.mHandler.post(new Runnable() {
                        /* class androidx.browser.customtabs.CustomTabsClient.AnonymousClass2.AnonymousClass1 */

                        public void run() {
                            customTabsCallback.onNavigationEvent(i, bundle);
                        }
                    });
                }
            }

            @Override // android.support.customtabs.ICustomTabsCallback
            public void extraCallback(final String str, final Bundle bundle) throws RemoteException {
                if (customTabsCallback != null) {
                    this.mHandler.post(new Runnable() {
                        /* class androidx.browser.customtabs.CustomTabsClient.AnonymousClass2.AnonymousClass2 */

                        public void run() {
                            customTabsCallback.extraCallback(str, bundle);
                        }
                    });
                }
            }

            @Override // android.support.customtabs.ICustomTabsCallback
            public void onMessageChannelReady(final Bundle bundle) throws RemoteException {
                if (customTabsCallback != null) {
                    this.mHandler.post(new Runnable() {
                        /* class androidx.browser.customtabs.CustomTabsClient.AnonymousClass2.AnonymousClass3 */

                        public void run() {
                            customTabsCallback.onMessageChannelReady(bundle);
                        }
                    });
                }
            }

            @Override // android.support.customtabs.ICustomTabsCallback
            public void onPostMessage(final String str, final Bundle bundle) throws RemoteException {
                if (customTabsCallback != null) {
                    this.mHandler.post(new Runnable() {
                        /* class androidx.browser.customtabs.CustomTabsClient.AnonymousClass2.AnonymousClass4 */

                        public void run() {
                            customTabsCallback.onPostMessage(str, bundle);
                        }
                    });
                }
            }

            @Override // android.support.customtabs.ICustomTabsCallback
            public void onRelationshipValidationResult(final int i, final Uri uri, final boolean z, final Bundle bundle) throws RemoteException {
                if (customTabsCallback != null) {
                    this.mHandler.post(new Runnable() {
                        /* class androidx.browser.customtabs.CustomTabsClient.AnonymousClass2.AnonymousClass5 */

                        public void run() {
                            customTabsCallback.onRelationshipValidationResult(i, uri, z, bundle);
                        }
                    });
                }
            }
        };
        try {
            if (!this.mService.newSession(r0)) {
                return null;
            }
            return new CustomTabsSession(this.mService, r0, this.mServiceComponentName);
        } catch (RemoteException unused) {
            return null;
        }
    }
}
