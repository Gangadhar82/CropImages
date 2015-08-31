package com.mmadapps.cropimages;

/**
 * Created by gangadhar.g on 8/19/2015.
 */

import android.content.res.Resources;
import android.widget.ImageView;


public class SimpleCropActivity extends CropActivity {

    private int[] images = {
            R.drawable.zombie,
            R.drawable.images,
    };

    @Override protected int getImagesCount() {
        return images.length;
    }

    @Override protected ImageView instantiatePagerItem(int position) {
        TestForegroundImageView testForegroundImageView = new TestForegroundImageView(SimpleCropActivity.this);
        final Resources res = getResources();
        testForegroundImageView.setImageDrawable(res.getDrawable(images[position]));
        testForegroundImageView.setForeground(res.getDrawable(R.drawable.shape_grad_black_transp_70));
        testForegroundImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return testForegroundImageView;
    }
}

