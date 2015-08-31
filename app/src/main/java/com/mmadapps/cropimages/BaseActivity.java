package com.mmadapps.cropimages;

/**
 * Created by gangadhar.g on 8/19/2015.
 */

import android.app.Activity;
import android.os.Bundle;

/**
 * @author cesards
 */
public abstract class BaseActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.switchToLowProfile();
    }

    private void switchToLowProfile() {
        final SystemUiHelper systemUiHelper =
                new SystemUiHelper(this, SystemUiHelper.LEVEL_LOW_PROFILE, SystemUiHelper.FLAG_IMMERSIVE_STICKY);
        systemUiHelper.hide();
    }
}

