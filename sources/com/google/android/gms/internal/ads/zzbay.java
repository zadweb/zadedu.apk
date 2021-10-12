package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbay extends zzbab<Double> implements zzbbt<Double>, RandomAccess {
    private static final zzbay zzdqo;
    private int size;
    private double[] zzdqp;

    static {
        zzbay zzbay = new zzbay();
        zzdqo = zzbay;
        zzbay.zzaaz();
    }

    zzbay() {
        this(new double[10], 0);
    }

    private zzbay(double[] dArr, int i) {
        this.zzdqp = dArr;
        this.size = i;
    }

    private final void zzbk(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
    }

    private final String zzbl(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    private final void zzc(int i, double d) {
        int i2;
        zzaba();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
        double[] dArr = this.zzdqp;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zzdqp, i, dArr2, i + 1, this.size - i);
            this.zzdqp = dArr2;
        }
        this.zzdqp[i] = d;
        this.size++;
        this.modCount++;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [int, java.lang.Object] */
    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final /* synthetic */ void add(int i, Double d) {
        zzc(i, d.doubleValue());
    }

    @Override // java.util.AbstractCollection, java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.Collection
    public final boolean addAll(Collection<? extends Double> collection) {
        zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbay)) {
            return super.addAll(collection);
        }
        zzbay zzbay = (zzbay) collection;
        int i = zzbay.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzdqp;
            if (i3 > dArr.length) {
                this.zzdqp = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzbay.zzdqp, 0, this.zzdqp, this.size, zzbay.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // com.google.android.gms.internal.ads.zzbab
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbay)) {
            return super.equals(obj);
        }
        zzbay zzbay = (zzbay) obj;
        if (this.size != zzbay.size) {
            return false;
        }
        double[] dArr = zzbay.zzdqp;
        for (int i = 0; i < this.size; i++) {
            if (this.zzdqp[i] != dArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List, java.util.AbstractList
    public final /* synthetic */ Object get(int i) {
        zzbk(i);
        return Double.valueOf(this.zzdqp[i]);
    }

    @Override // com.google.android.gms.internal.ads.zzbab
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbbq.zzv(Double.doubleToLongBits(this.zzdqp[i2]));
        }
        return i;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final /* synthetic */ Double remove(int i) {
        zzaba();
        zzbk(i);
        double[] dArr = this.zzdqp;
        double d = dArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, i2 - i);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab
    public final boolean remove(Object obj) {
        zzaba();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzdqp[i]))) {
                double[] dArr = this.zzdqp;
                System.arraycopy(dArr, i + 1, dArr, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzaba();
        if (i2 >= i) {
            double[] dArr = this.zzdqp;
            System.arraycopy(dArr, i2, dArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [int, java.lang.Object] */
    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final /* synthetic */ Double set(int i, Double d) {
        double doubleValue = d.doubleValue();
        zzaba();
        zzbk(i);
        double[] dArr = this.zzdqp;
        double d2 = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d2);
    }

    public final int size() {
        return this.size;
    }

    /* Return type fixed from 'com.google.android.gms.internal.ads.zzbbt' to match base method */
    @Override // com.google.android.gms.internal.ads.zzbbt
    public final /* synthetic */ zzbbt<Double> zzbm(int i) {
        if (i >= this.size) {
            return new zzbay(Arrays.copyOf(this.zzdqp, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzd(double d) {
        zzc(this.size, d);
    }
}
