package androidx.media2.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.CaptioningManager;
import android.widget.RelativeLayout;
import androidx.media2.widget.Cea708CCParser;
import androidx.media2.widget.ClosedCaptionWidget;
import androidx.media2.widget.SubtitleController;
import androidx.media2.widget.SubtitleTrack;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

class Cea708CaptionRenderer extends SubtitleController.Renderer {
    private Cea708CCWidget mCCWidget;
    private final Context mContext;

    Cea708CaptionRenderer(Context context) {
        this.mContext = context;
    }

    @Override // androidx.media2.widget.SubtitleController.Renderer
    public boolean supports(MediaFormat mediaFormat) {
        if (mediaFormat.containsKey("mime")) {
            return "text/cea-708".equals(mediaFormat.getString("mime"));
        }
        return false;
    }

    @Override // androidx.media2.widget.SubtitleController.Renderer
    public SubtitleTrack createTrack(MediaFormat mediaFormat) {
        if ("text/cea-708".equals(mediaFormat.getString("mime"))) {
            if (this.mCCWidget == null) {
                this.mCCWidget = new Cea708CCWidget(this, this.mContext);
            }
            return new Cea708CaptionTrack(this.mCCWidget, mediaFormat);
        }
        throw new RuntimeException("No matching format: " + mediaFormat.toString());
    }

    static class Cea708CaptionTrack extends SubtitleTrack {
        private final Cea708CCParser mCCParser;
        private final Cea708CCWidget mRenderingWidget;

        Cea708CaptionTrack(Cea708CCWidget cea708CCWidget, MediaFormat mediaFormat) {
            super(mediaFormat);
            this.mRenderingWidget = cea708CCWidget;
            this.mCCParser = new Cea708CCParser(cea708CCWidget);
        }

        @Override // androidx.media2.widget.SubtitleTrack
        public void onData(byte[] bArr, boolean z, long j) {
            this.mCCParser.parse(bArr);
        }

        @Override // androidx.media2.widget.SubtitleTrack
        public SubtitleTrack.RenderingWidget getRenderingWidget() {
            return this.mRenderingWidget;
        }
    }

    /* access modifiers changed from: package-private */
    public class Cea708CCWidget extends ClosedCaptionWidget implements Cea708CCParser.DisplayListener {
        private final CCHandler mCCHandler;

        Cea708CCWidget(Cea708CaptionRenderer cea708CaptionRenderer, Context context) {
            this(cea708CaptionRenderer, context, null);
        }

        Cea708CCWidget(Cea708CaptionRenderer cea708CaptionRenderer, Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        Cea708CCWidget(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.mCCHandler = new CCHandler((CCLayout) this.mClosedCaptionLayout);
        }

        @Override // androidx.media2.widget.ClosedCaptionWidget
        public ClosedCaptionWidget.ClosedCaptionLayout createCaptionLayout(Context context) {
            return new CCLayout(context);
        }

        @Override // androidx.media2.widget.Cea708CCParser.DisplayListener
        public void emitEvent(Cea708CCParser.CaptionEvent captionEvent) {
            this.mCCHandler.processCaptionEvent(captionEvent);
            setSize(getWidth(), getHeight());
            if (this.mListener != null) {
                this.mListener.onChanged(this);
            }
        }

        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            ((ViewGroup) this.mClosedCaptionLayout).draw(canvas);
        }

        /* access modifiers changed from: package-private */
        public class ScaledLayout extends ViewGroup {
            private Rect[] mRectArray;
            private final Comparator<Rect> mRectTopLeftSorter = new Comparator<Rect>() {
                /* class androidx.media2.widget.Cea708CaptionRenderer.Cea708CCWidget.ScaledLayout.AnonymousClass1 */

                public int compare(Rect rect, Rect rect2) {
                    int i;
                    int i2;
                    if (rect.top != rect2.top) {
                        i = rect.top;
                        i2 = rect2.top;
                    } else {
                        i = rect.left;
                        i2 = rect2.left;
                    }
                    return i - i2;
                }
            };

            ScaledLayout(Context context) {
                super(context);
            }

            /* access modifiers changed from: package-private */
            public class ScaledLayoutParams extends ViewGroup.LayoutParams {
                public float scaleEndCol;
                public float scaleEndRow;
                public float scaleStartCol;
                public float scaleStartRow;

                ScaledLayoutParams(float f, float f2, float f3, float f4) {
                    super(-1, -1);
                    this.scaleStartRow = f;
                    this.scaleEndRow = f2;
                    this.scaleStartCol = f3;
                    this.scaleEndCol = f4;
                }

