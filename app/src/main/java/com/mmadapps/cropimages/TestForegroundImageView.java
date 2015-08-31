package com.mmadapps.cropimages;

/**
 * Created by gangadhar.g on 8/19/2015.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;


public class TestForegroundImageView extends ForegroundImageView {

    public TestForegroundImageView(Context context) {
        super(context);
    }

    public TestForegroundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestForegroundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public TestForegroundImageView(Context context, AttributeSet attrs,
                                                                            int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

     @Override protected boolean setFrame(int l, int t, int r, int b) {
        return super.setFrame(l, t, r, b);
    }
}

