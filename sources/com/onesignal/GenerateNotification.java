package com.onesignal;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.facebook.ads.AdError;
import com.google.android.gms.plus.PlusShare;
import com.integralads.avid.library.mopub.video.AvidVideoPlaybackListenerImpl;
import com.mopub.common.AdType;
import com.onesignal.AndroidSupportV4Compat;
import com.onesignal.OneSignal;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class GenerateNotification {
    private static Resources contextResources;
    private static Context currentContext;
    private static Class<?> notificationOpenedClass;
    private static boolean openerIsBroadcast;
    private static String packageName;

    private static int convertOSToAndroidPriority(int i) {
        if (i > 9) {
            return 2;
        }
        if (i > 7) {
            return 1;
        }
        if (i > 4) {
            return 0;
        }
        return i > 2 ? -1 : -2;
    }

    /* access modifiers changed from: private */
    public static class OneSignalNotificationBuilder {
        NotificationCompat.Builder compatBuilder;
        boolean hasLargeIcon;

        private OneSignalNotificationBuilder() {
        }
    }

    private static void setStatics(Context context) {
        currentContext = context;
        packageName = context.getPackageName();
        contextResources = currentContext.getResources();
        PackageManager packageManager = currentContext.getPackageManager();
        Intent intent = new Intent(currentContext, NotificationOpenedReceiver.class);
        intent.setPackage(currentContext.getPackageName());
        if (packageManager.queryBroadcastReceivers(intent, 0).size() > 0) {
            openerIsBroadcast = true;
            notificationOpenedClass = NotificationOpenedReceiver.class;
            return;
        }
        notificationOpenedClass = NotificationOpenedActivity.class;
    }

    static void fromJsonPayload(NotificationGenerationJob notificationGenerationJob) {
        setStatics(notificationGenerationJob.context);
        if (notificationGenerationJob.restoring || !notificationGenerationJob.showAsAlert || ActivityLifecycleHandler.curActivity == null) {
            showNotification(notificationGenerationJob);
        } else {
            showNotificationAsAlert(notificationGenerationJob.jsonPayload, ActivityLifecycleHandler.curActivity, notificationGenerationJob.getAndroidId().intValue());
        }
    }

    private static void showNotificationAsAlert(final JSONObject jSONObject, final Activity activity, final int i) {
        activity.runOnUiThread(new Runnable() {
            /* class com.onesignal.GenerateNotification.AnonymousClass1 */

            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(GenerateNotification.getTitle(jSONObject));
                builder.setMessage(jSONObject.optString("alert"));
                ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                GenerateNotification.addAlertButtons(activity, jSONObject, arrayList, arrayList2);
                final Intent newBaseIntent = GenerateNotification.getNewBaseIntent(i);
                newBaseIntent.putExtra("action_button", true);
                newBaseIntent.putExtra("from_alert", true);
                newBaseIntent.putExtra("onesignalData", jSONObject.toString());
                if (jSONObject.has("grp")) {
                    newBaseIntent.putExtra("grp", jSONObject.optString("grp"));
                }
                AnonymousClass1 r4 = new DialogInterface.OnClickListener() {
                    /* class com.onesignal.GenerateNotification.AnonymousClass1.AnonymousClass1 */

                    public void onClick(DialogInterface dialogInterface, int i) {
                        int i2 = i + 3;
                        if (arrayList2.size() > 1) {
                            try {
                                JSONObject jSONObject = new JSONObject(jSONObject.toString());
                                jSONObject.put("actionId", arrayList2.get(i2));
                                newBaseIntent.putExtra("onesignalData", jSONObject.toString());
                                NotificationOpenedProcessor.processIntent(activity, newBaseIntent);
                            } catch (Throwable unused) {
                            }
                        } else {
                            NotificationOpenedProcessor.processIntent(activity, newBaseIntent);
                        }
                    }
                };
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    /* class com.onesignal.GenerateNotification.AnonymousClass1.AnonymousClass2 */

                    public void onCancel(DialogInterface dialogInterface) {
                        NotificationOpenedProcessor.processIntent(activity, newBaseIntent);
                    }
                });
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i == 0) {
                        builder.setNeutralButton((CharSequence) arrayList.get(i), r4);
                    } else if (i == 1) {
                        builder.setNegativeButton((CharSequence) arrayList.get(i), r4);
                    } else if (i == 2) {
                        builder.setPositiveButton((CharSequence) arrayList.get(i), r4);
                    }
                }
                AlertDialog create = builder.create();
                create.setCanceledOnTouchOutside(false);
                create.show();
            }
        });
    }

    /* access modifiers changed from: private */
    public static CharSequence getTitle(JSONObject jSONObject) {
        String optString = jSONObject.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, null);
        if (optString != null) {
            return optString;
        }
        return currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo());
    }

    private static PendingIntent getNewActionPendingIntent(int i, Intent intent) {
        if (openerIsBroadcast) {
            return PendingIntent.getBroadcast(currentContext, i, intent, 134217728);
        }
        return PendingIntent.getActivity(currentContext, i, intent, 134217728);
    }

    /* access modifiers changed from: private */
    public static Intent getNewBaseIntent(int i) {
        Intent putExtra = new Intent(currentContext, notificationOpenedClass).putExtra("androidNotificationId", i);
        if (openerIsBroadcast) {
            return putExtra;
        }
        return putExtra.addFlags(603979776);
    }

    private static Intent getNewBaseDeleteIntent(int i) {
        Intent putExtra = new Intent(currentContext, notificationOpenedClass).putExtra("androidNotificationId", i).putExtra("dismissed", true);
        if (openerIsBroadcast) {
            return putExtra;
        }
        return putExtra.addFlags(402718720);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|5|(1:9)|10|11|(1:13)|14|15|(1:17)(1:18)|19|21|(1:23)|24|(1:26)|27|(2:29|30)|31|33) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x006c */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0072 A[Catch:{ all -> 0x0080 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x007b A[Catch:{ all -> 0x0080 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ac A[SYNTHETIC, Splitter:B:29:0x00ac] */
    private static OneSignalNotificationBuilder getBaseOneSignalNotificationBuilder(NotificationGenerationJob notificationGenerationJob) {
        NotificationCompat.Builder builder;
        JSONObject jSONObject = notificationGenerationJob.jsonPayload;
        OneSignalNotificationBuilder oneSignalNotificationBuilder = new OneSignalNotificationBuilder();
        try {
            builder = new NotificationCompat.Builder(currentContext, NotificationChannelManager.createNotificationChannel(notificationGenerationJob));
        } catch (Throwable unused) {
            builder = new NotificationCompat.Builder(currentContext);
        }
        String optString = jSONObject.optString("alert", null);
        builder.setAutoCancel(true).setSmallIcon(getSmallIconId(jSONObject)).setStyle(new NotificationCompat.BigTextStyle().bigText(optString)).setContentText(optString).setTicker(optString);
        if (Build.VERSION.SDK_INT < 24 || !jSONObject.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE).equals("")) {
            builder.setContentTitle(getTitle(jSONObject));
        }
        BigInteger accentColor = getAccentColor(jSONObject);
        if (accentColor != null) {
            builder.setColor(accentColor.intValue());
        }
        builder.setVisibility(!jSONObject.has("vis") ? Integer.parseInt(jSONObject.optString("vis")) : 1);
        Bitmap largeIcon = getLargeIcon(jSONObject);
        if (largeIcon != null) {
            oneSignalNotificationBuilder.hasLargeIcon = true;
            builder.setLargeIcon(largeIcon);
        }
        Bitmap bitmap = getBitmap(jSONObject.optString("bicon", null));
        if (bitmap != null) {
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setSummaryText(optString));
        }
        if (notificationGenerationJob.shownTimeStamp != null) {
            try {
                builder.setWhen(notificationGenerationJob.shownTimeStamp.longValue() * 1000);
            } catch (Throwable unused2) {
            }
        }
        setAlertnessOptions(jSONObject, builder);
        oneSignalNotificationBuilder.compatBuilder = builder;
        return oneSignalNotificationBuilder;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    private static void setAlertnessOptions(JSONObject jSONObject, NotificationCompat.Builder builder) {
        int convertOSToAndroidPriority = convertOSToAndroidPriority(jSONObject.optInt("pri", 6));
        builder.setPriority(convertOSToAndroidPriority);
        int i = 0;
        if (!(convertOSToAndroidPriority < 0)) {
            if (jSONObject.has("ledc") && jSONObject.optInt("led", 1) == 1) {
                try {
                    builder.setLights(new BigInteger(jSONObject.optString("ledc"), 16).intValue(), AdError.SERVER_ERROR_CODE, 5000);
                } catch (Throwable unused) {
                }
                if (OneSignal.getVibrate() && jSONObject.optInt("vib", 1) == 1) {
                    if (!jSONObject.has("vib_pt")) {
                        long[] parseVibrationPattern = OSUtils.parseVibrationPattern(jSONObject);
                        if (parseVibrationPattern != null) {
                            builder.setVibrate(parseVibrationPattern);
                        }
                    } else {
                        i |= 2;
                    }
                }
                if (isSoundEnabled(jSONObject)) {
                    Uri soundUri = OSUtils.getSoundUri(currentContext, jSONObject.optString("sound", null));
                    if (soundUri != null) {
                        builder.setSound(soundUri);
                    } else {
                        i |= 1;
                    }
                }
                builder.setDefaults(i);
            }
            i = 4;
            if (!jSONObject.has("vib_pt")) {
            }
            if (isSoundEnabled(jSONObject)) {
            }
            builder.setDefaults(i);
        }
    }

    private static void removeNotifyOptions(NotificationCompat.Builder builder) {
        builder.setOnlyAlertOnce(true).setDefaults(0).setSound(null).setVibrate(null).setTicker(null);
    }

    private static void showNotification(NotificationGenerationJob notificationGenerationJob) {
        Notification notification;
        int intValue = notificationGenerationJob.getAndroidId().intValue();
        JSONObject jSONObject = notificationGenerationJob.jsonPayload;
        String optString = jSONObject.optString("grp", null);
        ArrayList<StatusBarNotification> arrayList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 24) {
            arrayList = OneSignalNotificationManager.getActiveGrouplessNotifications(currentContext);
            if (optString == null && arrayList.size() >= 3) {
                optString = OneSignalNotificationManager.getGrouplessSummaryKey();
                OneSignalNotificationManager.assignGrouplessNotifications(currentContext, arrayList);
            }
        }
        OneSignalNotificationBuilder baseOneSignalNotificationBuilder = getBaseOneSignalNotificationBuilder(notificationGenerationJob);
        NotificationCompat.Builder builder = baseOneSignalNotificationBuilder.compatBuilder;
        addNotificationActionButtons(jSONObject, builder, intValue, null);
        try {
            addBackgroundImage(jSONObject, builder);
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not set background notification image!", th);
        }
        applyNotificationExtender(notificationGenerationJob, builder);
        if (notificationGenerationJob.restoring) {
            removeNotifyOptions(builder);
        }
        NotificationLimitManager.clearOldestOverLimit(currentContext, optString != null ? 2 : 1);
        if (optString != null) {
            createGenericPendingIntentsForGroup(builder, jSONObject, optString, intValue);
            notification = createSingleNotificationBeforeSummaryBuilder(notificationGenerationJob, builder);
            if (Build.VERSION.SDK_INT < 24 || !optString.equals(OneSignalNotificationManager.getGrouplessSummaryKey())) {
                createSummaryNotification(notificationGenerationJob, baseOneSignalNotificationBuilder);
            } else {
                createGrouplessSummaryNotification(notificationGenerationJob, arrayList.size() + 1);
            }
        } else {
            notification = createGenericPendingIntentsForNotif(builder, jSONObject, intValue);
        }
        if (optString == null || Build.VERSION.SDK_INT > 17) {
            addXiaomiSettings(baseOneSignalNotificationBuilder, notification);
            NotificationManagerCompat.from(currentContext).notify(intValue, notification);
        }
    }

    private static Notification createGenericPendingIntentsForNotif(NotificationCompat.Builder builder, JSONObject jSONObject, int i) {
        SecureRandom secureRandom = new SecureRandom();
        builder.setContentIntent(getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseIntent(i).putExtra("onesignalData", jSONObject.toString())));
        builder.setDeleteIntent(getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseDeleteIntent(i)));
        return builder.build();
    }

    private static void createGenericPendingIntentsForGroup(NotificationCompat.Builder builder, JSONObject jSONObject, String str, int i) {
        SecureRandom secureRandom = new SecureRandom();
        builder.setContentIntent(getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseIntent(i).putExtra("onesignalData", jSONObject.toString()).putExtra("grp", str)));
        builder.setDeleteIntent(getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseDeleteIntent(i).putExtra("grp", str)));
        builder.setGroup(str);
        try {
            builder.setGroupAlertBehavior(1);
        } catch (Throwable unused) {
        }
    }

    private static void applyNotificationExtender(NotificationGenerationJob notificationGenerationJob, NotificationCompat.Builder builder) {
        if (notificationGenerationJob.overrideSettings != null && notificationGenerationJob.overrideSettings.extender != null) {
            try {
                Field declaredField = NotificationCompat.Builder.class.getDeclaredField("mNotification");
                declaredField.setAccessible(true);
                Notification notification = (Notification) declaredField.get(builder);
                notificationGenerationJob.orgFlags = Integer.valueOf(notification.flags);
                notificationGenerationJob.orgSound = notification.sound;
                builder.extend(notificationGenerationJob.overrideSettings.extender);
                Notification notification2 = (Notification) declaredField.get(builder);
                Field declaredField2 = NotificationCompat.Builder.class.getDeclaredField("mContentText");
                declaredField2.setAccessible(true);
                Field declaredField3 = NotificationCompat.Builder.class.getDeclaredField("mContentTitle");
                declaredField3.setAccessible(true);
                notificationGenerationJob.overriddenBodyFromExtender = (CharSequence) declaredField2.get(builder);
                notificationGenerationJob.overriddenTitleFromExtender = (CharSequence) declaredField3.get(builder);
                if (!notificationGenerationJob.restoring) {
                    notificationGenerationJob.overriddenFlags = Integer.valueOf(notification2.flags);
                    notificationGenerationJob.overriddenSound = notification2.sound;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static Notification createSingleNotificationBeforeSummaryBuilder(NotificationGenerationJob notificationGenerationJob, NotificationCompat.Builder builder) {
        boolean z = Build.VERSION.SDK_INT > 17 && Build.VERSION.SDK_INT < 24 && !notificationGenerationJob.restoring;
        if (z && notificationGenerationJob.overriddenSound != null && !notificationGenerationJob.overriddenSound.equals(notificationGenerationJob.orgSound)) {
            builder.setSound(null);
        }
        Notification build = builder.build();
        if (z) {
            builder.setSound(notificationGenerationJob.overriddenSound);
        }
        return build;
    }

    private static void addXiaomiSettings(OneSignalNotificationBuilder oneSignalNotificationBuilder, Notification notification) {
        if (oneSignalNotificationBuilder.hasLargeIcon) {
            try {
                Object newInstance = Class.forName("android.app.MiuiNotification").newInstance();
                Field declaredField = newInstance.getClass().getDeclaredField("customizedIcon");
                declaredField.setAccessible(true);
                declaredField.set(newInstance, true);
                Field field = notification.getClass().getField("extraNotification");
                field.setAccessible(true);
                field.set(notification, newInstance);
            } catch (Throwable unused) {
            }
        }
    }

    static void updateSummaryNotification(NotificationGenerationJob notificationGenerationJob) {
        setStatics(notificationGenerationJob.context);
        createSummaryNotification(notificationGenerationJob, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x014f  */
    private static void createSummaryNotification(NotificationGenerationJob notificationGenerationJob, OneSignalNotificationBuilder oneSignalNotificationBuilder) {
        Throwable th;
        String str;
        Cursor query;
        PendingIntent pendingIntent;
        Integer num;
        JSONObject jSONObject;
        ArrayList<SpannableString> arrayList;
        PendingIntent newActionPendingIntent;
        Notification notification;
        String str2;
        String str3;
        String str4 = AvidVideoPlaybackListenerImpl.MESSAGE;
        String str5 = PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE;
        String str6 = "is_summary";
        boolean z = notificationGenerationJob.restoring;
        JSONObject jSONObject2 = notificationGenerationJob.jsonPayload;
        Cursor cursor = null;
        String optString = jSONObject2.optString("grp", null);
        SecureRandom secureRandom = new SecureRandom();
        PendingIntent newActionPendingIntent2 = getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseDeleteIntent(0).putExtra("summary", optString));
        OneSignalDbHelper instance = OneSignalDbHelper.getInstance(currentContext);
        try {
            SQLiteDatabase sQLiteDatabaseWithRetries = instance.getSQLiteDatabaseWithRetries();
            String[] strArr = {"android_notification_id", "full_data", str6, str5, str4};
            try {
                String[] strArr2 = {optString};
                if (!z) {
                    try {
                        if (notificationGenerationJob.getAndroidId().intValue() != -1) {
                            str = "group_id = ? AND dismissed = 0 AND opened = 0" + " AND android_notification_id <> " + notificationGenerationJob.getAndroidId();
                            query = sQLiteDatabaseWithRetries.query("notification", strArr, str, strArr2, null, null, "_id DESC");
                            String str7 = "";
                            if (!query.moveToFirst()) {
                                ArrayList arrayList2 = new ArrayList();
                                String str8 = null;
                                num = null;
                                while (true) {
                                    pendingIntent = newActionPendingIntent2;
                                    if (query.getInt(query.getColumnIndex(str6)) == 1) {
                                        num = Integer.valueOf(query.getInt(query.getColumnIndex("android_notification_id")));
                                        str3 = str4;
                                        str2 = str5;
                                    } else {
                                        String string = query.getString(query.getColumnIndex(str5));
                                        String str9 = string == null ? str7 : string + " ";
                                        str3 = str4;
                                        str2 = str5;
                                        SpannableString spannableString = new SpannableString(str9 + query.getString(query.getColumnIndex(str4)));
                                        if (str9.length() > 0) {
                                            spannableString.setSpan(new StyleSpan(1), 0, str9.length(), 0);
                                        }
                                        arrayList2.add(spannableString);
                                        if (str8 == null) {
                                            str8 = query.getString(query.getColumnIndex("full_data"));
                                        }
                                    }
                                    if (!query.moveToNext()) {
                                        break;
                                    }
                                    newActionPendingIntent2 = pendingIntent;
                                    str6 = str6;
                                    str4 = str3;
                                    str5 = str2;
                                }
                                if (!(z == 0 || str8 == null)) {
                                    try {
                                        jSONObject = new JSONObject(str8);
                                        arrayList = arrayList2;
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                arrayList = arrayList2;
                                jSONObject = jSONObject2;
                            } else {
                                pendingIntent = newActionPendingIntent2;
                                jSONObject = jSONObject2;
                                arrayList = null;
                                num = null;
                            }
                            if (query != null && !query.isClosed()) {
                                query.close();
                            }
                            if (num == null) {
                                num = Integer.valueOf(secureRandom.nextInt());
                                createSummaryIdDatabaseEntry(instance, optString, num.intValue());
                            }
                            newActionPendingIntent = getNewActionPendingIntent(secureRandom.nextInt(), createBaseSummaryIntent(num.intValue(), jSONObject, optString));
                            if (arrayList != null || ((!z || arrayList.size() <= 1) && (z != 0 || arrayList.size() <= 0))) {
                                NotificationCompat.Builder builder = oneSignalNotificationBuilder.compatBuilder;
                                builder.mActions.clear();
                                addNotificationActionButtons(jSONObject, builder, num.intValue(), optString);
                                builder.setContentIntent(newActionPendingIntent).setDeleteIntent(pendingIntent).setOnlyAlertOnce(z).setAutoCancel(false).setGroup(optString).setGroupSummary(true);
                                builder.setGroupAlertBehavior(1);
                                notification = builder.build();
                                addXiaomiSettings(oneSignalNotificationBuilder, notification);
                            } else {
                                int size = arrayList.size() + (!z);
                                String optString2 = jSONObject.optString("grp_msg", null);
                                CharSequence replace = optString2 == null ? size + " new messages" : optString2.replace("$[notif_count]", str7 + size);
                                NotificationCompat.Builder builder2 = getBaseOneSignalNotificationBuilder(notificationGenerationJob).compatBuilder;
                                if (z != 0) {
                                    removeNotifyOptions(builder2);
                                } else {
                                    if (notificationGenerationJob.overriddenSound != null) {
                                        builder2.setSound(notificationGenerationJob.overriddenSound);
                                    }
                                    if (notificationGenerationJob.overriddenFlags != null) {
                                        builder2.setDefaults(notificationGenerationJob.overriddenFlags.intValue());
                                    }
                                }
                                builder2.setContentIntent(newActionPendingIntent).setDeleteIntent(pendingIntent).setContentTitle(currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo())).setContentText(replace).setNumber(size).setSmallIcon(getDefaultSmallIconId()).setLargeIcon(getDefaultLargeIcon()).setOnlyAlertOnce(z).setAutoCancel(false).setGroup(optString).setGroupSummary(true);
                                try {
                                    builder2.setGroupAlertBehavior(1);
                                } catch (Throwable unused) {
                                }
                                if (z == 0) {
                                    builder2.setTicker(replace);
                                }
                                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                                if (z == 0) {
                                    String charSequence = notificationGenerationJob.getTitle() != null ? notificationGenerationJob.getTitle().toString() : null;
                                    if (charSequence != null) {
                                        str7 = charSequence + " ";
                                    }
                                    SpannableString spannableString2 = new SpannableString(str7 + notificationGenerationJob.getBody().toString());
                                    if (str7.length() > 0) {
                                        spannableString2.setSpan(new StyleSpan(1), 0, str7.length(), 0);
                                    }
                                    inboxStyle.addLine(spannableString2);
                                }
                                for (SpannableString spannableString3 : arrayList) {
                                    inboxStyle.addLine(spannableString3);
                                }
                                inboxStyle.setBigContentTitle(replace);
                                builder2.setStyle(inboxStyle);
                                notification = builder2.build();
                            }
                            NotificationManagerCompat.from(currentContext).notify(num.intValue(), notification);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = null;
                        cursor.close();
                        throw th;
                    }
                }
                str = "group_id = ? AND dismissed = 0 AND opened = 0";
                query = sQLiteDatabaseWithRetries.query("notification", strArr, str, strArr2, null, null, "_id DESC");
                try {
                    String str72 = "";
                    if (!query.moveToFirst()) {
                    }
                    query.close();
                    if (num == null) {
                    }
                    newActionPendingIntent = getNewActionPendingIntent(secureRandom.nextInt(), createBaseSummaryIntent(num.intValue(), jSONObject, optString));
                    if (arrayList != null) {
                    }
                    NotificationCompat.Builder builder3 = oneSignalNotificationBuilder.compatBuilder;
                    builder3.mActions.clear();
                    addNotificationActionButtons(jSONObject, builder3, num.intValue(), optString);
                    builder3.setContentIntent(newActionPendingIntent).setDeleteIntent(pendingIntent).setOnlyAlertOnce(z).setAutoCancel(false).setGroup(optString).setGroupSummary(true);
                    try {
                        builder3.setGroupAlertBehavior(1);
                    } catch (Throwable unused2) {
                    }
                    notification = builder3.build();
                    addXiaomiSettings(oneSignalNotificationBuilder, notification);
                    NotificationManagerCompat.from(currentContext).notify(num.intValue(), notification);
                } catch (Throwable th3) {
                    th = th3;
                    cursor = query;
                    cursor.close();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                cursor = null;
                cursor.close();
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    private static void createGrouplessSummaryNotification(NotificationGenerationJob notificationGenerationJob, int i) {
        JSONObject jSONObject = notificationGenerationJob.jsonPayload;
        SecureRandom secureRandom = new SecureRandom();
        String grouplessSummaryKey = OneSignalNotificationManager.getGrouplessSummaryKey();
        String str = i + " new messages";
        int grouplessSummaryId = OneSignalNotificationManager.getGrouplessSummaryId();
        PendingIntent newActionPendingIntent = getNewActionPendingIntent(secureRandom.nextInt(), createBaseSummaryIntent(grouplessSummaryId, jSONObject, grouplessSummaryKey));
        PendingIntent newActionPendingIntent2 = getNewActionPendingIntent(secureRandom.nextInt(), getNewBaseDeleteIntent(0).putExtra("summary", grouplessSummaryKey));
        NotificationCompat.Builder builder = getBaseOneSignalNotificationBuilder(notificationGenerationJob).compatBuilder;
        if (notificationGenerationJob.overriddenSound != null) {
            builder.setSound(notificationGenerationJob.overriddenSound);
        }
        if (notificationGenerationJob.overriddenFlags != null) {
            builder.setDefaults(notificationGenerationJob.overriddenFlags.intValue());
        }
        builder.setContentIntent(newActionPendingIntent).setDeleteIntent(newActionPendingIntent2).setContentTitle(currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo())).setContentText(str).setNumber(i).setSmallIcon(getDefaultSmallIconId()).setLargeIcon(getDefaultLargeIcon()).setOnlyAlertOnce(true).setAutoCancel(false).setGroup(grouplessSummaryKey).setGroupSummary(true);
        try {
            builder.setGroupAlertBehavior(1);
        } catch (Throwable unused) {
        }
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(str);
        builder.setStyle(inboxStyle);
        NotificationManagerCompat.from(currentContext).notify(grouplessSummaryId, builder.build());
    }

    private static Intent createBaseSummaryIntent(int i, JSONObject jSONObject, String str) {
        return getNewBaseIntent(i).putExtra("onesignalData", jSONObject.toString()).putExtra("summary", str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    private static void createSummaryIdDatabaseEntry(OneSignalDbHelper oneSignalDbHelper, String str, int i) {
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase sQLiteDatabaseWithRetries = oneSignalDbHelper.getSQLiteDatabaseWithRetries();
            try {
                sQLiteDatabaseWithRetries.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put("android_notification_id", Integer.valueOf(i));
                contentValues.put("group_id", str);
                contentValues.put("is_summary", (Integer) 1);
                sQLiteDatabaseWithRetries.insertOrThrow("notification", null, contentValues);
                sQLiteDatabaseWithRetries.setTransactionSuccessful();
                if (sQLiteDatabaseWithRetries != null) {
                    try {
                        sQLiteDatabaseWithRetries.endTransaction();
                        return;
                    } catch (Throwable th2) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", th2);
                        return;
                    }
                } else {
                    return;
                }
            } catch (Throwable th3) {
                th = th3;
                sQLiteDatabase = sQLiteDatabaseWithRetries;
                try {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error adding summary notification record! ", th);
                    if (sQLiteDatabase == null) {
                        sQLiteDatabase.endTransaction();
                        return;
                    }
                    return;
                } catch (Throwable th4) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", th4);
                }
            }
        } catch (Throwable th5) {
            th = th5;
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error adding summary notification record! ", th);
            if (sQLiteDatabase == null) {
            }
        }
        throw th;
    }

    private static void addBackgroundImage(JSONObject jSONObject, NotificationCompat.Builder builder) throws Throwable {
        JSONObject jSONObject2;
        Bitmap bitmap;
        String str;
        if (Build.VERSION.SDK_INT >= 16) {
            String optString = jSONObject.optString("bg_img", null);
            if (optString != null) {
                jSONObject2 = new JSONObject(optString);
                bitmap = getBitmap(jSONObject2.optString("img", null));
            } else {
                bitmap = null;
                jSONObject2 = null;
            }
            if (bitmap == null) {
                bitmap = getBitmapFromAssetsOrResourceName("onesignal_bgimage_default_image");
            }
            if (bitmap != null) {
                RemoteViews remoteViews = new RemoteViews(currentContext.getPackageName(), R.layout.onesignal_bgimage_notif_layout);
                remoteViews.setTextViewText(R.id.os_bgimage_notif_title, getTitle(jSONObject));
                remoteViews.setTextViewText(R.id.os_bgimage_notif_body, jSONObject.optString("alert"));
                setTextColor(remoteViews, jSONObject2, R.id.os_bgimage_notif_title, "tc", "onesignal_bgimage_notif_title_color");
                setTextColor(remoteViews, jSONObject2, R.id.os_bgimage_notif_body, "bc", "onesignal_bgimage_notif_body_color");
                if (jSONObject2 == null || !jSONObject2.has("img_align")) {
                    int identifier = contextResources.getIdentifier("onesignal_bgimage_notif_image_align", "string", packageName);
                    str = identifier != 0 ? contextResources.getString(identifier) : null;
                } else {
                    str = jSONObject2.getString("img_align");
                }
                if ("right".equals(str)) {
                    remoteViews.setViewPadding(R.id.os_bgimage_notif_bgimage_align_layout, -5000, 0, 0, 0);
                    remoteViews.setImageViewBitmap(R.id.os_bgimage_notif_bgimage_right_aligned, bitmap);
                    remoteViews.setViewVisibility(R.id.os_bgimage_notif_bgimage_right_aligned, 0);
                    remoteViews.setViewVisibility(R.id.os_bgimage_notif_bgimage, 2);
                } else {
                    remoteViews.setImageViewBitmap(R.id.os_bgimage_notif_bgimage, bitmap);
                }
                builder.setContent(remoteViews);
                builder.setStyle(null);
            }
        }
    }

    private static void setTextColor(RemoteViews remoteViews, JSONObject jSONObject, int i, String str, String str2) {
        Integer safeGetColorFromHex = safeGetColorFromHex(jSONObject, str);
        if (safeGetColorFromHex != null) {
            remoteViews.setTextColor(i, safeGetColorFromHex.intValue());
            return;
        }
        int identifier = contextResources.getIdentifier(str2, "color", packageName);
        if (identifier != 0) {
            remoteViews.setTextColor(i, AndroidSupportV4Compat.ContextCompat.getColor(currentContext, identifier));
        }
    }

    private static Integer safeGetColorFromHex(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        try {
            if (jSONObject.has(str)) {
                return Integer.valueOf(new BigInteger(jSONObject.optString(str), 16).intValue());
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Bitmap getLargeIcon(JSONObject jSONObject) {
        Bitmap bitmap = getBitmap(jSONObject.optString("licon"));
        if (bitmap == null) {
            bitmap = getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default");
        }
        if (bitmap == null) {
            return null;
        }
        return resizeBitmapForLargeIconArea(bitmap);
    }

    private static Bitmap getDefaultLargeIcon() {
        return resizeBitmapForLargeIconArea(getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default"));
    }

    private static Bitmap resizeBitmapForLargeIconArea(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            int dimension = (int) contextResources.getDimension(17104902);
            int dimension2 = (int) contextResources.getDimension(17104901);
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            if (width <= dimension2 && height <= dimension) {
                return bitmap;
            }
            if (height > width) {
                dimension2 = (int) (((float) dimension) * (((float) width) / ((float) height)));
            } else if (width > height) {
                dimension = (int) (((float) dimension2) * (((float) height) / ((float) width)));
            }
            return Bitmap.createScaledBitmap(bitmap, dimension2, dimension, true);
        } catch (Throwable unused) {
            return bitmap;
        }
    }

    private static Bitmap getBitmapFromAssetsOrResourceName(String str) {
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(currentContext.getAssets().open(str));
        } catch (Throwable unused) {
            bitmap = null;
        }
        if (bitmap != null) {
            return bitmap;
        }
        try {
            for (String str2 : Arrays.asList(".png", ".webp", ".jpg", ".gif", ".bmp")) {
                try {
                    bitmap = BitmapFactory.decodeStream(currentContext.getAssets().open(str + str2));
                    continue;
                } catch (Throwable unused2) {
                }
                if (bitmap != null) {
                    return bitmap;
                }
            }
            int resourceIcon = getResourceIcon(str);
            if (resourceIcon != 0) {
                return BitmapFactory.decodeResource(contextResources, resourceIcon);
            }
        } catch (Throwable unused3) {
        }
        return null;
    }

    private static Bitmap getBitmapFromURL(String str) {
        try {
            return BitmapFactory.decodeStream(new URL(str).openConnection().getInputStream());
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Could not download image!", th);
            return null;
        }
    }

    private static Bitmap getBitmap(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("http://") || trim.startsWith("https://")) {
            return getBitmapFromURL(trim);
        }
        return getBitmapFromAssetsOrResourceName(str);
    }

    private static int getResourceIcon(String str) {
        if (str == null) {
            return 0;
        }
        String trim = str.trim();
        if (!OSUtils.isValidResourceName(trim)) {
            return 0;
        }
        int drawableId = getDrawableId(trim);
        if (drawableId != 0) {
            return drawableId;
        }
        try {
            return R.drawable.class.getField(str).getInt(null);
        } catch (Throwable unused) {
            return 0;
        }
    }

    private static int getSmallIconId(JSONObject jSONObject) {
        int resourceIcon = getResourceIcon(jSONObject.optString("sicon", null));
        if (resourceIcon != 0) {
            return resourceIcon;
        }
        return getDefaultSmallIconId();
    }

    private static int getDefaultSmallIconId() {
        int drawableId = getDrawableId("ic_stat_onesignal_default");
        if (drawableId != 0) {
            return drawableId;
        }
        int drawableId2 = getDrawableId("corona_statusbar_icon_default");
        if (drawableId2 != 0) {
            return drawableId2;
        }
        int drawableId3 = getDrawableId("ic_os_notification_fallback_white_24dp");
        if (drawableId3 != 0) {
            return drawableId3;
        }
        return 17301598;
    }

    private static int getDrawableId(String str) {
        return contextResources.getIdentifier(str, "drawable", packageName);
    }

    private static boolean isSoundEnabled(JSONObject jSONObject) {
        String optString = jSONObject.optString("sound", null);
        if ("null".equals(optString) || "nil".equals(optString)) {
            return false;
        }
        return OneSignal.getSoundEnabled();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0015 */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f A[Catch:{ all -> 0x0025 }] */
    private static BigInteger getAccentColor(JSONObject jSONObject) {
        if (jSONObject.has("bgac")) {
            return new BigInteger(jSONObject.optString("bgac", null), 16);
        }
        try {
            String manifestMeta = OSUtils.getManifestMeta(currentContext, "com.onesignal.NotificationAccentColor.DEFAULT");
            if (manifestMeta != null) {
                return new BigInteger(manifestMeta, 16);
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    private static void addNotificationActionButtons(JSONObject jSONObject, NotificationCompat.Builder builder, int i, String str) {
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString(AdType.CUSTOM));
            if (jSONObject2.has("a")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("a");
                if (jSONObject3.has("actionButtons")) {
                    JSONArray jSONArray = jSONObject3.getJSONArray("actionButtons");
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                        JSONObject jSONObject4 = new JSONObject(jSONObject.toString());
                        Intent newBaseIntent = getNewBaseIntent(i);
                        newBaseIntent.setAction("" + i2);
                        newBaseIntent.putExtra("action_button", true);
                        jSONObject4.put("actionId", optJSONObject.optString("id"));
                        newBaseIntent.putExtra("onesignalData", jSONObject4.toString());
                        if (str != null) {
                            newBaseIntent.putExtra("summary", str);
                        } else if (jSONObject.has("grp")) {
                            newBaseIntent.putExtra("grp", jSONObject.optString("grp"));
                        }
                        builder.addAction(optJSONObject.has("icon") ? getResourceIcon(optJSONObject.optString("icon")) : 0, optJSONObject.optString("text"), getNewActionPendingIntent(i, newBaseIntent));
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void addAlertButtons(Context context, JSONObject jSONObject, List<String> list, List<String> list2) {
        try {
            addCustomAlertButtons(context, jSONObject, list, list2);
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Failed to parse JSON for custom buttons for alert dialog.", th);
        }
        if (list.size() == 0 || list.size() < 3) {
            list.add(OSUtils.getResourceString(context, "onesignal_in_app_alert_ok_button_text", "Ok"));
            list2.add("__DEFAULT__");
        }
    }

    private static void addCustomAlertButtons(Context context, JSONObject jSONObject, List<String> list, List<String> list2) throws JSONException {
        JSONObject jSONObject2 = new JSONObject(jSONObject.optString(AdType.CUSTOM));
        if (jSONObject2.has("a")) {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("a");
            if (jSONObject3.has("actionButtons")) {
                JSONArray optJSONArray = jSONObject3.optJSONArray("actionButtons");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject4 = optJSONArray.getJSONObject(i);
                    list.add(jSONObject4.optString("text"));
                    list2.add(jSONObject4.optString("id"));
                }
            }
        }
    }
}
