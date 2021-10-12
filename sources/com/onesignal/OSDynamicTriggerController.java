package com.onesignal;

import com.onesignal.OSTrigger;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

class OSDynamicTriggerController {
    static Date sessionLaunchTime = new Date();
    private final OSDynamicTriggerControllerObserver observer;
    private final ArrayList<String> scheduledMessages = new ArrayList<>();

    /* access modifiers changed from: package-private */
    public interface OSDynamicTriggerControllerObserver {
        void messageTriggerConditionChanged();
    }

    OSDynamicTriggerController(OSDynamicTriggerControllerObserver oSDynamicTriggerControllerObserver) {
        this.observer = oSDynamicTriggerControllerObserver;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0075 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0077  */
    public boolean dynamicTriggerShouldFire(final OSTrigger oSTrigger) {
        long j;
        long doubleValue;
        long j2;
        long j3;
        if (oSTrigger.value == null) {
            return false;
        }
        synchronized (this.scheduledMessages) {
            if (!(oSTrigger.value instanceof Number)) {
                return false;
            }
            int i = AnonymousClass2.$SwitchMap$com$onesignal$OSTrigger$OSTriggerKind[oSTrigger.kind.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    j = 0;
                } else if (OSInAppMessageController.getController().isInAppMessageShowing()) {
                    return false;
                } else {
                    Date date = OSInAppMessageController.getController().lastTimeInAppDismissed;
                    if (date == null) {
                        j = 999999;
                    } else {
                        j3 = new Date().getTime();
                        j2 = date.getTime();
                    }
                }
                doubleValue = (long) (((Number) oSTrigger.value).doubleValue() * 1000.0d);
                if (!evaluateTimeIntervalWithOperator((double) doubleValue, (double) j, oSTrigger.operatorType)) {
                    return true;
                }
                long j4 = doubleValue - j;
                if (j4 <= 0) {
                    return false;
                }
                if (this.scheduledMessages.contains(oSTrigger.triggerId)) {
                    return false;
                }
                OSDynamicTriggerTimer.scheduleTrigger(new TimerTask() {
                    /* class com.onesignal.OSDynamicTriggerController.AnonymousClass1 */

                    public void run() {
                        OSDynamicTriggerController.this.scheduledMessages.remove(oSTrigger.triggerId);
                        OSDynamicTriggerController.this.observer.messageTriggerConditionChanged();
                    }
                }, oSTrigger.triggerId, j4);
                this.scheduledMessages.add(oSTrigger.triggerId);
                return false;
            }
            j3 = new Date().getTime();
            j2 = sessionLaunchTime.getTime();
            j = j3 - j2;
            doubleValue = (long) (((Number) oSTrigger.value).doubleValue() * 1000.0d);
            if (!evaluateTimeIntervalWithOperator((double) doubleValue, (double) j, oSTrigger.operatorType)) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.onesignal.OSDynamicTriggerController$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$OSTrigger$OSTriggerKind;
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|5|6|7|8|9|10|11|12|13|14|15|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0028 */
        static {
            int[] iArr = new int[OSTrigger.OSTriggerOperator.values().length];
            $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator = iArr;
            try {
                iArr[OSTrigger.OSTriggerOperator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.LESS_THAN_OR_EQUAL_TO.ordinal()] = 2;
            $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.GREATER_THAN.ordinal()] = 3;
            $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.GREATER_THAN_OR_EQUAL_TO.ordinal()] = 4;
            $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.EQUAL_TO.ordinal()] = 5;
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[OSTrigger.OSTriggerOperator.NOT_EQUAL_TO.ordinal()] = 6;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[OSTrigger.OSTriggerKind.values().length];
            $SwitchMap$com$onesignal$OSTrigger$OSTriggerKind = iArr2;
            iArr2[OSTrigger.OSTriggerKind.SESSION_TIME.ordinal()] = 1;
            try {
                $SwitchMap$com$onesignal$OSTrigger$OSTriggerKind[OSTrigger.OSTriggerKind.TIME_SINCE_LAST_IN_APP.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static boolean evaluateTimeIntervalWithOperator(double d, double d2, OSTrigger.OSTriggerOperator oSTriggerOperator) {
        switch (AnonymousClass2.$SwitchMap$com$onesignal$OSTrigger$OSTriggerOperator[oSTriggerOperator.ordinal()]) {
            case 1:
                if (d2 < d) {
                    return true;
                }
                return false;
            case 2:
                if (d2 <= d || roughlyEqual(d, d2)) {
                    return true;
                }
                return false;
            case 3:
                if (d2 > d) {
                    return true;
                }
                return false;
            case 4:
                if (d2 >= d || roughlyEqual(d, d2)) {
                    return true;
                }
                return false;
            case 5:
                return roughlyEqual(d, d2);
            case 6:
                return !roughlyEqual(d, d2);
            default:
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.onesignalLog(log_level, "Attempted to apply an invalid operator on a time-based in-app-message trigger: " + oSTriggerOperator.toString());
                return false;
        }
    }

    private static boolean roughlyEqual(double d, double d2) {
        return Math.abs(d - d2) < 0.3d;
    }
}
