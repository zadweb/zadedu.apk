package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import java.util.ArrayList;

public class ConstraintWidget {
    public static float DEFAULT_BIAS = 0.5f;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
    int mBaselineDistance;
    ConstraintWidgetGroup mBelongingGroup = null;
    ConstraintAnchor mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
    ConstraintAnchor mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
    private float mCircleConstraintAngle = 0.0f;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    boolean mGroupsToSolver;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution = -1;
    boolean mHorizontalWrapVisited;
    boolean mIsHeightWrapContent;
    boolean mIsWidthWrapContent;
    ConstraintAnchor mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
    protected ConstraintAnchor[] mListAnchors;
    protected DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    int mMatchConstraintDefaultHeight = 0;
    int mMatchConstraintDefaultWidth = 0;
    int mMatchConstraintMaxHeight = 0;
    int mMatchConstraintMaxWidth = 0;
    int mMatchConstraintMinHeight = 0;
    int mMatchConstraintMinWidth = 0;
    float mMatchConstraintPercentHeight = 1.0f;
    float mMatchConstraintPercentWidth = 1.0f;
    private int[] mMaxDimension = {Integer.MAX_VALUE, Integer.MAX_VALUE};
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    boolean mOptimizerMeasurable;
    boolean mOptimizerMeasured;
    ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    ResolutionDimension mResolutionHeight;
    ResolutionDimension mResolutionWidth;
    float mResolvedDimensionRatio = 1.0f;
    int mResolvedDimensionRatioSide = -1;
    int[] mResolvedMatchConstraintDefault = new int[2];
    ConstraintAnchor mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
    ConstraintAnchor mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution = -1;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    float[] mWeight;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX;
    protected int mY;

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public void resolve() {
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        ResolutionDimension resolutionDimension = this.mResolutionWidth;
        if (resolutionDimension != null) {
            resolutionDimension.reset();
        }
        ResolutionDimension resolutionDimension2 = this.mResolutionHeight;
        if (resolutionDimension2 != null) {
            resolutionDimension2.reset();
        }
        this.mBelongingGroup = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
    }

