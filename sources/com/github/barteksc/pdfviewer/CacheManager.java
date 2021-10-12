package com.github.barteksc.pdfviewer;

import android.graphics.RectF;
import com.github.barteksc.pdfviewer.model.PagePart;
import com.github.barteksc.pdfviewer.util.Constants;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/* access modifiers changed from: package-private */
public class CacheManager {
    private final PriorityQueue<PagePart> activeCache = new PriorityQueue<>(Constants.Cache.CACHE_SIZE, this.comparator);
    private final PagePartComparator comparator = new PagePartComparator();
    private final Object passiveActiveLock = new Object();
    private final PriorityQueue<PagePart> passiveCache = new PriorityQueue<>(Constants.Cache.CACHE_SIZE, this.comparator);
    private final List<PagePart> thumbnails = new ArrayList();

    public void cachePart(PagePart pagePart) {
        synchronized (this.passiveActiveLock) {
            makeAFreeSpace();
            this.activeCache.offer(pagePart);
        }
    }

    public void makeANewSet() {
        synchronized (this.passiveActiveLock) {
            this.passiveCache.addAll(this.activeCache);
            this.activeCache.clear();
        }
    }

    private void makeAFreeSpace() {
        synchronized (this.passiveActiveLock) {
            while (this.activeCache.size() + this.passiveCache.size() >= Constants.Cache.CACHE_SIZE && !this.passiveCache.isEmpty()) {
                this.passiveCache.poll().getRenderedBitmap().recycle();
            }
            while (this.activeCache.size() + this.passiveCache.size() >= Constants.Cache.CACHE_SIZE && !this.activeCache.isEmpty()) {
                this.activeCache.poll().getRenderedBitmap().recycle();
            }
        }
    }

    public void cacheThumbnail(PagePart pagePart) {
        synchronized (this.thumbnails) {
            if (this.thumbnails.size() >= 6) {
                this.thumbnails.remove(0).getRenderedBitmap().recycle();
            }
            this.thumbnails.add(pagePart);
        }
    }

    public boolean upPartIfContained(int i, int i2, float f, float f2, RectF rectF, int i3) {
        PagePart pagePart = new PagePart(i, i2, null, f, f2, rectF, false, 0);
        synchronized (this.passiveActiveLock) {
            PagePart find = find(this.passiveCache, pagePart);
            boolean z = true;
            if (find != null) {
                this.passiveCache.remove(find);
                find.setCacheOrder(i3);
                this.activeCache.offer(find);
                return true;
            }
            if (find(this.activeCache, pagePart) == null) {
                z = false;
            }
            return z;
        }
    }

    public boolean containsThumbnail(int i, int i2, float f, float f2, RectF rectF) {
        PagePart pagePart = new PagePart(i, i2, null, f, f2, rectF, true, 0);
        synchronized (this.thumbnails) {
            for (PagePart pagePart2 : this.thumbnails) {
                if (pagePart2.equals(pagePart)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static PagePart find(PriorityQueue<PagePart> priorityQueue, PagePart pagePart) {
        Iterator<PagePart> it = priorityQueue.iterator();
        while (it.hasNext()) {
            PagePart next = it.next();
            if (next.equals(pagePart)) {
                return next;
            }
        }
        return null;
    }

    public List<PagePart> getPageParts() {
        ArrayList arrayList;
        synchronized (this.passiveActiveLock) {
            arrayList = new ArrayList(this.passiveCache);
            arrayList.addAll(this.activeCache);
        }
        return arrayList;
    }

    public List<PagePart> getThumbnails() {
        List<PagePart> list;
        synchronized (this.thumbnails) {
            list = this.thumbnails;
        }
        return list;
    }

    public void recycle() {
        synchronized (this.passiveActiveLock) {
            Iterator<PagePart> it = this.passiveCache.iterator();
            while (it.hasNext()) {
                it.next().getRenderedBitmap().recycle();
            }
            this.passiveCache.clear();
            Iterator<PagePart> it2 = this.activeCache.iterator();
            while (it2.hasNext()) {
                it2.next().getRenderedBitmap().recycle();
            }
            this.activeCache.clear();
        }
        synchronized (this.thumbnails) {
            for (PagePart pagePart : this.thumbnails) {
                pagePart.getRenderedBitmap().recycle();
            }
            this.thumbnails.clear();
        }
    }

    class PagePartComparator implements Comparator<PagePart> {
        PagePartComparator() {
        }

        public int compare(PagePart pagePart, PagePart pagePart2) {
            if (pagePart.getCacheOrder() == pagePart2.getCacheOrder()) {
                return 0;
            }
            return pagePart.getCacheOrder() > pagePart2.getCacheOrder() ? 1 : -1;
        }
    }
}
