package com.tappx.a;

public enum h4 {
    CLOSE("close"),
    EXPAND("expand") {
        /* access modifiers changed from: package-private */
        @Override // com.tappx.a.h4
        public boolean a(b4 b4Var) {
            return b4Var == b4.INLINE;
        }
    },
    USE_CUSTOM_CLOSE("usecustomclose"),
    OPEN("open") {
        /* access modifiers changed from: package-private */
        @Override // com.tappx.a.h4
        public boolean a(b4 b4Var) {
            return true;
        }
    },
    RESIZE("resize") {
        /* access modifiers changed from: package-private */
        @Override // com.tappx.a.h4
        public boolean a(b4 b4Var) {
            return true;
        }
    },
    SET_ORIENTATION_PROPERTIES("setOrientationProperties"),
    PLAY_VIDEO("playVideo") {
        /* access modifiers changed from: package-private */
        @Override // com.tappx.a.h4
        public boolean a(b4 b4Var) {
            return b4Var == b4.INLINE;
        }
    },
    STORE_PICTURE("storePicture") {
        /* access modifiers changed from: package-private */
        @Override // com.tappx.a.h4
        public boolean a(b4 b4Var) {
            return true;
        }
    },
    CREATE_CALENDAR_EVENT("createCalendarEvent") {
        /* access modifiers changed from: package-private */
        @Override // com.tappx.a.h4
        public boolean a(b4 b4Var) {
            return true;
        }
    },
    UNSPECIFIED("");
    

    /* renamed from: a  reason: collision with root package name */
    private final String f696a;

    static h4 a(String str) {
        h4[] values = values();
        for (h4 h4Var : values) {
            if (h4Var.f696a.equals(str)) {
                return h4Var;
            }
        }
        return UNSPECIFIED;
    }

    /* access modifiers changed from: package-private */
    public boolean a(b4 b4Var) {
        return false;
    }

    private h4(String str) {
        this.f696a = str;
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.f696a;
    }
}
