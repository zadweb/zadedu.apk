package com.tappx.a;

import android.view.MotionEvent;

/* access modifiers changed from: package-private */
public class o3 {

    /* renamed from: a  reason: collision with root package name */
    private a f775a;

    public interface a {
        void a();
    }

    public void a(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            a aVar = this.f775a;
            if (aVar != null) {
                aVar.a();
            } else {
                j4.a("No listener, click ignored");
            }
        }
    }

    public void a(a aVar) {
        this.f775a = aVar;
    }
}
