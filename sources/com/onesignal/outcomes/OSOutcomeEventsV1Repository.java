package com.onesignal.outcomes;

import com.onesignal.OSLogger;
import com.onesignal.OneSignalApiResponseHandler;
import com.onesignal.OutcomeEvent;
import com.onesignal.influence.model.OSInfluenceType;
import com.onesignal.outcomes.domain.OutcomeEventsService;
import com.onesignal.outcomes.model.OSOutcomeEventParams;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class OSOutcomeEventsV1Repository extends OSOutcomeEventsRepository {
    OSOutcomeEventsV1Repository(OSLogger oSLogger, OSOutcomeEventsCache oSOutcomeEventsCache, OutcomeEventsService outcomeEventsService) {
        super(oSLogger, oSOutcomeEventsCache, outcomeEventsService);
    }

    /* renamed from: com.onesignal.outcomes.OSOutcomeEventsV1Repository$1  reason: invalid class name */
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

    @Override // com.onesignal.outcomes.domain.OSOutcomeEventsRepository
    public void requestMeasureOutcomeEvent(String str, int i, OSOutcomeEventParams oSOutcomeEventParams, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        OutcomeEvent fromOutcomeEventParamsV2toOutcomeEventV1 = OutcomeEvent.fromOutcomeEventParamsV2toOutcomeEventV1(oSOutcomeEventParams);
        int i2 = AnonymousClass1.$SwitchMap$com$onesignal$influence$model$OSInfluenceType[fromOutcomeEventParamsV2toOutcomeEventV1.getSession().ordinal()];
        if (i2 == 1) {
            requestMeasureDirectOutcomeEvent(str, i, fromOutcomeEventParamsV2toOutcomeEventV1, oneSignalApiResponseHandler);
        } else if (i2 == 2) {
            requestMeasureIndirectOutcomeEvent(str, i, fromOutcomeEventParamsV2toOutcomeEventV1, oneSignalApiResponseHandler);
        } else if (i2 == 3) {
            requestMeasureUnattributedOutcomeEvent(str, i, fromOutcomeEventParamsV2toOutcomeEventV1, oneSignalApiResponseHandler);
        }
    }

    private void requestMeasureDirectOutcomeEvent(String str, int i, OutcomeEvent outcomeEvent, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        try {
            JSONObject jSONObjectForMeasure = outcomeEvent.toJSONObjectForMeasure();
            jSONObjectForMeasure.put("app_id", str);
            jSONObjectForMeasure.put("device_type", i);
            jSONObjectForMeasure.put("direct", true);
            this.outcomeEventsService.sendOutcomeEvent(jSONObjectForMeasure, oneSignalApiResponseHandler);
        } catch (JSONException e) {
            this.logger.error("Generating direct outcome:JSON Failed.", e);
        }
    }

    private void requestMeasureIndirectOutcomeEvent(String str, int i, OutcomeEvent outcomeEvent, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        try {
            JSONObject jSONObjectForMeasure = outcomeEvent.toJSONObjectForMeasure();
            jSONObjectForMeasure.put("app_id", str);
            jSONObjectForMeasure.put("device_type", i);
            jSONObjectForMeasure.put("direct", false);
            this.outcomeEventsService.sendOutcomeEvent(jSONObjectForMeasure, oneSignalApiResponseHandler);
        } catch (JSONException e) {
            this.logger.error("Generating indirect outcome:JSON Failed.", e);
        }
    }

    private void requestMeasureUnattributedOutcomeEvent(String str, int i, OutcomeEvent outcomeEvent, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        try {
            JSONObject jSONObjectForMeasure = outcomeEvent.toJSONObjectForMeasure();
            jSONObjectForMeasure.put("app_id", str);
            jSONObjectForMeasure.put("device_type", i);
            this.outcomeEventsService.sendOutcomeEvent(jSONObjectForMeasure, oneSignalApiResponseHandler);
        } catch (JSONException e) {
            this.logger.error("Generating unattributed outcome:JSON Failed.", e);
        }
    }
}
