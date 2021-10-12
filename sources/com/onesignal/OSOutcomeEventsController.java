package com.onesignal;

import com.onesignal.OneSignal;
import com.onesignal.influence.model.OSInfluence;
import com.onesignal.influence.model.OSInfluenceChannel;
import com.onesignal.influence.model.OSInfluenceType;
import com.onesignal.outcomes.OSOutcomeEventsFactory;
import com.onesignal.outcomes.model.OSOutcomeEventParams;
import com.onesignal.outcomes.model.OSOutcomeSource;
import com.onesignal.outcomes.model.OSOutcomeSourceBody;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* access modifiers changed from: package-private */
public class OSOutcomeEventsController {
    private final OSSessionManager osSessionManager;
    private final OSOutcomeEventsFactory outcomeEventsFactory;
    private Set<String> unattributedUniqueOutcomeEventsSentOnSession;

    public OSOutcomeEventsController(OSSessionManager oSSessionManager, OSOutcomeEventsFactory oSOutcomeEventsFactory) {
        this.osSessionManager = oSSessionManager;
        this.outcomeEventsFactory = oSOutcomeEventsFactory;
        initUniqueOutcomeEventsSentSets();
    }

    private void initUniqueOutcomeEventsSentSets() {
        this.unattributedUniqueOutcomeEventsSentOnSession = OSUtils.newConcurrentSet();
        Set<String> unattributedUniqueOutcomeEventsSent = this.outcomeEventsFactory.getRepository().getUnattributedUniqueOutcomeEventsSent();
        if (unattributedUniqueOutcomeEventsSent != null) {
            this.unattributedUniqueOutcomeEventsSentOnSession = unattributedUniqueOutcomeEventsSent;
        }
    }

    /* access modifiers changed from: package-private */
    public void cleanOutcomes() {
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignal cleanOutcomes for session");
        this.unattributedUniqueOutcomeEventsSentOnSession = OSUtils.newConcurrentSet();
        saveUnattributedUniqueOutcomeEvents();
    }

