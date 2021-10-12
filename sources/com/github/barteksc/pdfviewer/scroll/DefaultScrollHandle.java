package com.github.barteksc.pdfviewer.scroll;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.R;
import com.github.barteksc.pdfviewer.util.Util;
import com.mopub.mobileads.resource.DrawableConstants;

public class DefaultScrollHandle extends RelativeLayout implements ScrollHandle {
    protected Context context;
    private float currentPos;
    private Handler handler;
    private Runnable hidePageScrollerRunnable;
    private boolean inverted;
    private PDFView pdfView;
    private float relativeHandlerMiddle;
    protected TextView textView;

    public DefaultScrollHandle(Context context2) {
        this(context2, false);
    }

    public DefaultScrollHandle(Context context2, boolean z) {
        super(context2);
        this.relativeHandlerMiddle = 0.0f;
        this.handler = new Handler();
        this.hidePageScrollerRunnable = new Runnable() {
            /* class com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle.AnonymousClass1 */

            public void run() {
                DefaultScrollHandle.this.hide();
            }
        };
        this.context = context2;
        this.inverted = z;
        this.textView = new TextView(context2);
        setVisibility(4);
        setTextColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
        setTextSize(16);
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void setupLayout(PDFView pDFView) {
        Drawable drawable;
        int i;
        int i2 = 65;
        int i3 = 40;
        if (!pDFView.isSwipeVertical()) {
            if (this.inverted) {
                i = 10;
                drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_top);
            } else {
                i = 12;
                drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_bottom);
            }
            i2 = 40;
            i3 = 65;
        } else if (this.inverted) {
            i = 9;
            drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_left);
        } else {
            i = 11;
            drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_right);
        }
        if (Build.VERSION.SDK_INT < 16) {
            setBackgroundDrawable(drawable);
        } else {
            setBackground(drawable);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Util.getDP(this.context, i2), Util.getDP(this.context, i3));
        layoutParams.setMargins(0, 0, 0, 0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        addView(this.textView, layoutParams2);
        layoutParams.addRule(i);
        pDFView.addView(this, layoutParams);
        this.pdfView = pDFView;
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void destroyLayout() {
        this.pdfView.removeView(this);
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void setScroll(float f) {
        if (!shown()) {
            show();
        } else {
            this.handler.removeCallbacks(this.hidePageScrollerRunnable);
        }
        setPosition(((float) (this.pdfView.isSwipeVertical() ? this.pdfView.getHeight() : this.pdfView.getWidth())) * f);
    }

    private void setPosition(float f) {
        int i;
        if (!Float.isInfinite(f) && !Float.isNaN(f)) {
            if (this.pdfView.isSwipeVertical()) {
                i = this.pdfView.getHeight();
            } else {
                i = this.pdfView.getWidth();
            }
            float f2 = (float) i;
            float f3 = f - this.relativeHandlerMiddle;
            if (f3 < 0.0f) {
                f3 = 0.0f;
            } else if (f3 > f2 - ((float) Util.getDP(this.context, 40))) {
                f3 = f2 - ((float) Util.getDP(this.context, 40));
            }
            if (this.pdfView.isSwipeVertical()) {
                setY(f3);
            } else {
                setX(f3);
            }
            calculateMiddle();
            invalidate();
        }
    }

    private void calculateMiddle() {
        int i;
        float f;
        float f2;
        if (this.pdfView.isSwipeVertical()) {
            f2 = getY();
            f = (float) getHeight();
            i = this.pdfView.getHeight();
        } else {
            f2 = getX();
            f = (float) getWidth();
            i = this.pdfView.getWidth();
        }
        this.relativeHandlerMiddle = ((f2 + this.relativeHandlerMiddle) / ((float) i)) * f;
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void hideDelayed() {
        this.handler.postDelayed(this.hidePageScrollerRunnable, 1000);
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void setPageNum(int i) {
        String valueOf = String.valueOf(i);
        if (!this.textView.getText().equals(valueOf)) {
            this.textView.setText(valueOf);
        }
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public boolean shown() {
        return getVisibility() == 0;
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void show() {
        setVisibility(0);
    }

    @Override // com.github.barteksc.pdfviewer.scroll.ScrollHandle
    public void hide() {
        setVisibility(4);
    }

    public void setTextColor(int i) {
        this.textView.setTextColor(i);
    }

    public void setTextSize(int i) {
        this.textView.setTextSize(1, (float) i);
    }

    private boolean isPDFViewReady() {
        PDFView pDFView = this.pdfView;
        return pDFView != null && pDFView.getPageCount() > 0 && !this.pdfView.documentFitsView();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078  */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isPDFViewReady()) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        if (action != 5) {
                            if (action != 6) {
                                return super.onTouchEvent(motionEvent);
                            }
                        }
                    }
                }
                if (!this.pdfView.isSwipeVertical()) {
                    setPosition((motionEvent.getRawY() - this.currentPos) + this.relativeHandlerMiddle);
                    this.pdfView.setPositionOffset(this.relativeHandlerMiddle / ((float) getHeight()), false);
                } else {
                    setPosition((motionEvent.getRawX() - this.currentPos) + this.relativeHandlerMiddle);
                    this.pdfView.setPositionOffset(this.relativeHandlerMiddle / ((float) getWidth()), false);
                }
                return true;
            }
            hideDelayed();
            return true;
        }
        this.pdfView.stopFling();
        this.handler.removeCallbacks(this.hidePageScrollerRunnable);
        if (this.pdfView.isSwipeVertical()) {
            this.currentPos = motionEvent.getRawY() - getY();
        } else {
            this.currentPos = motionEvent.getRawX() - getX();
        }
        if (!this.pdfView.isSwipeVertical()) {
        }
        return true;
    }
}
