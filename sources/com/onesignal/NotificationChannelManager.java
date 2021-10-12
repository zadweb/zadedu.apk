package com.onesignal;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.onesignal.OneSignal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationChannelManager {
    private static final Pattern hexPattern = Pattern.compile("^([A-Fa-f0-9]{8})$");

    private static int priorityToImportance(int i) {
        if (i > 9) {
            return 5;
        }
        if (i > 7) {
            return 4;
        }
        if (i > 5) {
            return 3;
        }
        if (i > 3) {
            return 2;
        }
        return i > 1 ? 1 : 0;
    }

    static String createNotificationChannel(NotificationGenerationJob notificationGenerationJob) {
        if (Build.VERSION.SDK_INT < 26) {
            return "fcm_fallback_notification_channel";
        }
        Context context = notificationGenerationJob.context;
        JSONObject jSONObject = notificationGenerationJob.jsonPayload;
        NotificationManager notificationManager = OneSignalNotificationManager.getNotificationManager(context);
        if (notificationGenerationJob.restoring) {
            return createRestoreChannel(notificationManager);
        }
        if (jSONObject.has("oth_chnl")) {
            String optString = jSONObject.optString("oth_chnl");
            if (notificationManager.getNotificationChannel(optString) != null) {
                return optString;
            }
        }
        if (!jSONObject.has("chnl")) {
            return createDefaultChannel(notificationManager);
        }
        try {
            return createChannel(context, notificationManager, jSONObject);
        } catch (JSONException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not create notification channel due to JSON payload error!", e);
            return "fcm_fallback_notification_channel";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0132  */
    private static String createChannel(Context context, NotificationManager notificationManager, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        NotificationChannel notificationChannel;
        long[] parseVibrationPattern;
        Object opt = jSONObject.opt("chnl");
        if (opt instanceof String) {
            jSONObject2 = new JSONObject((String) opt);
        } else {
            jSONObject2 = (JSONObject) opt;
        }
        String str = "fcm_fallback_notification_channel";
        String optString = jSONObject2.optString("id", str);
        if (!optString.equals("miscellaneous")) {
            str = optString;
        }
        if (jSONObject2.has("langs")) {
            JSONObject jSONObject4 = jSONObject2.getJSONObject("langs");
            String correctedLanguage = OSUtils.getCorrectedLanguage();
            if (jSONObject4.has(correctedLanguage)) {
                jSONObject3 = jSONObject4.optJSONObject(correctedLanguage);
                notificationChannel = new NotificationChannel(str, jSONObject3.optString("nm", "Miscellaneous"), priorityToImportance(jSONObject.optInt("pri", 6)));
                notificationChannel.setDescription(jSONObject3.optString("dscr", null));
                if (jSONObject2.has("grp_id")) {
                    String optString2 = jSONObject2.optString("grp_id");
                    notificationManager.createNotificationChannelGroup(new NotificationChannelGroup(optString2, jSONObject3.optString("grp_nm")));
                    notificationChannel.setGroup(optString2);
                }
                if (jSONObject.has("ledc")) {
                    String optString3 = jSONObject.optString("ledc");
                    if (!hexPattern.matcher(optString3).matches()) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "OneSignal LED Color Settings: ARGB Hex value incorrect format (E.g: FF9900FF)");
                        optString3 = "FFFFFFFF";
                    }
                    try {
                        notificationChannel.setLightColor(new BigInteger(optString3, 16).intValue());
                    } catch (Throwable th) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Couldn't convert ARGB Hex value to BigInteger:", th);
                    }
                }
                boolean z = true;
                notificationChannel.enableLights(jSONObject.optInt("led", 1) != 1);
                if (jSONObject.has("vib_pt") && (parseVibrationPattern = OSUtils.parseVibrationPattern(jSONObject)) != null) {
                    notificationChannel.setVibrationPattern(parseVibrationPattern);
                }
                notificationChannel.enableVibration(jSONObject.optInt("vib", 1) != 1);
                if (jSONObject.has("sound")) {
                    String optString4 = jSONObject.optString("sound", null);
                    Uri soundUri = OSUtils.getSoundUri(context, optString4);
                    if (soundUri != null) {
                        notificationChannel.setSound(soundUri, null);
                    } else if ("null".equals(optString4) || "nil".equals(optString4)) {
                        notificationChannel.setSound(null, null);
                    }
                }
                notificationChannel.setLockscreenVisibility(jSONObject.optInt("vis", 0));
                notificationChannel.setShowBadge(jSONObject.optInt("bdg", 1) != 1);
                if (jSONObject.optInt("bdnd", 0) != 1) {
                    z = false;
                }
                notificationChannel.setBypassDnd(z);
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.VERBOSE;
                OneSignal.onesignalLog(log_level, "Creating notification channel with channel:\n" + notificationChannel.toString());
                notificationManager.createNotificationChannel(notificationChannel);
                return str;
            }
        }
        jSONObject3 = jSONObject2;
        notificationChannel = new NotificationChannel(str, jSONObject3.optString("nm", "Miscellaneous"), priorityToImportance(jSONObject.optInt("pri", 6)));
        notificationChannel.setDescription(jSONObject3.optString("dscr", null));
        if (jSONObject2.has("grp_id")) {
        }
        if (jSONObject.has("ledc")) {
        }
        boolean z2 = true;
        notificationChannel.enableLights(jSONObject.optInt("led", 1) != 1);
        notificationChannel.setVibrationPattern(parseVibrationPattern);
        notificationChannel.enableVibration(jSONObject.optInt("vib", 1) != 1);
        if (jSONObject.has("sound")) {
        }
        notificationChannel.setLockscreenVisibility(jSONObject.optInt("vis", 0));
        notificationChannel.setShowBadge(jSONObject.optInt("bdg", 1) != 1);
        if (jSONObject.optInt("bdnd", 0) != 1) {
        }
        notificationChannel.setBypassDnd(z2);
        OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.VERBOSE;
        OneSignal.onesignalLog(log_level2, "Creating notification channel with channel:\n" + notificationChannel.toString());
        try {
            notificationManager.createNotificationChannel(notificationChannel);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String createDefaultChannel(NotificationManager notificationManager) {
        NotificationChannel notificationChannel = new NotificationChannel("fcm_fallback_notification_channel", "Miscellaneous", 3);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationManager.createNotificationChannel(notificationChannel);
        return "fcm_fallback_notification_channel";
    }

    private static String createRestoreChannel(NotificationManager notificationManager) {
        notificationManager.createNotificationChannel(new NotificationChannel("restored_OS_notifications", "Restored", 2));
        return "restored_OS_notifications";
    }

    static void processChannelList(Context context, JSONArray jSONArray) {
        if (Build.VERSION.SDK_INT >= 26 && jSONArray != null) {
            NotificationManager notificationManager = OneSignalNotificationManager.getNotificationManager(context);
            HashSet hashSet = new HashSet();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    hashSet.add(createChannel(context, notificationManager, jSONArray.getJSONObject(i)));
                } catch (JSONException e) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not create notification channel due to JSON payload error!", e);
                }
            }
            for (NotificationChannel notificationChannel : notificationManager.getNotificationChannels()) {
                String id = notificationChannel.getId();
                if (id.startsWith("OS_") && !hashSet.contains(id)) {
                    notificationManager.deleteNotificationChannel(id);
                }
            }
        }
    }
}
