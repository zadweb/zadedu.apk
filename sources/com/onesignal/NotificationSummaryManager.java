package com.onesignal;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.onesignal.OneSignal;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class NotificationSummaryManager {
    static void updatePossibleDependentSummaryOnDismiss(Context context, SQLiteDatabase sQLiteDatabase, int i) {
        Cursor query = sQLiteDatabase.query("notification", new String[]{"group_id"}, "android_notification_id = " + i, null, null, null, null);
        if (query.moveToFirst()) {
            String string = query.getString(query.getColumnIndex("group_id"));
            query.close();
            if (string != null) {
                updateSummaryNotificationAfterChildRemoved(context, sQLiteDatabase, string, true);
                return;
            }
            return;
        }
        query.close();
    }

    static void updateSummaryNotificationAfterChildRemoved(Context context, SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        try {
            Cursor internalUpdateSummaryNotificationAfterChildRemoved = internalUpdateSummaryNotificationAfterChildRemoved(context, sQLiteDatabase, str, z);
            if (internalUpdateSummaryNotificationAfterChildRemoved != null && !internalUpdateSummaryNotificationAfterChildRemoved.isClosed()) {
                internalUpdateSummaryNotificationAfterChildRemoved.close();
            }
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error running updateSummaryNotificationAfterChildRemoved!", th);
        }
    }

    private static Cursor internalUpdateSummaryNotificationAfterChildRemoved(Context context, SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        Cursor query = sQLiteDatabase.query("notification", new String[]{"android_notification_id", "created_time"}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{str}, null, null, "_id DESC");
        int count = query.getCount();
        if (count == 0) {
            query.close();
            Integer summaryNotificationId = getSummaryNotificationId(sQLiteDatabase, str);
            if (summaryNotificationId == null) {
                return query;
            }
            OneSignalNotificationManager.getNotificationManager(context).cancel(summaryNotificationId.intValue());
            ContentValues contentValues = new ContentValues();
            contentValues.put(z ? "dismissed" : "opened", (Integer) 1);
            sQLiteDatabase.update("notification", contentValues, "android_notification_id = " + summaryNotificationId, null);
            return query;
        } else if (count == 1) {
            query.close();
            if (getSummaryNotificationId(sQLiteDatabase, str) == null) {
                return query;
            }
            restoreSummary(context, str);
            return query;
        } else {
            try {
                query.moveToFirst();
                Long valueOf = Long.valueOf(query.getLong(query.getColumnIndex("created_time")));
                query.close();
                if (getSummaryNotificationId(sQLiteDatabase, str) == null) {
                    return query;
                }
                NotificationGenerationJob notificationGenerationJob = new NotificationGenerationJob(context);
                notificationGenerationJob.restoring = true;
                notificationGenerationJob.shownTimeStamp = valueOf;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("grp", str);
                notificationGenerationJob.jsonPayload = jSONObject;
                GenerateNotification.updateSummaryNotification(notificationGenerationJob);
                return query;
            } catch (JSONException unused) {
            }
        }
    }

    private static void restoreSummary(Context context, String str) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            cursor = OneSignalDbHelper.getInstance(context).getSQLiteDatabaseWithRetries().query("notification", NotificationRestorer.COLUMNS_FOR_RESTORE, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", strArr, null, null, null);
            NotificationRestorer.showNotificationsFromCursor(context, cursor, 0);
            if (cursor == null || cursor.isClosed()) {
                return;
            }
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
        cursor.close();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r9v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARNING: Unknown variable types count: 1 */
    static Integer getSummaryNotificationId(SQLiteDatabase sQLiteDatabase, String str) {
        Integer num;
        Throwable th;
        ?? r9 = 0;
        try {
            Cursor query = sQLiteDatabase.query("notification", new String[]{"android_notification_id"}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 1", new String[]{str}, null, null, null);
            try {
                if (!query.moveToFirst()) {
                    query.close();
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                    return r9;
                }
                Integer valueOf = Integer.valueOf(query.getInt(query.getColumnIndex("android_notification_id")));
                query.close();
                if (query == null || query.isClosed()) {
                    return valueOf;
                }
                query.close();
                return valueOf;
            } catch (Throwable th2) {
                th = th2;
                r9 = query;
                num = r9;
                try {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level, "Error getting android notification id for summary notification group: " + str, th);
                    return num;
                } finally {
                    if (r9 != 0 && !r9.isClosed()) {
                        r9.close();
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            num = r9;
            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.ERROR;
            OneSignal.Log(log_level2, "Error getting android notification id for summary notification group: " + str, th);
            return num;
        }
    }

    static void clearNotificationOnSummaryClick(Context context, SQLiteDatabase sQLiteDatabase, String str) {
        Integer summaryNotificationId = getSummaryNotificationId(sQLiteDatabase, str);
        boolean equals = str.equals(OneSignalNotificationManager.getGrouplessSummaryKey());
        NotificationManager notificationManager = OneSignalNotificationManager.getNotificationManager(context);
        Integer mostRecentNotifIdFromGroup = OneSignalNotificationManager.getMostRecentNotifIdFromGroup(sQLiteDatabase, str, equals);
        if (mostRecentNotifIdFromGroup == null) {
            return;
        }
        if (OneSignal.getClearGroupSummaryClick()) {
            if (equals) {
                summaryNotificationId = Integer.valueOf(OneSignalNotificationManager.getGrouplessSummaryId());
            }
            if (summaryNotificationId != null) {
                notificationManager.cancel(summaryNotificationId.intValue());
                return;
            }
            return;
        }
        OneSignal.cancelNotification(mostRecentNotifIdFromGroup.intValue());
    }
}
