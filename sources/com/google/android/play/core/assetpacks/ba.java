package com.google.android.play.core.assetpacks;

import java.util.Map;

final class ba extends AssetPackStates {

    /* renamed from: a  reason: collision with root package name */
    private final long f79a;
    private final Map<String, AssetPackState> b;

    ba(long j, Map<String, AssetPackState> map) {
        this.f79a = j;
        this.b = map;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackStates) {
            AssetPackStates assetPackStates = (AssetPackStates) obj;
            return this.f79a == assetPackStates.totalBytes() && this.b.equals(assetPackStates.packStates());
        }
    }

    public final int hashCode() {
        long j = this.f79a;
        return this.b.hashCode() ^ ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackStates
    public final Map<String, AssetPackState> packStates() {
        return this.b;
    }

    public final String toString() {
        long j = this.f79a;
        String valueOf = String.valueOf(this.b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 61);
        sb.append("AssetPackStates{totalBytes=");
        sb.append(j);
        sb.append(", packStates=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackStates
    public final long totalBytes() {
        return this.f79a;
    }
}
