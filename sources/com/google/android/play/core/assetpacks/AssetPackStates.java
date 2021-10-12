package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AssetPackStates {
    public static AssetPackStates a(long j, Map<String, AssetPackState> map) {
        return new ba(j, map);
    }

    public static AssetPackStates b(Bundle bundle, bo boVar) {
        return c(bundle, boVar, new ArrayList());
    }

    public static AssetPackStates c(Bundle bundle, bo boVar, List<String> list) {
        return e(bundle, boVar, list, at.f72a);
    }

    private static AssetPackStates e(Bundle bundle, bo boVar, List<String> list, as asVar) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        HashMap hashMap = new HashMap();
        int size = stringArrayList.size();
        for (int i = 0; i < size; i++) {
            String str = stringArrayList.get(i);
            hashMap.put(str, AssetPackState.d(bundle, str, boVar, asVar));
        }
        for (String str2 : list) {
            hashMap.put(str2, AssetPackState.c(str2, 4, 0, 0, 0, 0.0d, 1, ""));
        }
        return a(bundle.getLong("total_bytes_to_download"), hashMap);
    }

    public abstract Map<String, AssetPackState> packStates();

    public abstract long totalBytes();
}
