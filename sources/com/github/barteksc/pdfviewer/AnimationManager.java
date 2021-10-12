package com.github.barteksc.pdfviewer;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

/* access modifiers changed from: package-private */
public class AnimationManager {
    private ValueAnimator animation;
    private ValueAnimator flingAnimation;
    private PDFView pdfView;
    private Scroller scroller;

    public AnimationManager(PDFView pDFView) {
        this.pdfView = pDFView;
        this.scroller = new Scroller(pDFView.getContext(), null, true);
    }

    public void startXAnimation(float f, float f2) {
        stopAll();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        this.animation = ofFloat;
        ofFloat.setInterpolator(new DecelerateInterpolator());
        this.animation.addUpdateListener(new XAnimation());
        this.animation.setDuration(400L);
        this.animation.start();
    }

    public void startYAnimation(float f, float f2) {
        stopAll();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        this.animation = ofFloat;
        ofFloat.setInterpolator(new DecelerateInterpolator());
        this.animation.addUpdateListener(new YAnimation());
        this.animation.setDuration(400L);
        this.animation.start();
    }

    public void startZoomAnimation(float f, float f2, float f3, float f4) {
        stopAll();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f3, f4);
        this.animation = ofFloat;
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ZoomAnimation zoomAnimation = new ZoomAnimation(f, f2);
        this.animation.addUpdateListener(zoomAnimation);
        this.animation.addListener(zoomAnimation);
        this.animation.setDuration(400L);
        this.animation.start();
    }

    public void startFlingAnimation(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        stopAll();
        this.flingAnimation = ValueAnimator.ofFloat(0.0f, 1.0f);
        FlingAnimation flingAnimation2 = new FlingAnimation();
        this.flingAnimation.addUpdateListener(flingAnimation2);
        this.flingAnimation.addListener(flingAnimation2);
        this.scroller.fling(i, i2, i3, i4, i5, i6, i7, i8);
        this.flingAnimation.setDuration((long) this.scroller.getDuration());
        this.flingAnimation.start();
    }

    public void stopAll() {
        ValueAnimator valueAnimator = this.animation;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.animation = null;
        }
        stopFling();
    }

    public void stopFling() {
        if (this.flingAnimation != null) {
            this.scroller.forceFinished(true);
            this.flingAnimation.cancel();
            this.flingAnimation = null;
        }
    }

    /* access modifiers changed from: package-private */
    public class XAnimation implements ValueAnimator.AnimatorUpdateListener {
        XAnimation() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AnimationManager.this.pdfView.moveTo(((Float) valueAnimator.getAnimatedValue()).floatValue(), AnimationManager.this.pdfView.getCurrentYOffset());
        }
    }

    /* access modifiers changed from: package-private */
    public class YAnimation implements ValueAnimator.AnimatorUpdateListener {
        YAnimation() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AnimationManager.this.pdfView.moveTo(AnimationManager.this.pdfView.getCurrentXOffset(), ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* access modifiers changed from: package-private */
    public class ZoomAnimation implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        private final float centerX;
        private final float centerY;

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        public ZoomAnimation(float f, float f2) {
            this.centerX = f;
            this.centerY = f2;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AnimationManager.this.pdfView.zoomCenteredTo(((Float) valueAnimator.getAnimatedValue()).floatValue(), new PointF(this.centerX, this.centerY));
        }

        public void onAnimationEnd(Animator animator) {
            AnimationManager.this.pdfView.loadPages();
            AnimationManager.this.hideHandle();
        }
    }

    class FlingAnimation implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        FlingAnimation() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!AnimationManager.this.scroller.isFinished()) {
                AnimationManager.this.scroller.computeScrollOffset();
                AnimationManager.this.pdfView.moveTo((float) AnimationManager.this.scroller.getCurrX(), (float) AnimationManager.this.scroller.getCurrY());
                AnimationManager.this.pdfView.loadPageByOffset();
            }
        }

        public void onAnimationEnd(Animator animator) {
            AnimationManager.this.pdfView.loadPages();
            AnimationManager.this.hideHandle();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void hideHandle() {
        if (this.pdfView.getScrollHandle() != null) {
            this.pdfView.getScrollHandle().hideDelayed();
        }
    }
}