    public void resetResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().reset();
        }
    }

    public void updateResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().update();
        }
    }

    public void analyze(int i) {
        Optimizer.analyze(i, this);
    }

    public boolean isFullyResolved() {
        if (this.mLeft.getResolutionNode().state == 1 && this.mRight.getResolutionNode().state == 1 && this.mTop.getResolutionNode().state == 1 && this.mBottom.getResolutionNode().state == 1) {
            return true;
        }
        return false;
    }

    public ResolutionDimension getResolutionWidth() {
        if (this.mResolutionWidth == null) {
            this.mResolutionWidth = new ResolutionDimension();
        }
        return this.mResolutionWidth;
    }

    public ResolutionDimension getResolutionHeight() {
        if (this.mResolutionHeight == null) {
            this.mResolutionHeight = new ResolutionDimension();
        }
        return this.mResolutionHeight;
    }

    public ConstraintWidget() {
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(ConstraintAnchor.Type.CENTER, constraintWidget, ConstraintAnchor.Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (this.mType != null) {
            str = "type: " + this.mType + " ";
        } else {
            str = str2;
        }
        sb.append(str);
        if (this.mDebugName != null) {
            str2 = "id: " + this.mDebugName + " ";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.mX);
        sb.append(", ");
        sb.append(this.mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(") wrap: (");
        sb.append(this.mWrapWidth);
        sb.append(" x ");
        sb.append(this.mWrapHeight);
        sb.append(")");
        return sb.toString();
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getLength(int i) {
        if (i == 0) {
            return getWidth();
        }
        if (i == 1) {
            return getHeight();
        }
        return 0;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    /* access modifiers changed from: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* access modifiers changed from: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public float getBiasPercent(int i) {
        if (i == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (i == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void updateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = (this.mWidth + i) - i;
        this.mDrawHeight = (this.mHeight + i2) - i2;
    }

    public void setWidth(int i) {
        this.mWidth = i;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f < 1.0f && i == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f < 1.0f && i == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    public void setDimensionRatio(String str) {
        float f;
        if (str == null || str.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int i = -1;
        int length = str.length();
        int indexOf = str.indexOf(44);
        int i2 = 0;
        if (indexOf > 0 && indexOf < length - 1) {
            String substring = str.substring(0, indexOf);
            if (substring.equalsIgnoreCase("W")) {
                i = 0;
            } else if (substring.equalsIgnoreCase("H")) {
                i = 1;
            }
            i2 = indexOf + 1;
        }
        int indexOf2 = str.indexOf(58);
        if (indexOf2 < 0 || indexOf2 >= length - 1) {
            String substring2 = str.substring(i2);
            if (substring2.length() > 0) {
                f = Float.parseFloat(substring2);
                if (f > 0.0f) {
                    this.mDimensionRatio = f;
                    this.mDimensionRatioSide = i;
                    return;
                }
                return;
            }
        } else {
            String substring3 = str.substring(i2, indexOf2);
            String substring4 = str.substring(indexOf2 + 1);
            if (substring3.length() > 0 && substring4.length() > 0) {
                try {
                    float parseFloat = Float.parseFloat(substring3);
                    float parseFloat2 = Float.parseFloat(substring4);
                    if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                        f = i == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                        if (f > 0.0f) {
                        }
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        f = 0.0f;
        if (f > 0.0f) {
        }
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setWrapWidth(int i) {
        this.mWrapWidth = i;
    }

    public void setWrapHeight(int i) {
        this.mWrapHeight = i;
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i7 < (i6 = this.mWidth)) {
            i7 = i6;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i8 < (i5 = this.mHeight)) {
            i8 = i5;
        }
        this.mWidth = i7;
        this.mHeight = i8;
        int i9 = this.mMinHeight;
        if (i8 < i9) {
            this.mHeight = i9;
        }
        int i10 = this.mWidth;
        int i11 = this.mMinWidth;
        if (i10 < i11) {
            this.mWidth = i11;
        }
        this.mOptimizerMeasured = true;
    }

    public void setFrame(int i, int i2, int i3) {
        if (i3 == 0) {
            setHorizontalDimension(i, i2);
        } else if (i3 == 1) {
            setVerticalDimension(i, i2);
        }
        this.mOptimizerMeasured = true;
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        int i3 = i2 - i;
        this.mWidth = i3;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        int i3 = i2 - i;
        this.mHeight = i3;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    /* access modifiers changed from: package-private */
    public int getRelativePositioning(int i) {
        if (i == 0) {
            return this.mRelX;
        }
        if (i == 1) {
            return this.mRelY;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void setRelativePositioning(int i, int i2) {
        if (i2 == 0) {
            this.mRelX = i;
        } else if (i2 == 1) {
            this.mRelY = i;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, ConstraintAnchor.Strength.STRONG, 0, true);
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                this.mAnchors.get(i).reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.mCenterX;
            case 8:
                return this.mCenterY;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int i) {
        if (i == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public boolean isInHorizontalChain() {
        if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) {
            return this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight;
        }
        return true;
    }

    public boolean isInVerticalChain() {
        if (this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) {
            return this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom;
        }
        return true;
    }

    private boolean isChainHead(int i) {
        int i2 = i * 2;
        if (this.mListAnchors[i2].mTarget != null) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[i2].mTarget.mTarget;
            ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
            if (constraintAnchor != constraintAnchorArr[i2]) {
                int i3 = i2 + 1;
                return constraintAnchorArr[i3].mTarget != null && this.mListAnchors[i3].mTarget.mTarget == this.mListAnchors[i3];
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0241  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0252 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0253  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02b8  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02c7  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x02cf  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0306  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0339  */
    /* JADX WARNING: Removed duplicated region for block: B:166:? A[RETURN, SYNTHETIC] */
    public void addToSolver(LinearSystem linearSystem) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        int i2;
        boolean z5;
        int i3;
        int i4;
        SolverVariable solverVariable;
        boolean z6;
        SolverVariable solverVariable2;
        SolverVariable solverVariable3;
        boolean z7;
        SolverVariable solverVariable4;
        SolverVariable solverVariable5;
        boolean z8;
        LinearSystem linearSystem2;
        SolverVariable solverVariable6;
        ConstraintWidget constraintWidget;
        int i5;
        boolean z9;
        boolean z10;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
        ConstraintWidget constraintWidget2 = this.mParent;
        if (constraintWidget2 != null) {
            z4 = constraintWidget2 != null && constraintWidget2.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
            ConstraintWidget constraintWidget3 = this.mParent;
            boolean z11 = constraintWidget3 != null && constraintWidget3.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT;
            if (isChainHead(0)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 0);
                z9 = true;
            } else {
                z9 = isInHorizontalChain();
            }
            if (isChainHead(1)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 1);
                z10 = true;
            } else {
                z10 = isInVerticalChain();
            }
            if (z4 && this.mVisibility != 8 && this.mLeft.mTarget == null && this.mRight.mTarget == null) {
                linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mRight), createObjectVariable2, 0, 1);
            }
            if (z11 && this.mVisibility != 8 && this.mTop.mTarget == null && this.mBottom.mTarget == null && this.mBaseline == null) {
                linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mBottom), createObjectVariable4, 0, 1);
            }
            z3 = z11;
            z2 = z9;
            z = z10;
        } else {
            z4 = false;
            z3 = false;
            z2 = false;
            z = false;
        }
        int i6 = this.mWidth;
        int i7 = this.mMinWidth;
        if (i6 < i7) {
            i6 = i7;
        }
        int i8 = this.mHeight;
        int i9 = this.mMinHeight;
        if (i8 < i9) {
            i8 = i9;
        }
        boolean z12 = this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z13 = this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT;
        this.mResolvedDimensionRatioSide = this.mDimensionRatioSide;
        float f = this.mDimensionRatio;
        this.mResolvedDimensionRatio = f;
        int i10 = this.mMatchConstraintDefaultWidth;
        int i11 = this.mMatchConstraintDefaultHeight;
        if (f <= 0.0f || this.mVisibility == 8) {
            solverVariable = createObjectVariable5;
            i2 = i10;
            i4 = i6;
            i3 = i8;
            i = i11;
            z5 = false;
        } else {
            solverVariable = createObjectVariable5;
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && i10 == 0) {
                i10 = 3;
            }
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && i11 == 0) {
                i11 = 3;
            }
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && i10 == 3 && i11 == 3) {
                setupDimensionRatio(z4, z3, z12, z13);
            } else if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && i10 == 3) {
                this.mResolvedDimensionRatioSide = 0;
                DimensionBehaviour dimensionBehaviour = this.mListDimensionBehaviors[1];
                DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.MATCH_CONSTRAINT;
                i4 = (int) (this.mResolvedDimensionRatio * ((float) this.mHeight));
                if (dimensionBehaviour != dimensionBehaviour2) {
                    i3 = i8;
                    i = i11;
                    z5 = false;
                    i2 = 4;
                } else {
                    i2 = i10;
                    i3 = i8;
                    i = i11;
                    z5 = true;
                }
            } else if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && i11 == 3) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
                DimensionBehaviour dimensionBehaviour3 = this.mListDimensionBehaviors[0];
                DimensionBehaviour dimensionBehaviour4 = DimensionBehaviour.MATCH_CONSTRAINT;
                i3 = (int) (this.mResolvedDimensionRatio * ((float) this.mWidth));
                i2 = i10;
                i4 = i6;
                if (dimensionBehaviour3 != dimensionBehaviour4) {
                    z5 = false;
                    i = 4;
                }
                i = i11;
                z5 = true;
            }
            i2 = i10;
            i4 = i6;
            i3 = i8;
            i = i11;
            z5 = true;
        }
        int[] iArr = this.mResolvedMatchConstraintDefault;
        iArr[0] = i2;
        iArr[1] = i;
        if (z5) {
            int i12 = this.mResolvedDimensionRatioSide;
            if (i12 == 0 || i12 == -1) {
                z6 = true;
                boolean z14 = this.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
                boolean z15 = !this.mCenter.isConnected();
                if (this.mHorizontalResolution == 2) {
                    ConstraintWidget constraintWidget4 = this.mParent;
                    SolverVariable createObjectVariable6 = constraintWidget4 != null ? linearSystem.createObjectVariable(constraintWidget4.mRight) : null;
                    ConstraintWidget constraintWidget5 = this.mParent;
                    z7 = z3;
                    solverVariable3 = solverVariable;
                    solverVariable5 = createObjectVariable4;
                    solverVariable2 = createObjectVariable3;
                    solverVariable4 = createObjectVariable2;
                    applyConstraints(linearSystem, z4, constraintWidget5 != null ? linearSystem.createObjectVariable(constraintWidget5.mLeft) : null, createObjectVariable6, this.mListDimensionBehaviors[0], z14, this.mLeft, this.mRight, this.mX, i4, this.mMinWidth, this.mMaxDimension[0], this.mHorizontalBiasPercent, z6, z2, i2, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, z15);
                } else {
                    solverVariable2 = createObjectVariable3;
                    solverVariable4 = createObjectVariable2;
                    z7 = z3;
                    solverVariable3 = solverVariable;
                    solverVariable5 = createObjectVariable4;
                }
                if (this.mVerticalResolution == 2) {
                    boolean z16 = this.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
                    boolean z17 = z5 && ((i5 = this.mResolvedDimensionRatioSide) == 1 || i5 == -1);
                    if (this.mBaselineDistance <= 0) {
                        linearSystem2 = linearSystem;
                    } else if (this.mBaseline.getResolutionNode().state == 1) {
                        linearSystem2 = linearSystem;
                        this.mBaseline.getResolutionNode().addResolvedValue(linearSystem2);
                    } else {
                        linearSystem2 = linearSystem;
                        solverVariable6 = solverVariable2;
                        linearSystem2.addEquality(solverVariable3, solverVariable6, getBaselineDistance(), 6);
                        if (this.mBaseline.mTarget != null) {
                            linearSystem2.addEquality(solverVariable3, linearSystem2.createObjectVariable(this.mBaseline.mTarget), 0, 6);
                            z8 = false;
                            ConstraintWidget constraintWidget6 = this.mParent;
                            SolverVariable createObjectVariable7 = constraintWidget6 != null ? linearSystem2.createObjectVariable(constraintWidget6.mBottom) : null;
                            ConstraintWidget constraintWidget7 = this.mParent;
                            applyConstraints(linearSystem, z7, constraintWidget7 != null ? linearSystem2.createObjectVariable(constraintWidget7.mTop) : null, createObjectVariable7, this.mListDimensionBehaviors[1], z16, this.mTop, this.mBottom, this.mY, i3, this.mMinHeight, this.mMaxDimension[1], this.mVerticalBiasPercent, z17, z, i, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, z8);
                            if (z5) {
                                constraintWidget = this;
                                if (constraintWidget.mResolvedDimensionRatioSide == 1) {
                                    linearSystem.addRatio(solverVariable5, solverVariable6, solverVariable4, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                                } else {
                                    linearSystem.addRatio(solverVariable4, createObjectVariable, solverVariable5, solverVariable6, constraintWidget.mResolvedDimensionRatio, 6);
                                }
                            } else {
                                constraintWidget = this;
                            }
                            if (constraintWidget.mCenter.isConnected()) {
                                linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (constraintWidget.mCircleConstraintAngle + 90.0f)), constraintWidget.mCenter.getMargin());
                                return;
                            }
                            return;
                        }
                        z8 = z15;
                        ConstraintWidget constraintWidget62 = this.mParent;
                        if (constraintWidget62 != null) {
                        }
                        ConstraintWidget constraintWidget72 = this.mParent;
                        applyConstraints(linearSystem, z7, constraintWidget72 != null ? linearSystem2.createObjectVariable(constraintWidget72.mTop) : null, createObjectVariable7, this.mListDimensionBehaviors[1], z16, this.mTop, this.mBottom, this.mY, i3, this.mMinHeight, this.mMaxDimension[1], this.mVerticalBiasPercent, z17, z, i, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, z8);
                        if (z5) {
                        }
                        if (constraintWidget.mCenter.isConnected()) {
                        }
                    }
                    solverVariable6 = solverVariable2;
                    z8 = z15;
                    ConstraintWidget constraintWidget622 = this.mParent;
                    if (constraintWidget622 != null) {
                    }
                    ConstraintWidget constraintWidget722 = this.mParent;
                    applyConstraints(linearSystem, z7, constraintWidget722 != null ? linearSystem2.createObjectVariable(constraintWidget722.mTop) : null, createObjectVariable7, this.mListDimensionBehaviors[1], z16, this.mTop, this.mBottom, this.mY, i3, this.mMinHeight, this.mMaxDimension[1], this.mVerticalBiasPercent, z17, z, i, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, z8);
                    if (z5) {
                    }
                    if (constraintWidget.mCenter.isConnected()) {
                    }
                } else {
                    return;
                }
            }
        }
        z6 = false;
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT) {
        }
        boolean z152 = !this.mCenter.isConnected();
        if (this.mHorizontalResolution == 2) {
        }
        if (this.mVerticalResolution == 2) {
        }
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z && !z2) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z && z2) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1 && z && z2) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:153:0x0291  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x02d6  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02e5  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0306  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x030f  */
    /* JADX WARNING: Removed duplicated region for block: B:186:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01cc A[ADDED_TO_REGION] */
    private void applyConstraints(LinearSystem linearSystem, boolean z, SolverVariable solverVariable, SolverVariable solverVariable2, DimensionBehaviour dimensionBehaviour, boolean z2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2, int i3, int i4, float f, boolean z3, boolean z4, int i5, int i6, int i7, float f2, boolean z5) {
        boolean z6;
        int i8;
        int i9;
        int i10;
        SolverVariable solverVariable3;
        boolean z7;
        int i11;
        int i12;
        SolverVariable solverVariable4;
        int i13;
        SolverVariable solverVariable5;
        int i14;
        int i15;
        boolean z8;
        SolverVariable solverVariable6;
        SolverVariable solverVariable7;
        boolean z9;
        boolean z10;
        SolverVariable solverVariable8;
        boolean z11;
        int i16;
        int i17;
        SolverVariable solverVariable9;
        boolean z12;
        int i18;
        int i19;
        boolean z13;
        boolean z14;
        SolverVariable solverVariable10;
        SolverVariable solverVariable11;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintAnchor);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(constraintAnchor2);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(constraintAnchor.getTarget());
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(constraintAnchor2.getTarget());
        if (linearSystem.graphOptimizer && constraintAnchor.getResolutionNode().state == 1 && constraintAnchor2.getResolutionNode().state == 1) {
            if (LinearSystem.getMetrics() != null) {
                LinearSystem.getMetrics().resolvedWidgets++;
            }
            constraintAnchor.getResolutionNode().addResolvedValue(linearSystem);
            constraintAnchor2.getResolutionNode().addResolvedValue(linearSystem);
            if (!z4 && z) {
                linearSystem.addGreaterThan(solverVariable2, createObjectVariable2, 0, 6);
                return;
            }
            return;
        }
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().nonresolvedWidgets++;
        }
        boolean isConnected = constraintAnchor.isConnected();
        boolean isConnected2 = constraintAnchor2.isConnected();
        boolean isConnected3 = this.mCenter.isConnected();
        int i20 = isConnected ? 1 : 0;
        if (isConnected2) {
            i20++;
        }
        if (isConnected3) {
            i20++;
        }
        int i21 = z3 ? 3 : i5;
        int i22 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[dimensionBehaviour.ordinal()];
        boolean z15 = (i22 == 1 || i22 == 2 || i22 == 3 || i22 != 4 || i21 == 4) ? false : true;
        if (this.mVisibility == 8) {
            i8 = 0;
            z6 = false;
        } else {
            z6 = z15;
            i8 = i2;
        }
        if (z5) {
            if (!isConnected && !isConnected2 && !isConnected3) {
                linearSystem.addEquality(createObjectVariable, i);
            } else if (isConnected && !isConnected2) {
                i9 = 6;
                linearSystem.addEquality(createObjectVariable, createObjectVariable3, constraintAnchor.getMargin(), 6);
                if (z6) {
                    if (z2) {
                        linearSystem.addEquality(createObjectVariable2, createObjectVariable, 0, 3);
                        if (i3 > 0) {
                            linearSystem.addGreaterThan(createObjectVariable2, createObjectVariable, i3, 6);
                        }
                        if (i4 < Integer.MAX_VALUE) {
                            linearSystem.addLowerThan(createObjectVariable2, createObjectVariable, i4, 6);
                        }
                    } else {
                        linearSystem.addEquality(createObjectVariable2, createObjectVariable, i8, i9);
                    }
                    i11 = i6;
                    i10 = i21;
                    i13 = i20;
                    solverVariable4 = createObjectVariable4;
                    solverVariable3 = createObjectVariable3;
                    z7 = z6;
                    i12 = i7;
                } else {
                    i11 = i6;
                    int i23 = i7;
                    if (i11 == -2) {
                        i11 = i8;
                    }
                    if (i23 == -2) {
                        i23 = i8;
                    }
                    if (i11 > 0) {
                        linearSystem.addGreaterThan(createObjectVariable2, createObjectVariable, i11, 6);
                        i8 = Math.max(i8, i11);
                    }
                    if (i23 > 0) {
                        linearSystem.addLowerThan(createObjectVariable2, createObjectVariable, i23, 6);
                        i8 = Math.min(i8, i23);
                    }
                    if (i21 != 1) {
                        z14 = z6;
                        if (i21 == 2) {
                            if (constraintAnchor.getType() == ConstraintAnchor.Type.TOP || constraintAnchor.getType() == ConstraintAnchor.Type.BOTTOM) {
                                solverVariable10 = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.TOP));
                                solverVariable11 = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.BOTTOM));
                            } else {
                                solverVariable10 = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.LEFT));
                                solverVariable11 = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.RIGHT));
                            }
                            solverVariable3 = createObjectVariable3;
                            i19 = i8;
                            i10 = i21;
                            i13 = i20;
                            i12 = i23;
                            solverVariable4 = createObjectVariable4;
                            linearSystem.addConstraint(linearSystem.createRow().createRowDimensionRatio(createObjectVariable2, createObjectVariable, solverVariable11, solverVariable10, f2));
                            z13 = false;
                            if (z13) {
                            }
                            z7 = z13;
                        }
                    } else if (z) {
                        linearSystem.addEquality(createObjectVariable2, createObjectVariable, i8, 6);
                        i10 = i21;
                        i13 = i20;
                        solverVariable4 = createObjectVariable4;
                        solverVariable3 = createObjectVariable3;
                        z14 = z6;
                        i19 = i8;
                        i12 = i23;
                        z13 = z14;
                        if (z13 || i13 == 2 || z3) {
                            z7 = z13;
                        } else {
                            int max = Math.max(i11, i19);
                            if (i12 > 0) {
                                max = Math.min(i12, max);
                            }
                            linearSystem.addEquality(createObjectVariable2, createObjectVariable, max, 6);
                            z7 = false;
                        }
                    } else if (z4) {
                        z14 = z6;
                        linearSystem.addEquality(createObjectVariable2, createObjectVariable, i8, 4);
                    } else {
                        z14 = z6;
                        linearSystem.addEquality(createObjectVariable2, createObjectVariable, i8, 1);
                    }
                    i10 = i21;
                    i13 = i20;
                    i12 = i23;
                    solverVariable4 = createObjectVariable4;
                    solverVariable3 = createObjectVariable3;
                    i19 = i8;
                    z13 = z14;
                    if (z13) {
                    }
                    z7 = z13;
                }
                if (!z5 && !z4) {
                    if (isConnected || isConnected2 || isConnected3) {
                        if (!isConnected || isConnected2) {
                            if (!isConnected && isConnected2) {
                                linearSystem.addEquality(createObjectVariable2, solverVariable4, -constraintAnchor2.getMargin(), 6);
                                if (z) {
                                    linearSystem.addGreaterThan(createObjectVariable, solverVariable, 0, 5);
                                }
                            } else if (isConnected && isConnected2) {
                                if (z7) {
                                    solverVariable6 = solverVariable4;
                                    if (z && i3 == 0) {
                                        linearSystem.addGreaterThan(createObjectVariable2, createObjectVariable, 0, 6);
                                    }
                                    if (i10 == 0) {
                                        if (i12 > 0 || i11 > 0) {
                                            i18 = 4;
                                            z12 = true;
                                        } else {
                                            i18 = 6;
                                            z12 = false;
                                        }
                                        solverVariable7 = solverVariable3;
                                        linearSystem.addEquality(createObjectVariable, solverVariable7, constraintAnchor.getMargin(), i18);
                                        linearSystem.addEquality(createObjectVariable2, solverVariable6, -constraintAnchor2.getMargin(), i18);
                                        z9 = i12 > 0 || i11 > 0;
                                        z8 = z12;
                                        i15 = 5;
                                    } else {
                                        solverVariable7 = solverVariable3;
                                        if (i10 == 1) {
                                            z9 = true;
                                            z8 = true;
                                            i15 = 6;
                                        } else if (i10 == 3) {
                                            int i24 = (z3 || this.mResolvedDimensionRatioSide == -1 || i12 > 0) ? 4 : 6;
                                            linearSystem.addEquality(createObjectVariable, solverVariable7, constraintAnchor.getMargin(), i24);
                                            linearSystem.addEquality(createObjectVariable2, solverVariable6, -constraintAnchor2.getMargin(), i24);
                                            z9 = true;
                                            z8 = true;
                                            i15 = 5;
                                            if (z9) {
                                                solverVariable8 = solverVariable6;
                                                solverVariable9 = solverVariable7;
                                                solverVariable5 = createObjectVariable2;
                                                linearSystem.addCentering(createObjectVariable, solverVariable7, constraintAnchor.getMargin(), f, solverVariable6, createObjectVariable2, constraintAnchor2.getMargin(), i15);
                                                boolean z16 = constraintAnchor.mTarget.mOwner instanceof Barrier;
                                                boolean z17 = constraintAnchor2.mTarget.mOwner instanceof Barrier;
                                                if (z16 && !z17) {
                                                    z11 = z;
                                                    i17 = 6;
                                                    i16 = 5;
                                                    z10 = true;
                                                    if (z8) {
                                                    }
                                                    linearSystem.addGreaterThan(createObjectVariable, solverVariable9, constraintAnchor.getMargin(), i16);
                                                    linearSystem.addLowerThan(solverVariable5, solverVariable8, -constraintAnchor2.getMargin(), i17);
                                                    i14 = 0;
                                                    if (z) {
                                                    }
                                                    if (!z) {
                                                    }
                                                } else if (!z16 && z17) {
                                                    z10 = z;
                                                    i17 = 5;
                                                    i16 = 6;
                                                    z11 = true;
                                                    if (z8) {
                                                        i17 = 6;
                                                        i16 = 6;
                                                    }
                                                    if ((!z7 && z11) || z8) {
                                                        linearSystem.addGreaterThan(createObjectVariable, solverVariable9, constraintAnchor.getMargin(), i16);
                                                    }
                                                    if ((!z7 && z10) || z8) {
                                                        linearSystem.addLowerThan(solverVariable5, solverVariable8, -constraintAnchor2.getMargin(), i17);
                                                    }
                                                    i14 = 0;
                                                    if (z) {
                                                        linearSystem.addGreaterThan(createObjectVariable, solverVariable, 0, 6);
                                                    }
                                                    if (!z) {
                                                        linearSystem.addGreaterThan(solverVariable2, solverVariable5, i14, 6);
                                                        return;
                                                    }
                                                    return;
                                                }
                                            } else {
                                                solverVariable9 = solverVariable7;
                                                solverVariable8 = solverVariable6;
                                                solverVariable5 = createObjectVariable2;
                                            }
                                            z11 = z;
                                            z10 = z11;
                                            i17 = 5;
                                            i16 = 5;
                                            if (z8) {
                                            }
                                            linearSystem.addGreaterThan(createObjectVariable, solverVariable9, constraintAnchor.getMargin(), i16);
                                            linearSystem.addLowerThan(solverVariable5, solverVariable8, -constraintAnchor2.getMargin(), i17);
                                            i14 = 0;
                                            if (z) {
                                            }
                                            if (!z) {
                                            }
                                        } else {
                                            z9 = false;
                                        }
                                    }
                                    if (z9) {
                                    }
                                    z11 = z;
                                    z10 = z11;
                                    i17 = 5;
                                    i16 = 5;
                                    if (z8) {
                                    }
                                    linearSystem.addGreaterThan(createObjectVariable, solverVariable9, constraintAnchor.getMargin(), i16);
                                    linearSystem.addLowerThan(solverVariable5, solverVariable8, -constraintAnchor2.getMargin(), i17);
                                    i14 = 0;
                                    if (z) {
                                    }
                                    if (!z) {
                                    }
                                } else {
                                    solverVariable6 = solverVariable4;
                                    solverVariable7 = solverVariable3;
                                    z9 = true;
                                }
                                z8 = false;
                                i15 = 5;
                                if (z9) {
                                }
                                z11 = z;
                                z10 = z11;
                                i17 = 5;
                                i16 = 5;
                                if (z8) {
                                }
                                linearSystem.addGreaterThan(createObjectVariable, solverVariable9, constraintAnchor.getMargin(), i16);
                                linearSystem.addLowerThan(solverVariable5, solverVariable8, -constraintAnchor2.getMargin(), i17);
                                i14 = 0;
                                if (z) {
                                }
                                if (!z) {
                                }
                            }
                        } else if (z) {
                            linearSystem.addGreaterThan(solverVariable2, createObjectVariable2, 0, 5);
                        }
                    } else if (z) {
                        linearSystem.addGreaterThan(solverVariable2, createObjectVariable2, 0, 5);
                    }
                    solverVariable5 = createObjectVariable2;
                    i14 = 0;
                    if (!z) {
                    }
                } else if (i13 < 2 && z) {
                    linearSystem.addGreaterThan(createObjectVariable, solverVariable, 0, 6);
                    linearSystem.addGreaterThan(solverVariable2, createObjectVariable2, 0, 6);
                    return;
                }
            }
        }
        i9 = 6;
        if (z6) {
        }
        if (!z5) {
        }
        if (i13 < 2) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.solver.widgets.ConstraintWidget$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type;
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour;

        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0083 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x008f */
        static {
            int[] iArr = new int[DimensionBehaviour.values().length];
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour = iArr;
            try {
                iArr[DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ConstraintAnchor.Type.values().length];
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type = iArr2;
            iArr2[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        int i = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue4 = 0;
            objectVariableValue = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }
}
