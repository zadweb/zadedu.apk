package com.google.android.play.core.assetpacks;

/* access modifiers changed from: package-private */
public final class cy {

    /* renamed from: a  reason: collision with root package name */
    private final int f124a;
    private final String b;
    private final long c;
    private final long d;
    private final int e;

    cy() {
    }

    cy(int i, String str, long j, long j2, int i2) {
        this();
        this.f124a = i;
        this.b = str;
        this.c = j;
        this.d = j2;
        this.e = i2;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.f124a;
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public long c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public long d() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof cy) {
            cy cyVar = (cy) obj;
            return this.f124a == cyVar.a() && ((str = this.b) != null ? str.equals(cyVar.b()) : cyVar.b() == null) && this.c == cyVar.c() && this.d == cyVar.d() && this.e == cyVar.e();
        }
    }

    public int hashCode() {
        int i = (this.f124a ^ 1000003) * 1000003;
        String str = this.b;
        int hashCode = str == null ? 0 : str.hashCode();
        long j = this.c;
        long j2 = this.d;
        return ((((((i ^ hashCode) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.e;
    }

    public String toString() {
        int i = this.f124a;
        String str = this.b;
        long j = this.c;
        long j2 = this.d;
        int i2 = this.e;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 157);
        sb.append("SliceCheckpoint{fileExtractionStatus=");
        sb.append(i);
        sb.append(", filePath=");
        sb.append(str);
        sb.append(", fileOffset=");
        sb.append(j);
        sb.append(", remainingBytes=");
        sb.append(j2);
        sb.append(", previousChunk=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }
}
