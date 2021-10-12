package com.github.barteksc.pdfviewer;

import android.graphics.RectF;
import android.util.Pair;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.Constants;
import com.github.barteksc.pdfviewer.util.MathUtils;

/* access modifiers changed from: package-private */
public class PagesLoader {
    private int cacheOrder;
    private float colWidth;
    private Pair<Integer, Integer> colsRows;
    private float pageRelativePartHeight;
    private float pageRelativePartWidth;
    private float partRenderHeight;
    private float partRenderWidth;
    private PDFView pdfView;
    private float rowHeight;
    private float scaledHeight;
    private float scaledWidth;
    private int thumbnailHeight;
    private final RectF thumbnailRect = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
    private int thumbnailWidth;
    private float xOffset;
    private float yOffset;

    /* access modifiers changed from: private */
    public class Holder {
        int col;
        int page;
        int row;

        private Holder() {
        }
    }

    public PagesLoader(PDFView pDFView) {
        this.pdfView = pDFView;
    }

    private Pair<Integer, Integer> getPageColsRows() {
        return new Pair<>(Integer.valueOf(MathUtils.ceil(1.0f / (((1.0f / this.pdfView.getOptimalPageWidth()) * 256.0f) / this.pdfView.getZoom()))), Integer.valueOf(MathUtils.ceil(1.0f / (((1.0f / this.pdfView.getOptimalPageHeight()) * 256.0f) / this.pdfView.getZoom()))));
    }

    private int documentPage(int i) {
        int i2;
        if (this.pdfView.getFilteredUserPages() == null) {
            i2 = i;
        } else if (i < 0 || i >= this.pdfView.getFilteredUserPages().length) {
            return -1;
        } else {
            i2 = this.pdfView.getFilteredUserPages()[i];
        }
        if (i2 < 0 || i >= this.pdfView.getDocumentPageCount()) {
            return -1;
        }
        return i2;
    }

    private Holder getPageAndCoordsByOffset(float f) {
        Holder holder = new Holder();
        float f2 = -MathUtils.max(f, 0.0f);
        if (this.pdfView.isSwipeVertical()) {
            holder.page = MathUtils.floor(f2 / this.scaledHeight);
            holder.row = MathUtils.floor(Math.abs(f2 - (this.scaledHeight * ((float) holder.page))) / this.rowHeight);
            holder.col = MathUtils.floor(this.xOffset / this.colWidth);
        } else {
            holder.page = MathUtils.floor(f2 / this.scaledWidth);
            holder.col = MathUtils.floor(Math.abs(f2 - (this.scaledWidth * ((float) holder.page))) / this.colWidth);
            holder.row = MathUtils.floor(this.yOffset / this.rowHeight);
        }
        return holder;
    }

