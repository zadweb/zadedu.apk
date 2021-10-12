package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.google.android.gms.ads.internal.zzbv;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

@zzadh
public final class zzapu extends Thread implements SurfaceTexture.OnFrameAvailableListener, zzapt {
    private static final float[] zzcyv = {-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    private final float[] zzcys = new float[9];
    private final zzapr zzcyw;
    private final float[] zzcyx = new float[9];
    private final float[] zzcyy = new float[9];
    private final float[] zzcyz = new float[9];
    private final float[] zzcza = new float[9];
    private final float[] zzczb = new float[9];
    private final float[] zzczc = new float[9];
    private float zzczd = Float.NaN;
    private float zzcze;
    private float zzczf;
    private SurfaceTexture zzczg;
    private SurfaceTexture zzczh;
    private int zzczi;
    private int zzczj;
    private int zzczk;
    private FloatBuffer zzczl;
    private final CountDownLatch zzczm;
    private final Object zzczn;
    private EGL10 zzczo;
    private EGLDisplay zzczp;
    private EGLContext zzczq;
    private EGLSurface zzczr;
    private volatile boolean zzczs;
    private volatile boolean zzczt;
    private int zzuq;
    private int zzur;

    public zzapu(Context context) {
        super("SphericalVideoProcessor");
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(zzcyv.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.zzczl = asFloatBuffer;
        asFloatBuffer.put(zzcyv).position(0);
        zzapr zzapr = new zzapr(context);
        this.zzcyw = zzapr;
        zzapr.zza(this);
        this.zzczm = new CountDownLatch(1);
        this.zzczn = new Object();
    }

    private static void zza(float[] fArr, float f) {
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        double d = (double) f;
        fArr[4] = (float) Math.cos(d);
        fArr[5] = (float) (-Math.sin(d));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin(d);
        fArr[8] = (float) Math.cos(d);
    }

    private static void zza(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = (fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[3]) + (fArr2[2] * fArr3[6]);
        fArr[1] = (fArr2[0] * fArr3[1]) + (fArr2[1] * fArr3[4]) + (fArr2[2] * fArr3[7]);
        fArr[2] = (fArr2[0] * fArr3[2]) + (fArr2[1] * fArr3[5]) + (fArr2[2] * fArr3[8]);
        fArr[3] = (fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[3]) + (fArr2[5] * fArr3[6]);
        fArr[4] = (fArr2[3] * fArr3[1]) + (fArr2[4] * fArr3[4]) + (fArr2[5] * fArr3[7]);
        fArr[5] = (fArr2[3] * fArr3[2]) + (fArr2[4] * fArr3[5]) + (fArr2[5] * fArr3[8]);
        fArr[6] = (fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[3]) + (fArr2[8] * fArr3[6]);
        fArr[7] = (fArr2[6] * fArr3[1]) + (fArr2[7] * fArr3[4]) + (fArr2[8] * fArr3[7]);
        fArr[8] = (fArr2[6] * fArr3[2]) + (fArr2[7] * fArr3[5]) + (fArr2[8] * fArr3[8]);
    }

    private static void zzb(float[] fArr, float f) {
        double d = (double) f;
        fArr[0] = (float) Math.cos(d);
        fArr[1] = (float) (-Math.sin(d));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin(d);
        fArr[4] = (float) Math.cos(d);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    private static int zzd(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        zzdo("createShader");
        if (glCreateShader == 0) {
            return glCreateShader;
        }
        GLES20.glShaderSource(glCreateShader, str);
        zzdo("shaderSource");
        GLES20.glCompileShader(glCreateShader);
        zzdo("compileShader");
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        zzdo("getShaderiv");
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Could not compile shader ");
        sb.append(i);
        sb.append(":");
        Log.e("SphericalVideoRenderer", sb.toString());
        Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        zzdo("deleteShader");
        return 0;
    }

    private static void zzdo(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21);
            sb.append(str);
            sb.append(": glError ");
            sb.append(glGetError);
            Log.e("SphericalVideoRenderer", sb.toString());
        }
    }

    private final boolean zztk() {
        EGLSurface eGLSurface = this.zzczr;
        boolean z = false;
        if (!(eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE)) {
            z = this.zzczo.eglDestroySurface(this.zzczp, this.zzczr) | this.zzczo.eglMakeCurrent(this.zzczp, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | false;
            this.zzczr = null;
        }
        EGLContext eGLContext = this.zzczq;
        if (eGLContext != null) {
            z |= this.zzczo.eglDestroyContext(this.zzczp, eGLContext);
            this.zzczq = null;
        }
        EGLDisplay eGLDisplay = this.zzczp;
        if (eGLDisplay == null) {
            return z;
        }
        boolean eglTerminate = z | this.zzczo.eglTerminate(eGLDisplay);
        this.zzczp = null;
        return eglTerminate;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzczk++;
        synchronized (this.zzczn) {
            this.zzczn.notifyAll();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x03a6  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x03ab  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01d3 A[ADDED_TO_REGION] */
    public final void run() {
        boolean z;
        int zzd;
        int i;
        if (this.zzczh == null) {
            zzakb.e("SphericalVideoProcessor started with no output texture.");
            this.zzczm.countDown();
            return;
        }
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        this.zzczo = egl10;
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        this.zzczp = eglGetDisplay;
        if (eglGetDisplay != EGL10.EGL_NO_DISPLAY) {
            if (this.zzczo.eglInitialize(this.zzczp, new int[2])) {
                int[] iArr = new int[1];
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                EGLConfig eGLConfig = (!this.zzczo.eglChooseConfig(this.zzczp, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344}, eGLConfigArr, 1, iArr) || iArr[0] <= 0) ? null : eGLConfigArr[0];
                if (eGLConfig != null) {
                    EGLContext eglCreateContext = this.zzczo.eglCreateContext(this.zzczp, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
                    this.zzczq = eglCreateContext;
                    if (!(eglCreateContext == null || eglCreateContext == EGL10.EGL_NO_CONTEXT)) {
                        EGLSurface eglCreateWindowSurface = this.zzczo.eglCreateWindowSurface(this.zzczp, eGLConfig, this.zzczh, null);
                        this.zzczr = eglCreateWindowSurface;
                        if (!(eglCreateWindowSurface == null || eglCreateWindowSurface == EGL10.EGL_NO_SURFACE)) {
                            EGL10 egl102 = this.zzczo;
                            EGLDisplay eGLDisplay = this.zzczp;
                            EGLSurface eGLSurface = this.zzczr;
                            if (egl102.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.zzczq)) {
                                z = true;
                                zzna<String> zzna = zznk.zzazp;
                                zzd = zzd(35633, ((String) zzkb.zzik().zzd(zzna)).equals(zzna.zzja()) ? (String) zzkb.zzik().zzd(zzna) : "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}");
                                if (zzd != 0) {
                                    zzna<String> zzna2 = zznk.zzazq;
                                    int zzd2 = zzd(35632, !((String) zzkb.zzik().zzd(zzna2)).equals(zzna2.zzja()) ? (String) zzkb.zzik().zzd(zzna2) : "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}");
                                    if (zzd2 != 0) {
                                        i = GLES20.glCreateProgram();
                                        zzdo("createProgram");
                                        if (i != 0) {
                                            GLES20.glAttachShader(i, zzd);
                                            zzdo("attachShader");
                                            GLES20.glAttachShader(i, zzd2);
                                            zzdo("attachShader");
                                            GLES20.glLinkProgram(i);
                                            zzdo("linkProgram");
                                            int[] iArr2 = new int[1];
                                            GLES20.glGetProgramiv(i, 35714, iArr2, 0);
                                            zzdo("getProgramiv");
                                            if (iArr2[0] != 1) {
                                                Log.e("SphericalVideoRenderer", "Could not link program: ");
                                                Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(i));
                                                GLES20.glDeleteProgram(i);
                                                zzdo("deleteProgram");
                                            } else {
                                                GLES20.glValidateProgram(i);
                                                zzdo("validateProgram");
                                            }
                                        }
                                        this.zzczi = i;
                                        GLES20.glUseProgram(i);
                                        zzdo("useProgram");
                                        int glGetAttribLocation = GLES20.glGetAttribLocation(this.zzczi, "aPosition");
                                        GLES20.glVertexAttribPointer(glGetAttribLocation, 3, 5126, false, 12, (Buffer) this.zzczl);
                                        zzdo("vertexAttribPointer");
                                        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
                                        zzdo("enableVertexAttribArray");
                                        int[] iArr3 = new int[1];
                                        GLES20.glGenTextures(1, iArr3, 0);
                                        zzdo("genTextures");
                                        int i2 = iArr3[0];
                                        GLES20.glBindTexture(36197, i2);
                                        zzdo("bindTextures");
                                        GLES20.glTexParameteri(36197, 10240, 9729);
                                        zzdo("texParameteri");
                                        GLES20.glTexParameteri(36197, 10241, 9729);
                                        zzdo("texParameteri");
                                        GLES20.glTexParameteri(36197, 10242, 33071);
                                        zzdo("texParameteri");
                                        GLES20.glTexParameteri(36197, 10243, 33071);
                                        zzdo("texParameteri");
                                        int glGetUniformLocation = GLES20.glGetUniformLocation(this.zzczi, "uVMat");
                                        this.zzczj = glGetUniformLocation;
                                        GLES20.glUniformMatrix3fv(glGetUniformLocation, 1, false, new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f}, 0);
                                        boolean z2 = this.zzczi != 0;
                                        if (z || !z2) {
                                            String valueOf = String.valueOf(GLUtils.getEGLErrorString(this.zzczo.eglGetError()));
                                            String concat = valueOf.length() != 0 ? "EGL initialization failed: ".concat(valueOf) : new String("EGL initialization failed: ");
                                            zzakb.e(concat);
                                            zzbv.zzeo().zza(new Throwable(concat), "SphericalVideoProcessor.run.1");
                                            zztk();
                                            this.zzczm.countDown();
                                        }
                                        SurfaceTexture surfaceTexture = new SurfaceTexture(i2);
                                        this.zzczg = surfaceTexture;
                                        surfaceTexture.setOnFrameAvailableListener(this);
                                        this.zzczm.countDown();
                                        this.zzcyw.start();
                                        try {
                                            this.zzczs = true;
                                            while (!this.zzczt) {
                                                while (this.zzczk > 0) {
                                                    this.zzczg.updateTexImage();
                                                    this.zzczk--;
                                                }
                                                if (this.zzcyw.zza(this.zzcys)) {
                                                    if (Float.isNaN(this.zzczd)) {
                                                        float[] fArr = this.zzcys;
                                                        float[] fArr2 = {0.0f, 1.0f, 0.0f};
                                                        float[] fArr3 = {(fArr[0] * fArr2[0]) + (fArr[1] * fArr2[1]) + (fArr[2] * fArr2[2]), (fArr[3] * fArr2[0]) + (fArr[4] * fArr2[1]) + (fArr[5] * fArr2[2]), (fArr[6] * fArr2[0]) + (fArr[7] * fArr2[1]) + (fArr[8] * fArr2[2])};
                                                        this.zzczd = -(((float) Math.atan2((double) fArr3[1], (double) fArr3[0])) - 1.5707964f);
                                                    }
                                                    zzb(this.zzczb, this.zzczd + this.zzcze);
                                                } else {
                                                    zza(this.zzcys, -1.5707964f);
                                                    zzb(this.zzczb, this.zzcze);
                                                }
                                                zza(this.zzcyx, 1.5707964f);
                                                zza(this.zzcyy, this.zzczb, this.zzcyx);
                                                zza(this.zzcyz, this.zzcys, this.zzcyy);
                                                zza(this.zzcza, this.zzczf);
                                                zza(this.zzczc, this.zzcza, this.zzcyz);
                                                GLES20.glUniformMatrix3fv(this.zzczj, 1, false, this.zzczc, 0);
                                                GLES20.glDrawArrays(5, 0, 4);
                                                zzdo("drawArrays");
                                                GLES20.glFinish();
                                                this.zzczo.eglSwapBuffers(this.zzczp, this.zzczr);
                                                if (this.zzczs) {
                                                    GLES20.glViewport(0, 0, this.zzuq, this.zzur);
                                                    zzdo("viewport");
                                                    int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.zzczi, "uFOVx");
                                                    int glGetUniformLocation3 = GLES20.glGetUniformLocation(this.zzczi, "uFOVy");
                                                    if (this.zzuq > this.zzur) {
                                                        GLES20.glUniform1f(glGetUniformLocation2, 0.87266463f);
                                                        GLES20.glUniform1f(glGetUniformLocation3, (((float) this.zzur) * 0.87266463f) / ((float) this.zzuq));
                                                    } else {
                                                        GLES20.glUniform1f(glGetUniformLocation2, (((float) this.zzuq) * 0.87266463f) / ((float) this.zzur));
                                                        GLES20.glUniform1f(glGetUniformLocation3, 0.87266463f);
                                                    }
                                                    this.zzczs = false;
                                                }
                                                try {
                                                    synchronized (this.zzczn) {
                                                        if (!this.zzczt && !this.zzczs && this.zzczk == 0) {
                                                            this.zzczn.wait();
                                                        }
                                                    }
                                                } catch (InterruptedException unused) {
                                                }
                                            }
                                            return;
                                        } catch (IllegalStateException unused2) {
                                            zzakb.zzdk("SphericalVideoProcessor halted unexpectedly.");
                                            return;
                                        } catch (Throwable th) {
                                            zzakb.zzb("SphericalVideoProcessor died.", th);
                                            zzbv.zzeo().zza(th, "SphericalVideoProcessor.run.2");
                                            return;
                                        } finally {
                                            this.zzcyw.stop();
                                            this.zzczg.setOnFrameAvailableListener(null);
                                            this.zzczg = null;
                                            zztk();
                                        }
                                    }
                                }
                                i = 0;
                                this.zzczi = i;
                                GLES20.glUseProgram(i);
                                zzdo("useProgram");
                                int glGetAttribLocation2 = GLES20.glGetAttribLocation(this.zzczi, "aPosition");
                                GLES20.glVertexAttribPointer(glGetAttribLocation2, 3, 5126, false, 12, (Buffer) this.zzczl);
                                zzdo("vertexAttribPointer");
                                GLES20.glEnableVertexAttribArray(glGetAttribLocation2);
                                zzdo("enableVertexAttribArray");
                                int[] iArr32 = new int[1];
                                GLES20.glGenTextures(1, iArr32, 0);
                                zzdo("genTextures");
                                int i22 = iArr32[0];
                                GLES20.glBindTexture(36197, i22);
                                zzdo("bindTextures");
                                GLES20.glTexParameteri(36197, 10240, 9729);
                                zzdo("texParameteri");
                                GLES20.glTexParameteri(36197, 10241, 9729);
                                zzdo("texParameteri");
                                GLES20.glTexParameteri(36197, 10242, 33071);
                                zzdo("texParameteri");
                                GLES20.glTexParameteri(36197, 10243, 33071);
                                zzdo("texParameteri");
                                int glGetUniformLocation4 = GLES20.glGetUniformLocation(this.zzczi, "uVMat");
                                this.zzczj = glGetUniformLocation4;
                                GLES20.glUniformMatrix3fv(glGetUniformLocation4, 1, false, new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f}, 0);
                                if (this.zzczi != 0) {
                                }
                                if (z) {
                                }
                                String valueOf2 = String.valueOf(GLUtils.getEGLErrorString(this.zzczo.eglGetError()));
                                if (valueOf2.length() != 0) {
                                }
                                zzakb.e(concat);
                                zzbv.zzeo().zza(new Throwable(concat), "SphericalVideoProcessor.run.1");
                                zztk();
                                this.zzczm.countDown();
                            }
                        }
                    }
                }
            }
        }
        z = false;
        zzna<String> zzna3 = zznk.zzazp;
        zzd = zzd(35633, ((String) zzkb.zzik().zzd(zzna3)).equals(zzna3.zzja()) ? (String) zzkb.zzik().zzd(zzna3) : "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}");
        if (zzd != 0) {
        }
        i = 0;
        this.zzczi = i;
        GLES20.glUseProgram(i);
        zzdo("useProgram");
        int glGetAttribLocation22 = GLES20.glGetAttribLocation(this.zzczi, "aPosition");
        GLES20.glVertexAttribPointer(glGetAttribLocation22, 3, 5126, false, 12, (Buffer) this.zzczl);
        zzdo("vertexAttribPointer");
        GLES20.glEnableVertexAttribArray(glGetAttribLocation22);
        zzdo("enableVertexAttribArray");
        int[] iArr322 = new int[1];
        GLES20.glGenTextures(1, iArr322, 0);
        zzdo("genTextures");
        int i222 = iArr322[0];
        GLES20.glBindTexture(36197, i222);
        zzdo("bindTextures");
        GLES20.glTexParameteri(36197, 10240, 9729);
        zzdo("texParameteri");
        GLES20.glTexParameteri(36197, 10241, 9729);
        zzdo("texParameteri");
        GLES20.glTexParameteri(36197, 10242, 33071);
        zzdo("texParameteri");
        GLES20.glTexParameteri(36197, 10243, 33071);
        zzdo("texParameteri");
        int glGetUniformLocation42 = GLES20.glGetUniformLocation(this.zzczi, "uVMat");
        this.zzczj = glGetUniformLocation42;
        GLES20.glUniformMatrix3fv(glGetUniformLocation42, 1, false, new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f}, 0);
        if (this.zzczi != 0) {
        }
        if (z) {
        }
        String valueOf22 = String.valueOf(GLUtils.getEGLErrorString(this.zzczo.eglGetError()));
        if (valueOf22.length() != 0) {
        }
        zzakb.e(concat);
        zzbv.zzeo().zza(new Throwable(concat), "SphericalVideoProcessor.run.1");
        zztk();
        this.zzczm.countDown();
    }

    public final void zza(SurfaceTexture surfaceTexture, int i, int i2) {
        this.zzuq = i;
        this.zzur = i2;
        this.zzczh = surfaceTexture;
    }

    public final void zzb(float f, float f2) {
        float f3;
        float f4;
        float f5;
        int i = this.zzuq;
        int i2 = this.zzur;
        float f6 = f * 1.7453293f;
        if (i > i2) {
            f4 = f6 / ((float) i);
            f3 = f2 * 1.7453293f;
            f5 = (float) i;
        } else {
            f4 = f6 / ((float) i2);
            f3 = f2 * 1.7453293f;
            f5 = (float) i2;
        }
        this.zzcze -= f4;
        float f7 = this.zzczf - (f3 / f5);
        this.zzczf = f7;
        if (f7 < -1.5707964f) {
            this.zzczf = -1.5707964f;
        }
        if (this.zzczf > 1.5707964f) {
            this.zzczf = 1.5707964f;
        }
    }

    public final void zzh(int i, int i2) {
        synchronized (this.zzczn) {
            this.zzuq = i;
            this.zzur = i2;
            this.zzczs = true;
            this.zzczn.notifyAll();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzapt
    public final void zznn() {
        synchronized (this.zzczn) {
            this.zzczn.notifyAll();
        }
    }

    public final void zzti() {
        synchronized (this.zzczn) {
            this.zzczt = true;
            this.zzczh = null;
            this.zzczn.notifyAll();
        }
    }

    public final SurfaceTexture zztj() {
        if (this.zzczh == null) {
            return null;
        }
        try {
            this.zzczm.await();
        } catch (InterruptedException unused) {
        }
        return this.zzczg;
    }
}
