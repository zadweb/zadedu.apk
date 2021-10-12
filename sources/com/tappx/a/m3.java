package com.tappx.a;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import com.google.android.gms.common.ConnectionResult;
import java.util.Random;

public class m3 {

    /* renamed from: a  reason: collision with root package name */
    private static final k3[] f756a = {k3.FROM_LEFT, k3.FROM_RIGHT, k3.FROM_LEFT_BOUNCE, k3.FROM_RIGHT_BOUNCE};
    private static final Random b = new Random();

    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f757a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            int[] iArr = new int[k3.values().length];
            f757a = iArr;
            iArr[k3.FROM_LEFT.ordinal()] = 1;
            f757a[k3.FROM_LEFT_BOUNCE.ordinal()] = 2;
            f757a[k3.FROM_RIGHT.ordinal()] = 3;
            try {
                f757a[k3.FROM_RIGHT_BOUNCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static Animation a(k3 k3Var) {
        if (k3Var == k3.RANDOM) {
            k3Var = a();
        }
        int i = a.f757a[k3Var.ordinal()];
        if (i == 1) {
            return a(new AccelerateInterpolator(), 800);
        }
        if (i == 2) {
            return a(new a(), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        }
        if (i == 3) {
            return b(new AccelerateInterpolator(), 800);
        }
        if (i != 4) {
            return null;
        }
        return b(new a(), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
    }

    private static Animation b(Interpolator interpolator, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 1.0f, 2, 0.0f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setInterpolator(interpolator);
        return translateAnimation;
    }

    static k3 a() {
        k3[] k3VarArr = f756a;
        return k3VarArr[b.nextInt(k3VarArr.length)];
    }

    private static Animation a(Interpolator interpolator, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, -1.0f, 2, 0.0f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setInterpolator(interpolator);
        return translateAnimation;
    }
}
