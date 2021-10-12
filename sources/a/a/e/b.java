package a.a.e;

import a.a.b.b.e;
import a.a.b.b.h;
import java.io.Serializable;
import java.util.regex.Pattern;

/* compiled from: StartAppSDK */
public final class b implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public static final a f11a = new a(null);
    private final Pattern nativePattern;

    public b(Pattern pattern) {
        h.b(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public b(String str) {
        this(r2);
        h.b(str, "pattern");
        Pattern compile = Pattern.compile(str);
        h.a((Object) compile, "Pattern.compile(pattern)");
    }

    public final boolean a(CharSequence charSequence) {
        h.b(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    public String toString() {
        String pattern = this.nativePattern.toString();
        h.a((Object) pattern, "nativePattern.toString()");
        return pattern;
    }

    /* compiled from: StartAppSDK */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(e eVar) {
            this();
        }
    }
}