    private void loadThumbnail(int i, int i2) {
        if (!this.pdfView.cacheManager.containsThumbnail(i, i2, (float) this.thumbnailWidth, (float) this.thumbnailHeight, this.thumbnailRect)) {
            this.pdfView.renderingAsyncTask.addRenderingTask(i, i2, (float) this.thumbnailWidth, (float) this.thumbnailHeight, this.thumbnailRect, true, 0, this.pdfView.isBestQuality(), this.pdfView.isAnnotationRendering());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0044 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045  */
    private int loadRelative(int i, int i2, boolean z) {
        int i3;
        float f;
        float f2;
        int documentPage;
        int i4 = 0;
        if (this.pdfView.isSwipeVertical()) {
            f2 = (this.rowHeight * ((float) i)) + 1.0f;
            f = this.pdfView.getCurrentYOffset();
            if (z) {
                i3 = this.pdfView.getHeight();
                Holder pageAndCoordsByOffset = getPageAndCoordsByOffset((f - ((float) i3)) - f2);
                documentPage = documentPage(pageAndCoordsByOffset.page);
                if (documentPage < 0) {
                    return 0;
                }
                loadThumbnail(pageAndCoordsByOffset.page, documentPage);
                if (this.pdfView.isSwipeVertical()) {
                    int max = MathUtils.max(MathUtils.ceil((this.xOffset + ((float) this.pdfView.getWidth())) / this.colWidth) + 1, ((Integer) this.colsRows.first).intValue());
                    for (int min = MathUtils.min(MathUtils.floor(this.xOffset / this.colWidth) - 1, 0); min <= max; min++) {
                        if (loadCell(pageAndCoordsByOffset.page, documentPage, pageAndCoordsByOffset.row, min, this.pageRelativePartWidth, this.pageRelativePartHeight)) {
                            i4++;
                        }
                        if (i4 >= i2) {
                            return i4;
                        }
                    }
                } else {
                    int max2 = MathUtils.max(MathUtils.ceil((this.yOffset + ((float) this.pdfView.getHeight())) / this.rowHeight) + 1, ((Integer) this.colsRows.second).intValue());
                    for (int min2 = MathUtils.min(MathUtils.floor(this.yOffset / this.rowHeight) - 1, 0); min2 <= max2; min2++) {
                        if (loadCell(pageAndCoordsByOffset.page, documentPage, min2, pageAndCoordsByOffset.col, this.pageRelativePartWidth, this.pageRelativePartHeight)) {
                            i4++;
                        }
                        if (i4 >= i2) {
                            return i4;
                        }
                    }
                }
                return i4;
            }
        } else {
            f2 = this.colWidth * ((float) i);
            f = this.pdfView.getCurrentXOffset();
            if (z) {
                i3 = this.pdfView.getWidth();
                Holder pageAndCoordsByOffset2 = getPageAndCoordsByOffset((f - ((float) i3)) - f2);
                documentPage = documentPage(pageAndCoordsByOffset2.page);
                if (documentPage < 0) {
                }
            }
        }
        i3 = 0;
        Holder pageAndCoordsByOffset22 = getPageAndCoordsByOffset((f - ((float) i3)) - f2);
        documentPage = documentPage(pageAndCoordsByOffset22.page);
        if (documentPage < 0) {
        }
    }

    public int loadVisible() {
        int i;
        Holder holder;
        int i2;
        int i3;
        if (this.pdfView.isSwipeVertical()) {
            holder = getPageAndCoordsByOffset(this.pdfView.getCurrentYOffset());
            Holder pageAndCoordsByOffset = getPageAndCoordsByOffset((this.pdfView.getCurrentYOffset() - ((float) this.pdfView.getHeight())) + 1.0f);
            if (holder.page == pageAndCoordsByOffset.page) {
                i3 = (pageAndCoordsByOffset.row - holder.row) + 1;
            } else {
                int intValue = (((Integer) this.colsRows.second).intValue() - holder.row) + 0;
                int i4 = holder.page;
                while (true) {
                    i4++;
                    if (i4 >= pageAndCoordsByOffset.page) {
                        break;
                    }
                    intValue += ((Integer) this.colsRows.second).intValue();
                }
                i3 = pageAndCoordsByOffset.row + 1 + intValue;
            }
            i = 0;
            for (int i5 = 0; i5 < i3 && i < Constants.Cache.CACHE_SIZE; i5++) {
                i += loadRelative(i5, Constants.Cache.CACHE_SIZE - i, false);
            }
        } else {
            holder = getPageAndCoordsByOffset(this.pdfView.getCurrentXOffset());
            Holder pageAndCoordsByOffset2 = getPageAndCoordsByOffset((this.pdfView.getCurrentXOffset() - ((float) this.pdfView.getWidth())) + 1.0f);
            if (holder.page == pageAndCoordsByOffset2.page) {
                i2 = (pageAndCoordsByOffset2.col - holder.col) + 1;
            } else {
                int intValue2 = (((Integer) this.colsRows.first).intValue() - holder.col) + 0;
                int i6 = holder.page;
                while (true) {
                    i6++;
                    if (i6 >= pageAndCoordsByOffset2.page) {
                        break;
                    }
                    intValue2 += ((Integer) this.colsRows.first).intValue();
                }
                i2 = pageAndCoordsByOffset2.col + 1 + intValue2;
            }
            i = 0;
            for (int i7 = 0; i7 < i2 && i < Constants.Cache.CACHE_SIZE; i7++) {
                i += loadRelative(i7, Constants.Cache.CACHE_SIZE - i, false);
            }
        }
        int documentPage = documentPage(holder.page - 1);
        if (documentPage >= 0) {
            loadThumbnail(holder.page - 1, documentPage);
        }
        int documentPage2 = documentPage(holder.page + 1);
        if (documentPage2 >= 0) {
            loadThumbnail(holder.page + 1, documentPage2);
        }
        return i;
    }

    private boolean loadCell(int i, int i2, int i3, int i4, float f, float f2) {
        float f3 = ((float) i4) * f;
        float f4 = ((float) i3) * f2;
        float f5 = this.partRenderWidth;
        float f6 = this.partRenderHeight;
        float f7 = f3 + f > 1.0f ? 1.0f - f3 : f;
        float f8 = f4 + f2 > 1.0f ? 1.0f - f4 : f2;
        float f9 = f5 * f7;
        float f10 = f6 * f8;
        RectF rectF = new RectF(f3, f4, f7 + f3, f8 + f4);
        if (f9 <= 0.0f || f10 <= 0.0f) {
            return false;
        }
        if (!this.pdfView.cacheManager.upPartIfContained(i, i2, f9, f10, rectF, this.cacheOrder)) {
            this.pdfView.renderingAsyncTask.addRenderingTask(i, i2, f9, f10, rectF, false, this.cacheOrder, this.pdfView.isBestQuality(), this.pdfView.isAnnotationRendering());
        }
        this.cacheOrder++;
        return true;
    }

    public void loadPages() {
        PDFView pDFView = this.pdfView;
        this.scaledHeight = pDFView.toCurrentScale(pDFView.getOptimalPageHeight());
        PDFView pDFView2 = this.pdfView;
        this.scaledWidth = pDFView2.toCurrentScale(pDFView2.getOptimalPageWidth());
        this.thumbnailWidth = (int) (this.pdfView.getOptimalPageWidth() * 0.3f);
        this.thumbnailHeight = (int) (this.pdfView.getOptimalPageHeight() * 0.3f);
        this.colsRows = getPageColsRows();
        this.xOffset = -MathUtils.max(this.pdfView.getCurrentXOffset(), 0.0f);
        this.yOffset = -MathUtils.max(this.pdfView.getCurrentYOffset(), 0.0f);
        this.rowHeight = this.scaledHeight / ((float) ((Integer) this.colsRows.second).intValue());
        this.colWidth = this.scaledWidth / ((float) ((Integer) this.colsRows.first).intValue());
        this.pageRelativePartWidth = 1.0f / ((float) ((Integer) this.colsRows.first).intValue());
        float intValue = 1.0f / ((float) ((Integer) this.colsRows.second).intValue());
        this.pageRelativePartHeight = intValue;
        this.partRenderWidth = 256.0f / this.pageRelativePartWidth;
        this.partRenderHeight = 256.0f / intValue;
        this.cacheOrder = 1;
        int loadVisible = loadVisible();
        if (this.pdfView.getScrollDir().equals(PDFView.ScrollDir.END)) {
            for (int i = 0; i < 7 && loadVisible < Constants.Cache.CACHE_SIZE; i++) {
                loadVisible += loadRelative(i, loadVisible, true);
            }
            return;
        }
        for (int i2 = 0; i2 > -7 && loadVisible < Constants.Cache.CACHE_SIZE; i2--) {
            loadVisible += loadRelative(i2, loadVisible, false);
        }
    }
}
