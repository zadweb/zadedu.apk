package com.onesignal;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import java.util.Iterator;

public class OneSignalNotificationManager {
    static int getGrouplessSummaryId() {
        return -718463522;
    }

    static String getGrouplessSummaryKey() {
        return "os_group_undefined";
    }

    static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService("notification");
    }

    static Integer getGrouplessNotifsCount(Context context) {
        StatusBarNotification[] activeNotifications = getActiveNotifications(context);
        int i = 0;
        for (StatusBarNotification statusBarNotification : activeNotifications) {
            if (!NotificationCompat.isGroupSummary(statusBarNotification.getNotification()) && "os_group_undefined".equals(statusBarNotification.getNotification().getGroup())) {
                i++;
            }
        }
        return Integer.valueOf(i);
    }

    static StatusBarNotification[] getActiveNotifications(Context context) {
        StatusBarNotification[] statusBarNotificationArr = new StatusBarNotification[0];
        try {
            return getNotificationManager(context).getActiveNotifications();
        } catch (Throwable unused) {
            return statusBarNotificationArr;
        }
    }

    static ArrayList<StatusBarNotification> getActiveGrouplessNotifications(Context context) {
        ArrayList<StatusBarNotification> arrayList = new ArrayList<>();
        StatusBarNotification[] activeNotifications = getActiveNotifications(context);
        for (StatusBarNotification statusBarNotification : activeNotifications) {
            Notification notification = statusBarNotification.getNotification();
            boolean isGroupSummary = NotificationLimitManager.isGroupSummary(statusBarNotification);
            boolean z = notification.getGroup() == null || notification.getGroup().equals(getGrouplessSummaryKey());
            if (!isGroupSummary && z) {
                arrayList.add(statusBarNotification);
            }
        }
        return arrayList;
    }

    static void assignGrouplessNotifications(Context context, ArrayList<StatusBarNotification> arrayList) {
        Notification.Builder builder;
        Iterator<StatusBarNotification> it = arrayList.iterator();
        while (it.hasNext()) {
            StatusBarNotification next = it.next();
            if (Build.VERSION.SDK_INT >= 24) {
                builder = Notification.Builder.recoverBuilder(context, next.getNotification());
            } else {
                builder = new Notification.Builder(context);
            }
            NotificationManagerCompat.from(context).notify(next.getId(), builder.setGroup("os_group_undefined").build());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARNING: Unknown variable types count: 1 */
    static Integer getMostRecentNotifIdFromGroup(SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        Throwable th;
        Integer num;
        String str2 = z ? "group_id IS NULL" : "group_id = ?";
        ?? r0 = 0;
        try {
            Cursor query = sQLiteDatabase.query("notification", null, str2 + " AND dismissed = 0 AND opened = 0 AND is_summary = 0", z ? r0 : new String[]{str}, null, null, "created_time DESC", "1");
            try {
                if (!query.moveToFirst()) {
                    query.close();
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                    return r0;
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
                r0 = query;
                num = r0;
                try {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level, "Error getting android notification id for summary notification group: " + str, th);
                    return num;
                } finally {
                    if (r0 != 0 && !r0.isClosed()) {
                        r0.close();
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            num = r0;
            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.ERROR;
            OneSignal.Log(log_level2, "Error getting android notification id for summary notification group: " + str, th);
            return num;
        }
    }
}
