package com.onesignal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import com.onesignal.OneSignal;
import org.json.JSONArray;
import org.json.JSONObject;

class NotificationOpenedProcessor {
    private static final String TAG = NotificationOpenedProcessor.class.getCanonicalName();

    NotificationOpenedProcessor() {
    }

    static void processFromContext(Context context, Intent intent) {
        if (isOneSignalIntent(intent)) {
            OneSignal.setAppContext(context);
            handleDismissFromActionButtonPress(context, intent);
            processIntent(context, intent);
        }
    }

    private static boolean isOneSignalIntent(Intent intent) {
        return intent.hasExtra("onesignalData") || intent.hasExtra("summary") || intent.hasExtra("androidNotificationId");
    }

    private static void handleDismissFromActionButtonPress(Context context, Intent intent) {
        if (intent.getBooleanExtra("action_button", false)) {
            NotificationManagerCompat.from(context).cancel(intent.getIntExtra("androidNotificationId", 0));
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0074 A[SYNTHETIC, Splitter:B:27:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    static void processIntent(Context context, Intent intent) {
        JSONObject jSONObject;
        JSONArray jSONArray;
        SQLiteDatabase sQLiteDatabaseWithRetries;
        String stringExtra;
        Throwable th;
        String stringExtra2 = intent.getStringExtra("summary");
        boolean booleanExtra = intent.getBooleanExtra("dismissed", false);
        SQLiteDatabase sQLiteDatabase = null;
        if (!booleanExtra) {
            try {
                jSONObject = new JSONObject(intent.getStringExtra("onesignalData"));
                try {
                    if (!handleIAMPreviewOpen(context, jSONObject)) {
                        jSONObject.put("androidNotificationId", intent.getIntExtra("androidNotificationId", 0));
                        intent.putExtra("onesignalData", jSONObject.toString());
                        jSONArray = NotificationBundleProcessor.newJsonArray(new JSONObject(intent.getStringExtra("onesignalData")));
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    jSONArray = null;
                    sQLiteDatabaseWithRetries = OneSignalDbHelper.getInstance(context).getSQLiteDatabaseWithRetries();
                    sQLiteDatabaseWithRetries.beginTransaction();
                    addChildNotifications(jSONArray, stringExtra2, sQLiteDatabaseWithRetries);
                    markNotificationsConsumed(context, intent, sQLiteDatabaseWithRetries, booleanExtra);
                    NotificationSummaryManager.updateSummaryNotificationAfterChildRemoved(context, sQLiteDatabaseWithRetries, stringExtra, booleanExtra);
                    sQLiteDatabaseWithRetries.setTransactionSuccessful();
                    if (sQLiteDatabaseWithRetries != null) {
                    }
                    if (!booleanExtra) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                jSONObject = null;
                th.printStackTrace();
                jSONArray = null;
                sQLiteDatabaseWithRetries = OneSignalDbHelper.getInstance(context).getSQLiteDatabaseWithRetries();
                sQLiteDatabaseWithRetries.beginTransaction();
                addChildNotifications(jSONArray, stringExtra2, sQLiteDatabaseWithRetries);
                markNotificationsConsumed(context, intent, sQLiteDatabaseWithRetries, booleanExtra);
                NotificationSummaryManager.updateSummaryNotificationAfterChildRemoved(context, sQLiteDatabaseWithRetries, stringExtra, booleanExtra);
                sQLiteDatabaseWithRetries.setTransactionSuccessful();
                if (sQLiteDatabaseWithRetries != null) {
                }
                if (!booleanExtra) {
                }
            }
        } else {
            jSONArray = null;
            jSONObject = null;
        }
        try {
            sQLiteDatabaseWithRetries = OneSignalDbHelper.getInstance(context).getSQLiteDatabaseWithRetries();
            sQLiteDatabaseWithRetries.beginTransaction();
            if (!booleanExtra && stringExtra2 != null) {
                addChildNotifications(jSONArray, stringExtra2, sQLiteDatabaseWithRetries);
            }
            markNotificationsConsumed(context, intent, sQLiteDatabaseWithRetries, booleanExtra);
            if (stringExtra2 == null && (stringExtra = intent.getStringExtra("grp")) != null) {
                NotificationSummaryManager.updateSummaryNotificationAfterChildRemoved(context, sQLiteDatabaseWithRetries, stringExtra, booleanExtra);
            }
            sQLiteDatabaseWithRetries.setTransactionSuccessful();
            if (sQLiteDatabaseWithRetries != null) {
                try {
                    sQLiteDatabaseWithRetries.endTransaction();
                } catch (Throwable th4) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", th4);
                }
            }
        } catch (Exception e) {
            try {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error processing notification open or dismiss record! ", e);
                if (0 != 0) {
                    sQLiteDatabase.endTransaction();
                }
            } catch (Throwable th5) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", th5);
            }
        }
        if (!booleanExtra) {
            OneSignal.handleNotificationOpen(context, jSONArray, intent.getBooleanExtra("from_alert", false), OSNotificationFormatHelper.getOSNotificationIdFromJson(jSONObject));
            return;
        }
        return;
        throw th;
    }

    static boolean handleIAMPreviewOpen(Context context, JSONObject jSONObject) {
        String inAppPreviewPushUUID = NotificationBundleProcessor.inAppPreviewPushUUID(jSONObject);
        if (inAppPreviewPushUUID == null) {
            return false;
        }
        OneSignal.startOrResumeApp(context);
        OSInAppMessageController.getController().displayPreviewMessage(inAppPreviewPushUUID);
        return true;
    }

    private static void addChildNotifications(JSONArray jSONArray, String str, SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("notification", new String[]{"full_data"}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{str}, null, null, null);
        if (query.getCount() > 1) {
            query.moveToFirst();
            do {
                try {
                    jSONArray.put(new JSONObject(query.getString(query.getColumnIndex("full_data"))));
                } catch (Throwable unused) {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level, "Could not parse JSON of sub notification in group: " + str);
                }
            } while (query.moveToNext());
        }
        query.close();
    }

    private static void markNotificationsConsumed(Context context, Intent intent, SQLiteDatabase sQLiteDatabase, boolean z) {
        String str;
        String stringExtra = intent.getStringExtra("summary");
        String[] strArr = null;
        if (stringExtra != null) {
            boolean equals = stringExtra.equals(OneSignalNotificationManager.getGrouplessSummaryKey());
            if (equals) {
                str = "group_id IS NULL";
            } else {
                strArr = new String[]{stringExtra};
                str = "group_id = ?";
            }
            if (!z && !OneSignal.getClearGroupSummaryClick()) {
                String valueOf = String.valueOf(OneSignalNotificationManager.getMostRecentNotifIdFromGroup(sQLiteDatabase, stringExtra, equals));
                str = str + " AND android_notification_id = ?";
                strArr = equals ? new String[]{valueOf} : new String[]{stringExtra, valueOf};
            }
        } else {
            str = "android_notification_id = " + intent.getIntExtra("androidNotificationId", 0);
        }
        clearStatusBarNotifications(context, sQLiteDatabase, stringExtra);
        sQLiteDatabase.update("notification", newContentValuesWithConsumed(intent), str, strArr);
        BadgeCountUpdater.update(sQLiteDatabase, context);
    }

    private static void clearStatusBarNotifications(Context context, SQLiteDatabase sQLiteDatabase, String str) {
        if (str != null) {
            NotificationSummaryManager.clearNotificationOnSummaryClick(context, sQLiteDatabase, str);
        } else if (Build.VERSION.SDK_INT >= 23 && OneSignalNotificationManager.getGrouplessNotifsCount(context).intValue() < 1) {
            OneSignalNotificationManager.getNotificationManager(context).cancel(OneSignalNotificationManager.getGrouplessSummaryId());
        }
    }

    private static ContentValues newContentValuesWithConsumed(Intent intent) {
        ContentValues contentValues = new ContentValues();
        if (intent.getBooleanExtra("dismissed", false)) {
            contentValues.put("dismissed", (Integer) 1);
        } else {
            contentValues.put("opened", (Integer) 1);
        }
        return contentValues;
    }
}