    /* access modifiers changed from: package-private */
    public void sendSavedOutcomes() {
        new Thread(new Runnable() {
            /* class com.onesignal.OSOutcomeEventsController.AnonymousClass1 */

            public void run() {
                Thread.currentThread().setPriority(10);
                for (OSOutcomeEventParams oSOutcomeEventParams : OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().getSavedOutcomeEvents()) {
                    OSOutcomeEventsController.this.sendSavedOutcomeEvent(oSOutcomeEventParams);
                }
            }
        }, "OS_SEND_SAVED_OUTCOMES").start();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendSavedOutcomeEvent(final OSOutcomeEventParams oSOutcomeEventParams) {
        int deviceType = new OSUtils().getDeviceType();
        this.outcomeEventsFactory.getRepository().requestMeasureOutcomeEvent(OneSignal.appId, deviceType, oSOutcomeEventParams, new OneSignalApiResponseHandler() {
            /* class com.onesignal.OSOutcomeEventsController.AnonymousClass2 */

            @Override // com.onesignal.OneSignalApiResponseHandler
            public void onFailure(int i, String str, Throwable th) {
            }

            @Override // com.onesignal.OneSignalApiResponseHandler
            public void onSuccess(String str) {
                OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().removeEvent(oSOutcomeEventParams);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void sendClickActionOutcomes(List<OSInAppMessageOutcome> list) {
        for (OSInAppMessageOutcome oSInAppMessageOutcome : list) {
            String name = oSInAppMessageOutcome.getName();
            if (oSInAppMessageOutcome.isUnique()) {
                sendUniqueOutcomeEvent(name, null);
            } else if (oSInAppMessageOutcome.getWeight() > 0.0f) {
                sendOutcomeEventWithValue(name, oSInAppMessageOutcome.getWeight(), null);
            } else {
                sendOutcomeEvent(name, null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void sendUniqueOutcomeEvent(String str, OneSignal.OutcomeCallback outcomeCallback) {
        sendUniqueOutcomeEvent(str, this.osSessionManager.getInfluences(), outcomeCallback);
    }

    /* access modifiers changed from: package-private */
    public void sendOutcomeEvent(String str, OneSignal.OutcomeCallback outcomeCallback) {
        sendAndCreateOutcomeEvent(str, 0.0f, this.osSessionManager.getInfluences(), outcomeCallback);
    }

    /* access modifiers changed from: package-private */
    public void sendOutcomeEventWithValue(String str, float f, OneSignal.OutcomeCallback outcomeCallback) {
        sendAndCreateOutcomeEvent(str, f, this.osSessionManager.getInfluences(), outcomeCallback);
    }

    private void sendUniqueOutcomeEvent(String str, List<OSInfluence> list, OneSignal.OutcomeCallback outcomeCallback) {
        List<OSInfluence> removeDisabledInfluences = removeDisabledInfluences(list);
        if (removeDisabledInfluences.isEmpty()) {
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "Unique Outcome disabled for current session");
            return;
        }
        boolean z = false;
        Iterator<OSInfluence> it = removeDisabledInfluences.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().getInfluenceType().isAttributed()) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (z) {
            List<OSInfluence> uniqueIds = getUniqueIds(str, removeDisabledInfluences);
            if (uniqueIds == null) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                OneSignal.Log(log_level, "Measure endpoint will not send because unique outcome already sent for: \nSessionInfluences: " + removeDisabledInfluences.toString() + "\nOutcome name: " + str);
                if (outcomeCallback != null) {
                    outcomeCallback.onSuccess(null);
                    return;
                }
                return;
            }
            sendAndCreateOutcomeEvent(str, 0.0f, uniqueIds, outcomeCallback);
        } else if (this.unattributedUniqueOutcomeEventsSentOnSession.contains(str)) {
            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level2, "Measure endpoint will not send because unique outcome already sent for: \nSession: " + OSInfluenceType.UNATTRIBUTED + "\nOutcome name: " + str);
            if (outcomeCallback != null) {
                outcomeCallback.onSuccess(null);
            }
        } else {
            this.unattributedUniqueOutcomeEventsSentOnSession.add(str);
            sendAndCreateOutcomeEvent(str, 0.0f, removeDisabledInfluences, outcomeCallback);
        }
    }

    private void sendAndCreateOutcomeEvent(final String str, float f, List<OSInfluence> list, final OneSignal.OutcomeCallback outcomeCallback) {
        final long currentTimeMillis = System.currentTimeMillis() / 1000;
        int deviceType = new OSUtils().getDeviceType();
        String str2 = OneSignal.appId;
        OSOutcomeSourceBody oSOutcomeSourceBody = null;
        OSOutcomeSourceBody oSOutcomeSourceBody2 = null;
        boolean z = false;
        for (OSInfluence oSInfluence : list) {
            int i = AnonymousClass5.$SwitchMap$com$onesignal$influence$model$OSInfluenceType[oSInfluence.getInfluenceType().ordinal()];
            if (i == 1) {
                if (oSOutcomeSourceBody == null) {
                    oSOutcomeSourceBody = new OSOutcomeSourceBody();
                }
                oSOutcomeSourceBody = setSourceChannelIds(oSInfluence, oSOutcomeSourceBody);
            } else if (i == 2) {
                if (oSOutcomeSourceBody2 == null) {
                    oSOutcomeSourceBody2 = new OSOutcomeSourceBody();
                }
                oSOutcomeSourceBody2 = setSourceChannelIds(oSInfluence, oSOutcomeSourceBody2);
            } else if (i == 3) {
                z = true;
            } else if (i == 4) {
                OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "Outcomes disabled for channel: " + oSInfluence.getInfluenceChannel());
                return;
            }
        }
        if (oSOutcomeSourceBody == null && oSOutcomeSourceBody2 == null && !z) {
            OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "Outcomes disabled for all channels");
            return;
        }
        final OSOutcomeEventParams oSOutcomeEventParams = new OSOutcomeEventParams(str, new OSOutcomeSource(oSOutcomeSourceBody, oSOutcomeSourceBody2), f);
        this.outcomeEventsFactory.getRepository().requestMeasureOutcomeEvent(str2, deviceType, oSOutcomeEventParams, new OneSignalApiResponseHandler() {
            /* class com.onesignal.OSOutcomeEventsController.AnonymousClass3 */

            @Override // com.onesignal.OneSignalApiResponseHandler
            public void onSuccess(String str) {
                OSOutcomeEventsController.this.saveUniqueOutcome(oSOutcomeEventParams);
                OneSignal.OutcomeCallback outcomeCallback = outcomeCallback;
                if (outcomeCallback != null) {
                    outcomeCallback.onSuccess(OutcomeEvent.fromOutcomeEventParamsV2toOutcomeEventV1(oSOutcomeEventParams));
                }
            }

            @Override // com.onesignal.OneSignalApiResponseHandler
            public void onFailure(int i, String str, Throwable th) {
                new Thread(new Runnable() {
                    /* class com.onesignal.OSOutcomeEventsController.AnonymousClass3.AnonymousClass1 */

                    public void run() {
                        Thread.currentThread().setPriority(10);
                        oSOutcomeEventParams.setTimestamp(currentTimeMillis);
                        OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().saveOutcomeEvent(oSOutcomeEventParams);
                    }
                }, "OS_SAVE_OUTCOMES").start();
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.WARN;
                OneSignal.onesignalLog(log_level, "Sending outcome with name: " + str + " failed with status code: " + i + " and response: " + str + "\nOutcome event was cached and will be reattempted on app cold start");
                OneSignal.OutcomeCallback outcomeCallback = outcomeCallback;
                if (outcomeCallback != null) {
                    outcomeCallback.onSuccess(null);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.onesignal.OSOutcomeEventsController$5  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$influence$model$OSInfluenceChannel;
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$influence$model$OSInfluenceType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0043 */
        static {
            int[] iArr = new int[OSInfluenceChannel.values().length];
            $SwitchMap$com$onesignal$influence$model$OSInfluenceChannel = iArr;
            try {
                iArr[OSInfluenceChannel.IAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$onesignal$influence$model$OSInfluenceChannel[OSInfluenceChannel.NOTIFICATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[OSInfluenceType.values().length];
            $SwitchMap$com$onesignal$influence$model$OSInfluenceType = iArr2;
            iArr2[OSInfluenceType.DIRECT.ordinal()] = 1;
            $SwitchMap$com$onesignal$influence$model$OSInfluenceType[OSInfluenceType.INDIRECT.ordinal()] = 2;
            $SwitchMap$com$onesignal$influence$model$OSInfluenceType[OSInfluenceType.UNATTRIBUTED.ordinal()] = 3;
            $SwitchMap$com$onesignal$influence$model$OSInfluenceType[OSInfluenceType.DISABLED.ordinal()] = 4;
        }
    }

    private OSOutcomeSourceBody setSourceChannelIds(OSInfluence oSInfluence, OSOutcomeSourceBody oSOutcomeSourceBody) {
        int i = AnonymousClass5.$SwitchMap$com$onesignal$influence$model$OSInfluenceChannel[oSInfluence.getInfluenceChannel().ordinal()];
        if (i == 1) {
            oSOutcomeSourceBody.setInAppMessagesIds(oSInfluence.getIds());
        } else if (i == 2) {
            oSOutcomeSourceBody.setNotificationIds(oSInfluence.getIds());
        }
        return oSOutcomeSourceBody;
    }

    private List<OSInfluence> removeDisabledInfluences(List<OSInfluence> list) {
        ArrayList arrayList = new ArrayList(list);
        for (OSInfluence oSInfluence : list) {
            if (oSInfluence.getInfluenceType().isDisabled()) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                OneSignal.onesignalLog(log_level, "Outcomes disabled for channel: " + oSInfluence.getInfluenceChannel().toString());
                arrayList.remove(oSInfluence);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void saveUniqueOutcome(OSOutcomeEventParams oSOutcomeEventParams) {
        if (oSOutcomeEventParams.isUnattributed()) {
            saveUnattributedUniqueOutcomeEvents();
        } else {
            saveAttributedUniqueOutcomeNotifications(oSOutcomeEventParams);
        }
    }

    private void saveAttributedUniqueOutcomeNotifications(final OSOutcomeEventParams oSOutcomeEventParams) {
        new Thread(new Runnable() {
            /* class com.onesignal.OSOutcomeEventsController.AnonymousClass4 */

            public void run() {
                Thread.currentThread().setPriority(10);
                OSOutcomeEventsController.this.outcomeEventsFactory.getRepository().saveUniqueOutcomeNotifications(oSOutcomeEventParams);
            }
        }, "OS_SAVE_UNIQUE_OUTCOME_NOTIFICATIONS").start();
    }

    private void saveUnattributedUniqueOutcomeEvents() {
        this.outcomeEventsFactory.getRepository().saveUnattributedUniqueOutcomeEventsSent(this.unattributedUniqueOutcomeEventsSentOnSession);
    }

    private List<OSInfluence> getUniqueIds(String str, List<OSInfluence> list) {
        List<OSInfluence> notCachedUniqueOutcome = this.outcomeEventsFactory.getRepository().getNotCachedUniqueOutcome(str, list);
        if (notCachedUniqueOutcome.size() > 0) {
            return notCachedUniqueOutcome;
        }
        return null;
    }
}
