package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@zzadh
public final class zzha {
    private final int zzaiz;
    private final zzgq zzajb;
    private String zzajj;
    private String zzajk;
    private final boolean zzajl = false;
    private final int zzajm;
    private final int zzajn;

    public zzha(int i, int i2, int i3) {
        this.zzaiz = i;
        if (i2 > 64 || i2 < 0) {
            this.zzajm = 64;
        } else {
            this.zzajm = i2;
        }
        if (i3 <= 0) {
            this.zzajn = 1;
        } else {
            this.zzajn = i3;
        }
        this.zzajb = new zzgz(this.zzajm);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0107, code lost:
        if (r2.size() < r16.zzaiz) goto L_0x010b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0113 A[LOOP:0: B:1:0x0012->B:64:0x0113, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0117 A[EDGE_INSN: B:75:0x0117->B:65:0x0117 ?: BREAK  , SYNTHETIC] */
    public final String zza(ArrayList<String> arrayList, ArrayList<zzgp> arrayList2) {
        boolean z;
        boolean z2;
        int i;
        Collections.sort(arrayList2, new zzhb(this));
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            String[] split = Normalizer.normalize(arrayList.get(arrayList2.get(i2).zzhf()), Normalizer.Form.NFKC).toLowerCase(Locale.US).split("\n");
            if (split.length != 0) {
                int i3 = 0;
                while (true) {
                    if (i3 >= split.length) {
                        break;
                    }
                    String str = split[i3];
                    if (str.indexOf("'") != -1) {
                        StringBuilder sb = new StringBuilder(str);
                        int i4 = 1;
                        boolean z3 = false;
                        while (true) {
                            int i5 = i4 + 2;
                            if (i5 > sb.length()) {
                                break;
                            }
                            if (sb.charAt(i4) == '\'') {
                                if (sb.charAt(i4 - 1) != ' ') {
                                    int i6 = i4 + 1;
                                    if ((sb.charAt(i6) == 's' || sb.charAt(i6) == 'S') && (i5 == sb.length() || sb.charAt(i5) == ' ')) {
                                        sb.insert(i4, ' ');
                                        i4 = i5;
                                        i = 1;
                                        z3 = true;
                                    }
                                }
                                sb.setCharAt(i4, ' ');
                                i = 1;
                                z3 = true;
                            } else {
                                i = 1;
                            }
                            i4 += i;
                        }
                        String sb2 = z3 ? sb.toString() : null;
                        if (sb2 != null) {
                            this.zzajk = sb2;
                            str = sb2;
                        }
                    }
                    String[] zzb = zzgu.zzb(str, true);
                    if (zzb.length >= this.zzajn) {
                        int i7 = 0;
                        while (true) {
                            if (i7 >= zzb.length) {
                                break;
                            }
                            String str2 = "";
                            int i8 = 0;
                            while (true) {
                                if (i8 >= this.zzajn) {
                                    z2 = true;
                                    break;
                                }
                                int i9 = i7 + i8;
                                if (i9 >= zzb.length) {
                                    z2 = false;
                                    break;
                                }
                                if (i8 > 0) {
                                    str2 = String.valueOf(str2).concat(" ");
                                }
                                String valueOf = String.valueOf(str2);
                                String valueOf2 = String.valueOf(zzb[i9]);
                                str2 = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                                i8++;
                            }
                            if (!z2) {
                                break;
                            }
                            hashSet.add(str2);
                            if (hashSet.size() >= this.zzaiz) {
                                break;
                            }
                            i7++;
                        }
                    }
                    i3++;
                }
                z = false;
                if (z) {
                    break;
                }
            }
            z = true;
            if (z) {
            }
        }
        zzgt zzgt = new zzgt();
        this.zzajj = "";
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            try {
                zzgt.write(this.zzajb.zzx((String) it.next()));
            } catch (IOException e) {
                zzakb.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzgt.toString();
    }
}