                ScaledLayoutParams(Context context, AttributeSet attributeSet) {
                    super(-1, -1);
                }
            }

            @Override // android.view.ViewGroup
            public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
                return new ScaledLayoutParams(getContext(), attributeSet);
            }

            /* access modifiers changed from: protected */
            public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
                return layoutParams instanceof ScaledLayoutParams;
            }

            /* access modifiers changed from: protected */
            public void onMeasure(int i, int i2) {
                int i3;
                int size = View.MeasureSpec.getSize(i);
                int size2 = View.MeasureSpec.getSize(i2);
                int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
                int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
                int childCount = getChildCount();
                this.mRectArray = new Rect[childCount];
                int i4 = 0;
                while (i4 < childCount) {
                    View childAt = getChildAt(i4);
                    ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                    if (layoutParams instanceof ScaledLayoutParams) {
                        ScaledLayoutParams scaledLayoutParams = (ScaledLayoutParams) layoutParams;
                        float f = scaledLayoutParams.scaleStartRow;
                        float f2 = scaledLayoutParams.scaleEndRow;
                        float f3 = scaledLayoutParams.scaleStartCol;
                        float f4 = scaledLayoutParams.scaleEndCol;
                        if (f < 0.0f || f > 1.0f) {
                            throw new RuntimeException("A child of ScaledLayout should have a range of scaleStartRow between 0 and 1");
                        } else if (f2 < f || f > 1.0f) {
                            throw new RuntimeException("A child of ScaledLayout should have a range of scaleEndRow between scaleStartRow and 1");
                        } else if (f4 < 0.0f || f4 > 1.0f) {
                            throw new RuntimeException("A child of ScaledLayout should have a range of scaleStartCol between 0 and 1");
                        } else if (f4 < f3 || f4 > 1.0f) {
                            throw new RuntimeException("A child of ScaledLayout should have a range of scaleEndCol between scaleStartCol and 1");
                        } else {
                            float f5 = (float) paddingLeft;
                            float f6 = (float) paddingTop;
                            this.mRectArray[i4] = new Rect((int) (f3 * f5), (int) (f * f6), (int) (f4 * f5), (int) (f2 * f6));
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (f5 * (f4 - f3)), 1073741824);
                            childAt.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
                            if (childAt.getMeasuredHeight() > this.mRectArray[i4].height()) {
                                int measuredHeight = ((childAt.getMeasuredHeight() - this.mRectArray[i4].height()) + 1) / 2;
                                this.mRectArray[i4].bottom += measuredHeight;
                                this.mRectArray[i4].top -= measuredHeight;
                                if (this.mRectArray[i4].top < 0) {
                                    this.mRectArray[i4].bottom -= this.mRectArray[i4].top;
                                    this.mRectArray[i4].top = 0;
                                }
                                if (this.mRectArray[i4].bottom > paddingTop) {
                                    this.mRectArray[i4].top -= this.mRectArray[i4].bottom - paddingTop;
                                    this.mRectArray[i4].bottom = paddingTop;
                                }
                            }
                            childAt.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec((int) (f6 * (f2 - f)), 1073741824));
                            i4++;
                            paddingLeft = paddingLeft;
                            size = size;
                            size2 = size2;
                            childCount = childCount;
                        }
                    } else {
                        throw new RuntimeException("A child of ScaledLayout cannot have the UNSPECIFIED scale factors");
                    }
                }
                int[] iArr = new int[childCount];
                Rect[] rectArr = new Rect[childCount];
                int i5 = 0;
                for (int i6 = 0; i6 < childCount; i6++) {
                    if (getChildAt(i6).getVisibility() == 0) {
                        iArr[i5] = i5;
                        rectArr[i5] = this.mRectArray[i6];
                        i5++;
                    }
                }
                Arrays.sort(rectArr, 0, i5, this.mRectTopLeftSorter);
                int i7 = 0;
                while (true) {
                    i3 = i5 - 1;
                    if (i7 >= i3) {
                        break;
                    }
                    int i8 = i7 + 1;
                    for (int i9 = i8; i9 < i5; i9++) {
                        if (Rect.intersects(rectArr[i7], rectArr[i9])) {
                            iArr[i9] = iArr[i7];
                            rectArr[i9].set(rectArr[i9].left, rectArr[i7].bottom, rectArr[i9].right, rectArr[i7].bottom + rectArr[i9].height());
                        }
                    }
                    i7 = i8;
                }
                while (i3 >= 0) {
                    if (rectArr[i3].bottom > paddingTop) {
                        int i10 = rectArr[i3].bottom - paddingTop;
                        for (int i11 = 0; i11 <= i3; i11++) {
                            if (iArr[i3] == iArr[i11]) {
                                rectArr[i11].set(rectArr[i11].left, rectArr[i11].top - i10, rectArr[i11].right, rectArr[i11].bottom - i10);
                            }
                        }
                    }
                    i3--;
                }
                setMeasuredDimension(size, size2);
            }

            /* access modifiers changed from: protected */
            public void onLayout(boolean z, int i, int i2, int i3, int i4) {
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                int childCount = getChildCount();
                for (int i5 = 0; i5 < childCount; i5++) {
                    View childAt = getChildAt(i5);
                    if (childAt.getVisibility() != 8) {
                        childAt.layout(this.mRectArray[i5].left + paddingLeft, this.mRectArray[i5].top + paddingTop, this.mRectArray[i5].right + paddingTop, this.mRectArray[i5].bottom + paddingLeft);
                    }
                }
            }

            public void dispatchDraw(Canvas canvas) {
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    if (childAt.getVisibility() != 8) {
                        Rect[] rectArr = this.mRectArray;
                        if (i < rectArr.length) {
                            int save = canvas.save();
                            canvas.translate((float) (rectArr[i].left + paddingLeft), (float) (this.mRectArray[i].top + paddingTop));
                            childAt.draw(canvas);
                            canvas.restoreToCount(save);
                        } else {
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public class CCLayout extends ScaledLayout implements ClosedCaptionWidget.ClosedCaptionLayout {
            private final ScaledLayout mSafeTitleAreaLayout;

            CCLayout(Context context) {
                super(context);
                ScaledLayout scaledLayout = new ScaledLayout(context);
                this.mSafeTitleAreaLayout = scaledLayout;
                addView(scaledLayout, new ScaledLayout.ScaledLayoutParams(0.1f, 0.9f, 0.1f, 0.9f));
            }

            public void addOrUpdateViewToSafeTitleArea(CCWindowLayout cCWindowLayout, ScaledLayout.ScaledLayoutParams scaledLayoutParams) {
                if (this.mSafeTitleAreaLayout.indexOfChild(cCWindowLayout) < 0) {
                    this.mSafeTitleAreaLayout.addView(cCWindowLayout, scaledLayoutParams);
                } else {
                    this.mSafeTitleAreaLayout.updateViewLayout(cCWindowLayout, scaledLayoutParams);
                }
            }

            public void removeViewFromSafeTitleArea(CCWindowLayout cCWindowLayout) {
                this.mSafeTitleAreaLayout.removeView(cCWindowLayout);
            }

            @Override // androidx.media2.widget.ClosedCaptionWidget.ClosedCaptionLayout
            public void setCaptionStyle(CaptioningManager.CaptionStyle captionStyle) {
                int childCount = this.mSafeTitleAreaLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ((CCWindowLayout) this.mSafeTitleAreaLayout.getChildAt(i)).setCaptionStyle(captionStyle);
                }
            }

            @Override // androidx.media2.widget.ClosedCaptionWidget.ClosedCaptionLayout
            public void setFontScale(float f) {
                int childCount = this.mSafeTitleAreaLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ((CCWindowLayout) this.mSafeTitleAreaLayout.getChildAt(i)).setFontScale(f);
                }
            }
        }

        class CCHandler implements Handler.Callback {
            private final CCLayout mCCLayout;
            private final CCWindowLayout[] mCaptionWindowLayouts = new CCWindowLayout[8];
            private CCWindowLayout mCurrentWindowLayout;
            private final Handler mHandler;
            private boolean mIsDelayed = false;
            private final ArrayList<Cea708CCParser.CaptionEvent> mPendingCaptionEvents = new ArrayList<>();

            CCHandler(CCLayout cCLayout) {
                this.mCCLayout = cCLayout;
                this.mHandler = new Handler(this);
            }

            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    delayCancel();
                    return true;
                } else if (i != 2) {
                    return false;
                } else {
                    clearWindows(255);
                    return true;
                }
            }

            public void processCaptionEvent(Cea708CCParser.CaptionEvent captionEvent) {
                if (this.mIsDelayed) {
                    this.mPendingCaptionEvents.add(captionEvent);
                    return;
                }
                switch (captionEvent.type) {
                    case 1:
                        sendBufferToCurrentWindow((String) captionEvent.obj);
                        return;
                    case 2:
                        sendControlToCurrentWindow(((Character) captionEvent.obj).charValue());
                        return;
                    case 3:
                        setCurrentWindowLayout(((Integer) captionEvent.obj).intValue());
                        return;
                    case 4:
                        clearWindows(((Integer) captionEvent.obj).intValue());
                        return;
                    case 5:
                        displayWindows(((Integer) captionEvent.obj).intValue());
                        return;
                    case 6:
                        hideWindows(((Integer) captionEvent.obj).intValue());
                        return;
                    case 7:
                        toggleWindows(((Integer) captionEvent.obj).intValue());
                        return;
                    case 8:
                        deleteWindows(((Integer) captionEvent.obj).intValue());
                        return;
                    case 9:
                        delay(((Integer) captionEvent.obj).intValue());
                        return;
                    case 10:
                        delayCancel();
                        return;
                    case 11:
                        reset();
                        return;
                    case 12:
                        setPenAttr((Cea708CCParser.CaptionPenAttr) captionEvent.obj);
                        return;
                    case 13:
                        setPenColor((Cea708CCParser.CaptionPenColor) captionEvent.obj);
                        return;
                    case 14:
                        setPenLocation((Cea708CCParser.CaptionPenLocation) captionEvent.obj);
                        return;
                    case 15:
                        setWindowAttr((Cea708CCParser.CaptionWindowAttr) captionEvent.obj);
                        return;
                    case 16:
                        defineWindow((Cea708CCParser.CaptionWindow) captionEvent.obj);
                        return;
                    default:
                        return;
                }
            }

            private void setCurrentWindowLayout(int i) {
                CCWindowLayout cCWindowLayout;
                if (i >= 0) {
                    CCWindowLayout[] cCWindowLayoutArr = this.mCaptionWindowLayouts;
                    if (i < cCWindowLayoutArr.length && (cCWindowLayout = cCWindowLayoutArr[i]) != null) {
                        this.mCurrentWindowLayout = cCWindowLayout;
                    }
                }
            }

            private ArrayList<CCWindowLayout> getWindowsFromBitmap(int i) {
                CCWindowLayout cCWindowLayout;
                ArrayList<CCWindowLayout> arrayList = new ArrayList<>();
                for (int i2 = 0; i2 < 8; i2++) {
                    if (!(((1 << i2) & i) == 0 || (cCWindowLayout = this.mCaptionWindowLayouts[i2]) == null)) {
                        arrayList.add(cCWindowLayout);
                    }
                }
                return arrayList;
            }

            private void clearWindows(int i) {
                if (i != 0) {
                    Iterator<CCWindowLayout> it = getWindowsFromBitmap(i).iterator();
                    while (it.hasNext()) {
                        it.next().clear();
                    }
                }
            }

            private void displayWindows(int i) {
                if (i != 0) {
                    Iterator<CCWindowLayout> it = getWindowsFromBitmap(i).iterator();
                    while (it.hasNext()) {
                        it.next().show();
                    }
                }
            }

            private void hideWindows(int i) {
                if (i != 0) {
                    Iterator<CCWindowLayout> it = getWindowsFromBitmap(i).iterator();
                    while (it.hasNext()) {
                        it.next().hide();
                    }
                }
            }

            private void toggleWindows(int i) {
                if (i != 0) {
                    Iterator<CCWindowLayout> it = getWindowsFromBitmap(i).iterator();
                    while (it.hasNext()) {
                        CCWindowLayout next = it.next();
                        if (next.isShown()) {
                            next.hide();
                        } else {
                            next.show();
                        }
                    }
                }
            }

            private void deleteWindows(int i) {
                if (i != 0) {
                    Iterator<CCWindowLayout> it = getWindowsFromBitmap(i).iterator();
                    while (it.hasNext()) {
                        CCWindowLayout next = it.next();
                        next.removeFromCaptionView();
                        this.mCaptionWindowLayouts[next.getCaptionWindowId()] = null;
                    }
                }
            }

            public void reset() {
                this.mCurrentWindowLayout = null;
                this.mIsDelayed = false;
                this.mPendingCaptionEvents.clear();
                for (int i = 0; i < 8; i++) {
                    CCWindowLayout[] cCWindowLayoutArr = this.mCaptionWindowLayouts;
                    if (cCWindowLayoutArr[i] != null) {
                        cCWindowLayoutArr[i].removeFromCaptionView();
                    }
                    this.mCaptionWindowLayouts[i] = null;
                }
                this.mCCLayout.setVisibility(4);
                this.mHandler.removeMessages(2);
            }

            private void setWindowAttr(Cea708CCParser.CaptionWindowAttr captionWindowAttr) {
                CCWindowLayout cCWindowLayout = this.mCurrentWindowLayout;
                if (cCWindowLayout != null) {
                    cCWindowLayout.setWindowAttr(captionWindowAttr);
                }
            }

            private void defineWindow(Cea708CCParser.CaptionWindow captionWindow) {
                int i;
                if (captionWindow != null && (i = captionWindow.id) >= 0) {
                    CCWindowLayout[] cCWindowLayoutArr = this.mCaptionWindowLayouts;
                    if (i < cCWindowLayoutArr.length) {
                        CCWindowLayout cCWindowLayout = cCWindowLayoutArr[i];
                        if (cCWindowLayout == null) {
                            cCWindowLayout = new CCWindowLayout(Cea708CCWidget.this, this.mCCLayout.getContext());
                        }
                        cCWindowLayout.initWindow(this.mCCLayout, captionWindow);
                        this.mCaptionWindowLayouts[i] = cCWindowLayout;
                        this.mCurrentWindowLayout = cCWindowLayout;
                    }
                }
            }

            private void delay(int i) {
                if (i >= 0 && i <= 255) {
                    this.mIsDelayed = true;
                    Handler handler = this.mHandler;
                    handler.sendMessageDelayed(handler.obtainMessage(1), (long) (i * 100));
                }
            }

            private void delayCancel() {
                this.mIsDelayed = false;
                processPendingBuffer();
            }

            private void processPendingBuffer() {
                Iterator<Cea708CCParser.CaptionEvent> it = this.mPendingCaptionEvents.iterator();
                while (it.hasNext()) {
                    processCaptionEvent(it.next());
                }
                this.mPendingCaptionEvents.clear();
            }

            private void sendControlToCurrentWindow(char c) {
                CCWindowLayout cCWindowLayout = this.mCurrentWindowLayout;
                if (cCWindowLayout != null) {
                    cCWindowLayout.sendControl(c);
                }
            }

            private void sendBufferToCurrentWindow(String str) {
                CCWindowLayout cCWindowLayout = this.mCurrentWindowLayout;
                if (cCWindowLayout != null) {
                    cCWindowLayout.sendBuffer(str);
                    this.mHandler.removeMessages(2);
                    Handler handler = this.mHandler;
                    handler.sendMessageDelayed(handler.obtainMessage(2), 60000);
                }
            }

            private void setPenAttr(Cea708CCParser.CaptionPenAttr captionPenAttr) {
                CCWindowLayout cCWindowLayout = this.mCurrentWindowLayout;
                if (cCWindowLayout != null) {
                    cCWindowLayout.setPenAttr(captionPenAttr);
                }
            }

            private void setPenColor(Cea708CCParser.CaptionPenColor captionPenColor) {
                CCWindowLayout cCWindowLayout = this.mCurrentWindowLayout;
                if (cCWindowLayout != null) {
                    cCWindowLayout.setPenColor(captionPenColor);
                }
            }

            private void setPenLocation(Cea708CCParser.CaptionPenLocation captionPenLocation) {
                CCWindowLayout cCWindowLayout = this.mCurrentWindowLayout;
                if (cCWindowLayout != null) {
                    cCWindowLayout.setPenLocation(captionPenLocation.row, captionPenLocation.column);
                }
            }
        }

        /* access modifiers changed from: private */
        public class CCWindowLayout extends RelativeLayout implements View.OnLayoutChangeListener {
            private final SpannableStringBuilder mBuilder;
            private CCLayout mCCLayout;
            private CCView mCCView;
            private CaptioningManager.CaptionStyle mCaptionStyle;
            private int mCaptionWindowId;
            private final List<CharacterStyle> mCharacterStyles;
            private float mFontScale;
            private int mLastCaptionLayoutHeight;
            private int mLastCaptionLayoutWidth;
            private int mRow;
            private int mRowLimit;
            private float mTextSize;
            private String mWidestChar;

            private int getScreenColumnCount() {
                return 42;
            }

            public void sendControl(char c) {
            }

            public void setPenColor(Cea708CCParser.CaptionPenColor captionPenColor) {
            }

            public void setWindowAttr(Cea708CCParser.CaptionWindowAttr captionWindowAttr) {
            }

            CCWindowLayout(Cea708CCWidget cea708CCWidget, Context context) {
                this(cea708CCWidget, context, null);
            }

            CCWindowLayout(Cea708CCWidget cea708CCWidget, Context context, AttributeSet attributeSet) {
                this(context, attributeSet, 0);
            }

            CCWindowLayout(Context context, AttributeSet attributeSet, int i) {
                super(context, attributeSet, i);
                this.mRowLimit = 0;
                this.mBuilder = new SpannableStringBuilder();
                this.mCharacterStyles = new ArrayList();
                this.mRow = -1;
                this.mCCView = new CCView(Cea708CCWidget.this, context);
                addView(this.mCCView, new RelativeLayout.LayoutParams(-2, -2));
                CaptioningManager captioningManager = (CaptioningManager) context.getSystemService("captioning");
                this.mFontScale = captioningManager.getFontScale();
                setCaptionStyle(captioningManager.getUserStyle());
                this.mCCView.setText("");
                updateWidestChar();
            }

            public void setCaptionStyle(CaptioningManager.CaptionStyle captionStyle) {
                this.mCaptionStyle = captionStyle;
                this.mCCView.setCaptionStyle(captionStyle);
            }

            public void setFontScale(float f) {
                this.mFontScale = f;
                updateTextSize();
            }

            public int getCaptionWindowId() {
                return this.mCaptionWindowId;
            }

            public void setCaptionWindowId(int i) {
                this.mCaptionWindowId = i;
            }

            public void clear() {
                clearText();
                hide();
            }

            public void show() {
                setVisibility(0);
                requestLayout();
            }

            public void hide() {
                setVisibility(4);
                requestLayout();
            }

            public void setPenAttr(Cea708CCParser.CaptionPenAttr captionPenAttr) {
                this.mCharacterStyles.clear();
                if (captionPenAttr.italic) {
                    this.mCharacterStyles.add(new StyleSpan(2));
                }
                if (captionPenAttr.underline) {
                    this.mCharacterStyles.add(new UnderlineSpan());
                }
                int i = captionPenAttr.penSize;
                if (i == 0) {
                    this.mCharacterStyles.add(new RelativeSizeSpan(0.75f));
                } else if (i == 2) {
                    this.mCharacterStyles.add(new RelativeSizeSpan(1.25f));
                }
                int i2 = captionPenAttr.penOffset;
                if (i2 == 0) {
                    this.mCharacterStyles.add(new SubscriptSpan());
                } else if (i2 == 2) {
                    this.mCharacterStyles.add(new SuperscriptSpan());
                }
            }

            public void setPenLocation(int i, int i2) {
                int i3 = this.mRow;
                if (i3 >= 0) {
                    while (i3 < i) {
                        appendText("\n");
                        i3++;
                    }
                }
                this.mRow = i;
            }

            public void sendBuffer(String str) {
                appendText(str);
            }

            /* JADX WARNING: Removed duplicated region for block: B:45:0x0123  */
            /* JADX WARNING: Removed duplicated region for block: B:51:0x013e  */
            /* JADX WARNING: Removed duplicated region for block: B:54:0x0163  */
            /* JADX WARNING: Removed duplicated region for block: B:55:0x0167  */
            public void initWindow(CCLayout cCLayout, Cea708CCParser.CaptionWindow captionWindow) {
                float f;
                float f2;
                float f3;
                float f4;
                CCLayout cCLayout2 = this.mCCLayout;
                if (cCLayout2 != cCLayout) {
                    if (cCLayout2 != null) {
                        cCLayout2.removeOnLayoutChangeListener(this);
                    }
                    this.mCCLayout = cCLayout;
                    cCLayout.addOnLayoutChangeListener(this);
                    updateWidestChar();
                }
                int i = 99;
                float f5 = ((float) captionWindow.anchorVertical) / ((float) (captionWindow.relativePositioning ? 99 : 74));
                float f6 = (float) captionWindow.anchorHorizontal;
                if (!captionWindow.relativePositioning) {
                    i = 209;
                }
                float f7 = f6 / ((float) i);
                if (f5 < 0.0f || f5 > 1.0f) {
                    Log.i("CCWindowLayout", "The vertical position of the anchor point should be at the range of 0 and 1 but " + f5);
                    f5 = Math.max(0.0f, Math.min(f5, 1.0f));
                }
                if (f7 < 0.0f || f7 > 1.0f) {
                    Log.i("CCWindowLayout", "The horizontal position of the anchor point should be at the range of 0 and 1 but " + f7);
                    f7 = Math.max(0.0f, Math.min(f7, 1.0f));
                }
                int i2 = 17;
                int i3 = captionWindow.anchorId % 3;
                int i4 = captionWindow.anchorId / 3;
                if (i3 != 0) {
                    if (i3 == 1) {
                        float min = Math.min(1.0f - f7, f7);
                        int min2 = Math.min(getScreenColumnCount(), captionWindow.columnCount + 1);
                        StringBuilder sb = new StringBuilder();
                        for (int i5 = 0; i5 < min2; i5++) {
                            sb.append(this.mWidestChar);
                        }
                        Paint paint = new Paint();
                        paint.setTypeface(this.mCaptionStyle.getTypeface());
                        paint.setTextSize(this.mTextSize);
                        float measureText = this.mCCLayout.getWidth() > 0 ? (paint.measureText(sb.toString()) / 2.0f) / (((float) this.mCCLayout.getWidth()) * 0.8f) : 0.0f;
                        if (measureText <= 0.0f || measureText >= f7) {
                            this.mCCView.setAlignment(Layout.Alignment.ALIGN_CENTER);
                            f = f7 + min;
                            f2 = f7 - min;
                            i2 = 1;
                        } else {
                            this.mCCView.setAlignment(Layout.Alignment.ALIGN_NORMAL);
                            f7 -= measureText;
                            f2 = f7;
                            i2 = 3;
                        }
                    } else if (i3 != 2) {
                        f2 = 0.0f;
                    } else {
                        i2 = 5;
                        f = f7;
                        f2 = 0.0f;
                    }
                    if (i4 == 0) {
                        if (i4 == 1) {
                            i2 |= 16;
                            float min3 = Math.min(1.0f - f5, f5);
                            f4 = f5 - min3;
                            f3 = f5 + min3;
                        } else if (i4 != 2) {
                            f4 = 0.0f;
                        } else {
                            i2 |= 80;
                            f3 = f5;
                            f4 = 0.0f;
                        }
                        CCLayout cCLayout3 = this.mCCLayout;
                        CCLayout cCLayout4 = this.mCCLayout;
                        cCLayout4.getClass();
                        cCLayout3.addOrUpdateViewToSafeTitleArea(this, new ScaledLayout.ScaledLayoutParams(f4, f3, f2, f));
                        setCaptionWindowId(captionWindow.id);
                        setRowLimit(captionWindow.rowCount);
                        setGravity(i2);
                        if (captionWindow.visible) {
                            show();
                            return;
                        } else {
                            hide();
                            return;
                        }
                    } else {
                        i2 |= 48;
                        f4 = f5;
                    }
                    f3 = 1.0f;
                    CCLayout cCLayout32 = this.mCCLayout;
                    CCLayout cCLayout42 = this.mCCLayout;
                    cCLayout42.getClass();
                    cCLayout32.addOrUpdateViewToSafeTitleArea(this, new ScaledLayout.ScaledLayoutParams(f4, f3, f2, f));
                    setCaptionWindowId(captionWindow.id);
                    setRowLimit(captionWindow.rowCount);
                    setGravity(i2);
                    if (captionWindow.visible) {
                    }
                } else {
                    this.mCCView.setAlignment(Layout.Alignment.ALIGN_NORMAL);
                    f2 = f7;
                    i2 = 3;
                }
                f = 1.0f;
                if (i4 == 0) {
                }
                f3 = 1.0f;
                CCLayout cCLayout322 = this.mCCLayout;
                CCLayout cCLayout422 = this.mCCLayout;
                cCLayout422.getClass();
                cCLayout322.addOrUpdateViewToSafeTitleArea(this, new ScaledLayout.ScaledLayoutParams(f4, f3, f2, f));
                setCaptionWindowId(captionWindow.id);
                setRowLimit(captionWindow.rowCount);
                setGravity(i2);
                if (captionWindow.visible) {
                }
            }

            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int i9 = i3 - i;
                int i10 = i4 - i2;
                if (i9 != this.mLastCaptionLayoutWidth || i10 != this.mLastCaptionLayoutHeight) {
                    this.mLastCaptionLayoutWidth = i9;
                    this.mLastCaptionLayoutHeight = i10;
                    updateTextSize();
                }
            }

            private void updateWidestChar() {
                Paint paint = new Paint();
                paint.setTypeface(this.mCaptionStyle.getTypeface());
                Charset forName = Charset.forName("ISO-8859-1");
                float f = 0.0f;
                for (int i = 0; i < 256; i++) {
                    String str = new String(new byte[]{(byte) i}, forName);
                    float measureText = paint.measureText(str);
                    if (f < measureText) {
                        this.mWidestChar = str;
                        f = measureText;
                    }
                }
                updateTextSize();
            }

            private void updateTextSize() {
                if (this.mCCLayout != null) {
                    StringBuilder sb = new StringBuilder();
                    int screenColumnCount = getScreenColumnCount();
                    for (int i = 0; i < screenColumnCount; i++) {
                        sb.append(this.mWidestChar);
                    }
                    String sb2 = sb.toString();
                    Paint paint = new Paint();
                    paint.setTypeface(this.mCaptionStyle.getTypeface());
                    float f = 0.0f;
                    float f2 = 255.0f;
                    while (f < f2) {
                        float f3 = (f + f2) / 2.0f;
                        paint.setTextSize(f3);
                        if (((float) this.mCCLayout.getWidth()) * 0.8f > paint.measureText(sb2)) {
                            f = f3 + 0.01f;
                        } else {
                            f2 = f3 - 0.01f;
                        }
                    }
                    float f4 = f2 * this.mFontScale;
                    this.mTextSize = f4;
                    this.mCCView.setTextSize(f4);
                }
            }

            public void removeFromCaptionView() {
                CCLayout cCLayout = this.mCCLayout;
                if (cCLayout != null) {
                    cCLayout.removeViewFromSafeTitleArea(this);
                    this.mCCLayout.removeOnLayoutChangeListener(this);
                    this.mCCLayout = null;
                }
            }

            public void appendText(String str) {
                updateText(str, true);
            }

            public void clearText() {
                this.mBuilder.clear();
                this.mCCView.setText("");
            }

            private void updateText(String str, boolean z) {
                if (!z) {
                    this.mBuilder.clear();
                }
                if (str != null && str.length() > 0) {
                    int length = this.mBuilder.length();
                    this.mBuilder.append((CharSequence) str);
                    for (CharacterStyle characterStyle : this.mCharacterStyles) {
                        SpannableStringBuilder spannableStringBuilder = this.mBuilder;
                        spannableStringBuilder.setSpan(characterStyle, length, spannableStringBuilder.length(), 33);
                    }
                }
                String[] split = TextUtils.split(this.mBuilder.toString(), "\n");
                String join = TextUtils.join("\n", Arrays.copyOfRange(split, Math.max(0, split.length - (this.mRowLimit + 1)), split.length));
                SpannableStringBuilder spannableStringBuilder2 = this.mBuilder;
                spannableStringBuilder2.delete(0, spannableStringBuilder2.length() - join.length());
                int length2 = this.mBuilder.length() - 1;
                int i = 0;
                while (i <= length2 && this.mBuilder.charAt(i) <= ' ') {
                    i++;
                }
                int i2 = length2;
                while (i2 >= i && this.mBuilder.charAt(i2) <= ' ') {
                    i2--;
                }
                if (i == 0 && i2 == length2) {
                    this.mCCView.setText(this.mBuilder);
                    return;
                }
                SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder();
                spannableStringBuilder3.append((CharSequence) this.mBuilder);
                if (i2 < length2) {
                    spannableStringBuilder3.delete(i2 + 1, length2 + 1);
                }
                if (i > 0) {
                    spannableStringBuilder3.delete(0, i);
                }
                this.mCCView.setText(spannableStringBuilder3);
            }

            public void setRowLimit(int i) {
                if (i >= 0) {
                    this.mRowLimit = i;
                    return;
                }
                throw new IllegalArgumentException("A rowLimit should have a positive number");
            }
        }

        /* access modifiers changed from: package-private */
        public class CCView extends SubtitleView {
            CCView(Cea708CCWidget cea708CCWidget, Context context) {
                this(cea708CCWidget, context, null);
            }

            CCView(Cea708CCWidget cea708CCWidget, Context context, AttributeSet attributeSet) {
                this(context, attributeSet, 0);
            }

            CCView(Context context, AttributeSet attributeSet, int i) {
                super(context, attributeSet, i);
            }

            /* access modifiers changed from: package-private */
            public void setCaptionStyle(CaptioningManager.CaptionStyle captionStyle) {
                if (Build.VERSION.SDK_INT >= 21) {
                    if (captionStyle.hasForegroundColor()) {
                        setForegroundColor(captionStyle.foregroundColor);
                    }
                    if (captionStyle.hasBackgroundColor()) {
                        setBackgroundColor(captionStyle.backgroundColor);
                    }
                    if (captionStyle.hasEdgeType()) {
                        setEdgeType(captionStyle.edgeType);
                    }
                    if (captionStyle.hasEdgeColor()) {
                        setEdgeColor(captionStyle.edgeColor);
                    }
                }
                setTypeface(captionStyle.getTypeface());
            }
        }
    }
}
