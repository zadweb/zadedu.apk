package com.onesignal.outcomes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.onesignal.OSLogger;
import com.onesignal.OSSharedPreferences;
import com.onesignal.OneSignalDb;
import com.onesignal.influence.model.OSInfluence;
import com.onesignal.influence.model.OSInfluenceChannel;
import com.onesignal.influence.model.OSInfluenceType;
import com.onesignal.outcomes.model.OSCachedUniqueOutcome;
import com.onesignal.outcomes.model.OSOutcomeEventParams;
import com.onesignal.outcomes.model.OSOutcomeSource;
import com.onesignal.outcomes.model.OSOutcomeSourceBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/* access modifiers changed from: package-private */
public class OSOutcomeEventsCache {
    private OneSignalDb dbHelper;
    private OSLogger logger;
    private OSSharedPreferences preferences;

    OSOutcomeEventsCache(OSLogger oSLogger, OneSignalDb oneSignalDb, OSSharedPreferences oSSharedPreferences) {
        this.logger = oSLogger;
        this.dbHelper = oneSignalDb;
        this.preferences = oSSharedPreferences;
    }

    /* access modifiers changed from: package-private */
    public boolean isOutcomesV2ServiceEnabled() {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        return oSSharedPreferences.getBool(oSSharedPreferences.getPreferencesName(), this.preferences.getOutcomesV2KeyName(), false);
    }

    /* access modifiers changed from: package-private */
    public Set<String> getUnattributedUniqueOutcomeEventsSentByChannel() {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        return oSSharedPreferences.getStringSet(oSSharedPreferences.getPreferencesName(), "PREFS_OS_UNATTRIBUTED_UNIQUE_OUTCOME_EVENTS_SENT", null);
    }

    /* access modifiers changed from: package-private */
    public void saveUnattributedUniqueOutcomeEventsSentByChannel(Set<String> set) {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        oSSharedPreferences.saveStringSet(oSSharedPreferences.getPreferencesName(), "PREFS_OS_UNATTRIBUTED_UNIQUE_OUTCOME_EVENTS_SENT", set);
    }

    /* access modifiers changed from: package-private */
    public synchronized void deleteOldOutcomeEvent(OSOutcomeEventParams oSOutcomeEventParams) {
        OSLogger oSLogger;
        String str;
        SQLiteDatabase sQLiteDatabaseWithRetries = this.dbHelper.getSQLiteDatabaseWithRetries();
        try {
            sQLiteDatabaseWithRetries.beginTransaction();
            sQLiteDatabaseWithRetries.delete("outcome", "timestamp = ?", new String[]{String.valueOf(oSOutcomeEventParams.getTimestamp())});
            sQLiteDatabaseWithRetries.setTransactionSuccessful();
            if (sQLiteDatabaseWithRetries != null) {
                try {
                    sQLiteDatabaseWithRetries.endTransaction();
                } catch (SQLiteException e) {
                    e = e;
                    oSLogger = this.logger;
                    str = "Error closing transaction! ";
                }
            }
        } catch (SQLiteException e2) {
            this.logger.error("Error deleting old outcome event records! ", e2);
            if (sQLiteDatabaseWithRetries != null) {
                try {
                    sQLiteDatabaseWithRetries.endTransaction();
                } catch (SQLiteException e3) {
                    e = e3;
                    oSLogger = this.logger;
                    str = "Error closing transaction! ";
                }
            }
        } catch (Throwable th) {
            if (sQLiteDatabaseWithRetries != null) {
                try {
                    sQLiteDatabaseWithRetries.endTransaction();
                } catch (SQLiteException e4) {
                    this.logger.error("Error closing transaction! ", e4);
                }
            }
            throw th;
        }
        oSLogger.error(str, e);
    }

