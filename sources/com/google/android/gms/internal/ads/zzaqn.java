package com.google.android.gms.internal.ads;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzadh
public final class zzaqn extends zzaqh {
    private static final Set<String> zzdbg = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzdbh = new DecimalFormat("#,###");
    private File zzdbi;
    private boolean zzdbj;

    public zzaqn(zzapw zzapw) {
        super(zzapw);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzakb.zzdk("Context.getCacheDir() returned null");
            return;
        }
        File file = new File(cacheDir, "admobVideoStreams");
        this.zzdbi = file;
        if (!file.isDirectory() && !this.zzdbi.mkdirs()) {
            String valueOf = String.valueOf(this.zzdbi.getAbsolutePath());
            zzakb.zzdk(valueOf.length() != 0 ? "Could not create preload cache directory at ".concat(valueOf) : new String("Could not create preload cache directory at "));
            this.zzdbi = null;
        } else if (!this.zzdbi.setReadable(true, false) || !this.zzdbi.setExecutable(true, false)) {
            String valueOf2 = String.valueOf(this.zzdbi.getAbsolutePath());
            zzakb.zzdk(valueOf2.length() != 0 ? "Could not set cache file permissions at ".concat(valueOf2) : new String("Could not set cache file permissions at "));
            this.zzdbi = null;
        }
    }

    private final File zzc(File file) {
        return new File(this.zzdbi, String.valueOf(file.getName()).concat(".done"));
    }

    @Override // com.google.android.gms.internal.ads.zzaqh
    public final void abort() {
        this.zzdbj = true;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:275:0x0402 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:199:0x04d5 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:163:0x03c1 */
    /* JADX WARN: Type inference failed for: r24v0 */
    /* JADX WARN: Type inference failed for: r24v3 */
    /* JADX WARN: Type inference failed for: r24v4, types: [int] */
    /* JADX WARN: Type inference failed for: r24v9 */
    /* JADX WARN: Type inference failed for: r24v10 */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01ef, code lost:
        if ((r5 instanceof java.net.HttpURLConnection) == false) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01f1, code lost:
        r1 = r5.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01fa, code lost:
        if (r1 < 400) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01fc, code lost:
        r15 = "badUrl";
        r2 = java.lang.String.valueOf(java.lang.Integer.toString(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x020c, code lost:
        if (r2.length() == 0) goto L_0x0214;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x020e, code lost:
        r2 = "HTTP request failed. Code: ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0214, code lost:
        r2 = new java.lang.String("HTTP request failed. Code: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r31).length() + 32);
        r4.append("HTTP status code ");
        r4.append(r1);
        r4.append(" at ");
        r4.append(r31);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0241, code lost:
        throw new java.io.IOException(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0242, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0246, code lost:
        r7 = r5.getContentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x024a, code lost:
        if (r7 >= 0) goto L_0x0275;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x024c, code lost:
        r1 = java.lang.String.valueOf(r31);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0256, code lost:
        if (r1.length() == 0) goto L_0x025d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0258, code lost:
        r0 = "Stream cache aborted, missing content-length header at ".concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x025d, code lost:
        r0 = new java.lang.String("Stream cache aborted, missing content-length header at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0263, code lost:
        com.google.android.gms.internal.ads.zzakb.zzdk(r0);
        zza(r31, r12.getAbsolutePath(), "contentLengthMissing", null);
        com.google.android.gms.internal.ads.zzaqn.zzdbg.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0274, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0275, code lost:
        r1 = com.google.android.gms.internal.ads.zzaqn.zzdbh.format((long) r7);
        r3 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzauy)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x028c, code lost:
        if (r7 <= r3) goto L_0x02e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x028e, code lost:
        r2 = new java.lang.StringBuilder((java.lang.String.valueOf(r1).length() + 33) + java.lang.String.valueOf(r31).length());
        r2.append("Content length ");
        r2.append(r1);
        r2.append(" exceeds limit at ");
        r2.append(r31);
        com.google.android.gms.internal.ads.zzakb.zzdk(r2.toString());
        r1 = java.lang.String.valueOf(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02c7, code lost:
        if (r1.length() == 0) goto L_0x02ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02c9, code lost:
        r0 = "File too big for full file cache. Size: ".concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02ce, code lost:
        r0 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02d4, code lost:
        zza(r31, r12.getAbsolutePath(), "sizeExceeded", r0);
        com.google.android.gms.internal.ads.zzaqn.zzdbg.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02e2, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02e3, code lost:
        r4 = new java.lang.StringBuilder((java.lang.String.valueOf(r1).length() + 20) + java.lang.String.valueOf(r31).length());
        r4.append("Caching ");
        r4.append(r1);
        r4.append(" bytes from ");
        r4.append(r31);
        com.google.android.gms.internal.ads.zzakb.zzck(r4.toString());
        r5 = java.nio.channels.Channels.newChannel(r5.getInputStream());
        r4 = new java.io.FileOutputStream(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:?, code lost:
        r2 = r4.getChannel();
        r1 = java.nio.ByteBuffer.allocate(1048576);
        r16 = com.google.android.gms.ads.internal.zzbv.zzer();
        r17 = r16.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x033c, code lost:
        r10 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:?, code lost:
        r6 = new com.google.android.gms.internal.ads.zzamj(((java.lang.Long) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzavb)).longValue());
        r13 = ((java.lang.Long) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzava)).longValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0356, code lost:
        r20 = r5.read(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x035a, code lost:
        if (r20 < 0) goto L_0x0472;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x035c, code lost:
        r11 = r11 + r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x035e, code lost:
        if (r11 <= r3) goto L_0x0393;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0360, code lost:
        r15 = "sizeExceeded";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:?, code lost:
        r1 = java.lang.String.valueOf(java.lang.Integer.toString(r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0370, code lost:
        if (r1.length() == 0) goto L_0x0378;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0372, code lost:
        r1 = "File too big for full file cache. Size: ".concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0378, code lost:
        r1 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0384, code lost:
        throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0385, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0388, code lost:
        r2 = r1;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x038b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x038e, code lost:
        r1 = r10;
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0390, code lost:
        r10 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:?, code lost:
        r1.flip();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x039a, code lost:
        if (r2.write(r1) > 0) goto L_0x0396;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x039c, code lost:
        r1.clear();
        r24 = ((r16.currentTimeMillis() - r17) > (1000 * r13) ? 1 : ((r16.currentTimeMillis() - r17) == (1000 * r13) ? 0 : -1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x03ab, code lost:
        if (r24 > 0) goto L_0x0426;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x03b1, code lost:
        if (r30.zzdbj != false) goto L_0x0416;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x03b7, code lost:
        if (r6.tryAcquire() == false) goto L_0x03f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03bf, code lost:
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03c3, code lost:
        r24 = r10;
        r25 = r2;
        r26 = r3;
        r27 = r4;
        r21 = r5;
        r19 = r6;
        r29 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:?, code lost:
        com.google.android.gms.internal.ads.zzamu.zzsy.post(new com.google.android.gms.internal.ads.zzaqi(r30, r31, r12.getAbsolutePath(), r11, r7, false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03e8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03ea, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x03eb, code lost:
        r27 = r4;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x03f0, code lost:
        r25 = r2;
        r26 = r3;
        r27 = r4;
        r21 = r5;
        r19 = r6;
        r29 = r7;
        r24 = r10;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0402, code lost:
        r6 = r19;
        r1 = r1;
        r5 = r21;
        r15 = r22;
        r10 = r24;
        r2 = r25;
        r3 = r26;
        r4 = r27;
        r7 = r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0416, code lost:
        r27 = r4;
        r24 = r10;
        r15 = "externalAbort";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0425, code lost:
        throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0426, code lost:
        r15 = "downloadTimeout";
        r0 = java.lang.Long.toString(r13);
        r2 = new java.lang.StringBuilder(java.lang.String.valueOf(r0).length() + 29);
        r2.append("Timeout exceeded. Limit: ");
        r2.append(r0);
        r2.append(" sec");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0459, code lost:
        throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x045a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x045d, code lost:
        r2 = r2.toString();
        r1 = r10;
        r10 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0464, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0468, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x046b, code lost:
        r27 = r4;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0472, code lost:
        r27 = r4;
        r24 = r10;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:?, code lost:
        r27.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0481, code lost:
        if (com.google.android.gms.internal.ads.zzakb.isLoggable(3) == false) goto L_0x04c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0483, code lost:
        r1 = com.google.android.gms.internal.ads.zzaqn.zzdbh.format((long) r11);
        r3 = new java.lang.StringBuilder((java.lang.String.valueOf(r1).length() + 22) + java.lang.String.valueOf(r31).length());
        r3.append("Preloaded ");
        r3.append(r1);
        r3.append(" bytes from ");
        r3.append(r31);
        com.google.android.gms.internal.ads.zzakb.zzck(r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x04ba, code lost:
        r0 = e;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x04bc, code lost:
        r0 = e;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x04bd, code lost:
        r15 = r22;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x04bf, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x04c2, code lost:
        r12.setReadable(true, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x04cb, code lost:
        if (r0.isFile() == false) goto L_0x04d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x04cd, code lost:
        r0.setLastModified(java.lang.System.currentTimeMillis());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:?, code lost:
        r0.createNewFile();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x04ec, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x04ef, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x04f4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x04f7, code lost:
        r27 = r4;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x04fb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x04fe, code lost:
        r27 = r4;
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0507, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0511, code lost:
        throw new java.io.IOException("Invalid protocol.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x051c, code lost:
        throw new java.io.IOException("Too many redirects (20)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x051d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0520, code lost:
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0523, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0526, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0529, code lost:
        r2 = null;
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x052f, code lost:
        com.google.android.gms.ads.internal.zzbv.zzeo().zza(r0, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x0541, code lost:
        r3 = new java.lang.StringBuilder(java.lang.String.valueOf(r31).length() + 26);
        r3.append("Preload aborted for URL \"");
        r3.append(r31);
        r3.append("\"");
        com.google.android.gms.internal.ads.zzakb.zzdj(r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0565, code lost:
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r31).length() + 25);
        r4.append("Preload failed for URL \"");
        r4.append(r31);
        r4.append("\"");
        com.google.android.gms.internal.ads.zzakb.zzc(r4.toString(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x05a4, code lost:
        r0 = "Could not delete partial cache file at ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x05a9, code lost:
        r0 = new java.lang.String("Could not delete partial cache file at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012f, code lost:
        r15 = "error";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        com.google.android.gms.ads.internal.zzbv.zzew();
        r1 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzkb.zzik().zzd(com.google.android.gms.internal.ads.zznk.zzavc)).intValue();
        r2 = new java.net.URL(r31);
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x014a, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014d, code lost:
        if (r3 > 20) goto L_0x0512;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x014f, code lost:
        r5 = r2.openConnection();
        r5.setConnectTimeout(r1);
        r5.setReadTimeout(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x015b, code lost:
        if ((r5 instanceof java.net.HttpURLConnection) == false) goto L_0x0507;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x015d, code lost:
        r5 = (java.net.HttpURLConnection) r5;
        r6 = new com.google.android.gms.internal.ads.zzamy();
        r6.zza(r5, (byte[]) null);
        r5.setInstanceFollowRedirects(false);
        r7 = r5.getResponseCode();
        r6.zza(r5, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0174, code lost:
        if ((r7 / 100) != 3) goto L_0x01ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r4 = r5.getHeaderField("Location");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x017c, code lost:
        if (r4 == null) goto L_0x01de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x017e, code lost:
        r6 = new java.net.URL(r2, r4);
        r2 = r6.getProtocol();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0187, code lost:
        if (r2 == null) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x018f, code lost:
        if (r2.equals(com.mopub.common.Constants.HTTP) != false) goto L_0x01b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0197, code lost:
        if (r2.equals(com.mopub.common.Constants.HTTPS) != false) goto L_0x01b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0199, code lost:
        r2 = java.lang.String.valueOf(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01a5, code lost:
        if (r2.length() == 0) goto L_0x01ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01a7, code lost:
        r1 = "Unsupported scheme: ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01ac, code lost:
        r1 = new java.lang.String("Unsupported scheme: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01b5, code lost:
        throw new java.io.IOException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01b6, code lost:
        r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01c0, code lost:
        if (r4.length() == 0) goto L_0x01c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01c2, code lost:
        r2 = "Redirecting to ".concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01c7, code lost:
        r2 = new java.lang.String("Redirecting to ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01cd, code lost:
        com.google.android.gms.internal.ads.zzakb.zzck(r2);
        r5.disconnect();
        r2 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01dd, code lost:
        throw new java.io.IOException("Protocol is null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01e5, code lost:
        throw new java.io.IOException("Missing Location header in redirect");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01e6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01e9, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01ea, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x04ba A[ExcHandler: RuntimeException (e java.lang.RuntimeException), PHI: r22 r24 r27 
      PHI: (r22v7 java.lang.String) = (r22v4 java.lang.String), (r22v4 java.lang.String), (r22v15 java.lang.String), (r22v15 java.lang.String) binds: [B:199:0x04d5, B:200:?, B:163:0x03c1, B:166:0x03e1] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r24v3 ??) = (r24v0 ??), (r24v0 ??), (r24v4 ??), (r24v10 ??) binds: [B:199:0x04d5, B:200:?, B:163:0x03c1, B:166:0x03e1] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r27v9 java.io.FileOutputStream) = (r27v5 java.io.FileOutputStream), (r27v5 java.io.FileOutputStream), (r27v13 java.io.FileOutputStream), (r27v17 java.io.FileOutputStream) binds: [B:199:0x04d5, B:200:?, B:163:0x03c1, B:166:0x03e1] A[DONT_GENERATE, DONT_INLINE], Splitter:B:166:0x03e1] */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x052f  */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x0541  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0565  */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x05a4  */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x05a9  */
    @Override // com.google.android.gms.internal.ads.zzaqh
    public final boolean zzdp(String str) {
        int i;
        File file;
        String str2;
        FileOutputStream fileOutputStream;
        String str3;
        String str4;
        boolean z;
        String str5;
        FileOutputStream fileOutputStream2 = null;
        int i2 = 0;
        if (this.zzdbi == null) {
            str5 = "noCacheDir";
        } else {
            do {
                File file2 = this.zzdbi;
                if (file2 == null) {
                    i = 0;
                } else {
                    i = 0;
                    for (File file3 : file2.listFiles()) {
                        if (!file3.getName().endsWith(".done")) {
                            i++;
                        }
                    }
                }
                if (i > ((Integer) zzkb.zzik().zzd(zznk.zzaux)).intValue()) {
                    File file4 = this.zzdbi;
                    if (file4 != null) {
                        long j = Long.MAX_VALUE;
                        File[] listFiles = file4.listFiles();
                        File file5 = null;
                        for (File file6 : listFiles) {
                            if (!file6.getName().endsWith(".done")) {
                                long lastModified = file6.lastModified();
                                if (lastModified < j) {
                                    file5 = file6;
                                    j = lastModified;
                                }
                            }
                        }
                        if (file5 != null) {
                            z = file5.delete();
                            File zzc = zzc(file5);
                            if (zzc.isFile()) {
                                z &= zzc.delete();
                                continue;
                            } else {
                                continue;
                            }
                        }
                    }
                    z = false;
                    continue;
                } else {
                    zzkb.zzif();
                    file = new File(this.zzdbi, zzamu.zzde(str));
                    File zzc2 = zzc(file);
                    if (!file.isFile() || !zzc2.isFile()) {
                        String valueOf = String.valueOf(this.zzdbi.getAbsolutePath());
                        String valueOf2 = String.valueOf(str);
                        String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                        synchronized (zzdbg) {
                            if (zzdbg.contains(concat)) {
                                String valueOf3 = String.valueOf(str);
                                zzakb.zzdk(valueOf3.length() != 0 ? "Stream cache already in progress at ".concat(valueOf3) : new String("Stream cache already in progress at "));
                                zza(str, file.getAbsolutePath(), "inProgress", null);
                                return false;
                            }
                            zzdbg.add(concat);
                        }
                    } else {
                        int length = (int) file.length();
                        String valueOf4 = String.valueOf(str);
                        zzakb.zzck(valueOf4.length() != 0 ? "Stream cache hit at ".concat(valueOf4) : new String("Stream cache hit at "));
                        zza(str, file.getAbsolutePath(), length);
                        return true;
                    }
                }
            } while (z);
            zzakb.zzdk("Unable to expire stream cache");
            str5 = "expireFailed";
        }
        zza(str, null, str5, null);
        return false;
        zza(str, file.getAbsolutePath(), i2);
        String str6 = str3;
        try {
            zzdbg.remove(str6);
            return true;
        } catch (IOException | RuntimeException e) {
            Throwable e2 = e;
            String str7 = str4;
            fileOutputStream2 = fileOutputStream;
            String str8 = null;
            if (e2 instanceof RuntimeException) {
            }
            try {
                fileOutputStream2.close();
            } catch (IOException | NullPointerException unused) {
            }
            if (this.zzdbj) {
            }
            String valueOf5 = String.valueOf(file.getAbsolutePath());
            if (valueOf5.length() != 0) {
            }
            zzakb.zzdk(str2);
            zza(str, file.getAbsolutePath(), str7, str8);
            zzdbg.remove(str6);
            return false;
        }
    }
}
