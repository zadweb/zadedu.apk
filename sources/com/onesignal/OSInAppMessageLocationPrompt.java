package com.onesignal;

import com.onesignal.OneSignal;

class OSInAppMessageLocationPrompt extends OSInAppMessagePrompt {
    /* access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessagePrompt
    public String getPromptKey() {
        return "location";
    }

    OSInAppMessageLocationPrompt() {
    }

    /* access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessagePrompt
    public void handlePrompt(OneSignal.OSPromptActionCompletionCallback oSPromptActionCompletionCallback) {
        OneSignal.promptLocation(oSPromptActionCompletionCallback, true);
    }
}