    /* access modifiers changed from: package-private */
    public synchronized void saveOutcomeEvent(OSOutcomeEventParams oSOutcomeEventParams) {
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        OSInfluenceType oSInfluenceType = OSInfluenceType.UNATTRIBUTED;
        OSInfluenceType oSInfluenceType2 = OSInfluenceType.UNATTRIBUTED;
        if (oSOutcomeEventParams.getOutcomeSource() != null) {
            OSOutcomeSource outcomeSource = oSOutcomeEventParams.getOutcomeSource();
            if (outcomeSource.getDirectBody() != null) {
                OSOutcomeSourceBody directBody = outcomeSource.getDirectBody();
                if (directBody.getNotificationIds() != null && directBody.getNotificationIds().length() > 0) {
                    oSInfluenceType = OSInfluenceType.DIRECT;
                    jSONArray = outcomeSource.getDirectBody().getNotificationIds();
                }
                if (directBody.getInAppMessagesIds() != null && directBody.getInAppMessagesIds().length() > 0) {
                    oSInfluenceType2 = OSInfluenceType.DIRECT;
                    jSONArray2 = outcomeSource.getDirectBody().getInAppMessagesIds();
                }
            }
            if (outcomeSource.getIndirectBody() != null) {
                OSOutcomeSourceBody indirectBody = outcomeSource.getIndirectBody();
                if (indirectBody.getNotificationIds() != null && indirectBody.getNotificationIds().length() > 0) {
                    OSInfluenceType oSInfluenceType3 = OSInfluenceType.INDIRECT;
                    oSInfluenceType = oSInfluenceType3;
                    jSONArray = outcomeSource.getIndirectBody().getNotificationIds();
                }
                if (indirectBody.getInAppMessagesIds() != null && indirectBody.getInAppMessagesIds().length() > 0) {
                    oSInfluenceType2 = OSInfluenceType.INDIRECT;
                    jSONArray2 = outcomeSource.getIndirectBody().getInAppMessagesIds();
                }
            }
        }
        SQLiteDatabase sQLiteDatabaseWithRetries = this.dbHelper.getSQLiteDatabaseWithRetries();
        sQLiteDatabaseWithRetries.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("notification_ids", jSONArray.toString());
            contentValues.put("iam_ids", jSONArray2.toString());
            contentValues.put("notification_influence_type", oSInfluenceType.toString().toLowerCase());
            contentValues.put("iam_influence_type", oSInfluenceType2.toString().toLowerCase());
            contentValues.put("name", oSOutcomeEventParams.getOutcomeId());
            contentValues.put("weight", oSOutcomeEventParams.getWeight());
            contentValues.put(AvidJSONUtil.KEY_TIMESTAMP, Long.valueOf(oSOutcomeEventParams.getTimestamp()));
            sQLiteDatabaseWithRetries.insert("outcome", null, contentValues);
            sQLiteDatabaseWithRetries.setTransactionSuccessful();
        } finally {
            try {
                sQLiteDatabaseWithRetries.endTransaction();
            } catch (SQLException e) {
                this.logger.error("Error closing transaction! ", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized List<OSOutcomeEventParams> getAllEventsToSend() {
        ArrayList arrayList;
        Throwable th;
        OSOutcomeSource oSOutcomeSource;
        arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor query = this.dbHelper.getSQLiteDatabaseWithRetries().query("outcome", null, null, null, null, null, null);
            try {
                if (query.moveToFirst()) {
                    do {
                        OSInfluenceType fromString = OSInfluenceType.fromString(query.getString(query.getColumnIndex("notification_influence_type")));
                        OSInfluenceType fromString2 = OSInfluenceType.fromString(query.getString(query.getColumnIndex("iam_influence_type")));
                        String string = query.getString(query.getColumnIndex("notification_ids"));
                        if (string == null) {
                            string = "[]";
                        }
                        String string2 = query.getString(query.getColumnIndex("iam_ids"));
                        if (string2 == null) {
                            string2 = "[]";
                        }
                        String string3 = query.getString(query.getColumnIndex("name"));
                        float f = query.getFloat(query.getColumnIndex("weight"));
                        long j = query.getLong(query.getColumnIndex(AvidJSONUtil.KEY_TIMESTAMP));
                        try {
                            OSOutcomeSourceBody oSOutcomeSourceBody = new OSOutcomeSourceBody();
                            OSOutcomeSourceBody oSOutcomeSourceBody2 = new OSOutcomeSourceBody();
                            int i = AnonymousClass1.$SwitchMap$com$onesignal$influence$model$OSInfluenceType[fromString.ordinal()];
                            if (i == 1) {
                                oSOutcomeSourceBody.setNotificationIds(new JSONArray(string));
                                oSOutcomeSource = new OSOutcomeSource(oSOutcomeSourceBody, null);
                            } else if (i != 2) {
                                oSOutcomeSource = null;
                            } else {
                                oSOutcomeSourceBody2.setNotificationIds(new JSONArray(string));
                                oSOutcomeSource = new OSOutcomeSource(null, oSOutcomeSourceBody2);
                            }
                            int i2 = AnonymousClass1.$SwitchMap$com$onesignal$influence$model$OSInfluenceType[fromString2.ordinal()];
                            if (i2 == 1) {
                                oSOutcomeSourceBody.setInAppMessagesIds(new JSONArray(string2));
                                oSOutcomeSource = oSOutcomeSource == null ? new OSOutcomeSource(oSOutcomeSourceBody, null) : oSOutcomeSource.setDirectBody(oSOutcomeSourceBody);
                            } else if (i2 == 2) {
                                oSOutcomeSourceBody2.setInAppMessagesIds(new JSONArray(string2));
                                oSOutcomeSource = oSOutcomeSource == null ? new OSOutcomeSource(null, oSOutcomeSourceBody2) : oSOutcomeSource.setIndirectBody(oSOutcomeSourceBody2);
                            }
                            arrayList.add(new OSOutcomeEventParams(string3, oSOutcomeSource, f, j));
                        } catch (JSONException e) {
                            this.logger.error("Generating JSONArray from notifications ids outcome:JSON Failed.", e);
                        }
                    } while (query.moveToNext());
                }
                if (query != null && !query.isClosed()) {
                    query.close();
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor.close();
            throw th;
        }
        return arrayList;
    }

    /* renamed from: com.onesignal.outcomes.OSOutcomeEventsCache$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$influence$model$OSInfluenceType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            int[] iArr = new int[OSInfluenceType.values().length];
            $SwitchMap$com$onesignal$influence$model$OSInfluenceType = iArr;
            iArr[OSInfluenceType.DIRECT.ordinal()] = 1;
            $SwitchMap$com$onesignal$influence$model$OSInfluenceType[OSInfluenceType.INDIRECT.ordinal()] = 2;
            $SwitchMap$com$onesignal$influence$model$OSInfluenceType[OSInfluenceType.UNATTRIBUTED.ordinal()] = 3;
            try {
                $SwitchMap$com$onesignal$influence$model$OSInfluenceType[OSInfluenceType.DISABLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private void addIdToListFromChannel(List<OSCachedUniqueOutcome> list, JSONArray jSONArray, OSInfluenceChannel oSInfluenceChannel) {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    list.add(new OSCachedUniqueOutcome(jSONArray.getString(i), oSInfluenceChannel));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addIdsToListFromSource(List<OSCachedUniqueOutcome> list, OSOutcomeSourceBody oSOutcomeSourceBody) {
        if (oSOutcomeSourceBody != null) {
            JSONArray inAppMessagesIds = oSOutcomeSourceBody.getInAppMessagesIds();
            JSONArray notificationIds = oSOutcomeSourceBody.getNotificationIds();
            addIdToListFromChannel(list, inAppMessagesIds, OSInfluenceChannel.IAM);
            addIdToListFromChannel(list, notificationIds, OSInfluenceChannel.NOTIFICATION);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void saveUniqueOutcomeEventParams(OSOutcomeEventParams oSOutcomeEventParams) {
        OSLogger oSLogger = this.logger;
        oSLogger.debug("OneSignal saveUniqueOutcomeEventParams: " + oSOutcomeEventParams.toString());
        if (oSOutcomeEventParams.getOutcomeSource() != null) {
            String outcomeId = oSOutcomeEventParams.getOutcomeId();
            ArrayList arrayList = new ArrayList();
            OSOutcomeSourceBody directBody = oSOutcomeEventParams.getOutcomeSource().getDirectBody();
            OSOutcomeSourceBody indirectBody = oSOutcomeEventParams.getOutcomeSource().getIndirectBody();
            addIdsToListFromSource(arrayList, directBody);
            addIdsToListFromSource(arrayList, indirectBody);
            SQLiteDatabase sQLiteDatabaseWithRetries = this.dbHelper.getSQLiteDatabaseWithRetries();
            sQLiteDatabaseWithRetries.beginTransaction();
            try {
                for (OSCachedUniqueOutcome oSCachedUniqueOutcome : arrayList) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("channel_influence_id", oSCachedUniqueOutcome.getInfluenceId());
                    contentValues.put("channel_type", String.valueOf(oSCachedUniqueOutcome.getChannel()));
                    contentValues.put("name", outcomeId);
                    sQLiteDatabaseWithRetries.insert("cached_unique_outcome", null, contentValues);
                }
                sQLiteDatabaseWithRetries.setTransactionSuccessful();
            } finally {
                try {
                    sQLiteDatabaseWithRetries.endTransaction();
                } catch (SQLException e) {
                    this.logger.error("Error closing transaction! ", e);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009b, code lost:
        if (r3.isClosed() == false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009d, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ad, code lost:
        if (r3.isClosed() == false) goto L_0x009d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a9  */
    public synchronized List<OSInfluence> getNotCachedUniqueInfluencesForOutcome(String str, List<OSInfluence> list) {
        ArrayList arrayList;
        Throwable th;
        JSONException e;
        arrayList = new ArrayList();
        SQLiteDatabase sQLiteDatabaseWithRetries = this.dbHelper.getSQLiteDatabaseWithRetries();
        Cursor cursor = null;
        try {
            for (OSInfluence oSInfluence : list) {
                JSONArray jSONArray = new JSONArray();
                JSONArray ids = oSInfluence.getIds();
                if (ids != null) {
                    int i = 0;
                    Cursor cursor2 = cursor;
                    int i2 = 0;
                    while (i2 < ids.length()) {
                        try {
                            String string = ids.getString(i2);
                            OSInfluenceChannel influenceChannel = oSInfluence.getInfluenceChannel();
                            String[] strArr = new String[3];
                            strArr[i] = string;
                            strArr[1] = String.valueOf(influenceChannel);
                            strArr[2] = str;
                            cursor2 = sQLiteDatabaseWithRetries.query("cached_unique_outcome", new String[i], "channel_influence_id = ? AND channel_type = ? AND name = ?", strArr, null, null, null, "1");
                            if (cursor2.getCount() == 0) {
                                jSONArray.put(string);
                            }
                            i2++;
                            i = 0;
                        } catch (JSONException e2) {
                            e = e2;
                            cursor = cursor2;
                            try {
                                e.printStackTrace();
                                if (cursor != null) {
                                }
                                return arrayList;
                            } catch (Throwable th2) {
                                th = th2;
                                if (cursor != null && !cursor.isClosed()) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            cursor = cursor2;
                            cursor.close();
                            throw th;
                        }
                    }
                    if (jSONArray.length() > 0) {
                        OSInfluence copy = oSInfluence.copy();
                        copy.setIds(jSONArray);
                        arrayList.add(copy);
                    }
                    cursor = cursor2;
                }
            }
            if (cursor != null) {
            }
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            if (cursor != null) {
            }
            return arrayList;
        }
        return arrayList;
    }
}
