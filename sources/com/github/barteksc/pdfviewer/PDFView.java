package com.github.barteksc.pdfviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.model.PagePart;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
import com.github.barteksc.pdfviewer.source.AssetSource;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.github.barteksc.pdfviewer.source.InputStreamSource;
import com.github.barteksc.pdfviewer.util.ArrayUtils;
import com.github.barteksc.pdfviewer.util.MathUtils;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PDFView extends RelativeLayout {
    private static final String TAG = PDFView.class.getSimpleName();
    private AnimationManager animationManager;
    private boolean annotationRendering = false;
    private boolean bestQuality = false;
    CacheManager cacheManager;
    private int currentFilteredPage;
    private int currentPage;
    private float currentXOffset = 0.0f;
    private float currentYOffset = 0.0f;
    private Paint debugPaint;
    private DecodingAsyncTask decodingAsyncTask;
    private int defaultPage = 0;
    private int documentPageCount;
    private DragPinchManager dragPinchManager;
    private int[] filteredUserPageIndexes;
    private int[] filteredUserPages;
    private boolean isScrollHandleInit = false;
    private float maxZoom = 3.0f;
    private float midZoom = 1.75f;
    private float minZoom = 1.0f;
    private OnDrawListener onDrawListener;
    private OnErrorListener onErrorListener;
    private OnLoadCompleteListener onLoadCompleteListener;
    private OnPageChangeListener onPageChangeListener;
    private OnPageScrollListener onPageScrollListener;
    private float optimalPageHeight;
    private float optimalPageWidth;
    private int[] originalUserPages;
    private int pageHeight;
    private int pageWidth;
    private PagesLoader pagesLoader;
    private Paint paint;
    private PdfDocument pdfDocument;
    private PdfiumCore pdfiumCore;
    private boolean recycled = true;
    RenderingAsyncTask renderingAsyncTask;
    private ScrollDir scrollDir = ScrollDir.NONE;
    private ScrollHandle scrollHandle;
    private State state = State.DEFAULT;
    private boolean swipeVertical = true;
    private float zoom = 1.0f;

    /* access modifiers changed from: package-private */
    public enum ScrollDir {
        NONE,
        START,
        END
    }

    /* access modifiers changed from: private */
    public enum State {
        DEFAULT,
        LOADED,
        SHOWN,
        ERROR
    }

    /* access modifiers changed from: package-private */
    public ScrollHandle getScrollHandle() {
        return this.scrollHandle;
    }

    public PDFView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.cacheManager = new CacheManager();
            AnimationManager animationManager2 = new AnimationManager(this);
            this.animationManager = animationManager2;
            this.dragPinchManager = new DragPinchManager(this, animationManager2);
            this.paint = new Paint();
            Paint paint2 = new Paint();
            this.debugPaint = paint2;
            paint2.setStyle(Paint.Style.STROKE);
            this.pdfiumCore = new PdfiumCore(context);
            setWillNotDraw(false);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void load(DocumentSource documentSource, String str, OnLoadCompleteListener onLoadCompleteListener2, OnErrorListener onErrorListener2) {
        load(documentSource, str, onLoadCompleteListener2, onErrorListener2, null);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void load(DocumentSource documentSource, String str, OnLoadCompleteListener onLoadCompleteListener2, OnErrorListener onErrorListener2, int[] iArr) {
        if (this.recycled) {
            if (iArr != null) {
                this.originalUserPages = iArr;
                this.filteredUserPages = ArrayUtils.deleteDuplicatedPages(iArr);
                this.filteredUserPageIndexes = ArrayUtils.calculateIndexesInDuplicateArray(this.originalUserPages);
            }
            this.onLoadCompleteListener = onLoadCompleteListener2;
            this.onErrorListener = onErrorListener2;
            this.recycled = false;
            DecodingAsyncTask decodingAsyncTask2 = new DecodingAsyncTask(documentSource, str, this, this.pdfiumCore);
            this.decodingAsyncTask = decodingAsyncTask2;
            decodingAsyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        throw new IllegalStateException("Don't call load on a PDF View without recycling it first.");
    }

    public void jumpTo(int i, boolean z) {
        if (this.swipeVertical) {
            float currentScale = ((float) (-i)) * toCurrentScale(this.optimalPageHeight);
            if (z) {
                this.animationManager.startYAnimation(this.currentYOffset, currentScale);
            } else {
                moveTo(this.currentXOffset, currentScale);
            }
        } else {
            float currentScale2 = ((float) (-i)) * toCurrentScale(this.optimalPageWidth);
            if (z) {
                this.animationManager.startXAnimation(this.currentXOffset, currentScale2);
            } else {
                moveTo(currentScale2, this.currentYOffset);
            }
        }
        showPage(i);
    }

    /* access modifiers changed from: package-private */
    public void showPage(int i) {
        if (!this.recycled) {
            this.state = State.SHOWN;
            int determineValidPageNumberFrom = determineValidPageNumberFrom(i);
            this.currentPage = determineValidPageNumberFrom;
            this.currentFilteredPage = determineValidPageNumberFrom;
            int[] iArr = this.filteredUserPageIndexes;
            if (iArr != null && determineValidPageNumberFrom >= 0 && determineValidPageNumberFrom < iArr.length) {
                this.currentFilteredPage = iArr[determineValidPageNumberFrom];
            }
            loadPages();
            if (this.scrollHandle != null && !documentFitsView()) {
                this.scrollHandle.setPageNum(this.currentPage + 1);
            }
            OnPageChangeListener onPageChangeListener2 = this.onPageChangeListener;
            if (onPageChangeListener2 != null) {
                onPageChangeListener2.onPageChanged(this.currentPage, getPageCount());
            }
        }
    }

    public float getPositionOffset() {
        int i;
        float f;
        float f2;
        if (this.swipeVertical) {
            f2 = -this.currentYOffset;
            f = ((float) getPageCount()) * toCurrentScale(this.optimalPageHeight);
            i = getHeight();
        } else {
            f2 = -this.currentXOffset;
            f = ((float) getPageCount()) * toCurrentScale(this.optimalPageWidth);
            i = getWidth();
        }
        return MathUtils.limit(f2 / (f - ((float) i)), 0.0f, 1.0f);
    }

    public void setPositionOffset(float f, boolean z) {
        if (this.swipeVertical) {
            moveTo(this.currentXOffset, ((((float) (-getPageCount())) * toCurrentScale(this.optimalPageHeight)) + ((float) getHeight())) * f, z);
        } else {
            moveTo(((((float) (-getPageCount())) * toCurrentScale(this.optimalPageWidth)) + ((float) getWidth())) * f, this.currentYOffset, z);
        }
        loadPageByOffset();
    }

    public void setPositionOffset(float f) {
        setPositionOffset(f, true);
    }

    public void stopFling() {
        this.animationManager.stopFling();
    }

    public int getPageCount() {
        int[] iArr = this.originalUserPages;
        if (iArr != null) {
            return iArr.length;
        }
        return this.documentPageCount;
    }

    public void enableSwipe(boolean z) {
        this.dragPinchManager.setSwipeEnabled(z);
    }

    public void enableDoubletap(boolean z) {
        this.dragPinchManager.enableDoubletap(z);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOnPageChangeListener(OnPageChangeListener onPageChangeListener2) {
        this.onPageChangeListener = onPageChangeListener2;
    }

    /* access modifiers changed from: package-private */
    public OnPageChangeListener getOnPageChangeListener() {
        return this.onPageChangeListener;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOnPageScrollListener(OnPageScrollListener onPageScrollListener2) {
        this.onPageScrollListener = onPageScrollListener2;
    }

    /* access modifiers changed from: package-private */
    public OnPageScrollListener getOnPageScrollListener() {
        return this.onPageScrollListener;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOnDrawListener(OnDrawListener onDrawListener2) {
        this.onDrawListener = onDrawListener2;
    }

    public void recycle() {
        PdfDocument pdfDocument2;
        this.animationManager.stopAll();
        RenderingAsyncTask renderingAsyncTask2 = this.renderingAsyncTask;
        if (renderingAsyncTask2 != null) {
            renderingAsyncTask2.cancel(true);
        }
        DecodingAsyncTask decodingAsyncTask2 = this.decodingAsyncTask;
        if (decodingAsyncTask2 != null) {
            decodingAsyncTask2.cancel(true);
        }
        this.cacheManager.recycle();
        ScrollHandle scrollHandle2 = this.scrollHandle;
        if (scrollHandle2 != null && this.isScrollHandleInit) {
            scrollHandle2.destroyLayout();
        }
        PdfiumCore pdfiumCore2 = this.pdfiumCore;
        if (!(pdfiumCore2 == null || (pdfDocument2 = this.pdfDocument) == null)) {
            pdfiumCore2.closeDocument(pdfDocument2);
        }
        this.originalUserPages = null;
        this.filteredUserPages = null;
        this.filteredUserPageIndexes = null;
        this.pdfDocument = null;
        this.scrollHandle = null;
        this.isScrollHandleInit = false;
        this.currentYOffset = 0.0f;
        this.currentXOffset = 0.0f;
        this.zoom = 1.0f;
        this.recycled = true;
        this.state = State.DEFAULT;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        recycle();
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!isInEditMode()) {
            this.animationManager.stopAll();
            calculateOptimalWidthAndHeight();
            loadPages();
            if (this.swipeVertical) {
                moveTo(this.currentXOffset, calculateCenterOffsetForPage(this.currentFilteredPage));
            } else {
                moveTo(calculateCenterOffsetForPage(this.currentFilteredPage), this.currentYOffset);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            Drawable background = getBackground();
            if (background == null) {
                canvas.drawColor(-1);
            } else {
                background.draw(canvas);
            }
            if (!this.recycled && this.state == State.SHOWN) {
                float f = this.currentXOffset;
                float f2 = this.currentYOffset;
                canvas.translate(f, f2);
                for (PagePart pagePart : this.cacheManager.getThumbnails()) {
                    drawPart(canvas, pagePart);
                }
                for (PagePart pagePart2 : this.cacheManager.getPageParts()) {
                    drawPart(canvas, pagePart2);
                }
                if (this.onDrawListener != null) {
                    canvas.translate(toCurrentScale(((float) this.currentFilteredPage) * this.optimalPageWidth), 0.0f);
                    this.onDrawListener.onLayerDrawn(canvas, toCurrentScale(this.optimalPageWidth), toCurrentScale(this.optimalPageHeight), this.currentPage);
                    canvas.translate(-toCurrentScale(((float) this.currentFilteredPage) * this.optimalPageWidth), 0.0f);
                }
                canvas.translate(-f, -f2);
            }
        }
    }

    private void drawPart(Canvas canvas, PagePart pagePart) {
        float f;
        float f2;
        RectF pageRelativeBounds = pagePart.getPageRelativeBounds();
        Bitmap renderedBitmap = pagePart.getRenderedBitmap();
        if (!renderedBitmap.isRecycled()) {
            if (this.swipeVertical) {
                f2 = toCurrentScale(((float) pagePart.getUserPage()) * this.optimalPageHeight);
                f = 0.0f;
            } else {
                f = toCurrentScale(((float) pagePart.getUserPage()) * this.optimalPageWidth);
                f2 = 0.0f;
            }
            canvas.translate(f, f2);
            Rect rect = new Rect(0, 0, renderedBitmap.getWidth(), renderedBitmap.getHeight());
            float currentScale = toCurrentScale(pageRelativeBounds.left * this.optimalPageWidth);
            float currentScale2 = toCurrentScale(pageRelativeBounds.top * this.optimalPageHeight);
            RectF rectF = new RectF((float) ((int) currentScale), (float) ((int) currentScale2), (float) ((int) (currentScale + toCurrentScale(pageRelativeBounds.width() * this.optimalPageWidth))), (float) ((int) (currentScale2 + toCurrentScale(pageRelativeBounds.height() * this.optimalPageHeight))));
            float f3 = this.currentXOffset + f;
            float f4 = this.currentYOffset + f2;
            if (rectF.left + f3 >= ((float) getWidth()) || f3 + rectF.right <= 0.0f || rectF.top + f4 >= ((float) getHeight()) || f4 + rectF.bottom <= 0.0f) {
                canvas.translate(-f, -f2);
                return;
            }
            canvas.drawBitmap(renderedBitmap, rect, rectF, this.paint);
            canvas.translate(-f, -f2);
        }
    }

    public void loadPages() {
        if (this.optimalPageWidth != 0.0f && this.optimalPageHeight != 0.0f) {
            this.renderingAsyncTask.removeAllTasks();
            this.cacheManager.makeANewSet();
            this.pagesLoader.loadPages();
            redraw();
        }
    }

    public void loadComplete(PdfDocument pdfDocument2) {
        this.state = State.LOADED;
        this.documentPageCount = this.pdfiumCore.getPageCount(pdfDocument2);
        int[] iArr = this.originalUserPages;
        int i = iArr != null ? iArr[0] : 0;
        this.pdfDocument = pdfDocument2;
        this.pdfiumCore.openPage(pdfDocument2, i);
        this.pageWidth = this.pdfiumCore.getPageWidth(pdfDocument2, i);
        this.pageHeight = this.pdfiumCore.getPageHeight(pdfDocument2, i);
        calculateOptimalWidthAndHeight();
        this.pagesLoader = new PagesLoader(this);
        RenderingAsyncTask renderingAsyncTask2 = new RenderingAsyncTask(this, this.pdfiumCore, pdfDocument2);
        this.renderingAsyncTask = renderingAsyncTask2;
        renderingAsyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        ScrollHandle scrollHandle2 = this.scrollHandle;
        if (scrollHandle2 != null) {
            scrollHandle2.setupLayout(this);
            this.isScrollHandleInit = true;
        }
        jumpTo(this.defaultPage, false);
        OnLoadCompleteListener onLoadCompleteListener2 = this.onLoadCompleteListener;
        if (onLoadCompleteListener2 != null) {
            onLoadCompleteListener2.loadComplete(this.documentPageCount);
        }
    }

    public void loadError(Throwable th) {
        this.state = State.ERROR;
        recycle();
        invalidate();
        OnErrorListener onErrorListener2 = this.onErrorListener;
        if (onErrorListener2 != null) {
            onErrorListener2.onError(th);
        } else {
            Log.e("PDFView", "load pdf error", th);
        }
    }

    /* access modifiers changed from: package-private */
    public void redraw() {
        invalidate();
    }

    public void onBitmapRendered(PagePart pagePart) {
        if (pagePart.isThumbnail()) {
            this.cacheManager.cacheThumbnail(pagePart);
        } else {
            this.cacheManager.cachePart(pagePart);
        }
        redraw();
    }

    private int determineValidPageNumberFrom(int i) {
        if (i <= 0) {
            return 0;
        }
        int[] iArr = this.originalUserPages;
        if (iArr == null) {
            int i2 = this.documentPageCount;
            if (i >= i2) {
                return i2 - 1;
            }
        } else if (i >= iArr.length) {
            return iArr.length - 1;
        }
        return i;
    }

    private float calculateCenterOffsetForPage(int i) {
        float f;
        float width;
        float f2;
        if (this.swipeVertical) {
            f = -(((float) i) * this.optimalPageHeight);
            width = (float) (getHeight() / 2);
            f2 = this.optimalPageHeight;
        } else {
            f = -(((float) i) * this.optimalPageWidth);
            width = (float) (getWidth() / 2);
            f2 = this.optimalPageWidth;
        }
        return f + (width - (f2 / 2.0f));
    }

    private void calculateOptimalWidthAndHeight() {
        if (this.state != State.DEFAULT && getWidth() != 0) {
            float width = (float) getWidth();
            float height = (float) getHeight();
            float f = ((float) this.pageWidth) / ((float) this.pageHeight);
            float floor = (float) Math.floor((double) (width / f));
            if (floor > height) {
                width = (float) Math.floor((double) (f * height));
            } else {
                height = floor;
            }
            this.optimalPageWidth = width;
            this.optimalPageHeight = height;
        }
    }

    public void moveTo(float f, float f2) {
        moveTo(f, f2, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0168  */
    public void moveTo(float f, float f2, boolean z) {
        float f3;
        float height;
        float currentScale;
        float f4;
        float width;
        float currentScale2;
        if (this.swipeVertical) {
            if (toCurrentScale(this.optimalPageWidth) < ((float) getWidth())) {
                width = (float) (getWidth() / 2);
                currentScale2 = toCurrentScale(this.optimalPageWidth) / 2.0f;
            } else {
                if (f > 0.0f) {
                    f = 0.0f;
                } else if (toCurrentScale(this.optimalPageWidth) + f < ((float) getWidth())) {
                    width = (float) getWidth();
                    currentScale2 = toCurrentScale(this.optimalPageWidth);
                }
                if (((float) getPageCount()) * toCurrentScale(this.optimalPageHeight) >= ((float) getHeight())) {
                    f2 = (((float) getHeight()) - (((float) getPageCount()) * toCurrentScale(this.optimalPageHeight))) / 2.0f;
                } else if (f2 > 0.0f) {
                    f2 = 0.0f;
                } else if (toCurrentScale(((float) getPageCount()) * this.optimalPageHeight) + f2 < ((float) getHeight())) {
                    f2 = (-toCurrentScale(((float) getPageCount()) * this.optimalPageHeight)) + ((float) getHeight());
                }
                f4 = this.currentYOffset;
                if (f2 >= f4) {
                    this.scrollDir = ScrollDir.END;
                } else if (f2 > f4) {
                    this.scrollDir = ScrollDir.START;
                } else {
                    this.scrollDir = ScrollDir.NONE;
                }
            }
            f = width - currentScale2;
            if (((float) getPageCount()) * toCurrentScale(this.optimalPageHeight) >= ((float) getHeight())) {
            }
            f4 = this.currentYOffset;
            if (f2 >= f4) {
            }
        } else {
            if (toCurrentScale(this.optimalPageHeight) < ((float) getHeight())) {
                height = (float) (getHeight() / 2);
                currentScale = toCurrentScale(this.optimalPageHeight) / 2.0f;
            } else {
                if (f2 > 0.0f) {
                    f2 = 0.0f;
                } else if (toCurrentScale(this.optimalPageHeight) + f2 < ((float) getHeight())) {
                    height = (float) getHeight();
                    currentScale = toCurrentScale(this.optimalPageHeight);
                }
                if (((float) getPageCount()) * toCurrentScale(this.optimalPageWidth) >= ((float) getWidth())) {
                    f = (((float) getWidth()) - (((float) getPageCount()) * toCurrentScale(this.optimalPageWidth))) / 2.0f;
                } else if (f > 0.0f) {
                    f = 0.0f;
                } else if (toCurrentScale(((float) getPageCount()) * this.optimalPageWidth) + f < ((float) getWidth())) {
                    f = (-toCurrentScale(((float) getPageCount()) * this.optimalPageWidth)) + ((float) getWidth());
                }
                f3 = this.currentXOffset;
                if (f >= f3) {
                    this.scrollDir = ScrollDir.END;
                } else if (f > f3) {
                    this.scrollDir = ScrollDir.START;
                } else {
                    this.scrollDir = ScrollDir.NONE;
                }
            }
            f2 = height - currentScale;
            if (((float) getPageCount()) * toCurrentScale(this.optimalPageWidth) >= ((float) getWidth())) {
            }
            f3 = this.currentXOffset;
            if (f >= f3) {
            }
        }
        this.currentXOffset = f;
        this.currentYOffset = f2;
        float positionOffset = getPositionOffset();
        if (z && this.scrollHandle != null && !documentFitsView()) {
            this.scrollHandle.setScroll(positionOffset);
        }
        OnPageScrollListener onPageScrollListener2 = this.onPageScrollListener;
        if (onPageScrollListener2 != null) {
            onPageScrollListener2.onPageScrolled(getCurrentPage(), positionOffset);
        }
        redraw();
    }

    /* access modifiers changed from: package-private */
    public ScrollDir getScrollDir() {
        return this.scrollDir;
    }

    /* access modifiers changed from: package-private */
    public void loadPageByOffset() {
        float f;
        float f2;
        if (this.swipeVertical) {
            f2 = this.currentYOffset;
            f = this.optimalPageHeight;
        } else {
            f2 = this.currentXOffset;
            f = this.optimalPageWidth;
        }
        int floor = (int) Math.floor((double) ((Math.abs(f2) + ((float) (getHeight() / 5))) / toCurrentScale(f)));
        if (floor < 0 || floor > getPageCount() - 1 || floor == getCurrentPage()) {
            loadPages();
        } else {
            showPage(floor);
        }
    }

    /* access modifiers changed from: package-private */
    public int[] getFilteredUserPages() {
        return this.filteredUserPages;
    }

    /* access modifiers changed from: package-private */
    public int getDocumentPageCount() {
        return this.documentPageCount;
    }

    public void moveRelativeTo(float f, float f2) {
        moveTo(this.currentXOffset + f, this.currentYOffset + f2);
    }

    public void zoomTo(float f) {
        this.zoom = f;
    }

    public void zoomCenteredTo(float f, PointF pointF) {
        float f2 = f / this.zoom;
        zoomTo(f);
        moveTo((this.currentXOffset * f2) + (pointF.x - (pointF.x * f2)), (this.currentYOffset * f2) + (pointF.y - (pointF.y * f2)));
    }

    public void zoomCenteredRelativeTo(float f, PointF pointF) {
        zoomCenteredTo(this.zoom * f, pointF);
    }

    public boolean documentFitsView() {
        if (this.swipeVertical) {
            if (((float) getPageCount()) * this.optimalPageHeight < ((float) getHeight())) {
                return true;
            }
            return false;
        } else if (((float) getPageCount()) * this.optimalPageWidth < ((float) getWidth())) {
            return true;
        } else {
            return false;
        }
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public float getCurrentXOffset() {
        return this.currentXOffset;
    }

    public float getCurrentYOffset() {
        return this.currentYOffset;
    }

    public float toCurrentScale(float f) {
        return f * this.zoom;
    }

    public float getZoom() {
        return this.zoom;
    }

    public boolean isZooming() {
        return this.zoom != this.minZoom;
    }

    public float getOptimalPageWidth() {
        return this.optimalPageWidth;
    }

    public float getOptimalPageHeight() {
        return this.optimalPageHeight;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDefaultPage(int i) {
        this.defaultPage = i;
    }

    public void resetZoomWithAnimation() {
        zoomWithAnimation(this.minZoom);
    }

    public void zoomWithAnimation(float f, float f2, float f3) {
        this.animationManager.startZoomAnimation(f, f2, this.zoom, f3);
    }

    public void zoomWithAnimation(float f) {
        this.animationManager.startZoomAnimation((float) (getWidth() / 2), (float) (getHeight() / 2), this.zoom, f);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScrollHandle(ScrollHandle scrollHandle2) {
        this.scrollHandle = scrollHandle2;
    }

    public float getMinZoom() {
        return this.minZoom;
    }

    public void setMinZoom(float f) {
        this.minZoom = f;
    }

    public float getMidZoom() {
        return this.midZoom;
    }

    public void setMidZoom(float f) {
        this.midZoom = f;
    }

    public float getMaxZoom() {
        return this.maxZoom;
    }

    public void setMaxZoom(float f) {
        this.maxZoom = f;
    }

    public void useBestQuality(boolean z) {
        this.bestQuality = z;
    }

    public boolean isBestQuality() {
        return this.bestQuality;
    }

    public boolean isSwipeVertical() {
        return this.swipeVertical;
    }

    public void setSwipeVertical(boolean z) {
        this.swipeVertical = z;
    }

    public void enableAnnotationRendering(boolean z) {
        this.annotationRendering = z;
    }

    public boolean isAnnotationRendering() {
        return this.annotationRendering;
    }

    public PdfDocument.Meta getDocumentMeta() {
        PdfDocument pdfDocument2 = this.pdfDocument;
        if (pdfDocument2 == null) {
            return null;
        }
        return this.pdfiumCore.getDocumentMeta(pdfDocument2);
    }

    public List<PdfDocument.Bookmark> getTableOfContents() {
        PdfDocument pdfDocument2 = this.pdfDocument;
        if (pdfDocument2 == null) {
            return new ArrayList();
        }
        return this.pdfiumCore.getTableOfContents(pdfDocument2);
    }

    public Configurator fromAsset(String str) {
        return new Configurator(new AssetSource(str));
    }

    public Configurator fromStream(InputStream inputStream) {
        return new Configurator(new InputStreamSource(inputStream));
    }

    public class Configurator {
        private boolean annotationRendering;
        private int defaultPage;
        private final DocumentSource documentSource;
        private boolean enableDoubletap;
        private boolean enableSwipe;
        private OnDrawListener onDrawListener;
        private OnErrorListener onErrorListener;
        private OnLoadCompleteListener onLoadCompleteListener;
        private OnPageChangeListener onPageChangeListener;
        private OnPageScrollListener onPageScrollListener;
        private int[] pageNumbers;
        private String password;
        private ScrollHandle scrollHandle;
        private boolean swipeHorizontal;

        private Configurator(DocumentSource documentSource2) {
            this.pageNumbers = null;
            this.enableSwipe = true;
            this.enableDoubletap = true;
            this.defaultPage = 0;
            this.swipeHorizontal = false;
            this.annotationRendering = false;
            this.password = null;
            this.scrollHandle = null;
            this.documentSource = documentSource2;
        }

        public Configurator enableSwipe(boolean z) {
            this.enableSwipe = z;
            return this;
        }

        public Configurator enableDoubletap(boolean z) {
            this.enableDoubletap = z;
            return this;
        }

        public Configurator enableAnnotationRendering(boolean z) {
            this.annotationRendering = z;
            return this;
        }

        public Configurator onDraw(OnDrawListener onDrawListener2) {
            this.onDrawListener = onDrawListener2;
            return this;
        }

        public Configurator onLoad(OnLoadCompleteListener onLoadCompleteListener2) {
            this.onLoadCompleteListener = onLoadCompleteListener2;
            return this;
        }

        public Configurator onError(OnErrorListener onErrorListener2) {
            this.onErrorListener = onErrorListener2;
            return this;
        }

        public Configurator onPageChange(OnPageChangeListener onPageChangeListener2) {
            this.onPageChangeListener = onPageChangeListener2;
            return this;
        }

        public Configurator defaultPage(int i) {
            this.defaultPage = i;
            return this;
        }

        public Configurator password(String str) {
            this.password = str;
            return this;
        }

        public Configurator scrollHandle(ScrollHandle scrollHandle2) {
            this.scrollHandle = scrollHandle2;
            return this;
        }

        public void load() {
            PDFView.this.recycle();
            PDFView.this.setOnDrawListener(this.onDrawListener);
            PDFView.this.setOnPageChangeListener(this.onPageChangeListener);
            PDFView.this.setOnPageScrollListener(this.onPageScrollListener);
            PDFView.this.enableSwipe(this.enableSwipe);
            PDFView.this.enableDoubletap(this.enableDoubletap);
            PDFView.this.setDefaultPage(this.defaultPage);
            PDFView.this.setSwipeVertical(!this.swipeHorizontal);
            PDFView.this.enableAnnotationRendering(this.annotationRendering);
            PDFView.this.setScrollHandle(this.scrollHandle);
            PDFView.this.dragPinchManager.setSwipeVertical(PDFView.this.swipeVertical);
            int[] iArr = this.pageNumbers;
            if (iArr != null) {
                PDFView.this.load(this.documentSource, this.password, this.onLoadCompleteListener, this.onErrorListener, iArr);
            } else {
                PDFView.this.load(this.documentSource, this.password, this.onLoadCompleteListener, this.onErrorListener);
            }
        }
    }
}
