package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.base.zak;
import com.google.android.gms.internal.base.zal;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.internal.base.zaq;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class ImageManager {
    private static final Object zaa = new Object();
    private static HashSet<Uri> zab = new HashSet<>();
    private static ImageManager zac;
    private final Context zad;
    private final Handler zae = new zap(Looper.getMainLooper());
    private final ExecutorService zaf = zal.zaa().zaa(4, zaq.zab);
    private final zaa zag = null;
    private final zak zah = new zak();
    private final Map<zaa, ImageReceiver> zai = new HashMap();
    private final Map<Uri, ImageReceiver> zaj = new HashMap();
    private final Map<Uri, Long> zak = new HashMap();

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    public static ImageManager create(Context context) {
        if (zac == null) {
            zac = new ImageManager(context, false);
        }
        return zac;
    }

    /* access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static final class zaa extends LruCache<zac, Bitmap> {
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        /* access modifiers changed from: protected */
        @Override // androidx.collection.LruCache
        public final /* synthetic */ int sizeOf(zac zac, Bitmap bitmap) {
            Bitmap bitmap2 = bitmap;
            return bitmap2.getHeight() * bitmap2.getRowBytes();
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [boolean, java.lang.Object, java.lang.Object, java.lang.Object] */
        /* access modifiers changed from: protected */
        @Override // androidx.collection.LruCache
        public final /* synthetic */ void entryRemoved(boolean z, zac zac, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, zac, bitmap, bitmap2);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public final class zad implements Runnable {
        private final zaa zaa;

        public zad(zaa zaa2) {
            this.zaa = zaa2;
        }

        public final void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zai.get(this.zaa);
            if (imageReceiver != null) {
                ImageManager.this.zai.remove(this.zaa);
                imageReceiver.zab(this.zaa);
            }
            zac zac = this.zaa.zaa;
            if (zac.zaa == null) {
                this.zaa.zaa(ImageManager.this.zad, ImageManager.this.zah, true);
                return;
            }
            Bitmap zaa2 = ImageManager.this.zaa((ImageManager) zac);
            if (zaa2 != null) {
                this.zaa.zaa(ImageManager.this.zad, zaa2, true);
                return;
            }
            Long l = (Long) ImageManager.this.zak.get(zac.zaa);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zaa.zaa(ImageManager.this.zad, ImageManager.this.zah, true);
                    return;
                }
                ImageManager.this.zak.remove(zac.zaa);
            }
            this.zaa.zaa(ImageManager.this.zad, ImageManager.this.zah);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.zaj.get(zac.zaa);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageReceiver(zac.zaa);
                ImageManager.this.zaj.put(zac.zaa, imageReceiver2);
            }
            imageReceiver2.zaa(this.zaa);
            if (!(this.zaa instanceof zad)) {
                ImageManager.this.zai.put(this.zaa, imageReceiver2);
            }
            synchronized (ImageManager.zaa) {
                if (!ImageManager.zab.contains(zac.zaa)) {
                    ImageManager.zab.add(zac.zaa);
                    imageReceiver2.zaa();
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    private final class zab implements Runnable {
        private final Uri zaa;
        private final ParcelFileDescriptor zab;

        public zab(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.zaa = uri;
            this.zab = parcelFileDescriptor;
        }

        public final void run() {
            boolean z;
            Bitmap bitmap;
            Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            ParcelFileDescriptor parcelFileDescriptor = this.zab;
            boolean z2 = false;
            Bitmap bitmap2 = null;
            if (parcelFileDescriptor != null) {
                try {
                    bitmap2 = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    String valueOf = String.valueOf(this.zaa);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 34);
                    sb.append("OOM while loading bitmap for uri: ");
                    sb.append(valueOf);
                    Log.e("ImageManager", sb.toString(), e);
                    z2 = true;
                }
                try {
                    this.zab.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
                z = z2;
                bitmap = bitmap2;
            } else {
                bitmap = null;
                z = false;
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.zae.post(new zac(this.zaa, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException unused) {
                String valueOf2 = String.valueOf(this.zaa);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 32);
                sb2.append("Latch interrupted while posting ");
                sb2.append(valueOf2);
                Log.w("ImageManager", sb2.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public final class ImageReceiver extends ResultReceiver {
        private final Uri zaa;
        private final ArrayList<zaa> zab = new ArrayList<>();

        ImageReceiver(Uri uri) {
            super(new zap(Looper.getMainLooper()));
            this.zaa = uri;
        }

        public final void zaa(zaa zaa2) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zab.add(zaa2);
        }

        public final void zab(zaa zaa2) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zab.remove(zaa2);
        }

        public final void zaa() {
            Intent intent = new Intent(Constants.ACTION_LOAD_IMAGE);
            intent.setPackage("com.google.android.gms");
            intent.putExtra(Constants.EXTRA_URI, this.zaa);
            intent.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            intent.putExtra(Constants.EXTRA_PRIORITY, 3);
            ImageManager.this.zad.sendBroadcast(intent);
        }

        public final void onReceiveResult(int i, Bundle bundle) {
            ImageManager.this.zaf.execute(new zab(this.zaa, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    private final class zac implements Runnable {
        private final Uri zaa;
        private final Bitmap zab;
        private final CountDownLatch zac;
        private boolean zad;

        public zac(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.zaa = uri;
            this.zab = bitmap;
            this.zad = z;
            this.zac = countDownLatch;
        }

        public final void run() {
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.zab != null;
            if (ImageManager.this.zag != null) {
                if (this.zad) {
                    ImageManager.this.zag.evictAll();
                    System.gc();
                    this.zad = false;
                    ImageManager.this.zae.post(this);
                    return;
                } else if (this.zab != null) {
                    ImageManager.this.zag.put(new zac(this.zaa), this.zab);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zaj.remove(this.zaa);
            if (imageReceiver != null) {
                ArrayList arrayList = imageReceiver.zab;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    zaa zaa2 = (zaa) arrayList.get(i);
                    if (this.zab == null || !z) {
                        ImageManager.this.zak.put(this.zaa, Long.valueOf(SystemClock.elapsedRealtime()));
                        zaa2.zaa(ImageManager.this.zad, ImageManager.this.zah, false);
                    } else {
                        zaa2.zaa(ImageManager.this.zad, this.zab, false);
                    }
                    if (!(zaa2 instanceof zad)) {
                        ImageManager.this.zai.remove(zaa2);
                    }
                }
            }
            this.zac.countDown();
            synchronized (ImageManager.zaa) {
                ImageManager.zab.remove(this.zaa);
            }
        }
    }

    private ImageManager(Context context, boolean z) {
        this.zad = context.getApplicationContext();
    }

    public final void loadImage(ImageView imageView, Uri uri) {
        zaa(new zab(imageView, uri));
    }

    public final void loadImage(ImageView imageView, int i) {
        zaa(new zab(imageView, i));
    }

    public final void loadImage(ImageView imageView, Uri uri, int i) {
        zab zab2 = new zab(imageView, uri);
        zab2.zab = i;
        zaa(zab2);
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zaa(new zad(onImageLoadedListener, uri));
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        zad zad2 = new zad(onImageLoadedListener, uri);
        zad2.zab = i;
        zaa(zad2);
    }

    private final void zaa(zaa zaa2) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zad(zaa2).run();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final Bitmap zaa(zac zac2) {
        zaa zaa2 = this.zag;
        if (zaa2 == null) {
            return null;
        }
        return (Bitmap) zaa2.get(zac2);
    }
}
