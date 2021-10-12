package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.ag;
import com.google.android.play.core.internal.bz;
import com.google.android.play.core.internal.ca;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.zip.GZIPInputStream;

/* access modifiers changed from: package-private */
public final class bj {

    /* renamed from: a  reason: collision with root package name */
    private static final ag f87a = new ag("ExtractChunkTaskHandler");
    private final byte[] b = new byte[8192];
    private final au c;
    private final ca<t> d;
    private final ca<ar> e;
    private final bo f;

    bj(au auVar, ca<t> caVar, ca<ar> caVar2, bo boVar) {
        this.c = auVar;
        this.d = caVar;
        this.e = caVar2;
        this.f = boVar;
    }

    private final File b(bi biVar) {
        File h = this.c.h(biVar.k, biVar.f86a, biVar.b, biVar.c);
        if (!h.exists()) {
            h.mkdirs();
        }
        return h;
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:120:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x017c A[Catch:{ all -> 0x032e, all -> 0x0334 }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0290 A[SYNTHETIC, Splitter:B:91:0x0290] */
    public final void a(bi biVar) {
        InputStream inputStream;
        de a2;
        long j;
        File file;
        int min;
        int max;
        long j2;
        cz czVar = new cz(this.c, biVar.k, biVar.f86a, biVar.b, biVar.c);
        File o = this.c.o(biVar.k, biVar.f86a, biVar.b, biVar.c);
        if (!o.exists()) {
            o.mkdirs();
        }
        try {
            InputStream inputStream2 = biVar.i;
            GZIPInputStream gZIPInputStream = biVar.d != 1 ? inputStream2 : new GZIPInputStream(inputStream2, 8192);
            try {
                if (biVar.e > 0) {
                    cy e2 = czVar.e();
                    int e3 = e2.e();
                    int i = biVar.e;
                    if (e3 == i - 1) {
                        int a3 = e2.a();
                        if (a3 == 1) {
                            f87a.a("Resuming zip entry from last chunk during file %s.", e2.b());
                            File file2 = new File(e2.b());
                            if (file2.exists()) {
                                RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                                randomAccessFile.seek(e2.c());
                                long d2 = e2.d();
                                while (true) {
                                    min = (int) Math.min(d2, 8192L);
                                    max = Math.max(gZIPInputStream.read(this.b, 0, min), 0);
                                    if (max > 0) {
                                        randomAccessFile.write(this.b, 0, max);
                                    }
                                    j2 = d2 - ((long) max);
                                    if (j2 <= 0) {
                                        break;
                                    } else if (max <= 0) {
                                        break;
                                    } else {
                                        d2 = j2;
                                    }
                                }
                                long length = randomAccessFile.length();
                                randomAccessFile.close();
                                if (max != min) {
                                    f87a.a("Chunk has ended while resuming the previous chunks file content.", new Object[0]);
                                    czVar.a(file2.getCanonicalPath(), length, j2, biVar.e);
                                }
                            } else {
                                throw new bk("Partial file specified in checkpoint does not exist. Corrupt directory.", biVar.j);
                            }
                        } else if (a3 == 2) {
                            f87a.a("Resuming zip entry from last chunk during local file header.", new Object[0]);
                            File n = this.c.n(biVar.k, biVar.f86a, biVar.b, biVar.c);
                            if (n.exists()) {
                                inputStream = new SequenceInputStream(new FileInputStream(n), gZIPInputStream);
                                if (inputStream != null) {
                                    bd bdVar = new bd(inputStream);
                                    File b2 = b(biVar);
                                    do {
                                        a2 = bdVar.a();
                                        if (!a2.g() && !bdVar.c()) {
                                            if (!a2.c() || a2.b()) {
                                                czVar.h(a2.i(), bdVar);
                                            } else {
                                                czVar.g(a2.i());
                                                File file3 = new File(b2, a2.d());
                                                file3.getParentFile().mkdirs();
                                                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                                                int read = bdVar.read(this.b);
                                                while (read > 0) {
                                                    fileOutputStream.write(this.b, 0, read);
                                                    read = bdVar.read(this.b);
                                                }
                                                fileOutputStream.close();
                                            }
                                        }
                                        if (bdVar.b()) {
                                            break;
                                        }
                                    } while (!bdVar.c());
                                    if (bdVar.c()) {
                                        f87a.a("Writing central directory metadata.", new Object[0]);
                                        czVar.h(a2.i(), inputStream);
                                    }
                                    if (!biVar.a()) {
                                        if (a2.g()) {
                                            f87a.a("Writing slice checkpoint for partial local file header.", new Object[0]);
                                            czVar.b(a2.i(), biVar.e);
                                        } else if (bdVar.c()) {
                                            f87a.a("Writing slice checkpoint for central directory.", new Object[0]);
                                            czVar.c(biVar.e);
                                        } else {
                                            if (a2.f() == 0) {
                                                f87a.a("Writing slice checkpoint for partial file.", new Object[0]);
                                                file = new File(b(biVar), a2.d());
                                                j = a2.e() - bdVar.d();
                                                if (file.length() != j) {
                                                    throw new bk("Partial file is of unexpected size.");
                                                }
                                            } else {
                                                f87a.a("Writing slice checkpoint for partial unextractable file.", new Object[0]);
                                                file = czVar.j();
                                                j = file.length();
                                            }
                                            czVar.a(file.getCanonicalPath(), j, bdVar.d(), biVar.e);
                                        }
                                    }
                                }
                                gZIPInputStream.close();
                                if (biVar.a()) {
                                    try {
                                        czVar.d(biVar.e);
                                    } catch (IOException e4) {
                                        f87a.b("Writing extraction finished checkpoint failed with %s.", e4.getMessage());
                                        throw new bk("Writing extraction finished checkpoint failed.", e4, biVar.j);
                                    }
                                }
                                f87a.d("Extraction finished for chunk %s of slice %s of pack %s of session %s.", Integer.valueOf(biVar.e), biVar.c, biVar.k, Integer.valueOf(biVar.j));
                                this.d.a().e(biVar.j, biVar.k, biVar.c, biVar.e);
                                biVar.i.close();
                                if (biVar.h == 3) {
                                    String str = biVar.k;
                                    long j3 = biVar.g;
                                    this.e.a().b(AssetPackState.c(str, 3, 0, j3, j3, this.f.c(str, biVar), 1, ""));
                                    return;
                                }
                                return;
                            }
                            throw new bk("Checkpoint extension file not found.", biVar.j);
                        } else if (a3 == 3) {
                            f87a.a("Resuming central directory from last chunk.", new Object[0]);
                            czVar.f(gZIPInputStream, e2.c());
                            if (!biVar.a()) {
                                throw new bk("Chunk has ended twice during central directory. This should not be possible with chunk sizes of 50MB.", biVar.j);
                            }
                        } else {
                            throw new bk(String.format("Slice checkpoint file corrupt. Unexpected FileExtractionStatus %s.", Integer.valueOf(e2.a())), biVar.j);
                        }
                        inputStream = null;
                        if (inputStream != null) {
                        }
                        gZIPInputStream.close();
                        if (biVar.a()) {
                        }
                        f87a.d("Extraction finished for chunk %s of slice %s of pack %s of session %s.", Integer.valueOf(biVar.e), biVar.c, biVar.k, Integer.valueOf(biVar.j));
                        this.d.a().e(biVar.j, biVar.k, biVar.c, biVar.e);
                        biVar.i.close();
                        if (biVar.h == 3) {
                        }
                    } else {
                        throw new bk(String.format("Trying to resume with chunk number %s when previously processed chunk was number %s.", Integer.valueOf(i), Integer.valueOf(e2.e())), biVar.j);
                    }
                }
                inputStream = gZIPInputStream;
                if (inputStream != null) {
                }
                gZIPInputStream.close();
                if (biVar.a()) {
                }
                f87a.d("Extraction finished for chunk %s of slice %s of pack %s of session %s.", Integer.valueOf(biVar.e), biVar.c, biVar.k, Integer.valueOf(biVar.j));
                this.d.a().e(biVar.j, biVar.k, biVar.c, biVar.e);
                try {
                    biVar.i.close();
                } catch (IOException unused) {
                    f87a.e("Could not close file for chunk %s of slice %s of pack %s.", Integer.valueOf(biVar.e), biVar.c, biVar.k);
                }
                if (biVar.h == 3) {
                }
            } catch (Throwable th) {
                bz.a(th, th);
            }
            throw th;
        } catch (IOException e5) {
            f87a.b("IOException during extraction %s.", e5.getMessage());
            throw new bk(String.format("Error extracting chunk %s of slice %s of pack %s of session %s.", Integer.valueOf(biVar.e), biVar.c, biVar.k, Integer.valueOf(biVar.j)), e5, biVar.j);
        }
    }
}
