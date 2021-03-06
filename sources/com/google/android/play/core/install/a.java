package com.google.android.play.core.install;

/* access modifiers changed from: package-private */
public final class a extends InstallState {

    /* renamed from: a  reason: collision with root package name */
    private final int f152a;
    private final long b;
    private final long c;
    private final int d;
    private final String e;

    a(int i, long j, long j2, int i2, String str) {
        this.f152a = i;
        this.b = j;
        this.c = j2;
        this.d = i2;
        if (str != null) {
            this.e = str;
            return;
        }
        throw new NullPointerException("Null packageName");
    }

    @Override // com.google.android.play.core.install.InstallState
    public final long bytesDownloaded() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InstallState) {
            InstallState installState = (InstallState) obj;
            return this.f152a == installState.installStatus() && this.b == installState.bytesDownloaded() && this.c == installState.totalBytesToDownload() && this.d == installState.installErrorCode() && this.e.equals(installState.packageName());
        }
    }

    public final int hashCode() {
        int i = this.f152a;
        long j = this.b;
        long j2 = this.c;
        return ((((((((i ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.d) * 1000003) ^ this.e.hashCode();
    }

    @Override // com.google.android.play.core.install.InstallState
    public final int installErrorCode() {
        return this.d;
    }

    @Override // com.google.android.play.core.install.InstallState
    public final int installStatus() {
        return this.f152a;
    }

    @Override // com.google.android.play.core.install.InstallState
    public final String packageName() {
        return this.e;
    }

    public final String toString() {
        int i = this.f152a;
        long j = this.b;
        long j2 = this.c;
        int i2 = this.d;
        String str = this.e;
        StringBuilder sb = new StringBuilder(str.length() + 164);
        sb.append("InstallState{installStatus=");
        sb.append(i);
        sb.append(", bytesDownloaded=");
        sb.append(j);
        sb.append(", totalBytesToDownload=");
        sb.append(j2);
        sb.append(", installErrorCode=");
        sb.append(i2);
        sb.append(", packageName=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.play.core.install.InstallState
    public final long totalBytesToDownload() {
        return this.c;
    }
}
