package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.security.GeneralSecurityException;

class fa {

    /* renamed from: a  reason: collision with root package name */
    private static fh f1132a;
    private static ey b;
    private static fs c;

    static synchronized fh a(fe feVar) {
        fh fhVar;
        synchronized (fa.class) {
            if (f1132a == null) {
                f1132a = new fh(feVar, b(feVar), c(feVar), new fd());
            }
            fhVar = f1132a;
        }
        return fhVar;
    }

    static synchronized ey b(fe feVar) {
        ey eyVar;
        synchronized (fa.class) {
            if (b == null) {
                try {
                    b = new a(feVar, (byte) 0);
                } catch (IOException unused) {
                    b = new et();
                }
            }
            eyVar = b;
        }
        return eyVar;
    }

    static synchronized fs c(fe feVar) {
        fs fsVar;
        synchronized (fa.class) {
            if (c == null) {
                try {
                    c = feVar.d();
                } catch (IOException | GeneralSecurityException unused) {
                }
            }
            fsVar = c;
        }
        return fsVar;
    }

    /* access modifiers changed from: private */
    public static class a implements ey {

        /* renamed from: a  reason: collision with root package name */
        private static final String[] f1133a = {"LNFe+yc4/NZbJVynpxAeAd+brU3EPwGbtwF6VeUjI/Y=", "PL1/TTDEe9Cm2lb2X0tixyQC7zaPREm/V0IHJscTCmw=", "+B0DgmKB5hWEuHib00m2jvCJWBlOYI0NGTMmVjaVrJA=", "dy/Myn0WRtYGKBNP8ubn9boJWJi+WWmLzp0V+W9pqfM=", "OB84k4abNNzWpMVBdhI+TSgQmCqTKdPPQrwq6j4YdMU=", "AZQG1XXPKFo8LYu/gTPgz65IOcmcwYFb3yREhyWefNI=", "iZEDYF5LpvyxpOX9+x3+qDBXhdByZOUFatBA3JgW7sY=", "IQBnNBEiFuhj+8x6X8XLgh01V9Ic5/V3IRQLNFFc7v4=", "LvRiGEjRqfzurezaWuj8Wie2gyHMrW5Q06LspMnox7A="};
        private final fb b;
        private final fb c;

        /* synthetic */ a(fe feVar, byte b2) throws IOException {
            this(feVar);
        }

        private a(fe feVar) throws IOException {
            en enVar = new en(feVar.b(), "lib");
            this.b = new fb(enVar, "LIB-BLACK");
            this.c = new fb(enVar, "LIB-TRUST", f1133a);
        }

        @Override // com.yandex.metrica.impl.ob.ey
        public fb a() {
            return this.b;
        }

        @Override // com.yandex.metrica.impl.ob.ey
        public fb b() {
            throw new UnsupportedOperationException("white list isn't supported in shared container");
        }

        @Override // com.yandex.metrica.impl.ob.ey
        public fb c() {
            return this.c;
        }
    }
}
