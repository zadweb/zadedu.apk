package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: Strings.kt */
public final class DelimitedRangesSequence$iterator$1 implements Iterator<IntRange> {
    private int counter;
    private int currentStartIndex;
    private IntRange nextItem;
    private int nextSearchIndex;
    private int nextState = -1;
    final /* synthetic */ DelimitedRangesSequence this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX WARN: Incorrect args count in method signature: ()V */
    DelimitedRangesSequence$iterator$1(DelimitedRangesSequence delimitedRangesSequence) {
        this.this$0 = delimitedRangesSequence;
        int coerceIn = RangesKt.coerceIn(DelimitedRangesSequence.access$getStartIndex$p(delimitedRangesSequence), 0, DelimitedRangesSequence.access$getInput$p(delimitedRangesSequence).length());
        this.currentStartIndex = coerceIn;
        this.nextSearchIndex = coerceIn;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        if (r0 < kotlin.text.DelimitedRangesSequence.access$getLimit$p(r6.this$0)) goto L_0x0025;
     */
    private final void calcNext() {
        int i = 0;
        if (this.nextSearchIndex < 0) {
            this.nextState = 0;
            this.nextItem = null;
            return;
        }
        if (DelimitedRangesSequence.access$getLimit$p(this.this$0) > 0) {
            int i2 = this.counter + 1;
            this.counter = i2;
        }
        if (this.nextSearchIndex <= DelimitedRangesSequence.access$getInput$p(this.this$0).length()) {
            Pair pair = (Pair) DelimitedRangesSequence.access$getGetNextMatch$p(this.this$0).invoke(DelimitedRangesSequence.access$getInput$p(this.this$0), Integer.valueOf(this.nextSearchIndex));
            if (pair == null) {
                this.nextItem = new IntRange(this.currentStartIndex, StringsKt.getLastIndex(DelimitedRangesSequence.access$getInput$p(this.this$0)));
                this.nextSearchIndex = -1;
            } else {
                int intValue = ((Number) pair.component1()).intValue();
                int intValue2 = ((Number) pair.component2()).intValue();
                this.nextItem = RangesKt.until(this.currentStartIndex, intValue);
                int i3 = intValue + intValue2;
                this.currentStartIndex = i3;
                if (intValue2 == 0) {
                    i = 1;
                }
                this.nextSearchIndex = i3 + i;
            }
            this.nextState = 1;
        }
        this.nextItem = new IntRange(this.currentStartIndex, StringsKt.getLastIndex(DelimitedRangesSequence.access$getInput$p(this.this$0)));
        this.nextSearchIndex = -1;
        this.nextState = 1;
    }

    @Override // java.util.Iterator
    public IntRange next() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 0) {
            IntRange intRange = this.nextItem;
            if (intRange != null) {
                this.nextItem = null;
                this.nextState = -1;
                return intRange;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        if (this.nextState == -1) {
            calcNext();
        }
        return this.nextState == 1;
    }
}
