package com.truenet.android;

import a.a.a.g;
import a.a.b.b.h;
import com.startapp.common.c.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public final class LinksData {
    private final boolean bulkResponse;
    private final long maxRedirectTime;
    private final int numOfRedirect;
    private final long sleep;
    private final int validateParallel;
    @f(b = ArrayList.class, c = Link.class)
    private final List<Link> validation;

    public static /* synthetic */ LinksData copy$default(LinksData linksData, long j, int i, boolean z, int i2, long j2, List list, int i3, Object obj) {
        return linksData.copy((i3 & 1) != 0 ? linksData.sleep : j, (i3 & 2) != 0 ? linksData.validateParallel : i, (i3 & 4) != 0 ? linksData.bulkResponse : z, (i3 & 8) != 0 ? linksData.numOfRedirect : i2, (i3 & 16) != 0 ? linksData.maxRedirectTime : j2, (i3 & 32) != 0 ? linksData.validation : list);
    }

    public final long component1() {
        return this.sleep;
    }

    public final int component2() {
        return this.validateParallel;
    }

    public final boolean component3() {
        return this.bulkResponse;
    }

    public final int component4() {
        return this.numOfRedirect;
    }

    public final long component5() {
        return this.maxRedirectTime;
    }

    public final List<Link> component6() {
        return this.validation;
    }

    public final LinksData copy(long j, int i, boolean z, int i2, long j2, List<Link> list) {
        h.b(list, "validation");
        return new LinksData(j, i, z, i2, j2, list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof LinksData) {
                LinksData linksData = (LinksData) obj;
                if (this.sleep == linksData.sleep) {
                    if (this.validateParallel == linksData.validateParallel) {
                        if (this.bulkResponse == linksData.bulkResponse) {
                            if (this.numOfRedirect == linksData.numOfRedirect) {
                                if (!(this.maxRedirectTime == linksData.maxRedirectTime) || !h.a(this.validation, linksData.validation)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.sleep;
        int i = ((((int) (j ^ (j >>> 32))) * 31) + this.validateParallel) * 31;
        boolean z = this.bulkResponse;
        if (z) {
            z = true;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = z ? 1 : 0;
        long j2 = this.maxRedirectTime;
        int i5 = (((((i + i2) * 31) + this.numOfRedirect) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        List<Link> list = this.validation;
        return i5 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "LinksData(sleep=" + this.sleep + ", validateParallel=" + this.validateParallel + ", bulkResponse=" + this.bulkResponse + ", numOfRedirect=" + this.numOfRedirect + ", maxRedirectTime=" + this.maxRedirectTime + ", validation=" + this.validation + ")";
    }

    public LinksData(long j, int i, boolean z, int i2, long j2, List<Link> list) {
        h.b(list, "validation");
        this.sleep = j;
        this.validateParallel = i;
        this.bulkResponse = z;
        this.numOfRedirect = i2;
        this.maxRedirectTime = j2;
        this.validation = list;
    }

    public final long getSleep() {
        return this.sleep;
    }

    public final int getValidateParallel() {
        return this.validateParallel;
    }

    public final boolean getBulkResponse() {
        return this.bulkResponse;
    }

    public final int getNumOfRedirect() {
        return this.numOfRedirect;
    }

    public final long getMaxRedirectTime() {
        return this.maxRedirectTime;
    }

    public final List<Link> getValidation() {
        return this.validation;
    }

    public LinksData() {
        this(0, 0, false, 0, 0, g.a());
    }
}
