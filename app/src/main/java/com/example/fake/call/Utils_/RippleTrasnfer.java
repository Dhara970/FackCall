package com.example.fake.call.Utils_;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import com.example.fake.call.R;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class RippleTrasnfer extends RelativeLayout {
    private static final int DEFAULT_DURATION_TIME = 3000;
    private static final int DEFAULT_FILL_TYPE = 0;
    private static final int DEFAULT_RIPPLE_COUNT = 6;
    private static final float DEFAULT_SCALE = 6.0f;
    public static boolean Transfer_data;
    ObjectAnimator alphaAnimator;
    private ArrayList<Animator> animatorList;
    private AnimatorSet animatorSet;
    private Paint paint;
    private int rippleAmount;
    private int rippleColor;
    private int rippleDelay;
    private int rippleDurationTime;
    private LayoutParams rippleParams;
    private float rippleRadius;
    private float rippleScale;
    private float rippleStrokeWidth;
    private int rippleType;
    RippleView rippleView;
    ObjectAnimator scaleXAnimator;
    ObjectAnimator scaleYAnimator;
    private boolean animationRunning = false;
    public static final int[] RippleBackground = {R.attr.rb_color, R.attr.rb_duration, R.attr.rb_radius, R.attr.rb_rippleAmount, R.attr.rb_scale, R.attr.rb_strokeWidth, R.attr.rb_type};

    private ArrayList<RippleView> rippleViewList = new ArrayList<>();

    public RippleTrasnfer(Context context) {
        super(context);
    }

    public RippleTrasnfer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public RippleTrasnfer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    @SuppressLint("ResourceType")
    private void init(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, RippleBackground);
                this.rippleColor = obtainStyledAttributes.getColor(0, getResources().getColor(R.color.colorAccent));
                this.rippleStrokeWidth = obtainStyledAttributes.getDimension(5, getResources().getDimension(R.dimen.rippleStrokeWidth));
                this.rippleRadius = obtainStyledAttributes.getDimension(2, getResources().getDimension(R.dimen.rippleRadius));
                this.rippleDurationTime = obtainStyledAttributes.getInt(1, 3000);
                this.rippleAmount = obtainStyledAttributes.getInt(3, 6);
                this.rippleScale = obtainStyledAttributes.getFloat(4, DEFAULT_SCALE);
                this.rippleType = obtainStyledAttributes.getInt(6, 0);
                obtainStyledAttributes.recycle();
                this.rippleDelay = this.rippleDurationTime / this.rippleAmount;
                Paint paint = new Paint();
                this.paint = paint;
                paint.setAntiAlias(true);
                if (this.rippleType == 0) {
                    this.rippleStrokeWidth = 0.0f;
                    this.paint.setStyle(Paint.Style.FILL);
                } else {
                    this.paint.setStyle(Paint.Style.STROKE);
                }
                this.paint.setColor(this.rippleColor);
                float f = this.rippleRadius;
                float f2 = this.rippleStrokeWidth;
                LayoutParams layoutParams = new LayoutParams((int) ((f + f2) * 2.0f), (int) ((f + f2) * 2.0f));
                this.rippleParams = layoutParams;
                layoutParams.addRule(13, -1);
                AnimatorSet animatorSet = new AnimatorSet();
                this.animatorSet = animatorSet;
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                this.animatorList = new ArrayList<>();
                for (int i = 0; i < this.rippleAmount; i++) {
                    RippleView rippleView = new RippleView(getContext());
                    this.rippleView = rippleView;
                    addView(rippleView, this.rippleParams);
                    this.rippleViewList.add(this.rippleView);
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.rippleView, "ScaleX", 1.0f, this.rippleScale);
                    ofFloat.setRepeatCount(-1);
                    ofFloat.setRepeatMode(1);
                    ofFloat.setStartDelay((long) (this.rippleDelay * i));
                    ofFloat.setDuration((long) this.rippleDurationTime);
                    this.animatorList.add(ofFloat);
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.rippleView, "ScaleY", 1.0f, this.rippleScale);
                    ofFloat2.setRepeatCount(-1);
                    ofFloat2.setRepeatMode(1);
                    ofFloat2.setStartDelay((long) (this.rippleDelay * i));
                    ofFloat2.setDuration((long) this.rippleDurationTime);
                    this.animatorList.add(ofFloat2);
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.rippleView, "Alpha", 1.0f, 0.0f);
                    ofFloat3.setRepeatCount(-1);
                    ofFloat3.setRepeatMode(1);
                    ofFloat3.setStartDelay((long) (this.rippleDelay * i));
                    ofFloat3.setDuration((long) this.rippleDurationTime);
                    this.animatorList.add(ofFloat3);
                }
                this.animatorSet.playTogether(this.animatorList);
                return;
            }
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }
    }



    public class RippleView extends View {
        public RippleView(Context context) {
            super(context);
            setVisibility(INVISIBLE);
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            float min = (float) (Math.min(getWidth(), getHeight()) / 2);
            canvas.drawCircle(min, min, min - RippleTrasnfer.this.rippleStrokeWidth, RippleTrasnfer.this.paint);
        }
    }

    public void startRippleAnimation() {
        if (!isRippleAnimationRunning()) {
            Iterator<RippleView> it = this.rippleViewList.iterator();
            while (it.hasNext()) {
                it.next().setVisibility(View.VISIBLE);
            }
            this.animatorSet.start();
            this.animationRunning = true;
        }
    }

    public void stopRippleAnimation() {
        if (isRippleAnimationRunning()) {
            this.animatorSet.end();
            this.animationRunning = false;
        }
    }

    public boolean isRippleAnimationRunning() {
        return this.animationRunning;
    }
}
