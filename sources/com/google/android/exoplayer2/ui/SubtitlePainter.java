package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.RelativeSizeSpan;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.mopub.mobileads.resource.DrawableConstants;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

final class SubtitlePainter {
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private int backgroundColor;
    private Rect bitmapRect;
    private float bottomPaddingFraction;
    private Bitmap cueBitmap;
    private float cueBitmapHeight;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    private float cueSize;
    private CharSequence cueText;
    private Layout.Alignment cueTextAlignment;
    private float cueTextSizePx;
    private float defaultTextSizePx;
    private int edgeColor;
    private int edgeType;
    private int foregroundColor;
    private final float outlineWidth;
    private final Paint paint;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint;
    private int textTop;
    private int windowColor;

    public SubtitlePainter(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{16843287, 16843288}, 0, 0);
        this.spacingAdd = (float) obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
        float round = (float) Math.round((((float) context.getResources().getDisplayMetrics().densityDpi) * 2.0f) / 160.0f);
        this.outlineWidth = round;
        this.shadowRadius = round;
        this.shadowOffset = round;
        TextPaint textPaint2 = new TextPaint();
        this.textPaint = textPaint2;
        textPaint2.setAntiAlias(true);
        this.textPaint.setSubpixelText(true);
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL);
    }

    public void draw(Cue cue, boolean z, boolean z2, CaptionStyleCompat captionStyleCompat, float f, float f2, float f3, Canvas canvas, int i, int i2, int i3, int i4) {
        boolean z3 = cue.bitmap == null;
        int i5 = DrawableConstants.CtaButton.BACKGROUND_COLOR;
        if (z3) {
            if (!TextUtils.isEmpty(cue.text)) {
                i5 = (!cue.windowColorSet || !z) ? captionStyleCompat.windowColor : cue.windowColor;
            } else {
                return;
            }
        }
        if (areCharSequencesEqual(this.cueText, cue.text) && Util.areEqual(this.cueTextAlignment, cue.textAlignment) && this.cueBitmap == cue.bitmap && this.cueLine == cue.line && this.cueLineType == cue.lineType && Util.areEqual(Integer.valueOf(this.cueLineAnchor), Integer.valueOf(cue.lineAnchor)) && this.cuePosition == cue.position && Util.areEqual(Integer.valueOf(this.cuePositionAnchor), Integer.valueOf(cue.positionAnchor)) && this.cueSize == cue.size && this.cueBitmapHeight == cue.bitmapHeight && this.applyEmbeddedStyles == z && this.applyEmbeddedFontSizes == z2 && this.foregroundColor == captionStyleCompat.foregroundColor && this.backgroundColor == captionStyleCompat.backgroundColor && this.windowColor == i5 && this.edgeType == captionStyleCompat.edgeType && this.edgeColor == captionStyleCompat.edgeColor && Util.areEqual(this.textPaint.getTypeface(), captionStyleCompat.typeface) && this.defaultTextSizePx == f && this.cueTextSizePx == f2 && this.bottomPaddingFraction == f3 && this.parentLeft == i && this.parentTop == i2 && this.parentRight == i3 && this.parentBottom == i4) {
            drawLayout(canvas, z3);
            return;
        }
        this.cueText = cue.text;
        this.cueTextAlignment = cue.textAlignment;
        this.cueBitmap = cue.bitmap;
        this.cueLine = cue.line;
        this.cueLineType = cue.lineType;
        this.cueLineAnchor = cue.lineAnchor;
        this.cuePosition = cue.position;
        this.cuePositionAnchor = cue.positionAnchor;
        this.cueSize = cue.size;
        this.cueBitmapHeight = cue.bitmapHeight;
        this.applyEmbeddedStyles = z;
        this.applyEmbeddedFontSizes = z2;
        this.foregroundColor = captionStyleCompat.foregroundColor;
        this.backgroundColor = captionStyleCompat.backgroundColor;
        this.windowColor = i5;
        this.edgeType = captionStyleCompat.edgeType;
        this.edgeColor = captionStyleCompat.edgeColor;
        this.textPaint.setTypeface(captionStyleCompat.typeface);
        this.defaultTextSizePx = f;
        this.cueTextSizePx = f2;
        this.bottomPaddingFraction = f3;
        this.parentLeft = i;
        this.parentTop = i2;
        this.parentRight = i3;
        this.parentBottom = i4;
        if (z3) {
            Assertions.checkNotNull(this.cueText);
            setupTextLayout();
        } else {
            Assertions.checkNotNull(this.cueBitmap);
            setupBitmapLayout();
        }
        drawLayout(canvas, z3);
    }

    @RequiresNonNull({"cueText"})
    private void setupTextLayout() {
        SpannableStringBuilder spannableStringBuilder;
        int i;
        int i2;
        int i3;
        int round;
        int i4;
        SpannableStringBuilder spannableStringBuilder2;
        CharSequence charSequence = this.cueText;
        int i5 = this.parentRight - this.parentLeft;
        int i6 = this.parentBottom - this.parentTop;
        this.textPaint.setTextSize(this.defaultTextSizePx);
        int i7 = (int) ((this.defaultTextSizePx * 0.125f) + 0.5f);
        int i8 = i7 * 2;
        int i9 = i5 - i8;
        float f = this.cueSize;
        if (f != -3.4028235E38f) {
            i9 = (int) (((float) i9) * f);
        }
        if (i9 <= 0) {
            Log.w("SubtitlePainter", "Skipped drawing subtitle cue (insufficient space)");
            return;
        }
        if (!this.applyEmbeddedStyles) {
            charSequence = charSequence.toString();
        } else {
            if (!this.applyEmbeddedFontSizes) {
                spannableStringBuilder2 = new SpannableStringBuilder(charSequence);
                int length = spannableStringBuilder2.length();
                AbsoluteSizeSpan[] absoluteSizeSpanArr = (AbsoluteSizeSpan[]) spannableStringBuilder2.getSpans(0, length, AbsoluteSizeSpan.class);
                RelativeSizeSpan[] relativeSizeSpanArr = (RelativeSizeSpan[]) spannableStringBuilder2.getSpans(0, length, RelativeSizeSpan.class);
                for (AbsoluteSizeSpan absoluteSizeSpan : absoluteSizeSpanArr) {
                    spannableStringBuilder2.removeSpan(absoluteSizeSpan);
                }
                for (RelativeSizeSpan relativeSizeSpan : relativeSizeSpanArr) {
                    spannableStringBuilder2.removeSpan(relativeSizeSpan);
                }
            } else if (this.cueTextSizePx > 0.0f) {
                spannableStringBuilder2 = new SpannableStringBuilder(charSequence);
                spannableStringBuilder2.setSpan(new AbsoluteSizeSpan((int) this.cueTextSizePx), 0, spannableStringBuilder2.length(), 16711680);
            }
            charSequence = spannableStringBuilder2;
        }
        if (Color.alpha(this.backgroundColor) > 0) {
            SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(charSequence);
            spannableStringBuilder3.setSpan(new BackgroundColorSpan(this.backgroundColor), 0, spannableStringBuilder3.length(), 16711680);
            spannableStringBuilder = spannableStringBuilder3;
        } else {
            spannableStringBuilder = charSequence;
        }
        Layout.Alignment alignment = this.cueTextAlignment;
        if (alignment == null) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        }
        StaticLayout staticLayout = new StaticLayout(spannableStringBuilder, this.textPaint, i9, alignment, this.spacingMult, this.spacingAdd, true);
        this.textLayout = staticLayout;
        int height = staticLayout.getHeight();
        int lineCount = this.textLayout.getLineCount();
        int i10 = 0;
        for (int i11 = 0; i11 < lineCount; i11++) {
            i10 = Math.max((int) Math.ceil((double) this.textLayout.getLineWidth(i11)), i10);
        }
        if (this.cueSize == -3.4028235E38f || i10 >= i9) {
            i9 = i10;
        }
        int i12 = i9 + i8;
        float f2 = this.cuePosition;
        if (f2 != -3.4028235E38f) {
            int round2 = Math.round(((float) i5) * f2) + this.parentLeft;
            int i13 = this.cuePositionAnchor;
            if (i13 == 1) {
                round2 = ((round2 * 2) - i12) / 2;
            } else if (i13 == 2) {
                round2 -= i12;
            }
            i2 = Math.max(round2, this.parentLeft);
            i = Math.min(i12 + i2, this.parentRight);
        } else {
            i2 = ((i5 - i12) / 2) + this.parentLeft;
            i = i2 + i12;
        }
        int i14 = i - i2;
        if (i14 <= 0) {
            Log.w("SubtitlePainter", "Skipped drawing subtitle cue (invalid horizontal positioning)");
            return;
        }
        float f3 = this.cueLine;
        if (f3 != -3.4028235E38f) {
            if (this.cueLineType == 0) {
                round = Math.round(((float) i6) * f3);
                i4 = this.parentTop;
            } else {
                int lineBottom = this.textLayout.getLineBottom(0) - this.textLayout.getLineTop(0);
                float f4 = this.cueLine;
                if (f4 >= 0.0f) {
                    round = Math.round(f4 * ((float) lineBottom));
                    i4 = this.parentTop;
                } else {
                    round = Math.round((f4 + 1.0f) * ((float) lineBottom));
                    i4 = this.parentBottom;
                }
            }
            i3 = round + i4;
            int i15 = this.cueLineAnchor;
            if (i15 == 2) {
                i3 -= height;
            } else if (i15 == 1) {
                i3 = ((i3 * 2) - height) / 2;
            }
            int i16 = i3 + height;
            int i17 = this.parentBottom;
            if (i16 > i17) {
                i3 = i17 - height;
            } else {
                int i18 = this.parentTop;
                if (i3 < i18) {
                    i3 = i18;
                }
            }
        } else {
            i3 = (this.parentBottom - height) - ((int) (((float) i6) * this.bottomPaddingFraction));
        }
        this.textLayout = new StaticLayout(spannableStringBuilder, this.textPaint, i14, alignment, this.spacingMult, this.spacingAdd, true);
        this.textLeft = i2;
        this.textTop = i3;
        this.textPaddingX = i7;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e  */
    @RequiresNonNull({"cueBitmap"})
    private void setupBitmapLayout() {
        int i;
        int i2;
        float f;
        float f2;
        Bitmap bitmap = this.cueBitmap;
        int i3 = this.parentRight;
        int i4 = this.parentLeft;
        int i5 = this.parentBottom;
        int i6 = this.parentTop;
        float f3 = (float) (i3 - i4);
        float f4 = ((float) i4) + (this.cuePosition * f3);
        float f5 = (float) (i5 - i6);
        float f6 = ((float) i6) + (this.cueLine * f5);
        int round = Math.round(f3 * this.cueSize);
        float f7 = this.cueBitmapHeight;
        if (f7 != -3.4028235E38f) {
            i = Math.round(f5 * f7);
        } else {
            i = Math.round(((float) round) * (((float) bitmap.getHeight()) / ((float) bitmap.getWidth())));
        }
        int i7 = this.cuePositionAnchor;
        if (i7 == 2) {
            f2 = (float) round;
        } else {
            if (i7 == 1) {
                f2 = (float) (round / 2);
            }
            int round2 = Math.round(f4);
            i2 = this.cueLineAnchor;
            if (i2 != 2) {
                f = (float) i;
            } else {
                if (i2 == 1) {
                    f = (float) (i / 2);
                }
                int round3 = Math.round(f6);
                this.bitmapRect = new Rect(round2, round3, round + round2, i + round3);
            }
            f6 -= f;
            int round32 = Math.round(f6);
            this.bitmapRect = new Rect(round2, round32, round + round2, i + round32);
        }
        f4 -= f2;
        int round22 = Math.round(f4);
        i2 = this.cueLineAnchor;
        if (i2 != 2) {
        }
        f6 -= f;
        int round322 = Math.round(f6);
        this.bitmapRect = new Rect(round22, round322, round + round22, i + round322);
    }

    private void drawLayout(Canvas canvas, boolean z) {
        if (z) {
            drawTextLayout(canvas);
            return;
        }
        Assertions.checkNotNull(this.bitmapRect);
        Assertions.checkNotNull(this.cueBitmap);
        drawBitmapLayout(canvas);
    }

    private void drawTextLayout(Canvas canvas) {
        int i;
        StaticLayout staticLayout = this.textLayout;
        if (staticLayout != null) {
            int save = canvas.save();
            canvas.translate((float) this.textLeft, (float) this.textTop);
            if (Color.alpha(this.windowColor) > 0) {
                this.paint.setColor(this.windowColor);
                canvas.drawRect((float) (-this.textPaddingX), 0.0f, (float) (staticLayout.getWidth() + this.textPaddingX), (float) staticLayout.getHeight(), this.paint);
            }
            int i2 = this.edgeType;
            boolean z = true;
            if (i2 == 1) {
                this.textPaint.setStrokeJoin(Paint.Join.ROUND);
                this.textPaint.setStrokeWidth(this.outlineWidth);
                this.textPaint.setColor(this.edgeColor);
                this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                staticLayout.draw(canvas);
            } else if (i2 == 2) {
                TextPaint textPaint2 = this.textPaint;
                float f = this.shadowRadius;
                float f2 = this.shadowOffset;
                textPaint2.setShadowLayer(f, f2, f2, this.edgeColor);
            } else if (i2 == 3 || i2 == 4) {
                if (this.edgeType != 3) {
                    z = false;
                }
                int i3 = -1;
                if (z) {
                    i = -1;
                } else {
                    i = this.edgeColor;
                }
                if (z) {
                    i3 = this.edgeColor;
                }
                float f3 = this.shadowRadius / 2.0f;
                this.textPaint.setColor(this.foregroundColor);
                this.textPaint.setStyle(Paint.Style.FILL);
                float f4 = -f3;
                this.textPaint.setShadowLayer(this.shadowRadius, f4, f4, i);
                staticLayout.draw(canvas);
                this.textPaint.setShadowLayer(this.shadowRadius, f3, f3, i3);
            }
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Paint.Style.FILL);
            staticLayout.draw(canvas);
            this.textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            canvas.restoreToCount(save);
        }
    }

    @RequiresNonNull({"cueBitmap", "bitmapRect"})
    private void drawBitmapLayout(Canvas canvas) {
        canvas.drawBitmap(this.cueBitmap, (Rect) null, this.bitmapRect, (Paint) null);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == charSequence2 || (charSequence != null && charSequence.equals(charSequence2));
    }
}
