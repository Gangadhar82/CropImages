package com.mmadapps.cropimages;

/**
 * Created by gangadhar.g on 8/19/2015.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author cesards
 */
public class ForegroundImageView extends ImageView {

    // UI
    private Drawable foreground;

    // Controller/logic fields
    private final Rect rectPadding = new Rect();
    private boolean foregroundPadding = false;
    private boolean foregroundBoundsChanged = false;
    private boolean backgroundAsForeground = false;

    // Constructors
    public ForegroundImageView(Context context) {
        super(context);
    }

    public ForegroundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ForegroundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ForegroundLayout, defStyle, 0);

        final Drawable d = a.getDrawable(R.styleable.ForegroundLayout_foreground);
        foregroundPadding = a.getBoolean(R.styleable.ForegroundLayout_foregroundInsidePadding, false);

        backgroundAsForeground = a.getBoolean(R.styleable.ForegroundLayout_backgroundAsForeground, false);

        // Apply foreground padding for ninepatches automatically
        if (!foregroundPadding && getBackground() instanceof NinePatchDrawable) {
            final NinePatchDrawable npd = (NinePatchDrawable) getBackground();
            if (npd != null && npd.getPadding(rectPadding)) {
                foregroundPadding = true;
            }
        }

        final Drawable b = getBackground();
        if (backgroundAsForeground && b != null) {
            setForeground(b);
        } else if (d != null) {
            setForeground(d);
        }

        a.recycle();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public ForegroundImageView(Context context, AttributeSet attrs,
                                                                        int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Supply a Drawable that is to be rendered on top of all of the child views in the layout.
     *
     * @param drawable The Drawable to be drawn on top of the children.
     */
    public void setForeground(Drawable drawable) {
        if (foreground != drawable) {
            if (foreground != null) {
                foreground.setCallback(null);
                unscheduleDrawable(foreground);
            }

            foreground = drawable;

            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    /**
     * Returns the drawable used as the foreground of this layout. The foreground drawable,
     * if non-null, is always drawn on top of the children.
     *
     * @return A Drawable or null if no foreground was set.
     */
    public Drawable getForeground() {
        return foreground;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (foreground != null && foreground.isStateful()) {
            foreground.setState(getDrawableState());
        }
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || (who == foreground);
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();

        if (foreground != null) {
            foreground.jumpToCurrentState();
        }
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        foregroundBoundsChanged = true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (foreground != null) {
            final Drawable foreground = this.foreground;

            if (foregroundBoundsChanged) {
                foregroundBoundsChanged = false;

                final int w = getRight() - getLeft();
                final int h = getBottom() - getTop();

                if (foregroundPadding) {
                    foreground.setBounds(rectPadding.left, rectPadding.top, w - rectPadding.right, h - rectPadding.bottom);
                } else {
                    foreground.setBounds(0, 0, w, h);
                }
            }
            foreground.draw(canvas);
        }
    }
}


