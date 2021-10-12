package androidx.media2.widget;

import android.util.Log;
import com.mopub.mobileads.resource.DrawableConstants;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class Cea708CCParser {
    private static final String MUSIC_NOTE_CHAR = new String("♫".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    private final StringBuilder mBuilder = new StringBuilder();
    private int mCommand = 0;
    private DisplayListener mListener = new DisplayListener() {
        /* class androidx.media2.widget.Cea708CCParser.AnonymousClass1 */

        @Override // androidx.media2.widget.Cea708CCParser.DisplayListener
        public void emitEvent(CaptionEvent captionEvent) {
        }
    };

    /* access modifiers changed from: package-private */
    public interface DisplayListener {
        void emitEvent(CaptionEvent captionEvent);
    }

    private int parseG2(byte[] bArr, int i) {
        return i;
    }

    private int parseG3(byte[] bArr, int i) {
        return i;
    }

    Cea708CCParser(DisplayListener displayListener) {
        if (displayListener != null) {
            this.mListener = displayListener;
        }
    }

    private void emitCaptionEvent(CaptionEvent captionEvent) {
        emitCaptionBuffer();
        this.mListener.emitEvent(captionEvent);
    }

    private void emitCaptionBuffer() {
        if (this.mBuilder.length() > 0) {
            this.mListener.emitEvent(new CaptionEvent(1, this.mBuilder.toString()));
            this.mBuilder.setLength(0);
        }
    }

    public void parse(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            i = parseServiceBlockData(bArr, i);
        }
        emitCaptionBuffer();
    }

    private int parseServiceBlockData(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        this.mCommand = i2;
        int i3 = i + 1;
        if (i2 == 16) {
            return parseExt1(bArr, i3);
        }
        if (i2 >= 0 && i2 <= 31) {
            return parseC0(bArr, i3);
        }
        int i4 = this.mCommand;
        if (i4 >= 128 && i4 <= 159) {
            return parseC1(bArr, i3);
        }
        int i5 = this.mCommand;
        if (i5 >= 32 && i5 <= 127) {
            return parseG0(bArr, i3);
        }
        int i6 = this.mCommand;
        return (i6 < 160 || i6 > 255) ? i3 : parseG1(bArr, i3);
    }

    private int parseC0(byte[] bArr, int i) {
        int i2 = this.mCommand;
        if (i2 < 24 || i2 > 31) {
            int i3 = this.mCommand;
            if (i3 >= 16 && i3 <= 23) {
                return i + 1;
            }
            int i4 = this.mCommand;
            if (i4 == 3) {
                emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) i4)));
                return i;
            } else if (i4 != 8) {
                switch (i4) {
                    case 12:
                        emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) i4)));
                        return i;
                    case 13:
                        this.mBuilder.append('\n');
                        return i;
                    case 14:
                        emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) i4)));
                        return i;
                    default:
                        return i;
                }
            } else {
                emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) i4)));
                return i;
            }
        } else {
            if (i2 == 24) {
                try {
                    if (bArr[i] == 0) {
                        this.mBuilder.append((char) bArr[i + 1]);
                    } else {
                        this.mBuilder.append(new String(Arrays.copyOfRange(bArr, i, i + 2), "EUC-KR"));
                    }
                } catch (UnsupportedEncodingException e) {
                    Log.e("Cea708CCParser", "P16 Code - Could not find supported encoding", e);
                }
            }
            return i + 2;
        }
    }

    private int parseC1(byte[] bArr, int i) {
        int i2;
        int i3;
        int i4 = this.mCommand;
        switch (i4) {
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
                emitCaptionEvent(new CaptionEvent(3, Integer.valueOf(i4 - 128)));
                return i;
            case 136:
                int i5 = i + 1;
                emitCaptionEvent(new CaptionEvent(4, Integer.valueOf(bArr[i] & 255)));
                return i5;
            case 137:
                int i6 = i + 1;
                emitCaptionEvent(new CaptionEvent(5, Integer.valueOf(bArr[i] & 255)));
                return i6;
            case 138:
                int i7 = i + 1;
                emitCaptionEvent(new CaptionEvent(6, Integer.valueOf(bArr[i] & 255)));
                return i7;
            case 139:
                int i8 = i + 1;
                emitCaptionEvent(new CaptionEvent(7, Integer.valueOf(bArr[i] & 255)));
                return i8;
            case 140:
                int i9 = i + 1;
                emitCaptionEvent(new CaptionEvent(8, Integer.valueOf(bArr[i] & 255)));
                return i9;
            case 141:
                int i10 = i + 1;
                emitCaptionEvent(new CaptionEvent(9, Integer.valueOf(bArr[i] & 255)));
                return i10;
            case 142:
                emitCaptionEvent(new CaptionEvent(10, null));
                return i;
            case 143:
                emitCaptionEvent(new CaptionEvent(11, null));
                return i;
            case 144:
                int i11 = (bArr[i] & 240) >> 4;
                int i12 = bArr[i] & 3;
                int i13 = (bArr[i] & 12) >> 2;
                int i14 = i + 1;
                boolean z = (bArr[i14] & 128) != 0;
                boolean z2 = (bArr[i14] & 64) != 0;
                int i15 = bArr[i14] & 7;
                i2 = i + 2;
                emitCaptionEvent(new CaptionEvent(12, new CaptionPenAttr(i12, i13, i11, i15, (bArr[i14] & 56) >> 3, z2, z)));
                return i2;
            case 145:
                int i16 = i + 1;
                int i17 = i16 + 1;
                i2 = i17 + 1;
                emitCaptionEvent(new CaptionEvent(13, new CaptionPenColor(new CaptionColor((bArr[i] & 192) >> 6, (bArr[i] & 48) >> 4, (bArr[i] & 12) >> 2, bArr[i] & 3), new CaptionColor((bArr[i16] & 192) >> 6, (bArr[i16] & 48) >> 4, (bArr[i16] & 12) >> 2, bArr[i16] & 3), new CaptionColor(0, (bArr[i17] & 48) >> 4, (bArr[i17] & 12) >> 2, bArr[i17] & 3))));
                return i2;
            case 146:
                i3 = i + 2;
                emitCaptionEvent(new CaptionEvent(14, new CaptionPenLocation(bArr[i] & 15, bArr[i + 1] & 63)));
                return i3;
            case 147:
            case 148:
            case 149:
            case DrawableConstants.CtaButton.WIDTH_DIPS /*{ENCODED_INT: 150}*/:
            default:
                return i;
            case 151:
                CaptionColor captionColor = new CaptionColor((bArr[i] & 192) >> 6, (bArr[i] & 48) >> 4, (bArr[i] & 12) >> 2, bArr[i] & 3);
                int i18 = i + 1;
                int i19 = i + 2;
                int i20 = ((bArr[i18] & 192) >> 6) | ((bArr[i19] & 128) >> 5);
                CaptionColor captionColor2 = new CaptionColor(0, (bArr[i18] & 48) >> 4, (bArr[i18] & 12) >> 2, bArr[i18] & 3);
                boolean z3 = (bArr[i19] & 64) != 0;
                int i21 = (bArr[i19] & 48) >> 4;
                int i22 = (bArr[i19] & 12) >> 2;
                int i23 = bArr[i19] & 3;
                int i24 = i + 3;
                int i25 = (bArr[i24] & 240) >> 4;
                int i26 = bArr[i24] & 3;
                i2 = i + 4;
                emitCaptionEvent(new CaptionEvent(15, new CaptionWindowAttr(captionColor, captionColor2, i20, z3, i21, i22, i23, (bArr[i24] & 12) >> 2, i25, i26)));
                return i2;
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
                int i27 = i4 - 152;
                boolean z4 = (bArr[i] & 32) != 0;
                boolean z5 = (bArr[i] & 16) != 0;
                boolean z6 = (bArr[i] & 8) != 0;
                int i28 = bArr[i] & 7;
                int i29 = i + 1;
                boolean z7 = (bArr[i29] & 128) != 0;
                int i30 = i + 3;
                int i31 = bArr[i30] & 15;
                int i32 = bArr[i + 4] & 63;
                int i33 = i + 5;
                int i34 = bArr[i33] & 7;
                i3 = i + 6;
                emitCaptionEvent(new CaptionEvent(16, new CaptionWindow(i27, z4, z5, z6, i28, z7, bArr[i29] & Byte.MAX_VALUE, bArr[i + 2] & 255, (bArr[i30] & 240) >> 4, i31, i32, i34, (bArr[i33] & 56) >> 3)));
                return i3;
        }
    }

    private int parseG0(byte[] bArr, int i) {
        int i2 = this.mCommand;
        if (i2 == 127) {
            this.mBuilder.append(MUSIC_NOTE_CHAR);
        } else {
            this.mBuilder.append((char) i2);
        }
        return i;
    }

    private int parseG1(byte[] bArr, int i) {
        this.mBuilder.append((char) this.mCommand);
        return i;
    }

    private int parseExt1(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        this.mCommand = i2;
        int i3 = i + 1;
        if (i2 >= 0 && i2 <= 31) {
            return parseC2(bArr, i3);
        }
        int i4 = this.mCommand;
        if (i4 >= 128 && i4 <= 159) {
            return parseC3(bArr, i3);
        }
        int i5 = this.mCommand;
        if (i5 >= 32 && i5 <= 127) {
            return parseG2(bArr, i3);
        }
        int i6 = this.mCommand;
        return (i6 < 160 || i6 > 255) ? i3 : parseG3(bArr, i3);
    }

    private int parseC2(byte[] bArr, int i) {
        int i2 = this.mCommand;
        if (i2 >= 0 && i2 <= 7) {
            return i;
        }
        int i3 = this.mCommand;
        if (i3 >= 8 && i3 <= 15) {
            return i + 1;
        }
        int i4 = this.mCommand;
        if (i4 >= 16 && i4 <= 23) {
            return i + 2;
        }
        int i5 = this.mCommand;
        return (i5 < 24 || i5 > 31) ? i : i + 3;
    }

    private int parseC3(byte[] bArr, int i) {
        int i2 = this.mCommand;
        if (i2 >= 128 && i2 <= 135) {
            return i + 4;
        }
        int i3 = this.mCommand;
        return (i3 < 136 || i3 > 143) ? i : i + 5;
    }

    public static class CaptionColor {
        private static final int[] COLOR_MAP = {0, 15, 240, 255};
        private static final int[] OPACITY_MAP = {255, 254, 128, 0};
        public final int blue;
        public final int green;
        public final int opacity;
        public final int red;

        CaptionColor(int i, int i2, int i3, int i4) {
            this.opacity = i;
            this.red = i2;
            this.green = i3;
            this.blue = i4;
        }
    }

    public static class CaptionEvent {
        public final Object obj;
        public final int type;

        CaptionEvent(int i, Object obj2) {
            this.type = i;
            this.obj = obj2;
        }
    }

    public static class CaptionPenAttr {
        public final int edgeType;
        public final int fontTag;
        public final boolean italic;
        public final int penOffset;
        public final int penSize;
        public final int textTag;
        public final boolean underline;

        CaptionPenAttr(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            this.penSize = i;
            this.penOffset = i2;
            this.textTag = i3;
            this.fontTag = i4;
            this.edgeType = i5;
            this.underline = z;
            this.italic = z2;
        }
    }

    public static class CaptionPenColor {
        public final CaptionColor backgroundColor;
        public final CaptionColor edgeColor;
        public final CaptionColor foregroundColor;

        CaptionPenColor(CaptionColor captionColor, CaptionColor captionColor2, CaptionColor captionColor3) {
            this.foregroundColor = captionColor;
            this.backgroundColor = captionColor2;
            this.edgeColor = captionColor3;
        }
    }

    public static class CaptionPenLocation {
        public final int column;
        public final int row;

        CaptionPenLocation(int i, int i2) {
            this.row = i;
            this.column = i2;
        }
    }

    public static class CaptionWindowAttr {
        public final CaptionColor borderColor;
        public final int borderType;
        public final int displayEffect;
        public final int effectDirection;
        public final int effectSpeed;
        public final CaptionColor fillColor;
        public final int justify;
        public final int printDirection;
        public final int scrollDirection;
        public final boolean wordWrap;

        CaptionWindowAttr(CaptionColor captionColor, CaptionColor captionColor2, int i, boolean z, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.fillColor = captionColor;
            this.borderColor = captionColor2;
            this.borderType = i;
            this.wordWrap = z;
            this.printDirection = i2;
            this.scrollDirection = i3;
            this.justify = i4;
            this.effectDirection = i5;
            this.effectSpeed = i6;
            this.displayEffect = i7;
        }
    }

    public static class CaptionWindow {
        public final int anchorHorizontal;
        public final int anchorId;
        public final int anchorVertical;
        public final int columnCount;
        public final boolean columnLock;
        public final int id;
        public final int penStyle;
        public final int priority;
        public final boolean relativePositioning;
        public final int rowCount;
        public final boolean rowLock;
        public final boolean visible;
        public final int windowStyle;

        CaptionWindow(int i, boolean z, boolean z2, boolean z3, int i2, boolean z4, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.id = i;
            this.visible = z;
            this.rowLock = z2;
            this.columnLock = z3;
            this.priority = i2;
            this.relativePositioning = z4;
            this.anchorVertical = i3;
            this.anchorHorizontal = i4;
            this.anchorId = i5;
            this.rowCount = i6;
            this.columnCount = i7;
            this.penStyle = i8;
            this.windowStyle = i9;
        }
    }
}
