package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.R;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import com.appnext.base.b.d;
import com.google.android.gms.ads.AdRequest;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccessibilityNodeInfoCompat {
    private static int sClickableSpanId;
    private final AccessibilityNodeInfo mInfo;
    public int mParentVirtualDescendantId = -1;
    private int mVirtualDescendantId = -1;

    private static String getActionSymbolicName(int i) {
        if (i == 1) {
            return "ACTION_FOCUS";
        }
        if (i == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case AdRequest.MAX_CONTENT_URL_LENGTH /*{ENCODED_INT: 512}*/:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case d.fb /*{ENCODED_INT: 1024}*/:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            case 262144:
                return "ACTION_EXPAND";
            case 524288:
                return "ACTION_COLLAPSE";
            case 2097152:
                return "ACTION_SET_TEXT";
            case 16908354:
                return "ACTION_MOVE_WINDOW";
            default:
                switch (i) {
                    case 16908342:
                        return "ACTION_SHOW_ON_SCREEN";
                    case 16908343:
                        return "ACTION_SCROLL_TO_POSITION";
                    case 16908344:
                        return "ACTION_SCROLL_UP";
                    case 16908345:
                        return "ACTION_SCROLL_LEFT";
                    case 16908346:
                        return "ACTION_SCROLL_DOWN";
                    case 16908347:
                        return "ACTION_SCROLL_RIGHT";
                    case 16908348:
                        return "ACTION_CONTEXT_CLICK";
                    case 16908349:
                        return "ACTION_SET_PROGRESS";
                    default:
                        switch (i) {
                            case 16908356:
                                return "ACTION_SHOW_TOOLTIP";
                            case 16908357:
                                return "ACTION_HIDE_TOOLTIP";
                            case 16908358:
                                return "ACTION_PAGE_UP";
                            case 16908359:
                                return "ACTION_PAGE_DOWN";
                            case 16908360:
                                return "ACTION_PAGE_LEFT";
                            case 16908361:
                                return "ACTION_PAGE_RIGHT";
                            default:
                                return "ACTION_UNKNOWN";
                        }
                }
        }
    }

    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
        public static final AccessibilityActionCompat ACTION_CLICK = new AccessibilityActionCompat(16, null);
        public static final AccessibilityActionCompat ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null, 16908348, null, null, null);
        public static final AccessibilityActionCompat ACTION_COPY = new AccessibilityActionCompat(16384, null);
        public static final AccessibilityActionCompat ACTION_CUT = new AccessibilityActionCompat(65536, null);
        public static final AccessibilityActionCompat ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);
        public static final AccessibilityActionCompat ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
        public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1, null);
        public static final AccessibilityActionCompat ACTION_HIDE_TOOLTIP;
        public static final AccessibilityActionCompat ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
        public static final AccessibilityActionCompat ACTION_MOVE_WINDOW = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, 16908354, null, null, AccessibilityViewCommand.MoveWindowArguments.class);
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, null, AccessibilityViewCommand.MoveAtGranularityArguments.class);
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(d.fb, null, AccessibilityViewCommand.MoveHtmlArguments.class);
        public static final AccessibilityActionCompat ACTION_PAGE_DOWN = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, 16908359, null, null, null);
        public static final AccessibilityActionCompat ACTION_PAGE_LEFT = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, 16908360, null, null, null);
        public static final AccessibilityActionCompat ACTION_PAGE_RIGHT = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, 16908361, null, null, null);
        public static final AccessibilityActionCompat ACTION_PAGE_UP = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, 16908358, null, null, null);
        public static final AccessibilityActionCompat ACTION_PASTE = new AccessibilityActionCompat(32768, null);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(AdRequest.MAX_CONTENT_URL_LENGTH, null, AccessibilityViewCommand.MoveAtGranularityArguments.class);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, null, AccessibilityViewCommand.MoveHtmlArguments.class);
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null, 16908346, null, null, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null, 16908345, null, null, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null, 16908347, null, null, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null, 16908343, null, null, AccessibilityViewCommand.ScrollToPositionArguments.class);
        public static final AccessibilityActionCompat ACTION_SCROLL_UP = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null, 16908344, null, null, null);
        public static final AccessibilityActionCompat ACTION_SELECT = new AccessibilityActionCompat(4, null);
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, 16908349, null, null, AccessibilityViewCommand.SetProgressArguments.class);
        public static final AccessibilityActionCompat ACTION_SET_SELECTION = new AccessibilityActionCompat(131072, null, AccessibilityViewCommand.SetSelectionArguments.class);
        public static final AccessibilityActionCompat ACTION_SET_TEXT = new AccessibilityActionCompat(2097152, null, AccessibilityViewCommand.SetTextArguments.class);
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null, 16908342, null, null, null);
        public static final AccessibilityActionCompat ACTION_SHOW_TOOLTIP = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, 16908356, null, null, null);
        final Object mAction;
        protected final AccessibilityViewCommand mCommand;
        private final int mId;
        private final Class<? extends AccessibilityViewCommand.CommandArguments> mViewCommandArgumentClass;

        static {
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction = null;
            if (Build.VERSION.SDK_INT >= 28) {
                accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
            }
            ACTION_HIDE_TOOLTIP = new AccessibilityActionCompat(accessibilityAction, 16908357, null, null, null);
        }

        public AccessibilityActionCompat(int i, CharSequence charSequence) {
            this(null, i, charSequence, null, null);
        }

        AccessibilityActionCompat(Object obj) {
            this(obj, 0, null, null, null);
        }

        private AccessibilityActionCompat(int i, CharSequence charSequence, Class<? extends AccessibilityViewCommand.CommandArguments> cls) {
            this(null, i, charSequence, null, cls);
        }

        AccessibilityActionCompat(Object obj, int i, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand, Class<? extends AccessibilityViewCommand.CommandArguments> cls) {
            this.mId = i;
            this.mCommand = accessibilityViewCommand;
            if (Build.VERSION.SDK_INT < 21 || obj != null) {
                this.mAction = obj;
            } else {
                this.mAction = new AccessibilityNodeInfo.AccessibilityAction(i, charSequence);
            }
            this.mViewCommandArgumentClass = cls;
        }

        public int getId() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getId();
            }
            return 0;
        }

        public CharSequence getLabel() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getLabel();
            }
            return null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0025  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0028  */
        public boolean perform(View view, Bundle bundle) {
            Exception e;
            Class<? extends AccessibilityViewCommand.CommandArguments> cls;
            String str;
            if (this.mCommand == null) {
                return false;
            }
            AccessibilityViewCommand.CommandArguments commandArguments = null;
            Class<? extends AccessibilityViewCommand.CommandArguments> cls2 = this.mViewCommandArgumentClass;
            if (cls2 != null) {
                try {
                    AccessibilityViewCommand.CommandArguments commandArguments2 = (AccessibilityViewCommand.CommandArguments) cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    try {
                        commandArguments2.setBundle(bundle);
                        commandArguments = commandArguments2;
                    } catch (Exception e2) {
                        e = e2;
                        commandArguments = commandArguments2;
                        cls = this.mViewCommandArgumentClass;
                        if (cls != null) {
                        }
                        Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + str, e);
                        return this.mCommand.perform(view, commandArguments);
                    }
                } catch (Exception e3) {
                    e = e3;
                    cls = this.mViewCommandArgumentClass;
                    if (cls != null) {
                        str = "null";
                    } else {
                        str = cls.getName();
                    }
                    Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + str, e);
                    return this.mCommand.perform(view, commandArguments);
                }
            }
            return this.mCommand.perform(view, commandArguments);
        }

        public AccessibilityActionCompat createReplacementAction(CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand) {
            return new AccessibilityActionCompat(null, this.mId, charSequence, accessibilityViewCommand, this.mViewCommandArgumentClass);
        }

        public int hashCode() {
            Object obj = this.mAction;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof AccessibilityActionCompat)) {
                return false;
            }
            AccessibilityActionCompat accessibilityActionCompat = (AccessibilityActionCompat) obj;
            Object obj2 = this.mAction;
            if (obj2 == null) {
                if (accessibilityActionCompat.mAction != null) {
                    return false;
                }
                return true;
            } else if (!obj2.equals(accessibilityActionCompat.mAction)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static class CollectionInfoCompat {
        final Object mInfo;

        public static CollectionInfoCompat obtain(int i, int i2, boolean z, int i3) {
            if (Build.VERSION.SDK_INT >= 21) {
                return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3));
            }
            if (Build.VERSION.SDK_INT >= 19) {
                return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z));
            }
            return new CollectionInfoCompat(null);
        }

        CollectionInfoCompat(Object obj) {
            this.mInfo = obj;
        }
    }

    public static class CollectionItemInfoCompat {
        final Object mInfo;

        public static CollectionItemInfoCompat obtain(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            if (Build.VERSION.SDK_INT >= 21) {
                return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2));
            }
            if (Build.VERSION.SDK_INT >= 19) {
                return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z));
            }
            return new CollectionItemInfoCompat(null);
        }

        CollectionItemInfoCompat(Object obj) {
            this.mInfo = obj;
        }
    }

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.mInfo = accessibilityNodeInfo;
    }

    public static AccessibilityNodeInfoCompat wrap(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }

    public static AccessibilityNodeInfoCompat obtain(View view) {
        return wrap(AccessibilityNodeInfo.obtain(view));
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return wrap(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return wrap(AccessibilityNodeInfo.obtain(accessibilityNodeInfoCompat.mInfo));
    }

    public void setSource(View view) {
        this.mVirtualDescendantId = -1;
        this.mInfo.setSource(view);
    }

    public void setSource(View view, int i) {
        this.mVirtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setSource(view, i);
        }
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public void addChild(View view) {
        this.mInfo.addChild(view);
    }

    public void addChild(View view, int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.addChild(view, i);
        }
    }

    public int getActions() {
        return this.mInfo.getActions();
    }

    public void addAction(int i) {
        this.mInfo.addAction(i);
    }

    private List<Integer> extrasIntList(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        ArrayList<Integer> integerArrayList = this.mInfo.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.mInfo.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public void addAction(AccessibilityActionCompat accessibilityActionCompat) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) accessibilityActionCompat.mAction);
        }
    }

    public boolean removeAction(AccessibilityActionCompat accessibilityActionCompat) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.mInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) accessibilityActionCompat.mAction);
        }
        return false;
    }

    public boolean performAction(int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.mInfo.performAction(i, bundle);
        }
        return false;
    }

    public void setParent(View view) {
        this.mParentVirtualDescendantId = -1;
        this.mInfo.setParent(view);
    }

    public void setParent(View view, int i) {
        this.mParentVirtualDescendantId = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setParent(view, i);
        }
    }

    @Deprecated
    public void getBoundsInParent(Rect rect) {
        this.mInfo.getBoundsInParent(rect);
    }

    @Deprecated
    public void setBoundsInParent(Rect rect) {
        this.mInfo.setBoundsInParent(rect);
    }

    public void getBoundsInScreen(Rect rect) {
        this.mInfo.getBoundsInScreen(rect);
    }

    public void setBoundsInScreen(Rect rect) {
        this.mInfo.setBoundsInScreen(rect);
    }

    public boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    public void setCheckable(boolean z) {
        this.mInfo.setCheckable(z);
    }

    public boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public void setChecked(boolean z) {
        this.mInfo.setChecked(z);
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public void setFocusable(boolean z) {
        this.mInfo.setFocusable(z);
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public void setFocused(boolean z) {
        this.mInfo.setFocused(z);
    }

    public boolean isVisibleToUser() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.mInfo.isVisibleToUser();
        }
        return false;
    }

    public void setVisibleToUser(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setVisibleToUser(z);
        }
    }

    public boolean isAccessibilityFocused() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.mInfo.isAccessibilityFocused();
        }
        return false;
    }

    public void setAccessibilityFocused(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setAccessibilityFocused(z);
        }
    }

    public boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public void setSelected(boolean z) {
        this.mInfo.setSelected(z);
    }

    public boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public void setClickable(boolean z) {
        this.mInfo.setClickable(z);
    }

    public boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public void setLongClickable(boolean z) {
        this.mInfo.setLongClickable(z);
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public void setEnabled(boolean z) {
        this.mInfo.setEnabled(z);
    }

    public boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public void setScrollable(boolean z) {
        this.mInfo.setScrollable(z);
    }

    public CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    public void setPackageName(CharSequence charSequence) {
        this.mInfo.setPackageName(charSequence);
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public void setClassName(CharSequence charSequence) {
        this.mInfo.setClassName(charSequence);
    }

    public CharSequence getText() {
        if (!hasSpans()) {
            return this.mInfo.getText();
        }
        List<Integer> extrasIntList = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> extrasIntList2 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> extrasIntList3 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> extrasIntList4 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.mInfo.getText(), 0, this.mInfo.getText().length()));
        for (int i = 0; i < extrasIntList.size(); i++) {
            spannableString.setSpan(new AccessibilityClickableSpanCompat(extrasIntList4.get(i).intValue(), this, getExtras().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), extrasIntList.get(i).intValue(), extrasIntList2.get(i).intValue(), extrasIntList3.get(i).intValue());
        }
        return spannableString;
    }

    public void setText(CharSequence charSequence) {
        this.mInfo.setText(charSequence);
    }

    public void addSpansToExtras(CharSequence charSequence, View view) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 26) {
            clearExtrasSpans();
            removeCollectedSpans(view);
            ClickableSpan[] clickableSpans = getClickableSpans(charSequence);
            if (clickableSpans != null && clickableSpans.length > 0) {
                getExtras().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", R.id.accessibility_action_clickable_span);
                SparseArray<WeakReference<ClickableSpan>> orCreateSpansFromViewTags = getOrCreateSpansFromViewTags(view);
                int i = 0;
                while (clickableSpans != null && i < clickableSpans.length) {
                    int idForClickableSpan = idForClickableSpan(clickableSpans[i], orCreateSpansFromViewTags);
                    orCreateSpansFromViewTags.put(idForClickableSpan, new WeakReference<>(clickableSpans[i]));
                    addSpanLocationToExtras(clickableSpans[i], (Spanned) charSequence, idForClickableSpan);
                    i++;
                }
            }
        }
    }

    private SparseArray<WeakReference<ClickableSpan>> getOrCreateSpansFromViewTags(View view) {
        SparseArray<WeakReference<ClickableSpan>> spansFromViewTags = getSpansFromViewTags(view);
        if (spansFromViewTags != null) {
            return spansFromViewTags;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        view.setTag(R.id.tag_accessibility_clickable_spans, sparseArray);
        return sparseArray;
    }

    private SparseArray<WeakReference<ClickableSpan>> getSpansFromViewTags(View view) {
        return (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
    }

    public static ClickableSpan[] getClickableSpans(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    private int idForClickableSpan(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                if (clickableSpan.equals(sparseArray.valueAt(i).get())) {
                    return sparseArray.keyAt(i);
                }
            }
        }
        int i2 = sClickableSpanId;
        sClickableSpanId = i2 + 1;
        return i2;
    }

    private boolean hasSpans() {
        return !extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    private void clearExtrasSpans() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        }
    }

    private void addSpanLocationToExtras(ClickableSpan clickableSpan, Spanned spanned, int i) {
        extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i));
    }

    private void removeCollectedSpans(View view) {
        SparseArray<WeakReference<ClickableSpan>> spansFromViewTags = getSpansFromViewTags(view);
        if (spansFromViewTags != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < spansFromViewTags.size(); i++) {
                if (spansFromViewTags.valueAt(i).get() == null) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                spansFromViewTags.remove(((Integer) arrayList.get(i2)).intValue());
            }
        }
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public void setContentDescription(CharSequence charSequence) {
        this.mInfo.setContentDescription(charSequence);
    }

    public void recycle() {
        this.mInfo.recycle();
    }

    public String getViewIdResourceName() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.mInfo.getViewIdResourceName();
        }
        return null;
    }

    public void setCollectionInfo(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((CollectionInfoCompat) obj).mInfo);
        }
    }

    public void setCollectionItemInfo(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((CollectionItemInfoCompat) obj).mInfo);
        }
    }

    public List<AccessibilityActionCompat> getActionList() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = Build.VERSION.SDK_INT >= 21 ? this.mInfo.getActionList() : null;
        if (actionList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = actionList.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new AccessibilityActionCompat(actionList.get(i)));
        }
        return arrayList;
    }

    public void setContentInvalid(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setContentInvalid(z);
        }
    }

    public void setHintText(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setHintText(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charSequence);
        }
    }

    public void setError(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mInfo.setError(charSequence);
        }
    }

    public Bundle getExtras() {
        if (Build.VERSION.SDK_INT >= 19) {
            return this.mInfo.getExtras();
        }
        return new Bundle();
    }

    public void setDismissable(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setDismissable(z);
        }
    }

    public void setPaneTitle(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setPaneTitle(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    public void setScreenReaderFocusable(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setScreenReaderFocusable(z);
        } else {
            setBooleanProperty(1, z);
        }
    }

    public boolean isShowingHintText() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.mInfo.isShowingHintText();
        }
        return getBooleanProperty(4);
    }

    public void setShowingHintText(boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setShowingHintText(z);
        } else {
            setBooleanProperty(4, z);
        }
    }

    public void setHeading(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setHeading(z);
        } else {
            setBooleanProperty(2, z);
        }
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityNodeInfoCompat)) {
            return false;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            if (accessibilityNodeInfoCompat.mInfo != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(accessibilityNodeInfoCompat.mInfo)) {
            return false;
        }
        return this.mVirtualDescendantId == accessibilityNodeInfoCompat.mVirtualDescendantId && this.mParentVirtualDescendantId == accessibilityNodeInfoCompat.mParentVirtualDescendantId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        getBoundsInParent(rect);
        sb.append("; boundsInParent: " + rect);
        getBoundsInScreen(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(getPackageName());
        sb.append("; className: ");
        sb.append(getClassName());
        sb.append("; text: ");
        sb.append(getText());
        sb.append("; contentDescription: ");
        sb.append(getContentDescription());
        sb.append("; viewId: ");
        sb.append(getViewIdResourceName());
        sb.append("; checkable: ");
        sb.append(isCheckable());
        sb.append("; checked: ");
        sb.append(isChecked());
        sb.append("; focusable: ");
        sb.append(isFocusable());
        sb.append("; focused: ");
        sb.append(isFocused());
        sb.append("; selected: ");
        sb.append(isSelected());
        sb.append("; clickable: ");
        sb.append(isClickable());
        sb.append("; longClickable: ");
        sb.append(isLongClickable());
        sb.append("; enabled: ");
        sb.append(isEnabled());
        sb.append("; password: ");
        sb.append(isPassword());
        sb.append("; scrollable: " + isScrollable());
        sb.append("; [");
        if (Build.VERSION.SDK_INT >= 21) {
            List<AccessibilityActionCompat> actionList = getActionList();
            for (int i = 0; i < actionList.size(); i++) {
                AccessibilityActionCompat accessibilityActionCompat = actionList.get(i);
                String actionSymbolicName = getActionSymbolicName(accessibilityActionCompat.getId());
                if (actionSymbolicName.equals("ACTION_UNKNOWN") && accessibilityActionCompat.getLabel() != null) {
                    actionSymbolicName = accessibilityActionCompat.getLabel().toString();
                }
                sb.append(actionSymbolicName);
                if (i != actionList.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            int actions = getActions();
            while (actions != 0) {
                int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(actions);
                actions &= numberOfTrailingZeros ^ -1;
                sb.append(getActionSymbolicName(numberOfTrailingZeros));
                if (actions != 0) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void setBooleanProperty(int i, boolean z) {
        Bundle extras = getExtras();
        if (extras != null) {
            int i2 = extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (i ^ -1);
            if (!z) {
                i = 0;
            }
            extras.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }

    private boolean getBooleanProperty(int i) {
        Bundle extras = getExtras();
        if (extras != null && (extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & i) == i) {
            return true;
        }
        return false;
    }
}