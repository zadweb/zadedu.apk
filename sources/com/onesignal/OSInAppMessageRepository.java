package com.onesignal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/* access modifiers changed from: package-private */
public class OSInAppMessageRepository {
    private final OneSignalDbHelper dbHelper;

    OSInAppMessageRepository(OneSignalDbHelper oneSignalDbHelper) {
        this.dbHelper = oneSignalDbHelper;
    }

    /* access modifiers changed from: package-private */
    public synchronized void saveInAppMessage(OSInAppMessage oSInAppMessage) {
        SQLiteDatabase sQLiteDatabaseWithRetries = this.dbHelper.getSQLiteDatabaseWithRetries();
        sQLiteDatabaseWithRetries.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("message_id", oSInAppMessage.messageId);
            contentValues.put("display_quantity", Integer.valueOf(oSInAppMessage.getRedisplayStats().getDisplayQuantity()));
            contentValues.put("last_display", Long.valueOf(oSInAppMessage.getRedisplayStats().getLastDisplayTime()));
            contentValues.put("click_ids", oSInAppMessage.getClickedClickIds().toString());
            contentValues.put("displayed_in_session", Boolean.valueOf(oSInAppMessage.isDisplayedInSession()));
            if (sQLiteDatabaseWithRetries.update("in_app_message", contentValues, "message_id = ?", new String[]{oSInAppMessage.messageId}) == 0) {
                sQLiteDatabaseWithRetries.insert("in_app_message", null, contentValues);
            }
            sQLiteDatabaseWithRetries.setTransactionSuccessful();
        } finally {
            try {
                sQLiteDatabaseWithRetries.endTransaction();
            } catch (SQLException e) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0078, code lost:
        if (r1.isClosed() == false) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007a, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008e, code lost:
        if (r1.isClosed() == false) goto L_0x007a;
     */
    public synchronized List<OSInAppMessage> getCachedInAppMessages() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = this.dbHelper.getSQLiteDatabaseWithRetries().query("in_app_message", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String string = cursor.getString(cursor.getColumnIndex("message_id"));
                    String string2 = cursor.getString(cursor.getColumnIndex("click_ids"));
                    int i = cursor.getInt(cursor.getColumnIndex("display_quantity"));
                    long j = cursor.getLong(cursor.getColumnIndex("last_display"));
                    boolean z = true;
                    if (cursor.getInt(cursor.getColumnIndex("displayed_in_session")) != 1) {
                        z = false;
                    }
                    arrayList.add(new OSInAppMessage(string, OSUtils.newStringSetFromJSONArray(new JSONArray(string2)), z, new OSInAppMessageRedisplayStats(i, j)));
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
            }
        } catch (JSONException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Generating JSONArray from iam click ids:JSON Failed.", e);
            if (0 != 0) {
            }
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0079, code lost:
        if (r13.isClosed() == false) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007b, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009f, code lost:
        if (r13.isClosed() == false) goto L_0x007b;
     */
    public synchronized void cleanCachedInAppMessages() {
        SQLiteDatabase sQLiteDatabaseWithRetries = this.dbHelper.getSQLiteDatabaseWithRetries();
        String[] strArr = {"message_id", "click_ids"};
        String[] strArr2 = {String.valueOf((System.currentTimeMillis() / 1000) - 15552000)};
        Set<String> newConcurrentSet = OSUtils.newConcurrentSet();
        Set<String> newConcurrentSet2 = OSUtils.newConcurrentSet();
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabaseWithRetries.query("in_app_message", strArr, "last_display < ?", strArr2, null, null, null);
            if (cursor != null) {
                if (cursor.getCount() != 0) {
                    if (cursor.moveToFirst()) {
                        do {
                            String string = cursor.getString(cursor.getColumnIndex("message_id"));
                            String string2 = cursor.getString(cursor.getColumnIndex("click_ids"));
                            newConcurrentSet.add(string);
                            newConcurrentSet2.addAll(OSUtils.newStringSetFromJSONArray(new JSONArray(string2)));
                        } while (cursor.moveToNext());
                    }
                    if (cursor != null) {
                    }
                    sQLiteDatabaseWithRetries.beginTransaction();
                    try {
                        sQLiteDatabaseWithRetries.delete("in_app_message", "last_display < ?", strArr2);
                        sQLiteDatabaseWithRetries.setTransactionSuccessful();
                        cleanInAppMessageIds(newConcurrentSet);
                        cleanInAppMessageClickedClickIds(newConcurrentSet2);
                        return;
                    } finally {
                        try {
                            sQLiteDatabaseWithRetries.endTransaction();
                        } catch (SQLException e) {
                            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", e);
                        }
                    }
                }
            }
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, "Attempted to clean 6 month old IAM data, but none exists!");
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            if (0 != 0) {
            }
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    private void cleanInAppMessageIds(Set<String> set) {
        if (set != null && set.size() > 0) {
            Set<String> stringSet = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_DISPLAYED_IAMS", null);
            Set<String> stringSet2 = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_IMPRESSIONED_IAMS", null);
            if (stringSet != null && stringSet.size() > 0) {
                stringSet.removeAll(set);
                OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_DISPLAYED_IAMS", stringSet);
            }
            if (stringSet2 != null && stringSet2.size() > 0) {
                stringSet2.removeAll(set);
                OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_IMPRESSIONED_IAMS", stringSet2);
            }
        }
    }

    private void cleanInAppMessageClickedClickIds(Set<String> set) {
        Set<String> stringSet;
        if (set != null && set.size() > 0 && (stringSet = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_CLICKED_CLICK_IDS_IAMS", null)) != null && stringSet.size() > 0) {
            stringSet.removeAll(set);
            OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_CLICKED_CLICK_IDS_IAMS", stringSet);
        }
    }
}
