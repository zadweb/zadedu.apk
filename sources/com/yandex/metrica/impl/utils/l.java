package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.impl.bk;
import java.util.HashMap;
import java.util.Map;

public class l {
    public static String a(Map<String, String> map) {
        if (bk.a(map)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey())) {
                sb.append(entry.getKey());
                sb.append(":");
                sb.append(entry.getValue());
                sb.append(",");
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static Map<String, String> a(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(",");
            for (String str2 : split) {
                int indexOf = str2.indexOf(":");
                if (indexOf != -1) {
                    String substring = str2.substring(0, indexOf);
                    if (!TextUtils.isEmpty(substring)) {
                        hashMap.put(substring, str2.substring(indexOf + 1));
                    }
                }
            }
        }
        return hashMap;
    }
}
