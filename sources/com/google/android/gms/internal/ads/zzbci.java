package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbci extends zzbab<Long> implements zzbbt<Long>, RandomAccess {
    private static final zzbci zzdvt;
    private int size;
    private long[] zzdvu;

    static {
        zzbci zzbci = new zzbci();
        zzdvt = zzbci;
        zzbci.zzaaz();
    }

    zzbci() {
        this(new long[10], 0);
    }

    private zzbci(long[] jArr, int i) {
        this.zzdvu = jArr;
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

    private final void zzk(int i, long j) {
        int i2;
        zzaba();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
        long[] jArr = this.zzdvu;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.zzdvu, i, jArr2, i + 1, this.size - i);
            this.zzdvu = jArr2;
        }
        this.zzdvu[i] = j;
        this.size++;
        this.modCount++;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [int, java.lang.Object] */
    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final /* synthetic */ void add(int i, Long l) {
        zzk(i, l.longValue());
    }

    @Override // java.util.AbstractCollection, java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.Collection
    public final boolean addAll(Collection<? extends Long> collection) {
        zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbci)) {
            return super.addAll(collection);
        }
        zzbci zzbci = (zzbci) collection;
        int i = zzbci.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zzdvu;
            if (i3 > jArr.length) {
                this.zzdvu = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzbci.zzdvu, 0, this.zzdvu, this.size, zzbci.size);
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
        if (!(obj instanceof zzbci)) {
            return super.equals(obj);
        }
        zzbci zzbci = (zzbci) obj;
        if (this.size != zzbci.size) {
            return false;
        }
        long[] jArr = zzbci.zzdvu;
        for (int i = 0; i < this.size; i++) {
            if (this.zzdvu[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List, java.util.AbstractList
    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    public final long getLong(int i) {
        zzbk(i);
        return this.zzdvu[i];
    }

    @Override // com.google.android.gms.internal.ads.zzbab
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbbq.zzv(this.zzdvu[i2]);
        }
        return i;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final /* synthetic */ Long remove(int i) {
        zzaba();
        zzbk(i);
        long[] jArr = this.zzdvu;
        long j = jArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, i2 - i);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab
    public final boolean remove(Object obj) {
        zzaba();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzdvu[i]))) {
                long[] jArr = this.zzdvu;
                System.arraycopy(jArr, i + 1, jArr, i, this.size - i);
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
            long[] jArr = this.zzdvu;
            System.arraycopy(jArr, i2, jArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [int, java.lang.Object] */
    @Override // java.util.List, com.google.android.gms.internal.ads.zzbab, java.util.AbstractList
    public final /* synthetic */ Long set(int i, Long l) {
        long longValue = l.longValue();
        zzaba();
        zzbk(i);
        long[] jArr = this.zzdvu;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    public final int size() {
        return this.size;
    }

    /* Return type fixed from 'com.google.android.gms.internal.ads.zzbbt' to match base method */
    @Override // com.google.android.gms.internal.ads.zzbbt
    public final /* synthetic */ zzbbt<Long> zzbm(int i) {
        if (i >= this.size) {
            return new zzbci(Arrays.copyOf(this.zzdvu, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzw(long j) {
        zzk(this.size, j);
    }
}
